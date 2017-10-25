package org.spring.springboot.domain.mysqlBean.microWeb;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ziy
 */
public class JqInfoPictureLibraryPage implements Serializable {
    private List<JqInfoPictureLibraryVo> list;
    private int count;
    private int totalPage;

    public List<JqInfoPictureLibraryVo> getList() {
        return list;
    }

    public void setList(List<JqInfoPictureLibraryVo> list) {
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
