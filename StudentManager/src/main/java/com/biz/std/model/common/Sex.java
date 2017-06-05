package com.biz.std.model.common;

/**
 * Description:
 * -
 * Author: Evan  Date: 2017/6/1
 * Email: yonglin.zhu@biz-united.com.cn
 */
public enum Sex {
    Male("男"),
    Female("女");

    private String sex;

    Sex(String value) {
        sex = value;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
