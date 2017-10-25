package org.spring.springboot.domain.madeVoBean.carFlow;

import java.io.Serializable;

/**
 * @Author: yangkang .
 * @Date: Created in 2017-6-7.
 * @Version: V3.0.0.
 * @describe:三维地图-停车场
 */
public class ParkingSealWidget implements Serializable {
    private long id;
    private String name;
    private String longitude;
    private String latitude;

    public ParkingSealWidget() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
