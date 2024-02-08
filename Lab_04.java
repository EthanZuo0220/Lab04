package edu.pasadena.cs.cs03b;

import java.util.Scanner;

public class Lab_04 {
	Scanner input = new Scanner(System.in); // Scanner for user input
	double tuitionCost = 0; // Stores the initial tuition cost
	double annualInterestRate = 0; // Stores the annual interest rate
	double totalCostSingleYear = 0; // Stores total cost for a single year after 10 years
	double totalCostFourYear = 0; // Stores total cost for four years of tuition after 10 years
	
	
	
	String result = ""; // Result of the addition

	// Method to print the menu options to the user
	public void printList() {
		System.out.println("1. Calculate Tuition Cost");
		System.out.println("2. Addition Algorithm");
		System.out.print("Enter your choice: ");
	}

	// Method to calculate the total cost of single year tuition over ten years
	public static double calculateTotalCostSingleYear(double tuitionCost, double annualInterestRate) {
		// Iterating over a 10 year period to calculate compounded tuition cost
		for (int i = 1; i <= 10; i++) {
			tuitionCost *= (1 + annualInterestRate); // Apply interest rate each year
		}
		return tuitionCost; // Return the compounded tuition cost after 10 years
	}

	// Method to calculate the total cost of four-year tuition over ten years
	public static double calculateTotalCostFourYear(double tuitionCost, double annualInterestRate) {
		double totalCost = tuitionCost; // Initialize total cost with first year's tuition
		// Looping for the next three years to calculate compounded tuition for each
		// year
		for (int year = 1; year <= 3; year++) {
			tuitionCost *= (1 + annualInterestRate); // Apply interest rate each year
			totalCost += tuitionCost; // Add the cost for the year to the total cost
		}
		return totalCost; // Return the total cost of four years of tuition after 10 years
	}

	// Method to get tuition cost and interest rate from the user
	public void tuition() {
		// Get annual tuition
		do{
			if (tuitionCost < 0) {
				System.out.println("Invalid input!");
			}
			System.out.print("Enter the tuition cost per year: ");
			tuitionCost = input.nextDouble();
		}while(tuitionCost < 0);
		

		// Get the annual interest rate
		do{
			if (annualInterestRate < 0) {
				System.out.println("Invalid input!");
			}
			System.out.print("Enter the annual interest rate (5% = 0.05): ");
			annualInterestRate = input.nextDouble();
			totalCostSingleYear = calculateTotalCostSingleYear(tuitionCost, annualInterestRate);
			totalCostFourYear = calculateTotalCostFourYear(totalCostSingleYear, annualInterestRate);
		}while (annualInterestRate < 0); // Loop until valid input is entered
	}

	// Method to print the results of tuition cost calculations
	public void printResult() {
		System.out.println("Single Year Tuition in ten years is " + String.format("%.15f", totalCostSingleYear));
		System.out.println("The four-year tuition in ten years is " + String.format("%.15f", totalCostFourYear));
	}

	// Method to convert a hexadecimal string to an integer array
	public int[] Hex_converter(String A, int m) {
		int[] convert = new int[m]; // Array to hold the converted hexadecimal values

		// Iterate over the string and convert each character
		for (int i = 0; i < m; i++) {
			char hexChar = A.charAt(A.length() - 1 - i); // Extract character from the end
			// Convert hexadecimal characters to their decimal equivalent
			switch (hexChar) {
				case 'A':
				case 'a':
					convert[m - 1 - i] = 10;
					break;
				case 'B':
				case 'b':
					convert[m - 1 - i] = 11;
					break;
				case 'C':
				case 'c':
					convert[m - 1 - i] = 12;
					break;
				case 'D':
				case 'd':
					convert[m - 1 - i] = 13;
					break;
				case 'E':
				case 'e':
					convert[m - 1 - i] = 14;
					break;
				case 'F':
				case 'f':
					convert[m - 1 - i] = 15;
					break;
				default:
					convert[m - 1 - i] = Integer.parseInt(String.valueOf(hexChar));
					break;
			}
		}
		return convert;
	}

