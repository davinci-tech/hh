package defpackage;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.clockwork.companion.partnerapi.SmartWatchInfo;
import com.google.gson.Gson;
import com.huawei.callback.BindPhoneServiceCallback;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.constant.TransferBusinessType;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwdevice.phoneprocess.mgr.hwmenstrualmanager.bean.MenstrualSwitchStatus;
import com.huawei.hwdevicemgr.framework.INoitifyPhoneServiceCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.HwMusicMgrCallback;
import com.huawei.hwservicesmgr.IAddDeviceStateAIDLCallback;
import com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.hwservicesmgr.IOTAResultAIDLCallback;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import com.huawei.hwservicesmgr.IWearPhoneServiceAIDL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
class jfk {
    private static jfk c;
    private String g;
    private String i;
    private List<DeviceInfo> j;
    private DeviceParameter k;
    private IAddDeviceStateAIDLCallback l;
    private List<DeviceInfo> n;
    private static final Object e = new Object();
    private static final Object b = new Object();
    private boolean d = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f13784a = false;
    private boolean h = false;
    private BindPhoneServiceCallback f = new BindPhoneServiceCallback() { // from class: jfk.2
        @Override // com.huawei.callback.BindPhoneServiceCallback
        public void onBind() {
            LogUtil.a("DeviceConfigAidlUtil", "forceInit bindPhoneServiceCallback success", " isAddDevice == ", Boolean.valueOf(jfk.this.d), " isMigrateUsedDeviceList == ", Boolean.valueOf(jfk.this.f13784a));
            if (jfk.this.f13784a) {
                try {
                    jfk.this.f13784a = false;
                    jfk jfkVar = jfk.this;
                    jfkVar.d(jfkVar.j);
                } catch (RemoteException unused) {
                    LogUtil.b("DeviceConfigAidlUtil", "migrateUsedDeviceList() onbind exception");
                }
            }
            if (jfk.this.d) {
                jfk.this.d = false;
                try {
                    jfk jfkVar2 = jfk.this;
                    jfkVar2.b(jfkVar2.k, jfk.this.i, jfk.this.l);
                } catch (RemoteException unused2) {
                    LogUtil.b("DeviceConfigAidlUtil", "addDevice() onbind exception");
                }
            }
            if (jfk.this.h) {
                jfk.this.h = false;
                try {
                    jfk jfkVar3 = jfk.this;
                    jfkVar3.e(jfkVar3.n, jfk.this.g);
                } catch (RemoteException unused3) {
                    LogUtil.b("DeviceConfigAidlUtil", "setUsedDeviceList() onbind exception");
                }
            }
        }
    };

    private jfk() {
    }

    public static jfk e() {
        jfk jfkVar;
        synchronized (e) {
            if (c == null) {
                c = new jfk();
            }
            jfkVar = c;
        }
        return jfkVar;
    }

    public void b(DeviceParameter deviceParameter, String str, IAddDeviceStateAIDLCallback iAddDeviceStateAIDLCallback) throws RemoteException {
        synchronized (e) {
            IWearPhoneServiceAIDL e2 = jez.e();
            if (e2 != null) {
                e2.addDevice(deviceParameter, str, iAddDeviceStateAIDLCallback);
            } else {
                LogUtil.h("DeviceConfigAidlUtil", "addDevice() iPhoneServiceAIDL is null");
                this.d = true;
                this.k = deviceParameter;
                this.i = str;
                this.l = iAddDeviceStateAIDLCallback;
                jez.d(BaseApplication.getContext(), this.f);
            }
        }
    }

