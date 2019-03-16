package com.centyun.base.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centyun.base.BaseApplication;
import com.centyun.base.constant.BaseConstant;
import com.centyun.base.domain.Dwz;
import com.centyun.base.mapper.DwzMapper;
import com.centyun.base.service.DwzService;
import com.centyun.base.util.DwzGenerator;
import com.centyun.base.util.QRCodeUtil;
import com.centyun.core.constant.AppConstant;
import com.centyun.core.domain.User;
import com.centyun.core.exception.BadRequestException;
import com.centyun.core.table.DataTableParam;
import com.centyun.core.table.KeyValuePair;
import com.centyun.core.util.RandomGenerator;
import com.centyun.core.util.SnowFlakeIdWorker;
import com.centyun.core.util.file.FileUploadUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class DwzServiceImpl implements DwzService {
    
    private Logger log = LoggerFactory.getLogger(DwzServiceImpl.class);
    
    private static final String DWZ_PREFIX = "d/";
    
    @Value("${BASE_URL}")
    private String baseUrl;

    @Value("${UPLOAD_DIR}")
    private String uploadDir;

    @Autowired
    private DwzMapper dwzMapper;

    @Override
    public PageInfo<Dwz> listPageDwzs(DataTableParam dataTableParam) {
        PageHelper.startPage(dataTableParam.getPageNum(), dataTableParam.getLength());
        String searchValue = dataTableParam.getSearchValue();
        List<KeyValuePair> orders = dataTableParam.getOrders();
        List<Dwz> users = dwzMapper.listPageDwzs(StringUtils.isEmpty(searchValue) ? null : searchValue,
                CollectionUtils.isEmpty(orders) ? null : orders);
        PageInfo<Dwz> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public Dwz getDwzById(String dwzId) {
        return dwzMapper.getDwzById(dwzId);
    }

    /**
     * 如果没有找到就根据长网址创建一个短网址，如果找到了短网址则返回该短网址
     */
    @Override
    public Dwz getDwzByLongUrl(Dwz dwz, User user) {
        if(dwz == null || StringUtils.isEmpty(dwz.getLongUrl())) {
            throw new BadRequestException(BaseConstant.DWZ_LONGURL_REQUIRED);
        }
        String longUrl = dwz.getLongUrl();
        dwz = null;
        Dwz checkDwz = dwzMapper.getDwzByUrl(longUrl);
        if(checkDwz == null) {
            // 如果没有找到就创建一个
            // 计算code， shortUrl 和 qrcode, 并保存到数据库
            checkDwz = getDwzCode(longUrl, user, baseUrl);
        }
        return checkDwz;
    }
    
    private void setDwzValue(Dwz checkDwz, User user) {
        // 设置id及用户和租户信息
        SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(BaseConstant.DATACENTER_ID, BaseConstant.MACHINE_ID);
        checkDwz.setId(snowFlake.nextId());
        checkDwz.setCreator(user.getId());
        checkDwz.setCreatorName(user.getDisplayName());
        checkDwz.setTenantId(user.getTenantId());
        checkDwz.setTenantName(user.getTenantName());
    }

    private Dwz getDwzCode(String longUrl, User user, String dwzPrefix) {
        // 一个长链接可生成多个短网址code
        String[] dwzCodes = DwzGenerator.dwz(longUrl);
        for (String dwzCode : dwzCodes) {
            Dwz codeDwz = getDwzByRandom(longUrl, user, dwzPrefix, dwzCode);
            if(codeDwz != null) {
                return codeDwz;
            }
        }
        
        // 如果上面的算法不行，则生成随机的6位码进行验证，直到找到可用的随机码为止
        while(true) {
            String code = RandomGenerator.getString(6);
            Dwz codeDwz = getDwzByRandom(longUrl, user, dwzPrefix, code);
            if(codeDwz != null) {
                return codeDwz;
            }
        }
    }

    private Dwz getDwzByRandom(String longUrl, User user, String dwzPrefix, String dwzCode) {
        Dwz codeDwz = dwzMapper.getDwzByCode(dwzCode);
        if(codeDwz == null) {
            codeDwz = new Dwz();
            codeDwz.setCode(dwzCode);
            String shortUrl = dwzPrefix + DWZ_PREFIX + dwzCode;
            codeDwz.setShortUrl(shortUrl);
            codeDwz.setLongUrl(longUrl);
            // 生成shortUrl对应的二维码
            String filePath = FileUploadUtils.getFilePath(uploadDir, BaseApplication.CODE, ".png");
            try {
                QRCodeUtil.createQRCode(shortUrl, filePath);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage(), e);
            }
            String qrcodeUrl = baseUrl + AppConstant.UPLOAD_DIR + filePath.substring(uploadDir.length());
            codeDwz.setQrcode(qrcodeUrl);
            setDwzValue(codeDwz, user);
            dwzMapper.addDwz(codeDwz);
            return codeDwz;
        } else if(codeDwz.getLongUrl().equals(longUrl)){
            return codeDwz;
        }
        
        return null;
    }

    @Override
    public Dwz getDwzByCode(String dwzCode) {
        return dwzMapper.getDwzByCode(dwzCode);
    }

}
