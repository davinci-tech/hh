package com.huawei.wearengine.service.api;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import com.huawei.wearengine.DeviceManager;
import com.huawei.wearengine.auth.Permission;
import com.huawei.wearengine.common.WearEngineBiOperate;
import com.huawei.wearengine.core.common.ClientBinderDied;
import com.huawei.wearengine.core.device.DeviceManagerCaller;
import com.huawei.wearengine.core.device.PowerModeChangeManager;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.utils.DeviceProcessor;
import defpackage.toh;
import defpackage.tom;
import defpackage.tor;
import defpackage.tos;
import defpackage.tot;
import defpackage.toz;
import defpackage.tqo;
import defpackage.trd;
import defpackage.tri;
import defpackage.trj;
import java.util.List;

/* loaded from: classes9.dex */
public class DeviceManagerImpl extends DeviceManager.Stub implements ClientBinderDied {

    /* renamed from: a, reason: collision with root package name */
    private tor f11241a;
    private toz b;
    private toh c;

    public DeviceManagerImpl(toh tohVar, tor torVar) {
        this.b = new toz(torVar);
        this.c = tohVar;
        this.f11241a = torVar;
    }

    @Override // com.huawei.wearengine.DeviceManager
    public List<Device> getBondedDevices() throws RemoteException {
        tos.a("DeviceManagerImpl", "getBondedDevices enter");
        List<Device> bondedDeviceEx = getBondedDeviceEx();
        for (Device device : bondedDeviceEx) {
            device.setReservedness(device.getReservedness());
        }
        return bondedDeviceEx;
    }

    @Override // com.huawei.wearengine.DeviceManager
    public List<Device> getAllBondedDevices() throws RemoteException {
        PowerModeChangeManager.a().b(true);
        return a("getAllBondedDevices", new DeviceManagerCaller() { // from class: com.huawei.wearengine.service.api.DeviceManagerImpl.4
            @Override // com.huawei.wearengine.core.device.DeviceManagerCaller
            public List<Device> getDeviceList() throws RemoteException {
                return DeviceManagerImpl.this.b.c();
            }
        });
    }

    @Override // com.huawei.wearengine.DeviceManager
    public List<Device> getCommonDevice() throws RemoteException {
        PowerModeChangeManager.a().b(true);
        return a("getCommonDevice", new DeviceManagerCaller() { // from class: com.huawei.wearengine.service.api.DeviceManagerImpl.3
            @Override // com.huawei.wearengine.core.device.DeviceManagerCaller
            public List<Device> getDeviceList() throws RemoteException {
                return DeviceManagerImpl.this.b.b();
            }
        });
    }

