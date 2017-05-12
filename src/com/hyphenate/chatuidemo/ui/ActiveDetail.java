package com.hyphenate.chatuidemo.ui;

import java.io.Serializable;

public class ActiveDetail implements Serializable {

    private String name;
    private String id;
    private String starttime;
    private String endtime;
    private String address;
    private String all;
    private String person;
    private String flag;
    private String deleteFlag;
    private String myNameOrOther;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMyNameOrOther() {
        return myNameOrOther;
    }

    public void setMyNameOrOther(String myNameOrOther) {
        this.myNameOrOther = myNameOrOther;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    @Override
    public String toString() {
        return "ActiveDetail{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", all='" + all + '\'' +
                ", person='" + person + '\'' +
                ", flag='" + flag + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", myNameOrOther='" + myNameOrOther + '\'' +
                '}';
    }
}
