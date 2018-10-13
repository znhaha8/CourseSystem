package main.com.WCZZ.entity;

public class User {

    private Integer username;

    private String password;

    private String roleType;

    public Integer getUsername() {
        return username;
    }

    public void setUsername(Integer username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType == null ? null : roleType.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "username=" + username +
                ", password='" + password + '\'' +
                ", roleType='" + roleType + '\'' +
                '}';
    }
}