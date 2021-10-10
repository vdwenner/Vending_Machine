package com.techelevator;

import org.junit.Assert;
import org.junit.Test;
import java.util.TreeMap;

public class VendingMachineTests {

VendingMachine testMachine = new VendingMachine();
TreeMap<String, Slot> slotMap = new TreeMap<>();

        @Test
        public void makeChange_should_give_8_quarters_and_2_pennies_when_202_is_passed(){
            int testBalance = 202;
            String after202 = testMachine.makeChange(testBalance);
            String expectedResult = "You receive 8 quarters, 2 pennies.";
            Assert.assertEquals(expectedResult, after202);
        }

        @Test
        public void makeChange_should_give_nothing_when_0_is_passed(){
            int testBalance = 0;
            String actualResult = testMachine.makeChange(testBalance);
            String expectedResult = "You receive nothing, because the balance was zero.";
            Assert.assertEquals(expectedResult, actualResult);
        }

        @Test
        public void displayItems_should_display_B3_information(){
            slotMap.put("B3", new Slot ("B3", "Wonka Bar", "Candy", 150));
            String testString = testMachine.displayItems();
            testMachine.setSlotMap(slotMap);
            String actualResult = testMachine.displayItems();
            String expectedResult = "\n" + "B3, Wonka Bar, $1.50, 5 in stock";
            Assert.assertEquals(expectedResult,actualResult);
        }

        @Test
        public void getVendingInfo_should_give_brand_name_cowtales_for_B2_when_file_is_read(){
            testMachine.getVendingInfo();
            String actualResult = testMachine.getSlotMap().get("B2").getBrandName();
            Slot testSlot = new Slot ("B2", "Cowtales", "Candy", 150);
            String expectedResult = testSlot.getBrandName();
            Assert.assertEquals(expectedResult,actualResult);
        }

        @Test
        public void takeMoney_should_give_500_to_balance_if_500_is_passed(){
            testMachine.takeMoney(500);
            int actualResult = testMachine.getBalance();
            int expectedResult = 500;
            Assert.assertEquals(expectedResult,actualResult);
        }

        @Test
        public void getBalance_should_give_500_if_500_is_passed_by_setBalance(){
            testMachine.setBalance(500);
            int actualResult = testMachine.getBalance();
            int expectedResult = 500;
            Assert.assertEquals(expectedResult, actualResult);
        }

        @Test
        public void selectProduct_should_lower_balance_by_150_if_B3_is_choosen(){
            testMachine.setSlotMap(slotMap);
            slotMap.put("B3", new Slot ("B3", "Wonka Bar", "Candy", 150));
            testMachine.setBalance(500);
            testMachine.selectProduct("B3");
            int actualResult = testMachine.getBalance();
            int expectedResult = 350;
            Assert.assertEquals(expectedResult, actualResult);
        }

        @Test
    public void showAsDollars_should_return_$1period50_when_150_is_passed(){
            String actualResult = testMachine.showAsDollars(150);
            String expectedResult = "$1.50";
            Assert.assertEquals(expectedResult, actualResult);
        }

        @Test
        public void showAsDouble_should_return_3period00_when_300_is_passed(){
            String actualResult = testMachine.showAsDouble(300);
            String expectedResult = "3.00";
            Assert.assertEquals(expectedResult, actualResult);
        }

}


