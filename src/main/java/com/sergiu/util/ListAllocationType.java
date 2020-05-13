package com.sergiu.util;

public enum ListAllocationType {

    L1("L1"),
    L2("L2"),
    L3("L3"),
    L4("L4"),
    L5("L5"),
    L6("L6"),
    L7("L7"),
    L8("L8");

    ListAllocationType(String type) {
        this.type = type;
    }

    private final String type;

    public String getType() {
        return type;
    }
}
