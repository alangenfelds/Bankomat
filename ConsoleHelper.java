package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by alangenfelds on 15.03.2016.
 */
public class ConsoleHelper
{
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException
    {
        String s = "";
        try
        {
            s = reader.readLine();
            if (s.toUpperCase().equals("EXIT")) throw new InterruptOperationException();
        }
        catch (IOException e)
        {
            throw new InterruptOperationException();
        }
        return s;
    }

    public static String askCurrencyCode() throws InterruptOperationException
    {
        System.out.println("Enter currency code [XXX]: ");
        String cur = readString();
        while (cur.length() != 3)
        {
            System.out.println("ERROR: Currency code must be 3 characters example: USD");
            cur = readString();
        }
        cur = cur.toUpperCase();
        return cur;
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        System.out.println("ввести два целых положительных числа: Первое число - номинал, второе - количество банкнот.");
        String input = readString();
        String[] test = input.split(" ");
        input = input.replaceAll(" ", "");
        while (test.length != 2 || !input.matches("^[0-9]+$"))
        {
            System.out.println("Incorrect params. Example: 200 5 ");
            input = readString();
            test = input.split(" ");
            input = input.replaceAll(" ", "");
        }
        return test;
    }

    public static Operation askOperation() throws InterruptOperationException
    {
        int num=0;
        System.out.println("Выберите номер операции: ");
        try
        {
            num = Integer.parseInt(readString());
            if (num < 1 || num > Operation.values().length) throw new IllegalArgumentException();
        }
        catch (IllegalArgumentException e){
            askOperation();
        }
        return Operation.getAllowableOperationByOrdinal(num);
    }
}
