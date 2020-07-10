package kz.gaziza.emdikmed.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "template")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Template extends BaseAuditable {
    @Id
    private String id;
    private String name;
    private String description;
    private String fileId;
    private String fileType;
    private String fileName;
}
