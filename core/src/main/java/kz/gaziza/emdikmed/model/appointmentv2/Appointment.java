package kz.gaziza.emdikmed.model.appointmentv2;

import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.BaseAuditable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Document(collection = "ais_appointment")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Appointment extends BaseAuditable {

    @Id
    private String id;
    private String organizationId;
    private String doctorId;
    private LocalDate from;
    private LocalDate due;
    private String fromString;
    private String dueString;
    private Map<String, List<CellInfo>> cellInfoMap;
    private State state;

}
