package kz.gaziza.emdikmed.repository.appointmentv2;

import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.appointmentv2.Appointment;
import kz.gaziza.emdikmed.repository.ResourceUtilRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends ResourceUtilRepository<Appointment, String> {

    @Query(value = "{organizationId: '?0', doctorId: '?1', from: {$gte: '?2'}, dueTo: {$lte: '?3'}, state: '?4'}")
    List<Appointment> getAppointments(String organizationId, String doctorId, LocalDate from, LocalDate dueTo, State state);

//    @Query(value = "{organizationId: '?0', doctorId: '?1', state: '?2'}")
//    List<Appointment> getAppointments(String organizationId, String doctorId, State state);

    @Query(value = "{doctorId: '?0', fromString: '?1', dueString: '?2', state: '?3'}")
    Appointment getAppointments(String doctorId, String from, String due, State state);

}
