package kz.gaziza.emdikmed.service;

import kz.gaziza.emdikmed.model.Template;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ITemplateService {

    List<Template> getAll();

    Template getById(String id);


    Template save(Template template);

    void delete(Template template);

    void deleteById(String id);

    Page<Template> searchTemplate(Map<String, String> allRequestParams);
}
