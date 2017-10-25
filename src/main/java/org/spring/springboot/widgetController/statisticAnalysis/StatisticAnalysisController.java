package org.spring.springboot.widgetController.statisticAnalysis;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.spring.springboot.config.MyProps;
import org.spring.springboot.service.forecastwarning.ScenicMonitorService;
import org.spring.springboot.timer.ParkingTimer;
import org.spring.springboot.utils.DateUtil;
import org.spring.springboot.utils.annotation.SysLog;
import org.spring.springboot.utils.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zhouq
 * @email zhouq@daqsoft.com
 * @date 2017-06-27 10:53
 * @Version:
 * @Describe: 统计分析微件
 */
@Api("统计分析系统")
@RestController
@RequestMapping("/statisticAnalysis")
public class StatisticAnalysisController {
    @Autowired
    private ScenicMonitorService scenicMonitorService;
    @Autowired
    private ParkingTimer parkingTimer;
    @Autowired
    private MyProps props;
    /**
     * 停车场历史补偿方法
     * @param
     * @return
     */
    @SysLog("停车场redis数据补偿")
    @ApiOperation(value = "停车场redis数据补偿", notes = "停车场redis数据补偿")
    @ApiImplicitParam(name = "date", value = "日期", required = true, paramType = "String")
    @GetMapping(value = "/getParkingHistory")
    public Object getParkingHistory(String startDate, String endDate) throws  Exception{
        try{
            Assert.paramIsBlank(startDate, "开始日期不能为空");
            Assert.paramIsBlank(endDate, "结束日期不能为空");
            // 计算天的数据
            /*String startTime = DateUtil.getDateStrBefore(date);
            String endTime  = date;*/
            Map<String,String> mapVcode=props.getCommonScenic();
            for (Map.Entry<String,String> entryday: mapVcode.entrySet()) {
                final Map.Entry<String,String> entrytodady=entryday;
                String vcode = entrytodady.getValue();
                scenicMonitorService.getParkingByThisHors(vcode,startDate,endDate);
            }
            // 计算月的数据
            // 传入时间的年份
            String year = DateUtil.getCurYearStrByDate(startDate);
            // 当前年份
            String nowyear = DateUtil.getCurYearStr();
            String[] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};
            if(Integer.valueOf(year).compareTo(Integer.valueOf(nowyear)) >= 0){
                String nowmonth = DateUtil.getCurMonthNumStr();
                for(String month : months){
                    if(Integer.valueOf(month).compareTo(Integer.valueOf(nowmonth)) <= 0){
                        parkingTimer.getParkingByMonth(year,month);
                    }
                }
            }else{
                for(String month : months){
                    parkingTimer.getParkingByMonth(year,month);
                }
            }
            // 计算年的数据
            parkingTimer.getParkingByYear(year);
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
        return true;
    }
}
