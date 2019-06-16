package learn.spring.cloud.feignclient.controller;

import learn.spring.cloud.feignclient.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @Autowired
    HiService hiService;
    @GetMapping("hi/{name}")
    public String hi(@PathVariable("name") String name)
    {
        return hiService.sayHi(name);
    }
}
