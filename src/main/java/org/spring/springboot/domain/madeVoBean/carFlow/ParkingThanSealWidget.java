package org.spring.springboot.domain.madeVoBean.carFlow;

import java.io.Serializable;

/**
 * Created by yangk on 2017-6-6.
 */
public class ParkingThanSealWidget implements Serializable {
    private Long num;
    private String carNum;

    public ParkingThanSealWidget() {
    }

    public Long getNum() {
        return this.num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getCarNum() {
        return this.carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }
}
