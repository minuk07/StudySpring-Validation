package com.example.validation.exception;

import com.example.validation.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(value ={ MethodArgumentNotValidException.class } )
    public ResponseEntity<Api<? extends Object>> validationException(
            //어떤 타입인지는 모르겠지만 Object를 상속받은 것을 리턴해주겠다. //ResponseEntity로 StatusCode 지정
        MethodArgumentNotValidException exception
    ){
        log.error("", exception);

        var errorMessageList = exception.getFieldErrors().stream() //에러가난 필드를 가져옴.
                .map( it -> { //값을 변환
                    var format = "%s : { %s } 은 %s"; //어떠한 필드가 어떠한 이유때문에 통과할수 없다는 메세지
                    var message = String.format(format, it.getField(), it.getRejectedValue(), it.getDefaultMessage());
                    return message;
                }).collect(Collectors.toList());

        var error = Api.Error
                .builder()
                .errorMessage(errorMessageList) //Error에 errorMessage들 담아줌.
                .build();

        var errorResponse = Api.builder()
                .resultCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .resultMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .error(error)
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

}
