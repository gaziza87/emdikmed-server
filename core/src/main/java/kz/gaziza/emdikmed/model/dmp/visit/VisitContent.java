package kz.gaziza.emdikmed.model.dmp.visit;

import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.BaseAuditable;
import kz.gaziza.emdikmed.model.dmp.configuration.DiseaseData;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Document(collection = "dmp_visit_content")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VisitContent extends BaseAuditable {
    @Id
    private String id;
    private DiseaseData diseaseData;
    private Map<String, Boolean> whichTemplatesAdded;
    private LocalDateTime nextVisitDate;
    private boolean checkFullFill;
    private List<String> selectedDiseaseIds;
    private List<String> selectedDiagnosticIds;
    private List<String> selectedLaboratoryIds;
    private List<String> selectedMedicineIds;
    private List<String> selectedMediaContentIds;
    private List<String> selectedProceduresAndInterventionsIds;
    private State state;
}
