package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        float firstNumber;
        float secondNumber;
        String calculation;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the first number: ");
        firstNumber = scanner.nextFloat();

        System.out.println("Enter the second number: ");
        secondNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Would you like to (A)dd, (S)ubtract, (M)ultiply, or (D)ivide the first number from the second number. ");

        String mathMethod = scanner.nextLine();


        if (mathMethod.equalsIgnoreCase("A")) {
            System.out.println(firstNumber + " + " + secondNumber + " = " + (firstNumber + secondNumber));
        } else if (mathMethod.equalsIgnoreCase("S")) {
            System.out.println(firstNumber + " - " + secondNumber + " = " + (firstNumber - secondNumber));
        } else if (mathMethod.equalsIgnoreCase("M")) {
            System.out.println(firstNumber + " * " + secondNumber + " = " + (firstNumber * secondNumber));
        } else if (mathMethod.equalsIgnoreCase("D")) {
            System.out.println(firstNumber + " / " + secondNumber + " = " + (firstNumber / secondNumber));
        }


    }

    public static float addTwoNumbers(float firstNumber, float secondNumber) {
        return firstNumber + secondNumber;
    }

    public static float subtractTwoNumbers(float firstNumber, float secondNumber) {
        return firstNumber + secondNumber;

    }

    public static float multiplyTwoNumbers(float firstNumber, float secondNumber) {
        return firstNumber * secondNumber;
    }

    public static float divideTwoNumbers(float firstNumber, float secondNumber) {
        return firstNumber / secondNumber;

    }

}