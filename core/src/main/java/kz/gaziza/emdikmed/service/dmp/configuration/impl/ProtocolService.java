package kz.gaziza.emdikmed.service.dmp.configuration.impl;

import kz.gaziza.emdikmed.constant.ProtocolConstants;
import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.dmp.configuration.Protocol;
import kz.gaziza.emdikmed.repository.dmp.configuration.ProtocolRepository;
import kz.gaziza.emdikmed.service.dmp.configuration.IProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class ProtocolService implements IProtocolService {

    @Autowired
    private ProtocolRepository protocolRepository;
    @Override
    public Page<Protocol> readPageable(Map<String, String> allRequestParams) {
        Query query = new Query();

        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = ProtocolConstants.DEFAUT_PAGE_NUMBER;

        int pageSize = ProtocolConstants.DEFAUT_PAGE_SIZE;

        String sortBy = ProtocolConstants.ID_FIELD_NAME;

        if (allRequestParams.containsKey("id")) {
            query.addCriteria(Criteria.where(ProtocolConstants.ID_FIELD_NAME).is(allRequestParams.get("id")));
        }
        if (allRequestParams.containsKey("code")) {
            query.addCriteria(Criteria.where(ProtocolConstants.CODE_FIELD_NAME).is(allRequestParams.get("code")));
        }
        if (allRequestParams.containsKey("name")) {
            query.addCriteria(Criteria.where(ProtocolConstants.NAME_FIELD_NAME).is(allRequestParams.get("name")));
        }
        if (allRequestParams.containsKey("description")) {
            query.addCriteria(Criteria.where(ProtocolConstants.DESCRIPTION_FIELD_NAME).is(allRequestParams.get("description")));
        }
        if (allRequestParams.containsKey("sortDirection")) {

            if (allRequestParams.get("sortDirection").equals(ProtocolConstants.SORT_DIRECTION_DESC))
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

        query.addCriteria(Criteria.where(ProtocolConstants.STATE_FIELD_NAME).is(ProtocolConstants.STATUS_ACTIVE));

        final Pageable pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

        return protocolRepository.findAll(query, pageableRequest);
    }

    @Override
    public Page<Protocol> searchPageable(Map<String, String> allRequestParams) {
        return null;
    }

    @Override
    public List<Protocol> readIterable() {
        return protocolRepository.findAll();
    }

    @Override
    public List<Protocol> readIterableByDMPV2Id(String dmpV2Id) {
      return null;
    }

    @Override
    public Protocol readOne(String id) {
        return protocolRepository.getById(id, State.ACTIVE);
    }

    @Override
    public Protocol create(Protocol protocol) {
        protocol.setState(State.ACTIVE);
        return protocolRepository.save(protocol);
    }

    @Override
    public Protocol update(Protocol protocol) {
        protocol.setState(State.ACTIVE);
        return protocolRepository.save(protocol);
    }



    @Override
    public String saveFile(InputStream content, String mimeType, String fileName) throws IOException {
        return null;
    }

    @Override
    public void delete(String id) {
        Protocol protocol = protocolRepository.getById(id, State.ACTIVE);
        protocol.setState(State.DELETED);
        protocolRepository.save(protocol);
    }

    @Override
    public GridFsResource downloadFile(String fileId) throws IOException {
        return null;
    }
}
