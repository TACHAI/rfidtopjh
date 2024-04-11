package com.laishishui.rfidtopjh.bean.po;

import java.util.Date;

public class ShelfNo {
    private String barcode;

    private String locationName;

    private String callNo;

    private String bookName;

    private String shellNo;

    private Date updateTime;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName == null ? null : locationName.trim();
    }

    public String getCallNo() {
        return callNo;
    }

    public void setCallNo(String callNo) {
        this.callNo = callNo == null ? null : callNo.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getShellNo() {
        return shellNo;
    }

    public void setShellNo(String shellNo) {
        this.shellNo = shellNo == null ? null : shellNo.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}