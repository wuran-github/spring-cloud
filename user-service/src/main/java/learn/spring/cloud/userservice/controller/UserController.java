package learn.spring.cloud.userservice.controller;

import learn.spring.cloud.userservice.entity.User;
import learn.spring.cloud.userservice.entity.UserLoginDTO;
import learn.spring.cloud.userservice.service.UserService;
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
    @GetMapping("login/{name}/{password}")
    public UserLoginDTO login(@PathVariable("name") String username,
                              @PathVariable("password") String password){
        return userService.login(username, password);
    }
}
