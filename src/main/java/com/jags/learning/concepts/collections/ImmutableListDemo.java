package com.jags.learning.concepts.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// Note: Guava requires an external dependency, so it's commented out here
// import com.google.common.collect.ImmutableList;

public class ImmutableListDemo {

    public static void main(String[] args) {
        
        // 1. Java 9+: List.of()
        // The modern standard. Truly immutable and concise.
        List<String> modernStyle = List.of("Java", "Python", "C++");
       //  modernStyle.add("Rust"); // Throws UnsupportedOperationException
        
        
        // 2. Java 10+: List.copyOf()
        // Best for taking a "snapshot" of an existing mutable list.
        List<String> mutableList = new ArrayList<>();
        mutableList.add("Red");
        mutableList.add("Blue");
        List<String> snapshot = List.copyOf(mutableList);
        
        //snapshot.add("Green"); // Throws UnsupportedOperationException
        
        // 3. Java 8 & Older: Collections.unmodifiableList()
        // WARNING: This is a "view". If the underlying list changes, this one does too.
        List<String> original = new ArrayList<>();
        original.add("Apple");
        List<String> view = Collections.unmodifiableList(original);
        original.add("Banana"); // This works!
        //view.add("Cherry"); // Throws UnsupportedOperationException
        // System.out.println(view); // Prints [Apple, Banana] - NOT truly immutable.
        
        
        // 4. Java 16+: Stream.toList()
        // Streams now have a shortcut to produce an unmodifiable list.
        List<String> fromStream = mutableList.stream()
                .filter(s -> s.startsWith("R"))
                .toList();

        
        // 5. Shallow Immutability Warning
        // Even if the LIST is immutable, the OBJECTS inside might not be.
        List<StringBuilder> sbList = List.of(new StringBuilder("Hello"));
        sbList.get(0).append(" World"); 
        // The list didn't change, but the object AT index 0 did.
        
        printResults(modernStyle, snapshot, view, fromStream);
    }

    private static void printResults(List<?>... lists) {
        String[] labels = {"List.of", "List.copyOf", "Unmodifiable View", "Stream.toList"};
        for (int i = 0; i < lists.length; i++) {
            System.out.println(labels[i] + ": " + lists[i]);
        }
    }
}