/**
 * @Copyright: <a htef="http://www.daqsoft.com
 * <p>
 * ">成都中科大旗软件有限公司Copyright  2004-2017蜀ICP备08010315号</a>
 * @Warning: 注意：本内容仅限于成都中科大旗软件有限公司内部传阅，禁止外泄以及用于其他的商业目的。
 */
package org.spring.springboot.widgetController.passengerFlow;
import com.daqsoft.commons.responseEntity.ResponseBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.spring.springboot.service.passengerFlowService.PassengerService;
import org.spring.springboot.utils.DateUtil;
import org.spring.springboot.utils.annotation.SysLog;
import org.spring.springboot.utils.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Title:
 * @Author: lrd
 * @Date: 2017/08/14 17:07
 * @Description:实时人数数据补偿接口，如果需要补偿某个景区的当年的数据，
 * 包含当年每天的实时人数，每月的实时人数。每年的实时人数
 * 传入日期时间段，补偿所有天数的数据，并且同步补偿月份，年份的实时人数数据
 * 维护人员调用，当出现原来数据丢失情况，可以调用。其他情况不要调用。
 * @Comment：
 * @see
 * @Version:
 * @since JDK 1.8
 * @Warning:
 */
@Api("景区客流缓存补偿接口")
@RequestMapping("/peopleCompensate")
@RestController
public class PassengerCompensate {

    @Autowired
    private PassengerService passengerService;
    /**
     * 景区实时人数补偿方法
     */
    @SysLog("景区实时人数数据补偿")
    @ApiOperation(value = "实时人数数据补偿", notes = "实时人数数据补偿")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始日期", required = true, paramType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束日期", required = true, paramType = "String"),
            @ApiImplicitParam(name = "vcode", value = "景区代码", required = true, paramType = "String")
    })
    @GetMapping("/people")
    public Object getPassengerFlowByDay(String vcode,String startTime,String endTime) throws Exception{
        Assert.isBlank(startTime, "请输入开始日期！");
        Assert.isBlank(endTime, "请输入结束日期！");
        Assert.isBlank(vcode, "请输入景区编码！");
        System.out.print("景区实时数据补偿开始"+"---");
        String stime = startTime+" 00:00:00";
        String etime = endTime+" 23:59:59";
        passengerService.getPassengerFlowByDay(vcode,stime,etime);
        System.out.print("日期阶段内数据按日统计已补偿完成，开始补偿月份统计数据");
        //按天补偿完之后，按月补偿开始
        getPassengerFlowByMonth(vcode,startTime,endTime);
        Map map = new HashMap<>();
        map.put("message",startTime+"至"+endTime+"时间段内天缓存，月缓存，年缓存数据补偿完成");
        return ResponseBuilder.custom().success("success",200).data(map).build();
    }


    /**
     * 补偿月份数据
     */
    public void getPassengerFlowByMonth(String vcode,String startTime,String endTime) throws Exception{
        //获取日期时间段内的月份
        Calendar instance = Calendar.getInstance();
        List<String> ListMonth = DateUtil.getListMonth(startTime,endTime);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (int i=0;i<ListMonth.size();i++) {
             String stime = ListMonth.get(i)+"-01";
             Date date = df.parse(stime);
             instance.setTime(date);
             instance.add(Calendar.MONTH,1);
             instance.add(Calendar.DATE,-1);
             int end =instance.get(Calendar.DATE);
             String etime = ListMonth.get(i)+"-"+end;
             System.out.print("景区当月实时游客数据补偿------开始");
             passengerService.getPassengerFlowByMonth(vcode,ListMonth.get(i),stime, etime);
             }
             System.out.print("阶段内数据按月统计已补偿完成，开始补偿年份统计数据");
             //月份补偿完成，开始补偿年份数据
             String startYear=startTime.substring(0,4);
             String endYear=endTime.substring(0,4);
             getPassengerFlowByYear(vcode,startYear,endYear);
     }

     /**
     * 补偿年份数据
     */
    public void getPassengerFlowByYear(String vcode,String startYear,String endYear) throws Exception{
        for (int i=Integer.parseInt(startYear);i<=Integer.parseInt(endYear);i++) {
            System.out.print("景区当天实时游客数据统计");
            passengerService.getPassengerFlowByYear(vcode, String.valueOf(i));
            System.out.print("补偿年份统计数据完成，数据补偿全部完成");
        }

    }

    @GetMapping("/peopleHours")
    public Object getPassengerFlowByHours(String vcode,String sdate,String edate) throws Exception{
        Assert.isBlank(sdate, "请输入补偿开始日期！");
        Assert.isBlank(sdate, "请输入补偿结束日期！");
        Assert.isBlank(vcode, "请输入景区编码！");
        passengerService.getPassengerFlowByHour(vcode,sdate,edate);
        System.out.print("补偿当前天数小时段数据完成");
        Map map = new HashMap<>();
        map.put("message","日“小时”段数据补偿完成");
        return ResponseBuilder.custom().success("success",200).data(map).build();
    }

}