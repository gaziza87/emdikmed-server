package kz.gaziza.emdikmed.model.custom;

import kz.gaziza.emdikmed.model.User;
import kz.gaziza.emdikmed.model.UserAccount;
import lombok.Data;

@Data
public class UserDTO {

    private String userId;
    private User user;
    private UserAccount userAccount;

}
