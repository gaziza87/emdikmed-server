package kz.gaziza.emdikmed.model.appointment;

import lombok.Data;

@Data
public class Payment {
    private Double payment;
    private boolean patientAgree;
    private String serviceId;
    private String discountId;
}
