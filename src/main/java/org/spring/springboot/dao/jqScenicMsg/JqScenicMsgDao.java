package org.spring.springboot.dao.jqScenicMsg;

import org.spring.springboot.domain.madeVoBean.JqScenicAreas;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhouq
 * @email zhouq@daqsoft.com
 * @date 2017-06-26 17:57
 * @Version:
 * @Describe: 景区信息DAO
 */
@Repository
public interface JqScenicMsgDao {

    /**
     * 获取景区相关信息
     * @param vcode
     * @return
     */
    List<JqScenicAreas> getJqScenicList(String vcode);
}