    public void e(List<DeviceInfo> list, String str) throws RemoteException {
        synchronized (e) {
            IWearPhoneServiceAIDL e2 = jez.e();
            if (e2 != null) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                e2.switchDevice(list, str);
            } else {
                LogUtil.h("DeviceConfigAidlUtil", "setUsedDeviceList() iPhoneServiceAIDL is null");
                this.h = true;
                this.n = list;
                this.g = str;
                jez.d(BaseApplication.getContext(), this.f);
            }
        }
    }

    public void d(List<DeviceInfo> list) throws RemoteException {
        synchronized (e) {
            IWearPhoneServiceAIDL e2 = jez.e();
            if (e2 != null) {
                e2.migrateUsedDeviceList(list);
            } else {
                LogUtil.h("DeviceConfigAidlUtil", "migrateUsedDeviceList() iPhoneServiceAIDL is null");
                this.f13784a = true;
                this.j = list;
                jez.d(BaseApplication.getContext(), this.f);
            }
        }
    }

    public void e(DeviceCommand deviceCommand) {
        try {
            if (deviceCommand == null) {
                LogUtil.h("DeviceConfigAidlUtil", "deviceCommand is null");
                return;
            }
            LogUtil.a("DeviceConfigAidlUtil", "sendDeviceData(): Command = ", cvx.e(deviceCommand.getServiceID()), cvx.e(deviceCommand.getCommandID()), cvx.d(deviceCommand.getDataContent()));
            IWearPhoneServiceAIDL e2 = jez.e();
            if (e2 != null) {
                synchronized (e) {
                    e2.sendDeviceData(deviceCommand);
                }
            } else {
                LogUtil.h("DeviceConfigAidlUtil", "sendDeviceData() iPhoneServiceAIDL is null");
                jez.a(BaseApplication.getContext());
            }
        } catch (RemoteException e3) {
            LogUtil.b("DeviceConfigAidlUtil", "sendDeviceData() exception = ", e3.getMessage());
        }
    }

    public void e(TransferFileInfo transferFileInfo, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback) {
        try {
            IWearPhoneServiceAIDL e2 = jez.e();
            if (e2 != null) {
                e2.getFile(transferFileInfo, iTransferSleepAndDFXFileCallback);
            } else {
                LogUtil.h("DeviceConfigAidlUtil", "getFile() iPhoneServiceAIDL is null");
                jez.a(BaseApplication.getContext());
            }
        } catch (RemoteException e3) {
            LogUtil.b("DeviceConfigAidlUtil", "getFile() exception = ", e3.getMessage());
        }
    }

    public void a(DeviceInfo deviceInfo, String str, String str2, int i, IOTAResultAIDLCallback iOTAResultAIDLCallback) {
        try {
            IWearPhoneServiceAIDL e2 = jez.e();
            if (e2 != null) {
                synchronized (e) {
                    e2.sendOTAFileData(deviceInfo, str, str2, i, iOTAResultAIDLCallback);
                }
            } else {
                LogUtil.h("DeviceConfigAidlUtil", "sendOtaFileData() iPhoneServiceAIDL is null");
                jez.a(BaseApplication.getContext());
            }
        } catch (RemoteException e3) {
            LogUtil.b("DeviceConfigAidlUtil", "sendOtaFileData() exception = ", e3.getMessage());
        }
    }

    public void bGU_(TransferBusinessType transferBusinessType, jqj jqjVar, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback, ParcelFileDescriptor parcelFileDescriptor) {
        try {
            IWearPhoneServiceAIDL e2 = jez.e();
            if (e2 != null) {
                synchronized (e) {
                    String json = new Gson().toJson(jqjVar);
                    if (TextUtils.isEmpty(json)) {
                        LogUtil.h("DeviceConfigAidlUtil", "commonTransferFile() transferFileInfo: ", json);
                    }
                    if (transferBusinessType == null) {
                        LogUtil.h("DeviceConfigAidlUtil", "busiType is null");
                        return;
                    } else {
                        e2.transFileUnite(transferBusinessType.getValue(), json, iAppTransferFileResultAIDLCallback, parcelFileDescriptor);
                        return;
                    }
                }
            }
            LogUtil.h("DeviceConfigAidlUtil", "commonTransferFile(busiType) iPhoneServiceAIDL is null");
            jez.a(BaseApplication.getContext());
        } catch (RemoteException unused) {
            LogUtil.b("DeviceConfigAidlUtil", "commonTransferFile() RemoteException");
        }
    }

    public void c(String str, int i, IBaseCallback iBaseCallback) {
        try {
            IWearPhoneServiceAIDL e2 = jez.e();
            if (e2 != null) {
                synchronized (e) {
                    e2.stopTransferFile(str, i, iBaseCallback);
                }
            } else {
                LogUtil.h("DeviceConfigAidlUtil", "commonStopTransfer() iPhoneServiceAIDL is null");
                jez.a(BaseApplication.getContext());
            }
        } catch (RemoteException e3) {
            LogUtil.b("DeviceConfigAidlUtil", "commonStopTransfer() exception = ", e3.getMessage());
        }
    }

    public void b(jqj jqjVar, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback) {
        LogUtil.a("DeviceConfigAidlUtil", " enter startRequestFile()");
        try {
            IWearPhoneServiceAIDL e2 = jez.e();
            if (e2 != null) {
                synchronized (e) {
                    String json = new Gson().toJson(jqjVar);
                    if (!TextUtils.isEmpty(json)) {
                        LogUtil.h("DeviceConfigAidlUtil", "startRequestFile() transferFileInfo: ", json);
                        e2.startRequestFile(json, iTransferSleepAndDFXFileCallback);
                    }
                }
                return;
            }
            LogUtil.h("DeviceConfigAidlUtil", "startRequestFile() iPhoneServiceAIDL is null");
            jez.a(BaseApplication.getContext());
        } catch (RemoteException e3) {
            LogUtil.b("DeviceConfigAidlUtil", "startRequestFile() exception = ", e3.getMessage());
        }
    }

    public void e(HwMusicMgrCallback hwMusicMgrCallback) {
        try {
            IWearPhoneServiceAIDL e2 = jez.e();
            if (e2 != null) {
                synchronized (e) {
                    e2.registMusicMgrCallback(hwMusicMgrCallback);
                }
            } else {
                LogUtil.h("DeviceConfigAidlUtil", "registMusicMgrCallback() iPhoneServiceAIDL is null");
                jez.a(BaseApplication.getContext());
            }
        } catch (RemoteException e3) {
            LogUtil.b("DeviceConfigAidlUtil", "registMusicMgrCallback() exception = ", e3.getMessage());
        }
    }

    public Map<String, DeviceCapability> c(int i, String str, String str2) {
        Map<String, DeviceCapability> queryDeviceCapability;
        synchronized (e) {
            IWearPhoneServiceAIDL e2 = jez.e();
            if (e2 != null) {
                try {
                    queryDeviceCapability = e2.queryDeviceCapability(i, str, str2);
                } catch (RemoteException e3) {
                    LogUtil.b("DeviceConfigAidlUtil", "capabilityNegotiation() exception = ", e3.getMessage());
                }
            }
            queryDeviceCapability = null;
        }
        return queryDeviceCapability;
    }

    public SmartWatchInfo d() throws RemoteException {
        IWearPhoneServiceAIDL e2 = jez.e();
        if (e2 != null) {
            return e2.getPendingConnectWatchInfo();
        }
        LogUtil.h("DeviceConfigAidlUtil", "forceConnectBTDevice() iPhoneServiceAIDL is null");
        jez.a(BaseApplication.getContext());
        return null;
    }

    public void c(boolean z) throws RemoteException {
        IWearPhoneServiceAIDL e2 = jez.e();
        if (e2 != null) {
            e2.openSystemBluetoothSwitch(z);
        } else {
            LogUtil.h("DeviceConfigAidlUtil", "openSystemBluetoothSwitch() iPhoneServiceAIDL is null");
            jez.d(BaseApplication.getContext(), null);
        }
    }

    public void b(List<String> list, boolean z) throws RemoteException {
        IWearPhoneServiceAIDL e2 = jez.e();
        if (e2 != null) {
            e2.unPair(list, z);
        } else {
            LogUtil.h("DeviceConfigAidlUtil", "unPair() iPhoneServiceAIDL is null");
            jez.d(BaseApplication.getContext(), null);
        }
    }

    public boolean c() throws RemoteException {
        IWearPhoneServiceAIDL e2 = jez.e();
        if (e2 != null) {
            return e2.isPrompt();
        }
        LogUtil.h("DeviceConfigAidlUtil", "isPrompt() iPhoneServiceAIDL is null");
        jez.a(BaseApplication.getContext());
        return true;
    }

    public boolean a() throws RemoteException {
        IWearPhoneServiceAIDL e2 = jez.e();
        if (e2 != null) {
            return e2.isOutgoingCall();
        }
        LogUtil.h("DeviceConfigAidlUtil", "isOutgoingCall iPhoneServiceAIDL is null");
        jez.a(BaseApplication.getContext());
        return false;
    }

    public void b(IBaseResponseCallback iBaseResponseCallback) {
        IWearPhoneServiceAIDL e2 = jez.e();
        if (e2 != null) {
            try {
                e2.startSyncWorkOut();
                return;
            } catch (RemoteException e3) {
                if (iBaseResponseCallback != null) {
                    iBaseResponseCallback.d(-1, "send command error");
                }
                jez.a(BaseApplication.getContext());
                LogUtil.b("DeviceConfigAidlUtil", "startSyncWorkOut exception:", e3.getMessage());
                return;
            }
        }
        LogUtil.h("DeviceConfigAidlUtil", "startSyncWorkOut() iPhoneServiceAIDL is null");
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(-1, "send command error");
        }
        jez.a(BaseApplication.getContext());
    }

    public void b(MenstrualSwitchStatus menstrualSwitchStatus) {
        if (menstrualSwitchStatus == null) {
            LogUtil.h("DeviceConfigAidlUtil", "sendMenstrualSwitch switchStatus null");
            return;
        }
        try {
            IWearPhoneServiceAIDL e2 = jez.e();
            if (e2 != null) {
                synchronized (e) {
                    e2.sendMenstrualSwitch(menstrualSwitchStatus);
                }
            } else {
                LogUtil.h("DeviceConfigAidlUtil", "sendMenstrualSwitch() iPhoneServiceAIDL is null");
                jez.a(BaseApplication.getContext());
            }
        } catch (RemoteException unused) {
            LogUtil.b("DeviceConfigAidlUtil", "sendMenstrualSwitch() RemoteException");
        }
    }

    public void c(String str, IBaseCallback iBaseCallback) {
        if (iBaseCallback == null) {
            LogUtil.h("DeviceConfigAidlUtil", "sendEcgBlockList callback is null.");
            return;
        }
        LogUtil.a("DeviceConfigAidlUtil", "sendEcgBlockList callback");
        synchronized (e) {
            try {
                IWearPhoneServiceAIDL e2 = jez.e();
                if (e2 != null) {
                    e2.sendEcgBlockList(str, iBaseCallback);
                } else {
                    LogUtil.h("DeviceConfigAidlUtil", "sendEcgBlockList() iPhoneServiceAIDL is null");
                    jez.a(BaseApplication.getContext());
                }
            } catch (RemoteException unused) {
                LogUtil.b("DeviceConfigAidlUtil", "sendMenstrualSwitch() RemoteException");
            }
        }
    }

    public void b() {
        try {
            IWearPhoneServiceAIDL e2 = jez.e();
            if (e2 != null) {
                synchronized (e) {
                    e2.sendWifiConfigurationInformation();
                }
            } else {
                LogUtil.h("DeviceConfigAidlUtil", "sendWifiData() iPhoneServiceAIDL is null");
                jez.a(BaseApplication.getContext());
            }
        } catch (RemoteException unused) {
            LogUtil.b("DeviceConfigAidlUtil", "sendWifiData() RemoteException");
        }
    }

    public List<DeviceInfo> d(HwGetDevicesMode hwGetDevicesMode, HwGetDevicesParameter hwGetDevicesParameter, String str) {
        List<DeviceInfo> list = null;
        try {
            IWearPhoneServiceAIDL e2 = jez.e();
            if (e2 != null) {
                synchronized (b) {
                    if (hwGetDevicesMode != null) {
                        try {
                            list = e2.getDeviceList(hwGetDevicesMode.value(), hwGetDevicesParameter, str);
                        } finally {
                        }
                    }
                }
            } else {
                LogUtil.h("DeviceConfigAidlUtil", "getDeviceList iPhoneServiceAIDL is null");
                jez.a(BaseApplication.getContext());
            }
        } catch (RemoteException unused) {
            LogUtil.b("DeviceConfigAidlUtil", "getDeviceList RemoteException");
        }
        return list;
    }

    public boolean c(String str, DeviceInfo deviceInfo, int i, String str2) {
        boolean z = false;
        try {
            IWearPhoneServiceAIDL e2 = jez.e();
            if (e2 != null) {
                synchronized (e) {
                    z = e2.notifyPhoneService(str, deviceInfo, i, str2);
                }
            } else {
                LogUtil.h("DeviceConfigAidlUtil", "notifyPhoneService iPhoneServiceAIDL is null");
                jez.a(BaseApplication.getContext());
            }
        } catch (RemoteException unused) {
            LogUtil.b("DeviceConfigAidlUtil", "notifyPhoneService RemoteException");
        }
        return z;
    }

    public void e(String str, INoitifyPhoneServiceCallback iNoitifyPhoneServiceCallback) {
        try {
            IWearPhoneServiceAIDL e2 = jez.e();
            if (e2 != null) {
                synchronized (e) {
                    e2.registerCallback(str, iNoitifyPhoneServiceCallback);
                }
            } else {
                LogUtil.h("DeviceConfigAidlUtil", "registerCallback iPhoneServiceAIDL is null");
                jez.a(BaseApplication.getContext());
            }
        } catch (RemoteException unused) {
            LogUtil.b("DeviceConfigAidlUtil", "registerCallback RemoteException");
        }
    }

    public void d(String str) {
        try {
            IWearPhoneServiceAIDL e2 = jez.e();
            if (e2 != null) {
                synchronized (e) {
                    e2.unregisterCallback(str);
                }
            } else {
                LogUtil.h("DeviceConfigAidlUtil", "unregisterCallback iPhoneServiceAIDL is null");
                jez.a(BaseApplication.getContext());
            }
        } catch (RemoteException unused) {
            LogUtil.b("DeviceConfigAidlUtil", "unregisterCallback RemoteException");
        }
    }
}
