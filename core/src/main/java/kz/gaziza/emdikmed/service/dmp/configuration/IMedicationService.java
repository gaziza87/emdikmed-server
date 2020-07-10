package kz.gaziza.emdikmed.service.dmp.configuration;

import kz.gaziza.emdikmed.model.dmp.configuration.Medication;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IMedicationService {

    Page<Medication> readPageable(Map<String, String> allRequestParams);
    Page<Medication> searchPageable(Map<String, String> allRequestParams);
    List<Medication> readIterable();
    List<Medication> readIterableByCategoryId(String categoryId);
    /*List<Medication> readIterableByIdIn(List<String> ids);*/
    Medication readOne(String id);
    Medication create(Medication medication);
    Medication update(Medication medication);
    void delete(String id);

}
