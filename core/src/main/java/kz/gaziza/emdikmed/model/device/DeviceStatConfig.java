package kz.gaziza.emdikmed.model.device;

import kz.gaziza.emdikmed.model.BaseAuditable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document(collection = "device_stat_config")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class DeviceStatConfig extends BaseAuditable {

    @Id
    private String id;
    private HashMap<String, DeviceStatConfigObjValue> parameterMap;
    private String userAccountId;

}
