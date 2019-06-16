package learn.spring.cloud.securityclient.config;

import learn.spring.cloud.securityclient.filter.CodeAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())//新版本需要指定加密格式
//                .withUser("user")
//                .password(new BCryptPasswordEncoder().encode("123456"))//需要转换成加密版本
//                .roles("USER");
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new CodeAuthenticationFilter("/login"), UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers("/css/**","/index","/login").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/blogs/**").hasRole("USER")
                .and()
                .formLogin().loginPage("/login").failureUrl("/login-error")
                .loginProcessingUrl("/login")//处理登录流程的URL
                .successForwardUrl("/login-success")
                .successHandler(new SuccessHandler())//登录成功后的处理器
                .failureHandler(new FailureHandler())//失败的处理器
                .and()
                .exceptionHandling().accessDeniedPage("/401")
                .and()
                .csrf().disable();//要禁用掉跨站请求伪造

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    private static class SuccessHandler implements AuthenticationSuccessHandler{

        @Override
        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                            HttpServletResponse httpServletResponse,
                                            Authentication authentication) throws IOException, ServletException {
            httpServletResponse.sendRedirect("login-success");
        }
    }
    private static class FailureHandler implements AuthenticationFailureHandler{

        @Override
        public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
            //跳转页面
//            httpServletResponse.sendRedirect("");
            httpServletResponse.getWriter().print("{status:-1, message: 'failure'}");
        }
    }

}
