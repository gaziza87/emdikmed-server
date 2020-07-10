package kz.gaziza.emdikmed.controller.dmp;

import io.swagger.annotations.*;
import kz.gaziza.emdikmed.model.dmp.configuration.Diagnostic;
import kz.gaziza.emdikmed.service.dmp.configuration.impl.DiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("diagnostic")
@Api(tags = {"Diagnostic"}, description = "Diagnostic", authorizations = {@Authorization(value = "bearerAuth")})
public class DiagnosticController {


    /******************************************************************************************************
     * Diagnostic Services
     */


    @Autowired
    private DiagnosticService diagnosticService;

    @ApiOperation(value = "Получить объект/список диагностики по параметрам с пагинацией", tags = {"DIAGNOSTICS"})
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
            @ApiResponse(code = 200, message = "Указывает, что объект или список объектов Diagnostics существуют и возвращает их.")
    })
    @RequestMapping(value = "/read/diagnostics/pageable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<Diagnostic> readDiagnosticsPageable(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
            return diagnosticService.readPageable(allRequestParams);
    }

    @ApiOperation(value = "Получить список диагностики", tags = {"DIAGNOSTIC"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Строка для поиска", paramType = "query"),
            @ApiImplicitParam(name = "sort", dataType = "string", value = "Поле для сортировки, которое будет использоваться вместе с order.", allowableValues = "id,code,nameKz,nameRu,nameEn", paramType = "query"),
            @ApiImplicitParam(name = "page", dataType = "int", value = "№ страницы с которой нужно отображать.", paramType = "query"),
            @ApiImplicitParam(name = "size", dataType = "int", value = "Кол-во записей на одной странице.", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Указывает, что пользователи существуют и возвращает.")
    })
    @RequestMapping(value = "/search/diagnostics/pageable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<Diagnostic> searchDiagnosticPageable(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        return diagnosticService.searchPageable(allRequestParams);
    }


    @ApiOperation(value = "Получить список всех диагностики", tags = {"DIAGNOSTIC"})
    @RequestMapping(value = "read/diagnostics/iterable", method = RequestMethod.GET, produces = "application/json")
    public List<Diagnostic> getDiagnosticsIterable() {
        return diagnosticService.readIterable();
    }

    @ApiOperation(value = "Получить список всех диагностики по категорий", tags = {"DIAGNOSTIC"})
    @RequestMapping(value = "read/diagnostics/iterable/byCategoryId/{filter}", method = RequestMethod.GET, produces = "application/json")
    public List<Diagnostic> getDiagnosticsIterableByCategoryId(@PathVariable(name = "filter") String categoryId) {
        return diagnosticService.readIterableByCategoryId(categoryId);
    }

    @ApiOperation(value = "Получить диагностику по заданному ID", tags = {"DIAGNOSTIC"})
    @RequestMapping(value = "read/diagnostics/{id}", method = RequestMethod.GET, produces = "application/json")
    public Diagnostic getDiagnostics(@PathVariable(name = "id") String id) {
        return diagnosticService.readOne(id);
    }

    @ApiOperation(value = "Получить список всех диагностических методов по категориям", tags = {"DIAGNOSTIC"})
    @RequestMapping(value = "read/categorized/diagnostics", method = RequestMethod.GET, produces = "application/json")
    public List<Object> getCategorizedDiagnostics() {
            return diagnosticService.readCategorizedDiagnostics();
    }

    @ApiOperation(value = "Создать новую диагностику", tags = {"DIAGNOSTICS"})
    @RequestMapping(value = "create/diagnostics", method = RequestMethod.POST, produces = "application/json")
    public Diagnostic createDiagnostics(@RequestBody @Valid Diagnostic diagnostic) {
        return diagnosticService.create(diagnostic);
    }

    @ApiOperation(value = "Обновить существующую диагностику", tags = {"DIAGNOSTICS"})
    @RequestMapping(value = "update/diagnostics", method = RequestMethod.PUT, produces = "application/json")
    public Diagnostic updateDiagnostics(@RequestBody @Valid Diagnostic diagnostic) {
        return diagnosticService.update(diagnostic);
    }

    @ApiOperation(value = "Удалить диагностику", tags = {"DIAGNOSTICS"})
    @RequestMapping(value = "delete/diagnostics/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteDiagnostics(@PathVariable(name = "id") String id) {
        diagnosticService.delete(id);
        return ("OK");

    }


}
