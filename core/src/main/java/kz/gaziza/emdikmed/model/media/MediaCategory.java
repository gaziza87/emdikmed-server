package kz.gaziza.emdikmed.model.media;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.BaseAuditable;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "media_category")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MediaCategory extends BaseAuditable {
    @Id
    private String id;
    private Map<String, String> name;
    private Map<String, String> description;
    private String code;

    @JsonIgnore
    private State state;
}
