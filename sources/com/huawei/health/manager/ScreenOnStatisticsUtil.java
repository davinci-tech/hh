package com.huawei.health.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.moj;
import health.compact.a.CommonUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class ScreenOnStatisticsUtil {
    public static void e(Context context) {
        Integer a2;
        if (context == null) {
            LogUtil.a("R_ScreenOnStatisticsHelper", "screenOnCount context == null");
            return;
        }
        Map<Integer, Integer> c = c(context);
        HashMap hashMap = new HashMap(c);
        int b = DateFormatUtil.b(System.currentTimeMillis());
        LogUtil.a("R_ScreenOnStatisticsHelper", "allCounts:", c);
        if (hashMap.size() >= 2 && !hashMap.containsKey(Integer.valueOf(b)) && (a2 = a(hashMap)) != null) {
            hashMap.remove(a2);
        }
        Integer num = (Integer) hashMap.get(Integer.valueOf(b));
        hashMap.put(Integer.valueOf(b), Integer.valueOf((num != null ? num.intValue() : 0) + 1));
        d(context, hashMap);
    }

    private static Integer a(Map<Integer, Integer> map) {
        Map.Entry entry = (Map.Entry) Collections.min(map.entrySet(), Map.Entry.comparingByKey());
        if (entry != null) {
            return (Integer) entry.getKey();
        }
        return null;
    }

    private static int d(Context context, long j) {
        Map<Integer, Integer> c = c(context);
        int b = DateFormatUtil.b(j);
        if (c == null) {
            return 0;
        }
        if (!c.containsKey(Integer.valueOf(b))) {
            LogUtil.a("R_ScreenOnStatisticsHelper", "getDataScreenCount not contains");
            return 0;
        }
        Integer num = c.get(Integer.valueOf(b));
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public static boolean a(Context context, long j) {
        return d(context, j) > 10;
    }

    private static void d(Context context, Map<Integer, Integer> map) {
        if (context == null) {
            LogUtil.h("R_ScreenOnStatisticsHelper", "setScreenOnList context is null");
            return;
        }
        String e = moj.e(map);
        SharedPreferences.Editor edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit();
        if (edit != null) {
            edit.putString("ScreenOnCounts", e);
            edit.commit();
        }
    }

    public static Map<Integer, Integer> c(Context context) {
        if (context == null) {
            LogUtil.h("R_ScreenOnStatisticsHelper", "getScreenOnStatistics context is null");
            return new HashMap();
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        HashMap hashMap = new HashMap();
        if (sharedPreferences == null) {
            return hashMap;
        }
        String string = sharedPreferences.getString("ScreenOnCounts", "");
        if (TextUtils.isEmpty(string)) {
            return new HashMap();
        }
        try {
            return (Map) new Gson().fromJson(CommonUtil.z(string), new TypeToken<Map<Integer, Integer>>() { // from class: com.huawei.health.manager.ScreenOnStatisticsUtil.1
            }.getType());
        } catch (JsonSyntaxException unused) {
            LogUtil.b("R_ScreenOnStatisticsHelper", "JsonSyntaxException");
            return new HashMap();
        }
    }
}
