package main.com.WCZZ.entity;

public class StudentCourse {
    private Integer id;
    private String graName;
    private String proName;
    private String couName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGraName() {
        return graName;
    }

    public void setGraName(String graName) {
        this.graName = graName;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public StudentCourse(String graName, String proName, String couName) {
        this.graName = graName;
        this.proName = proName;
        this.couName = couName;
    }
    public StudentCourse(){}
}
