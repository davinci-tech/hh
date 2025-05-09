package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.huawei.controlcenter.featureability.sdk.IAuthCallback;
import com.huawei.controlcenter.featureability.sdk.IConnectCallback;
import com.huawei.controlcenter.featureability.sdk.IRemoteRegisterService;
import com.huawei.controlcenter.featureability.sdk.model.ExtraParams;
import com.huawei.onehop.fasdk.job.PendingRequest;
import com.tencent.open.apireq.BaseResp;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes5.dex */
public class lsn implements ServiceConnection {
    private static lsn b;

    /* renamed from: a, reason: collision with root package name */
    private IRemoteRegisterService f14853a;
    private final Context c;
    private final Object j = new Object();
    private final Object i = new Object();
    private final AtomicBoolean d = new AtomicBoolean(false);
    private final List<PendingRequest> e = new ArrayList(1);

    private lsn(Context context) {
        if (context == null) {
            this.c = null;
        } else {
            this.c = context.getApplicationContext();
        }
    }

    public static lsn a(Context context) {
        lsn lsnVar;
        synchronized (lsn.class) {
            if (b == null) {
                b = new lsn(context);
            }
            lsnVar = b;
        }
        return lsnVar;
    }

    public int e() {
        Log.i("FAKit: ConnectorMgr", "bindRemoteRegisterService start");
        if (this.c == null) {
            Log.e("FAKit: ConnectorMgr", "bindService, mContext is null");
            return -1101;
        }
        if (!j()) {
            Optional<IRemoteRegisterService> c = c();
            if (c.isPresent()) {
                this.f14853a = c.get();
                this.d.set(true);
                return 0;
            }
            Log.e("FAKit: ConnectorMgr", "get system service,but service is null");
            return -1109;
        }
        if (d()) {
            return 0;
        }
        return b();
    }

    public int a(PendingRequest pendingRequest) {
        Log.i("FAKit: ConnectorMgr", "bindRemoteRegisterService with pendingRequest start");
        if (this.c == null || pendingRequest == null) {
            Log.w("FAKit: ConnectorMgr", "request task is null");
            return -1101;
        }
        if (d()) {
            Log.i("FAKit: ConnectorMgr", "bindRemoteRegisterService is connected");
            pendingRequest.execute();
            return 0;
        }
        synchronized (this.i) {
            this.e.add(pendingRequest);
        }
        return e();
    }

    private Optional<IRemoteRegisterService> c() {
        IBinder iBinder = null;
        try {
            Object invoke = Class.forName("com.huawei.android.os.ServiceManagerEx").getMethod("getService", String.class).invoke(null, "HopTaskManagementService");
            if (invoke instanceof IBinder) {
                iBinder = (IBinder) invoke;
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("FAKit: ConnectorMgr", "invoke getService exception " + e.getMessage());
        }
        if (iBinder == null) {
            Log.e("FAKit: ConnectorMgr", "get system service,but binder is null");
            return Optional.empty();
        }
        return Optional.ofNullable(IRemoteRegisterService.Stub.asInterface(iBinder));
    }

    private int b() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.huawei.controlcenter", "com.huawei.controlcenter.fatransfer.service.FeatureAbilityRegisterService"));
        try {
            boolean bindService = this.c.bindService(intent, this, 1);
            Log.i("FAKit: ConnectorMgr", "bindFeatureAbilityService " + bindService);
            return !bindService ? -1102 : 0;
        } catch (SecurityException unused) {
            return -1102;
        }
    }

