package learn.spring.cloud.feignclient.handler;

import learn.spring.cloud.feignclient.client.EurekaClientFeign;
import org.springframework.stereotype.Component;

@Component
public class HiErrorHandler implements EurekaClientFeign {
    @Override
    public String sayHiFromEurekaClient(String name) {
        return "is error" + name;
    }
}
