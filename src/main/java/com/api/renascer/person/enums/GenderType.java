package com.api.renascer.person.enums;

public enum GenderType {
    FEMALE("Feminino"),
    MALE("Masculino");

    private String label;

    GenderType(String label) {
        this.label = label;
    }
}
