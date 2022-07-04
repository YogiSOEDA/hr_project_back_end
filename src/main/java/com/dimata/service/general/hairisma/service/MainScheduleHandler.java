package com.dimata.service.general.hairisma.service;

import com.dimata.service.general.hairisma.core.rest.CommonParam;
import com.dimata.service.general.hairisma.entity.DataIndustry;
import com.dimata.service.general.hairisma.entity.DataSchedule;
import com.dimata.service.general.hairisma.entity.DataUser;
import com.dimata.service.general.hairisma.entity.MainSchedule;
import com.dimata.service.general.hairisma.entity.enums.DayOfWeeks;
import com.dimata.service.general.hairisma.exception.DataNotFoundException;
import com.dimata.service.general.hairisma.exception.ExceptionCode;
import com.dimata.service.general.hairisma.model.body.MainScheduleBody;
import com.dimata.service.general.hairisma.model.output.UserScheduleBody;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MainScheduleHandler {
    public List<MainScheduleBody> getMainSchedule(long id) {
        return MainSchedule.findById(id)
                .stream()
                .map(MainScheduleBody::formUserSchedule)
                .collect(Collectors.toList());
    }

    public List<MainScheduleBody> getAllMainSchedule(CommonParam param) {
        return MainSchedule.getAllData(param)
                .stream()
                .map(MainScheduleBody::formUserSchedule)
                .collect(Collectors.toList());
    }

    public List<UserScheduleBody> getUserSchedule(DayOfWeeks day, long idUser) {
        return MainSchedule.findUserSchedule(day,idUser)
                .stream()
                .map(UserScheduleBody::formUserSchedule)
                .collect(Collectors.toList());
    }

    public MainSchedule updateMainSchedule(MainScheduleBody body) {
        MainSchedule schedule = MainSchedule.findById(body.getIdMainSchedule());
        if (schedule == null) {
            throw new DataNotFoundException(ExceptionCode.DATA_NOT_FOUND);
        }
        body.updateMainSchedule(schedule);
        return schedule;
    }

    public MainScheduleBody createMainSchedule(MainScheduleBody body) {
        var schedule = fetchSchedule(body.getIdSchedule());
        var industry = fetchIndustry(body.getIdIndustry());
        var user = fetchUser(body.getIdUser());
        var mainSchedule = saveNewMainSchedule(body, schedule, industry, user);
        return MainScheduleBody.formUserSchedule(mainSchedule);
    }

    private DataSchedule fetchSchedule(long idDataSchedule) {
        return (DataSchedule) DataSchedule.findByIdOptional(idDataSchedule)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.DATA_NOT_FOUND, "idSchedule Not Found"));
    }

    private DataIndustry fetchIndustry(long idDataIndustry) {
        return (DataIndustry) DataIndustry.findByIdOptional(idDataIndustry)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.DATA_NOT_FOUND, "idIndustry Not Found"));
    }

    private DataUser fetchUser(long idDataUser) {
        return (DataUser) DataUser.findByIdOptional(idDataUser)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.DATA_NOT_FOUND, "idUser Not Found"));
    }

    private MainSchedule saveNewMainSchedule(MainScheduleBody body, DataSchedule schedule, DataIndustry industry, DataUser user) {
        var mainSchedule = new MainSchedule();
        mainSchedule.id = body.getIdMainSchedule();
        mainSchedule.idSchedule = schedule;
        mainSchedule.idIndustry = industry;
        mainSchedule.idUser = user;
        mainSchedule.status = body.getStatus();
        mainSchedule.persist();
        return mainSchedule;
    }
}
