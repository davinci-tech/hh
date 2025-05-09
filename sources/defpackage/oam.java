package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.datatype.DataDeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.Utils;
import org.slf4j.Marker;

/* loaded from: classes6.dex */
public class oam {

    /* renamed from: a, reason: collision with root package name */
    private static ixz f15584a;
    private static ixx b;

    /* JADX INFO: Access modifiers changed from: private */
    public static ixx d() {
        return b;
    }

    private static void a(ixx ixxVar) {
        b = ixxVar;
    }

    public void c(String str, Context context, IBaseResponseCallback iBaseResponseCallback) {
        DeviceInfo e;
        if (d() == null) {
            a(ixx.d());
        }
        if (f15584a == null) {
            f15584a = new ixz();
        }
        LogUtil.c("WearBIUtil", "huid = ", LoginInit.getInstance(context).getAccountInfo(1011));
        if (TextUtils.isEmpty(str)) {
            e = jpt.d("WearBIUtil");
        } else {
            e = jpt.e(str, "WearBIUtil");
        }
        if (e != null) {
            LogUtil.a("WearBIUtil", "mDeviceInfo.setBiAnalyticsData curDeviceInfo = ", e.getSoftVersion());
            c(e);
            if (e.getProductType() >= 57) {
                d().b(f15584a);
                if (iBaseResponseCallback != null) {
                    iBaseResponseCallback.d(0, f15584a);
                }
            }
        }
        e(str, iBaseResponseCallback, e);
    }

    private void e(String str, final IBaseResponseCallback iBaseResponseCallback, final DeviceInfo deviceInfo) {
        jot.a().b(str, new IBaseResponseCallback() { // from class: oam.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0 && obj != null) {
                    DataDeviceInfo dataDeviceInfo = obj instanceof DataDeviceInfo ? (DataDeviceInfo) obj : null;
                    if (dataDeviceInfo != null) {
                        oam.f15584a.c(dataDeviceInfo.getDeviceSoftVersion());
                        if (dataDeviceInfo.getDeviceType() >= 34) {
                            oam.f15584a.a(dataDeviceInfo.getDeviceModel());
                        }
                        LogUtil.c("WearBIUtil", "mDeviceInfo.DeviceType = " + oam.f15584a.d());
                        LogUtil.c("WearBIUtil", "mDeviceInfo.DeviceVersion = " + oam.f15584a.a());
                        if (3 == dataDeviceInfo.getDeviceType()) {
                            oam.f15584a.b(dataDeviceInfo.getDeviceSn());
                        } else if (deviceInfo != null && dataDeviceInfo.getDeviceType() == 10) {
                            if (TextUtils.isEmpty(deviceInfo.getUuid())) {
                                oam.f15584a.b(dataDeviceInfo.getDeviceSn());
                            } else {
                                oam.f15584a.b(deviceInfo.getUuid());
                            }
                        } else {
                            LogUtil.c("WearBIUtil", "don't need special handle ProductType");
                        }
                        oam.d().b(oam.f15584a);
                    }
                }
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(i, obj);
                }
            }
        });
    }

    private void c(DeviceInfo deviceInfo) {
        String udidFromDevice = deviceInfo.getUdidFromDevice();
        if (!TextUtils.isEmpty(udidFromDevice)) {
            f15584a.b(udidFromDevice);
        } else {
            if (Utils.o()) {
                String replace = knl.d(deviceInfo.getSecurityDeviceId()).replace(Marker.ANY_NON_NULL_MARKER, "A").replace("=", "A").replace("/", "A");
                if (replace.length() >= 24) {
                    replace = replace.substring(0, 24);
                }
                f15584a.b(replace);
            } else if (deviceInfo.getDeviceIdType() == 1) {
                String replace2 = knl.d(deviceInfo.getSecurityDeviceId()).replace(Marker.ANY_NON_NULL_MARKER, "A").replace("=", "A").replace("/", "A");
                if (replace2.length() >= 24) {
                    replace2 = replace2.substring(0, 24);
                }
                f15584a.b(replace2);
            } else {
                f15584a.b(deviceInfo.getDeviceIdentify());
            }
            e(deviceInfo);
        }
        if (11 == deviceInfo.getProductType() && "HUAWEI CM-R1P".equals(deviceInfo.getDeviceName())) {
            f15584a.a("R1-PRO");
        } else if (deviceInfo.getProductType() == 10 && (TextUtils.equals(deviceInfo.getDeviceName(), "PORSCHE DESIGN") || TextUtils.equals(deviceInfo.getDeviceModel(), "PORSCHE DESIGN"))) {
            f15584a.a("PORSCHE DESIGN");
        } else if (deviceInfo.getProductType() >= 34) {
            f15584a.a(deviceInfo.getDeviceModel());
        } else {
            f15584a.a(jpp.c(deviceInfo));
        }
        f15584a.c(deviceInfo.getSoftVersion());
        f15584a.h(deviceInfo.getUdidFromDevice());
        f15584a.e(deviceInfo.getSecurityDeviceId());
        f15584a.d(deviceInfo.getDeviceIdentify());
        f15584a.b(deviceInfo.getProductType());
    }

    private void e(DeviceInfo deviceInfo) {
        if (deviceInfo != null && deviceInfo.getProductType() >= 58) {
            f15584a.b(deviceInfo.getDeviceUdid());
        }
    }
}
