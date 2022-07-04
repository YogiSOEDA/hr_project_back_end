package com.dimata.service.general.hairisma.service;

import com.dimata.service.general.hairisma.core.rest.CommonParam;
import com.dimata.service.general.hairisma.entity.DataIndustry;
import com.dimata.service.general.hairisma.exception.DataNotFoundException;
import com.dimata.service.general.hairisma.exception.ExceptionCode;
import com.dimata.service.general.hairisma.model.body.DataIndustryBody;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DataIndustryHandler {
    public List<DataIndustryBody> getIndustry(long id) {
        return DataIndustry.findById(id)
                .stream()
                .map(DataIndustryBody::formIndustry)
                .collect(Collectors.toList());
    }

    public List<DataIndustryBody> getAllIndustry(CommonParam param) {
        return DataIndustry.getAllData(param)
                .stream()
                .map(DataIndustryBody::formIndustry)
                .collect(Collectors.toList());
    }

    public DataIndustry updateIndustry(DataIndustryBody body) {
        DataIndustry industry = DataIndustry.findById(body.getIdDataIndustry());
        if (industry == null) {
            throw new DataNotFoundException(ExceptionCode.DATA_NOT_FOUND);
        }
        body.updateIndustry(industry);
        return industry;
    }

    public DataIndustryBody createIndustry(DataIndustryBody body) {
        var industry = saveNewIndustry(body);
        return DataIndustryBody.formIndustry(industry);
    }

    private DataIndustry saveNewIndustry(DataIndustryBody body) {
        var dataIndustry = new DataIndustry();
        dataIndustry.id = body.getIdDataIndustry();
        dataIndustry.nameIndustry = body.getNameIndustry();
        dataIndustry.persist();
        return dataIndustry;
    }
}
