package com.dimata.service.general.hairisma.model.body;

import static com.dimata.service.general.hairisma.core.util.ManipulateUtil.changeItOrNot;

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
public class MainScheduleBody {
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
    private Long idUser;
    private WorkStatus status;

    public static MainScheduleBody formMainSchedule(MainSchedule ent) {
        var output = new MainScheduleBody();
        output.setIdMainSchedule(ent.id);
        output.setIdSchedule(ent.idSchedule.id);
        output.setIdIndustry(ent.idIndustry.id);
        output.setIdUser(ent.idUser.id);
        output.setStatus(ent.status);
        return output;
    }

    public static MainScheduleBody formUserSchedule(MainSchedule ent) {
        var output = new MainScheduleBody();
        output.setIdMainSchedule(ent.id);
        output.setIdSchedule(ent.idSchedule.id);
        output.setDay(ent.idSchedule.day);
        output.setTimeIn(ent.idSchedule.timeIn);
        output.setTimeOut(ent.idSchedule.timeOut);
        output.setIdIndustry(ent.idIndustry.id);
        output.setIdUser(ent.idUser.id);
        output.setStatus(ent.status);
        return output;
    }

    public MainSchedule updateMainSchedule(MainSchedule mainSchedule) {
        mainSchedule.idSchedule.id = changeItOrNot(idSchedule, mainSchedule.idSchedule.id);
        mainSchedule.idIndustry.id = changeItOrNot(idIndustry, mainSchedule.idIndustry.id);
        mainSchedule.idUser.id = changeItOrNot(idUser, mainSchedule.idUser.id);
        mainSchedule.status = changeItOrNot(status, mainSchedule.status);
        return mainSchedule;
    }
}
