package com.miaosha.dataobject;

/**
 * @author luther
 */
public class QuaLiFiCationsDO {
    private Integer qualificationsId;

    private Integer qualificationsNum;

    private String dateTime;

    private Integer prizeId;

    public Integer getQualificationsId() {
        return qualificationsId;
    }

    public void setQualificationsId(Integer qualificationsId) {
        this.qualificationsId = qualificationsId;
    }

    public Integer getQualificationsNum() {
        return qualificationsNum;
    }

    public void setQualificationsNum(Integer qualificationsNum) {
        this.qualificationsNum = qualificationsNum;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime == null ? null : dateTime.trim();
    }

    public Integer getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Integer prizeId) {
        this.prizeId = prizeId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}