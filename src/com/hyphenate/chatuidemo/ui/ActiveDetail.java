package com.hyphenate.chatuidemo.ui;

public class ActiveDetail {

    private String name;
    private String id;
    private String time;
    private String all;
    private String person;
    private String flag;
    private String deleteFlag;
    private String myNameOrOther;

    public String getMyNameOrOther() {
        return myNameOrOther;
    }

    public void setMyNameOrOther(String myNameOrOther) {
        this.myNameOrOther = myNameOrOther;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ActiveDetail{" +
                "all='" + all + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", person='" + person + '\'' +
                ", flag='" + flag + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", myNameOrOther='" + myNameOrOther + '\'' +
                '}';
    }
}
