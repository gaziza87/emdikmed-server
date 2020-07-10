package kz.gaziza.emdikmed.service.dmp.configuration;

import kz.gaziza.emdikmed.model.dmp.configuration.Protocol;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.gridfs.GridFsResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface IProtocolService {

    Page<Protocol> readPageable(Map<String, String> allRequestParams);
    Page<Protocol> searchPageable(Map<String, String> allRequestParams);
    List<Protocol> readIterable();
    List<Protocol> readIterableByDMPV2Id(String dmpV2Id);
    Protocol readOne(String id);
    Protocol create(Protocol protocol);
    Protocol update(Protocol protocol);
    String saveFile(InputStream content, String mimeType, String fileName) throws IOException;
    void delete(String id);
    GridFsResource downloadFile(String fileId) throws IOException;
    
}
