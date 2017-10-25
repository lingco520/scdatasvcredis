package org.spring.springboot.service.carflow;

import java.util.Map;

/**
 * 车流趋势每年月度每日每月统计
 * Created by lianch on 2017/6/6.
 */
public interface CarFlowService {

    /**
     * 通过vcode查询省份名称
     * @param vcode
     * @return
     */
    String getRegionByVcode(String vcode);

}
