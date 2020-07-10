package kz.gaziza.emdikmed.service.dmp.configuration.impl;

import kz.gaziza.emdikmed.constant.CategoryConstants;
import kz.gaziza.emdikmed.constant.DiagnosticConstants;
import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.dmp.configuration.DMPCategory;
import kz.gaziza.emdikmed.model.dmp.configuration.Diagnostic;
import kz.gaziza.emdikmed.repository.dmp.configuration.DMPCategoryRepository;
import kz.gaziza.emdikmed.repository.dmp.configuration.DiagnosticRepository;
import kz.gaziza.emdikmed.service.dmp.configuration.IDiagnosticService;
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
public class DiagnosticService implements IDiagnosticService {

    @Autowired
    private DiagnosticRepository diagnosticRepository;
    @Autowired
    private DMPCategoryRepository dmpCategoryRepository;

    @Override
    public Page<Diagnostic> readPageable(Map<String, String> allRequestParams) {

        Query query = new Query();

        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = DiagnosticConstants.DEFAUT_PAGE_NUMBER;

        int pageSize = DiagnosticConstants.DEFAUT_PAGE_SIZE;

        String sortBy = DiagnosticConstants.ID_FIELD_NAME;

        if (allRequestParams.containsKey("id")) {
            query.addCriteria(Criteria.where(DiagnosticConstants.ID_FIELD_NAME).is(allRequestParams.get("id")));
        }
        if (allRequestParams.containsKey("code")) {
            query.addCriteria(Criteria.where(DiagnosticConstants.CODE_FIELD_NAME).is(allRequestParams.get("code")));
        }
        if (allRequestParams.containsKey("name")) {
            query.addCriteria(Criteria.where(DiagnosticConstants.NAME_FIELD_NAME).is(allRequestParams.get("name")));
        }
        if (allRequestParams.containsKey("description")) {
            query.addCriteria(Criteria.where(DiagnosticConstants.DESCRIPTION_FIELD_NAME).is(allRequestParams.get("description")));
        }
        if (allRequestParams.containsKey("categoryId")) {
            query.addCriteria(Criteria.where(DiagnosticConstants.CATEGORY_ID_FIELD_NAME).is(allRequestParams.get("categoryId")));
        }
        if (allRequestParams.containsKey("sortDirection")) {

            if (allRequestParams.get("sortDirection").equals(DiagnosticConstants.SORT_DIRECTION_DESC))
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

        return diagnosticRepository.findAll(query, pageableRequest);
    }

    @Override
    public Page<Diagnostic> searchPageable(Map<String, String> allRequestParams) {
        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = DiagnosticConstants.DEFAUT_PAGE_NUMBER;

        int pageSize = DiagnosticConstants.DEFAUT_PAGE_SIZE;

        String sortBy = DiagnosticConstants.ID_FIELD_NAME;

        if (allRequestParams.containsKey("sortDirection")) {

            if (allRequestParams.get("sortDirection").equals(DiagnosticConstants.SORT_DIRECTION_DESC))
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

        return diagnosticRepository.query(allRequestParams.get("searchString"), pageableRequest);
    }

    @Override
    public List<Diagnostic> readIterable() {
        return diagnosticRepository.findAll();
    }

   /* @Override
    public List<Diagnostic> readIterableByIdIn(List<String> ids) {
        return diagnosticRepository.findAllByIdInAndState(ids, State.ACTIVE );
    }*/

    @Override
    public List<Diagnostic> readIterableByCategoryId(String categoryId) {
        return diagnosticRepository.getAllByCategoryId(categoryId, State.ACTIVE);
    }

    @Override
    public List<Object> readCategorizedDiagnostics() {
        List<Object> data = new ArrayList<>();

        List<Diagnostic> diagnostics = readIterable();
        List<DMPCategory> diagnosticsCategories = dmpCategoryRepository.findAllByFilterAndState("diagnostics", State.ACTIVE);
        diagnosticsCategories.forEach(dmpCategory -> {
            List<Diagnostic> filteredDiagnostics = diagnostics.stream().filter(diagnostic -> diagnostic != null && dmpCategory != null && diagnostic.getCategoryId() != null && diagnostic.getCategoryId().equals(dmpCategory.getId())).collect(Collectors.toList());

            LinkedHashMap<String, Object> content = new LinkedHashMap<>();
            content.put("category", dmpCategory);
            content.put("diagnostics", filteredDiagnostics);

            data.add(content);
        });

        return data;
    }

    @Override
    public Diagnostic readOne(String id) {
        return diagnosticRepository.getById(id, State.ACTIVE);
    }

    @Override
    public Diagnostic create(Diagnostic diagnostic) {
        diagnostic.setState(State.ACTIVE);
        return diagnosticRepository.save(diagnostic);
    }

    @Override
    public Diagnostic update(Diagnostic diagnostic) {
        diagnostic.setState(State.ACTIVE);
        return diagnosticRepository.save(diagnostic);
    }


    @Override
    public void delete(String id) {
        Diagnostic diagnostic = diagnosticRepository.getById(id, State.ACTIVE);
        diagnostic.setState(State.DELETED);
        diagnosticRepository.save(diagnostic);

    }
}
