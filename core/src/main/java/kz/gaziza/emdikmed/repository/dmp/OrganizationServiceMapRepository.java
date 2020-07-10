package kz.gaziza.emdikmed.repository.dmp;

import kz.gaziza.emdikmed.model.dmp.OrganizationServiceMap;
import kz.gaziza.emdikmed.repository.ResourceUtilRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationServiceMapRepository extends ResourceUtilRepository<OrganizationServiceMap, String> {

    List<OrganizationServiceMap> getAllByOrgId(String orgId);
    List<OrganizationServiceMap> getAllByServiceId(String serviceId);
    OrganizationServiceMap getByOrgIdAndServiceId(String orgId, String serviceId);

}
