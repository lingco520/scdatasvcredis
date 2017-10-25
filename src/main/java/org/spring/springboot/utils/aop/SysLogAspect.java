package org.spring.springboot.utils.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.daqsoft.log.util.LogFactory;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.spring.springboot.domain.madeVoBean.SysLogEntity;
import org.spring.springboot.service.sys.SysLogService;
import org.spring.springboot.utils.DateUtil;
import org.spring.springboot.utils.HttpContextUtils;
import org.spring.springboot.utils.IPUtils;
import org.spring.springboot.utils.annotation.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author zhouq
 * @email zhouq@daqsoft.com
 * @date 2017-06-21 11:54
 * @Version: V1.0.0
 * @Describe: 系统日志，切面处理类
 */
@Aspect
@Component
public class SysLogAspect {
    /**
     *
     */
    private com.daqsoft.log.util.Logger logger = LogFactory.getLogger(getClass());

    private static long Warningtime = 300;

    @Autowired
    private SysLogService sysLogService;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(org.spring.springboot.utils.annotation.SysLog)")
    public void logPointCut() {

    }

    /**
     * 前置通知 --记录操作日志(操作账户、ip、操作、方法、参数、时间)
     *
     * @param joinPoint
     */
    @Before("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            SysLog syslog = method.getAnnotation(SysLog.class);

            //请求的方法名
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = signature.getName();
            String methodStr = className + "." + methodName + "()";

            //请求的参数
            String params = "";
            Object[] args = joinPoint.getArgs();
            //sysLog.setParams(params);
            if (args.length > 0) params = JSON.toJSONString(args[0]);

            String ip ="";
            try {
                //获取request
                HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
                //获取IP 地址
                ip = IPUtils.getIpAddr(request);
            }catch (Exception e){

            }

            //用户名..
            String username = "";
            //记录日志

            logger.debug("开始执行-->operation:%s,method:%s,params:%s,create_date:%s,IP:%s ", syslog.value(), methodStr, params, DateUtil.format(new Date(), DateUtil.DATE_TIME_PATTERN),ip);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("日志记录错误:%s", e.getStackTrace());
        }

    }


    /**
     * 后置异常通知-记录异常日志 (记录操作、异常代码位置、异常信息)
     *
     * @param joinPoint
     * @param
     */
////    @AfterThrowing(value = "logPointCut()", throwing = "e")
//    public void afterThrwing(JoinPoint joinPoint, Throwable e) {
//        try {
//            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//            Method method = signature.getMethod();
//            SysLog syslog = method.getAnnotation(SysLog.class);
////        //请求的方法名
////        String className = joinPoint.getTarget().getClass().getName();
////        String methodName = signature.getName();
////        //请求的参数
////        Object[] args = joinPoint.getArgs();
////        if(args.length>0){
////            String params = JSON.toJSONString(args[0]);
////            //sysLog.setParams(params);
////        }
////        System.out.println(e.getStackTrace()[0].toString());
//            // e.printStackTrace();
//
//            logger.error("发现异常-->operation:%s,exceptionPosition:%s,message:%s", syslog.value(), e.getStackTrace()[0].toString(), e.getMessage());
//        } catch (Exception e1) {
//            logger.error("日志记录错误:%s", e1.getStackTrace());
//        }
//    }

    /**
     * 环绕通知
     * 记录操作日志(操作账户、ip、操作、方法、参数、请求时间、异常信息、异常发生位置、整个方法执行时间)
     *
     * @param point
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) {
        Object object;
        SysLogEntity sysLog = new SysLogEntity();
        long start ;
        try {
            MethodSignature signature = (MethodSignature) point.getSignature();

            Method method = signature.getMethod();
            SysLog syslog = method.getAnnotation(SysLog.class);
            if (syslog != null) {
                //注解描述
                sysLog.setOperation(syslog.value());
            }
            //请求的方法名
            String className = point.getTarget().getClass().getName();
            String methodName = signature.getName();

            String methodStr = className + "." + methodName + "()";
            sysLog.setMethod(methodStr);
            String[] parameterNames = signature.getParameterNames();
            Object[] args = point.getArgs();
            if (parameterNames.length > 0) {
                //请求的参数
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < parameterNames.length;i++){
                    jsonObject.put(parameterNames[i],args[i]);
                }
                String params = JSON.toJSONString(jsonObject);
                sysLog.setParams(params);
            }

            try {
                //获取request
                HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
                //获取IP 地址
                sysLog.setIp(IPUtils.getIpAddr(request));
            }catch (Exception e){

            }

            //用户名..
            String username =null;

            //时间
            sysLog.setCreateDate(new Date());
//            sysLog.setUsername(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            start = System.currentTimeMillis();
            //执行原始方法
            object = point.proceed();

            sysLog.setTime(System.currentTimeMillis() - start);
            String str = String.format("执行完成-->operation:%s,method:%s,params:%s,create_date:%s,time:%s,exceptionPosition:%s,exceptionMessage:%s,username:%s,ipaddr:%s",  sysLog.getOperation(), sysLog.getMethod(), sysLog.getParams(), DateUtil.format(sysLog.getCreateDate(), DateUtil.DATE_TIME_PATTERN), sysLog.getTime(), "", "",sysLog.getUsername(), sysLog.getIp());
            if (sysLog.getTime() > Warningtime) {
                logger.warn(str);
            } else {
                logger.info(str);
            }


        } catch (Throwable throwable) {
//            throwable.printStackTrace();
            sysLog.setExceptionPosition(throwable.getStackTrace()[0].toString());
            sysLog.setExceptionMessage(throwable.getMessage());
            logger.error("执行完成-->operation:%s,method:%s,params:%s,create_date:%s,time:%s,exceptionPosition:%s,exceptionMessage:%s,username:%s,ipaddr:%s",  sysLog.getOperation(), sysLog.getMethod(), sysLog.getParams(), DateUtil.format(sysLog.getCreateDate(), DateUtil.DATE_TIME_PATTERN), sysLog.getTime(), sysLog.getExceptionPosition(), sysLog.getExceptionMessage(),sysLog.getUsername(), sysLog.getIp());
            //throwable .printStackTrace();
            //logger.error("执行发生异常");
            throw new RuntimeException(throwable);
        }
        try {
            if (StringUtils.isBlank(sysLog.getOperation()))
                sysLog.setOperation(sysLog.getMethod());

            sysLogService.save(sysLog);
        }catch (Exception e){
            logger.error("保存操作日志错误!");
        }

        return object;

    }
}
