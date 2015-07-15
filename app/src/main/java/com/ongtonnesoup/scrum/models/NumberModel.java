package com.ongtonnesoup.scrum.models;

public class NumberModel {

    private static final String[] numbers = {"1", "2", "3", "5", "8", "13", "20", "40", "100"};
    public static final String INITIAL = numbers[0];

    public static String[] getValues() {
        return numbers;
    }

}
