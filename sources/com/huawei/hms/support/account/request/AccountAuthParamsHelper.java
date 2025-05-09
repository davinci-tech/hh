package com.huawei.hms.support.account.request;

import com.huawei.hms.hwid.ap;
import com.huawei.hms.hwid.as;
import com.huawei.hms.support.api.entity.auth.PermissionInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.feature.request.AbstractAuthParamsHelper;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AccountAuthParamsHelper extends AbstractAuthParamsHelper {

    /* renamed from: a, reason: collision with root package name */
    private String f5944a = "";
    private int b = 2;
    private boolean c = false;
    private boolean d = false;

    @Deprecated
    public AccountAuthParamsHelper setDialogAuth() {
        return this;
    }

    public AccountAuthParamsHelper() {
    }

    public AccountAuthParamsHelper(AccountAuthParams accountAuthParams) {
        this.signInScopes.addAll(accountAuthParams.getRequestScopeList());
        this.permissionSet.addAll(accountAuthParams.getPermissionInfos());
    }

    public AccountAuthParamsHelper setUid() {
        this.permissionSet.add(AccountAuthParams.UID_DYNAMIC_PERMISSION);
        return this;
    }

    public AccountAuthParamsHelper setAuthorizationCode() {
        PermissionInfo permissionInfo = new PermissionInfo();
        permissionInfo.setPermissionUri("https://www.huawei.com/auth/account/base.profile/serviceauthcode");
        this.permissionSet.add(permissionInfo);
        return this;
    }

    @Deprecated
    public AccountAuthParamsHelper setAccessToken() {
        PermissionInfo permissionInfo = new PermissionInfo();
        permissionInfo.setPermissionUri("https://www.huawei.com/auth/account/base.profile/accesstoken");
        this.permissionSet.add(permissionInfo);
        return this;
    }

    public AccountAuthParamsHelper setScopeList(List<Scope> list) {
        if (ap.b(list).booleanValue()) {
            for (Scope scope : list) {
                if (scope != null && scope.getScopeUri() != null) {
                    this.signInScopes.add(scope);
                }
            }
        }
        return this;
    }

    protected AccountAuthParamsHelper setScope(Scope scope) {
        this.signInScopes.add(scope);
        return this;
    }

    public AccountAuthParams createParams() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sessionId", this.f5944a);
            jSONObject.put("idTokenSignAlg", this.b);
            jSONObject.put("needForceRefresh", this.c);
            jSONObject.put(CommonConstant.RequestParams.KEY_ID_NEED_CONSENT, this.d);
            return new AccountAuthParams(this.signInScopes, this.permissionSet, jSONObject.toString());
        } catch (JSONException e) {
            as.d("AccountAuthParamsHelper", "JSONException：" + e.getClass().getSimpleName(), true);
            return new AccountAuthParams(this.signInScopes, this.permissionSet);
        }
    }

    public AccountAuthParamsHelper setSessionId(String str) {
        this.f5944a = str;
        return this;
    }

    public AccountAuthParamsHelper setEmail() {
        return setScope(AccountAuthParams.EMAIL);
    }

    public AccountAuthParamsHelper setId() {
        return setScope(AccountAuthParams.OPENID);
    }

    public AccountAuthParamsHelper setIdToken() {
        this.permissionSet.add(new PermissionInfo().setPermissionUri("idtoken"));
        return this;
    }

    public AccountAuthParamsHelper setProfile() {
        return setScope(AccountAuthParams.PROFILE);
    }

    public AccountAuthParamsHelper setMobileNumber() {
        setAccessToken();
        setId();
        return setScope(new Scope("https://www.huawei.com/auth/account/mobile.number"));
    }

    public AccountAuthParamsHelper setForceLogout() {
        PermissionInfo permissionInfo = new PermissionInfo();
        permissionInfo.setPermissionUri(CommonConstant.LocalPermission.SETFORCELOGOUT);
        this.permissionSet.add(permissionInfo);
        return this;
    }

    public AccountAuthParamsHelper setCarrierId() {
        PermissionInfo permissionInfo = new PermissionInfo();
        permissionInfo.setPermissionUri(CommonConstant.LocalPermission.CARRIER_ID);
        this.permissionSet.add(permissionInfo);
        return this;
    }

    public AccountAuthParamsHelper setAssistToken() {
        PermissionInfo permissionInfo = new PermissionInfo();
        permissionInfo.setPermissionUri(CommonConstant.PERMISSION.ASSIST_TOKEN);
        this.permissionSet.add(permissionInfo);
        this.signInScopes.add(new Scope(CommonConstant.SCOPE.SCOPE_ASSIST_TOKEN));
        return this;
    }

    public AccountAuthParamsHelper setIdTokenSignAlg(int i) {
        this.b = i;
        return this;
    }

    public AccountAuthParamsHelper setForceRefresh() {
        this.c = true;
        return this;
    }

    public AccountAuthParamsHelper setNeedConsent() {
        this.d = true;
        return this;
    }
}
