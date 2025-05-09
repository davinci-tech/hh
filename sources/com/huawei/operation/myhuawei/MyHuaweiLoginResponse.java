package com.huawei.operation.myhuawei;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.operation.utils.CloudParamKeys;

/* loaded from: classes5.dex */
public class MyHuaweiLoginResponse {

    @SerializedName("result")
    private LoginMyHuaweiResult mLoginMyHuaweiResult;

    @SerializedName("resultCode")
    private int mResultCode;

    @SerializedName("resultDesc")
    private String mResultDesc;

    public int getResultCode() {
        return this.mResultCode;
    }

    public String getResultDesc() {
        return this.mResultDesc;
    }

    public LoginMyHuaweiResult getLoginResponseInfo() {
        return this.mLoginMyHuaweiResult;
    }

    public String getCookie() {
        LoginMyHuaweiResult loginMyHuaweiResult = this.mLoginMyHuaweiResult;
        return loginMyHuaweiResult == null ? "" : loginMyHuaweiResult.getCookie();
    }

    public String toString() {
        return "MyHuaweiLoginResponse{resultCode=" + getResultCode() + ", resultDesc=" + getResultDesc() + ", LoginResponseInfo=" + getLoginResponseInfo() + '}';
    }

    static class LoginMyHuaweiResult {

        @SerializedName("cookie")
        private String mCookie;

        @SerializedName("data")
        private LoginResponseInfo mLoginResponseInfo;

        private LoginMyHuaweiResult() {
        }

        public LoginResponseInfo getLoginResponseInfo() {
            return this.mLoginResponseInfo;
        }

        public String getCookie() {
            return this.mCookie;
        }

        public String toString() {
            return "LoginMyHuaweiResult{data=" + getLoginResponseInfo() + ", cookie=" + getCookie() + '}';
        }
    }

    static class LoginResponseInfo {

        @SerializedName(CloudParamKeys.CLIENT_TYPE)
        private String mClientType;

        @SerializedName("emailAccount")
        private String mEmailAccount;

        @SerializedName("expireTime")
        private String mExpireTime;

        @SerializedName(CommonConstant.KEY_GENDER)
        private String mGender;

        @SerializedName("headPictureUrl")
        private String mHeadPictureUrl;

        @SerializedName("mobileAccount")
        private String mMobileAccount;

        @SerializedName("nickName")
        private String mNickName;

        @SerializedName("safeEmailAccount")
        private String mSafeEmailAccount;

        @SerializedName("safeMobileAccount")
        private String mSafeMobileAccount;

        @SerializedName(JsbMapKeyNames.H5_USER_ID)
        private String mUserId;

        @SerializedName(HwPayConstant.KEY_USER_NAME)
        private String mUserName;

        private LoginResponseInfo() {
        }

        public String toString() {
            return " LoginResponseInfo{userId=" + this.mUserId + ", userName=" + this.mUserName + ", nickName=" + this.mNickName + "headPictureUrl=" + this.mHeadPictureUrl + ", safeEmailAccount=" + this.mSafeEmailAccount + ", safeMobileAccount=" + this.mSafeMobileAccount + ", emailAccount=" + this.mEmailAccount + ", mobileAccount=" + this.mMobileAccount + "gender=" + this.mGender + ", clientType=" + this.mClientType + ", expireTime=" + this.mExpireTime + '}';
        }
    }
}
