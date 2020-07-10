package kz.gaziza.emdikmed.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SignUpRequest {

    @NotBlank
    private String username;
 
    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    private String password;

}
