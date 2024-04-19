package org.bank.helper;

public class Customexception extends Exception {
    public Customexception(Object ob) {
        super((Throwable) ob);
        System.out.println("Invalid parameters");
    }
}
