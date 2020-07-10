package kz.gaziza.emdikmed.repository;

import kz.gaziza.emdikmed.model.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserAccountRepository extends ResourceUtilRepository<UserAccount, String> {

    @Query("{id: '?0'}")
    UserAccount getById(String id);

    @Query("{userId: '?0'}")
    UserAccount getByUserId(String userId);

    @Query("{username: '?0'")
    UserAccount getByUsername(String username);

    @Query("{mobilePhone: '?0'}")
    UserAccount getByMobilePhone(String mobilePhone);

    @Query("{'userRoleOrgMapList.roles': {$in: ['?0']}, 'userRoleOrgMapList.orgId': '?1'}")
    Page<UserAccount> getAllUsersByRoleFromOneOrganization(String role, String organizationId, Pageable pageable);

    @Query(value = "{ $or: [ " +
                                "{ 'name' : {$regex: '?0', $options: 'i'} }, " +
                                "{ 'surname' : {$regex:'?0', $options: 'i'} }, " +
                                "{ 'middlename' : {$regex:'?0', $options: 'i'} }, " +
                                "{ 'username' : {$regex:'?0', $options: 'i'} }, " +
                                "{ 'email' : {$regex:'?0', $options: 'i'} }, " +
                                "{ 'idn' : {$regex:'?0', $options: 'i'} }, " +
                                "{ 'phone' : {$regex:'?0', $options: 'i'} }, " +
                          "], " +
                      "'userRoleOrgMapList.roles': {$in: ['?1']}, " +
                      "'userRoleOrgMapList.orgId': '?2'" +
                  "}")
    Page<UserAccount> searchAllUsersByRoleFromOneOrganization(String searchString, String role, String organizationId, Pageable pageable);


    @Query("{'userRoleOrgMapList.roles': {$in: ['?0']}}")
    Page<UserAccount> getAllUsersByRole(String role, Pageable pageable);

    @Query(value = "{ $or: [ " +
                                "{ 'name' : {$regex: '?0', $options: 'i'} }, " +
                                "{ 'surname' : {$regex:'?0', $options: 'i'} }, " +
                                "{ 'middlename' : {$regex:'?0', $options: 'i'} }, " +
                                "{ 'username' : {$regex:'?0', $options: 'i'} }, " +
                                "{ 'email' : {$regex:'?0', $options: 'i'} }, " +
                                "{ 'phone' : {$regex:'?0', $options: 'i'} }, " +
                                "{ 'idn' : {$regex:'?0', $options: 'i'} }, " +
                                "], " +
                    "'userRoleOrgMapList.roles': {$in: ['?1']}, " +
                    "}")
    Page<UserAccount> searchAllUsersByRole(String searchString, String role, Pageable pageable);
}
