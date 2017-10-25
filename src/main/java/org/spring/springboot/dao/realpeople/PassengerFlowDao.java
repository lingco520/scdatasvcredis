package org.spring.springboot.dao.realpeople;

import org.spring.springboot.domain.madeVoBean.ProvinceLicensePlateVo;
import org.spring.springboot.domain.madeVoBean.ScenicSpotsTimeVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: ziy .
 * @Date: Created in 2017/4/13.
 * @Version: V3.0.0.
 * @describe:
 */

@Repository
public interface PassengerFlowDao {
    /**
     * 团队统计
     * @param map
     * @return
     */
    List<ProvinceLicensePlateVo> getProvinceLicensePlate(Map map);

    /**
     * 年统计省外客流
     * @param map
     * @return
     * @update zf 20170904
     */
    List<ProvinceLicensePlateVo> getProvinceOutside(Map map);

    /**
     * 2018-08-11
     * lrd:获取所有景区当天的实时人数
     * 查询范围是当前时间的前天，因为考虑推数据的延迟，需要补偿之前的缓存数据
     * 此方法为共用方法。
     * @return
     */
    List<Map<String,Object>> getPassengerFlowByDay(Map map);

    /**
     * 按小时统计景区实时人数
     * @param map
     * @return
     */
    List<Map<String,Object>>  getPassengerFlowByHour(Map map);

    /**
     * 景点实时人数获取
     * @param map
     * @return
     */
    Map<String,Object> getPassengerFlowByScenic(Map map);


    /**
     * 按小时拉取景区实时人数
     * @param map
     * @return
     */
    List<Map<String,Object>> getPassengerFlowByThisDay(Map map);


    /**
     * 通用地图微件接口
     * 景区游客来源分析
     * 2017-09-08
     * @param map
     * @return
     */
    List<Map<String,Object>> getPeopleComeTopTenCommon(Map map);

    /**
     * 查询景点小时人数
     * @param mapMapper
     * @return
     */
    List<ScenicSpotsTimeVo> getScenicSpotsDataTime(Map mapMapper);

    /**
     * 云台山小时段客流数据模拟(插入)
     * @param map
     */
    void saveYtsPassengerByDay(Map map);

    /**
     * 云台山日客流趋势(插入)
     * @param map
     */
    void savePassengerByDayYts(Map map);

    /**
     * 云台山日客流驻留时长分析--天段(插入)
     * @param map
     */
    void  savePassengerByTimeYts(Map map);

    /**
     * 云台山日客流驻留时长--小时段(插入)
     * @param map
     */
    void savePeopleRemainByYts(Map map);

}
