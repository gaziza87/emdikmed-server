package kz.gaziza.emdikmed.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import kz.gaziza.emdikmed.model.PatientVideoContentMap;
import kz.gaziza.emdikmed.service.IPatientVideoContentMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/patient-video")
@Api(tags = {"Patient Video Content Map"}, description = "Управление организациями", authorizations = {@Authorization(value = "bearerAuth")})
public class PatientVideoContentMapController {

    @Autowired
    IPatientVideoContentMapService iPatientVideoContentMapService;

    @ApiOperation(value = "Получить список Patient Video Content Map", tags = {"Patient Video Content Map"})
    @GetMapping("/all/{patientId}")
    public List<PatientVideoContentMap> getAllPatientVideoContentMapByUserId(@PathVariable(name = "patientId") String patientId) {
        return iPatientVideoContentMapService.getAllByPatientId(patientId);
    }

    @ApiOperation(value = "Создать Patient Video Content Map", tags = {"Patient Video Content Map"})
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PatientVideoContentMap setPatientVideoContentMap(@Valid @RequestBody PatientVideoContentMap patientVideoContentMap) {
        return iPatientVideoContentMapService.create(patientVideoContentMap);
    }

}
