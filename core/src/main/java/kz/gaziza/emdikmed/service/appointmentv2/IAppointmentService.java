package kz.gaziza.emdikmed.service.appointmentv2;

import kz.gaziza.emdikmed.model.appointmentv2.Appointment;
import org.springframework.data.domain.Page;
import java.util.Map;

public interface IAppointmentService {

    Page<Appointment> readPageable(Map<String, String> allRequestParams);
    Page<Appointment> searchPageable(Map<String, String> allRequestParams);
    Appointment read(String doctorId, String from, String due);
    Appointment read(String id);
    Appointment create(Appointment appointment);
    Appointment update(Appointment appointment);
    void delete(String id);

}
