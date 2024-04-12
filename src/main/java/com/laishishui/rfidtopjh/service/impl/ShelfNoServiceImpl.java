package com.laishishui.rfidtopjh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.laishishui.rfidtopjh.bean.ShelfNoDTO;
import com.laishishui.rfidtopjh.bean.po.ShelfNo;
import com.laishishui.rfidtopjh.common.ServerResponse;
import com.laishishui.rfidtopjh.dao.ShelfNoMapper;
import com.laishishui.rfidtopjh.service.ShellfNoService;
import com.laishishui.rfidtopjh.util.DateUtil;
import com.laishishui.rfidtopjh.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ShelfNoServiceImpl
 * @Author https://github.com/TACHAI
 * @Email tc1206966083@gmail.com
 * @Date 2024-04-10 12:00
 */
@Slf4j
@Service("shellNoService")
public class ShelfNoServiceImpl implements ShellfNoService {

    private static  String PRIMARYID;
    private static  String LIBCODE;


    private static String CLIENTID;
    private static String CLIENTSECRET;

    private static String CNTYPE;

    private static String NOURL="https://open.libsp.cn/open/shelf/updateShelfNo";
    private static String NOURL2="https://open.libsp.com/open/shelf/updateShelfNo";

    @Autowired
    private ShelfNoMapper shelfNoMapper;

    @Value("${lsp.cntype}")
    public void setCNTYPE(String cntype){
        ShelfNoServiceImpl.CNTYPE=cntype;
    }


    @Value("${lsp.primaryId}")
    public void setPRIMARYID(String primaryId){
        ShelfNoServiceImpl.PRIMARYID=primaryId;
    }

    @Value("${lsp.libcode}")
    public void setLIBCODE(String libcode){
        ShelfNoServiceImpl.LIBCODE=libcode;
    }


    @Value("${lsp.clientId}")
    public void setCLIENTID(String clientId){
        ShelfNoServiceImpl.CLIENTID=clientId;
    }

    @Value("${lsp.clientSecret}")
    public void setCLIENTSECRET(String clientSecret){
        ShelfNoServiceImpl.CLIENTSECRET=clientSecret;
    }

    /**
     * 全量更新排架号
     * @return
     */
    @Override
    public ServerResponse<String> initShelfNo() {
        List<ShelfNoDTO>  list = getAllShellNoDTO().getData();
        log.info("=========更新排架号开始========="+DateUtil.dateToStr(new Date(),DateUtil.DEFAULT_FORMAT));
        // 发起请求
        corePost(list);
        log.info("========更新排架号结束=========="+DateUtil.dateToStr(new Date(),DateUtil.DEFAULT_FORMAT));
        return ServerResponse.createBySuccessMessage("任务已启动，请查看日志校验");
    }

    /**
     *  定时任务  更新排架号，根据时间来，最近两天更新的排架号
     * @return
     */
    @Override
    public ServerResponse<String> autoUpdate() {

        // 获取要更新的排架号
        List<ShelfNoDTO>  list = getShellNoDTO().getData();

        log.info("=========更新排架号开始========="+DateUtil.dateToStr(new Date(),DateUtil.DEFAULT_FORMAT));
        // 发起请求
        corePost(list);
        log.info("========更新排架号结束=========="+DateUtil.dateToStr(new Date(),DateUtil.DEFAULT_FORMAT));
        return null;
    }

    /**
     *  手动更新排架号
     * @param barcode
     * @param shelfNo
     * @return
     */
    @Override
    public ServerResponse<String> handUpdate(String barcode, String shelfNo) {
        ShelfNoDTO dto = new ShelfNoDTO();
        dto.setShelfNo(shelfNo);
        dto.setLocationId("-1");
        dto.setCircAttr("-1");
        dto.setLibCode(LIBCODE);
        dto.setBarOrProp(0);
        dto.setBarcode(barcode);
        dto.setPrimaryId(PRIMARYID);
        List<ShelfNoDTO> list = new ArrayList<>();
        list.add(dto);
        corePost(list);
        return ServerResponse.createBySuccessMessage("请查看opac是否修改");
    }

    //  从数据库 获取 最近两天排架号信息
    public ServerResponse<List<ShelfNoDTO>> getShellNoDTO(){

        List<ShelfNo> shelfNoList=shelfNoMapper.selectBylastDay();
        List<ShelfNoDTO> list =poToDto(shelfNoList);
        return ServerResponse.createBySuccess(list);
    }
    // 从数据库获取全量的排架号
    public ServerResponse<List<ShelfNoDTO>> getAllShellNoDTO(){

        List<ShelfNo> shelfNoList=shelfNoMapper.selectAll();
        List<ShelfNoDTO> list =poToDto(shelfNoList);
        return ServerResponse.createBySuccess(list);
    }

    //  post  发起更新排架号请求
    public void corePost(List<ShelfNoDTO> list){
        // 判断是发送 .com  域名，还是。cn域名
        System.out.println("TYPE====="+CNTYPE);
        if(StringUtils.equals(CNTYPE,"1")){
            System.out.println("=====1====TYPE====="+CNTYPE);

            // 获取token
            String token = HttpUtil.getComLibToken(CLIENTID,CLIENTSECRET).getData();
            list.forEach(e->{
                // 发起http 请求
                HttpUtil.doLibPost(NOURL2, JSONObject.toJSONString(e),token);

            });
        }else {
            System.out.println("=====2====TYPE====="+CNTYPE);

            // 获取token
            String token = HttpUtil.getCnLibToken(CLIENTID,CLIENTSECRET).getData();
            list.forEach(e->{
                // 发起http 请求
                HttpUtil.doLibPost(NOURL, JSONObject.toJSONString(e),token);

            });
        }


    }

    // po to dto
    public List<ShelfNoDTO> poToDto(List<ShelfNo> shelfNoList){
        List<ShelfNoDTO> list =new ArrayList<>();
        shelfNoList.forEach(e->{
            ShelfNoDTO dto = new ShelfNoDTO();
            dto.setShelfNo(e.getShellNo());
            dto.setLocationId("-1");
            dto.setCircAttr("-1");
            dto.setLibCode(LIBCODE);
            dto.setBarOrProp(0);
            dto.setBarcode(e.getBarcode());
            dto.setPrimaryId(PRIMARYID);
            list.add(dto);
        });
        return list;
    }
}
