package org.spring.springboot.dao.carflow;


import org.spring.springboot.domain.mysqlBean.JqScenicTimelyPopulation;

import java.util.List;
import java.util.Map;

/**
 * 散客统计
 * @author zf
 * @version V1.0.0
 * @date 2017-07-28
 */
public interface JqScenicTimelyPopulationWidgetDao {
    /***
     * @update zf 20170822
     * @param map
     * @return
     */
    List<JqScenicTimelyPopulation> getScenicTimePeople(Map map);
}
