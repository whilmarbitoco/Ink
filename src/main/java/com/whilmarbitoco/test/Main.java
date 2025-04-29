package com.whilmarbitoco.test;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " is a prime: " + isPrime(i));
        }
    }

    private static int factorial(int n) {
        if (n == 1) return n;

        return n * factorial(n - 1);
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        return isPrimeHelper(n, 2);
    }

    private static boolean isPrimeHelper(int n, int divisor) {
        if (divisor > Math.sqrt(n)) return true;
        if (n % divisor == 0) return false;

        return isPrimeHelper(n, divisor + 1);
    }


//    private static boolean isPrime(int n) {
//        for (int i = 2; i < n / 2; i++) {
//            if (n % i == 0) return false;
//        }
//        return true;
//    }


}
