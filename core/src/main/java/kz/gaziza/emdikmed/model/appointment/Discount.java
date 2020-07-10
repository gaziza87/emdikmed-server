package kz.gaziza.emdikmed.model.appointment;

import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.BaseAuditable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document(collection = "ais_discount")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Discount extends BaseAuditable {
    @Id
    private String id;
    private HashMap<String, String> name;
    private HashMap<String, String> description;
    private HashMap<String, String> condition;
    private String serviceId;
    private Double percentage;
    private String organizationId;
    private String startDate;
    private String endDate;
    private State state;
}
