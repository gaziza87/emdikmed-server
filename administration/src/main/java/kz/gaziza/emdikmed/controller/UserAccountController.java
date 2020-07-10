package kz.gaziza.emdikmed.controller;

import io.swagger.annotations.*;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import kz.gaziza.emdikmed.model.User;
import kz.gaziza.emdikmed.model.UserAccount;
import kz.gaziza.emdikmed.model.custom.UserDTO;
import kz.gaziza.emdikmed.model.custom.UserRoleMapDTO;
import kz.gaziza.emdikmed.service.IUserAccountService;
import kz.gaziza.emdikmed.service.IVideoContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user-account")
@Api(tags = {"User Account"}, description = "Управление аккаунтами", authorizations = {@Authorization(value = "bearerAuth")})
public class UserAccountController {

    @Autowired
    private IUserAccountService userAccountService;

    @Autowired
    private IVideoContentService iVideoContentService;

    @ApiOperation(value = "Получить список данных с пагинацией", tags = {"User Account"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", dataType = "string", value = "Возвращает объект с данным id", paramType = "query"),
            @ApiImplicitParam(name = "organizationId", dataType = "string", value = "Возвращает список объектов с данным ключом", paramType = "query"),
            @ApiImplicitParam(name = "sort", dataType = "string", value = "Поле для сортировки, которое будет использоваться вместе с order.", paramType = "query"),
            @ApiImplicitParam(name = "sortDirection", dataType = "string", value = "Поле для сортировки, которое будет использоваться вместе с order.",  paramType = "query"),
            @ApiImplicitParam(name = "page", dataType = "int", value = "№ страницы с которой нужно отображать.", paramType = "query"),
            @ApiImplicitParam(name = "size", dataType = "int", value = "Кол-во записей на одной странице.", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Указывает, что объекты существуют и возвращает.")
    })
    @RequestMapping(value = "/read/custom/pageable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<UserDTO> readCustomPageable(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
//        System.out.println(allRequestParams);
        System.out.println(userAccountService.readAllUsers(allRequestParams));
        return userAccountService.readAllUsers(allRequestParams);
    }

    @ApiOperation(value = "Получить список данных с пагинацией", tags = {"User Account"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "searchString", paramType = "query"),
            @ApiImplicitParam(name = "role", dataType = "string", value = "Возвращает объект с данным id", paramType = "query"),
            @ApiImplicitParam(name = "organizationId", dataType = "string", value = "Возвращает список объектов с данным ключом", paramType = "query"),
            @ApiImplicitParam(name = "searchString", dataType = "string", value = "Возвращает список объектов с данным ключом", paramType = "query"),
            @ApiImplicitParam(name = "sort", dataType = "string", value = "Поле для сортировки, которое будет использоваться вместе с order.", paramType = "query"),
            @ApiImplicitParam(name = "sortDirection", dataType = "string", value = "Поле для сортировки, которое будет использоваться вместе с order.",  paramType = "query"),
            @ApiImplicitParam(name = "page", dataType = "int", value = "№ страницы с которой нужно отображать.", paramType = "query"),
            @ApiImplicitParam(name = "size", dataType = "int", value = "Кол-во записей на одной странице.", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Указывает, что объекты существуют и возвращает.")
    })
    @RequestMapping(value = "/search/custom/pageable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<UserDTO> searchCustomPageable(@ApiParam(hidden = true) @RequestParam Map<String, String> allRequestParams) {
        System.out.println("allRequestParams");
        System.out.println(allRequestParams);
        return userAccountService.searchUsers(allRequestParams);
    }


    @ApiOperation(value = "Получить список аккаунтов", tags = {"User Account"})
    @GetMapping("/all")
    public List<UserAccount> getAllUserAccounts() {
        return userAccountService.readAll();
    }

    @ApiOperation(value = "Получить аккаунт по UserId", tags = {"User Account"})
    @GetMapping("/byId/{id}")
    public UserAccount getByUserAccountId(@PathVariable(name = "id") String id) {
        return userAccountService.getById(id);
    }

    @ApiOperation(value = "Получить аккаунт по UserId", tags = {"User Account"})
    @GetMapping("dto/byId/{id}")
    public UserDTO getDTOByUserAccountId(@PathVariable(name = "id") String id) {
        return userAccountService.getDTOById(id);
    }

    @ApiOperation(value = "Получить аккаунт по UserId", tags = {"User Account"})
    @GetMapping("/by/{userId}")
    public UserAccount getById(@PathVariable(name = "userId") String userId) {
        return userAccountService.getByUserId(userId);
    }

    @ApiOperation(value = "Создать аккаунтов", tags = {"User Account"})
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserAccount setUserAccount(@Valid @RequestBody UserAccount userAccount) {
        return userAccountService.create(userAccount);
    }

    @ApiOperation(value = "Создать аккаунтов", tags = {"User Account"})
    @RequestMapping(value = "/create/from/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserAccount setUserAccountFromUser(@Valid @RequestBody User user) {
        return userAccountService.create(user);
    }
    @ApiOperation(value = "Создать аккаунт", tags = {"User Account"})
    @RequestMapping(value = "/create/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDTO createPatient(@Valid @RequestBody UserRoleMapDTO userRoleMapDTO) {
        return userAccountService.createUser(userRoleMapDTO);
    }

    @ApiOperation(value = "Обновить аккаунт", tags = {"User Account"})
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserAccount updateUserAccount(@Valid @RequestBody UserAccount userAccount) {
        return userAccountService.update(userAccount);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Удаление аккаунт по id")
    public void delete(@ApiParam("ID пользователя") @PathVariable(name = "id") String id) throws InternalException {
        UserAccount userAccount = userAccountService.getByUserId(id);
        userAccountService.delete(userAccount);

    }
    @ApiOperation(value = "getImage", tags = {"Dashboard"}, notes = "getImage(imageId)")
    @GetMapping("/image/{imageId}")
    public ResponseEntity<?> getImage(@ApiParam(name = "imageId") @PathVariable(name = "imageId") String imageId,
                                      HttpServletResponse response) throws IOException {
            return iVideoContentService.downloadImage(imageId);
    }
}
