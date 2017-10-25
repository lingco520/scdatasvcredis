package org.spring.springboot.service.carflow.impl;

import org.spring.springboot.dao.carflow.BigCarFlowDao;
import org.spring.springboot.service.carflow.CarFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 车流趋势每年月度每日每月统计
 * Created by lianch on 2017/6/6.
 */
@Service
public class CarFlowServiceImpl implements CarFlowService {

    @Autowired
    private BigCarFlowDao bigCarFlowDao;

    @Override
    public String getRegionByVcode(String vcode) {
        return bigCarFlowDao.getRegionByVcode(vcode);
    }
}
