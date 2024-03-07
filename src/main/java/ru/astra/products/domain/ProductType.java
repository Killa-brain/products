package ru.astra.products.domain;

import java.util.Arrays;

public enum ProductType {
    ACCOUNT("account"),
    CARD("card");

    private String value;

    ProductType(String value) {
        this.value = value;
    }

    public static ProductType getByValue(String value) {
        return Arrays.stream(values())
                .filter(p -> value.equals(p.getValue()))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public String getValue() {
        return this.value;
    }
}
