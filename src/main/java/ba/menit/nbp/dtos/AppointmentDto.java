package ba.menit.nbp.dtos;

import java.time.LocalDateTime;

public class AppointmentDto{

    public LocalDateTime startTime;

    public AppointmentDto() {

    }

    public LocalDateTime getStartTime(){
        return startTime;
    }

    public void setStartTime(LocalDateTime localDateTime){
        this.startTime = localDateTime;
    }
}