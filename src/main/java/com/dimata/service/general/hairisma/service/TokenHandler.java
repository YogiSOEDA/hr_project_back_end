package com.dimata.service.general.hairisma.service;

import com.dimata.service.general.hairisma.core.rest.CommonParam;
import com.dimata.service.general.hairisma.entity.Token;
import com.dimata.service.general.hairisma.exception.DataNotFoundException;
import com.dimata.service.general.hairisma.exception.ExceptionCode;
import com.dimata.service.general.hairisma.model.body.TokenBody;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TokenHandler {
    public List<TokenBody> getToken(long id) {
        return Token.findById(id)
                .stream()
                .map(TokenBody::formToken)
                .collect(Collectors.toList());
    }

    public List<TokenBody> getAllToken(CommonParam param) {
        return Token.getAllData(param)
                .stream()
                .map(TokenBody::formToken)
                .collect(Collectors.toList());
    }

    public Token updateToken(TokenBody body) {
        Token token = Token.findById(body.getIdToken());
        if (token == null) {
            throw new DataNotFoundException(ExceptionCode.DATA_NOT_FOUND);
        }
        body.updateToken(token);
        return token;
    }

    public TokenBody createToken(TokenBody body) {
        var token = saveNewToken(body);
        return TokenBody.formToken(token);
    }

    private Token saveNewToken(TokenBody body) {
        var token = new Token();
        token.id = body.getIdToken();
        token.isActive = body.getIsActive();
        token.createAt = body.getCreateAt();
        token.persist();
        return token;
    }
}
