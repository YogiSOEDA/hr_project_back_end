package com.dimata.service.general.hairisma.entity;

import com.dimata.service.general.hairisma.core.rest.CommonParam;
import com.dimata.service.general.hairisma.entity.enums.IsOff;
import com.dimata.service.general.hairisma.entity.enums.StatusAbsent;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "data_absent")
public class DataAbsent extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "dimata_id_gen")
    @Column(name = "id_absent")
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    public DataUser idUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_schedule")
    public DataSchedule idSchedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_token")
    public Token idToken;

    @Column(name = "used_at")
    public LocalDateTime usedAt;

    @Enumerated
    @Column(name = "status")
    public StatusAbsent status;

    @Enumerated
    @Column(name = "is_late")
    public IsOff isLate;

    //---------------- ACTIVE RECORD

    public static Optional<DataAbsent> findById(long id) {
        return find("id = ?1", id).firstResultOptional();
    }

    public static List<DataAbsent> getAllData(CommonParam param) {
        return DataAbsent.findAll().page(param.getPage()).list();
    }

    public static Optional<DataAbsent> getCheckIn(long idUser) {
        var a = LocalDate.now();
        var b = a.atStartOfDay();
        var c = LocalDateTime.now();

        var query = "select p " +
                "from DataAbsent p " +
                "where p.idUser.id = ?1 and " +
                "p.usedAt between ?2 and ?3 and " +
                "p.status = 0";
        return find(query, idUser, b, c).firstResultOptional();
    }

    public static Optional<DataAbsent> getCheckOut(long idUser) {
        var a = LocalDate.now();
        var b = a.atStartOfDay();
        var c = LocalDateTime.now();

        var query = "select p " +
                "from DataAbsent p " +
                "where p.idUser.id = ?1 and " +
                "p.usedAt between ?2 and ?3 and " +
                "p.status = 1";
        return find(query, idUser, b, c).firstResultOptional();
    }

    public static List<DataAbsent> getDataAbsentUser(long idUser) {
        return find("idUser.id = ?1", idUser).list();
    }

    public static List<DataAbsent> getDataAbsentUserToday(long idUser) {
        var a = LocalDate.now();
        var b = a.atStartOfDay();
        var c = LocalDateTime.now();

        var query = "select p " +
                "from DataAbsent p " +
                "where p.idUser.id = ?1 and " +
                "p.usedAt between ?2 and ?3";
        return find(query, b, c).list();
    }
}
