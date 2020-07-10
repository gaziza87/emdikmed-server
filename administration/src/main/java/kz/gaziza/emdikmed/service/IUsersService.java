package kz.gaziza.emdikmed.service;


import kz.gaziza.emdikmed.model.User;
import kz.gaziza.emdikmed.model.UserRoleOrgMap;
import kz.gaziza.emdikmed.model.custom.UserDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.gridfs.GridFsResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface IUsersService {
    Page<User> readPageable(Map<String, String> allRequestParams) throws DataAccessException;

    Page<UserDTO> readPageableByIdIn(List<String> patientIds, Map<String, String> allRequestParams) throws DataAccessException;

    Page<User> searchPageable(Map<String, String> allRequestParams) throws DataAccessException;

    List<User> readIterable() throws DataAccessException;

    List<User> readPatients();

    Page<User> search(Map<String, String> allRequestParams);

    User findUserById(String id) throws DataAccessException;

    List<User> readByUsername(String username) throws DataAccessException;

    List<User> readByUsername(String id, String username) throws DataAccessException;

    void updateUserShortInformation(User newUserInfo);

    void updateUserStateInformation(String userId, Integer state);

    User get(String id);

    User save(User userRequest);

    void delete(User user);

    GridFsResource downloadFile(String fileId) throws IOException;

    String saveUserPicture(InputStream content, String mimeType, String fileName) throws IOException;

    String save(InputStream inputStream, String contentType, String filename,
                String username, String bucket, String mimeType);

    UserRoleOrgMap changeOrg(UserRoleOrgMap unit);

    Page<User> getUsersByOrgAndRole(Map<String, String> allRequestParams, String userId, String userRespondentId);

    List<User> findUserByIds(List<String> ids);
}
