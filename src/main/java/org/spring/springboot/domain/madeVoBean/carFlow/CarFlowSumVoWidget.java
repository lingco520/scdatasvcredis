package org.spring.springboot.domain.madeVoBean.carFlow;

import java.io.Serializable;

/**
 * Created by lianch on 16/8/3.
 */
public class CarFlowSumVoWidget implements Serializable {
    private long sum;
    private String time;

    public CarFlowSumVoWidget() {
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
