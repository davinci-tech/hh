package defpackage;

import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;

/* loaded from: classes6.dex */
public class mpw extends EngineLogicBaseManager {
    private static volatile mpw b;
    private static final Object e = new Object();

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
    }

    private mpw() {
        super(BaseApplication.getContext());
    }

    public static mpw d() {
        if (b == null) {
            synchronized (e) {
                if (b == null) {
                    b = new mpw();
                }
            }
        }
        return b;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        mpy.d().receiveDataFromDevice(i, spnVar);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.HEALTH_ZONE_MODULE;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void sendComand(spn spnVar, SendCallback sendCallback) {
        super.sendComand(spnVar, sendCallback);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void pingComand(PingCallback pingCallback, String str) {
        super.pingComand(pingCallback, str);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "com.huawei.watch.family";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return "11CC31B7123B2AA2E6AEBB54BD4789104F0979F598BC63F3C0EE68698F442869";
    }
}
