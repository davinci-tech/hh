package defpackage;

import android.text.TextUtils;
import android.util.Log;
import com.huawei.controlcenter.featureability.sdk.IConnectCallback;
import com.huawei.controlcenter.featureability.sdk.model.ExtraParams;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public final class lsm {

    /* renamed from: a, reason: collision with root package name */
    private static final Pattern f14852a = Pattern.compile("[a-zA-Z]+[0-9a-zA-Z_]*(\\.[a-zA-Z]+[0-9a-zA-Z_]*)*");

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int length = str.length();
        if (length < 6) {
            return str;
        }
        return str.substring(0, 6) + "***" + str.substring(length - 6, length);
    }

    public static boolean c(String str, ExtraParams extraParams, IConnectCallback iConnectCallback) {
        return a(str, "packageName") && b(iConnectCallback) && e(extraParams);
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e("FAKit: ParamVerifyUtil", "Param Verify: deviceId is empty.");
            return false;
        }
        if (str.length() <= 128) {
            return true;
        }
        Log.e("FAKit: ParamVerifyUtil", "Param Verify: deviceId'length is too long");
        return false;
    }

    private static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            Log.e("FAKit: ParamVerifyUtil", "Param Verify:" + str2 + " is empty.");
            return false;
        }
        if (str.length() > 128) {
            Log.e("FAKit: ParamVerifyUtil", "Param Verify: " + str2 + "'length is too long");
            return false;
        }
        if (f14852a.matcher(str).matches()) {
            return true;
        }
        Log.e("FAKit: ParamVerifyUtil", "name not matched regex");
        return false;
    }

    public static boolean b(IConnectCallback iConnectCallback) {
        if (iConnectCallback != null) {
            return true;
        }
        Log.e("FAKit: ParamVerifyUtil", "Param Verify: callback is null.");
        return false;
    }

    public static boolean e(ExtraParams extraParams) {
        if (extraParams == null) {
            return true;
        }
        if (extraParams.getTargetPkgName() != null && extraParams.getTargetPkgName().length() > 128) {
            Log.e("FAKit: ParamVerifyUtil", "extras.targetPkgName is too long");
            return false;
        }
        if (extraParams.getDescription() != null && extraParams.getDescription().length() > 256) {
            Log.e("FAKit: ParamVerifyUtil", "extras.description is too long");
            return false;
        }
        if (extraParams.getJsonParams() == null || extraParams.getJsonParams().length() <= 4096) {
            return true;
        }
        Log.e("FAKit: ParamVerifyUtil", "extras.jsonParams is too long");
        return false;
    }
}
