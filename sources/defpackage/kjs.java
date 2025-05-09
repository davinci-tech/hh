package defpackage;

import android.os.Handler;
import android.os.Message;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import health.compact.a.Services;
import java.util.List;

/* loaded from: classes.dex */
public class kjs {

    /* renamed from: a, reason: collision with root package name */
    private volatile boolean f14423a;
    private IBaseResponseCallback d;
    private e e;

    private kjs() {
        this.f14423a = true;
        this.e = new e();
    }

    public boolean e() {
        return this.f14423a;
    }

    public void c(boolean z) {
        this.f14423a = z;
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        this.d = iBaseResponseCallback;
    }

    public void e(List<DeviceInfo> list) {
        if (!list.isEmpty() || ((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isNoneHonourAm16BondedDevice()) {
            return;
        }
        this.f14423a = true;
        this.e.sendEmptyMessageDelayed(2, OpAnalyticsConstants.H5_LOADING_DELAY);
    }

    public void b(int i) {
        this.e.removeMessages(i);
    }

    public static kjs d() {
        return b.c;
    }

    /* loaded from: classes5.dex */
    public class e extends Handler {
        public e() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            LogUtil.a("RamOptimizationManager", "ProcessKillHandler get msg:", Integer.valueOf(message.what));
            if (message.what == 2) {
                if (kjs.this.d != null) {
                    kjs.this.d.onResponse(2, "no device");
                    return;
                }
                return;
            }
            LogUtil.c("RamOptimizationManager", "handle other branches");
        }
    }

    /* loaded from: classes5.dex */
    static class b {
        private static kjs c = new kjs();
    }
}
