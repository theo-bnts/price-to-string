package fr.upjv.iut.dut2.numbers.tools;

import java.util.*;

public class Price {

    private final double value;
    private int wholePart;
    private int decimalPart;

    public Price(double value) {
        if (value < 0.01 || value > 999_999_999.99)
            throw new IllegalArgumentException("Entered price looks invalid");

        this.value = value;

        this.splitValue();
    }

    private void splitValue () {
        this.wholePart = (int) this.value;

        int decimalsCount = Tools.getDecimalsCount(this.value);
        if (decimalsCount > 2) {
            throw new IllegalArgumentException("Precision exceeded");
        }

        this.decimalPart = (int) Math.round((this.value - this.wholePart) * Math.pow(10, decimalsCount));

        if (decimalsCount == 1) {
            this.decimalPart *= 10;
        }
    }

    public String toString() {
        String callback = "";

        if (this.wholePart > 0) {
            List<Integer> digits = Tools.explodeInteger(this.wholePart);
            callback += Digits.toString(digits, true);

            if (this.wholePart == 1) {
                callback += " euro ";
            } else {
                callback += " euros ";
            }

            if (decimalPart > 0) {
                callback += " et ";
            }
        }

        if (decimalPart > 0) {
            List<Integer> digits = Tools.explodeInteger(this.decimalPart);
            callback += Digits.toString(digits, true);

            if (this.decimalPart == 1) {
                callback += " centime ";
            } else {
                callback += " centimes ";
            }
        }

        return callback.replaceAll(" +", " ").trim();
    }

}