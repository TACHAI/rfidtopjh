package com.laishishui.rfidtopjh.common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ExceptionHandle
 * @Author https://github.com/TACHAI
 * @Email tc1206966083@gmail.com
 * @Date 2020-05-20 8:47
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ServerResponse<String> handle(Exception e){
        if(e instanceof BadRequestException){
            return ServerResponse.createByErrorCodeMessage(-1,e.getMessage());
        }else {
            return ServerResponse.createByErrorCodeMessage(500,e.getMessage());
        }
    }
}
