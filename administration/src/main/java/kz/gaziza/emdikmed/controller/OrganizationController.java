package kz.gaziza.emdikmed.controller;


import io.swagger.annotations.*;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import kz.gaziza.emdikmed.model.Organization;
import kz.gaziza.emdikmed.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/org")
@Api(tags = {"Organization"}, description = "Управление организациями", authorizations = {@Authorization(value = "bearerAuth")})
public class OrganizationController {
    @Autowired
    IOrganizationService organizationService;


    @ApiOperation(value = "Получить список данных с пагинацией", tags = {"Organization"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "searchString", paramType = "query"),
            @ApiImplicitParam(name = "page", dataType = "int", value = "№ страницы с которой нужно отображать.", paramType = "query"),
            @ApiImplicitParam(name = "size", dataType = "int", value = "Кол-во записей на одной странице.", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Указывает, что объекты существуют и возвращает.")
    })
    @RequestMapping(value = "/search/pageable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Organization> searchCustomPageable(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        return organizationService.search(allRequestParams);
    }

    @ApiOperation(value = "Получить список организация", tags = {"Organization"})
    @GetMapping("/all")
    public List<Organization> getAllOrganization() {
        return organizationService.getAll();
    }

    @ApiOperation(value = "Получить список организация by userAccountId", tags = {"Organization"})
    @GetMapping("/by/userAccountId/{userAccountId}")
    public List<Organization> getAllOrganizationByUserAccountId(@PathVariable(name = "userAccountId") String userAccountId) {
        return organizationService.getAllByUserAccountId(userAccountId);
    }

    @ApiOperation(value = "Получить организация по ID", tags = {"Organization"})
    @GetMapping("/by/{id}")
    public Organization getById(@PathVariable(name = "id") String id) {
        return  organizationService.get(id);
    }

    @ApiOperation(value = "Создать организация", tags = {"Organization"})
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Organization setOrganization(@Valid @RequestBody Organization organization) {
        return organizationService.create(organization);
    }

    @ApiOperation(value = "Обновить организация", tags = {"Organization"})
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Organization updateOrganization(@Valid @RequestBody Organization organization) {
        return organizationService.update(organization);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Удаление организация по id")
    public void delete(@ApiParam("ID пользователя") @PathVariable(name = "id") String id) throws InternalException {
        Organization organization = organizationService.get(id);
        organizationService.delete(organization);
    }
}
