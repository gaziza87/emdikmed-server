package kz.gaziza.emdikmed.repository.dmp.configuration;

import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.dmp.configuration.DMPCategory;
import kz.gaziza.emdikmed.model.dmp.configuration.DMPCategoryFilter;
import kz.gaziza.emdikmed.repository.ResourceUtilRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DMPCategoryRepository extends ResourceUtilRepository<DMPCategory, String> {

    @Query(value = "{ $or: [ " +
            "{ 'id' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'code' : {$regex: '?0', $options: 'i'} }, " +
            "{ 'name' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'description' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'filter' : {$regex:'?0', $options: 'i'} }, " +
            "{ 'state' : {$regex:'?0', $options: 'i'} }, " +
            "] }")
    Page<DMPCategory> query(String searchString, Pageable pageable);

    @Query(value = "{id:'?0', state: '?1'}")
    DMPCategory findById(String id, State state);

    @Query("{filter: '?0', state: '?1'}")
    List<DMPCategory> getAllByFilter(DMPCategoryFilter filter, State state);

    @Query("{code: '?0', state: '?1'}")
    DMPCategory getByCode(String code, State state);

    List<DMPCategory> findAllByFilterAndState(String filter, State state) throws DataAccessException;



}
