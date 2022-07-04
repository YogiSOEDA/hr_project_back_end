package com.dimata.service.general.hairisma.service;

import com.dimata.service.general.hairisma.core.rest.CommonParam;
import com.dimata.service.general.hairisma.entity.DataAbsent;
import com.dimata.service.general.hairisma.entity.DataSchedule;
import com.dimata.service.general.hairisma.entity.DataUser;
import com.dimata.service.general.hairisma.entity.Token;
import com.dimata.service.general.hairisma.exception.DataNotFoundException;
import com.dimata.service.general.hairisma.exception.ExceptionCode;
import com.dimata.service.general.hairisma.model.body.DataAbsentBody;
import com.dimata.service.general.hairisma.model.output.UserAbsentBody;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DataAbsentHandler {
    public List<DataAbsentBody> getAbsent(long id) {
        return DataAbsent.findById(id)
                .stream()
                .map(DataAbsentBody::formAbsent)
                .collect(Collectors.toList());
    }

    public List<UserAbsentBody> getAllAbsent(CommonParam param) {
        return DataAbsent.getAllData(param)
                .stream()
                .map(UserAbsentBody::formUserAbsent)
                .collect(Collectors.toList());
    }

    public List<DataAbsentBody> getCheckIn(long idUser) {
        return DataAbsent.getCheckIn(idUser)
                .stream()
                .map(DataAbsentBody::formAbsent)
                .collect(Collectors.toList());
    }

    public List<DataAbsentBody> getCheckOut(long idUser) {
        return DataAbsent.getCheckOut(idUser)
                .stream()
                .map(DataAbsentBody::formAbsent)
                .collect(Collectors.toList());
    }

    public List<DataAbsentBody> getAbsentUser(long idUser) {
        return DataAbsent.getDataAbsentUser(idUser)
                .stream()
                .map(DataAbsentBody::formAbsent)
                .collect(Collectors.toList());
    }

    public List<DataAbsentBody> getAbsentUserToday(long idUser) {
        return DataAbsent.getDataAbsentUserToday(idUser)
                .stream()
                .map(DataAbsentBody::formAbsent)
                .collect(Collectors.toList());
    }

    public DataAbsent updateAbsent(DataAbsentBody body) {
        DataAbsent absent = DataAbsent.findById(body.getIdDataAbsent());
        if (absent == null) {
            throw new DataNotFoundException(ExceptionCode.DATA_NOT_FOUND);
        }
        body.updateAbsent(absent);
        return absent;
    }

    public DataAbsentBody createAbsent(DataAbsentBody body) {
        var user = fetchUser(body.getIdUser());
        var schedule = fetchSchedule(body.getIdSchedule());
        var token = fetchToken(body.getIdToken());
        var absent = saveNewAbsent(body, user, schedule, token);
        return DataAbsentBody.formAbsent(absent);
    }

    private DataUser fetchUser(long idUser) {
        return (DataUser) DataUser.findByIdOptional(idUser)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.DATA_NOT_FOUND, "idUser Not Found"));
    }

    private DataSchedule fetchSchedule(long idSchedule) {
        return (DataSchedule) DataSchedule.findByIdOptional(idSchedule)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.DATA_NOT_FOUND, "idSchedule Not Found"));
    }

    private Token fetchToken(long idToken) {
        return (Token) Token.findByIdOptional(idToken)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.DATA_NOT_FOUND, "idToken Not Found"));
    }

    private DataAbsent saveNewAbsent(DataAbsentBody body, DataUser user, DataSchedule schedule, Token token) {
        var dataAbsent = new DataAbsent();
        dataAbsent.id = body.getIdDataAbsent();
        dataAbsent.idUser = user;
        dataAbsent.idSchedule = schedule;
        dataAbsent.idToken = token;
        dataAbsent.usedAt = body.getUsedAt();
        dataAbsent.status = body.getStatus();
        dataAbsent.isLate = body.getIsLate();
        dataAbsent.persist();
        return dataAbsent;
    }
}
