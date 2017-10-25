package org.spring.springboot.service.sys.impl;

import org.spring.springboot.dao.sys.SysLogDao;
import org.spring.springboot.domain.madeVoBean.SysLogEntity;
import org.spring.springboot.service.sys.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhouq
 * @email zhouq@daqsoft.com
 * @date 2017-08-21 10:21
 * @Version: v1.0.0
 * @Describe: 日志service
 */
@Service
public class SysLogServiceImpl implements SysLogService{

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void save(SysLogEntity sysLogEntity) {
        sysLogDao.save(sysLogEntity);
    }
}
