package com.sergiu.service;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import com.sergiu.dto.ReportCandidatesDTO;
import com.sergiu.dto.ReportHallsDTO;
import com.sergiu.entity.AdmissionResult;
import com.sergiu.entity.Candidate;
import com.sergiu.model.ColumnReport;
import com.sergiu.repository.AdmissionResultRepository;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.util.AdmissionType;
import com.sergiu.util.FieldWidth;
import com.sergiu.util.ListAllocationType;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class ReportServiceImpl implements ReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportServiceImpl.class);
    public static final String CANDIDATES_TITLE = "Candidati";
    public static final String HALLS_TITLE = "Sali";
    public static final String CANDIDATES_PDF = "candidati.pdf";
    public static final String HALLS_PDF = "sali.pdf";

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private AdmissionResultRepository admissionResultRepository;

    @Value("classpath:distribustion/general_list_distributed.jrxml")
    private Resource generalListDistributedTemplate;

    @Value("classpath:distribustion/candidates_without_exam.jrxml")
    private Resource candidatesWithoutExam;

    @Value("classpath:distribustion/candidates_from_hall.jrxml")
    private Resource candidatesFromHall;

    @Value("classpath:results/general_list_results.jrxml")
    private Resource generalListResults;

    @Value("classpath:results/list_L1.jrxml")
    private Resource listL1;

    @Value("classpath:results/list_L2.jrxml")
    private Resource listL2;

    @Value("classpath:results/list_L3.jrxml")
    private Resource listL3;

    @Value("classpath:results/list_L4.jrxml")
    private Resource listL4;

    @Value("classpath:results/list_L5.jrxml")
    private Resource listL5;

    @Value("classpath:results/list_L6.jrxml")
    private Resource listL6;

    @Value("classpath:results/list_L7.jrxml")
    private Resource listL7;

    @Value("classpath:results/list_L8.jrxml")
    private Resource listL8;

    @Override
    public File buildGeneralListDistributedReport() {
        List<Candidate> candidates = candidateRepository.findAllByCategory_AdmissionTypeOrderByLastNameAscFirstNameAsc(AdmissionType.ADMITERE);
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(candidates);
        return buildReportUsingTemplate(generalListDistributedTemplate, "lista_generala_distribuire.pdf", jrBeanCollectionDataSource);
    }

    @Override
    public File buildCandidatesListWithoutExam() {
        List<Candidate> candidates = candidateRepository.findAllByCategory_AdmissionTypeNot(AdmissionType.ADMITERE);
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(candidates);
        return buildReportUsingTemplate(candidatesWithoutExam, "lista_candidatilor_fara_examen.pdf", jrBeanCollectionDataSource);
    }

    @Override
    public File buildCandidatesListFromHall(Integer hallId) {
        List<Candidate> candidates = candidateRepository.findAllByHall_IdOrderByCategoryAscLastNameAscFirstNameAsc(hallId);
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(candidates);
        String fileName = "lista_candidatilor_din_sala_" + candidates.get(0).getHall().getName() + ".pdf";
        return buildReportUsingTemplate(candidatesFromHall, fileName, jrBeanCollectionDataSource);
    }

    @Override
    public File buildGeneralListWithGradesReport() {
        List<AdmissionResult> admissionResults = admissionResultRepository.findAll();
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(new TreeSet(admissionResults));
        return buildReportUsingTemplate(generalListResults, "lista_generala_rezultate.pdf", jrBeanCollectionDataSource);
    }

    @Override
    public File buildListL(ListAllocationType type) throws Exception {
        List<AdmissionResult> admissionResultEntities = admissionResultRepository.findAllByListName(type);
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(new TreeSet(admissionResultEntities));

        switch (type) {
            case L1:
                return buildReportUsingTemplate(listL1, "lista_+" + type + ".pdf", jrBeanCollectionDataSource);
            case L2:
                return buildReportUsingTemplate(listL2, "lista_+" + type + ".pdf", jrBeanCollectionDataSource);
            case L3:
                return buildReportUsingTemplate(listL3, "lista_+" + type + ".pdf", jrBeanCollectionDataSource);
            case L4:
                return buildReportUsingTemplate(listL4, "lista_+" + type + ".pdf", jrBeanCollectionDataSource);
            case L5:
                return buildReportUsingTemplate(listL5, "lista_+" + type + ".pdf", jrBeanCollectionDataSource);
            case L6:
                return buildReportUsingTemplate(listL6, "lista_+" + type + ".pdf", jrBeanCollectionDataSource);
            case L7:
                return buildReportUsingTemplate(listL7, "lista_+" + type + ".pdf", jrBeanCollectionDataSource);
            case L8:
                return buildReportUsingTemplate(listL8, "lista_+" + type + ".pdf", jrBeanCollectionDataSource);
        }
        throw new Exception();
    }


    private File buildReportUsingTemplate(Resource template, String filename, JRBeanCollectionDataSource jrBeanCollectionDataSource) {

        try {
            byte[] bytes;

            InputStream inputStream;

            inputStream = template.getInputStream();

            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("createdBy", "Volocaru Sergiu Adrian");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                    jrBeanCollectionDataSource);

            bytes = JasperExportManager.exportReportToPdf(jasperPrint);

            System.out.println("Done");

            LOGGER.info("Report successfully generated");

            FileOutputStream out = new FileOutputStream(filename);
            out.write(bytes);
            out.close();
            return new File(filename);

        } catch (IOException ex) {

            ex.printStackTrace();
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public File generateReportCandidates(ReportCandidatesDTO reportCandidatesDTO) {
        try {

            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reportCandidatesDTO.getSourceList());
            return buildReport(reportCandidatesDTO.getColumnsReport(), jrBeanCollectionDataSource, CANDIDATES_TITLE, CANDIDATES_PDF);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Report fail!", e);
            return null;
        }

    }

    @Override
    public File generateReportHalls(ReportHallsDTO reportHallsDTO) {
        try {

            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reportHallsDTO.getSourceList());
            return buildReport(reportHallsDTO.getColumnsReport(), jrBeanCollectionDataSource, HALLS_TITLE, HALLS_PDF);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Report fail!", e);
            return null;
        }

    }


    private File buildReport(List<ColumnReport> columnsReport, JRBeanCollectionDataSource jrBeanCollectionDataSource, String title, String pdfName) throws JRException, IOException, ClassNotFoundException {
        DynamicReport dr = createJasperDesign(columnsReport, title);
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("createdBy", "Volocaru Sergiu Adrian");


        JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), jrBeanCollectionDataSource);

        System.out.println("Done");

        LOGGER.info("Report successfully generated");

        FileOutputStream out = new FileOutputStream(pdfName);
        out.write(JasperExportManager.exportReportToPdf(jasperPrint));
        out.close();
        return new File(pdfName);
    }

    private DynamicReport createJasperDesign(List<ColumnReport> columnReports, String title) throws ClassNotFoundException {

        Style styleValue = new Style();
        styleValue.setPattern("##.00");


        FastReportBuilder drb = new FastReportBuilder();
        for (ColumnReport column : columnReports) {
            if (column.isReport()) {
                if (column.getDataType().equals("java.lang.Double")) {
                    drb = drb.addColumn(column.getText(), column.getField(), column.getDataType(), FieldWidth.getPredefinedWidth(column.getField()), styleValue, null);
                } else {
                    drb = drb.addColumn(column.getText(), column.getField(), column.getDataType(), FieldWidth.getPredefinedWidth(column.getField()));
                }
            }
        }

        for (int i = 0; i < drb.getColumns().size(); i++) {
            if (drb.getColumn(i).getStyle() == null) {
                drb.getColumn(i).setStyle(new Style());
            }
            drb.getColumn(i).getStyle().setHorizontalAlign(HorizontalAlign.CENTER);
            drb.getColumn(i).getStyle().setBorder(Border.THIN());
        }
        drb.setUseFullPageWidth(true);
        drb.setSubtitle("Acest raport a fost generat la data de" + new Date());
        DynamicReport dr = drb.build();
        dr.setTitle(title);
        return dr;
    }
}