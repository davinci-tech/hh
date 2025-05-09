package defpackage;

import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class iws extends EngineLogicBaseManager {
    private static volatile iws c;
    private static final Object e = new Object();

    private iws() {
        super(BaseApplication.getContext());
    }

    public static iws c() {
        LogUtil.a("HwMedalMessageManager", "HwMedalMessageManager getInstance.");
        if (c == null) {
            synchronized (e) {
                if (c == null) {
                    c = new iws();
                }
            }
        }
        return c;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        LogUtil.h("HwMedalMessageManager", "onReceiveDeviceCommand code：", Integer.valueOf(i));
        if (i == 207 || i == 206) {
            return;
        }
        mxo.d().receiveFromDevice(i, spnVar);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.MEDAL_MODULE;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        LogUtil.a("HwMedalMessageManager", "getWearPkgName: ", "com.huawei.sport.dailytrack");
        return "com.huawei.sport.dailytrack";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        LogUtil.a("HwMedalMessageManager", "getWearFingerprint SportWatchType: ", Integer.valueOf(e()), " WatchType: ", Integer.valueOf(b()));
        if (e() == 1 || b() == 1) {
            if (d(155)) {
                LogUtil.a("HwMedalMessageManager", "getWearFingerprint=", "11CC31B7123B2AA2E6AEBB54BD4789104F0979F598BC63F3C0EE68698F442869");
                return "11CC31B7123B2AA2E6AEBB54BD4789104F0979F598BC63F3C0EE68698F442869";
            }
            LogUtil.a("HwMedalMessageManager", "getWearFingerprint=", OfflineMapWearEngineManager.WEAR_FINGERPRINT);
            return OfflineMapWearEngineManager.WEAR_FINGERPRINT;
        }
        if (d(155)) {
            LogUtil.a("HwMedalMessageManager", "getWearFingerprint: ", OfflineMapWearEngineManager.WEAR_FINGERPRINT);
            return OfflineMapWearEngineManager.WEAR_FINGERPRINT;
        }
        LogUtil.a("HwMedalMessageManager", "getWearFingerprint: ", "11CC31B7123B2AA2E6AEBB54BD4789104F0979F598BC63F3C0EE68698F442869");
        return "11CC31B7123B2AA2E6AEBB54BD4789104F0979F598BC63F3C0EE68698F442869";
    }

    public int b() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "HwMedalMessageManager");
        if (deviceInfo != null) {
            return e(deviceInfo) ? 1 : 0;
        }
        ReleaseLogUtil.c("HwMedalMessageManager", "getWatchType failed");
        return -1;
    }

    private boolean e(DeviceInfo deviceInfo) {
        if (cwi.c(deviceInfo, 159)) {
            return true;
        }
        int productType = deviceInfo.getProductType();
        LogUtil.a("HwMedalMessageManager", "getProductType: ", Integer.valueOf(productType));
        return productType == 57 || productType == 78;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
        if (connectState == ConnectState.CONNECTED) {
            mxo.d().e(0);
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void sendComand(spn spnVar, SendCallback sendCallback) {
        super.sendComand(spnVar, sendCallback);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void pingComand(PingCallback pingCallback, String str) {
        super.pingComand(pingCallback, str);
    }

    public static boolean d(int i) {
        List<DeviceInfo> b = jfq.c().b(HwGetDevicesMode.CONNECTED_DEVICES, (HwGetDevicesParameter) null, "HwMedalMessageManager");
        if (!koq.b(b)) {
            LogUtil.a("HwMedalMessageManager", "isSupportCapability deviceInfos: ", b.toString());
            Iterator<DeviceInfo> it = b.iterator();
            while (it.hasNext()) {
                if (cwi.c(it.next(), i)) {
                    LogUtil.a("HwMedalMessageManager", "isSupportCapability isSupportSportDevice", Integer.valueOf(i), true);
                    return true;
                }
            }
            return false;
        }
        LogUtil.h("HwMedalMessageManager", "isSupportCapability connected device list is isEmpty.");
        return false;
    }

    public static int e() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "HwMedalMessageManager");
        if (deviceInfo == null) {
            LogUtil.h("HwMedalMessageManager", "isSportWatch deviceInfo is null!");
            return -1;
        }
        return deviceInfo.getSportVersion();
    }
}
