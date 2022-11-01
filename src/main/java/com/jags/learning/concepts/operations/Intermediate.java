package com.jags.learning.concepts.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jags.learning.concepts.vo.Book;
import com.jags.learning.concepts.vo.Employee;

public class Intermediate {

	/*
	 * Java 8 Stream intermediate operations return another Stream which allows
	 * you to call multiple operations in the form of a query.
	 * 
	 * All Intermediate operations are lazy, so they’re not executed until a
	 * result of processing is actually needed.
	 * 
	 * Traversal of the Stream does not begin until the terminal operation of
	 * the pipeline is executed.
	 */

	public static void main(String[] args) {

		// filter()
		//
		// Returns a stream consisting of the elements of this stream that
		// match the given predicate.
		Stream<Integer> intStream = Stream.of(1, 2, 3, 4, 5, 6, 7);
		Stream<Integer> numGreaterThantwo = intStream.filter(num -> num > 2);
		System.out.println(numGreaterThantwo.count());

		// map()
		//
		// Returns a stream consisting of
		// the results of applying the given function to the elements of this
		// stream.
		Employee emp1 = new Employee();
		emp1.setEmpId(53456);
		emp1.setEmpName("Jhon");
		Employee emp2 = new Employee();
		emp2.setEmpId(33456);
		emp2.setEmpName("Aron");
		List<Employee> emp = new ArrayList<>();
		emp.add(emp1);
		emp.add(emp2);
		System.out.println(emp.stream().map(Employee::getEmpName).collect(Collectors.toList()));

		/*
		 * flatMap()
		 * 
		 * In Java 8 Streams, the flatMap() method applies operation as a mapper
		 * function and provides a stream of element values. It means that in
		 * each iteration of each element the map() method creates a separate
		 * new stream. By using the flattening mechanism, it merges all streams
		 * into a single resultant stream. In short, it is used to convert a
		 * Stream of Stream into a list of values.
		 * 
		 * Before flattening : [[1, 2, 3], [4, 5], [6, 7, 8]] After flattening :
		 * [1, 2, 3, 4, 5, 6, 7, 8]
		 */
		List<Integer> list1 = Arrays.asList(1, 2, 3);
		List<Integer> list2 = Arrays.asList(4, 5, 6);
		List<Integer> list3 = Arrays.asList(7, 8, 9);
		List<List<Integer>> listOfLists = Arrays.asList(list1, list2, list3);
		listOfLists.stream().flatMap(list -> list.stream()).forEach(System.out::println);

		/*
		 * distinct()
		 * 
		 * Returns a stream consisting of the distinct elements (according to
		 * Object.equals(Object)) of this stream.
		 * 
		 * For ordered streams, the selection of distinct elements is stable
		 * (for duplicated elements, the element appearing first in the
		 * encounter order is preserved.) For unordered streams, no stability
		 * guarantees are made.
		 */

		Book book1 = new Book();
		book1.setBookId(53456);
		book1.setBookName("Java8 concepts");
		Book book2 = new Book();
		book2.setBookId(53456);
		book2.setBookName("Java8 concepts");
		List<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
		books.stream().distinct().forEach(System.out::println);
		
		/*
		 * sorted() 
		 * 
		 * Returns a stream consisting of the elements of this stream,
		 * sorted according to the natural order.
		 * 
		 * If the elements of this stream are not Comparable, a
		 * java.lang.ClassCastException may be thrown when the terminal
		 * operation is executed.
		 * 
		 * Refer SortingConcept.java for detailed information
		 */
		Stream<String> vegStream = Stream.of("tomoto", "Green Chilli", "Pototo", "Beet root");
		vegStream.sorted().forEach(System.out::println);
		
		/*
		 * peek()
		 * 
		 * Returns a stream consisting of the elements of this stream,
		 * additionally performing the provided action on each element as
		 * elements are consumed from the resulting stream.
		 * 
		 * peek() method is the best way to debug the streams in Java 8. The
		 * eclipse will not support for debugging.
		 * 
		 * Note: We can not predict the order of peek() invocation for parallel
		 * stream pipelines.
		 * 
		 */
		Stream<String> stream = Stream.of("tomoto", "Green Chilli", "Pototo", "Beet root");
		stream.filter(o -> o.length() > 3)
		.peek(e -> System.out.println("Filtered value "+e))
		.map(e -> e.toUpperCase())
		.peek(e -> System.out.println("Mapped value "+e))
		.count();
		
		/*
		 * limit()
		 * 
		 * Returns a stream with the limited size given. It will
		 * truncate the remaining elements from the stream.
		 * 
		 * Note: limit() is suitable for sequential streams and cannot give good
		 * performance results for parallel streams.
		 */
		Stream<String> stream1 = Stream.of("tomoto", "Green Chilli", "Pototo", "Beet root");
		stream1.limit(2).forEach(System.out::println);
		
		/*
		 * skip() 
		 * 
		 * This method skips the given n elements and returns a Stream.
		 * 
		 * This is the most useful when want to perform any operations on last n
		 * records or lines from a List or Stream.
		 */
		Stream.of("one", "two", "three", "four", "five").skip(2)
		.forEach(System.out::println);
	}
}
