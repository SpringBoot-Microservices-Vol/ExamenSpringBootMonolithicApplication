package com.sergiu.util;

public enum AdmissionOption {

    RO_BUGET("RO-BUGET"),
    RO_TAXA("RO-TAXA"),
    EN_BUGET("EN-BUGET"),
    EN_TAXA("EN-TAXA"),
    MD_RO_BUGET("MD-RO-BUGET"),
    MD_EN_BUGET("MD-EN-BUGET");

    private String type;

    AdmissionOption(String type) {
        this.type = type;
    }

    public static AdmissionOption get(String value) {
        if ("RO-BUGET".equals(value)) {
            return RO_BUGET;
        }

        if ("RO-TAXA".equals(value)) {
            return RO_TAXA;
        }

        if ("EN-BUGET".equals(value)) {
            return EN_BUGET;
        }

        if ("EN-TAXA".equals(value)) {
            return EN_TAXA;
        }

        if ("MD-RO-BUGET".equals(value)) {
            return MD_RO_BUGET;
        }

        if ("MD-EN-BUGET".equals(value)) {
            return MD_EN_BUGET;
        }
        return null;
    }
}
