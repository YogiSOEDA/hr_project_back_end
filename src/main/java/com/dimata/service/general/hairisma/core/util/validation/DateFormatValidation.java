package com.dimata.service.general.hairisma.core.util.validation;


import com.dimata.service.general.hairisma.core.util.CheckUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateFormatValidation implements ConstraintValidator<DateFormatVal, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return CheckUtil.isDateFormatValid(value);
    }
}
