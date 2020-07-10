package kz.gaziza.emdikmed.repository.device;

import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.device.DeviceStatConfig;
import kz.gaziza.emdikmed.repository.ResourceUtilRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DeviceStatConfigRepository extends ResourceUtilRepository<DeviceStatConfig, String> {

    @Query(value = "{id:'?0', state: '?1'}")
    DeviceStatConfig findById(String id, State state);

    @Query(value = "{userAccountId:'?0'}")
    DeviceStatConfig getByUserAccountId(String userId);

}
