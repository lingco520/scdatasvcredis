package org.spring.springboot.domain.mysqlBean.microWeb;

import java.io.Serializable;

/**
 * @Author: lxm .
 * @Date: Created in
 * @Version: V2.3.0.
 * @describe:数据中心公共资源音频库
 */

public class JqVoice implements Serializable {
    private  String name;
    private  Integer  id;
    private  String  voiceUrl;
    private  Integer  sex;
    private  Integer  age;
    private  String  abstractv;//摘要
    private  String  introduce;//简介
    private  Integer  image;
    private  Integer  renqi; //1：是本周人气王，2：不是本周人气王
    private  Integer  type;// 1表示为熊猫  2 表示植物园 3 表示动物园
    private  String  voiceLenth;//语音播放时长
    private  Integer   scenicId;//景区id
    private  Integer  sort;
    private  String  status;
    private  String  vcode;
  public JqVoice(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAbstractv() {
        return abstractv;
    }

    public void setAbstractv(String abstractv) {
        this.abstractv = abstractv;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public Integer getRenqi() {
        return renqi;
    }

    public void setRenqi(Integer renqi) {
        this.renqi = renqi;
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

    public Integer getScenicId() {
        return scenicId;
    }

    public void setScenicId(Integer scenicId) {
        this.scenicId = scenicId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }
}
