package kz.gaziza.emdikmed.service.dmp.visit;

import kz.gaziza.emdikmed.model.dmp.visit.VisitContent;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IVisitContentService {
    Page<VisitContent> readPageable(Map<String, String> allRequestParams);
    Page<VisitContent> searchPageable(Map<String, String> allRequestParams);
    VisitContent readOne(String id);
    VisitContent create(VisitContent visitContent);
    VisitContent update(VisitContent visitContent);
    VisitContent updateAfterDiseaseSelection(VisitContent visitContent);
}
