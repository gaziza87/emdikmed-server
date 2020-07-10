package kz.gaziza.emdikmed.controller.devices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import kz.gaziza.emdikmed.model.device.DeviceStatConfig;
import kz.gaziza.emdikmed.service.device.IDeviceStatConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/device-stat-config")
@Api(tags = {"DeviceStatConfig"}, description = "Конфигурация статистики устройства", authorizations = {@Authorization(value = "bearerAuth")})
public class DeviceStatConfigController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceStatConfigController.class);

    @Autowired
    IDeviceStatConfigService deviceStatConfigService;

    @ApiOperation(value = "Получить список Конфигурация статистики устройства", tags = {"DeviceStatConfig"})
    @GetMapping("/all")
    public List<DeviceStatConfig> getAllDeviceStatConfig() {
        return deviceStatConfigService.readAll();
    }

    @ApiOperation(value = "Получить Конфигурация статистики устройства по ID", tags = {"DeviceStatConfig"})
    @GetMapping("/by/{id}")
    public DeviceStatConfig getDeviceStatConfigById(@PathVariable(name = "id") String id) {
        return  deviceStatConfigService.read(id);
    }

    @ApiOperation(value = "Создать Конфигурация статистики устройства", tags = {"DeviceStatConfig"})
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DeviceStatConfig setDeviceStatConfig(@Valid @RequestBody DeviceStatConfig deviceStatConfig) {
        return deviceStatConfigService.create(deviceStatConfig);
    }

    @ApiOperation(value = "Обновить Конфигурация статистики устройства", tags = {"DeviceStatConfig"})
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DeviceStatConfig updateDeviceStatConfig(@Valid @RequestBody DeviceStatConfig deviceStatConfig) {
        return deviceStatConfigService.update(deviceStatConfig);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Удаление Конфигурация статистики устройства по ID")
    public void delete(@ApiParam("ID приема") @PathVariable(name = "id") String id) throws InternalException {
        deviceStatConfigService.delete(id);
    }

}
