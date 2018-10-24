package main.com.WCZZ.entity;

public class Permission {
    private Integer perId;
    private String theurl;
    private String description;
    private int roleId;

    public Integer getPerId() {
        return perId;
    }

    public void setPerId(Integer perId) {
        this.perId = perId;
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
