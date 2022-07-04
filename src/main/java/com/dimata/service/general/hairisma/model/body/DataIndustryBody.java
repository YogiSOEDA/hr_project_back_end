package com.dimata.service.general.hairisma.model.body;

import static com.dimata.service.general.hairisma.core.util.ManipulateUtil.changeItOrNot;

import com.dimata.service.general.hairisma.entity.DataIndustry;
import lombok.Data;

@Data
public class DataIndustryBody {
    private Long idDataIndustry;
    private String nameIndustry;

    public static DataIndustryBody formIndustry(DataIndustry ent) {
        var output = new DataIndustryBody();
        output.setIdDataIndustry(ent.id);
        output.setNameIndustry(ent.nameIndustry);
        return output;
    }

    public DataIndustry updateIndustry(DataIndustry industry) {
        industry.nameIndustry = changeItOrNot(nameIndustry, industry.nameIndustry);
        return industry;
    }
}
