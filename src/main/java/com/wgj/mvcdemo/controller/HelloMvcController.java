package com.wgj.mvcdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Guojian Wang
 * @Date 2019/7/25 11:45
 */
@Controller
@RequestMapping("/hello")
public class HelloMvcController {

    @RequestMapping("/mvc")
    public String helloMvc() {
        return "home";
    }
}
