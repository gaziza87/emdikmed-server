package kz.gaziza.emdikmed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "users_account")
public class UserAccount {

    @Id
    private String id;
    private String email;
    private String username;
    private String password;
    private String phone;
    private String signUpToken;
    private String userId;
    private String avatarId;
    private String imageUrl;
    private String position;
    private String aboutYourself;
    private String interfaceLang;
    private boolean active;
    private List<UserRoleOrgMap> userRoleOrgMapList;
    private State state;

    /**
     * Дата регистрации в системе
     */
    @JsonIgnore
    private LocalDateTime sysRegDate;

}
