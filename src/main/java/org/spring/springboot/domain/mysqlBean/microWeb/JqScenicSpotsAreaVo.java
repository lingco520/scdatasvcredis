package org.spring.springboot.domain.mysqlBean.microWeb;


import java.io.Serializable;

/**
 * Created by zjy
 */
public class JqScenicSpotsAreaVo implements Serializable {
    private Integer id;
    private String address;
    private String image;
    private String introduce;
    private String latitude;
    private String level;
    private String longitude;
    private String name;
    private String openTime;
    private String phone;
    private String playTime;
    private String scenicType;
    private String vcode;
    private Integer status;
    private String resource_code;
    private String pro_type;
    private String sketch_picture;
    private Integer play_sign;
    private String sign_720;
    private Integer check_price;
    private Integer picCount;

    public JqScenicSpotsAreaVo(){}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPicCount() {
        return picCount;
    }

    public void setPicCount(Integer picCount) {
        this.picCount = picCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public String getScenicType() {
        return scenicType;
    }

    public void setScenicType(String scenicType) {
        this.scenicType = scenicType;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {

        this.vcode = vcode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getResource_code() {
        return resource_code;
    }

    public void setResource_code(String resource_code) {
        this.resource_code = resource_code;
    }

    public String getPro_type() {
        return pro_type;
    }

    public void setPro_type(String pro_type) {
        this.pro_type = pro_type;
    }

    public String getSketch_picture() {
        return sketch_picture;
    }

    public void setSketch_picture(String sketch_picture) {
        this.sketch_picture = sketch_picture;
    }

    public Integer getPlay_sign() {
        return play_sign;
    }

    public void setPlay_sign(Integer play_sign) {
        this.play_sign = play_sign;
    }

    public String getSign_720() {
        return sign_720;
    }

    public void setSign_720(String sign_720) {
        this.sign_720 = sign_720;
    }

    public Integer getCheck_price() {
        return check_price;
    }

    public void setCheck_price(Integer check_price) {
        this.check_price = check_price;
    }
}
