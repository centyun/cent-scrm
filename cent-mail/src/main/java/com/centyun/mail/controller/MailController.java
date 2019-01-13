package com.centyun.mail.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centyun.core.constant.AppConstant;
import com.centyun.core.util.RandomGenerator;
import com.centyun.core.util.SnowFlakeIdWorker;
import com.centyun.mail.domain.Mail;
import com.centyun.mail.domain.MailProvider;
import com.centyun.mail.service.MailProviderService;
import com.centyun.mail.service.MailService;

@Controller
@RequestMapping(value = "/mail")
public class MailController {
    private Logger log = LoggerFactory.getLogger(MailController.class);

    @Autowired
    private MailService mailService;

    @Autowired
    private MailProviderService mailProviderService;

    @RequestMapping(value = { "", "/", "/index.html" })
    public String index() {
        return "index";
    }

    @RequestMapping("/package/index.html")
    @ResponseBody
    public Object saveMail(HttpServletRequest request) {
        Object user = request.getSession().getAttribute(AppConstant.LOGIN_USER);
        log.info("user:" + user);
        SnowFlakeIdWorker worker = new SnowFlakeIdWorker(1, 0);
        int count = 55;
        List<Integer> shardings = RandomGenerator.getRandomIntList(0, 20, count);
        for (int i = 0; i < count; i++) {
            Mail mail = getMail();
            Long id = mail.getId();
            if (id == null || id == 0l) {
                long nextId = worker.nextId();
                System.out.println("nextId===" + nextId);
                mail.setId(nextId);
                mail.setShardingColumn(shardings.get(i));
                mailService.addMail(mail);
            } else {
                mailService.updateMail(mail);
            }
        }
        List<MailProvider> mailProviders = mailProviderService.getMailProviders();
        Map<String, Object> result = new HashMap<>();
        result.put("mailProviders", mailProviders);
        result.put("user", user);
        return result;
    }

    private Mail getMail() {
        Mail mail = new Mail();
        mail.setTenantId(1l);
        mail.setPackageId(1l);
        mail.setProviderId(1);
        mail.setRecipient("test@qq.com");
        mail.setTemplateName("这是模板名称");
        mail.setSubject("这是主题");
        mail.setMailType(1);
        return mail;
    }

    @RequestMapping("/getMail")
    @ResponseBody
    public Object getMails() {
        List<Mail> mails = mailService.getMails(1l);
        return mails;
    }
}
