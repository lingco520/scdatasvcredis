package org.spring.springboot.service.trafficAnalysisService.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.spring.springboot.dao.carflow.BigCarFlowWidgetDao;
import org.spring.springboot.domain.madeVoBean.carFlow.Big_CarFlow_WidgetTend;
import org.spring.springboot.redis.ExecutorServiceUtils;
import org.spring.springboot.redis.RedisCache;
import org.spring.springboot.service.trafficAnalysisService.BigCarFlowAnalyseService;
import org.spring.springboot.service.trafficAnalysisService.task.CarFlowQsTask;
import org.spring.springboot.utils.DateUtil;
import org.spring.springboot.utils.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 车流趋势实现类
 *
 * @author lianch
 *         Created by Administrator on 2017/6/6.
 */
@Service
public class BigCarFlowAnalyseServiceImpl implements BigCarFlowAnalyseService {

    @Autowired
    private BigCarFlowWidgetDao bigCarFlowDao;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 分段日期车流趋势
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param type      时间段类型
     * @param vcode     景区编码
     * @return
     * @update zf 20170802
     * @update zf 20170822
     */
    @Override
    public Object findCarFlowByDay(String startTime, String endTime, String type, String vcode) {
        //大key
        String k = RedisKey.MOUDEL_CARFLOW + RedisKey.CLASS_BIGCARFLOW + "getFlowCarbyDay:findCarFlowByDay:";
        //hashkey
        String hk = type + startTime + endTime + DigestUtils.md5Hex(vcode);
        Object obj = RedisCache.getHash(redisTemplate, k, hk);
        if (null == obj) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            map.put("vcode", vcode);

            //根据type调用不同的方法，暂时只支持日时间段查询
            List<Big_CarFlow_WidgetTend> returnList = new ArrayList();
            if ("day".equals(type)) {
                List<Big_CarFlow_WidgetTend> big_carFlow_tends = new ArrayList();
//                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//                big_carFlow_tends = bigCarFlowDao.findCarFlowByDay(map);

                //获取区间内所有日期list,用于后面多线程循环读取数据
                List<String> subsectionDateList = DateUtil.getSubsectionDateList(startTime, endTime);

                List<Future<List<Big_CarFlow_WidgetTend>>> futureList = new ArrayList<>();
                //使用多线程去拉取每天的数据.
                ExecutorService es = ExecutorServiceUtils.getFixedThreadPool();
                for (String dateStr : subsectionDateList) {
                    if(es.isShutdown()){
                        es = ExecutorServiceUtils.getFixedThreadPool();
                    }
                    futureList.add(es.submit(new CarFlowQsTask(vcode, dateStr)));
                }

                for (Future<List<Big_CarFlow_WidgetTend>> listFuture : futureList) {
                    try {
                        big_carFlow_tends.addAll(listFuture.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
                es.shutdown();
                /*while(!es.isTerminated()) {
                    try {
                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/

                for (Big_CarFlow_WidgetTend ob : big_carFlow_tends) {
                    /*try {
                        ob.setTime(sdf2.format(sdf1.parse(ob.getTime())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }*/
                    returnList.add(ob);
                }

            } else if ("year".equals(type)) {
                map.put("startTime", startTime + "-01-01 00:00:00");
                map.put("endTime", endTime + "-12-31 23:59:59");
                returnList = bigCarFlowDao.findCarFlowByYear(map);
//                returnList=carFlowWidgetService.getByYear(vcode,startTime);
            }
            RedisCache.putHash(redisTemplate, k, hk, returnList);
            obj = returnList;
        }


        return obj;
    }

    /**
     * 每年季度车流趋势
     *
     * @param date  时间
     * @param vcode 景区编码
     * @return
     * @update zf 20170822
     */
    @Override
    public Object findCarFlowQuarter(String date, String vcode) {
        //大key
        String k = RedisKey.MOUDEL_CARFLOW + RedisKey.CLASS_BIGCARFLOW + "getCarFlowQuarter:findCarFlowQuarter:";
        //hashkey
        String hk = "year" + date + DigestUtils.md5Hex(vcode);
        Object obj = RedisCache.getHash(redisTemplate, k, hk);
        if (null == obj) {
            Date dates = null;
            DateFormat df = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            try {
                dates = sdf.parse(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //每年
            df = new SimpleDateFormat("yyyy");
            List<Big_CarFlow_WidgetTend> big_carFlow_tends = new ArrayList<Big_CarFlow_WidgetTend>();
            Map map = new HashMap();
            map.put("date", df.format(dates));
            map.put("vcode", vcode);
            Map m = bigCarFlowDao.find_quarter_date(map);
            Big_CarFlow_WidgetTend tend1 = new Big_CarFlow_WidgetTend();
            tend1.setTime("1");
            tend1.setSumCar(m.get("q1").toString());
            big_carFlow_tends.add(tend1);
            Big_CarFlow_WidgetTend tend2 = new Big_CarFlow_WidgetTend();
            tend2.setTime("2");
            tend2.setSumCar(m.get("q2").toString());
            big_carFlow_tends.add(tend2);
            Big_CarFlow_WidgetTend tend3 = new Big_CarFlow_WidgetTend();
            tend3.setTime("3");
            tend3.setSumCar(m.get("q3").toString());
            big_carFlow_tends.add(tend3);
            Big_CarFlow_WidgetTend tend4 = new Big_CarFlow_WidgetTend();
            tend4.setTime("4");
            tend4.setSumCar(m.get("q4").toString());
            big_carFlow_tends.add(tend4);
            RedisCache.putHash(redisTemplate, k, hk, big_carFlow_tends);
            obj = big_carFlow_tends;
        }

        /*List<Big_CarFlow_WidgetTend> big_carFlow_tends = new ArrayList<Big_CarFlow_WidgetTend>();
        if (vcode == null || date == null) {
            return big_carFlow_tends;
        }
        //拼接redis的hashKey
        String key = strToFormatStr(date, "month") + DigestUtils.md5Hex(vcode);
        //从redis中获取数据
        Object resultStr = getDataFromRedis("carflow:big_carflow:quarter:", key);
        if (resultStr == null) {
            return big_carFlow_tends;
        }
        JSONObject resultJson = JSONObject.fromObject(resultStr);
        if (resultJson == null) {
            return big_carFlow_tends;
        }
        for (Object jsonKey : resultJson.keySet()) {
            Big_CarFlow_WidgetTend big_CarFlow_Tend = new Big_CarFlow_WidgetTend();
            Object value = resultJson.get(jsonKey);
            //若key值为空，则直接不用往前端返数据
            if (jsonKey == null) {
                continue;
            }
            if ("第一季度".contains(jsonKey.toString())) {
                big_CarFlow_Tend.setTime("1");
            } else if ("第二季度".contains(jsonKey.toString())) {
                big_CarFlow_Tend.setTime("2");
            } else if ("第三季度".contains(jsonKey.toString())) {
                big_CarFlow_Tend.setTime("3");
            } else if ("第四季度".contains(jsonKey.toString())) {
                big_CarFlow_Tend.setTime("4");
            }
            big_CarFlow_Tend.setSumCar(String.valueOf(value));
            big_carFlow_tends.add(big_CarFlow_Tend);
        }*/
        return obj;
    }

}
