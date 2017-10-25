package org.spring.springboot.dao.wifiAnalysis;


import java.util.Map;

/**
 * @author tanggm
 * @version V3.1
 * @description
 * @date 2017-06-29 9:58
 */
public interface WifiAnalysisMapper {
    Integer getWifiByDay(Map parMap);
}
