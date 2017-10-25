package org.spring.springboot.service.passengerFlowService.impl;

import com.daqsoft.log.util.LogFactory;
import com.daqsoft.log.util.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.spring.springboot.context.Constants;
import org.spring.springboot.dao.realpeople.PassengerFlowDao;
import org.spring.springboot.dao.scenicspotinfo.ScenicSpotInfoDao;
import org.spring.springboot.domain.madeVoBean.*;
import org.spring.springboot.redis.RedisCache;
import org.spring.springboot.service.carflow.CarFlowService;
import org.spring.springboot.service.passengerFlowService.PassengerService;
import org.spring.springboot.service.wifiAnalysisService.JqScenicMsgService;
import org.spring.springboot.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: lyl .lrd(2017-08-11)
 * @Date: Created in 2017/6/18.
 * @Version: V3.0.0.
 * @describe:客流分析实现类
 */
@Service
public class PassengerServiceImpl implements PassengerService {

    private static final String SCENICCODE = "scenicCode=410800B010100002";//云台山资源编码
    private Logger logger = LogFactory.getLogger(PassengerServiceImpl.class);
    @Autowired
    private PassengerFlowDao passengerFlowDao;
    @Autowired
    private CarFlowService carFlowService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ScenicSpotInfoDao scenicSpotInfoDao;
    @Autowired
    private JqScenicMsgService jqScenicMsgService;




    /**
     * 生成key（银川花博会）
     *
     * @param i
     * @return
     */
    private String key(int i) {
        String key = "";
        switch(i)
        {
            case 8:
                key = "eight";
            break;
            case 9:
                key = "nine";
            break;
            case 10:
                key = "ten";
            break;
            case 11:
                key = "eleven";
            break;
            case 12:
                key = "twelve";
            break;
            case 13:
                key = "thirteen";
            break;
            case 14:
                key = "fourteen";
            break;
            case 15:
                key = "fifteen";
            break;
            case 16:
                key = "sixteen";
            break;
            case 17:
                key = "seventeen";
            break;
            case 18:
                key = "eighteen";
            break;
        }
        return key;
    }




    /**
     * 获取地图客流来源
     * //TODO  此方法目前只有云台山用。获取的是移动数据
     * @param vcode
     * @param date
     * @return
     */
    @Override
    public List<RealPeopleComeFromTopVo> getPeopleComeTopTen(String vcode, String date) {
        //根据VCODE查询资源编码
        String startTime = date + "-01-01";//开始时间
        String endTime = date + "-12-31";//结束时间
        String condition = "&timeType=5&visitorType=2&startDate=" + startTime + "&endDate=" + endTime;//拼上条件
        String str = HttpRequestUtil.sendGet(Constants.Bigdata + "visitorSource/list", SCENICCODE + condition);//url调用接口
        //转换为json
        JSONObject jasonObject = JSONObject.fromObject(str);
        //遍历封装
        String value = null;
        //创建对象与封装
        value = jasonObject.getString("datas");
        List<RealPeopleComeFromTopVo> list = new LinkedList();
        if (value.equals("[]")) {
            System.out.println("无数据！");
        } else {
            JSONArray json = JSONArray.fromObject(value);
            for (int i = 0; i < json.size(); i++) {
                RealPeopleComeFromTopVo comeFromVo = new RealPeopleComeFromTopVo();
                JSONObject job = json.getJSONObject(i);
                String sourceCity = String.valueOf(job.get("sourceCity"));//来源城市
                if (!"".equals(vcode)) {
                    if (!"".equals(sourceCity) && !sourceCity.equals("未知")) {//过滤来源省份为未知的
                        String sourcecityNew = String.valueOf(job.get("sourcecityNew"));//省份游客数
                        comeFromVo.setSourceCity(sourceCity);
                        comeFromVo.setSourcecityNew(sourcecityNew);
                        list.add(comeFromVo);
                    }
                }
            }
        }
        return list;
    }


    /**
     * 获取地图客流来源
     * 数据来源停车场
     * 每辆车*5人
     * @param vcode
     * @param date
     * @return
     */
    public List<Map<String,Object>> getPeopleComeTopTenCommon(String vcode, String date) {
        //根据VCODE查询资源编码
        Map map = new HashMap();
        map.put("vcode",vcode);
        map.put("time",date);
        List<Map<String,Object>> list = passengerFlowDao.getPeopleComeTopTenCommon(map);
        if(list.size()>0 && list!=null){
            return list ;
        }
        return new ArrayList<>();
    }


