package fr.upjv.iut.dut2.numbers.tools;

public abstract class Tens {

    public static String toString(int value) {
        return switch (value) {
            case 1 -> "dix";
            case 2 -> "vingt";
            case 3 -> "trente";
            case 4 -> "quarante";
            case 5 -> "cinquante";
            case 6 -> "soixante";
            case 8 -> "quatre-vingt";
            default -> throw new IllegalArgumentException("Number not found");
        };
    }

}