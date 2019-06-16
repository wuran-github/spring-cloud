package learn.spring.cloud.servicehi.service;



import learn.spring.cloud.servicehi.dao.UserDao;
import learn.spring.cloud.servicehi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


public interface UserService  {
   User addUser(User user);
}
