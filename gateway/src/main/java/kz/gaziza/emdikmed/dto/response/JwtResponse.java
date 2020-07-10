package kz.gaziza.emdikmed.dto.response;

import kz.gaziza.emdikmed.model.UserAccount;
import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {

    private String id;
    private String token;
    private String username;
    private String email;
    private List<String> roles;
    private String type = "Bearer";
    private UserAccount userAccount;

    public JwtResponse(String accessToken, String id, String username, String email, List<String> roles, UserAccount userAccount) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.userAccount = userAccount;
    }

}
