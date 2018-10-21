package wiwi.qinj.domain;

import java.util.Date;
import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Wisya
 * @description
 * @buildTime 2018-10-15 21:08
 */
public class Coupon {


    private String couponId;
    private String couponName;
    private Long prePrice;
    private Long realPrice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private int totalCount;
    private String picture;
    private String description;
    private String status;


    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Long getPrePrice() {
        return prePrice;
    }

    public void setPrePrice(Long prePrice) {
        this.prePrice = prePrice;
    }

    public Long getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Long realPrice) {
        this.realPrice = realPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Coupon.class.getSimpleName() + "[", "]")
                .add("couponId='" + couponId + "'")
                .add("couponName='" + couponName + "'")
                .add("prePrice=" + prePrice)
                .add("realPrice=" + realPrice)
                .add("createTime=" + createTime)
                .add("totalCount=" + totalCount)
                .add("picture='" + picture + "'")
                .add("description='" + description + "'")
                .add("status='" + status + "'")
                .toString();
    }
}
