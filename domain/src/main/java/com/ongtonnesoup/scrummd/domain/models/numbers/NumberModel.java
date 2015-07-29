package com.ongtonnesoup.scrummd.domain.models.numbers;

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

    protected void setNumbers(String[] values) {
        mNumbers = values;
    }

    public String getNumber(int index) {
        return mNumbers[index];
    }

    public String getInitialValue() {
        return mInitialValue;
    }

    protected void setInitialValue(int index) {
        mInitialValue = mNumbers[index];
    }

}
