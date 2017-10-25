package org.spring.springboot.dao.carflow;

/**
 * Created by lianch on 2017/6/6.
 */
public interface BigCarFlowDao {

    /**
     * 查询景区省份名
     * @param vcode
     * @return
     */
    String getRegionByVcode(String vcode);

}
