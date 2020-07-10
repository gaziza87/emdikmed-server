package kz.gaziza.emdikmed.repository;

import kz.gaziza.emdikmed.model.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends ResourceUtilRepository<Template, String> {
    @Query("{id:'?0'}")
    Template getById(String id);

    @Query(value = "{ $or: [ " +
            "{ 'name' : {$regex: '?0', $options: 'i'} }, " +
            "{ 'description' : {$regex:'?0', $options: 'i'} }, " +
            "], " +
            "}")
    Page<Template> searchTemplate(String searchString, Pageable pageable);
}
