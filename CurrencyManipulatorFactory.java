package com.javarush.test.level26.lesson15.big01;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alangenfelds on 15.03.2016.
 */
public final class CurrencyManipulatorFactory
{
    private static Map<String, CurrencyManipulator> listCM = new HashMap<String, CurrencyManipulator>();

    private CurrencyManipulatorFactory()
    {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode)
    {
        if (!listCM.containsKey(currencyCode)) listCM.put(currencyCode,new CurrencyManipulator(currencyCode));
        return listCM.get(currencyCode);
    }

    //public static Map<String, CurrencyManipulator> getAllCurrencyManipulators()
    public static Collection<CurrencyManipulator> getAllCurrencyManipulators()
    {
        return listCM.values();
    }


}
