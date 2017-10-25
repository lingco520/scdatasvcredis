package org.spring.springboot.dao.scenicspotinfo;

import java.util.List;
import java.util.Map;

/**
 * @Author: yangkang .
 * @Date: Created in 2017/4/13.
 * @Version: V3.0.0.
 * @describe:景区资讯dao
 */
public interface ScenicSpotInfoDao {

    /**
     * 获取各景点的最大承载量
     */
    List<Map<String,Object>> getScenicMaxquantity(String vcode);

    List<Map> getScenicNamesList(String vcode);

    Integer getParkingCount(Map parMap);



}
