package wiwi.qinj.domain;

import java.util.StringJoiner;

/**
 * @author Wisya
 * @description
 * @buildTime 2018-10-16 18:57
 */
public class User {

    private String userId;
    private String userName;
    private String phone;
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("userId='" + userId + "'")
                .add("userName='" + userName + "'")
                .add("phone='" + phone + "'")
                .add("password='" + password + "'")
                .toString();
    }
}
