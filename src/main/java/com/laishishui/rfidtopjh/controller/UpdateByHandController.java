package com.laishishui.rfidtopjh.controller;

import com.laishishui.rfidtopjh.common.ServerResponse;
import com.laishishui.rfidtopjh.service.ShellfNoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UpdateByHand
 * @Author https://github.com/TACHAI
 * @Email tc1206966083@gmail.com
 * @Date 2024-04-11 14:33
 */

@Api(tags = "UpdateByHandController|消息列表")
@RequestMapping("/api/back/shelf/")
@RestController
public class UpdateByHandController {

    @Autowired
    private ShellfNoService     shellfNoService;

    @ApiOperation("初始化，更新所有排架号")
    @GetMapping("init")
    public ServerResponse<String> init(){
        return shellfNoService.initShelfNo();
    }

    @ApiOperation("测试排架号 barcode是图书条码号，shelfNo是排架号")
    @PostMapping("test")
    public ServerResponse<String> test(String barcode,String shelfNo){
        return shellfNoService.handUpdate(barcode,shelfNo);
    }
}
