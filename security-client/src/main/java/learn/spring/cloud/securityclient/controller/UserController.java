package learn.spring.cloud.securityclient.controller;

import learn.spring.cloud.securityclient.entity.Role;
import learn.spring.cloud.securityclient.entity.User;
import learn.spring.cloud.securityclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @RequestMapping("addUser/{name}/{password}/{role}")
    public Map<String, Object> addUser(@PathVariable("name") String name,
                                       @PathVariable("password") String password,
                                       @PathVariable("role") int role)
    {
        Map<String, Object> maps = new HashMap<>();
        User user = new User(name, passwordEncoder.encode(password));
        Role role1 = new Role();
        role1.setId(role);
        user.addRole(role1);
        userService.addUser(user);
        maps.put("status", 1);
        maps.put("message","success");
        return maps;
    }
}
