package com.huawei.basichealth.bloodsugar;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import defpackage.cun;
import defpackage.cvt;
import defpackage.koq;
import defpackage.spn;
import java.util.List;

/* loaded from: classes3.dex */
public class BloodSugarEngineLogicManager extends EngineLogicBaseManager {
    private BloodSugarEngineLogicListener d;

    public interface BloodSugarEngineLogicListener {
        void onDeviceConnected(String str);

        void onDeviceDisconnected();

        void onReceiveDeviceCommand(byte[] bArr);
    }

    public BloodSugarEngineLogicManager(Context context) {
        super(context);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        LogUtil.a("BloodSugarEngineLogicManager", "backCode = ", Integer.valueOf(i));
        if (spnVar == null) {
            LogUtil.h("BloodSugarEngineLogicManager", "onReceiveDeviceCommand message is null");
        } else {
            this.d.onReceiveDeviceCommand(spnVar.b());
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.BLOOD_SUGAR_MODULE;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
        LogUtil.a("BloodSugarEngineLogicManager", "connectState ", connectState);
        if (connectState == ConnectState.CONNECTED) {
            String c2 = c(deviceInfo);
            this.d.onDeviceConnected(c2);
            LogUtil.a("BloodSugarEngineLogicManager", "onDeviceConnectionChange = CONNECTED, deviceUuid = ", c2);
        } else {
            this.d.onDeviceDisconnected();
            LogUtil.h("BloodSugarEngineLogicManager", "onDeviceConnectionChange=", connectState);
        }
    }

    public String c(DeviceInfo deviceInfo) {
        if (deviceInfo != null && !TextUtils.isEmpty(deviceInfo.getSecurityUuid())) {
            String str = deviceInfo.getSecurityUuid() + "#ANDROID21";
            LogUtil.a("BloodSugarEngineLogicManager", "getDeviceUuid = ", str);
            return str;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "BloodSugarEngineLogicManager");
        if (!koq.b(deviceList)) {
            DeviceInfo deviceInfo2 = deviceList.get(0);
            if (!cvt.c(deviceInfo2.getProductType())) {
                String str2 = deviceInfo2.getSecurityUuid() + "#ANDROID21";
                LogUtil.a("BloodSugarEngineLogicManager", "getDeviceUuid from list = ", str2);
                return str2;
            }
        }
        return null;
    }

    public void d(PingCallback pingCallback) {
        super.pingComand(pingCallback, "com.example.mqhomswatch");
    }

    public void e(byte[] bArr) {
        sendComand(new spn.b().c(bArr).e(), new c());
    }

    static class c implements SendCallback {
        @Override // com.huawei.health.deviceconnect.callback.SendCallback
        public void onSendProgress(long j) {
        }

        private c() {
        }

        @Override // com.huawei.health.deviceconnect.callback.SendCallback
        public void onSendResult(int i) {
            LogUtil.a("BloodSugarEngineLogicManager", "sendCommandResult=", Integer.valueOf(i));
        }

        @Override // com.huawei.health.deviceconnect.callback.SendCallback
        public void onFileTransferReport(String str) {
            LogUtil.a("BloodSugarEngineLogicManager", "sendCommand onFileTransferReport transferWay: ", str);
        }
    }

    public void e(BloodSugarEngineLogicListener bloodSugarEngineLogicListener) {
        this.d = bloodSugarEngineLogicListener;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "com.example.mqhomswatch";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return "com.example.mqhomswatch_BEX3m1geBbqx/kae1N5iJduBqpzyc80Q7wP/9FbJKsvwPpnUUJjgoH6EuUxzVYhKnKatOTUsJTdSdpEb+Ms5+dM=";
    }
}
