package com.dimata.service.general.hairisma.entity;

import com.dimata.service.general.hairisma.core.rest.CommonParam;
import com.dimata.service.general.hairisma.entity.enums.DayOfWeeks;
import com.dimata.service.general.hairisma.entity.enums.WorkStatus;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "main_schedule")
public class MainSchedule extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "dimata_id_gen")
    @Column(name = "id_mainschedule")
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_schedule")
    public DataSchedule idSchedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_industry")
    public DataIndustry idIndustry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    public DataUser idUser;

    @Enumerated
    @Column(name = "status")
    public WorkStatus status;

    //---------------- ACTIVE RECORD

    public static Optional<MainSchedule> findById(long id) {
        return find("id = ?1", id).firstResultOptional();
    }

    public static List<MainSchedule> getAllData(CommonParam param) {
        return MainSchedule.findAll().page(param.getPage()).list();
    }

    public static List<MainSchedule> findUserSchedule(DayOfWeeks day, long id_user) {
        return find("idSchedule.day = ?1 and idUser.id = ?2", day, id_user).list();
    }
}
