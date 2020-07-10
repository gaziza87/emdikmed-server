package kz.gaziza.emdikmed.service;

import kz.gaziza.emdikmed.dto.request.LoginRequest;
import kz.gaziza.emdikmed.dto.request.SignUpRequest;
import kz.gaziza.emdikmed.dto.response.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface IAuthService {

    JwtResponse signIn(LoginRequest loginRequest);
    ResponseEntity<?> signUp(SignUpRequest signupRequest);

}
