package com.huawei.hms.hwid;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hms.hwid.internal.ui.activity.HwIdSignInHubActivity;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.api.entity.hwid.HuaweiIdSignInRequest;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParamsHelper;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import com.huawei.hms.support.hwid.result.HuaweiIdAuthResult;
import com.huawei.hms.utils.Util;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

/* loaded from: classes4.dex */
public final class x {
    public static Intent a(Context context, HuaweiIdAuthParams huaweiIdAuthParams, String str) {
        as.b("[HUAWEIIDSDK]HuaweiIdAuthTool", "getSignInIntent enter", true);
        Intent intent = new Intent("com.huawei.hms.jos.signIn");
        try {
            intent.setPackage(context.getPackageName());
            intent.setClass(context, HwIdSignInHubActivity.class);
            String appId = Util.getAppId(context);
            String packageName = context.getPackageName();
            s sVar = new s();
            sVar.b(appId);
            sVar.c(packageName);
            sVar.a(61200300L);
            sVar.d(str);
            HuaweiIdSignInRequest huaweiIdSignInRequest = new HuaweiIdSignInRequest();
            huaweiIdSignInRequest.setHuaweiIdAuthParams(huaweiIdAuthParams);
            try {
                intent.putExtra(AuthInternalPickerConstant.SignInReqKey.HUAWEIIDCPCLIENTINFO, sVar.d());
                intent.putExtra(AuthInternalPickerConstant.SignInReqKey.HUAWEIIDSIGNINREQUEST, huaweiIdSignInRequest.toJson());
            } catch (JSONException e) {
                as.d("[HUAWEIIDSDK]HuaweiIdAuthTool", "JSONException:" + e.getClass().getSimpleName(), true);
            }
        } catch (Exception e2) {
            as.d("[HUAWEIIDSDK]HuaweiIdAuthTool", "Exception:" + e2.getClass().getSimpleName(), true);
        }
        return intent;
    }

    public static HuaweiIdAuthResult a(Intent intent) {
        as.b("[HUAWEIIDSDK]HuaweiIdAuthTool", "getSignInResultFromIntent", true);
        if (intent == null || !intent.hasExtra("HUAWEIID_SIGNIN_RESULT")) {
            as.d("[HUAWEIIDSDK]HuaweiIdAuthTool", "data or signInResult is null", true);
            return null;
        }
        try {
            return new HuaweiIdAuthResult().fromJson(intent.getStringExtra("HUAWEIID_SIGNIN_RESULT"));
        } catch (JSONException unused) {
            as.d("[HUAWEIIDSDK]HuaweiIdAuthTool", "JSONException", true);
            return null;
        }
    }

    public static void a() {
        y.a().c();
    }

    public static AuthHuaweiId b() {
        return y.a().b();
    }

    public static HuaweiIdAuthParams a(List<Scope> list) {
        HuaweiIdAuthParamsHelper huaweiIdAuthParamsHelper = new HuaweiIdAuthParamsHelper();
        if (ap.b(list).booleanValue()) {
            huaweiIdAuthParamsHelper.setScopeList(list);
        }
        Iterator<Scope> it = list.iterator();
        while (it.hasNext()) {
            if (a(it.next(), "https://www.huawei.com/auth/account/shipping.address")) {
                huaweiIdAuthParamsHelper.setShippingAddress();
            }
        }
        return huaweiIdAuthParamsHelper.createParams();
    }

    private static boolean a(Scope scope, String str) {
        if (scope != null) {
            return TextUtils.equals(scope.getScopeUri(), str);
        }
        return false;
    }
}
