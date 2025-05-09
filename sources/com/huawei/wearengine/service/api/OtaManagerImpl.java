package com.huawei.wearengine.service.api;

import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.wearengine.OtaManager;
import com.huawei.wearengine.common.WearEngineBiOperate;
import com.huawei.wearengine.connect.ServiceConnectCallback;
import com.huawei.wearengine.core.common.ClientBinderDied;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.ota.CheckBinderCallback;
import com.huawei.wearengine.ota.DeviceListBinderCallback;
import com.huawei.wearengine.ota.UpgradeBinderCallBack;
import com.huawei.wearengine.ota.UpgradeStatusBinderCallBack;
import defpackage.toh;
import defpackage.tom;
import defpackage.tor;
import defpackage.tos;
import defpackage.tot;
import defpackage.toz;
import defpackage.tri;
import defpackage.trj;

/* loaded from: classes9.dex */
public class OtaManagerImpl extends OtaManager.Stub implements ClientBinderDied {
    private final tor b;
    private final toz c;
    private final toh d;

    public OtaManagerImpl(toh tohVar, tor torVar) {
        this.c = new toz(torVar);
        this.d = tohVar;
        this.b = torVar;
    }

    @Override // com.huawei.wearengine.OtaManager
    public void getConnectedDevices(DeviceListBinderCallback deviceListBinderCallback) {
        tos.a("OtaManagerImpl", "Start getConnectedDevices");
        tom.e(deviceListBinderCallback, "deviceListBinderCallback must not be null");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c = c();
        try {
            c(tot.d(), c, "getConnectedDevices");
            this.c.c(deviceListBinderCallback);
            wearEngineBiOperate.biApiCalling(a2, c, "getConnectedDevices", String.valueOf(0));
        } catch (IllegalStateException e) {
            wearEngineBiOperate.biApiCalling(a2, c, "getConnectedDevices", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    @Override // com.huawei.wearengine.OtaManager
    public void getDeviceNewVersion(Device device, CheckBinderCallback checkBinderCallback) {
        tos.a("OtaManagerImpl", "Start getDeviceNewVersion");
        tom.e(device, "device must not be null!");
        tom.e(checkBinderCallback, "checkBinderCallback must not be null");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c = c();
        try {
            c(tot.d(), c, "getDeviceNewVersion");
            this.c.e(device, checkBinderCallback);
            wearEngineBiOperate.biApiCalling(a2, c, "getDeviceNewVersion", String.valueOf(0));
        } catch (IllegalStateException e) {
            wearEngineBiOperate.biApiCalling(a2, c, "getDeviceNewVersion", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    @Override // com.huawei.wearengine.OtaManager
    public void doUpgrade(Device device, String str, UpgradeBinderCallBack upgradeBinderCallBack) {
        tos.a("OtaManagerImpl", "Start doUpgrade");
        tom.e(device, "device must not be null!");
        if (TextUtils.isEmpty(str)) {
            tos.e("OtaManagerImpl", "doUpgrade command is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        tom.e(upgradeBinderCallBack, "upgradeBinderCallBack must not be null");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c = c();
        try {
            c(tot.d(), c, "doUpgrade");
            this.c.e(device, str, upgradeBinderCallBack);
            wearEngineBiOperate.biApiCalling(a2, c, "doUpgrade", String.valueOf(0));
        } catch (IllegalStateException e) {
            wearEngineBiOperate.biApiCalling(a2, c, "doUpgrade", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    @Override // com.huawei.wearengine.OtaManager
    public void getUpgradeStatus(Device device, UpgradeStatusBinderCallBack upgradeStatusBinderCallBack) {
        tos.a("OtaManagerImpl", "Start getUpgradeStatus");
        tom.e(device, "device must not be null!");
        tom.e(upgradeStatusBinderCallBack, "upgradeStatusBinderCallBack must not be null");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c = c();
        try {
            c(tot.d(), c, "getUpgradeStatus");
            this.c.a(device, upgradeStatusBinderCallBack);
            wearEngineBiOperate.biApiCalling(a2, c, "getUpgradeStatus", String.valueOf(0));
        } catch (IllegalStateException e) {
            wearEngineBiOperate.biApiCalling(a2, c, "getUpgradeStatus", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    @Override // com.huawei.wearengine.OtaManager
    public void registerUpgradeListener(Device device, UpgradeStatusBinderCallBack upgradeStatusBinderCallBack) throws RemoteException {
        tos.a("OtaManagerImpl", "Start registerUpgradeListener");
        tom.e(device, "device must not be null!");
        tom.e(upgradeStatusBinderCallBack, "upgradeStatusBinderCallBack must not be null");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c = c();
        try {
            c(tot.d(), c, "registerUpgradeListener");
            upgradeStatusBinderCallBack.asBinder().linkToDeath(feS_(device, upgradeStatusBinderCallBack), 0);
            this.c.c(device, upgradeStatusBinderCallBack);
            wearEngineBiOperate.biApiCalling(a2, c, "registerUpgradeListener", String.valueOf(0));
        } catch (IllegalStateException e) {
            wearEngineBiOperate.biApiCalling(a2, c, "registerUpgradeListener", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    private IBinder.DeathRecipient feS_(final Device device, final UpgradeStatusBinderCallBack upgradeStatusBinderCallBack) {
        return new IBinder.DeathRecipient() { // from class: com.huawei.wearengine.service.api.OtaManagerImpl.5
            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                tos.b("OtaManagerImpl", "DeathRecipient device uuid: " + device.getUuid());
                tos.a("OtaManagerImpl", "DeathRecipient unRegisterUpgradeListener start");
                try {
                    OtaManagerImpl.this.c.a(device);
                } catch (Exception unused) {
                    tos.e("OtaManagerImpl", "DeathRecipient unRegisterUpgradeListener exception");
                }
                upgradeStatusBinderCallBack.asBinder().unlinkToDeath(this, 0);
            }
        };
    }

    @Override // com.huawei.wearengine.OtaManager
    public void unRegisterUpgradeListener(Device device) {
        tos.a("OtaManagerImpl", "Start unRegisterUpgradeListener");
        tom.e(device, "device must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c = c();
        try {
            c(tot.d(), c, "unRegisterUpgradeListener");
            this.c.a(device);
            wearEngineBiOperate.biApiCalling(a2, c, "unRegisterUpgradeListener", String.valueOf(0));
        } catch (IllegalStateException e) {
            wearEngineBiOperate.biApiCalling(a2, c, "unRegisterUpgradeListener", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    @Override // com.huawei.wearengine.OtaManager
    public int registerOtaServiceConnectCallback(ServiceConnectCallback serviceConnectCallback) {
        tos.a("OtaManagerImpl", "Start registerOtaServiceConnectCallback");
        String c = c();
        WearEngineBiOperate e = e();
        Context a2 = tot.a();
        try {
            c(tot.d(), c, "registerOtaServiceConnectCallback");
            int c2 = this.c.c(this.b.e(tot.d(), tot.e()), serviceConnectCallback);
            e.biApiCalling(a2, c, "registerOtaServiceConnectCallback", String.valueOf(c2));
            return c2;
        } catch (IllegalStateException e2) {
            int b = trj.b(e2);
            e.biApiCalling(a2, c, "registerOtaServiceConnectCallback", String.valueOf(b));
            return b;
        }
    }

    @Override // com.huawei.wearengine.OtaManager
    public int unregisterOtaServiceConnectCallback(ServiceConnectCallback serviceConnectCallback) {
        tos.a("OtaManagerImpl", "Start unregisterOtaServiceConnectCallback");
        String c = c();
        WearEngineBiOperate e = e();
        Context a2 = tot.a();
        try {
            c(tot.d(), c, "unregisterOtaServiceConnectCallback");
            int a3 = this.c.a(this.b.e(tot.d(), tot.e()), serviceConnectCallback);
            e.biApiCalling(a2, c, "unregisterOtaServiceConnectCallback", String.valueOf(a3));
            return a3;
        } catch (IllegalStateException e2) {
            int b = trj.b(e2);
            e.biApiCalling(a2, c, "unregisterOtaServiceConnectCallback", String.valueOf(b));
            return b;
        }
    }

    private void c(int i, String str, String str2) {
        if (this.d.b(i, str)) {
            return;
        }
        this.d.d(i, str, str2);
    }

    private String c() {
        return tri.c(tot.d(), tot.a(), this.b.getApplicationIdByPid(Integer.valueOf(tot.e())));
    }

    private WearEngineBiOperate e() {
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        return wearEngineBiOperate;
    }

    @Override // com.huawei.wearengine.core.common.ClientBinderDied
    public void handleClientBinderDied(String str) {
        tos.a("OtaManagerImpl", "handleClientBinderDied clientPkgName is: " + str);
        if (!d()) {
            tos.e("OtaManagerImpl", "handleClientBinderDied checkPermission failed");
        } else if ("com.huawei.hms.dupdateengine".equals(str)) {
            try {
                this.c.e();
            } catch (IllegalStateException unused) {
                tos.e("OtaManagerImpl", "handleClientBinderDied disconnectOtaService exception");
            }
        }
    }

    private boolean d() {
        return Binder.getCallingUid() == getCallingUid();
    }
}
