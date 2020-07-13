package by.clowns.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegionsValidator.class)
public @interface ValidRegions {

    String message() default "errors.region.name";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String[] allowedRegionNames();

}
