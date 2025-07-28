package com.marieteck.gestionstock_backend.model;

public enum ImageSize {
    ORIGINAL("original"),
    LARGE2X("large2x"),
    LARGE("large"),
    MEDIUM("medium"),
    SMALL("small"),
    PORTRAIT("portrait"),
    LANDSCAPE("landscape"),
    TINY("tiny");

    private final String value;

    ImageSize(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