    /**
     * 省内游客TOP10
     * @param vcode
     * @param date
     * @return
     */
    @Override
    public List<ProvinceLicensePlateVo> getProvinceLicensePlate(String vcode, String date) {
        Map map = new HashMap();
        map.put("vcode", vcode);
        // 查询景区省份名称
        String pname = carFlowService.getRegionByVcode(vcode);
        map.put("time", date);
        map.put("province", pname);
        List<ProvinceLicensePlateVo>   platVo = passengerFlowDao.getProvinceLicensePlate(map);
            Integer total = 0;
            for (int t = 0; t < platVo.size(); t++) {//获取总数
                total = total + platVo.get(t).getSUMCAR();
            }
            for (int i = 0; i < platVo.size(); i++) {
                double sumCar = Double.valueOf(platVo.get(i).getSUMCAR() * 5);
                double totals = Double.valueOf(total * 5);
                double num = (sumCar / totals) * 100.0D;
                BigDecimal bigDecimal = new BigDecimal(num);
                double dPload = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                platVo.get(i).setPercentage(String.valueOf(dPload) + "%");
                platVo.get(i).setSUMCAR(platVo.get(i).getSUMCAR() * 5);
            }
        return platVo;
    }

    /**
     * 省内游客
     * lrd  2017-08-10
     * 优化sql 方法
     * @param vcode
     * @param year
     * @return
     * //TODO  LRD
     */
    @Override
    public List<ProvinceLicensePlateVo> getProvinceLicensePlateRank(String year, String vcode) {
        //缓存添加
        String k= RedisKey.MOUDEL_BIGDATAPASSENGER+ RedisKey.CLASS_BIG_PASSENGER+"year:getProvincePlateRankTen:getProvinceLicensePlateRank:";
        String hk=year+"|"+vcode;
        Object obj= RedisCache.getHash(redisTemplate,k, hk);
        // 查询景区省份名称
        String pname = carFlowService.getRegionByVcode(vcode);
        Map map = new HashMap();
        map.put("vcode", vcode);
        map.put("startDate", year + "-01-01");
        map.put("endDate", year + "-12-31");
        map.put("province", pname);
        map.put("time",year);
        List<ProvinceLicensePlateVo> platVo = null;
        List<ProvinceLicensePlateVo> platVoRank = new LinkedList<>();
        if(obj==null){
            platVo = passengerFlowDao.getProvinceLicensePlate(map); //省外游客分析top10
            Integer total = 0;
            for (int t = 0; t < platVo.size(); t++) {//获取总数
                total = total + platVo.get(t).getSUMCAR();
            }
            if (!platVo.isEmpty()) {
                //因为2个地方调用了这个方法。这个地方取的top10 所以判断
                int size=0;
                if(platVo.size()<10){
                    size = platVo.size();
                }else{
                    size=10;
                }
                for (int i = 0; i < size; i++) {//获取排名前十的数据
                    ProvinceLicensePlateVo provinceLicensePlateVo = new ProvinceLicensePlateVo();//创建前10封装对象
                    double sumCar = Double.valueOf(platVo.get(i).getSUMCAR() * 5);
                    double totals = Double.valueOf(total * 5);
                    double num = (sumCar / totals) * 100.0D;
                    BigDecimal bigDecimal = new BigDecimal(num);
                    double dPload = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    provinceLicensePlateVo.setPercentage(String.valueOf(dPload));
                    provinceLicensePlateVo.setSUMCAR(platVo.get(i).getSUMCAR() * 5);
                    provinceLicensePlateVo.setCARFROM(platVo.get(i).getCARFROM());
                    platVoRank.add(provinceLicensePlateVo);//封装对象进入 list
                }
                RedisCache.putHash(redisTemplate,k,hk,platVoRank);
            }
        }else{
            platVoRank= (List<ProvinceLicensePlateVo>)obj;
        }
        return platVoRank;
    }

