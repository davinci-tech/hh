package com.huawei.picture.security.account.bean;

import java.util.List;

/* loaded from: classes6.dex */
public class SignInResponse {
    private String resultcode;
    private String resultinfo;
    private UserInfoBean userInfo;
    private String userRefreshToken;
    private List<UserTokenBean> userToken;

    public String getResultcode() {
        return this.resultcode;
    }

    public void setResultcode(String str) {
        this.resultcode = str;
    }

    public String getResultinfo() {
        return this.resultinfo;
    }

    public void setResultinfo(String str) {
        this.resultinfo = str;
    }

    public UserInfoBean getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(UserInfoBean userInfoBean) {
        this.userInfo = userInfoBean;
    }

    public String getUserRefreshToken() {
        return this.userRefreshToken;
    }

    public void setUserRefreshToken(String str) {
        this.userRefreshToken = str;
    }

    public List<UserTokenBean> getUserToken() {
        return this.userToken;
    }

    public void setUserToken(List<UserTokenBean> list) {
        this.userToken = list;
    }
}
