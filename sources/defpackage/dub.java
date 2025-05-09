package defpackage;

import com.huawei.health.device.callback.HeartRateDeviceSelectedCallback;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dub {

    /* renamed from: a, reason: collision with root package name */
    private IBaseResponseCallback f11835a;
    private List<String> b;
    private HeartRateDeviceSelectedCallback d;
    private HealthDevice e;

    static class e {
        private static final dub d = new dub();
    }

    private dub() {
        this.b = new ArrayList(10);
        this.f11835a = new IBaseResponseCallback() { // from class: dub.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 100000 && (obj instanceof JSONObject)) {
                    dug.c().d((JSONObject) obj, 50001);
                }
            }
        };
        this.d = new HeartRateDeviceSelectedCallback() { // from class: dub.2
            @Override // com.huawei.health.device.callback.HeartRateDeviceSelectedCallback
            public void onResponse(HealthDevice healthDevice) {
                if (healthDevice == null) {
                    LogUtil.a("HWhealthLinkage_", "HeartRateDeviceSelectedCallback device is null");
                    return;
                }
                LogUtil.a("HWhealthLinkage_", "HeartRateDeviceSelectedCallback " + healthDevice.getDeviceName());
                dub.this.e = healthDevice;
                if (dul.c().d() == -1) {
                    LogUtil.a("HWhealthLinkage_", "HeartRateDeviceSelectedCallback failed to open heartrate");
                    dul.c().d(1, dub.this.f11835a);
                }
            }
        };
    }

    public static final dub a() {
        return e.d;
    }

    public void e() {
        LogUtil.a("HWhealthLinkage_", "Enter removeHeartRateDevice");
        this.e = null;
    }

    public boolean b() {
        dfg.d().c(this.d);
        return true;
    }

    public void a(String str) {
        this.b.add(str);
    }

    public void c(DeviceInfo deviceInfo) {
        LogUtil.a("HWhealthLinkage_", "putDevicesInfoInPluginDevice enter");
        if (deviceInfo == null) {
            LogUtil.h("HWhealthLinkage_", "no heartRate devices");
            return;
        }
        if (cvt.c(deviceInfo.getProductType())) {
            LogUtil.a("HWhealthLinkage_", "aw70 device not support heart rate, do not put into PluginDevice");
            return;
        }
        LogUtil.c("HWhealthLinkage_", "deviceInfo is ", deviceInfo.toString(), ",deviceInfo deviceName is ", deviceInfo.getDeviceName(), ",deviceInfo uuid size ", Integer.valueOf(this.b.size()));
        String uuid = UUID.randomUUID().toString();
        LogUtil.c("HWhealthLinkage_", "randomUUidString is " + uuid);
        String deviceName = deviceInfo.getDeviceName();
        String securityUuid = deviceInfo.getSecurityUuid();
        this.b.add(securityUuid);
        if (deviceName != null && securityUuid != null) {
            cjx.e().b(uuid, null, new cfb(deviceInfo.getDeviceName(), deviceInfo.getSecurityUuid() + "#ANDROID21", deviceInfo.getProductType()), null);
            return;
        }
        LogUtil.h("HWhealthLinkage_", "deviceName is null or uniqueId is null");
    }

    public void c() {
        for (int i = 0; i < this.b.size(); i++) {
            if (d(this.b.get(i))) {
                LogUtil.a("HWhealthLinkage_", "succeed to delete device in device.db");
                dum.d().b().remove(i);
                dum.d().e(false);
                dum.d().b(false);
                dum.d().d(false);
            } else {
                LogUtil.h("HWhealthLinkage_", "failed to delete device in device.db");
            }
        }
        this.b.clear();
        dum.d().b().clear();
    }

    private boolean d(String str) {
        if (dum.d().b(str)) {
            return true;
        }
        return cjx.e().o(str + "#ANDROID21");
    }
}
