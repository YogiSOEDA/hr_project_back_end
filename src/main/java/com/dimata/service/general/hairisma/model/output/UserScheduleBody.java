package com.dimata.service.general.hairisma.model.output;

import com.dimata.service.general.hairisma.core.util.jackson.OnlyTimeDeserialize;
import com.dimata.service.general.hairisma.core.util.jackson.OnlyTimeSerialize;
import com.dimata.service.general.hairisma.entity.MainSchedule;
import com.dimata.service.general.hairisma.entity.enums.DayOfWeeks;
import com.dimata.service.general.hairisma.entity.enums.WorkStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalTime;

@Data
public class UserScheduleBody {
    private Long idMainSchedule;
    private Long idSchedule;
    private DayOfWeeks day;
    @JsonSerialize(converter = OnlyTimeSerialize.class)
    @JsonDeserialize(converter = OnlyTimeDeserialize.class)
    private LocalTime timeIn;
    @JsonSerialize(converter = OnlyTimeSerialize.class)
    @JsonDeserialize(converter = OnlyTimeDeserialize.class)
    private LocalTime timeOut;
    private Long idIndustry;
    private String nameIndustry;
    private Long idUser;
    private String username;
    private WorkStatus status;

    public static UserScheduleBody formUserSchedule(MainSchedule ent) {
        var output = new UserScheduleBody();
        output.setIdMainSchedule(ent.id);
        output.setIdSchedule(ent.idSchedule.id);
        output.setDay(ent.idSchedule.day);
        output.setTimeIn(ent.idSchedule.timeIn);
        output.setTimeOut(ent.idSchedule.timeOut);
        output.setIdIndustry(ent.idIndustry.id);
        output.setNameIndustry(ent.idIndustry.nameIndustry);
        output.setIdUser(ent.idUser.id);
        output.setUsername(ent.idUser.username);
        output.setStatus(ent.status);
        return output;
    }
}
