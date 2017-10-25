package testcode;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.spring.springboot.utils.DateUtil;
import org.spring.springboot.utils.HttpRequestUtil;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author zhouq
 * @email zhouq@daqsoft.com
 * @date 2017-08-03 15:22
 * @Version:
 * @Describe:
 */
public class TestDemo {

    @Test
    public void te() throws ParseException {
        Date parse = DateUtil.MONTH_SDF.parse("2017-07");
        Date parse1 = DateUtil.MONTH_SDF.parse("2017-06");
        System.out.println(parse.getTime()>parse1.getTime());

    }

    @Test
    public void test1(){
//        Map<String,Integer> m =new HashMap<>();
//        System.out.println(m.get("xsa"));
        String strA = " abcd ", strB = null;
        Optional.ofNullable(strA).ifPresent(System.out::println);
        if(Optional.ofNullable(strA).isPresent()){

        }
        System.out.println(Optional.ofNullable(strA).isPresent());
        System.out.println(Optional.ofNullable(strB).map(String::length).orElse(-1));

    }

    @Test
    public void test2(){
        List<Map> list=new ArrayList<>();
        Map m=new HashMap();
        m.put("entrancetime","2015-05-05 17:26:48");
        m.put("entrancevehicleno","川A12345");
        m.put("exittime","2015-05-05 17:42:29");
        m.put("exitvehicleno","川A12345");
        m.put("vehicletype","小型车zf");
        m.put("parkid","23");
        m.put("province","四川省");
        m.put("city","成都市");
        for(int i=0;i<1;i++){
            list.add(m);
        }
        JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("data",jsonArray);
        String st="vcode=d5034caae86f1081e0e6ae5337e48e9f&data="+jsonObject.toJSONString();

        String s="vcode=d5034caae86f1081e0e6ae5337e48e9f&data={\"data\":[{\"entrancetime\":\"进入停车场时间\",\"entrancevehicleno\":\"进入停车场车牌\",\"exittime\":\"出停车场时间\",\"exitvehicleno\":\"出停车场车牌\",\"vehicletype\":\"车辆类型\",\"parkid\":\"停车场id\",\"province\":\"省份名\",\"city\":\"城市名\"},{\"entrancetime\":\"2015-05-05 17:26:48\",\"entrancevehicleno\":\"川A12345\",\"exittime\":\"2015-05-05 17:42:29\",\"exitvehicleno\":\"川A12345\",\"vehicletype\":\"小型车\",\"parkid\":\"23\",\"province\":\"四川省\",\"city\":\"成都市\"}]}";
      String str=  HttpRequestUtil.sendPost("http://192.168.2.122:8808/saveJqTvpmCheckoutV1",st,false);
        System.out.println(str);
    }


    @Test
    public void test3(){
        List<Map> list=new ArrayList<>();
        Map m=new HashMap();

        m.put("entranceTime","2015-05-05 17:26:48");
        m.put("entranceVehicleNo","川A12345");
        m.put("exitTime","2015-05-05 17:42:29");
        m.put("exitVehicleNo","川A12345");
        m.put("vehicleType","小型车zf");
        m.put("parkid","23");
        m.put("memo","备注");
        m.put("totalMoney","100");
        m.put("deductionMoney","20");
        m.put("paymentMoney","80");
        for(int i=0;i<1;i++){
            list.add(m);
        }
        JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("data",jsonArray);
        String st="vcode=d5034caae86f1081e0e6ae5337e48e9f&data="+jsonObject.toJSONString();

        String s="vcode=d5034caae86f1081e0e6ae5337e48e9f&data={\"data\":[{\"entrancetime\":\"进入停车场时间\",\"entrancevehicleno\":\"进入停车场车牌\",\"exittime\":\"出停车场时间\",\"exitvehicleno\":\"出停车场车牌\",\"vehicletype\":\"车辆类型\",\"parkid\":\"停车场id\",\"province\":\"省份名\",\"city\":\"城市名\"},{\"entrancetime\":\"2015-05-05 17:26:48\",\"entrancevehicleno\":\"川A12345\",\"exittime\":\"2015-05-05 17:42:29\",\"exitvehicleno\":\"川A12345\",\"vehicletype\":\"小型车\",\"parkid\":\"23\",\"province\":\"四川省\",\"city\":\"成都市\"}]}";
        String str=  HttpRequestUtil.sendPost("http://192.168.2.122:8808/saveParkingV1",st,false);
        System.out.println(str);
    }

