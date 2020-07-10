package kz.gaziza.emdikmed.model.appointmentv2;

import kz.gaziza.emdikmed.model.BaseAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "ais_cell_info")
@EqualsAndHashCode(callSuper = true)
public class CellInfo extends BaseAuditable {

    @Id
    private String id;
    private String userId;
    private String userFullName;
    private String doctorId;
    private String doctorCode;
    private String doctorFullName;
    private String doctorSpecialty;
    private LocalDate appointDate;
    private String appointDateString;
    private String day;
    private Integer time;
    private String timeString;
    private String room;
    private String cellText;
    private String color;
    private String visitId;
    private boolean visitFulfilled;
    private boolean active;

}
