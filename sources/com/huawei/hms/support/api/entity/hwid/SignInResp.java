package com.huawei.hms.support.api.entity.hwid;

import android.os.Bundle;
import com.huawei.hms.support.api.entity.common.CommonConstant;

/* loaded from: classes9.dex */
public class SignInResp extends IHwIDRespEntity {

    @Checked(permission = "https://www.huawei.com/auth/account/base.profile/accesstoken", value = CommonConstant.RETKEY.ACCESS_TOKEN)
    private String mAccessToken;

    @Checked(permission = CommonConstant.PERMISSION.COUNTRY, scope = CommonConstant.SCOPE.SCOPE_ACCOUNT_COUNTRY, value = CommonConstant.RETKEY.COUNTRYCODE)
    private String mCountryCode;

    @Checked(permission = CommonConstant.PERMISSION.BASE_PROFILE, scope = "https://www.huawei.com/auth/account/base.profile", value = CommonConstant.RETKEY.GENDER)
    private String mGender;

    @Checked(permission = CommonConstant.PERMISSION.BASE_PROFILE, scope = "https://www.huawei.com/auth/account/base.profile", value = CommonConstant.RETKEY.DISPLAYNAME)
    private String mLoginUserName;

    @Checked(permission = CommonConstant.PERMISSION.OPENID, value = CommonConstant.RETKEY.OPENID)
    private String mOpenId;

    @Checked(CommonConstant.RETKEY.SCOPE)
    private String mScopeUri;

    @Checked(permission = "https://www.huawei.com/auth/account/base.profile/serviceauthcode", value = CommonConstant.RETKEY.SERVICEAUTHCODE)
    private String mServiceAuthCode;

    @Checked(permission = CommonConstant.PERMISSION.COUNTRY, scope = CommonConstant.SCOPE.SCOPE_ACCOUNT_COUNTRY, value = CommonConstant.RETKEY.SERVICECOUNTRYCODE)
    private String mServiceCountryCode;

    @Checked(permission = "com.huawei.android.hms.account.getUID", value = CommonConstant.RETKEY.USERID)
    private String mUid;

    @Checked(permission = CommonConstant.PERMISSION.UNIONID, value = CommonConstant.RETKEY.UNIONID)
    private String mUnionID;

    @Checked(permission = CommonConstant.PERMISSION.BASE_PROFILE, scope = "https://www.huawei.com/auth/account/base.profile", value = CommonConstant.RETKEY.STATUS)
    private String mUserStatus;

    @Checked(permission = CommonConstant.PERMISSION.BASE_PROFILE, scope = "https://www.huawei.com/auth/account/base.profile", value = CommonConstant.RETKEY.PHOTOURL)
    private String photoUrl;

    public static SignInResp buildSignInResp(Bundle bundle) {
        SignInResp signInResp = new SignInResp();
        if (bundle != null) {
            signInResp.mUid = bundle.getString(CommonConstant.RETKEY.USERID, "");
            signInResp.mLoginUserName = bundle.getString(CommonConstant.RETKEY.DISPLAYNAME, "");
            signInResp.photoUrl = bundle.getString(CommonConstant.RETKEY.PHOTOURL, "");
            signInResp.mAccessToken = bundle.getString(CommonConstant.RETKEY.ACCESS_TOKEN, "");
            signInResp.mUserStatus = bundle.getString(CommonConstant.RETKEY.STATUS, "");
            signInResp.mGender = bundle.getString(CommonConstant.RETKEY.GENDER, "");
            signInResp.mScopeUri = bundle.getString(CommonConstant.RETKEY.SCOPE, "");
            signInResp.mOpenId = bundle.getString(CommonConstant.RETKEY.OPENID, "");
            signInResp.mServiceCountryCode = bundle.getString(CommonConstant.RETKEY.SERVICECOUNTRYCODE, "");
            signInResp.mCountryCode = bundle.getString(CommonConstant.RETKEY.COUNTRYCODE, "");
            signInResp.mServiceAuthCode = bundle.getString(CommonConstant.RETKEY.SERVICEAUTHCODE, "");
            signInResp.mUnionID = bundle.getString(CommonConstant.RETKEY.UNIONID, "");
        }
        return signInResp;
    }

    public String getUid() {
        return this.mUid;
    }

    public String getOpenId() {
        return this.mOpenId;
    }

    public String getLoginUserName() {
        return this.mLoginUserName;
    }

    public String getPhotoUrl() {
        return this.photoUrl;
    }

    public String getUserStatus() {
        return this.mUserStatus;
    }

    public String getGender() {
        return this.mGender;
    }

    public String getServiceCountryCode() {
        return this.mServiceCountryCode;
    }

    public String getCountryCode() {
        return this.mCountryCode;
    }

    public String getScopeUri() {
        return this.mScopeUri;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public String getServiceAuthCode() {
        return this.mServiceAuthCode;
    }

    public String getmUnionID() {
        return this.mUnionID;
    }
}
