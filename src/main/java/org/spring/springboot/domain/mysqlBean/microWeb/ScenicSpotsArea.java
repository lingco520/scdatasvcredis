package org.spring.springboot.domain.mysqlBean.microWeb;

import java.io.Serializable;

/**
 * Created by ziy on 2016/8/2 0002.
 * 景区数据
 */
public class ScenicSpotsArea implements Serializable {
    private  Integer id;
    private  String  ROLE_ID;

    private  String  ADDRESS;
    private  String  IMAGE;
    private  String  INTRODUCE;
    private  String  LATITUDE;
    private  String  LONGITUDE;
    private  String  LEVEL;
    private  String  NAME;
    private  String  OPEN_TIME;
    private  String  PHONE;
    private  String  ADMINISTRATIVE_DIVISION;
    private  String  PLAY_TIME;
    private  String  SCENIC_TYPE;
    private  String  VCODE;
    private  String  typeName;
    private Integer STATUS;
    private String RESOURCE_CODE;
    private String PRO_TYPE;
    private String SKETCH_PICTURE;
    private Integer PLAY_SIGN;
    private String SIGN_720;
    private Integer CHECK_PRICE;

    public ScenicSpotsArea(){}

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(String ROLE_ID) {
        this.ROLE_ID = ROLE_ID;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

    public String getINTRODUCE() {
        return INTRODUCE;
    }

    public void setINTRODUCE(String INTRODUCE) {
        this.INTRODUCE = INTRODUCE;
    }

    public String getLATITUDE() {
        return LATITUDE;
    }

    public void setLATITUDE(String LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    public String getLONGITUDE() {
        return LONGITUDE;
    }

    public void setLONGITUDE(String LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    public String getLEVEL() {
        return LEVEL;
    }

    public void setLEVEL(String LEVEL) {
        this.LEVEL = LEVEL;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getOPEN_TIME() {
        return OPEN_TIME;
    }

    public void setOPEN_TIME(String OPEN_TIME) {
        this.OPEN_TIME = OPEN_TIME;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getADMINISTRATIVE_DIVISION() {
        return ADMINISTRATIVE_DIVISION;
    }

    public void setADMINISTRATIVE_DIVISION(String ADMINISTRATIVE_DIVISION) {
        this.ADMINISTRATIVE_DIVISION = ADMINISTRATIVE_DIVISION;
    }

    public String getPLAY_TIME() {
        return PLAY_TIME;
    }

    public void setPLAY_TIME(String PLAY_TIME) {
        this.PLAY_TIME = PLAY_TIME;
    }

    public String getSCENIC_TYPE() {
        return SCENIC_TYPE;
    }

    public void setSCENIC_TYPE(String SCENIC_TYPE) {
        this.SCENIC_TYPE = SCENIC_TYPE;
    }

    public String getVCODE() {
        return VCODE;
    }

    public void setVCODE(String VCODE) {
        this.VCODE = VCODE;
    }

    public Integer getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(Integer STATUS) {
        this.STATUS = STATUS;
    }

    public String getRESOURCE_CODE() {
        return RESOURCE_CODE;
    }

    public void setRESOURCE_CODE(String RESOURCE_CODE) {
        this.RESOURCE_CODE = RESOURCE_CODE;
    }

    public String getPRO_TYPE() {
        return PRO_TYPE;
    }

    public void setPRO_TYPE(String PRO_TYPE) {
        this.PRO_TYPE = PRO_TYPE;
    }

    public String getSKETCH_PICTURE() {
        return SKETCH_PICTURE;
    }

    public void setSKETCH_PICTURE(String SKETCH_PICTURE) {
        this.SKETCH_PICTURE = SKETCH_PICTURE;
    }

    public Integer getPLAY_SIGN() {
        return PLAY_SIGN;
    }

    public void setPLAY_SIGN(Integer PLAY_SIGN) {
        this.PLAY_SIGN = PLAY_SIGN;
    }

    public String getSIGN_720() {
        return SIGN_720;
    }

    public void setSIGN_720(String SIGN_720) {
        this.SIGN_720 = SIGN_720;
    }

    public Integer getCHECK_PRICE() {
        return CHECK_PRICE;
    }

    public void setCHECK_PRICE(Integer CHECK_PRICE) {
        this.CHECK_PRICE = CHECK_PRICE;
    }
}
