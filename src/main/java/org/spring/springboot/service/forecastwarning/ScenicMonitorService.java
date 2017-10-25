package org.spring.springboot.service.forecastwarning;

/**
 * @author tanggm
 * @description
 * @date 2017-06-23 14:42
 */
public interface ScenicMonitorService{

    /**
     * 停车场天的数据redis更新
     * @param vcode
     * @param startTime
     * @param endTime
     */
    void getParkingByThisHors(String vcode, String startTime, String endTime);
    /**
     * 停车场月的数据redis更新
     * @param vcode
     * @param startTime
     * @param endTime
     */
    void getParkingByMonth(String vcode, String year,String month, String startTime, String endTime);
    /**
     * 停车场年的数据redis更新
     * @param vcode
     */
    void getParkingByYear(String vcode, String year);

    /**
     * 景点实时人数-小时统计 历史数据补偿方法
     * @param vcode
     * @param startTime
     * @param endTime
     */
    void getScenicSpotsDataAnalysisTimeAllHistory(String vcode, String startTime, String endTime);
}
