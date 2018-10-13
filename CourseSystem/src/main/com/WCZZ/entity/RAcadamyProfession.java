package main.com.WCZZ.entity;

public class RAcadamyProfession {

    private String acaName;

    private String proName;

    public String getAcaName() {
        return acaName;
    }

    public void setAcaName(String acaName) {
        this.acaName = acaName == null ? null : acaName.trim();
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }
}