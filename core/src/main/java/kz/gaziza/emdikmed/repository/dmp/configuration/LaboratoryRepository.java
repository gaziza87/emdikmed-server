package kz.gaziza.emdikmed.repository.dmp.configuration;

import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.dmp.configuration.Laboratory;
import kz.gaziza.emdikmed.repository.ResourceUtilRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaboratoryRepository extends ResourceUtilRepository<Laboratory, String> {

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
    Page<Laboratory> query(String searchString, Pageable pageable);

    @Query(value = "{id:'?0', state: '?1'}")
    Laboratory getById(String id, State state);

    @Query("{categoryId: '?0', state: '?1'}")
    List<Laboratory> getAllByCategoryId(String categoryId, State state);

    @Query("{code: '?0', state: '?1'}")
    Laboratory getByCode(String code, State state);

    List<Laboratory> findAllByIdInAndState(List<String> ids, State state);



}
