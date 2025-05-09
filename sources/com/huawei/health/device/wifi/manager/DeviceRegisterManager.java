package com.huawei.health.device.wifi.manager;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.health.device.wifi.entity.model.Entity;
import com.huawei.health.device.wifi.interfaces.BaseCallbackInterface;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.WifiDataCloudFactory;
import com.huawei.hwcloudmodel.model.unite.RegisterInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetWifiDeviceInfoReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetWifiDeviceInfoRsp;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceRegisterVerifyCodeInfoRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.utils.Constants;
import defpackage.cpw;
import defpackage.csv;
import defpackage.ctc;
import defpackage.ctg;
import defpackage.cti;
import defpackage.ctj;
import defpackage.ctk;
import defpackage.ctn;
import defpackage.cto;
import defpackage.ctu;
import defpackage.ctv;
import defpackage.jbs;
import defpackage.lqi;
import defpackage.lql;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.util.Map;

/* loaded from: classes3.dex */
public class DeviceRegisterManager {

    /* renamed from: a, reason: collision with root package name */
    private static DeviceRegisterManager f2275a;
    private static final Object c = new Object();
    private BaseCallbackInterface b;
    private Context d;
    private String f;
    private ctn h;
    private int i;
    private Handler n;
    private int e = 0;
    private RegisterMode o = RegisterMode.REGISTER_NORFORM;
    private CountDownTimer k = new CountDownTimer(90000, 2000) { // from class: com.huawei.health.device.wifi.manager.DeviceRegisterManager.2
        @Override // android.os.CountDownTimer
        public void onTick(long j2) {
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            cpw.c(false, "DeviceRegisterManager", "mRegisterWaitTimer: register timeout");
            DeviceRegisterManager.this.g.sendEmptyMessage(9);
        }
    };
    private e g = new e(this);
    private RegisterDeviceCallback j = new d();

    public interface RegisterDeviceCallback {
        void onCreateSessionSuccess(cto ctoVar);

        void onFailure(int i, String... strArr);

        void onGetVerifyCodeSuccess(RegisterInfo registerInfo);

        void onRegisterDeviceSuccess(Object obj);

        void onWriteVerifyCodeSuccess(ctc ctcVar, String str, String str2);
    }

    public enum RegisterMode {
        REGISTER_SOFTAP,
        REGISTER_NORFORM,
        REGISTER_BLE
    }

    static /* synthetic */ int i(DeviceRegisterManager deviceRegisterManager) {
        int i = deviceRegisterManager.e;
        deviceRegisterManager.e = i + 1;
        return i;
    }

    private DeviceRegisterManager(Context context) {
        this.n = null;
        this.d = context;
        HandlerThread handlerThread = new HandlerThread("DeviceRegisterManager");
        handlerThread.start();
        this.n = new b(handlerThread.getLooper());
    }

    static class b extends Handler {
        b(Looper looper) {
            super(looper);
        }
    }

    public static DeviceRegisterManager a(Context context) {
        DeviceRegisterManager deviceRegisterManager;
        synchronized (c) {
            if (f2275a == null) {
                f2275a = new DeviceRegisterManager(context);
            }
            deviceRegisterManager = f2275a;
        }
        return deviceRegisterManager;
    }

