package kz.gaziza.emdikmed.service.dmp.visit;

import kz.gaziza.emdikmed.model.dmp.visit.Visit;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IVisitService {
    Page<Visit> readPageable(Map<String, String> allRequestParams);
    Page<Visit> searchPageable(Map<String, String> allRequestParams);
    List<Visit> readIterable();
    List<Visit> readIterableByDoctorId(String doctorId);
    List<Visit> readIterableByPatientId(String patientId);
    /*List<Visit> readIterableByPatientId(String patientId);*/
    Visit readLastVisit(String patientId);
    Visit readOne(String id);
    Visit create(Visit visit);
    Visit update(Visit visit);
    void sendToPatientEmail(List<String> diseaseIds, String patientId, String doctorId);
    void sendfileToPatientEmail(String email, String userId, List<String> templateCodes, Visit visit);

}
