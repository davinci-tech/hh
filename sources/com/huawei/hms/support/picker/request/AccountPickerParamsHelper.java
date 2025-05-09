package com.huawei.hms.support.picker.request;

import android.text.TextUtils;
import com.huawei.hms.common.utils.PickerCollectionUtil;
import com.huawei.hms.support.api.entity.auth.PermissionInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.api.entity.common.CommonPickerConstant;
import com.huawei.hms.support.feature.request.AbstractAuthParamsHelper;
import defpackage.ksy;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class AccountPickerParamsHelper extends AbstractAuthParamsHelper {
    private static final String TAG = "AccountPickerParamsHelper";
    private String mDeviceInfo;
    private String mGrsAppName;
    private String mHostAccessToken;
    private boolean mIsGuideLogin;
    private String mLoginUrl;
    private boolean mOobeFlag;
    private String mQRPromptFirstLevelType;
    private String mQRPromptSecondLevel;
    private String mRedirectUrl;
    private int mIdTokenSignAlg = 2;
    private boolean needForceRefresh = false;

    @Deprecated
    public AccountPickerParamsHelper setDialogAuth() {
        return this;
    }

    public AccountPickerParamsHelper() {
    }

    public AccountPickerParamsHelper(AccountPickerParams accountPickerParams) {
        this.signInScopes.addAll(accountPickerParams.getRequestScopeList());
        this.permissionSet.addAll(accountPickerParams.getPermissionInfos());
    }

    public AccountPickerParamsHelper setUid() {
        this.permissionSet.add(AccountPickerParams.UID_DYNAMIC_PERMISSION);
        return this;
    }

    public AccountPickerParamsHelper setAuthorizationCode() {
        PermissionInfo permissionInfo = new PermissionInfo();
        permissionInfo.setPermissionUri("https://www.huawei.com/auth/account/base.profile/serviceauthcode");
        this.permissionSet.add(permissionInfo);
        return this;
    }

    public AccountPickerParamsHelper setAccessToken() {
        PermissionInfo permissionInfo = new PermissionInfo();
        permissionInfo.setPermissionUri("https://www.huawei.com/auth/account/base.profile/accesstoken");
        this.permissionSet.add(permissionInfo);
        return this;
    }

    public AccountPickerParamsHelper setScopeList(List<Scope> list) {
        if (PickerCollectionUtil.isNotEmpty(list).booleanValue()) {
            for (Scope scope : list) {
                if (scope != null && scope.getScopeUri() != null) {
                    this.signInScopes.add(scope);
                }
            }
        }
        return this;
    }

    public AccountPickerParamsHelper setScope(Scope scope) {
        this.signInScopes.add(scope);
        return this;
    }

    public AccountPickerParamsHelper setDeviceInfo(String str) {
        this.mDeviceInfo = str;
        return this;
    }

    public AccountPickerParamsHelper setRedirecturl(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "hms://redirect_url";
        }
        this.mRedirectUrl = str;
        return this;
    }

    public AccountPickerParamsHelper setIsGuideLogin(boolean z) {
        this.mIsGuideLogin = z;
        return this;
    }

    public AccountPickerParamsHelper setQRPromptFirstLevelType(String str) {
        this.mQRPromptFirstLevelType = str;
        return this;
    }

    public AccountPickerParamsHelper setQRPromptSecondLevel(String str) {
        this.mQRPromptSecondLevel = str;
        return this;
    }

    public AccountPickerParamsHelper setOobeFlag(boolean z) {
        this.mOobeFlag = z;
        return this;
    }

    public AccountPickerParamsHelper setHostAccessToken(String str) {
        this.mHostAccessToken = str;
        return this;
    }

    public AccountPickerParams createParams() {
        ksy.b(TAG, "begin createParams", true);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CommonPickerConstant.RequestParams.KEY_REDIRECT_URL, this.mRedirectUrl);
            jSONObject.put("deviceInfo", this.mDeviceInfo);
            jSONObject.put(CommonPickerConstant.RequestParams.KEY_GUIDE_LOGIN, this.mIsGuideLogin);
            jSONObject.put(CommonPickerConstant.RequestParams.KEY_QR_PROMPT_FIRST_LEVEL_TYPE, this.mQRPromptFirstLevelType);
            jSONObject.put(CommonPickerConstant.RequestParams.KEY_QR_PROMPT_SECOND_LEVEL, this.mQRPromptSecondLevel);
            jSONObject.put(CommonPickerConstant.RequestParams.KEY_OOBE_FLAG, this.mOobeFlag);
            jSONObject.put(CommonPickerConstant.RequestParams.KEY_LOGNIN_URL, this.mLoginUrl);
            jSONObject.put("idTokenSignAlg", this.mIdTokenSignAlg);
            jSONObject.put(CommonPickerConstant.RequestParams.KEY_HOST_ACCESS_TOKEN, this.mHostAccessToken);
            jSONObject.put(CommonPickerConstant.RequestParams.KEY_GRS_APP_NAME, this.mGrsAppName);
            jSONObject.put("needForceRefresh", this.needForceRefresh);
        } catch (JSONException unused) {
            ksy.c(TAG, "JSONException occur", true);
        }
        return new AccountPickerParams(this.signInScopes, this.permissionSet, jSONObject.toString());
    }

    public AccountPickerParamsHelper setEmail() {
        return setScope(AccountPickerParams.EMAIL);
    }

    public AccountPickerParamsHelper setId() {
        return setScope(AccountPickerParams.OPENID);
    }

    public AccountPickerParamsHelper setIdToken() {
        this.permissionSet.add(new PermissionInfo().setPermissionUri("idtoken"));
        return this;
    }

    public AccountPickerParamsHelper setProfile() {
        return setScope(AccountPickerParams.PROFILE);
    }

    public AccountPickerParamsHelper setShippingAddress() {
        setAccessToken();
        setId();
        return setScope(new Scope("https://www.huawei.com/auth/account/shipping.address"));
    }

    public AccountPickerParamsHelper setMobileNumber() {
        setAccessToken();
        setId();
        return setScope(new Scope("https://www.huawei.com/auth/account/mobile.number"));
    }

    public AccountPickerParamsHelper setIncludeGranted(boolean z) {
        if (!z) {
            PermissionInfo permissionInfo = new PermissionInfo();
            permissionInfo.setPermissionUri("https://www.huawei.com/auth/account/nonincludegranted");
            this.permissionSet.add(permissionInfo);
        }
        return this;
    }

    public AccountPickerParamsHelper setLoginUrl(String str) {
        this.mLoginUrl = str;
        return this;
    }

    public AccountPickerParamsHelper setIdTokenSignAlg(int i) {
        this.mIdTokenSignAlg = i;
        return this;
    }

    public AccountPickerParamsHelper setGrsAppName(String str) {
        this.mGrsAppName = str;
        return this;
    }

    public AccountPickerParamsHelper setForceRefresh() {
        this.needForceRefresh = true;
        return this;
    }
}
