package com.laishishui.rfidtopjh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.laishishui.rfidtopjh.bean.ShellNoDTO;
import com.laishishui.rfidtopjh.common.ServerResponse;
import com.laishishui.rfidtopjh.service.ShellfNoService;
import com.laishishui.rfidtopjh.util.DateUtil;
import com.laishishui.rfidtopjh.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @ClassName ShellNoServiceImpl
 * @Author https://github.com/TACHAI
 * @Email tc1206966083@gmail.com
 * @Date 2024-04-10 12:00
 */
@Slf4j
@Service("shellNoService")
public class ShellNoServiceImpl implements ShellfNoService {

    private static  String PRIMARYID;
    private static  String LIBCODE;


    private static String CLIENTID;
    private static String CLIENTSECRET;

    private static String NOURL="https://open.libsp.cn/shelf/updateShelfNo";

    @Value("${lsp.primaryId}")
    public void setPRIMARYID(String primaryId){
        ShellNoServiceImpl.PRIMARYID=PRIMARYID;
    }

    @Value("${lsp.libcode}")
    public void setLIBCODE(String libcode){
        ShellNoServiceImpl.LIBCODE=LIBCODE;
    }


    @Value("${lsp.clientId}")
    public void setCLIENTID(String clientId){
        ShellNoServiceImpl.CLIENTID=clientId;
    }

    @Value("${lsp.clientSecret}")
    public void setCLIENTSECRET(String clientSecret){
        ShellNoServiceImpl.CLIENTSECRET=clientSecret;
    }

    @Override
    public ServerResponse<String> autoUpdate(String primaryId, String libCode) {

        if(StringUtils.isNotBlank(primaryId)){

        }else {
            primaryId=PRIMARYID;
        }
        if(StringUtils.isNotBlank(libCode)){

        }else {
            libCode=LIBCODE;
        }

        log.info("=========更新排架号开始========="+DateUtil.dateToStr(new Date(),DateUtil.DEFAULT_FORMAT));
        // 获取token
        String token =HttpUtil.getCnLibToken(CLIENTID,CLIENTSECRET).getData();
        // 获取要更新的排架号
        List<ShellNoDTO>  list = getShellNoDTO().getData();
        list.forEach(e->{
            // 发起http 请求
            HttpUtil.doLibPost(NOURL, JSONObject.toJSONString(e),token);

        });
        log.info("========更新排架号结束=========="+DateUtil.dateToStr(new Date(),DateUtil.DEFAULT_FORMAT));
        return null;
    }

    @Override
    public ServerResponse<String> handUpdate(String primaryId, String libCode) {

        return null;
    }

    //  从数据库 获取 排架号信息
    public ServerResponse<List<ShellNoDTO>> getShellNoDTO(){

        return null;

    }
}
