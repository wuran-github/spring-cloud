package learn.spring.cloud.servicehi.impl;

import learn.spring.cloud.servicehi.dao.UserDao;
import learn.spring.cloud.servicehi.entity.User;
import learn.spring.cloud.servicehi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public User addUser(User user) {
        String pwd = user.getPassword();
        user.setPassword(passwordEncoder.encode(pwd));
        return userDao.save(user);
    }
}
