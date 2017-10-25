package org.spring.springboot.service.passengerFlowService.impl;

import org.spring.springboot.domain.mysqlBean.Big_RealP_Tend;
import org.spring.springboot.redis.RedisCache;
import org.spring.springboot.service.passengerFlowService.RealBigPeopleService;
import org.spring.springboot.utils.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 客流量分析
 *
 * @author fy
 */
@Service
public class RealBigPeopleServiceImpl implements RealBigPeopleService {
    @Autowired
    private RedisTemplate redisTemplate;

    /***
     * 每年月度客流趋势
     * @update lrd 20170920
     * @param vcode
     * @return
     */
    @Override
    public Object getPassengerFlowByMonth(String year, String vcode) {
        List<Big_RealP_Tend> propleVoList = new ArrayList<>();
        //月统计主KEY
        String monthKey = RedisKey.MOUDEL_PASSENGERFLOW+"month:"+"getPassengerFlowByMonth:";
        String[] monthTime ={"01","02","03","04","05","06","07","08","09","10","11","12"};
        //月统计小KEY
        for (int i = 0; i < monthTime.length; i++) {
            Big_RealP_Tend tend = new Big_RealP_Tend();
            String montHk = year+"-"+monthTime[i]+"|"+vcode;
            Object obj= RedisCache.getHash(redisTemplate,monthKey, montHk);
            if(obj!=null){
                Map map = (Map)obj;
                tend.setNum(map.get("num").toString());
                tend.setTime(year+"-"+monthTime[i]);
            }else{
                tend.setNum("0");
                tend.setTime(year+"-"+monthTime[i]);
            }
            propleVoList.add(tend);
        }
        return propleVoList;

    }

    /***
     * 每月客流趋势
     * @update lrd 20170920
     * @param date
     * @param vcode
     * @return
     */
    @Override
    public Object getPassengerFlowByMonth_day(String date, String vcode) {
        List<Big_RealP_Tend> list = new ArrayList<>();
        String datKey = RedisKey.MOUDEL_PASSENGERFLOW+"day:"+"getPassengerFlowByDay:";
        String[] day ={"01","02","03","04","05","06","07","08","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"
        };
        for (int i = 0; i <day.length ; i++) {
            Big_RealP_Tend tend = new Big_RealP_Tend();
            String hk = date+"-"+day[i]+"|"+vcode;
            Object dayObj= RedisCache.getHash(redisTemplate,datKey, hk);
            if(dayObj!=null){
                Map map = (Map)dayObj;
                tend.setTime(date+"-"+day[i]);
                tend.setNum(map.get("num").toString());
            }else{
                tend.setTime(date+"-"+day[i]);
                tend.setNum("0");
            }
            list.add(tend);
        }
             return list;
    }
}
