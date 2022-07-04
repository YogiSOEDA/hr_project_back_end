package com.dimata.service.general.hairisma.controller;

import com.dimata.service.general.hairisma.core.rest.CommonParam;
import com.dimata.service.general.hairisma.entity.MainSchedule;
import com.dimata.service.general.hairisma.entity.enums.DayOfWeeks;
import com.dimata.service.general.hairisma.model.body.MainScheduleBody;
import com.dimata.service.general.hairisma.model.output.UserScheduleBody;
import com.dimata.service.general.hairisma.service.MainScheduleHandler;
import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;

@Path("api/v1/maintainer/main_schedule")
public class MainScheduleController {
    @Inject
    MainScheduleHandler mainScheduleHandler;

    @GET
    @Path("/{id_mainschedule}")
    public List<MainScheduleBody> getMainSchedule(@PathParam("id_mainschedule") long id) {
        return mainScheduleHandler.getMainSchedule(id);
    }

    @GET
    @Transactional
    public List<MainScheduleBody> getAllMainSchedule(@Form CommonParam param) {
        return mainScheduleHandler.getAllMainSchedule(param);
    }

    @GET
    @Path("/day/{day}/user/{id_user}")
    @Transactional
    public List<UserScheduleBody> getUserSchedule(@PathParam("day") DayOfWeeks day, @PathParam("id_user") long idUser) {
        return mainScheduleHandler.getUserSchedule(day, idUser);
    }

    @PUT
    @Path("/update")
    @Transactional
    public MainSchedule updateMainSchedule(MainScheduleBody body) {
        return mainScheduleHandler.updateMainSchedule(body);
    }

    @POST
    @Path("/create")
    @Transactional
    public MainScheduleBody createMainSchedule(MainScheduleBody body) {
        return mainScheduleHandler.createMainSchedule(body);
    }
}
