package com.huawei.hms.support.picker.service;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hwidauth.api.ResultCallBack;
import com.huawei.hwidauth.datatype.DeviceInfo;
import com.huawei.hwidauth.ui.WebViewActivity;
import defpackage.kqg;
import defpackage.kqi;
import defpackage.kqk;
import defpackage.kqp;
import defpackage.ksy;

/* loaded from: classes4.dex */
public class AccountLiteSdkServiceImpl {
    private static final String AT_INVALID = "AT invalid";
    private static final String CONST_SIGN = "from_signin";
    private static final String CONTEXT_INVALID = "Context is null";
    private static final String DEVICEID_DEVICETYPE_EXCEPTION = "deviceID and deviceType are null";
    private static final String PARAM_EXCEPTION = "param is error";
    private static final String PICKER_SIGNIN = "pickerSignIn";
    private static final String REDIRECURI_INVALID = "RedirectUri is null";
    private static final String SCOPE_EXCEPTION = "scopes is null";
    private static final String SPLIT_CHAR = " ";
    private static final String TAG = "AccountLiteSdkServiceImpl";

    public static Intent signInH5(Context context, String str, String[] strArr, String str2, String str3, String str4, boolean z, String str5, String str6, String str7) throws kqk {
        ksy.b(TAG, "accountPicker: signInH5", true);
        if (context == null) {
            throw new kqk(CONTEXT_INVALID);
        }
        if (TextUtils.isEmpty(str6)) {
            ksy.b(TAG, "Appid is null", true);
            throw new kqk(AT_INVALID);
        }
        if (strArr == null || strArr.length == 0) {
            ksy.b(TAG, SCOPE_EXCEPTION, true);
            throw new kqk(SCOPE_EXCEPTION);
        }
        if (TextUtils.isEmpty(str2)) {
            ksy.b(TAG, REDIRECURI_INVALID, true);
            throw new kqk(REDIRECURI_INVALID);
        }
        if (TextUtils.isEmpty(str3)) {
            ksy.b(TAG, "deviceID or deviceType are null", true);
            throw new kqk(DEVICEID_DEVICETYPE_EXCEPTION);
        }
        try {
            Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
            intent.setPackage(context.getPackageName());
            intent.putExtra("key_pickerSignIn", PICKER_SIGNIN);
            if (!TextUtils.isEmpty(str7)) {
                intent.putExtra("key_cp_login_url", str7);
                intent.putExtra("key_oper", "from_other_app_signin");
            } else {
                intent.putExtra("key_oper", CONST_SIGN);
            }
            intent.putExtra("key_app_id", str6);
            intent.putExtra("key_scopes", setScopes(strArr));
            intent.putExtra("key_redirecturi", str2);
            intent.putExtra("key_code_verifier", str4);
            intent.putExtra("key_mcp_signIn", z);
            intent.putExtra("grs_app_name", str);
            if (z) {
                intent.putExtra("key_access_token", str5);
            }
            intent.putExtra("key_device_info", (Parcelable) DeviceInfo.a(context, str3));
            return intent;
        } catch (RuntimeException unused) {
            ksy.c(TAG, "RuntimeException signInH5", true);
            throw new kqk(PARAM_EXCEPTION);
        } catch (Exception unused2) {
            ksy.c(TAG, "Exception signInH5", true);
            throw new kqk(PARAM_EXCEPTION);
        }
    }

    public static void h5SignOut(Context context, ResultCallBack<kqg> resultCallBack) throws kqk {
        ksy.b(TAG, "accountPicker signOut", true);
        kqi.b(context, resultCallBack);
    }

    public static void revoke(Context context, String str, String str2, ResultCallBack<kqp> resultCallBack) throws kqk {
        ksy.b(TAG, "accountPicker revoke", true);
        kqi.c(context, str, str2, resultCallBack);
    }

    private static String setScopes(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
            sb.append(" ");
        }
        return sb.toString();
    }
}
