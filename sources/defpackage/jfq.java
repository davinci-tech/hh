package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteException;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import com.google.android.clockwork.companion.partnerapi.SmartWatchInfo;
import com.google.gson.JsonSyntaxException;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.datatype.DataDeviceIntelligentInfo;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.constant.TransferBusinessType;
import com.huawei.health.devicemgr.business.constant.TransferFileReqType;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwdevice.mainprocess.callback.NotifyPhoneServiceCallback;
import com.huawei.hwdevice.mainprocess.mgr.smssend.HwDeviceReplyPhraseEngineManager;
import com.huawei.hwdevice.phoneprocess.mgr.hwmenstrualmanager.bean.MenstrualSwitchStatus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.HwMusicMgrCallback;
import com.huawei.hwservicesmgr.IAddDeviceStateAIDLCallback;
import com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.hwservicesmgr.IOTAResultAIDLCallback;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes5.dex */
public class jfq extends HwBaseManager {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13790a = new Object();
    private static final Object c = new Object();
    private static jfq d;
    private Context b;
    private BroadcastReceiver e;
    private jfk g;
    private final MutableLiveData<Boolean> h;
    private ExecutorService i;

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public boolean onDataMigrate() {
        return true;
    }

    public MutableLiveData<Boolean> d() {
        return this.h;
    }

