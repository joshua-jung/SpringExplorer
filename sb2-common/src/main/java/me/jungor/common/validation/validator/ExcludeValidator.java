package me.jungor.common.validation.validator;

import me.jungor.common.validation.constraints.Exclude;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by joshua on 2018/6/6.
 */
public class ExcludeValidator implements ConstraintValidator<Exclude, String> {

    private String[] letters;

    @Override
    public void initialize(Exclude exclude) {
        this.letters = exclude.value();
    }

    @Override
    public boolean isValid(String letter, ConstraintValidatorContext constraintValidatorContext) {
        if(letter != null) {
            for(String s : this.letters) {
                if(letter.equals(s)) {
                    return false;
                }
            }
        }
        return true;
    }
}
