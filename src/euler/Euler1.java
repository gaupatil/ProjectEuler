package euler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import sun.dc.path.PathConsumer;

/**
 * 
 * @author Gauri
 * 
 */

public class Euler1 {

	
	public static void main(String[] args) {
		Euler1 euler = new Euler1();
		euler.testAll();

	}
	
	public void testAll(){
		
		//Sum of Multiples
		System.out.println("Sum of Multiples for 10: "+getSumOfMultiples(10));
		System.out.println("Sum of Multiples for 1000: "+getSumOfMultiples(1000));
		
		//Fibonacci
		/*
		System.out.println("Sum of Even Fibonacci for 1: "+s(1));
		System.out.println("Sum of Even Fibonacci for 2: "+s(2));
		System.out.println("Sum of Even Fibonacci for 3: "+s(3));
		System.out.println("Sum of Even Fibonacci for 4: "+s(4));
		System.out.println("Sum of Even Fibonacci for 5: "+s(5));
		System.out.println("Sum of Even Fibonacci for 6: "+s(6));
		System.out.println("Sum of Even Fibonacci for 7: "+s(7));
		System.out.println("Sum of Even Fibonacci for 8: "+s(8));
		System.out.println("Sum of Even Fibonacci for first 9 terms: "+s(9));
		*/
		System.out.println("Sum of Even Fibonacci for first 10 Fibonacci terms: "+s(10));		
		//System.out.println("Sum of Even Fibonacci for first 100 terms: "+s(100));
		
		//Largest Prime
		/*
		System.out.println("LargestPrimeFactor of 1: "+largestPrimeFactor(1));
		System.out.println("LargestPrimeFactor of 2: "+largestPrimeFactor(2));
		System.out.println("LargestPrimeFactor of 3: "+largestPrimeFactor(3));
		System.out.println("LargestPrimeFactor of 5: "+largestPrimeFactor(5));
		System.out.println("LargestPrimeFactor of 6: "+largestPrimeFactor(6));
		System.out.println("LargestPrimeFactor of 10: "+largestPrimeFactor(10));
		System.out.println("LargestPrimeFactor of 7: "+largestPrimeFactor(7));
		System.out.println("LargestPrimeFactor of 36: "+largestPrimeFactor(36));
		System.out.println("LargestPrimeFactor of 13: "+largestPrimeFactor(13));
		System.out.println("LargestPrimeFactor of 54: "+largestPrimeFactor(54));
		System.out.println("LargestPrimeFactor of 13195: "+largestPrimeFactor(13195));
		*/
		System.out.println("LargestPrimeFactor of 600851475143: "+largestPrimeFactor(600851475143.0));
		
	
		
		System.out.println("Sum of first 10 digits of total sum: "+getSumOfFirstTen("fifties.txt"));
			

	}
	
	/**
	 * Problem 1: MULTIPLES OF 3 AND 5
	 * If we list all the natural numbers below 10 
	 * that are multiples of 3 or 5, we get 3, 5, 6 
	 * and 9. The sum of these multiples is 23. 
	 * Find the sum of all the multiples of 3 or 5 below 1000
	 *
	 */
	public int getSumOfMultiples(int number){
		int sum = 0;
		for (int i=0; i<number; i++){
			if (i % 3 == 0)
				sum += i;
			else if (i % 5 == 0)
				sum += i;
		}
		return sum;
	}
	 
	/**
	 * Problem 2: EVEN FIBONACCI NUMBERS
	 * Each new term in the Fibonacci sequence is 
	 * generated by adding the previous two terms. 
	 * By starting with 1 and 2, the first 10 terms will be:
	 * 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
	 * By considering the terms in the Fibonacci sequence
	 * whose values do not exceed four million, find the sum
	 * of the even-valued terms.
	 * 
	 * f returns the fibonacci function
	 * s returns the sum of Fibonacci numbers below 4 million
	 * @param i : The sequence number
	 */
	public int f(int i) throws Exception{
		 if (i==1) return 1;
		 if (i==2) return 2;
		 return f(i-1) + f(i-2);
	}
	public double s(int i){
		try {
			if (i==1) return 0;
			if (i==2) return 2;
			else {
				int f = f(i);
				if (f%2 == 0 && f<4000000)
					return s(i-1)+f;
				else 
					return s(i-1);
			}
		} catch (Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	/**
	 * Problem 3: LARGEST PRIME FACTOR
	 * The prime factors of 13195 are 5, 7, 13 and 29.
	 * What is the largest prime factor of the number 600851475143 ?
	 *
	 */
	public double largestPrimeFactor(double i){
		double max = Math.sqrt(i);
		double largest = i;
		
		if (i == 2)
			return 2;
		while (i % 2 == 0)			
	    {				
	    	i = i/2;
	    	largest = i;
	    	
	    }

		//max value to iterate is square root of original number		
		for (int j=3; j<=max; j+=2){
			while (i%j == 0){					
				i = i/j;
				largest = j;
			}			
		}
		return largest;
	}
	/**
	 * Problem 13: LARGE SUM
	 * Work out the first ten digits of the sum of the following one-hundred 50-digit numbers.
	 * One hundred fifties
	 */
	public int getSumOfFirstTen(String filename){
		//open file
		String line = "";
		int sum = 0;
		Path path = FileSystems.getDefault().getPath(filename);	
		//System.out.println(path.toAbsolutePath());
		try ( BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			int numLines = 0;
			while((line = reader.readLine()) != null){
				numLines ++;
				char[] chars = line.toCharArray();
				for (char c : chars){
					sum += Character.getNumericValue(c);
				}
			}
			//System.out.println("Read "+numLines+" lines.");
		} catch (IOException e){
			e.printStackTrace();
		}
		System.out.println("Sum total of all digits in file = "+sum);
		char[] sumchars = (""+sum).toCharArray();
		int sum10 = 0;
		int max = sumchars.length < 10 ? sumchars.length : 10;
		for (int i=0; i<max; i++){
			sum10+=Character.getNumericValue(sumchars[i]);
		}
		return sum10;		
		
	}
	
	/**
	 * Problem 15: LATTICE PATHS
	 * Starting in the top left corner of a 2�2 grid, and only being
	 * able to move to the right and down, there are exactly 6 routes
	 * to the bottom right corner.
	 * How many such routes are there through a 20�20 grid?
	 */
	
	/** 
	 * Problem 28: NUMBER SPIRAL DIAGONALS
	 * Starting with the number 1 and moving to the right in a 
	 * clockwise direction a 5 by 5 spiral is formed as follows:
	 * 
	 * 	21 22 23 24 25
	 *  20  7  8  9 10
	 * 	19  6  1  2 11
	 * 	18  5  4  3 12
	 * 	17 16 15 14 13
	 * 
	 * 	It can be verified that the sum of the numbers on the
	 *  diagonals is 101.
	 * 	What is the sum of the numbers on the diagonals in a 1001 by 1001
	 *  spiral formed in the same way?	 	
	 */

}
