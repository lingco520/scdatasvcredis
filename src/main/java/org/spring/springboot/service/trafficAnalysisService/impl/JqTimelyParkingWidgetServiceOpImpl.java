package org.spring.springboot.service.trafficAnalysisService.impl;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.spring.springboot.dao.carflow.JqTimelyParkingWidgetDao;
import org.spring.springboot.domain.madeVoBean.carFlow.JqTimelyParkingWidget;
import org.spring.springboot.domain.madeVoBean.carFlow.JqTimely_TimeOnTime_TendWidget;
import org.spring.springboot.redis.RedisCache;
import org.spring.springboot.service.trafficAnalysisService.JqTimelyParkingWidgetServiceOp;
import org.spring.springboot.utils.DateUtil;
import org.spring.springboot.utils.PerCentUtil;
import org.spring.springboot.utils.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lianch
 * @version V3.0.0
 * @date 2017-06-07.
 * @describe:景区实时停车场实现类
 */
@Service
public class JqTimelyParkingWidgetServiceOpImpl implements JqTimelyParkingWidgetServiceOp {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private JqTimelyParkingWidgetDao jqTimelyParkingdao;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 实时停车场所占百分比 年月日
     *
     * @param vcode 景区编码
     * @param time  时间
     * @param type  时间类型
     * @return
     * @update zf 20170822
     */
    @Override
    public Object findJqTimelyPercent(String vcode, String time, String type) {
        //大key
        String k = RedisKey.MOUDEL_CARFLOW + RedisKey.CLASS_PARKINGWIDGET + "getJqTimelyPercent:findJqTimelyPercent:";
        //hashkey
        String hk = type + time + DigestUtils.md5Hex(vcode);
        Object obj = RedisCache.getHash(redisTemplate, k, hk);
        if (null == obj) {
            JqTimely_TimeOnTime_TendWidget JqTimelyListPercent = new JqTimely_TimeOnTime_TendWidget();
            String peopleSurplusPercent = "";
            String peopleUsefulPercen = "";
            String peopleSurplusPercentNew = "";
            String peopleUsefulPercentNew = "";
            Map map = new HashMap<>();
            map.put("vcode", vcode);

            Date dates = null;
            DateFormat df = null;
            if (!"".equals(type) && type.equals("day")) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    dates = sdf.parse(time);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //每天
                df = new SimpleDateFormat("yyyy-MM-dd");
                map.put("time", df.format(dates));
                map.put("startTime",df.format(dates)+" 00:00:00");
                map.put("endTime",df.format(dates)+" 23:59:59");
                List<JqTimelyParkingWidget> JqTimelyList = jqTimelyParkingdao.getJqTimelyPercent(map);
                for (JqTimelyParkingWidget parkingList : JqTimelyList) {
                    JqTimely_TimeOnTime_TendWidget jqParking = new JqTimely_TimeOnTime_TendWidget();
                    if ("".equals(parkingList) || parkingList == null) {
//                    peopleSurplusPercentNew = "0";
//                    peopleUsefulPercentNew = "0";
                    } else {
                        peopleSurplusPercentNew = parkingList.getSurplusPaking();
                        peopleUsefulPercentNew = parkingList.getParkingUseFul();
                        JqTimelyListPercent.setParkingSurplusPercent(peopleSurplusPercentNew);
                        JqTimelyListPercent.setParkingUsefulPercent(peopleUsefulPercentNew);
//                        JqTimelyListPercent.add(jqParking);
                    }
                }
            } else if (!"".equals(type) && type.equals("month")) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                try {
                    dates = sdf.parse(time);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //每月
                df = new SimpleDateFormat("yyyy-MM");
                map.put("time", df.format(dates));
                map.put("startTime",df.format(dates)+"-01 00:00:00");
                map.put("endTime",df.format(dates)+"-31 23:59:59");
                List<JqTimelyParkingWidget> JqTimelyList = jqTimelyParkingdao.getJqTimelyPercent(map);
                for (JqTimelyParkingWidget parkingListMonth : JqTimelyList) {
                    JqTimely_TimeOnTime_TendWidget jqParkingMonth = new JqTimely_TimeOnTime_TendWidget();
                    if ("".equals(parkingListMonth) || parkingListMonth == null) {
//                    peopleSurplusPercentNew = "0";
//                    peopleUsefulPercentNew = "0";
                    } else {
                        peopleSurplusPercentNew = parkingListMonth.getSurplusPaking();
                        peopleUsefulPercentNew = parkingListMonth.getParkingUseFul();
                        JqTimelyListPercent.setParkingSurplusPercent(peopleSurplusPercentNew);
                        JqTimelyListPercent.setParkingUsefulPercent(peopleUsefulPercentNew);
//                        JqTimelyListPercent.add(jqParkingMonth);
                    }

                }
            } else if (!"".equals(type) && type.equals("year")) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                try {
                    dates = sdf.parse(time);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //每年
                df = new SimpleDateFormat("yyyy");
                map.put("time", df.format(dates));
                map.put("startTime",df.format(dates)+"-01-01 00:00:00");
                map.put("endTime",df.format(dates)+"-12-31 23:59:59");
                List<JqTimelyParkingWidget> JqTimelyList = jqTimelyParkingdao.getJqTimelyPercent(map);
                for (JqTimelyParkingWidget parkingListMonth : JqTimelyList) {
                    if (null != parkingListMonth) {
                        JqTimely_TimeOnTime_TendWidget jqParkingMonth = new JqTimely_TimeOnTime_TendWidget();
                        if ("".equals(jqParkingMonth) || jqParkingMonth == null) {
//                    peopleSurplusPercentNew = "0";
//                    peopleUsefulPercentNew = "0";
                        } else {
                            peopleSurplusPercentNew = parkingListMonth.getSurplusPaking();
                            peopleUsefulPercentNew = parkingListMonth.getParkingUseFul();
                            JqTimelyListPercent.setParkingSurplusPercent(peopleSurplusPercentNew);
                            JqTimelyListPercent.setParkingUsefulPercent(peopleUsefulPercentNew);
//                            JqTimelyListPercent.add(jqParkingMonth);
                        }

                    }
                }
            }
            RedisCache.putHash(redisTemplate, k, hk, JqTimelyListPercent);
            obj = JqTimelyListPercent;
        }

