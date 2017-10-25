package org.spring.springboot.domain.mysqlBean.microWeb;

import java.io.Serializable;

/**
 * @Author: lxm .
 * @Date: Created in
 * @Version: V2.2.2.
 * @describe:微官网景区景区舒适度
 */

public class JqComfortVo implements Serializable {
    private String NAME;
    private String RATING;
    private String COLORVALUE;
    private  String  conut;
    private  String maxquantity;

    public JqComfortVo(){}
    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getRATING() {
        return RATING;
    }

    public void setRATING(String RATING) {
        this.RATING = RATING;
    }

    public String getCOLORVALUE() {
        return COLORVALUE;
    }

    public void setCOLORVALUE(String COLORVALUE) {
        this.COLORVALUE = COLORVALUE;
    }


    public String getConut() {
        return conut;
    }

    public void setConut(String conut) {
        this.conut = conut;
    }

    public String getMaxquantity() {
        return maxquantity;
    }

    public void setMaxquantity(String maxquantity) {
        this.maxquantity = maxquantity;
    }
}