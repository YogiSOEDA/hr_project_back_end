package com.dimata.service.general.hairisma.entity;

import com.dimata.service.general.hairisma.core.rest.CommonParam;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "data_user")
public class DataUser extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "dimata_id_gen")
    @Column(name = "id_user")
    public Long id;

    @Column(name = "username")
    public String username;

    @Column(name = "password")
    public String password;

    //---------------- ACTIVE RECORD

    public static Optional<DataUser> findById(long id) {
        return find("id = ?1", id).firstResultOptional();
    }

    public static List<DataUser> getAllData(CommonParam param) {
        return DataUser.findAll().page(param.getPage()).list();
    }
}
