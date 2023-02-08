package fr.univcotedazur.multifidelity.interfaces;

import fr.univcotedazur.multifidelity.entities.ScheduleType;
import fr.univcotedazur.multifidelity.entities.Society;

import java.time.LocalTime;

public interface NotificationSender {

    void notifyConsumerNewSchedule(LocalTime schedule , Society society , ScheduleType scheduleType);

}
