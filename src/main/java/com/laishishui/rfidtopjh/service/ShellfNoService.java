package com.laishishui.rfidtopjh.service;

import com.laishishui.rfidtopjh.common.ServerResponse;

/**
 * @ClassName ShellfNoService
 * @Author https://github.com/TACHAI
 * @Email tc1206966083@gmail.com
 * @Date 2024-04-10 11:58
 */
public interface ShellfNoService {
    ServerResponse<String> initShelfNo();

    ServerResponse<String> autoUpdate();

    ServerResponse<String> handUpdate(String barcode,String shelfNo);


}
