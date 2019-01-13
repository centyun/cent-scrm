package com.centyun.core.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.centyun.core.domain.ProductVO;
import com.centyun.core.domain.User;

/**
 * 用户的Feign接口
 * @author yinww
 *
 */

@FeignClient(name = "user")
public interface UserFeignClient {

    @RequestMapping("/user/getUserByToken")
    public User getUserByToken(@RequestParam("token") String token);

    @RequestMapping("/user/updateLanguage")
    public void updateLanguage(@RequestParam("id") Long id, @RequestParam("language") String language);
    
    @RequestMapping("/product/getAvailableProducts")
    public List<ProductVO> getAvailableProducts();
}
