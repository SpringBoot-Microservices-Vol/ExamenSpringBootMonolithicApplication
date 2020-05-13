package com.sergiu.model;

import java.io.Serializable;

public class ColumnReport implements Serializable {
    public String field;

    public String text;

    public String dataType;

    public boolean isReport;

    public String getField() {
        return field;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public boolean isReport() {
        return isReport;
    }

    public void setReport(boolean report) {
        isReport = report;
    }
}
