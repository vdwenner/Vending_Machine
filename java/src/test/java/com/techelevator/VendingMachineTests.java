package com.techelevator;

import com.sun.source.tree.Tree;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

public class VendingMachineTests {

VendingMachine testMachine = new VendingMachine();

@Test
public void makeChange_should_give_8_quarters_and_2_pennies_when_202_is_passed(){

    int testBalance = 202;
    String after202 = testMachine.makeChange(testBalance);
    String expectedResult = "8 quarters, 2 pennies";
            Assert.assertEquals(expectedResult, after202);
        }

        @Test
        public void displayItems_should_display_b3_information(){
            TreeMap<String, Slot> slotMap = new TreeMap<>();
            slotMap.put("B3", new Slot ("B3", "Wonka Bar", "Candy", 150));
            String testString = testMachine.displayItems();
            testMachine.setSlotMap(slotMap);
            String actualResult = testMachine.displayItems();
            String expectedResult = "\n" + "B3, Wonka Bar, 150, 5";
            Assert.assertEquals(expectedResult,actualResult);

        }


}


