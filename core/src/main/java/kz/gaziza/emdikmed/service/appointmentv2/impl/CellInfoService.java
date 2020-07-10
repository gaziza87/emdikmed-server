package kz.gaziza.emdikmed.service.appointmentv2.impl;

import kz.gaziza.emdikmed.model.appointmentv2.CellInfo;
import kz.gaziza.emdikmed.repository.appointmentv2.CellInfoRepository;
import kz.gaziza.emdikmed.service.appointmentv2.ICellInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CellInfoService implements ICellInfoService {

    @Autowired
    private CellInfoRepository cellInfoRepository;

    @Override
    public Page<CellInfo> readPageable(Map<String, String> allRequestParams) {
        return null;
    }

    @Override
    public Page<CellInfo> searchPageable(Map<String, String> allRequestParams) {
        return null;
    }

    @Override
    public List<CellInfo> readIterableByDoctorId(String doctorId) {
        return cellInfoRepository.findAllByDoctorId(doctorId);
    }

    @Override
    public List<CellInfo> readIterableByUserId(String userId) {
        return cellInfoRepository.findAllByUserId(userId);
    }

    @Override
    public List<CellInfo> readIterableByDate(String appointDateString) {
        return cellInfoRepository.findAllByAppointDateString(appointDateString);
    }

    @Override
    public List<CellInfo> readIterableByDoctorIdAndDate(String doctorId, String appointDateString) {
        return cellInfoRepository.findAllByDoctorIdAndAppointDateString(doctorId, appointDateString);
    }

    @Override
    public CellInfo read(String id) {
        return cellInfoRepository.findById(id).get();
    }

    @Override
    public CellInfo create(CellInfo cellInfo) {
        return cellInfoRepository.save(cellInfo);
    }


    @Override
    public CellInfo getById(String id){
        return cellInfoRepository.getById(id);
    }


    @Override
    public CellInfo update(CellInfo cellInfo) {
        return cellInfoRepository.save(cellInfo);
    }

    @Override
    public void delete(String id) {
        cellInfoRepository.deleteById(id);
    }
}
