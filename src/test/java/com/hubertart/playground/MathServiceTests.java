package com.hubertart.playground;

import com.hubertart.playground.MathService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathServiceTests {

    @Test
    public void testAddXAndY(){
        MathService mathService = new MathService();
        mathService.setOperation("add");
        mathService.setX(4);
        mathService.setY(6);
        String result = mathService.performOperation();
        assertEquals("4 + 6 = 10", result);
    }
    @Test
    public void testSubtractXAndY(){
        MathService mathService = new MathService();
        mathService.setOperation("subtract");
        mathService.setX(4);
        mathService.setY(6);
        String result = mathService.performOperation();
        assertEquals("4 - 6 = -2", result);
    }
    @Test
    public void testMultiplyXAndY(){
        MathService mathService = new MathService();
        mathService.setOperation("multiply");
        mathService.setX(4);
        mathService.setY(6);
        String result = mathService.performOperation();
        assertEquals("4 * 6 = 24", result);
    }
    @Test
    public void testDivideXAndY(){
        MathService mathService = new MathService();
        mathService.setOperation("divide");
        mathService.setX(30);
        mathService.setY(5);
        String result = mathService.performOperation();
        assertEquals("30 / 5 = 6", result);
    }
    @Test
    public void testNoOpXAndY(){
        MathService mathService = new MathService();
        mathService.setX(30);
        mathService.setY(5);
        String result = mathService.performOperation();
        assertEquals("30 + 5 = 35", result);
    }

    @Test
    public void testSummation(){
        MathService mathService = new MathService();
        Integer[] n = {4, 5, 6};
        mathService.setN(n);
        String result = mathService.performSummation();
        assertEquals("4 + 5 + 6 = 15", result);
    }

}