    /**
     * 省外游客
     * 通用
     * @param vcode
     * @param year
     * @return
     * @update zf 20170904
     */
    @Override
    public List<ProvinceLicensePlateVo> getProvinceOutside(String year, String vcode) {
        //缓存添加
        String k = RedisKey.MOUDEL_BIGDATAPASSENGER + RedisKey.CLASS_BIG_PASSENGER + "year:getProvinceOutside:getProvinceOutside:";
        String hk = "year" + "|" + year + "|" + vcode;
        Object obj = RedisCache.getHash(redisTemplate, k, hk);
        // 查询景区省份名称
        String pname = carFlowService.getRegionByVcode(vcode);
        Map map = new HashMap();
        map.put("vcode", vcode);
        map.put("startDate", year + "-01-01");
        map.put("endDate", year + "-12-31");
        map.put("pname", pname);
        List<ProvinceLicensePlateVo> platVo = null;
        List<ProvinceLicensePlateVo> platVoRank = new LinkedList<>();
        if (obj == null) {
            platVo = passengerFlowDao.getProvinceOutside(map); //省外游客分析top10
            Integer total = 0;
            for (int t = 0; t < platVo.size(); t++) {//获取总数
                total = total + platVo.get(t).getSUMCAR();
            }
            if (!platVo.isEmpty()) {
                //因为2个地方调用了这个方法。这个地方取的top10 所以判断
                int size = 0;
                if (platVo.size() < 10) {
                    size = platVo.size();
                } else {
                    size = 10;
                }
                for (int i = 0; i < size; i++) {//获取排名前十的数据
                    ProvinceLicensePlateVo provinceLicensePlateVo = new ProvinceLicensePlateVo();//创建前10封装对象
                    double sumCar = Double.valueOf(platVo.get(i).getSUMCAR() * 5);
                    double totals = Double.valueOf(total * 5);
                    double num = (sumCar / totals) * 100.0D;
                    BigDecimal bigDecimal = new BigDecimal(num);
                    double dPload = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    provinceLicensePlateVo.setPercentage(String.valueOf(dPload));
                    provinceLicensePlateVo.setSUMCAR(platVo.get(i).getSUMCAR() * 5);
                    provinceLicensePlateVo.setCARFROM(platVo.get(i).getCARFROM());
                    platVoRank.add(provinceLicensePlateVo);//封装对象进入 list
                }
                RedisCache.putHash(redisTemplate, k, hk, platVoRank);
            }
        } else {
            platVoRank = (List<ProvinceLicensePlateVo>) obj;
        }
        return platVoRank;
    }

    /**
     * 省外游客来源地
     *  调用的大数据
     *  云台山定制
     * @param vcode
     * @param date
     * @return
     */
    @Override
    public List<RealPeopleComeFromVo> getPeopleComeFrom(String vcode, String date) {
        String startTime = date + "-01-01";
        String endTime = date + "-12-31";
        List<RealPeopleComeFromVo> list = new LinkedList<>();
            String condition = "&timeType=5&visitorType=2&startDate=" + startTime + "&endDate=" + endTime;//拼上条件
            String str = HttpRequestUtil.sendGet(Constants.Bigdata + "visitorSource/list", SCENICCODE + condition);//url调用接口
            //转换hash为json
            JSONObject jasonObject = JSONObject.fromObject(str);
            //遍历封装
            String value = null;
            //创建对象与封装
            value = jasonObject.getString("datas");
            if (value.equals("[]")) {
                System.out.println("无数据！");
            } else {
                JSONArray json = JSONArray.fromObject(value);
                for (int i = 0; i < json.size(); i++) {
                    JSONObject job = json.getJSONObject(i);
                    String sourceCity = String.valueOf(job.get("sourceCity"));//来源城市
                    String sourcecityNew = String.valueOf(job.get("sourcecityNew"));//省份游客数
                    String sourcecityPro = String.valueOf(job.get("sourcecityPro"));//游客占比
                    RealPeopleComeFromVo comeFromVo = FiltrationUtil.getRealPeopleList(vcode, sourceCity, sourcecityNew, sourcecityPro);
                    if (comeFromVo.getSourceCity() != null) {
                        list.add(comeFromVo);
                    } else {
                        continue;
                    }
                }
            }
        return list;
    }

