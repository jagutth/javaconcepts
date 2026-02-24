package com.jags.learning.concepts.datatypes;

public class DataTypeTutorial {
    public static void main(String[] args) {
        
        // --- 1. PRIMITIVE DECLARATIONS ---
        
        byte smallNum = 127;        // Max value for 1 byte
        int standardNum = 5000;    // 4 bytes (standard for whole numbers)
        long bigNum = 9000000000L; // 8 bytes (Note the 'L' suffix)
        
        float decimalF = 3.14f;    // 4 bytes (Note the 'f' suffix)
        double decimalD = 3.14159; // 8 bytes (Standard for precision)
        
        char letter = 'A';         // 2 bytes (Stores Unicode)
        boolean isJavaFun = true;  // Size varies by JVM

        // --- 2. WIDENING CASTING (Automatic) ---
        // Converting a smaller type to a larger type size. No data loss.
        
        int myInt = smallNum;      // byte -> int (Automatic)
        double myDouble = myInt;   // int -> double (Automatic)
        System.out.println("Widened Double: " + myDouble); // Prints 127.0


        // --- 3. NARROWING CASTING (Manual) ---
        // Converting a larger type to a smaller type. REQUIRES (type) syntax.
        
        double pi = 3.99;
        int truncatedPi = (int) pi; // Manual cast: double -> int
        // WARNING: Narrowing decimals to integers removes everything after the dot!
        System.out.println("Truncated Int: " + truncatedPi); // Prints 3


        // --- 4. THE OVERFLOW PITFALL (Critical!) ---
        // What happens when you cast a number too big for the target type?
        
        int tooBigForByte = 130; 
        byte overflowedByte = (byte) tooBigForByte;
        
        // Logic: byte range is -128 to 127. 130 "wraps around" the circle.
        // Expected 130, but result will be -126!
        System.out.println("Overflow Result: " + overflowedByte); 


        // --- 5. EXPRESSION PROMOTION ---
        // When performing math, Java promotes results to the largest type in the mix.
        
        byte b1 = 10;
        byte b2 = 20;
        // byte b3 = b1 + b2; // ERROR! Java promotes 'b1 + b2' to an int automatically.
        byte b3 = (byte) (b1 + b2); // Correct way to handle expression casting.
        
        System.out.println("Byte Sum: " + b3);
    }
}
