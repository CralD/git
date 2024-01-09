package com.epam;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;

@RunWith(Parameterized.class)
public class AppTest 
    extends TestCase
{
    private String[] input;
    private String expectedOutput;

    public AppTest(String[] input, String expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                // Existing test cases
                //less than 10 integer values provided
                { new String[] { "5", "3", "8", "1", "7" }, "Invalid input. Please provide at least ten integer values as command-line arguments." },
                //10 integer values provided
                { new String[] { "5", "3", "8","1","4","10","20","11","2","7" }, "Sorted Numbers: [1, 2, 3, 4, 5, 7, 8, 10, 11, 20]" },
                // zero arguments provided
                { new String[] {}, "No input provided. Please provide integer values as command-line arguments." },
                //less than 10 integer values provided
                { new String[] { "5" }, "Invalid input. Please provide at least ten integer values as command-line arguments." },
                //more than 10 integer values provided
                { new String[] { "5", "3", "8", "1", "7", "12", "10", "9", "6", "4", "2", "15" }, "Invalid input. Please provide up to ten integer values as command-line arguments." },
                //more than 10 integer values provided
                { new String[] { "5", "3", "8", "1", "7", "12", "10", "9", "6", "4", "2", "15", "18", "14", "16", "19", "11", "13", "17" },
                        "Invalid input. Please provide up to ten integer values as command-line arguments." },
                //more than 10 integer values provided
                { new String[] { "5", "3", "8", "1", "7", "12", "10", "9", "6", "4", "2", "15", "18", "14", "16", "19", "11", "13", "17", "20", "22", "21", "25", "24", "23" },
                        "Invalid input. Please provide up to ten integer values as command-line arguments." }
        });
    }

    @Test
    public void testSortingApp() {
        // Redirect System.out to capture the output
        SystemOutTestUtils.enableOutputCapture();

        // Run the Sorting App with the provided input
        App.main(input);

        // Get the captured output
        String actualOutput = SystemOutTestUtils.getSystemOut().trim();

        // Assert that the actual output matches the expected output
        assertEquals(expectedOutput, actualOutput);

        // Reset System.out to normal
        SystemOutTestUtils.resetSystemOut();
    }


}
