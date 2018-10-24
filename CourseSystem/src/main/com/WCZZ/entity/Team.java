package main.com.WCZZ.entity;

public class Team {
    Integer claId;
    String graName;
    String acaName;
    String proName;
    String claName;
    String createDate;

    public Integer getClaId() {
        return claId;
    }

    public void setClaId(Integer claId) {
        this.claId = claId;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Team(Integer claId, String graName, String acaName, String proName, String claName, String createDate) {
        this.claId = claId;
        this.graName = graName;
        this.acaName = acaName;
        this.proName = proName;
        this.claName = claName;
        this.createDate = createDate;
    }

    public Team() {}

    @Override
    public String toString() {
        return "Team{" +
                "claId=" + claId +
                ", graName='" + graName + '\'' +
                ", acaName='" + acaName + '\'' +
                ", proName='" + proName + '\'' +
                ", claName='" + claName + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
