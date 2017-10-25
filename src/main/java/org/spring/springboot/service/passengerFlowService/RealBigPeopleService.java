package org.spring.springboot.service.passengerFlowService;

/**
 * Created by Administrator on 2016/7/28 0028. 客流分析——年月日
 */
public interface RealBigPeopleService {

	/**
	 * 按年月日不同时段查询客流人次
	 *
	 * @return
	 */
	Object getPassengerFlowByMonth(String date, String vcode);

	/***
	 * 每月客流趋势
	 * @update lrd 20170920
	 * @param vcode
	 * @return
	 */
	Object getPassengerFlowByMonth_day(String month, String vcode) ;

 }
