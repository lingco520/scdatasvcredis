package org.spring.springboot.domain.madeVoBean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhouq
 * @email zhouq@daqsoft.com
 * @date 2017-06-29 9:35
 * @Version:
 * @Describe:
 */
public class SysLogEntity implements Serializable {

    private Long id;
    //用户名
    private String username;
    //用户操作
    private String operation;
    //请求方法
    private String method;
    //请求参数
    private String params;
    //IP地址
    private String ip;
    //创建时间

    private Date createDate;
    //异常代码位置
    private String exceptionPosition;
    //异常信息
    private String exceptionMessage;

    private long time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getExceptionPosition() {
        return exceptionPosition;
    }

    public void setExceptionPosition(String exceptionPosition) {
        this.exceptionPosition = exceptionPosition;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SysLogEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", operation='" + operation + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", ip='" + ip + '\'' +
                ", createDate=" + createDate +
                ", exceptionPosition='" + exceptionPosition + '\'' +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                ", time=" + time +
                '}';
    }
}
