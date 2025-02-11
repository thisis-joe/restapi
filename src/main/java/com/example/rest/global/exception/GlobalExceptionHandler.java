package com.example.rest.global.exception;
import com.example.rest.global.dto.RsData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.NoSuchElementException;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class) //없는 데이터 요청 시
    public ResponseEntity<RsData<Void>> handle() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new RsData<>(
                                "404-1",
                                "해당 데이터가 존재하지 않습니다"
                        )
                );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class) //입력값 검증 실패
    public String handleMethodArgumentNotValidException() {
        return "MethodArgumentNotValidException";
    }
}