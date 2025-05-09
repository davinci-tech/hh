package defpackage;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.device.api.PluginDeviceApi;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearengine.HwWearEngineNative;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.device.FoundListener;
import com.huawei.wearengine.device.GetAttributeListener;
import com.huawei.wearengine.p2p.IdentityInfo;
import com.huawei.wearengine.p2p.MessageParcel;
import com.huawei.wearengine.p2p.P2pPingCallback;
import com.huawei.wearengine.p2p.P2pReceiverCallback;
import com.huawei.wearengine.p2p.P2pSendCallback;
import com.huawei.wearengine.p2p.ReceiveResultCallback;
import defpackage.tnv;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class tnv extends HwBaseManager implements ParserInterface {
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private Map<Integer, Object> f17269a;
    private int b;
    private P2pReceiverCallback d;
    private HwWearEngineNative e;

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private static final tnv f17271a = new tnv();
    }

    private tnv() {
        super(BaseApplication.getContext());
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
        this.f17269a = concurrentHashMap;
        this.d = null;
        this.b = 1;
        concurrentHashMap.clear();
        LogUtil.a("WearEngine_HwHiWearKitManager", "enter HwHiWearKitManager");
    }

    public static tnv d() {
        return e.f17271a;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 52;
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        LogUtil.a("WearEngine_HwHiWearKitManager", "getResult");
        if (deviceInfo != null && bArr != null && bArr.length >= 2) {
            LogUtil.a("WearEngine_HwHiWearKitManager", "HwHiWearKitManager getResult dataInfos is:", Arrays.toString(bArr));
            if (bArr[1] == 1) {
                c(deviceInfo, bArr);
                return;
            } else {
                LogUtil.a("WearEngine_HwHiWearKitManager", "other commandID");
                return;
            }
        }
        LogUtil.h("WearEngine_HwHiWearKitManager", "device or dataContents is null");
    }

    private void c(DeviceInfo deviceInfo, byte[] bArr) {
        List<cwd> a2 = tqy.a(bArr);
        if (a2 == null || a2.size() <= 0) {
            return;
        }
        d(deviceInfo, a2);
    }

    private void d(DeviceInfo deviceInfo, List<cwd> list) {
        cwd cwdVar = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        byte[] bArr = null;
        int i = -1;
        int i2 = -1;
        for (cwd cwdVar2 : list) {
            switch (CommonUtil.w(cwdVar2.e())) {
                case 1:
                    i2 = tqy.b(cwdVar2);
                    break;
                case 2:
                    i = tqy.b(cwdVar2);
                    break;
                case 3:
                    str = cvx.e(cwdVar2.c());
                    break;
                case 4:
                    str2 = cvx.e(cwdVar2.c());
                    break;
                case 5:
                    str3 = cvx.e(cwdVar2.c());
                    break;
                case 6:
                    str4 = cvx.e(cwdVar2.c());
                    break;
                case 7:
                    bArr = cvx.a(cwdVar2.c());
                    break;
                case 8:
                    cwdVar = cwdVar2;
                    break;
            }
        }
        if (i2 == -1) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "handResponseCallback subCmd is invalid");
            return;
        }
        if (i2 == 3) {
            c(cwdVar, i);
        } else if (i2 == 2) {
            a(deviceInfo, bArr, i, new IdentityInfo(str, str3), new IdentityInfo(str2, str4));
        } else {
            c(deviceInfo, i, str, str2);
        }
    }

    private void c(DeviceInfo deviceInfo, int i, String str, String str2) {
        LogUtil.a("WearEngine_HwHiWearKitManager", "handDevicePingCommand enter");
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (TextUtils.isEmpty(deviceIdentify)) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "handDevicePingCommand device identify is null");
            return;
        }
        DeviceCommand e2 = trc.e(i, new IdentityInfo(str2, ""), new IdentityInfo(str, ""), 1, 203);
        if (e2 == null) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "handDevicePingCommand deviceCommand is null");
            return;
        }
        if (trc.a(str2) == 205) {
            trc.d(str2);
        }
        LogUtil.c("WearEngine_HwHiWearKitManager", "handDevicePingCommand deviceCommand is:", e2.toString());
        e2.setmIdentify(deviceIdentify);
        jsz.b(BaseApplication.getContext()).sendDeviceData(e2);
    }

    private void a(DeviceInfo deviceInfo, byte[] bArr, int i, IdentityInfo identityInfo, IdentityInfo identityInfo2) {
        synchronized (this) {
            LogUtil.a("WearEngine_HwHiWearKitManager", "handDeviceSendCommand enter");
            if (this.d == null) {
                LogUtil.b("WearEngine_HwHiWearKitManager", "handDeviceSendCommand mP2pReceiverCallback is null");
                d(deviceInfo, i, identityInfo, identityInfo2, 206);
                return;
            }
            Device c2 = tqy.c(deviceInfo);
            if (c2 == null) {
                LogUtil.b("WearEngine_HwHiWearKitManager", "handDeviceSendCommand HiWearDevice is null");
                return;
            }
            if (identityInfo == null || identityInfo2 == null) {
                LogUtil.b("WearEngine_HwHiWearKitManager", "handDeviceSendCommand pkgInfo is null");
                return;
            }
            if (bArr != null && !TextUtils.isEmpty(identityInfo.getPackageName()) && !TextUtils.isEmpty(identityInfo2.getPackageName())) {
                d(c2, bArr, new tqd(identityInfo, identityInfo2), i, deviceInfo);
                return;
            }
            LogUtil.b("WearEngine_HwHiWearKitManager", "handDeviceSendCommand message or package name is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(DeviceInfo deviceInfo, int i, IdentityInfo identityInfo, IdentityInfo identityInfo2, int i2) {
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (TextUtils.isEmpty(deviceIdentify)) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "sendDeviceCommand device identify is null");
            return;
        }
        DeviceCommand e2 = trc.e(i, identityInfo2, identityInfo, 2, i2);
        if (e2 == null) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "sendDeviceCommand deviceCommand is null");
            return;
        }
        LogUtil.c("WearEngine_HwHiWearKitManager", "sendDeviceCommand deviceCommand is:", e2.toString());
        e2.setmIdentify(deviceIdentify);
        jsz.b(BaseApplication.getContext()).sendDeviceData(e2);
    }

    private void d(Device device, byte[] bArr, tqd tqdVar, final int i, final DeviceInfo deviceInfo) {
        synchronized (this) {
            final IdentityInfo a2 = tqdVar.a();
            final IdentityInfo b = tqdVar.b();
            MessageParcel a3 = a(bArr);
            P2pReceiverCallback p2pReceiverCallback = this.d;
            if (p2pReceiverCallback == null) {
                LogUtil.h("WearEngine_HwHiWearKitManager", "mP2pReceiverCallback is null");
            } else {
                try {
                    p2pReceiverCallback.onDataReceived(device, a3, a2, b, new ReceiveResultCallback.Stub() { // from class: com.huawei.wearengine.HwHiWearKitManager$1
                        @Override // com.huawei.wearengine.p2p.ReceiveResultCallback
                        public void onReceiveResult(int i2) {
                            tnv.this.d(deviceInfo, i, a2, b, i2);
                        }
                    });
                } catch (RemoteException unused) {
                    LogUtil.b("WearEngine_HwHiWearKitManager", "handleDataReceive RemoteException");
                }
            }
        }
    }

    private MessageParcel a(byte[] bArr) {
        MessageParcel messageParcel = new MessageParcel();
        messageParcel.setType(1);
        messageParcel.setData(bArr);
        return messageParcel;
    }

    private void c(cwd cwdVar, int i) {
        if (cwdVar == null) {
            LogUtil.h("WearEngine_HwHiWearKitManager", "responseTlv is null");
            return;
        }
        if (i == -1) {
            LogUtil.h("WearEngine_HwHiWearKitManager", "result sequenceNum is error");
            return;
        }
        String c2 = cwdVar.c();
        LogUtil.a("WearEngine_HwHiWearKitManager", "handleResultCallback resultString is:", c2);
        LogUtil.a("WearEngine_HwHiWearKitManager", "handleResultCallback sequenceNum is:", Integer.valueOf(i));
        int w = !TextUtils.isEmpty(c2) ? CommonUtil.w(c2) : 203;
        LogUtil.a("WearEngine_HwHiWearKitManager", "handleResultCallback result int is:", Integer.valueOf(w));
        Object remove = this.f17269a.remove(Integer.valueOf(i));
        if (remove == null) {
            LogUtil.h("WearEngine_HwHiWearKitManager", "handleResultCallback is null");
            return;
        }
        if (remove instanceof P2pPingCallback) {
            b(w, (P2pPingCallback) remove);
        } else if (remove instanceof P2pSendCallback) {
            c(w, (P2pSendCallback) remove);
        } else {
            LogUtil.h("WearEngine_HwHiWearKitManager", "handleResultCallback callback not target class");
        }
    }

    private void b(int i, P2pPingCallback p2pPingCallback) {
        LogUtil.a("WearEngine_HwHiWearKitManager", "handleResultCallback is P2pPingCallback");
        try {
            p2pPingCallback.onResult(i);
        } catch (RemoteException unused) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "handleP2pPingCallback RemoteException");
        } catch (Exception unused2) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "handleP2pPingCallback p2pPingCallback has exception");
        }
    }

    private void c(int i, P2pSendCallback p2pSendCallback) {
        LogUtil.a("WearEngine_HwHiWearKitManager", "handleResultCallback is P2pSendCallback");
        try {
            p2pSendCallback.onSendResult(i);
            if (i != 207 && i != 209) {
                p2pSendCallback.onSendProgress(0L);
            }
            p2pSendCallback.onSendProgress(100L);
        } catch (RemoteException unused) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "handleP2pSendCallback RemoteException");
        } catch (Exception unused2) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "handleP2pSendCallback p2pSendCallback has exception");
        }
    }

    public void e(FoundListener foundListener, String str, boolean z) {
        LogUtil.a("WearEngine_HwHiWearKitManager", "foundDevices enter");
        if (foundListener == null) {
            LogUtil.h("WearEngine_HwHiWearKitManager", "listener is null");
            return;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "WearEngine_HwHiWearKitManager");
        if (!z && deviceList != null) {
            LogUtil.a("WearEngine_HwHiWearKitManager", "enter non-white filter");
            List<DeviceInfo> e2 = tqy.e(deviceList);
            deviceList.clear();
            deviceList.addAll(e2);
        }
        if (deviceList != null) {
            try {
                if (!deviceList.isEmpty()) {
                    LogUtil.a("WearEngine_HwHiWearKitManager", "foundDevices device is ok");
                    foundListener.onDeviceFound(tqy.c(deviceList, str));
                    return;
                }
            } catch (RemoteException unused) {
                LogUtil.b("WearEngine_HwHiWearKitManager", "foundDevices remoteException");
                return;
            }
        }
        LogUtil.h("WearEngine_HwHiWearKitManager", "discoverDevices device is empty");
        foundListener.onDeviceFound(new ArrayList(16));
    }

    public void a(FoundListener foundListener, String str) {
        LogUtil.a("WearEngine_HwHiWearKitManager", "foundAllDevices enter");
        if (foundListener == null) {
            LogUtil.h("WearEngine_HwHiWearKitManager", "foundAllDevices listener is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "WearEngine_HwHiWearKitManager");
        if (deviceList != null && deviceList.size() > 0) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (it.hasNext()) {
                arrayList.add(tqy.d(it.next()));
            }
        }
        List<cjv> thirdDeviceList = ((PluginDeviceApi) Services.c("PluginDevice", PluginDeviceApi.class)).getThirdDeviceList();
        if (thirdDeviceList != null && thirdDeviceList.size() > 0) {
            Iterator<cjv> it2 = thirdDeviceList.iterator();
            while (it2.hasNext()) {
                arrayList.add(tqy.e(it2.next()));
            }
        }
        try {
            if (arrayList.isEmpty()) {
                LogUtil.h("WearEngine_HwHiWearKitManager", "discoverAllDevices device is empty");
                foundListener.onDeviceFound(new ArrayList(16));
            } else {
                LogUtil.a("WearEngine_HwHiWearKitManager", "foundAllDevices device is ok");
                foundListener.onDeviceFound(arrayList);
            }
        } catch (RemoteException unused) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "foundAllDevices remoteException");
        }
    }

    public void c(FoundListener foundListener, String str, boolean z) {
        LogUtil.a("WearEngine_HwHiWearKitManager", "foundCommonDevices enter");
        if (foundListener == null) {
            LogUtil.h("WearEngine_HwHiWearKitManager", "listener is null");
            return;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "WearEngine_HwHiWearKitManager");
        if (deviceList != null) {
            try {
                if (!deviceList.isEmpty()) {
                    LogUtil.a("WearEngine_HwHiWearKitManager", "foundCommonDevices device is ok");
                    ArrayList arrayList = new ArrayList(16);
                    Iterator<DeviceInfo> it = deviceList.iterator();
                    while (it.hasNext()) {
                        e(str, z, arrayList, it.next());
                    }
                    foundListener.onDeviceFound(arrayList);
                    return;
                }
            } catch (RemoteException unused) {
                LogUtil.b("WearEngine_HwHiWearKitManager", "foundCommonDevices remoteException");
                return;
            }
        }
        LogUtil.h("WearEngine_HwHiWearKitManager", "discoverDevices device is empty");
        foundListener.onDeviceFound(new ArrayList(16));
    }

    private void e(String str, boolean z, List<Device> list, DeviceInfo deviceInfo) {
        Device j;
        if ((tqy.m(deviceInfo) && !tqy.b(str)) || tqy.c(deviceInfo, str) || (j = tqy.j(deviceInfo)) == null) {
            return;
        }
        if (tqy.m(deviceInfo) && tqy.b(str)) {
            tqy.b(j);
        } else {
            tqy.d(j, deviceInfo, z);
        }
        tqy.b(deviceInfo, j, str);
        tqy.c(deviceInfo, j);
        if (e(j)) {
            list.add(j);
        }
    }

    private boolean e(Device device) {
        if (device == null) {
            return false;
        }
        return device.getP2pCapability() == 0 || device.getMonitorCapability() == 0 || device.getNotifyCapability() == 0 || device.getSensorCapability() == 0;
    }

    public void d(Device device, GetAttributeListener getAttributeListener) {
        LogUtil.a("WearEngine_HwHiWearKitManager", "getHiLinkDeviceId enter");
        if (getAttributeListener == null) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "getHiLinkDeviceId getAttributeListener == null");
            throw new IllegalStateException(String.valueOf(12));
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "WearEngine_HwHiWearKitManager");
        if (deviceList == null || deviceList.isEmpty()) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "getHiLinkDeviceId deviceInfoList is empty");
            throw new IllegalStateException(String.valueOf(5));
        }
        LogUtil.a("WearEngine_HwHiWearKitManager", "getHiLinkDeviceId deviceInfoList is ok");
        DeviceInfo e2 = tqy.e(device, deviceList);
        if (!tqy.g(e2)) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "getHiLinkDeviceId device not support hiwearkit.");
            throw new IllegalStateException(String.valueOf(12));
        }
        try {
            getAttributeListener.onGetString(e2.getHiLinkDeviceId());
        } catch (RemoteException unused) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "getHiLinkDeviceId remoteException");
        }
    }

    public void c(Device device, int i, GetAttributeListener getAttributeListener) {
        LogUtil.a("WearEngine_HwHiWearKitManager", "queryDeviceCapability enter");
        if (getAttributeListener == null) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "queryDeviceCapability queryCapabilityCallback == null");
            throw new IllegalStateException(String.valueOf(5));
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "WearEngine_HwHiWearKitManager");
        if (deviceList == null || deviceList.isEmpty()) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "queryDeviceCapability deviceInfoList is empty");
            throw new IllegalStateException(String.valueOf(12));
        }
        DeviceInfo e2 = tqy.e(device, deviceList);
        if (!tqy.g(e2)) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "queryDeviceCapability device not support hiwearkit.");
            throw new IllegalStateException(String.valueOf(12));
        }
        try {
            getAttributeListener.onGetString(Integer.toString(!cwi.c(e2, i) ? 1 : 0));
        } catch (RemoteException unused) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "queryDeviceCapability remoteException");
        }
    }

    public void b(int i, Object obj) {
        if (obj == null) {
            LogUtil.h("WearEngine_HwHiWearKitManager", "registerDataReceiveListener callback is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        this.f17269a.put(Integer.valueOf(i), obj);
        LogUtil.a("WearEngine_HwHiWearKitManager", "registerDataReceiveListener callback ok");
    }

    public int b() {
        int i;
        synchronized (c) {
            i = (this.b + 1) % 32766;
            this.b = i;
        }
        return i;
    }

    public String d(String str) {
        LogUtil.a("WearEngine_HwHiWearKitManager", "getIdentify enter");
        if (str == null) {
            LogUtil.h("WearEngine_HwHiWearKitManager", "getIdentify enter parameter is invalid.");
            return "";
        }
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "WearEngine_HwHiWearKitManager");
        if (deviceList != null && deviceList.size() > 0) {
            for (DeviceInfo deviceInfo2 : deviceList) {
                if (!cvt.c(deviceInfo2.getProductType()) && (str.equals(deviceInfo2.getDeviceUdid()) || str.equals(deviceInfo2.getUuid()))) {
                    deviceInfo = deviceInfo2;
                    break;
                }
            }
        }
        if (deviceInfo == null) {
            LogUtil.h("WearEngine_HwHiWearKitManager", "getUsedDeviceList is invalid.");
            throw new IllegalStateException(String.valueOf(16));
        }
        return deviceInfo.getDeviceIdentify();
    }

    public P2pReceiverCallback a() {
        P2pReceiverCallback p2pReceiverCallback;
        synchronized (this) {
            p2pReceiverCallback = this.d;
        }
        return p2pReceiverCallback;
    }

    public void d(P2pReceiverCallback p2pReceiverCallback) {
        synchronized (this) {
            if (p2pReceiverCallback != null) {
                LogUtil.a("WearEngine_HwHiWearKitManager", "registerDeviceDataListener enter");
                this.d = p2pReceiverCallback;
                e();
            }
        }
    }

    public void c() {
        synchronized (this) {
            LogUtil.a("WearEngine_HwHiWearKitManager", "unsubscribeDeviceDataListener enter");
            this.d = null;
            g();
        }
    }

    public void e(HwWearEngineNative hwWearEngineNative) {
        this.e = hwWearEngineNative;
    }

    public boolean d(jys jysVar) {
        if (jysVar == null) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "isP2pReceiverExist commonFileInfo is null");
            return false;
        }
        if (this.e == null) {
            LogUtil.b("WearEngine_HwHiWearKitManager", "isP2pReceiverExist mHwWearEngineNative is null");
            return false;
        }
        LogUtil.a("WearEngine_HwHiWearKitManager", "enter isP2pReceiverExist");
        IdentityInfo a2 = a(jysVar);
        IdentityInfo b = b(jysVar);
        return this.e.d(tqy.d(), b, a2);
    }

    private void e() {
        LogUtil.a("WearEngine_HwHiWearKitManager", "begin registerFileReceiveCallback");
        jyp.c().b(new IBaseResponseCallback() { // from class: tnv.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("WearEngine_HwHiWearKitManager", "enter file receive callback onResponse");
                if (i != 0) {
                    LogUtil.b("WearEngine_HwHiWearKitManager", "IBaseResponseCallback onResponse failed, errorCode:" + i);
                } else {
                    if (!(obj instanceof jys)) {
                        LogUtil.b("WearEngine_HwHiWearKitManager", "IBaseResponseCallback onResponse failed, objData is not CommonFileInfo");
                        return;
                    }
                    jys jysVar = (jys) obj;
                    IdentityInfo a2 = tnv.this.a(jysVar);
                    IdentityInfo b = tnv.this.b(jysVar);
                    tnv.this.d(tqy.d(), tnv.this.c(jysVar), a2, b);
                }
            }
        });
    }

    private void g() {
        LogUtil.a("WearEngine_HwHiWearKitManager", "unregisterFileReceiveCallback enter");
        jyp.c().e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Device device, MessageParcel messageParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2) {
        synchronized (this) {
            LogUtil.a("WearEngine_HwHiWearKitManager", "enter handleFileReceiveCallback");
            if (device == null) {
                LogUtil.b("WearEngine_HwHiWearKitManager", "handleFileReceiveCallback device is null");
                return;
            }
            P2pReceiverCallback p2pReceiverCallback = this.d;
            if (p2pReceiverCallback == null) {
                LogUtil.b("WearEngine_HwHiWearKitManager", "handleFileReceiveCallback mP2pReceiverCallback is null");
            } else {
                try {
                    p2pReceiverCallback.onDataReceived(device, messageParcel, identityInfo, identityInfo2, new ReceiveResultCallback.Stub() { // from class: com.huawei.wearengine.HwHiWearKitManager$3
                        @Override // com.huawei.wearengine.p2p.ReceiveResultCallback
                        public void onReceiveResult(int i) throws RemoteException {
                        }
                    });
                } catch (RemoteException unused) {
                    LogUtil.b("WearEngine_HwHiWearKitManager", "handleFileReceiveCallback RemoteException");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IdentityInfo a(jys jysVar) {
        return new IdentityInfo(jysVar.ab(), jysVar.y());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IdentityInfo b(jys jysVar) {
        return new IdentityInfo(jysVar.i(), jysVar.f());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MessageParcel c(jys jysVar) {
        ParcelFileDescriptor open;
        MessageParcel messageParcel = new MessageParcel();
        messageParcel.setType(2);
        String l = jysVar.l();
        LogUtil.c("WearEngine_HwHiWearKitManager", "getMessageParcelFromCommonFileInfo filePath:" + l);
        if (TextUtils.isEmpty(l)) {
            LogUtil.h("WearEngine_HwHiWearKitManager", "getMessageParcelFromCommonFileInfo filePath is empty");
        } else {
            try {
                open = ParcelFileDescriptor.open(new File(l), 268435456);
            } catch (FileNotFoundException unused) {
                LogUtil.b("WearEngine_HwHiWearKitManager", "getMessageParcelFromCommonFileInfo FileNotFoundException");
            }
            messageParcel.setParcelFileDescriptor(open);
            messageParcel.setFileName(jysVar.n());
            messageParcel.setDescription(jysVar.b());
            messageParcel.setFileSha256(jysVar.u());
            messageParcel.setData(new byte[0]);
            return messageParcel;
        }
        open = null;
        messageParcel.setParcelFileDescriptor(open);
        messageParcel.setFileName(jysVar.n());
        messageParcel.setDescription(jysVar.b());
        messageParcel.setFileSha256(jysVar.u());
        messageParcel.setData(new byte[0]);
        return messageParcel;
    }
}
