package com.soo.routine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/testHelloa")
    public String testHello() {
        return "testHello";
    }

    @GetMapping
    public String write() {

        return "";
    }



}
