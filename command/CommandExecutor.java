package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.Operation;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artur on 15.03.2016.
 */
public final class CommandExecutor{
    private CommandExecutor() {}
    private static Map<Operation, Command> mapCE;
    static {
        mapCE = new HashMap<Operation, Command>();
        mapCE.put(Operation.DEPOSIT, new DepositCommand());
        mapCE.put(Operation.INFO, new InfoCommand());
        mapCE.put(Operation.WITHDRAW, new WithdrawCommand());
        mapCE.put(Operation.EXIT, new ExitCommand());
        mapCE.put(Operation.LOGIN,new LoginCommand());
    }
    public static final void execute (Operation operation) throws InterruptOperationException
    {
        mapCE.get(operation).execute();
    }
}