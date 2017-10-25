package org.spring.springboot.timer;

import org.spring.springboot.config.MyProps;
import org.spring.springboot.service.forecastwarning.ScenicMonitorService;
import org.spring.springboot.utils.DateUtil;
import org.spring.springboot.utils.annotation.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author tanggm
 * @version V3.0.0
 * @date 2017-08-22
 * 统计停车场
 */
@Component
public class ParkingTimer {
    @Autowired
    private MyProps props;
    @Autowired
    private ScenicMonitorService scenicMonitorService;
    /**
     * 更新停车场天的数据，用于跨时间查询
     * 1小时更新一次
     */
    @SysLog
    @Async
    @Scheduled(initialDelay = 20000L , fixedRate = 1200000L)
    public void getParkingByThisHors() throws Exception{
        // 计算天的数据
        String startTime = DateUtil.getDateBefore();
        String endTime  = DateUtil.getCurDateStr();
        Map<String,String> mapVcode=props.getCommonScenic();
        for (Map.Entry<String,String> entryday: mapVcode.entrySet()) {
            final Map.Entry<String,String> entrytodady=entryday;
            String vcode = entrytodady.getValue();
            scenicMonitorService.getParkingByThisHors(vcode,startTime,endTime);
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
                    getParkingByMonth(year,month);
                }
            }
        }else{
            for(String month : months){
                getParkingByMonth(year,month);
            }
        }
        // 计算年的数据
        getParkingByYear(year);
    }

    /**
     * 计算月的数据
     * @throws Exception
     */
    public void getParkingByMonth(String year, String month) throws Exception{
        String startTime = year+"-"+month+"-01";
        int days = DateUtil.getDaysByYearMonth(Integer.valueOf(year), Integer.valueOf(month));
        String endTime = year+"-"+month+"-"+days;
        Map<String,String> map=props.getCommonScenic();
        for (Map.Entry<String,String> entry : map.entrySet()) {
            String vcode = entry.getValue();
            scenicMonitorService.getParkingByMonth(vcode, year, month,startTime, endTime);
        }
    }
    /**
     * 计算年的数据
     * @throws Exception
     */
    public void getParkingByYear(String year) throws Exception{
        Map<String,String> map=props.getCommonScenic();
        for (Map.Entry<String,String> entry : map.entrySet()) {
            String vcode = entry.getValue();
            scenicMonitorService.getParkingByYear(vcode,year);
        }
    }

}
