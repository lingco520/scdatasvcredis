package org.spring.springboot.domain.mysqlBean.microWeb;

import java.io.Serializable;

/**
 * Created by lxm
 */
public class JqTouristRoutesVo implements Serializable {
    private  Integer id;
    private  Integer  scenicId;
    private  String  time;//建议游玩时间
    private  String  aspect;//游览看点
    private  String  SNACK;//特色小吃
    private  String  precautions;//注意事项
    private  String  image;//图片
    private  String name;//线路名称
    private  Integer  status;
    private  Integer  sort;
        private String details;

    public JqTouristRoutesVo(){}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScenicId() {
        return scenicId;
    }

    public void setScenicId(Integer scenicId) {
        this.scenicId = scenicId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAspect() {
        return aspect;
    }

    public void setAspect(String aspect) {
        this.aspect = aspect;
    }

    public String getSNACK() {
        return SNACK;
    }

    public void setSNACK(String SNACK) {
        this.SNACK = SNACK;
    }

    public String getPrecautions() {
        return precautions;
    }

    public void setPrecautions(String precautions) {
        this.precautions = precautions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
