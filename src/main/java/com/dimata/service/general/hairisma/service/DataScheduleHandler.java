package com.dimata.service.general.hairisma.service;

import com.dimata.service.general.hairisma.core.rest.CommonParam;
import com.dimata.service.general.hairisma.entity.DataIndustry;
import com.dimata.service.general.hairisma.entity.DataSchedule;
import com.dimata.service.general.hairisma.exception.DataNotFoundException;
import com.dimata.service.general.hairisma.exception.ExceptionCode;
import com.dimata.service.general.hairisma.model.body.DataScheduleBody;
import com.dimata.service.general.hairisma.model.output.IndustryScheduleBody;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DataScheduleHandler {
    public List<IndustryScheduleBody> getSchedule(long id) {
        return DataSchedule.findById(id)
                .stream()
                .map(IndustryScheduleBody::formIndustrySchedule)
                .collect(Collectors.toList());
    }

    public List<IndustryScheduleBody> getAllSchedule(CommonParam param) {
        return DataSchedule.getAllData(param)
                .stream()
                .map(IndustryScheduleBody::formIndustrySchedule)
                .collect(Collectors.toList());
    }

    public DataSchedule updateSchedule(DataScheduleBody body) {
        DataSchedule schedule = DataSchedule.findById(body.getIdSchedule());
        if (schedule == null) {
            throw new DataNotFoundException(ExceptionCode.DATA_NOT_FOUND);
        }
        body.updateSchedule(schedule);
        return schedule;
    }

    public DataScheduleBody createSchedule(DataScheduleBody body) {
        var industry = fetchIndustry(body.getIdIndustry());
        var schedule = saveNewSchedule(body, industry);
        return DataScheduleBody.formSchedule(schedule);
    }

    private DataIndustry fetchIndustry(long idDataIndustry) {
        return (DataIndustry) DataIndustry.findByIdOptional(idDataIndustry)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.DATA_NOT_FOUND, "idIndustry Not Found"));
    }

    private DataSchedule saveNewSchedule(DataScheduleBody body, DataIndustry industry) {
        var dataSchedule = new DataSchedule();
        dataSchedule.id = body.getIdSchedule();
        dataSchedule.idIndustry = industry;
        dataSchedule.day = body.getDay();
        dataSchedule.timeIn = body.getTimeIn();
        dataSchedule.timeOut = body.getTimeOut();
        dataSchedule.isOff = body.getIsOff();
        dataSchedule.persist();
        return dataSchedule;
    }
}
