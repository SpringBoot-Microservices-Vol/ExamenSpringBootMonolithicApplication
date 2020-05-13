package com.sergiu.service;

import com.sergiu.dto.AdmissionResultDTO;
import com.sergiu.entity.AdmissionResult;
import com.sergiu.entity.Candidate;
import com.sergiu.entity.CandidateOption;
import com.sergiu.exception.FrameworkException;
import com.sergiu.model.AllocationModel;
import com.sergiu.repository.AdmissionResultRepository;
import com.sergiu.repository.CandidateOptionRepository;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.repository.GradeRepository;
import com.sergiu.util.AdmissionOption;
import com.sergiu.util.AdmissionType;
import com.sergiu.util.ListAllocationType;
import com.sergiu.util.StatusExam;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@Service
public class AllocationServiceImpl implements AllocationService, AllocationRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllocationServiceImpl.class);

    private ModelMapper modelMapper;
    private CandidateRepository candidateRepository;
    private CandidateOptionRepository candidateOptionRepository;
    private AdmissionResultRepository admissionResultRepository;
    private GradeRepository gradeRepository;


    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setCandidateRepository(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Autowired
    public void setCandidateOptionRepository(CandidateOptionRepository candidateOptionRepository) {
        this.candidateOptionRepository = candidateOptionRepository;
    }

    @Autowired
    public void setAdmissionResultRepository(AdmissionResultRepository admissionResultRepository) {
        this.admissionResultRepository = admissionResultRepository;
    }

    @Autowired
    public void setGradeRepository(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public void startAllocateCandidates() {
        if (gradeRepository.count() != candidateRepository.count()) {
            throw new FrameworkException("No toti candidatii au note!");
        }

        AllocationModel allocation = new AllocationModel(RO_BUGET, RO_TAXA, EN_BUGET, EN_TAXA, MD_RO_BUGET, MD_EN_TAXA);
        SortedSet<AdmissionResult> admissionResults = new TreeSet<>(admissionResultRepository.findAllByListNameIsNullOrListNameIsNot(ListAllocationType.L8));

        for (AdmissionResult admissionResult : admissionResults) {
            if (isOlympic(admissionResult)) {
                admissionResult.setFinalGrade(10.0);
                admissionResult.setListName(ListAllocationType.L1);
            } else {

                ListAllocationType listAllocationType = getAllocationListForCandidate(admissionResult, allocation);
                admissionResult.setListName(listAllocationType);
            }
            updateAdmissionResult(admissionResult);
            LOGGER.info("Candidatul:" + admissionResult.getCnp() + "a fost adaugat in lista" + admissionResult.getListName());
        }
    }

    private boolean isOlympic(AdmissionResult admissionResult) {
        return AdmissionType.OLIMPIC.equals(admissionResult.getCandidate().getCategory().getAdmissionType());
    }

    private ListAllocationType getAllocationListForCandidate(AdmissionResult admissionResult, AllocationModel allocation) {
        Long cnp = admissionResult.getCnp();

        List<CandidateOption> candidateOptionEntities = candidateOptionRepository.findAllByCandidateOptionId_CnpOrderByPriority(cnp);
        for (CandidateOption candidateOption : candidateOptionEntities) {

            AdmissionOption option = candidateOption.getCandidateOptionId().getAdmissionOption();
            switch (option) {
                case RO_BUGET: {
                    if (allocation.getRoBuget() > 0) {
                        allocation.decrementRoBuget();
                        return ListAllocationType.L3;
                    }
                    break;
                }
                case EN_BUGET: {
                    if (allocation.getEnBuget() > 0) {
                        allocation.decrementEnBuget();
                        return ListAllocationType.L4;
                    }
                    break;
                }
                case RO_TAXA: {
                    if (allocation.getRoTaxa() > 0) {
                        allocation.decrementRoTaxa();
                        return ListAllocationType.L5;
                    }
                    break;
                }
                case EN_TAXA: {
                    if (allocation.getEnTaxa() > 0) {
                        allocation.decrementEnTaxa();
                        return ListAllocationType.L6;
                    }
                    break;
                }
                case MD_RO_BUGET: {
                    if (allocation.getMdRoBuget() > 0) {
                        allocation.decrementMdRoBuget();
                        return ListAllocationType.L2;
                    }
                    break;
                }
                case MD_EN_BUGET: {
                    if (allocation.getMdEnBuget() > 0) {
                        allocation.decrementMdEnBuget();
                        return ListAllocationType.L2;
                    }
                    break;
                }
            }
        }

        if (admissionResult.getFinalGrade() > 5) {
            return ListAllocationType.L7;
        }

        return ListAllocationType.L8;
    }

    @Override
    public void rejectCandidate(Long cnp) {
        AdmissionResult admissionResult = admissionResultRepository.findById(cnp).get();
        admissionResult.setListName(ListAllocationType.L8);
        updateAdmissionResult(admissionResult);
        startAllocateCandidates();
    }

    @Override
    public void updateAdmissionResult(AdmissionResult admissionResult) {
        admissionResultRepository.save(admissionResult);
        triggerUpdateIntoCandidate(admissionResult);
    }

    private void triggerUpdateIntoCandidate(AdmissionResult admissionResult) {
        Candidate candidate = candidateRepository.findByCnp(admissionResult.getCnp()).get();

        if (admissionResult.getListName() == ListAllocationType.L8) {
            candidate.setStatusExam(StatusExam.RESPINS);
        } else {
            candidate.setStatusExam(StatusExam.ADMIS);
        }
        candidateRepository.save(candidate);
        LOGGER.info("Trigger update for candidate with cnp" + candidate.getCnp() + " with status update to: " + candidate.getStatusExam());
    }

    @Override
    public AdmissionResultDTO getCandidateResult(Long cnp) {
        AdmissionResult admissionResult = admissionResultRepository.findById(cnp).get();
        return modelMapper.map(admissionResult, AdmissionResultDTO.class);
    }

    @Override
    public AllocationModel getAllocationDetails() {
        return new AllocationModel(RO_BUGET, RO_TAXA, EN_BUGET, EN_TAXA, MD_RO_BUGET, MD_EN_TAXA);
    }
}
