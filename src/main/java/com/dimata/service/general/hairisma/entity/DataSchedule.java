package com.dimata.service.general.hairisma.entity;

import com.dimata.service.general.hairisma.core.rest.CommonParam;
import com.dimata.service.general.hairisma.entity.enums.DayOfWeeks;
import com.dimata.service.general.hairisma.entity.enums.IsOff;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "data_schedule")
public class DataSchedule extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "dimata_id_gen")
    @Column(name = "id_schedule")
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_industry")
    public DataIndustry idIndustry;

    @Enumerated
    @Column(name = "day")
    public DayOfWeeks day;

    @Column(name = "time_in")
    public LocalTime timeIn;

    @Column(name = "time_out")
    public LocalTime timeOut;

    @Enumerated
    @Column(name = "isoff")
    public IsOff isOff;

    //---------------- ACTIVE RECORD

    public static Optional<DataSchedule> findById(long id) {
        return find("id = ?1", id).firstResultOptional();
    }

    public static List<DataSchedule> getAllData(CommonParam param) {
        return DataSchedule.findAll().page(param.getPage()).list();
    }
}
