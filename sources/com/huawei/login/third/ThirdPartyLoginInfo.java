package com.huawei.login.third;

/* loaded from: classes.dex */
public class ThirdPartyLoginInfo {
    private String accessToken;
    private String accessTokenExpireTime;
    private int ageGroupFlag;
    private String birthDate;
    private int gender;
    private String guardianAccount;
    private long guardianUid;
    private String headPictureUrl;
    private String languageCode;
    private String loginUserName;
    private String nationalCode;
    private String nickName;
    private String refreshToken;
    private String refreshTokenExpireTime;
    private int resultCode;
    private int siteId;
    private String srvNationalCode;
    private long timeStamp;
    private String uid;
    private String userAccount;
    private UserAccountInfo userAccountInfo;

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public String getAccessTokenExpireTime() {
        return this.accessTokenExpireTime;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getGuardianAccount() {
        return this.guardianAccount;
    }

    public void setGuardianAccount(String str) {
        this.guardianAccount = str;
    }

    public Long getGuardianUid() {
        return Long.valueOf(this.guardianUid);
    }

    public void setGuardianUid(Long l) {
        this.guardianUid = l.longValue();
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public int getSiteId() {
        return this.siteId;
    }

    public void setSiteId(int i) {
        this.siteId = i;
    }

    public String getNationalCode() {
        return this.nationalCode;
    }

    public void setNationalCode(String str) {
        this.nationalCode = str;
    }

    public String getSrvNationalCode() {
        return this.srvNationalCode;
    }

    public void setSrvNationalCode(String str) {
        this.srvNationalCode = str;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getUserAccount() {
        return this.userAccount;
    }

    public String getLoginUserName() {
        return this.loginUserName;
    }

    public String getHeadPictureUrl() {
        return this.headPictureUrl;
    }

    public int getGender() {
        return this.gender;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public int getAgeGroupFlag() {
        return this.ageGroupFlag;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public UserAccountInfo getUserAccountInfo() {
        return this.userAccountInfo;
    }

    public String toString() {
        return "ThirdPartyLoginInfo{accessToken='" + this.accessToken + "', accessTokenExpireTime='" + this.accessTokenExpireTime + "', refreshTicket='" + this.refreshToken + "', accessToken='" + this.accessToken + "', refreshTicketExpireTime='" + this.refreshTokenExpireTime + "', resultCode='" + this.resultCode + "', uid='" + this.uid + "'}";
    }
}