    /**
     * 云台山定制微件数据
     * @param vcode
     * @param dateTime   当前的年份
     * @param open
     * @return
     */
    @Override
    public Map<String, Object> getBigPassengerAll(String vcode, String dateTime,String open) {
        //缓存添加
        String k= RedisKey.MOUDEL_PASSENGERFLOW+"getPeopleComeFrom:getBigPassengerAll:";
        //hashkey
        String hk="year|"+dateTime+"|"+vcode;
        Object obj= RedisCache.getHash(redisTemplate,k, hk);
        Map map = new HashMap();
        //根据VCODE获取景区名称和经纬度
        List<JqScenicAreas> scenicList = jqScenicMsgService.getJqScenicList(vcode);
        if(obj==null || "yes".equals(open)){
            List<RealPeopleComeFromTopVo> comeTopTen = new LinkedList<>();
            List<ProvinceLicensePlateVo> PlateVo = new LinkedList<>();
            List<RealPeopleComeFromVo> comeFromVo = new LinkedList<>();
            try {
                //景区地图来源
                comeTopTen = getPeopleComeTopTen(vcode, dateTime);//调用的大数据
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            try {
                //景区年统计省内客流分析 TOP 10  TODO 省内游客TOP10  非常慢
                PlateVo = getProvinceLicensePlate(vcode, dateTime);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            try {
                //景区年统计省外游客数量 TOP 10 //TODO 省外游客TOP10
                comeFromVo =getPeopleComeFrom(vcode, dateTime);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            map.put("comeFromByMap", comeTopTen);
            map.put("platePeople", PlateVo);
            map.put("comeFromPeople", comeFromVo);
            if(scenicList.size()>0){
                map.put("scenicList",scenicList.get(0));
            }else{
                JqScenicAreas jq = new JqScenicAreas();
                map.put("scenicList",jq);
            }
            RedisCache.putHash(redisTemplate,k,hk,map);
        }else{
            map =(Map) obj;
        }
        return map;
    }


    /**
     * 景区游客来源分析
     * 通用大微件
     * lrd 2017-09-08
     * @param vcode
     * @param dateTime
     * @param open
     * @return
     */
    @Override
    public Map<String, Object> getPeopleYearCommon(String vcode, String dateTime, String open) {
        //缓存添加
        String k= RedisKey.MOUDEL_PASSENGERFLOW+"getPeopleComeFrom:getPeopleYearCommon:";
        //hashkey
        String hk="year|"+dateTime+"|"+vcode;
        Object obj= RedisCache.getHash(redisTemplate,k, hk);
        Map map = new HashMap();
        //根据VCODE获取景区名称和经纬度
        List<JqScenicAreas> scenicList = jqScenicMsgService.getJqScenicList(vcode);
        if(obj==null || "yes".equals(open)){
            List<Map<String,Object>> comeTopTen = new LinkedList<>();
            List<ProvinceLicensePlateVo> PlateVo = new LinkedList<>();
            List<ProvinceLicensePlateVo> comeFromVo = new LinkedList<>();
            try {
                //景区地图来源
                comeTopTen = getPeopleComeTopTenCommon(vcode, dateTime);//通用微件。
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            try {
                //景区年统计省内客流分析 TOP 10  TODO 省内游客TOP10
                PlateVo = getProvinceLicensePlate(vcode, dateTime);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            try {
                //景区年统计省外游客数量 TOP 10 //TODO 省外游客TOP10
                comeFromVo =getProvinceOutside(dateTime,vcode);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            map.put("comeFromByMap", comeTopTen);
            map.put("platePeople", PlateVo);
            map.put("comeFromPeople", comeFromVo);
            RedisCache.putHash(redisTemplate,k,hk,map);
        }else{
            map =(Map) obj;
        }
        if(scenicList.size()>0){
            map.put("scenicList",scenicList.get(0));
        }else{
            JqScenicAreas jq = new JqScenicAreas();
            map.put("scenicList",jq);
        }
        return map;
    }

    //按日方法获取计算(补偿的是6天)
    @Override
    public void getPassengerFlowByDay(String vcode, String startTime, String endTime) {
        Map  map = new HashMap();
        map.put("vcode",vcode);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        List<Map<String, Object>> list = passengerFlowDao.getPassengerFlowByDay(map);
        if(list.size()>0 && list!=null){
            String k = RedisKey.MOUDEL_PASSENGERFLOW+"day:"+"getPassengerFlowByDay:";
                for (int i = 0; i <list.size() ; i++) {
                    String hk = list.get(i).get("time")+"|"+vcode;
                    RedisCache.putHash(redisTemplate,k,hk,list.get(i));
                }
        }
    }
    //按月方法获取计算
    @Override
    public void getPassengerFlowByMonth(String vcode,String month,String startTime,String endTime) {
        //获取当前日期时间段的所有日期，查询每日缓存，进行累加得出月缓存
        List<String> list = DateUtil.getSubsectionDateList(startTime,endTime);
        int result =0;
        if(list.size()>0 && list!=null){
            //日统计的主KEY
            String datKey = RedisKey.MOUDEL_PASSENGERFLOW+"day:"+"getPassengerFlowByDay:";
            for (int i = 0; i <list.size() ; i++) {
                //日统计每天实时人数的小KEY
                String hk = list.get(i)+"|"+vcode;
                Object obj= RedisCache.getHash(redisTemplate,datKey, hk);
                if(obj!=null){
                    Map map = (Map)obj;
                   result += Integer.parseInt(map.get("num")+"");
                }
            }

            //月统计主KEY
            String monthKey = RedisKey.MOUDEL_PASSENGERFLOW+"month:"+"getPassengerFlowByMonth:";
            //月统计小KEY
            String montHk = month+"|"+vcode;
            Map resultMap= new HashMap();
            resultMap.put("num",result);
            RedisCache.putHash(redisTemplate,monthKey,montHk,resultMap);

        }
    }
    //按年方法获取计算（获取月份的缓存进行累加，统计年份缓存）
    @Override
    public void getPassengerFlowByYear(String vcode,String year) {
        //月份统计主KEY
        String monthKey = RedisKey.MOUDEL_PASSENGERFLOW+"month:"+"getPassengerFlowByMonth:";
        int result=0;
        String[] month = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        for (int i=0;i<month.length;i++){
            //月份统计小KEY
            String monthHk = year+"-"+month[i]+"|"+vcode;
            Object obj= RedisCache.getHash(redisTemplate,monthKey, monthHk);
            if(obj!=null){
                Map map = (Map)obj;
                result += Integer.parseInt(map.get("num").toString());
            }
        }
        //年份统计主KEY
        String yearKey = RedisKey.MOUDEL_PASSENGERFLOW+"year:"+"getPassengerFlowByYear:";
        String yearHk = year+"|"+vcode;
        Map<String,Object> resultMap= new HashMap();
        resultMap.put("num",result);
        RedisCache.putHash(redisTemplate,yearKey,yearHk,resultMap);

    }

    //统计景区每天各小时的实时人数
    @Override
    public void getPassengerFlowByHour(String vcode, String stime,String time) {
        //小时统计主KEY
        String hoursKey = RedisKey.MOUDEL_PASSENGERFLOW+"hours:"+"getPassengerFlowByHour:";
        List<String> timeList = DateUtil.getSubsectionDateList(stime,time);
        for (int i = 0; i < timeList.size(); i++) {
            String hourHk = timeList.get(i)+"|"+vcode;
            Map map = new HashMap();
            map.put("time",timeList.get(i));
            map.put("vcode",vcode);
            List<Map<String,Object>> list=passengerFlowDao.getPassengerFlowByHour(map);
            if(list.size()> 0 && list!=null){
                RedisCache.putHash(redisTemplate,hoursKey,hourHk,list);
            }
        }
    }

    //景点实时人数数据的获取
    @Override
    public void getPassengerFlowByScenic(String vcode) {
        String time = DateUtil.getCurDateStr();
        String lastTime = DateTools.addDate(time, -6);
        List<String> timeList = DateUtil.getSubsectionDateList(lastTime, time);
        //获取景区的景点进行循环查询
        List<Map<String,Object>> scenicNames = scenicSpotInfoDao.getScenicMaxquantity(vcode);
        if(scenicNames == null || scenicNames.size() < 1){
            System.out.print("该景区没添加景点数据！");
        }
        Map datamap =new HashMap();
        datamap.put("vcode",vcode);
        //获取每个景点今天--前6天的实时数据
        for (int k  = 0;  k< timeList.size(); k++) {
            List<Map<String,Object>> list = new ArrayList<>();
            for (int i = 0; i < scenicNames.size(); i++) {
                datamap.put("name", scenicNames.get(i).get("name"));
                Map<String, Object> map = new HashMap<>();
                map.put("scenicName", scenicNames.get(i).get("name"));
                datamap.put("time",timeList.get(k));
                Map<String, Object> result = passengerFlowDao.getPassengerFlowByScenic(datamap);
                map.put("num", result.get("num"));
                map.put("maxquantity", scenicNames.get(i).get("maxquantity"));//最大承载量
                list.add(map);
            }
            if(list.size()>0 && list!=null){//景点有数据才存。无数据不存
                //景点统计主KEY
                String scenicKey = RedisKey.MOUDEL_PASSENGERFLOW+"scenic:day:"+"getPassengerFlowByScenic:";
                String scenicHk = timeList.get(k)+"|"+vcode;
                RedisCache.putHash(redisTemplate,scenicKey,scenicHk,list);
            }
            System.out.print(String.format(timeList.get(k)+"-----实时景点人数数据缓存完成！"));
        }
    }

    @Override
    public void getPassengerFlowByThisHours(String vcode, String startTime, String endTime,String time,String year,String month) {
        Map  map = new HashMap();
        map.put("vcode",vcode);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        List<Map<String, Object>> list = passengerFlowDao.getPassengerFlowByThisDay(map);
        if(list.size()>0 && list!=null){
            if(list.get(0).get("num").toString()!="0"){//如果人数为0.没必须走下面方法
                int num = Integer.parseInt(list.get(0).get("num").toString());
                //更新天缓存
                String dayKey = RedisKey.MOUDEL_PASSENGERFLOW+"day:"+"getPassengerFlowByDay:";
                String dayhk = time+"|"+vcode;
                Object dayObject= RedisCache.getHash(redisTemplate,dayKey, dayhk);
                //获取当前缓存中今天的数据  月缓存-之前的当天缓存+最实时的缓存
                int daynum = 0;
                if(dayObject!=null){
                    Map dmap = (Map)dayObject;
                    Map<String,Object> dayMap = new HashMap<>();
                    daynum = Integer.parseInt(dmap.get("num")+"");
                    //存入新缓存
                    dayMap.put("num",num);
                    dayMap.put("time",time);
                    RedisCache.putHash(redisTemplate,dayKey,dayhk,dayMap);
                }
                //更新月缓存
                String monthKey = RedisKey.MOUDEL_PASSENGERFLOW+"month:"+"getPassengerFlowByMonth:";
                String monthhk = month+"|"+vcode;
                Object monthobj= RedisCache.getHash(redisTemplate,monthKey, monthhk);
                if(monthobj!=null){
                    Map mmap = (Map)monthobj;
                    Map<String,Object> monthMap = new HashMap<>();
                    monthMap.put("num",Integer.parseInt(mmap.get("num")+"")-daynum+num);
                    RedisCache.putHash(redisTemplate,monthKey,monthhk,monthMap);
                }
                //更新年缓存
                String yearKey = RedisKey.MOUDEL_PASSENGERFLOW+"year:"+"getPassengerFlowByYear:";
                String yearhk = year+"|"+vcode;
                Object yearobj= RedisCache.getHash(redisTemplate,yearKey,yearhk);
                if(yearobj!=null){
                    Map<String,Object> yearMap = new HashMap<>();
                    Map ymap= (Map)yearobj;
                    yearMap.put("num",Integer.parseInt(ymap.get("num")+"")-daynum+num);
                    RedisCache.putHash(redisTemplate,yearKey,yearhk,yearMap);
                }
            }
        }
    }

   //节假日数据实时人数  lrd 2017-08-19 这样做是保证如果当天是节假日。那么展示的时候，实时人数需求滚动，避免请求这个方法太频繁。
    @Override
    public void getPassengerFlowByHoliay(String vcode, String time,String year) {
        //节假日的主key
        String holidayKey = RedisKey.MOUDEL_PASSENGERFLOW+"holiday:"+"getPassengerFlowByHoliay:";
        String holidayhk = year+"|"+vcode;
        String condition = "&year=" + year;//拼上条件
       //从大数据获取今年的节假日
        String str = HttpRequestUtil.sendGet(Constants.Bigdata + "searchHolidayByYear", condition);//url调用接口
        //转换hash为json
        JSONObject jasonObject = JSONObject.fromObject(str);
        //遍历封装
        JSONArray value = null;
        //创建对象与封装
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Integer result = 0;//节假日总人数
        try {
            value = jasonObject.getJSONArray("datas");
            if (value.size() < 1) {//节假日接口无数据判断
            } else {
                JSONArray json = JSONArray.fromObject(value);
                //日统计的主KEY
                String datKey = RedisKey.MOUDEL_PASSENGERFLOW+"day:"+"getPassengerFlowByDay:";
                Date thisday = df.parse(time);
                for (int i = 0; i < json.size(); i++) {//循环节假日有几天，查询统计数据
                    JSONObject job = json.getJSONObject(i);
                    String dayTime = String.valueOf(job.get("searchDate"));//循环获取节假日的时间
                    //如果当前节假日期小与当前日期，才进行。当前日期和当前日期之后的，不需要计算。如果当前日期是节假日。那么会在节假日实时人数接口处理。保证人数实时。
                    Date holiday = df.parse(dayTime);
                    if(holiday.getTime()<thisday.getTime()){
                        //按日的实时人数key
                        String hk =dayTime+"|"+vcode;
                        Object obj= RedisCache.getHash(redisTemplate,datKey, hk);
                        if(obj!=null){
                            Map map = (Map)obj;
                            result += Integer.parseInt(map.get("num").toString());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        //存入缓存。
        Map  resMap = new HashMap();
        resMap.put("num",result);
        RedisCache.putHash(redisTemplate,holidayKey,holidayhk,resMap);

    }


    @Override
    public void getScenicSpotsDataAnalysisTimeAll(String vcode) {
        SimpleDateFormat TIME_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String h = "passengerflow:scenic:time:getPassengerFlowByScenic:";
        String dateFormat = "%Y-%m-%d %H";
        Date start = new Date();
        Map mapMapper = new HashMap();
        String hk = "";
        String nowDate = DateUtil.getCurDateStr();
        mapMapper.put("vcode",vcode);
        mapMapper.put("dateFormat",dateFormat);
        mapMapper.put("startTime", nowDate+" 00:00:00");
        mapMapper.put("endTime", nowDate+" 23:59:59");
        List<ScenicSpotsTimeVo> scenicSpotsTimeVos = passengerFlowDao.getScenicSpotsDataTime(mapMapper);
        for (int i = 0; i < scenicSpotsTimeVos.size(); i++) {
            Map<Object, Object> map = new TreeMap<Object, Object>();
            map.put("time", scenicSpotsTimeVos.get(i).getTime());
            map.put("name", scenicSpotsTimeVos.get(i).getName());
            map.put("count", scenicSpotsTimeVos.get(i).getCount());
            hk = scenicSpotsTimeVos.get(i).getDateTime() + DigestUtils.md5Hex(vcode);
            RedisCache.putHash(redisTemplate, h, hk, map);
        }
        Date end = new Date();
        logger.info("景区景点数据分析(公共调用存储redis方法).-------开始:%s,结束:%s,耗时:%s ", TIME_SDF.format(start), TIME_SDF.format(end), (end.getTime() - start.getTime()));
    }

    @Override
    public void getPassengerFlowByQuarter(String vcode, String year) {
        //季度统计主KEY
        String monthKey = RedisKey.MOUDEL_PASSENGERFLOW+"month:"+"getPassengerFlowByMonth:";
        List<Map> quarterMonth = DateUtil.getQuarterMonthByYear(year);
        List<Map> resList = new ArrayList<>();
        for (Map qm : quarterMonth){
            Map resMap = new HashMap();
            Integer quarter = Integer.valueOf(qm.get("quarter")+"");
            int result=0;
            //月份统计小KEY
            for(String month : (List<String >)qm.get("months")){
                String monthHk = year+"-"+month+"|"+vcode;
                Object obj= RedisCache.getHash(redisTemplate,monthKey, monthHk);
                if(obj!=null){
                    Map map = (Map)obj;
                    if(map.get("num") != null && !"".equals(map.get("num"))){
                        result += Integer.parseInt(map.get("num")+"");
                    }
                }
            }
            resMap.put("time", quarter);
            resMap.put("num", result);
            resList.add(resMap);
        }

        //年份统计主KEY
        String quarterKey = RedisKey.MOUDEL_PASSENGERFLOW+"quarter:"+"getPassengerFlowByQuarter:";
        String yearHk = year+"|"+vcode;
        RedisCache.putHash(redisTemplate,quarterKey,yearHk,resList);
    }

    @Override
    public void saveYtsPassengerByDay(Map map) {
        passengerFlowDao.saveYtsPassengerByDay(map);
    }

    @Override
    public void savePassengerByDayYts(Map map) {
        passengerFlowDao.savePassengerByDayYts(map);
    }

    @Override
    public void savePassengerByTimeYts(Map map) {
        passengerFlowDao.savePassengerByTimeYts(map);
    }

    @Override
    public void savePeopleRemainByYts(Map map) {
        passengerFlowDao.savePeopleRemainByYts(map);
    }
}
