package com.huawei.watchface;

import com.google.gson.Gson;
import com.huawei.watchface.utils.HwLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class dx {

    /* renamed from: a, reason: collision with root package name */
    private static final dx f10998a = new dx();
    private static final Object b = new Object();

    private dx() {
    }

    public static dx a() {
        dx dxVar;
        synchronized (b) {
            dxVar = f10998a;
        }
        return dxVar;
    }

    public String a(Object obj) {
        return new Gson().toJson(obj);
    }

    public <T> T a(String str, Class<T> cls) {
        HwLog.i("WatchFaceJsonUtil", "fromJson is called");
        Gson gson = new Gson();
        if (!a(str)) {
            HwLog.w("WatchFaceJsonUtil", "fromJson no valid , return null");
            return null;
        }
        return (T) gson.fromJson(str, (Class) cls);
    }

    private static boolean a(String str) {
        try {
            try {
                new JSONObject(str);
                return true;
            } catch (JSONException unused) {
                new JSONArray(str);
                return true;
            }
        } catch (JSONException unused2) {
            return false;
        }
    }

    public static List<String> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            HwLog.i("WatchFaceJsonUtil", "parseStringList stringListJson is null");
            return null;
        }
        int length = jSONArray.length();
        if (length == 0) {
            HwLog.i("WatchFaceJsonUtil", "parseStringList stringListJson size is 0");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(jSONArray.optString(i));
        }
        return arrayList;
    }
}
