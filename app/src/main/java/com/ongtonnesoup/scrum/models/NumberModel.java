package com.ongtonnesoup.scrum.models;

public class NumberModel {

    private static final String[] numbers = {"1", "2", "3", "5", "8", "13", "20", "40", "100", "?"};
    public static final String INITIAL = numbers[0];
    private int mCurrentNumberIndex = 0;

    public String[] getValues() {
        return numbers;
    }

    public String getCurrent() {
        return numbers[mCurrentNumberIndex];
    }

    public void setCurrent(String number) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i].equals(number)) {
                mCurrentNumberIndex = i;
                break;
            }
        }
    }

    public void nextNumber() {
        if (mCurrentNumberIndex < numbers.length - 1) {
            mCurrentNumberIndex++;
        }
    }

    public void previousNumber() {
        if (mCurrentNumberIndex > 0) {
            mCurrentNumberIndex--;
        }
    }

}
