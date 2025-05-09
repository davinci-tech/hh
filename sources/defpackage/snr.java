package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.devicesdk.callback.CompatibleFitterCallback;
import com.huawei.devicesdk.callback.ConnectFilter;
import com.huawei.devicesdk.callback.DeviceCompatibleCallback;
import com.huawei.devicesdk.callback.DeviceHandshakeCallback;
import com.huawei.devicesdk.callback.DeviceScanCallback;
import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.callback.FrameReceiver;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.callback.ScanCallback;
import com.huawei.devicesdk.callback.StatusCallback;
import com.huawei.devicesdk.entity.CommandMessageParcel;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.DeviceDataFrameParcel;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ScanFilterParcel;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.devicesdk.entity.SendMode;
import com.huawei.devicesdk.service.DevicesManagementBinderInterface;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.store.SharedStoreManager;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.unitedevice.api.EngineChannelInterface;
import com.huawei.unitedevice.api.UniteChannelInterface;
import com.huawei.unitedevice.callback.IResultAIDLCallback;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.entity.CommonFileInfoParcel;
import com.huawei.unitedevice.hwcommonfilemgr.entity.FileInfo;
import com.huawei.unitedevice.hwcommonfilemgr.entity.RequestFileInfo;
import com.huawei.unitedevice.p2p.IdentityInfo;
import com.huawei.unitedevice.p2p.MessageParcel;
import com.huawei.unitedevice.p2p.P2pPingCallback;
import com.huawei.unitedevice.p2p.P2pReceiver;
import com.huawei.unitedevice.p2p.P2pSendCallback;
import com.huawei.unitedevice.p2p.ReceiverCallback;
import defpackage.spn;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class snr implements UniteChannelInterface, EngineChannelInterface {

    /* renamed from: a, reason: collision with root package name */
    private static final int f17145a;
    private static final int e;
    private Context b;
    private ConnectFilter c;
    private IBinder.DeathRecipient d;
    private DeviceStatusChangeCallback f;
    private final StatusCallback g;
    private final FrameReceiver h;
    private Map<String, DeviceCompatibleCallback> i;
    private volatile DevicesManagementBinderInterface j;
    private final Object k;
    private ExecutorService l;
    private boolean m;
    private ExecutorService n;
    private boolean o;
    private ServiceConnection p;
    private MessageReceiveCallback q;
    private String r;
    private String t;

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        f17145a = availableProcessors;
        e = availableProcessors + 1;
    }

    private snr() {
        this.k = new Object();
        this.h = new FrameReceiver.Stub() { // from class: snr.2
            @Override // com.huawei.devicesdk.callback.FrameReceiver
            public void onFrameReceived(int i, UniteDevice uniteDevice, DeviceDataFrameParcel deviceDataFrameParcel) {
                if (uniteDevice != null && snr.this.q != null) {
                    biu biuVar = new biu();
                    if (deviceDataFrameParcel != null) {
                        biuVar.a(deviceDataFrameParcel.getCharacteristicId());
                        biuVar.d(deviceDataFrameParcel.getData());
                    } else {
                        LogUtil.e("UniteDeviceServiceProxy", "DeviceDataFrame is null");
                    }
                    byte[] a2 = biuVar.a();
                    if (a2 != null && a2.length > 1) {
                        byte b2 = a2[0];
                        byte b3 = a2[1];
                        if (b2 == 27 || b2 == 39) {
                            ReleaseLogUtil.b("DEVMGR_UniteDeviceServiceProxy", "response sid: ", Integer.valueOf(b2), " cid: ", Integer.valueOf(b3));
                        }
                    }
                    snr.this.q.onDataReceived(snr.this.e(uniteDevice), biuVar, i);
                    return;
                }
                ReleaseLogUtil.c("DEVMGR_UniteDeviceServiceProxy", "Device is null and messageReceiveCallbacks is null");
            }

            @Override // com.huawei.devicesdk.callback.FrameReceiver
            public void onChannelResult(int i, UniteDevice uniteDevice, String str) {
                LogUtil.c("UniteDeviceServiceProxy", "onChannelResult :", Integer.valueOf(i));
                if (uniteDevice != null && snr.this.q != null) {
                    snr.this.q.onChannelEnable(snr.this.e(uniteDevice), str, i);
                } else {
                    LogUtil.e("UniteDeviceServiceProxy", "Device is null");
                }
            }
        };
        this.g = new StatusCallback.Stub() { // from class: snr.11
            @Override // com.huawei.devicesdk.callback.StatusCallback
            public void onStatusChanged(int i, UniteDevice uniteDevice, int i2) {
                LogUtil.c("UniteDeviceServiceProxy", "onConnectStatusChanged :", Integer.valueOf(i2), " errorCode:", Integer.valueOf(i));
                if (uniteDevice != null && snr.this.f != null) {
                    snr.this.f.onConnectStatusChanged(snr.this.e(uniteDevice), i2, i);
                } else {
                    LogUtil.e("UniteDeviceServiceProxy", "Device is null");
                }
            }
        };
        this.f = null;
        this.q = null;
        this.c = null;
        this.j = null;
        this.i = new ConcurrentHashMap();
        this.t = "hw.unitedevice.";
        this.r = "UniteDeviceManagement";
        this.m = false;
        this.o = false;
        this.l = new ThreadPoolExecutor(e, 50, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.n = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        this.d = new IBinder.DeathRecipient() { // from class: snr.21
            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                snr.this.j = null;
            }
        };
        this.p = new ServiceConnection() { // from class: snr.25
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                synchronized (snr.this.k) {
                    ReleaseLogUtil.b("DEVMGR_UniteDeviceServiceProxy", "onServiceConnected success!");
                    snr.this.m = true;
                    snr.this.j = DevicesManagementBinderInterface.Stub.asInterface(iBinder);
                    DevicesManagementBinderInterface devicesManagementBinderInterface = snr.this.j;
                    if (devicesManagementBinderInterface != null) {
                        LogUtil.c("UniteDeviceServiceProxy", "init enter registerDeviceStateListener");
                        try {
                            devicesManagementBinderInterface.registerDeviceStatusListener(snr.this.g);
                        } catch (RemoteException unused) {
                            LogUtil.e("UniteDeviceServiceProxy", "registerDeviceStatusListener RemoteException!");
                        }
                        try {
                            devicesManagementBinderInterface.registerDeviceFrameListener(snr.this.h);
                        } catch (RemoteException unused2) {
                            LogUtil.e("UniteDeviceServiceProxy", "registerDeviceFrameListener RemoteException!");
                        }
                        b();
                        try {
                            IBinder asBinder = devicesManagementBinderInterface.asBinder();
                            if (asBinder != null) {
                                asBinder.linkToDeath(snr.this.d, 0);
                            }
                        } catch (RemoteException unused3) {
                            LogUtil.e("UniteDeviceServiceProxy", "linkToDeath RemoteException!");
                        }
                    }
                }
            }

            private void b() {
                try {
                    if (snr.this.o && snr.this.c != null) {
                        LogUtil.c("UniteDeviceServiceProxy", "handleDeviceHandShake registerHandshakeFilter");
                        snr.this.c();
                    }
                    CompatibleFitterCallback.Stub stub = new CompatibleFitterCallback.Stub() { // from class: snr.25.2
                        @Override // com.huawei.devicesdk.callback.CompatibleFitterCallback
                        public void adapterOperate(List<String> list) {
                            Iterator it = snr.this.i.values().iterator();
                            while (it.hasNext()) {
                                ((DeviceCompatibleCallback) it.next()).adapterOperate(list);
                            }
                        }
                    };
                    DevicesManagementBinderInterface devicesManagementBinderInterface = snr.this.j;
                    if (devicesManagementBinderInterface != null) {
                        devicesManagementBinderInterface.initUniteService(stub);
                    }
                } catch (RemoteException unused) {
                    LogUtil.e("UniteDeviceServiceProxy", "initUniteService RemoteException!");
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                LogUtil.c("UniteDeviceServiceProxy", "onServiceDisconnected success!");
                snr.this.m = false;
                snr.this.j = null;
            }
        };
    }

    public static snr a() {
        return b.b;
    }

    public void e(Context context) {
        if (context == null) {
            LogUtil.e("UniteDeviceServiceProxy", "context is null");
            context = BaseApplication.e();
        }
        this.b = context;
        a(context);
    }

    private void a(final Context context) {
        this.l.execute(new Runnable() { // from class: snr.28
            @Override // java.lang.Runnable
            public void run() {
                snr.this.b(context, false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, boolean z) {
        if (this.j != null) {
            return;
        }
        if (context == null) {
            LogUtil.e("UniteDeviceServiceProxy", "context is null");
            context = BaseApplication.e();
        }
        if (this.b == null) {
            this.b = context;
        }
        d();
        if (!z && !bml.c() && !bio.b()) {
            LogUtil.e("UniteDeviceServiceProxy", "Have no bounded devices");
            return;
        }
        context.bindService(ejx_(), this.p, 1);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.l.execute(new Runnable() { // from class: snr.27
            @Override // java.lang.Runnable
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    if (snr.this.j != null) {
                        LogUtil.c("UniteDeviceServiceProxy", " devicesManagementBinder not null in: " + i);
                        countDownLatch.countDown();
                        return;
                    }
                    try {
                        Thread.sleep(5L);
                    } catch (InterruptedException unused) {
                        LogUtil.e("UniteDeviceServiceProxy", " bindService InterruptedException");
                        countDownLatch.countDown();
                    }
                }
            }
        });
        try {
            countDownLatch.await(5L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            LogUtil.e("UniteDeviceServiceProxy", " countDownLatch InterruptedException");
        }
        LogUtil.c("UniteDeviceServiceProxy", "bindService end");
    }

    private void d() {
        if ("ok".equals(SharedStoreManager.zZ_("keyvaldb_encrypt_udsdevice").getString("sync_data", ""))) {
            return;
        }
        LogUtil.c("UniteDeviceServiceProxy", "startDaemonService");
        Intent intent = new Intent();
        intent.setAction("com.huawei.daemonservice");
        intent.setPackage(this.b.getPackageName());
        intent.putExtra("startSyncData", "startSyncData");
        try {
            this.b.startService(intent);
        } catch (IllegalStateException | SecurityException unused) {
            LogUtil.a("UniteDeviceServiceProxy", "start daemon service fail");
        }
    }

    private Intent ejx_() {
        Intent intent = new Intent();
        String packageName = this.b.getPackageName();
        intent.setComponent(new ComponentName(packageName, "com.huawei.devicesdk.service.DevicesManagementService"));
        intent.setPackage(packageName);
        return intent;
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void scanDevice(final ScanMode scanMode, final List<bjf> list, final DeviceScanCallback deviceScanCallback) {
        LogUtil.c("UniteDeviceServiceProxy", "enter scanDevice");
        if (scanMode == null || list == null || deviceScanCallback == null) {
            LogUtil.e("UniteDeviceServiceProxy", "ScanMode , filters or DeviceScanCallback is null");
        } else {
            this.l.execute(new Runnable() { // from class: snr.29
                @Override // java.lang.Runnable
                public void run() {
                    snr snrVar = snr.this;
                    snrVar.b(snrVar.b, true);
                    if (snr.this.j != null) {
                        try {
                            snr.this.j.scanDevice(scanMode.value(), snr.b((List<bjf>) list), new ScanCallback.Stub() { // from class: snr.29.5
                                @Override // com.huawei.devicesdk.callback.ScanCallback
                                public void onScanResult(int i, UniteDevice uniteDevice, byte[] bArr, String str) {
                                    deviceScanCallback.scanResult(uniteDevice, bArr, str, i);
                                }
                            });
                        } catch (RemoteException unused) {
                            LogUtil.e("UniteDeviceServiceProxy", "scanDevice RemoteException!");
                        }
                    }
                }
            });
        }
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void stopScanDevice() {
        LogUtil.c("UniteDeviceServiceProxy", "enter stopScanDevice");
        this.l.execute(new Runnable() { // from class: snr.30
            @Override // java.lang.Runnable
            public void run() {
                snr snrVar = snr.this;
                snrVar.b(snrVar.b, false);
                if (snr.this.j != null) {
                    try {
                        snr.this.j.stopScanDevice();
                    } catch (RemoteException unused) {
                        LogUtil.e("UniteDeviceServiceProxy", "scanDevice RemoteException!");
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<ScanFilterParcel> b(List<bjf> list) {
        ArrayList arrayList = new ArrayList(16);
        if (list != null && list.size() != 0) {
            for (bjf bjfVar : list) {
                if (bjfVar != null) {
                    ScanFilterParcel scanFilterParcel = new ScanFilterParcel();
                    scanFilterParcel.setType(bjfVar.e());
                    scanFilterParcel.setMatcher(bjfVar.d());
                    arrayList.add(scanFilterParcel);
                }
            }
        }
        return arrayList;
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void connectDevice(UniteDevice uniteDevice, boolean z, ConnectMode connectMode) {
        connectDevice(uniteDevice, z, connectMode, null);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void connectDevice(final UniteDevice uniteDevice, final boolean z, final ConnectMode connectMode, final ConnectFilter connectFilter) {
        LogUtil.c("UniteDeviceServiceProxy", "enter connectDevice");
        if (uniteDevice == null) {
            LogUtil.e("UniteDeviceServiceProxy", "Device is null");
        } else if (connectMode == null) {
            LogUtil.e("UniteDeviceServiceProxy", "connectMode is null");
        } else {
            this.l.execute(new Runnable() { // from class: snr.26
                @Override // java.lang.Runnable
                public void run() {
                    if (connectMode == ConnectMode.SIMPLE) {
                        LogUtil.c("UniteDeviceServiceProxy", "connectDevice mode is simple.");
                        snr snrVar = snr.this;
                        snrVar.b(snrVar.b, true);
                    } else {
                        snr snrVar2 = snr.this;
                        snrVar2.b(snrVar2.b, false);
                    }
                    if (snr.this.j != null) {
                        synchronized (snr.this.k) {
                            try {
                                snr.this.j.connectDevice(uniteDevice, z, connectMode.value(), connectFilter != null ? new DeviceHandshakeCallback.Stub() { // from class: snr.26.1
                                    @Override // com.huawei.devicesdk.callback.DeviceHandshakeCallback
                                    public int onProcess(UniteDevice uniteDevice2, String str, CommandMessageParcel commandMessageParcel) {
                                        bir e2 = snr.this.e(commandMessageParcel);
                                        int onFilter = connectFilter.onFilter(uniteDevice2, str, e2);
                                        snr.this.e(commandMessageParcel, e2);
                                        return onFilter;
                                    }

                                    @Override // com.huawei.devicesdk.callback.DeviceHandshakeCallback
                                    public String preProcess(UniteDevice uniteDevice2, String str) {
                                        return connectFilter.preProcess(uniteDevice2, str);
                                    }
                                } : null);
                            } catch (RemoteException unused) {
                                LogUtil.e("UniteDeviceServiceProxy", "connectDevice RemoteException!");
                            }
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(CommandMessageParcel commandMessageParcel, bir birVar) {
        if (commandMessageParcel == null || birVar == null) {
            return;
        }
        commandMessageParcel.setCharacteristicUuid(birVar.b());
        if (birVar.g() != null) {
            commandMessageParcel.setCommandType(birVar.g().value());
        }
        commandMessageParcel.setCommand(birVar.e());
        commandMessageParcel.setServiceUuid(birVar.j());
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void createSystemBond(final UniteDevice uniteDevice) {
        LogUtil.c("UniteDeviceServiceProxy", "enter createSystemBond");
        if (uniteDevice == null) {
            LogUtil.e("UniteDeviceServiceProxy", "Device is null");
        } else {
            this.l.execute(new Runnable() { // from class: snr.4
                @Override // java.lang.Runnable
                public void run() {
                    snr snrVar = snr.this;
                    snrVar.b(snrVar.b, false);
                    if (snr.this.j != null) {
                        try {
                            snr.this.j.pairDevice(uniteDevice, ConnectMode.TRANSPARENT.value());
                        } catch (RemoteException unused) {
                            LogUtil.e("UniteDeviceServiceProxy", "pairDevice RemoteException!");
                        }
                    }
                }
            });
        }
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void sendCommand(final UniteDevice uniteDevice, final bir birVar) {
        LogUtil.c("UniteDeviceServiceProxy", "enter sendCommand");
        if (uniteDevice == null) {
            LogUtil.e("UniteDeviceServiceProxy", "Device is null");
        } else if (birVar == null) {
            LogUtil.e("UniteDeviceServiceProxy", "CommandMessage is null");
        } else {
            this.n.submit(new Runnable() { // from class: snr.5
                @Override // java.lang.Runnable
                public void run() {
                    snr snrVar = snr.this;
                    snrVar.b(snrVar.b, false);
                    if (snr.this.j != null) {
                        try {
                            snr.this.j.sendCommand(uniteDevice, snr.c(birVar));
                        } catch (RemoteException unused) {
                            LogUtil.e("UniteDeviceServiceProxy", "sendCommand RemoteException!");
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CommandMessageParcel c(bir birVar) {
        if (birVar == null) {
            return null;
        }
        CommandMessageParcel commandMessageParcel = new CommandMessageParcel();
        if (birVar.g() != null) {
            commandMessageParcel.setCommandType(birVar.g().value());
        }
        commandMessageParcel.setServiceUuid(birVar.j());
        commandMessageParcel.setCharacteristicUuid(birVar.b());
        commandMessageParcel.setCommand(birVar.e());
        commandMessageParcel.setPriorityType(birVar.h());
        commandMessageParcel.setNeedEncrypt(birVar.k());
        commandMessageParcel.setSocketChannel(birVar.i());
        if (birVar.c() == null) {
            return commandMessageParcel;
        }
        commandMessageParcel.setOptionsType(birVar.c().value());
        return commandMessageParcel;
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public Map<String, UniteDevice> getDeviceList() {
        HashMap hashMap = new HashMap(10);
        a(this.b);
        if (this.j != null) {
            try {
                List<UniteDevice> deviceList = this.j.getDeviceList();
                if (deviceList == null) {
                    return hashMap;
                }
                for (UniteDevice uniteDevice : deviceList) {
                    if (uniteDevice == null) {
                        LogUtil.e("UniteDeviceServiceProxy", "Device is null");
                    } else {
                        hashMap.put(uniteDevice.getIdentify(), uniteDevice);
                    }
                }
            } catch (RemoteException unused) {
                LogUtil.e("UniteDeviceServiceProxy", "getDeviceList RemoteException!");
            }
        } else {
            LogUtil.e("UniteDeviceServiceProxy", "devicesManagementBinder is null");
        }
        return hashMap;
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void unPairDevice(final UniteDevice uniteDevice) {
        LogUtil.c("UniteDeviceServiceProxy", "enter unPairDevice");
        if (uniteDevice == null) {
            LogUtil.e("UniteDeviceServiceProxy", "Device is null");
        } else {
            this.l.execute(new Runnable() { // from class: snr.3
                @Override // java.lang.Runnable
                public void run() {
                    snr snrVar = snr.this;
                    snrVar.b(snrVar.b, false);
                    if (snr.this.j != null) {
                        try {
                            snr.this.j.unpairDevice(uniteDevice);
                            LogUtil.c("UniteDeviceServiceProxy", "unpairDevice success");
                        } catch (RemoteException unused) {
                            LogUtil.e("UniteDeviceServiceProxy", "unpairDevice RemoteException!");
                        }
                    }
                }
            });
        }
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void disconnect(final UniteDevice uniteDevice) {
        LogUtil.c("UniteDeviceServiceProxy", "enter disconnect");
        if (uniteDevice == null) {
            LogUtil.e("UniteDeviceServiceProxy", "Device is null");
        } else {
            this.l.execute(new Runnable() { // from class: snr.1
                @Override // java.lang.Runnable
                public void run() {
                    snr snrVar = snr.this;
                    snrVar.b(snrVar.b, false);
                    if (snr.this.j != null) {
                        try {
                            snr.this.j.disconnectDevice(uniteDevice);
                            LogUtil.c("UniteDeviceServiceProxy", "disconnectDevice success");
                        } catch (RemoteException unused) {
                            LogUtil.e("UniteDeviceServiceProxy", "disconnectDevice RemoteException!");
                        }
                    }
                }
            });
        }
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public boolean isSupportService(UniteDevice uniteDevice, String str) {
        LogUtil.c("UniteDeviceServiceProxy", "enter isSupportService");
        if (uniteDevice == null) {
            LogUtil.e("UniteDeviceServiceProxy", "Device is null");
            return false;
        }
        if (str == null) {
            LogUtil.e("UniteDeviceServiceProxy", "serviceUuid is null");
            return false;
        }
        a(this.b);
        if (this.j == null) {
            return false;
        }
        try {
            return this.j.isSupportService(uniteDevice, str);
        } catch (RemoteException unused) {
            LogUtil.e("UniteDeviceServiceProxy", "isSupportService RemoteException!");
            return false;
        }
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public boolean isSupportCharactor(UniteDevice uniteDevice, String str, String str2) {
        LogUtil.c("UniteDeviceServiceProxy", "enter isSupportCharactor");
        if (uniteDevice == null) {
            LogUtil.e("UniteDeviceServiceProxy", "Device is null");
            return false;
        }
        if (str == null) {
            LogUtil.e("UniteDeviceServiceProxy", "serviceUuid is null");
            return false;
        }
        if (str2 == null) {
            LogUtil.e("UniteDeviceServiceProxy", "charactorUuid is null");
            return false;
        }
        a(this.b);
        if (this.j == null) {
            return false;
        }
        try {
            return this.j.isSupportCharacteristic(uniteDevice, str, str2);
        } catch (RemoteException unused) {
            LogUtil.e("UniteDeviceServiceProxy", "isSupportService RemoteException!");
            return false;
        }
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void registerDeviceStateListener(String str, DeviceStatusChangeCallback deviceStatusChangeCallback) {
        LogUtil.c("UniteDeviceServiceProxy", "enter registerDeviceStateListener");
        if (str == null) {
            LogUtil.e("UniteDeviceServiceProxy", "listenerId is null");
        } else if (deviceStatusChangeCallback == null) {
            LogUtil.e("UniteDeviceServiceProxy", "DeviceStatusChangeCallback is null");
        } else {
            this.f = deviceStatusChangeCallback;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DeviceInfo e(UniteDevice uniteDevice) {
        return uniteDevice.getDeviceInfo();
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void registerDeviceMessageListener(String str, MessageReceiveCallback messageReceiveCallback) {
        LogUtil.c("UniteDeviceServiceProxy", "enter registerDeviceMessageListener");
        if (str == null) {
            LogUtil.e("UniteDeviceServiceProxy", "listenerId is null");
        } else if (messageReceiveCallback == null) {
            LogUtil.e("UniteDeviceServiceProxy", "MessageReceiveCallback is null");
        } else {
            this.q = messageReceiveCallback;
        }
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void unregisterDeviceStateListener(String str) {
        LogUtil.c("UniteDeviceServiceProxy", "enter unregisterDeviceStateListener");
        if (str == null) {
            LogUtil.e("UniteDeviceServiceProxy", "listenerId is null");
        } else {
            this.f = null;
        }
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void unregisterDeviceMessageListener(String str) {
        LogUtil.c("UniteDeviceServiceProxy", "enter unregisterDeviceMessageListener");
        if (str == null) {
            LogUtil.e("UniteDeviceServiceProxy", "listenerId is null");
        } else {
            this.q = null;
        }
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void registerHandshakeFilter(ConnectFilter connectFilter) {
        LogUtil.c("UniteDeviceServiceProxy", "enter registerHandshakeFilter");
        if (connectFilter == null) {
            LogUtil.a("UniteDeviceServiceProxy", "registerHandshakeFilter filter is null");
        } else {
            this.c = connectFilter;
            this.l.execute(new Runnable() { // from class: snr.9
                @Override // java.lang.Runnable
                public void run() {
                    snr snrVar = snr.this;
                    snrVar.b(snrVar.b, false);
                    if (snr.this.j == null) {
                        snr.this.o = true;
                        LogUtil.e("UniteDeviceServiceProxy", "registerHandshakeFilter time out");
                    } else {
                        snr.this.c();
                        try {
                            snr.this.j.initUniteService(null);
                        } catch (RemoteException unused) {
                            LogUtil.e("UniteDeviceServiceProxy", "initUniteService RemoteException!");
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        DeviceHandshakeCallback.Stub stub = new DeviceHandshakeCallback.Stub() { // from class: snr.6
            @Override // com.huawei.devicesdk.callback.DeviceHandshakeCallback
            public int onProcess(UniteDevice uniteDevice, String str, CommandMessageParcel commandMessageParcel) {
                bir e2 = snr.this.e(commandMessageParcel);
                int onFilter = snr.this.c != null ? snr.this.c.onFilter(uniteDevice, str, e2) : -1;
                snr.this.e(commandMessageParcel, e2);
                return onFilter;
            }

            @Override // com.huawei.devicesdk.callback.DeviceHandshakeCallback
            public String preProcess(UniteDevice uniteDevice, String str) {
                return snr.this.c != null ? snr.this.c.preProcess(uniteDevice, str) : "";
            }
        };
        if (this.j == null) {
            LogUtil.e("UniteDeviceServiceProxy", "registerHandshakeCallback devicesManagementBinder is null");
            return;
        }
        try {
            this.j.registerHandshakeCallback(stub);
        } catch (RemoteException unused) {
            LogUtil.e("UniteDeviceServiceProxy", "registerHandshakeCallback RemoteException!");
        }
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void setCharacteristicNotify(final UniteDevice uniteDevice, final String str, final String str2, final SendMode sendMode, final boolean z) {
        LogUtil.c("UniteDeviceServiceProxy", "enter setCharacteristicNotify");
        if (uniteDevice == null || str == null || str2 == null) {
            LogUtil.e("UniteDeviceServiceProxy", "Device,serviceId or characteristicId is null");
        } else {
            this.l.execute(new Runnable() { // from class: snr.8
                @Override // java.lang.Runnable
                public void run() {
                    snr snrVar = snr.this;
                    snrVar.b(snrVar.b, false);
                    if (snr.this.j != null) {
                        try {
                            snr.this.j.setCharacteristicNotify(uniteDevice, str, str2, sendMode.value(), z);
                        } catch (RemoteException unused) {
                            LogUtil.e("UniteDeviceServiceProxy", "setCharacteristicNotify RemoteException!");
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public bir e(CommandMessageParcel commandMessageParcel) {
        bir birVar = new bir();
        if (commandMessageParcel != null) {
            birVar.c(commandMessageParcel.getServiceUuid());
            birVar.b(commandMessageParcel.getCharacteristicUuid());
            birVar.e(commandMessageParcel.getCommand());
            birVar.b(SendMode.getValue(commandMessageParcel.getCommandType()));
        }
        return birVar;
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void p2pSendForWearEngine(final Context context, final UniteDevice uniteDevice, final snt sntVar, final SendCallback sendCallback) {
        this.n.submit(new Runnable() { // from class: snr.10
            @Override // java.lang.Runnable
            public void run() {
                snr.this.b(context, false);
                if (snr.this.j != null) {
                    try {
                        LogUtil.c("UniteDeviceServiceProxy", "enter p2pSendForWearEngine");
                        P2pSendCallback.Stub d = snr.this.d(sendCallback);
                        if (TextUtils.isEmpty(sntVar.i())) {
                            LogUtil.c("UniteDeviceServiceProxy", "enter btproxy/medta TransferFile");
                            snr.this.j.send(uniteDevice, sph.e(sntVar.f()), new IdentityInfo(snr.this.t + sntVar.a().getValue(), snr.this.r), new IdentityInfo(sntVar.m(), sntVar.h()), d);
                        } else {
                            LogUtil.c("UniteDeviceServiceProxy", "enter wearengine transferfile.");
                            snr.this.j.send(uniteDevice, snr.this.a(sntVar), new IdentityInfo(sntVar.i(), sntVar.j()), new IdentityInfo(sntVar.m(), sntVar.h()), d);
                        }
                    } catch (RemoteException unused) {
                        LogUtil.e("UniteDeviceServiceProxy", "setCharacteristicNotify RemoteException!");
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public P2pSendCallback.Stub d(final SendCallback sendCallback) {
        if (sendCallback != null) {
            return new P2pSendCallback.Stub() { // from class: snr.7
                @Override // com.huawei.unitedevice.p2p.P2pSendCallback
                public void onSendResult(int i) throws RemoteException {
                    LogUtil.c("UniteDeviceServiceProxy", "p2pSendForWearEngine enter onSendResult:", Integer.valueOf(i));
                    sendCallback.onSendResult(i);
                }

                @Override // com.huawei.unitedevice.p2p.P2pSendCallback
                public void onSendProgress(long j) throws RemoteException {
                    LogUtil.c("UniteDeviceServiceProxy", "enter onSendProgress");
                    sendCallback.onSendProgress(j);
                }

                @Override // com.huawei.unitedevice.p2p.P2pSendCallback
                public void onFileTransferReport(String str) {
                    LogUtil.c("UniteDeviceServiceProxy", "getP2pSendCallbackStub onFileTransferReport transferWay:", str);
                    sendCallback.onFileTransferReport(str);
                }
            };
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MessageParcel a(snt sntVar) {
        if (sntVar == null) {
            return null;
        }
        MessageParcel messageParcel = new MessageParcel();
        messageParcel.setType(2);
        messageParcel.setParcelFileDescriptor(sntVar.ejD_());
        messageParcel.setFileName(sntVar.d());
        messageParcel.setDescription(sntVar.e());
        messageParcel.setFileSha256(sntVar.g());
        messageParcel.setFilePath(sntVar.c());
        return messageParcel;
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void startTransferFileToWear(final sol solVar, final IResultAIDLCallback iResultAIDLCallback) {
        this.n.submit(new Runnable() { // from class: snr.15
            @Override // java.lang.Runnable
            public void run() {
                snr snrVar = snr.this;
                snrVar.b(snrVar.b, false);
                if (snr.this.j != null) {
                    try {
                        LogUtil.c("UniteDeviceServiceProxy", "enter startTransferFile");
                        FileInfo fileInfo = new FileInfo();
                        fileInfo.setFileName(solVar.m());
                        fileInfo.setFileType(solVar.u());
                        fileInfo.setFilePath(solVar.s());
                        fileInfo.setSourceId(solVar.af());
                        fileInfo.setPackageName(solVar.ac());
                        fileInfo.setAttentionTypes(solVar.a());
                        fileInfo.setRequestType(solVar.ab());
                        if (solVar.i() != null) {
                            fileInfo.setIdentify(solVar.i().getIdentify());
                        }
                        fileInfo.setFileDescriptor(solVar.ejT_());
                        fileInfo.setFileSize(solVar.v());
                        snr.this.j.startTransferFile(fileInfo, iResultAIDLCallback);
                    } catch (RemoteException unused) {
                        LogUtil.e("UniteDeviceServiceProxy", "setCharacteristicNotify RemoteException!");
                    }
                }
            }
        });
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void registerReceiver(final Context context, final snt sntVar, final P2pReceiver p2pReceiver, final SendCallback sendCallback) {
        this.n.submit(new Runnable() { // from class: snr.12
            @Override // java.lang.Runnable
            public void run() {
                snr.this.b(context, false);
                if (snr.this.j != null) {
                    try {
                        String str = snr.this.t + sntVar.a().getValue();
                        IdentityInfo identityInfo = new IdentityInfo(str, snr.this.r);
                        IdentityInfo identityInfo2 = new IdentityInfo(sntVar.m(), sntVar.h());
                        String b2 = ProcessUtil.b();
                        LogUtil.c("UniteDeviceServiceProxy", "enter registerReceiver srcPkgName：", str, ",destPkgName: ", sntVar.m(), ",processName: ", b2);
                        snr.this.j.subscribeDeviceDataReceiver(identityInfo, identityInfo2, snr.this.b(p2pReceiver), b2, snr.this.e(sendCallback));
                    } catch (RemoteException unused) {
                        LogUtil.e("UniteDeviceServiceProxy", "setCharacteristicNotify RemoteException!");
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public P2pSendCallback.Stub e(final SendCallback sendCallback) {
        return new P2pSendCallback.Stub() { // from class: snr.14
            @Override // com.huawei.unitedevice.p2p.P2pSendCallback
            public void onSendResult(int i) throws RemoteException {
                LogUtil.c("UniteDeviceServiceProxy", "registerReceiver enter onSendResult:", Integer.valueOf(i));
                sendCallback.onSendResult(i);
            }

            @Override // com.huawei.unitedevice.p2p.P2pSendCallback
            public void onSendProgress(long j) throws RemoteException {
                LogUtil.c("UniteDeviceServiceProxy", "enter onSendProgress:", Long.valueOf(j));
                sendCallback.onSendProgress(j);
            }

            @Override // com.huawei.unitedevice.p2p.P2pSendCallback
            public void onFileTransferReport(String str) {
                LogUtil.c("UniteDeviceServiceProxy", "enter onFileTransferReport:", str);
                sendCallback.onFileTransferReport(str);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ReceiverCallback.Stub b(final P2pReceiver p2pReceiver) {
        return new ReceiverCallback.Stub() { // from class: snr.13
            @Override // com.huawei.unitedevice.p2p.ReceiverCallback
            public void onReceiveMessage(DeviceInfo deviceInfo, MessageParcel messageParcel) throws RemoteException {
                spn.b bVar = new spn.b();
                if (messageParcel != null) {
                    if (messageParcel.getType() == 2) {
                        LogUtil.e("UniteDeviceServiceProxy", "registerReceiver P2pMessage type is error");
                    } else {
                        bVar.c(messageParcel.getData());
                    }
                }
                p2pReceiver.onReceiveMessage(deviceInfo, bVar.e());
            }
        };
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void unregisterReceiver(final Context context, final P2pReceiver p2pReceiver, final snt sntVar) {
        this.n.submit(new Runnable() { // from class: snr.20
            @Override // java.lang.Runnable
            public void run() {
                snr.this.b(context, false);
                if (snr.this.j != null) {
                    try {
                        final spn spnVar = null;
                        ReceiverCallback.Stub stub = new ReceiverCallback.Stub() { // from class: snr.20.4
                            @Override // com.huawei.unitedevice.p2p.ReceiverCallback
                            public void onReceiveMessage(DeviceInfo deviceInfo, MessageParcel messageParcel) throws RemoteException {
                                p2pReceiver.onReceiveMessage(deviceInfo, spnVar);
                            }
                        };
                        String str = snr.this.t + sntVar.a().getValue();
                        String m = sntVar.m();
                        String b2 = ProcessUtil.b();
                        LogUtil.c("UniteDeviceServiceProxy", "enter unregisterReceiver srcPkgName：", str, ",destPkgName: ", m, ",processName: ", b2);
                        snr.this.j.unsubscribeDeviceDataReceiver(stub, b2, str, m);
                    } catch (RemoteException unused) {
                        LogUtil.e("UniteDeviceServiceProxy", "setCharacteristicNotify RemoteException!");
                    }
                }
            }
        });
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void pingSendForWearEngine(final Context context, final UniteDevice uniteDevice, final String str, final PingCallback pingCallback, final int i) {
        this.n.submit(new Runnable() { // from class: snr.18
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.c("UniteDeviceServiceProxy", "enter pingSendForWearEngine");
                snr.this.b(context, false);
                if (snr.this.j != null) {
                    try {
                        LogUtil.c("UniteDeviceServiceProxy", "enter ping");
                        P2pPingCallback.Stub stub = new P2pPingCallback.Stub() { // from class: snr.18.1
                            @Override // com.huawei.unitedevice.p2p.P2pPingCallback
                            public void onResult(int i2) throws RemoteException {
                                LogUtil.c("UniteDeviceServiceProxy", "enter pingSendForWearEngine onResult", Integer.valueOf(i2));
                                pingCallback.onPingResult(i2);
                            }
                        };
                        LogUtil.c("UniteDeviceServiceProxy", "enter ping:", "com.huawei.health");
                        snr.this.j.ping(uniteDevice, "com.huawei.health", str, stub, i);
                    } catch (RemoteException unused) {
                        LogUtil.e("UniteDeviceServiceProxy", "setCharacteristicNotify RemoteException!");
                    }
                }
            }
        });
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void startReceiveFileFromWear(final RequestFileInfo requestFileInfo, final ITransferFileCallback iTransferFileCallback) {
        this.n.submit(new Runnable() { // from class: snr.16
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.c("UniteDeviceServiceProxy", "enter startRequestFile");
                snr snrVar = snr.this;
                snrVar.b(snrVar.b, false);
                if (snr.this.j != null) {
                    try {
                        LogUtil.c("UniteDeviceServiceProxy", "enter startRequestFile1");
                        snr.this.j.startRequestFile(requestFileInfo, iTransferFileCallback);
                    } catch (RemoteException unused) {
                        LogUtil.e("UniteDeviceServiceProxy", "startRequestFile RemoteException!");
                    }
                }
            }
        });
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void stopReceiveFileFromWear(final RequestFileInfo requestFileInfo, final ITransferFileCallback iTransferFileCallback) {
        this.n.submit(new Runnable() { // from class: snr.17
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.c("UniteDeviceServiceProxy", "enter stopRequestFile");
                snr snrVar = snr.this;
                snrVar.b(snrVar.b, false);
                if (snr.this.j != null) {
                    try {
                        LogUtil.c("UniteDeviceServiceProxy", "enter stopRequestFile1");
                        snr.this.j.stopRequestFile(requestFileInfo, iTransferFileCallback);
                    } catch (RemoteException unused) {
                        LogUtil.e("UniteDeviceServiceProxy", "startRequestFile RemoteException!");
                    }
                }
            }
        });
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void stopTransferFileToWear(final sol solVar, final ITransferFileCallback iTransferFileCallback) {
        this.n.submit(new Runnable() { // from class: snr.19
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.c("UniteDeviceServiceProxy", "enter stopTransferByQueue");
                snr snrVar = snr.this;
                snrVar.b(snrVar.b, false);
                if (snr.this.j != null) {
                    CommonFileInfoParcel commonFileInfoParcel = new CommonFileInfoParcel();
                    commonFileInfoParcel.setFileName(solVar.m());
                    commonFileInfoParcel.setFileType(solVar.u());
                    commonFileInfoParcel.setSourcePackageName(solVar.ae());
                    commonFileInfoParcel.setSourceCertificate(solVar.ah());
                    commonFileInfoParcel.setDestinationPackageName(solVar.g());
                    commonFileInfoParcel.setDestinationCertificate(solVar.h());
                    commonFileInfoParcel.setFilePath(solVar.s());
                    commonFileInfoParcel.setDescription(solVar.j());
                    commonFileInfoParcel.setSha256Result(solVar.ai());
                    if (solVar.i() != null) {
                        commonFileInfoParcel.setIdentify(solVar.i().getIdentify());
                    }
                    try {
                        snr.this.j.stopTransferByQueue(commonFileInfoParcel, iTransferFileCallback);
                    } catch (RemoteException unused) {
                        LogUtil.e("UniteDeviceServiceProxy", "setCharacteristicNotify RemoteException!");
                    }
                }
            }
        });
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void registerDeviceCompatibleListener(String str, DeviceCompatibleCallback deviceCompatibleCallback) {
        this.i.put(str, deviceCompatibleCallback);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void unregisterDeviceCompatibleListener(String str) {
        this.i.remove(str);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void registerListener(final String str, final String str2, final DataReceiveCallback dataReceiveCallback) {
        this.n.submit(new Runnable() { // from class: snr.23
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.c("UniteDeviceServiceProxy", "enter registerListener");
                snr snrVar = snr.this;
                snrVar.b(snrVar.b, false);
                if (snr.this.j != null) {
                    try {
                        LogUtil.c("UniteDeviceServiceProxy", "enter receiverCallback");
                        FrameReceiver.Stub stub = new FrameReceiver.Stub() { // from class: snr.23.2
                            @Override // com.huawei.devicesdk.callback.FrameReceiver
                            public void onFrameReceived(int i, UniteDevice uniteDevice, DeviceDataFrameParcel deviceDataFrameParcel) {
                                LogUtil.c("UniteDeviceServiceProxy", "registerListener onFrameReceived enter");
                                if (deviceDataFrameParcel != null) {
                                    byte[] data = deviceDataFrameParcel.getData();
                                    if (uniteDevice != null) {
                                        spj.c().c(uniteDevice, data, dataReceiveCallback);
                                        return;
                                    } else {
                                        LogUtil.e("UniteDeviceServiceProxy", "device is null");
                                        return;
                                    }
                                }
                                LogUtil.e("UniteDeviceServiceProxy", "onFrameReceived is null");
                            }

                            @Override // com.huawei.devicesdk.callback.FrameReceiver
                            public void onChannelResult(int i, UniteDevice uniteDevice, String str3) {
                                LogUtil.c("UniteDeviceServiceProxy", "onChannelResult enter", Integer.valueOf(i));
                            }
                        };
                        LogUtil.c("UniteDeviceServiceProxy", "enter registerListener:", str, str2);
                        snr.this.j.registerListener(str, str2, stub);
                    } catch (RemoteException unused) {
                        LogUtil.e("UniteDeviceServiceProxy", "registerListener RemoteException!");
                    }
                }
            }
        });
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void unregisterListener(final String str, final String str2) {
        this.n.submit(new Runnable() { // from class: snr.22
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.c("UniteDeviceServiceProxy", "enter unregisterListener");
                snr snrVar = snr.this;
                snrVar.b(snrVar.b, false);
                if (snr.this.j != null) {
                    try {
                        LogUtil.c("UniteDeviceServiceProxy", "enter unregisterListener:", str, str2);
                        snr.this.j.unregisterListener(str, str2);
                    } catch (RemoteException unused) {
                        LogUtil.e("UniteDeviceServiceProxy", "unregisterListener RemoteException!");
                    }
                }
            }
        });
    }

    public void b() {
        ServiceConnection serviceConnection;
        LogUtil.c("UniteDeviceServiceProxy", "destory");
        Context context = this.b;
        if (context != null && (serviceConnection = this.p) != null && this.m) {
            try {
                context.unbindService(serviceConnection);
            } catch (Exception unused) {
                LogUtil.e("UniteDeviceServiceProxy", "destory unbindService exception");
            }
            this.m = false;
        }
        this.j = null;
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void updateDeviceAfterSimulatConnected(final String str) {
        LogUtil.c("UniteDeviceServiceProxy", "enter updateDeviceAfterSimulatConnected");
        this.l.execute(new Runnable() { // from class: snr.24
            @Override // java.lang.Runnable
            public void run() {
                snr snrVar = snr.this;
                snrVar.b(snrVar.b, false);
                if (snr.this.j != null) {
                    try {
                        snr.this.j.updateDeviceAfterSimulatConnected(str);
                    } catch (RemoteException unused) {
                        LogUtil.e("UniteDeviceServiceProxy", "updateDeviceAfterSimulatConnected RemoteException!");
                    }
                }
            }
        });
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public List<com.huawei.health.devicemgr.business.entity.DeviceInfo> getDeviceMgrList(int i, String str) {
        a(this.b);
        if (this.j != null) {
            try {
                return this.j.getDeviceMgrList(i, str);
            } catch (RemoteException unused) {
                LogUtil.e("UniteDeviceServiceProxy", "getDeviceMgrList RemoteException!");
            }
        } else {
            LogUtil.e("UniteDeviceServiceProxy", "devicesManagementBinder is null");
        }
        return new ArrayList(16);
    }

    /* loaded from: classes7.dex */
    static class b {
        private static snr b = new snr();
    }
}
