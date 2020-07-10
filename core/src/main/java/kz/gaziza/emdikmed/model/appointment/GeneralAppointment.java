package kz.gaziza.emdikmed.model.appointment;

import kz.gaziza.emdikmed.model.BaseAuditable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "ais_general_appointment")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class GeneralAppointment extends BaseAuditable {
    @Id
    private String id;
    private String organizationId;
    private Map<String, AppointmentData> appointmentDataMap;
}
