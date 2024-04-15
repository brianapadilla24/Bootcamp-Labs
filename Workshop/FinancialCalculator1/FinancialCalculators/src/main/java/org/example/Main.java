package org.example;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    private static final int MONTHS_IN_A_YEAR = 12;
    public static void main(String[] args) {


        float annualInterestRate;
        int loanTime;
        double principal;


        Scanner scanner = new Scanner(System.in);
        System.out.println("Mortgage Calculator: ");
        System.out.println("This calculator will calculate how much" +
                "a monthly payment for a loan would be. As well as " +
                "how much interest you would pay over the life" +
                "of the loan.");

        System.out.println("Enter Principal amount: ");
        principal = scanner.nextDouble();

        System.out.println("Enter annual interest rate: ");
        annualInterestRate = scanner.nextFloat();
        //float monthlyInterestRate = annualInterestRate / MONTHS_IN_A_YEAR;

        System.out.println("Enter loan years: ");
        loanTime = scanner.nextInt();

        float monthlyInterestRate = (annualInterestRate / MONTHS_IN_A_YEAR) / 100;

        //calculate the number of monthly payments
        int numberOfPayments = loanTime * MONTHS_IN_A_YEAR;

        //calculate the monthly payment
        double myMonthlyPayment = principal * (monthlyInterestRate * (Math.pow(1 + monthlyInterestRate, numberOfPayments))) /
                ((Math.pow(1 + monthlyInterestRate, numberOfPayments)) - 1);


        System.out.println("Monthly Payment: $" + NumberFormat.getCurrencyInstance().format(myMonthlyPayment));

        System.out.println("Total payback amount: " + NumberFormat.getCurrencyInstance().format(myMonthlyPayment * numberOfPayments));

        double totalInterest = (myMonthlyPayment * numberOfPayments) - principal;
        System.out.println("The total interest paid: " + NumberFormat.getCurrencyInstance().format(totalInterest));
//       double theTotalInterest = (monthlyInterestRate * loanTime) - principal;
//        double theTotalInterest = (myMonthlyPayment * loanTime) - principal;
//        System.out.println("Total interest: " + theTotalInterest);

        //scanner.close();
    }


}