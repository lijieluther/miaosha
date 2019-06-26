package com.miaosha.dataobject;

public class RewardDO {
    private Integer id;

    private Integer firstAward;

    private Integer secondAward;

    private Integer thirdAward;

    private Integer fourAward;

    private String startTime;

    private String endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFirstAward() {
        return firstAward;
    }

    public void setFirstAward(Integer firstAward) {
        this.firstAward = firstAward;
    }

    public Integer getSecondAward() {
        return secondAward;
    }

    public void setSecondAward(Integer secondAward) {
        this.secondAward = secondAward;
    }

    public Integer getThirdAward() {
        return thirdAward;
    }

    public void setThirdAward(Integer thirdAward) {
        this.thirdAward = thirdAward;
    }

    public Integer getFourAward() {
        return fourAward;
    }

    public void setFourAward(Integer fourAward) {
        this.fourAward = fourAward;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }
}