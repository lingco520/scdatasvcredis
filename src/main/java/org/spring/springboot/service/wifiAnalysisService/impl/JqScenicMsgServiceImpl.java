package org.spring.springboot.service.wifiAnalysisService.impl;

import org.spring.springboot.dao.jqScenicMsg.JqScenicMsgDao;
import org.spring.springboot.domain.madeVoBean.JqScenicAreas;
import org.spring.springboot.service.wifiAnalysisService.JqScenicMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhouq
 * @email zhouq@daqsoft.com
 * @date 2017-06-26 18:09
 * @Version:
 * @Describe: 景区信息service 实现
 */
@Service
public class JqScenicMsgServiceImpl implements JqScenicMsgService {


    @Autowired
    private JqScenicMsgDao jqScenicMsgDao;



    @Override
    public List<JqScenicAreas> getJqScenicList(String vcode) {
        List list = jqScenicMsgDao.getJqScenicList(vcode);
        return list;
    }
}
