package com.dimata.service.general.hairisma.controller;

import com.dimata.service.general.hairisma.core.rest.CommonParam;
import com.dimata.service.general.hairisma.entity.DataSchedule;
import com.dimata.service.general.hairisma.model.body.DataScheduleBody;
import com.dimata.service.general.hairisma.model.output.IndustryScheduleBody;
import com.dimata.service.general.hairisma.service.DataScheduleHandler;
import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;

@Path("api/v1/maintainer/schedule")
public class ScheduleController {
    @Inject
    DataScheduleHandler dataScheduleHandler;

    @GET
    @Path("/{id_schedule}")
    public List<IndustryScheduleBody> getSchedule(@PathParam("id_schedule") long id) {
        return dataScheduleHandler.getSchedule(id);
    }

    @GET
    @Transactional
    public List<IndustryScheduleBody> getAllSchedule(@Form CommonParam param) {
        return dataScheduleHandler.getAllSchedule(param);
    }

    @PUT
    @Path("/update")
    @Transactional
    public DataSchedule updateSchedule(DataScheduleBody body) {
        return dataScheduleHandler.updateSchedule(body);
    }

    @POST
    @Path("/create")
    @Transactional
    public DataScheduleBody createSchedule(DataScheduleBody body) {
        return dataScheduleHandler.createSchedule(body);
    }
}
