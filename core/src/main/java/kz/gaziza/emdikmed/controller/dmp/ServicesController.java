package kz.gaziza.emdikmed.controller.dmp;


import io.swagger.annotations.*;
import kz.gaziza.emdikmed.model.dmp.configuration.Service;
import kz.gaziza.emdikmed.service.dmp.configuration.impl.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("services")
@Api(tags = {"Services"}, description = "Services", authorizations = {@Authorization(value = "bearerAuth")})
public class ServicesController {

    /******************************************************************************************************
     * Services Services
     */

    @Autowired
    private ServicesService servicesService;

    @ApiOperation(value = "Получить объект/список услуг по параметрам с пагинацией", tags = {"SERVICES"})
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
            @ApiResponse(code = 200, message = "Указывает, что объект или список объектов Protocol существуют и возвращает их.")
    })

    @RequestMapping(value = "/read/services/pageable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<Service> readProtocolPageable(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        return servicesService.readPageable(allRequestParams);
    }

    @ApiOperation(value = "Получить список всех услуг", tags = {"SERVICES"})
    @RequestMapping(value = "read/services/iterable", method = RequestMethod.GET, produces = "application/json")
    public List<Service> getServicesIterable() {
            return servicesService.readIterable();
    }

    @ApiOperation(value = "Получить список всех услуг по фильтру", tags = {"SERVICES"})
    @RequestMapping(value = "read/services/iterable/byCategoryId/{filter}", method = RequestMethod.GET, produces = "application/json")
    public List<Service> getServicesIterableByCategoryId(@PathVariable(name = "filter") String categoryId) {
            return servicesService.readIterableByCategoryId(categoryId);
    }

    @ApiOperation(value = "Получить услуги по заданному ID", tags = {"SERVICES"})
    @RequestMapping(value = "read/services/{id}", method = RequestMethod.GET, produces = "application/json")
    public Service getServices(@PathVariable(name = "id") String id) {
            return servicesService.readOne(id);
    }

    @ApiOperation(value = "Создать новую услугу", tags = {"SERVICES"})
    @RequestMapping(value = "create/services", method = RequestMethod.POST, produces = "application/json")
    public Service createServices(@RequestBody @Valid Service service) {
            return servicesService.create(service);
    }

    @ApiOperation(value = "Обновить существующую услугу", tags = {"SERVICES"})
    @RequestMapping(value = "update/services", method = RequestMethod.PUT, produces = "application/json")
    public Service updateServices(@RequestBody @Valid Service service) {
            return servicesService.update(service);
    }

    @ApiOperation(value = "Удалить услугу", tags = {"SERVICES"})
    @RequestMapping(value = "delete/services/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteServices(@PathVariable(name = "id") String id) {
            servicesService.delete(id);
            return ("OK");
    }


}
