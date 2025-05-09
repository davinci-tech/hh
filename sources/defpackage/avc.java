package defpackage;

import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;

/* loaded from: classes3.dex */
public class avc extends EngineLogicBaseManager {

    /* renamed from: a, reason: collision with root package name */
    private static long f247a;
    private static volatile avc d;
    private static final Object e = new Object();

    private avc() {
        super(BaseApplication.getContext());
        registerNotificationAction();
        LogUtil.a("HealthLife_DeviceManager", "DeviceManager");
    }

    public static avc e() {
        if (d == null) {
            synchronized (e) {
                if (d == null) {
                    d = new avc();
                }
            }
        }
        return d;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        if (i == 207 || i == 206) {
            return;
        }
        avm.a().d(i, spnVar);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.HEALTH_MODEL_MODULE;
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
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
        LogUtil.a("HealthLife_DeviceManager", "onDeviceConnectionChange device ", deviceInfo, " state ", connectState);
        if (deviceInfo == null) {
            return;
        }
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        LogUtil.a("HealthLife_DeviceManager", "onDeviceConnectionChange connectState ", Integer.valueOf(deviceConnectState));
        if (deviceConnectState != 2) {
            if (deviceConnectState == 3) {
                bao.b("connect_device_identify", deviceInfo.getDeviceIdentify());
                return;
            }
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - f247a < PreConnectManager.CONNECT_INTERNAL) {
            return;
        }
        f247a = currentTimeMillis;
        avm.a().b(0);
        d(deviceInfo);
        avm.a().h();
        avm.a().g();
        avm.a().i();
        avm.a().k();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(com.huawei.health.devicemgr.business.entity.DeviceInfo r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L17
            java.lang.String r3 = r3.getDeviceIdentify()
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L17
            java.lang.String r0 = "connect_device_identify"
            java.lang.String r0 = defpackage.bao.g(r0)
            boolean r3 = r3.equals(r0)
            goto L18
        L17:
            r3 = 0
        L18:
            java.lang.String r0 = "removeDeviceMsgVersion isSameDevice "
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r3)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
            java.lang.String r1 = "HealthLife_DeviceManager"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            if (r3 != 0) goto L35
            java.lang.String r3 = "health_model_device_link_version"
            java.lang.String r0 = ""
            defpackage.bao.c(r3, r0)
            java.lang.String r3 = "health_model_device_link_package_size"
            defpackage.bao.c(r3, r0)
        L35:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.avc.d(com.huawei.health.devicemgr.business.entity.DeviceInfo):void");
    }

    public void c(String str) {
        LogUtil.a("HealthLife_DeviceManager", "unregister source ", str);
        avm.a().b(false);
        avm.a().b(0);
        onDestroy();
        d = null;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "com.huawei.health.healthyliving";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return "com.huawei.health.healthyliving_BBLV5gbaG5WYtIAUCOwrnqycG0TTHvDSFCf1skDcwpWT+tu2wJ2tOwPYFzjV+ZsiVJrV1nO/KsbJ687t7b9a6Jg=";
    }
}
