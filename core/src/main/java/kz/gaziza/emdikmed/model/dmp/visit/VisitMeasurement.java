package kz.gaziza.emdikmed.model.dmp.visit;

import lombok.Data;

import java.util.List;

@Data
public class VisitMeasurement {
    private Double height;
    private Double weight;
    private Double bmi;
    private List<Integer> adSys;
    private List<Integer> adDys;
    private String ad;
}
