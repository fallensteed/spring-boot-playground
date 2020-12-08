package com.hubertart.playground;

public class MathService {
    private int x;
    private int y;
    private String operation = "add";
    private Integer[] n;
    private int height;
    private int width;
    private int length;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String performOperation() {
        switch (operation) {
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

    public void setN(Integer[] n) {
        this.n = n;
    }

    public String performSummation() {
        int total = 0;
        String output = "";
        for( int i : n){
            output += i + " + ";
            total += i;
        }
        return output.substring(0, output.length() - 2) + "= " + total;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String performVolume() {
        return "The volume of a " + length + "x" + width + "x" + height + " rectangular cuboid is " + (length * width * height);
    }
}