    @Test
    public void test4(){
        List<Map> list=new ArrayList<>();
        Map m=new HashMap();

        m.put("sort",0);
        m.put("source","1");
        m.put("total","10");
        m.put("usings","20");
        m.put("vehicletype","小型车zf");
        m.put("paking_id","30");
        for(int i=0;i<1;i++){
            list.add(m);
        }
        JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("data",jsonArray);
        String st="vcode=45e692de336b6d661d2a5d1907837af5&data="+jsonObject.toJSONString();
//        String st="vcode=45e692de336b6d661d2a5d1907837af5&data={\"data\":[{\"vehicletype\":\"小型车\",\"parkid\":\"30\",\"total\":284,\"usings\":116,\"source\":1}]}";

        String str=  HttpRequestUtil.sendPost("http://192.168.2.122:8808/saveParkingLotV1",st,false);
        System.out.println(str);
    }

    @Test
    public void test5(){
        List<Map> list=new ArrayList<>();
        Map m=new HashMap();

        m.put("mark","00001");
        m.put("time","2017-09-04 08:20:32");
        m.put("vehicleType",0);
        m.put("vehicleno","川A615H");
        m.put("source",0);
        m.put("province","四川省");
        m.put("city","成都市");
        for(int i=0;i<1;i++){
            list.add(m);
        }
        JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("data",jsonArray);
        String st="vcode=d5034caae86f1081e0e6ae5337e48e9f&data="+jsonObject.toJSONString();

        String str=  HttpRequestUtil.sendPost("http://192.168.2.122:8808/saveCarVehiclelogV1",st,false);
        System.out.println(str);
    }

    @Test
    public void test6(){
        List<Map> list=new ArrayList<>();
        Map m=new HashMap();

        m.put("maxQuantity",50000);
        m.put("name","石象湖zf");
        m.put("source","散客");
        m.put("time","2017-09-04 13:46:52");
        m.put("total",10);

        for(int i=0;i<1;i++){
            list.add(m);
        }
        JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("data",jsonArray);
        String st="vcode=d5034caae86f1081e0e6ae5337e48e9f&data="+jsonObject.toJSONString();

        String str=  HttpRequestUtil.sendPost("http://192.168.2.122:8808/savePopulationV1",st,false);
        System.out.println(str);
    }

    @Test
    public void test7(){
        List<Map> list=new ArrayList<>();
        List<String> types = Arrays.asList("全价票", "半价票", "本地优惠票", "身份证老年卡");
        List<String> counts = Arrays.asList("5", "10", "15", "20");
        Random rand=new Random();
        for(int i=0;i<10;i++){
            Map m=new HashMap();
            String type=types.get(rand.nextInt(types.size()));
            String count=counts.get(rand.nextInt(counts.size()));
            int totalprice=Integer.parseInt(count)*20;

            m.put("status","-99");
            m.put("type", type);
            m.put("money","20");
            m.put("time","2017-09-04 13:46:52");
            m.put("count",count);
            m.put("totalprice",totalprice);
            list.add(m);
        }
        JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("data",jsonArray);
        String st="vcode=45e692de336b6d661d2a5d1907837af5&data="+jsonObject.toJSONString();

        String str=  HttpRequestUtil.sendPost("http://192.168.2.122:8808/saveJqTicketV1",st,false);
        System.out.println(str);
    }

    @Test
    public void test8(){
        List<Map> list=new ArrayList<>();
        Map m=new HashMap();

        m.put("name","驴妈妈zf");
        m.put("time","2017-09-04 13:46:52");
        m.put("usetime","2017-09-04 13:46:52");
        m.put("count","5");
        m.put("unitprice","20");
        m.put("totalprice","100");

        for(int i=0;i<1;i++){
            list.add(m);
        }
        net.sf.json.JSONArray jsonArray=  net.sf.json.JSONArray.fromObject(list);
        net.sf.json.JSONObject jsonObject=new net.sf.json.JSONObject();
        jsonObject.put("data",jsonArray);
        System.out.println(jsonObject.toString());
        System.out.println(jsonObject.toString());
        String st="vcode=45e692de336b6d661d2a5d1907837af5&data="+jsonObject.toString();


        net.sf.json.JSONObject js=new net.sf.json.JSONObject();
        js.put("data",jsonArray);
        System.out.println(js.toString());

//        String str=  HttpRequestUtil.sendPost("http://ota.test.daqsoft.com/saveJqOtaV1",st,false);
        String str=  HttpRequestUtil.sendPost("http://192.168.2.122:8808/saveJqOtaV1",st,false);
        System.out.println(str);
    }

