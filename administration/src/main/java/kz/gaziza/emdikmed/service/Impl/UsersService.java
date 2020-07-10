package kz.gaziza.emdikmed.service.Impl;

import kz.gaziza.emdikmed.model.User;
import kz.gaziza.emdikmed.model.UserAccount;
import kz.gaziza.emdikmed.model.UserRoleOrgMap;
import kz.gaziza.emdikmed.model.custom.UserDTO;
import kz.gaziza.emdikmed.repository.IUserAccountRepository;
import kz.gaziza.emdikmed.repository.UsersRepository;
import kz.gaziza.emdikmed.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UsersService implements IUsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    IUserAccountRepository userAccountRepository;

    @Override
    public Page<User> readPageable(Map<String, String> allRequestParams) throws DataAccessException {
        return null;
    }

    @Override
    public Page<UserDTO> readPageableByIdIn(List<String> patientIds, Map<String, String> allRequestParams) throws DataAccessException {
        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = 0;

        int pageSize = 15;

        String sortBy = "name";

        if (allRequestParams.containsKey("sortDirection")) {

            if (allRequestParams.get("sortDirection").equals("desc"))
                sortDirection = Sort.Direction.DESC;

        }
        if (allRequestParams.containsKey("sort")) {
            sortBy = allRequestParams.get("sort");
        }
        if (allRequestParams.containsKey("page")) {
            pageNumber = Integer.parseInt(allRequestParams.get("page"));
        }
        if (allRequestParams.containsKey("size")) {
            pageSize = Integer.parseInt(allRequestParams.get("size"));
        }

        Pageable pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

        Page<User> usersPage = usersRepository.findAllByIdIn(patientIds, pageableRequest);

        List<UserDTO> userDTOList = new ArrayList<>();

        usersPage.getContent().forEach(user -> {
            UserAccount userAccount = userAccountRepository.getByUserId(user.getId());
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getId());
            userDTO.setUser(user);
            userDTO.setUserAccount(userAccount);
            userDTOList.add(userDTO);
        });

        return new PageImpl<>(userDTOList, usersPage.getPageable(), usersPage.getTotalElements());
    }

    @Override
    public Page<User> searchPageable(Map<String, String> allRequestParams) throws DataAccessException {
        Query query = new Query();

        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = 0;

        int pageSize = 15;

        String sortBy = "name";

        String searchString = "";

        if (allRequestParams.containsKey("searchString")) {
            searchString = allRequestParams.get("searchString");
        }

        if (allRequestParams.containsKey("sortDirection")) {

            if (allRequestParams.get("sortDirection").equals("desc"))
                sortDirection = Sort.Direction.DESC;

        }
        if (allRequestParams.containsKey("sort")) {
            sortBy = allRequestParams.get("sort");
        }
        if (allRequestParams.containsKey("page")) {
            pageNumber = Integer.parseInt(allRequestParams.get("page"));
        }
        if (allRequestParams.containsKey("size")) {
            pageSize = Integer.parseInt(allRequestParams.get("size"));
        }

        Pageable pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));

        return usersRepository.query(searchString, pageableRequest);
    }

    @Override
    public List<User> readIterable() throws DataAccessException {
        return usersRepository.findAll();
    }

    @Override
    public List<User> readPatients() {
//        userAccountRepository.get
//        return usersRepository.findAllBy;
        return null;
    }

    @Override
    public Page<User> search(Map<String, String> allRequestParams) {
        return null;
    }

    @Override
    public User findUserById(String id) throws DataAccessException {
        return null;
    }

    @Override
    public List<User> readByUsername(String username) throws DataAccessException {
        return usersRepository.readByUsername(username);
    }

    @Override
    public List<User> readByUsername(String id, String username) throws DataAccessException {
        return usersRepository.readByUsername(username, id);
    }

    @Override
    public void updateUserShortInformation(User newUserInfo) {

    }

    @Override
    public void updateUserStateInformation(String userId, Integer state) {

    }

    @Override
    public User get(String id) {
        return usersRepository.getById(id);
    }

    public Integer getCountByCity(String city) {
        return null;
    }

    @Override
    public User save(User userRequest) {
        return usersRepository.save(userRequest);
    }
    @Override
    public void delete(User user) {
        usersRepository.delete(user);
    }

    @Override
    public GridFsResource downloadFile(String fileId) throws IOException {
        return null;
    }

    @Override
    public String saveUserPicture(InputStream content, String mimeType, String fileName) throws IOException {
        return null;
    }

    @Override
    public String save(InputStream inputStream, String contentType, String filename, String username, String bucket, String mimeType) {
        return null;
    }

    @Override
    public UserRoleOrgMap changeOrg(UserRoleOrgMap unit) {
        return null;
    }

    @Override
    public Page<User> getUsersByOrgAndRole(Map<String, String> allRequestParams, String userId, String userRespondentId) {
        return null;
    }

    @Override
    public List<User> findUserByIds(List<String> ids) {
        return null;
    }
}
