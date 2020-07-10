package kz.gaziza.emdikmed.service;

import kz.gaziza.emdikmed.constants.OrganizationConstants;
import kz.gaziza.emdikmed.dto.request.LoginRequest;
import kz.gaziza.emdikmed.dto.request.SignUpRequest;
import kz.gaziza.emdikmed.dto.response.JwtResponse;
import kz.gaziza.emdikmed.dto.response.MessageResponse;
import kz.gaziza.emdikmed.model.*;
import kz.gaziza.emdikmed.repository.OrganizationRepository;
import kz.gaziza.emdikmed.repository.UserAccountRepository;
import kz.gaziza.emdikmed.security.jwt.JwtProvider;
import kz.gaziza.emdikmed.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public JwtResponse signIn(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        UserAccount userAccount = userAccountRepository.getByUsername(userDetails.getUsername(), State.ACTIVE);

        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles, userAccount);
    }

    @Override
    public ResponseEntity<?> signUp(SignUpRequest signUpRequest) {
        Boolean existsByUsername = userAccountRepository.existsByUsername(signUpRequest.getUsername(), State.ACTIVE);
        if (existsByUsername != null && existsByUsername) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        Boolean existsByEmail = userAccountRepository.existsByEmail(signUpRequest.getEmail(), State.ACTIVE);
        if (existsByEmail != null && existsByEmail) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(signUpRequest.getUsername());
        userAccount.setEmail(signUpRequest.getEmail());
        userAccount.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userAccount = userAccountRepository.save(userAccount);

        Organization organization = organizationRepository.findByCode(OrganizationConstants.ROOT_ORG);
        if (organization == null) {
            organization = new Organization();

            Map<String, String> name = new HashMap<>();
            name.put("kz", "Басты ұйым");
            name.put("ru", "Корневая организация");
            name.put("en", "Root organization");
            organization.setName(name);

            organization.setCode(OrganizationConstants.ROOT_ORG);
            organization.setRootOrg(true);

            organization = organizationRepository.save(organization);
        }

        List<UserRoleOrgMap> userRoleOrgMapList = new ArrayList<>();

        UserRoleOrgMap userRoleOrgMap = new UserRoleOrgMap();
        userRoleOrgMap.setOrgId(organization.getId());
        userRoleOrgMap.setUserAccountId(userAccount.getId());

        List<Roles> roles = new ArrayList<>();
        roles.add(Roles.GLOBAL_ADMINISTRATOR);

        userRoleOrgMap.setRoles(roles);

        userRoleOrgMapList.add(userRoleOrgMap);
        userAccount.setUserRoleOrgMapList(userRoleOrgMapList );
        userAccount.setState(State.ACTIVE);

        userAccountRepository.save(userAccount);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
