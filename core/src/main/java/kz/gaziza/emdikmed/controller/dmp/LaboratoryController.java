package kz.gaziza.emdikmed.controller.dmp;


import io.swagger.annotations.*;
import kz.gaziza.emdikmed.model.dmp.configuration.Laboratory;
import kz.gaziza.emdikmed.service.dmp.configuration.impl.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("laboratory")
@Api(tags = {"Laboratory"}, description = "Laboratory", authorizations = {@Authorization(value = "bearerAuth")})
public class LaboratoryController {

    /******************************************************************************************************
     * Laboratory Services
     */


    @Autowired
    private LaboratoryService laboratoryService;

    @ApiOperation(value = "Получить объект/список лабораторий  по параметрам с пагинацией", tags = {"LABORATORY"})
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

    @RequestMapping(value = "/read/laboratory/pageable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<Laboratory> readLaboratoryPageable(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        return laboratoryService.readPageable(allRequestParams);
    }

    @ApiOperation(value = "Получить список лабораторий", tags = {"LABORATORY"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Строка для поиска", paramType = "query"),
            @ApiImplicitParam(name = "sort", dataType = "string", value = "Поле для сортировки, которое будет использоваться вместе с order.", allowableValues = "id,code,nameKz,nameRu,nameEn", paramType = "query"),
            @ApiImplicitParam(name = "page", dataType = "int", value = "№ страницы с которой нужно отображать.", paramType = "query"),
            @ApiImplicitParam(name = "size", dataType = "int", value = "Кол-во записей на одной странице.", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Указывает, что пользователи существуют и возвращает.")
    })
    @RequestMapping(value = "/search/laboratory/pageable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<Laboratory> searchLaboratoryPageable(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        return laboratoryService.searchPageable(allRequestParams);
    }

    @ApiOperation(value = "Получить список всех лабораторий", tags = {"LABORATORY"})
    @RequestMapping(value = "read/laboratory/iterable", method = RequestMethod.GET, produces = "application/json")
    public List<Laboratory> getLaboratoryIterable() {
        return laboratoryService.readIterable();
    }

    @ApiOperation(value = "Получить список всех лабораторий по фильтру", tags = {"LABORATORY"})
    @RequestMapping(value = "read/laboratory/iterable/byCategoryId/{filter}", method = RequestMethod.GET, produces = "application/json")
    public List<Laboratory> getLaboratoryIterableByCategoryId(@PathVariable(name = "filter") String categoryId) {
        return laboratoryService.readIterableByCategoryId(categoryId);
    }

    @ApiOperation(value = "Получить список всех лабораторий по указанному списку", tags = {"LABORATORY"})
    @RequestMapping(value = "read/laboratory/iterable/byIdIn/{selectedIds}", method = RequestMethod.GET, produces = "application/json")
    public List<Object> getCategorizedLaboratoryIterableByIdIn(@PathVariable List<String> selectedIds) {
        return laboratoryService.readCategorizedLaboratoriesByIdIn(selectedIds);
    }

    @ApiOperation(value = "Получить список всех лабораторий по категориям", tags = {"LABORATORY"})
    @RequestMapping(value = "read/categorized/laboratories", method = RequestMethod.GET, produces = "application/json")
    public List<Object> getCategorizedLaboratories() {
            return laboratoryService.readCategorizedLaboratories();
    }

    @ApiOperation(value = "Получить лабораторий по заданному ID", tags = {"LABORATORY"})
    @RequestMapping(value = "read/laboratory/{id}", method = RequestMethod.GET, produces = "application/json")
    public Laboratory getLaboratory(@PathVariable(name = "id") String id) {
        return laboratoryService.readOne(id);
    }

    @ApiOperation(value = "Создать новую лабораторий", tags = {"LABORATORY"})
    @RequestMapping(value = "create/laboratory", method = RequestMethod.POST, produces = "application/json")
    public Laboratory createLaboratory(@RequestBody @Valid Laboratory laboratory) {
        return laboratoryService.create(laboratory);
    }

    @ApiOperation(value = "Обновить существующую лабораторий", tags = {"LABORATORY"})
    @RequestMapping(value = "update/laboratory", method = RequestMethod.PUT, produces = "application/json")
    public Laboratory updateLaboratory(@RequestBody @Valid Laboratory laboratory) {
        return laboratoryService.update(laboratory);
    }

    @ApiOperation(value = "Удалить лабораторий", tags = {"LABORATORY"})
    @RequestMapping(value = "delete/laboratory/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteLaboratory(@PathVariable(name = "id") String id) {
        laboratoryService.delete(id);
        return ("OK");
    }
}
