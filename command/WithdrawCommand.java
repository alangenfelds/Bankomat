package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Map;

class WithdrawCommand implements Command
{
    @Override
    public void execute() throws InterruptOperationException
    {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        int sum;

        while (true)
        {
            ConsoleHelper.writeMessage("Enter withdraw amount");
            String s = ConsoleHelper.readString();
            try
            {
                sum = Integer.parseInt(s);
            }
            catch (NumberFormatException e)
            {
                ConsoleHelper.writeMessage("Enter correct amount");
                continue;
            }
            if (sum <= 0)
            {
                ConsoleHelper.writeMessage("Enter positive amount");
                continue;
            }
            if (!currencyManipulator.isAmountAvailable(sum))
            {
                ConsoleHelper.writeMessage("Not enough money");
                continue;
            }
            try
            {
                Map<Integer, Integer> result = currencyManipulator.withdrawAmount(sum);
                //success
                for (Map.Entry<Integer, Integer> entry : result.entrySet())
                    ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
                ConsoleHelper.writeMessage("Transaction has completed successfully");
            }
            catch (NotEnoughMoneyException e)
            {
                ConsoleHelper.writeMessage("Cannot withdraw current sum");
                continue;
            }
            break;
        }
    }
}