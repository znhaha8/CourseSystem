package main.com.WCZZ.entity;

import java.util.Date;
import java.util.List;

public class User {
    private String id;
    private String username;
    private String password;
    private String salt;
    private int delFlag;
    private String createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }


    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public User(){}

    public User(String id, String username, String password, String salt, String createDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.createDate = createDate;
    }
}