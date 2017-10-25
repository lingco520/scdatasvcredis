package org.spring.springboot.domain.mysqlBean.microWeb;

import java.io.Serializable;

/**
 * Created by liliangyu on 16/8/16.
 */
public class MicHotelVo implements Serializable {
    private Long id;
    private String name;

    public MicHotelVo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
