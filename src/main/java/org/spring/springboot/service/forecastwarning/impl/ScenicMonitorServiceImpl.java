package org.spring.springboot.service.forecastwarning.impl;

import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.spring.springboot.dao.realpeople.PassengerFlowDao;
import org.spring.springboot.dao.scenicspotinfo.ScenicSpotInfoDao;
import org.spring.springboot.domain.madeVoBean.ScenicSpotsTimeVo;
import org.spring.springboot.redis.RedisCache;
import org.spring.springboot.service.forecastwarning.ScenicMonitorService;
import org.spring.springboot.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author tanggm
 * @version V1.0.0
 * @description
 * @date 2017-06-23 14:43
 */
@Service
public class ScenicMonitorServiceImpl implements ScenicMonitorService{


    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate  redisTemplate;
    @Autowired
    private ScenicSpotInfoDao scenicSpotInfoDao;
    @Autowired
    private PassengerFlowDao passengerFlowDao;
    //redis中的key值
    private static final String TABLE_NAME = "parkspace:";
    private static final String PARK_DAY = "parking:";
    private static final String DAY = "day";
    private static final String MONTH = "month";
    private static final String YEAR = "year";
    private static final String COLON = ":";

    private static final String TABLE_NAME_SPOTS = "scenicSpots:";
    private static final String BIG_SPOTS_NAME = "spots:";//景点
    private static final String BIG_TEAM_NAME = "team:";//省团队


    @Override
    public void getParkingByThisHors(String vcode, String startTime, String endTime) {
        List<Map> scenicNames = scenicSpotInfoDao.getScenicNamesList(vcode);
        String md5key = DigestUtils.md5Hex(vcode);
        String h = TABLE_NAME + PARK_DAY + DAY + COLON;

        List<String> timeList = DateUtil.getSubsectionDateList(startTime,endTime);
        for (int i = 0; i <timeList.size() ; i++) {
            List<Map<String, Object>> dayMap = new ArrayList<Map<String, Object>>();
            String hk = DAY + "|" + timeList.get(i) + "|" + md5key;
            for (Map rmap : scenicNames) {
                Map parMap = new HashMap();
                parMap.put("parkid", rmap.get("id"));
                parMap.put("startTime", timeList.get(i)+" 00:00:00");
                parMap.put("endTime", timeList.get(i)+" 23:59:59");
                parMap.put("vcode", vcode);
                Integer count = scenicSpotInfoDao.getParkingCount(parMap);
                Map resultMap = new HashMap();
                resultMap.put("name", rmap.get("name") + "");
                resultMap.put("count", count);
                dayMap.add(resultMap);
            }
            RedisCache.putHash(redisTemplate, h, hk, dayMap);
        }
    }

    @Override
    public void getParkingByMonth(String vcode, String year, String month, String startTime, String endTime) {
        List<Map> scenicNames = scenicSpotInfoDao.getScenicNamesList(vcode);
        String md5Vcode = DigestUtils.md5Hex(vcode);
        //获取当前日期时间段的所有日期，查询每日缓存，进行累加得出月缓存
        List<String> list = DateUtil.getSubsectionDateList(startTime,endTime);
        List<Map<String, Object>> monthMap = new ArrayList<Map<String, Object>>();
        for (Map rmap : scenicNames) {
            int monthCount =0;
            if(list.size()>0 && list!=null){
                //日统计的主KEY
                String datKey = TABLE_NAME + PARK_DAY + DAY + COLON;
                for (int i = 0; i <list.size() ; i++) {
                    //日统计每天实时人数的小KEY
                    String hk = DAY + "|" + list.get(i) + "|" + md5Vcode;
                    List<Map> dayMapList = new ArrayList<>();
                    dayMapList = (List<Map>) RedisCache.getHash(redisTemplate, datKey, hk);
                    if (dayMapList != null && dayMapList.size() > 0) {
                        for(Map m : dayMapList){
                            if(rmap.get("name").equals(m.get("name"))){
                                monthCount += Integer.valueOf(m.get("count")+"");
                            }
                        }
                    }
                }
            }
            Map resultMap = new HashMap();
            resultMap.put("name", rmap.get("name") + "");
            resultMap.put("count", monthCount);
            monthMap.add(resultMap);
        }
        //月统计主KEY
        String monthKey = TABLE_NAME+PARK_DAY+MONTH+COLON;
        //月统计小KEY
        String montHk = MONTH + "|" + year + "-" + month+"|"+md5Vcode;
        RedisCache.putHash(redisTemplate,monthKey,montHk,monthMap);
    }

