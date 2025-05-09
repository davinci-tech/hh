package defpackage;

import android.content.Context;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import com.huawei.multisimsdk.multidevicemanager.common.AuthParam;

/* loaded from: classes5.dex */
public class lnc {
    private static boolean c(int i) {
        return i >= 0 && i < 2;
    }

    private static int c(Context context) {
        SubscriptionManager.from(context);
        return SubscriptionManager.getDefaultDataSubscriptionId();
    }

    public static String e(Context context) {
        if (context == null) {
            loq.c("SimCardInfo", "Get imsi failed, context is null.");
            return null;
        }
        if (!lni.e("android.permission.READ_PHONE_STATE", context)) {
            loq.c("SimCardInfo", "Get imsi failed, do not have READ_PHONE_STATE permission.");
            return null;
        }
        return ((TelephonyManager) context.getSystemService("phone")).createForSubscriptionId(c(context)).getSubscriberId();
    }

    public static String e(Context context, AuthParam authParam) {
        String e;
        if (authParam != null) {
            e = d(context, authParam.getSlotId());
        } else {
            e = e(context);
        }
        loq.c("SimCardInfo", "getImsi(), mAuthParam is null ");
        return e;
    }

    public static String d(Context context, int i) {
        if (context == null) {
            loq.c("SimCardInfo", "Get IMSI failed, context is null");
            return null;
        }
        if (!c(i)) {
            loq.c("SimCardInfo", "Get IMSI failed, slotId is invalid");
            return null;
        }
        if (!lni.e("android.permission.READ_PHONE_STATE", context)) {
            loq.c("SimCardInfo", "Get IMSI failed, do not have READ_PHONE_STATE permission.");
            return null;
        }
        return ((TelephonyManager) context.getSystemService("phone")).createForSubscriptionId(lop.c(context, i)).getSubscriberId();
    }

    public static String a(Context context, int i) {
        if (context == null) {
            loq.c("SimCardInfo", "Get IMEI failed, context is null");
            return null;
        }
        if (!c(i)) {
            loq.c("SimCardInfo", "Get IMEI failed, slotId is invalid");
            return null;
        }
        if (!lni.e("android.permission.READ_PHONE_STATE", context)) {
            loq.c("SimCardInfo", "Get IMEI failed, do not have READ_PHONE_STATE permission.");
            return null;
        }
        return ((TelephonyManager) context.getSystemService("phone")).getImei(i);
    }
}
