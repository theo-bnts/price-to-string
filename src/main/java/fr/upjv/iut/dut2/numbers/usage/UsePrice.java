package fr.upjv.iut.dut2.numbers.usage;

import fr.upjv.iut.dut2.numbers.tools.Price;

import java.util.*;

public class UsePrice {

    public static void main(String[] args) {

        System.out.println("Enter a price :");

        double price = 0;
        try {
            Scanner scanner = new Scanner(System.in);
            price = scanner.nextDouble();
        } catch (Exception e) {
            System.err.println("Entered number is not a double. Tip: Use ',' instead of '.'");
        }

        Price n = new Price(price);

        System.out.println(n.toString());

    }

}