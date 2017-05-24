package com.biz.std.vo;

/**
 * Description:
 * -
 * Author: Evan  Date: 2017/5/24
 * Email: yonglin.zhu@biz-united.com.cn
 */
public class PageVo {
    /**
     * 默认页大小
     */
    private static final int DEFAULT_PAGE_SIZE = 9;

    private static final int DEFAULT_PAGE_NUMBER = 1;

    // 当前页大小
    private int pageSize = DEFAULT_PAGE_SIZE;

    // 当前第几页
    private int pageIndex = DEFAULT_PAGE_NUMBER;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
