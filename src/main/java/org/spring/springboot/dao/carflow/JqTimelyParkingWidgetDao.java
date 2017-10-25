package org.spring.springboot.dao.carflow;

import org.spring.springboot.domain.madeVoBean.carFlow.JqTimelyParkingWidget;

import java.util.List;
import java.util.Map;

/**
 * 停车场使用
 *
 * @author lianch.
 * @date 2017-06-07 14:37.
 * @update zf 20170822
 */
public interface JqTimelyParkingWidgetDao {
    /**
     * @update zf 20170822
     * @param map
     * @return
     */
    List<JqTimelyParkingWidget> getJqTimelyPercent(Map map);

    /**
     * @update zf 20170822
     * @param map
     * @return
     */
    List<JqTimelyParkingWidget> getJqTimelyBetween(Map map);

    /***
     * @update zf 20170822
     * @param map
     * @return
     */
    List<JqTimelyParkingWidget> getJqTimelyByDay(Map map);
    /***
     * @update zf 20170822
     * @param map
     * @return
     */
    List<JqTimelyParkingWidget> getJqTimelyByMonth(Map map);
    /***
     * @update zf 20170822
     * @param map
     * @return
     */
    List<JqTimelyParkingWidget> getJqTimelyByYear(Map map);
}
