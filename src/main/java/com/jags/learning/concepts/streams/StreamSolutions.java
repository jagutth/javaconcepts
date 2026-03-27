package com.jags.learning.concepts.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamSolutions {

    public static void main(String[] args) {

        //Calculate average of integers using streams

        IntStream intStream = IntStream.range(0,100);
        double average = intStream.average().orElse(0);
        System.out.println("Average of integers from 0 to 99 is: " + average);

        //Convert strings to upper/lowercase using streams
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
        names.stream().map(String::toUpperCase).forEach(System.out::println);

         List < Integer > numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        long count = numbers.stream().filter(n -> n % 2 == 0).mapToInt(Integer::intValue).sum();
        System.err.println("Sum of even numbers from 1 to 10 is: " + count);

       //calculate the sum of all even, odd numbers in a list using streams.
        Map<Boolean, List<Integer>> partitionedNumbers = numbers.stream().collect(Collectors.partitioningBy(n -> n%2 == 0));
        System.out.println("Even numbers sum: " + partitionedNumbers.get(true).stream().mapToInt(Integer::intValue).sum());
        System.out.println("Odd numbers sum: " + partitionedNumbers.get(false).stream().mapToInt(Integer::intValue).sum());


        // remove all duplicate elements from a list using streams.
         List < Integer > nums = Arrays.asList(10, 23, 22, 23, 24, 24, 33, 15, 26, 15);
         nums.stream().distinct().forEach(System.out::println);
        
         // Count strings starting with letter using streams
        List<String> words = Arrays.asList("apple", "banana", "bherry", "date", "elderberry", "fig", "grape");
        Map<Character, List<String>> groupedWords = words.stream().collect(Collectors.groupingBy(str -> str.charAt(0)));
        groupedWords.forEach((key, value) -> System.out.println("Words starting with " + key + ": count = " + value.stream().count()));

       // Find 2nd smallest/largest using streams
        List < Integer > nums1 = Arrays.asList(10, 23, 22, 23, 24, 24, 33, 15, 26, 15);
        nums1.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().ifPresent(secondLargest -> System.out.println("Second largest: " + secondLargest));
        nums1.stream().sorted().skip(1).findFirst().ifPresent(secondSmallest -> System.out.println("Second smallest: " + secondSmallest));

       

       
    }
}
