package kz.gaziza.emdikmed.model.appointment;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CellInfo {
    private String color;
    private String doctorCode;
    private String doctorId;
    private String userId;
    private String doctorFio;
    private String patientFio;

    private String time;
    private LocalDate date;
    private String room;
}
