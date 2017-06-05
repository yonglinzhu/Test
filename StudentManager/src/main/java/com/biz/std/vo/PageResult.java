package com.biz.std.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Description:
 * -
 * Author: Evan  Date: 2017/5/24
 * Email: yonglin.zhu@biz-united.com.cn
 */
public class PageResult<E> {
    private int currentPage = 1;

    private int size = 10;

    private List<E> result;

    private int total;

    public PageResult(int currentPage, int size, List<E> result, int total) {
        this.currentPage = currentPage;
        this.size = size;
        this.result = result;
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<E> getResult() {
        return result;
    }

    public void setResult(List<E> result) {
        this.result = result;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
