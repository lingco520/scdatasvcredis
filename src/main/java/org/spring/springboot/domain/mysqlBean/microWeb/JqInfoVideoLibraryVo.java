package org.spring.springboot.domain.mysqlBean.microWeb;

import java.io.Serializable;

/**
 * Created by ziy  景区视频
 */
public class JqInfoVideoLibraryVo implements Serializable {
    private String imgUrl;
    private String remark;
    private String scenicName;
    private Integer sort;
    private Integer status;
    private String title;
    private String typeName;
    private String url;
    private String vcode;
    private String videoPlatform;


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String getVideoPlatform() {
        return videoPlatform;
    }

    public void setVideoPlatform(String videoPlatform) {
        this.videoPlatform = videoPlatform;
    }
}
