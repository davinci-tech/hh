package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes7.dex */
public class ikk {
    private static Context b;

    /* renamed from: a, reason: collision with root package name */
    private ikp f13410a;

    private ikk() {
        this.f13410a = new ikp();
    }

    static class d {
        private static final ikk d = new ikk();
    }

    public static ikk a(Context context) {
        if (context != null) {
            b = context.getApplicationContext();
        }
        return d.d;
    }

    public HiDeviceInfo e(int i) {
        if (i <= 0) {
            return null;
        }
        Object b2 = this.f13410a.b(i);
        if (b2 != null && (b2 instanceof HiDeviceInfo)) {
            return (HiDeviceInfo) b2;
        }
        int a2 = iis.d().a(i);
        if (a2 <= 0) {
            LogUtil.h("Debug_DeviceInfoCache", "getDeviceInfo deviceID <= 0, clientID = ", Integer.valueOf(i));
            return null;
        }
        HiDeviceInfo a3 = ijf.d(b).a(a2);
        if (a3 == null) {
            LogUtil.h("Debug_DeviceInfoCache", "getDeviceInfo deviceInfo == null, clientID = ", Integer.valueOf(i));
            return null;
        }
        a3.setPriority(iic.b(a3.getDeviceType()).intValue());
        LogUtil.a("Debug_DeviceInfoCache", "getDeviceInfo clientID = ", Integer.valueOf(i), ", deviceInfo = ", a3);
        this.f13410a.a(i, a3);
        return a3;
    }

    public int b(int i) {
        HiDeviceInfo e = e(i);
        if (e != null) {
            return e.getDeviceType();
        }
        return 0;
    }

    public String b(HiDeviceInfo hiDeviceInfo) {
        return hiDeviceInfo != null ? hiDeviceInfo.getModel() : "";
    }

    public String d(HiDeviceInfo hiDeviceInfo) {
        return hiDeviceInfo != null ? hiDeviceInfo.getDeviceUniqueCode() : "";
    }

    public String a(HiDeviceInfo hiDeviceInfo) {
        return hiDeviceInfo != null ? hiDeviceInfo.getProdId() : "";
    }

    public String c(HiDeviceInfo hiDeviceInfo) {
        return hiDeviceInfo != null ? hiDeviceInfo.getDeviceName() : "";
    }

    public void d() {
        this.f13410a.a();
    }
}
