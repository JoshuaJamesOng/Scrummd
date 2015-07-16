package com.ongtonnesoup.scrum.models.numbers;

public abstract class NumberModel {

    private String[] mNumbers;
    private String mInitialValue;

    public String[] getNumbers() {
        return mNumbers;
    }

    public String getInitialValue() {
        return mInitialValue;
    }

    protected void setNumbers(String[] values) {
        mNumbers = values;
    }

    protected void setInitialValue(int index) {
        mInitialValue = mNumbers[index];
    }

}
