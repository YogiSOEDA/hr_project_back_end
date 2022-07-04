package com.dimata.service.general.hairisma.model.output;

import com.dimata.service.general.hairisma.core.util.jackson.TimeDeserialize;
import com.dimata.service.general.hairisma.core.util.jackson.TimeSerialize;
import com.dimata.service.general.hairisma.entity.DataAbsent;
import com.dimata.service.general.hairisma.entity.enums.IsOff;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserAbsentBody {
    private Long idDataAbsent;
    private Long idUser;
    private String username;
    private Long idToken;
    @JsonSerialize(converter = TimeSerialize.class)
    @JsonDeserialize(converter = TimeDeserialize.class)
    private LocalDateTime usedAt;
    private IsOff isLate;

    public static UserAbsentBody formUserAbsent(DataAbsent ent) {
        var output = new UserAbsentBody();
        output.setIdDataAbsent(ent.id);
        output.setIdUser(ent.idUser.id);
        output.setIdToken(ent.idToken.id);
        output.setUsername(ent.idUser.username);
        output.setIsLate(ent.isLate);
        output.setUsedAt(ent.usedAt);
        return output;
    }
}
