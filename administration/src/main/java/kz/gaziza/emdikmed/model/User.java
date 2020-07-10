package kz.gaziza.emdikmed.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "users")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class User extends BaseAuditable {


    @Id
    private String id;
    private String idn;
    private String email;
    private String name;
    private String surname;
    private String middlename;
    private LocalDate birthday;
    private String address;
    private String sex;
    private String mobilePhone;
    private String nationality;


}
