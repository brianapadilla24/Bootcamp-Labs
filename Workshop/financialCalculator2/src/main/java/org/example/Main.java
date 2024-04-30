package org.example;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        double futureValue;
        double p; //principle
        float r; //annual interest rate
        double dailyCompounding; //# interest is compounded per year
        int t; //# of years the money is invested for
        int n = 365;

        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your principle? ");
        p = scanner.nextDouble();

        System.out.println("Annual Rate?: ");
        r = scanner.nextFloat();

        System.out.println("Enter number of Years: ");
        t = scanner.nextInt();

        //calculate the daily interest rate
        dailyCompounding = r / n;

        //future value
        n = 365; //assuming interest is compounded daily
        futureValue = p * Math.pow((1 + dailyCompounding / n), n * t);

        //total interest
        double totalInterest = futureValue - p;

        //display total interest
        //display future value

        System.out.println("Total interest: " + NumberFormat.getCurrencyInstance().format(totalInterest) +
                " Future value: " + NumberFormat.getCurrencyInstance().format(futureValue));
//
//        System.out.println("If: " + principle + " is invested at a rate of: " +
//                (100 * annualRate) + "% for " + numberYears + " years," +
//                "the future amount is $: " + futureValue +
//                "total interest of " + totalInterest);

    }
}
