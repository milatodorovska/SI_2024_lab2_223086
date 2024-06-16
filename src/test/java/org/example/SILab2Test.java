package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test
{

    @Test
    void everyBranch(){

        // allItems = null
        RuntimeException ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null,0));
        assertTrue(ex.getMessage().contains("the list can't be null!"));

        List<Item> allItems1 = new ArrayList<>();
        // payment < 0
        assertTrue(SILab2.checkCart(allItems1, 0));
        // payment > 0
        assertTrue(SILab2.checkCart(allItems1, 100));

        List<Item> allItems2 = new ArrayList<>();
        // barcode = null
        allItems2.add(new Item("item",null,1,0));
        RuntimeException ex3 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems2,100));
        assertTrue(ex3.getMessage().contains("No barcode!"));

        List<Item> allItems3 = new ArrayList<>();
        // barcode содржи невалиден карактер
        allItems3.add(new Item("item1","blabla",1,0));
        RuntimeException ex2 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems3,100));
        assertTrue(ex2.getMessage().contains("Invalid character in item barcode!"));

        List<Item> allItems4 = new ArrayList<>();
        // елементот е внесен без име
        allItems4.add(new Item("","123",100,0));
        assertTrue(SILab2.checkCart(allItems4,100));

        List<Item> allItems5 = new ArrayList<>();
        // исполнет услов price > 300, discount > 0 && barcode почнува на 0
        allItems5.add(new Item("item","012",350,1));
        assertFalse(SILab2.checkCart(allItems5,50));
    }

    @Test
    void MultipleCondition(){
        List<Item> allItems = new ArrayList<>();
        allItems.add(new Item("item","000",210,1));
        allItems.add(new Item("item","000",310,0));

        allItems.add(new Item("item","100",310,0.2f));
        allItems.add(new Item("item","100",310,0.2f));

        assertTrue(SILab2.checkCart(allItems,2000));
        assertFalse(SILab2.checkCart(allItems,50));
    }



}
