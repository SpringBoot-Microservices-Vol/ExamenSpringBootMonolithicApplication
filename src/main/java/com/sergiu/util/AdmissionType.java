package com.sergiu.util;

public enum AdmissionType {

    ADMITERE("Admitere"), PREADMITERE("PreAdmitere"), OLIMPIC("Olimpic");
    private String type;

    AdmissionType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public static AdmissionType get(String value) {
        if ("Admitere".equals(value)) {
            return ADMITERE;
        }

        if ("PreAdmitere".equals(value)) {
            return PREADMITERE;
        }

        if ("Olimpic".equals(value)) {
            return OLIMPIC;
        }

        return null;
    }
}
