package com.laishishui.rfidtopjh.dao;

import com.laishishui.rfidtopjh.bean.po.ShelfNo;

import java.util.List;

public interface ShelfNoMapper {
    int deleteByPrimaryKey(String barcode);

    int insert(ShelfNo record);

    int insertSelective(ShelfNo record);

    ShelfNo selectByPrimaryKey(String barcode);

    int updateByPrimaryKeySelective(ShelfNo record);

    int updateByPrimaryKey(ShelfNo record);

    List<ShelfNo> selectAll( );

    List<ShelfNo> selectBylastDay( );
}