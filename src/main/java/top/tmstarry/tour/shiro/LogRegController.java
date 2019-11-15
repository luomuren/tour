package top.tmstarry.tour.shiro;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import top.tmstarry.tour.user.service.IUserService;
import top.tmstarry.tour.user.service.bean.User;
import top.tmstarry.tour.user.service.bean.UserInfo;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName LogRegController
 * @Author 落幕人
 * <p>
 * 登录与注册控制器
 * <p>
 * @Date 2019/10/26 15:11
 * @Version 1.0
 **/
@Controller
public class LogRegController{
    private static final Logger logger = LogManager.getLogger(LogRegController.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 登录方法
     * @param user 用户详细信息
     * @param remember 记住密码
     * @return
     */
    @RequestMapping(value = "/ulogin",method = RequestMethod.POST)
    @ResponseBody
    public String login(User user, Boolean remember,HttpSession session) {
        if (remember == null) {
            remember = false;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword(),remember);

        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            if (subject.isAuthenticated()) {
                User user1 = userService.getUser(user.getUserName());
                session.setAttribute("userId",user1.getUserId());
                session.setAttribute("userName",user1.getUserName());
                session.setAttribute("email",user1.getEmail());
                Set<String> roles = userService.selectRoles(user1.getUserName());
                Iterator<String> iterator = roles.iterator();
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    if ("manage".equals(next)) {
                        return "manage";
                    }
                }
                return "index";
            }
        } catch (UnknownAccountException e) {
            return "error";
        } catch (IncorrectCredentialsException e) {
            return "error";
        }
        return "fail";
    }

    /**
     * 注册方法 通过MD5加密，1024次
     * @param map
     * @return 返回添加的用户信息
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public User register(@RequestBody Map<String,Object> map, HttpSession session) {
        String yzmTrue = (String) map.get("yzm");
        String yzm = (String) session.getAttribute("yzm");
        if (yzm==null||(!yzm.equals(yzmTrue))) {
            return null;
        } else {
            User user = new User();
            String userName = (String) map.get("userName");
            String password = (String) map.get("password");
            String email = (String) map.get("email");
            user.setUserName(userName);
            user.setEmail(email);
            user.setPassword(password);
            Hash hash = new SimpleHash("MD5", user.getPassword(), DatabaseRealm.salf, 1024);
            user.setPassword(hash.toBase64());
            userService.add(user);

            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(user.getUserId());
            userInfo.setCreatTime(new Date());
            userInfo.setUpTime(user.getCreatTime());
            userInfo.setCreatUser((String) session.getAttribute("userName"));
            userInfo.setUpUser(user.getCreatUser());
            userService.insertUserInfo(userInfo);

            return user;
        }
    }

    /**
     * 判断用户名是否重复
     * @param userName
     * @return 重复：true、不重复：false
     */
    @RequestMapping(value = "/nameIsExist")
    @ResponseBody
    public boolean nameIsExist(String userName) {
        User user = userService.getUser(userName);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/sendmail")
    @ResponseBody
    public boolean sendMail(String email,HttpSession session) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            Random random = new Random();
            String i = String.valueOf(random.nextInt(8999)+1000);
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("您好");
            Map<String, String> map = new HashMap<>();
            map.put("content", i);
            mimeMessageHelper.setText(getText(map));
//            mimeMessageHelper.addAttachment("附件", new File("d:/logs/aa.log"));
            mailSender.send(mimeMessage);
            session.setAttribute("yzm", i);
        } catch (MessagingException e) {
            return false;
        }
        return true;
    }

    private String getText(Map<String, String> moduleDate) {
        try {
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail.ftl");
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, moduleDate);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return "模版";
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
