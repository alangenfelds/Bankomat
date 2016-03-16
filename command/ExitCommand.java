package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

/**
 * Created by Artur on 15.03.2016.
 */
class ExitCommand implements Command
{
    @Override
    public void execute() throws InterruptOperationException
    {
        System.out.println("Are you sure ? y/n");
         String s= ConsoleHelper.readString();
        if (s.equals("y")) System.out.println("Good bye :)");;
    }
}
