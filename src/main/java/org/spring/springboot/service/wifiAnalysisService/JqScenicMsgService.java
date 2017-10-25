package org.spring.springboot.service.wifiAnalysisService;

import org.spring.springboot.domain.madeVoBean.JqScenicAreas;

import java.util.List;

/**
 * @author zhouq
 * @email zhouq@daqsoft.com
 * @date 2017-06-26 18:08
 * @Version:
 * @Describe: 景区service
 */
public interface JqScenicMsgService {


    /**
     * 获取景区列表
     * @param vcode
     * @return
     */
    List<JqScenicAreas> getJqScenicList(String vcode);
}
