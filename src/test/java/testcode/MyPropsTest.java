package testcode;

import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.spring.springboot.Application;
import org.spring.springboot.config.MyProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * @author zhouq
 * @email zhouq@daqsoft.com
 * @date 2017-09-06 15:29
 * @Version:
 * @Describe:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@MapperScan("org.spring.springboot.dao")
public class MyPropsTest {

    @Autowired
    private MyProps myProps;

    @Test
    public void test1(){
        Map<String, Map<String, String>> localAndField = myProps.getLocalAndField();

        System.out.println(JSONObject.fromObject(localAndField).toString());
    }

    @Test
    public void test2(){
        Map<String, Map<String, String>> touristType = myProps.getTouristType();

        System.out.println(JSONObject.fromObject(touristType).toString());
    }
}
