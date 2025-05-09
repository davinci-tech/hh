package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.App;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.ipc.CallResult;
import com.huawei.openalliance.ad.mr;
import com.huawei.operation.OpAnalyticsConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    private static final k f7710a = new k();
    private final AtomicBoolean b = new AtomicBoolean(true);

    public List<App> b(Context context) {
        try {
            CallResult<String> a2 = mr.a(context).a("queryUninstalledAppInfo", null, String.class);
            if (a2 == null) {
                ho.c("AppRestoreUtils", "get error with result");
                return null;
            }
            if (b(a2)) {
                return a(a2);
            }
            return null;
        } catch (Throwable unused) {
            ho.c("AppRestoreUtils", "get info failed");
            return null;
        }
    }

    public boolean a(Context context) {
        String str;
        ho.a("AppRestoreUtils", "isRemoteSupport:%s", this.b);
        if (!this.b.get()) {
            return false;
        }
        if (!x.o(context) && !x.n(context)) {
            str = "device not phone or pad, not support";
        } else {
            if (com.huawei.openalliance.ad.bz.b(context)) {
                return true;
            }
            str = "device not support";
        }
        ho.a("AppRestoreUtils", str);
        return false;
    }

    public int a(Context context, AppDownloadTask appDownloadTask) {
        if (context != null && appDownloadTask != null) {
            try {
                AppInfo B = appDownloadTask.B();
                if (B == null) {
                    return -1;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("appInfo", be.a(B));
                jSONObject.put("slotId", appDownloadTask.M());
                jSONObject.put("appTaskInfo", appDownloadTask.N());
                CallResult a2 = mr.a(context).a("restoreAppByPackageName", jSONObject.toString(), String.class);
                if (a2 == null || !b(a2)) {
                    return -1;
                }
                String str = (String) a2.getData();
                if (!"0".equals(str) && !OpAnalyticsConstants.SSL_FAIL_CODE.equals(str)) {
                    if (OpAnalyticsConstants.WATCH_FAIL_CODE.equals(str)) {
                        return -2;
                    }
                }
                return 0;
            } catch (Throwable unused) {
                ho.c("AppRestoreUtils", "call remote occurs exception");
            }
        }
        return -1;
    }

    private <T> boolean b(CallResult<T> callResult) {
        int code = callResult.getCode();
        ho.a("AppRestoreUtils", "call rs result code:%s", Integer.valueOf(code));
        boolean z = code == 200;
        if (z) {
            this.b.set(!"-100".equals(callResult.getData()));
            ho.a("AppRestoreUtils", "updated isRemoteSupport:%s", Boolean.valueOf(this.b.get()));
        }
        return z;
    }

    private List<App> a(CallResult<String> callResult) {
        String data = callResult.getData();
        ArrayList arrayList = null;
        if (TextUtils.isEmpty(data)) {
            return null;
        }
        JSONArray optJSONArray = new JSONObject(data).optJSONArray("result_list");
        if (optJSONArray != null) {
            arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject = optJSONArray.getJSONObject(i);
                App app = new App();
                app.b(jSONObject.optString("packageName"));
                app.a(jSONObject.optString("versionName"));
                arrayList.add(app);
            }
        }
        return arrayList;
    }

    public static k a() {
        return f7710a;
    }

    private k() {
    }
}
