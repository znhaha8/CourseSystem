package main.com.WCZZ.entity;

import java.util.Date;

public class Student {
    private String stuId;

    private String stuName;

    private String sex;

    private String graName;

    private String acaName;

    private String proName;

    private String claName;

    private String phone;

    private Date createTime;

    private Integer delFlag;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGraName() {
        return graName;
    }

    public void setGraName(String graName) {
        this.graName = graName;
    }

    public String getAcaName() {
        return acaName;
    }

    public void setAcaName(String acaName) {
        this.acaName = acaName;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getClaName() {
        return claName;
    }

    public void setClaName(String claName) {
        this.claName = claName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Student(String stuId, String stuName, String graName, String acaName, String proName, String claName) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.graName = graName;
        this.acaName = acaName;
        this.proName = proName;
        this.claName = claName;
    }

    public Student(String stuName, String sex, String graName, String acaName, String proName, String claName, String phone) {
        this.stuName = stuName;
        this.sex = sex;
        this.graName = graName;
        this.acaName = acaName;
        this.proName = proName;
        this.claName = claName;
        this.phone = phone;
    }

    public Student(){}

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", sex='" + sex + '\'' +
                ", graName=" + graName +
                ", acaName='" + acaName + '\'' +
                ", proName='" + proName + '\'' +
                ", claName=" + claName +
                ", phone='" + phone + '\'' +
                ", createTime=" + createTime +
                ", delFlag=" + delFlag +
                '}';
    }
}