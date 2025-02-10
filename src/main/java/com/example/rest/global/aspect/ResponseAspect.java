package com.example.rest.global.aspect;
import com.example.rest.global.dto.RsData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Aspect
@Component
@RequiredArgsConstructor
public class ResponseAspect {

    private final HttpServletResponse response;

    @Around("""
            (
                within
                (
                    @org.springframework.web.bind.annotation.RestController *
                )
                &&
                (
                    @annotation(org.springframework.web.bind.annotation.GetMapping)
                    ||
                    @annotation(org.springframework.web.bind.annotation.PostMapping)
                    ||
                    @annotation(org.springframework.web.bind.annotation.PutMapping)
                    ||
                    @annotation(org.springframework.web.bind.annotation.DeleteMapping)
                )
            )
            ||
            @annotation(org.springframework.web.bind.annotation.ResponseBody)
            """
    )

    public Object test(ProceedingJoinPoint joinPoint) throws Throwable {
        //System.out.println("pre");
        Object proceed = joinPoint.proceed(); //실제 수행 메서드
        if(proceed instanceof RsData rsData) {
            String msg = rsData.getMsg();
            //System.out.println("msg : " + msg );
            response.setStatus(201); //응답 코드 설정
        }
        //System.out.println("post");
        return proceed;
    }

}