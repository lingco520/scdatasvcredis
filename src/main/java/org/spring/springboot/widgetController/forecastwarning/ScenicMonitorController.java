package org.spring.springboot.widgetController.forecastwarning;

import com.daqsoft.log.util.LogFactory;
import com.daqsoft.log.util.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.spring.springboot.service.forecastwarning.ScenicMonitorService;
import org.spring.springboot.utils.annotation.SysLog;
import org.spring.springboot.utils.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanggm
 * @version V3.1.0
 * @description 景区运行监测
 * @date 2017-06-23 14:40
 */
@Api("景区运行监测")
@RestController
@RequestMapping(value = "/scenicMonitor")
public class ScenicMonitorController {
    @Autowired
    private ScenicMonitorService scenicMonitorService;
    Logger logger = LogFactory.getLogger(ScenicMonitorController.class);
    /**
     * 景点实时人数--补偿历史方法
     * @return
     */
    @SysLog("景点实时人数--补偿历史方法")
    @ApiOperation(value = "景点实时人数", notes = "景点实时人数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vcode", value = "景区代码", required = true, paramType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始日期", required = true, paramType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束日期", required = true, paramType = "String")}
    )
    @RequestMapping(value = "/actualPassengerHistory", method = RequestMethod.GET)
    public Object actualPassengerHistory(String vcode, String startTime, String endTime) throws JsonProcessingException{
        Assert.paramIsBlank(vcode,"景区编码不能为空!");
        Assert.paramIsDate(startTime,"非法的开始日期参数!");
        Assert.paramIsDate(endTime,"非法的结束日期参数!");
        try {
            scenicMonitorService.getScenicSpotsDataAnalysisTimeAllHistory(vcode, startTime, endTime);
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