    private jfq(Context context) {
        super(context);
        this.i = Executors.newSingleThreadExecutor();
        this.h = new MutableLiveData<>(false);
        this.e = new BroadcastReceiver() { // from class: jfq.5
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, final Intent intent) {
                if (intent != null && intent.getAction() != null) {
                    jfq.this.i.execute(new Runnable() { // from class: jfq.5.4
                        @Override // java.lang.Runnable
                        public void run() {
                            LogUtil.a("HwDeviceConfigManager", "mConnectStateChangedReceiver() intent is ", intent.getAction());
                            String action = intent.getAction();
                            action.hashCode();
                            if (action.equals("com.huawei.plugin.account.login")) {
                                LogUtil.a("HwDeviceConfigManager", "HwTwsManger login goto syncTwsPair.");
                            } else if (action.equals("com.huawei.bone.action.CONNECTION_STATE_CHANGED")) {
                                jff.e().bGT_(intent);
                                jfo.e();
                            } else {
                                LogUtil.h("HwDeviceConfigManager", "unknown broadcast.");
                            }
                        }
                    });
                } else {
                    LogUtil.h("HwDeviceConfigManager", "mConnectStateChangedReceiver() intent is null");
                }
            }
        };
        LogUtil.a("HwDeviceConfigManager", "HWDeviceConfigManager() start");
        i();
        this.b = context;
        try {
            n();
            jez.a(this.b);
            jez.b();
            o();
        } catch (SQLiteException e) {
            LogUtil.b("HwDeviceConfigManager", "HWDeviceConfigManager() SQLiteException:", ExceptionUtils.d(e));
        } catch (JsonSyntaxException e2) {
            LogUtil.b("HwDeviceConfigManager", "HWDeviceConfigManager() JsonSyntaxException:", ExceptionUtils.d(e2));
        }
        LogUtil.a("HwDeviceConfigManager", "HWDeviceConfigManager() end");
    }

    public void h() {
        LogUtil.a("HwDeviceConfigManager", "processPhoneServiceBindSucess");
        jfu.e(this.b);
        new jfe().a();
        m();
        jfo.e();
        this.h.postValue(true);
    }

    private void n() {
        jfu.f();
        this.g = jfk.e();
    }

    private void o() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.setPriority(Integer.MAX_VALUE);
        BroadcastManagerUtil.bFC_(this.b, this.e, intentFilter, LocalBroadcast.c, null);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.huawei.plugin.account.login");
        LogUtil.a("HwDeviceConfigManager", "broadcast com.huawei.plugin.account.login.");
        BroadcastManagerUtil.bFA_(this.b, this.e, intentFilter2, LocalBroadcast.c, null);
    }

    private void i() {
        if (CompileParameterUtil.a("IS_RELEASE_VERSION")) {
            return;
        }
        String d2 = CommonUtil.d(Process.myPid());
        if (TextUtils.isEmpty(d2) || BaseApplication.getAppPackage().equals(d2)) {
            return;
        }
        throw new RuntimeException("DeviceConfig must init in main process." + d2);
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 1;
    }

    public static jfq c() {
        jfq jfqVar;
        jfh.d();
        synchronized (f13790a) {
            if (d == null) {
                d = new jfq(BaseApplication.getContext());
            }
            jfqVar = d;
        }
        return jfqVar;
    }

    public void j() {
        jez.d(BaseApplication.getContext(), null);
    }

    public void e(int i, BluetoothDataReceiveCallback bluetoothDataReceiveCallback) {
        LogUtil.a("HwDeviceConfigManager", "registerManagerCallback() serviceId:", Integer.valueOf(i));
        if (e(i) || c(i)) {
            ReleaseLogUtil.e("DEVMGR_HwDeviceConfigManager", "registerManagerCallback serviceId:", Integer.valueOf(i));
        }
        jfr.e(i, bluetoothDataReceiveCallback);
    }

    public void d(int i) {
        LogUtil.a("HwDeviceConfigManager", "unRegisterBaseResponseCallback,serviceId:", Integer.valueOf(i));
        if (e(i) || c(i)) {
            ReleaseLogUtil.e("DEVMGR_HwDeviceConfigManager", "unRegisterManagerResponseCallback serviceId:", Integer.valueOf(i));
        }
        jfr.c(i);
    }

    public void b(DeviceParameter deviceParameter, String str, IAddDeviceStateAIDLCallback iAddDeviceStateAIDLCallback) throws RemoteException {
        this.g.b(deviceParameter, str, iAddDeviceStateAIDLCallback);
    }

    public List<DeviceInfo> b(HwGetDevicesMode hwGetDevicesMode, HwGetDevicesParameter hwGetDevicesParameter, String str) {
        return this.g.d(hwGetDevicesMode, hwGetDevicesParameter, str);
    }

    public void e(List<DeviceInfo> list, String str) throws RemoteException {
        LogUtil.a("HwDeviceConfigManager", "switchDevice() enter");
        this.g.e(list, str);
    }

    @Deprecated
    public void b(List<DeviceInfo> list) throws RemoteException {
        this.g.d(list);
    }

    public void a(TransferFileInfo transferFileInfo, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback) {
        this.g.e(transferFileInfo, iTransferSleepAndDFXFileCallback);
    }

    public void b(DeviceCommand deviceCommand) {
        List<DeviceInfo> b;
        if (TextUtils.isEmpty(deviceCommand.getmIdentify()) && ((b = b(HwGetDevicesMode.CONNECTED_DEVICES, (HwGetDevicesParameter) null, "HwDeviceConfigManager")) == null || b.isEmpty())) {
            return;
        }
        this.g.e(deviceCommand);
    }

    public void c(DeviceInfo deviceInfo, String str, String str2, int i, IOTAResultAIDLCallback iOTAResultAIDLCallback) {
        this.g.a(deviceInfo, str, str2, i, iOTAResultAIDLCallback);
    }

    public void d(TransferBusinessType transferBusinessType, jqj jqjVar, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback) {
        bGV_(transferBusinessType, jqjVar, iAppTransferFileResultAIDLCallback, null);
    }

    public void bGV_(TransferBusinessType transferBusinessType, jqj jqjVar, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback, ParcelFileDescriptor parcelFileDescriptor) {
        LogUtil.a("HwDeviceConfigManager", "commonTransferFile");
        if (jqjVar != null) {
            TransferFileReqType o = jqjVar.o();
            if (TransferFileReqType.DEVICE_UNREGISTRATION.equals(o)) {
                jfi.c().b(jqjVar.a(), iAppTransferFileResultAIDLCallback);
            } else {
                if (TransferFileReqType.DEVICE_REGISTRATION.equals(o)) {
                    jfi.c().c(jqjVar.a(), iAppTransferFileResultAIDLCallback);
                    LogUtil.a("HwDeviceConfigManager", "mDeviceConfigAidlUtil commonTransferFile entry");
                    this.g.bGU_(transferBusinessType, jqjVar, jfi.c().a(), parcelFileDescriptor);
                    return;
                }
                this.g.bGU_(transferBusinessType, jqjVar, iAppTransferFileResultAIDLCallback, parcelFileDescriptor);
            }
        }
    }

    @Deprecated
    public void d(String str, int i, IBaseCallback iBaseCallback) {
        this.g.c(str, i, iBaseCallback);
    }

    public void c(jqj jqjVar, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback) {
        LogUtil.a("HwDeviceConfigManager", " enter startRequestFile()");
        this.g.b(jqjVar, iTransferSleepAndDFXFileCallback);
    }

    public void d(HwMusicMgrCallback hwMusicMgrCallback) {
        this.g.e(hwMusicMgrCallback);
    }

    public void e(MenstrualSwitchStatus menstrualSwitchStatus) {
        this.g.b(menstrualSwitchStatus);
    }

    public void f() {
        LogUtil.a("HwDeviceConfigManager", "5.35.15 sendBlockList enter.");
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "ecg_blocklist");
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("HwDeviceConfigManager", "json is empty.");
        } else {
            LogUtil.c("HwDeviceConfigManager", "sendEcgBlockList json:", b);
            this.g.c(b, new IBaseCallback.Stub() { // from class: jfq.2
                @Override // com.huawei.hwservicesmgr.IBaseCallback
                public void onResponse(int i, String str) throws RemoteException {
                    LogUtil.a("HwDeviceConfigManager", "sendEcgBlockList response code :", Integer.valueOf(i));
                }
            });
        }
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
        this.i.shutdown();
        d(2);
        k();
        try {
            this.b.unregisterReceiver(this.e);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("HwDeviceConfigManager", "mConnectStateChangedReceiver is not registered");
        }
        jfh.e();
        this.g.d("ProcessInfoRequest");
        LogUtil.a("HwDeviceConfigManager", "onDestroy()");
    }

    private void k() {
        synchronized (c) {
            jot.a().d();
            jot.a().c();
            jog.c().a((DataDeviceIntelligentInfo) null);
        }
        synchronized (f13790a) {
            d = null;
        }
    }

    public SmartWatchInfo e() throws RemoteException {
        return this.g.d();
    }

    public void e(final String str) {
        if (l()) {
            LogUtil.h("HwDeviceConfigManager", "deleteUnbindMessage isExistConnectedDevice");
            return;
        }
        if (str == null) {
            LogUtil.h("HwDeviceConfigManager", "deviceName is null.");
            return;
        }
        final MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        if (messageCenterApi == null) {
            LogUtil.h("HwDeviceConfigManager", "messageCenterApi is null");
        } else {
            messageCenterApi.getMessages(String.valueOf(19), "device_type_connected", new IBaseResponseCallback() { // from class: jfq.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("HwDeviceConfigManager", "deleteUnbindMessage errorCode: ", Integer.valueOf(i));
                    if (i != 0 || !(obj instanceof List)) {
                        LogUtil.h("HwDeviceConfigManager", "deleteUnbindMessage getMessages failed");
                        return;
                    }
                    List<MessageObject> list = (List) obj;
                    if (!list.isEmpty()) {
                        if (jfq.this.l()) {
                            LogUtil.h("HwDeviceConfigManager", "deleteUnbindMessage onResponse isExistConnectedDevice");
                            return;
                        }
                        for (MessageObject messageObject : list) {
                            String msgTitle = messageObject.getMsgTitle();
                            if (!TextUtils.isEmpty(msgTitle) && msgTitle.contains(str)) {
                                messageCenterApi.deleteMessage(messageObject.getMsgId());
                                jfq.this.setSharedPreference("kStorage_DeviceCfgMgr_Identify", "", null);
                                LogUtil.a("HwDeviceConfigManager", "deleteUnbindMessage success: ", msgTitle);
                                return;
                            }
                        }
                        return;
                    }
                    LogUtil.h("HwDeviceConfigManager", "deleteUnbindMessage messageList is empty");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l() {
        List<DeviceInfo> b = b(HwGetDevicesMode.ACTIVE_DEVICES, (HwGetDevicesParameter) null, "isExistConnectedDevice");
        if (b == null) {
            LogUtil.h("HwDeviceConfigManager", "getConnectDeviceInfo() deviceInfoList is null");
            return false;
        }
        for (DeviceInfo deviceInfo : b) {
            if (deviceInfo != null && deviceInfo.getDeviceConnectState() == 2 && ("main_relationship".equals(deviceInfo.getRelationship()) || cvt.c(deviceInfo.getProductType()))) {
                LogUtil.a("HwDeviceConfigManager", "isExistConnectedDevice deviceInfoList size ", Integer.valueOf(b.size()));
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public void b(boolean z) throws RemoteException {
        this.g.c(z);
    }

    public void c(List<String> list, boolean z) throws RemoteException {
        this.g.b(list, z);
    }

    @Deprecated
    public boolean b() throws RemoteException {
        return this.g.c();
    }

    @Deprecated
    public boolean a() throws RemoteException {
        return this.g.a();
    }

    public void d(final IBaseResponseCallback iBaseResponseCallback) {
        ThreadPoolManager.d().d("startSyncWorkOut", new Runnable() { // from class: jfq.4
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("HwDeviceConfigManager", "ready register device info.");
                List<DeviceInfo> b = jfq.this.b(HwGetDevicesMode.CONNECTED_DEVICES, (HwGetDevicesParameter) null, "HwDeviceConfigManager");
                if (b == null) {
                    LogUtil.h("HwDeviceConfigManager", "startSyncWorkOut deviceInfoList is null");
                    return;
                }
                for (DeviceInfo deviceInfo : b) {
                    if (deviceInfo != null) {
                        if (!jpp.d(deviceInfo)) {
                            return;
                        } else {
                            jpp.i(deviceInfo);
                        }
                    }
                }
                jfq.this.g.b(iBaseResponseCallback);
            }
        });
    }

    public Map<String, DeviceCapability> a(int i, String str, String str2) {
        return this.g.c(i, str, str2);
    }

    public void g() {
        this.g.b();
    }

    public void e(String str, NotifyPhoneServiceCallback notifyPhoneServiceCallback) {
        if (TextUtils.isEmpty(str) || notifyPhoneServiceCallback == null) {
            LogUtil.h("HwDeviceConfigManager", "registerCallback input parameter is null.");
        } else {
            jfp.d().b(str, notifyPhoneServiceCallback);
        }
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwDeviceConfigManager", "unregisterCallback module is null.");
        } else {
            jfp.d().c(str);
        }
    }

    public boolean d(String str, DeviceInfo deviceInfo, int i, String str2) {
        if (TextUtils.isEmpty(str) || deviceInfo == null) {
            LogUtil.h("HwDeviceConfigManager", "notifyPhoneService input parameter is null.");
            return false;
        }
        LogUtil.a("HwDeviceConfigManager", "notifyPhoneService module: ", str);
        return this.g.c(str, deviceInfo, i, str2);
    }

    private void m() {
        this.g.e("ProcessInfoRequest", jfp.d().b());
        jez.b();
        HwDeviceReplyPhraseEngineManager.e().a();
        HwDeviceReplyPhraseEngineManager.e();
    }

    public boolean e(int i) {
        if (i != 27 && i != 39) {
            return false;
        }
        ReleaseLogUtil.e("DEVMGR_HwDeviceConfigManager", "isWatchFaceOrPayService");
        return true;
    }

    public boolean c(int i) {
        if (i != 37 && i != 42) {
            return false;
        }
        ReleaseLogUtil.e("DEVMGR_HwDeviceConfigManager", "isMusicOrMarketService");
        return true;
    }
}
