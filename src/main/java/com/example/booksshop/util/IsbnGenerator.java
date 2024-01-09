package com.example.booksshop.util;

import java.util.Random;

public class IsbnGenerator {
    public static String generate() {
        return "ISBN-" + (new Random().nextInt(1000) + 1000);
    }
}
