package com.hubertart.playground;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {
    @GetMapping("/")
    public String helloWorld() {
        return "Hello World";
    }
    @GetMapping("/tasks")
    public String getTasks() {
        return "These are tasks";
    }
    @PostMapping("/tasks")
    public String createTask(){
        return "You just POSTed to /tasks";
    }
    @GetMapping("/math/pi")
    public String getPie(){
        return "" + Math.PI;
    }
}
