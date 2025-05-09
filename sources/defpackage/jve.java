package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes.dex */
public class jve implements ParserInterface {

    /* renamed from: a, reason: collision with root package name */
    private static jve f14116a;
    private static final Object d = new Object();

    private jve() {
    }

    public static jve c() {
        jve jveVar;
        synchronized (d) {
            LogUtil.a("HwFileServicesManagerQueue", "getInstance() instance = ", f14116a);
            if (f14116a == null) {
                f14116a = new jve();
            }
            jveVar = f14116a;
        }
        return jveVar;
    }

    private static void b() {
        synchronized (d) {
            f14116a = null;
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        LogUtil.a("HwFileServicesManagerQueue", "getResult.");
        jvf.e().e(bArr, deviceInfo);
    }

    public void d() {
        jvf.e().a();
        b();
    }
}
