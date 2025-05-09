package defpackage;

import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hms.network.embedded.r3;
import com.huawei.hwotamanager.ICheckCallback;
import com.huawei.hwotamanager.IDeviceListCallback;
import com.huawei.hwotamanager.IUpgradeCallBack;
import com.huawei.hwotamanager.IUpgradeStatusCallBack;
import com.huawei.hwotamanager.IWearHwUpdateServiceAIDL;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.wearengine.HiWearEngineService;
import com.huawei.wearengine.core.callback.ServiceConnectStateCallback;
import com.huawei.wearengine.core.device.VirtualDevice;
import com.huawei.wearengine.core.device.WearableDeviceCaller;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.device.FoundListener;
import com.huawei.wearengine.device.GetAttributeListener;
import com.huawei.wearengine.monitor.MonitorCallback;
import com.huawei.wearengine.monitor.QueryDataCallback;
import com.huawei.wearengine.monitor.SwitchStatusCallback;
import com.huawei.wearengine.notify.NotificationParcel;
import com.huawei.wearengine.notify.NotifySendCallback;
import com.huawei.wearengine.p2p.FileIdentificationParcel;
import com.huawei.wearengine.p2p.IdentityInfo;
import com.huawei.wearengine.p2p.MessageParcelExtra;
import com.huawei.wearengine.p2p.P2pCancelFileTransferCallBack;
import com.huawei.wearengine.p2p.P2pInnerApi;
import com.huawei.wearengine.p2p.P2pPingCallback;
import com.huawei.wearengine.p2p.P2pReceiverCallback;
import com.huawei.wearengine.p2p.P2pSendCallback;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes9.dex */
public class tpe implements VirtualDevice {

    /* renamed from: a, reason: collision with root package name */
    private final trv f17302a;
    private volatile HiWearEngineService b;
    private IBinder.DeathRecipient c;
    private ServiceConnectStateCallback d;
    private P2pInnerApi e;
    private ExecutorService j = Executors.newSingleThreadExecutor();

