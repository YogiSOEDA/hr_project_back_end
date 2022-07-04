package com.dimata.service.general.hairisma.controller;

import com.dimata.service.general.hairisma.core.rest.CommonParam;
import com.dimata.service.general.hairisma.entity.DataIndustry;
import com.dimata.service.general.hairisma.model.body.DataIndustryBody;
import com.dimata.service.general.hairisma.service.DataIndustryHandler;
import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;

@Path("api/v1/maintainer/industry")
public class IndustryController {
    @Inject
    DataIndustryHandler dataIndustryHandler;

    @GET
    @Path("/{id_industry}")
    public List<DataIndustryBody> getIndustry(@PathParam("id_industry") long id) {
        return dataIndustryHandler.getIndustry(id);
    }

    @GET
    @Transactional
    public List<DataIndustryBody> getAllIndustry(@Form CommonParam param) {
        return dataIndustryHandler.getAllIndustry(param);
    }

    @PUT
    @Path("/update")
    @Transactional
    public DataIndustry updateIndustry(DataIndustryBody body) {
        return dataIndustryHandler.updateIndustry(body);
    }

    @POST
    @Path("/create")
    @Transactional
    public DataIndustryBody createIndustry(DataIndustryBody body) {
        return dataIndustryHandler.createIndustry(body);
    }
}
