package kz.gaziza.emdikmed.service.device;

import kz.gaziza.emdikmed.model.device.DeviceStat;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IDeviceStatService {

    Page<DeviceStat> readPageable(Map<String, String> allRequestParams);
    Page<DeviceStat> search(Map<String, String> allRequestParams);
    List<DeviceStat> readIterableByUserAccountId(String userAccountId);
    List<DeviceStat> readAll();
    DeviceStat getLastRecordByUserAccountId(String userAccountId);
    DeviceStat get(String id);
    DeviceStat create(DeviceStat deviceStat);
    DeviceStat update(DeviceStat deviceStat);
    void delete(String id);
    int defineHealthStatus(DeviceStat deviceStat);
    
}
