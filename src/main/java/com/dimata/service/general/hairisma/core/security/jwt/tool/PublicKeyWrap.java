package com.dimata.service.general.hairisma.core.security.jwt.tool;

import lombok.Data;

@Data
public class PublicKeyWrap {
    private final String id;
    private final String publicKey;
}
