package kz.gaziza.emdikmed.controller;

import io.swagger.annotations.*;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import kz.gaziza.emdikmed.model.Template;
import kz.gaziza.emdikmed.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/template")
@Api(tags = {"Template"}, description = "Управление template", authorizations = {@Authorization(value = "bearerAuth")})
public class TemplateController {

    @Autowired
    ITemplateService iTemplateService;


    @ApiOperation(value = "Получить список Template", tags = {"Template"})
    @GetMapping("/all")
    public List<Template> getAll() {
        return iTemplateService.getAll();
    }

    @ApiOperation(value = "Получить список данных с пагинацией", tags = {"Template"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "searchString", paramType = "query"),
            @ApiImplicitParam(name = "page", dataType = "int", value = "№ страницы с которой нужно отображать.", paramType = "query"),
            @ApiImplicitParam(name = "size", dataType = "int", value = "Кол-во записей на одной странице.", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Указывает, что объекты существуют и возвращает.")
    })
    @RequestMapping(value = "/search/pageable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Template> searchCustomPageable(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        return iTemplateService.searchTemplate(allRequestParams);
    }


    @ApiOperation(value = "Получить Template по id", tags = {"Template"})
    @GetMapping("/by/{id}")
    public Template getById(@PathVariable(name = "id") String id) {
        return iTemplateService.getById(id);
    }

    @ApiOperation(value = "Создать Template", tags = {"Template"})
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Template setTemplate(@Valid @RequestBody Template template) {
        return iTemplateService.save(template);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Удаление Template по id")
    public void delete(@ApiParam("ID Template") @PathVariable(name = "id") String id) throws InternalException {
        Template template = iTemplateService.getById(id);
        iTemplateService.delete(template);

    }
}
