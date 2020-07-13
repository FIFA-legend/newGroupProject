package by.clowns.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class RegionsValidator implements ConstraintValidator<ValidRegions, String> {

    private List<String> regionNames;

    @Override
    public void initialize(ValidRegions validRegions) {
        regionNames = Arrays.asList(validRegions.allowedRegionNames());
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return regionNames.contains(s);
    }
}
