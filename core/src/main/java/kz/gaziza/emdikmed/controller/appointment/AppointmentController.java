package kz.gaziza.emdikmed.controller.appointment;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import kz.gaziza.emdikmed.model.appointmentv2.Appointment;
import kz.gaziza.emdikmed.service.appointmentv2.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ais/appointment")
@Api(tags = {"Appointment"}, description = "Прием", authorizations = {@Authorization(value = "bearerAuth")})
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;

    @ApiOperation(value = "Получить список приемов с пагинацией", tags = {"Appointment"})
    @GetMapping("/read/pageable")
    public Page<Appointment> readPageable(Map<String, String> allRequestParams) {
        return appointmentService.readPageable(allRequestParams);
    }

    @ApiOperation(value = "Поиск приемов с пагинацией", tags = {"Appointment"})
    @GetMapping("/search/pageable")
    public Page<Appointment> searchPageable(Map<String, String> allRequestParams) {
        return appointmentService.searchPageable(allRequestParams);
    }

    @ApiOperation(value = "Получить список приемов", tags = {"Appointment"})
    @GetMapping("/read/{doctorId}/{from}/{due}")
    public Appointment readAppointment(@PathVariable String doctorId,
                                          @PathVariable String from,
                                          @PathVariable String due) {
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME
        System.out.println(from);
        System.out.println(due);
        return appointmentService.read(doctorId, from, due);
    }

    @ApiOperation(value = "Получить прием по ID", tags = {"Appointment"})
    @GetMapping("/read/{id}")
    public Appointment read(@PathVariable String id) {
        return appointmentService.read(id);
    }

    @ApiOperation(value = "Создать прием", tags = {"Appointment"})
    @PostMapping("/create")
    public Appointment create(@RequestBody Appointment appointment) {
        return appointmentService.create(appointment);
    }

    @ApiOperation(value = "Обновить прием", tags = {"Appointment"})
    @PutMapping("/update")
    public Appointment read(Appointment appointment) {
        return appointmentService.update(appointment);
    }

    @ApiOperation(value = "Удалить прием по ID", tags = {"Appointment"})
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        appointmentService.delete(id);
    }



}
