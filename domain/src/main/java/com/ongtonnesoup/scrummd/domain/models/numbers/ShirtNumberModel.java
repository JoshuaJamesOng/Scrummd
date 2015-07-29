package com.ongtonnesoup.scrummd.domain.models.numbers;

public class ShirtNumberModel extends NumberModel {

    public ShirtNumberModel() {
        mName = "Shirt Sizes";
        setNumbers(new String[]{"XS", "S", "M", "L", "XL"});
        setInitialValue(0);
    }

}
