package kz.gaziza.emdikmed.service.dmp.configuration;

import kz.gaziza.emdikmed.model.dmp.configuration.DMPCategory;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IDMPCategoryService {

    Page<DMPCategory> readPageable(Map<String, String> allRequestParams);

    Page<DMPCategory> searchPageable(Map<String, String> allRequestParams);

    List<DMPCategory> readIterable();

    List<DMPCategory> readIterableByFilter(String filter);

    DMPCategory readOne(String id);

    DMPCategory create(DMPCategory category);

    DMPCategory update(DMPCategory category);

    void delete(String id);

}
