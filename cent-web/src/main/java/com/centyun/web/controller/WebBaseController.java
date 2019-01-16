package com.centyun.web.controller;

import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.centyun.core.client.UserFeignClient;
import com.centyun.core.domain.ModuleVO;
import com.centyun.core.domain.ProductVO;

public class WebBaseController {

    @Value("${CONSOLE_URL}")
    protected String consoleUrl; // 控制台地址

    @Autowired
    protected UserFeignClient userFeignClient;

    protected List<ProductVO> getAvailableProducts(HttpServletRequest request) {
        int port = request.getServerPort();
        String serverName = request.getServerName();
        String server = (port == 80 || port == 443) ? serverName : serverName + ":" + port;
        List<ProductVO> products = userFeignClient.getAvailableProducts();
        // setActive
        for (ProductVO product : products) {
            List<ModuleVO> modules = getModules(request, product.getCode());
            if(modules != null && modules.size() > 0) {
                product.setModules(modules); // 每个产品下的模块, 添加到左侧菜单中相应的产品菜单下
            }
            String releaseUrl = product.getReleaseUrl();
            if(releaseUrl != null && releaseUrl.length() > 8) { // https:// 的长度是8
                String host = getHost(releaseUrl);
                product.setActive(host.equals(server));
            } else {
                product.setActive(false);
            }
        }
        return products;
    }
    
    protected List<ModuleVO> getModules(HttpServletRequest request, String productCode) {
        return null;
    }

    /**
     * 只获取url中域名(或ip:port)的内容
     * @param releaseUrl
     * @return
     */
    private String getHost(String releaseUrl) {
        try {
            URL url = new URL(releaseUrl);
            return url.getHost();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 如果通过构造URL解析失败，则通过截取字符串进行比较
        int begin = 7;
        if(releaseUrl.startsWith("https://")) {
            begin = 8; 
        }
        return releaseUrl.substring(begin).split("/")[0];
    }
}
