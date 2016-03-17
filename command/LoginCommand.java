package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command
{
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"login_en");

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        do
        {
            String userCardNum = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("Введите пин - состоящий из 4 цифр");
            String userPin = ConsoleHelper.readString();

            if (userCardNum.length() != 12 || !userCardNum.matches("[0-9]+") || userPin.length() != 4 || !userPin.matches("[0-9]+"))
            {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));

            } else if (!validCreditCards.containsKey(userCardNum) || !validCreditCards.getString(userCardNum).equals(userPin))
            {
                //ConsoleHelper.writeMessage("Неправильный пин или номер карты !");
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), userCardNum));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            }
            else
            {
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"),userCardNum));
                break;
            }
        }
        while (true);
    }
}
