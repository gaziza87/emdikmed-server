package kz.gaziza.emdikmed.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patient_video_map")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PatientVideoContentMap extends BaseAuditable {
    @Id
    private String id;
    private String patientId;
    private VideoContent videoContent;
}
