package learn.spring.cloud.securityclient.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
public class TestController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    @RequestMapping(value = "/401", method = RequestMethod.GET)
    public String error401(){
        return "401";
    }
    @RequestMapping(value = "/login-error", method = RequestMethod.GET)
    public String loginError(){
        return "login-error";
    }
    @RequestMapping(value = "/login-success", method = RequestMethod.GET)
    public String loginSuccess(){
        return "login-success";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

}
