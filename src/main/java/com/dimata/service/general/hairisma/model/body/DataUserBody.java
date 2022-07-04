package com.dimata.service.general.hairisma.model.body;

import static com.dimata.service.general.hairisma.core.util.ManipulateUtil.changeItOrNot;

import com.dimata.service.general.hairisma.entity.DataUser;
import lombok.Data;

@Data
public class DataUserBody {
    private Long idDataUser;
    private String username;
    private String password;

    public static DataUserBody formDataUser(DataUser ent) {
        var output = new DataUserBody();
        output.setIdDataUser(ent.id);
        output.setUsername(ent.username);
        output.setPassword(ent.password);
        return output;
    }

    public DataUser updateDataUser(DataUser user) {
        user.username = changeItOrNot(username, user.username);
        user.password = changeItOrNot(password, user.password);
        return user;
    }
}
