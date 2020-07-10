package kz.gaziza.emdikmed.repository.dmp.configuration;

import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.dmp.configuration.Protocol;
import kz.gaziza.emdikmed.repository.ResourceUtilRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProtocolRepository extends ResourceUtilRepository<Protocol, String> {

    @Query(value = "{ $or: [ " +
            "{ 'id' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'code' : {$regex: '?0', $options: 'i'} }, " +
            "{ 'name' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'description' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'diseaseId' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'fileId' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'state' : {$regex:'?0', $options: 'i'} }, " +
            "] }")
    Page<Protocol> query(String searchString, Pageable pageable);

    @Query(value = "{id:'?0', state: '?1'}")
    Protocol getById(String id, State state);

    @Query("{diseaseId: '?0', state: '?1'}")
    List<Protocol> getAllByDiseaseId(String diseaseId, State state);

    @Query("{code: '?0', state: '?1'}")
    Protocol getByCode(String code, State state);

}
