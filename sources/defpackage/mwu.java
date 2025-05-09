package defpackage;

import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsport.helper.DeviceInterface;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public class mwu {
    private final ConcurrentHashMap<Long, IBaseResponseCallback> c;
    private DeviceInterface e;

    static class b {
        public static final mwu d = new mwu();
    }

    private mwu() {
        this.c = new ConcurrentHashMap<>();
    }

    public static mwu d() {
        return b.d;
    }

    public void e(DeviceInterface deviceInterface) {
        LogUtil.a("Track_HWhealthLinkage_DeviceStateHelper", "registerCallback");
        this.e = deviceInterface;
        c(deviceInterface);
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("Track_HWhealthLinkage_DeviceStateHelper", "getSportState ", this.e);
        if (this.e != null) {
            ReleaseLogUtil.e("Track_HWhealthLinkage_DeviceStateHelper", "getSportState ", "into");
            this.e.getOperator(iBaseResponseCallback);
        } else {
            this.c.put(Long.valueOf(System.currentTimeMillis()), iBaseResponseCallback);
        }
    }

    public void b(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Track_HWhealthLinkage_DeviceStateHelper", "getDeviceStepRateAlgorithmEnterprise ", this.e);
        DeviceInterface deviceInterface = this.e;
        if (deviceInterface != null) {
            deviceInterface.getDeviceStepRateAlgorithmEnterprise(iBaseResponseCallback);
        }
    }

    public void a(String str, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Track_HWhealthLinkage_DeviceStateHelper", "setBoltWearStatusListener ");
        DeviceInterface deviceInterface = this.e;
        if (deviceInterface != null) {
            deviceInterface.setBoltWearStatusListener(str, iBaseResponseCallback);
        }
    }

    public void b(String str) {
        LogUtil.a("Track_HWhealthLinkage_DeviceStateHelper", "unreg AbnormalStatus");
        DeviceInterface deviceInterface = this.e;
        if (deviceInterface != null) {
            deviceInterface.unregBoltWearStatusListener(str);
        }
    }

    private void c(DeviceInterface deviceInterface) {
        if (this.c.isEmpty()) {
            ReleaseLogUtil.d("Track_HWhealthLinkage_DeviceStateHelper", "mSavedActionMap is empty");
            return;
        }
        for (Map.Entry<Long, IBaseResponseCallback> entry : this.c.entrySet()) {
            if (System.currentTimeMillis() - entry.getKey().longValue() < 1000) {
                ReleaseLogUtil.d("Track_HWhealthLinkage_DeviceStateHelper", "mSavedActionMap getOperator");
                deviceInterface.getOperator(entry.getValue());
            }
        }
        this.c.clear();
    }
}
