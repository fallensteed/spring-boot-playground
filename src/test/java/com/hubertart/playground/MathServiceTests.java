package com.hubertart.playground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathServiceTests {

    MathService mathService;

    @BeforeEach
    public void setUp(){
        mathService = new MathService();
    }

    @Test
    public void testAddXAndY(){
        mathService.setOperation("add");
        mathService.setX(4);
        mathService.setY(6);
        String result = mathService.performOperation();
        assertEquals("4 + 6 = 10", result);
    }
    @Test
    public void testSubtractXAndY(){
        mathService.setOperation("subtract");
        mathService.setX(4);
        mathService.setY(6);
        String result = mathService.performOperation();
        assertEquals("4 - 6 = -2", result);
    }
    @Test
    public void testMultiplyXAndY(){
        mathService.setOperation("multiply");
        mathService.setX(4);
        mathService.setY(6);
        String result = mathService.performOperation();
        assertEquals("4 * 6 = 24", result);
    }
    @Test
    public void testDivideXAndY(){
        mathService.setOperation("divide");
        mathService.setX(30);
        mathService.setY(5);
        String result = mathService.performOperation();
        assertEquals("30 / 5 = 6", result);
    }
    @Test
    public void testNoOpXAndY(){
        mathService.setX(30);
        mathService.setY(5);
        String result = mathService.performOperation();
        assertEquals("30 + 5 = 35", result);
    }

    @Test
    public void testSummation(){
        Integer[] n = {4, 5, 6};
        mathService.setN(n);
        String result = mathService.performSummation();
        assertEquals("4 + 5 + 6 = 15", result);
    }

    @Test
    public void testVolume(){
        mathService.setLength(3);
        mathService.setWidth(4);
        mathService.setHeight(5);
        String result = mathService.performVolumeCalc();
        assertEquals("The volume of a 3x4x5 rectangular cuboid is 60", result);
    }

    @Test public void testAreaCircle() {
        mathService.setType("circle");
        mathService.setRadius(4);
        String result = mathService.performAreaCalc();
        assertEquals("Area of a circle with a radius of 4 is 50.26548", result);
    }

    @Test public void testAreaRectangle() {
        mathService.setType("rectangle");
        mathService.setHeight(7);
        mathService.setWidth(4);
        String result = mathService.performAreaCalc();
        assertEquals("Area of a 4x7 rectangle is 28", result);
    }

}
