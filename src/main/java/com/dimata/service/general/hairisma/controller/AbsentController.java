package com.dimata.service.general.hairisma.controller;

import com.dimata.service.general.hairisma.core.rest.CommonParam;
import com.dimata.service.general.hairisma.entity.DataAbsent;
import com.dimata.service.general.hairisma.model.body.DataAbsentBody;
import com.dimata.service.general.hairisma.model.output.UserAbsentBody;
import com.dimata.service.general.hairisma.service.DataAbsentHandler;
import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;

@Path("api/v1/maintainer/absent")
public class AbsentController {
    @Inject
    DataAbsentHandler dataAbsentHandler;

    @GET
    @Path("/(id_absent)")
    public List<DataAbsentBody> getAbsent(@PathParam("id_absent") long id) {
        return dataAbsentHandler.getAbsent(id);
    }

    @GET
    @Transactional
    public List<UserAbsentBody> getAllAbsent(@Form CommonParam param) {
        return dataAbsentHandler.getAllAbsent(param);
    }

    @GET
    @Path("/in/user/{id_user}")
    @Transactional
    public List<DataAbsentBody> getCheckIn(@PathParam("id_user") long idUser) {
        return dataAbsentHandler.getCheckIn(idUser);
    }

    @GET
    @Path("/out/user/{id_user}")
    @Transactional
    public List<DataAbsentBody> getCheckOut(@PathParam("id_user") long idUser) {
        return dataAbsentHandler.getCheckOut(idUser);
    }

    @GET
    @Path("/user/{id_user}")
    @Transactional
    public List<DataAbsentBody> getAbsentUser(@PathParam("id_user") long idUser) {
        return dataAbsentHandler.getAbsentUser(idUser);
    }

    @GET
    @Path("/today/user/{id_user}")
    @Transactional
    public List<DataAbsentBody> getAbsentUserToday(@PathParam("id_user") long idUser) {
        return dataAbsentHandler.getAbsentUserToday(idUser);
    }

    @PUT
    @Path("/update")
    @Transactional
    public DataAbsent updateAbsent(DataAbsentBody body) {
        return dataAbsentHandler.updateAbsent(body);
    }

    @POST
    @Path("/create")
    @Transactional
    public DataAbsentBody createAbsent(DataAbsentBody body) {
        return dataAbsentHandler.createAbsent(body);
    }
}
