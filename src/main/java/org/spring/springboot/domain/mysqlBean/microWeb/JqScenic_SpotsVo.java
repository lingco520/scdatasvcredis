package org.spring.springboot.domain.mysqlBean.microWeb;


import java.io.Serializable;

/**
 * Created by zjy
 */
public class JqScenic_SpotsVo implements Serializable {

    private String name;
    private Long id;
    private String longitude;
    private String latitude;
    private String pictureLibrary;
    private String introduction;
    private String phone;
    private String vcode;
    private String sign720;
    private String pixel_x;
    private String Pixel_y;
    private  String vname;
    private  String voiceUrl;
//    private  String vnametwo;
//    private  String voiceUrltow;
//    private  String vnamethree;
//    private  String voiceUrlthree;

    public JqScenic_SpotsVo(){}
    public String getSign720() {
        return sign720;
    }

    public void setSign720(String sign720) {
        this.sign720 = sign720;
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

    public String getPictureLibrary() {
        return pictureLibrary;
    }

    public void setPictureLibrary(String pictureLibrary) {
        this.pictureLibrary = pictureLibrary;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String getPixel_x() {
        return pixel_x;
    }

    public void setPixel_x(String pixel_x) {
        this.pixel_x = pixel_x;
    }

    public String getPixel_y() {
        return Pixel_y;
    }

    public void setPixel_y(String pixel_y) {
        Pixel_y = pixel_y;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

//    public String getVnametwo() {
//        return vnametwo;
//    }
//
//    public void setVnametwo(String vnametwo) {
//        this.vnametwo = vnametwo;
//    }
//
//    public String getVoiceUrltow() {
//        return voiceUrltow;
//    }
//
//    public void setVoiceUrltow(String voiceUrltow) {
//        this.voiceUrltow = voiceUrltow;
//    }
//
//    public String getVnamethree() {
//        return vnamethree;
//    }
//
//    public void setVnamethree(String vnamethree) {
//        this.vnamethree = vnamethree;
//    }
//
//    public String getVoiceUrlthree() {
//        return voiceUrlthree;
//    }
//
//    public void setVoiceUrlthree(String voiceUrlthree) {
//        this.voiceUrlthree = voiceUrlthree;
//    }
}
