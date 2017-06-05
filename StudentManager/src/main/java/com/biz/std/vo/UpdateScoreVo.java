package com.biz.std.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Description:
 * -
 * Author: Evan  Date: 2017/6/5
 * Email: yonglin.zhu@biz-united.com.cn
 */
public class UpdateScoreVo {
    private int id;
    private String type;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
