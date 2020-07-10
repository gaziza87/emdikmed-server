package kz.gaziza.emdikmed.model.dmp.visit;

import kz.gaziza.emdikmed.model.BaseAuditable;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "dmp_visit")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Visit extends BaseAuditable {
    @Id
    private String id;
    private Map<String, String> name;
    private Map<String, String> description;
    private String visitContentId;
    private String patientId;
    private String organizationId;
    private String doctorId;
    private Boolean checkFullFill;
    private VisitMeasurement measurement;
    private LocalDateTime visitDate;
    private String prevVisitId;
    private Integer counter;
}
