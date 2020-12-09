package com.hubertart.playground;

import org.springframework.web.bind.annotation.*;

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

//    @PostMapping("/math/sum")
//    public String performSummation(@RequestParam Integer[] n){
//        int total = 0;
//        String output = "";
//        for( int i : n){
//            output += i + " + ";
//            total += i;
//        }
//        return output.substring(0, output.length() - 2) + "= " + total;
//    }

    @PostMapping("/math/sum")
    public String performSummation(MathService mathService){
        return mathService.performSummation();
    }

    @RequestMapping("/math/volume/{length}/{width}/{height}")
    public String performVolumeCalc(MathService mathService){
        return mathService.performVolumeCalc();
    }

    @PostMapping("/math/area")
    public String performAreaCalc(MathService mathService){
        return mathService.performAreaCalc();
    }

}
