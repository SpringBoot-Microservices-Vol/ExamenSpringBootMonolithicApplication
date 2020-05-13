package com.sergiu.dto;

import com.sergiu.model.ColumnReport;

import java.io.Serializable;
import java.util.List;

public class ReportHallsDTO implements Serializable {
    List<HallDTO> sourceList;

    List<ColumnReport> columnsReport;

    public List<HallDTO> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<HallDTO> sourceList) {
        this.sourceList = sourceList;
    }

    public List<ColumnReport> getColumnsReport() {
        return columnsReport;
    }

    public void setColumnsReport(List<ColumnReport> columnsReport) {
        this.columnsReport = columnsReport;
    }
}
