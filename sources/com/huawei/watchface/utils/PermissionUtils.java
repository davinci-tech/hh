package com.huawei.watchface.utils;

import android.app.Activity;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import com.huawei.operation.ble.BleConstants;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.pluginachievement.manager.model.ResultCode;
import com.huawei.watchface.dp;
import com.huawei.watchface.environment.Environment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class PermissionUtils {

    /* renamed from: a, reason: collision with root package name */
    public static Map<String, String> f11196a = new HashMap();
    private static final String[] b = new String[0];

    public interface PermissonListener {
        void onRequested(boolean z);
    }

    public static boolean a() {
        return true;
    }

    public static Map<String, String> a(boolean z) {
        String str;
        String str2;
        HwLog.i("PermissionUtils", "getPermissionToWebConstants isH5Search:" + z);
        f11196a.clear();
        f11196a.put("android.permission.CAMERA", z ? FaqConstants.CHANNEL_HICARE : "1001");
        Map<String, String> map = f11196a;
        String str3 = "1005";
        String str4 = BleConstants.CODE_AUTHORIZED_FAIL;
        if (z) {
            str = "1005";
        } else {
            str = CommonUtils.isAndroid11() ? ResultCode.ERROR_TS_TIMEOUT : BleConstants.CODE_AUTHORIZED_FAIL;
        }
        map.put("android.permission.WRITE_EXTERNAL_STORAGE", str);
        Map<String, String> map2 = f11196a;
        if (z) {
            str2 = "1005";
        } else {
            str2 = CommonUtils.isAndroid11() ? ResultCode.ERROR_TS_TIMEOUT : BleConstants.CODE_AUTHORIZED_FAIL;
        }
        map2.put("android.permission.READ_EXTERNAL_STORAGE", str2);
        Map<String, String> map3 = f11196a;
        if (!z) {
            str3 = CommonUtils.isAndroid11() ? ResultCode.ERROR_TS_TIMEOUT : BleConstants.CODE_AUTHORIZED_FAIL;
        }
        map3.put("android.permission.READ_MEDIA_IMAGES", str3);
        Map<String, String> map4 = f11196a;
        if (CommonUtils.isAndroid11()) {
            str4 = ResultCode.ERROR_TS_TIMEOUT;
        }
        map4.put("android.permission.READ_MEDIA_VIDEO", str4);
        return f11196a;
    }

    public static boolean requestPermissionIfNeed(Activity activity, String[] strArr, int i) {
        return a(activity, strArr, i, -1);
    }

    public static boolean a(Activity activity, String[] strArr, int i, int i2) {
        if (activity == null) {
            return false;
        }
        String[] checkPermission = checkPermission(strArr);
        boolean z = !ArrayUtils.isEmpty(checkPermission);
        if (z) {
            activity.requestPermissions(checkPermission, i);
        }
        return z;
    }

    public static String[] checkPermission(String[] strArr) {
        if (!a() || ArrayUtils.isEmpty(strArr)) {
            return b;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (!checkPermission(str)) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static boolean checkPermission(String str) {
        int checkSelfPermission;
        if (!a()) {
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (CommonUtils.isAndroid14() && (TextUtils.equals(str, "android.permission.READ_MEDIA_IMAGES") || TextUtils.equals(str, "android.permission.READ_MEDIA_VIDEO"))) {
            checkSelfPermission = ContextCompat.checkSelfPermission(Environment.getApplicationContext(), "android.permission.READ_MEDIA_VISUAL_USER_SELECTED");
        } else {
            checkSelfPermission = CommonUtils.isAndroid13() ? ContextCompat.checkSelfPermission(Environment.getApplicationContext(), str) : PermissionChecker.checkSelfPermission(Environment.getApplicationContext(), str);
        }
        HwLog.i("checkPermission", "checkPermission：" + str + ",permisson" + checkSelfPermission);
        return checkSelfPermission == 0;
    }

    public static boolean verifyPermissions(int[] iArr) {
        if (iArr == null) {
            return false;
        }
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(Activity activity, String str) {
        if (activity == null) {
            return false;
        }
        boolean shouldShowRequestPermissionRationale = ActivityCompat.shouldShowRequestPermissionRationale(activity, str);
        boolean a2 = dp.a(str, "sp_permission_file", true);
        HwLog.i(HwLog.TAG, "isEverForbidPermission isForbiddenPermission:" + shouldShowRequestPermissionRationale + ",isFirst=" + a2);
        return (a2 || shouldShowRequestPermissionRationale) ? false : true;
    }
}
