package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.health.device.wifi.interfaces.BaseCallback;
import com.huawei.health.device.wifi.interfaces.BaseCallbackInterface;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.device.wifi.manager.DeviceRegisterManager;
import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.unite.RegisterInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetWifiDeviceInfoReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetWifiDeviceInfoRsp;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class cum {
    private static String b = null;
    private static boolean c = false;
    private static String d;
    private static BroadcastReceiver e = new BroadcastReceiver() { // from class: cum.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            char c2;
            cpw.c(false, "SoftApOuthManager", " sNetChangeReceiver enter");
            if (intent == null) {
                cpw.c(false, "SoftApOuthManager", " sNetChangeReceiver | intent == null，return！");
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                cpw.c(false, "SoftApOuthManager", " sNetChangeReceiver | action == null，return！");
                return;
            }
            action.hashCode();
            int hashCode = action.hashCode();
            if (hashCode == -1875733435) {
                if (action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                    c2 = 0;
                }
                c2 = 65535;
            } else if (hashCode != -1172645946) {
                if (hashCode == -343630553 && action.equals("android.net.wifi.STATE_CHANGE")) {
                    c2 = 2;
                }
                c2 = 65535;
            } else {
                if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    c2 = 1;
                }
                c2 = 65535;
            }
            if (c2 == 0) {
                cpw.c(false, "SoftApOuthManager", "sNetChangeReceiver WIFI_STATE_CHANGED_ACTION: wifi state:", Integer.valueOf(intent.getIntExtra("wifi_state", 0)));
                return;
            }
            if (c2 == 1) {
                NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                if (networkInfo != null) {
                    cum.MR_(networkInfo, context);
                    return;
                } else {
                    cpw.c(false, "SoftApOuthManager", "sNetChangeReceiver CONNECTIVITY_ACTION: NULL");
                    return;
                }
            }
            if (c2 != 2) {
                return;
            }
            try {
                Parcelable parcelableExtra = intent.getParcelableExtra("networkInfo");
                if (parcelableExtra != null) {
                    if (parcelableExtra instanceof NetworkInfo) {
                        cum.MR_((NetworkInfo) parcelableExtra, context);
                    }
                } else {
                    cpw.c(false, "SoftApOuthManager", "sNetChangeReceiver NETWORK_STATE_CHANGED_ACTION: NULL");
                }
            } catch (ClassCastException e2) {
                cpw.e(false, "SoftApOuthManager", "sNetChangeReceiver NETWORK_STATE_CHANGED_ACTION exception:", e2.getMessage());
            }
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private ctn f11483a;
    private BaseCallbackInterface f;
    private Context g;
    private String h;
    private CommBaseCallbackInterface i;
    private String j;
    private e m;
    private csl o;
    private ctw s;
    private BaseCallbackInterface v;
    private long x;
    private WifiManager y;
    private final Object q = new Object();
    private volatile int ad = -1;
    private volatile boolean r = false;
    private int n = 0;
    private boolean p = false;
    private int l = -1;
    private int k = 0;
    private int t = -1;
    private String u = "";
    private long w = 0;

    private cum(Context context) {
        this.g = context;
        o();
    }

    public static boolean c() {
        return c;
    }

    public static cum e(Context context) {
        if (context == null) {
            cpw.c(false, "SoftApOuthManager", " create Context is null");
            return null;
        }
        return new cum(context);
    }

    private void o() {
        this.ad = -1;
        this.y = (WifiManager) this.g.getSystemService("wifi");
        this.f = new c(this);
        this.v = new d(this);
        this.m = new e(this);
    }

    public void d(ctn ctnVar, String str, String str2, CommBaseCallbackInterface commBaseCallbackInterface) {
        if (ctnVar == null) {
            cpw.c(false, "SoftApOuthManager", " start addDeviceInfo is null");
            return;
        }
        if (str == null || commBaseCallbackInterface == null) {
            cpw.c(false, "SoftApOuthManager", " start authName or callback is null");
            return;
        }
        cpw.c(false, "SoftApOuthManager", " start() addDeviceInfo = ", ctnVar, " authSSID = ", str, " authPsd = ", cpw.d(str2));
        this.f11483a = ctnVar;
        b(ctnVar.f());
        d(this.f11483a.b());
        this.h = str;
        this.j = str2;
        this.i = commBaseCallbackInterface;
        this.u = null;
        this.w = 0L;
        a(false);
        e(0, 150000L);
    }

    private static void b(String str) {
        b = str;
    }

    private static void d(String str) {
        d = str;
    }

    private static void a(boolean z) {
        c = z;
    }

    private void e(int i, long j) {
        if (this.f11483a == null || TextUtils.isEmpty(this.h)) {
            cpw.e(false, "SoftApOuthManager", " startSoftApAuth params error");
            return;
        }
        if (TextUtils.isEmpty(b)) {
            cpw.e(false, "SoftApOuthManager", "startSoftApAuth sDeviceSsid is empty");
            this.m.removeMessages(10);
            Message obtain = Message.obtain();
            obtain.what = 10;
            obtain.arg1 = 1101;
            this.m.sendEmptyMessage(10);
            return;
        }
        l();
        this.m.removeMessages(10);
        this.m.sendEmptyMessageDelayed(10, j);
        this.x = System.currentTimeMillis();
        if (i == 0) {
            this.ad = -1;
            this.m.sendEmptyMessage(100);
        } else if (i == 1) {
            this.ad = 0;
            this.m.sendEmptyMessage(102);
        } else if (i == 2) {
            this.ad = 1;
            this.m.sendEmptyMessage(103);
        }
        cpw.c(false, "SoftApOuthManager", "startSoftApAuth begin staff authStep:", Integer.valueOf(i), " mWorkFlow:", Integer.valueOf(this.ad));
        ctw ctwVar = new ctw("modeDevAp");
        this.s = ctwVar;
        ctwVar.a();
    }

    private void l() {
        if (this.g == null) {
            cpw.e(false, "SoftApOuthManager", "regWifiStateBroadCast activity NULL");
            return;
        }
        if (this.r) {
            cpw.e(false, "SoftApOuthManager", "Already regWifiStateBroadCast");
            return;
        }
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.setPriority(1000);
        this.g.registerReceiver(e, intentFilter);
        this.r = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void MR_(NetworkInfo networkInfo, Context context) {
        if (networkInfo == null || context == null) {
            return;
        }
        if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
            if (context.getSystemService("wifi") instanceof WifiManager) {
                MT_((WifiManager) context.getSystemService("wifi"));
                return;
            }
            return;
        }
        cpw.c(false, "SoftApOuthManager", "checkWifiConnectDevice : disconnected");
    }

    static final class c extends BaseCallback<cum> {
        @Override // com.huawei.health.device.wifi.interfaces.BaseCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onStatus(cum cumVar, int i) {
        }

        c(cum cumVar) {
            super(cumVar);
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(cum cumVar, Object obj) {
            cpw.a(false, "SoftApOuthManager", " SoftApDeviceCallback onSuccess:");
            if (cumVar != null) {
                if (cumVar.m == null || cumVar.ad != 1) {
                    return;
                }
                cumVar.m.removeMessages(6);
                cumVar.m.removeMessages(1000);
                cumVar.m.removeMessages(4);
                cumVar.m.sendEmptyMessage(5);
                return;
            }
            cpw.a(false, "SoftApOuthManager", " SoftApDeviceCallback onSuccess obj is null");
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onFailure(cum cumVar, int i) {
            cpw.a(false, "SoftApOuthManager", " SoftApDeviceCallback onFailure:");
            if (cumVar != null) {
                if (cumVar.m == null || cumVar.ad != 1) {
                    return;
                }
                cumVar.m.removeMessages(1000);
                cumVar.m.removeMessages(6);
                cumVar.m.sendEmptyMessage(6);
                return;
            }
            cpw.a(false, "SoftApOuthManager", " SoftApDeviceCallback onFailure obj is null");
        }
    }

    static final class d extends BaseCallback<cum> {
        @Override // com.huawei.health.device.wifi.interfaces.BaseCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onStatus(cum cumVar, int i) {
        }

        d(cum cumVar) {
            super(cumVar);
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(cum cumVar, Object obj) {
            if (cumVar == null) {
                cpw.e(false, "SoftApOuthManager", "onGetVerifyCodeSuccess SoftApAuthManager is null");
                return;
            }
            Bundle bundle = obj instanceof Bundle ? (Bundle) obj : null;
            if (bundle == null) {
                cpw.e(false, "SoftApOuthManager", "onGetVerifyCodeSuccess bundle is null");
                return;
            }
            RegisterInfo registerInfo = bundle.getParcelable("device_register_verifycode") instanceof RegisterInfo ? (RegisterInfo) bundle.getParcelable("device_register_verifycode") : null;
            if (registerInfo != null) {
                if (cumVar.m == null || cumVar.ad != 0) {
                    cpw.e(false, "SoftApOuthManager", "onGetVerifyCodeSuccess mHandler is null");
                    return;
                }
                cpw.e(false, "SoftApOuthManager", "onGetVerifyCodeSuccess success");
                csw cswVar = new csw();
                cswVar.a(registerInfo.getDevId());
                cumVar.f11483a.e(registerInfo.getDevId());
                cswVar.b(registerInfo.getVerifyCode());
                cswVar.d(GRSManager.a(cumVar.g).getUrl("healthAPPToDeviceUrl"));
                cswVar.e(registerInfo.getPsk());
                cpw.c(false, "SoftApOuthManager", " softApRegisterInfo = ", cswVar);
                cumVar.u = new Gson().toJson(cswVar, csw.class);
                cumVar.w = System.currentTimeMillis();
                cumVar.m.removeMessages(0);
                cumVar.m.sendEmptyMessage(0);
                return;
            }
            cpw.e(false, "SoftApOuthManager", "onGetVerifyCodeSuccess entity is null");
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onFailure(cum cumVar, int i) {
            if (cumVar != null) {
                if (cumVar.m != null) {
                    cumVar.m.removeMessages(1);
                    cumVar.m.sendEmptyMessage(1);
                    return;
                } else {
                    cpw.e(false, "SoftApOuthManager", "onGetVerify onFailure mHandler is null");
                    return;
                }
            }
            cpw.e(false, "SoftApOuthManager", "onGetVerify onFailure SoftApAuthManager is null");
        }
    }

    static class e extends StaticHandler<cum> {
        e(cum cumVar) {
            super(cumVar);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        /* renamed from: MU_, reason: merged with bridge method [inline-methods] */
        public void handleMessage(cum cumVar, Message message) {
            if (cumVar != null && message != null) {
                if (cumVar.ad == 999) {
                    cpw.e(false, "SoftApOuthManager", "SoftApHandler this object has release all, ignore this msg");
                    return;
                }
                int i = message.what;
                if (i == 0 || i == 1 || i == 100) {
                    cumVar.b(message.what);
                    return;
                }
                if (i != 1000) {
                    if (i != 1002) {
                        switch (i) {
                            case 4:
                            case 5:
                            case 6:
                                break;
                            case 7:
                            case 8:
                            case 9:
                                break;
                            case 10:
                                cpw.c(false, "SoftApOuthManager", "SoftApHandler MSG_WHOLE_TIME_OUT");
                                removeMessages(1000);
                                removeMessages(1002);
                                cumVar.e();
                                break;
                            default:
                                switch (i) {
                                    case 102:
                                        break;
                                    case 103:
                                    case 104:
                                        break;
                                    default:
                                        cpw.e(false, "SoftApOuthManager", "SoftApHandler default msg:", Integer.valueOf(message.what));
                                        break;
                                }
                        }
                        return;
                    }
                    cumVar.MS_(message);
                    return;
                }
                cumVar.a(message.what);
                return;
            }
            cpw.e(false, "SoftApOuthManager", "SoftApHandler this object is null or msg is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void MS_(Message message) {
        cpw.c(false, "SoftApOuthManager", "handleMessageWaitDeviceOnline msg:", message, "mWorkFlow:", Integer.valueOf(this.ad));
        int i = message.what;
        if (i == 7) {
            cpw.c(false, "SoftApOuthManager", " connect wifi success");
            return;
        }
        if (i == 8) {
            cpw.c(false, "SoftApOuthManager", " connect wifi fail");
            return;
        }
        if (i == 9) {
            cpw.a(false, "SoftApOuthManager", "DEVICE REGISTER SUCCESS!");
            this.ad = 100;
            this.m.removeMessages(1000);
            this.m.removeMessages(1002);
            this.m.removeMessages(10);
            a(message.obj);
            return;
        }
        if (i == 103) {
            t();
            return;
        }
        if (i != 104) {
            if (i == 1002) {
                h();
                return;
            } else {
                cpw.d(false, "SoftApOuthManager", " handleMessageSwitchToRouter never go here");
                return;
            }
        }
        if (this.ad == 2 || this.ad == 3) {
            this.ad = 3;
            this.m.sendEmptyMessageDelayed(1002, 1000L);
            this.k = 0;
            return;
        }
        cpw.c(false, "SoftApOuthManager", " MSG_START_GET_ONLINE_DEVICE_INFO: ERR ", Integer.valueOf(this.ad));
    }

    private void t() {
        this.m.removeMessages(1000);
        if (this.ad == 1) {
            this.ad = 2;
            this.m.sendEmptyMessage(104);
            d(ExceptionCode.FILE_NO_EXIST, (Object) null);
            d(false);
        }
        if (this.ad == 2 || this.ad == 3) {
            this.m.removeMessages(1002);
            this.m.sendEmptyMessage(1002);
        } else {
            cpw.c(false, "SoftApOuthManager", " MSG_START_SWITCH_TO_ROUTER, ERR:", Integer.valueOf(this.ad));
        }
    }

    private void h() {
        if (this.ad == 3 || this.ad == 2) {
            cpw.c(false, "SoftApOuthManager", " MSG_GET_DEVICE_INFO_FROM get info:", Integer.valueOf(this.k));
            if (this.k > 5) {
                g();
                this.k = 0;
            }
            if (!k()) {
                cpw.c(false, "SoftApOuthManager", " MSG_GET_DEVICE_INFO_FROM reconnect preWiFiAP");
                d(true);
            }
            this.k++;
            this.m.sendEmptyMessageDelayed(1002, 1000L);
            return;
        }
        cpw.e(false, "SoftApOuthManager", " MSG_GET_DEVICE_INFO_FROM ERR", Integer.valueOf(this.ad));
    }

    private boolean d(boolean z) {
        LogUtil.c("SoftApOuthManager", "connectWiFi");
        if (this.t < 0) {
            this.t = cub.Mw_(this.y, this.h, this.j, b);
        }
        int i = this.t;
        if (i < 0) {
            LogUtil.c("SoftApOuthManager", "connectWiFi false: ", Integer.valueOf(i));
            return false;
        }
        if (z) {
            return cub.MD_(this.y, i);
        }
        return cub.Mt_(this.y, i);
    }

    private void g() {
        if (!TextUtils.isEmpty(this.f11483a.e())) {
            WifiDeviceGetWifiDeviceInfoReq wifiDeviceGetWifiDeviceInfoReq = new WifiDeviceGetWifiDeviceInfoReq();
            wifiDeviceGetWifiDeviceInfoReq.setDevId(this.f11483a.e());
            jbs.a(this.g).a(wifiDeviceGetWifiDeviceInfoReq, new ICloudOperationResult<WifiDeviceGetWifiDeviceInfoRsp>() { // from class: cum.2
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void operationResult(WifiDeviceGetWifiDeviceInfoRsp wifiDeviceGetWifiDeviceInfoRsp, String str, boolean z) {
                    cum.this.d(wifiDeviceGetWifiDeviceInfoRsp, z);
                }
            });
            return;
        }
        cpw.e(false, "SoftApOuthManager", "getAuthDeviceFromCloud mAddDeviceInfo DevId is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(WifiDeviceGetWifiDeviceInfoRsp wifiDeviceGetWifiDeviceInfoRsp, boolean z) {
        int i;
        String str;
        if (z) {
            if (wifiDeviceGetWifiDeviceInfoRsp == null || wifiDeviceGetWifiDeviceInfoRsp.getDeviceDetailInfo() == null) {
                cpw.a(false, "SoftApOuthManager", "getAuthDeviceFromCloud reg device success but rsp is null");
                return;
            }
            Message obtain = Message.obtain();
            ctk ctkVar = new ctk();
            ctkVar.a(wifiDeviceGetWifiDeviceInfoRsp.getDeviceDetailInfo());
            obtain.obj = ctkVar;
            obtain.what = 9;
            this.m.sendMessage(obtain);
            cpw.a(false, "SoftApOuthManager", "getAuthDeviceFromCloud reg device success :", wifiDeviceGetWifiDeviceInfoRsp.toString());
            return;
        }
        if (wifiDeviceGetWifiDeviceInfoRsp != null) {
            i = wifiDeviceGetWifiDeviceInfoRsp.getResultCode().intValue();
            str = wifiDeviceGetWifiDeviceInfoRsp.getResultDesc();
        } else {
            i = Constants.CODE_UNKNOWN_ERROR;
            str = "unknown error";
        }
        cpw.c(false, "SoftApOuthManager", "getAuthDeviceFromCloud() getSingleDevice errCode = ", Integer.valueOf(i), ",resultDesc:", str);
    }

    private boolean k() {
        String ssid = ((WifiManager) this.g.getSystemService("wifi")).getConnectionInfo().getSSID();
        if (!TextUtils.isEmpty(ssid)) {
            if (ssid.startsWith("\"") && ssid.endsWith("\"") && TextUtils.equals(ssid.substring(1, ssid.length() - 1), this.h)) {
                return true;
            }
        } else {
            cpw.c(false, "SoftApOuthManager", "isConnectRouter CONNECTIVITY_ACTION: ", ssid);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        cpw.c(false, "SoftApOuthManager", "handleMessageGetRegisterInfo msg:", Integer.valueOf(i), " mWorkFlow:", Integer.valueOf(this.ad));
        if (i == 0) {
            if (this.ad == 0) {
                this.m.sendEmptyMessage(102);
                return;
            } else {
                cpw.c(false, "SoftApOuthManager", " MSG_GET_REGISTER_INFO_SUCCESS ERR: ", Integer.valueOf(this.ad));
                return;
            }
        }
        if (i != 1) {
            if (i == 100) {
                this.ad = 0;
                this.n = 0;
                i();
                return;
            }
            cpw.c(false, "SoftApOuthManager", " handleMessageGetRegisterInfo never go here");
            return;
        }
        this.n++;
        cpw.c(false, "SoftApOuthManager", "handleMessageGetRegisterInfo: ", Integer.valueOf(this.ad), " count:", Integer.valueOf(this.n));
        if (this.n > 3) {
            b(1102, (Object) null);
        } else {
            i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        cpw.c(false, "SoftApOuthManager", "handleMessageSendMsgToDevice msg: ", Integer.valueOf(i), " mWorkFlow", Integer.valueOf(this.ad));
        if (i == 4) {
            f();
            return;
        }
        if (i == 5) {
            if (this.ad == 1) {
                cpw.c(false, "SoftApOuthManager", " MSG_SEND_MSG_TO_DEVICE_SUCCESS: success");
                b();
                this.m.sendEmptyMessage(103);
                return;
            }
            cpw.c(false, "SoftApOuthManager", " MSG_SEND_MSG_TO_DEVICE_SUCCESS: err", Integer.valueOf(this.ad));
            return;
        }
        if (i == 6) {
            if (this.ad == 1) {
                cpw.c(false, "SoftApOuthManager", " MSG_SEND_MSG_TO_DEVICE_FAILURE:");
                b();
                this.m.sendEmptyMessage(103);
                return;
            }
            cpw.c(false, "SoftApOuthManager", " MSG_SEND_MSG_TO_DEVICE_FAILURE: err ", Integer.valueOf(this.ad));
            return;
        }
        if (i == 102) {
            m();
            return;
        }
        if (i == 1000) {
            if (this.ad == 1) {
                n();
                this.m.sendEmptyMessageDelayed(1000, 1000L);
                if (this.p) {
                    return;
                }
                this.p = true;
                d();
                return;
            }
            cpw.e(false, "SoftApOuthManager", " MSG_CONNECT_DEVICE_TIMER ERR:", Integer.valueOf(this.ad));
            return;
        }
        cpw.c(false, "SoftApOuthManager", "handleMessageSendMsgToDevice never go here");
    }

    private void m() {
        if (this.ad == -1 || this.ad == 0) {
            this.ad = 1;
            d(ExceptionCode.DOWNLOAD_RENAME_ERROR, (Object) null);
            a(false);
            this.p = false;
            q();
            if (this.l < 0) {
                LogUtil.b("SoftApOuthManager", "handleMessageSendMsgToDevice device addNetWork == -1");
                this.m.removeMessages(4);
                this.m.sendEmptyMessageDelayed(4, 5000L);
            } else {
                this.m.removeMessages(4);
                this.m.sendEmptyMessageDelayed(4, 50000L);
                this.m.removeMessages(6);
                this.m.sendEmptyMessageDelayed(6, 55000L);
            }
        }
        if (this.ad == 1) {
            this.m.sendEmptyMessageDelayed(1000, 1000L);
        } else {
            cpw.a(false, "SoftApOuthManager", " MSG_START_SEND_MSG_TO_DEV nodeal state:", Integer.valueOf(this.ad));
        }
    }

    private void f() {
        if (this.ad == 1) {
            this.m.removeMessages(6);
            if (c) {
                cpw.c(false, "SoftApOuthManager", "isConnected so successed!");
                b();
                this.m.sendEmptyMessage(103);
                return;
            } else {
                cpw.c(false, "SoftApOuthManager", "isConnected false ,goto manual");
                b();
                b(1103, (Object) null);
                j();
                return;
            }
        }
        cpw.e(false, "SoftApOuthManager", " MSG_CONNECT_DEVICE_FAILURE ERR:", Integer.valueOf(this.ad));
    }

    public void e() {
        ctw ctwVar = this.s;
        if (ctwVar != null) {
            ctwVar.a("networkTimeout", String.valueOf(ExceptionCode.CHECK_FILE_HASH_FAILED));
        }
        a();
        b(ExceptionCode.CHECK_FILE_HASH_FAILED, (Object) null);
    }

    private void a(Object obj) {
        cpw.a(false, "SoftApOuthManager", " registerSuccess()");
        ctw ctwVar = this.s;
        if (ctwVar != null) {
            ctwVar.d("networkSuccess");
        }
        a();
        b(1100, obj);
    }

    private void j() {
        long currentTimeMillis = 150000 - (System.currentTimeMillis() - this.x);
        cpw.a(false, "SoftApOuthManager", " connectDeviceApFail() left time:", Long.valueOf(currentTimeMillis));
        if (currentTimeMillis <= PreConnectManager.CONNECT_INTERNAL) {
            this.m.removeMessages(10);
            this.m.sendEmptyMessage(10);
        } else {
            ctw ctwVar = this.s;
            if (ctwVar != null) {
                ctwVar.a("authFail", String.valueOf(1103));
            }
            a();
        }
    }

    public void a() {
        r();
        if (this.m != null) {
            this.ad = 999;
            this.m.removeCallbacksAndMessages(null);
        }
    }

    private void r() {
        Context context;
        if (this.r && (context = this.g) != null) {
            context.unregisterReceiver(e);
            this.r = false;
        } else {
            cpw.e(false, "SoftApOuthManager", "unRegisterWifiStateBroadCast mIsHasRegBroadcast is false");
        }
    }

    public void d() {
        cpw.a(false, "SoftApOuthManager", " startDeviceApConnection() mAuthName = ", this.h, " mAuthPazzword = ", cpw.d(this.j), " mAddDeviceInfo = ", this.f11483a, " mRegisterMessage = ", cpw.d(this.u));
        csu csuVar = new csu();
        csuVar.e(this.h);
        csuVar.b(this.j);
        csuVar.d(this.f11483a.c());
        csuVar.e(this.f11483a.a());
        csuVar.a(this.f11483a.f());
        b();
        csl cslVar = new csl(csuVar, this.u, this.f);
        this.o = cslVar;
        cslVar.a(this.g);
    }

    public void b() {
        cpw.a(false, "SoftApOuthManager", " stopDeviceApConnection() in");
        csl cslVar = this.o;
        if (cslVar != null) {
            cslVar.d();
            this.o = null;
        }
    }

    private void d(int i, Object obj) {
        synchronized (this.q) {
            CommBaseCallbackInterface commBaseCallbackInterface = this.i;
            if (commBaseCallbackInterface != null) {
                commBaseCallbackInterface.onResult(i, "work_flow", obj);
            } else {
                cpw.e(false, "SoftApOuthManager", "sendWorkFlowMsg callback is null");
            }
        }
    }

    private void b(int i, Object obj) {
        synchronized (this.q) {
            CommBaseCallbackInterface commBaseCallbackInterface = this.i;
            if (commBaseCallbackInterface != null) {
                commBaseCallbackInterface.onResult(i, "error_code", obj);
            } else {
                cpw.e(false, "SoftApOuthManager", "sendResultMsg callback is null");
            }
        }
    }

    private void i() {
        cpw.c(false, "SoftApOuthManager", " getDeviceRegisterInfo() enter");
        d(1111, (Object) null);
        DeviceRegisterManager.a(this.g).a(this.f11483a, DeviceRegisterManager.RegisterMode.REGISTER_SOFTAP, this.v);
    }

    private boolean q() {
        cpw.a(false, "SoftApOuthManager", " startConnectDeviceAp() enter");
        int My_ = cub.My_(this.y, b);
        this.l = My_;
        if (My_ < 0) {
            return cub.Mt_(this.y, My_);
        }
        if (Build.VERSION.SDK_INT < 27) {
            cpw.c(false, "SoftApOuthManager", "startConnectDeviceAp false: ", Integer.valueOf(this.l));
            return false;
        }
        return cub.Mt_(this.y, this.l);
    }

    private void n() {
        cpw.a(false, "SoftApOuthManager", "reconnectDeviceAp：", Integer.valueOf(this.l));
        if (this.l < 0) {
            this.l = cub.My_(this.y, b);
        }
        int i = this.l;
        if (i >= 0) {
            cub.MD_(this.y, i);
        } else {
            cpw.c(false, "SoftApOuthManager", "reconnectDeviceAp false: ", Integer.valueOf(i));
        }
    }

    public static boolean MT_(WifiManager wifiManager) {
        if (wifiManager == null) {
            cpw.c(false, "SoftApOuthManager", "isWifiConnectDevice wifi is null");
            return false;
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        String ssid = connectionInfo.getSSID();
        String b2 = ctv.b(connectionInfo.getBSSID());
        if (!TextUtils.isEmpty(ssid) && ssid.length() > 30) {
            if (ssid.startsWith("\"") && ssid.endsWith("\"") && TextUtils.equals(ssid.substring(1, ssid.length() - 1), b)) {
                c = true;
            }
        } else {
            cpw.c(false, "SoftApOuthManager", "isWifiConnectDevice : ", connectionInfo.getSSID());
        }
        if (!TextUtils.isEmpty(b2) && b2.equalsIgnoreCase(d)) {
            cpw.c(false, "SoftApOuthManager", "isWifiConnectDevice : MAC equal");
            c = true;
        } else {
            cpw.c(false, "SoftApOuthManager", "isWifiConnectDevice : Bssid:", cpw.d(b2), " devMac:", cpw.d(d));
        }
        return c;
    }
}
