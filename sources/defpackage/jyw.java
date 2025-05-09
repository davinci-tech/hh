package defpackage;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jyw {
    public static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("needNotice")) {
                return jSONObject.getBoolean("needNotice");
            }
            return false;
        } catch (JSONException unused) {
            LogUtil.b("CloudFileConfigUtils", "isNotice, exception");
            return false;
        }
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return jSONObject.has("srcPkgName") ? jSONObject.optString("srcPkgName") : "";
        } catch (JSONException unused) {
            LogUtil.b("CloudFileConfigUtils", "getWearName, exception");
            return "";
        }
    }

    public static int bLj_(Bundle bundle) {
        if (bundle == null) {
            return 0;
        }
        try {
            String string = bundle.getString("hex_data");
            if (TextUtils.isEmpty(string)) {
                return 0;
            }
            JSONObject jSONObject = new JSONObject(string);
            if (jSONObject.has("resourceType")) {
                return jSONObject.optInt("resourceType");
            }
            return 0;
        } catch (JSONException unused) {
            LogUtil.b("CloudFileConfigUtils", "getResourceType, exception");
            return 0;
        }
    }

    public static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("autoDelete")) {
                return jSONObject.getBoolean("autoDelete");
            }
            return false;
        } catch (JSONException unused) {
            LogUtil.b("CloudFileConfigUtils", "isAutoDelete, exception");
            return false;
        }
    }

    public static String b(cvq cvqVar) {
        if (cvqVar == null) {
            return "";
        }
        List<cvn> configInfoList = cvqVar.getConfigInfoList();
        if (configInfoList == null || configInfoList.size() == 0) {
            LogUtil.h("CloudFileConfigUtils", "onDataReceived sampleConfigInfos empty");
            return "";
        }
        String d = cvx.d(configInfoList.get(0).b());
        LogUtil.a("CloudFileConfigUtils", "getConfigData; hexConfigContent: ", d);
        return d;
    }

    public static String b(String str) {
        long j = 0;
        String b = nsn.b(BaseApplication.e(), 0L);
        if (TextUtils.isEmpty(str)) {
            return b;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.isNull("fileData")) {
                return b;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("fileData");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                if (jSONObject2.has("fileSize")) {
                    j += jSONObject2.getLong("fileSize");
                }
            }
            b = nsn.b(BaseApplication.e(), j);
            LogUtil.a("CloudFileConfigUtils", "file size is ", b);
            return b;
        } catch (JSONException unused) {
            LogUtil.b("CloudFileConfigUtils", "downloadFileSize, exception");
            return b;
        }
    }

    public static String b(int i, String str) {
        String string = BaseApplication.e().getResources().getString(R$string.IDS_notify_file_content);
        String string2 = BaseApplication.e().getResources().getString(R$string.IDS_notify_default_file);
        if (i == 1 || TextUtils.isEmpty(str)) {
            string2 = BaseApplication.e().getResources().getString(R$string.IDS_notify_sport_laguage_file);
        }
        return String.format(Locale.ROOT, string, string2, str);
    }
}
