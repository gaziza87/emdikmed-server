package kz.gaziza.emdikmed.controller.dmp;

import io.swagger.annotations.*;
import kz.gaziza.emdikmed.model.dmp.configuration.Medication;
import kz.gaziza.emdikmed.model.dmp.configuration.Protocol;
import kz.gaziza.emdikmed.service.dmp.configuration.impl.ProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("protocol")
@Api(tags = {"Protocol"}, description = "Protocol", authorizations = {@Authorization(value = "bearerAuth")})
public class ProtocolController {

    /******************************************************************************************************
     * Protocol Services
     */


    @Autowired
    private ProtocolService protocolService;

    @ApiOperation(value = "Получить объект/список протокол по параметрам с пагинацией", tags = {"MEDICATION"})
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

    @RequestMapping(value = "/read/protocol/pageable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<Protocol> readProtocolPageable(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        return protocolService.readPageable(allRequestParams);
    }

    @ApiOperation(value = "Получить список всех протокол", tags = {"PROTOCOL"})
    @RequestMapping(value = "read/protocol/iterable", method = RequestMethod.GET, produces = "application/json")
    public List<Protocol> getProtocolIterable() {
        return protocolService.readIterable();
    }

    @ApiOperation(value = "Получить протокол по заданному ID", tags = {"PROTOCOL"})
    @RequestMapping(value = "read/protocol/{id}", method = RequestMethod.GET, produces = "application/json")
    public Protocol getProtocol(@PathVariable(name = "id") String id) {
        return protocolService.readOne(id);
    }

    @ApiOperation(value = "Создать новую протокол", tags = {"PROTOCOL"})
    @RequestMapping(value = "create/protocol", method = RequestMethod.POST, produces = "application/json")
    public Protocol createProtocol(@RequestBody @Valid Protocol protocol) {
        return protocolService.create(protocol);
    }

    @ApiOperation(value = "Обновить существующую протокол", tags = {"PROTOCOL"})
    @RequestMapping(value = "update/protocol", method = RequestMethod.PUT, produces = "application/json")
    public Protocol updateProtocol(@RequestBody @Valid Protocol protocol) {
        return protocolService.update(protocol);
    }

    @ApiOperation(value = "Удалить протокол", tags = {"PROTOCOL"})
    @RequestMapping(value = "delete/protocol/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteMedication(@PathVariable(name = "id") String id) {
        protocolService.delete(id);
        return ("OK");
    }


}
