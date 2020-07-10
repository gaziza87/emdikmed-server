package kz.gaziza.emdikmed.model.custom;

import kz.gaziza.emdikmed.model.User;
import kz.gaziza.emdikmed.model.UserRoleOrgMap;
import lombok.Data;

import java.util.List;

@Data
public class UserRoleMapDTO {
    private User user;
    private List<UserRoleOrgMap> userRoleOrgMapList;
    private String password;
}
