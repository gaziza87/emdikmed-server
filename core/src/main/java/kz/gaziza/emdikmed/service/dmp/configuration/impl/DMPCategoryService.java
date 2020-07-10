package kz.gaziza.emdikmed.service.dmp.configuration.impl;

import kz.gaziza.emdikmed.constant.CategoryConstants;
import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.dmp.configuration.DMPCategory;
import kz.gaziza.emdikmed.repository.dmp.configuration.DMPCategoryRepository;
import kz.gaziza.emdikmed.service.dmp.configuration.IDMPCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DMPCategoryService implements IDMPCategoryService {


    @Autowired
    private DMPCategoryRepository dmpCategoryRepository;

    @Override
    public Page<DMPCategory> readPageable(Map<String, String> allRequestParams) {
        Query query = new Query();

        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = CategoryConstants.DEFAUT_PAGE_NUMBER;

        int pageSize = CategoryConstants.DEFAUT_PAGE_SIZE;

        String sortBy = CategoryConstants.ID_FIELD_NAME;

        if (allRequestParams.containsKey("id")) {
            query.addCriteria(Criteria.where(CategoryConstants.ID_FIELD_NAME).is(allRequestParams.get("id")));
        }
        if (allRequestParams.containsKey("code")) {
            query.addCriteria(Criteria.where(CategoryConstants.CODE_FIELD_NAME).is(allRequestParams.get("code")));
        }
        if (allRequestParams.containsKey("name")) {
            query.addCriteria(Criteria.where(CategoryConstants.NAME_FIELD_NAME).is(allRequestParams.get("name")));
        }
        if (allRequestParams.containsKey("description")) {
            query.addCriteria(Criteria.where(CategoryConstants.DESCRIPTION_FIELD_NAME).is(allRequestParams.get("description")));
        }
        if (allRequestParams.containsKey("filter")) {
            query.addCriteria(Criteria.where(CategoryConstants.FILTER_FIELD_NAME).is(allRequestParams.get("filter")));
        }
        if (allRequestParams.containsKey("sortDirection")) {

            if (allRequestParams.get("sortDirection").equals(CategoryConstants.SORT_DIRECTION_DESC))
                sortDirection = Sort.Direction.DESC;

        }
        if (allRequestParams.containsKey("sort")) {
            sortBy = allRequestParams.get("sort");
        }
        if (allRequestParams.containsKey("page")) {
            pageNumber = Integer.parseInt(allRequestParams.get("page"));
        }
        if (allRequestParams.containsKey("size")) {
            pageSize = Integer.parseInt(allRequestParams.get("size"));
        }

        query.addCriteria(Criteria.where(CategoryConstants.STATE_FIELD_NAME).is(State.ACTIVE));

        final Pageable pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

        return dmpCategoryRepository.findAll(query, pageableRequest);
    }

    @Override
    public Page<DMPCategory> searchPageable(Map<String, String> allRequestParams) {

        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = CategoryConstants.DEFAUT_PAGE_NUMBER;

        int pageSize = CategoryConstants.DEFAUT_PAGE_SIZE;

        String sortBy = CategoryConstants.ID_FIELD_NAME;

        if (allRequestParams.containsKey("sortDirection")) {

            if (allRequestParams.get("sortDirection").equals(CategoryConstants.SORT_DIRECTION_DESC))
                sortDirection = Sort.Direction.DESC;

        }
        if (allRequestParams.containsKey("sort")) {
            sortBy = allRequestParams.get("sort");
        }
        if (allRequestParams.containsKey("page")) {
            pageNumber = Integer.parseInt(allRequestParams.get("page"));
        }
        if (allRequestParams.containsKey("size")) {
            pageSize = Integer.parseInt(allRequestParams.get("size"));
        }
        final Pageable pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

        return dmpCategoryRepository.query(allRequestParams.get("searchString"), pageableRequest);
    }

    @Override
    public List<DMPCategory> readIterable() {
        return dmpCategoryRepository.findAll();
    }

    @Override
    public List<DMPCategory> readIterableByFilter(String filter) {
        return dmpCategoryRepository.findAllByFilterAndState(filter, State.ACTIVE);
    }

    @Override
    public DMPCategory readOne(String id) {
        return dmpCategoryRepository.findById(id, State.ACTIVE);
    }

    @Override
    public DMPCategory create(DMPCategory category) {
        category.setState(State.ACTIVE);
        return dmpCategoryRepository.save(category);
    }

    @Override
    public DMPCategory update(DMPCategory category) {
        category.setState(State.ACTIVE);
        return dmpCategoryRepository.save(category);
    }

    @Override
    public void delete(String id) {
        DMPCategory category = dmpCategoryRepository.findById(id, State.ACTIVE);
        category.setState(State.DELETED);
        dmpCategoryRepository.save(category);
    }
}
