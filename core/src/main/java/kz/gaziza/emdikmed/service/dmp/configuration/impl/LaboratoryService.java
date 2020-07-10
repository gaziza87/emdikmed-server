package kz.gaziza.emdikmed.service.dmp.configuration.impl;

import kz.gaziza.emdikmed.constant.LaboratoryConstants;
import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.dmp.configuration.DMPCategory;
import kz.gaziza.emdikmed.model.dmp.configuration.Laboratory;
import kz.gaziza.emdikmed.repository.dmp.configuration.DMPCategoryRepository;
import kz.gaziza.emdikmed.repository.dmp.configuration.LaboratoryRepository;
import kz.gaziza.emdikmed.service.dmp.configuration.ILaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LaboratoryService implements ILaboratoryService {

    @Autowired
    private LaboratoryRepository laboratoryRepository;
    @Autowired
    private DMPCategoryRepository dmpCategoryRepository;

    @Override
    public Page<Laboratory> readPageable(Map<String, String> allRequestParams) {
        Query query = new Query();

        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = LaboratoryConstants.DEFAUT_PAGE_NUMBER;

        int pageSize = LaboratoryConstants.DEFAUT_PAGE_SIZE;

        String sortBy = LaboratoryConstants.ID_FIELD_NAME;

        if (allRequestParams.containsKey("id")) {
            query.addCriteria(Criteria.where(LaboratoryConstants.ID_FIELD_NAME).is(allRequestParams.get("id")));
        }
        if (allRequestParams.containsKey("code")) {
            query.addCriteria(Criteria.where(LaboratoryConstants.CODE_FIELD_NAME).is(allRequestParams.get("code")));
        }
        if (allRequestParams.containsKey("name")) {
            query.addCriteria(Criteria.where(LaboratoryConstants.NAME_FIELD_NAME).is(allRequestParams.get("name")));
        }
        if (allRequestParams.containsKey("description")) {
            query.addCriteria(Criteria.where(LaboratoryConstants.DESCRIPTION_FIELD_NAME).is(allRequestParams.get("description")));
        }
        if (allRequestParams.containsKey("categoryId")) {
            query.addCriteria(Criteria.where(LaboratoryConstants.CATEGORY_ID_FIELD_NAME).is(allRequestParams.get("categoryId")));
        }
        if (allRequestParams.containsKey("sortDirection")) {

            if (allRequestParams.get("sortDirection").equals(LaboratoryConstants.SORT_DIRECTION_DESC))
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

        query.addCriteria(Criteria.where(LaboratoryConstants.STATE_FIELD_NAME).is(LaboratoryConstants.STATUS_ACTIVE));

        final Pageable pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

        return laboratoryRepository.findAll(query, pageableRequest);
    }

    @Override
    public Page<Laboratory> searchPageable(Map<String, String> allRequestParams) {
        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = LaboratoryConstants.DEFAUT_PAGE_NUMBER;

        int pageSize = LaboratoryConstants.DEFAUT_PAGE_SIZE;

        String sortBy = LaboratoryConstants.ID_FIELD_NAME;

        if (allRequestParams.containsKey("sortDirection")) {

            if (allRequestParams.get("sortDirection").equals(LaboratoryConstants.SORT_DIRECTION_DESC))
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

        return laboratoryRepository.query(allRequestParams.get("searchString"), pageableRequest);
    }

    @Override
    public List<Laboratory> readIterable() {
        return laboratoryRepository.findAll();
    }

    @Override
    public List<Object> readCategorizedLaboratoriesByIdIn(List<String> ids) {
        List<Object> data = new ArrayList<>();

        List<Laboratory> laboratories = laboratoryRepository.findAllByIdInAndState(ids, State.ACTIVE);
        List<DMPCategory> laboratoryCategories = dmpCategoryRepository.findAllByFilterAndState("laboratory", State.ACTIVE);

        laboratoryCategories.forEach(category -> {
            List<Laboratory> filteredLabs = laboratories.stream().filter(laboratory -> laboratory.getCategoryId().equals(category.getId())).collect(Collectors.toList());

            LinkedHashMap<String, Object> content = new LinkedHashMap<>();
            content.put("category", category);
            content.put("laboratories", filteredLabs);

            data.add(content);
        });

        return data;
    }

    @Override
    public List<Object> readCategorizedLaboratories() {
        List<Object> data = new ArrayList<>();

        List<Laboratory> laboratories = readIterable();
        List<DMPCategory> laboratoryCategories = dmpCategoryRepository.findAllByFilterAndState("laboratory", State.ACTIVE);

        laboratoryCategories.forEach(dmpCategory -> {
            List<Laboratory> filteredLabs = laboratories.stream().filter(laboratory -> laboratory.getCategoryId().equals(dmpCategory.getId())).collect(Collectors.toList());

            LinkedHashMap<String, Object> content = new LinkedHashMap<>();
            content.put("category", dmpCategory);
            content.put("laboratories", filteredLabs);

            data.add(content);
        });

        return data;
    }

    @Override
    public List<Laboratory> readIterableByIdIn(List<String> ids) {
        return laboratoryRepository.findAllByIdInAndState(ids, State.ACTIVE);
    }

    @Override
    public List<Laboratory> readIterableByCategoryId(String categoryId) {
        return laboratoryRepository.getAllByCategoryId(categoryId, State.ACTIVE);
    }

    @Override
    public Laboratory readOne(String id) {
        return laboratoryRepository.getById(id, State.ACTIVE);
    }

    @Override
    public Laboratory create(Laboratory laboratory) {
        laboratory.setState(State.ACTIVE);
        return laboratoryRepository.save(laboratory);
    }

    @Override
    public Laboratory update(Laboratory laboratory) {
        laboratory.setState(State.ACTIVE);
        return laboratoryRepository.save(laboratory);
    }

    @Override
    public void delete(String id) {
        Laboratory laboratory = laboratoryRepository.getById(id, State.ACTIVE);
        laboratory.setState(State.DELETED);
        laboratoryRepository.save(laboratory);
    }
}
