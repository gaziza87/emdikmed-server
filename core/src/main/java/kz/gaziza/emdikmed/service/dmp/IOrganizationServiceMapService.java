package kz.gaziza.emdikmed.service.dmp;

import kz.gaziza.emdikmed.model.dmp.OrganizationServiceMap;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IOrganizationServiceMapService {

    Page<OrganizationServiceMap> readPageable(Map<String, String> allRequestParams);
    List<OrganizationServiceMap> readIterable();
    List<OrganizationServiceMap> readIterableByOrgId(String orgId);
    List<OrganizationServiceMap> readIterableByServiceId(String serviceId);
    OrganizationServiceMap readOne(String id);
    OrganizationServiceMap readOneByOrgIdAndServiceId(String orgId, String serviceId);
    OrganizationServiceMap create(OrganizationServiceMap organizationServiceMap);
    OrganizationServiceMap update(OrganizationServiceMap organizationServiceMap);
    void delete(String id);

}
