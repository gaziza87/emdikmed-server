package kz.gaziza.emdikmed.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "users")
public class User {


    @Id
    private String id;
    private String idn;
    private String email;
    private String name;
    private String surname;
    private String middlename;
    private LocalDateTime birthday;
    private String address;
    private String sex;
    private String mobilePhone;

}
