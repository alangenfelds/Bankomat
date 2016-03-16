package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.*;

/**
 * Created by alangenfelds on 15.03.2016.
 */
public class CurrencyManipulator
{
    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
        denominations = new HashMap<Integer, Integer>();
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void addAmount(int denomination, int count)
    {
        if (denominations.containsKey(denomination))
        {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else
        {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount()
    {
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet())
        {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            sum += key * value;
        }
        return sum;
    }

    public boolean hasMoney()
    {
        boolean result = true;
        if (denominations.isEmpty() || getTotalAmount() == 0) result = false;
        return result;
    }

    public boolean isAmountAvailable(int expectedAmount)
    {
        if (expectedAmount < getTotalAmount()) return true;
        else return false;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
    {
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>();
        sortedMap.putAll(denominations);

        HashMap<Integer, Integer> resultMap = new HashMap<>();


        for (Integer i : sortedMap.descendingKeySet()) //from max to min
        {
            int denomination = i;

            if (denomination <= expectedAmount)
            {
                int count = expectedAmount / denomination;
                expectedAmount -= denomination * count;
                resultMap.put(denomination, count);
                if (expectedAmount == 0)
                {
                    break;
                }
                if (expectedAmount < 0)
                    throw new  NotEnoughMoneyException();
            }
        }
        if (expectedAmount > 0)
            throw new NotEnoughMoneyException();

        HashMap<Integer, Integer> map = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet())
        {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (resultMap.containsKey(key))
            {
                if (value - resultMap.get(key) != 0)
                    map.put(key, value - resultMap.get(key));
            }
            else
                map.put(key, value);
        }
        denominations = map;  //updating original denominations
        return resultMap;
    }
}