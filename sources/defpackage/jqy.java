package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.ProcessUtil;
import health.compact.a.SharedPreferenceManager;

/* loaded from: classes5.dex */
public class jqy {
    public static String c(String str) {
        return SharedPreferenceManager.e("DeviceSettingConfigUtil", str, "");
    }

    public static void b(String str, String str2) {
        SharedPreferenceManager.c("DeviceSettingConfigUtil", str, str2);
    }

    public static void a(String str, String str2) {
        b(str, str2, null);
    }

    public static void b(final String str, final String str2, final IBaseResponseCallback iBaseResponseCallback) {
        jqi.a().setSwitchSetting(str, str2, new IBaseResponseCallback() { // from class: jqy.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("DeviceSettingConfigUtil", "saveSwitchSetting switchKey:", str, " errorCode:", Integer.valueOf(i), " switchSetting:", str);
                if (i == 0) {
                    jqy.b(str, str2);
                }
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(i, obj);
                }
            }
        });
    }

    public static void d(final DeviceInfo deviceInfo, final String str, String str2) {
        if (!ProcessUtil.d()) {
            LogUtil.h("DeviceSettingConfigUtil", "setDeviceSwitchSetting is not MainProcess");
        } else {
            b(str, str2, new IBaseResponseCallback() { // from class: jqy.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("DeviceSettingConfigUtil", "setDeviceSwitchSetting switchKey:", str, " errorCode:", Integer.valueOf(i), " switchSetting:", str);
                    if (i == 0) {
                        jfq.c().d("notify_device_setting_changer", deviceInfo, 0, str);
                    }
                }
            });
        }
    }
}
