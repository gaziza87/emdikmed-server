package kz.gaziza.emdikmed.service.dmp.configuration;

import kz.gaziza.emdikmed.model.dmp.configuration.ProceduresAndInterventions;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IProceduresAndInterventionsService {

    Page<ProceduresAndInterventions> readPageable(Map<String, String> allRequestParams);
    Page<ProceduresAndInterventions> searchPageable(Map<String, String> allRequestParams);
    List<ProceduresAndInterventions> readIterable();
   /* List<ProceduresAndInterventions> readIterableByIdIn(List<String> ids);*/
    List<ProceduresAndInterventions> readIterableByCategoryId(String categoryId);
    Object readCategorizedList();
    ProceduresAndInterventions readOne(String id);
    ProceduresAndInterventions create(ProceduresAndInterventions proceduresAndInterventions);
    ProceduresAndInterventions update(ProceduresAndInterventions proceduresAndInterventions);
    void delete(String id);
    
}
