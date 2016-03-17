package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;


/**
 * Created by Artur on 15.03.2016.
 */
class InfoCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"info_en");
    @Override
    public void execute()
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> collCM = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (collCM.isEmpty()) ConsoleHelper.writeMessage(res.getString("no.money"));
        else
        {
            int count=0;
            for (CurrencyManipulator cm : collCM)
            {
                if (cm.hasMoney() && cm.getTotalAmount()>0)
                {
                    ConsoleHelper.writeMessage(cm.getCurrencyCode() + " - " + cm.getTotalAmount());
                    count++;
                }
                if (count==0) ConsoleHelper.writeMessage(res.getString("no.money"));
            }
        }
    }
}
