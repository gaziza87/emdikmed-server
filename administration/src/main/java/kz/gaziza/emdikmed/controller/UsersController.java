package kz.gaziza.emdikmed.controller;


import io.swagger.annotations.*;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import kz.gaziza.emdikmed.model.User;
import kz.gaziza.emdikmed.model.custom.UserDTO;
import kz.gaziza.emdikmed.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Api(tags = {"Users"}, description = "Управление пользователями")
public class UsersController {

    @Autowired
    IUsersService usersService;

    @ApiOperation(value = "Получить пользователя по ID", tags = {"Users"})
    @GetMapping("/patients")
    public List<User> readPatients() {
        return usersService.readIterable();
    }

    /********************************USERS*******************************************/

    @ApiOperation(value = "Получить пользователя по ID", tags = {"Users"})
    @GetMapping("/")
    public List<User> readIterable() {
        return usersService.readIterable();
    }

    @ApiOperation(value = "Получить список данных с пагинацией", tags = {"Users"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sort", dataType = "string", value = "Поле для сортировки, которое будет использоваться вместе с order.", paramType = "query"),
            @ApiImplicitParam(name = "sortDirection", dataType = "string", value = "Поле для сортировки, которое будет использоваться вместе с order.",  paramType = "query"),
            @ApiImplicitParam(name = "page", dataType = "int", value = "№ страницы с которой нужно отображать.", paramType = "query"),
            @ApiImplicitParam(name = "size", dataType = "int", value = "Кол-во записей на одной странице.", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Указывает, что объекты существуют и возвращает.")
    })
    @RequestMapping(value = "/read/byIdIn", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<UserDTO> readIterableByIdIn(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams, @RequestBody List<String> patientIds) {
        return usersService.readPageableByIdIn(patientIds, allRequestParams);
    }

    @ApiOperation(value = "Получить пользователя по ID", tags = {"Users"})
    @GetMapping("/by/{id}")
    public User getById(@PathVariable(name = "id") String id) {
        return  usersService.get(id);
    }

    @ApiOperation(value = "Создать пользователя", tags = {"Users"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public User createUser(@Valid @RequestBody User user) {
        System.out.println(user);
        return usersService.save(user);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Удаление пользователя по id")
    public void delete(@ApiParam("ID пользователя") @PathVariable(name = "id") String id) throws InternalException {
        User user = usersService.get(id);
        usersService.delete(user);
    }
}
