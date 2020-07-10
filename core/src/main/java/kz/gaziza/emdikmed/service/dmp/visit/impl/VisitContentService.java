package kz.gaziza.emdikmed.service.dmp.visit.impl;

import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.dmp.configuration.*;
import kz.gaziza.emdikmed.model.dmp.visit.VisitContent;
import kz.gaziza.emdikmed.repository.dmp.configuration.*;
import kz.gaziza.emdikmed.repository.dmp.visit.VisitContentRepository;
import kz.gaziza.emdikmed.service.dmp.visit.IVisitContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VisitContentService implements IVisitContentService {

    @Autowired
    private VisitContentRepository visitContentRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private LaboratoryRepository laboratoryRepository;
    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private ProceduresAndInterventionsRepository proceduresAndInterventionsRepository;
    @Autowired
    private DiagnosticRepository diagnosticRepository;

    @Override
    public Page<VisitContent> readPageable(Map<String, String> allRequestParams) {
        return null;
    }

    @Override
    public Page<VisitContent> searchPageable(Map<String, String> allRequestParams) {
        return null;
    }

    @Override
    public VisitContent readOne(String id) {
        return visitContentRepository.findById(id).get();
    }

    @Override
    public VisitContent create(VisitContent visitContent) {
        return visitContentRepository.save(visitContent);
    }

    @Override
    public VisitContent update(VisitContent visitContent) {
        return visitContentRepository.save(visitContent);
    }

    @Override
    public VisitContent updateAfterDiseaseSelection(VisitContent visitContent) {

        List<Disease> diseases = diseaseRepository.findAllByIdIn(visitContent.getSelectedDiseaseIds());
        DiseaseData dmpData = visitContent.getDiseaseData();
        for (Disease disease: diseases) {
            DiseaseData template = disease.getTemplate();

            if (template != null) {

                if (visitContent.getWhichTemplatesAdded() == null) {
                    visitContent.setWhichTemplatesAdded(new HashMap<>());
                }

                if (!visitContent.getWhichTemplatesAdded().containsKey(disease.getId())) {
                    visitContent.getWhichTemplatesAdded().put(disease.getId(), false);
                }

                boolean copied = visitContent.getWhichTemplatesAdded().get(disease.getId());

                if (!copied) {
                    dmpData.setSubDannie(dmpData.getSubDannie() + "\n" + template.getSubDannie() + "\n");
                    dmpData.setAnamBol(dmpData.getAnamBol() + "\n" + template.getAnamBol() + "\n");
                    dmpData.setAnamZhiz(dmpData.getAnamZhiz() + "\n" + template.getAnamZhiz() + "\n");
                    dmpData.setDiagMethodsText(dmpData.getDiagMethodsText() + "\n" + template.getDiagMethodsText() + "\n");
                    dmpData.setDiagZakl(dmpData.getDiagZakl() + "\n" + template.getDiagZakl() + "\n");
                    dmpData.setInfoMat(dmpData.getInfoMat() + "\n" + template.getInfoMat() + "\n");
                    dmpData.setLabAnalysText(dmpData.getLabAnalysText() + "\n" + template.getLabAnalysText() + "\n");
                    dmpData.setLekNaznText(dmpData.getLekNaznText() + "\n" + template.getLekNaznText() + "\n");
                    dmpData.setProcAndInterText(dmpData.getProcAndInterText() + "\n" + template.getProcAndInterText() + "\n");
                    dmpData.setNote(dmpData.getNote() + "\n" + template.getNote() + "\n");
                    dmpData.setObjDannie(dmpData.getObjDannie() + "\n" + template.getObjDannie() + "\n");
                    dmpData.setSovNomadiet(dmpData.getSovNomadiet() + "\n" + template.getSovNomadiet() + "\n");
                    dmpData.setVrachRec(dmpData.getVrachRec() + "\n" + template.getVrachRec() + "\n");

                    if (template.getDiagMethods() != null && template.getDiagMethods().size() > 0) {
                        template.getDiagMethods().forEach(method -> {
                            if (!dmpData.getDiagMethods().contains(method)) {
                                dmpData.getDiagMethods().add(method);
                            }
                        });
                    }
                    if (template.getLabAnalys() != null && template.getLabAnalys().size() > 0) {
                        template.getLabAnalys().forEach(lab -> {
                            if (!dmpData.getLabAnalys().contains(lab)) {
                                dmpData.getLabAnalys().add(lab);
                            }
                        });
                    }
                    if (template.getLekNazn() != null && template.getLekNazn().size() > 0) {
                        template.getLekNazn().forEach(medicine -> {
                            if (!dmpData.getLekNazn().contains(medicine)) {
                                dmpData.getLekNazn().add(medicine);
                            }
                        });
                    }
                    if (template.getProcAndInter() != null && template.getProcAndInter().size() > 0) {
                        template.getProcAndInter().forEach(pai -> {
                            if (!dmpData.getProcAndInter().contains(pai)) {
                                dmpData.getProcAndInter().add(pai);
                            }
                        });
                    }

                    visitContent.getWhichTemplatesAdded().put(disease.getId(), true);
                }

            }
        }

        visitContent.setDiseaseData(dmpData);

        if (visitContent.getSelectedLaboratoryIds() == null) {
            visitContent.setSelectedLaboratoryIds(new ArrayList<>());
        }

        if (visitContent.getSelectedDiagnosticIds() == null) {
            visitContent.setSelectedDiagnosticIds(new ArrayList<>());
        }

        if (visitContent.getSelectedDiseaseIds() == null) {
            visitContent.setSelectedDiseaseIds(new ArrayList<>());
        }

        if (visitContent.getSelectedMedicineIds() == null) {
            visitContent.setSelectedMedicineIds(new ArrayList<>());
        }

        if (visitContent.getSelectedProceduresAndInterventionsIds() == null) {
            visitContent.setSelectedProceduresAndInterventionsIds(new ArrayList<>());
        }

        visitContent.getDiseaseData().getDiagMethods().forEach(diagnosticId -> {
            if (!visitContent.getSelectedDiagnosticIds().contains(diagnosticId)) {
                visitContent.getSelectedDiagnosticIds().add(diagnosticId);
            }
        });

        visitContent.getDiseaseData().getLabAnalys().forEach(labId -> {
            if (!visitContent.getSelectedLaboratoryIds().contains(labId)) {
                visitContent.getSelectedLaboratoryIds().add(labId);
            }
        });

        visitContent.getDiseaseData().getLekNazn().forEach(medicineId -> {
            if (!visitContent.getSelectedMedicineIds().contains(medicineId)) {
                visitContent.getSelectedMedicineIds().add(medicineId);
            }
        });

        visitContent.getDiseaseData().getProcAndInter().forEach(paiId -> {
            if (!visitContent.getSelectedProceduresAndInterventionsIds().contains(paiId)) {
                visitContent.getSelectedProceduresAndInterventionsIds().add(paiId);
            }
        });

        visitContent.getSelectedDiagnosticIds().forEach(diagnosticId -> {
            if (!visitContent.getDiseaseData().getDiagMethods().contains(diagnosticId)) {
                visitContent.getDiseaseData().getDiagMethods().add(diagnosticId);

                Diagnostic diagnostic = diagnosticRepository.getById(diagnosticId, State.ACTIVE);

                String diagMethodText = visitContent.getDiseaseData().getDiagMethodsText();
                if (diagnostic != null) {
                    String text = (diagMethodText != null ? diagMethodText : "") + "\n" + diagnostic.getName();
                    visitContent.getDiseaseData().setDiagMethodsText(text);
                }
            }
        });

        visitContent.getSelectedLaboratoryIds().forEach(labId -> {
            if (!visitContent.getDiseaseData().getLabAnalys().contains(labId)) {
                visitContent.getDiseaseData().getLabAnalys().add(labId);

                Laboratory laboratory = laboratoryRepository.getById(labId, State.ACTIVE);

                String labAnalysText = visitContent.getDiseaseData().getLabAnalysText();
                if (laboratory != null) {
                    String text = (labAnalysText != null ? labAnalysText : "") + "\n" + laboratory.getName();
                    visitContent.getDiseaseData().setLabAnalysText(text);
                }

            }
        });

        visitContent.getSelectedProceduresAndInterventionsIds().forEach(paiId -> {
            if (!visitContent.getDiseaseData().getProcAndInter().contains(paiId)) {
                visitContent.getDiseaseData().getProcAndInter().add(paiId);

                ProceduresAndInterventions proceduresAndInterventions = proceduresAndInterventionsRepository.getById(paiId, State.ACTIVE);

                String procAndInterText = visitContent.getDiseaseData().getProcAndInterText();
                if (proceduresAndInterventions != null) {
                    String text = (procAndInterText != null ? procAndInterText : "") + "\n" + proceduresAndInterventions.getName();
                    visitContent.getDiseaseData().setProcAndInterText(text);
                }
            }
        });

        visitContent.getSelectedMedicineIds().forEach(medId -> {
            if (!visitContent.getDiseaseData().getLekNazn().contains(medId)) {
                visitContent.getDiseaseData().getLekNazn().add(medId);

                Medication medicine = medicationRepository.getById(medId, State.ACTIVE);

                String lekNaznText = visitContent.getDiseaseData().getLekNaznText();
                if (medicine != null) {
                    String text = (lekNaznText != null ? lekNaznText : "") + "\n" + medicine.getName();
                    visitContent.getDiseaseData().setLekNaznText(text);
                }
            }
        });


        return visitContentRepository.save(visitContent);

    }


}
