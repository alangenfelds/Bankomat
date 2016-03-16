package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.Collection;


/**
 * Created by Artur on 15.03.2016.
 */
class InfoCommand implements Command
{
    @Override
    public void execute()
    {
        Collection<CurrencyManipulator> collCM = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (collCM.isEmpty()) System.out.println("No money available.");
        else
        {
            int count=0;
            for (CurrencyManipulator cm : collCM)
            {
                if (cm.hasMoney() && cm.getTotalAmount()>0)
                {
                    System.out.println(cm.getCurrencyCode() + " - " + cm.getTotalAmount());
                    count++;
                }
                if (count==0) System.out.println("No money available.");
            }
        }
    }
}