    static class e extends StaticHandler<DeviceRegisterManager> {
        e(DeviceRegisterManager deviceRegisterManager) {
            super(deviceRegisterManager);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        /* renamed from: MM_, reason: merged with bridge method [inline-methods] */
        public void handleMessage(DeviceRegisterManager deviceRegisterManager, Message message) {
            if (deviceRegisterManager == null || message == null) {
                cpw.d(false, "DeviceRegisterManager", "RegisterHandler object or msg is null");
                return;
            }
            cpw.a(false, "DeviceRegisterManager", "RegisterHandler msg is ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                deviceRegisterManager.g.removeCallbacksAndMessages(null);
                if (deviceRegisterManager.k != null) {
                    deviceRegisterManager.k.cancel();
                }
                if (deviceRegisterManager.j != null) {
                    deviceRegisterManager.j.onRegisterDeviceSuccess(message.obj);
                    return;
                }
                return;
            }
            if (i == 9) {
                deviceRegisterManager.g.removeCallbacksAndMessages(null);
                if (deviceRegisterManager.k != null) {
                    deviceRegisterManager.k.cancel();
                }
                if (deviceRegisterManager.j != null) {
                    deviceRegisterManager.j.onFailure(3101, new String[0]);
                    return;
                }
                return;
            }
            if (i != 1002) {
                if (i == 1003) {
                    deviceRegisterManager.e(message.arg1);
                    return;
                } else {
                    cpw.c(false, "DeviceRegisterManager", "RegisterHandler unKnow msg");
                    return;
                }
            }
            if (deviceRegisterManager.e > 4) {
                deviceRegisterManager.c();
                deviceRegisterManager.e = 0;
            }
            DeviceRegisterManager.i(deviceRegisterManager);
            if (deviceRegisterManager.g != null) {
                deviceRegisterManager.g.sendEmptyMessageDelayed(1002, 1000L);
            }
        }
    }

