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

        @Test
        public void getVendingInfo_should_give_brand_name_cowtales_for_b2_when_file_is_read(){
            testMachine.getVendingInfo();
            String actualResult = testMachine.getSlotMap().get("B2").getBrandName();
            Slot testSlot = new Slot ("B2", "Cowtales", "Candy", 150);
            String expectedResult = testSlot.getBrandName();
            Assert.assertEquals(expectedResult,actualResult);
        }

        @Test
        public void takeMoney_should_add_five_hundred_if_five_dollars_is_deposited(){
            testMachine.takeMoney(5);
            int actualResult = testMachine.getBalance();
            int expectedResult = 500;
            Assert.assertEquals(expectedResult,actualResult);
        }


}


