package com.huawei.wearengine.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.wearengine.BinderService;
import com.huawei.wearengine.ClientToken;
import com.huawei.wearengine.HwWearEngineNativeBinder;
import com.huawei.wearengine.IdentityStoreCallback;
import com.huawei.wearengine.auth.AuthListener;
import com.huawei.wearengine.auth.AuthListenerManager;
import com.huawei.wearengine.auth.Permission;
import com.huawei.wearengine.core.device.VirtualDevice;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.p2p.IdentityInfo;
import com.huawei.wearengine.repository.AuthInfoRepositoryImpl;
import com.huawei.wearengine.repository.api.AuthInfoRepository;
import com.huawei.wearengine.service.api.AuthManagerImpl;
import com.huawei.wearengine.service.api.DeviceManagerImpl;
import com.huawei.wearengine.service.api.MonitorManagerImpl;
import com.huawei.wearengine.service.api.NotifyManagerImpl;
import com.huawei.wearengine.service.api.OtaManagerImpl;
import com.huawei.wearengine.service.api.P2pManagerImpl;
import com.huawei.wearengine.service.api.SensorManagerImpl;
import com.huawei.wearengine.service.api.WearEngineManagerImpl;
import defpackage.mbk;
import defpackage.tnz;
import defpackage.toe;
import defpackage.toh;
import defpackage.tor;
import defpackage.tos;
import defpackage.tot;
import defpackage.toy;
import defpackage.trd;
import defpackage.trf;
import defpackage.tri;
import defpackage.tro;
import defpackage.trt;
import defpackage.tru;
import defpackage.trw;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class WearEngineExtendService extends Service {
    private static ConcurrentHashMap<Integer, IBinder> c = new ConcurrentHashMap<>(8);
    private tor j = null;
    private volatile AuthInfoRepository e = null;
    private volatile toh b = null;

    /* renamed from: a, reason: collision with root package name */
    private final Object f11236a = new Object();
    private String g = null;
    private String f = UUID.randomUUID().toString();
    private IBinder h = new BinderService.Stub() { // from class: com.huawei.wearengine.service.WearEngineExtendService.1
        @Override // com.huawei.wearengine.BinderService
        public IBinder getBinder(int i) throws RemoteException {
            tos.a("WearEngineExtendService", "BinderService getBinder");
            return WearEngineExtendService.this.feN_(tot.d(), tot.e(), i);
        }

        @Override // com.huawei.wearengine.BinderService
        public IBinder getBinderProxy(int i, int i2, int i3) throws RemoteException {
            return WearEngineExtendService.this.feN_(i, i2, i3);
        }

        @Override // com.huawei.wearengine.BinderService
        public void registerToken(String str, ClientToken clientToken) throws RemoteException {
            WearEngineExtendService.this.c(tot.d(), tot.e(), str, clientToken);
        }

        @Override // com.huawei.wearengine.BinderService
        public void registerTokenProxy(int i, int i2, String str, ClientToken clientToken) throws RemoteException {
            WearEngineExtendService.this.c(i, i2, str, clientToken);
        }

        @Override // com.huawei.wearengine.BinderService
        public void checkPermissionIdentity(String str, String str2, IdentityStoreCallback identityStoreCallback) throws RemoteException {
            if (checkPermission()) {
                WearEngineExtendService.this.b(tot.d(), tot.e(), str, str2, identityStoreCallback);
            }
        }

        @Override // com.huawei.wearengine.BinderService
        public void checkPermissionIdentityProxy(int i, int i2, String str, String str2, IdentityStoreCallback identityStoreCallback) throws RemoteException {
            tos.a("WearEngineExtendService", "BinderService checkPermissionIdentityProxy");
            WearEngineExtendService.this.b(i, i2, str, str2, identityStoreCallback);
        }

        @Override // com.huawei.wearengine.BinderService
        public int exchangeApiLevel(int i) {
            tos.a("WearEngineExtendService", "BinderService exchangeApiLevel, sdkApiLevel:" + i);
            if (!checkPermission()) {
                return 0;
            }
            WearEngineExtendService.this.a(tot.d(), tot.e(), i);
            return tru.c();
        }

        @Override // com.huawei.wearengine.BinderService
        public int exchangeApiLevelProxy(int i, int i2, int i3) {
            tos.a("WearEngineExtendService", "BinderService exchangeApiLevelProxy, sdkApiLevel:" + i3);
            if (!checkPermission()) {
                return 0;
            }
            WearEngineExtendService.this.a(i, i2, i3);
            return exchangeApiLevel(i3);
        }

        private boolean checkPermission() {
            return Binder.getCallingUid() == getCallingUid();
        }
    };
    private IBinder i = new HwWearEngineNativeBinder.Stub() { // from class: com.huawei.wearengine.service.WearEngineExtendService.2
        @Override // com.huawei.wearengine.HwWearEngineNativeBinder
        public void setBinder(String str, IBinder iBinder) throws RemoteException {
            if (TextUtils.isEmpty(str)) {
                tos.e("WearEngineExtendService", "setBinder tag is null");
                return;
            }
            if (iBinder == null) {
                tos.e("WearEngineExtendService", "setBinder binder is null");
                return;
            }
            if (!trf.c()) {
                tos.e("WearEngineExtendService", "setBinder is not InnerInvoke");
                return;
            }
            if (str.equals("WearEngine")) {
                toy.b().fcZ_(str, iBinder);
            }
            if (str.equals("WearEnginePhdkit")) {
                mbk.d(tot.a()).ccQ_(iBinder);
            }
        }

        @Override // com.huawei.wearengine.HwWearEngineNativeBinder
        public boolean isP2pReceiverExist(Device device, IdentityInfo identityInfo, IdentityInfo identityInfo2) {
            if (device == null || identityInfo == null || identityInfo2 == null) {
                tos.e("WearEngineExtendService", "isP2pReceiverExist argument is invalid");
                return false;
            }
            tos.a("WearEngineExtendService", "enter isP2pReceiverExist");
            VirtualDevice d = toy.b().d(device);
            if (d == null) {
                tos.e("WearEngineExtendService", "isP2pReceiverExist virtualDevice is null");
                return false;
            }
            return d.isP2pReceiverExist(device.getUuid(), identityInfo, identityInfo2);
        }
    };
    private IBinder d = new AuthListenerManager.Stub() { // from class: com.huawei.wearengine.service.WearEngineExtendService.4
        @Override // com.huawei.wearengine.auth.AuthListenerManager
        public void authListenerOnOk(String str, Permission[] permissionArr) throws RemoteException {
            tos.a("WearEngineExtendService", "authListenerOnOk key:" + str);
            if (!TextUtils.isEmpty(str)) {
                synchronized (WearEngineExtendService.this.f11236a) {
                    if (WearEngineExtendService.this.e != null) {
                        WearEngineExtendService.this.e.updateCache(str);
                    }
                }
                AuthListener c2 = tnz.b().c(str);
                if (c2 == null) {
                    tos.d("WearEngineExtendService", "authListenerOnOk authListener is null");
                    return;
                }
                try {
                    c2.onOk(permissionArr);
                    tos.a("WearEngineExtendService", "authListener.onOk");
                    return;
                } catch (Exception unused) {
                    tos.e("WearEngineExtendService", "authListener.onOk has exception");
                    return;
                }
            }
            tos.e("WearEngineExtendService", "authListenerOnOk key is empty");
        }

        @Override // com.huawei.wearengine.auth.AuthListenerManager
        public void authListenerOnCancel(String str) throws RemoteException {
            tos.a("WearEngineExtendService", "authListenerOnCancel key:" + str);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            AuthListener c2 = tnz.b().c(str);
            if (c2 == null) {
                tos.a("WearEngineExtendService", "authListenerOnCancel authListener is null");
                return;
            }
            try {
                c2.onCancel();
                tos.a("WearEngineExtendService", "authListener.onCancel");
            } catch (Exception unused) {
                tos.e("WearEngineExtendService", "authListener.onCancel has exception");
            }
        }

        @Override // com.huawei.wearengine.auth.AuthListenerManager
        public List<String> getAllPackageName() throws RemoteException {
            Set<String> a2 = trw.a(tot.a());
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(a2);
            return arrayList;
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2, int i3) {
        synchronized (this.f11236a) {
            tor torVar = this.j;
            if (torVar == null) {
                tos.e("WearEngineExtendService", "mClientManager is null in saveSdkApiLevel.");
            } else {
                torVar.a(i, i2, i3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IBinder feN_(int i, int i2, int i3) {
        String c2;
        IBinder iBinder;
        tos.a("WearEngineExtendService", this.f + " queryBinder apiType: " + i3);
        Context a2 = tot.a();
        if (!trd.e(tri.a(a2))) {
            tos.d("WearEngineExtendService", "getBinder account not login!");
            throw new IllegalStateException(String.valueOf(3));
        }
        if (!tri.d(a2)) {
            tos.d("WearEngineExtendService", "getBinder not authorized");
            throw new IllegalStateException(String.valueOf(7));
        }
        tos.b("WearEngineExtendService", "BinderService getBinder:" + i3);
        if (i3 == 1 && !tri.b()) {
            tos.e("WearEngineExtendService", "get DeviceManagerImpl no device");
            throw new IllegalStateException(String.valueOf(4));
        }
        tos.b("WearEngineExtendService", "BinderService getBinder uid: " + i);
        tos.b("WearEngineExtendService", "BinderService getBinder pid: " + i2);
        synchronized (this.f11236a) {
            if (this.j == null) {
                tos.e("WearEngineExtendService", "mClientManager is null in queryBinder.");
                throw new IllegalStateException(String.valueOf(12));
            }
            c2 = tri.c(i, tot.a(), this.j.getApplicationIdByPid(Integer.valueOf(i2)));
        }
        a(i, c2);
        int c3 = c(i, c2, a2);
        if (c3 != 0) {
            tos.d("WearEngineExtendService", "preGrantPermission failed, ret:" + c3);
            throw new IllegalStateException(String.valueOf(c3));
        }
        synchronized (this.f11236a) {
            iBinder = c.get(Integer.valueOf(i3));
            if (iBinder == null) {
                iBinder = feM_(i3);
            }
        }
        return iBinder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, int i2, String str, ClientToken clientToken) throws RemoteException {
        tos.a("WearEngineExtendService", this.f + " BinderService registerToken");
        if (TextUtils.isEmpty(str)) {
            tos.e("WearEngineExtendService", "BinderService registerToken clientPkgName is null");
            return;
        }
        if (clientToken == null) {
            tos.e("WearEngineExtendService", "BinderService registerToken token is null");
            return;
        }
        if (TextUtils.isEmpty(this.g)) {
            String a2 = tri.a(tot.a());
            boolean d = tri.d(tot.a());
            if (trd.e(a2) && d) {
                this.g = tri.e(tot.a());
            }
        }
        synchronized (this.f11236a) {
            if (this.j == null) {
                tos.e("WearEngineExtendService", "mClientManager is null in startRegisterToken.");
                return;
            }
            tos.a("WearEngineExtendService", "BinderService setApplicationId pid:" + i2);
            tos.a("WearEngineExtendService", "BinderService setApplicationId clientPkgName:" + str);
            this.j.setApplicationId(Integer.valueOf(i2), str);
            this.j.d(i, clientToken, str);
            clientToken.asBinder().linkToDeath(this.j.fcX_(), 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2, String str, String str2, IdentityStoreCallback identityStoreCallback) throws RemoteException {
        if (identityStoreCallback == null) {
            tos.d("WearEngineExtendService", "BinderService handleCheck callback == null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            tos.d("WearEngineExtendService", "BinderService handleCheck packageName isEmpty");
            identityStoreCallback.storePermissionIdentity(null);
            return;
        }
        String c2 = c(i, str);
        if (TextUtils.isEmpty(c2)) {
            tos.e("WearEngineExtendService", "BinderService handleCheck generatePermissionIdentity error");
            identityStoreCallback.storePermissionIdentity(null);
        } else {
            if (c2.equals(str2)) {
                return;
            }
            tos.a("WearEngineExtendService", "BinderService need clear permission data");
            identityStoreCallback.storePermissionIdentity(c2);
            b(i, str);
        }
    }

    private String c(int i, String str) {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append(i);
        stringBuffer.append("_");
        stringBuffer.append(str);
        stringBuffer.append("_wearEngine");
        return trt.d(stringBuffer.toString(), "SHA-256");
    }

    private void b(int i, String str) {
        tos.b("WearEngineExtendService", "BinderService clearPermissionData");
        Context a2 = tot.a();
        if (TextUtils.isEmpty(str)) {
            tos.d("WearEngineExtendService", "BinderService clearPermissionData packageName isEmpty");
            return;
        }
        int j = tri.j(a2, str);
        if (j == 0) {
            tos.d("WearEngineExtendService", "BinderService clearPermissionData appId == 0");
            return;
        }
        String a3 = tri.a(a2);
        if (TextUtils.isEmpty(a3)) {
            tos.d("WearEngineExtendService", "BinderService clearPermissionData userId isEmpty");
            return;
        }
        synchronized (this.f11236a) {
            if (this.e == null) {
                this.e = new AuthInfoRepositoryImpl(a2);
            }
            this.e.deleteAuth(i, a3, j);
        }
    }

    private void a(int i, String str) {
        String c2 = tri.c(i, tot.a(), str);
        new toh(this.e, this.g);
        if (toh.c(i, c2)) {
            return;
        }
        trw.e(tot.a(), c2);
        if (TextUtils.isEmpty(tri.a(tot.a()))) {
            throw new IllegalStateException(String.valueOf(3));
        }
        tri.a(tot.a(), c2);
    }

    private int c(int i, String str, Context context) {
        synchronized (this.f11236a) {
            if (this.e == null) {
                this.e = new AuthInfoRepositoryImpl(tot.a());
            }
        }
        String c2 = tri.c(i, context, str);
        new toh(this.e, this.g);
        if (toh.c(i, c2)) {
            tos.a("WearEngineExtendService", "preGrantPermission isSuperCaller");
            return 0;
        }
        return toe.c(this.e).a(context, i, c2);
    }

    private IBinder feM_(int i) {
        tos.a("WearEngineExtendService", "makeClientManager:" + i);
        if (this.j == null) {
            tos.d("WearEngineExtendService", "makeClientManager mClientManager is null");
            throw new IllegalStateException(String.valueOf(6));
        }
        if (this.e == null) {
            this.e = new AuthInfoRepositoryImpl(tot.a());
        }
        if (this.b == null) {
            this.b = new toh(this.e, this.g);
        }
        return feK_(i);
    }

    private IBinder feK_(int i) {
        switch (i) {
            case 1:
                DeviceManagerImpl deviceManagerImpl = new DeviceManagerImpl(this.b, this.j);
                c.put(Integer.valueOf(i), deviceManagerImpl);
                return deviceManagerImpl;
            case 2:
                P2pManagerImpl p2pManagerImpl = new P2pManagerImpl(this.b, this.j);
                c.put(Integer.valueOf(i), p2pManagerImpl);
                return p2pManagerImpl;
            case 3:
                MonitorManagerImpl monitorManagerImpl = new MonitorManagerImpl(this.b, this.j);
                c.put(Integer.valueOf(i), monitorManagerImpl);
                return monitorManagerImpl;
            case 4:
                NotifyManagerImpl notifyManagerImpl = new NotifyManagerImpl(this.b, this.j);
                c.put(Integer.valueOf(i), notifyManagerImpl);
                return notifyManagerImpl;
            case 5:
                AuthManagerImpl authManagerImpl = new AuthManagerImpl(this.e, this.j);
                c.put(Integer.valueOf(i), authManagerImpl);
                return authManagerImpl;
            case 6:
                return new WearEngineManagerImpl(this.b, this.j, c);
            case 7:
                SensorManagerImpl sensorManagerImpl = new SensorManagerImpl(this.b, this.j);
                c.put(Integer.valueOf(i), sensorManagerImpl);
                return sensorManagerImpl;
            case 8:
                OtaManagerImpl otaManagerImpl = new OtaManagerImpl(this.b, this.j);
                c.put(Integer.valueOf(i), otaManagerImpl);
                return otaManagerImpl;
            default:
                return null;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        tos.a("WearEngineExtendService", this.f + " onCreate!");
        tot.a(getApplicationContext());
        tro.e();
        c.clear();
        this.j = new tor(c);
        super.onCreate();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        tos.a("WearEngineExtendService", this.f + " onBind!");
        if (intent != null) {
            if ("com.huawei.wearengine.action.PHONE_SERVICE".equals(intent.getAction())) {
                tos.a("WearEngineExtendService", "onBind return HwWearEngineNativeBinder");
                return this.i;
            }
            if ("com.huawei.wearengine.action.AUTH_LISTENER_MANAGER".equals(intent.getAction())) {
                tos.a("WearEngineExtendService", "onBind return AuthListenerManagerBinder");
                return this.d;
            }
        }
        tos.a("WearEngineExtendService", "onBind return BinderService");
        return this.h;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        tos.a("WearEngineExtendService", this.f + " onUnbind!");
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        tos.a("WearEngineExtendService", this.f + " onStartCommand!");
        if (intent == null || !"com.huawei.bone.action.DELETE_AUTH_CACHE".equals(intent.getAction())) {
            return 2;
        }
        feL_(intent);
        super.onStartCommand(intent, i, i2);
        return 2;
    }

    private void feL_(Intent intent) {
        try {
            String stringExtra = intent.getStringExtra("third_party_package_name");
            synchronized (this.f11236a) {
                if (this.e != null && !TextUtils.isEmpty(stringExtra)) {
                    this.e.deleteAuthFromCache(stringExtra);
                }
            }
        } catch (BadParcelableException unused) {
            tos.e("WearEngineExtendService", "getStringExtra catch an BadParcelableException");
        } catch (RuntimeException unused2) {
            tos.e("WearEngineExtendService", "getStringExtra catch an RuntimeException");
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        tos.a("WearEngineExtendService", this.f + " onDestroy!");
        synchronized (this.f11236a) {
            c.clear();
            this.j = null;
            this.e = null;
            this.b = null;
        }
        this.g = null;
        super.onDestroy();
    }
}
