package kz.gaziza.emdikmed.controller.devices;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import kz.gaziza.emdikmed.model.device.DeviceStat;
import kz.gaziza.emdikmed.service.device.IDeviceStatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/device-stat")
@Api(tags = {"DeviceStat"}, description = "Статистика устройства", authorizations = {@Authorization(value = "bearerAuth")})
public class DeviceStatController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceStatController.class);
    
    @Autowired
    IDeviceStatService deviceStatService;

    @ApiOperation(value = "Получить список Статистика устройства", tags = {"DeviceStat"})
    @GetMapping("/all")
    public List<DeviceStat> getAllDeviceStat() {
        return deviceStatService.readAll();
    }

    @ApiOperation(value = "Получить Статистика устройства по ID", tags = {"DeviceStat"})
    @GetMapping("/by/{id}")
    public DeviceStat getDeviceStatById(@PathVariable(name = "id") String id) {
        return  deviceStatService.get(id);
    }

    @ApiOperation(value = "Получить Статистика устройства по ID", tags = {"DeviceStat"})
    @GetMapping("/get/last/{userAccountId}")
    public DeviceStat getDeviceStatByUserId(@PathVariable(name = "userAccountId") String userAccountId) {
        return deviceStatService.getLastRecordByUserAccountId(userAccountId);
    }

    @ApiOperation(value = "Создать Статистика устройства", tags = {"DeviceStat"})
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DeviceStat setDeviceStat(@Valid @RequestBody DeviceStat deviceStat) {
        return deviceStatService.create(deviceStat);
    }

    @ApiOperation(value = "Обновить Статистика устройства", tags = {"DeviceStat"})
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DeviceStat updateDeviceStat(@Valid @RequestBody DeviceStat deviceStat) {
        return deviceStatService.update(deviceStat);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Удаление Статистика устройства по ID")
    public void delete(@ApiParam("ID приема") @PathVariable(name = "id") String id) throws InternalException {
        deviceStatService.delete(id);
    }
}
