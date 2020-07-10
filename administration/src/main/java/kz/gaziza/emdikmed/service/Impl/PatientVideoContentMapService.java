package kz.gaziza.emdikmed.service.Impl;

import kz.gaziza.emdikmed.model.PatientVideoContentMap;
import kz.gaziza.emdikmed.repository.PatientVideoContentMapRepository;
import kz.gaziza.emdikmed.service.IPatientVideoContentMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientVideoContentMapService implements IPatientVideoContentMapService {

    @Autowired
    PatientVideoContentMapRepository patientVideoContentMapRepository;

    @Override
    public PatientVideoContentMap create(PatientVideoContentMap patientVideoContentMap) {
        return patientVideoContentMapRepository.save(patientVideoContentMap);
    }

    @Override
    public List<PatientVideoContentMap> getAllByPatientId(String patientId) {
        return patientVideoContentMapRepository.findAllByPatientId(patientId);
    }

}
