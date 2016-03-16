package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.command.CommandExecutor;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.Locale;

/**
 * Created by alangenfelds on 15.03.2016.
 */
public class CashMachine
{
    public static void main(String[] args)
    {
        Locale.setDefault(Locale.ENGLISH);
        Operation o;
        try
        {
            do
            {
                o = ConsoleHelper.askOperation();
                CommandExecutor.execute(o);
            }
            while (o != Operation.EXIT);
        }
        catch (InterruptOperationException ioe)
        {
            ConsoleHelper.writeMessage("Good bye !");
        }
    }


}
