package org.spring.springboot.service.comprehensiveService;

import org.spring.springboot.domain.madeVoBean.TrafficStatisticsVo;
import org.spring.springboot.domain.mysqlBean.WeChatCountIndex;

import java.util.List;

/**
 * @Author: ziy .
 * @Date: Created in 2017/4/13.
 * @Version: V3.0.0.
 * @describe:公共资源数据service
 */


public interface CommonResourceService {
    List<TrafficStatisticsVo> getWebCount(String type, String vcode);

    List<WeChatCountIndex> getWechatCount(String vcode);

    /**
     * 获取景区官网，微官网的浏览量
     * @param vcode
     * @return
     */
    List  getStatsticsCount(String vcode,String open);


}
