package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.ExternalPhoneService;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class jwo {
    private static volatile jwo b;
    private static final Object d = new Object();
    private Context c;

    private jwo(Context context) {
        this.c = context;
    }

    public static jwo e() {
        jwo jwoVar;
        synchronized (d) {
            if (b == null) {
                b = new jwo(BaseApplication.getContext());
            }
            jwoVar = b;
        }
        return jwoVar;
    }

    public void a(String str) {
        int i;
        LogUtil.a("HwAlarmClockManager", "handleAlarmClockMessage hex=", str);
        if (str == null || str.length() < 20) {
            LogUtil.h("HwAlarmClockManager", "handleAlarmClockMessage hex length is error");
            return;
        }
        String substring = str.substring(8, 16);
        int i2 = -1;
        if (TextUtils.isEmpty(substring)) {
            i = -1;
        } else {
            LogUtil.a("HwAlarmClockManager", "handleAlarmClockMessage alarmIdHex=" + substring);
            i = CommonUtil.w(substring);
            LogUtil.a("HwAlarmClockManager", "handleAlarmClockMessage alarmId=" + i);
            if (i == -1) {
                return;
            }
        }
        String substring2 = str.substring(20, str.length());
        if (!TextUtils.isEmpty(substring2)) {
            int w = CommonUtil.w(substring2);
            LogUtil.a("HwAlarmClockManager", "handleAlarmClockMessage alarmActionId=" + w);
            if (w == -1) {
                return;
            } else {
                i2 = w;
            }
        }
        LogUtil.a("HwAlarmClockManager", "testAlarmClockReceive alarmActionId=" + i2);
        Intent intent = new Intent();
        intent.setClass(this.c, ExternalPhoneService.class);
        intent.setAction("alarm_clock_msg_from_device");
        intent.putExtra("notification_id", i);
        intent.putExtra("action", i2);
        try {
            this.c.startService(intent);
        } catch (IllegalStateException | SecurityException unused) {
            LogUtil.b("HwAlarmClockManager", "handleAlarmClockMessage occur exception");
        }
    }
}
