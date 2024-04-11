package com.laishishui.rfidtopjh.bean;

import lombok.Data;

/**
 * @ClassName ShelfNoDTO
 * @Author https://github.com/TACHAI
 * @Email tc1206966083@gmail.com
 * @Date 2024-04-10 12:19
 */

@Data
public class ShelfNoDTO {

    // 排架号
    private String shelfNo;
    // 馆藏地
    private String locationId;
    // 流通属性
    private String circAttr;
    // 馆代码
    private String libCode;
    // 修改的是条码号or财产号
    private Integer barOrProp;
    // 条码号or财产号
    private String barcode;
    // 账号
    private String primaryId;

}
