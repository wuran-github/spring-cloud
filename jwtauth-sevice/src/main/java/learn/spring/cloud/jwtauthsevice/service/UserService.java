package learn.spring.cloud.jwtauthsevice.service;



import learn.spring.cloud.jwtauthsevice.dao.UserDao;
import learn.spring.cloud.jwtauthsevice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }
    public void addUser(User user){
        userDao.save(user);
    }
}
