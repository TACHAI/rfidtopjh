package com.laishishui.rfidtopjh.common;

/**
 * @ClassName ResponseCode
 * @Author https://github.com/TACHAI
 * @Email tc1206966083@gmail.com
 * @Date 2019-06-05 16:06
 **/
public enum

ResponseCode {
    /**
     * 200 成功
     */
    SUCCESS(200,"SUCCESS"),
    /**
     * 400 失败
     */
    ERROR(400,"ERROR"),
    /**
     * 500 参数错误
     */
    ILLEGAL_ARGUMENT(500,"ILLEGAL_ARGUMENT"),
    /**
     * 300 参数丢失
     */
    MISS_ARGS(300,"MISSING_ARGS");

    private final int code;
    private final String desc;

    ResponseCode(int code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public int getCode() {
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
