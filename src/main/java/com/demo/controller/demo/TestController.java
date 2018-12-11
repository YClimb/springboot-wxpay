package com.demo.controller.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * model
 *
 * @author yclimb
 * @date 2018/12/7
 */
@RestController
@RequestMapping("/demo")
public class TestController {

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/index")
    public String test() {
        System.out.println("TestController>>>test>>>is running....return a string");
        return "index";
    }

    @RequestMapping("get")
    public String getYdTest() {
        String url = "https://www.baidu.com/";
        String data = restTemplate.getForObject(url, String.class);
        System.out.println(data);
        return data;
    }

}
