package main.com.WCZZ.entity;

public class Acadamy {

    private String acaName;

    public String getAcaName() {
        return acaName;
    }

    public void setAcaName(String acaName) {
        this.acaName = acaName == null ? null : acaName.trim();
    }
}