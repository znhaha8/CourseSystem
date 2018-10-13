package main.com.WCZZ.entity;

import java.util.Date;

public class Time {

    private Integer graName;

    private Date start;

    private Date end;

    private String type;

    public Integer getGraName() {
        return graName;
    }

    public void setGraName(Integer graName) {
        this.graName = graName;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}