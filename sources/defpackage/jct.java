package defpackage;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.BadParcelableException;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jct {
    public static boolean b() {
        LogUtil.a("DeviceCapabilityUtils", "isSupportFloor enter");
        Object systemService = BaseApplication.getContext().getApplicationContext().getSystemService("sensor");
        try {
            if (systemService instanceof SensorManager) {
                if (((SensorManager) systemService).getDefaultSensor(6) == null) {
                    LogUtil.h("DeviceCapabilityUtils", "isSupportFloor is false");
                    return false;
                }
                LogUtil.a("DeviceCapabilityUtils", "isSupportFloor is true");
                return true;
            }
        } catch (BadParcelableException unused) {
            LogUtil.b("DeviceCapabilityUtils", "isSupportFloor exceptionTwo");
        } catch (ClassCastException unused2) {
            LogUtil.b("DeviceCapabilityUtils", "isSupportFloor exceptionOne");
        }
        LogUtil.h("DeviceCapabilityUtils", "object can not cast to SensorManager");
        return false;
    }

    public static boolean d(Context context) {
        SensorManager sensorManager;
        if (context == null) {
            LogUtil.h("DeviceCapabilityUtils", "isSupportStandStepCounter context is null");
            return false;
        }
        Object systemService = context.getSystemService("sensor");
        try {
        } catch (BadParcelableException unused) {
            LogUtil.b("DeviceCapabilityUtils", "isSupportStandStepCounter exceptionTwo");
        } catch (ClassCastException unused2) {
            LogUtil.b("DeviceCapabilityUtils", "isSupportStandStepCounter exceptionOne");
        }
        if (systemService instanceof SensorManager) {
            sensorManager = (SensorManager) systemService;
            if (sensorManager == null && sensorManager.getDefaultSensor(19) != null) {
                LogUtil.a("DeviceCapabilityUtils", "supportStandStepCounter true");
                return true;
            }
            LogUtil.a("supportStandStepCounter", "supportStandStepCounter false");
            return false;
        }
        sensorManager = null;
        if (sensorManager == null) {
        }
        LogUtil.a("supportStandStepCounter", "supportStandStepCounter false");
        return false;
    }
}
