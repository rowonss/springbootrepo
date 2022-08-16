package com.example.demo.controller;


import com.example.demo.model.man;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class testController {

    @RequestMapping("test")
    public String hell(Model model){

        man a = new man("김종기", 28);

        man b = new man("양호열", 25);

        model.addAttribute("a", a);
        model.addAttribute("b", b);

        return "test";
    }

    @RequestMapping("human")
    public String human(@RequestParam  String name,
                        @RequestParam  int age,
                        Model model){

        man A = new man(name, age);

        model.addAttribute(A);

        return "human";
    }

}
