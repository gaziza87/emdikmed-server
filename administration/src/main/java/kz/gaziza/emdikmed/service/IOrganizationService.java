package kz.gaziza.emdikmed.service;


import kz.gaziza.emdikmed.model.Organization;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IOrganizationService {
    List<Organization> getAll();

    List<Organization> getAllByUserAccountId(String userAccountId);

    List<Organization> getAllByCategory(String categoryCode);

    List<Organization> getSelected(String ticketId);

    List<Organization> getTop(Integer number);

    Page<Organization> getAll(Map<String, String> allRequestParams) throws DataAccessException;

    Page<Organization> search(Map<String, String> allRequestParams);

    List<Organization> readButRoot();

    Organization get(String id);

    Organization readById(String id);

    Organization readByRootOrg(boolean rootOrg);

    Organization readByCode(String code);

    Organization getFirstOrgUnit();

    Integer getOrgCountByCity(String city);

    Organization create(Organization organization);

    Organization update(Organization organization);

    Organization updateNew(Organization organization);

    void delete(Organization organization);
}
