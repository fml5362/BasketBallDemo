package com.hyphenate.chatuidemo.ui;

public class ActiveDetail {

    private String name;
    private String time;
    private String all;
    private String person;

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
                ", time='" + time + '\'' +
                ", person='" + person + '\'' +
                '}';
    }
}
