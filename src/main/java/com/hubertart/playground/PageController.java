package com.hubertart.playground;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/math/calculate")
    public String performOperation(MathService mathService){
        return mathService.performOperation();
    }

    @PostMapping("/math/sum")
    public String performSummation(MathService mathService){
        return mathService.performSummation();
    }

    @RequestMapping("/math/volume/{length}/{width}/{height}")
    public String performVolume(MathService mathService){
        return mathService.performVolume();
    }
}
