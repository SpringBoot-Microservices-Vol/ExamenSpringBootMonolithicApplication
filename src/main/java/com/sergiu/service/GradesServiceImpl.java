package com.sergiu.service;

import com.sergiu.dto.GradesDTO;
import com.sergiu.entity.AdmissionResult;
import com.sergiu.entity.Candidate;
import com.sergiu.entity.Grades;
import com.sergiu.repository.AdmissionResultRepository;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.repository.GradeRepository;
import com.sergiu.transformer.GradesTransformer;
import com.sergiu.util.GradeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradesServiceImpl implements GradesService {

    private AdmissionResultRepository admissionResultRepository;
    private CandidateRepository candidateRepository;
    private GradeRepository gradeRepository;
    private GradesTransformer transformer;

    @Autowired
    public void setAdmissionResultRepository(AdmissionResultRepository admissionResultRepository) {
        this.admissionResultRepository = admissionResultRepository;
    }

    @Autowired
    public void setGradeRepository(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Autowired
    public void setTransformer(GradesTransformer transformer) {
        this.transformer = transformer;
    }

    @Autowired
    public void setCandidateRepository(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public List<GradesDTO> getAllGrades() {
        return transformer.toDTO(gradeRepository.findAll());
    }

    @Override
    public void add(GradesDTO gradesDTO) {
        Grades grades = gradeRepository.saveAndFlush(transformer.toEntity(gradesDTO));
        triggerInsertIntoAdmissionResult(grades);
    }

    private void triggerInsertIntoAdmissionResult(Grades grades) {
        Candidate candidate = candidateRepository.findByCnp(grades.getCnp()).get();
        AdmissionResult admissionResult = new AdmissionResult();
        admissionResult.setCnp(candidate.getCnp());
        admissionResult.setTestGrade(getAverageWriteTest(grades));
        admissionResult.setFinalGrade(getAverageFinal(candidate));
        admissionResultRepository.save(admissionResult);
    }

    @Override
    public GradesDTO updateGrades(Long cnp, GradesDTO gradesDTO) {
        Grades grades = gradeRepository.saveAndFlush(transformer.toEntity(gradesDTO));
        triggerUpdateIntoAdmissionResult(grades);
        return transformer.toDTO(grades);
    }

    private void triggerUpdateIntoAdmissionResult(Grades grades) {
        Candidate candidate = candidateRepository.findByCnp(grades.getCnp()).get();
        AdmissionResult admissionResult = admissionResultRepository.findById(grades.getCnp()).get();
        admissionResult.setTestGrade(getAverageWriteTest(grades));
        admissionResult.setFinalGrade(getAverageFinal(candidate));
        admissionResultRepository.save(admissionResult);
    }

    @Override
    public void deleteGrades(Long cnp) {
        gradeRepository.deleteById(cnp);
        triggerDeleteIntoAdmissionResult(cnp);
    }

    private void triggerDeleteIntoAdmissionResult(Long cnp) {
        admissionResultRepository.deleteById(cnp);
    }

    private Double getAverageWriteTest(Grades grades) {
        return GradeUtils.calculateAverageWriteTest(grades.getFirstGrade(), grades.getSecondGrade());
    }

    private Double getAverageFinal(Candidate candidate) {
        Double writeTest = getAverageWriteTest(candidate.getGrades());
        Double bacGrade = candidate.getBacGrade();
        Double bacBestGrade = candidate.getBacBestGrade();
        return GradeUtils.calculateFinalResult(writeTest, bacGrade, bacBestGrade);
    }
}
