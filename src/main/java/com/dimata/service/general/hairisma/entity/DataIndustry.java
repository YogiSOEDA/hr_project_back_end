package com.dimata.service.general.hairisma.entity;

import com.dimata.service.general.hairisma.core.rest.CommonParam;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "data_industry")
public class DataIndustry extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "dimata_id_gen")
    @Column(name = "id_industry")
    public Long id;

    @Column(name = "name_industry")
    public String nameIndustry;

    //---------------- ACTIVE RECORD

    public static Optional<DataIndustry> findById(long id) {
        return find("id = ?1", id).firstResultOptional();
    }

    public static List<DataIndustry> getAllData(CommonParam param) {
        return DataIndustry.findAll().page(param.getPage()).list();
    }
}
