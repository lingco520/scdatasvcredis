package org.spring.springboot.domain.mysqlBean.microWeb;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class Bd_RealPeople implements Serializable {
    private String id;
    private String vcode;
    private String realtimeData;//今天的实时人数
    private String maxMonth;//人数最多的月份
    private String maxRealpeople;//人数最多月份的具体人数
    private String lastMonthData;//上月人数
    private String thisMonthData;//本月人数
    public Bd_RealPeople() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String getRealtimeData() {
        return realtimeData;
    }

    public void setRealtimeData(String realtimeData) {
        this.realtimeData = realtimeData;
    }

    public String getMaxMonth() {
        return maxMonth;
    }

    public void setMaxMonth(String maxMonth) {
        this.maxMonth = maxMonth;
    }

    public String getMaxRealpeople() {
        return maxRealpeople;
    }

    public void setMaxRealpeople(String maxRealpeople) {
        this.maxRealpeople = maxRealpeople;
    }

    public String getLastMonthData() {
        return lastMonthData;
    }

    public void setLastMonthData(String lastMonthData) {
        this.lastMonthData = lastMonthData;
    }

    public String getThisMonthData() {
        return thisMonthData;
    }

    public void setThisMonthData(String thisMonthData) {
        this.thisMonthData = thisMonthData;
    }
}
