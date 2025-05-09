package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import com.huawei.health.device.api.DeviceInfoUtilsApi;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ggx {
    private static final int[] c = {8, 15, 13, 16, 20, 21, 18, 19, 34, 35, 44, 45};

    private static boolean e(int i, Context context) {
        for (int i2 : c) {
            if (i2 == i) {
                LogUtil.a("Suggestion_HWDeviceManager", "isSupportIndependentSport is true, productType is:", Integer.valueOf(i));
                return true;
            }
        }
        if (a(i, context)) {
            LogUtil.a("Suggestion_HWDeviceManager", "support workout");
            return true;
        }
        LogUtil.a("Suggestion_HWDeviceManager", "isSupportIndependentSport is false, productType is:", Integer.valueOf(i));
        return false;
    }

    private static boolean a(int i, Context context) {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "Suggestion_HWDeviceManager");
        if (deviceList == null || deviceList.isEmpty()) {
            LogUtil.h("Suggestion_HWDeviceManager", "isSupportWorkout getDeviceList is null or empty");
            return false;
        }
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (next.getProductType() == i) {
                Map<String, DeviceCapability> queryDeviceCapability = cun.c().queryDeviceCapability(1, next.getDeviceIdentify(), "Suggestion_HWDeviceManager");
                if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
                    LogUtil.h("Suggestion_HWDeviceManager", "isSupportWorkout deviceCapabilityHashMaps is null or empty");
                } else {
                    return queryDeviceCapability.get(next.getDeviceIdentify()).isSupportExerciseAdvice();
                }
            }
        }
        return false;
    }

    public static boolean e(Context context, boolean z) {
        if (z) {
            return a(context, z) || e();
        }
        LogUtil.b("Suggestion_HWDeviceManager", "isConnectedHeartRateDeviceAll()=false", "bluetoothIsEnabled = false");
        return false;
    }

    public static boolean e() {
        if (cei.b().getBondedProductsForDeviceOnly(HealthDevice.HealthDeviceKind.HDK_HEART_RATE).size() > 0) {
            LogUtil.a("Suggestion_HWDeviceManager", "isConnectedHeartRateDeviceOther() = true");
            return true;
        }
        LogUtil.h("Suggestion_HWDeviceManager", "isConnectedHeartRateDeviceOther() = false");
        return false;
    }

    public static boolean a(Context context, boolean z) {
        if (!z) {
            LogUtil.h("Suggestion_HWDeviceManager", "isConnectedHeartRateDeviceWear()=false, bluetoothIsEnabled=false");
            return false;
        }
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "Suggestion_HWDeviceManager");
        if (deviceInfo != null && deviceInfo.getDeviceConnectState() == 2 && (cux.e(deviceInfo.getProductType()) || cus.e().d(deviceInfo.getProductType()))) {
            LogUtil.a("Suggestion_HWDeviceManager", "isConnectedHeartRateDeviceWear()=true, isSupportHeartRate Wear=true, realtime_hearrate");
            return true;
        }
        LogUtil.h("Suggestion_HWDeviceManager", "isConnectedHeartRateDeviceWear()=false, failed");
        return false;
    }

    public static boolean c() {
        boolean isBindingHeartRateDeviceWear = ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).isBindingHeartRateDeviceWear(null);
        LogUtil.a("Suggestion_HWDeviceManager", "isBindedHeartRateDeviceWear()=", Boolean.valueOf(isBindingHeartRateDeviceWear));
        return isBindingHeartRateDeviceWear;
    }

    public static boolean d() {
        boolean isBindingHeartRateDeviceWear = ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).isBindingHeartRateDeviceWear(null);
        LogUtil.a("Suggestion_HWDeviceManager", "isBindedHeartRateDeviceAll() wear=", Boolean.valueOf(isBindingHeartRateDeviceWear));
        if (isBindingHeartRateDeviceWear) {
            return true;
        }
        boolean e = e();
        LogUtil.a("Suggestion_HWDeviceManager", "isBindedHeartRateDeviceAll() other=", Boolean.valueOf(e));
        return e;
    }

    public static void e(Context context) {
        if (context == null) {
            LogUtil.h("Suggestion_HWDeviceManager", "startSelectDeviceActivity context is null");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("SWITCH_PLUGINDEVICE");
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.ui.device.activity.adddevice.AllDeviceListActivity");
        intent.putExtra("progressbar", 0);
        intent.putExtra("isFromFitnessAdvice", true);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("Suggestion_HWDeviceManager", "startSelectDeviceActivity() exception: ", LogAnonymous.b((Throwable) e));
        }
    }

    public static void b(Context context) {
        if (context == null) {
            LogUtil.h("Suggestion_HWDeviceManager", "startMoreDeviceActivity context is null");
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity");
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("Suggestion_HWDeviceManager", "startMoreDeviceActivity() exception: ", LogAnonymous.b((Throwable) e));
        }
    }

    public static boolean a() {
        if (BluetoothAdapter.getDefaultAdapter() != null) {
            return BluetoothAdapter.getDefaultAdapter().isEnabled();
        }
        return false;
    }

    public static boolean c(Context context) {
        if (context == null) {
            LogUtil.h("Suggestion_HWDeviceManager", "isIndependentSportDevice context is null");
            return false;
        }
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "Suggestion_HWDeviceManager");
        if (deviceInfo != null && deviceInfo.getDeviceConnectState() == 2 && e(deviceInfo.getProductType(), context)) {
            LogUtil.a("Suggestion_HWDeviceManager", "isIndependentSportDevice()=true, isSupportHeartRate Wear=true, realtime_hearrate");
            return true;
        }
        LogUtil.h("Suggestion_HWDeviceManager", "isIndependentSportDevice()=false, failed");
        return false;
    }
}
