package com.ongtonnesoup.scrummd.domain.models.numbermodels;

public class FibonacciNumberModel extends NumberModel {

    public FibonacciNumberModel() {
        mName = "Fibonacci";
        setNumbers(new String[]{"0", "1", "2", "3", "5", "8", "13", "21", "34", "55", "89"});
        setInitialValue(0);
    }
}
