package main.com.WCZZ.entity;

public class Time {

    private Integer TimeId;

    private String graName;

    private String start;

    private String end;

    private String type;

    public String getGraName() {
        return graName;
    }

    public void setGraName(String graName) {
        this.graName = graName;
    }

    public Integer getTimeId() {
        return TimeId;
    }

    public void setTimeId(Integer timeId) {
        TimeId = timeId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}