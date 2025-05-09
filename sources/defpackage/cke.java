package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class cke {
    private String b;

    public cke(String str) {
        this.b = str;
    }

    public void e(Context context, String str, int i) {
        LogUtil.a("PluginDeviceSharePreferencesManager", "Enter flushMemoryToSharedPreferences sequenceNumber");
        if (str == null || context == null) {
            LogUtil.h("PluginDeviceSharePreferencesManager", "flushMemoryToSharedPreferences(sequenceNumber) macAddress or context is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(this.b, 32768).edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public void c(Context context, String str, long j) {
        LogUtil.a("PluginDeviceSharePreferencesManager", "Enter flushMemoryToSharedPreferences time");
        if (str == null || context == null) {
            LogUtil.h("PluginDeviceSharePreferencesManager", "flushMemoryToSharedPreferences(time) macAddress or context is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(this.b, 32768).edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public void a(Context context, String str, long j) {
        LogUtil.a("PluginDeviceSharePreferencesManager", "Enter flushDeviceUsedTimeToSharedPreferences");
        if (str == null || context == null) {
            LogUtil.h("PluginDeviceSharePreferencesManager", "flushDeviceUsedTimeToSharedPreferences invalid productId or context");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(this.b, 32768).edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public int c(Context context, String str) {
        LogUtil.a("PluginDeviceSharePreferencesManager", "Enter getSequenceNumberFromSharedPreferences");
        if (str == null || context == null) {
            return 0;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.b, 32768);
        if (sharedPreferences.contains(str)) {
            return sharedPreferences.getInt(str, 0);
        }
        return 0;
    }

    public long d(Context context, String str) {
        LogUtil.a("PluginDeviceSharePreferencesManager", "Enter getSequenceNumberFromSharedPreferences");
        if (str == null || context == null) {
            return 0L;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.b, 32768);
        if (sharedPreferences.contains(str)) {
            return sharedPreferences.getLong(str, 0L);
        }
        return 0L;
    }

    public long b(Context context, String str) {
        LogUtil.a("PluginDeviceSharePreferencesManager", "Enter getDeviceUsedTimeStampFromSharedPreferences");
        if (str == null || context == null) {
            LogUtil.h("PluginDeviceSharePreferencesManager", "getDeviceUsedTimeStampFromSharedPreferences invalid productId or context");
            return 0L;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.b, 32768);
        if (sharedPreferences.contains(str)) {
            return sharedPreferences.getLong(str, 0L);
        }
        return 0L;
    }

    public void a(Context context, String str) {
        LogUtil.a("PluginDeviceSharePreferencesManager", "Enter deleteDeviceUsedTimeStampToSharedPreferences");
        if (str == null || context == null) {
            LogUtil.h("PluginDeviceSharePreferencesManager", "deleteDeviceUsedTimeStampToSharedPreferences invalid productId or context");
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.b, 32768);
        if (sharedPreferences.contains(str)) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.remove(str);
            edit.commit();
        }
    }
}
