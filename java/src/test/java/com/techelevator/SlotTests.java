package com.techelevator;
import org.junit.Assert;
import org.junit.Test;

public class SlotTests {
    @Test
    public void getPhrase_should_get_correct_phrase_for_Chip(){
        Slot constructedSlot = new Slot ("A1", "Potato Crisps", "Chip", 305);
        String actualResult = constructedSlot.getPhrase();
        String expectedResult = "Crunch Crunch, Yum!";
        Assert.assertEquals(expectedResult, actualResult);
    }

}