    public void d() {
        cpw.a(false, "DeviceRegisterManager", "onDestroy");
        b();
        e eVar = this.g;
        if (eVar != null) {
            eVar.removeCallbacksAndMessages(null);
        }
        synchronized (c) {
            if (this.b != null) {
                this.b = null;
            }
        }
        CountDownTimer countDownTimer = this.k;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    class d implements RegisterDeviceCallback {
        private d() {
        }

        @Override // com.huawei.health.device.wifi.manager.DeviceRegisterManager.RegisterDeviceCallback
        public void onGetVerifyCodeSuccess(RegisterInfo registerInfo) {
            if (registerInfo == null) {
                return;
            }
            if (DeviceRegisterManager.this.o != RegisterMode.REGISTER_SOFTAP) {
                if (DeviceRegisterManager.this.o == RegisterMode.REGISTER_BLE) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("device_register_verifycode", registerInfo);
                    if (TextUtils.isEmpty(DeviceRegisterManager.this.h.e())) {
                        DeviceRegisterManager.this.h.e(registerInfo.getDevId());
                    }
                    synchronized (DeviceRegisterManager.c) {
                        if (DeviceRegisterManager.this.b != null) {
                            DeviceRegisterManager.this.b.onSuccess(bundle);
                        }
                    }
                    LogUtil.a("DeviceRegisterManager", "begin to query device info: ", registerInfo.getDevId());
                    DeviceRegisterManager.this.g.sendEmptyMessageDelayed(1002, 1000L);
                    DeviceRegisterManager.this.e = 4;
                    return;
                }
                String devId = registerInfo.getDevId();
                cpw.a(true, "DeviceRegisterManager", "register DeviceID = ", cpw.a(devId));
                if (TextUtils.isEmpty(DeviceRegisterManager.this.h.e())) {
                    DeviceRegisterManager.this.h.e(devId);
                }
                cpw.c(false, "DeviceRegisterManager", "deviceType ", DeviceRegisterManager.this.h.i());
                String psk = registerInfo.getPsk();
                String verifyCode = registerInfo.getVerifyCode();
                cto ctoVar = new cto();
                ctoVar.e(devId);
                ctoVar.a(psk);
                ctoVar.g(verifyCode);
                DeviceRegisterManager.this.c(ctoVar, 3, false);
                cpw.c(false, "DeviceRegisterManager", "begin Shake hands");
                return;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("device_register_verifycode", registerInfo);
            synchronized (DeviceRegisterManager.c) {
                if (DeviceRegisterManager.this.b != null) {
                    DeviceRegisterManager.this.b.onSuccess(bundle2);
                }
            }
            cpw.a(false, "DeviceRegisterManager", "get verifyCode success softAP");
        }

        @Override // com.huawei.health.device.wifi.manager.DeviceRegisterManager.RegisterDeviceCallback
        public void onCreateSessionSuccess(cto ctoVar) {
            cpw.a(false, "DeviceRegisterManager", "Shake hands success and start send verifyCode");
            if (ctoVar == null) {
                cpw.d(false, "DeviceRegisterManager", "VerifyCode is null");
                return;
            }
            String e = ctoVar.e();
            if (TextUtils.isEmpty(DeviceRegisterManager.this.h.e())) {
                DeviceRegisterManager.this.h.e(e);
            }
            cpw.c(true, "DeviceRegisterManager", "VerifyCode2CoapSessionInfo: ", ctoVar.toString());
            DeviceRegisterManager.this.b(ctoVar, 3);
        }

        @Override // com.huawei.health.device.wifi.manager.DeviceRegisterManager.RegisterDeviceCallback
        public void onWriteVerifyCodeSuccess(ctc ctcVar, String str, String str2) {
            cpw.a(false, "DeviceRegisterManager", "onWriteVerifyCodeSuccess send verifyCode success and wait register for device");
            DeviceRegisterManager.this.g.sendEmptyMessageDelayed(1002, 1000L);
            DeviceRegisterManager.this.e = 4;
        }

        @Override // com.huawei.health.device.wifi.manager.DeviceRegisterManager.RegisterDeviceCallback
        public void onRegisterDeviceSuccess(Object obj) {
            cpw.a(false, "DeviceRegisterManager", "onRegisterDeviceSuccess device register success");
            DeviceRegisterManager.this.c(obj);
        }

        @Override // com.huawei.health.device.wifi.manager.DeviceRegisterManager.RegisterDeviceCallback
        public void onFailure(int i, String... strArr) {
            cpw.a(false, "DeviceRegisterManager", "onFailure device register fail errCode: ", Integer.valueOf(i), " msg: ", strArr);
            DeviceRegisterManager.this.a(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Object obj) {
        synchronized (c) {
            BaseCallbackInterface baseCallbackInterface = this.b;
            if (baseCallbackInterface != null) {
                baseCallbackInterface.onSuccess(obj);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        synchronized (c) {
            BaseCallbackInterface baseCallbackInterface = this.b;
            if (baseCallbackInterface != null) {
                baseCallbackInterface.onFailure(i);
            }
        }
    }

    public void b() {
        this.e = 0;
    }

    public void a(ctn ctnVar, RegisterMode registerMode, BaseCallbackInterface baseCallbackInterface) {
        if (ctnVar == null || registerMode == null || baseCallbackInterface == null) {
            return;
        }
        this.o = registerMode;
        synchronized (c) {
            this.b = baseCallbackInterface;
        }
        this.h = ctnVar;
        if (registerMode == RegisterMode.REGISTER_NORFORM || registerMode == RegisterMode.REGISTER_BLE) {
            CountDownTimer countDownTimer = this.k;
            if (countDownTimer != null) {
                countDownTimer.cancel();
                this.k.start();
            }
            b();
        }
        if (!TextUtils.isEmpty(this.h.e())) {
            cpw.a(false, "DeviceRegisterManager", "registerDevice: deviceId: ", CommonUtil.l(this.h.e()));
            this.g.sendEmptyMessageDelayed(1002, 1000L);
            this.e = 4;
            return;
        }
        e(3);
    }

    class c implements ICloudOperationResult<WifiDeviceRegisterVerifyCodeInfoRsp> {
        private int e;

        c(int i) {
            this.e = i;
        }

        @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void operationResult(WifiDeviceRegisterVerifyCodeInfoRsp wifiDeviceRegisterVerifyCodeInfoRsp, String str, boolean z) {
            int i;
            String str2;
            if (z) {
                cpw.c(false, "DeviceRegisterManager", "registerDeviceGetVerifyCode: getVerifyCode succeed");
                if (wifiDeviceRegisterVerifyCodeInfoRsp != null) {
                    if (DeviceRegisterManager.this.j != null) {
                        DeviceRegisterManager.this.j.onGetVerifyCodeSuccess(wifiDeviceRegisterVerifyCodeInfoRsp.getRegisterInfo());
                        return;
                    }
                    return;
                }
                cpw.d(false, "DeviceRegisterManager", "registerDeviceGetVerifyCode: getVerifyCode succeed, but rsp is null");
                return;
            }
            cpw.d(false, "DeviceRegisterManager", "registerDeviceGetVerifyCode: getVerifyCode failed");
            if (wifiDeviceRegisterVerifyCodeInfoRsp != null) {
                i = wifiDeviceRegisterVerifyCodeInfoRsp.getResultCode().intValue();
                str2 = wifiDeviceRegisterVerifyCodeInfoRsp.getResultDesc();
                OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_WEIGHT_SCALE_NETWORK_85070012.value(), i);
            } else {
                i = Constants.CODE_UNKNOWN_ERROR;
                str2 = "unknown error";
            }
            cpw.c(false, "DeviceRegisterManager", "registerDeviceGetVerifyCode: getVerifyCode failed errCode: ", Integer.valueOf(i), " resultDesc: ", str2);
            int i2 = this.e - 1;
            if (i2 <= 0 || DeviceRegisterManager.this.j == null) {
                if (DeviceRegisterManager.this.j != null) {
                    cpw.e(false, "DeviceRegisterManager", "get verifyCode fail");
                    DeviceRegisterManager.this.j.onFailure(IEventListener.EVENT_ID_HISIGHT_STATE_SERVER_READY, new String[0]);
                    return;
                }
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = 1003;
            obtain.arg1 = i2;
            DeviceRegisterManager.this.g.sendMessageDelayed(obtain, 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        cpw.a(false, "DeviceRegisterManager", "registerDeviceGetVerifyCode max is ", Integer.valueOf(i));
        d(new c(i));
    }

    private void d(final ICloudOperationResult<WifiDeviceRegisterVerifyCodeInfoRsp> iCloudOperationResult) {
        LogUtil.a("DeviceRegisterManager", "Enter getVerifyCode");
        final WifiDataCloudFactory wifiDataCloudFactory = new WifiDataCloudFactory(BaseApplication.getContext());
        Map<String, Object> body = wifiDataCloudFactory.getBody(null);
        body.put("unique", true);
        final String b2 = lql.b(body);
        this.n.post(new Runnable() { // from class: com.huawei.health.device.wifi.manager.DeviceRegisterManager.1
            @Override // java.lang.Runnable
            public void run() {
                WifiDeviceRegisterVerifyCodeInfoRsp wifiDeviceRegisterVerifyCodeInfoRsp = (WifiDeviceRegisterVerifyCodeInfoRsp) lqi.d().d(wifiDataCloudFactory.getRequestUrl("/deviceAgent/deviceRegister"), wifiDataCloudFactory.getHeaders(), b2, WifiDeviceRegisterVerifyCodeInfoRsp.class);
                if (wifiDeviceRegisterVerifyCodeInfoRsp == null) {
                    LogUtil.h("DeviceRegisterManager", "getVerifyCode response is null");
                    iCloudOperationResult.operationResult(null, null, false);
                    return;
                }
                LogUtil.a("DeviceRegisterManager", "getVerifyCode ResultCode is ", wifiDeviceRegisterVerifyCodeInfoRsp.getResultCode());
                if (wifiDeviceRegisterVerifyCodeInfoRsp.getResultCode().intValue() == 0) {
                    iCloudOperationResult.operationResult(wifiDeviceRegisterVerifyCodeInfoRsp, null, true);
                } else {
                    iCloudOperationResult.operationResult(wifiDeviceRegisterVerifyCodeInfoRsp, null, false);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(cto ctoVar, int i) {
        cpw.a(false, "DeviceRegisterManager", "Write a verification code to the device to complete the registration ,registerDeviceWriteVerifyCode: retry " + i);
        String str = "";
        if ("coap".equals(this.h.i()) || "wifiap".equals(this.h.i())) {
            String d2 = this.h.d();
            if (d2 != null) {
                cpw.a(true, "DeviceRegisterManager", "registerDeviceWriteVerifyCode: uri is ", cpw.d(d2));
                if (!d2.startsWith("coap://") && !d2.startsWith("coaps://")) {
                    d2 = "coap://" + d2;
                }
                try {
                    str = new URI(d2).getHost();
                    cpw.a(true, "DeviceRegisterManager", "registerDeviceWriteVerifyCode: ip is ", cpw.d(str));
                } catch (URISyntaxException e2) {
                    cpw.a(false, "DeviceRegisterManager", "registerDeviceWriteVerifyCode: ", e2.getMessage());
                }
            }
        } else {
            cpw.e(false, "DeviceRegisterManager", "registerDeviceWriteVerifyCode Unknown device source type:" + this.h.i());
        }
        c(ctoVar, i, str);
    }

    private void c(cto ctoVar, int i, String str) {
        csv csvVar = new csv();
        ctj ctjVar = new ctj();
        cpw.c(false, "DeviceRegisterManager", "registerDeviceWriteVerifyCode coapApi: ", csvVar);
        ctjVar.e(ctoVar.f());
        ctjVar.a(ctoVar.e());
        ctjVar.d(ctoVar.a());
        ctjVar.c(GRSManager.a(this.d).getUrl("healthAPPToDeviceUrl"));
        cpw.c(false, "DeviceRegisterManager", "registerDeviceWriteVerifyCode to device url ", ctjVar.e());
        int intValue = ctoVar.c().intValue();
        String j2 = ctoVar.j();
        String h = ctoVar.h();
        String g = ctoVar.g();
        Long i2 = ctoVar.i();
        Integer b2 = ctoVar.b();
        int d2 = ctoVar.d();
        cpw.c(false, "DeviceRegisterManager", "registerDeviceWriteVerifyCode modeResp: ", Integer.valueOf(intValue));
        j jVar = new j(i, ctoVar);
        if (intValue == 2 || intValue == 3) {
            cpw.a(false, "DeviceRegisterManager", "rich device send verifyCode");
            csvVar.a(str, ctjVar, j2, h);
            csvVar.e(b2, jVar);
        } else {
            if (intValue == 1) {
                cpw.a(false, "DeviceRegisterManager", "thin device send verifyCode");
                csvVar.b(ctjVar, g, i2, d2);
                csvVar.d(str, j2, h, jVar);
                return;
            }
            cpw.d(false, "DeviceRegisterManager", "registerDeviceWriteVerifyCode error modeResp");
        }
    }

    class j implements Entity.EntityResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private cto f2277a;
        private int d;

        j(int i, cto ctoVar) {
            this.d = i;
            this.f2277a = ctoVar;
        }

        @Override // com.huawei.health.device.wifi.entity.model.Entity.EntityResponseCallback
        public void onResponse(ctc ctcVar) {
            cpw.a(false, "DeviceRegisterManager", "WriteVerifyCodeCallBack: onResponse");
            if (ctcVar != null) {
                if (ctcVar.b() != 0) {
                    cpw.d(false, "DeviceRegisterManager", "WriteVerifyCodeCallBack errorCode: ", Integer.valueOf(ctcVar.b()));
                } else {
                    cpw.a(false, "DeviceRegisterManager", "WriteVerifyCodeCallBack: success write");
                    if (DeviceRegisterManager.this.j != null) {
                        DeviceRegisterManager.this.j.onWriteVerifyCodeSuccess(ctcVar, this.f2277a.j(), this.f2277a.h());
                        return;
                    }
                    return;
                }
            } else {
                cpw.d(false, "DeviceRegisterManager", "WriteVerifyCodeCallBack: response is null");
            }
            int i = this.d - 1;
            if (i <= 0 || DeviceRegisterManager.this.j == null) {
                if (DeviceRegisterManager.this.j != null) {
                    cpw.d(false, "DeviceRegisterManager", "WriteVerifyCodeCallBack Write verify code fail");
                    DeviceRegisterManager.this.j.onWriteVerifyCodeSuccess(ctcVar, this.f2277a.j(), this.f2277a.h());
                    return;
                }
                return;
            }
            DeviceRegisterManager.this.b(this.f2277a, i);
        }
    }

    class a implements Entity.EntityResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private int f2276a;
        private cto e;

        a(cto ctoVar, int i) {
            this.e = ctoVar;
            this.f2276a = i;
        }

        @Override // com.huawei.health.device.wifi.entity.model.Entity.EntityResponseCallback
        public void onResponse(ctc ctcVar) {
            if (ctcVar != null && (ctcVar instanceof ctg)) {
                ctg ctgVar = (ctg) ctcVar;
                cpw.c(true, "DeviceRegisterManager", "createSessionInterface: response not null, errCode is ", ctgVar.d());
                Integer d = ctgVar.d();
                if (d == null || d.intValue() != 0) {
                    if (d != null) {
                        cpw.d(false, "DeviceRegisterManager", "createSessionInterface: fail errcode: ", d);
                        return;
                    }
                    return;
                }
                cpw.c(false, "DeviceRegisterManager", "createSessionInterface: success");
                String i = ctgVar.i();
                String h = ctgVar.h();
                Long a2 = ctgVar.a();
                int intValue = ctgVar.e().intValue();
                cpw.c(true, "DeviceRegisterManager", "registerDeviceCreateSession: snDeviceRandom is ", i, "; getSessId: ", h, "; ", "seq: ", a2, ",modeRseq: ", Integer.valueOf(intValue));
                this.e.c(i);
                this.e.b(h);
                this.e.c(a2);
                this.e.a(Integer.valueOf(intValue));
                this.e.c(ctgVar.c());
                if (DeviceRegisterManager.this.j != null) {
                    DeviceRegisterManager.this.j.onCreateSessionSuccess(this.e);
                    return;
                }
                return;
            }
            int i2 = this.f2276a - 1;
            if (i2 > 0) {
                DeviceRegisterManager.this.c(this.e, i2, true);
            } else if (DeviceRegisterManager.this.j != null) {
                cpw.d(false, "DeviceRegisterManager", "Failed to create session");
                DeviceRegisterManager.this.j.onFailure(IEventListener.EVENT_ID_HISIGHT_STATE_PAUSED, new String[0]);
            }
            cpw.d(false, "DeviceRegisterManager", "registerDeviceCreateSession: create session fail");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(cto ctoVar, int i, boolean z) {
        cpw.a(false, "DeviceRegisterManager", "registerDeviceCreateSession: max ", Integer.valueOf(i), " isRetry ", Boolean.valueOf(z));
        if (!z) {
            String a2 = ctv.a(ctu.b());
            int nextInt = new SecureRandom().nextInt(32767);
            this.f = a2;
            this.i = nextInt;
            ctoVar.d(a2);
            ctoVar.c(nextInt);
        } else {
            ctoVar.d(this.f);
            ctoVar.c(this.i);
        }
        cpw.a(true, "DeviceRegisterManager", "registerDeviceCreateSession: mReSnAppRandom is ", cpw.d(this.f));
        String b2 = b(this.h);
        if (b2 == null) {
            this.j.onFailure(IEventListener.EVENT_ID_HISIGHT_STATE_PLAYING, new String[0]);
            return;
        }
        cpw.a(true, "DeviceRegisterManager", "registerDeviceCreateSession: ip is ", cpw.d(b2));
        cti ctiVar = new cti();
        ctiVar.a(1);
        ctiVar.d(3);
        ctiVar.c(this.f);
        ctiVar.c(Integer.valueOf(this.i));
        new csv().e(b2, ctiVar, new a(ctoVar, i));
    }

    private static String b(ctn ctnVar) {
        String d2;
        String str = "";
        if ((!"coap".equals(ctnVar.i()) && !"wifiap".equals(ctnVar.i())) || (d2 = ctnVar.d()) == null) {
            return "";
        }
        cpw.a(false, "DeviceRegisterManager", "getDeviceIp: uri is ", cpw.d(d2));
        if (!d2.startsWith("coap://") && !d2.startsWith("coaps://")) {
            d2 = "coap://" + d2;
        }
        try {
            str = new URI(d2).getHost();
            cpw.a(true, "DeviceRegisterManager", "getDeviceIp: ip is ", cpw.d(str));
            return str;
        } catch (URISyntaxException e2) {
            cpw.e(false, "DeviceRegisterManager", "getDeviceIp: ", e2.getMessage());
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        cpw.a(false, "DeviceRegisterManager", " getAuthDeviceFromCloud() DevId errCode: ", cpw.a(this.h.e()));
        if (!TextUtils.isEmpty(this.h.e())) {
            WifiDeviceGetWifiDeviceInfoReq wifiDeviceGetWifiDeviceInfoReq = new WifiDeviceGetWifiDeviceInfoReq();
            wifiDeviceGetWifiDeviceInfoReq.setDevId(this.h.e());
            jbs.a(this.d).a(wifiDeviceGetWifiDeviceInfoReq, new ICloudOperationResult<WifiDeviceGetWifiDeviceInfoRsp>() { // from class: com.huawei.health.device.wifi.manager.DeviceRegisterManager.3
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void operationResult(WifiDeviceGetWifiDeviceInfoRsp wifiDeviceGetWifiDeviceInfoRsp, String str, boolean z) {
                    DeviceRegisterManager.this.e(wifiDeviceGetWifiDeviceInfoRsp, z);
                }
            });
            return;
        }
        cpw.d(false, "DeviceRegisterManager", "getAuthDeviceFromCloud mAddDeviceInfo device id is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(WifiDeviceGetWifiDeviceInfoRsp wifiDeviceGetWifiDeviceInfoRsp, boolean z) {
        int i;
        String str;
        if (!z) {
            if (wifiDeviceGetWifiDeviceInfoRsp != null) {
                i = wifiDeviceGetWifiDeviceInfoRsp.getResultCode().intValue();
                str = wifiDeviceGetWifiDeviceInfoRsp.getResultDesc();
                OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_WEIGHT_SCALE_NETWORK_85070012.value(), i);
            } else {
                i = Constants.CODE_UNKNOWN_ERROR;
                str = "unknown error";
            }
            cpw.c(false, "DeviceRegisterManager", "getAuthDeviceFromCloud() getSingleDevice errCode: ", Integer.valueOf(i), " resultDesc: ", str);
            return;
        }
        if (wifiDeviceGetWifiDeviceInfoRsp == null) {
            cpw.a(false, "DeviceRegisterManager", "getAuthDeviceFromCloud reg device success but rsp is null");
            return;
        }
        if (wifiDeviceGetWifiDeviceInfoRsp.getDeviceDetailInfo() == null) {
            cpw.d(false, "DeviceRegisterManager", "getAuthDeviceFromCloud reg device success but rsp.getDeviceDetailInfo is null");
            return;
        }
        if (this.g != null) {
            ctk ctkVar = new ctk();
            Message obtain = Message.obtain();
            ctkVar.a(wifiDeviceGetWifiDeviceInfoRsp.getDeviceDetailInfo());
            obtain.obj = ctkVar;
            obtain.what = 1;
            this.g.sendMessage(obtain);
        }
    }
}
