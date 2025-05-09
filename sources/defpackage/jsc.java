package defpackage;

import android.content.Context;
import com.hihonor.assistant.cardmgrsdk.CardMgrSdkConst;
import com.huawei.watchface.mvp.model.latona.provider.WatchFaceProvider;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes5.dex */
public class jsc {
    public static void e(Context context, int i) {
        if (context == null) {
            return;
        }
        SharedPreferenceManager.e(context, String.valueOf(1000001), "sp_ota_update_type", i == 0 ? CardMgrSdkConst.KEY_RECOMMEND : i == 2 ? WatchFaceProvider.BACKGROUND_LABEL : "", new StorageParams(0));
    }

    public static String d(Context context) {
        return context == null ? "" : SharedPreferenceManager.b(context, String.valueOf(1000001), "sp_ota_update_type");
    }

    public static void c(Context context, String str) {
        if (context == null) {
            return;
        }
        SharedPreferenceManager.e(context, String.valueOf(1000002), "sp_ota_update_tlv", str, new StorageParams(0));
    }

    public static String c(Context context) {
        return context == null ? "" : SharedPreferenceManager.b(context, String.valueOf(1000002), "sp_ota_update_tlv");
    }

    public static void e(Context context, String str) {
        if (context == null) {
            return;
        }
        SharedPreferenceManager.e(context, String.valueOf(1000003), "sp_ota_update_version", str, new StorageParams(0));
    }

    public static String a(Context context) {
        return context == null ? "" : SharedPreferenceManager.b(context, String.valueOf(1000003), "sp_ota_update_version");
    }

    public static void d(Context context, String str) {
        if (context == null) {
            return;
        }
        SharedPreferenceManager.e(context, String.valueOf(1000004), "sp_ota_update_connect_version", str, new StorageParams(0));
    }

    public static void b(Context context, String str) {
        if (context == null) {
            return;
        }
        SharedPreferenceManager.e(context, String.valueOf(1000005), "sp_ota_update_device_mac", str, new StorageParams(0));
    }

    public static void a(Context context, String str) {
        if (context == null) {
            return;
        }
        SharedPreferenceManager.e(context, String.valueOf(1000006), "sp_ota_update_start_version", str, new StorageParams(0));
    }

    public static String e(Context context) {
        return context == null ? "" : SharedPreferenceManager.b(context, String.valueOf(1000006), "sp_ota_update_start_version");
    }

    public static void b(Context context) {
        if (context == null) {
            return;
        }
        e(context, 1);
        d(context, "");
        b(context, "");
        e(context, "");
        a(context, "");
        c(context, "");
    }

    public static void f(Context context) {
        if (context == null) {
            return;
        }
        e(context, 1);
        d(context, "");
        b(context, "");
        e(context, "");
        a(context, "");
        c(context, "");
        SharedPreferenceManager.d(context, 0L, "ota_datatime");
        SharedPreferenceManager.d(context, 0L, "EXCE_OTA_APP_UPG_TRANS_START");
        SharedPreferenceManager.d(context, 0L, "EXCE_OTA_APP_UPG_TRANS_STOP_TIME");
    }
}
