package com.epam;

import java.util.Arrays;


public class App 
{
    public static void main( String[] args )
    {

        if (args.length > 0) {
            if (args.length < 10) {
                System.out.println("Invalid input. Please provide at least ten integer values as command-line arguments.");
            } else if (args.length > 10) {
                System.out.println("Invalid input. Please provide up to ten integer values as command-line arguments.");
            } else {
                int[] numbers = new int[args.length];

                for (int i = 0; i < args.length; i++) {
                    numbers[i] = Integer.parseInt(args[i]);
                }

                Arrays.sort(numbers);

                // Format the output as a string
                String sortedNumbers = Arrays.toString(numbers);

                System.out.println("Sorted Numbers: " + sortedNumbers);
            }
        } else {
            System.out.println("No input provided. Please provide integer values as command-line arguments.");
        }
    }

}

