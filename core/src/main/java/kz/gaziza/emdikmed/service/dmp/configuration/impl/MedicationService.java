package kz.gaziza.emdikmed.service.dmp.configuration.impl;

import kz.gaziza.emdikmed.constant.LaboratoryConstants;
import kz.gaziza.emdikmed.constant.MedicationConstants;
import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.dmp.configuration.Medication;
import kz.gaziza.emdikmed.repository.dmp.configuration.MedicationRepository;
import kz.gaziza.emdikmed.service.dmp.configuration.IMedicationService;
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
public class MedicationService implements IMedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public Page<Medication> readPageable(Map<String, String> allRequestParams) {
        Query query = new Query();

        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = MedicationConstants.DEFAUT_PAGE_NUMBER;

        int pageSize = MedicationConstants.DEFAUT_PAGE_SIZE;

        String sortBy = MedicationConstants.ID_FIELD_NAME;

        if (allRequestParams.containsKey("id")) {
            query.addCriteria(Criteria.where(MedicationConstants.ID_FIELD_NAME).is(allRequestParams.get("id")));
        }
        if (allRequestParams.containsKey("code")) {
            query.addCriteria(Criteria.where(MedicationConstants.CODE_FIELD_NAME).is(allRequestParams.get("code")));
        }
        if (allRequestParams.containsKey("name")) {
            query.addCriteria(Criteria.where(MedicationConstants.NAME_FIELD_NAME).is(allRequestParams.get("name")));
        }
        if (allRequestParams.containsKey("description")) {
            query.addCriteria(Criteria.where(MedicationConstants.DESCRIPTION_FIELD_NAME).is(allRequestParams.get("description")));
        }
        if (allRequestParams.containsKey("categoryId")) {
            query.addCriteria(Criteria.where(MedicationConstants.CATEGORY_ID_FIELD_NAME).is(allRequestParams.get("categoryId")));
        }
        if (allRequestParams.containsKey("sortDirection")) {

            if (allRequestParams.get("sortDirection").equals(MedicationConstants.SORT_DIRECTION_DESC))
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

        return medicationRepository.findAll(query, pageableRequest);
    }

    @Override
    public Page<Medication> searchPageable(Map<String, String> allRequestParams) {
        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = MedicationConstants.DEFAUT_PAGE_NUMBER;

        int pageSize = MedicationConstants.DEFAUT_PAGE_SIZE;

        String sortBy = MedicationConstants.ID_FIELD_NAME;

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

        return medicationRepository.query(allRequestParams.get("searchString"), pageableRequest);
    }

    @Override
    public List<Medication> readIterable() {
        return medicationRepository.findAll();
    }

    @Override
    public List<Medication> readIterableByCategoryId(String categoryId) {
        return medicationRepository.getAllByCategoryId(categoryId, State.ACTIVE);
    }

   /* @Override
    public List<Medication> readIterableByIdIn(List<String> ids) {
        return null;
    }*/

    @Override
    public Medication readOne(String id) {
        return medicationRepository.getById(id, State.ACTIVE);
    }

    @Override
    public Medication create(Medication medication) {
        medication.setState(State.ACTIVE);
        return medicationRepository.save(medication);
    }

    @Override
    public Medication update(Medication medication) {
        medication.setState(State.ACTIVE);
        return medicationRepository.save(medication);
    }

    @Override
    public void delete(String id) {
        Medication medication = medicationRepository.getById(id, State.ACTIVE);
        medication.setState(State.DELETED);
        medicationRepository.save(medication);
    }
}
