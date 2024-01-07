package com.api.renascer.person.enums;

public enum MaritalStatusType {
    SINGLE("Solteiro(a)"),
    GROOM("Noivo(a)"),
    MARRIED("Casado(a)"),
    STABLEUNION("União estável"),
    WIDOWER("Viúvo(a)"),
    DIVORCED("Divorciado(a)");

    private String label;

    MaritalStatusType(String label) {
        this.label = label;
    }
}
