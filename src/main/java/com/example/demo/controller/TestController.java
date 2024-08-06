package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @GetMapping("/list")
    public String list(Model model){
        List<String> list=new ArrayList<>();
        list.add("사과");
        list.add("바나바");
        list.add("오랜지");
        list.add("귤");
        model.addAttribute("list", list);
        return "views/list"; // list.html
    }
}
