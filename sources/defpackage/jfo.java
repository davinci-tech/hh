package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.device.api.EventBusApi;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.api.UserRecoverableException;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.ResolvableApiException;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.huawei.hms.support.hwid.HuaweiIdAdvancedManager;
import com.huawei.hms.support.hwid.bean.SignInByQrReq;
import com.huawei.hms.support.hwid.service.HuaweiIdAdvancedService;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.login.huaweilogin.ThirdPartyLoginManager;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jfo extends HwBaseManager implements BluetoothDataReceiveCallback {
    private static jfq c;
    private static jfo g;
    private a f;
    private int h;
    private HandlerThread i;
    private int j;
    private static final Object b = new Object();
    private static final OnFailureListener d = new OnFailureListener() { // from class: jfo.4
        @Override // com.huawei.hmf.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            if (exc == null) {
                LogUtil.h("HwWearableManager", "onFailure: exception is null");
            } else {
                c(exc);
            }
        }

        private void c(Exception exc) {
            if (exc instanceof ResolvableApiException) {
                LogUtil.h("HwWearableManager", "ResolvableApiException");
                jfo.d("1");
                jfo.b(CommonCode.ErrorCode.HMS_VERSION_CONFIGER_INVALID);
            } else if (exc instanceof UserRecoverableException) {
                b(exc);
            } else {
                d(exc);
            }
        }

        private void b(Exception exc) {
            if (exc instanceof UserRecoverableException) {
                Activity activity = BaseApplication.getActivity();
                Intent intent = ((UserRecoverableException) exc).getIntent();
                jfo.d("-1");
                if (activity == null || intent == null) {
                    jfo.b(300001);
                    jfo.d("1");
                    LogUtil.h("HwWearableManager", "signInByQrCode: currentActivity or intent is null");
                    return;
                }
                activity.startActivityForResult(intent, 3001);
            }
        }

        private void d(Exception exc) {
            if (exc instanceof ApiException) {
                int statusCode = ((ApiException) exc).getStatusCode();
                LogUtil.h("HwWearableManager", "onFailure statusCode:", Integer.valueOf(statusCode));
                if (statusCode != 2017) {
                    jfo.a("1");
                } else {
                    jfo.e().a(1);
                }
            }
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private static Map<Integer, List<IBaseResponseCallback>> f13787a = new HashMap(1);
    private static List<Integer> e = new ArrayList(Arrays.asList(1));

    static /* synthetic */ int c(jfo jfoVar) {
        int i = jfoVar.j;
        jfoVar.j = i + 1;
        return i;
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr == null) {
            LogUtil.h("HwWearableManager", "onDataReceived, data is null");
            return;
        }
        LogUtil.a("HwWearableManager", "Wearable manager receive data :", cvx.d(bArr));
        if (bArr.length < 2) {
            LogUtil.h("HwWearableManager", "onDataReceived responseData length less than 1");
        } else {
            c(bArr, deviceInfo);
        }
    }

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                Object obj = message.obj;
                DeviceInfo deviceInfo = obj instanceof DeviceInfo ? (DeviceInfo) obj : null;
                jfo jfoVar = jfo.this;
                jfoVar.d(jfoVar.h, deviceInfo);
            }
        }
    }

    private jfo(Context context) {
        super(context);
        this.j = 0;
        this.h = 0;
        jfq c2 = jfq.c();
        c = c2;
        if (c2 != null) {
            c2.e(26, this);
        } else {
            LogUtil.h("HwWearableManager", "HwWearableManager() sHwDeviceConfigManager is null");
        }
        HandlerThread handlerThread = new HandlerThread("HwWearableManager");
        this.i = handlerThread;
        handlerThread.start();
        this.f = new a(this.i.getLooper());
    }

    public static jfo e() {
        jfo jfoVar;
        synchronized (b) {
            if (f13787a.isEmpty()) {
                Iterator<Integer> it = e.iterator();
                while (it.hasNext()) {
                    f13787a.put(it.next(), new ArrayList(1));
                }
            }
            if (g == null) {
                g = new jfo(BaseApplication.getContext());
            }
            jfoVar = g;
        }
        return jfoVar;
    }

    public void a(String str, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwWearableManager", "ENTER sendAccount");
        DeviceCapability d2 = cvs.d();
        LogUtil.a("HwWearableManager", "sendAccount ability :", d2);
        if (d2 == null) {
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(-2, -2);
            }
            LogUtil.h("HwWearableManager", "ability is null, Do not sendAccount");
            return;
        }
        LogUtil.a("HwWearableManager", "sendAccount ability :", d2);
        if (!d2.isSupportAccount()) {
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(-2, -2);
            }
            LogUtil.h("HwWearableManager", "bot SupportAccount, Do not sendAccount");
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(26);
        deviceCommand.setCommandID(1);
        String c2 = cvx.c(str);
        String e2 = cvx.e(c2.length() / 2);
        String e3 = cvx.e(1);
        int length = e3.length() / 2;
        int length2 = e2.length() / 2;
        ByteBuffer allocate = ByteBuffer.allocate(Math.addExact(Math.addExact(length, length2), c2.length() / 2));
        allocate.put(cvx.a(e3));
        allocate.put(cvx.a(e2));
        allocate.put(cvx.a(c2));
        d(deviceCommand, allocate, 1, iBaseResponseCallback);
    }

    public void a(byte[] bArr, DeviceInfo deviceInfo) {
        LogUtil.a("HwWearableManager", "enter processDataFromBroadcast");
        c(bArr, deviceInfo);
    }

    private void c(byte[] bArr, DeviceInfo deviceInfo) {
        int i = 0;
        int i2 = a(bArr) == 100000 ? 0 : -1;
        LogUtil.a("HwWearableManager", "getResult errorCode :", Integer.valueOf(i2));
        byte b2 = bArr[1];
        Object obj = null;
        if (b2 == 1) {
            String d2 = cvx.d(bArr);
            try {
                for (cwd cwdVar : new cwl().a(d2.substring(4, d2.length())).e()) {
                    int parseInt = Integer.parseInt(cwdVar.e(), 16);
                    if (parseInt == 1) {
                        try {
                            obj = cwdVar.c();
                            i2 = 0;
                        } catch (cwg unused) {
                            LogUtil.b("HwWearableManager", "COMMAND_ID_GET_DATE error");
                            i2 = i;
                            a(bArr, i2, obj);
                        } catch (NumberFormatException unused2) {
                            LogUtil.b("HwWearableManager", "getHandsetInfo() error NumberFormatException");
                            i2 = i;
                            a(bArr, i2, obj);
                        }
                    } else if (parseInt == 127) {
                        obj = Integer.valueOf(Integer.parseInt(cwdVar.c(), 16));
                    } else {
                        LogUtil.h("HwWearableManager", "getResult not switch");
                    }
                }
            } catch (cwg unused3) {
                i = i2;
            } catch (NumberFormatException unused4) {
                i = i2;
            }
        } else if (b2 == 3) {
            LogUtil.a("HwWearableManager", "electronic card request");
            e(deviceInfo);
        } else if (b2 == 4) {
            d(bArr, deviceInfo);
        } else if (bArr.length > 2 && b2 == 7) {
            c(bArr);
        } else if (bArr.length > 2 && b2 == 9) {
            e(bArr);
        } else {
            LogUtil.h("HwWearableManager", "not support commandId");
        }
        a(bArr, i2, obj);
    }

    private void e(byte[] bArr) {
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            LogUtil.h("HwWearableManager", "handleLoginState messageHex is error");
            return;
        }
        try {
            List<cwd> e2 = new cwl().a(d2.substring(4)).e();
            if (e2 != null && !e2.isEmpty()) {
                for (cwd cwdVar : e2) {
                    if (CommonUtil.w(cwdVar.e()) == 1) {
                        String e3 = cvx.e(cwdVar.c());
                        if (e3 == null) {
                            LogUtil.h("HwWearableManager", "hmsLoginSuccess is null");
                            return;
                        } else if (e3.equals("0")) {
                            LogUtil.a("HwWearableManager", "SIGN_ACCOUNT_HMS_LOGIN_STATE_SUCCESS : ");
                            d("0");
                        } else {
                            LogUtil.a("HwWearableManager", "SIGN_ACCOUNT_HMS_LOGIN_STATE_fail : ");
                            d("1");
                        }
                    }
                }
                return;
            }
            LogUtil.h("HwWearableManager", "handleLoginState tlv is error");
        } catch (cwg unused) {
            LogUtil.b("HwWearableManager", "COMMAND_ID_GET_DATE TlvException");
        }
    }

    private void c(byte[] bArr) {
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            LogUtil.h("HwWearableManager", "handleSignAccount messageHex is error");
            return;
        }
        try {
            List<cwd> e2 = new cwl().a(d2.substring(4, d2.length())).e();
            if (e2 != null && !e2.isEmpty()) {
                String str = null;
                String str2 = null;
                for (cwd cwdVar : e2) {
                    int w = CommonUtil.w(cwdVar.e());
                    if (w == 2) {
                        str = cvx.e(cwdVar.c());
                        LogUtil.a("HwWearableManager", "accept from health qrCode:", str);
                    } else if (w == 3) {
                        str2 = cvx.e(cwdVar.c());
                        LogUtil.a("HwWearableManager", "accept from health qrSiteId:", str2);
                    } else if (w == 4) {
                        d("-1");
                        e(str, str2, cwdVar);
                    } else {
                        LogUtil.h("HwWearableManager", "handleSyncAccount unknown tlv type");
                    }
                }
                return;
            }
            LogUtil.h("HwWearableManager", "handleSyncAccount tlv is error");
        } catch (cwg unused) {
            LogUtil.b("HwWearableManager", "COMMAND_ID_GET_DATE TlvException");
        }
    }

    private void e(String str, String str2, cwd cwdVar) {
        LogUtil.a("HwWearableManager", "qrSources:", Integer.valueOf(CommonUtil.w(cwdVar.c())));
        Activity activity = BaseApplication.getActivity();
        Context context = BaseApplication.getContext();
        int m = str2 != null ? CommonUtil.m(context, str2) : 0;
        if (!CommonUtil.z(context)) {
            bGW_(str, str2, context, activity);
        } else {
            if (activity == null) {
                LogUtil.h("HwWearableManager", "litesdk: currentActivity is null");
                b(300001);
                d("1");
                return;
            }
            bGX_(str, activity, m);
        }
    }

    private void bGX_(String str, Activity activity, int i) {
        ThirdPartyLoginManager.getInstance().qrCodeAuthLogin(activity, str, i, "0", new IBaseResponseCallback() { // from class: jfo.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (i2 == 0) {
                    LogUtil.a("HwWearableManager", "QrAuthLoginResult isSuccess()");
                    jfo.a("0");
                } else {
                    LogUtil.h("HwWearableManager", "QrAuthLoginResult fail:", Integer.valueOf(i2));
                    jfo.a("1");
                    jfo.d("1");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(String str) {
        ((EventBusApi) Services.c("PluginDevice", EventBusApi.class)).publishHmsLoginState(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(String str) {
        DeviceCapability d2 = cvs.d();
        if (d2 == null) {
            LogUtil.h("HwWearableManager", "sendLoginStateHms deviceCapability is null");
        } else if (d2.isSupportAccountSwitch()) {
            e(str);
        } else {
            LogUtil.h("HwWearableManager", "sendLoginStateHms is not support");
        }
    }

    private void bGW_(String str, String str2, Context context, Activity activity) {
        HuaweiIdAdvancedService service;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("HwWearableManager", "qrCode or qrSiteId is error");
            return;
        }
        if (activity == null) {
            LogUtil.h("HwWearableManager", "handleSignAccount: currentActivity is null");
            service = HuaweiIdAdvancedManager.getService(context);
        } else {
            service = HuaweiIdAdvancedManager.getService(activity);
        }
        SignInByQrReq signInByQrReq = new SignInByQrReq();
        signInByQrReq.setQrCode(str);
        signInByQrReq.setQrSiteId(str2);
        service.signInByQrCode(signInByQrReq).addOnSuccessListener(new OnSuccessListener<Void>() { // from class: jfo.1
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Void r2) {
                LogUtil.a("HwWearableManager", "addOnSuccessListener onSuccess");
                jfo.a("0");
            }
        }).addOnFailureListener(d);
    }

    private void d(byte[] bArr, final DeviceInfo deviceInfo) {
        String d2 = cvx.d(bArr);
        if (d2 == null || d2.length() < 4) {
            LogUtil.h("HwWearableManager", "confirmElectronicCard messageHex is valid data");
            return;
        }
        try {
            for (cwd cwdVar : new cwl().a(d2.substring(4, d2.length())).e()) {
                if (Integer.parseInt(cwdVar.e(), 16) == 127) {
                    int parseInt = Integer.parseInt(cwdVar.c(), 16);
                    if (parseInt == 100000) {
                        LogUtil.a("HwWearableManager", "electronic card response");
                        b(deviceInfo);
                        this.j = 0;
                        return;
                    } else if (this.j < 3 && parseInt == 100009) {
                        this.f.postDelayed(new Runnable() { // from class: jfo.2
                            @Override // java.lang.Runnable
                            public void run() {
                                Message obtainMessage = jfo.this.f.obtainMessage();
                                obtainMessage.what = 1;
                                obtainMessage.obj = deviceInfo;
                                jfo.this.f.handleMessage(obtainMessage);
                                jfo.c(jfo.this);
                            }
                        }, PreConnectManager.CONNECT_INTERNAL);
                    }
                } else {
                    LogUtil.h("HwWearableManager", "confirmElectronicCard getResult not switch");
                }
            }
        } catch (cwg unused) {
            LogUtil.b("HwWearableManager", "COMMAND_ID_GET_DATE error TlvException");
        } catch (NumberFormatException unused2) {
            LogUtil.b("HwWearableManager", "confirmElectronicCard NumberFormatException exception");
        }
    }

    private void b(DeviceInfo deviceInfo) {
        if (this.h == 1) {
            if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getUuid())) {
                LogUtil.h("HwWearableManager", "deleteUuid DeviceInfo is null or uuid is empty");
                return;
            }
            LogUtil.a("HwWearableManager", "delete :", Integer.valueOf(SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(26), "electronicPrefix" + knl.a(deviceInfo.getUuid()))));
        }
    }

    public void e(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwWearableManager", "DeviceInfo is null");
            return;
        }
        kxz.d(deviceInfo.getDeviceIdentify(), "recElectronic", "true");
        String uuid = deviceInfo.getUuid();
        if (TextUtils.isEmpty(uuid)) {
            LogUtil.h("HwWearableManager", "save result uuid is empty");
            return;
        }
        int e2 = SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(26), "electronicPrefix" + knl.a(uuid), uuid, new StorageParams(1));
        LogUtil.a("HwWearableManager", "save result :", Integer.valueOf(e2));
        kxz.d(deviceInfo.getDeviceIdentify(), "saveElectronic", e2 != 0 ? "false" : "true");
        if (e2 == 0) {
            c(deviceInfo);
        }
    }

    private void c(DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(26);
        deviceCommand.setCommandID(3);
        byte[] e2 = e(100000);
        deviceCommand.setDataLen(e2.length);
        deviceCommand.setDataContent(e2);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        c.b(deviceCommand);
    }

    private byte[] e(int i) {
        return new bms().i(127, i).d();
    }

    public void d(String str, boolean z) {
        kxz.d(str, "upLoadServer", String.valueOf(z));
        if (HwVersionManager.c(BaseApplication.getContext()).i()) {
            kxz.d(str, "3", Boolean.valueOf(z));
        } else {
            kxz.d(str, "1", Boolean.valueOf(z));
        }
        if (!HwVersionManager.c(BaseApplication.getContext()).g()) {
            LogUtil.h("HwWearableManager", "electronic card is false not electronic card");
            HwVersionManager.c(BaseApplication.getContext()).d(false);
            return;
        }
        DeviceInfo e2 = jpt.e(str, "HwWearableManager");
        if (e2 == null || TextUtils.isEmpty(e2.getUuid())) {
            LogUtil.h("HwWearableManager", "electronic card DeviceInfo is null or uuid is empty");
            HwVersionManager.c(BaseApplication.getContext()).d(false);
            return;
        }
        if (TextUtils.isEmpty(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(26), "electronicPrefix" + knl.a(e2.getUuid())))) {
            LogUtil.h("HwWearableManager", "electronic card isEmpty is not electronic card");
            HwVersionManager.c(BaseApplication.getContext()).d(false);
        } else {
            a(z, e2);
        }
    }

    private void a(boolean z, DeviceInfo deviceInfo) {
        HwVersionManager.c(BaseApplication.getContext()).d(false);
        if (z) {
            d(1, deviceInfo);
        } else {
            d(0, deviceInfo);
        }
    }

    public void d(int i, DeviceInfo deviceInfo) {
        LogUtil.a("HwWearableManager", "enter notifyDevice value :", Integer.valueOf(i));
        this.h = i;
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(26);
        deviceCommand.setCommandID(4);
        StringBuilder sb = new StringBuilder(16);
        String e2 = cvx.e(i);
        String d2 = cvx.d(e2.length() / 2);
        sb.append(cvx.e(1));
        sb.append(d2);
        sb.append(e2);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        LogUtil.a("HwWearableManager", "electronic card notifyDevice");
        LogUtil.a("HwWearableManager", "sendCommand() electronic card retryTimes:", Integer.valueOf(this.j));
        c.b(deviceCommand);
    }

    public void a(int i) {
        String concat = cvx.e(1).concat(cvx.e(1)).concat(cvx.e(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(26);
        deviceCommand.setCommandID(7);
        deviceCommand.setDataContent(cvx.a(concat));
        deviceCommand.setDataLen(cvx.a(concat).length);
        LogUtil.a("HwWearableManager", "sendSyncAccountData() : valueString", concat);
        c.b(deviceCommand);
    }

    private void a(byte[] bArr, int i, Object obj) {
        synchronized (b()) {
            List<IBaseResponseCallback> list = f13787a.get(Integer.valueOf(bArr[1]));
            if (list != null) {
                if (obj != null && list.size() != 0) {
                    list.get(0).d(i, obj);
                    list.remove(0);
                } else if (list.size() != 0) {
                    list.get(0).d(100001, "UNKNOWN_ERROR");
                    list.remove(0);
                } else {
                    LogUtil.h("HwWearableManager", "getResult, removeList, callbackList.size() is 0");
                }
            } else {
                LogUtil.h("HwWearableManager", "getResult, removeList, callbackList is null");
            }
        }
    }

    private int a(byte[] bArr) {
        String d2 = cvx.d(bArr);
        if (d2 == null || d2.length() < 8) {
            LogUtil.h("HwWearableManager", "getInt messageHex is valid data");
            return 0;
        }
        try {
            return Integer.parseInt(d2.substring(8, d2.length()), 16);
        } catch (NumberFormatException unused) {
            LogUtil.b("HwWearableManager", "getInt NumberFormatException");
            return 0;
        }
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 26;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
        c.d(26);
        synchronized (b()) {
            Iterator<Integer> it = e.iterator();
            while (it.hasNext()) {
                int intValue = it.next().intValue();
                if (f13787a.get(Integer.valueOf(intValue)) != null) {
                    f13787a.get(Integer.valueOf(intValue)).clear();
                }
            }
        }
        c();
        a();
        LogUtil.a("HwWearableManager", "onDestroy() complete");
    }

    private static void a() {
        synchronized (b) {
            g = null;
        }
    }

    private void c() {
        synchronized (b) {
            HandlerThread handlerThread = this.i;
            if (handlerThread != null) {
                handlerThread.quit();
                this.i = null;
            }
        }
    }

    private static Map<Integer, List<IBaseResponseCallback>> b() {
        Map<Integer, List<IBaseResponseCallback>> map;
        synchronized (jfo.class) {
            map = f13787a;
        }
        return map;
    }

    private void d(DeviceCommand deviceCommand, ByteBuffer byteBuffer, int i, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (b()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = f13787a.get(Integer.valueOf(i));
                if (list != null) {
                    list.add(iBaseResponseCallback);
                } else {
                    LogUtil.h("HwWearableManager", "addToList, callbacks is null");
                }
            } else {
                LogUtil.h("HwWearableManager", "addToList, callback is null");
            }
        }
        deviceCommand.setDataLen(byteBuffer.array().length);
        deviceCommand.setDataContent(byteBuffer.array());
        c.b(deviceCommand);
    }

    private static void c(int i) {
        LogUtil.a("HwWearableManager", "Enter sendSyncAccountHmsError ", Integer.valueOf(i));
        String e2 = cvx.e(i);
        String concat = cvx.e(1).concat(cvx.e(e2.length() / 2)).concat(e2);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(26);
        deviceCommand.setCommandID(8);
        deviceCommand.setDataContent(cvx.a(concat));
        deviceCommand.setDataLen(cvx.a(concat).length);
        LogUtil.a("HwWearableManager", "sendSyncAccountHmsError() : 26.8", concat);
        c.b(deviceCommand);
    }

    public static void e(String str) {
        LogUtil.a("HwWearableManager", "Enter sendSyncAccountHmsLogin ", str);
        String c2 = cvx.c(str);
        String concat = cvx.e(1).concat(cvx.e(c2.length() / 2)).concat(c2);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(26);
        deviceCommand.setCommandID(9);
        deviceCommand.setDataContent(cvx.a(concat));
        deviceCommand.setDataLen(cvx.a(concat).length);
        LogUtil.a("HwWearableManager", "sendSyncAccountHmsLogin() : 26.9", concat);
        c.b(deviceCommand);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(int i) {
        DeviceCapability d2 = cvs.d();
        if (d2 == null) {
            LogUtil.h("HwWearableManager", "sendHmsLow deviceCapability is null");
        } else if (d2.isSupportAccountSwitch()) {
            c(i);
        } else {
            LogUtil.h("HwWearableManager", "sendHmsLow is not support");
        }
    }
}
