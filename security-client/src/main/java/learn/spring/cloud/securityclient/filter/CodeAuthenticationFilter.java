package learn.spring.cloud.securityclient.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private boolean isOpenValidateCode = true;
    private String mockCode = "1234";
    public CodeAuthenticationFilter(String pattern){
        //指定请求地址，拦截哪个URL
        super(new AntPathRequestMatcher(pattern, "POST"));
        //获取失败处理器
        SimpleUrlAuthenticationFailureHandler failureHandler = (SimpleUrlAuthenticationFailureHandler)getFailureHandler();

        //失败后跳转到指定页面
//        failureHandler.setDefaultFailureUrl("/error");

        //设置成功处理器
        setAuthenticationSuccessHandler(new SuccessHandler());
        //也可以指定自定义的失败处理器，做其他操作，如前后端分离
//        setAuthenticationFailureHandler(failureHandler);
        //设置成功后往下传递
        setContinueChainBeforeSuccessfulAuthentication(true);

    }

    /**
     * 这是过滤链执行的主要函数
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        //自己重写的doFilter
        customDoFilter(req, res, chain);
//        super.doFilter(req,res,chain);
    }

    private void customDoFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        //获取到request和response,就可以取出表单的内容了
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        //可以获取到session传递一些信息

        HttpSession session = request.getSession();
        //先判断路径，不是要过滤的路径就直接下一个链
        if(!requiresAuthentication(request,response)){
            chain.doFilter(req, res);
            return;
        }
        //获取认证
        Authentication authentication = attemptAuthentication(request, response);
        //把逻辑移到这里，
        if(isOpenValidateCode) {
            String code = request.getParameter("code");
            if(validateCode(code)){

            }
            else{
                session.setAttribute("errorMsg", "code Error");
                return;
            }
        }
        //继续执行过滤链的下一个过滤器
        chain.doFilter(req, res);
        //执行成功处理器
        successfulAuthentication(request,response, chain, authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        getSuccessHandler().onAuthenticationSuccess(request,response,authResult);
    }

    /**
     * 验证函数
     * @param code
     * @return
     */
    private boolean validateCode(String code){
        return mockCode.equals(code);
    }

    /**
     * 尝试认证
     * 影响到DoFilter是否往下执行，有3个可能的情况
     * 1. 返回Authentication，代表认证完成，不会继续往下执行
     * 2. 过程中抛出AuthenticationException异常，代表认证异常
     * unsuccessfulAuthentication会被执行
     * 3. 返回null,代表认证还没结束，直接返回
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        //可以获取到session传递一些信息
        return null;
    }
    //静态代表不传递外部类对象
    private static class SuccessHandler implements AuthenticationSuccessHandler{
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            //do nothing
        }
    }

}
