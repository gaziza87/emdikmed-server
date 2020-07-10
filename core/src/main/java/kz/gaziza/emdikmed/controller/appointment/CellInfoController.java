package kz.gaziza.emdikmed.controller.appointment;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import kz.gaziza.emdikmed.model.appointmentv2.CellInfo;
import kz.gaziza.emdikmed.service.appointmentv2.ICellInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ais/cell-info")
@Api(tags = {"Cell-info"}, description = "Cell Info", authorizations = {@Authorization(value = "bearerAuth")})
public class CellInfoController {

    @Autowired
    private ICellInfoService cellInfoService;

    @ApiOperation(value = "Получить список записей с пагинацией", tags = {"Cell-info"})
    @GetMapping("/read/pageable")
    public Page<CellInfo> readPageable(Map<String, String> allRequestParams) {
        return cellInfoService.readPageable(allRequestParams);
    }

    @ApiOperation(value = "Поиск записей с пагинацией", tags = {"Cell-info"})
    @GetMapping("/search/pageable")
    public Page<CellInfo> searchPageable(Map<String, String> allRequestParams) {
        return cellInfoService.searchPageable(allRequestParams);
    }

    @ApiOperation(value = "Получить список записей", tags = {"Cell-info"})
    @GetMapping("/read/iterable/{key}/{value}")
    public List<CellInfo> readIterable(@PathVariable String key, @PathVariable String value) {
        switch (key) {
            case "doctorId":
                return cellInfoService.readIterableByDoctorId(value);
            case "userId":
                return cellInfoService.readIterableByUserId(value);
            case "appointDateString":
                return cellInfoService.readIterableByDate(value);
            default:
                return null;
        }
    }

    @ApiOperation(value = "Получить список записей", tags = {"Cell-info"})
    @GetMapping("/read/doctors/appointments/{doctorId}/{date}")
    public List<CellInfo> readDoctorsAppointments(@PathVariable String doctorId, @PathVariable String date) {
        System.out.println(doctorId);
        System.out.println(date);
        return cellInfoService.readIterableByDoctorIdAndDate(doctorId, date);
    }

    @ApiOperation(value = "Получить записей по ID", tags = {"Cell-info"})
    @GetMapping("/read/{id}")
    public CellInfo read(@PathVariable String id) {
        return cellInfoService.read(id);
    }

    @ApiOperation(value = "Получить записей по ID", tags = {"Cell-info"})
    @GetMapping("/get/{id}")
    public CellInfo getById(@PathVariable String id) {
        return cellInfoService.getById(id);
    }

    @ApiOperation(value = "Создать ячейку записи", tags = {"Cell-info"})
    @PostMapping("/create")
    public CellInfo create(@RequestBody CellInfo cellInfo) {
        return cellInfoService.create(cellInfo);
    }

    @ApiOperation(value = "Обновить ячейку записи", tags = {"Cell-info"})
    @PutMapping("/update")
    public CellInfo read(@RequestBody CellInfo cellInfo) {
        return cellInfoService.update(cellInfo);
    }

    @ApiOperation(value = "Удалить ячейку записи по ID", tags = {"Cell-info"})
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        cellInfoService.delete(id);
    }


}
