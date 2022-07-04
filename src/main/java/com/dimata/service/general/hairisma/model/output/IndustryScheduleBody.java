package com.dimata.service.general.hairisma.model.output;

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
public class IndustryScheduleBody {
    private Long idSchedule;
    private Long idIndustry;
    private String nameIndustry;
    private DayOfWeeks day;
    @JsonSerialize(converter = OnlyTimeSerialize.class)
    @JsonDeserialize(converter = OnlyTimeDeserialize.class)
    private LocalTime timeIn;
    @JsonSerialize(converter = OnlyTimeSerialize.class)
    @JsonDeserialize(converter = OnlyTimeDeserialize.class)
    private LocalTime timeOut;
    private IsOff isOff;

    public static IndustryScheduleBody formIndustrySchedule(DataSchedule ent) {
        var output = new IndustryScheduleBody();
        output.setIdSchedule(ent.id);
        output.setIdIndustry(ent.idIndustry.id);
        output.setNameIndustry(ent.idIndustry.nameIndustry);
        output.setDay(ent.day);
        output.setTimeIn(ent.timeIn);
        output.setTimeOut(ent.timeOut);
        output.setIsOff(ent.isOff);
        return output;
    }
}
