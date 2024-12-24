package com.github.erfan_davoodi_nasr_hw10_maktab117.util;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorProvider {
    private static ValidatorFactory validatorFactory;

    private ValidatorProvider() {
    }

    public static synchronized Validator getValidator() {
        if (validatorFactory == null) {
            validatorFactory = Validation.buildDefaultValidatorFactory();
        }

        return validatorFactory.getValidator();

    }
}
