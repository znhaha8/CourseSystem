package main.com.WCZZ.entity;

public class Choice {

    private Integer choiceId;

    private String stuId;

    private String couName;

    private String chooseDate;

    private String withdrawDate;

    public Integer getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(Integer choiceId) {
        this.choiceId = choiceId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public String getChooseDate() {
        return chooseDate;
    }

    public void setChooseDate(String chooseDate) {
        this.chooseDate = chooseDate;
    }

    public String getWithdrawDate() {
        return withdrawDate;
    }

    public void setWithdrawDate(String withdrawDate) {
        this.withdrawDate = withdrawDate;
    }

    public Choice(String stuId, String couName, String chooseDate) {
        this.stuId = stuId;
        this.couName = couName;
        this.chooseDate = chooseDate;
    }

    public Choice(){}
}