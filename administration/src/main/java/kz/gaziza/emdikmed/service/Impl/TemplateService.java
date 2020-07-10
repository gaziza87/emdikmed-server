package kz.gaziza.emdikmed.service.Impl;

import kz.gaziza.emdikmed.model.Template;
import kz.gaziza.emdikmed.repository.TemplateRepository;
import kz.gaziza.emdikmed.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TemplateService implements ITemplateService {

    @Autowired
    TemplateRepository templateRepository;

    @Override
    public List<Template> getAll() {
        return templateRepository.findAll();
    }

    @Override
    public Template getById(String id) {
        return templateRepository.getById(id);
    }

    @Override
    public Template save(Template template) {
        return templateRepository.save(template);
    }

    @Override
    public void delete(Template template) {
        templateRepository.delete(template);
    }

    @Override
    public void deleteById(String id) {
        templateRepository.deleteById(id);
    }

    @Override
    public Page<Template> searchTemplate(Map<String, String> allRequestParams) {
        Sort.Direction sortDirection = Sort.Direction.ASC;
        int pageNumber = 0;
        int pageSize = 15;
        String searchString = "";
        if (allRequestParams.containsKey("searchString")) {
            searchString = allRequestParams.get("searchString");
        }
        if (allRequestParams.containsKey("page")) {
            pageNumber = Integer.parseInt(allRequestParams.get("page"));
        }
        if (allRequestParams.containsKey("size")) {
            pageSize = Integer.parseInt(allRequestParams.get("size"));
        }
        Pageable pageableRequest = PageRequest.of(pageNumber, pageSize);
        Page<Template> templatePage = templateRepository.searchTemplate(searchString, pageableRequest);
        return new PageImpl<>(templatePage.getContent(), templatePage.getPageable(), templatePage.getTotalElements());
    }
}
