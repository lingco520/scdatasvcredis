package org.spring.springboot.service.trafficAnalysisService.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.spring.springboot.dao.carflow.BigCarFlowWidgetDao;
import org.spring.springboot.domain.madeVoBean.CarTypeVo;
import org.spring.springboot.domain.madeVoBean.carFlow.CarFlow_Change_Time_TendWidget;
import org.spring.springboot.redis.RedisCache;
import org.spring.springboot.service.trafficAnalysisService.CarTypeWidgetOpService;
import org.spring.springboot.utils.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zf.
 * @Date: Created in 2017/07/28.
 * @Version: V3.0.0.
 * @describe: 车辆类型实现类
 */
@Service
public class CarTypeWidgetOpServiceImpl implements CarTypeWidgetOpService {
    @Autowired
    private BigCarFlowWidgetDao bigCarFlowDao;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取汽车数量小时数据
     *
     * @param vcode 景区编码
     * @param day   日期
     * @param type  汽车类型
     * @return
     * @update zf 20170822
     */
    @Override
    public Object getCarTypeHour(String vcode, String day, String type) {
        //大key
        String k = RedisKey.MOUDEL_CARFLOW + RedisKey.CLASS_CARTYPEANALYS + "day:getCarTypeHour:";
        //hashkey
        String hk = "day" + type + day + DigestUtils.md5Hex(vcode);
        Object obj = RedisCache.getHash(redisTemplate, k, hk);
        if (null == obj) {
            List<CarTypeVo> carTypeVos = new ArrayList();
            //如果参数有一个为空，返回空集合
            if (StringUtils.isBlank(vcode) || StringUtils.isBlank(day) || StringUtils.isBlank(type)) {
                return carTypeVos;
            }
            Map<String, Object> map = new HashMap();
            map.put("vcode", vcode);
            map.put("day", day);
            map.put("type", type);//车型转换
            map.put("startTime", day + " 00:00:00");
            map.put("endTime", day + " 23:59:59");
            List<CarFlow_Change_Time_TendWidget> carFlow_change_time_tends = bigCarFlowDao.getCarTypeHour(map);
            convertData(carTypeVos, carFlow_change_time_tends);
            RedisCache.putHash(redisTemplate, k, hk, carTypeVos);
            obj = carTypeVos;
        }

        return obj;
    }
    /**
     * 转换数据
     *
     * @param carTypeVos                需要返回的数据
     * @param carFlow_change_time_tends 查询出来的数据
     */
    private void convertData(List<CarTypeVo> carTypeVos, List<CarFlow_Change_Time_TendWidget> carFlow_change_time_tends) {
        for (CarFlow_Change_Time_TendWidget obj : carFlow_change_time_tends) {
            CarTypeVo carTypeVo = new CarTypeVo();
            carTypeVo.setTime(obj.getTime());
            carTypeVo.setNum(Long.valueOf(obj.getNum()));
            carTypeVos.add(carTypeVo);
        }
    }
}
