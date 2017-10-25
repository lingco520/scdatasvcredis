package org.spring.springboot.service.wifiAnalysisService.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.spring.springboot.dao.wifiAnalysis.WifiAnalysisMapper;
import org.spring.springboot.redis.RedisCache;
import org.spring.springboot.service.wifiAnalysisService.WifiAnalysisService;
import org.spring.springboot.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tanggm
 * @version V3.1
 * @description
 * @date 2017-06-29 9:58
 */
@Service
public class WifiAnalysisServiceImpl implements WifiAnalysisService {
    @Autowired
    private WifiAnalysisMapper wifiAnalysisMapper;
    // redis中的key值
    private static final String TABLE_NAME = "wifi:";
    private static final String BIG_TABLENAME = "big_wifi:";
    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String DAY = "day";
    private static final String TIME = "time";
    private static final String QUARTER = "quarter";
    private static final String COLON = ":";
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void getWifiByDay(String vcode, String startTime, String endTime) {
        String md5key = DigestUtils.md5Hex(vcode);
        String h = "wifi:wifi:day:getWifiByDay:";
        List<String> timeList = DateUtil.getSubsectionDateList(startTime,endTime);
        for (int i = 0; i <timeList.size() ; i++) {
            String hk = timeList.get(i) + "|" + md5key;
                Map parMap = new HashMap();
                parMap.put("startTime", timeList.get(i)+" 00:00:00");
                parMap.put("endTime", timeList.get(i)+" 23:59:59");
                parMap.put("vcode", vcode);
                Integer count = wifiAnalysisMapper.getWifiByDay(parMap);
                Map resultMap = new HashMap();
                resultMap.put("num", count);
            RedisCache.putHash(redisTemplate, h, hk, resultMap);
        }
    }

    @Override
    public void getWifiByMonth(String vcode, String year, String month, String startTime, String endTime) {
        String md5Vcode = DigestUtils.md5Hex(vcode);
        //获取当前日期时间段的所有日期，查询每日缓存，进行累加得出月缓存
        List<String> list = DateUtil.getSubsectionDateList(startTime,endTime);
            Integer monthCount =0;
            if(list.size()>0 && list!=null){
                //日统计的主KEY
                String datKey = "wifi:wifi:day:getWifiByDay:";
                for (int i = 0; i <list.size() ; i++) {
                    String hk = list.get(i) + "|" + md5Vcode;
                    Map dayMap = new HashMap();
                    dayMap = (Map) RedisCache.getHash(redisTemplate, datKey, hk);
                    if (dayMap != null && dayMap.get("num") != null) {
                        monthCount += Integer.valueOf(dayMap.get("num")+"");
                    }
                }
            }
            Map resultMap = new HashMap();
            resultMap.put("num", monthCount);
        //月统计主KEY
        String monthKey = "wifi:wifi:month:getWifiByMonth:";
        //月统计小KEY
        String montHk = year + "-" + month+"|"+md5Vcode;
        RedisCache.putHash(redisTemplate,monthKey,montHk,resultMap);
    }

    @Override
    public void getWifiByYear(String vcode, String year) {
        String md5Vcode = DigestUtils.md5Hex(vcode);
        String[] month = {"01","02","03","04","05","06","07","08","09","10","11","12"};
            String monthKey = "wifi:wifi:month:getWifiByMonth:";
            int yearCount =0;
            for (int i=0;i<month.length;i++){
                //日统计每天实时人数的小KEY
                String hk = year + "-" + month[i] + "|" + md5Vcode;
                Map dayMap = new HashMap();
                dayMap = (Map) RedisCache.getHash(redisTemplate, monthKey, hk);
                if (dayMap != null && dayMap.get("num") != null) {
                    yearCount += Integer.valueOf(dayMap.get("num")+"");
                }
            }
            Map resultMap = new HashMap();
            resultMap.put("num", yearCount);
        //年统计主KEY
        String yearKey = "wifi:wifi:year:getWifiByYear:";
        //年统计小KEY
        String yearHk = year +"|"+md5Vcode;
        RedisCache.putHash(redisTemplate,yearKey,yearHk,resultMap);
    }

    @Override
    public void getWifiByQuarter(String vcode, String year) {
        List<Map> quarterMonthList = DateUtil.getQuarterMonthByYear(year);
        String md5Vcode = DigestUtils.md5Hex(vcode);
        List<Map> resList = new ArrayList<>();
        for(Map quarter : quarterMonthList){
                List<String> months = (List<String>) quarter.get("months");
                String monthKey = "wifi:wifi:month:getWifiByMonth:";
                int quarterCount =0;
                for (int i=0;i<months.size();i++){
                    //日统计每天实时人数的小KEY
                    String hk = year + "-" + months.get(i) + "|" + md5Vcode;
                    Map dayMap = new HashMap();
                    dayMap = (Map) RedisCache.getHash(redisTemplate, monthKey, hk);
                    if (dayMap != null && dayMap.get("num") != null) {
                        quarterCount += Integer.valueOf(dayMap.get("num")+"");
                    }
                }
                Map resultMap = new HashMap();
                resultMap.put("num", quarterCount);
                resultMap.put("time", quarter.get("quarter"));
                resList.add(resultMap);
        }
        //年统计主KEY
        String yearKey = "wifi:wifi:quarter:getWifiByQuarter:";
        //年统计小KEY
        String yearHk = year +"|"+md5Vcode;
        RedisCache.putHash(redisTemplate,yearKey,yearHk,resList);
    }
}
