package org.example;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //PV = PMT * [(1-(1+r)^(-n)) / r]
        //Accept the monthly payout (PMT), (r), (t)
        System.out.println("Enter the monthly payout amount PMT: ");
        double PMT = scanner.nextDouble();

        System.out.println("Annual Interested Rate : ");
        double annualInterestRate = scanner.nextDouble();

        System.out.println("Number of years to payout: ");
        int numberOfYears = scanner.nextInt();

        //calculate the monthly interest rate (r)
        //by dividing the annual interest rate by 12 &
        //then dividing by 100 to convert it to a decimal
        double monthlyInterestRate = annualInterestRate / 12  / 100;

        //total number of monthly payments (n) = number of years to payout * 12
        int monthlyPayments = numberOfYears * 12;

        //use formula to calculate the present value (PV) of the annuity
        //PV = PMT * [(1 - (1 + r)^(-n)) / r]

        double presentValue = PMT * ((1 - Math.pow(1 + monthlyInterestRate, - monthlyPayments)) / monthlyInterestRate);

        //display the present value of the annuity to user
        System.out.println("Your present value of annuity: " + NumberFormat.getCurrencyInstance().format(presentValue));

    }
}