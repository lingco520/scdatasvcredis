package org.spring.springboot.domain.mysqlBean.microWeb;

import java.io.Serializable;

/**
 * @Author: ziy.
 * @Date: Created in 2016/9/29 0007.
 * @Version: V2.3.0.
 * @describe:景区视频
 */
public class JqInfoVideoLibrary implements Serializable {
    private Integer id;
    private String title;
    private String remark;
    private String memo;
    private String imgUrl;
    private String url;
    private Integer orginFilesize;
    private Integer statu;
    private String vcode;
    private String image;
    private Integer zan;
    private Integer play;
    private Integer tagId;
    private String typeName;
    private String videoLenth;
    private Integer scenicId;
    private String name;
    private String count;
    private Integer sort;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getOrginFilesize() {
        return orginFilesize;
    }

    public void setOrginFilesize(Integer orginFilesize) {
        this.orginFilesize = orginFilesize;
    }

    public Integer getStatu() {
        return statu;
    }

    public void setStatu(Integer statu) {
        this.statu = statu;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getZan() {
        return zan;
    }

    public void setZan(Integer zan) {
        this.zan = zan;
    }

    public Integer getPlay() {
        return play;
    }

    public void setPlay(Integer play) {
        this.play = play;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getVideoLenth() {
        return videoLenth;
    }

    public void setVideoLenth(String videoLenth) {
        this.videoLenth = videoLenth;
    }

    public Integer getScenicId() {
        return scenicId;
    }

    public void setScenicId(Integer scenicId) {
        this.scenicId = scenicId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
