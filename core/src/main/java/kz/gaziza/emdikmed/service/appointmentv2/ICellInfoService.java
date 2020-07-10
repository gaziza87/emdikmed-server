package kz.gaziza.emdikmed.service.appointmentv2;

import kz.gaziza.emdikmed.model.appointmentv2.CellInfo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ICellInfoService {

    Page<CellInfo> readPageable(Map<String, String> allRequestParams);
    Page<CellInfo> searchPageable(Map<String, String> allRequestParams);
    List<CellInfo> readIterableByDoctorId(String doctorId);
    List<CellInfo> readIterableByUserId(String userId);
    List<CellInfo> readIterableByDate(String appointDateString);
    List<CellInfo> readIterableByDoctorIdAndDate(String doctorId, String appointDateString);
    CellInfo read(String id);
    CellInfo create(CellInfo cellInfo);
    CellInfo getById(String id);
    CellInfo update(CellInfo cellInfo);
    void delete(String id);

}

