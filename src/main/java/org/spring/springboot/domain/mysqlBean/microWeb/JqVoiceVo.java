package org.spring.springboot.domain.mysqlBean.microWeb;

import java.io.Serializable;

/**
 * @Author: lxm .
 * @Date: Created in
 * @Version: V2.2.0.
 * @describe:数据中心公共资源音频库
 */


public class JqVoiceVo implements Serializable {
    private  Integer id;
    private  Integer  scenicId;
    private  String  name;//音频名称
    private  String  voiceUrl;//音频播放地址
    private  String  scenicName;//对应景点名称
    private  String  image;//图片
    private  Integer type;//线路名称
    private  String  voiceLenth;//播放时长
    private  String  status;
    private  Integer  sort;
    public JqVoiceVo(){}


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getVoiceLenth() {
        return voiceLenth;
    }

    public void setVoiceLenth(String voiceLenth) {
        this.voiceLenth = voiceLenth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
