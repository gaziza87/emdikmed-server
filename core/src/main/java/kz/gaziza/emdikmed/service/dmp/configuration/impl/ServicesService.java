package kz.gaziza.emdikmed.service.dmp.configuration.impl;


import kz.gaziza.emdikmed.constant.ProtocolConstants;
import kz.gaziza.emdikmed.constant.ServicesConstants;
import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.dmp.configuration.Service;
import kz.gaziza.emdikmed.repository.dmp.configuration.ServiceRepository;
import kz.gaziza.emdikmed.service.dmp.configuration.IServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class ServicesService implements IServicesService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Page<Service> readPageable(Map<String, String> allRequestParams) {
        Query query = new Query();

        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = ServicesConstants.DEFAUT_PAGE_NUMBER;

        int pageSize = ServicesConstants.DEFAUT_PAGE_SIZE;

        String sortBy = ServicesConstants.ID_FIELD_NAME;

        if (allRequestParams.containsKey("id")) {
            query.addCriteria(Criteria.where(ServicesConstants.ID_FIELD_NAME).is(allRequestParams.get("id")));
        }
        if (allRequestParams.containsKey("code")) {
            query.addCriteria(Criteria.where(ServicesConstants.CODE_FIELD_NAME).is(allRequestParams.get("code")));
        }
        if (allRequestParams.containsKey("name")) {
            query.addCriteria(Criteria.where(ServicesConstants.NAME_FIELD_NAME).is(allRequestParams.get("name")));
        }
        if (allRequestParams.containsKey("description")) {
            query.addCriteria(Criteria.where(ServicesConstants.DESCRIPTION_FIELD_NAME).is(allRequestParams.get("description")));
        }
        if (allRequestParams.containsKey("sortDirection")) {

            if (allRequestParams.get("sortDirection").equals(ServicesConstants.SORT_DIRECTION_DESC))
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

        query.addCriteria(Criteria.where(ProtocolConstants.STATE_FIELD_NAME).is(ServicesConstants.STATUS_ACTIVE));

        final Pageable pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

        return serviceRepository.findAll(query, pageableRequest);
    }

    @Override
    public Page<Service> searchPageable(Map<String, String> allRequestParams) {
        return null;
    }

    @Override
    public List<Service> readIterable() {
        return serviceRepository.findAll();
    }

    /*@Override
    public List<Service> readIterableByIdIn(List<String> ids) {
        return null;
    }
*/
    @Override
    public List<Service> readIterableByCategoryId(String categoryId) {
        return serviceRepository.getAllByCategoryId(categoryId, State.ACTIVE);
    }

    @Override
    public Object readCategorizedListByIdIn(List<String> ids) {
       return null;
    }

    @Override
    public Object readCategorizedList() {
        return null;
    }

    @Override
    public Service readOne(String id) {
        return serviceRepository.getById(id, State.ACTIVE);
    }

    @Override
    public Service create(Service service) {
        service.setState(State.ACTIVE);
        return serviceRepository.save(service);
    }

    @Override
    public Service update(Service service) {
        service.setState(State.ACTIVE);
        return serviceRepository.save(service);
    }

    @Override
    public void delete(String id) {
        Service service = serviceRepository.getById(id, State.ACTIVE);
        service.setState(State.DELETED);
        serviceRepository.save(service);
    }
}
