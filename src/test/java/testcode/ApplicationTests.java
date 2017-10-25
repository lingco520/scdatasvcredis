package testcode;

/**
 * Created by yangmei on 17/4/12.
 */

import net.sf.json.JSONObject;
import org.junit.Test;
import org.spring.springboot.utils.HttpRequestUtil;
import org.spring.springboot.utils.validator.ValidateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Pattern;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Application.class)
//@MapperScan("org.spring.springboot.dao")
public class ApplicationTests {

    public static String formatList(List<String> list, String delimiter) {
        StringBuilder result = new StringBuilder();
        for (String str : list) {
            result.append(str).append(delimiter);
        }
        result.delete(result.length() - delimiter.length(), result.length()); // 删除末尾多余的 delimiter
        return result.toString();
    }

    @Test
    public void test1() {

        if (ValidateUtil.Validate("1234", ValidateUtil.SIMPLEMONTH)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

    }

    public boolean isDateStringValid(String date) {
        boolean isValidDateStr = false;
        String yyyyMMddFmt = "[0-9]{2}";

        Pattern p = Pattern.compile(yyyyMMddFmt);
        if (p.matcher(date).matches()) {
            SimpleDateFormat sdf = new SimpleDateFormat("DD");
            try {
                sdf.parse(date);
                isValidDateStr = true;
            } catch (ParseException parseExp) {
            }
        }
        return isValidDateStr;
    }

    @Test
    public void test3(){


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("entrancetime","时间");
        jsonObject.put("entrancevehicleno","车票");
        jsonObject.put("entrancevehicleno","川A12345");

        String s = HttpRequestUtil.sendPost("http://192.168.2.125:8020/saveJqTvpmCheckoutV1", "vcode=d5034caae86f1081e0e6ae5337e48e9f&data={\"data\":[{\"entrancetime\":\\\"进入停车场时间\\\",\\\"entrancevehicleno\\\":\\\"进入停车场车牌\\\",\\\"exittime\\\":\\\"出停车场时间\\\",\\\"exitvehicleno\\\":\\\"出停车场车牌\\\",\\\"vehicletype\\\":\\\"车辆类型\\\",\\\"parkid\\\":\\\"停车场id\\\",\\\"province\\\":\\\"省份名\\\",\\\"city\\\":\\\"城市名\\\"},{\\\"entrancetime\\\":\\\"2015-05-05%2017:26:48\\\",\\\"entrancevehicleno\\\":\\\"川A12345\\\",\\\"exittime\\\":\\\"2015-05-05 2017:42:29\\\",\\\"exitvehicleno\\\":\\\"川A12345\\\",\\\"vehicletype\\\":\\\"小型车\\\",\\\"parkid\\\":\\\"23\\\",\\\"province\\\":\\\"四川省\\\",\\\"city\\\":\\\"成都市\\\"}]}", false);

        System.out.println(s);

    }


}

