package com.sergiu.dto;

import com.sergiu.model.ColumnReport;

import java.io.Serializable;
import java.util.List;

public class ReportCandidatesDTO implements Serializable {

    List<CandidateDTO> sourceList;

    List<ColumnReport> columnsReport;

    public List<CandidateDTO> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<CandidateDTO> sourceList) {
        this.sourceList = sourceList;
    }

    public List<ColumnReport> getColumnsReport() {
        return columnsReport;
    }

    public void setColumnsReport(List<ColumnReport> columnsReport) {
        this.columnsReport = columnsReport;
    }
}
