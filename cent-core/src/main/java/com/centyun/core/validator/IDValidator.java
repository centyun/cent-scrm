package com.centyun.core.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 身份证号码的验证器
 * @author yinww
 *
 */

public class IDValidator implements ConstraintValidator<IDConstraint, String> {
    private boolean required;
    
    @Override
    public void initialize(IDConstraint idConstraint) {
        this.required = idConstraint.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.trim().length() == 0) {
            return !required;
        }
        
        String regIdcard = "^\\d{6}(19|20)\\d{2}((1[0-2])|0\\d)([0-2]\\d|30|31)\\d{3}[\\d|X|x]$";
        return value.matches(regIdcard);
    }

}
