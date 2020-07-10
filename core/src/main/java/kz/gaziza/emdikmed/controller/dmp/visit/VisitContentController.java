package kz.gaziza.emdikmed.controller.dmp.visit;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import kz.gaziza.emdikmed.model.dmp.visit.VisitContent;
import kz.gaziza.emdikmed.service.dmp.visit.impl.VisitContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("visit-content")
@Api(tags = {"Visit-content"}, description = "Visit-content", authorizations = {@Authorization(value = "bearerAuth")})
public class VisitContentController {

    /******************************************************************************************************
     * Visit Content Services
     */


    @Autowired
    private VisitContentService visitContentService;

    @ApiOperation(value = "Получить визит-контент по заданному ID", tags = {"VISIT-CONTENT"})
    @RequestMapping(value = "read/{id}", method = RequestMethod.GET, produces = "application/json")
    public VisitContent getVisitContent(@PathVariable(name = "id") String id) {
        return visitContentService.readOne(id);
    }

    @ApiOperation(value = "Создать новую визит-контент", tags = {"VISIT-CONTENT"})
    @RequestMapping(value = "create", method = RequestMethod.POST, produces = "application/json")
    public VisitContent createVisitContent(@RequestBody @Valid VisitContent visitContent) {
        return visitContentService.create(visitContent);
    }

    @ApiOperation(value = "Обновить существующую визит-контент", tags = {"VISIT-CONTENT"})
    @RequestMapping(value = "update", method = RequestMethod.PUT, produces = "application/json")
    public VisitContent updateVisitContent(@RequestBody @Valid VisitContent visitContent) {
        return visitContentService.update(visitContent);
    }

    @ApiOperation(value = "Обновить объект визит-контент", tags = {"VISIT-CONTENT"})
    @RequestMapping(value = "disease/selection", method = RequestMethod.PUT, produces = "application/json")
    public VisitContent updateVisitContentAfterDiseaseSelection(@RequestBody @Valid VisitContent visitContent) {
        return visitContentService.updateAfterDiseaseSelection(visitContent);

    }


}
