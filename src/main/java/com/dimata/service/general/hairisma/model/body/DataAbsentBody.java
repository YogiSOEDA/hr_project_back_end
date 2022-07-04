package com.dimata.service.general.hairisma.model.body;

import static com.dimata.service.general.hairisma.core.util.ManipulateUtil.changeItOrNot;

import com.dimata.service.general.hairisma.core.util.jackson.TimeDeserialize;
import com.dimata.service.general.hairisma.core.util.jackson.TimeSerialize;
import com.dimata.service.general.hairisma.entity.DataAbsent;
import com.dimata.service.general.hairisma.entity.enums.IsOff;
import com.dimata.service.general.hairisma.entity.enums.StatusAbsent;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DataAbsentBody {
    private Long idDataAbsent;
    private Long idUser;
    private Long idSchedule;
    private Long idToken;
    @JsonSerialize(converter = TimeSerialize.class)
    @JsonDeserialize(converter = TimeDeserialize.class)
    private LocalDateTime usedAt;
    private StatusAbsent status;
    private IsOff isLate;

    public static DataAbsentBody formAbsent(DataAbsent ent) {
        var output = new DataAbsentBody();
        output.setIdDataAbsent(ent.id);
        output.setIdUser(ent.idUser.id);
        output.setIdSchedule(ent.idSchedule.id);
        output.setIdToken(ent.idToken.id);
        output.setStatus(ent.status);
        output.setUsedAt(ent.usedAt);
        output.setIsLate(ent.isLate);
        return output;
    }

    public DataAbsent updateAbsent(DataAbsent absent) {
        absent.idUser.id = changeItOrNot(idUser, absent.idUser.id);
        absent.idSchedule.id = changeItOrNot(idSchedule, absent.idSchedule.id);
        absent.idToken.id = changeItOrNot(idToken, absent.idToken.id);
        absent.usedAt = changeItOrNot(usedAt, absent.usedAt);
        absent.status = changeItOrNot(status, absent.status);
        absent.isLate = changeItOrNot(isLate, absent.isLate);
        return absent;
    }
}