	// Method for addition algorithm supporting different bases
	public void calculator(String A, String B, int m, int base) {
		int carry = 0; // Variable to hold carry value during addition
		int temp = 0; // Temporary variable for intermediate results

		// Implement addition logic based on the base
		if (base == 10) {
			// Decimal addition logic
			for (int i = 0; i < m; i++) {
				if(!Character.isDigit(A.charAt(A.length() - 1 - i)) || !Character.isDigit(B.charAt(B.length() - 1 - i))) {
					System.out.println("Cannot calculate decimal");
					result = "";
					carry = 0;
					break;
				}
				// Calculate sum of corresponding digits and carry
				temp = Integer.parseInt(String.valueOf(A.charAt(A.length() - 1 - i)))
						+ Integer.parseInt(String.valueOf(B.charAt(B.length() - 1 - i)))
						+ carry;
				// Append the unit digit of sum to the result
				result = String.valueOf(temp % 10) + result;
				// Update carry for next iteration
				carry = (temp > 9) ? 1 : 0;
			}
			// If carry exists after final iteration, append it
			result = (carry == 1) ? (carry + result) : result;
		} else if (base == 2) {
			// Binary addition logic
			for (int i = 0; i < m; i++) {
				if(!Character.isDigit(A.charAt(A.length() - 1 - i)) || !Character.isDigit(B.charAt(B.length() - 1 - i)) 
				|| (A.charAt(A.length() - 1 - i) > '1') || (B.charAt(B.length() - 1 - i) > '1' || A.charAt(A.length() - 1 - i) < '0') 
				|| (B.charAt(B.length() - 1 - i) < '0')){
					System.out.println("Cannot calculate binary");
					result = "";
					carry = 0;
					break;
				}
				// Similar logic as decimal but with base 2
				temp = Integer.parseInt(String.valueOf(A.charAt(A.length() - 1 - i)))
						+ Integer.parseInt(String.valueOf(B.charAt(B.length() - 1 - i)))
						+ carry;
				result = String.valueOf(temp % 2) + result;
				carry = (temp > 1) ? 1 : 0;
			}
			result = (carry == 1) ? (carry + result) : result;
		} else if (base == 16) {
			// Hexadecimal addition logic
			for(int i = 0; i < m; i++) {
				char hexCharA = A.charAt(A.length() - 1 - i);
				char hexCharB = B.charAt(B.length() - 1 - i);
				if (!((hexCharA >= '0' && hexCharA <= '9') || (hexCharA >= 'a' && hexCharA <= 'f') || (hexCharA >= 'A' && hexCharA <= 'F'))
					|| !((hexCharB >= '0' && hexCharB <= '9') || (hexCharB >= 'a' && hexCharB <= 'f') || (hexCharB >= 'A' && hexCharB <= 'F'))) {
					System.out.println("Cannot calculate hexadecimal");
					return;
				}
			}
			// Convert the hexadecimal strings to integer arrays
			int[] convertA = Hex_converter(A, m);
			int[] convertB = Hex_converter(B, m);

			// Loop to perform addition on each digit
			for (int i = 0; i < m; i++) {
				temp = convertA[m - 1 - i] + convertB[m - 1 - i] + carry;
				result = String.valueOf(temp % 16) + result;
				carry = (temp > 15) ? 1 : 0;
			}
			result = (carry == 1) ? (carry + result) : result;
		}
	}

	// Method to execute the addition algorithm
	public void Addition_Algorithm() {
		int m = 1; // Variable used in addition algorithm, represents the number of digits
		int base = 0; // Base for the number system used in addition (10, 2, or 16)
		String A = ""; // First number for addition
		String B = ""; // Second number for addition
		// Loop to validate and read base from user
		do {
			if (base != 0) {
				System.out.println("Invalid base!");
			}
			System.out.println("Enter the base (10, 2, or 16):");
			base = input.nextInt();
		} while (base != 10 && base != 2 && base != 16);

		// Loop to validate and read number of digits from user
		do {
			if (m < 1) {
				System.out.println("Invalid input!");
			}
			System.out.println("Enter the number of digits (m):");
			m = input.nextInt();
		} while (m < 1);
		input.nextLine(); // Consume newline left in buffer

		// Loop to validate and read first number from user
		do {
			if (!A.isEmpty()) {
				System.out.println("Invalid input!");
			}
			System.out.println("Enter the first number (A):");
			A = input.nextLine();
		} while (A.length() != m);

		// Loop to validate and read second number from user
		do {
			if (!B.isEmpty()) {
				System.out.println("Invalid input!");
			}
			System.out.println("Enter the second number (B):");
			B = input.nextLine();
		} while (B.length() != m);

		// Perform the addition calculation
		calculator(A, B, m, base);
		// Display the result
		if(!result.isEmpty()){
			System.out.println("The result of A + B = " + result);
			result = "";
		}
	}

	// Main method to run the program
	public static void main(String[] args) {
		char cont = 'Y';
		Scanner input = new Scanner(System.in);
		Lab_04 drill = new Lab_04();
		do{
			int choice = 0;
		do{
			if (choice != 0) {
				System.out.println("Invalid input!");
			}
			drill.printList();
			choice = input.nextInt();
		}while(choice != 1 && choice != 2);
		
		// Execute the chosen option
		if (choice == 1) {
			drill.tuition();
			drill.printResult();
		} else if (choice == 2) {
			drill.Addition_Algorithm();
		}
		input.nextLine(); // Consume newline left in buffer
		System.out.println("Do you want to continue? (Y/N)");
		cont = input.nextLine().toUpperCase().charAt(0);
	}while(cont == 'Y');
		input.close(); // Close the scanner
	}

	// A dummy method placeholder for future implementation
	public static int dummy() {
		// TODO: add your logic here
		return 1;
	}
}