package kz.gaziza.emdikmed.model.appointment;

import kz.gaziza.emdikmed.model.BaseAuditable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "ais_appointment")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Appointment extends BaseAuditable {
    @Id
    private String id;
    private String organizationId;
    private String userId;
    private String doctorId;
    private String serviceId;
    private LocalDate appointDate;
    private AppointmentCondition appointCondition;
    private CellInfo cellInfo;
    private List<Payment> payment;
}
