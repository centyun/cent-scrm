package com.centyun.user.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.centyun.core.util.IpUtils;
import com.centyun.user.domain.City;
import com.centyun.user.domain.IpAddress;
import com.centyun.user.domain.Province;
import com.centyun.user.service.CityService;
import com.centyun.user.service.IpAddressService;
import com.centyun.user.service.ProvinceService;

@Component
public class IpAddressTask {
    private Logger log = LoggerFactory.getLogger(IpAddressTask.class);
    
    public static final Integer TAOBAO = 1;
    public static final Integer IP_API = 2;
    
    @Autowired
    private IpAddressService ipService;
    
    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CityService cityService;
    
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 对比淘宝api库的数据，请求频率为不超过每秒1次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void checkTaobaoIp() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.debug("checkTaobaoIp==" + sdf.format(System.currentTimeMillis()));
        List<IpAddress> ipAddresses = ipService.listIpAddresses(1, TAOBAO);
        taobaoApi(ipAddresses);
    }

    /**
     * 对比api-api库的数据, 请求频率为不超过每分钟150次, 每次可以批量请求不超过100个ip
     */
     @Scheduled(cron = "0/20 * * * * ?")
    public void checkIpApi() {
        List<IpAddress> ipAddresses = ipService.listIpAddresses(90, IP_API);
        ipApi(ipAddresses);
    }

    private void taobaoApi(List<IpAddress> ipAddresses) {
        if(ipAddresses == null || ipAddresses.size() == 0) return;
        try {
            String url = "http://ip.taobao.com/service/getIpInfo.php?ip=";
            for (IpAddress ipAddress : ipAddresses) {
                String link = url + ipAddress.getIpv4();
                log.debug("ip地址为:" + link);
                ResponseEntity<String> entity = restTemplate.getForEntity(link, String.class);
                String body = entity.getBody();
                log.debug("调用淘宝ip接口返回结果:" + body);
                JSONObject jsonObject = JSONObject.parseObject(body);
                int code = jsonObject.getIntValue("code");
                if(0 == code) {
                    JSONObject dataObj = jsonObject.getJSONObject("data");
                    IpAddress ip = new IpAddress();
                    ip.setId(ipAddress.getId());
                    ip.setIsp(dataObj.getString("isp"));
                    ip.setCountryRegionName(dataObj.getString("country"));
                    
                    // 处理省
                    String provinceName = dataObj.getString("region");
                    Province province = getProvince(provinceName);
                    if(province == null) {
                        ip.setProvinceName(provinceName);
                    } else {
                        ip.setProvinceName(province.getName());
                        ip.setProvinceId(province.getId());
                    }
                    
                    // 处理市
                    String cityName = dataObj.getString("city");
                    City city = getCity(cityName);
                    if(city == null) {
                        ip.setCityName(provinceName);
                    } else {
                        ip.setCityName(city.getName());
                        ip.setCityId(city.getId());
                    }
                    ip.setStatus(TAOBAO);
                    ipService.updateIpAddress(ip);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private Province getProvince(String name) {
        try {
            Province province = provinceService.getProvinceByName(name);
            return province;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private City getCity(String name) {
        try {
            City city = cityService.getCityByName(name);
            return city;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void ipApi(List<IpAddress> ipAddresses) {
        if(ipAddresses == null || ipAddresses.size() == 0) return;
        try {
            String url = "http://ip-api.com/batch"; // 使用批量接口
            List<Map<String, String>> parmas = new ArrayList<>();
            for (IpAddress ipAddress : ipAddresses) {
                Map<String, String> ipMap = new HashMap<>();
                ipMap.put("query", ipAddress.getIpv4());
                parmas.add(ipMap);
            }
            log.debug("parmas==" + parmas);
            ResponseEntity<String> entity = restTemplate.postForEntity(url, parmas, String.class);
            String body = entity.getBody();
            log.debug("调用ip-api接口返回结果:" + body);
            JSONArray ipArray = JSONObject.parseArray(body);
            for (Object object : ipArray) {
                JSONObject ipObject = (JSONObject) object;
                IpAddress ip = new IpAddress();
                String ipv4 = ipObject.getString("query");
                Long id = IpUtils.ipToLong(ipv4);
                ip.setId(id);
                ip.setIspEn(ipObject.getString("isp"));
                ip.setLat(ipObject.getFloatValue("lat"));
                ip.setLon(ipObject.getFloatValue("lon"));
                ip.setTimezone(ipObject.getString("timezone"));
                ip.setStatus(IP_API);
                ipService.updateIpAddress(ip);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
