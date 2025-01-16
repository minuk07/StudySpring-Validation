package com.example.validation.validator;

import com.example.validation.annotation.PhoneNumber;
import com.example.validation.annotation.YearMonth;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {//어떠한 값이 들어오는지

    private String pattern;

    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
       //"2025-01-01T13:00:00" yyyy-MM-ddTHH:mm:ss
        //"202501"
        //size = 6

        var reValue = value+"01";
        var rePattern = pattern+"dd";

        try{
            LocalDate date = LocalDate.parse(value, DateTimeFormatter.ofPattern(value));
            System.out.println(date);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
