package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTests {

VendingMachine testMachine = new VendingMachine();

@Test
public void makeChange_should_give_8_quarters_and_2_pennies_when_202_is_passed(){

    int testBalance = 202;
    String after202 = testMachine.makeChange(testBalance);
    String expectedResult = "8 quarters, 2 pennies";
            Assert.assertEquals(expectedResult, after202);
        }

}
