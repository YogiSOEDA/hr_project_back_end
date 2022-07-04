package com.dimata.service.general.hairisma.core.util.jackson;

import com.dimata.service.general.hairisma.core.util.FormatUtil;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;

public class TimeDeserialize extends StdConverter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String s) {
        return FormatUtil.convertToLocalDate(s);
    }
}
