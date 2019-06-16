package learn.spring.cloud.zuulclient.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class TestFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {

        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request = requestContext.getRequest();
        Object token = request.getParameter("token");
        if(token == null){
            requestContext.setSendZuulResponse(false); //不往下传递，直接返回响应
            requestContext.setResponseStatusCode(401);
            try{
                HashMap<String, Object> map = new HashMap<>();
                map.put("status", -1);
                map.put("message", "token is empty");
//                requestContext.getResponse().getWriter().print("token is map");
                HttpServletResponse response = requestContext.getResponse();
                response.getWriter().print(map);
            }catch(Exception ex) {
            }
        }
        return null;
    }
}
