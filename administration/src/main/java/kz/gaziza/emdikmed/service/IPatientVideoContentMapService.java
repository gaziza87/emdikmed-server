package kz.gaziza.emdikmed.service;

import kz.gaziza.emdikmed.model.PatientVideoContentMap;

import java.util.List;

public interface IPatientVideoContentMapService {
    PatientVideoContentMap create(PatientVideoContentMap patientVideoContentMap);
    List<PatientVideoContentMap> getAllByPatientId(String patientId);
}