    @Override
    public void getParkingByYear(String vcode, String year) {
        List<Map> scenicNames = scenicSpotInfoDao.getScenicNamesList(vcode);
        String md5Vcode = DigestUtils.md5Hex(vcode);
        String[] month = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        List<Map<String, Object>> yearMap = new ArrayList<Map<String, Object>>();
        for (Map rmap : scenicNames) {
            String monthKey = TABLE_NAME + PARK_DAY + MONTH + COLON;
            int yearCount =0;
            for (int i=0;i<month.length;i++){
                //日统计每天实时人数的小KEY
                String hk = MONTH + "|" + year + "-" + month[i] + "|" + md5Vcode;
                List<Map> dayMapList = new ArrayList<>();
                dayMapList = (List<Map>) RedisCache.getHash(redisTemplate, monthKey, hk);
                if (dayMapList != null && dayMapList.size() > 0) {
                    for(Map m : dayMapList){
                        if(rmap.get("name").equals(m.get("name"))){
                            yearCount += Integer.valueOf(m.get("count")+"");
                        }
                    }
                }
            }
            Map resultMap = new HashMap();
            resultMap.put("name", rmap.get("name") + "");
            resultMap.put("count", yearCount);
            yearMap.add(resultMap);
        }
        //年统计主KEY
        String yearKey = TABLE_NAME+PARK_DAY+YEAR+COLON;
        //年统计小KEY
        String yearHk = YEAR + "|" + year +"|"+md5Vcode;
        RedisCache.putHash(redisTemplate,yearKey,yearHk,yearMap);
    }

    @Override
    public void getScenicSpotsDataAnalysisTimeAllHistory(String vcode, String startTime, String endTime) {
        String h = "passengerflow:scenic:time:getPassengerFlowByScenic:";
        String dateFormat = "%Y-%m-%d %H";
        Map mapMapper = new HashMap();
        String hk = "";
        mapMapper.put("vcode",vcode);
        mapMapper.put("dateFormat",dateFormat);
        mapMapper.put("startTime", startTime+" 00:00:00");
        mapMapper.put("endTime", endTime+" 23:59:59");
        List<ScenicSpotsTimeVo> scenicSpotsTimeVos = passengerFlowDao.getScenicSpotsDataTime(mapMapper);
        for (int i = 0; i < scenicSpotsTimeVos.size(); i++) {
            Map<Object, Object> map = new TreeMap<Object, Object>();
            map.put("time", scenicSpotsTimeVos.get(i).getTime());
            map.put("name", scenicSpotsTimeVos.get(i).getName());
            map.put("count", scenicSpotsTimeVos.get(i).getCount());
            hk = scenicSpotsTimeVos.get(i).getDateTime() + DigestUtils.md5Hex(vcode);
            RedisCache.putHash(redisTemplate, h, hk, map);
        }
    }

    //获取redis查询数量数量
    private String getRedis(String key, String date, String vcode) {
        String str = "";
        String num = "";
        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
        str = (String) hash.get(key, date + vcode);
        //捕获异常
        if ("".equals(str) || str == null) {
            num = "0";
        } else {
            try {
                JSONObject jasonObject = JSONObject.fromObject(str);
                //取nums
                num = jasonObject.getString("nums");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return num;
    }
}
