package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.StatFs;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.mainprocess.mgr.hwotamanager.NotificationManager;
import com.huawei.hwdevice.outofprocess.datatype.DataOtaParametersV2;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwotamanager.IUpgradeStatusCallBack;
import com.huawei.hwservicesmgr.IOTAResultAIDLCallback;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jkj {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13908a = new Object();
    private static jkj c;
    private int b;
    private long g;
    private String h;
    private Context i;
    private HwVersionManager n;
    private long q;
    private String t;
    private int f = 0;
    private Handler x = null;
    private HandlerThread k = null;
    private boolean m = false;
    private boolean o = false;
    private IOTAResultAIDLCallback.Stub s = null;
    private IOTAResultAIDLCallback.Stub d = null;
    private Map<String, Integer> p = new HashMap(16);
    private boolean l = false;
    private boolean r = false;
    private BroadcastReceiver e = new BroadcastReceiver() { // from class: jkj.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.a("HwUpdateInteractors", "mBackgroundReceiver onReceive action :", action);
            if ("action_app_check_new_version_state".equals(action)) {
                int intExtra = intent.getIntExtra("state", -1);
                int intExtra2 = intent.getIntExtra("result", -1);
                String stringExtra = intent.getStringExtra("extra_band_imei");
                if (!TextUtils.isEmpty(stringExtra)) {
                    LogUtil.a("HwUpdateInteractors", "mBackgroundReceiver state :", Integer.valueOf(intExtra), " result :", Integer.valueOf(intExtra2));
                    jkj.this.a(stringExtra, intExtra, intExtra2);
                } else {
                    LogUtil.h("HwUpdateInteractors", "deviceId is null");
                }
            }
        }
    };
    private final BroadcastReceiver j = new BroadcastReceiver() { // from class: jkj.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Parcelable parcelableExtra;
            LogUtil.a("HwUpdateInteractors", "mConnectStateChangedReceiver(): intent = ", intent.getAction());
            if (context == null || !"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) || (parcelableExtra = intent.getParcelableExtra("deviceinfo")) == null) {
                return;
            }
            if (!(parcelableExtra instanceof DeviceInfo)) {
                LogUtil.a("HwUpdateInteractors", "! parcelableExtra mInstanceof DeviceInfo ");
                return;
            }
            DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
            int deviceConnectState = deviceInfo.getDeviceConnectState();
            LogUtil.a("HwUpdateInteractors", "mConnectStateChangedReceiver(): state = ", Integer.valueOf(deviceConnectState), ",deviceInfo = ", deviceInfo.toString());
            if (deviceConnectState == 3) {
                jkj.this.o(deviceInfo.getDeviceIdentify());
            } else {
                LogUtil.a("HwUpdateInteractors", "not device disconnected");
            }
        }
    };

    private jkj(Context context) {
        this.i = context;
        this.n = HwVersionManager.c(context);
        k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(String str) {
        Handler handler = this.x;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.obj = str;
            int c2 = c(str);
            if (c2 == 12) {
                obtainMessage.what = 20;
                this.x.sendMessage(obtainMessage);
                this.x.removeMessages(10);
            } else if (c2 == 3) {
                obtainMessage.what = 60;
                this.x.sendMessage(obtainMessage);
            } else if (c2 == 4 || c2 == 6) {
                obtainMessage.what = 70;
                this.x.sendMessage(obtainMessage);
            } else {
                LogUtil.h("HwUpdateInteractors", "device disconnected default");
                this.t = this.i.getString(R$string.IDS_music_management_disconnection);
                jkk.d().d(5, str, 7, this.t);
                d(str);
            }
        }
    }

    public static jkj d() {
        jkj jkjVar;
        synchronized (f13908a) {
            if (c == null) {
                c = new jkj(BaseApplication.getContext());
            }
            jkjVar = c;
        }
        return jkjVar;
    }

    public void j(String str) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_app_check_new_version_state");
        BroadcastManagerUtil.bFA_(this.i, this.e, intentFilter, LocalBroadcast.c, null);
        this.m = true;
        f();
        Handler handler = this.x;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(50, 500L);
        }
        jkn.a().c(str);
        LogUtil.a("HwUpdateInteractors", "startBackgroundUpdate enter notifyProgress");
        jkn.a().c(2, jkk.d().i(str));
    }

    public boolean j() {
        Iterator<Map.Entry<String, Integer>> it = this.p.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue().intValue() == 6) {
                return true;
            }
        }
        return false;
    }

    public void g(final String str) {
        LogUtil.a("HwUpdateInteractors", "startReceiveManualTransfer() enter");
        String j = HwVersionManager.c(BaseApplication.getContext()).j(str);
        String g = HwVersionManager.c(BaseApplication.getContext()).g(str);
        LogUtil.a("HwUpdateInteractors", "startReceiveAutoTransfer() version = ", j, " filePath = ", g);
        DataOtaParametersV2 dataOtaParametersV2 = new DataOtaParametersV2();
        dataOtaParametersV2.setOtaInterval(109000L);
        jfq.c().c(e(str), g + "@" + j, new Gson().toJson(dataOtaParametersV2), 0, new IOTAResultAIDLCallback.Stub() { // from class: jkj.4
            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onFileTransferState(int i) throws RemoteException {
                jkj.this.a(i, str);
            }

            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onUpgradeFailed(int i, String str2) throws RemoteException {
                jkj.this.e(i, str2, str);
            }

            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onFileRespond(int i) throws RemoteException {
                jkj.this.b(i, str);
            }

            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onFileTransferReport(String str2) {
                jkj.this.s(str2);
            }
        });
    }

    public void a(String str) {
        LogUtil.a("HwUpdateInteractors", "initUpdateInteractors");
        o();
        b(false);
        if (c(str) == 3) {
            d(str);
        }
        Context context = this.i;
        if (context != null) {
            HwVersionManager.c(context).l(str);
        }
        d(str, 0);
        jkn.a().e();
    }

    public void o() {
        LogUtil.a("HwUpdateInteractors", "enter unregisterBackgroundReceiver");
        try {
            BroadcastReceiver broadcastReceiver = this.e;
            if (broadcastReceiver == null || !this.m) {
                return;
            }
            this.i.unregisterReceiver(broadcastReceiver);
            this.m = false;
        } catch (IllegalArgumentException e) {
            LogUtil.b("HwUpdateInteractors", "unregisterBackgroundReceiver IllegalArgumentException ", ExceptionUtils.d(e));
        }
    }

    public boolean h(String str) {
        if (this.p.size() <= 0) {
            return false;
        }
        for (Map.Entry<String, Integer> entry : this.p.entrySet()) {
            if (!TextUtils.equals(str, entry.getKey()) && e(entry, e(entry.getKey()))) {
                return true;
            }
        }
        return false;
    }

    private boolean e(Map.Entry<String, Integer> entry, DeviceInfo deviceInfo) {
        if (deviceInfo == null || deviceInfo.getDeviceConnectState() != 2) {
            return false;
        }
        int intValue = entry.getValue().intValue();
        return intValue == 6 || intValue == 4;
    }

    public int c(String str) {
        Integer num;
        int intValue = (!this.p.containsKey(str) || (num = this.p.get(str)) == null) ? 0 : num.intValue();
        LogUtil.a("HwUpdateInteractors", "getOtaStatus deviceId ", CommonUtil.l(str), " OtaStatus ", Integer.valueOf(intValue));
        return intValue;
    }

    public void d(String str, int i) {
        LogUtil.a("HwUpdateInteractors", "setOtaStatus deviceId ", CommonUtil.l(str), " OtaStatus ", Integer.valueOf(i));
        this.p.put(str, Integer.valueOf(i));
    }

    public void h() {
        if (this.p.size() > 0) {
            for (Map.Entry<String, Integer> entry : this.p.entrySet()) {
                String key = entry.getKey();
                if (entry.getValue().intValue() != 12) {
                    this.p.put(key, 0);
                }
            }
        }
    }

    public Map<String, Integer> c() {
        return this.p;
    }

    public void b(final boolean z) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: jkj.5
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("HwUpdateInteractors", "enter deleteMessage");
                MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
                NotificationManager notificationManager = new NotificationManager(jkj.this.i);
                List<MessageObject> messages = messageCenterApi.getMessages(String.valueOf(19), "device_ota");
                LogUtil.a("HwUpdateInteractors", "makeMessage, delete messageList, messageList.size() = ", Integer.valueOf(messages.size()));
                for (int i = 0; i < messages.size(); i++) {
                    try {
                        if (!z) {
                            messageCenterApi.deleteMessage(messages.get(i).getMsgId());
                        }
                        notificationManager.d(20171027);
                        LogUtil.a("HwUpdateInteractors", "cancelNotification device ota");
                    } catch (NumberFormatException e) {
                        LogUtil.b("HwUpdateInteractors", "delete error", ExceptionUtils.d(e));
                        return;
                    }
                }
            }
        });
    }

    public void i(String str) {
        String str2;
        String str3;
        LogUtil.a("HwUpdateInteractors", "start_makeMessage");
        MessageObject messageObject = new MessageObject();
        messageObject.setModule("device");
        String string = this.i.getString(R$string.IDS_device_software_update);
        DeviceInfo e = e(str);
        if (e != null) {
            str2 = e.getDeviceName();
            LogUtil.a("HwUpdateInteractors", "getCurrentDeviceInfo() deviceName = ", str2);
            str3 = e.getDeviceIdentify();
        } else {
            str2 = "";
            str3 = "";
        }
        LogUtil.a("HwUpdateInteractors", "messageTitle = ", string);
        messageObject.setMsgTitle(string);
        messageObject.setMsgContent(this.i.getString(R$string.IDS_update_device_notification, kxz.j(this.i, str3), str2));
        messageObject.setMsgId("D201710261048");
        messageObject.setWeight(1);
        messageObject.setPosition(3);
        messageObject.setType("device_ota");
        messageObject.setCreateTime(System.currentTimeMillis());
        messageObject.setDetailUri("messagecenter://device_ota?key=" + str3);
        LogUtil.a("HwUpdateInteractors", "end_to_set_message");
        NotificationManager notificationManager = new NotificationManager(this.i, messageObject);
        LogUtil.a("HwUpdateInteractors", "notificationManager =  ", notificationManager);
        notificationManager.e(20171027L);
        LogUtil.a("HwUpdateInteractors", "end_makeMessage");
    }

    public void a(final String str, final int i) {
        DeviceInfo e = e(str);
        String str2 = (e == null || !cvt.c(e.getProductType())) ? "device_ota" : "device_ota1";
        final MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        messageCenterApi.getMessages(String.valueOf(19), str2, new IBaseResponseCallback() { // from class: jkj.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                List list;
                if (i2 == 0 && (obj instanceof List)) {
                    try {
                        list = (List) obj;
                    } catch (ClassCastException e2) {
                        LogUtil.b("HwUpdateInteractors", "makingMessage ClassCastException ", ExceptionUtils.d(e2));
                        list = null;
                    }
                    if (!jkj.this.c((List<MessageObject>) list)) {
                        jkj.this.c(str, messageCenterApi, i);
                    } else {
                        LogUtil.a("HwUpdateInteractors", "has message donot makeMessage, ");
                    }
                }
            }
        });
    }

    public void d(String str, String str2) {
        if (this.n == null) {
            this.n = HwVersionManager.c(this.i);
        }
        this.n.b(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final String str, final MessageCenterApi messageCenterApi, final int i) {
        messageCenterApi.getMessageId(String.valueOf(19), "device_ota", new IBaseResponseCallback() { // from class: jkj.6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                String str2;
                String str3;
                LogUtil.a("HwUpdateInteractors", "generateMessage() errCode = ", Integer.valueOf(i2));
                if (i2 == 0 && (obj instanceof String)) {
                    String str4 = (String) obj;
                    LogUtil.a("HwUpdateInteractors", "generateConnectedMessage messageID = ", str4);
                    MessageObject messageObject = new MessageObject();
                    messageObject.setMsgId(str4);
                    messageObject.setModule(String.valueOf(19));
                    messageObject.setType("device_ota");
                    messageObject.setMsgType(2);
                    messageObject.setPosition(i);
                    messageObject.setHuid(SharedPreferenceUtil.getInstance(jkj.this.i).getUserID());
                    String string = jkj.this.i.getString(R$string.IDS_device_software_update);
                    messageObject.setMsgTitle(string);
                    messageObject.setMetadata(string);
                    messageObject.setReadFlag(0);
                    messageObject.setExpireTime(0L);
                    LogUtil.a("HwUpdateInteractors", "generateConnectedMessage mstTitle = ", string);
                    DeviceInfo e = jkj.this.e(str);
                    if (e != null) {
                        LogUtil.c("HwUpdateInteractors", "generateConnectedMessage identify = ", e.getDeviceIdentify());
                        str2 = e.getDeviceIdentify();
                        str3 = jkj.this.i.getString(R$string.IDS_available_for_your_device, kxz.j(jkj.this.i, e.getDeviceIdentify()), e.getDeviceName());
                    } else {
                        str2 = "";
                        str3 = "";
                    }
                    messageObject.setMsgContent(str3);
                    LogUtil.a("HwUpdateInteractors", "generateConnectedMessage mstContent = ", str3);
                    messageObject.setCreateTime(System.currentTimeMillis());
                    messageObject.setDetailUri("messagecenter://device_ota?key=" + str2);
                    messageObject.setImgUri("assets://localMessageIcon/ic_band_update.png");
                    messageCenterApi.insertMessage(messageObject);
                    LogUtil.a("HwUpdateInteractors", "generateConnectedMessage leave");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(List<MessageObject> list) {
        LogUtil.a("HwUpdateInteractors", "isMessageExists enter");
        if (list == null) {
            LogUtil.h("HwUpdateInteractors", "isMessageExists messageList == null");
            return false;
        }
        Iterator<MessageObject> it = list.iterator();
        while (it.hasNext()) {
            if ("device_ota".equals(Uri.parse(it.next().getDetailUri()).getHost())) {
                LogUtil.a("HwUpdateInteractors", "isMessageExists message exists");
                return true;
            }
        }
        LogUtil.a("HwUpdateInteractors", "isMessageExists message not exists");
        return false;
    }

    public DeviceInfo e(String str) {
        return jpt.b(str, "HwUpdateInteractors");
    }

    private void p(String str) {
        File file;
        if (TextUtils.isEmpty(str) || (file = FileUtils.getFile(str)) == null) {
            return;
        }
        ReleaseLogUtil.e("HwUpdateInteractors", "ota length : ", Long.valueOf(file.length()));
    }

    public void c(String str, int i) {
        jkv.b().d(true);
        if (d().h(str)) {
            LogUtil.h("HwUpdateInteractors", "startOtaFileQueryTransfer() isTransferringOtherOta is true");
            return;
        }
        int c2 = c(str);
        if (c2 == 4 || c2 == 6) {
            LogUtil.h("HwUpdateInteractors", "startOtaFileQueryTransfer() return otaStatus = ", Integer.valueOf(c2));
            return;
        }
        DeviceInfo e = e(str);
        if (e == null) {
            LogUtil.h("HwUpdateInteractors", "startOtaFileQueryTransfer() currentDeviceInfo == null");
            l();
            return;
        }
        HwVersionManager c3 = HwVersionManager.c(BaseApplication.getContext());
        String j = c3.j(str);
        String g = c3.g(str);
        LogUtil.a("R_OTA_HwUpdateInteractors", "startOtaFileQueryTransfer() version = ", j, " updateMode = ", Integer.valueOf(i), " filePath = ", g, " current version ", e.getSoftVersion());
        if (j != null && j.equals(e.getSoftVersion())) {
            LogUtil.h("HwUpdateInteractors", "startOtaFileQueryTransfer() current version is Latest version");
            d(str, 0);
            c3.m(str);
            l();
            return;
        }
        p(g);
        DeviceCapability e2 = cvs.e(str);
        if (e2 != null && e2.getIsSupportDeviceRequestCheck()) {
            e(str, i, j, g, null);
        } else if (i == 0) {
            c(str, j, g, (IOTAResultAIDLCallback.Stub) null);
        } else {
            d(str, j, g);
        }
    }

    private void l() {
        jrj.b("user-otaDownloadFile");
        jrj.b("auto-deviceOta-downloadFile");
        jrs.b();
    }

    private void e(final String str, final int i, final String str2, final String str3, final IOTAResultAIDLCallback.Stub stub) {
        LogUtil.a("HwUpdateInteractors", "queryDeviceTransmitMode enter");
        jkv.b().b(str, new IBaseResponseCallback() { // from class: jkj.8
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (!(obj instanceof Integer)) {
                    LogUtil.h("HwUpdateInteractors", "queryDeviceTransmitMode error");
                    return;
                }
                int intValue = ((Integer) obj).intValue();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("0x01", intValue);
                } catch (JSONException unused) {
                    LogUtil.b("HwUpdateInteractors", "queryDeviceTransmitMode response JSONException");
                }
                jrd.b(jkj.this.e(str), "050919", "0", "", jSONObject.toString());
                if (intValue == 255) {
                    intValue = i;
                }
                if (intValue != 0) {
                    jkj.this.d(str, str2, str3);
                } else {
                    jkj.this.c(str, str2, str3, stub);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final String str, String str2, String str3) {
        LogUtil.a("R_OTA_HwUpdateInteractors", "enter backgroundTransfer");
        jkv.b().b(str, str2, 2, str3, new IOTAResultAIDLCallback.Stub() { // from class: jkj.10
            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onFileTransferState(int i) throws RemoteException {
                jkj.this.e(i, str);
            }

            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onUpgradeFailed(int i, String str4) throws RemoteException {
                jkj.this.b(i, str4, str);
            }

            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onFileRespond(int i) throws RemoteException {
                jkj.this.c(i, str);
            }

            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onFileTransferReport(String str4) {
                jkj.this.n(str4);
            }
        });
        this.q = System.currentTimeMillis();
        SharedPreferenceManager.d(BaseApplication.getContext(), System.currentTimeMillis(), "EXCE_OTA_APP_UPG_TRANS_START");
        this.l = false;
    }

    public void f(final String str) {
        LogUtil.a("R_OTA_HwUpdateInteractors", "startReceiveAutoTransfer() enter");
        f();
        String j = HwVersionManager.c(BaseApplication.getContext()).j(str);
        String g = HwVersionManager.c(BaseApplication.getContext()).g(str);
        LogUtil.a("HwUpdateInteractors", "startReceiveAutoTransfer() version = ", j, " filePath = ", g);
        DataOtaParametersV2 dataOtaParametersV2 = new DataOtaParametersV2();
        dataOtaParametersV2.setOtaInterval(109000L);
        jfq.c().c(e(str), g + "@" + j, new Gson().toJson(dataOtaParametersV2), 2, new IOTAResultAIDLCallback.Stub() { // from class: jkj.9
            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onFileTransferState(int i) throws RemoteException {
                jkj.this.e(i, str);
            }

            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onUpgradeFailed(int i, String str2) throws RemoteException {
                jkj.this.b(i, str2, str);
            }

            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onFileRespond(int i) throws RemoteException {
                jkj.this.c(i, str);
            }

            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onFileTransferReport(String str2) {
                jkj.this.n(str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(String str) {
        LogUtil.a("HwUpdateInteractors", "backgroundTransferReport，transferWay: ", str);
        IOTAResultAIDLCallback.Stub stub = this.d;
        if (stub != null) {
            try {
                stub.onFileTransferReport(str);
            } catch (RemoteException e) {
                LogUtil.b("HwUpdateInteractors", "backgroundTransferReport error ", ExceptionUtils.d(e));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, String str) {
        this.l = true;
        LogUtil.a("HwUpdateInteractors", "backgroundTransferState: percentage = ", Integer.valueOf(i));
        if (c(str) != 4) {
            d(str, 4);
        }
        IOTAResultAIDLCallback.Stub stub = this.d;
        if (stub != null) {
            try {
                stub.onFileTransferState(i);
            } catch (RemoteException e) {
                LogUtil.b("HwUpdateInteractors", "backgroundTransferState error ", ExceptionUtils.d(e));
            }
        }
        jkn.a().c(1, i);
        jkk.d().b(i, str, 3);
        jkk.d().b(str, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, String str, String str2) {
        LogUtil.a("R_OTA_HwUpdateInteractors", "backgroundTransferFailed: errorCode = ", Integer.valueOf(i), " errorMessage = ", str);
        IOTAResultAIDLCallback.Stub stub = this.d;
        if (stub != null) {
            try {
                stub.onUpgradeFailed(i, str);
            } catch (RemoteException e) {
                LogUtil.b("HwUpdateInteractors", "backgroundTransferFailed error ", ExceptionUtils.d(e));
            }
        }
        if (i == 109019) {
            g(str2);
            return;
        }
        if (i == 109020) {
            f(str2);
            return;
        }
        c(str2, 1001, i);
        d(str2, 0);
        this.t = d(str2, i, str);
        jkk.d().b(str2, false, true);
        jkn.a().d(BaseApplication.getContext().getString(R$string.IDS_device_auto_update_transfer_failed), this.t);
        jkk.d().d(5, str2, i, this.t);
        jkk.d().b(str2, 0);
        jkk.d().d(str2, (IUpgradeStatusCallBack) null);
        l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, String str) {
        LogUtil.a("R_OTA_HwUpdateInteractors", "backgroundTransferRespond checkResult", Integer.valueOf(i));
        d(str, 0);
        IOTAResultAIDLCallback.Stub stub = this.d;
        if (stub != null) {
            try {
                stub.onFileRespond(i);
            } catch (RemoteException e) {
                LogUtil.b("HwUpdateInteractors", "backgroundTransferRespond error ", ExceptionUtils.d(e));
            }
        }
        e(str, i);
        jrd.c(str, false);
        jkk.d().b(100, str, 4);
        jkk.d().b(str, 100);
        jkk.d().d(str, (IUpgradeStatusCallBack) null);
        l();
    }

    private void e(String str, int i) {
        DeviceCapability e = cvs.e(str);
        if (e != null && e.getIsSupportUpdateChange()) {
            LogUtil.a("HwUpdateInteractors", "device support update change");
            jkv.b().a(str, 1);
        }
        b(false);
        this.t = "";
        jkn.a().e();
        if (i != 0) {
            jkk.d().b(str, true, true);
            this.g = System.currentTimeMillis();
            SharedPreferenceManager.d(BaseApplication.getContext(), System.currentTimeMillis(), "EXCE_OTA_APP_UPG_TRANS_STOP_TIME");
            HashMap hashMap = new HashMap(20);
            DeviceInfo e2 = e(str);
            hashMap.put("product_type", Integer.valueOf(e2 != null ? e2.getProductType() : -1));
            if (this.l) {
                hashMap.put("status", 2);
            } else {
                hashMap.put("status", 3);
            }
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090026.value(), hashMap, 0);
            c(str, 1000, i);
        } else {
            c(str, 1001, 1002);
        }
        HwVersionManager.c(BaseApplication.getContext()).d(str);
    }

    private void c(String str, int i, int i2) {
        LogUtil.a("HwUpdateInteractors", "enter sentAutoUpdateEvent. errorMsg=", Integer.valueOf(i2));
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        DeviceInfo e = e(str);
        if (e == null) {
            LogUtil.a("HwUpdateInteractors", "enter sentAutoUpdateEvent device is null.");
            return;
        }
        if (e.getProductType() != -1) {
            linkedHashMap.put(DeviceCategoryFragment.DEVICE_TYPE, String.valueOf(e.getProductType()));
            linkedHashMap.put("device_classfication", String.valueOf(cwc.b(e.getProductType())));
        }
        if (i == 1001) {
            linkedHashMap.put("operationID", "10");
            linkedHashMap.put("errorID", String.valueOf(i2));
            if (CommonUtil.br()) {
                linkedHashMap.put(com.huawei.hwidauth.datatype.DeviceInfo.TAG_DEVICE_ID, CommonUtil.l(CommonUtil.h()));
                LogUtil.a("HwUpdateInteractors", "enter sentAutoUpdateEvent. CommonUtil.fuzzyData(CommonUtil.getEmuiDeviceUdid()) ", CommonUtil.l(CommonUtil.h()));
            } else {
                linkedHashMap.put(com.huawei.hwidauth.datatype.DeviceInfo.TAG_DEVICE_ID, CommonUtil.l(CommonUtil.an()));
                LogUtil.a("HwUpdateInteractors", "enter sentAutoUpdateEvent. CommonUtil.fuzzyData(CommonUtil.getUdidBySn()) ", CommonUtil.l(CommonUtil.an()));
            }
        } else {
            linkedHashMap.put("operationID", "9");
            long j = this.q;
            if (j > 0) {
                long j2 = this.g;
                if (j2 > 0) {
                    long j3 = j2 - j;
                    if (j3 > 0) {
                        linkedHashMap.put("OTATime", String.valueOf(j3));
                        c(str, linkedHashMap, j3);
                    }
                }
            }
        }
        HwVersionManager c2 = HwVersionManager.c(BaseApplication.getContext());
        linkedHashMap.put("deviceNewVersion", c2.j(str));
        linkedHashMap.put("versionID", c2.i(str));
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_DEVICE_OTA_UPDATE_80020004.value(), linkedHashMap);
    }

    public void c(String str, LinkedHashMap<String, String> linkedHashMap, long j) {
        if (TextUtils.isEmpty(HwVersionManager.c(BaseApplication.getContext()).g(str)) || !this.l) {
            return;
        }
        String valueOf = String.valueOf((FileUtils.getFile(r5).length() / 1.024d) / j);
        linkedHashMap.put("OTASpeed", valueOf);
        ReleaseLogUtil.e("HwUpdateInteractors", "ota time : ", Long.valueOf(j), ", ota speed : ", valueOf);
    }

    public void c(final String str, String str2, String str3, IOTAResultAIDLCallback.Stub stub) {
        this.f = 0;
        c(stub);
        jkv.b().b(str, str2, 0, str3, new IOTAResultAIDLCallback.Stub() { // from class: jkj.3
            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onFileTransferState(int i) throws RemoteException {
                jkj.this.a(i, str);
            }

            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onUpgradeFailed(int i, String str4) throws RemoteException {
                jkj.this.e(i, str4, str);
            }

            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onFileRespond(int i) throws RemoteException {
                jkj.this.b(i, str);
            }

            @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
            public void onFileTransferReport(String str4) {
                jkj.this.s(str4);
            }
        });
        DeviceInfo e = e(str);
        if (e != null) {
            HwVersionManager.c(BaseApplication.getContext()).a(e.getSecurityDeviceId(), e.getDeviceIdentify());
        }
        this.l = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(String str) {
        LogUtil.a("HwUpdateInteractors", "manualTransferReport，transferWay: ", str);
        IOTAResultAIDLCallback.Stub stub = this.s;
        if (stub != null) {
            try {
                stub.onFileTransferReport(str);
            } catch (RemoteException e) {
                LogUtil.b("HwUpdateInteractors", "manualTransferReport error ", ExceptionUtils.d(e));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str) {
        this.l = true;
        if (c(str) != 6) {
            HwVersionManager.c(BaseApplication.getContext()).a(true);
            d(str, 6);
            Intent intent = new Intent();
            intent.setAction("com.huawei.bone.action.DEVICE_UPGRADING");
            this.i.sendBroadcast(intent, LocalBroadcast.c);
            jkn.a().e();
        }
        this.f = i;
        if (this.s != null) {
            LogUtil.a("HwUpdateInteractors", "onFileTransferState,mOtaCallBack != null");
            try {
                this.s.onFileTransferState(i);
            } catch (RemoteException e) {
                LogUtil.b("HwUpdateInteractors", "transferFileTransferState RemoteException ", ExceptionUtils.d(e));
            }
        } else {
            LogUtil.a("HwUpdateInteractors", "onFileTransferState,mOtaCallBack == null");
        }
        jkk.d().b(i, str, 3);
        jkk.d().b(str, i);
        a(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, String str, String str2) {
        LogUtil.a("R_OTA_HwUpdateInteractors", "transferUpgradeFailed errorCode ", Integer.valueOf(i), " errorMessage = ", str);
        if (this.s != null) {
            LogUtil.a("HwUpdateInteractors", "onUpgradeFailed,mOtaCallBack != null");
            try {
                this.s.onUpgradeFailed(i, str);
            } catch (RemoteException e) {
                LogUtil.b("HwUpdateInteractors", "transferUpgradeFailed RemoteException ", ExceptionUtils.d(e));
            }
        } else {
            LogUtil.a("HwUpdateInteractors", "onUpgradeFailed,mOtaCallBack == null");
        }
        if (i == 109019) {
            g(str2);
            return;
        }
        if (i == 109020) {
            f(str2);
            return;
        }
        this.f = 0;
        jkk.d().b(str2, false, false);
        HwVersionManager.c(BaseApplication.getContext()).a(false);
        d(str2, 7);
        this.t = d(str2, i, str);
        jkk.d().d(5, str2, i, this.t);
        jkk.d().b(str2, 0);
        jkk.d().d(jkk.d().c(str2), (IUpgradeStatusCallBack) null);
        l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, String str) {
        LogUtil.a("R_OTA_HwUpdateInteractors", "manualTransferRespond checkResult", Integer.valueOf(i));
        this.f = 100;
        a(true);
        jkk.d().d(str, 0);
        HwVersionManager.c(BaseApplication.getContext()).a(false);
        d(str, 12);
        f();
        Handler handler = this.x;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = 10;
            obtainMessage.obj = str;
            this.x.sendMessageDelayed(obtainMessage, 20000L);
        }
        this.b = i;
        if (i != 0) {
            jkk.d().b(str, true, false);
            HashMap hashMap = new HashMap(20);
            DeviceInfo e = e(str);
            hashMap.put("product_type", Integer.valueOf(e != null ? e.getProductType() : -1));
            hashMap.put("status", 1);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090026.value(), hashMap, 0);
        } else {
            jkk.d().b(str, false, false);
        }
        jrd.c(str, false);
        jkk.d().b(100, str, 4);
        jkk.d().b(str, 100);
        jkk.d().d(jkk.d().c(str), (IUpgradeStatusCallBack) null);
        l();
    }

    public void a(boolean z) {
        if (this.r != z) {
            LogUtil.a("HwUpdateInteractors", "recordOtaTransferSuccessFlag isSuccess is ", Boolean.valueOf(z), " mIsTransferSuccess is ", Boolean.valueOf(this.r));
            SharedPreferenceManager.e(String.valueOf(1003), "flag_ota_update_success", z);
            this.r = z;
        }
    }

    public String d(String str, int i, String str2) {
        DeviceInfo e = e(str);
        if (e == null) {
            LogUtil.h("HwUpdateInteractors", "handleFailed,deviceInfo is null");
            return "";
        }
        switch (i) {
            case 104002:
            case 109002:
                if (TextUtils.isEmpty(str2)) {
                    return "";
                }
                String e2 = UnitUtil.e(CommonUtil.a(str2, 10), 2, 0);
                LogUtil.a("HwUpdateInteractors", "battery :", e2);
                return String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_settings_firmware_upgrade_low_battery), e.getDeviceName(), e2);
            case 104003:
                return BaseApplication.getContext().getString(R$string.IDS_settings_firmware_upgrade_file_not_exist);
            case 104007:
                return BaseApplication.getContext().getString(R$string.IDS_music_management_disconnection);
            case 109018:
                return BaseApplication.getContext().getString(R$string.IDS_ota_update_high_temperature_error);
            case 109025:
                if (TextUtils.isEmpty(str2)) {
                    return "";
                }
                String e3 = UnitUtil.e(CommonUtil.a(str2, 10), 2, 0);
                LogUtil.a("HwUpdateInteractors", "earBattery : ", e3);
                return String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_device_ear_low_battery), e3);
            default:
                return BaseApplication.getContext().getString(R$string.IDS_settings_firmware_upgrade_talk_band_failed);
        }
    }

    public void f() {
        LogUtil.a("HwUpdateInteractors", "enter registerConnectStateBroadcast");
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(this.i, this.j, intentFilter, LocalBroadcast.c, null);
        this.o = true;
    }

    private void n() {
        LogUtil.a("HwUpdateInteractors", "enter unregisterConnectStateChangedBroadcast");
        BroadcastReceiver broadcastReceiver = this.j;
        if (broadcastReceiver == null || !this.o) {
            return;
        }
        this.i.unregisterReceiver(broadcastReceiver);
        this.o = false;
    }

    private void k() {
        HandlerThread handlerThread = new HandlerThread("HwUpdateInteractors");
        this.k = handlerThread;
        handlerThread.start();
        this.x = new b(this.k.getLooper());
    }

    class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            LogUtil.a("HwUpdateInteractors", "UpdateHandler receive msg:", Integer.valueOf(message.what));
            String str = message.obj instanceof String ? (String) message.obj : "";
            int i = message.what;
            if (i == 10 || i == 20) {
                jkj.this.m(str);
                return;
            }
            if (i == 50) {
                nrh.b(jkj.this.i, R$string.IDS_device_auto_update_background_download);
                return;
            }
            if (i == 60) {
                jkj.this.l(str);
            } else if (i == 70) {
                jkj.this.k(str);
            } else {
                LogUtil.h("HwUpdateInteractors", "handleMessage error");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(String str) {
        this.t = this.i.getString(R$string.IDS_music_management_disconnection);
        jkk.d().d(5, str, 7, this.t);
        d(str, 5);
        jkn.a().d(BaseApplication.getContext().getString(R$string.IDS_device_auto_update_transfer_failed), this.t);
        LogUtil.h("HwUpdateInteractors", "enter handleTransferDisconnect :", this.t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(String str) {
        d(str, 2);
        this.h = this.i.getString(R$string.IDS_music_management_disconnection);
        jkk.d().d(5, str, 7, this.h);
        jkn.a().d(BaseApplication.getContext().getString(R$string.IDS_device_auto_update_download_failed), this.h);
        d(str);
        LogUtil.h("HwUpdateInteractors", "enter handleDownloadDisconnect :", this.h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(String str) {
        n();
        if (this.s != null) {
            LogUtil.a("HwUpdateInteractors", "onFileRespond,mOtaCallBack != null");
            try {
                this.s.onFileRespond(this.b);
            } catch (RemoteException e) {
                LogUtil.a("HwUpdateInteractors", "mOtaCallBack.onFileRespond error:", e.getMessage());
            }
        } else {
            LogUtil.a("HwUpdateInteractors", "onFileRespond,mOtaCallBack == null");
        }
        b(false);
        d(str, 0);
        HwVersionManager.c(BaseApplication.getContext()).m(str);
        this.f = 0;
    }

    public boolean b(long j) {
        StatFs statFs;
        LogUtil.a("HwUpdateInteractors", "checkMemory needSize = ", Long.valueOf(j));
        try {
            statFs = new StatFs(this.i.getFilesDir().getCanonicalPath());
        } catch (IOException e) {
            LogUtil.b("HwUpdateInteractors", "checkMemory IOException ", ExceptionUtils.d(e));
            statFs = null;
        }
        if (statFs != null) {
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize()) > j;
        }
        LogUtil.a("HwUpdateInteractors", "stats is null");
        return false;
    }

    public void c(IOTAResultAIDLCallback.Stub stub) {
        if (stub != null) {
            this.s = stub;
        }
        int i = this.f;
        if (i != 0) {
            try {
                LogUtil.a("HwUpdateInteractors", "onFileTransferState,mCurrentPercent:", Integer.valueOf(i));
                stub.onFileTransferState(this.f);
            } catch (RemoteException e) {
                LogUtil.a("HwUpdateInteractors", "onFileTransferState,error:", e.getMessage());
            }
        }
    }

    public void i() {
        this.s = null;
    }

    public void a(String str, IOTAResultAIDLCallback.Stub stub) {
        this.d = stub;
        int f = jkk.d().f(str);
        try {
            LogUtil.a("HwUpdateInteractors", "onFileTransferState,mBackgroundTransferPercent:", Integer.valueOf(f));
            stub.onFileTransferState(f);
        } catch (RemoteException e) {
            LogUtil.a("HwUpdateInteractors", "onFileTransferState error ", ExceptionUtils.d(e));
        }
    }

    public void g() {
        this.d = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i, int i2) {
        switch (i) {
            case 19:
                f();
                break;
            case 20:
            default:
                LogUtil.h("HwUpdateInteractors", "updateDownloadState default");
                break;
            case 21:
                jkn.a().c(2, jkk.d().i(str));
                break;
            case 22:
                DeviceInfo e = e(str);
                if (e != null && e.getDeviceConnectState() != 2) {
                    this.h = this.i.getString(R$string.IDS_music_management_disconnection);
                } else {
                    this.h = e(this.i, i2);
                }
                jkn.a().d(BaseApplication.getContext().getString(R$string.IDS_device_auto_update_download_failed), this.h);
                n();
                break;
            case 23:
                LogUtil.a("HwUpdateInteractors", "STATE_DOWNLOAD_APP_SUCCESS");
                this.h = "";
                break;
        }
    }

    public static String e(Context context, int i) {
        if (context == null) {
            LogUtil.h("HwUpdateInteractors", "getErrorMessage context is null");
            return "";
        }
        if (i == 1) {
            return context.getString(R$string.IDS_update_download_check_failed);
        }
        if (i == 3) {
            return context.getString(R$string.IDS_update_network_abnormal);
        }
        if (i != 4) {
            return context.getString(R$string.IDS_update_download_failed);
        }
        return nsn.ae(BaseApplication.getContext()) ? context.getString(R$string.IDS_pad_phone_low_battery, UnitUtil.e(10.0d, 2, 0)) : context.getString(R$string.IDS_settings_firmware_upgrade_phone_low_battery);
    }

    public String b() {
        return this.h;
    }

    public String a() {
        return this.t;
    }

    public void d(String str) {
        LogUtil.a("R_OTA_HwUpdateInteractors", "handleIntent, cancel download app!");
        DeviceInfo e = d().e(str);
        kxl k = e != null ? kxz.k(BaseApplication.getContext(), e.getSecurityDeviceId()) : null;
        if (k == null || TextUtils.isEmpty(k.i())) {
            return;
        }
        jdk.e(str, true);
        LogUtil.a("HwUpdateInteractors", "isCancelSuccess ", Boolean.valueOf(lqi.d().d(k.i())));
    }

    public void e() {
        LogUtil.a("R_OTA_HwUpdateInteractors", "handleIntent, cancel all download app!");
        ArrayList arrayList = new ArrayList(16);
        for (Map.Entry<String, Integer> entry : d().c().entrySet()) {
            String key = entry.getKey();
            if (entry.getValue().intValue() == 3) {
                jdk.e(key, true);
                DeviceInfo e = d().e(key);
                if (e != null) {
                    arrayList.add(e.getSecurityDeviceId());
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            kxl c2 = kxu.c((String) it.next());
            if (c2 != null && !TextUtils.isEmpty(c2.i())) {
                lqi.d().d(c2.i());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwUpdateInteractors", "getAnswerDeviceStatus deviceId isEmpty");
            return false;
        }
        jkv b2 = jkv.b();
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            b2.b(str, 3, 2);
            LogUtil.h("HwUpdateInteractors", "getAnswerDeviceStatus no network connected");
            return false;
        }
        boolean z = CommonUtil.l(BaseApplication.getContext()) == 1 ? 1 : 0;
        int i = !z;
        LogUtil.a("HwUpdateInteractors", "getAnswerDeviceStatus isWifiType ", Boolean.valueOf(z));
        if (e(str, b2, i)) {
            return false;
        }
        if (z == 0) {
            b2.b(str, 3, i);
            return false;
        }
        if (ScreenUtil.a() && !CommonUtil.x(BaseApplication.getContext()) && jrd.a()) {
            LogUtil.h("HwUpdateInteractors", "getAnswerDeviceStatus ota activity is showing");
            return false;
        }
        DeviceInfo e = d().e(str);
        if (e != null && HwVersionManager.c(this.i).c(str, e.getSecurityDeviceId())) {
            b2.b(str, 2, i);
            LogUtil.h("HwUpdateInteractors", "getAnswerDeviceStatus ota startOtaFileQueryTransfer");
            jrj.d(1800000L, "user-otaDownloadFile");
            jrs.d(BaseApplication.getContext());
            d().c(str, 0);
            return false;
        }
        LogUtil.a("HwUpdateInteractors", "getAnswerDeviceStatus start device request download");
        b2.b(str, 0, i);
        return true;
    }

    private boolean e(String str, jkv jkvVar, int i) {
        if (TextUtils.isEmpty(kxz.j(this.i, str))) {
            LogUtil.h("HwUpdateInteractors", "getOtaAnswerResult no new version");
            jkvVar.b(str, 255, i);
            return true;
        }
        int c2 = d().c(str);
        if (c2 == 3) {
            LogUtil.h("HwUpdateInteractors", "getOtaAnswerResult ota is downloading");
            jkvVar.b(str, 1, i);
            return true;
        }
        if (c2 == 4 || c2 == 6) {
            LogUtil.h("HwUpdateInteractors", "getOtaAnswerResult ota is transferring");
            jkvVar.b(str, 2, i);
            return true;
        }
        if (!jkk.d().e()) {
            return false;
        }
        LogUtil.h("HwUpdateInteractors", "getOtaAnswerResult low battery");
        jkvVar.b(str, 4, i);
        return true;
    }
}
