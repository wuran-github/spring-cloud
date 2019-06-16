package learn.spring.cloud.userservice.service;


import learn.spring.cloud.userservice.entity.User;
import learn.spring.cloud.userservice.entity.UserLoginDTO;

public interface UserService  {
   User addUser(User user);
   UserLoginDTO login(String username, String password);
}
