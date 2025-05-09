package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jow {

    /* renamed from: a, reason: collision with root package name */
    private static jow f13996a;
    private static final Object c = new Object();
    private boolean b = false;

    private jow() {
    }

    public static jow e() {
        jow jowVar;
        LogUtil.a("HwHeartRateInfo", "HwHeartRateInfo Constructor");
        synchronized (c) {
            if (f13996a == null) {
                f13996a = new jow();
            }
            jowVar = f13996a;
        }
        return jowVar;
    }

    public void d(boolean z) {
        this.b = z;
    }

    public int c(String str) {
        int i = BaseApplication.getContext().getSharedPreferences("heartRateRemindSP", 0).getInt(str, -1);
        LogUtil.a("HwHeartRateInfo", "heart rate remind default open flag is ", Integer.valueOf(i));
        return i;
    }
}
