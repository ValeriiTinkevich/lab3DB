package ru.valerit.exceptions;

public class WrongAmountOfElementsException extends Exception{
    public WrongAmountOfElementsException() {
        super("Wrong ammount of arguments!");
    }
}
