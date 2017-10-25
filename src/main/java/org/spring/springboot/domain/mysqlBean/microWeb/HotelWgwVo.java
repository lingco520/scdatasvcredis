package org.spring.springboot.domain.mysqlBean.microWeb;

import java.io.Serializable;

/**
 * Created by ziy on 16/8/16.
 */
public class HotelWgwVo implements Serializable {
    private Long id;
    private String name;
    private String address;
    private String level;
    private String longitude;
    private String latitude;
    private String sketchPicture;
    private Double checkPrice;
    private String checkPriceTime;
    private Integer playSign;
    private Integer sign720;
    private String picUrl360;
    private String resourceCode;

    public HotelWgwVo() {
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getSketchPicture() {
        return sketchPicture;
    }

    public void setSketchPicture(String sketchPicture) {
        this.sketchPicture = sketchPicture;
    }

    public Double getCheckPrice() {
        return checkPrice;
    }

    public void setCheckPrice(Double checkPrice) {
        this.checkPrice = checkPrice;
    }

    public String getCheckPriceTime() {
        return checkPriceTime;
    }

    public void setCheckPriceTime(String checkPriceTime) {
        this.checkPriceTime = checkPriceTime;
    }

    public Integer getPlaySign() {
        return playSign;
    }

    public void setPlaySign(Integer playSign) {
        this.playSign = playSign;
    }

    public Integer getSign720() {
        return sign720;
    }

    public void setSign720(Integer sign720) {
        this.sign720 = sign720;
    }


    public String getPicUrl360() {
        return picUrl360;
    }

    public void setPicUrl360(String picUrl360) {
        this.picUrl360 = picUrl360;
    }
}
