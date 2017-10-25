package org.spring.springboot.dao.carflow;


import org.spring.springboot.domain.madeVoBean.carFlow.JqTvpmCheckoutWidget;
import org.spring.springboot.domain.madeVoBean.carFlow.JqTvpm_Percent_TendWidget;

import java.util.List;
import java.util.Map;

/**
 * @Author: zf.
 * @Date: Created in 2017/07/27.
 * @Version: V3.0.0.
 * @describe: 停车场dao
 * @update zf 20170822
 */
public interface JqTvpmCheckoutWidgetDao {

    /***
     * @update zf 20170822
     * @param map
     * @return
     */
    List<JqTvpmCheckoutWidget> getJqTvpmCheckout(Map map);

    /***
     * 基础查询天的方法
     * @param map
     * @return
     */
    List<JqTvpm_Percent_TendWidget> getJqTvpmSumByDay(Map map);


    /***
     * 分组车牌基础查询天的方法
     * @param map
     * @return
     */
    List<JqTvpmCheckoutWidget> getJqTvpmCheckoutByDay(Map map);


}
