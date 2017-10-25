package org.spring.springboot.domain.mysqlBean.microWeb;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cl on
 */
public class ScenicSpotsPage implements Serializable {
    private List<JqScenic_SpotsVo> list;
    private int count;
    private int totalPage;


    public List<JqScenic_SpotsVo> getList() {
        return list;
    }

    public void setList(List<JqScenic_SpotsVo> list) {
        this.list = list;
    }

    /**
     * 条数
     */
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 总页数
     */
    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
