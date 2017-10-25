package org.spring.springboot.domain.mysqlBean.microWeb;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: lxm .
 * @Date: Created in
 * @Version: V2.3.0.
 * @describe:数据中心公共资源音频库
 */

public class JqVoicePage implements Serializable {
    private List<JqVoiceVo> list;
    private int count;
    private int totalPage;
    public JqVoicePage(){}

    public List<JqVoiceVo> getList() {
        return list;
    }

    public void setList(List<JqVoiceVo> list) {
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
