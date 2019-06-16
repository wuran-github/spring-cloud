package learn.spring.cloud.servicehi.controller;

import learn.spring.cloud.servicehi.entity.User;
import learn.spring.cloud.servicehi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("registry/{name}/{password}")
    public User create(@PathVariable("name") String username,
                       @PathVariable("password") String password){
        User user = new User(username, password);
        return userService.addUser(user);
    }
}
