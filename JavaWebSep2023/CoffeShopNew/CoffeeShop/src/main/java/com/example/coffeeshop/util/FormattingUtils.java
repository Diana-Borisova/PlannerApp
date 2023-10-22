package com.example.coffeeshop.util;

public class FormattingUtils {
    public static String formatPrice(Double price) {
        return String.format("%.2f", price);
    }
}
