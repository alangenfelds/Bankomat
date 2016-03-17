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
        return expectedAmount <= getTotalAmount();
    }
//--------------===========--------------------------------------------
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
    {
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>();
        sortedMap.putAll(denominations);

        HashMap<Integer, Integer> resultMap = new HashMap<>();  // rezultat zapishem sjuda


        for (Integer i : sortedMap.descendingKeySet()) //from max to min
        {
            int denomination = i;
            if (denomination <= expectedAmount)
            {
                int count = expectedAmount / denomination;  // vichislaem skolko kupjur tekusego nominala potrebuetsa
                while (count>denominations.get(i))
                {
                    count--;
                }
                expectedAmount =expectedAmount - denomination * count;  // otnimaem iz trebuemoj summi nominal*count
                resultMap.put(denomination, count); //dobavlaem polucennuju kombinaciju v resultat
                if (expectedAmount == 0) // esli trebuemaja summa=0 t.e. dostignuta, to zakanchivaem
                {
                    break;
                }
                if (expectedAmount < 0)
                {        // esli summa stala menjse 0 - znachit takaja kombinacija ne goditsa - vihodim
                    System.out.println("expectedAmount < 0");
                    throw new NotEnoughMoneyException();
                }
            }
        }
        if (expectedAmount > 0) //esli vsa summa esce ne vidana, znachit ne smogli skombinirovatj nuznuju summu - vihodim
            throw new NotEnoughMoneyException();

        HashMap<Integer, Integer> map = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet())  //kopiruem v novij map tekushuju situaciju
        {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (resultMap.containsKey(key)) //esli nominal estj v resultMap'e
            {
               if (value - resultMap.get(key) != 0) //esli (kol-vo tekushih kupjur - kol-vo kupjur v resulte)!=0
                    map.put(key, value - resultMap.get(key));  // to v map otnimaem iz kolva kupjur - kolvo vidannih kupjur
            }
            else  // a esli takoj nominal ne vidavali, to prosto zapisivaem v map takoe ze kol-vo kupjur kak i bilo iznacaljno v denominations
                map.put(key, value);
        }
        if (!map.isEmpty())
            denominations = map;  //updating original denominations iz novogo map'a
        else denominations.clear(); //esli deneg ne ostalosj udaljaem map denominations
        return resultMap;
    }
//--------------===========-------------------------------------------------------------
}