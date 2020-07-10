package kz.gaziza.emdikmed.controller.dmp.visit;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import kz.gaziza.emdikmed.model.dmp.visit.Visit;
import kz.gaziza.emdikmed.service.dmp.visit.impl.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("visit")
@Api(tags = {"Visit"}, description = "Visit", authorizations = {@Authorization(value = "bearerAuth")})
public class VisitController {

    /******************************************************************************************************
     * Visit Services
     */


    @Autowired
    private VisitService visitService;

    @ApiOperation(value = "Получить список всех визитов", tags = {"VISIT"})
    @RequestMapping(value = "read/iterable", method = RequestMethod.GET, produces = "application/json")
    public List<Visit> getVisitIterable() {
            return visitService.readIterable();
    }

    @ApiOperation(value = "Получить список всех визитов", tags = {"VIZIT"})
    @RequestMapping(value = "read/visit/iterable/byPatientId/{patientId}", method = RequestMethod.GET, produces = "application/json")
    public List<Visit> getVisitIterableByPatientId(@PathVariable String patientId) {
        return visitService.readIterableByPatientId(patientId);
    }

    @ApiOperation(value = "Получить список всех визитов", tags = {"VIZIT"})
    @RequestMapping(value = "read/visit/iterable/byDoctorId/{doctorId}", method = RequestMethod.GET, produces = "application/json")
    public List<Visit> getVisitIterableByDoctorId(@PathVariable String doctorId) {
        return visitService.readIterableByDoctorId(doctorId);
    }

    @ApiOperation(value = "Получить визит по заданному ID", tags = {"VISIT"})
    @RequestMapping(value = "read/{id}", method = RequestMethod.GET, produces = "application/json")
    public Visit getVisit(@PathVariable(name = "id") String id) {
        return visitService.readOne(id);
    }

    @ApiOperation(value = "Создать новую визит", tags = {"VISIT"})
    @RequestMapping(value = "create", method = RequestMethod.POST, produces = "application/json")
    public Visit createVisit(@RequestBody @Valid Visit visit) {
        return visitService.create(visit);
    }

    @ApiOperation(value = "Обновить существующую визит", tags = {"VISIT"})
    @RequestMapping(value = "update", method = RequestMethod.PUT, produces = "application/json")
    public Visit updateVisit(@RequestBody @Valid Visit visit) {
        return visitService.update(visit);
    }


}
