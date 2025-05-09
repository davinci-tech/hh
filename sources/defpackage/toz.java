package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.hwotamanager.ICheckCallback;
import com.huawei.hwotamanager.IDeviceListCallback;
import com.huawei.hwotamanager.IUpgradeCallBack;
import com.huawei.hwotamanager.IUpgradeStatusCallBack;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.wearengine.connect.ServiceConnectCallback;
import com.huawei.wearengine.connect.ServiceConnectCallbackManager;
import com.huawei.wearengine.core.device.VirtualDevice;
import com.huawei.wearengine.core.device.VirtualDeviceCaller;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.device.FoundListener;
import com.huawei.wearengine.device.GetAttributeListener;
import com.huawei.wearengine.monitor.MonitorCallback;
import com.huawei.wearengine.monitor.MonitorData;
import com.huawei.wearengine.monitor.MonitorItem;
import com.huawei.wearengine.monitor.MonitorMessage;
import com.huawei.wearengine.monitor.QueryDataCallback;
import com.huawei.wearengine.monitor.SwitchStatusCallback;
import com.huawei.wearengine.notify.NotificationParcel;
import com.huawei.wearengine.notify.NotifySendCallback;
import com.huawei.wearengine.ota.CheckBinderCallback;
import com.huawei.wearengine.ota.DeviceListBinderCallback;
import com.huawei.wearengine.ota.UpgradeBinderCallBack;
import com.huawei.wearengine.ota.UpgradeStatusBinderCallBack;
import com.huawei.wearengine.p2p.FileIdentificationParcel;
import com.huawei.wearengine.p2p.IdentityInfo;
import com.huawei.wearengine.p2p.MessageParcel;
import com.huawei.wearengine.p2p.MessageParcelExtra;
import com.huawei.wearengine.p2p.P2pCancelFileTransferCallBack;
import com.huawei.wearengine.p2p.P2pInnerApi;
import com.huawei.wearengine.p2p.P2pPingCallback;
import com.huawei.wearengine.p2p.P2pReceiverCallback;
import com.huawei.wearengine.p2p.P2pSendCallback;
import com.huawei.wearengine.p2p.ReceiveResultCallback;
import defpackage.tos;
import defpackage.tow;
import defpackage.toz;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class toz {
    private tor b;
    private final ExecutorService c;
    private P2pInnerApi d;
    private tox e;
    private final trv f;
    private ThreadFactory g;
    private tpa i;
    private P2pReceiverCallback h = null;

    /* renamed from: a, reason: collision with root package name */
    private MonitorCallback f17286a = null;

    public toz(tor torVar) {
        trv trvVar = new trv("com.huawei.health", "com.huawei.wearengine.service.WearEngineService");
        this.f = trvVar;
        this.d = new P2pInnerApi() { // from class: toz.1
            @Override // com.huawei.wearengine.p2p.P2pInnerApi
            public boolean isP2pReceiverExist(String str, IdentityInfo identityInfo, IdentityInfo identityInfo2) {
                return toz.this.i.d(str, identityInfo, identityInfo2);
            }
        };
        this.g = new ThreadFactory() { // from class: toz.7
            private final AtomicInteger e = new AtomicInteger();

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "common-device-manager-thread-" + this.e.getAndIncrement());
            }
        };
        this.c = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), this.g);
        this.i = new tpa();
        this.e = new tox();
        this.b = torVar;
        trvVar.c("com.huawei.wearengine.action.REGISTER_CALLBACK_MANAGER");
    }

    public List<Device> d() throws RemoteException {
        return a("getBondedDevices", new VirtualDeviceCaller() { // from class: toz.6
            @Override // com.huawei.wearengine.core.device.VirtualDeviceCaller
            public void onCall(VirtualDevice virtualDevice, FoundListener foundListener, String str, tou touVar) {
                if (virtualDevice == null) {
                    tos.e("CommonDeviceManager", "getBondedDevices virtualDevice is null");
                    return;
                }
                if (foundListener == null) {
                    tos.e("CommonDeviceManager", "getBondedDevices listener is null");
                    return;
                }
                if (TextUtils.isEmpty(str)) {
                    tos.e("CommonDeviceManager", "callingPackageName listener is null or empty");
                    return;
                }
                if (touVar == null) {
                    tos.e("CommonDeviceManager", "getBondedDevices asynToSyncListener is null");
                    return;
                }
                try {
                    virtualDevice.getDeviceList(foundListener, str);
                } catch (IllegalStateException unused) {
                    touVar.onFinish();
                    tos.e("CommonDeviceManager", "getBondedDevices IllegalStateException");
                }
            }
        });
    }

    public List<Device> c() throws RemoteException {
        return a("getAllBondedDevices", new VirtualDeviceCaller() { // from class: toz.9
            @Override // com.huawei.wearengine.core.device.VirtualDeviceCaller
            public void onCall(VirtualDevice virtualDevice, FoundListener foundListener, String str, tou touVar) {
                if (virtualDevice == null) {
                    tos.e("CommonDeviceManager", "getAllBondedDevices virtualDevice is null");
                    return;
                }
                if (foundListener == null) {
                    tos.e("CommonDeviceManager", "getAllBondedDevices listener is null");
                    return;
                }
                if (TextUtils.isEmpty(str)) {
                    tos.e("CommonDeviceManager", "getAllBondedDevices callingPackageName is null");
                    return;
                }
                if (touVar == null) {
                    tos.e("CommonDeviceManager", "getAllBondedDevices asynToSyncListener is null");
                    return;
                }
                try {
                    virtualDevice.getAllDeviceList(foundListener, str);
                } catch (IllegalStateException unused) {
                    touVar.onFinish();
                    tos.e("CommonDeviceManager", "getAllBondedDevices IllegalStateException");
                }
            }
        });
    }

    public List<Device> b() {
        return a("getCommonDevice", new VirtualDeviceCaller() { // from class: toz.12
            @Override // com.huawei.wearengine.core.device.VirtualDeviceCaller
            public void onCall(VirtualDevice virtualDevice, FoundListener foundListener, String str, tou touVar) {
                if (virtualDevice == null || foundListener == null || touVar == null) {
                    tos.e("CommonDeviceManager", "getCommonDevice device or listener or asynToSyncListener is null");
                    return;
                }
                if (TextUtils.isEmpty(str)) {
                    tos.e("CommonDeviceManager", "getCommonDevice callingPackageName is null");
                    return;
                }
                try {
                    virtualDevice.getCommonDevice(foundListener, str);
                } catch (IllegalStateException unused) {
                    touVar.onFinish();
                    tos.e("CommonDeviceManager", "getCommonDevice IllegalStateException");
                }
            }
        });
    }

    private List<Device> a(String str, VirtualDeviceCaller virtualDeviceCaller) {
        final ArrayList arrayList = new ArrayList();
        if (virtualDeviceCaller == null) {
            tos.e("CommonDeviceManager", str + " caller is null");
            return arrayList;
        }
        VirtualDevice[] a2 = toy.b().a();
        final tou touVar = new tou(a2.length);
        FoundListener.Stub stub = new FoundListener.Stub() { // from class: com.huawei.wearengine.core.device.CommonDeviceManager$6
            @Override // com.huawei.wearengine.device.FoundListener
            public void onDeviceFound(List<Device> list) {
                if (list != null) {
                    arrayList.addAll(list);
                }
                touVar.onFinish();
            }
        };
        String c = tri.c(Binder.getCallingUid(), tot.a(), this.b.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        for (VirtualDevice virtualDevice : a2) {
            if (virtualDevice == null) {
                tos.e("CommonDeviceManager", str + "virtualDevice is null");
            } else {
                try {
                    virtualDeviceCaller.onCall(virtualDevice, stub, c, touVar);
                } catch (IllegalStateException unused) {
                    touVar.onFinish();
                    tos.e("CommonDeviceManager", str + " IllegalStateException");
                }
            }
        }
        try {
            if (!touVar.e(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS)) {
                tos.e("CommonDeviceManager", str + " asyncToSyncListener timeout");
            }
        } catch (InterruptedException unused2) {
            tos.e("CommonDeviceManager", "asynToSyncListener await error");
        }
        return arrayList;
    }

    public boolean i() throws RemoteException {
        List<Device> d = d();
        if (d.isEmpty()) {
            return false;
        }
        Iterator<Device> it = d.iterator();
        while (it.hasNext()) {
            if (it.next().isConnected()) {
                return true;
            }
        }
        return false;
    }

    public String d(Device device) throws RemoteException {
        tom.e(device, "device must not be null!");
        VirtualDevice d = toy.b().d(device);
        final String[] strArr = new String[1];
        final tou touVar = new tou(1);
        GetAttributeListener.Stub stub = new GetAttributeListener.Stub() { // from class: com.huawei.wearengine.core.device.CommonDeviceManager$7
            @Override // com.huawei.wearengine.device.GetAttributeListener
            public void onGetString(String str) throws RemoteException {
                strArr[0] = str;
                touVar.onFinish();
            }
        };
        if (d != null) {
            d.getHiLinkDeviceId(device, stub);
            try {
                if (touVar.e(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS)) {
                    return strArr[0];
                }
                tos.e("CommonDeviceManager", "getHiLinkDeviceId asyncToSyncListener timeout");
                throw new IllegalStateException(String.valueOf(12));
            } catch (InterruptedException unused) {
                tos.e("CommonDeviceManager", "getHiLinkDeviceId asynToSyncListener await error");
                throw new IllegalStateException(String.valueOf(12));
            }
        }
        throw new IllegalStateException(String.valueOf(12));
    }

    public int c(Device device, int i) throws RemoteException {
        tom.e(device, "device must not be null!");
        final int[] iArr = {1};
        final tou touVar = new tou(1);
        GetAttributeListener.Stub stub = new GetAttributeListener.Stub() { // from class: com.huawei.wearengine.core.device.CommonDeviceManager$8
            @Override // com.huawei.wearengine.device.GetAttributeListener
            public void onGetString(String str) throws RemoteException {
                try {
                    iArr[0] = Integer.parseInt(str);
                } catch (NumberFormatException unused) {
                    tos.e("CommonDeviceManager", "queryDeviceCapability callback NumberFormatException");
                }
                touVar.onFinish();
            }
        };
        VirtualDevice d = toy.b().d(device);
        if (d != null) {
            d.queryDeviceCapability(device, i, stub);
            try {
                if (touVar.e(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS)) {
                    return iArr[0];
                }
                tos.e("CommonDeviceManager", "queryDeviceCapability asyncToSyncListener timeout");
                throw new IllegalStateException(String.valueOf(12));
            } catch (InterruptedException unused) {
                tos.e("CommonDeviceManager", "queryDeviceCapability asynToSyncListener await error");
                throw new IllegalStateException(String.valueOf(12));
            }
        }
        throw new IllegalStateException(String.valueOf(12));
    }

    public String b(Device device) {
        tom.e(device, "device must not be null!");
        if (TextUtils.isEmpty(device.getUuid())) {
            tos.d("CommonDeviceManager", "getDeviceSn queryDevice.Uuid is empty");
            return "";
        }
        List<Device> b = b();
        if (b.isEmpty()) {
            tos.d("CommonDeviceManager", "getDeviceSn deviceList is empty");
            return "";
        }
        String uuid = device.getUuid();
        for (Device device2 : b) {
            if (device2 == null || !uuid.equals(device2.getUuid())) {
                tos.d("CommonDeviceManager", "getDeviceSn device is null or uuid not equal");
            } else {
                String b2 = tow.b(device2);
                if (TextUtils.isEmpty(b2)) {
                    tos.d("CommonDeviceManager", "getDeviceSn deviceJsonString is null");
                    return "";
                }
                try {
                    return new JSONObject(new tow(b2).a()).getString(HealthEngineRequestManager.PARAMS_DEVICE_SN);
                } catch (JSONException unused) {
                    tos.e("CommonDeviceManager", "getDeviceSn has an JSONException");
                    return "";
                }
            }
        }
        return "";
    }

    public boolean c(String str) throws RemoteException {
        List<Device> b = b();
        if (b.isEmpty()) {
            return false;
        }
        for (Device device : b) {
            if (device.getUuid().equals(str) && device.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public void e(Device device, String str, String str2, P2pPingCallback p2pPingCallback) throws RemoteException {
        tom.e(device, "device must not be null!");
        tom.e(str, "srcPkgName must not be null!");
        tom.e(str2, "destPkgName must not be null!");
        tom.e(p2pPingCallback, "pingCallback must not be null!");
        VirtualDevice d = toy.b().d(device);
        if (d != null) {
            d.ping(device, str, str2, p2pPingCallback);
            return;
        }
        throw new IllegalStateException(String.valueOf(12));
    }

    public void a(Device device, MessageParcelExtra messageParcelExtra, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) throws RemoteException {
        tom.e(device, "device must not be null!");
        tom.e(messageParcelExtra, "message must not be null!");
        tom.e(identityInfo, "srcInfo must not be null!");
        tom.e(identityInfo2, "destInfo must not be null!");
        tom.e(p2pSendCallback, "sendCallback must not be null!");
        VirtualDevice d = toy.b().d(device);
        if (d != null) {
            d.send(device, messageParcelExtra, identityInfo, identityInfo2, p2pSendCallback);
            return;
        }
        throw new IllegalStateException(String.valueOf(12));
    }

    public int e(Device device, IdentityInfo identityInfo, IdentityInfo identityInfo2, tqb tqbVar) {
        tom.e(identityInfo, "srcInfo must not be null!");
        tom.e(identityInfo2, "destInfo must not be null!");
        if (!b(device, identityInfo.getPackageName(), identityInfo2.getPackageName(), tqbVar)) {
            tos.e("CommonDeviceManager", "registerReceiver parameters is invalid");
            return 5;
        }
        c(identityInfo, identityInfo2, device, 0);
        VirtualDevice d = toy.b().d(device);
        if (d == null) {
            tos.e("CommonDeviceManager", "registerReceiver deviceAdapter is null");
            return 12;
        }
        int b = this.i.b(device.getUuid(), identityInfo, identityInfo2, tqbVar);
        if (b != 0) {
            return b;
        }
        if (this.h == null) {
            tos.a("CommonDeviceManager", "new p2pReceiverCallback");
            this.h = new P2pReceiverCallback.Stub() { // from class: com.huawei.wearengine.core.device.CommonDeviceManager$9
                @Override // com.huawei.wearengine.p2p.P2pReceiverCallback
                public void onDataReceived(Device device2, MessageParcel messageParcel, IdentityInfo identityInfo3, IdentityInfo identityInfo4, ReceiveResultCallback receiveResultCallback) throws RemoteException {
                    toz.this.a(device2, messageParcel, identityInfo3, identityInfo4, receiveResultCallback);
                }
            };
        }
        return d.subscribeDeviceDataReceiver(this.h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Device device, MessageParcel messageParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, ReceiveResultCallback receiveResultCallback) {
        tos.a("CommonDeviceManager", "onDataReceived enter");
        if (device == null || messageParcel == null || identityInfo == null || identityInfo2 == null) {
            tos.e("CommonDeviceManager", "onDataReceived para is null");
            return;
        }
        c(identityInfo2, identityInfo, device, 1);
        try {
            byte[] data = messageParcel.getData();
            if (data != null) {
                tos.b("CommonDeviceManager", "onDataReceived: data=".concat(new String(data, "UTF-8")));
            }
        } catch (UnsupportedEncodingException unused) {
            tos.e("CommonDeviceManager", "UnsupportedEncodingException");
        }
        tos.a("CommonDeviceManager", "receive file:" + messageParcel.getFileName());
        this.i.e(device.getUuid(), messageParcel, identityInfo, identityInfo2, receiveResultCallback);
    }

    private boolean b(Device device, String str, String str2, tqb tqbVar) {
        return (device == null || tqbVar == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !tot.c(str) || !tot.c(str2)) ? false : true;
    }

    public int d(tqb tqbVar) {
        if (tqbVar == null) {
            tos.e("CommonDeviceManager", "unregisterReceiver receiverCallbackProxy is null");
            return 5;
        }
        this.i.e(tqbVar);
        return o();
    }

    public tpa j() {
        return this.i;
    }

    private int o() {
        if (this.i.a() != 0) {
            return 0;
        }
        int n = n();
        this.h = null;
        return n;
    }

    private int n() {
        int unsubscribeDeviceDataReceiver;
        int i = 0;
        for (VirtualDevice virtualDevice : toy.b().a()) {
            if (virtualDevice != null && (unsubscribeDeviceDataReceiver = virtualDevice.unsubscribeDeviceDataReceiver()) != 0) {
                i = unsubscribeDeviceDataReceiver;
            }
        }
        tos.a("CommonDeviceManager", "unSubscribeDeviceDataReciverInter result: " + i);
        return i;
    }

    public void a() {
        tos.a("CommonDeviceManager", "cleanDiedCallbackByBinderDied enter");
        this.i.e();
        o();
    }

    public int d(Device device, List<MonitorItem> list, tpk tpkVar) {
        if (!a(device, list, tpkVar)) {
            tos.e("CommonDeviceManager", "registerMonitor parameters is invalid");
            return 5;
        }
        tos.b("CommonDeviceManager", "registerMonitor monitorItemType is:" + list + ", device uuid is:" + device.getUuid());
        return b(device, list, tpkVar);
    }

    private int b(Device device, List<MonitorItem> list, tpk tpkVar) {
        VirtualDevice d = toy.b().d(device);
        if (d == null) {
            tos.e("CommonDeviceManager", "registerMonitor deviceAdapter is null");
            return 12;
        }
        ArrayList arrayList = new ArrayList();
        int e = e(device, list, d, arrayList);
        if (e != 0) {
            return e;
        }
        int b = this.e.b(device.getUuid(), list, tpkVar);
        if (b != 0) {
            tos.e("CommonDeviceManager", "registerMonitor handleMonitorCallbackProxy failed, result: " + b);
            e(d, device.getUuid(), arrayList);
            return b;
        }
        if (this.f17286a == null) {
            tos.a("CommonDeviceManager", "new monitorCallback");
            this.f17286a = m();
        }
        int subscribeMonitorListener = d.subscribeMonitorListener(this.f17286a);
        if (subscribeMonitorListener != 0) {
            tos.e("CommonDeviceManager", "registerMonitor subscribeMonitorListener failed, result: " + subscribeMonitorListener);
            this.e.d(tpkVar);
            e(d, device.getUuid(), arrayList);
        }
        return subscribeMonitorListener;
    }

    private MonitorCallback m() {
        return new MonitorCallback.Stub() { // from class: com.huawei.wearengine.core.device.CommonDeviceManager$10
            @Override // com.huawei.wearengine.monitor.MonitorCallback
            public void onChanged(int i, MonitorMessage monitorMessage) throws RemoteException {
                boolean b;
                tos.a("CommonDeviceManager", "MonitorCallback.onChanged errorCode is:" + i);
                tos.b("CommonDeviceManager", "onDataReceived: data=" + monitorMessage);
                toz.this.e.a(i, monitorMessage);
                b = toz.this.b(i, monitorMessage);
                if (b) {
                    toz.this.e(monitorMessage);
                }
            }
        };
    }

    private int e(Device device, List<MonitorItem> list, VirtualDevice virtualDevice, List<String> list2) {
        for (MonitorItem monitorItem : list) {
            if (!this.e.e(monitorItem.getName())) {
                int e = e(virtualDevice, 1, device.getUuid(), monitorItem.getName());
                if (e != 0) {
                    tos.e("CommonDeviceManager", "registerMonitor open switch fail");
                    tos.b("CommonDeviceManager", "device id: " + device.getUuid());
                    e(virtualDevice, device.getUuid(), list2);
                    return e;
                }
                list2.add(monitorItem.getName());
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(int i, MonitorMessage monitorMessage) {
        return monitorMessage != null && !TextUtils.isEmpty(monitorMessage.getMonitorItemType()) && monitorMessage.getMonitorItemType().equals(MonitorItem.MONITOR_ITEM_CONNECTION.getName()) && monitorMessage.getIntData() == 2 && i == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final MonitorMessage monitorMessage) {
        if (monitorMessage == null) {
            return;
        }
        this.c.execute(new Runnable() { // from class: toz.3
            @Override // java.lang.Runnable
            public void run() {
                String deviceId = monitorMessage.getDeviceId();
                Set<String> a2 = toz.this.e.a(deviceId);
                if (a2.size() == 0) {
                    tos.b("CommonDeviceManager", "reopenSwitchStatus monitorItemTypeSet is empty");
                    return;
                }
                a2.remove(MonitorItem.MONITOR_ITEM_CONNECTION.getName());
                a2.remove(MonitorItem.MONITOR_ITEM_POWER_MODE.getName());
                if (a2.size() == 0) {
                    tos.b("CommonDeviceManager", "reopenSwitchStatus this device not other monitor type except connect");
                    return;
                }
                Device device = new Device();
                device.setProductType(monitorMessage.getProductType());
                device.setUuid(deviceId);
                VirtualDevice d = toy.b().d(device);
                if (d == null) {
                    tos.e("CommonDeviceManager", "reopenSwitchStatus deviceAdapter is null");
                    return;
                }
                for (String str : a2) {
                    tos.a("CommonDeviceManager", "reopenSwitchStatus " + str + " result:" + toz.this.e(d, 1, deviceId, str));
                }
            }
        });
    }

    private void e(VirtualDevice virtualDevice, String str, List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            e(virtualDevice, 2, str, it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int e(VirtualDevice virtualDevice, int i, String str, String str2) {
        tos.a("CommonDeviceManager", "switchMonitorStatus monitorItem:" + str2 + ", switchStatus:" + i);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            tos.e("CommonDeviceManager", "switchMonitorStatus parameters is invalid");
            return 5;
        }
        if (this.e.e(str2)) {
            tos.a("CommonDeviceManager", "switchMonitorStatus monitorItem is defaultOpenStatus, return");
            return 0;
        }
        final int[] iArr = {12};
        final tou touVar = new tou(1);
        int switchMonitorStatus = virtualDevice.switchMonitorStatus(i, str, str2, new SwitchStatusCallback.Stub() { // from class: com.huawei.wearengine.core.device.CommonDeviceManager$12
            @Override // com.huawei.wearengine.monitor.SwitchStatusCallback
            public void onResult(int i2) {
                iArr[0] = i2;
                touVar.onFinish();
            }
        });
        if (switchMonitorStatus != 0) {
            tos.e("CommonDeviceManager", "deviceAdapter switchMonitorStatus failed, result: " + switchMonitorStatus);
            return switchMonitorStatus;
        }
        try {
            if (touVar.e(5000L, TimeUnit.MILLISECONDS)) {
                return iArr[0];
            }
            tos.e("CommonDeviceManager", "switchMonitorStatus asyncToSyncListener timeout");
            return 12;
        } catch (InterruptedException unused) {
            tos.e("CommonDeviceManager", "asynToSyncListener await error");
            return 12;
        }
    }

    private boolean a(Device device, List<MonitorItem> list, tpk tpkVar) {
        if (device == null || TextUtils.isEmpty(device.getUuid()) || tpkVar == null || list == null || list.size() == 0) {
            return false;
        }
        for (MonitorItem monitorItem : list) {
            if (monitorItem == null || TextUtils.isEmpty(monitorItem.getName())) {
                return false;
            }
        }
        return true;
    }

    public int e(tpk tpkVar) {
        tos.a("CommonDeviceManager", "enter unregisterMonitor");
        if (tpkVar == null) {
            tos.e("CommonDeviceManager", "unregisterMonitor monitorCallbackProxy is null");
            return 5;
        }
        Map<String, List<String>> c = this.e.c(tpkVar);
        VirtualDevice d = toy.b().d(tpkVar.c());
        if (d == null) {
            tos.e("CommonDeviceManager", "deviceAdapter is null");
            return 12;
        }
        for (Map.Entry<String, List<String>> entry : c.entrySet()) {
            int b = b(d, entry.getKey(), entry.getValue());
            if (b != 0) {
                tos.a("CommonDeviceManager", "unregisterMonitor closeMonitorStatusByUnregister result: " + b);
            }
        }
        this.e.d(tpkVar);
        tos.a("CommonDeviceManager", "unregisterMonitor removeMonitorCallback result: " + l());
        return 0;
    }

    private int b(VirtualDevice virtualDevice, String str, List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            int e = e(virtualDevice, 2, str, it.next());
            if (e != 0) {
                return e;
            }
        }
        return 0;
    }

    public tox f() {
        return this.e;
    }

    private int l() {
        int unsubscribeMonitorListener;
        if (this.e.e() != 0) {
            return 0;
        }
        int i = 0;
        for (VirtualDevice virtualDevice : toy.b().a()) {
            if (virtualDevice != null && (unsubscribeMonitorListener = virtualDevice.unsubscribeMonitorListener()) != 0) {
                i = unsubscribeMonitorListener;
            }
        }
        this.f17286a = null;
        return i;
    }

    public void g() {
        tos.a("CommonDeviceManager", "unregisterMonitor enter");
        this.e.c();
        l();
    }

    public void e(String str) {
        tos.a("CommonDeviceManager", "clearDiedMonitorByClientName enter");
        this.e.d(str);
        l();
    }

    public void b(String str) {
        this.e.b(str);
        l();
    }

    public MonitorData b(Device device, MonitorItem monitorItem) throws RemoteException {
        if (device == null || monitorItem == null) {
            tos.e("CommonDeviceManager", "queryMonitorData parameters is invalid");
            throw new IllegalStateException(String.valueOf(5));
        }
        tos.a("CommonDeviceManager", "enter queryMonitorData, monitorItem:" + monitorItem.getName());
        VirtualDevice d = toy.b().d(device);
        if (d != null) {
            MonitorData[] monitorDataArr = new MonitorData[1];
            tou touVar = new tou(1);
            d.queryMonitorData(device, monitorItem.getName(), d(monitorDataArr, touVar));
            try {
                if (touVar.e(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS)) {
                    return monitorDataArr[0];
                }
                tos.e("CommonDeviceManager", "queryMonitorData asyncToSyncListener timeout");
                throw new IllegalStateException(String.valueOf(12));
            } catch (InterruptedException unused) {
                tos.e("CommonDeviceManager", "asynToSyncListener await error");
                throw new IllegalStateException(String.valueOf(12));
            }
        }
        tos.e("CommonDeviceManager", "queryMonitorData deviceAdapter is null");
        throw new IllegalStateException(String.valueOf(12));
    }

    private QueryDataCallback d(final MonitorData[] monitorDataArr, final tou touVar) {
        return new QueryDataCallback.Stub() { // from class: com.huawei.wearengine.core.device.CommonDeviceManager$13
            @Override // com.huawei.wearengine.monitor.QueryDataCallback
            public void onDataReceived(int i, MonitorMessage monitorMessage) {
                if (i == 0) {
                    monitorDataArr[0] = toz.this.e.e(monitorMessage);
                }
                touVar.onFinish();
            }
        };
    }

    public void c(Device device, NotificationParcel notificationParcel, NotifySendCallback notifySendCallback) {
        if (device == null || notificationParcel == null || notifySendCallback == null) {
            tos.e("CommonDeviceManager", "notifyData parameters is invalid");
            return;
        }
        VirtualDevice d = toy.b().d(device);
        if (d != null) {
            d.notifyData(device, notificationParcel, notifySendCallback);
        }
    }

    public int b(int i, ServiceConnectCallback serviceConnectCallback) {
        return a("registerConnectCallback", i, serviceConnectCallback);
    }

    public int d(int i, ServiceConnectCallback serviceConnectCallback) {
        return a("unregisterConnectCallback", i, serviceConnectCallback);
    }

    public void h() {
        for (VirtualDevice virtualDevice : toy.b().a()) {
            virtualDevice.setP2pInnerApi(this.d);
        }
    }

    public int e(int i) {
        this.i.b(i);
        return o();
    }

    public int a(String str) {
        this.i.b(str);
        return o();
    }

    private void c(IdentityInfo identityInfo, IdentityInfo identityInfo2, Device device, int i) {
        if (device.getUuid().isEmpty()) {
            tos.e("CommonDeviceManager", "device Uuid is invalid");
            return;
        }
        StringBuilder sb = new StringBuilder(512);
        if (identityInfo.getPackageName().length() >= 128 || identityInfo2.getFingerPrint().length() >= 128 || device.getUuid().length() >= 128) {
            return;
        }
        if (i == 0) {
            sb.append("registerReceiver srcPkgName is:");
        } else if (i == 1) {
            sb.append("handleReceive srcPkgName is:");
        }
        sb.append(identityInfo.getPackageName());
        sb.append(", srcFingerPrint is:");
        sb.append(tri.e(identityInfo.getFingerPrint()));
        sb.append(", destPkgName is:");
        sb.append(identityInfo2.getPackageName());
        sb.append(", destFingerPrint is:");
        sb.append(tri.e(identityInfo2.getFingerPrint()));
        sb.append(", deviceId is:");
        sb.append(tri.e(device.getUuid()));
        tos.a("CommonDeviceManager", sb.toString());
    }

    public int c(Device device, String str, String str2) {
        tom.e(device, "device must not be null!");
        tom.c(str, "srcPkgName must not be null!");
        tom.c(str2, "destPkgName must not be null!");
        VirtualDevice d = toy.b().d(device);
        if (d == null) {
            tos.e("CommonDeviceManager", "getDeviceAppVersionCode deviceAdapter is null");
            throw new IllegalStateException(String.valueOf(12));
        }
        final int[] iArr = {-1};
        final tou touVar = new tou(1);
        d.getDeviceAppVersionCode(device, str, str2, new P2pPingCallback.Stub() { // from class: com.huawei.wearengine.core.device.CommonDeviceManager$14
            @Override // com.huawei.wearengine.p2p.P2pPingCallback
            public void onResult(int i) {
                iArr[0] = i;
                touVar.onFinish();
            }
        });
        try {
            if (touVar.e(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS)) {
                return iArr[0];
            }
            tos.e("CommonDeviceManager", "getDeviceAppVersionCode asyncToSyncListener timeout");
            throw new IllegalStateException(String.valueOf(12));
        } catch (InterruptedException unused) {
            tos.e("CommonDeviceManager", "getDeviceAppVersionCode asynToSyncListener await error");
            throw new IllegalStateException(String.valueOf(12));
        }
    }

    public int b(Device device, FileIdentificationParcel fileIdentificationParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pCancelFileTransferCallBack p2pCancelFileTransferCallBack) {
        tom.e(device, "device must not be null!");
        tom.e(fileIdentificationParcel, "fileIdentification must not be null!");
        tom.e(identityInfo, "srcInfo must not be null!");
        tom.e(identityInfo2, "destInfo must not be null!");
        tom.e(p2pCancelFileTransferCallBack, "p2pCancelFileTransferCallBack must not be null!");
        VirtualDevice d = toy.b().d(device);
        if (d == null) {
            tos.e("CommonDeviceManager", "cancelFileTransfer deviceAdapter is null");
            return 12;
        }
        return d.cancelFileTransfer(device, fileIdentificationParcel, identityInfo, identityInfo2, p2pCancelFileTransferCallBack);
    }

    public void c(DeviceListBinderCallback deviceListBinderCallback) {
        tom.e(deviceListBinderCallback, "deviceListBinderCallback must not be null");
        Device device = new Device();
        device.setProductType(0);
        VirtualDevice d = toy.b().d(device);
        if (d == null) {
            tos.e("CommonDeviceManager", "getConnectedDevices deviceAdapter is null");
            throw new IllegalStateException(String.valueOf(12));
        }
        final ArrayList arrayList = new ArrayList();
        final tou touVar = new tou(1);
        d.getConnectedDevices(new IDeviceListCallback.Stub() { // from class: toz.2
            @Override // com.huawei.hwotamanager.IDeviceListCallback
            public void onDeviceListObtain(String str) {
                arrayList.addAll(toz.this.d(str));
                touVar.onFinish();
            }
        });
        try {
            if (!touVar.e(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS)) {
                tos.e("CommonDeviceManager", "getConnectedDevices asyncToSyncListener timeout");
            }
            deviceListBinderCallback.onDeviceListObtain(arrayList);
        } catch (RemoteException unused) {
            tos.e("CommonDeviceManager", "getConnectedDevices RemoteException");
            throw new IllegalStateException(String.valueOf(12));
        } catch (InterruptedException unused2) {
            tos.e("CommonDeviceManager", "asynToSyncListener await error");
            throw new IllegalStateException(String.valueOf(12));
        } catch (Exception unused3) {
            tos.e("CommonDeviceManager", "getConnectedDevices deviceListBinderCallback Exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Device> d(String str) {
        ArrayList arrayList = new ArrayList();
        JSONArray b = trk.b(str, "deviceList");
        if (b != null) {
            if (b.length() == 0) {
                tos.a("CommonDeviceManager", "parseDevice deviceList is empty");
                return arrayList;
            }
            for (int i = 0; i < b.length(); i++) {
                JSONObject e = trk.e(b, i);
                Device device = new Device();
                device.setName(trk.b(e, "name"));
                device.setUuid(trk.b(e, "uuid"));
                device.setModel(trk.b(e, "model"));
                device.setProductType(0);
                device.setConnectState(trk.a(e, BleConstants.KEY_CONNECTSTATE, 2));
                tow d = new tow.b().e("").b(trk.b(e, "version")).a(trk.c(e, "supportOTA", false)).a(trk.b(e, KnitFragment.KEY_EXTRA)).d();
                device.setReservedness(d == null ? "{}" : d.toString());
                arrayList.add(device);
            }
            tos.a("CommonDeviceManager", " devicesList size is " + arrayList.size());
            return arrayList;
        }
        tos.d("CommonDeviceManager", " parseDevice deviceList is null");
        return arrayList;
    }

    public void e(Device device, CheckBinderCallback checkBinderCallback) {
        tom.e(device, "device must not be null!");
        tom.e(checkBinderCallback, "checkBinderCallback must not be null");
        VirtualDevice d = toy.b().d(device);
        if (d == null) {
            tos.e("CommonDeviceManager", "getDeviceNewVersion deviceAdapter is null");
            throw new IllegalStateException(String.valueOf(12));
        }
        final String[] strArr = {device.getUuid(), ""};
        final tou touVar = new tou(1);
        d.getDeviceNewVersion(device, new ICheckCallback.Stub() { // from class: toz.5
            @Override // com.huawei.hwotamanager.ICheckCallback
            public void onCheckComplete(String str, String str2) {
                tos.a("CommonDeviceManager", "getDeviceNewVersion resultInfo :" + str2);
                JSONObject d2 = trk.d(str2, "newVersionInfo");
                if (str != null && d2 != null) {
                    String[] strArr2 = strArr;
                    strArr2[0] = str;
                    strArr2[1] = d2.toString();
                    tos.a("CommonDeviceManager", " getDeviceNewVersion newVersionInfo is not null");
                }
                touVar.onFinish();
            }
        });
        try {
            if (!touVar.e(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS)) {
                tos.e("CommonDeviceManager", "getDeviceNewVersion asyncToSyncListener timeout");
                throw new IllegalStateException(String.valueOf(19));
            }
            if (TextUtils.equals(strArr[0], device.getUuid())) {
                tos.a("CommonDeviceManager", "getDeviceNewVersion callback resultInfo:" + strArr[1]);
                checkBinderCallback.onCheckComplete(device, strArr[1]);
                return;
            }
            tos.d("CommonDeviceManager", "getDeviceNewVersion device uuid is't equal");
        } catch (RemoteException unused) {
            tos.e("CommonDeviceManager", "getDeviceNewVersion RemoteException");
            throw new IllegalStateException(String.valueOf(12));
        } catch (IllegalStateException e) {
            throw e;
        } catch (InterruptedException unused2) {
            tos.e("CommonDeviceManager", "asynToSyncListener await error");
            throw new IllegalStateException(String.valueOf(12));
        } catch (Exception unused3) {
            tos.e("CommonDeviceManager", "getDeviceNewVersion checkBinderCallback Exception");
        }
    }

    public void e(Device device, String str, UpgradeBinderCallBack upgradeBinderCallBack) {
        tom.e(device, "device must not be null!");
        if (TextUtils.isEmpty(str)) {
            tos.e("CommonDeviceManager", "doUpgrade command is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        tom.e(upgradeBinderCallBack, "upgradeBinderCallBack must not be null");
        VirtualDevice d = toy.b().d(device);
        if (d == null) {
            tos.e("CommonDeviceManager", "doUpgrade deviceAdapter is null");
            throw new IllegalStateException(String.valueOf(12));
        }
        final String[] strArr = {device.getUuid(), ""};
        final tou touVar = new tou(1);
        d.doUpgrade(device, str, new IUpgradeCallBack.Stub() { // from class: toz.4
            @Override // com.huawei.hwotamanager.IUpgradeCallBack
            public void onUpgradeStatus(String str2, String str3) {
                tos.a("CommonDeviceManager", "doUpgrade upgradeStatus:" + str3);
                if (str2 != null && str3 != null) {
                    String[] strArr2 = strArr;
                    strArr2[0] = str2;
                    strArr2[1] = str3;
                }
                touVar.onFinish();
            }
        });
        try {
            b(device, upgradeBinderCallBack, strArr, touVar);
        } catch (RemoteException unused) {
            tos.e("CommonDeviceManager", "doUpgrade RemoteException");
            throw new IllegalStateException(String.valueOf(12));
        } catch (IllegalStateException e) {
            throw e;
        } catch (InterruptedException unused2) {
            tos.e("CommonDeviceManager", "asynToSyncListener await error");
            throw new IllegalStateException(String.valueOf(12));
        } catch (Exception unused3) {
            tos.e("CommonDeviceManager", "doUpgrade upgradeBinderCallBack Exception");
        }
    }

    private void b(Device device, UpgradeBinderCallBack upgradeBinderCallBack, String[] strArr, tou touVar) throws InterruptedException, RemoteException {
        if (!touVar.e(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS)) {
            tos.e("CommonDeviceManager", "doUpgrade asyncToSyncListener timeout");
            throw new IllegalStateException(String.valueOf(19));
        }
        if (TextUtils.equals(strArr[0], device.getUuid())) {
            tos.a("CommonDeviceManager", "doUpgrade callback upgradeStatus:" + strArr[1]);
            upgradeBinderCallBack.onUpgradeStatus(device, strArr[1]);
            return;
        }
        tos.d("CommonDeviceManager", "doUpgrade device uuid is't equal");
    }

    public void a(Device device, UpgradeStatusBinderCallBack upgradeStatusBinderCallBack) {
        tom.e(device, "device must not be null!");
        tom.e(upgradeStatusBinderCallBack, "upgradeStatusBinderCallBack must not be null");
        VirtualDevice d = toy.b().d(device);
        if (d == null) {
            tos.e("CommonDeviceManager", "getUpgradeStatus deviceAdapter is null");
            throw new IllegalStateException(String.valueOf(12));
        }
        final String[] strArr = {device.getUuid(), ""};
        final tou touVar = new tou(1);
        d.getUpgradeStatus(device, new IUpgradeStatusCallBack.Stub() { // from class: toz.8
            @Override // com.huawei.hwotamanager.IUpgradeStatusCallBack
            public void onStatus(String str, String str2) {
                tos.a("CommonDeviceManager", "getUpgradeStatus upgradeStatus:" + str2);
                if (str != null && str2 != null) {
                    String[] strArr2 = strArr;
                    strArr2[0] = str;
                    strArr2[1] = str2;
                }
                touVar.onFinish();
            }
        });
        try {
            if (!touVar.e(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS)) {
                tos.e("CommonDeviceManager", "getUpgradeStatus asyncToSyncListener timeout");
                throw new IllegalStateException(String.valueOf(19));
            }
            if (TextUtils.equals(strArr[0], device.getUuid())) {
                tos.a("CommonDeviceManager", "getUpgradeStatus callback upgradeStatus:" + strArr[1]);
                upgradeStatusBinderCallBack.onStatus(device, strArr[1]);
                return;
            }
            tos.d("CommonDeviceManager", "getUpgradeStatus device uuid is't equal");
        } catch (RemoteException unused) {
            tos.e("CommonDeviceManager", "getUpgradeStatus RemoteException");
            throw new IllegalStateException(String.valueOf(12));
        } catch (IllegalStateException e) {
            throw e;
        } catch (InterruptedException unused2) {
            tos.e("CommonDeviceManager", "asynToSyncListener await error");
            throw new IllegalStateException(String.valueOf(12));
        } catch (Exception unused3) {
            tos.e("CommonDeviceManager", "getUpgradeStatus upgradeStatusBinderCallBack Exception");
        }
    }

    public void c(final Device device, final UpgradeStatusBinderCallBack upgradeStatusBinderCallBack) {
        tom.e(device, "device must not be null!");
        tom.e(upgradeStatusBinderCallBack, "upgradeStatusBinderCallBack must not be null");
        VirtualDevice d = toy.b().d(device);
        if (d == null) {
            tos.e("CommonDeviceManager", "registerUpgradeListener deviceAdapter is null");
            throw new IllegalStateException(String.valueOf(12));
        }
        d.registerUpgradeListener(device, new IUpgradeStatusCallBack.Stub() { // from class: toz.10
            @Override // com.huawei.hwotamanager.IUpgradeStatusCallBack
            public void onStatus(String str, String str2) {
                toz.this.c(str, str2, device, upgradeStatusBinderCallBack);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2, Device device, UpgradeStatusBinderCallBack upgradeStatusBinderCallBack) {
        tos.a("CommonDeviceManager", "registerUpgradeListener upgradeStatus:" + str2);
        try {
            if (TextUtils.equals(str, device.getUuid())) {
                upgradeStatusBinderCallBack.onStatus(device, str2);
            } else {
                tos.d("CommonDeviceManager", "registerUpgradeListener device uuid is't equal");
            }
        } catch (RemoteException unused) {
            tos.e("CommonDeviceManager", "registerUpgradeListener RemoteException");
            throw new IllegalStateException(String.valueOf(12));
        } catch (Exception unused2) {
            tos.e("CommonDeviceManager", "registerUpgradeListener upgradeStatusBinderCallBack Exception");
        }
    }

    public void a(Device device) {
        tom.e(device, "device must not be null!");
        VirtualDevice d = toy.b().d(device);
        if (d == null) {
            tos.e("CommonDeviceManager", "unRegisterUpgradeListener deviceAdapter is null");
            throw new IllegalStateException(String.valueOf(12));
        }
        d.unRegisterUpgradeListener(device);
    }

    public int c(int i, ServiceConnectCallback serviceConnectCallback) {
        return a("registerOtaServiceConnectCallback", i, serviceConnectCallback);
    }

    public int a(int i, ServiceConnectCallback serviceConnectCallback) {
        return a("unregisterOtaServiceConnectCallback", i, serviceConnectCallback);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int a(String str, int i, ServiceConnectCallback serviceConnectCallback) {
        char c;
        int b;
        tpf b2 = tpf.b();
        str.hashCode();
        switch (str.hashCode()) {
            case -1999273428:
                if (str.equals("registerConnectCallback")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1590673229:
                if (str.equals("registerOtaServiceConnectCallback")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -437900998:
                if (str.equals("unregisterOtaServiceConnectCallback")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 999561075:
                if (str.equals("unregisterConnectCallback")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            b = b2.b(serviceConnectCallback);
        } else if (c == 1) {
            b = b2.c(serviceConnectCallback);
        } else if (c != 2) {
            b = c != 3 ? 12 : b2.d(serviceConnectCallback);
        } else {
            b = b2.e(serviceConnectCallback);
        }
        tos.a("CommonDeviceManager", "handlePhoneServiceConnect: " + str + ", " + i + ", " + b);
        return (i < 11 && b == 0) ? c(str, serviceConnectCallback) : b;
    }

    private int c(String str, ServiceConnectCallback serviceConnectCallback) {
        char c;
        int i = 12;
        try {
            try {
                ServiceConnectCallbackManager k = k();
                switch (str.hashCode()) {
                    case -1999273428:
                        if (str.equals("registerConnectCallback")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1590673229:
                        if (str.equals("registerOtaServiceConnectCallback")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case -437900998:
                        if (str.equals("unregisterOtaServiceConnectCallback")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case 999561075:
                        if (str.equals("unregisterConnectCallback")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                if (c == 0) {
                    i = k.registerConnectCallback(serviceConnectCallback);
                } else if (c == 1) {
                    i = k.unregisterConnectCallback(serviceConnectCallback);
                } else if (c == 2) {
                    i = k.registerOtaServiceConnectCallback(serviceConnectCallback);
                } else if (c == 3) {
                    i = k.unregisterOtaServiceConnectCallback(serviceConnectCallback);
                }
                this.f.c();
                tos.a("CommonDeviceManager", "handleDaemonServiceConnect: " + str + ", " + i);
                return i;
            } catch (RemoteException unused) {
                tos.e("CommonDeviceManager", "handleDaemonServiceConnect " + str + " has RemoteException");
                throw new IllegalStateException(String.valueOf(12));
            }
        } catch (Throwable th) {
            this.f.c();
            throw th;
        }
    }

    private ServiceConnectCallbackManager k() {
        IBinder ffl_ = this.f.ffl_();
        if (ffl_ == null) {
            tos.e("CommonDeviceManager", "getServiceConnectCallbackManager serviceBinder is null");
            throw new IllegalStateException(String.valueOf(12));
        }
        return ServiceConnectCallbackManager.Stub.asInterface(ffl_);
    }

    public void e() {
        Device device = new Device();
        device.setProductType(0);
        VirtualDevice d = toy.b().d(device);
        if (d == null) {
            tos.e("CommonDeviceManager", "disconnectOtaService deviceAdapter is null");
            throw new IllegalStateException(String.valueOf(12));
        }
        d.disconnectOtaService();
    }
}
