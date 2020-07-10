package kz.gaziza.emdikmed.model.dmp.configuration;

import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.BaseAuditable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "dmp_protocol")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Protocol extends BaseAuditable {
    @Id
    private String id;
    private String code;
    private Map<String, String> name;
    private Map<String, String> description;
    private String diseaseId;
    private String fileId;
    private State state;
}
