package kz.gaziza.emdikmed.repository;

import kz.gaziza.emdikmed.constant.AdministrationConstants;
import kz.gaziza.emdikmed.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends ResourceUtilRepository<User, String> {

    @Query(value = "{ 'id': ?0, 'state': " + AdministrationConstants.STATUS_ACTIVE + " }",
            fields = "{ 'id': 1, 'username': 1, 'name': 1, 'surname': 1,'middlename': 1}")
    User findActiveById(String id) throws DataAccessException;

    @Query(value = "{ 'id': {$in: ?0}, 'state': " + AdministrationConstants.STATUS_ACTIVE + " }",
            fields = "{ 'id': 1, 'username': 1, 'name': 1, 'surname': 1,'middlename': 1}")
    List<User> findUserLightByIdIn(List<String> ids) throws DataAccessException;


    @Query("{id:'?0'}")
    //, state:"+AdministrationConstants.STATUS_ACTIVE +"
    User findUserById(String id) throws DataAccessException;


    @Query("{username:'?0', state:" + AdministrationConstants.STATUS_ACTIVE + "}")
    List<User> readByUsername(String username) throws DataAccessException;

    @Query("{username:'?0', id: {$ne : '?1'}, state:" + AdministrationConstants.STATUS_ACTIVE + "}")
    List<User> readByUsername(String username, String id) throws DataAccessException;

    Page<User> findAllByIdIn(List<String> ids, Pageable pageable);


    @Query("{id:'?0'}")
    User getById(String id);


    @Query(value = "{ $or: [ { 'username'   : {$regex:'?0', $options: 'i'}}, " +
            "         { 'email'      : {$regex:'?0', $options: 'i'}}, " +
            "         { 'mobilePhone': {$regex:'?0', $options: 'i'}}, " +
            "         { 'position'   : {$regex:'?0', $options: 'i'}}, " +
            "         { 'name'       : {$regex:'?0', $options: 'i'}}, " +
            "         { 'surname'    : {$regex:'?0', $options: 'i'}}, " +
            "         { 'middlename' : {$regex:'?0', $options: 'i'}}], " +
            "}")
    Page<User> query(String searchString, Pageable pageable);

}