    @Override // com.huawei.wearengine.DeviceManager
    public boolean hasAvailableDevices() throws RemoteException {
        tos.a("DeviceManagerImpl", "hasAvailableDevices enter");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c = tri.c(Binder.getCallingUid(), a2, this.f11241a.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            PowerModeChangeManager.a().b(true);
            if (trd.e(tri.a(a2))) {
                wearEngineBiOperate.biApiCalling(a2, c, "hasAvailableDevices", String.valueOf(0));
                return this.b.i();
            }
            tos.e("DeviceManagerImpl", "hasAvailableDevices not login in Huawei Health!");
            throw new IllegalStateException(String.valueOf(3));
        } catch (IllegalStateException e) {
            wearEngineBiOperate.biApiCalling(a2, c, "hasAvailableDevices", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    @Override // com.huawei.wearengine.DeviceManager
    public String getHiLinkDeviceId(Device device) throws RemoteException {
        tos.a("DeviceManagerImpl", "getHiLinkDeviceId enter");
        tom.e(device, "device must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c = tri.c(Binder.getCallingUid(), a2, this.f11241a.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            PowerModeChangeManager.a().b(true);
            this.c.a(c, "getHiLinkDeviceId", tqo.f17349a, Permission.DEVICE_MANAGER);
            Device processInputDevice = DeviceProcessor.processInputDevice(c, device);
            wearEngineBiOperate.biApiCalling(a2, c, "getHiLinkDeviceId", String.valueOf(0));
            return this.b.d(processInputDevice);
        } catch (IllegalStateException e) {
            wearEngineBiOperate.biApiCalling(a2, c, "getHiLinkDeviceId", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    @Override // com.huawei.wearengine.DeviceManager
    public List<Device> getBondedDeviceEx() throws RemoteException {
        PowerModeChangeManager.a().b(true);
        return a("getBondedDevices", new DeviceManagerCaller() { // from class: com.huawei.wearengine.service.api.DeviceManagerImpl.5
            @Override // com.huawei.wearengine.core.device.DeviceManagerCaller
            public List<Device> getDeviceList() throws RemoteException {
                return DeviceManagerImpl.this.b.d();
            }
        });
    }

    @Override // com.huawei.wearengine.core.common.ClientBinderDied
    public void handleClientBinderDied(String str) {
        tos.a("DeviceManagerImpl", "handleClientBinderDied clientPkgName is: " + str);
    }

    private List<Device> a(String str, DeviceManagerCaller deviceManagerCaller) throws RemoteException {
        tos.a("DeviceManagerImpl", str + " entry");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        tos.a("DeviceManagerImpl", "getDeviceListApiName :" + str + " getCallingUid :" + Binder.getCallingUid() + " getCallingPid :" + Binder.getCallingPid());
        String c = tri.c(Binder.getCallingUid(), a2, this.f11241a.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            if (!this.c.b(Binder.getCallingUid(), c)) {
                this.c.a(c, str, tqo.f17349a, Permission.DEVICE_MANAGER);
            }
            if (deviceManagerCaller == null) {
                tos.e("DeviceManagerImpl", str + " caller is null");
                throw new IllegalStateException(String.valueOf(12));
            }
            List<Device> processOutputDevice = DeviceProcessor.processOutputDevice(c, deviceManagerCaller.getDeviceList());
            wearEngineBiOperate.biApiCalling(a2, c, str, String.valueOf(0));
            return processOutputDevice;
        } catch (IllegalStateException e) {
            wearEngineBiOperate.biApiCalling(a2, c, str, String.valueOf(trj.b(e)));
            throw e;
        }
    }

    @Override // com.huawei.wearengine.DeviceManager
    public int queryDeviceCapability(Device device, int i) throws RemoteException {
        tos.a("DeviceManagerImpl", "queryDeviceCapability enter");
        tom.e(device, "device must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c = tri.c(Binder.getCallingUid(), a2, this.f11241a.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            PowerModeChangeManager.a().b(true);
            if (!this.c.b(Binder.getCallingUid(), c)) {
                this.c.a(c, "queryDeviceCapability", tqo.f17349a, Permission.DEVICE_MANAGER);
            }
            Device processInputDevice = DeviceProcessor.processInputDevice(c, device);
            wearEngineBiOperate.biApiCalling(a2, c, "queryDeviceCapability", String.valueOf(0));
            return this.b.c(processInputDevice, i);
        } catch (IllegalStateException e) {
            wearEngineBiOperate.biApiCalling(a2, c, "queryDeviceCapability", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    @Override // com.huawei.wearengine.DeviceManager
    public String getDeviceSn(Device device) throws RemoteException {
        tos.a("DeviceManagerImpl", "getDeviceSn enter");
        tom.e(device, "device must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c = tri.c(Binder.getCallingUid(), a2, this.f11241a.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            PowerModeChangeManager.a().b(true);
            this.c.c(c, "getDeviceSn", tqo.b, Permission.DEVICE_SN);
            Device processInputDevice = DeviceProcessor.processInputDevice(c, device);
            wearEngineBiOperate.biApiCalling(a2, c, "getDeviceSn", String.valueOf(0));
            return this.b.b(processInputDevice);
        } catch (IllegalStateException e) {
            wearEngineBiOperate.biApiCalling(a2, c, "getDeviceSn", String.valueOf(trj.b(e)));
            throw e;
        }
    }
}
