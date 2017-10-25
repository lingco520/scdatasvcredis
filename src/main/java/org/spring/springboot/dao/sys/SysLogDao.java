package org.spring.springboot.dao.sys;

import org.spring.springboot.domain.madeVoBean.SysLogEntity;

/**
 * @author zhouq
 * @email zhouq@daqsoft.com
 * @date 2017-08-21 10:15
 * @Version: v1.0.0
 * @Describe: 日志 dao
 */
public interface SysLogDao {
    void save(SysLogEntity dto);
}
