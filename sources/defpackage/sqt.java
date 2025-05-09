package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.File;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class sqt {
    public static boolean b() {
        String d = d("webCashSwitch");
        LogUtil.c("R_SwitchConfig", "isClosedWebCash status: ", d);
        return "CLOSE".equals(d);
    }

    public static String d(String str) {
        String d = drd.d("com.huawei.health_common_config", "SwitchConfig", "json");
        LogUtil.c("R_SwitchConfig", "filePath = ", d);
        if (TextUtils.isEmpty(d)) {
            return "";
        }
        String t = CommonUtil.t(d);
        if (TextUtils.isEmpty(t)) {
            LogUtil.a("commonConfigStr isEmpty", new Object[0]);
            return "";
        }
        try {
            JSONArray jSONArray = new JSONArray(t);
            int d2 = CommonUtil.d(BaseApplication.getContext());
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject == null) {
                    LogUtil.a("object == null", new Object[0]);
                } else {
                    JSONArray optJSONArray = optJSONObject.optJSONArray(str);
                    if (optJSONArray != null) {
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                            if (optJSONObject2 != null) {
                                int optInt = optJSONObject2.optInt("minVersion");
                                int optInt2 = optJSONObject2.optInt("maxVersion");
                                if (optInt2 == 0) {
                                    optInt2 = Integer.MAX_VALUE;
                                }
                                String optString = optJSONObject2.optString("status");
                                LogUtil.c("R_SwitchConfig", "currentVer: ", Integer.valueOf(d2), " minVer: ", Integer.valueOf(optInt), " maxVer: ", Integer.valueOf(optInt2), " status: ", optString);
                                if (d2 >= optInt && d2 <= optInt2 && "CLOSE".equals(optString)) {
                                    return optString;
                                }
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            ReleaseLogUtil.c("R_SwitchConfig", "the exception msg is: ", ExceptionUtils.d(e));
        }
        return "";
    }

    public static void d() {
        HashMap hashMap = new HashMap();
        hashMap.put("configType", "SwitchConfig");
        drd.e(new dql("com.huawei.health_common_config", hashMap), "SwitchConfig", 1, (DownloadCallback<File>) new DownloadCallback() { // from class: sqt.4
            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFinish(Object obj) {
                LogUtil.c("R_SwitchConfig", "preDownloadConfig success, result: ", obj.toString());
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onProgress(long j, long j2, boolean z, String str) {
                LogUtil.c("R_SwitchConfig", "preDownloadConfig onProgress, already: ", Long.valueOf(j), ", total: " + j2, ", fileId: ", str);
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFail(int i, Throwable th) {
                LogUtil.e("R_SwitchConfig", "preDownloadConfig failed, e: ", th.getMessage());
            }
        });
    }
}
