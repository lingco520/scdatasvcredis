/**
 * @Copyright: <a htef="http://www.daqsoft.com">成都中科大旗软件有限公司Copyright © 2004-2017蜀ICP备08010315号</a>
 * @Warning: 注意：本内容仅限于成都中科大旗软件有限公司内部传阅，禁止外泄以及用于其他的商业目的。
 */
package org.spring.springboot.timer;

import org.spring.springboot.config.MyProps;
import org.spring.springboot.service.wifiAnalysisService.WifiAnalysisService;
import org.spring.springboot.utils.DateUtil;
import org.spring.springboot.utils.annotation.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * @Title: Wifi定时推送缓存
 * @Author: yangmei
 * @Date: 2017/08/15 10:15
 * @Comment：主要为Wifi相关统计的数据缓存
 * @Version: V3.0.0
 * @since JDK 1.8
 */
@Component
public class WifiTimer {

    @Autowired
    private MyProps props; //获取配置微件信息

    @Autowired
    private WifiAnalysisService wifiAnalysisService;
    /**
     * wifi 天 月 季度 年 数据redis推送
     */
    @SysLog
    @Async
    @Scheduled(initialDelay = 60000L , fixedRate = 300000L)
    public void getWifiByDay() throws Exception{
        // 计算天的数据
        String startTime = DateUtil.getDateBefore();
        String endTime  = DateUtil.getCurDateStr();
        Map<String,String> mapVcode=props.getCommonScenic();
        for (Map.Entry<String,String> entryday: mapVcode.entrySet()) {
            final Map.Entry<String,String> entrytodady=entryday;
            String vcode = entrytodady.getValue();
            wifiAnalysisService.getWifiByDay(vcode,startTime,endTime);
        }
        // 计算月的数据
        // 传入时间的年份
        String year = DateUtil.getCurYearStr();
        // 当前年份
        String nowyear = DateUtil.getCurYearStr();
        String[] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        if(Integer.valueOf(year).compareTo(Integer.valueOf(nowyear)) >= 0){
            String nowmonth = DateUtil.getCurMonthNumStr();
            for(String month : months){
                if(Integer.valueOf(month).compareTo(Integer.valueOf(nowmonth)) <= 0){
                    getWifiByMonth(year,month);
                }
            }
        }else{
            for(String month : months){
                getWifiByMonth(year,month);
            }
        }
        // 计算季度的数据
        getWifiByQuarter(year);
        // 计算年的数据
        getWifiByYear(year);
    }

    /**
     * 计算月的数据
     * @throws Exception
     */
    public void getWifiByMonth(String year, String month) throws Exception{
        String startTime = year+"-"+month+"-01";
        int days = DateUtil.getDaysByYearMonth(Integer.valueOf(year), Integer.valueOf(month));
        String endTime = year+"-"+month+"-"+days;
        Map<String,String> map=props.getCommonScenic();
        for (Map.Entry<String,String> entry : map.entrySet()) {
            String vcode = entry.getValue();
            wifiAnalysisService.getWifiByMonth(vcode, year, month,startTime, endTime);
        }
    }
    /**
     * 计算季度的数据
     * @throws Exception
     */
    public void getWifiByQuarter(String year) throws Exception{
        Map<String,String> map=props.getCommonScenic();
        for (Map.Entry<String,String> entry : map.entrySet()) {
            String vcode = entry.getValue();
            wifiAnalysisService.getWifiByQuarter(vcode,year);
        }
    }
    /**
     * 计算年的数据
     * @throws Exception
     */
    public void getWifiByYear(String year) throws Exception{
        Map<String,String> map=props.getCommonScenic();
        for (Map.Entry<String,String> entry : map.entrySet()) {
            String vcode = entry.getValue();
            wifiAnalysisService.getWifiByYear(vcode,year);
        }
    }

}
