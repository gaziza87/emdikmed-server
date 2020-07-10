package kz.gaziza.emdikmed.controller.dmp;

import io.swagger.annotations.*;
import kz.gaziza.emdikmed.model.dmp.configuration.DMPCategory;
import kz.gaziza.emdikmed.service.dmp.configuration.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("dmpCategory")
@Api(tags = {"DMPCategory"}, description = "DMP", authorizations = {@Authorization(value = "bearerAuth")})
public class DMPCategoryController {


    @Autowired
    private IDMPCategoryService dmpCategoryService;

    /******************************************************************************************************
     * DMP-CATEGORY Services
     */

    @ApiOperation(value = "Получить объект/список категорий по параметрам с пагинацией", tags = {"CATEGORY"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "string", value = "Возвращает объект с данным id", paramType = "query"),
            @ApiImplicitParam(name = "code", dataType = "string", value = "Возвращает список объектов с данным кодом", paramType = "query"),
            @ApiImplicitParam(name = "name", dataType = "string", value = "Возвращает список объектов с данным названием", paramType = "query"),
            @ApiImplicitParam(name = "description", dataType = "string", value = "Возвращает список объектов с данным описанием", paramType = "query"),
            @ApiImplicitParam(name = "filter", dataType = "string", value = "Возвращает список объектов с данным фильтром", paramType = "query"),
            @ApiImplicitParam(name = "sort", dataType = "string", value = "Поле для сортировки, которое будет использоваться вместе с order.", allowableValues = "code", paramType = "query"),
            @ApiImplicitParam(name = "page", dataType = "int", value = "№ страницы с которой нужно отображать.", paramType = "query"),
            @ApiImplicitParam(name = "size", dataType = "int", value = "Кол-во записей на одной странице.", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Указывает, что объект или список объектов CATEGORY существуют и возвращает их.")
    })
    @RequestMapping(value = "/read/category/pageable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<DMPCategory> readCategoryPageable(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        return dmpCategoryService.readPageable(allRequestParams);
    }

    @ApiOperation(value = "Получить список категорий", tags = {"CATEGORY"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Строка для поиска", paramType = "query"),
            @ApiImplicitParam(name = "sort", dataType = "string", value = "Поле для сортировки, которое будет использоваться вместе с order.", allowableValues = "id,code,nameKz,nameRu,nameEn", paramType = "query"),
            @ApiImplicitParam(name = "page", dataType = "int", value = "№ страницы с которой нужно отображать.", paramType = "query"),
            @ApiImplicitParam(name = "size", dataType = "int", value = "Кол-во записей на одной странице.", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Указывает, что пользователи существуют и возвращает.")
    })
    @RequestMapping(value = "/search/category/pageable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<DMPCategory> searchCategoryPageable(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
            return dmpCategoryService.searchPageable(allRequestParams);
    }

    @ApiOperation(value = "Получить список всех категорий", tags = {"DMP-CATEGORY"})
    @RequestMapping(value = "read/category/iterable", method = RequestMethod.GET, produces = "application/json")
    public List<DMPCategory> getCategoryIterable() {
        return dmpCategoryService.readIterable();
    }

    @ApiOperation(value = "Получить список всех категорий по фильтру", tags = {"DMP-CATEGORY"})
    @RequestMapping(value = "read/category/iterable/byFilter/{filter}", method = RequestMethod.GET, produces = "application/json")
    public List<DMPCategory> getCategoryIterableByFilter(@PathVariable(name = "filter") String filter) {
        return dmpCategoryService.readIterableByFilter(filter);
    }

    @ApiOperation(value = "Получить категорию по заданному ID", tags = {"DMP-CATEGORY"})
    @RequestMapping(value = "read/category/{id}", method = RequestMethod.GET, produces = "application/json")
    public DMPCategory getCategory(@PathVariable(name = "id") String id) {
        return dmpCategoryService.readOne(id);
    }

    @ApiOperation(value = "Создать новую категорию", tags = {"DMP-CATEGORY"})
    @RequestMapping(value = "create/category", method = RequestMethod.POST, produces = "application/json")
    public DMPCategory createCategory(@RequestBody @Valid DMPCategory dmpCategory) {
        return dmpCategoryService.create(dmpCategory);
    }

    @ApiOperation(value = "Обновить существующую категорию", tags = {"DMP-CATEGORY"})
    @RequestMapping(value = "update/category", method = RequestMethod.PUT, produces = "application/json")
    public DMPCategory updateCategory(@RequestBody @Valid DMPCategory dmpCategory) {
        return dmpCategoryService.update(dmpCategory);
    }

    @ApiOperation(value = "Удалить категорию", tags = {"DMP-CATEGORY"})
    @RequestMapping(value = "delete/category/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteCategory(@PathVariable(name = "id") String id) {
        dmpCategoryService.delete(id);
        return ("OK");
    }


}
