package defpackage;

import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;

/* loaded from: classes4.dex */
public class iww extends EngineLogicBaseManager {
    private static volatile iww c;
    private static final Object e = new Object();

    private iww() {
        super(BaseApplication.getContext());
    }

    public static iww b() {
        LogUtil.a("HwSyncDeviceMgr", "HwSyncDeviceMgr getInstance.");
        if (c == null) {
            synchronized (e) {
                if (c == null) {
                    c = new iww();
                }
            }
        }
        return c;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        LogUtil.h("HwSyncDeviceMgr", "onReceiveDeviceCommand code：", Integer.valueOf(i));
        if (i == 207 || i == 206) {
            return;
        }
        mxo.d().receiveFromDevice(i, spnVar);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        if (iws.d(155)) {
            return WearEngineModule.MEDAL_WORKOUT_MODULE;
        }
        return WearEngineModule.MEDAL_MODULE;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        LogUtil.a("HwSyncDeviceMgr", "getWearPkgName: ", "com.huawei.sport.workout");
        return "com.huawei.sport.workout";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        LogUtil.a("HwSyncDeviceMgr", "getWearFingerprint: ", "com.huawei.sport.workout_BO2t/bvVsvJi32wxoQnpxZwLsOp31g7NVRM5bgxjEyD+7UzFWoNcyB8VkMgg0fABbknE4tU+Y34vfAzpSD8TJ5g=");
        return "com.huawei.sport.workout_BO2t/bvVsvJi32wxoQnpxZwLsOp31g7NVRM5bgxjEyD+7UzFWoNcyB8VkMgg0fABbknE4tU+Y34vfAzpSD8TJ5g=";
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
}
