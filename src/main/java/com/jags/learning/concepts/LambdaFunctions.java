package com.jags.learning.concepts;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/**
 * Hello world!
 *
 */
public class LambdaFunctions {

	private static int var = 10;

	public LambdaFunctions(Integer x) {
		System.out.println(x);
	}

	public LambdaFunctions() {

	}

	public static void main(String[] args) {
		// Print list of integers with lambda
		List<Integer> integerList = Arrays.asList(1, 2, 3, 5, 6, 7);
		integerList.forEach(x -> System.out.println(x));

		// A multiline lambda
		List<Integer> intSeq = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		intSeq.forEach(x -> {
			x += 2;
			System.out.println(x);
		});

		// A Lambda defined with local variable
		/*
		 * Just as with ordinary functions, you can define local variables
		 * inside the body of a lambda expression
		 */
		List<Integer> intSeq1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		intSeq1.forEach(x -> {
			int y = x + 2;
			System.out.println(y);
		});

		// A lambda with declared parameter
		// You can, if you wish, specify the parameter type.
		List<Integer> intSeq2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		intSeq2.forEach((Integer x) -> {
			x += 2;
			System.out.println(x);
		});

		// Assigning a Lambda to a Local Variable
		/*
		 * Java 8 lambdas are assigned to functional interfaces. A functional
		 * interface is a Java interface with exactly one non-default method.
		 */
		List<Integer> intSeq3 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		Consumer<Integer> cnsmr = x -> System.out.println(x);
		intSeq3.forEach(cnsmr);

		/*
		 * Lambdas can interact with variables defined outside the body of the
		 * lambda Using these variables is called variable capture local
		 * variables used inside the body of a lambda must be final or
		 * effectively final
		 */
		List<Integer> intSeq4 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		int X = 0;
		intSeq4.forEach(x -> {
			System.out.println(X + x);

			// Static Variable Capture Example
			System.out.println(var + x);
		});

		// Method References
		/*
		 * Method references can be used to pass an existing function in places
		 * where a lambda is expected The signature of the referenced method
		 * needs to match the signature of the functional interface method
		 */
		List<Integer> intSeq5 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

		// static method reference
		intSeq5.forEach(System.out::println);

		// object reference
		LambdaFunctions a = new LambdaFunctions();
		intSeq5.forEach(a::customPrint);

		// Constructor reference
		intSeq5.forEach(LambdaFunctions::new);

	}

	void customPrint(Integer x) {
		System.out.println(x);
	}

}
