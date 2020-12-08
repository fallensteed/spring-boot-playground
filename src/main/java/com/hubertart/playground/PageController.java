package com.hubertart.playground;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String performOperations(
            @RequestParam(value="operation", required=false, defaultValue = "add") String mathType,
            @RequestParam("x") Integer x,
            @RequestParam("y") Integer y
    ) {
        switch (mathType) {
            case "multiply":
                return (x + " * " + y + " = " + (x * y));
            case "subtract":
                return (x + " - " + y + " = " + (x - y));
            case "divide":
                return (x + " / " + y + " = " + (x / y));
            default:
                return (x + " + " + y + " = " + (x + y));
        }
    }

    @PostMapping("/math/sum")
    public String performSummation(
            @RequestParam Integer [] n){

        int total = 0;
        String output = "";
        for( int i : n){
            output += i + " + ";
            total += i;
        }
        return output.substring(0, output.length() - 2) + "= " + total;
    }
}
