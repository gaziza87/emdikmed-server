package kz.gaziza.emdikmed.service.appointmentv2.impl;

import kz.gaziza.emdikmed.constant.State;
import kz.gaziza.emdikmed.model.appointmentv2.Appointment;
import kz.gaziza.emdikmed.repository.appointmentv2.AppointmentRepository;
import kz.gaziza.emdikmed.service.appointmentv2.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class AppointmentService implements IAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Page<Appointment> readPageable(Map<String, String> allRequestParams) {
        return null;
    }

    @Override
    public Page<Appointment> searchPageable(Map<String, String> allRequestParams) {
        return null;
    }


    @Override
    public Appointment read(String doctorId, String from, String due) {
        return appointmentRepository.getAppointments(doctorId, from, due, State.ACTIVE);
    }

    @Override
    public Appointment read(String id) {
        return appointmentRepository.findById(id).get();
    }

    @Override
    public Appointment create(Appointment appointment) {
        appointment.setFrom(appointment.getFrom().plusDays(1));
        appointment.setDue(appointment.getDue().plusDays(1));
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment update(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void delete(String id) {
        Appointment appointment = read(id);
        appointment.setState(State.DELETED);
        appointmentRepository.save(appointment);
    }
}
