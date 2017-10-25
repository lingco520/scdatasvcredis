package org.spring.springboot.domain.mysqlBean.microWeb;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lxm
 */
public class JqTouristRoutesPage implements Serializable {
    private List<JqTouristRoutesVo> list;
    private int count;
    private int totalPage;
    public JqTouristRoutesPage(){}

    public List<JqTouristRoutesVo> getList() {
        return list;
    }

    public void setList(List<JqTouristRoutesVo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
