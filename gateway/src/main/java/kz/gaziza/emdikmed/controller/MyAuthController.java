package kz.gaziza.emdikmed.controller;

import kz.gaziza.emdikmed.dto.request.LoginRequest;
import kz.gaziza.emdikmed.dto.request.SignUpRequest;
import kz.gaziza.emdikmed.repository.UserAccountRepository;
import kz.gaziza.emdikmed.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/emdikmed/auth")
public class MyAuthController {

    @Autowired
    private IAuthService authService;
    @Autowired
    private UserAccountRepository repo;

    @GetMapping("/")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getUsername());
        System.out.println(loginRequest.getPassword());
        return ResponseEntity.ok(authService.signIn(loginRequest));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> singUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.signUp(signUpRequest);
    }
}
