package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.watchface.videoedit.gles.Constant;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.LinkedHashMap;

/* loaded from: classes9.dex */
public class jjc {
    private static final Object c = new Object();
    private static jjc e;

    /* renamed from: a, reason: collision with root package name */
    private StorageParams f13885a = new StorageParams(0);
    private Context b;

    private jjc(Context context) {
        this.b = context.getApplicationContext();
    }

    public static jjc b(Context context) {
        jjc jjcVar;
        synchronized (c) {
            if (e == null) {
                e = new jjc(context);
            }
            jjcVar = e;
        }
        return jjcVar;
    }

    public long e() {
        LogUtil.a("HwNotificationBiTools", "getUpdateMessageNumberTime");
        return a("update_record_message_number_time");
    }

    public void b(long j) {
        LogUtil.a("HwNotificationBiTools", "setUpdateMessageNumberTime is:", Long.valueOf(j));
        d(j, "update_record_message_number_time");
    }

    public long b() {
        LogUtil.a("HwNotificationBiTools", "getReportBiTime");
        return a("update_report_number_time");
    }

    public void a(long j) {
        LogUtil.a("HwNotificationBiTools", "setReportBiTime is:", Long.valueOf(j));
        d(j, "update_report_number_time");
    }

    private long a(String str) {
        long j;
        String b = SharedPreferenceManager.b(this.b, String.valueOf(10039), str);
        try {
            j = Long.parseLong(b);
        } catch (NumberFormatException unused) {
            LogUtil.b("HwNotificationBiTools", "getCurrentSystemTime NumberFormatException");
            j = 0;
        }
        LogUtil.a("HwNotificationBiTools", "getCurrentSystemTime currentTime is:", b, ",time is:", Long.valueOf(j));
        return j;
    }

    private void d(long j, String str) {
        if (SharedPreferenceManager.e(this.b, String.valueOf(10039), str, String.valueOf(j), this.f13885a) == 0) {
            LogUtil.a("HwNotificationBiTools", "setCurrentSystemTime setSharedPreference success");
        } else {
            LogUtil.h("HwNotificationBiTools", "setCurrentSystemTime setSharedPreference failed");
        }
    }

    public void a(int i) {
        e(this.b, "received_message_number", b(this.b, "received_message_number") + i);
        LogUtil.a("HwNotificationBiTools", "setReceivedNumber SharedPreference");
    }

    public void d(int i) {
        e(this.b, "filtered_message_number", b(this.b, "filtered_message_number") + i);
        LogUtil.a("HwNotificationBiTools", "setFilteredNumber SharedPreference");
    }

    public void b(int i) {
        e(this.b, "synergy_message_number", b(this.b, "synergy_message_number") + i);
        LogUtil.a("HwNotificationBiTools", "setSynergyNumber SharedPreference");
    }

    private int b(Context context, String str) {
        try {
            return Integer.parseInt(SharedPreferenceManager.b(context, String.valueOf(10039), str));
        } catch (NumberFormatException unused) {
            LogUtil.b("HwNotificationBiTools", "getMessageNumber NumberFormatException");
            return 0;
        }
    }

    private void e(Context context, String str, int i) {
        if (SharedPreferenceManager.e(context, String.valueOf(10039), str, String.valueOf(i), this.f13885a) == 0) {
            LogUtil.a("HwNotificationBiTools", "upDateMessageNumber setSharedPreference success");
        } else {
            LogUtil.h("HwNotificationBiTools", "upDateMessageNumber setSharedPreference failed");
        }
    }

    public void e(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("HwNotificationBiTools", "setBiModel method parameter is illegal");
            return;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
        linkedHashMap.put(str, str2);
        OpAnalyticsUtil.getInstance().setEvent2nd(str3, linkedHashMap);
        LogUtil.a("HwNotificationBiTools", "report BI success,key is:", str, "value is :", str2);
    }

    public void d() {
        String b = SharedPreferenceManager.b(this.b, String.valueOf(10039), "received_message_number");
        String b2 = SharedPreferenceManager.b(this.b, String.valueOf(10039), "filtered_message_number");
        String b3 = SharedPreferenceManager.b(this.b, String.valueOf(10039), "synergy_message_number");
        e("listen", b, OperationKey.NOTIFY_RECEIVED_MESSAGE_2129001.value());
        e(Constant.FILTER, b2, OperationKey.NOTIFY_FILTERED_MESSAGE_2129002.value());
        e("HwSynergy", b3, OperationKey.NOTIFY_SYNERGY_PUSH_2129006.value());
    }
}
