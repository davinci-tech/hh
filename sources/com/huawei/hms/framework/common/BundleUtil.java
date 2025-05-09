package com.huawei.hms.framework.common;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class BundleUtil {
    public static final String TAG = "BundleUtil";
    public static final String UNDERLINE_TAG = "_";

    public static String transPkgPrefix(String str) {
        StringBuilder sb = new StringBuilder();
        if (str != null && str.contains(".")) {
            String[] split = str.split("\\.");
            if (split.length > 0) {
                for (String str2 : split) {
                    sb.append(str2);
                }
            }
            sb.append("_");
        }
        return sb.toString();
    }

    public static String bundleToJson(Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        if (bundle == null) {
            return jSONObject.toString();
        }
        try {
            for (String str : bundle.keySet()) {
                jSONObject.put(str, String.valueOf(bundle.get(str)));
            }
        } catch (JSONException unused) {
            Logger.e(TAG, "bundleToJson meet error");
        }
        return jSONObject.toString();
    }

    public static Bundle jsonToBundle(String str) {
        Bundle bundle = new Bundle();
        if (TextUtils.isEmpty(str)) {
            return bundle;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.optString(next));
            }
        } catch (JSONException unused) {
            Logger.e(TAG, "jsonToBundle meet error");
        }
        return bundle;
    }
}
