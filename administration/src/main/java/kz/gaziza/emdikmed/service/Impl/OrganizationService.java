package kz.gaziza.emdikmed.service.Impl;

import com.google.gson.Gson;
import kz.gaziza.emdikmed.model.Organization;
import kz.gaziza.emdikmed.model.UserAccount;
import kz.gaziza.emdikmed.model.UserRoleOrgMap;
import kz.gaziza.emdikmed.repository.IUserAccountRepository;
import kz.gaziza.emdikmed.repository.OrganizationRepository;
import kz.gaziza.emdikmed.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrganizationService implements IOrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    IUserAccountRepository userAccountRepository;

    @Override
    public List<Organization> getAll() {
        return organizationRepository.findAll();
    }

    @Override
    public List<Organization> getAllByUserAccountId(String userAccountId) {
        UserAccount userAccount = userAccountRepository.getById(userAccountId);
        System.out.println(new Gson().toJsonTree(userAccount));
        List<String> orgIds = userAccount.getUserRoleOrgMapList().stream().map(UserRoleOrgMap::getOrgId).collect(Collectors.toList());
        return organizationRepository.findAllByIdIn(orgIds);
    }

    @Override
    public List<Organization> getAllByCategory(String categoryCode) {
        return null;
    }

    @Override
    public List<Organization> getSelected(String ticketId) {
        return null;
    }

    @Override
    public List<Organization> getTop(Integer number) {
        return null;
    }

    @Override
    public Page<Organization> getAll(Map<String, String> allRequestParams) throws DataAccessException {
        return null;
    }

    @Override
    public Page<Organization> search(Map<String, String> allRequestParams) {
        int pageNumber = 0;

        int pageSize = 15;

        String searchString = "";

        if (allRequestParams.containsKey("searchString")) {
            searchString = allRequestParams.get("searchString");
        }
        if (allRequestParams.containsKey("page")) {
            pageNumber = Integer.parseInt(allRequestParams.get("page"));
        }
        if (allRequestParams.containsKey("size")) {
            pageSize = Integer.parseInt(allRequestParams.get("size"));
        }

        Pageable pageableRequest = PageRequest.of(pageNumber, pageSize);

        Page<Organization> organizationPage = organizationRepository.query(searchString, pageableRequest);

        return new PageImpl<>(organizationPage.getContent(), organizationPage.getPageable(), organizationPage.getTotalElements());
    }

    @Override
    public List<Organization> readButRoot() {
        return null;
    }

    @Override
    public Organization get(String id) {
        return organizationRepository.getById(id);
    }

    @Override
    public Organization readById(String id) {
        return organizationRepository.getById(id);
    }

    @Override
    public Organization readByRootOrg(boolean rootOrg) {
        return organizationRepository.findByIsRootOrg(rootOrg);
    }

    @Override
    public Organization readByCode(String code) {
        return organizationRepository.getByCode(code);
    }

    @Override
    public Organization getFirstOrgUnit() {
        return null;
    }

    @Override
    public Integer getOrgCountByCity(String city) {
        return null;
    }

    @Override
    public Organization create(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public Organization update(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public Organization updateNew(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public void delete(Organization organization) {
        organizationRepository.delete(organization);
    }
}
