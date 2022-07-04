package com.dimata.service.general.hairisma.controller;

import com.dimata.service.general.hairisma.core.rest.CommonParam;
import com.dimata.service.general.hairisma.entity.DataUser;
import com.dimata.service.general.hairisma.model.body.DataUserBody;
import com.dimata.service.general.hairisma.service.DataUserHandler;
import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;

@Path("api/v1/maintainer/user")
public class UserController {
    @Inject
    DataUserHandler dataUserHandler;

    @GET
    @Path("/{id_user}")
    public List<DataUserBody> getDataUser(@PathParam("id_user") long id) {
        return dataUserHandler.getDataUser(id);
    }

    @GET
    @Transactional
    public List<DataUserBody> getAllDataUser(@Form CommonParam param) {
        return dataUserHandler.getAllDataUser(param);
    }

    @PUT
    @Path("/update")
    @Transactional
    public DataUser updatDataUser(DataUserBody body) {
        return dataUserHandler.updateDataUser(body);
    }

    @POST
    @Path("/create")
    @Transactional
    public DataUserBody createDataUser(DataUserBody body) {
        return dataUserHandler.createDataUser(body);
    }
}
