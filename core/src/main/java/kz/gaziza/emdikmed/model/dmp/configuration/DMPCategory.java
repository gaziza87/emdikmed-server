package kz.gaziza.emdikmed.model.dmp.configuration;

import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.BaseAuditable;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "dmp_category")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DMPCategory extends BaseAuditable {
    @Id
    private String id;
    private String code;
    private Map<String, String> name;
    private Map<String, String> description;
    private String filter;
    private State state;
}
