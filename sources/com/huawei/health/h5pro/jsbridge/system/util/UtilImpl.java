package com.huawei.health.h5pro.jsbridge.system.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class UtilImpl implements Util {
    @Override // com.huawei.health.h5pro.jsbridge.system.util.Util
    public boolean isAppInstalled(Context context, String str) {
        LogUtil.i("H5PRO_util", "isAppInstalled start");
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.i("H5PRO_util", "context or packageName is null");
            return false;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            return packageManager.getPackageInfo(str, 0) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.util.Util
    public JSONObject deviceInfo(Context context) {
        if (context == null) {
            return null;
        }
        int px2dip = CommonUtil.px2dip(context, CommonUtil.getStatusBarHeight(context));
        boolean z = (context.getResources().getConfiguration().uiMode & 48) == 32;
        int activityWindowMode = context instanceof Activity ? CommonUtil.getActivityWindowMode((Activity) context) : 0;
        if (activityWindowMode == 1 || activityWindowMode == 3) {
            px2dip = 0;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("topLayout", px2dip);
            jSONObject.put("bottomLayout", 0);
            jSONObject.put("isDarkTheme", z);
            jSONObject.put("winMode", activityWindowMode);
            LogUtil.i("H5PRO_util", "deviceInfo: result -> " + jSONObject);
            return jSONObject;
        } catch (JSONException unused) {
            LogUtil.e("H5PRO_util", "build result json exception");
            return null;
        }
    }
}
