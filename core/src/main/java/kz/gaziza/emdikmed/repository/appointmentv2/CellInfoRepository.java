package kz.gaziza.emdikmed.repository.appointmentv2;

import kz.gaziza.emdikmed.model.appointmentv2.CellInfo;
import kz.gaziza.emdikmed.repository.ResourceUtilRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CellInfoRepository extends ResourceUtilRepository<CellInfo, String> {

    CellInfo getById(String id);
    List<CellInfo> findAllByDoctorId(String doctorId);
    List<CellInfo> findAllByUserId(String userId);
    List<CellInfo> findAllByAppointDateString(String appointDateString);
    List<CellInfo> findAllByDoctorIdAndAppointDateString(String doctorId, String appointDateString);

}
