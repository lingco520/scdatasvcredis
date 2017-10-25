package org.spring.springboot.utils;

import com.daqsoft.commons.responseEntity.DatasResponse;
import com.daqsoft.commons.responseEntity.ResponseBuilder;
import com.daqsoft.log.util.LogFactory;
import com.daqsoft.log.util.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

/**
 * 异常处理器
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午10:16:19
 */
@RestControllerAdvice
public class RRExceptionHandler {
    private Logger logger = LogFactory.getLogger(getClass());

    /**
     * 自定义异常
     * 使用 ResponseBuilder 统一抛出
     * 此处只能记录到“抛出异常”的位置。
     */
    @ExceptionHandler(RRException.class)
    public Object handleRRException(RRException e) {
//        DataResponse r = new DataResponse();
//        r.setCode(e.getCode());
//        r.setMessage(r.getMessage());
//        r.setData(new HashMap<>());
//        r.setResponseTime(System.currentTimeMillis());
//        ObjectMapper objectMapper = new ObjectMapper();
//        String duty = null;
//        try {
//            duty = objectMapper.writeValueAsString(r);
//        } catch (JsonProcessingException e1) {
//            e1.printStackTrace();
//        }
        return ResponseBuilder.custom().success(e.getMessage(), e.getCode()).build();
    }


//    @ExceptionHandler(DuplicateKeyException.class)
//    public DatasResponse handleDuplicateKeyException(DuplicateKeyException e) {
////        logger.error(e.getMessage(), e);
//        return R.error("数据库中已存在该记录");
//    }

//    @ExceptionHandler(AuthorizationException.class)
//    public R handleAuthorizationException(AuthorizationException e) {
////        logger.error(e.getMessage(), e);
//        return R.error("没有权限，请联系管理员授权");
//    }

//    /**
//     * 全局的异常处理
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(Exception.class)
//    public Object handleException(Exception e) {
////        logger.error(e.getMessage(), e);
//        DatasResponse r = new DatasResponse();
//        r.setCode(403);
//        r.setMessage(e.getMessage());
//        r.setResponseTime(System.currentTimeMillis());
//        ObjectMapper objectMapper = new ObjectMapper();
//        String duty = null;
//        try {
//            duty = objectMapper.writeValueAsString(r);
//        } catch (JsonProcessingException e1) {
//            e1.printStackTrace();
//        }
//        return duty;
//    }

    @ExceptionHandler(MultipartException.class)
    public DatasResponse handleMultipartException(MultipartException e) {
//        logger.error(e.getMessage(),e);
        return null;
    }
}
