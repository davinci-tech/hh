package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.phdkit.DataContentListener;
import com.huawei.phdkit.DataReceiveListener;
import com.huawei.phdkit.DeviceData;
import com.huawei.phdkit.DeviceStateListener;
import com.huawei.phdkit.DiscoveryListener;
import com.huawei.phdkit.DvLiteBinder;
import com.huawei.phdkit.DvLiteCommand;
import defpackage.mbk;
import defpackage.tos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes9.dex */
public class mbk {
    private static Context b;

    /* renamed from: a, reason: collision with root package name */
    private IBinder.DeathRecipient f14867a;
    private DvLiteBinder f;
    private Map<String, DataReceiveListener> g;
    private boolean h;
    private boolean i;
    private Map<String, DataReceiveListener> j;
    private DeviceStateListener l;
    private ExecutorService m;
    private static final Object c = new Object();
    private static final Object e = new Object();
    private static final Object d = new Object();

    static class a {
        public static final mbk e = new mbk();
    }

    private mbk() {
        this.h = true;
        this.g = new HashMap(4);
        this.i = false;
        this.j = new ConcurrentHashMap();
        this.f14867a = new IBinder.DeathRecipient() { // from class: mbk.4
            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                try {
                    Log.i("PhdKit", "binderDied enter");
                    if (mbk.this.l != null) {
                        mbk.this.l.onServiceDied();
                    }
                    if (mbk.this.f != null) {
                        mbk.this.f = null;
                    }
                    mbk.this.j.clear();
                    synchronized (mbk.d) {
                        mbk.this.g.clear();
                        Log.i("PhdKit", "mDataListenerMap.clear()");
                    }
                } catch (RemoteException unused) {
                    Log.e("PhdKit", "binderDied RemoteException");
                }
            }
        };
        Log.i("PhdKit", "getInstance create");
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        this.m = newSingleThreadExecutor;
        newSingleThreadExecutor.execute(new Runnable() { // from class: mbk.3
            @Override // java.lang.Runnable
            public void run() {
                mbk.this.b();
            }
        });
    }

    public Map<String, DataReceiveListener> c() {
        return this.j;
    }

    public void ccQ_(IBinder iBinder) {
        tos.a("PhdKit", "setWearEnginePhdkitBinder enter");
        try {
            this.f = DvLiteBinder.Stub.asInterface(iBinder);
            tos.b("PhdKit", "setWearEnginePhdkitBinder mApiAidl: " + this.f);
            DvLiteBinder dvLiteBinder = this.f;
            if (dvLiteBinder == null) {
                tos.d("PhdKit", "onServiceConnected error !");
                return;
            }
            dvLiteBinder.asBinder().linkToDeath(this.f14867a, 0);
            Object obj = e;
            synchronized (obj) {
                tos.a("PhdKit", "setWearEnginePhdkitBinder BIND_LOCK unlock");
                this.i = true;
                obj.notifyAll();
            }
        } catch (RemoteException unused) {
            tos.d("PhdKit", "setBinder exception");
        }
    }

    public static mbk d(Context context) {
        Log.i("PhdKit", "PhdKit getInstance");
        if (context != null) {
            b = context.getApplicationContext();
        }
        return a.e;
    }

    private void d() {
        tos.a("PhdKit", "phdkit startPhoneService enter");
        if (tpe.c()) {
            tos.a("PhdKit", "phdkit startPhoneService start phone service");
            Intent intent = new Intent();
            intent.setAction("com.huawei.bone.action.StartPhoneService");
            intent.setPackage("com.huawei.health");
            intent.putExtra("WearEngineDataKey", "WearEnginePhdkitStartPhoneService");
            try {
                tot.a().startService(intent);
            } catch (IllegalStateException | SecurityException unused) {
                tos.d("PhdKit", "startPhoneService fail");
                throw new IllegalStateException(String.valueOf(12));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Log.d("PhdKit", "bindService enter");
        if (this.f != null) {
            return;
        }
        synchronized (c) {
            if (this.f == null) {
                Log.w("PhdKit", "bindService mApiAidl == null");
                d();
                e();
            }
        }
    }

    private void e() {
        synchronized (e) {
            this.i = false;
            try {
            } catch (InterruptedException unused) {
                Log.e("PhdKit", "waitBind() InterruptedException");
            }
            if (this.f != null) {
                Log.i("PhdKit", "bindService bind mApiAidl is not null");
                return;
            }
            while (!this.i) {
                tos.a("PhdKit", "setWearEnginePhdkitBinder BIND_LOCK lock on");
                e.wait(OpAnalyticsConstants.H5_LOADING_DELAY);
                this.i = true;
            }
            Log.i("PhdKit", "bindService bind over mApiAidl is null");
        }
    }

    public boolean b(int i, final DiscoveryListener discoveryListener) {
        if (discoveryListener == null) {
            return false;
        }
        Log.i("PhdKit", "startDiscovery enter");
        this.m.execute(new Runnable() { // from class: mbk.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    mbk.this.b();
                    if (mbk.this.f == null) {
                        Log.e("PhdKit", "startDiscovery mApiAidl is null");
                        discoveryListener.onDeviceFound(new ArrayList());
                    } else {
                        Log.i("PhdKit", "startDiscovery send");
                        mbk.this.f.startDiscovery(discoveryListener);
                    }
                } catch (RemoteException unused) {
                    Log.e("PhdKit", "startDiscovery mApiAidl is null");
                }
            }
        });
        return true;
    }

    public void d(String str, byte[] bArr) {
        Log.i("PhdKit", "sendDataCommand enter");
        c(str, bArr, 1, 1);
    }

    public void b(String str, byte[] bArr) {
        Log.i("PhdKit", "sendControlCommand enter");
        Log.d("PhdKit", "sendControlCommand data " + mbg.c(bArr));
        c(str, bArr, 2, 2);
    }

    private void c(final String str, final byte[] bArr, final int i, final int i2) {
        a(this.m.submit(new Callable<Integer>() { // from class: mbk.5
            @Override // java.util.concurrent.Callable
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                if (mbk.this.h) {
                    mbk.this.b();
                    if (mbk.this.b(bArr, str)) {
                        mbk.this.a(str);
                        Log.e("PhdKit", "sendData mApiAidl parameter is invalid.");
                        return -1;
                    }
                    try {
                        DvLiteCommand dvLiteCommand = new DvLiteCommand();
                        dvLiteCommand.setServiceId(48);
                        dvLiteCommand.setCommandId(i);
                        dvLiteCommand.setDataContents(mbg.a(mbg.a(1) + mbg.e(bArr.length) + mbg.c(bArr)));
                        dvLiteCommand.setUdid(str);
                        dvLiteCommand.setPriority(i2);
                        mbk.this.f.sendBluetoothData(dvLiteCommand);
                        return 0;
                    } catch (RemoteException unused) {
                        Log.e("PhdKit", "sendData RemoteException occur");
                        return 12;
                    } catch (IllegalStateException e2) {
                        Log.e("PhdKit", "sendData IllegalStateException occur");
                        return Integer.valueOf(toq.b(e2.getMessage()));
                    }
                }
                Log.w("PhdKit", "sendData MSDP isn't binded.");
                return -1;
            }
        }), "sendData");
    }

    public int a(final String str, final DataReceiveListener dataReceiveListener) {
        Log.i("PhdKit", "connectDevice enter");
        if (dataReceiveListener == null || TextUtils.isEmpty(str)) {
            Log.i("PhdKit", "dataListener is null or udid is null");
            return -2;
        }
        this.m.execute(new Runnable() { // from class: mbk.1
            @Override // java.lang.Runnable
            public void run() {
                mbk.this.b();
                if (mbk.this.f != null) {
                    try {
                        mbk.this.h = true;
                        DvLiteBinder dvLiteBinder = mbk.this.f;
                        String str2 = str;
                        dvLiteBinder.connectDevice(str2, mbk.this.e(str2, dataReceiveListener));
                        return;
                    } catch (RemoteException unused) {
                        Log.e("PhdKit", "RemoteException occur");
                        return;
                    }
                }
                Log.e("PhdKit", "connectDevice mApiAidl is null");
            }
        });
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DataContentListener e(final String str, final DataReceiveListener dataReceiveListener) {
        return new DataContentListener.Stub() { // from class: com.huawei.phdkit.PhdKit$6
            @Override // com.huawei.phdkit.DataContentListener
            public void getResult(String str2, String str3) throws RemoteException {
                tos.b("PhdKit", "getResult: " + str3);
                mbk.this.d(str2, str3, dataReceiveListener);
            }

            @Override // com.huawei.phdkit.DataContentListener
            public void getStatus(DeviceData deviceData) throws RemoteException {
                if (deviceData == null) {
                    Log.w("PhdKit", "getStatus onDataChanged deviceData is null");
                    return;
                }
                tos.b("PhdKit", "getStatus udid: " + deviceData.getUdid());
                tos.a("PhdKit", "getStatus errorCode: " + deviceData.getErrorCode());
                if (deviceData.getErrorCode() == 0) {
                    mbk.this.d(str, dataReceiveListener);
                }
                Log.i("PhdKit", "connectDevice onDataChanged");
                dataReceiveListener.onDataChanged(deviceData);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, DataReceiveListener dataReceiveListener) {
        synchronized (d) {
            this.g.put(str, dataReceiveListener);
        }
    }

    private void e(int i, String str, List<mbn> list, DataReceiveListener dataReceiveListener) {
        if (list == null) {
            return;
        }
        DeviceData deviceData = new DeviceData();
        deviceData.setErrorCode(0);
        deviceData.setStatus(1);
        deviceData.setUdid(str);
        for (mbn mbnVar : list) {
            try {
                int parseInt = Integer.parseInt(mbnVar.d(), 16);
                if (parseInt == 1) {
                    deviceData.setErrorCode(0);
                    deviceData.setDataContents(mbg.a(mbnVar.c()));
                    deviceData.setLinkType(i);
                } else if (parseInt == 127) {
                    deviceData.setErrorCode(-2);
                    deviceData.setDataContents(mbg.a(mbnVar.c()));
                } else {
                    Log.w("PhdKit", "Invalid control types");
                }
            } catch (NumberFormatException unused) {
                Log.e("PhdKit", "musicOperation NumberFormatException");
            }
        }
        try {
            tos.a("PhdKit", "DataListener onDataChanged");
            dataReceiveListener.onDataChanged(deviceData);
        } catch (RemoteException e2) {
            Log.e("PhdKit", "sendDataCallback remoteException:" + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, String str2, DataReceiveListener dataReceiveListener) {
        if (str == null || str2 == null || dataReceiveListener == null) {
            Log.w("PhdKit", "inputer parameters is invaild.");
            return;
        }
        byte[] a2 = mbg.a(str2);
        if (str2.length() >= 4) {
            try {
                List<mbn> c2 = new mbr().a(str2.substring(4, str2.length())).c();
                byte b2 = a2[1];
                if (b2 == 1 || b2 == 2) {
                    e(b2, str, c2, dataReceiveListener);
                } else {
                    Log.i("PhdKit", "other commandID getResult().");
                }
                return;
            } catch (IndexOutOfBoundsException unused) {
                Log.e("PhdKit", "IndexOutOfBoundsException");
                return;
            } catch (mbl unused2) {
                Log.e("PhdKit", "TlvException");
                return;
            }
        }
        Log.e("PhdKit", "data lenth less four.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (str == null) {
            Log.i("PhdKit", "sendData uuid is null.");
            return;
        }
        DeviceData deviceData = new DeviceData();
        deviceData.setErrorCode(-2);
        deviceData.setStatus(0);
        deviceData.setUdid(str);
        synchronized (d) {
            try {
                DataReceiveListener dataReceiveListener = this.g.get(str);
                if (dataReceiveListener != null) {
                    Log.i("PhdKit", "sendErrorInfo onDataChanged");
                    dataReceiveListener.onDataChanged(deviceData);
                } else {
                    Log.i("PhdKit", "sendData uuid is invalid.");
                }
            } catch (RemoteException unused) {
                Log.e("PhdKit", "RemoteException occur");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(byte[] bArr, String str) {
        if (this.f == null || bArr == null) {
            Log.e("PhdKit", "sendData mApiAidl parameter is invalid.");
            return true;
        }
        if (bArr.length != 0 && bArr.length <= 2048) {
            return false;
        }
        a(str);
        Log.e("PhdKit", "data is invalid.");
        return true;
    }

    private void a(Future<Integer> future, String str) {
        try {
            if (future == null) {
                tos.e("PhdKit", str + " future is null");
                throw new IllegalStateException(String.valueOf(12));
            }
            int intValue = future.get(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS).intValue();
            if (intValue != 0 && intValue != -1) {
                throw new IllegalStateException(String.valueOf(intValue));
            }
        } catch (InterruptedException unused) {
            tos.e("PhdKit", str + " InterruptedException");
            throw new IllegalStateException(String.valueOf(12));
        } catch (ExecutionException unused2) {
            tos.e("PhdKit", str + " ExecutionException");
            throw new IllegalStateException(String.valueOf(12));
        } catch (TimeoutException unused3) {
            tos.e("PhdKit", str + " TimeoutException");
            throw new IllegalStateException(String.valueOf(12));
        }
    }
}
