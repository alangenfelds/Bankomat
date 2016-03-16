package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;


/**
 * Created by Artur on 15.03.2016.
 */
class DepositCommand implements Command
{
    @Override
    public void execute() throws InterruptOperationException
    {
        String curCode = ConsoleHelper.askCurrencyCode();
        String[] str = ConsoleHelper.getValidTwoDigits(curCode);
        CurrencyManipulatorFactory.getManipulatorByCurrencyCode(curCode).addAmount(Integer.parseInt(str[0]),Integer.parseInt(str[1]));
    }
}
