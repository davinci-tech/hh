package com.huawei.wearengine.service.api;

import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.wearengine.MonitorManager;
import com.huawei.wearengine.auth.Permission;
import com.huawei.wearengine.common.WearEngineBiOperate;
import com.huawei.wearengine.core.common.ClientBinderDied;
import com.huawei.wearengine.core.device.PowerModeChangeManager;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.monitor.MonitorData;
import com.huawei.wearengine.monitor.MonitorDataCallback;
import com.huawei.wearengine.monitor.MonitorItem;
import com.huawei.wearengine.utils.DeviceProcessor;
import defpackage.toh;
import defpackage.tol;
import defpackage.tom;
import defpackage.top;
import defpackage.tor;
import defpackage.tos;
import defpackage.tot;
import defpackage.toz;
import defpackage.tpk;
import defpackage.tqo;
import defpackage.tri;
import defpackage.trj;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes9.dex */
public class MonitorManagerImpl extends MonitorManager.Stub implements ClientBinderDied, PowerModeChangeManager.HandlePowerModeChange {
    private static ArrayList<String> c;

    /* renamed from: a, reason: collision with root package name */
    private toz f11243a;
    private IBinder.DeathRecipient b = new IBinder.DeathRecipient() { // from class: com.huawei.wearengine.service.api.MonitorManagerImpl.2
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            MonitorManagerImpl.this.f11243a.g();
        }
    };
    private tor d;
    private toh e;

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        c = arrayList;
        arrayList.add(MonitorItem.MONITOR_ITEM_LOW_POWER.getName());
        c.add(MonitorItem.MONITOR_ITEM_CONNECTION.getName());
        c.add(MonitorItem.MONITOR_POWER_STATUS.getName());
        c.add(MonitorItem.MONITOR_CHARGE_STATUS.getName());
        c.add(MonitorItem.MONITOR_ITEM_USER_AVAILABLE_KBYTES.getName());
        c.add(MonitorItem.MONITOR_ITEM_POWER_MODE.getName());
    }

    public MonitorManagerImpl(toh tohVar, tor torVar) {
        this.f11243a = new toz(torVar);
        this.e = tohVar;
        this.d = torVar;
    }

    @Override // com.huawei.wearengine.MonitorManager
    public int registerListener(Device device, String str, MonitorItem monitorItem, MonitorDataCallback monitorDataCallback, int i) throws RemoteException {
        tos.a("MonitorManagerImpl", "start registerListener, srcPkgName:" + str + ", code is:" + i);
        tom.e(device, "device must not be null!");
        tom.e(str, "srcPkgName must not be null!");
        tom.e(monitorItem, "monitorItem must not be null!");
        tom.e(monitorDataCallback, "monitorDataCallback must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        try {
            PowerModeChangeManager.a().b(true);
            Permission e = e(str, monitorItem);
            this.e.a(str, "registerListener", c(e), e);
            Device processInputDevice = DeviceProcessor.processInputDevice(str, device);
            monitorDataCallback.asBinder().linkToDeath(this.b, 0);
            int d = this.f11243a.d(processInputDevice, new ArrayList(Collections.singleton(monitorItem)), new tpk(Binder.getCallingPid(), i, monitorDataCallback, processInputDevice, str));
            wearEngineBiOperate.biApiCalling(a2, str, "registerListener", String.valueOf(d));
            return d;
        } catch (IllegalStateException e2) {
            wearEngineBiOperate.biApiCalling(a2, str, "registerListener", String.valueOf(trj.b(e2)));
            throw e2;
        }
    }

    @Override // com.huawei.wearengine.MonitorManager
    public int registerListListener(Device device, String str, List<MonitorItem> list, MonitorDataCallback monitorDataCallback, int i) throws RemoteException {
        tos.a("MonitorManagerImpl", "start registerListListener, srcPkgName:" + str + ", code is:" + i);
        tom.e(device, "device must not be null!");
        tom.e(str, "srcPkgName must not be null!");
        tom.e(list, "monitorItemList must not be null!");
        tom.e(monitorDataCallback, "monitorDataCallback must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        try {
            PowerModeChangeManager.a().b(true);
            for (Permission permission : c(str, list)) {
                this.e.a(str, "registerListListener", c(permission), permission);
            }
            Device processInputDevice = DeviceProcessor.processInputDevice(str, device);
            monitorDataCallback.asBinder().linkToDeath(this.b, 0);
            int d = this.f11243a.d(processInputDevice, list, new tpk(Binder.getCallingPid(), i, monitorDataCallback, processInputDevice, str));
            wearEngineBiOperate.biApiCalling(a2, str, "registerListListener", String.valueOf(d));
            return d;
        } catch (IllegalStateException e) {
            wearEngineBiOperate.biApiCalling(a2, str, "registerListListener", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    @Override // com.huawei.wearengine.MonitorManager
    public int unregisterListener(MonitorDataCallback monitorDataCallback, int i) {
        tos.a("MonitorManagerImpl", "start unregisterListener, code is:" + i);
        int callingPid = Binder.getCallingPid();
        tom.e(monitorDataCallback, "unregisterListener must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c2 = tri.c(Binder.getCallingUid(), a2, this.d.getApplicationIdByPid(Integer.valueOf(callingPid)));
        tos.a("MonitorManagerImpl", "unregisterListener, srcPkgName:" + c2);
        PowerModeChangeManager.a().b(true);
        if (this.e.c(new tol(tot.d(), tot.e(), c2), wearEngineBiOperate, "unregisterListener", tqo.f17349a, Permission.DEVICE_MANAGER) != 0) {
            this.e.a(c2, "unregisterListener", tqo.g, Permission.WEAR_USER_STATUS);
        }
        tpk b = this.f11243a.f().b(new tpk(callingPid, i, monitorDataCallback, null, null));
        try {
            if (b == null) {
                tos.e("MonitorManagerImpl", "unregisterListener map do not have the listener");
                wearEngineBiOperate.biApiCalling(tot.a(), c2, "unregisterListener", String.valueOf(0));
                return 0;
            }
            int e = this.f11243a.e(b);
            if (e == 0) {
                b.d().asBinder().unlinkToDeath(this.b, 0);
            }
            wearEngineBiOperate.biApiCalling(tot.a(), c2, "unregisterListener", String.valueOf(e));
            return e;
        } catch (IllegalStateException e2) {
            wearEngineBiOperate.biApiCalling(a2, c2, "unregisterListener", String.valueOf(trj.b(e2)));
            throw e2;
        }
    }

    @Override // com.huawei.wearengine.MonitorManager
    public MonitorData query(Device device, MonitorItem monitorItem) throws RemoteException {
        tos.a("MonitorManagerImpl", "enter query");
        tom.e(device, "device must not be null!");
        tom.e(monitorItem, "monitorItem must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c2 = tri.c(Binder.getCallingUid(), a2, this.d.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            Permission e = e(c2, monitorItem);
            PowerModeChangeManager.a().b(true);
            this.e.a(c2, "query", c(e), e);
            MonitorData b = this.f11243a.b(DeviceProcessor.processInputDevice(c2, device), monitorItem);
            wearEngineBiOperate.biApiCalling(a2, c2, "query", String.valueOf(0));
            return b;
        } catch (IllegalStateException e2) {
            wearEngineBiOperate.biApiCalling(a2, c2, "unregisterListener", String.valueOf(trj.b(e2)));
            throw e2;
        }
    }

    @Override // com.huawei.wearengine.core.common.ClientBinderDied
    public void handleClientBinderDied(String str) {
        tom.e(str, "clientPkgName must not be null!");
        tos.a("MonitorManagerImpl", "handleClientBinderDied clientPkgName is: " + str);
        if (!b()) {
            tos.e("MonitorManagerImpl", "handleClientBinderDied checkPermission failed");
        } else {
            this.f11243a.e(str);
        }
    }

    int e(String str) {
        if (!b()) {
            tos.e("MonitorManagerImpl", "removeListener checkPermission failed");
            return 8;
        }
        this.f11243a.e(str);
        return 0;
    }

    void c(String str) {
        this.f11243a.b(str);
    }

    private boolean b() {
        return Binder.getCallingUid() == getCallingUid();
    }

    private Permission e(String str, MonitorItem monitorItem) {
        top.a(monitorItem, "getMonitorPermission monitorItem must not be null.");
        top.a(monitorItem.getName(), "getMonitorPermission monitorItem name must not be null.");
        String name = monitorItem.getName();
        if (tri.d(str) <= 5) {
            return Permission.DEVICE_MANAGER;
        }
        return c.contains(name) ? Permission.DEVICE_MANAGER : Permission.WEAR_USER_STATUS;
    }

    private List<Permission> c(String str, List<MonitorItem> list) {
        top.a(list, "getMonitorPermissions monitorItems must not be null.");
        ArrayList arrayList = new ArrayList();
        if (tri.d(str) <= 5) {
            arrayList.add(Permission.DEVICE_MANAGER);
            return arrayList;
        }
        for (MonitorItem monitorItem : list) {
            top.a(monitorItem, "getMonitorPermissions monitorItem must not be null.");
            top.a(monitorItem.getName(), "getMonitorPermissions monitorItem name must not be null.");
            if (c.contains(monitorItem.getName())) {
                if (!arrayList.contains(Permission.DEVICE_MANAGER)) {
                    arrayList.add(Permission.DEVICE_MANAGER);
                }
            } else if (!arrayList.contains(Permission.WEAR_USER_STATUS)) {
                arrayList.add(Permission.WEAR_USER_STATUS);
            }
        }
        return arrayList;
    }

    private tqo c(Permission permission) {
        top.a(permission, "getMonitorScope permission must not be null.");
        return Permission.DEVICE_MANAGER.getName().equals(permission.getName()) ? tqo.f17349a : tqo.g;
    }

    @Override // com.huawei.wearengine.core.device.PowerModeChangeManager.HandlePowerModeChange
    public void startClearData(String str) {
        tos.a("MonitorManagerImpl", "startClearData");
        tos.b("MonitorManagerImpl", "startClearData deviceId is: " + str);
        c(str);
    }
}
