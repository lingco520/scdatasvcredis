package org.spring.springboot.domain.madeVoBean.carFlow;

import java.io.Serializable;

/**
 * Created by yangk on 2017-6-6.
 */
public class ParkingUseSealWidget implements Serializable{
    private Long parkingUseFul;
    private Long surplusPaking;
    private Long id;
    private Long parkingTotal;

    public ParkingUseSealWidget() {
    }

    public Long getParkingUseFul() {
        return this.parkingUseFul;
    }

    public void setParkingUseFul(Long parkingUseFul) {
        this.parkingUseFul = parkingUseFul;
    }

    public Long getSurplusPaking() {
        return this.surplusPaking;
    }

    public void setSurplusPaking(Long surplusPaking) {
        this.surplusPaking = surplusPaking;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParkingTotal() {
        return this.parkingTotal;
    }

    public void setParkingTotal(Long parkingTotal) {
        this.parkingTotal = parkingTotal;
    }
}