    private boolean j() {
        Context context = this.c;
        if (context == null) {
            Log.e("FAKit: ConnectorMgr", "isSupportedTagService context is null");
            return false;
        }
        try {
            context.getPackageManager().getServiceInfo(new ComponentName("com.huawei.controlcenter", "com.huawei.controlcenter.fatransfer.service.FeatureAbilityRegisterService"), 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("FAKit: ConnectorMgr", "can not find getServiceInfo.");
            return false;
        }
    }

    public void a() {
        Log.i("FAKit: ConnectorMgr", "unbindRemoteCardService.");
        try {
            this.c.unbindService(this);
        } catch (IllegalArgumentException e) {
            Log.w("FAKit: ConnectorMgr", "unbindService find a IllegalArgumentException." + e.getMessage());
        }
        synchronized (this.j) {
            this.d.set(false);
            this.f14853a = null;
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.i("FAKit: ConnectorMgr", "onServiceConnected: component = " + componentName);
        if (iBinder == null) {
            Log.i("FAKit: ConnectorMgr", "in onServiceConnected IBinder is null");
            return;
        }
        synchronized (this.j) {
            this.f14853a = IRemoteRegisterService.Stub.asInterface(iBinder);
            this.d.set(true);
        }
        synchronized (this.i) {
            Iterator<PendingRequest> it = this.e.iterator();
            while (it.hasNext()) {
                it.next().execute();
            }
            this.e.clear();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        Log.i("FAKit: ConnectorMgr", "onServiceDisconnected: component = " + componentName);
        a();
    }

    @Override // android.content.ServiceConnection
    public void onBindingDied(ComponentName componentName) {
        Log.i("FAKit: ConnectorMgr", "onBindingDied: component = " + componentName);
        a();
    }

    @Override // android.content.ServiceConnection
    public void onNullBinding(ComponentName componentName) {
        Log.i("FAKit: ConnectorMgr", "onNullBinding: component = " + componentName);
        a();
    }

    public boolean d() {
        boolean z;
        synchronized (this.j) {
            z = this.f14853a != null && this.d.get();
        }
        return z;
    }

    public int cac_(String str, IBinder iBinder, ExtraParams extraParams, IConnectCallback iConnectCallback) {
        Log.i("FAKit: ConnectorMgr", "register, packageName:" + lsm.a(str));
        if (!lsm.c(str, extraParams, iConnectCallback)) {
            return BaseResp.CODE_QQ_LOW_VERSION;
        }
        IRemoteRegisterService iRemoteRegisterService = this.f14853a;
        if (iRemoteRegisterService == null) {
            Log.e("FAKit: ConnectorMgr", "register: service is not connected!");
            return BaseResp.CODE_QQ_LOW_VERSION;
        }
        try {
            return iRemoteRegisterService.register(str, iBinder, extraParams, iConnectCallback);
        } catch (RemoteException e) {
            Log.w("FAKit: ConnectorMgr", "register find a remote exception!" + e.getMessage());
            return BaseResp.CODE_QQ_LOW_VERSION;
        }
    }

    public boolean c(int i) {
        Log.i("FAKit: ConnectorMgr", "unregister, token:" + lsm.a(String.valueOf(i)));
        IRemoteRegisterService iRemoteRegisterService = this.f14853a;
        if (iRemoteRegisterService == null) {
            Log.i("FAKit: ConnectorMgr", "unregister: service is not connected!");
            return false;
        }
        try {
            return iRemoteRegisterService.unregister(i);
        } catch (RemoteException e) {
            Log.w("FAKit: ConnectorMgr", "unregister find a remote exception!" + e.getMessage());
            return false;
        }
    }

    public boolean d(int i, String str, int i2) {
        Log.i("FAKit: ConnectorMgr", "updateDeviceStatus, token: " + lsm.a(String.valueOf(i)) + ", deviceId: " + lsm.a(str) + ", status: " + i2);
        if (!lsm.b(str)) {
            return false;
        }
        IRemoteRegisterService iRemoteRegisterService = this.f14853a;
        if (iRemoteRegisterService == null) {
            Log.i("FAKit: ConnectorMgr", "updateDeviceStatus: service is not connected!");
            return false;
        }
        try {
            return iRemoteRegisterService.updateConnectStatus(i, str, i2);
        } catch (RemoteException e) {
            Log.w("FAKit: ConnectorMgr", "updateDeviceStatus find a remote exception!" + e.getMessage());
            return false;
        }
    }

    public boolean d(int i, ExtraParams extraParams) {
        Log.i("FAKit: ConnectorMgr", "showDeviceList, token:" + lsm.a(String.valueOf(i)));
        IRemoteRegisterService iRemoteRegisterService = this.f14853a;
        if (iRemoteRegisterService == null) {
            Log.e("FAKit: ConnectorMgr", "showDeviceList: service is not connected!");
            return false;
        }
        try {
            return iRemoteRegisterService.showDeviceList(i, extraParams);
        } catch (RemoteException e) {
            Log.w("FAKit: ConnectorMgr", "showDeviceList find a remote exception!" + e.getMessage());
            return false;
        }
    }

    public boolean d(String str, IAuthCallback iAuthCallback) {
        Log.i("FAKit: ConnectorMgr", "authReq, pkgName:" + lsm.a(String.valueOf(str)));
        IRemoteRegisterService iRemoteRegisterService = this.f14853a;
        if (iRemoteRegisterService == null) {
            Log.e("FAKit: ConnectorMgr", "authReq: service is not connected!");
            return false;
        }
        try {
            return iRemoteRegisterService.authReq(str, iAuthCallback);
        } catch (RemoteException e) {
            Log.w("FAKit: ConnectorMgr", "authReq find a remote exception!" + e.getMessage());
            return false;
        }
    }
}
