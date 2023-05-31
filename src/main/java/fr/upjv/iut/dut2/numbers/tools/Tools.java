package fr.upjv.iut.dut2.numbers.tools;

import java.math.BigDecimal;
import java.util.*;

public abstract class Tools {

    public static List<Integer> explodeInteger(int value) {
        List<Integer> callback = new ArrayList<>();

        while (value > 0) {
            callback.add(value % 10);
            value /= 10;
        }

        Collections.reverse(callback);

        return callback;
    }

    public static int implodeList(List<Integer> valuesReference) {
        List<Integer> values = new ArrayList<>(valuesReference);

        Collections.reverse(values);

        int callback = 0;

        int i = 0;
        for (Integer digit : values) {
            callback += digit * Math.pow(10, i);
            i++;
        }

        return callback;
    }

    public static int getDecimalsCount(double value) {
        return BigDecimal.valueOf(value).scale();
    }

}