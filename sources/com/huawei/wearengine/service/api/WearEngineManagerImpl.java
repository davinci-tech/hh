package com.huawei.wearengine.service.api;

import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import com.huawei.wearengine.WearEngineManager;
import com.huawei.wearengine.auth.Permission;
import com.huawei.wearengine.common.WearEngineBiOperate;
import com.huawei.wearengine.connect.ServiceConnectCallback;
import com.huawei.wearengine.core.common.ClientBinderDied;
import defpackage.toh;
import defpackage.tol;
import defpackage.tor;
import defpackage.tos;
import defpackage.tot;
import defpackage.toz;
import defpackage.tqo;
import defpackage.tri;
import defpackage.trj;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class WearEngineManagerImpl extends WearEngineManager.Stub implements ClientBinderDied {

    /* renamed from: a, reason: collision with root package name */
    private tor f11248a;
    private toz c;
    private ConcurrentHashMap<Integer, IBinder> d;
    private toh e;

    public WearEngineManagerImpl(toh tohVar, tor torVar, ConcurrentHashMap<Integer, IBinder> concurrentHashMap) {
        this.c = new toz(torVar);
        this.f11248a = torVar;
        this.d = concurrentHashMap;
        this.e = tohVar;
    }

    @Override // com.huawei.wearengine.core.common.ClientBinderDied
    public void handleClientBinderDied(String str) {
        if (!b()) {
            tos.e("WearEngineManagerImpl", "handleClientBinderDied checkPermission failed");
            return;
        }
        tos.b("WearEngineManagerImpl", "handleClientBinderDied clientPkgName:" + str);
    }

    @Override // com.huawei.wearengine.WearEngineManager
    public int registerConnectCallback(ServiceConnectCallback serviceConnectCallback) {
        tos.a("WearEngineManagerImpl", "Start registerConnectCallback");
        String d = d();
        WearEngineBiOperate a2 = a();
        Context a3 = tot.a();
        try {
            if (!b()) {
                tos.e("WearEngineManagerImpl", "registerConnectCallback checkPermission failed");
                throw new IllegalStateException(String.valueOf(8));
            }
            int b = this.c.b(this.f11248a.e(tot.d(), tot.e()), serviceConnectCallback);
            a2.biApiCalling(a3, d, "registerConnectCallback", String.valueOf(b));
            return b;
        } catch (IllegalStateException e) {
            int b2 = trj.b(e);
            a2.biApiCalling(a3, d, "registerConnectCallback", String.valueOf(b2));
            return b2;
        }
    }

    @Override // com.huawei.wearengine.WearEngineManager
    public int unregisterConnectCallback(ServiceConnectCallback serviceConnectCallback) {
        tos.a("WearEngineManagerImpl", "Start unregisterConnectCallback");
        String d = d();
        WearEngineBiOperate a2 = a();
        Context a3 = tot.a();
        try {
            if (!b()) {
                tos.e("WearEngineManagerImpl", "unregisterConnectCallback checkPermission failed");
                throw new IllegalStateException(String.valueOf(8));
            }
            int d2 = this.c.d(this.f11248a.e(tot.d(), tot.e()), serviceConnectCallback);
            a2.biApiCalling(a3, d, "unregisterConnectCallback", String.valueOf(d2));
            return d2;
        } catch (IllegalStateException e) {
            int b = trj.b(e);
            a2.biApiCalling(a3, d, "unregisterConnectCallback", String.valueOf(b));
            return b;
        }
    }

    @Override // com.huawei.wearengine.WearEngineManager
    public int releaseConnection() {
        tos.a("WearEngineManagerImpl", "releaseConnection start.");
        String d = d();
        WearEngineBiOperate a2 = a();
        Context a3 = tot.a();
        try {
            this.c.e();
            int d2 = d(d, tot.d(), tot.e(), a2);
            a2.biApiCalling(a3, d, "releaseConnection", String.valueOf(d2));
            return d2;
        } catch (IllegalStateException e) {
            int b = trj.b(e);
            a2.biApiCalling(a3, d, "releaseConnection", String.valueOf(b));
            return b;
        }
    }

    @Override // com.huawei.wearengine.WearEngineManager.Stub, android.os.IInterface
    public IBinder asBinder() {
        if (b()) {
            return this;
        }
        tos.e("WearEngineManagerImpl", "asBinder checkPermission failed");
        return null;
    }

    private boolean b() {
        return Binder.getCallingUid() == getCallingUid();
    }

    private WearEngineBiOperate a() {
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        return wearEngineBiOperate;
    }

    private String d() {
        return tri.c(Binder.getCallingUid(), tot.a(), this.f11248a.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
    }

    private int d(String str, int i, int i2, WearEngineBiOperate wearEngineBiOperate) {
        int e;
        int c;
        if (this.d == null) {
            tos.e("WearEngineManagerImpl", "clean mBinderMap is null");
            return 12;
        }
        tos.a("WearEngineManagerImpl", "clean callingPkgName: " + str + " callingPid:" + i2);
        int c2 = this.e.c(new tol(i, i2, str), wearEngineBiOperate, "releaseConnection", tqo.f17349a, Permission.DEVICE_MANAGER);
        if (c2 == 0 && (c = c(i2)) != 0) {
            return c;
        }
        if (c2 != 0) {
            c2 = this.e.c(new tol(i, i2, str), wearEngineBiOperate, "releaseConnection", tqo.g, Permission.WEAR_USER_STATUS);
        }
        if (c2 == 0 && (e = e(str)) != 0) {
            return e;
        }
        int c3 = this.e.c(new tol(i, i2, str), wearEngineBiOperate, "releaseConnection", tqo.e, Permission.SENSOR);
        if (c3 != 0) {
            c3 = this.e.c(new tol(i, i2, str), wearEngineBiOperate, "releaseConnection", tqo.c, Permission.MOTION_SENSOR);
        }
        if (c3 != 0) {
            return 0;
        }
        c(str);
        return 0;
    }

    private int c(int i) {
        IBinder iBinder = this.d.get(2);
        if (iBinder instanceof P2pManagerImpl) {
            int c = ((P2pManagerImpl) iBinder).c(i);
            if (c == 0) {
                return 0;
            }
            tos.e("WearEngineManagerImpl", "removeReceiver failed, ret:" + c);
            return c;
        }
        tos.a("WearEngineManagerImpl", "p2pBinder is null, no need to remove receiver");
        return 0;
    }

    private int e(String str) {
        IBinder iBinder = this.d.get(3);
        if (iBinder instanceof MonitorManagerImpl) {
            int e = ((MonitorManagerImpl) iBinder).e(str);
            if (e == 0) {
                return 0;
            }
            tos.e("WearEngineManagerImpl", "removeMonitorReceiver failed, ret:" + e);
            return e;
        }
        tos.a("WearEngineManagerImpl", "monitorManager is null, no need to remove receiver");
        return 0;
    }

    private void c(String str) {
        IBinder iBinder = this.d.get(7);
        if (iBinder instanceof SensorManagerImpl) {
            ((SensorManagerImpl) iBinder).b(str);
        } else {
            tos.a("WearEngineManagerImpl", "sensorManager is null, no need to stop sensor");
        }
    }
}
