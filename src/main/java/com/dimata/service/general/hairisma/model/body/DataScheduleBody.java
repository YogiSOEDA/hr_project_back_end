package com.dimata.service.general.hairisma.model.body;

import static com.dimata.service.general.hairisma.core.util.ManipulateUtil.changeItOrNot;

import com.dimata.service.general.hairisma.core.util.jackson.OnlyTimeDeserialize;
import com.dimata.service.general.hairisma.core.util.jackson.OnlyTimeSerialize;
import com.dimata.service.general.hairisma.entity.DataSchedule;
import com.dimata.service.general.hairisma.entity.enums.DayOfWeeks;
import com.dimata.service.general.hairisma.entity.enums.IsOff;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalTime;

@Data
public class DataScheduleBody {
    private Long idSchedule;
    private Long idIndustry;
    private DayOfWeeks day;
    @JsonSerialize(converter = OnlyTimeSerialize.class)
    @JsonDeserialize(converter = OnlyTimeDeserialize.class)
    private LocalTime timeIn;
    @JsonSerialize(converter = OnlyTimeSerialize.class)
    @JsonDeserialize(converter = OnlyTimeDeserialize.class)
    private LocalTime timeOut;
    private IsOff isOff;

    public static DataScheduleBody formSchedule(DataSchedule ent) {
        var output = new DataScheduleBody();
        output.setIdSchedule(ent.id);
        output.setIdIndustry(ent.idIndustry.id);
        output.setDay(ent.day);
        output.setTimeIn(ent.timeIn);
        output.setTimeOut(ent.timeOut);
        output.setIsOff(ent.isOff);
        return output;
    }

    public DataSchedule updateSchedule(DataSchedule schedule) {
        schedule.idIndustry.id = changeItOrNot(idIndustry, schedule.idIndustry.id);
        schedule.day = changeItOrNot(day, schedule.day);
        schedule.timeIn = changeItOrNot(timeIn, schedule.timeIn);
        schedule.timeOut = changeItOrNot(timeOut, schedule.timeOut);
        schedule.isOff = changeItOrNot(isOff, schedule.isOff);
        return schedule;
    }
}
