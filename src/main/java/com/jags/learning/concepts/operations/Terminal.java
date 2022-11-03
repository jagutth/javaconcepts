package com.jags.learning.concepts.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jags.learning.concepts.vo.Employee;
import com.jags.learning.concepts.vo.Product;

/*The terminal operation doesn’t return any stream. It returns a result of a certain type.

1. The terminal operations take Stream as input and produce the result.
2. After completion of the Terminal operation, you can’t use the Stream.
3. Terminal operation is eager in nature. The terminal operation processes all the elements of 
the stream before returning the result.*/
public class Terminal {

	public static void main(String[] args) {

		Predicate<Integer> numGreaterThanTwo = (num) -> num > 2;
		Comparator<Product> productPriceComparator = new Comparator<Product>() {

			@Override
			public int compare(Product o1, Product o2) {
				// TODO Auto-generated method stub
				return o1.getProdcutPrice() - o2.getProdcutPrice();
			}

		};

		/*
		 * 1. forEach()
		 * 
		 * The forEach() method works as a utility method that helps to iterate
		 * over a collection or stream.
		 * 
		 * The forEach() method accepts the reference of Consumer Interface and
		 * performs a certain action on each element of it which define in
		 * Consumer. As you can see forEach() accepts reference of Consumer that
		 * is action.
		 */
		Stream.of(1, 2, 3, 4, 5, 6, 7).forEach(System.out::println);

		/*
		 * 2. Collect()
		 * 
		 * collect() method is used to get all the elements from a stream and
		 * store them in the Collection. This method works based on the equals
		 * method of the class. It is a terminal operation because it produces
		 * the result from Stream.
		 */
		List<Integer> numbers = Arrays.asList(1, 3, 98, 24);
		List<Integer> filteredNumberList = numbers.stream().filter(numGreaterThanTwo).collect(Collectors.toList());
		System.out.println(filteredNumberList);

		/*
		 * Optional<T> reduce(BinaryOperator<T> accumulator)
		 * 
		 * This method performs a reduction on the elements of the stream, using
		 * an associative accumulation function, and returns an Optional object
		 * describing the reduced value.
		 */
		Employee emp1 = new Employee();
		emp1.setEmpId(53456);
		emp1.setEmpName("Jhon");
		Employee emp2 = new Employee();
		emp2.setEmpId(33456);
		emp2.setEmpName("Aron");
		List<Employee> emp = new ArrayList<>();
		emp.add(emp1);
		emp.add(emp2);
		emp.stream().map(Employee::getEmpName).reduce((name1, name2) -> name1 + ", " + name2)
				.ifPresent(System.out::println);

		/*
		 * T reduce(T identity, BinaryOperator<T> accumulator)
		 * 
		 * This method is returns the reduced value of the specified type T. The
		 * identity value must be an identity value for the accumulator
		 * function, which means it does not affect the result of accumulation.
		 */
		Stream<Product> productStream = Stream.of(new Product(1231, "Pen", 10), new Product(1232, "Pencil", 5));
		Integer totalPrice = productStream.map(Product::getProdcutPrice).reduce(0, (price1, price2) -> price1 + price2);
		System.out.println(totalPrice);

		/*
		 * reduce(U identity, BiFunction accumulator, BinaryOperator combiner)
		 * 
		 * identity - The identity value for the combiner function
		 * 
		 * accumulator - Accumulator is function for incorporating additional
		 * element into result.
		 * 
		 * combiner - Combiner is function for combining two values. Combiner
		 * works with only parallelStream.
		 */
		Stream<Product> productStream1 = Stream.of(new Product(1231, "Pen", 10), new Product(1232, "Pencil", 5));
		totalPrice = productStream1.reduce(0, (result, current) -> result + current.getProdcutPrice(), (a, b) -> a + b);
		System.out.println(totalPrice);

		/*
		 * allMatch()
		 * 
		 * Do all elements in the stream meet this condition? It returns true if
		 * and only if all elements match a provided predicate, otherwise return
		 * false. This is a short-circuiting terminal operation because the
		 * operation stops immediately if any unmatched element is found (just
		 * like short-circuit behavior of the AND operator).
		 */
		productStream = Stream.of(new Product(1231, "Pen", 10), new Product(1232, "Pencil", 5));
		boolean allHas5dollarPrice = productStream.allMatch(p -> p.getProdcutPrice() == 5);
		System.out.println(allHas5dollarPrice);

		/*
		 * anyMatch()
		 * 
		 * operation returns true if any element in the stream matches a
		 * provided predicate. In other words, it answers the following
		 * question: Is there any element that meets this condition?
		 */
		productStream = Stream.of(new Product(1231, "Pen", 10), new Product(1232, "Pencil", 5));
		boolean hasPen = productStream.anyMatch(p -> p.getProductName().equals("Pen"));
		System.out.println(hasPen);

		/*
		 * noneMatch()
		 * 
		 * operation returns true if no elements in the stream match a provided
		 * predicate. In other words, it answers the question: Does no element
		 * meet this condition?
		 */
		productStream = Stream.of(new Product(1231, "Pen", 10), new Product(1232, "Pencil", 5));
		boolean hasNoScale = productStream.noneMatch(p -> p.getProductName().equals("Scale"));
		System.out.println(hasNoScale);

		/*
		 * count()
		 * 
		 * operation simply returns total number of elements in the stream
		 */
		productStream = Stream.of(new Product(1231, "Box", 100), new Product(1231, "Scale", 10),
				new Product(1231, "Pen", 10), new Product(1232, "Pencil", 5));
		System.out.println(productStream.count());

		/*
		 * min(comparator)
		 * 
		 * It is a special reduction operation that returns the minimum element
		 * in the stream according to the provided comparator. It returns an
		 * Optional which is a container object that contains the value.
		 */
		productStream = Stream.of(new Product(1231, "Box", 100), new Product(1231, "Scale", 10),
				new Product(1231, "Pen", 10), new Product(1232, "Pencil", 5));
		productStream.min(productPriceComparator).ifPresent(p -> System.out.println(p.getProductName()));

		productStream = Stream.of(new Product(1231, "Box", 100), new Product(1231, "Scale", 10),
				new Product(1231, "Pen", 10), new Product(1232, "Pencil", 5));
		productStream.min(Comparator.comparingInt(Product::getProdcutPrice))
				.ifPresent(p -> System.out.println(p.getProductName()));

		productStream = Stream.of(new Product(1231, "Box", 100), new Product(1231, "Scale", 10),
				new Product(1231, "Pen", 10), new Product(1232, "Pencil", 5));
		productStream.min((p1, p2) -> p1.getProdcutPrice() - p2.getProdcutPrice())
				.ifPresent(p -> System.out.println(p.getProductName()));

		/*
		 * max()
		 * 
		 * is a special reduction operation that returns the maximum element in
		 * the stream according to the specified comparator.
		 */
		productStream = Stream.of(new Product(1231, "Box", 100), new Product(1231, "Scale", 10),
				new Product(1231, "Pen", 10), new Product(1232, "Pencil", 5));
		productStream.max(productPriceComparator).ifPresent(p -> System.out.println(p.getProductName()));

		productStream = Stream.of(new Product(1231, "Box", 100), new Product(1231, "Scale", 10),
				new Product(1231, "Pen", 10), new Product(1232, "Pencil", 5));
		productStream.max(Comparator.comparingInt(Product::getProdcutPrice))
				.ifPresent(p -> System.out.println(p.getProductName()));

		productStream = Stream.of(new Product(1231, "Box", 100), new Product(1231, "Scale", 10),
				new Product(1231, "Pen", 10), new Product(1232, "Pencil", 5));
		productStream.max((p1, p2) -> p1.getProdcutPrice() - p2.getProdcutPrice())
				.ifPresent(p -> System.out.println(p.getProductName()));

	}

}
