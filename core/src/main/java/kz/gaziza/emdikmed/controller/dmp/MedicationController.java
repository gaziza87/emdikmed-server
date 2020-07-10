package kz.gaziza.emdikmed.controller.dmp;


import io.swagger.annotations.*;
import kz.gaziza.emdikmed.model.dmp.configuration.Laboratory;
import kz.gaziza.emdikmed.model.dmp.configuration.Medication;
import kz.gaziza.emdikmed.service.dmp.configuration.impl.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("medication")
@Api(tags = {"Medication"}, description = "Medication", authorizations = {@Authorization(value = "bearerAuth")})
public class MedicationController {

    /******************************************************************************************************
     * Medication
     */


    @Autowired
    private MedicationService medicationService;

    @ApiOperation(value = "Получить объект/список лекарств по параметрам с пагинацией", tags = {"MEDICATION"})
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
            @ApiResponse(code = 200, message = "Указывает, что объект или список объектов MEDICATION существуют и возвращает их.")
    })

    @RequestMapping(value = "/read/medication/pageable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<Medication> readMedicationPageable(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        return medicationService.readPageable(allRequestParams);
    }

    @ApiOperation(value = "Получить список лекарств", tags = {"MEDICATION"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Строка для поиска", paramType = "query"),
            @ApiImplicitParam(name = "sort", dataType = "string", value = "Поле для сортировки, которое будет использоваться вместе с order.", allowableValues = "id,code,nameKz,nameRu,nameEn", paramType = "query"),
            @ApiImplicitParam(name = "page", dataType = "int", value = "№ страницы с которой нужно отображать.", paramType = "query"),
            @ApiImplicitParam(name = "size", dataType = "int", value = "Кол-во записей на одной странице.", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Указывает, что пользователи существуют и возвращает.")
    })
    @RequestMapping(value = "/search/medication/pageable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<Medication> searchMedicationPageable(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        return medicationService.searchPageable(allRequestParams);
    }

    @ApiOperation(value = "Получить список всех лекарств", tags = {"MEDICATION"})
    @RequestMapping(value = "read/medication/iterable", method = RequestMethod.GET, produces = "application/json")
    public List<Medication> getMedicationIterable() {
        return medicationService.readIterable();
    }

    @ApiOperation(value = "Получить список всех лекарств по фильтру", tags = {"MEDICATION"})
    @RequestMapping(value = "read/medication/iterable/byCategoryId/{filter}", method = RequestMethod.GET, produces = "application/json")
    public List<Medication> getMedicationIterableByCategoryId(@PathVariable(name = "filter") String categoryId) {
        return medicationService.readIterableByCategoryId(categoryId);
    }

    @ApiOperation(value = "Получить лекарств по заданному ID", tags = {"MEDICATION"})
    @RequestMapping(value = "read/medication/{id}", method = RequestMethod.GET, produces = "application/json")
    public Medication getMedication(@PathVariable(name = "id") String id) {
        return medicationService.readOne(id);
    }

    @ApiOperation(value = "Создать новую лекарств", tags = {"MEDICATION"})
    @RequestMapping(value = "create/medication", method = RequestMethod.POST, produces = "application/json")
    public Medication createMedication(@RequestBody @Valid Medication medication) {
        return medicationService.create(medication);
    }

    @ApiOperation(value = "Обновить существующую лекарств", tags = {"MEDICATION"})
    @RequestMapping(value = "update/medication", method = RequestMethod.PUT, produces = "application/json")
    public Medication updateMedication(@RequestBody @Valid Medication medication) {
        return medicationService.update(medication);
    }

    @ApiOperation(value = "Удалить лекарству", tags = {"MEDICATION"})
    @RequestMapping(value = "delete/medication/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteMedication(@PathVariable(name = "id") String id) {
        medicationService.delete(id);
        return ("OK");
    }


}
