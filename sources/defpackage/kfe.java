package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class kfe {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14337a = new Object();
    private static kfe e;

    private kfe() {
    }

    public static kfe a() {
        kfe kfeVar;
        synchronized (f14337a) {
            if (e == null) {
                e = new kfe();
            }
            kfeVar = e;
        }
        return kfeVar;
    }

    public void d(byte[] bArr, DeviceInfo deviceInfo) {
        if (bArr == null || bArr.length < 2) {
            LogUtil.h("HwSyncTimeManager", "HwSyncTimeManager dataInfo is null");
            return;
        }
        String d = cvx.d(bArr);
        LogUtil.a("HwSyncTimeManager", "HwSyncTimeManager data ", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            LogUtil.h("HwSyncTimeManager", "HwSyncTimeManager messageHex is error");
            return;
        }
        try {
            List<cwd> e2 = new cwl().a(d.substring(4)).e();
            if (e2 != null && !e2.isEmpty()) {
                a(e2, deviceInfo);
                return;
            }
            LogUtil.h("HwSyncTimeManager", "HwSyncTimeManager tlv is error");
        } catch (cwg unused) {
            LogUtil.b("HwSyncTimeManager", "COMMAND_ID_GET_DATE TlvException");
        }
    }

    private void a(List<cwd> list, DeviceInfo deviceInfo) {
        int w;
        for (cwd cwdVar : list) {
            if (CommonUtil.a(cwdVar.e(), 16) == 1 && (w = CommonUtil.w(cwdVar.c())) == 1) {
                LogUtil.a("HwSyncTimeManager", "HwSyncTimeManager syncTime is ", Integer.valueOf(w));
                e(deviceInfo);
                return;
            }
        }
    }

    private void e(DeviceInfo deviceInfo) {
        LogUtil.a("HwSyncTimeManager", "is Enter ZoneID");
        if (deviceInfo == null) {
            LogUtil.h("HwSyncTimeManager", "btDeviceInfo is null");
            return;
        }
        if (!jsn.d(deviceInfo.getProductType())) {
            jsz.b().d(deviceInfo);
            return;
        }
        izf o = iyo.o();
        o.e(deviceInfo.getDeviceIdentify());
        jtc.c().sendDeviceData(jte.b(o));
    }
}
