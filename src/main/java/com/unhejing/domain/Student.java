package com.unhejing.domain;

import java.util.Date;

/**
 * com.unhejing.dao
 *
 * @author unhejing
 * @create 2018-04-16 上午11:15
 **/

public class Student {

    //学生id，长度为40
    private String id;

    //学生name，长度为40
    private String name;

    //出生日期(birthday) 日期类型,
    private Date birthday;

    //备注 (description)字符串类型长度 255,
    private String description;

    //平均分(avgscore) 整数类型,
    private int avgscore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvgscore() {
        return avgscore;
    }

    public void setAvgscore(int avgscore) {
        this.avgscore = avgscore;
    }

    @Override
    public String toString() {
        return "StudentDao{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", description='" + description + '\'' +
                ", avgscore=" + avgscore +
                '}';
    }
}
