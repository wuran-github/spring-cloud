package learn.spring.cloud.ribbonclient.controller;

import learn.spring.cloud.ribbonclient.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HiController {

    @Autowired
    RibbonService ribbonService;
    @GetMapping("hi/{name}")
    public String hi(@PathVariable("name") String name){
        return ribbonService.hi(name);
    }
}
