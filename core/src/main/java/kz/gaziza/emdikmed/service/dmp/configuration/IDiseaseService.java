package kz.gaziza.emdikmed.service.dmp.configuration;

import kz.gaziza.emdikmed.model.dmp.configuration.Disease;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IDiseaseService {

    Page<Disease> readPageable(Map<String, String> allRequestParams);
    Page<Disease> searchPageable(Map<String, String> allRequestParams);
    List<Disease> readIterable();
    List<Disease> readIterableByCategoryId(String categoryId);
    List<Disease> readIterableByIdIn(List<String> diseaseIds);
    Disease readOne(String id);
    Disease create(Disease disease);
    Disease update(Disease disease);
    void delete(String id);
    
}
