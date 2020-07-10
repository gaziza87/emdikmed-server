package kz.gaziza.emdikmed.service.Impl;

import com.google.gson.Gson;
import kz.gaziza.emdikmed.constant.AdministrationConstants;
import kz.gaziza.emdikmed.model.*;
import kz.gaziza.emdikmed.model.custom.UserDTO;
import kz.gaziza.emdikmed.model.custom.UserRoleMapDTO;
import kz.gaziza.emdikmed.repository.IUserAccountRepository;
import kz.gaziza.emdikmed.repository.OrganizationRepository;
import kz.gaziza.emdikmed.repository.UsersRepository;
import kz.gaziza.emdikmed.service.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserAccountService implements IUserAccountService {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private IUserAccountRepository userAccountRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<UserDTO> readAllUsers(Map<String, String> allRequestParams) {
        Query query = new Query();

        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = 0;

        int pageSize = 15;

        String sortBy = "name";

        String role = null;
        String organizationId = null;

        if (allRequestParams.containsKey("role")) {
            role = allRequestParams.get("role");
        }
        if (allRequestParams.containsKey("organizationId")) {
            organizationId = allRequestParams.get("organizationId");
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

        Page<UserAccount> userAccountPage = null;

        if (role != null && organizationId != null) {
            userAccountPage = userAccountRepository.getAllUsersByRoleFromOneOrganization(role, organizationId, pageableRequest);
        } else {
            userAccountPage = userAccountRepository.getAllUsersByRole(role, pageableRequest);
        }

        System.out.println(new Gson().toJsonTree(userAccountPage));
        System.out.println(userAccountPage.getContent().toString());


        List<UserDTO> userDTOList = new ArrayList<>();
        userAccountPage.getContent().forEach(userAccount -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(userAccount.getUserId());
            userDTO.setUser(usersRepository.findUserById(userAccount.getUserId()));
            userDTO.setUserAccount(userAccount);
            userDTOList.add(userDTO);
        });


        return new PageImpl<>(userDTOList, userAccountPage.getPageable(), userAccountPage.getTotalElements());
    }


    @Override
    public Page<UserDTO> searchUsers(Map<String, String> allRequestParams) {
        Query query = new Query();

        Sort.Direction sortDirection = Sort.Direction.ASC;

        int pageNumber = 0;

        int pageSize = 15;

        String sortBy = "name";

        String role = null;
        String organizationId = null;
        String searchString = "";

        if (allRequestParams.containsKey("role")) {
            role = allRequestParams.get("role");
        }
        if (allRequestParams.containsKey("organizationId")) {
            organizationId = allRequestParams.get("organizationId");
        }
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

        Page<UserAccount> userAccountPage = null;

        if (role != null && organizationId != null) {
            userAccountPage = userAccountRepository.searchAllUsersByRoleFromOneOrganization(searchString, role, organizationId, pageableRequest);
        } else {
            userAccountPage = userAccountRepository.searchAllUsersByRole(searchString, role, pageableRequest);
        }

        List<UserDTO> userDTOList = new ArrayList<>();
        userAccountPage.getContent().forEach(userAccount -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUser(usersRepository.findUserById(userAccount.getUserId()));
            userDTO.setUserAccount(userAccount);
            userDTOList.add(userDTO);
        });

        return new PageImpl<>(userDTOList, userAccountPage.getPageable(), userAccountPage.getTotalElements());
    }

    @Override
    public List<UserAccount> readByOrg(String orgId) {
        return null;
    }

    @Override
    public List<UserAccount> readAll() {
        return userAccountRepository.findAll();
    }

    @Override
    public Integer getCountByOrg(String org) {
        return null;
    }

    @Override
    public UserAccount getById(String id) {
        return userAccountRepository.getById(id);
    }

    @Override
    public UserDTO getDTOById(String id) {
        UserAccount userAccount = userAccountRepository.getById(id);
        User user = usersRepository.getById(userAccount.getUserId());
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getId());
        userDTO.setUser(user);
        userDTO.setUserAccount(userAccount);
        return userDTO;
    }

    @Override
    public UserAccount getByUserId(String userId) {
        return userAccountRepository.getByUserId(userId);
    }

    @Override
    public UserAccount create(UserAccount userRequest) {
        return userAccountRepository.save(userRequest);
    }

    @Override
    public UserAccount create(User user) {
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(user.getEmail());

        if (user.getIdn() != null)
            userAccount.setUsername(user.getIdn());
        else if (user.getEmail() != null)
            userAccount.setUsername(user.getEmail());
        else if (user.getMobilePhone() != null)
            userAccount.setUsername(user.getMobilePhone());

        userAccount.setPassword(passwordEncoder.encode("P@ssw0rd"));
        userAccount.setIdn(user.getIdn());
        userAccount.setPhone(user.getMobilePhone());
        userAccount.setSignUpToken(null);
        userAccount.setUserId(user.getId());
        userAccount.setActive(false);
        userAccount.setState(State.ACTIVE);

        userAccount = userAccountRepository.save(userAccount);

        Organization organization = organizationRepository.findByIsRootOrg(true);

        List<UserRoleOrgMap> userRoleOrgMapList = new ArrayList<>();

        UserRoleOrgMap userRoleOrgMap = new UserRoleOrgMap();
        userRoleOrgMap.setOrgId(organization.getId());
        userRoleOrgMap.setUserAccountId(userAccount.getId());

        List<AdministrationConstants.ROLE> roles = new ArrayList<>();
        roles.add(AdministrationConstants.ROLE.PATIENT);

        userRoleOrgMap.setRoles(roles);
        userRoleOrgMapList.add(userRoleOrgMap);
        userAccount.setUserRoleOrgMapList(userRoleOrgMapList);

        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserDTO createUser(UserRoleMapDTO userRoleMapDTO) {
        System.out.println("userRoleMapDTO " + userRoleMapDTO.toString());
//        List<AdministrationConstants.ROLE> roles = new ArrayList<>();
//        roles.add(AdministrationConstants.ROLE.DOCTOR);
        return createUserByRole(userRoleMapDTO.getUser(), userRoleMapDTO.getUserRoleOrgMapList(), userRoleMapDTO.getPassword());
    }

    public UserDTO createUserByRole (User user, List<UserRoleOrgMap> userRoleOrgMapList, String password) {
        user = usersRepository.save(user);

        UserAccount userAccount = userAccountRepository.getByUserId(user.getId());
        if(userAccount == null) userAccount = new UserAccount();
        userAccount.setEmail(user.getEmail());

        if (user.getIdn() != null)
            userAccount.setUsername(user.getIdn());
        else if (user.getEmail() != null)
            userAccount.setUsername(user.getEmail());
        else if (user.getMobilePhone() != null)
            userAccount.setUsername(user.getMobilePhone());

        userAccount.setPassword(passwordEncoder.encode(password));
        userAccount.setIdn(user.getIdn());
        userAccount.setPhone(user.getMobilePhone());
        userAccount.setSignUpToken(null);
        userAccount.setUserId(user.getId());
        userAccount.setActive(false);
        userAccount.setState(State.ACTIVE);

        userAccount = userAccountRepository.save(userAccount);

//        Organization organization = organizationRepository.findByIsRootOrg(true);
//        List<UserRoleOrgMap> userRoleOrgMapList = new ArrayList<>();
//
//        UserRoleOrgMap userRoleOrgMap = new UserRoleOrgMap();
//        userRoleOrgMap.setOrgId(organization.getId());
//        userRoleOrgMap.setUserAccountId(userAccount.getId());
//        userRoleOrgMap.setRoles(roles);
//        userRoleOrgMapList.add(userRoleOrgMap);

        userAccount.setUserRoleOrgMapList(userRoleOrgMapList);
        userAccount = userAccountRepository.save(userAccount);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserAccount(userAccount);
        userDTO.setUser(user);
        userDTO.setUserId(user.getId());

        return userDTO;
    }

    @Override
    public UserAccount update(UserAccount userRequest) {
        return userAccountRepository.save(userRequest);
    }

    @Override
    public void delete(UserAccount user) {
        userAccountRepository.delete(user);
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
    public Page<UserAccount> getUsersByOrgAndRole(Map<String, String> allRequestParams, String userId, String userRespondentId) {
        return null;
    }

    @Override
    public List<UserAccount> findUserByIds(List<String> ids) {
        return null;
    }
}
