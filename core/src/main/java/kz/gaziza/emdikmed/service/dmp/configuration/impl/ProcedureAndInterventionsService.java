package kz.gaziza.emdikmed.service.dmp.configuration.impl;

import kz.gaziza.emdikmed.constant.PAIConstants;
import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.dmp.configuration.DMPCategory;
import kz.gaziza.emdikmed.model.dmp.configuration.ProceduresAndInterventions;
import kz.gaziza.emdikmed.repository.dmp.configuration.DMPCategoryRepository;
import kz.gaziza.emdikmed.repository.dmp.configuration.ProceduresAndInterventionsRepository;
import kz.gaziza.emdikmed.service.dmp.configuration.IProceduresAndInterventionsService;
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
public class ProcedureAndInterventionsService implements IProceduresAndInterventionsService {

    @Autowired
    private ProceduresAndInterventionsRepository proceduresAndInterventionsRepository;
    @Autowired
    private DMPCategoryRepository dmpCategoryRepository;

    @Override
    public Page<ProceduresAndInterventions> readPageable(Map<String, String> allRequestParams) {
        Query query = new Query();

        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = PAIConstants.DEFAUT_PAGE_NUMBER;

        int pageSize = PAIConstants.DEFAUT_PAGE_SIZE;

        String sortBy = PAIConstants.ID_FIELD_NAME;

        if (allRequestParams.containsKey("id")) {
            query.addCriteria(Criteria.where(PAIConstants.ID_FIELD_NAME).is(allRequestParams.get("id")));
        }
        if (allRequestParams.containsKey("code")) {
            query.addCriteria(Criteria.where(PAIConstants.CODE_FIELD_NAME).is(allRequestParams.get("code")));
        }
        if (allRequestParams.containsKey("name")) {
            query.addCriteria(Criteria.where(PAIConstants.NAME_FIELD_NAME).is(allRequestParams.get("name")));
        }
        if (allRequestParams.containsKey("description")) {
            query.addCriteria(Criteria.where(PAIConstants.DESCRIPTION_FIELD_NAME).is(allRequestParams.get("description")));
        }
        if (allRequestParams.containsKey("categoryId")) {
            query.addCriteria(Criteria.where(PAIConstants.CATEGORY_ID_FIELD_NAME).is(allRequestParams.get("categoryId")));
        }
        if (allRequestParams.containsKey("sortDirection")) {

            if (allRequestParams.get("sortDirection").equals(PAIConstants.SORT_DIRECTION_DESC))
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

        query.addCriteria(Criteria.where(PAIConstants.STATE_FIELD_NAME).is(PAIConstants.STATUS_ACTIVE));

        final Pageable pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

        return proceduresAndInterventionsRepository.findAll(query, pageableRequest);
    }

    @Override
    public Page<ProceduresAndInterventions> searchPageable(Map<String, String> allRequestParams) {
        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = PAIConstants.DEFAUT_PAGE_NUMBER;

        int pageSize = PAIConstants.DEFAUT_PAGE_SIZE;

        String sortBy = PAIConstants.ID_FIELD_NAME;

        if (allRequestParams.containsKey("sortDirection")) {

            if (allRequestParams.get("sortDirection").equals(PAIConstants.SORT_DIRECTION_DESC))
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

        return proceduresAndInterventionsRepository.query(allRequestParams.get("searchString"), pageableRequest);
    }

    @Override
    public List<ProceduresAndInterventions> readIterable() {
        return proceduresAndInterventionsRepository.findAll();
    }

   /* @Override
    public List<ProceduresAndInterventions> readIterableByIdIn(List<String> ids) {
        return proceduresAndInterventionsRepository.
    }
*/
    @Override
    public List<ProceduresAndInterventions> readIterableByCategoryId(String categoryId) {
        return proceduresAndInterventionsRepository.getAllByCategoryId(categoryId, State.ACTIVE);
    }

    @Override
    public List<Object> readCategorizedList() {
        List<Object> data = new ArrayList<>();

        List<ProceduresAndInterventions> proceduresAndInterventions = readIterable();
        List<DMPCategory> paiCategories = dmpCategoryRepository.findAllByFilterAndState("proceduresAndInterventions", State.ACTIVE);

        paiCategories.forEach(dmpCategory -> {
            List<ProceduresAndInterventions> filteredPais = proceduresAndInterventions.stream().filter(p -> p.getCategoryId().equals(dmpCategory.getId())).collect(Collectors.toList());

            LinkedHashMap<String, Object> content = new LinkedHashMap<>();
            content.put("category", dmpCategory);
            content.put("proceduresAndInterventions", filteredPais);

            data.add(content);
        });

        return data;
    }

    @Override
    public ProceduresAndInterventions readOne(String id) {
        return proceduresAndInterventionsRepository.getById(id, State.ACTIVE);
    }

    @Override
    public ProceduresAndInterventions create(ProceduresAndInterventions proceduresAndInterventions) {
        proceduresAndInterventions.setState(State.ACTIVE);
        return proceduresAndInterventionsRepository.save(proceduresAndInterventions);
    }

    @Override
    public ProceduresAndInterventions update(ProceduresAndInterventions proceduresAndInterventions) {
        proceduresAndInterventions.setState(State.ACTIVE);
        return proceduresAndInterventionsRepository.save(proceduresAndInterventions);
    }

    @Override
    public void delete(String id) {
        ProceduresAndInterventions proceduresAndInterventions = proceduresAndInterventionsRepository.getById(id, State.ACTIVE);
        proceduresAndInterventions.setState(State.DELETED);
        proceduresAndInterventionsRepository.save(proceduresAndInterventions);


    }
}
