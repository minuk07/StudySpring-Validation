package com.example.validation.annotation;


import com.example.validation.validator.PhoneNumberValidator;
import com.example.validation.validator.YearMonthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {YearMonthValidator.class}) //어떠한 클래스로 검증을 할것인지
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME) //언제 실행시킬지
@NotBlank
public @interface YearMonth {

    String message() default "year month 양식에 맞지 않습니다 ex) 202501";

    String pattern() default "yyyyMM";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
