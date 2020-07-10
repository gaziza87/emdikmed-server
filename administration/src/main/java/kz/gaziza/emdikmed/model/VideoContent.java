package kz.gaziza.emdikmed.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "video_content")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class VideoContent extends BaseAuditable {
    @Id
    private String id;

    private String name;

    private String code;

    private List<String> diseaseIds;

    private String videoId;
}
