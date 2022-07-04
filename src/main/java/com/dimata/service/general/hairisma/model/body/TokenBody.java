package com.dimata.service.general.hairisma.model.body;

import static com.dimata.service.general.hairisma.core.util.ManipulateUtil.changeItOrNot;

import com.dimata.service.general.hairisma.core.util.jackson.TimeDeserialize;
import com.dimata.service.general.hairisma.core.util.jackson.TimeSerialize;
import com.dimata.service.general.hairisma.entity.Token;
import com.dimata.service.general.hairisma.entity.enums.IsActive;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TokenBody {
    private Long idToken;
    private IsActive isActive;
    @JsonSerialize(converter = TimeSerialize.class)
    @JsonDeserialize(converter = TimeDeserialize.class)
    private LocalDateTime createAt;

    public static TokenBody formToken(Token ent) {
        var output = new TokenBody();
        output.setIdToken(ent.id);
        output.setIsActive(ent.isActive);
        output.setCreateAt(ent.createAt);
        return output;
    }

    public Token updateToken(Token token) {
        token.isActive = changeItOrNot(isActive, token.isActive);
        return token;
    }

}
