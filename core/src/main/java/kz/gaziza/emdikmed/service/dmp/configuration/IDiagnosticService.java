package kz.gaziza.emdikmed.service.dmp.configuration;

import kz.gaziza.emdikmed.model.dmp.configuration.Diagnostic;
import org.apache.tomcat.util.Diagnostics;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IDiagnosticService {

    Page<Diagnostic> readPageable(Map<String, String> allRequestParams);

    Page<Diagnostic> searchPageable(Map<String, String> allRequestParams);

    List<Diagnostic> readIterable();

    /* List<Diagnostic> readIterableByIdIn(List<String> ids);*/
    List<Diagnostic> readIterableByCategoryId(String categoryId);

    Object readCategorizedDiagnostics();

    Diagnostic readOne(String id);

    Diagnostic create(Diagnostic diagnostic);

    Diagnostic update(Diagnostic diagnostic);

    void delete(String id);

}
