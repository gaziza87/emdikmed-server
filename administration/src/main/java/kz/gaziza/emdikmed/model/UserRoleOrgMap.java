package kz.gaziza.emdikmed.model;

import kz.gaziza.emdikmed.constant.AdministrationConstants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
public class UserRoleOrgMap {
    String userAccountId;
    String orgId;
    List<AdministrationConstants.ROLE> roles;
}
