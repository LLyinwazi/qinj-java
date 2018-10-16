package wiwi.qinj.domain;

import java.util.Date;
import java.util.StringJoiner;

/**
 * @author Wisya
 * @description
 * @buildTime 2018-10-14 13:30
 */
public class Member {

    private Long memberId;
    private String memberName;
    private String phone;
    private String loginPassword;
    private Date createTime;
    private String wechatId;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Member.class.getSimpleName() + "[", "]")
                .add("memberId=" + memberId)
                .add("memberName='" + memberName + "'")
                .add("phone='" + phone + "'")
                .add("loginPassword='" + loginPassword + "'")
                .add("createTime=" + createTime)
                .add("wechatId='" + wechatId + "'")
                .toString();
    }
}

