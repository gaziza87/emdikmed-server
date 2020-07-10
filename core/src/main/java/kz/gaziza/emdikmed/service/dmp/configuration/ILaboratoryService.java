package kz.gaziza.emdikmed.service.dmp.configuration;

import kz.gaziza.emdikmed.model.dmp.configuration.Laboratory;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ILaboratoryService {

    Page<Laboratory> readPageable(Map<String, String> allRequestParams);
    Page<Laboratory> searchPageable(Map<String, String> allRequestParams);
    List<Laboratory> readIterable();
    List<Laboratory> readIterableByIdIn(List<String> ids);
    Object readCategorizedLaboratoriesByIdIn(List<String> ids);
    Object readCategorizedLaboratories();
    List<Laboratory> readIterableByCategoryId(String categoryId);
    Laboratory readOne(String id);
    Laboratory create(Laboratory laboratory);
    Laboratory update(Laboratory laboratory);
    void delete(String id);


}
