package com.example.validation.model;


import com.example.validation.annotation.PhoneNumber;
import com.example.validation.annotation.YearMonth;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRegisterRequest {

    //@NotBlank
    private String name;

    private String nickName;

    @Size(min = 1, max = 12) //password 길익가 1~12 글자까지 가능. 문자열에서만 사용.
    @NotBlank
    private String password;

    @NotNull
    @Min(1) // 1살~100살까지 가능.
    @Max(100)
    private Integer age;

    @Email
    private String email;

    //@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "휴대폰 번호 양식에 맞지 않습니다.") //휴대폰번호는 별도의 어노테이션이 존재하지 않아 정규식 사용.
    @PhoneNumber
    private String phoneNumber;

    @FutureOrPresent //현재 또는 미래의 날짜 지정.
    private LocalDateTime registerAt;

    @YearMonth(pattern = "yyyy-MM")
    private String birthDayYearMonth;

    @AssertTrue(message = "name or nickName은 존재해야 합니다.") //is로 시작, 해당 리턴값이 true일때 실행.
    public boolean isNameCheck(){
        if(Objects.nonNull(name) &&!name.isBlank()){
            return true;
        }

        if(Objects.nonNull(nickName) && !nickName.isBlank()){
            return true;
        }

        return false;
    }
}
