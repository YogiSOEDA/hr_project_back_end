package com.dimata.service.general.hairisma.controller;

import com.dimata.service.general.hairisma.core.rest.CommonParam;
import com.dimata.service.general.hairisma.entity.Token;
import com.dimata.service.general.hairisma.model.body.TokenBody;
import com.dimata.service.general.hairisma.service.TokenHandler;
import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;

@Path("api/v1/maintainer/token")
public class TokenController {
    @Inject
    TokenHandler tokenHandler;

    @GET
    @Path("/{token_code}")
    public List<TokenBody> getToken(@PathParam("token_code") long id) {
        return tokenHandler.getToken(id);
    }

    @GET
    @Transactional
    public List<TokenBody> getAllToken(@Form CommonParam param) {
        return tokenHandler.getAllToken(param);
    }

    @PUT
    @Path("/update")
    @Transactional
    public Token updateToken(TokenBody body) {
        return tokenHandler.updateToken(body);
    }

    @POST
    @Path("/create")
    @Transactional
    public TokenBody createToken(TokenBody body) {
        return tokenHandler.createToken(body);
    }
}
