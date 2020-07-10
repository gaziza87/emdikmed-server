package kz.gaziza.emdikmed.service.dmp.configuration.impl;

import kz.gaziza.emdikmed.constant.DiseaseConstants;
import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.dmp.configuration.Disease;
import kz.gaziza.emdikmed.repository.dmp.configuration.DiseaseRepository;
import kz.gaziza.emdikmed.service.dmp.configuration.IDiseaseService;
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
public class DiseaseService implements IDiseaseService {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Override
    public Page<Disease> readPageable(Map<String, String> allRequestParams) {
        Query query = new Query();

        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = DiseaseConstants.DEFAUT_PAGE_NUMBER;

        int pageSize = DiseaseConstants.DEFAUT_PAGE_SIZE;

        String sortBy = DiseaseConstants.ID_FIELD_NAME;

        if (allRequestParams.containsKey("id")) {
            query.addCriteria(Criteria.where(DiseaseConstants.ID_FIELD_NAME).is(allRequestParams.get("id")));
        }
        if (allRequestParams.containsKey("code")) {
            query.addCriteria(Criteria.where(DiseaseConstants.CODE_FIELD_NAME).is(allRequestParams.get("code")));
        }
        if (allRequestParams.containsKey("name")) {
            query.addCriteria(Criteria.where(DiseaseConstants.NAME_FIELD_NAME).is(allRequestParams.get("name")));
        }
        if (allRequestParams.containsKey("description")) {
            query.addCriteria(Criteria.where(DiseaseConstants.DESCRIPTION_FIELD_NAME).is(allRequestParams.get("description")));
        }
        if (allRequestParams.containsKey("categoryId")) {
            query.addCriteria(Criteria.where(DiseaseConstants.CATEGORY_ID_FIELD_NAME).is(allRequestParams.get("categoryId")));
        }
        if (allRequestParams.containsKey("sortDirection")) {

            if (allRequestParams.get("sortDirection").equals(DiseaseConstants.SORT_DIRECTION_DESC))
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

        query.addCriteria(Criteria.where(DiseaseConstants.STATE_FIELD_NAME).is(State.ACTIVE));

        final Pageable pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

        return diseaseRepository.findAll(query, pageableRequest);
    }
    @Override
    public Page<Disease> searchPageable(Map<String, String> allRequestParams) {
        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = DiseaseConstants.DEFAUT_PAGE_NUMBER;

        int pageSize = DiseaseConstants.DEFAUT_PAGE_SIZE;

        String sortBy = DiseaseConstants.ID_FIELD_NAME;

        if (allRequestParams.containsKey("sortDirection")) {

            if (allRequestParams.get("sortDirection").equals(DiseaseConstants.SORT_DIRECTION_DESC))
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

        return diseaseRepository.query(allRequestParams.get("searchString"), pageableRequest);
    }

    @Override
    public List<Disease> readIterable() {
        return diseaseRepository.findAll();
    }

    @Override
    public List<Disease> readIterableByCategoryId(String categoryId) {
        return diseaseRepository.getAllByCategoryId(categoryId, State.ACTIVE);
    }

    @Override
    public List<Disease> readIterableByIdIn(List<String> diseaseIds) {
        return diseaseRepository.findAllByIdIn(diseaseIds);
    }

    @Override
    public Disease readOne(String id) {
        return diseaseRepository.getById(id, State.ACTIVE);
    }

    @Override
    public Disease create(Disease disease) {
        disease.setState(State.ACTIVE);
        return diseaseRepository.save(disease);
    }

    @Override
    public Disease update(Disease disease) {
        disease.setState(State.ACTIVE);
        return diseaseRepository.save(disease);
    }

    @Override
    public void delete(String id) {
        Disease disease = diseaseRepository.getById(id, State.ACTIVE);
        disease.setState(State.DELETED);
        diseaseRepository.save(disease);

    }
}
