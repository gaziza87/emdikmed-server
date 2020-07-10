package kz.gaziza.emdikmed.service.device;


import kz.gaziza.emdikmed.model.device.DeviceStatConfig;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IDeviceStatConfigService {

    Page<DeviceStatConfig> readPageable(Map<String, String> allRequestParams);
    List<DeviceStatConfig> readIterable();
    List<DeviceStatConfig> readAll();
    DeviceStatConfig readByUserAccountId(String userAccountId);
    DeviceStatConfig read(String id);
    DeviceStatConfig create(DeviceStatConfig deviceStatConfig);
    DeviceStatConfig update(DeviceStatConfig deviceStatConfig);
    void delete(String id);
    
}
