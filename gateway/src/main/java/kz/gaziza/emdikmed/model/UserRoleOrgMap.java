package kz.gaziza.emdikmed.model;

import lombok.Data;

import java.util.List;

@Data
public class UserRoleOrgMap {
    String userAccountId;
    String orgId;
    List<Roles> roles;
}
