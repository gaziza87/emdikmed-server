package kz.gaziza.emdikmed.repository.dmp.configuration;

import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.dmp.configuration.ProceduresAndInterventions;
import kz.gaziza.emdikmed.repository.ResourceUtilRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProceduresAndInterventionsRepository extends ResourceUtilRepository<ProceduresAndInterventions, String> {

    @Query(value = "{ $or: [ " +
            "{ 'id' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'code' : {$regex: '?0', $options: 'i'} }, " +
            "{ 'name' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'description' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'categoryId' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'diseaseIds' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'price' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'state' : {$regex:'?0', $options: 'i'} }, " +
            "] }")
    Page<ProceduresAndInterventions> query(String searchString, Pageable pageable);

    @Query(value = "{id:'?0', state: '?1'}")
    ProceduresAndInterventions getById(String id, State state);

    @Query("{categoryId: '?0', state: '?1'}")
    List<ProceduresAndInterventions> getAllByCategoryId(String categoryId, State state);

    @Query("{code: '?0', state: '?1'}")
    ProceduresAndInterventions getByCode(String code, State state);

}
