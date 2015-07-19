package com.ongtonnesoup.scrum.models.numbers;

public abstract class NumberModel {

    protected String mName;
    private String[] mNumbers;
    private String mInitialValue;

    public String getName() {
        return mName;
    }

    public String[] getNumbers() {
        return mNumbers;
    }

    public String getNumber(int index) {
        return mNumbers[index];
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
