package main.com.WCZZ.entity;

public class Team {
    Integer claId;
    String gra_name;
    String aca_name;
    String pro_name;
    String class_name;

    public Integer getId() {
        return claId;
    }

    public void setId(Integer claId) {
        this.claId = claId;
    }

    public String getGra_name() {
        return gra_name;
    }

    public void setGra_name(String gra_name) {
        this.gra_name = gra_name;
    }

    public String getAca_name() {
        return aca_name;
    }

    public void setAca_name(String aca_name) {
        this.aca_name = aca_name;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

}
