package defpackage;

import android.os.Binder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.phdkit.DataContentListener;
import com.huawei.phdkit.DeviceData;
import com.huawei.phdkit.DeviceStateListener;
import com.huawei.phdkit.DiscoveryListener;
import com.huawei.phdkit.DvLiteDevice;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class mbo extends HwBaseManager implements ParserInterface {
    private static mbo b;
    private Map<String, DataContentListener> d;
    private Set<String> g;
    private cwl i;
    private List<DeviceStateListener> j;
    private static final Object e = new Object();
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14871a = new Object();

    private mbo() {
        super(BaseApplication.getContext());
        this.d = new HashMap(16);
        this.j = new ArrayList(24);
        this.g = new HashSet(10);
        this.i = new cwl();
        LogUtil.a("WearEngine_HwDvLiteManager", "enter HwDvLiteManager");
    }

    public static mbo a() {
        mbo mboVar;
        synchronized (e) {
            if (b == null) {
                b = new mbo();
            }
            mboVar = b;
        }
        return mboVar;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 48;
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        blt.d("WearEngine_HwDvLiteManager", bArr, "getResult Hex: ");
        if (deviceInfo == null || bArr == null || bArr.length < 2) {
            LogUtil.h("WearEngine_HwDvLiteManager", "device or dataContents is null");
            return;
        }
        if (bArr[1] == 3) {
            e(deviceInfo, bArr);
            LogUtil.a("WearEngine_HwDvLiteManager", "set isCapability");
            return;
        }
        LogUtil.a("WearEngine_HwDvLiteManager", "other commandID");
        String deviceUdid = deviceInfo.getDeviceUdid();
        LogUtil.c("WearEngine_HwDvLiteManager", "getResult udid is: " + deviceUdid);
        try {
            Iterator<DataContentListener> it = i(deviceUdid).iterator();
            while (it.hasNext()) {
                it.next().getResult(deviceUdid, cvx.d(bArr));
            }
        } catch (RemoteException unused) {
            LogUtil.b("WearEngine_HwDvLiteManager", "DataContentListener remoteException");
        }
    }

    private List<DataContentListener> i(String str) {
        LogUtil.a("WearEngine_HwDvLiteManager", "enter getDataContentListenerList");
        LinkedList linkedList = new LinkedList();
        synchronized (c) {
            for (Map.Entry<String, DataContentListener> entry : this.d.entrySet()) {
                String key = entry.getKey();
                LogUtil.c("WearEngine_HwDvLiteManager", "entry.getKey(): " + key);
                if (key != null && key.startsWith(str)) {
                    if (entry.getValue() == null) {
                        LogUtil.h("WearEngine_HwDvLiteManager", "dataContentListener is null");
                    } else {
                        linkedList.add(entry.getValue());
                    }
                }
            }
        }
        LogUtil.a("WearEngine_HwDvLiteManager", "dataContentListeners size: " + linkedList.size());
        return linkedList;
    }

    private void e(DeviceInfo deviceInfo, byte[] bArr) {
        String d = cvx.d(bArr);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            LogUtil.h("WearEngine_HwDvLiteManager", "info data is error");
            return;
        }
        try {
            List<cwd> e2 = this.i.a(d.substring(4)).e();
            if (e2 == null || e2.size() <= 0) {
                return;
            }
            for (cwd cwdVar : e2) {
                if (CommonUtil.w(cwdVar.e()) == 1) {
                    e(deviceInfo, cwdVar);
                } else {
                    LogUtil.h("WearEngine_HwDvLiteManager", "parameter is invalid.");
                }
            }
        } catch (cwg unused) {
            LogUtil.b("WearEngine_HwDvLiteManager", "setCapability TlvException");
        } catch (NumberFormatException unused2) {
            LogUtil.b("WearEngine_HwDvLiteManager", "setCapability NumberFormatException");
        }
    }

    private void e(DeviceInfo deviceInfo, cwd cwdVar) {
        if (SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(3000000), cvx.c(deviceInfo.getDeviceIdentify()), cvx.e(cwdVar.c()), new StorageParams(0)) == 0) {
            LogUtil.a("WearEngine_HwDvLiteManager", "setSharedPreference success");
        } else {
            LogUtil.h("WearEngine_HwDvLiteManager", "setSharedPreference failed");
        }
        b(deviceInfo.getDeviceIdentify());
    }

    private void b(String str) {
        if (TextUtils.isEmpty(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(3000000), str))) {
            return;
        }
        if (SharedPreferenceManager.d(String.valueOf(3000000), str)) {
            LogUtil.a("WearEngine_HwDvLiteManager", "deleteMacKey success");
        } else {
            LogUtil.h("WearEngine_HwDvLiteManager", "deleteMacKey failed");
        }
    }

    public void c(String str, DataContentListener dataContentListener) {
        if (str != null && dataContentListener != null) {
            LogUtil.a("WearEngine_HwDvLiteManager", "listener is not null");
            DeviceData deviceData = new DeviceData();
            if (c(str) == null) {
                LogUtil.a("WearEngine_HwDvLiteManager", "udid is invalid or device is null");
                deviceData.setErrorCode(1);
                deviceData.setStatus(0);
                deviceData.setUdid(str);
                try {
                    dataContentListener.getStatus(deviceData);
                    return;
                } catch (RemoteException unused) {
                    LogUtil.b("WearEngine_HwDvLiteManager", "DataContentListener remoteException:");
                    return;
                }
            }
            String f = f(str);
            synchronized (c) {
                this.d.put(f, dataContentListener);
            }
            deviceData.setErrorCode(0);
            deviceData.setStatus(0);
            deviceData.setUdid(str);
            try {
                dataContentListener.getStatus(deviceData);
                return;
            } catch (RemoteException unused2) {
                LogUtil.b("WearEngine_HwDvLiteManager", "sendDataCallback remoteException:");
                return;
            }
        }
        LogUtil.h("WearEngine_HwDvLiteManager", "DataContentListener is null");
    }

    private String f(String str) {
        return str + Constants.LINK + Binder.getCallingUid();
    }

    public void e(DiscoveryListener discoveryListener) {
        LogUtil.a("WearEngine_HwDvLiteManager", "discoverDevices enter");
        if (discoveryListener == null) {
            LogUtil.h("WearEngine_HwDvLiteManager", "listener is null");
            return;
        }
        List<DeviceInfo> c2 = c();
        try {
            ArrayList arrayList = new ArrayList(16);
            if (c2.isEmpty()) {
                LogUtil.h("WearEngine_HwDvLiteManager", "discoverDevices device is empty");
                discoveryListener.onDeviceFound(arrayList);
            } else {
                Iterator<DeviceInfo> it = c2.iterator();
                while (it.hasNext()) {
                    arrayList.addAll(b(it.next()));
                }
                discoveryListener.onDeviceFound(arrayList);
            }
        } catch (RemoteException unused) {
            LogUtil.b("WearEngine_HwDvLiteManager", "discoverDevices remoteException");
        }
    }

    private List<DvLiteDevice> b(DeviceInfo deviceInfo) {
        ArrayList arrayList = new ArrayList(16);
        if (deviceInfo != null && deviceInfo.getDeviceConnectState() == 2) {
            Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "WearEngine_HwDvLiteManager");
            if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
                LogUtil.h("WearEngine_HwDvLiteManager", "getDvLiteDevices, deviceCapabilityHashMaps is empty");
                return arrayList;
            }
            DeviceCapability deviceCapability = queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
            if (deviceCapability == null || !deviceCapability.isSupportPhd()) {
                LogUtil.a("WearEngine_HwDvLiteManager", "device not support dmsp.");
                return arrayList;
            }
            LogUtil.a("WearEngine_HwDvLiteManager", "discoverDevices devices is not empty");
            DvLiteDevice dvLiteDevice = new DvLiteDevice();
            LogUtil.a("WearEngine_HwDvLiteManager", "device.getDeviceName()", deviceInfo.getDeviceName());
            dvLiteDevice.setDeviceName(deviceInfo.getDeviceName());
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(3000000), cvx.c(deviceInfo.getDeviceIdentify()));
            if (!TextUtils.isEmpty(b2)) {
                dvLiteDevice.setAbilityContents(cvx.a(cvx.c(b2)));
                LogUtil.c("WearEngine_HwDvLiteManager", "device.setAbilityContents()", b2);
            } else {
                LogUtil.a("WearEngine_HwDvLiteManager", "deviceCapabilityString is null");
            }
            dvLiteDevice.setUdid(deviceInfo.getDeviceUdid());
            arrayList.add(dvLiteDevice);
        } else {
            LogUtil.h("WearEngine_HwDvLiteManager", "device is null or not connected");
        }
        return arrayList;
    }

    public void c(String str, DeviceStateListener deviceStateListener) {
        if (deviceStateListener == null || str == null) {
            return;
        }
        LogUtil.a("WearEngine_HwDvLiteManager", "registerDeviceStatusListener enter :", str);
        synchronized (f14871a) {
            this.j.add(deviceStateListener);
        }
        LogUtil.a("WearEngine_HwDvLiteManager", "mPhdDeviceStateListenerList add success");
    }

    public void a(String str) {
        if (str != null) {
            LogUtil.a("WearEngine_HwDvLiteManager", "unRegisterDeviceStatusListener enter :", str);
            synchronized (f14871a) {
                this.j.clear();
            }
        }
    }

    public void d() {
        LogUtil.a("WearEngine_HwDvLiteManager", "clearDataListenerMap enter");
        synchronized (c) {
            this.d.clear();
        }
    }

    public void e(String str) {
        LogUtil.a("WearEngine_HwDvLiteManager", "clearOneDataListenerMap enter");
        if (str == null) {
            return;
        }
        String f = f(str);
        synchronized (c) {
            this.d.remove(f);
        }
    }

    public String d(String str) {
        LogUtil.a("WearEngine_HwDvLiteManager", "getIdentify enter");
        if (str == null) {
            LogUtil.h("WearEngine_HwDvLiteManager", "getIdentify enter parameter is invalid.");
            return "";
        }
        DeviceInfo c2 = c(str);
        if (c2 == null) {
            LogUtil.h("WearEngine_HwDvLiteManager", "getUsedDeviceList is invalid.");
            return "";
        }
        if (!str.equals(c2.getDeviceUdid()) && !str.equals(c2.getUuid())) {
            return "";
        }
        LogUtil.a("WearEngine_HwDvLiteManager", "udid equal");
        return c2.getDeviceIdentify();
    }

    public void a(DeviceInfo deviceInfo) {
        LogUtil.a("WearEngine_HwDvLiteManager", "notifyPhdDeviceStates enter");
        if (deviceInfo == null) {
            return;
        }
        if (deviceInfo.getDeviceConnectState() == 2) {
            c(deviceInfo);
        }
        if (deviceInfo.getDeviceConnectState() == 3) {
            d(deviceInfo);
        }
        synchronized (f14871a) {
            if (!this.g.contains(deviceInfo.getDeviceIdentify())) {
                LogUtil.a("WearEngine_HwDvLiteManager", "device not suppot msdp");
                return;
            }
            for (int i = 0; i < this.j.size(); i++) {
                if (this.j.get(i) != null) {
                    DvLiteDevice dvLiteDevice = new DvLiteDevice();
                    dvLiteDevice.setDeviceName(deviceInfo.getDeviceName());
                    dvLiteDevice.setDeviceStatus(deviceInfo.getDeviceConnectState());
                    LogUtil.a("WearEngine_HwDvLiteManager", "btDeviceInfo.getDeviceName() :", deviceInfo.getDeviceName(), " , DeviceConnectState.DEVICE_CONNECTED :", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                    dvLiteDevice.setUdid(deviceInfo.getDeviceUdid());
                    try {
                        if (deviceInfo.getDeviceConnectState() == 2) {
                            this.j.get(i).onDeviceStateChanged(dvLiteDevice, 1);
                        }
                        if (deviceInfo.getDeviceConnectState() == 3) {
                            this.j.get(i).onDeviceStateChanged(dvLiteDevice, 1);
                            this.g.remove(deviceInfo.getDeviceIdentify());
                        }
                    } catch (RemoteException unused) {
                        LogUtil.b("WearEngine_HwDvLiteManager", "getResult remoteException");
                    }
                }
            }
        }
    }

    private void c(DeviceInfo deviceInfo) {
        LogUtil.a("WearEngine_HwDvLiteManager", "inquireDeviceMsdpCapability enter");
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "WearEngine_HwDvLiteManager");
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            LogUtil.h("WearEngine_HwDvLiteManager", "inquireDeviceMsdpCapability, deviceCapabilityHashMaps is empty");
            return;
        }
        DeviceCapability deviceCapability = queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
        if (deviceCapability == null || !deviceCapability.isSupportPhd()) {
            LogUtil.h("WearEngine_HwDvLiteManager", "device not support dmsp.");
            return;
        }
        synchronized (f14871a) {
            this.g.add(deviceInfo.getDeviceIdentify());
        }
        LogUtil.a("WearEngine_HwDvLiteManager", "mSupportMsdpDeviceSet add device");
        a(deviceInfo, 48, 3, new byte[]{1, 0});
    }

    private void d(DeviceInfo deviceInfo) {
        LogUtil.a("WearEngine_HwDvLiteManager", "deleteVirtualizationCapability enter");
        if (TextUtils.isEmpty(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(3000000), cvx.c(deviceInfo.getDeviceIdentify())))) {
            return;
        }
        if (SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(3000000), cvx.c(deviceInfo.getDeviceIdentify())) == 0) {
            LogUtil.a("WearEngine_HwDvLiteManager", "deleteVirtualizationCapability success");
        }
    }

    private void a(DeviceInfo deviceInfo, int i, int i2, byte[] bArr) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(i);
        deviceCommand.setCommandID(i2);
        deviceCommand.setDataLen(bArr.length);
        deviceCommand.setDataContent(bArr);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private List<DeviceInfo> c() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "WearEngine_HwDvLiteManager");
        ArrayList arrayList = new ArrayList(16);
        if (deviceList != null && deviceList.size() > 0) {
            for (DeviceInfo deviceInfo : deviceList) {
                if (!cvt.c(deviceInfo.getProductType())) {
                    arrayList.add(deviceInfo);
                }
            }
        }
        return arrayList;
    }

    private DeviceInfo c(String str) {
        List<DeviceInfo> c2 = c();
        if (c2.size() > 0) {
            Iterator<DeviceInfo> it = c2.iterator();
            while (it.hasNext()) {
                DeviceInfo next = it.next();
                if (str.equals(next.getDeviceUdid()) || str.equals(next.getUuid())) {
                    return next;
                }
            }
        }
        return null;
    }
}
