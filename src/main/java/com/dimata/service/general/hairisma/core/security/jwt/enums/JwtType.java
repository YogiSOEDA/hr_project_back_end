package com.dimata.service.general.hairisma.core.security.jwt.enums;

public enum JwtType {
    ACCESS, REFRESH;

    public String getCodeName() {
        return toString().toLowerCase();
    }
}
