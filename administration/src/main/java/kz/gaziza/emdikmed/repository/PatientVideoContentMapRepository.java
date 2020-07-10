package kz.gaziza.emdikmed.repository;

import kz.gaziza.emdikmed.model.PatientVideoContentMap;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientVideoContentMapRepository extends ResourceUtilRepository<PatientVideoContentMap, String>{

    List<PatientVideoContentMap> findAllByPatientId(String patientId);

}
