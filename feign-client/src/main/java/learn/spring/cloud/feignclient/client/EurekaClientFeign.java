package learn.spring.cloud.feignclient.client;

import learn.spring.cloud.feignclient.config.FeignConfig;
import learn.spring.cloud.feignclient.handler.HiErrorHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-client",
        configuration = FeignConfig.class,
        fallback = HiErrorHandler.class)
public interface EurekaClientFeign {
    @GetMapping("hi")
    String sayHiFromEurekaClient(@RequestParam("name") String name);
}
