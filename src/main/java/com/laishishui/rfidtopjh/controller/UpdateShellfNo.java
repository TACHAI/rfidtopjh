package com.laishishui.rfidtopjh.controller;

import com.laishishui.rfidtopjh.service.ShellfNoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @ClassName UpdateShellfNo
 * @Author https://github.com/TACHAI
 * @Email tc1206966083@gmail.com
 * @Date 2024-04-10 11:56
 */


@Configuration
@EnableScheduling
@Slf4j
public class UpdateShellfNo {


    @Autowired
    private ShellfNoService shellfNoService;

    @Scheduled(cron="")
    private void autoUpdateShellNo(){
        shellfNoService.autoUpdate(null,null);
    }



}