        return obj;
    }

    /**
     * 实时停车场所占百分比 时间段
     *
     * @param vcode     景区编码
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public Object findJqTimelyBetweenTime(String vcode, String startTime, String endTime) {
        //大key
        String k = RedisKey.MOUDEL_CARFLOW + RedisKey.CLASS_PARKINGWIDGET + "getJqTimelyBetweenTime:findJqTimelyBetweenTime:";
        //hashkey
        String hk = "day" + startTime + endTime + DigestUtils.md5Hex(vcode);
        Object obj = RedisCache.getHash(redisTemplate, k, hk);
        if (null == obj) {
//            List<JqTimely_TimeOnTime_TendWidget> JqTimelyBetween = new LinkedList<JqTimely_TimeOnTime_TendWidget>();
            Map map = new HashMap<>();
            map.put("vcode", vcode);
            Date stTime = null;
            Date enTime = null;
            DateFormat df = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                stTime = sdf.parse(startTime);
                enTime = sdf.parse(endTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            df = new SimpleDateFormat("yyyy-MM-dd");
            map.put("stTime", df.format(stTime)+" 00:00:00");
            map.put("enTime", df.format(enTime)+" 23:59:59");
            String peopleSurplusPercent = "";
            String peopleUsefulPercent = "";
            List<JqTimelyParkingWidget> JqTimelyList = jqTimelyParkingdao.getJqTimelyBetween(map);
            /*不用线程了List<JqTimelyParkingWidget> JqTimelyList = new ArrayList<>();
            //获取区间内所有日期list,用于后面多线程循环读取数据
            List<String> subsectionDateList = DateUtil.getSubsectionDateList(df.format(stTime), df.format(enTime));

            List<Future<List<JqTimelyParkingWidget>>> futureList = new ArrayList<>();
            //使用多线程去拉取每天的数据.
            ExecutorService es = ExecutorServiceUtils.getFixedThreadPool();
            for (String dateStr : subsectionDateList) {
                if(es.isShutdown()){
                    es = ExecutorServiceUtils.getFixedThreadPool();
                }
                futureList.add(es.submit(new JqTimelyTask(vcode, dateStr)));
            }

            for (Future<List<JqTimelyParkingWidget>> listFuture : futureList) {
                try {
                    JqTimelyList.addAll(listFuture.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            es.shutdown();*/
            /*while(!es.isTerminated()) {
                try {
                    Thread.sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/

            JqTimely_TimeOnTime_TendWidget jqParkingMonth = new JqTimely_TimeOnTime_TendWidget();
            for (JqTimelyParkingWidget parkingListBetween : JqTimelyList) {
                if ("".equals(parkingListBetween) || parkingListBetween == null) {
                    peopleSurplusPercent = "0";
                    peopleUsefulPercent = "0";
                } else {
                    peopleSurplusPercent = parkingListBetween.getSurplusPaking();
                    peopleUsefulPercent = parkingListBetween.getParkingUseFul();
                }
                jqParkingMonth.setParkingSurplusPercent(peopleSurplusPercent);
                jqParkingMonth.setParkingUsefulPercent(peopleUsefulPercent);
//                JqTimelyBetween.add(jqParkingMonth);
            }
            RedisCache.putHash(redisTemplate, k, hk, jqParkingMonth);
            obj = jqParkingMonth;
        }


        return obj;
    }

    /**
     * 实时停车场所占百分比 趋势图
     *
     * @param time  时间
     * @param type  时间类型
     * @param vcode 景区编码
     * @return
     * @update zf 20170822
     */
    public Object findCountParkingTrend(String time, String type, String vcode) {
        String parkName="";

        //大key
        String k = RedisKey.MOUDEL_CARFLOW + RedisKey.CLASS_PARKINGWIDGET + "getCountParkingTrend:findCountParkingTrend:";
        //hashkey
        String hk = type + time + DigestUtils.md5Hex(vcode);
        Object obj = RedisCache.getHash(redisTemplate, k, hk);
        if (null == obj) {
            List<JqTimely_TimeOnTime_TendWidget> JqTimelyTrend = new LinkedList<JqTimely_TimeOnTime_TendWidget>();
            List<JqTimely_TimeOnTime_TendWidget> JqTimelyTrend2 = new LinkedList<JqTimely_TimeOnTime_TendWidget>();
            Map map = new HashMap<>();
            map.put("vcode", vcode);
            String peopleSurplusPercent = "";
            String peopleUsefulPercent = "";
            String peopleSurplusPercentNew = "";
            String peopleUsefulPercentNew = "";
            if (!"".equals(type) && type.equals("day")) {
                Date date = null;
                DateFormat df = null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = sdf.parse(time);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                df = new SimpleDateFormat("yyyy-MM-dd");
                map.put("date", df.format(date));
                map.put("startTime",df.format(date)+" 00:00:00");
                map.put("endTime",df.format(date)+" 23:59:59");
                List<JqTimelyParkingWidget> JqTimelyListTrend = jqTimelyParkingdao.getJqTimelyByDay(map);
                if (JqTimelyListTrend != null || JqTimelyListTrend.size() > 1) {
                    for (JqTimelyParkingWidget parkingListTrend : JqTimelyListTrend) {
                        JqTimely_TimeOnTime_TendWidget jqParking = new JqTimely_TimeOnTime_TendWidget();
                        peopleSurplusPercent = Double.valueOf(parkingListTrend.getSurplusPaking()) / Double.valueOf(parkingListTrend.getParkingTotal()) * 100.0D + "";
                        peopleUsefulPercent = Double.valueOf(parkingListTrend.getParkingUseFul()) / Double.valueOf(parkingListTrend.getParkingTotal()) * 100.0D + "";

                        if (peopleSurplusPercent.equals(0) || peopleSurplusPercent.equals("0.0")) {
                            peopleSurplusPercentNew = "0";
                        } else {
                            peopleSurplusPercentNew = PerCentUtil.percent(peopleSurplusPercent);
                        }
                        if (peopleUsefulPercent.equals(0) || peopleUsefulPercent.equals("0.0")) {
                            peopleUsefulPercentNew = "0";
                        } else {
                            peopleUsefulPercentNew = PerCentUtil.percent(peopleUsefulPercent);
                        }
                        jqParking.setParkingSurplusPercent(peopleSurplusPercentNew);
                        jqParking.setParkingUsefulPercent(peopleUsefulPercentNew);
                        jqParking.setChoiceTime(parkingListTrend.getChoiceTime());
                        jqParking.setName(parkingListTrend.getName());
                        JqTimelyTrend.add(jqParking);
                    }
                }
                JqTimelyTrend2=JqTimelyTrend;
            }
            if (!"".equals(type) && type.equals("month")) {
                Date date = null;
                DateFormat df = null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                try {
                    date = sdf.parse(time);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                df = new SimpleDateFormat("yyyy-MM");
                map.put("date", df.format(date));
                map.put("startTime", df.format(date)+"-01 00:00:00");
                map.put("endTime", df.format(date)+"-31 23:59:59");
                List<JqTimelyParkingWidget> JqTimelyListTrend1 = jqTimelyParkingdao.getJqTimelyByMonth(map);
                /*//线程调用 此处不用线程了
                List<JqTimelyParkingWidget> JqTimelyListTrend1 = this.callGetJqTimelyByMonth(vcode, df.format(date));*/
                if (JqTimelyListTrend1 != null || JqTimelyListTrend1.size() > 1) {
                    for (JqTimelyParkingWidget parkingListTrend1 : JqTimelyListTrend1) {
                        JqTimely_TimeOnTime_TendWidget jqParking1 = new JqTimely_TimeOnTime_TendWidget();

                        if(null!=parkingListTrend1.getParkingTotal() && !"0".equals(parkingListTrend1.getParkingTotal())){
                            peopleSurplusPercent = Double.valueOf(parkingListTrend1.getSurplusPaking()) / Double.valueOf(parkingListTrend1.getParkingTotal()) * 100.0D + "";
                            peopleUsefulPercent = Double.valueOf(parkingListTrend1.getParkingUseFul()) / Double.valueOf(parkingListTrend1.getParkingTotal()) * 100.0D + "";
                        }else{
                            peopleSurplusPercent = "0.0";
                            peopleUsefulPercent  = "0.0";
                        }
                        if (peopleSurplusPercent.equals(0) || peopleSurplusPercent.equals("0.0")) {
                            peopleSurplusPercentNew = "0";
                        } else {
                            peopleSurplusPercentNew = PerCentUtil.percent(peopleSurplusPercent);
                        }
                        if (peopleUsefulPercent.equals(0) || peopleUsefulPercent.equals("0.0")) {
                            peopleUsefulPercentNew = "0";
                        } else {
                            peopleUsefulPercentNew = PerCentUtil.percent(peopleUsefulPercent);
                        }
                        jqParking1.setParkingSurplusPercent(peopleSurplusPercentNew);
                        jqParking1.setParkingUsefulPercent(peopleUsefulPercentNew);
                        jqParking1.setChoiceTime(parkingListTrend1.getChoiceTime());
                        jqParking1.setName(parkingListTrend1.getName());
                        JqTimelyTrend.add(jqParking1);
                        parkName=parkingListTrend1.getName();
                    }
                }
                //填充map
                Map<String,JqTimely_TimeOnTime_TendWidget> map1 = new HashMap<>();
                JqTimelyTrend.forEach(item -> map1.put(item.getChoiceTime(),item) );

                //填充每月天数 30天 @update zf 20170925
                Date parse = null;
                try {
                    parse = DateUtil.MONTH_SDF.parse(df.format(date));
                    List<String> dayReport = DateUtil.getDayReport(parse);

                    for (int i=1;i<=dayReport.size();i++) {
                        String day=df.format(date);
                        if(i<10){
                            day += "-0" + i;
                        }else {
                            day += "-" + i;
                        }
                        //循环匹配日期，没获取到就给此日期填充0
                        if (map1.get(day)!=null){
                            JqTimelyTrend2.add(map1.get(day));
                        }else {
                            JqTimely_TimeOnTime_TendWidget jq1 = new JqTimely_TimeOnTime_TendWidget();
                            jq1.setParkingSurplusPercent("0");
                            jq1.setParkingUsefulPercent("0");
                            jq1.setChoiceTime(day);
                            jq1.setName(parkName);
                            JqTimelyTrend2.add(jq1);
                        }
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            if (!"".equals(type) && type.equals("year")) {
                Date date = null;
                DateFormat df = null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                try {
                    date = sdf.parse(time);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                df = new SimpleDateFormat("yyyy");
                map.put("date", df.format(date));
                map.put("startTime", df.format(date)+"-01-01 00:00:00");
                map.put("endTime", df.format(date)+"-12-31 23:59:59");
                List<JqTimelyParkingWidget> JqTimelyListTrend2 = jqTimelyParkingdao.getJqTimelyByYear(map);
                /*//线程调用 此处不用线程了
                List<JqTimelyParkingWidget> JqTimelyListTrend2 = this.callGetJqTimelyByYear(vcode, df.format(date));*/
                if (JqTimelyListTrend2 != null || JqTimelyListTrend2.size() > 1) {
                    for (JqTimelyParkingWidget parkingListTrend2 : JqTimelyListTrend2) {
                        JqTimely_TimeOnTime_TendWidget jqParking2 = new JqTimely_TimeOnTime_TendWidget();
                        if(null!=parkingListTrend2.getParkingTotal() && !"0".equals(parkingListTrend2.getParkingTotal())){
                            peopleSurplusPercent = Double.valueOf(parkingListTrend2.getSurplusPaking()) / Double.valueOf(parkingListTrend2.getParkingTotal()) * 100.0D + "";
                            peopleUsefulPercent = Double.valueOf(parkingListTrend2.getParkingUseFul()) / Double.valueOf(parkingListTrend2.getParkingTotal()) * 100.0D + "";
                        }else{
                            peopleSurplusPercent="0.0";
                            peopleUsefulPercent="0.0";
                        }
                        if (peopleSurplusPercent.equals(0) || peopleSurplusPercent.equals("0.0")) {
                            peopleSurplusPercentNew = "0";
                        } else {
                            peopleSurplusPercentNew = PerCentUtil.percent(peopleSurplusPercent);
                        }
                        if (peopleUsefulPercent.equals(0) || peopleUsefulPercent.equals("0.0")) {
                            peopleUsefulPercentNew = "0";
                        } else {
                            peopleUsefulPercentNew = PerCentUtil.percent(peopleUsefulPercent);
                        }
                        jqParking2.setParkingSurplusPercent(peopleSurplusPercentNew);
                        jqParking2.setParkingUsefulPercent(peopleUsefulPercentNew);
                        jqParking2.setChoiceTime(parkingListTrend2.getChoiceTime());
                        jqParking2.setName(parkingListTrend2.getName());
                        JqTimelyTrend.add(jqParking2);
                        parkName=parkingListTrend2.getName();
                    }
                }


                //填充map
                Map<String,JqTimely_TimeOnTime_TendWidget> map1 = new HashMap<>();
                JqTimelyTrend.forEach(item -> map1.put(item.getChoiceTime(),item) );

                //填充12个月 @update zf 20170925

                for (int i = 1; i <= 12; i++) {
                    String day = df.format(date);
                    if (i < 10) {
                        day += "-0" + i;
                    } else {
                        day += "-" + i;
                    }
                    //循环匹配日期，没获取到就给此日期填充0
                    if (map1.get(day) != null) {
                        JqTimelyTrend2.add(map1.get(day));
                    } else {
                        JqTimely_TimeOnTime_TendWidget jq1 = new JqTimely_TimeOnTime_TendWidget();
                        jq1.setParkingSurplusPercent("0");
                        jq1.setParkingUsefulPercent("0");
                        jq1.setChoiceTime(day);
                        jq1.setName(parkName);
                        JqTimelyTrend2.add(jq1);
                    }
                }

            }
            RedisCache.putHash(redisTemplate, k, hk, JqTimelyTrend2);
            obj = JqTimelyTrend2;
        }


        return obj;
    }
}
