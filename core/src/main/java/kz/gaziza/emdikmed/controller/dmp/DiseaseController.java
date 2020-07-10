package kz.gaziza.emdikmed.controller.dmp;

import io.swagger.annotations.*;
import kz.gaziza.emdikmed.model.dmp.configuration.Disease;
import kz.gaziza.emdikmed.service.dmp.configuration.IDiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("disease")
@Api(tags = {"Disease"}, description = "Disease", authorizations = {@Authorization(value = "bearerAuth")})
public class DiseaseController {

    /******************************************************************************************************
     * Disease Services
     */


    @Autowired
    private IDiseaseService iDiseaseService;

    @ApiOperation(value = "Получить объект/список болезнь по параметрам с пагинацией", tags = {"DISEASE"})
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
            @ApiResponse(code = 200, message = "Указывает, что объект или список объектов Diseases существуют и возвращает их.")
    })
    @RequestMapping(value = "/read/disease/pageable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<Disease> readDiseasePageable(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
            return iDiseaseService.readPageable(allRequestParams);
    }

    @ApiOperation(value = "Получить список заболеваний", tags = {"DISEASE"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Строка для поиска", paramType = "query"),
            @ApiImplicitParam(name = "sort", dataType = "string", value = "Поле для сортировки, которое будет использоваться вместе с order.", allowableValues = "id,code,nameKz,nameRu,nameEn", paramType = "query"),
            @ApiImplicitParam(name = "page", dataType = "int", value = "№ страницы с которой нужно отображать.", paramType = "query"),
            @ApiImplicitParam(name = "size", dataType = "int", value = "Кол-во записей на одной странице.", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Указывает, что пользователи существуют и возвращает.")
    })
    @RequestMapping(value = "/search/disease/pageable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<Disease> searchDiagnosticPageable(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        return iDiseaseService.searchPageable(allRequestParams);
    }

    @ApiOperation(value = "Получить список всех болезни", tags = {"DISEASE"})
    @RequestMapping(value = "read/disease/iterable", method = RequestMethod.GET, produces = "application/json")
    public List<Disease> getDiseaseIterable() {
        return iDiseaseService.readIterable();
    }

    @ApiOperation(value = "Получить список всех болезни по фильтру", tags = {"DISEASE"})
    @RequestMapping(value = "read/disease/iterable/byCategoryId/{filter}", method = RequestMethod.GET, produces = "application/json")
    public List<Disease> getDiseaseIterableByCategoryId(@PathVariable(name = "filter") String categoryId) {
        return iDiseaseService.readIterableByCategoryId(categoryId);
    }

    @ApiOperation(value = "Получить болезнь по заданному ID", tags = {"DISEASE"})
    @RequestMapping(value = "read/disease/{id}", method = RequestMethod.GET, produces = "application/json")
    public Disease getDisease(@PathVariable(name = "id") String id) {
        return iDiseaseService.readOne(id);
    }

    @ApiOperation(value = "Создать новую болезнь", tags = {"DISEASE"})
    @RequestMapping(value = "create/disease", method = RequestMethod.POST, produces = "application/json")
    public Disease createDisease(@RequestBody @Valid Disease disease) {
        return iDiseaseService.create(disease);
    }

    @ApiOperation(value = "Обновить существующую болезнь", tags = {"DISEASE"})
    @RequestMapping(value = "update/disease", method = RequestMethod.PUT, produces = "application/json")
    public Disease updateDisease(@RequestBody @Valid Disease disease) {
        return iDiseaseService.update(disease);
    }

    @ApiOperation(value = "Удалить болезнь", tags = {"DISEASE"})
    @RequestMapping(value = "delete/disease/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteDisease(@PathVariable(name = "id") String id) {
        iDiseaseService.delete(id);
        return ("OK");
    }

    @ApiOperation(value = "Получить список болезней по заданному списку ID", tags = {"DISEASE"})
    @RequestMapping(value = "read/disease/iterable/byIdIn/{ids}", method = RequestMethod.GET, produces = "application/json")
    public List<Disease> getDiseasesByIdIn(@PathVariable(name = "ids") List<String> ids) {
            return iDiseaseService.readIterableByIdIn(ids);
    }


}

