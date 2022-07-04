package com.dimata.service.general.hairisma.core.util.jackson;

import com.dimata.service.general.hairisma.core.util.FormatUtil;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalTime;

public class OnlyTimeSerialize extends StdConverter<LocalTime, String>{
    @Override
    public String convert(LocalTime time) {
        return FormatUtil.convertTimeToString(time);
    }
}
