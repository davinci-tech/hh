package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevice.mainprocess.callback.NotifyPhoneServiceCallback;
import com.huawei.hwdevicemgr.framework.INoitifyPhoneServiceCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
class jfp {
    private Map<String, NotifyPhoneServiceCallback> c;
    private INoitifyPhoneServiceCallback d;

    private jfp() {
        this.c = new HashMap(16);
        this.d = new INoitifyPhoneServiceCallback.Stub() { // from class: jfp.2
            @Override // com.huawei.hwdevicemgr.framework.INoitifyPhoneServiceCallback
            public void executeResponse(String str, int i, DeviceInfo deviceInfo, int i2, String str2) {
                LogUtil.a("ProcessInfoRequest", "executeResponse begin.");
                if (!TextUtils.isEmpty(str) && deviceInfo != null) {
                    boolean z = false;
                    for (Map.Entry entry : jfp.this.c.entrySet()) {
                        if (str.equals(entry.getKey()) && entry.getValue() != null) {
                            ((NotifyPhoneServiceCallback) entry.getValue()).executeResponse(i, deviceInfo, i2, str2);
                            z = true;
                        }
                    }
                    LogUtil.a("ProcessInfoRequest", "executeResponse end. isFound: ", Boolean.valueOf(z));
                    return;
                }
                LogUtil.h("ProcessInfoRequest", "callback executeResponse input is invalid.");
            }
        };
        LogUtil.a("ProcessInfoRequest", "create ProcessInfoRequest");
    }

    public INoitifyPhoneServiceCallback b() {
        return this.d;
    }

    public static jfp d() {
        return e.f13789a;
    }

    public void b(String str, NotifyPhoneServiceCallback notifyPhoneServiceCallback) {
        LogUtil.h("ProcessInfoRequest", "registerCallback put: ", str);
        this.c.put(str, notifyPhoneServiceCallback);
    }

    public void c(String str) {
        LogUtil.h("ProcessInfoRequest", "unregisterCallback remove: ", str);
        this.c.remove(str);
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private static jfp f13789a = new jfp();
    }
}
