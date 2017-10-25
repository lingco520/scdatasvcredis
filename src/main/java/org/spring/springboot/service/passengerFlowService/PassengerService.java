package org.spring.springboot.service.passengerFlowService;

import org.spring.springboot.domain.madeVoBean.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: lyl .
 * @Date: Created in 2017/6/18.
 * @Version: V3.0.0.
 * @describe:景区客流人数分析service
 */
public interface PassengerService {

    /**
     * 地图客流来源
     *
     * @param vcode
     * @param date
     * @return
     */
    List<RealPeopleComeFromTopVo> getPeopleComeTopTen(String vcode, String date);

    /**
     * 景区省内客流来源
     *
     * @param vcode
     * @param date
     * @return
     */
    List<ProvinceLicensePlateVo> getProvinceLicensePlate(String vcode, String date);

    /**
     * 景区省内客流来源排名前十
     *
     * @param vcode
     * @param year
     * @return
     */
    List<ProvinceLicensePlateVo> getProvinceLicensePlateRank(String year, String vcode);

    /**
     * 省外游客
     * @param vcode
     * @param year
     * @return
     * @update zf 20170904
     */
    List<ProvinceLicensePlateVo> getProvinceOutside(String year, String vcode) ;

    /**
     * 景区省内客流来源
     *
     * @param vcode
     * @param date
     * @return
     */
    List<RealPeopleComeFromVo> getPeopleComeFrom(String vcode, String date);

    /**
     * 获取景区大数据--景区游客来源分析 定時器在走
     * 2017-08-11
     * lrd：此方法为特定方法，数据来源部分获取移动公司。
     * 云台山定制接口
     * @param vcode
     * @param dateTime   当前的年份
     * @return
     */
    Map<String,Object>  getBigPassengerAll(String vcode, String dateTime, String open);


    /**
     * 获取景区大数据--景区游客来源分析 定時器在走
     * 2017-09-09  通用微件
     * @param vcode
     * @param dateTime
     * @return
     */
    Map<String,Object>  getPeopleYearCommon(String vcode, String dateTime, String open);

    /**
     * 2017-08-11
     * lrd:获取所有景区当天的实时人数 推送redis
     * 查询范围是当前时间的前6天，因为考虑推数据的延迟，需要补偿之前的缓存数据
     * @param vcode
     * @param startTime
     * @param endTime
     * @return
     */
    void getPassengerFlowByDay(String vcode, String startTime, String endTime);
    /**
     * 2017-08-11
     * lrd:获取所有景区当月的实时人数 推送redis
     * @param startTime
     * @param endTime
     * @return
     */
    void getPassengerFlowByMonth(String vcode, String month, String startTime, String endTime);
    /**
     * 2017-08-11
     * lrd:获取景区当年的实时人数(读月份缓存进行累加) 推送redis
     * @return
     */
    void getPassengerFlowByYear(String vcode, String year);

    /**
     * 按小时获取景区数据
     * 2017-08-16
     * @param vcode
     * @param time
     */
    void getPassengerFlowByHour(String vcode,String stime, String time);


    /**
     * 获取景区景点数据人数
     */
    void  getPassengerFlowByScenic(String vcode);


    /**
     * 获取当天段内的实时人数
     * @param vcode
     * @param startTime
     * @param endTime
     */
    void getPassengerFlowByThisHours(String vcode, String startTime, String endTime, String time, String year, String month);


    /**
     * 抓举节假日的实时人数
     */
    void getPassengerFlowByHoliay(String vcode, String time, String year);

    void getScenicSpotsDataAnalysisTimeAll(String vcode);

    /**
     * 通过年份存入季度数据
     * @param vcode
     * @param year
     */
    void getPassengerFlowByQuarter(String vcode, String year);

    /**
     * 定制 -- 云台山模拟  日客流 -- 每小时执行一次
     * @param map
     */
    void saveYtsPassengerByDay(Map map);

    /**
     * 定制 -- 云台山模拟  日客流小时段 - 每天下午6点执行一次
     * @param map
     */
    void  savePassengerByDayYts(Map map);

    /**
     * 定制 -- 云台山模拟  日客流驻留时长分析（天段） - 每天下午6点执行一次
     * @param map
     */
    void savePassengerByTimeYts(Map map);

    /**
     * 定制 -- 云台山模拟  日客流驻留时长分析（小时段） - 每天下午6点执行一次
     * @param map
     */
    void savePeopleRemainByYts(Map map);

}
