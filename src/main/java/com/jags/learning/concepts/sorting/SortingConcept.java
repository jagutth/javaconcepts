package com.jags.learning.concepts.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.jags.learning.concepts.vo.Employee;

public class SortingConcept {

	public static void main(String[] args) {

		/*
		 * sorted()
		 * 
		 * returns a stream consisting of the elements of this stream,
		 * sorted according to natural order. For ordered streams, the sort
		 * method is stable but for unordered streams, no stability is
		 * guaranteed. It is a stateful intermediate operation i.e, it may
		 * incorporate state from previously seen elements when processing new
		 * elements.
		 */List<String> list = Arrays.asList("Jhon", "Aron", "34", "Bob");
		List<String> sortedList = list.stream().sorted().collect(Collectors.toList());
		System.out.println(sortedList);

		/*
		 * The reverseOrder() is a method of Comparator interface which is
		 * defined in java.util package. The method returns a comparator that
		 * imposes the reverse of the natural ordering. It throws
		 * NullPointerException when comparing null. The method signature is:
		 */
		List<String> list1 = Arrays.asList("Jhon", "Aron", "34", "Bob");
		list1.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

		/// Sorting the Custom Objects
		Employee emp1 = new Employee();
		emp1.setEmpId(53456);
		emp1.setEmpName("Jhon");
		Employee emp2 = new Employee();
		emp2.setEmpId(33456);
		emp2.setEmpName("Aron");

		List<Employee> emp = new ArrayList<>();
		emp.add(emp1);
		emp.add(emp2);

		emp.stream().sorted(Comparator.comparingInt(Employee::getEmpId)).forEach(System.out::println);

		// Defining a Custom Comparator with Stream.sorted()
		List<Employee> employee = new ArrayList<>();
		employee.add(emp1);
		employee.add(emp2);

		employee.stream().sorted((o1, o2) -> {
			if (o1.getEmpId() == o2.getEmpId())
				return o1.getEmpName().compareTo(o2.getEmpName());
			else if (o1.getEmpId() > o2.getEmpId())
				return 1;
			else
				return -1;
		}).forEach(System.out::println);

		// create a Comparator beforehand,
		// though, for the sake of code readability,
		// it's advised to shorten it to a Lambda
		employee.stream().sorted(empComparator).forEach(System.out::println);

	}

	static Comparator<Employee> empComparator = new Comparator<Employee>() {

		@Override
		public int compare(Employee o1, Employee o2) {
			// TODO Auto-generated method stub
			if (o1.getEmpId() == o2.getEmpId())
				return o1.getEmpName().compareTo(o2.getEmpName());
			else if (o1.getEmpId() > o2.getEmpId())
				return 1;
			else
				return -1;
		}

	};

}
