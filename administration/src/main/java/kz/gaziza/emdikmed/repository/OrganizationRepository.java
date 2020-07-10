package kz.gaziza.emdikmed.repository;

import kz.gaziza.emdikmed.constant.AdministrationConstants;
import kz.gaziza.emdikmed.model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends ResourceUtilRepository<Organization, String> {

    @Query("{id:'?0'}")
    Organization getById(String id);

    @Query("{code:'?0', state:" + AdministrationConstants.STATUS_ACTIVE + "}")
    Organization getByCode(String code);

    Organization findByIsRootOrg(boolean rootOrg);

    Organization findTopByOrderByCreatedDateDesc();

    @Query("{path: {$regex:'^?0'}, state:" + AdministrationConstants.STATUS_ACTIVE + "}")
        //"{'path': {$regex:'^?0'}, 'isActive': {$gte: 1} }")
    List<Organization> readPath(String path, Pageable page);

    List<Organization> findAllByState(Integer state);

    List<Organization> findAllByState(Integer state, Pageable page);

    List<Organization> findAllByIdIn(List<String> ids);

    List<Organization> findAllByStateAndIdNot(Integer state, String id);

    @Query("{parentId:'?0', state:" + AdministrationConstants.STATUS_ACTIVE + "}")
    List<Organization> findAllByParentId(String id);

    @Query("{parentId: {$ne: '?0'}, id: {$ne : '?0'}, state:" + AdministrationConstants.STATUS_ACTIVE + "}")
    List<Organization> findAllOthersByParentId(String id);

    @Query(value = "{code:'?0', state:" + AdministrationConstants.STATUS_ACTIVE + "}")
    List<Organization> findByCode(String code);

    // TODO - nam kz, ru, en dep algan durs emes. nuzhno chto to pridumat
    @Query(value = "{ $or: [ " +
            "                { 'name.kz'                : {$regex:'?0', $options: 'i'}},  " +
            "                { 'name.ru'                : {$regex:'?0', $options: 'i'}},  " +
            "                { 'name.en'                : {$regex:'?0', $options: 'i'}},  " +
            "                { 'code'                   : {$regex:'?0', $options: 'i'}},  " +
            "                { 'address'                : {$regex:'?0', $options: 'i'}},  " +
            "                { 'phone'                  : {$regex:'?0', $options: 'i'}},  " +
            "                { 'orgTypeCode'               : {$regex:'?0', $options: 'i'}}]}")
    Page<Organization> query(String searchString, Pageable pageable);


    @Query(value = "{ $or: [ { 'name.kz'                : {$regex:'?0', $options: 'i'}},  " +
            "                { 'name.ru'                : {$regex:'?0', $options: 'i'}},  " +
            "                { 'name.en'                : {$regex:'?0', $options: 'i'}},  " +
            "                { 'code'                   : {$regex:'?0', $options: 'i'}},  " +
            "                { 'address'                : {$regex:'?0', $options: 'i'}},  " +
            "                { 'phone'                  : {$regex:'?0', $options: 'i'}},  " +
            "                { 'orgTypeCode'               : {$regex:'?0', $options: 'i'}}], " +
            "'state' : " + AdministrationConstants.STATUS_ACTIVE + "}")
    Page<Organization> query1(String searchString, Pageable pageable);


}