    @Test
    public void test9(){
        List<Map> list=new ArrayList<>();
        Map m=new HashMap();

        m.put("apMac","70.65.82.E3.F2.A0");
        m.put("authenticationMode","wechat");
        m.put("updateTime","2017-07-27 15:02:58");
        m.put("createTime","2017-07-27 15:09:52");
        m.put("userAccount","wechat5CADCF65FD9D");
        m.put("deviceBrand","苹果zf");
        m.put("deviceModel","IOS 10");
        m.put("deviceOS","iOS");
        m.put("deviceType","Phone");
        m.put("deviceMac","5C:AD:CF:65:FD:9D]");
        for(int i=0;i<1;i++){
            list.add(m);
        }
        net.sf.json.JSONArray jsonArray=  net.sf.json.JSONArray.fromObject(list);
        net.sf.json.JSONObject jsonObject=new net.sf.json.JSONObject();
        jsonObject.put("data",jsonArray);
        String st="vcode=45e692de336b6d661d2a5d1907837af5&data="+jsonObject.toString();



        String str=  HttpRequestUtil.sendPost("http://192.168.2.122:8808/saveWifiDeviceV1",st,false);
        System.out.println(str);
    }

    @Test
    public void test10(){
        List<Map> list=new ArrayList<>();
        Map m=new HashMap();

        m.put("createTime","2017-07-27 15:09:52");
        m.put("endTime","2017-07-27 15:09:52");
        m.put("beginTime","2017-07-27 15:09:52");
        m.put("sumMacs","20");
        m.put("sumType","1");
        m.put("buildingCode","游客中心zf");
        m.put("buildingId","2");
        for(int i=0;i<1;i++){
            list.add(m);
        }
        net.sf.json.JSONArray jsonArray=  net.sf.json.JSONArray.fromObject(list);
        net.sf.json.JSONObject jsonObject=new net.sf.json.JSONObject();
        jsonObject.put("data",jsonArray);
        String st="vcode=45e692de336b6d661d2a5d1907837af5&data="+jsonObject.toString();



        String str=  HttpRequestUtil.sendPost("http://192.168.2.122:8808/saveWifiTimesV1",st,false);
        System.out.println(str);
    }

    @Test
    public void test11(){
        List<Map> list=new ArrayList<>();
        Map m=new HashMap();

        m.put("CreateTime","2017-07-27 15:09:52");
        m.put("EndTime","2017-07-27 15:09:52");
        m.put("BeginTime","2017-07-27 15:09:52");
        m.put("TotalNumber",20);
        m.put("OverNumber",10);
        m.put("InNumber",5);
        m.put("buildingcode","游客中心zf");
        m.put("BuildingId",2);
        for(int i=0;i<1;i++){
            list.add(m);
        }
        net.sf.json.JSONArray jsonArray=  net.sf.json.JSONArray.fromObject(list);
        net.sf.json.JSONObject jsonObject=new net.sf.json.JSONObject();
        jsonObject.put("data",jsonArray);
        String st="vcode=45e692de336b6d661d2a5d1907837af5&data="+jsonObject.toString();



        String str=  HttpRequestUtil.sendPost("http://192.168.2.122:8808/saveWifiDetailV1",st,false);
        System.out.println(str);
    }

    @Test
    public void test12(){
        List<Map> list=new ArrayList<>();
        Map m=new HashMap();

        m.put("CreateTime","2017-07-27 15:09:52");
        m.put("EndTime","2017-07-27 15:09:52");
        m.put("BeginTime","2017-07-27 15:09:52");
        m.put("SumTime",20);
        m.put("SumMacs",10);
        m.put("SumType","0");
        m.put("BuildingCode","游客中心zf");
        m.put("BuildingId",2);
        for(int i=0;i<1;i++){
            list.add(m);
        }
        net.sf.json.JSONArray jsonArray=  net.sf.json.JSONArray.fromObject(list);
        net.sf.json.JSONObject jsonObject=new net.sf.json.JSONObject();
        jsonObject.put("data",jsonArray);
        String st="vcode=45e692de336b6d661d2a5d1907837af5&data="+jsonObject.toString();



        String str=  HttpRequestUtil.sendPost("http://192.168.2.122:8808/saveWifiStayV1",st,false);
        System.out.println(str);
    }
}
