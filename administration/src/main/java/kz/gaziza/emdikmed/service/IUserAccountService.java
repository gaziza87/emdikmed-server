package kz.gaziza.emdikmed.service;

import kz.gaziza.emdikmed.model.User;
import kz.gaziza.emdikmed.model.UserAccount;
import kz.gaziza.emdikmed.model.UserRoleOrgMap;
import kz.gaziza.emdikmed.model.custom.UserDTO;
import kz.gaziza.emdikmed.model.custom.UserRoleMapDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.gridfs.GridFsResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface IUserAccountService {

    Page<UserDTO> readAllUsers(Map<String, String> allRequestParams);
    Page<UserDTO> searchUsers(Map<String, String> allRequestParams);

    List<UserAccount> readByOrg(String orgId);
    List<UserAccount> readAll();
    Integer getCountByOrg(String org);
    UserAccount getById(String id);
    UserDTO getDTOById(String id);
    UserAccount getByUserId(String userId);
    UserAccount create(UserAccount userRequest);
    UserAccount create(User user);

    UserDTO createUser(UserRoleMapDTO userRoleMapDTO);

    UserAccount update(UserAccount userRequest);
    void delete(UserAccount userAccount);
    GridFsResource downloadFile(String fileId) throws IOException;
    String saveUserPicture(InputStream content, String mimeType, String fileName) throws IOException;
    String save(InputStream inputStream, String contentType, String filename,
                String username, String bucket, String mimeType);
    UserRoleOrgMap changeOrg(UserRoleOrgMap unit);

    Page<UserAccount> getUsersByOrgAndRole(Map<String, String> allRequestParams, String userId, String userRespondentId);

    List<UserAccount> findUserByIds(List<String> ids);




}
