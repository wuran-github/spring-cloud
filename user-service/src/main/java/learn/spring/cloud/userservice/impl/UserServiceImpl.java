package learn.spring.cloud.userservice.impl;


import learn.spring.cloud.userservice.client.AuthServiceClient;
import learn.spring.cloud.userservice.dao.UserDao;
import learn.spring.cloud.userservice.entity.JWT;
import learn.spring.cloud.userservice.entity.User;
import learn.spring.cloud.userservice.entity.UserLoginDTO;
import learn.spring.cloud.userservice.error.UserLoginException;
import learn.spring.cloud.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthServiceClient authServiceClient;
    @Override
    public User addUser(User user) {
        String pwd = user.getPassword();
        user.setPassword(passwordEncoder.encode(pwd));
        return userDao.save(user);
    }

    @Override
    public UserLoginDTO login(String username, String password) {
        User user = userDao.findByUsername(username);
        if(user ==null){
            throw new UserLoginException("username error");
        }
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new UserLoginException("password error");
        }

        //使用feign获取token
        JWT jwt = authServiceClient.getToken("Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==",
                "password",
                username,
                password);
        if(jwt == null){
            throw new UserLoginException("error internal");
        }
        UserLoginDTO dto = new UserLoginDTO();
        dto.setJwt(jwt);
        dto.setUser(user);
        return dto;
    }
}
