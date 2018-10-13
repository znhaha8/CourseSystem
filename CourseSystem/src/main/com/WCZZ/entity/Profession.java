package main.com.WCZZ.entity;

public class Profession {

    private String proName;

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }
}