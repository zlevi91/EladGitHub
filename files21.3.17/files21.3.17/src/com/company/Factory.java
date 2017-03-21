package com.company;

/**
 * Created by This_user on 20/03/2017.
 */
public class Factory {
    static boolean flag = true;
    Menu menu = new Menu();
    Algorithm chooseAlgorithm(String choose ){
        switch (choose){
            case "1":
                return new Caesar();
            case "2":
                return new XorAlgorithm();
            case "3":
                flag = !flag;
                menu.returnAgain(2);
                break;
            default:
                menu.returnAgain(1);

        }
        return new Caesar();
    }
}
