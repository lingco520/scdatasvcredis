package org.spring.springboot.domain.mysqlBean.microWeb;

import java.io.Serializable;

/**
 * Created by ziy
 *
 */
public class AccommodationVo implements Serializable{
    private String address;
    private  String  businessTime;
    private  String  longitude;
    private  String  latitude;
    private  String  image;
    private  String  introduce;
    private  String  name;
    private  String  phone;
    private  String  starLevel;
    private  String  type;
    private  String  vcode;

    private  Integer  picCount;
    private  String PicUrl360;
    private  String resourceCode;


    public AccommodationVo(){}

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }



    public Integer getPicCount() {
        return picCount;
    }

    public void setPicCount(Integer picCount) {
        this.picCount = picCount;
    }

    public String getPicUrl360() {
        return PicUrl360;
    }

    public void setPicUrl360(String picUrl360) {
        PicUrl360 = picUrl360;
    }
}
