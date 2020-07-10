package kz.gaziza.emdikmed.model.dmp;

import kz.gaziza.emdikmed.model.BaseAuditable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dmp_org_service_map")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class OrganizationServiceMap extends BaseAuditable {
    @Id
    private String id;
    private String orgId;
    private String serviceId;
}
