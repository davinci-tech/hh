package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HEXUtils;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes5.dex */
public class jlj {
    private static final Object d = new Object();
    private static jlj e;

    private jlj() {
    }

    public static jlj a() {
        jlj jljVar;
        synchronized (d) {
            if (e == null) {
                LogUtil.a("HwSleepBreatheManager", "getInstance");
                e = new jlj();
            }
            jljVar = e;
        }
        return jljVar;
    }

    public boolean e(DeviceInfo deviceInfo) {
        return cwi.c(deviceInfo, 178) && drl.c("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.sleep-apnea") && (Utils.o() ? true : cwi.c(deviceInfo, 107));
    }

    public boolean d() {
        dqo a2 = drl.a("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.sleep-apnea");
        if (a2 == null) {
            ReleaseLogUtil.d("HwSleepBreatheManager", "isEnableNMPA featureConfig is null");
            return false;
        }
        Map a3 = a2.a();
        Object obj = a3.get("medical");
        ReleaseLogUtil.e("HwSleepBreatheManager", "isEnableNMPA medicalValue = ", obj);
        if (!(obj instanceof Boolean) || !((Boolean) obj).booleanValue()) {
            return false;
        }
        Object obj2 = a3.get("medicalType");
        ReleaseLogUtil.e("HwSleepBreatheManager", "isEnableNMPA medicalTypeValue = ", obj2);
        return "NMPA".equals(obj2);
    }

    public boolean c() {
        dqo a2 = drl.a("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.emotion");
        if (a2 == null) {
            ReleaseLogUtil.d("HwSleepBreatheManager", "isEnableForEmotionNMPA featureConfig is null");
            return true;
        }
        Object obj = a2.a().get("medical");
        ReleaseLogUtil.e("HwSleepBreatheManager", "isEnableForEmotionNMPA medicalValue = ", obj);
        if (obj instanceof Boolean) {
            return obj.equals(true);
        }
        return false;
    }

    public void d(String str) {
        jqi.a().setSwitchSetting("sleep_breathe_key", str, new IBaseResponseCallback() { // from class: jlm
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                LogUtil.a("HwSleepBreatheManager", "setSleepBreatheSwitch errorCode: ", Integer.valueOf(i));
            }
        });
        a(str);
    }

    public void a(String str) {
        DeviceInfo a2 = jpt.a("HwSleepBreatheManager");
        if (a2 == null || TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("R_HwSleepBreatheManager", "sendSettingDeviceCommand, deviceInfo is null or switchStatus isEmpty.");
            return;
        }
        if (a2.getDeviceConnectState() != 2) {
            ReleaseLogUtil.d("R_HwSleepBreatheManager", "device is not connected.");
            return;
        }
        if (cwi.c(a2, 107)) {
            cvq c = c(str);
            ReleaseLogUtil.e("R_HwSleepBreatheManager", "constructSampleConfig: ", c);
            cuk.b().sendSampleConfigCommand(a2, c, "HwSleepBreatheManager");
        } else {
            boolean equals = "true".equals(str);
            ReleaseLogUtil.e("R_HwSleepBreatheManager", "setSleepBreatheSwitch sendSettingDeviceCommand: ", Boolean.valueOf(equals));
            jho.f(equals ? 2 : 0, a2);
        }
    }

    private cvq c(String str) {
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.health.apneajsmodule");
        cvqVar.setWearPkgName("hw.watch.health.osa");
        cvqVar.setCommandId(1);
        cvn cvnVar = new cvn();
        cvnVar.e(900300008L);
        boolean equals = "true".equals(str);
        String str2 = HEXUtils.e(1) + HEXUtils.e(1) + HEXUtils.e(equals ? 1 : 0);
        LogUtil.a("HwSleepBreatheManager", "constructSampleConfig status is: ", Integer.valueOf(equals ? 1 : 0));
        cvnVar.c(HEXUtils.c(str2));
        cvnVar.d(1);
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        return cvqVar;
    }
}
