package org.spring.springboot.widgetController.wifiAnalysis;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.spring.springboot.config.MyProps;
import org.spring.springboot.service.wifiAnalysisService.WifiAnalysisService;
import org.spring.springboot.timer.WifiTimer;
import org.spring.springboot.utils.DateUtil;
import org.spring.springboot.utils.annotation.SysLog;
import org.spring.springboot.utils.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description wifi连接与客流量趋势
 * @author tanggm
 * @version V3.1
 * @date 2017-06-29 9:33
 */
@Api("wifi连接与客流量趋势")
@RestController
@RequestMapping("/wifiPassenger")
public class WifiPassengerController {
    @Autowired
    private WifiAnalysisService wifiAnalysisService;
    @Autowired
    private WifiTimer wifiTimer;
    @Autowired
    private MyProps props; //获取配置微件信息
    /**
     * wifi历史补偿方法
     * @param
     * @return
     */
    @SysLog("WIFI redis数据补偿")
    @ApiOperation(value = "WIFI redis数据补偿", notes = "WIFI redis数据补偿")
    @ApiImplicitParam(name = "date", value = "日期", required = true, paramType = "String")
    @GetMapping(value = "/getWifiHistory")
    public Object getWifiHistory(String startDate, String endDate) throws  Exception{
        try{
            Assert.paramIsBlank(startDate, "开始日期不能为空");
            Assert.paramIsBlank(endDate, "结束日期不能为空");
            // 计算天的数据
            Map<String,String> mapVcode=props.getCommonScenic();
            for (Map.Entry<String,String> entryday: mapVcode.entrySet()) {
                final Map.Entry<String,String> entrytodady=entryday;
                String vcode = entrytodady.getValue();
                wifiAnalysisService.getWifiByDay(vcode,startDate,endDate);
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
                        wifiTimer.getWifiByMonth(year,month);
                    }
                }
            }else{
                for(String month : months){
                    wifiTimer.getWifiByMonth(year,month);
                }
            }
            // 计算季度的数据
            wifiTimer.getWifiByQuarter(year);
            // 计算年的数据
            wifiTimer.getWifiByYear(year);
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
        return true;
    }
}
