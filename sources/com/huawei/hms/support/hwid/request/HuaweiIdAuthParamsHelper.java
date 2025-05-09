package com.huawei.hms.support.hwid.request;

import com.huawei.hms.hwid.ap;
import com.huawei.hms.support.api.entity.auth.PermissionInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.feature.request.AbstractAuthParamsHelper;
import java.util.List;

/* loaded from: classes4.dex */
public final class HuaweiIdAuthParamsHelper extends AbstractAuthParamsHelper {
    @Deprecated
    public HuaweiIdAuthParamsHelper setDialogAuth() {
        return this;
    }

    public HuaweiIdAuthParamsHelper() {
    }

    public HuaweiIdAuthParamsHelper(HuaweiIdAuthParams huaweiIdAuthParams) {
        this.signInScopes.addAll(huaweiIdAuthParams.getRequestScopeList());
        this.permissionSet.addAll(huaweiIdAuthParams.getPermissionInfos());
    }

    public HuaweiIdAuthParamsHelper setUid() {
        this.permissionSet.add(HuaweiIdAuthParams.UID_DYNAMIC_PERMISSION);
        return this;
    }

    public HuaweiIdAuthParamsHelper setAuthorizationCode() {
        PermissionInfo permissionInfo = new PermissionInfo();
        permissionInfo.setPermissionUri("https://www.huawei.com/auth/account/base.profile/serviceauthcode");
        this.permissionSet.add(permissionInfo);
        return this;
    }

    @Deprecated
    public HuaweiIdAuthParamsHelper setAccessToken() {
        PermissionInfo permissionInfo = new PermissionInfo();
        permissionInfo.setPermissionUri("https://www.huawei.com/auth/account/base.profile/accesstoken");
        this.permissionSet.add(permissionInfo);
        return this;
    }

    public HuaweiIdAuthParamsHelper setScopeList(List<Scope> list) {
        if (ap.b(list).booleanValue()) {
            for (Scope scope : list) {
                if (scope != null && scope.getScopeUri() != null) {
                    this.signInScopes.add(scope);
                }
            }
        }
        return this;
    }

    protected HuaweiIdAuthParamsHelper setScope(Scope scope) {
        this.signInScopes.add(scope);
        return this;
    }

    public HuaweiIdAuthParams createParams() {
        return new HuaweiIdAuthParams(this.signInScopes, this.permissionSet);
    }

    public HuaweiIdAuthParamsHelper setEmail() {
        return setScope(HuaweiIdAuthParams.EMAIL);
    }

    public HuaweiIdAuthParamsHelper setId() {
        return setScope(HuaweiIdAuthParams.OPENID);
    }

    public HuaweiIdAuthParamsHelper setIdToken() {
        this.permissionSet.add(new PermissionInfo().setPermissionUri("idtoken"));
        return this;
    }

    public HuaweiIdAuthParamsHelper setProfile() {
        return setScope(HuaweiIdAuthParams.PROFILE);
    }

    public HuaweiIdAuthParamsHelper setShippingAddress() {
        setAccessToken();
        setId();
        return setScope(new Scope("https://www.huawei.com/auth/account/shipping.address"));
    }

    public HuaweiIdAuthParamsHelper setMobileNumber() {
        setAccessToken();
        setId();
        return setScope(new Scope("https://www.huawei.com/auth/account/mobile.number"));
    }

    public HuaweiIdAuthParamsHelper setIncludeGranted(boolean z) {
        if (!z) {
            PermissionInfo permissionInfo = new PermissionInfo();
            permissionInfo.setPermissionUri("https://www.huawei.com/auth/account/nonincludegranted");
            this.permissionSet.add(permissionInfo);
        }
        return this;
    }
}
