package org.spring.springboot.domain.madeVoBean.carFlow;

import java.io.Serializable;

/**
 * Created by llianch on 16/7/30.
 */
public class CarFlowVosWidget implements Serializable {
    private String time;
    private long num;


    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
