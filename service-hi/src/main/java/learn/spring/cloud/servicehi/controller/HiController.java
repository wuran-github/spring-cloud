package learn.spring.cloud.servicehi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.security.Principal;

@RestController
public class HiController {
    /** logger */
    private final static Logger LOGGER  = LoggerFactory.getLogger(HiController.class);
    @Value("${server.port}")
    String port;

    @RequestMapping("hi")
    public String home(){
        return "hi, i am "+ port;
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("hello")
    public String hello(){
        return "hello, i am "+ port;
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("role_user")
    public String role_user(){
        return "role_user";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("role_admin")
    public String role_admin(){
        return "role_admin";
    }
    @PreAuthorize("hasRole('USER')")
    @RequestMapping("users")
    public String user(){
        return "user";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("admin")
    public String admin(){
        return "admin";
    }
    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("helloUser")
    public String helloUser(){
        return "hello user, i am "+ port;
    }
    @GetMapping("/getPrinciple")
    public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication,
                                             Principal principal,
                                             Authentication authentication){
        LOGGER.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        LOGGER.info(oAuth2Authentication.toString());
        LOGGER.info("principal, {}", principal.toString());
        LOGGER.info("principal getName: [{}]", principal.getName());
        LOGGER.info("authentication:{}", authentication.getAuthorities().toString());
        return oAuth2Authentication;
    }
}
