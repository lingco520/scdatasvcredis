package org.spring.springboot.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

/**
 * 工具类
 * 1、给传递的参数加密
 * 2、模拟post请求，传递参数，得到返回结果
 * 3、从回调URL的参数中获得visitor_nick值，即nick
 *
 * @author TOP
 */
public class Util {
    /*
     * 二行制转字符串
     */
    private static String byte2hex(byte[] b) {

        StringBuffer hs = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs.append("0").append(stmp);
            else
                hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

    /*


    /*
     * 签名方法，用于生成签名。
     *
     * @param params 传给服务器的参数
     *
     * @param secret 分配给您的APP_SECRET
     */
    public static String sign(TreeMap<String, String> params, String secret) {

        String result = null;
        if (params == null)
            return result;
        Iterator<String> iter = params.keySet().iterator();
        StringBuffer orgin = new StringBuffer(secret);
        while (iter.hasNext()) {
            String name = (String) iter.next();
            orgin.append(name).append(params.get(name));
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            result = byte2hex(md.digest(orgin.toString().getBytes("utf-8")));
        } catch (Exception ex) {
            throw new RuntimeException("sign error !");
        }
        return result;
    }


    public static void responsePrint(HttpServletResponse response, String str) {

        response.setContentType("text/html; charset=utf-8");
        try {
            response.getWriter().print(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Boolean isNullString(Object obj) {
        Boolean bo = false;

        if (obj != null && !"".equals(obj.toString().trim()) && !"null".equals(obj.toString().trim())) {
            bo = true;
        }
        return bo;
    }


    public static Map transToMAP(Map parameterMap) {
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = parameterMap.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
//			  if()
            Object valueObj = entry.getValue();

            if (valueObj == null && valueObj.equals(" ")) {
                if (name.equals("person")) {
                    value = "0";
                } else if (name.equals("leadership")) {
                    value = "0";
                } else
                    value = " ";
            } else if (valueObj instanceof String[]) {

                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
//					  System.out.println(value);
//					  System.out.println(value.equals("null,"));
//					  System.out.println(name.equals("person"));
                if (name.equals("person") && value.equals("0,")) {
//						  System.out.println(value.equals("null"));
//						  System.out.println(name.equals("person"));
                    value = "201";
                } else if (name.equals("leadership") && value.equals("0,"))
                    value = "200";
                else
                    value = value.substring(0, value.length() - 1);

            } else {
                value = valueObj.toString();
            }

            returnMap.put(name, value);
        }
        return returnMap;
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

}