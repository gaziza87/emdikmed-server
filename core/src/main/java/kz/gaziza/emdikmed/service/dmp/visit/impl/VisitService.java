package kz.gaziza.emdikmed.service.dmp.visit.impl;

import kz.gaziza.emdikmed.model.dmp.visit.Visit;
import kz.gaziza.emdikmed.repository.dmp.visit.VisitRepository;
import kz.gaziza.emdikmed.service.dmp.visit.IVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VisitService implements IVisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Override
    public Page<Visit> readPageable(Map<String, String> allRequestParams) {
        return null;
    }

    @Override
    public Page<Visit> searchPageable(Map<String, String> allRequestParams) {
        return null;
    }

    @Override
    public List<Visit> readIterable() {
        return visitRepository.findAll();
    }

    @Override
    public List<Visit> readIterableByDoctorId(String doctorId) {
        return visitRepository.findAllByDoctorId(doctorId);
    }

    @Override
    public List<Visit> readIterableByPatientId(String patientId) {
        return visitRepository.findAllByPatientId(patientId);
    }

    /*@Override
    public List<Visit> readIterableByPatientId(String patientId) {
        return visitRepository.get();
    }*/

    @Override
    public Visit readLastVisit(String patientId) {
        return null;
    }

    @Override
    public Visit readOne(String id) {
        return visitRepository.findById(id).get();
    }

    @Override
    public Visit create(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public Visit update(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public void sendToPatientEmail(List<String> diseaseIds, String patientId, String doctorId) {

    }

    @Override
    public void sendfileToPatientEmail(String email, String userId, List<String> templateCodes, Visit visit) {

    }


}
