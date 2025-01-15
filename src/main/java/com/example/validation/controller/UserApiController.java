package com.example.validation.controller;

import com.example.validation.model.Api;
import com.example.validation.model.UserRegisterRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @PostMapping("")
    public Api<UserRegisterRequest> register(
            @Valid      //요청이 들어올 때 자동으로 해당 클래스에 대해서 붙어있는 어노테이션을 기반으로 검증.
            @RequestBody
            Api<UserRegisterRequest> userRegisterRequest,

                                                      BindingResult bindingResult //valid가 실행됐을 때, 해당 결과를 담아줌.
    ){
        log.info("init : {}", userRegisterRequest);

//        if(bindingResult.hasErrors()){ //에러가 발생했다면
//            var errorMessageList = bindingResult.getFieldErrors().stream() //에러가난 필드를 가져옴.
//                    .map( it -> { //값을 변환
//                        var format = "%s : { %s } 은 %s"; //어떠한 필드가 어떠한 이유때문에 통과할수 없다는 메세지
//                        var message = String.format(format, it.getField(), it.getRejectedValue(), it.getDefaultMessage());
//                        return message;
//                    }).collect(Collectors.toList());
//
//            var error = Api.Error
//                    .builder()
//                    .errorMessage(errorMessageList) //Error에 errorMessage들 담아줌.
//                    .build();
//
//            var errorResponse = Api.builder()
//                    .resultCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
//                    .resultMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
//                    .error(error)
//                    .build();
//
//            return errorResponse;
//        }

        var body = userRegisterRequest.getData();
        Api<UserRegisterRequest> response = Api.<UserRegisterRequest>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.getReasonPhrase())
                .data(body).build();

        return response;
    }
}
