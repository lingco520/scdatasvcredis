package org.spring.springboot.utils.validator;

import org.apache.commons.lang.StringUtils;
import org.spring.springboot.utils.RRException;

/**
 * 数据校验
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:50
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message,400);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }

    /**
     * 请求参数验证(非空)
     * @param str
     * @param message
     */
    public static void paramIsBlank(String str,String message){
        if (StringUtils.isBlank(str)) {
            //400 参数有误
            throw new RRException(message,400);
        }
    }

    /**
     * 验证是否为日期格式
     * @param str
     * @param message
     */
    public static void paramIsDate(String str,String message){
        paramIsBlank(str,message);
        if(!ValidateUtil.Validate(str,ValidateUtil.DATE)){
            throw new RRException(message,400);
        }
    }

    /**
     * 验证是否为季度格式  2017_1
     * @param str
     * @param message
     * @update zf 20170822
     */
    public static void paramIsQuarter2(String str,String message){
        paramIsBlank(str,message);
        if(!ValidateUtil.Validate(str,ValidateUtil.QUARTER)){
            throw new RRException(message,400);
        }
    }

    /**
     * 验证是否为月份格式
     * @param str
     * @param message
     */
    public static void paramIsMonth(String str,String message){
        paramIsBlank(str,message);
        if(!ValidateUtil.Validate(str,ValidateUtil.MONTH)){
            throw new RRException(message,400);
        }
    }

    /**
     * 验证是否为月份格式
     *
     */
    public static void paramIsSimpleMonth(String str,String message){
        paramIsNumber(str,message);
        if(!ValidateUtil.Validate(str,ValidateUtil.SIMPLEMONTH)){
            throw new RRException(message,400);
        }

    }
    /**
     * 验证是否为季度
     * @param str
     * @param message
     */
    public static void paramIsQuarter(String str,String message){
        paramIsNumber(str,message);
        Integer quarter = Integer.parseInt(str);
        if(!(quarter>0 && quarter<5)){
            throw new RRException(message,400);
        }
    }

    /**
     * 验证是否为年格式
     * @param str
     * @param message
     */
    public static void paramIsYear(String str,String message){
        paramIsBlank(str,message);
        if(!ValidateUtil.Validate(str,ValidateUtil.YEAR)){
            throw new RRException(message,400);
        }
    }

    /**
     * 验证参数是否为数字
     * @param str
     * @param message
     */
    public static void paramIsNumber(String str,String message){
        paramIsBlank(str,message);
        if(!ValidateUtil.Validate(str,ValidateUtil.NUMBER)){
            throw new RRException(message,400);
        }
    }
}
