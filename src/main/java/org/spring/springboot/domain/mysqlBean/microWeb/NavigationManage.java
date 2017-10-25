package org.spring.springboot.domain.mysqlBean.microWeb;

/**
 * 栏目实体
 * Created by lyl on 2017/4/13 0013.
 */
public class NavigationManage implements java.io.Serializable{

    private int id;
    private  String code;
    private  String name;//名称
    private  String pcode;//
    private  int sort;//排序
    private int level;//1、2级为导航，3级为栏目
    private int type;//1：资讯网，2：微网点，3：触摸屏 ;4地图
    private String ABSTRACT;//简介
    private  String introduction;//介绍
    private String imgUrl;//图片链接
    private String vcode;
    private int status;//状态
    private int pid;//关联ID
    private int languageType;

    public NavigationManage(){

    }

    public NavigationManage(int id, String code, String name, String pcode, int sort, int level, int type, String ABSTRACT, String introduction, String imgUrl, String vcode, int status, int pid, int languageType) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.pcode = pcode;
        this.sort = sort;
        this.level = level;
        this.type = type;
        this.ABSTRACT = ABSTRACT;
        this.introduction = introduction;
        this.imgUrl = imgUrl;
        this.vcode = vcode;
        this.status = status;
        this.pid = pid;
        this.languageType = languageType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getABSTRACT() {
        return ABSTRACT;
    }

    public void setABSTRACT(String ABSTRACT) {
        this.ABSTRACT = ABSTRACT;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getLanguageType() {
        return languageType;
    }

    public void setLanguageType(int languageType) {
        this.languageType = languageType;
    }
}
