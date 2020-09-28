package com.maxlogic.tutorials.java8.lambda_expression;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class LambdaExpressionExample {
	public static void main(String[] args) {
		
		//Find min/max from Stream of integer
		System.out.println(IntStream.of(2,3,1,5).min().orElse(0));
		System.out.println(Stream.of(2,3,4,1,5,6).min((a,b) -> a.compareTo(b)).orElse(0));
		System.out.println(Stream.of(2,3,4,5,6).max(Comparator.comparing(Integer::valueOf)).orElse(0));
		
		//Filter values based on condition and get it in one array
		List<Integer> filteredArr = IntStream.of(2,3,1,5).filter((a) -> a>=3);
	}
}
