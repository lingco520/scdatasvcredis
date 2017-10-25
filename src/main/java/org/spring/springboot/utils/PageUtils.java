package org.spring.springboot.utils;

import com.daqsoft.commons.responseEntity.PageResponse;

/**
 * Created by lyl on 2017/4/13 0013.
 */

public class PageUtils {

    public static PageResponse page(int pageNo, int pageSize, Long total, int totalPage) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setCurrPage(pageNo);
        pageResponse.setPageSize(pageSize);
        pageResponse.setTotal(total);
        pageResponse.setTotalPage(totalPage);

        return  pageResponse;
    }
}
