package kz.gaziza.emdikmed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document(collection = "organizations")
public class Organization {


    @Id
    private String id;


    @Indexed
    private String parentId;
    private boolean isRootOrg;

    private Map<String, String> name;

    private String code;

    private String description;
    private Integer employeeCount;

    private String bik;


    private String orgTypeCode;

    private String address;

    private List<String> phone;

    private String photoId;

    @JsonIgnore
    private int state;
}