    public tpe() {
        trv trvVar = new trv("com.huawei.health", "com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService");
        this.f17302a = trvVar;
        this.c = new IBinder.DeathRecipient() { // from class: tpe.4
            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                tos.a("WearableDeviceManager", "binderDied enter");
                if (tpe.this.b != null) {
                    tpe.this.b.asBinder().unlinkToDeath(tpe.this.c, 0);
                    tpe.this.b = null;
                }
                tpe.this.b();
            }
        };
        ServiceConnectStateCallback serviceConnectStateCallback = new ServiceConnectStateCallback() { // from class: tpe.13
            @Override // com.huawei.wearengine.core.callback.ServiceConnectStateCallback
            public void onConnectChanged(ComponentName componentName, IBinder iBinder, boolean z) {
                if (z) {
                    return;
                }
                tos.a("WearableDeviceManager", "otaService is died");
                tpf.b().c();
            }
        };
        this.d = serviceConnectStateCallback;
        trvVar.c(serviceConnectStateCallback);
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void setBinder(String str, IBinder iBinder) {
        tos.a("WearableDeviceManager", "setBinder enter");
        try {
            this.b = HiWearEngineService.Stub.asInterface(iBinder);
            tos.c("WearableDeviceManager", "mApiAidl: " + this.b);
            if (this.b == null) {
                tos.d("WearableDeviceManager", "onServiceConnected error !");
            } else {
                this.b.asBinder().linkToDeath(this.c, 0);
            }
        } catch (RemoteException unused) {
            tos.d("WearableDeviceManager", "setBinder exception");
        }
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void initModule() {
        final String uuid = UUID.randomUUID().toString();
        tos.c("WearableDeviceManager", uuid + " initModule enter");
        this.j.execute(new Runnable() { // from class: tpe.19
            @Override // java.lang.Runnable
            public void run() {
                try {
                    tpe.this.d(false, uuid);
                } catch (IllegalStateException unused) {
                    tos.e("WearableDeviceManager", uuid + " initModule IllegalStateException");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z, String str) {
        tos.b("WearableDeviceManager", str + " bindService enter");
        if (this.b != null) {
            tos.b("WearableDeviceManager", str + " bindService mApiAidl != null return");
            return;
        }
        if (!z && !c()) {
            tos.e("WearableDeviceManager", str + " bindService no device");
            throw new IllegalStateException(String.valueOf(4));
        }
        tos.a("WearableDeviceManager", str + " start to invoke phone service");
        d();
        for (int i = 0; i < 70; i++) {
            if (this.b != null) {
                tos.d("WearableDeviceManager", str + " mApiAidl not null in: " + i);
                return;
            }
            try {
                Thread.sleep(200L);
            } catch (InterruptedException unused) {
                tos.d("WearableDeviceManager", str + " bindService InterruptedException");
            }
        }
    }

    private void d() {
        tos.a("WearableDeviceManager", "startPhoneService enter");
        tos.a("WearableDeviceManager", "startPhoneService start phone service");
        Intent intent = new Intent();
        intent.setAction("com.huawei.bone.action.StartPhoneService");
        intent.setPackage("com.huawei.health");
        intent.putExtra("WearEngineDataKey", "WearEngineStartPhoneService");
        try {
            tot.a().startService(intent);
        } catch (IllegalStateException | SecurityException unused) {
            tos.d("WearableDeviceManager", "startPhoneService fail");
            throw new IllegalStateException(String.valueOf(12));
        }
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void releaseModule() {
        if (this.b != null) {
            this.b.asBinder().unlinkToDeath(this.c, 0);
        }
        this.b = null;
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void getDeviceList(final FoundListener foundListener, final String str) {
        c("getDeviceList", new WearableDeviceCaller() { // from class: tpe.22
            @Override // com.huawei.wearengine.core.device.WearableDeviceCaller
            public void onCall() throws RemoteException {
                tpe.this.b.getBondedDevices(foundListener, str, toh.c().contains(str));
            }
        }, foundListener);
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void getAllDeviceList(final FoundListener foundListener, final String str) {
        c("getAllDeviceList", new WearableDeviceCaller() { // from class: tpe.24
            @Override // com.huawei.wearengine.core.device.WearableDeviceCaller
            public void onCall() throws RemoteException {
                tpe.this.b.getAllBondedDevices(foundListener, str);
            }
        }, foundListener);
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void getCommonDevice(final FoundListener foundListener, final String str) {
        c("getCommonDevice", new WearableDeviceCaller() { // from class: tpe.25
            @Override // com.huawei.wearengine.core.device.WearableDeviceCaller
            public void onCall() throws RemoteException {
                tpe.this.b.getCommonDevices(foundListener, str, toh.c().contains(str));
            }
        }, foundListener);
    }

    private void c(String str, final WearableDeviceCaller wearableDeviceCaller, final FoundListener foundListener) {
        final String uuid = UUID.randomUUID().toString();
        final String format = String.format(Locale.ROOT, "%s %s", uuid, str);
        if (foundListener == null) {
            tos.d("WearableDeviceManager", format + " callback is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        tos.a("WearableDeviceManager", format + " enter");
        c(this.j.submit(new Callable<Integer>() { // from class: tpe.23
            @Override // java.util.concurrent.Callable
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                try {
                    tpe.this.d(false, uuid);
                    if (tpe.this.b == null) {
                        tos.e("WearableDeviceManager", format + " mApiAidl is null");
                        foundListener.onDeviceFound(null);
                        return 12;
                    }
                    tos.a("WearableDeviceManager", format + " send");
                    WearableDeviceCaller wearableDeviceCaller2 = wearableDeviceCaller;
                    if (wearableDeviceCaller2 != null) {
                        wearableDeviceCaller2.onCall();
                    }
                    return 0;
                } catch (RemoteException unused) {
                    tos.e("WearableDeviceManager", format + " RemoteException");
                    return 12;
                } catch (IllegalStateException e) {
                    return Integer.valueOf(toq.b(e.getMessage()));
                }
            }
        }), str);
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void getHiLinkDeviceId(final Device device, final GetAttributeListener getAttributeListener) {
        final String uuid = UUID.randomUUID().toString();
        tos.a("WearableDeviceManager", uuid + " getHiLinkDeviceId enter");
        if (device == null) {
            tos.d("WearableDeviceManager", uuid + " getHiLinkDeviceId device is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (getAttributeListener == null) {
            tos.d("WearableDeviceManager", uuid + " getHiLinkDeviceId getAttributeListener is null");
            throw new IllegalStateException(String.valueOf(12));
        }
        c(this.j.submit(new Callable<Integer>() { // from class: tpe.21
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                try {
                    tpe.this.d(false, uuid);
                    if (tpe.this.b == null) {
                        tos.e("WearableDeviceManager", uuid + " getHiLinkDeviceId mApiAidl is null");
                        getAttributeListener.onGetString(null);
                        return 12;
                    }
                    tos.a("WearableDeviceManager", uuid + " getHiLinkDeviceId send");
                    tpe.this.b.getHiLinkDeviceId(device, getAttributeListener);
                    return 0;
                } catch (RemoteException unused) {
                    tos.e("WearableDeviceManager", uuid + " getHiLinkDeviceId RemoteException");
                    return 12;
                } catch (IllegalStateException e) {
                    tos.e("WearableDeviceManager", uuid + " getHiLinkDeviceId IllegalStateException");
                    return Integer.valueOf(toq.b(e.getMessage()));
                }
            }
        }), "getHiLinkDeviceId");
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void queryDeviceCapability(final Device device, final int i, final GetAttributeListener getAttributeListener) {
        final String uuid = UUID.randomUUID().toString();
        if (device == null) {
            tos.d("WearableDeviceManager", uuid + " queryDeviceCapability device is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (getAttributeListener == null) {
            tos.d("WearableDeviceManager", uuid + " queryDeviceCapability queryCapabilityCallback is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        c(this.j.submit(new Callable<Integer>() { // from class: tpe.29
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                try {
                    tpe.this.d(false, uuid);
                    if (tpe.this.b == null) {
                        tos.e("WearableDeviceManager", uuid + " queryDeviceCapability mApiAidl is null");
                        return 12;
                    }
                    tos.a("WearableDeviceManager", uuid + " queryDeviceCapability send");
                    tpe.this.b.queryDeviceCapability(device, i, getAttributeListener);
                    return 0;
                } catch (RemoteException unused) {
                    tos.e("WearableDeviceManager", uuid + " queryDeviceCapability RemoteException");
                    return 12;
                } catch (IllegalStateException e) {
                    tos.e("WearableDeviceManager", uuid + " queryDeviceCapability IllegalStateException");
                    return Integer.valueOf(toq.b(e.getMessage()));
                }
            }
        }), "queryDeviceCapability");
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void ping(Device device, String str, String str2, P2pPingCallback p2pPingCallback) {
        if (p2pPingCallback == null) {
            tos.d("WearableDeviceManager", "ping pingCallback is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (device == null || !tot.c(str) || !tot.c(str2)) {
            try {
                p2pPingCallback.onResult(5);
                tos.d("WearableDeviceManager", "ping device or pkgName is valid");
                throw new IllegalStateException(String.valueOf(5));
            } catch (RemoteException unused) {
                tos.e("WearableDeviceManager", "ping RemoteException");
                throw new IllegalStateException(String.valueOf(12));
            }
        }
        tos.a("WearableDeviceManager", "ping enter");
        c(this.j.submit(b(device, str, str2, p2pPingCallback)), r3.r);
    }

    private Callable<Integer> b(final Device device, final String str, final String str2, final P2pPingCallback p2pPingCallback) {
        final String uuid = UUID.randomUUID().toString();
        tos.a("WearableDeviceManager", uuid + " getPingCallable enter");
        return new Callable<Integer>() { // from class: tpe.2
            @Override // java.util.concurrent.Callable
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                return tpe.this.b(device, str, str2, p2pPingCallback, uuid);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Integer b(Device device, String str, String str2, P2pPingCallback p2pPingCallback, String str3) {
        try {
            d(false, str3);
            if (this.b == null) {
                tos.e("WearableDeviceManager", str3 + " ping mApiAidl is null");
                p2pPingCallback.onResult(12);
                return 12;
            }
            tos.a("WearableDeviceManager", str3 + " ping send");
            this.b.ping(device, str, str2, p2pPingCallback);
            return 0;
        } catch (RemoteException unused) {
            tos.e("WearableDeviceManager", str3 + " ping RemoteException");
            return 12;
        } catch (IllegalStateException e) {
            return Integer.valueOf(toq.b(e.getMessage()));
        }
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void send(Device device, MessageParcelExtra messageParcelExtra, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) {
        if (p2pSendCallback == null) {
            tos.d("WearableDeviceManager", "send sendCallback is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (messageParcelExtra == null) {
            tos.d("WearableDeviceManager", "send message is null");
            a(p2pSendCallback, 5);
            throw new IllegalStateException(String.valueOf(5));
        }
        c(messageParcelExtra, p2pSendCallback);
        if (identityInfo == null || identityInfo2 == null) {
            a(p2pSendCallback, 5);
            tos.d("WearableDeviceManager", "send pkgInfo is invalid");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (!tot.c(identityInfo.getPackageName()) || !tot.c(identityInfo2.getPackageName())) {
            a(p2pSendCallback, 5);
            tos.d("WearableDeviceManager", "send pkgName is invalid");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (device == null || TextUtils.isEmpty(device.getUuid())) {
            a(p2pSendCallback, 5);
            tos.d("WearableDeviceManager", "send pkgName is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        tos.a("WearableDeviceManager", "send enter");
        c(this.j.submit(e(device, messageParcelExtra, identityInfo, identityInfo2, p2pSendCallback)), "send");
    }

    private void c(Future<Integer> future, String str) {
        try {
            int intValue = future.get(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS).intValue();
            if (intValue == 0) {
            } else {
                throw new IllegalStateException(String.valueOf(intValue));
            }
        } catch (InterruptedException unused) {
            tos.e("WearableDeviceManager", str + " InterruptedException");
            throw new IllegalStateException(String.valueOf(12));
        } catch (ExecutionException unused2) {
            tos.e("WearableDeviceManager", str + " ExecutionException");
            throw new IllegalStateException(String.valueOf(12));
        } catch (TimeoutException unused3) {
            tos.e("WearableDeviceManager", str + " TimeoutException");
            throw new IllegalStateException(String.valueOf(12));
        }
    }

    private int e(Future<Integer> future, String str) {
        try {
            return future.get(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS).intValue();
        } catch (InterruptedException unused) {
            tos.e("WearableDeviceManager", str + "InterruptedException");
            return 12;
        } catch (ExecutionException unused2) {
            tos.e("WearableDeviceManager", str + "ExecutionException");
            return 12;
        } catch (TimeoutException unused3) {
            tos.e("WearableDeviceManager", str + "TimeoutException");
            return 12;
        }
    }

    private Callable<Integer> e(final Device device, final MessageParcelExtra messageParcelExtra, final IdentityInfo identityInfo, final IdentityInfo identityInfo2, final P2pSendCallback p2pSendCallback) {
        final String uuid = UUID.randomUUID().toString();
        tos.a("WearableDeviceManager", uuid + "getSendCallable enter");
        return new Callable<Integer>() { // from class: tpe.5
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                try {
                    tpe.this.d(false, uuid);
                    if (tpe.this.b == null) {
                        tos.e("WearableDeviceManager", uuid + " send mApiAidl is null");
                        tpe.this.a(p2pSendCallback, 12);
                        return 12;
                    }
                    tos.a("WearableDeviceManager", uuid + " send send");
                    tpe.this.b.send(device.getUuid(), messageParcelExtra, identityInfo, identityInfo2, p2pSendCallback);
                    return 0;
                } catch (RemoteException unused) {
                    tos.e("WearableDeviceManager", uuid + " send RemoteException");
                    return 12;
                } catch (IllegalStateException e) {
                    return Integer.valueOf(toq.b(e.getMessage()));
                }
            }
        };
    }

    private Callable<Integer> a(final Device device, final FileIdentificationParcel fileIdentificationParcel, final IdentityInfo identityInfo, final IdentityInfo identityInfo2, final P2pCancelFileTransferCallBack p2pCancelFileTransferCallBack) {
        final String uuid = UUID.randomUUID().toString();
        tos.a("WearableDeviceManager", uuid + "getCancelFileTransferCallable enter");
        return new Callable<Integer>() { // from class: tpe.1
            @Override // java.util.concurrent.Callable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                try {
                    tpe.this.d(false, uuid);
                    if (tpe.this.b == null) {
                        tos.e("WearableDeviceManager", uuid + " getCancelFileTransferCallable mApiAidl is null");
                        tpe.this.c(p2pCancelFileTransferCallBack, 12, "Internal error");
                        return 12;
                    }
                    tos.a("WearableDeviceManager", uuid + " getCancelFileTransferCallable");
                    tpe.this.b.cancelFileTransfer(device, fileIdentificationParcel, identityInfo, identityInfo2, p2pCancelFileTransferCallBack);
                    return 0;
                } catch (RemoteException unused) {
                    tos.e("WearableDeviceManager", uuid + " getCancelFileTransferCallable RemoteException");
                    return 12;
                } catch (IllegalStateException e) {
                    return Integer.valueOf(toq.b(e.getMessage()));
                }
            }
        };
    }

    private void c(MessageParcelExtra messageParcelExtra, P2pSendCallback p2pSendCallback) {
        if (messageParcelExtra.getType() == 1) {
            byte[] data = messageParcelExtra.getData();
            if (data == null || data.length == 0) {
                tos.d("WearableDeviceManager", "send message data is valid");
                a(p2pSendCallback, 5);
                throw new IllegalStateException(String.valueOf(5));
            }
            return;
        }
        if (messageParcelExtra.getParcelFileDescriptor() != null) {
            return;
        }
        tos.d("WearableDeviceManager", "send message file is null");
        a(p2pSendCallback, 10);
        throw new IllegalStateException(String.valueOf(10));
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public int subscribeDeviceDataReceiver(final P2pReceiverCallback p2pReceiverCallback) {
        final String uuid = UUID.randomUUID().toString();
        tos.a("WearableDeviceManager", uuid + " subscribeDeviceDataReceiver enter");
        return e(this.j.submit(new Callable<Integer>() { // from class: tpe.3
            @Override // java.util.concurrent.Callable
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                try {
                    tpe.this.d(false, uuid);
                    if (tpe.this.b == null) {
                        tos.e("WearableDeviceManager", uuid + " mApiAidl is null");
                        return 12;
                    }
                    tos.a("WearableDeviceManager", uuid + " start subscribeDeviceDataReceiver");
                    tpe.this.b.subscribeDeviceDataReceiver(p2pReceiverCallback);
                    return 0;
                } catch (RemoteException unused) {
                    tos.e("WearableDeviceManager", uuid + " subscribeDeviceDataReceiver RemoteException");
                    return 12;
                } catch (IllegalStateException e) {
                    return Integer.valueOf(toq.b(e.getMessage()));
                }
            }
        }), "subscribeDeviceDataReceiver");
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public int unsubscribeDeviceDataReceiver() {
        final String uuid = UUID.randomUUID().toString();
        tos.a("WearableDeviceManager", uuid + " unsubscribeDeviceDataReceiver enter");
        return e(this.j.submit(new Callable<Integer>() { // from class: tpe.7
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                try {
                    tpe.this.d(false, uuid);
                    if (tpe.this.b == null) {
                        tos.e("WearableDeviceManager", uuid + " mApiAidl is null");
                        return 12;
                    }
                    tos.a("WearableDeviceManager", uuid + " start unsubscribeDeviceDataReceiver");
                    tpe.this.b.unsubscribeDeviceDataReceiver();
                    return 0;
                } catch (RemoteException unused) {
                    tos.e("WearableDeviceManager", uuid + " unsubscribeDeviceDataReceiver RemoteException");
                    return 12;
                } catch (IllegalStateException e) {
                    return Integer.valueOf(toq.b(e.getMessage()));
                }
            }
        }), "unsubscribeDeviceDataReceiver");
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public int subscribeMonitorListener(final MonitorCallback monitorCallback) {
        final String uuid = UUID.randomUUID().toString();
        tos.a("WearableDeviceManager", uuid + " subscribeMonitorListener enter");
        return e(this.j.submit(new Callable<Integer>() { // from class: tpe.6
            @Override // java.util.concurrent.Callable
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                try {
                    tpe.this.d(false, uuid);
                    if (tpe.this.b == null) {
                        tos.e("WearableDeviceManager", uuid + " mApiAidl is null");
                        return 12;
                    }
                    tos.a("WearableDeviceManager", uuid + " start subscribeMonitorListener");
                    tpe.this.b.subscribeMonitorReceiver(monitorCallback);
                    return 0;
                } catch (RemoteException unused) {
                    tos.e("WearableDeviceManager", uuid + " subscribeMonitorListener RemoteException");
                    return 12;
                } catch (IllegalStateException e) {
                    return Integer.valueOf(toq.b(e.getMessage()));
                }
            }
        }), "subscribeMonitorListener");
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public int unsubscribeMonitorListener() {
        final String uuid = UUID.randomUUID().toString();
        tos.a("WearableDeviceManager", uuid + " unsubscribeMonitorListener enter");
        return e(this.j.submit(new Callable<Integer>() { // from class: tpe.10
            @Override // java.util.concurrent.Callable
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                try {
                    tpe.this.d(false, uuid);
                    if (tpe.this.b == null) {
                        tos.e("WearableDeviceManager", uuid + " mApiAidl is null");
                        return 12;
                    }
                    tos.a("WearableDeviceManager", uuid + " start unsubscribeMonitorListener");
                    tpe.this.b.unsubscribeMonitorReceiver();
                    return 0;
                } catch (RemoteException unused) {
                    tos.e("WearableDeviceManager", uuid + " unsubscribeMonitorListener RemoteException");
                    return 12;
                } catch (IllegalStateException e) {
                    return Integer.valueOf(toq.b(e.getMessage()));
                }
            }
        }), "unsubscribeMonitorListener");
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public int switchMonitorStatus(final int i, final String str, final String str2, final SwitchStatusCallback switchStatusCallback) {
        final String uuid = UUID.randomUUID().toString();
        tos.a("WearableDeviceManager", uuid + " switchMonitorStatus enter");
        try {
            return ((Integer) this.j.submit(new Callable<Integer>() { // from class: tpe.8
                @Override // java.util.concurrent.Callable
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public Integer call() {
                    try {
                        tpe.this.d(false, uuid);
                        if (tpe.this.b == null) {
                            tos.e("WearableDeviceManager", uuid + " mApiAidl is null");
                            return 12;
                        }
                        tos.a("WearableDeviceManager", uuid + " start switchMonitorStatus");
                        tpe.this.b.switchMonitorStatus(i, str, str2, switchStatusCallback);
                        return 0;
                    } catch (RemoteException unused) {
                        tos.e("WearableDeviceManager", uuid + " switchMonitorStatus RemoteException");
                        return 12;
                    } catch (IllegalStateException e) {
                        return Integer.valueOf(toq.b(e.getMessage()));
                    }
                }
            }).get(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS)).intValue();
        } catch (InterruptedException unused) {
            tos.e("WearableDeviceManager", "switchMonitorStatus InterruptedException");
            return 12;
        } catch (ExecutionException unused2) {
            tos.e("WearableDeviceManager", "switchMonitorStatus ExecutionException");
            return 12;
        } catch (TimeoutException unused3) {
            tos.e("WearableDeviceManager", "switchMonitorStatus TimeoutException");
            return 12;
        }
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void queryMonitorData(final Device device, final String str, final QueryDataCallback queryDataCallback) {
        final String uuid = UUID.randomUUID().toString();
        if (queryDataCallback == null) {
            tos.d("WearableDeviceManager", uuid + " queryMonitorData monitorQueryCallback is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (device == null || TextUtils.isEmpty(str)) {
            try {
                queryDataCallback.onDataReceived(5, null);
                tos.d("WearableDeviceManager", uuid + " queryMonitorData device or monitorItemType is valid");
            } catch (RemoteException unused) {
                tos.e("WearableDeviceManager", uuid + " queryMonitorData RemoteException");
            }
            throw new IllegalStateException(String.valueOf(5));
        }
        tos.a("WearableDeviceManager", uuid + " queryMonitorData enter");
        c(this.j.submit(new Callable<Integer>() { // from class: tpe.9
            @Override // java.util.concurrent.Callable
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                try {
                    tpe.this.d(false, uuid);
                    if (tpe.this.b == null) {
                        tos.e("WearableDeviceManager", uuid + " queryMonitorData mApiAidl is null");
                        queryDataCallback.onDataReceived(5, null);
                        return 12;
                    }
                    tos.a("WearableDeviceManager", uuid + " queryMonitorData send");
                    tpe.this.b.query(device, str, queryDataCallback);
                    return 0;
                } catch (RemoteException unused2) {
                    tos.e("WearableDeviceManager", uuid + " queryMonitorData RemoteException");
                    return 12;
                } catch (IllegalStateException e) {
                    return Integer.valueOf(toq.b(e.getMessage()));
                }
            }
        }), "queryMonitorData");
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void notifyData(final Device device, final NotificationParcel notificationParcel, final NotifySendCallback notifySendCallback) {
        final String uuid = UUID.randomUUID().toString();
        if (notifySendCallback == null || notificationParcel == null) {
            tos.d("WearableDeviceManager", uuid + " notifySendCallback or notificationParcel is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (device == null || TextUtils.isEmpty(device.getUuid())) {
            try {
                notifySendCallback.onError(notificationParcel, 5);
                tos.d("WearableDeviceManager", uuid + " notifyData device or notificationParcel is valid");
            } catch (RemoteException unused) {
                tos.e("WearableDeviceManager", uuid + " notifyData RemoteException");
            }
            throw new IllegalStateException(String.valueOf(5));
        }
        tos.a("WearableDeviceManager", uuid + " notify enter");
        c(this.j.submit(new Callable<Integer>() { // from class: tpe.12
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                try {
                    tpe.this.d(false, uuid);
                    if (tpe.this.b == null) {
                        tos.e("WearableDeviceManager", uuid + " notify mApiAidl is null");
                        notifySendCallback.onError(notificationParcel, 5);
                        return 12;
                    }
                    tos.a("WearableDeviceManager", uuid + " notify send");
                    tpe.this.b.notify(device.getUuid(), notificationParcel, notifySendCallback);
                    return 0;
                } catch (RemoteException unused2) {
                    tos.e("WearableDeviceManager", uuid + " notify RemoteException");
                    return 12;
                } catch (IllegalStateException e) {
                    return Integer.valueOf(toq.b(e.getMessage()));
                }
            }
        }), "notify");
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void setP2pInnerApi(P2pInnerApi p2pInnerApi) {
        this.e = p2pInnerApi;
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public boolean isP2pReceiverExist(String str, IdentityInfo identityInfo, IdentityInfo identityInfo2) {
        P2pInnerApi p2pInnerApi = this.e;
        if (p2pInnerApi == null) {
            tos.e("WearableDeviceManager", "isP2pReceiverExist mReceiverCallbackManager is null");
            return false;
        }
        return p2pInnerApi.isP2pReceiverExist(str, identityInfo, identityInfo2);
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void getDeviceAppVersionCode(final Device device, final String str, final String str2, final P2pPingCallback p2pPingCallback) {
        final String uuid = UUID.randomUUID().toString();
        if (device == null || !tot.c(str) || !tot.c(str2)) {
            tos.d("WearableDeviceManager", uuid + " getDeviceAppVersionCode device or pkgName is valid");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (p2pPingCallback == null) {
            tos.d("WearableDeviceManager", uuid + " getDeviceAppVersionCode versionCodeCallback is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        c(this.j.submit(new Callable<Integer>() { // from class: tpe.15
            @Override // java.util.concurrent.Callable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                try {
                    tpe.this.d(false, uuid);
                    if (tpe.this.b == null) {
                        tos.e("WearableDeviceManager", uuid + " getDeviceAppVersionCode mApiAidl is null");
                        return 12;
                    }
                    tos.a("WearableDeviceManager", uuid + " getDeviceAppVersionCode send");
                    tpe.this.b.getDeviceAppVersionCode(device, str, str2, p2pPingCallback);
                    return 0;
                } catch (RemoteException unused) {
                    tos.e("WearableDeviceManager", uuid + " getDeviceAppVersionCode RemoteException");
                    return 12;
                } catch (IllegalStateException e) {
                    tos.e("WearableDeviceManager", uuid + " getDeviceAppVersionCode IllegalStateException");
                    return Integer.valueOf(toq.b(e.getMessage()));
                }
            }
        }), "getDeviceAppVersionCode");
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public int cancelFileTransfer(Device device, FileIdentificationParcel fileIdentificationParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pCancelFileTransferCallBack p2pCancelFileTransferCallBack) {
        if (!e(device, fileIdentificationParcel, identityInfo, identityInfo2, p2pCancelFileTransferCallBack)) {
            throw new IllegalStateException(String.valueOf(5));
        }
        tos.a("WearableDeviceManager", "cancelFileTransfer enter");
        return e(this.j.submit(a(device, fileIdentificationParcel, identityInfo, identityInfo2, p2pCancelFileTransferCallBack)), "cancelFileTransfer");
    }

    private boolean e(Device device, FileIdentificationParcel fileIdentificationParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pCancelFileTransferCallBack p2pCancelFileTransferCallBack) {
        if (p2pCancelFileTransferCallBack == null) {
            tos.d("WearableDeviceManager", "checkCancelFileTransferParams p2pCancelFileTransferCallBack is null");
            return false;
        }
        if (fileIdentificationParcel == null || TextUtils.isEmpty(fileIdentificationParcel.getFileName())) {
            tos.d("WearableDeviceManager", "checkCancelFileTransferParams fileIdentification is null or fileName is empty");
            c(p2pCancelFileTransferCallBack, 5, "Invalid argument");
            return false;
        }
        if (device == null || TextUtils.isEmpty(device.getUuid())) {
            tos.d("WearableDeviceManager", "checkCancelFileTransferParams device is null or device uuid is empty");
            c(p2pCancelFileTransferCallBack, 5, "Invalid argument");
            return false;
        }
        if (identityInfo == null || identityInfo2 == null) {
            c(p2pCancelFileTransferCallBack, 5, "Invalid argument");
            tos.d("WearableDeviceManager", "checkCancelFileTransferParams pkgInfo is invalid");
            return false;
        }
        if (tot.c(identityInfo.getPackageName()) && tot.c(identityInfo2.getPackageName())) {
            return true;
        }
        c(p2pCancelFileTransferCallBack, 5, "Invalid argument");
        tos.d("WearableDeviceManager", "checkCancelFileTransferParams pkgName is invalid");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(P2pSendCallback p2pSendCallback, int i) {
        try {
            p2pSendCallback.onSendResult(i);
            p2pSendCallback.onSendProgress(0L);
        } catch (Exception unused) {
            tos.e("WearableDeviceManager", "sendErrorResult Exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(P2pCancelFileTransferCallBack p2pCancelFileTransferCallBack, int i, String str) {
        try {
            p2pCancelFileTransferCallBack.onCancelFileTransferResult(i, str);
        } catch (Exception unused) {
            tos.e("WearableDeviceManager", "stopSendFileErrorResult Exception");
        }
    }

    public static boolean c() {
        try {
            Class<?> cls = Class.forName("com.huawei.wearengine.utills.WearEngineReflectUtil");
            Object invoke = cls.getDeclaredMethod("getHasDeviceDbInfo", new Class[0]).invoke(cls, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
        } catch (ClassNotFoundException unused) {
            tos.d("WearableDeviceManager", "hasDeviceConnectedInfo ClassNotFoundException");
        } catch (IllegalAccessException unused2) {
            tos.d("WearableDeviceManager", "hasDeviceConnectedInfo IllegalAccessException");
        } catch (NoSuchMethodException unused3) {
            tos.d("WearableDeviceManager", "hasDeviceConnectedInfo NoSuchMethodException");
        } catch (InvocationTargetException unused4) {
            tos.d("WearableDeviceManager", "hasDeviceConnectedInfo InvocationTargetException");
        }
        return false;
    }

    private boolean a() {
        try {
            Class<?> cls = Class.forName("com.huawei.wearengine.utills.WearEngineReflectUtil");
            Object invoke = cls.getDeclaredMethod("getHasWearableDeviceDbInfo", new Class[0]).invoke(cls, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
        } catch (ClassNotFoundException unused) {
            tos.d("WearableDeviceManager", "hasWearableDeviceInfo ClassNotFoundException");
        } catch (IllegalAccessException unused2) {
            tos.d("WearableDeviceManager", "hasWearableDeviceInfo IllegalAccessException");
        } catch (NoSuchMethodException unused3) {
            tos.d("WearableDeviceManager", "hasWearableDeviceInfo NoSuchMethodException");
        } catch (InvocationTargetException unused4) {
            tos.d("WearableDeviceManager", "hasWearableDeviceInfo InvocationTargetException");
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        tpf.b().d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IWearHwUpdateServiceAIDL d(String str) {
        IBinder ffk_ = this.f17302a.ffk_();
        if (ffk_ == null) {
            if (!a()) {
                tos.e("WearableDeviceManager", str + " syncCheckUpdateServiceStatus no bond device");
                throw new IllegalStateException(String.valueOf(16));
            }
            ffk_ = this.f17302a.ffl_();
        }
        if (ffk_ == null) {
            tos.e("WearableDeviceManager", str + " getWearHwUpdateService wearHwUpdateServiceAidl is null");
            throw new IllegalStateException(String.valueOf(12));
        }
        return IWearHwUpdateServiceAIDL.Stub.asInterface(ffk_);
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void getConnectedDevices(IDeviceListCallback iDeviceListCallback) {
        String uuid = UUID.randomUUID().toString();
        tos.a("WearableDeviceManager", uuid + " getConnectedDevices enter");
        tom.e(iDeviceListCallback, uuid + " getConnectedDevices deviceListCallback is null");
        c(this.j.submit(d(uuid, iDeviceListCallback)), "getConnectedDevices");
    }

    private Callable<Integer> d(final String str, final IDeviceListCallback iDeviceListCallback) {
        return new Callable<Integer>() { // from class: tpe.11
            @Override // java.util.concurrent.Callable
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                try {
                    IWearHwUpdateServiceAIDL d = tpe.this.d(str);
                    tos.a("WearableDeviceManager", str + " getConnectedDevices send");
                    d.getConnectedDevices(iDeviceListCallback);
                    return 0;
                } catch (RemoteException unused) {
                    tos.e("WearableDeviceManager", str + " getConnectedDevices RemoteException");
                    return 12;
                } catch (IllegalStateException e) {
                    tos.e("WearableDeviceManager", str + " getConnectedDevices IllegalStateException");
                    return Integer.valueOf(toq.b(e.getMessage()));
                }
            }
        };
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void getDeviceNewVersion(Device device, ICheckCallback iCheckCallback) {
        String uuid = UUID.randomUUID().toString();
        tos.a("WearableDeviceManager", uuid + " getDeviceNewVersion enter");
        tom.e(device, uuid + " getDeviceNewVersion device is null");
        tom.e(iCheckCallback, uuid + " getDeviceNewVersion checkCallback is null");
        c(this.j.submit(c(uuid, device, iCheckCallback)), "getDeviceNewVersion");
    }

    private Callable<Integer> c(final String str, final Device device, final ICheckCallback iCheckCallback) {
        return new Callable<Integer>() { // from class: tpe.14
            @Override // java.util.concurrent.Callable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                try {
                    IWearHwUpdateServiceAIDL d = tpe.this.d(str);
                    tos.a("WearableDeviceManager", str + " getDeviceNewVersion send");
                    d.getDeviceNewVersion(device.getUuid(), iCheckCallback);
                    return 0;
                } catch (RemoteException unused) {
                    tos.e("WearableDeviceManager", str + " getDeviceNewVersion RemoteException");
                    return 12;
                } catch (IllegalStateException e) {
                    tos.e("WearableDeviceManager", str + " getDeviceNewVersion IllegalStateException");
                    return Integer.valueOf(toq.b(e.getMessage()));
                }
            }
        };
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void doUpgrade(final Device device, final String str, final IUpgradeCallBack iUpgradeCallBack) {
        final String uuid = UUID.randomUUID().toString();
        tom.e(device, uuid + " doUpgrade device is null");
        if (TextUtils.isEmpty(str)) {
            tos.e("WearableDeviceManager", uuid + " doUpgrade command is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        tom.e(iUpgradeCallBack, uuid + " doUpgrade upgradeCallBack is null");
        c(this.j.submit(new Callable<Integer>() { // from class: tpe.17
            @Override // java.util.concurrent.Callable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                try {
                    IWearHwUpdateServiceAIDL d = tpe.this.d(uuid);
                    tos.a("WearableDeviceManager", uuid + " doUpgrade send");
                    d.doUpgrade(device.getUuid(), str, iUpgradeCallBack);
                    return 0;
                } catch (RemoteException unused) {
                    tos.e("WearableDeviceManager", uuid + " doUpgrade RemoteException");
                    return 12;
                } catch (IllegalStateException e) {
                    tos.e("WearableDeviceManager", uuid + " doUpgrade IllegalStateException");
                    return Integer.valueOf(toq.b(e.getMessage()));
                }
            }
        }), "doUpgrade");
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void getUpgradeStatus(final Device device, final IUpgradeStatusCallBack iUpgradeStatusCallBack) {
        final String uuid = UUID.randomUUID().toString();
        tom.e(device, uuid + " getUpgradeStatus device is null");
        tom.e(iUpgradeStatusCallBack, uuid + " getUpgradeStatus upgradeStatusCallBack is null");
        c(this.j.submit(new Callable<Integer>() { // from class: tpe.20
            @Override // java.util.concurrent.Callable
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                try {
                    IWearHwUpdateServiceAIDL d = tpe.this.d(uuid);
                    tos.a("WearableDeviceManager", uuid + " getUpgradeStatus send");
                    d.getUpgradeStatus(device.getUuid(), iUpgradeStatusCallBack);
                    return 0;
                } catch (RemoteException unused) {
                    tos.e("WearableDeviceManager", uuid + " getUpgradeStatus RemoteException");
                    return 12;
                } catch (IllegalStateException e) {
                    tos.e("WearableDeviceManager", uuid + " getUpgradeStatus IllegalStateException");
                    return Integer.valueOf(toq.b(e.getMessage()));
                }
            }
        }), "getUpgradeStatus");
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void registerUpgradeListener(final Device device, final IUpgradeStatusCallBack iUpgradeStatusCallBack) {
        final String uuid = UUID.randomUUID().toString();
        tom.e(device, uuid + " registerUpgradeListener device is null");
        tom.e(iUpgradeStatusCallBack, uuid + " registerUpgradeListener upgradeStatusCallBack is null");
        c(this.j.submit(new Callable<Integer>() { // from class: tpe.18
            @Override // java.util.concurrent.Callable
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                try {
                    IWearHwUpdateServiceAIDL d = tpe.this.d(uuid);
                    tos.a("WearableDeviceManager", uuid + " registerUpgradeListener send");
                    d.registerUpgradeListener(device.getUuid(), iUpgradeStatusCallBack);
                    return 0;
                } catch (RemoteException unused) {
                    tos.e("WearableDeviceManager", uuid + " registerUpgradeListener RemoteException");
                    return 12;
                } catch (IllegalStateException e) {
                    tos.e("WearableDeviceManager", uuid + " registerUpgradeListener IllegalStateException");
                    return Integer.valueOf(toq.b(e.getMessage()));
                }
            }
        }), "registerUpgradeListener");
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void unRegisterUpgradeListener(final Device device) {
        final String uuid = UUID.randomUUID().toString();
        tom.e(device, uuid + " unRegisterUpgradeListener device is null");
        c(this.j.submit(new Callable<Integer>() { // from class: tpe.16
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                try {
                    IWearHwUpdateServiceAIDL d = tpe.this.d(uuid);
                    tos.a("WearableDeviceManager", uuid + " unRegisterUpgradeListener send");
                    d.unRegisterUpgradeListener(device.getUuid());
                    return 0;
                } catch (RemoteException unused) {
                    tos.e("WearableDeviceManager", uuid + " unRegisterUpgradeListener RemoteException");
                    return 12;
                } catch (IllegalStateException e) {
                    tos.e("WearableDeviceManager", uuid + " unRegisterUpgradeListener IllegalStateException");
                    return Integer.valueOf(toq.b(e.getMessage()));
                }
            }
        }), "unRegisterUpgradeListener");
    }

    @Override // com.huawei.wearengine.core.device.VirtualDevice
    public void disconnectOtaService() {
        tos.a("WearableDeviceManager", "disconnectOtaService enter");
        this.f17302a.c();
    }
}
