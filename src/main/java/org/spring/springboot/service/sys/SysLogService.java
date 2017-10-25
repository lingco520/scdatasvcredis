package org.spring.springboot.service.sys;

import org.spring.springboot.domain.madeVoBean.SysLogEntity;

/**
 * @author zhouq
 * @email zhouq@daqsoft.com
 * @date 2017-08-21 10:20
 * @Version: v1.0.0
 * @Describe: 日志service
 */
public interface SysLogService {
    void save(SysLogEntity sysLogEntity);
}
