package org.spring.springboot.domain.mysqlBean.microWeb;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ziy
 */
public class JqFlowerPage implements Serializable {
    private List<JqFlowerVo> list;
    private int count;
    private int totalPage;

    public List<JqFlowerVo> getList() {
        return list;
    }

    public void setList(List<JqFlowerVo> list) {
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
