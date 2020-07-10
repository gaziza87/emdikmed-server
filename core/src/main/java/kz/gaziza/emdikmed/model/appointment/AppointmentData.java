package kz.gaziza.emdikmed.model.appointment;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class AppointmentData {

    private Map<String, RoomVariants> roomVariantsMap;

}
