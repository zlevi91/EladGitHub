package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Created by hackeru on 3/15/2017.
 */
class MenuTest {
    @BeforeEach
    void setUp() {
        System.out.println("Begin test Menu");

    }

    @AfterEach
    void tearDown() {
        System.out.println("After test Menu");

    }

    @Test
    void printMenu() {


    }

    @Test
    void chooseOption() {
        Menu myMenu = new Menu();
        String testChoice = " ";
        Assertions.assertEquals(myMenu.myChoice(testChoice), "invalid option. try again.", "assertion failed for space");
        testChoice = "\n";
        Assertions.assertEquals(myMenu.myChoice(testChoice), "invalid option. try again.", "assertion failed for Enter");
        testChoice = "a";
        Assertions.assertEquals(myMenu.myChoice(testChoice), "invalid option. try again.", "assertion failed for letter");
        testChoice = "6";
        Assertions.assertEquals(myMenu.myChoice(testChoice), "invalid option. try again.", "assertion failed for number except 1,2");

    }

    @Test
    void chooseOption2() {
        Menu myMenu = new Menu();
        Input input = mock(Input.class);
        when(input.input()).thenReturn("1").thenReturn("C:\\Users\\hackeru.HACKERU3\\Downloads\\z.txt").thenReturn("0");
        
        //Assertions.assertEquals(myMenu.myChoice("1"),,"gfi");

    }

    @Test
    void getPathFromUser() {

    }

}