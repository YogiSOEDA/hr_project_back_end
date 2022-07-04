package com.dimata.service.general.hairisma.entity;

import com.dimata.service.general.hairisma.core.rest.CommonParam;
import com.dimata.service.general.hairisma.entity.enums.IsActive;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "data_token")
public class Token extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "dimata_id_gen")
    @Column(name = "token_code")
    public Long id;

    @Enumerated
    @Column(name = "is_active")
    public IsActive isActive;

    @CreationTimestamp
    @Column(name = "created_at")
    public LocalDateTime createAt;

    //---------------- ACTIVE RECORD

    public static Optional<Token> findById(long id) {
        return find("id = ?1", id).firstResultOptional();
    }

    public static List<Token> getAllData(CommonParam param) {
        return Token.findAll().page(param.getPage()).list();
    }
}
