package defpackage;

import android.content.Context;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.ui.main.R$string;
import com.huawei.wearengine.auth.Permission;

/* loaded from: classes9.dex */
public class pfr {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String a(Context context, String str, String str2, boolean z) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1480384828:
                if (str.equals("device_manager")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1039689911:
                if (str.equals("notify")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -905948230:
                if (str.equals("sensor")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -445736861:
                if (str.equals("motion_sensor")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -340472282:
                if (str.equals("wear_user_status")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 25210084:
                if (str.equals(PluginPayAdapter.KEY_DEVICE_INFO_SN)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            if (toe.c().contains(str2)) {
                return context.getString(R$string.IDS_wear_device_describe);
            }
            return d(context, z, R$string.IDS_permission_wear_engine_device_manager_describe, R$string.IDS_wear_device_describe);
        }
        if (c == 1) {
            return d(context, z, R$string.IDS_permission_wear_engine_notify_describe, R$string.IDS_wear_notify_describe);
        }
        if (c == 2) {
            return d(context, z, R$string.IDS_permission_wear_engine_sensor_describe, R$string.IDS_wear_human_sensor_describe);
        }
        if (c == 3) {
            return context.getString(R$string.IDS_wear_motion_sensor_desc);
        }
        if (c != 4) {
            return c != 5 ? "" : context.getString(R$string.IDS_wear_device_sn_desc);
        }
        return context.getString(R$string.IDS_wear_user_status_desc);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String c(Context context, String str, boolean z) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1480384828:
                if (str.equals("device_manager")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1039689911:
                if (str.equals("notify")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -905948230:
                if (str.equals("sensor")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -445736861:
                if (str.equals("motion_sensor")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -340472282:
                if (str.equals("wear_user_status")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 25210084:
                if (str.equals(PluginPayAdapter.KEY_DEVICE_INFO_SN)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return d(context, z, R$string.IDS_permission_wear_engine_device_manager, R$string.IDS_wear_device);
        }
        if (c == 1) {
            return context.getString(R$string.IDS_permission_wear_engine_notify);
        }
        if (c == 2) {
            return d(context, z, R$string.IDS_permission_wear_engine_sensor, R$string.IDS_wear_human_sensor);
        }
        if (c == 3) {
            return context.getString(R$string.IDS_wear_motion_sensor);
        }
        if (c != 4) {
            return c != 5 ? "" : context.getString(R$string.IDS_wear_device_sn);
        }
        return context.getString(R$string.IDS_wear_user_status);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static Permission b(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1480384828:
                if (str.equals("device_manager")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1039689911:
                if (str.equals("notify")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -905948230:
                if (str.equals("sensor")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -445736861:
                if (str.equals("motion_sensor")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -340472282:
                if (str.equals("wear_user_status")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 25210084:
                if (str.equals(PluginPayAdapter.KEY_DEVICE_INFO_SN)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return Permission.DEVICE_MANAGER;
        }
        if (c == 1) {
            return Permission.NOTIFY;
        }
        if (c == 2) {
            return Permission.SENSOR;
        }
        if (c == 3) {
            return Permission.MOTION_SENSOR;
        }
        if (c == 4) {
            return Permission.WEAR_USER_STATUS;
        }
        if (c != 5) {
            return null;
        }
        return Permission.DEVICE_SN;
    }

    private static String d(Context context, boolean z, int i, int i2) {
        if (z) {
            return context.getString(i);
        }
        return context.getString(i2);
    }
}
