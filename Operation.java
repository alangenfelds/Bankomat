package com.javarush.test.level26.lesson15.big01;

/**
 * Created by alangenfelds on 15.03.2016.
 */
public enum Operation
{
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i)
    {
        if (i<1 || i>Operation.values().length) throw new IllegalArgumentException();
            return Operation.values()[i-1];
    }
}
