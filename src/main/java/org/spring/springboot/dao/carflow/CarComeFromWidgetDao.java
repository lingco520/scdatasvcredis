package org.spring.springboot.dao.carflow;

import org.spring.springboot.domain.madeVoBean.carFlow.CarComeFromWidgetVo;

import java.util.List;
import java.util.Map;

/***
 * 车流来源
 * @update zf 20170822
 */
public interface CarComeFromWidgetDao {

    /***
     * @update zf 20170822
     * @param map
     * @return
     */
    List<CarComeFromWidgetVo> getCarComeFromDay2Day(Map map);//选择日期车辆来源排行

    /***
     * @update zf 20170822
     * @param map
     * @return
     */
    List<CarComeFromWidgetVo> getCarComeFromQuarter(Map map);//季度城市车辆排行

    List<CarComeFromWidgetVo> getCarComeFromYear2Year(Map map);//选择年城市车辆来源

    /***
     * @update zf 20170822
     * @param map
     * @return
     */
    List<CarComeFromWidgetVo> getCarComeFromMonth(Map map);//当月城市车辆来源

    String getRegion(String vcode);//查询车流来源景区region

}
