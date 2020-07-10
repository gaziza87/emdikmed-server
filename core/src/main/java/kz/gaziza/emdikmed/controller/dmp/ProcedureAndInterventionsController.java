package kz.gaziza.emdikmed.controller.dmp;


import io.swagger.annotations.*;
import kz.gaziza.emdikmed.model.dmp.configuration.ProceduresAndInterventions;
import kz.gaziza.emdikmed.service.dmp.configuration.impl.ProcedureAndInterventionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("procedures-and-interventions")
@Api(tags = {"Procedures and Interventions Services"}, description = "Procedures and Interventions Services", authorizations = {@Authorization(value = "bearerAuth")})
public class ProcedureAndInterventionsController {

    /******************************************************************************************************
     * Procedures and Interventions Services
     */


    @Autowired
    private ProcedureAndInterventionsService procedureAndInterventionsService;


    @ApiOperation(value = "Получить объект/список процедуры и вмешательства по параметрам с пагинацией", tags = {"PROCEDURES_AND_INTERVENTIONS"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "string", value = "Возвращает объект с данным id", paramType = "query"),
            @ApiImplicitParam(name = "code", dataType = "string", value = "Возвращает список объектов с данным кодом", paramType = "query"),
            @ApiImplicitParam(name = "name", dataType = "string", value = "Возвращает список объектов с данным названием", paramType = "query"),
            @ApiImplicitParam(name = "description", dataType = "string", value = "Возвращает список объектов с данным описанием", paramType = "query"),
            @ApiImplicitParam(name = "categoryId", dataType = "string", value = "Возвращает список объектов с данным категорий", paramType = "query"),
            @ApiImplicitParam(name = "sort", dataType = "string", value = "Поле для сортировки, которое будет использоваться вместе с order.", allowableValues = "code", paramType = "query"),
            @ApiImplicitParam(name = "page", dataType = "int", value = "№ страницы с которой нужно отображать.", paramType = "query"),
            @ApiImplicitParam(name = "size", dataType = "int", value = "Кол-во записей на одной странице.", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Указывает, что объект или список объектов Laboratory существуют и возвращает их.")
    })

    @RequestMapping(value = "/read/procedures-and-interventions/pageable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<ProceduresAndInterventions> readProceduresAndInterventionsPageable(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        return procedureAndInterventionsService.readPageable(allRequestParams);
    }

    @ApiOperation(value = "Получить список процедур и вмещательство", tags = {"PROCEDURES_AND_INTERVENTIONS"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Строка для поиска", paramType = "query"),
            @ApiImplicitParam(name = "sort", dataType = "string", value = "Поле для сортировки, которое будет использоваться вместе с order.", allowableValues = "id,code,nameKz,nameRu,nameEn", paramType = "query"),
            @ApiImplicitParam(name = "page", dataType = "int", value = "№ страницы с которой нужно отображать.", paramType = "query"),
            @ApiImplicitParam(name = "size", dataType = "int", value = "Кол-во записей на одной странице.", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Указывает, что пользователи существуют и возвращает.")
    })
    @RequestMapping(value = "/search/procedures-and-interventions/pageable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<ProceduresAndInterventions> searchProceduresAndInterventionsPageable(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        return procedureAndInterventionsService.searchPageable(allRequestParams);
    }

    @ApiOperation(value = "Получить список всех процедур и вмешательств", tags = {"PROCEDURES_AND_INTERVENTIONS"})
    @RequestMapping(value = "read/procedures-and-interventions/iterable", method = RequestMethod.GET, produces = "application/json")
    public List<ProceduresAndInterventions> getProceduresAndInterventionsIterable() {
        return procedureAndInterventionsService.readIterable();
    }

    @ApiOperation(value = "Получить список всех процедур и вмешательств по фильтру", tags = {"PROCEDURES_AND_INTERVENTIONS"})
    @RequestMapping(value = "read/procedures-and-interventions/iterable/byCategoryId/{filter}", method = RequestMethod.GET, produces = "application/json")
    public List<ProceduresAndInterventions> getProceduresAndInterventionsIterableByCategoryId(@PathVariable(name = "filter") String categoryId) {
        return procedureAndInterventionsService.readIterableByCategoryId(categoryId);
    }

    @ApiOperation(value = "Получить процедур и вмешательств по заданному ID", tags = {"PROCEDURES_AND_INTERVENTIONS"})
    @RequestMapping(value = "read/procedures-and-interventions/{id}", method = RequestMethod.GET, produces = "application/json")
    public ProceduresAndInterventions getProceduresAndInterventions(@PathVariable(name = "id") String id) {
        return procedureAndInterventionsService.readOne(id);
    }

    @ApiOperation(value = "Получить список всех процедур и вмешательств по категориям", tags = {"PROCEDURES_AND_INTERVENTIONS"})
    @RequestMapping(value = "read/categorized/procedures-and-interventions", method = RequestMethod.GET, produces = "application/json")
    public List<Object> getCategorizedProceduresAndInterventions() {
        return procedureAndInterventionsService.readCategorizedList();
    }

    @ApiOperation(value = "Создать новую процедур и вмешательств", tags = {"PROCEDURES_AND_INTERVENTIONS"})
    @RequestMapping(value = "create/procedures-and-interventions", method = RequestMethod.POST, produces = "application/json")
    public ProceduresAndInterventions createProceduresAndInterventions(@RequestBody @Valid ProceduresAndInterventions proceduresAndInterventions) {
        return procedureAndInterventionsService.create(proceduresAndInterventions);
    }

    @ApiOperation(value = "Обновить существующую процедур и вмешательств", tags = {"PROCEDURES_AND_INTERVENTIONS"})
    @RequestMapping(value = "update/procedures-and-interventions", method = RequestMethod.PUT, produces = "application/json")
    public ProceduresAndInterventions updateProceduresAndInterventions(@RequestBody @Valid ProceduresAndInterventions proceduresAndInterventions) {
        return procedureAndInterventionsService.update(proceduresAndInterventions);
    }

    @ApiOperation(value = "Удалить процедур и вмешательств", tags = {"PROCEDURES_AND_INTERVENTIONS"})
    @RequestMapping(value = "delete/procedures-and-interventions/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteProceduresAndInterventions(@PathVariable(name = "id") String id) {
        procedureAndInterventionsService.delete(id);
        return ("OK");
    }


}
