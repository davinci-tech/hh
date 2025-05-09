package com.huawei.health.device.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class DeviceCloudSharePreferencesManager {

    /* renamed from: a, reason: collision with root package name */
    private Context f2226a;
    private String b;
    private String d;

    public DeviceCloudSharePreferencesManager(Context context) {
        this(context, null);
    }

    public DeviceCloudSharePreferencesManager(Context context, String str) {
        this.d = "threedevicecloudSPfile";
        if (TextUtils.isEmpty(str)) {
            this.b = this.d;
        } else {
            this.b = str;
        }
        this.f2226a = context;
    }

    public void a(String str, String str2) {
        Context context;
        if (str2 == null || (context = this.f2226a) == null) {
            LogUtil.h("Plugin_DeviceCloudSharePreferencesManager", "commitFreshTimeToSharePreferences mContext is null or value is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(this.b, 0).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public void b(String str, boolean z) {
        Context context = this.f2226a;
        if (context == null) {
            LogUtil.h("Plugin_DeviceCloudSharePreferencesManager", "commitStatusToSharePreferences mContext is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(this.b, 0).edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public String b(String str) {
        Context context = this.f2226a;
        if (context == null) {
            LogUtil.h("Plugin_DeviceCloudSharePreferencesManager", "getFreshTimeFromSharePreferences mContext is null");
            return "0";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.b, 0);
        return !sharedPreferences.contains(str) ? "0" : sharedPreferences.getString(str, "0");
    }

    public void a(String str, boolean z) {
        Context context = this.f2226a;
        if (context == null) {
            LogUtil.h("Plugin_DeviceCloudSharePreferencesManager", "commitUserDownloadToSharePreferences mContext is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(this.b, 0).edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public boolean e(String str) {
        Context context = this.f2226a;
        if (context == null) {
            LogUtil.h("Plugin_DeviceCloudSharePreferencesManager", "getStatusFromSharePreferences mContext is null");
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.b, 0);
        if (sharedPreferences.contains(str)) {
            return sharedPreferences.getBoolean(str, false);
        }
        return false;
    }

    public boolean g(String str) {
        Context context = this.f2226a;
        if (context == null) {
            LogUtil.h("Plugin_DeviceCloudSharePreferencesManager", "getUserDownloadFromSharePreferences mContext is null");
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.b, 0);
        if (sharedPreferences.contains(str)) {
            return sharedPreferences.getBoolean(str, false);
        }
        return false;
    }

    public long d(String str) {
        Context context = this.f2226a;
        if (context == null) {
            LogUtil.h("Plugin_DeviceCloudSharePreferencesManager", "getLongData mContext is null");
            return 0L;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.b, 0);
        if (sharedPreferences.contains(str)) {
            return sharedPreferences.getLong(str, 0L);
        }
        return 0L;
    }

    public void b(String str, long j) {
        Context context = this.f2226a;
        if (context == null) {
            LogUtil.h("Plugin_DeviceCloudSharePreferencesManager", "putLongData mContext is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(this.b, 0).edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public String c(String str) {
        Context context = this.f2226a;
        if (context == null) {
            return "";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.b, 0);
        return !sharedPreferences.contains(str) ? "" : sharedPreferences.getString(str, "");
    }

    public void d(String str, String str2) {
        Context context = this.f2226a;
        if (context == null) {
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(this.b, 0).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public void c(String str, Map<String, String> map) {
        if (this.f2226a == null || TextUtils.isEmpty(str) || map == null) {
            LogUtil.c("Plugin_DeviceCloudSharePreferencesManager", "putHashMapData param is null");
            return;
        }
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            try {
                jSONObject.put(entry.getKey(), entry.getValue());
            } catch (JSONException unused) {
                LogUtil.b("Plugin_DeviceCloudSharePreferencesManager", "putHashMapData JSONException");
            }
        }
        jSONArray.put(jSONObject);
        SharedPreferences.Editor edit = this.f2226a.getSharedPreferences(this.b, 0).edit();
        edit.putString(str, jSONArray.toString());
        edit.commit();
    }

    public Map<String, String> a(String str) {
        if (this.f2226a == null) {
            return new HashMap();
        }
        HashMap hashMap = new HashMap();
        try {
            JSONArray jSONArray = new JSONArray(this.f2226a.getSharedPreferences(this.b, 0).getString(str, ""));
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                JSONArray names = jSONObject.names();
                if (names != null) {
                    for (int i2 = 0; i2 < names.length(); i2++) {
                        String string = names.getString(i2);
                        hashMap.put(string, jSONObject.getString(string));
                    }
                }
            }
        } catch (JSONException unused) {
            LogUtil.b("Plugin_DeviceCloudSharePreferencesManager", "getHashMapData JSONException");
        }
        return hashMap;
    }
}
