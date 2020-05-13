package com.sergiu.service;

import com.sergiu.dto.ReportCandidatesDTO;
import com.sergiu.dto.ReportHallsDTO;
import com.sergiu.util.ListAllocationType;

import java.io.File;

public interface ReportService {

    File buildGeneralListDistributedReport();

    File buildCandidatesListWithoutExam();

    File buildCandidatesListFromHall(Integer hallId);

    File buildGeneralListWithGradesReport();

    File buildListL(ListAllocationType type) throws Exception;

    File generateReportCandidates(ReportCandidatesDTO reportCandidatesDTO);

    File generateReportHalls(ReportHallsDTO reportHallsDTO);
}
