package com.sergiu.util;

public enum StatusExam {
    ADMIS("ADMIS"), RESPINS("RESPINS");

    private String type;

    StatusExam(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
