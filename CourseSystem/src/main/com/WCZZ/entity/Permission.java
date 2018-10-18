package main.com.WCZZ.entity;

public class Permission {
    private int perId;
    private String token;
    private String theurl;
    private String description;
    private int roleId;

    public int getPerId() {
        return perId;
    }

    public void setPerId(int perId) {
        this.perId = perId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTheurl() {
        return theurl;
    }

    public void setTheurl(String theurl) {
        this.theurl = theurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
