package com.dimata.service.general.hairisma.service;

import com.dimata.service.general.hairisma.core.rest.CommonParam;
import com.dimata.service.general.hairisma.entity.DataUser;
import com.dimata.service.general.hairisma.exception.DataNotFoundException;
import com.dimata.service.general.hairisma.exception.ExceptionCode;
import com.dimata.service.general.hairisma.model.body.DataUserBody;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DataUserHandler {
    public List<DataUserBody> getDataUser(long id) {
        return DataUser.findById(id)
                .stream()
                .map(DataUserBody::formDataUser)
                .collect(Collectors.toList());
    }

    public List<DataUserBody> getAllDataUser(CommonParam param) {
        return DataUser.getAllData(param)
                .stream()
                .map(DataUserBody::formDataUser)
                .collect(Collectors.toList());
    }

    public DataUser updateDataUser(DataUserBody body) {
        DataUser user = DataUser.findById(body.getIdDataUser());
        if (user == null) {
            throw new DataNotFoundException(ExceptionCode.DATA_NOT_FOUND);
        }
        body.updateDataUser(user);
        return user;
    }

    public DataUserBody createDataUser(DataUserBody body) {
        var dataUser = savaeNewDataUser(body);
        return DataUserBody.formDataUser(dataUser);
    }

    private DataUser savaeNewDataUser(DataUserBody body) {
        var dataUser = new DataUser();
        dataUser.id = body.getIdDataUser();
        dataUser.username = body.getUsername();
        dataUser.password = body.getPassword();
        dataUser.persist();
        return dataUser;
    }
}
