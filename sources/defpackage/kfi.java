package defpackage;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.TextUtils;
import com.alipay.sdk.m.p.e;
import com.google.android.gms.common.api.ApiException;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.baseapi.hiaiengine.HiAiKitCreateListener;
import com.huawei.health.baseapi.hiaiengine.HiAiKitInitTtsListener;
import com.huawei.health.baseapi.hiaiengine.HiAiKitRecognizeListener;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hiai.awareness.client.AwarenessRequest;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.mlsdk.asr.MLAsrRecognizer;
import com.huawei.hms.mlsdk.common.AgConnectInfo;
import com.huawei.hms.mlsdk.common.MLApplication;
import com.huawei.hms.network.NetworkKit;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.hwid.HuaweiIdAuthManager;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParamsHelper;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import com.huawei.hms.support.hwid.service.HuaweiIdAuthService;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.mgr.hwmlkitmgr.SpeechRecognizerManager;
import com.huawei.hwdevice.phoneprocess.mgr.hwvoicekitmgr.HwControlLocalAppHelper;
import com.huawei.hwdevice.phoneprocess.mgr.hwvoicekitmgr.ScoUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.watchface.videoedit.gles.Constant;
import defpackage.kfj;
import health.compact.a.AuthorizationUtils;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.GRSManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPerferenceUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kfi extends HwBaseManager implements ParserInterface {
    private static String b = "com.android.phone/com.android.services.telephony.TelephonyConnectionService";
    private static volatile kfi j;
    private boolean aa;
    private boolean ab;
    private boolean ac;
    private boolean ad;
    private boolean ae;
    private boolean af;
    private boolean ag;
    private boolean ah;
    private boolean ai;
    private String aj;
    private HiAiKitInitTtsListener ak;
    private HiAiKitRecognizeListener al;
    private String am;
    private List<String> an;
    private int ao;
    private JSONArray ap;
    private ScoUtils aq;
    private BroadcastReceiver ar;
    private ScoUtils.OnScoConnectListener as;
    private HuaweiIdAuthService at;
    private JSONArray au;
    private SpeechRecognizerManager av;
    private String aw;
    private String ax;
    private String bb;
    private String bc;
    private List<String> f;
    private String g;
    private boolean h;
    private BroadcastReceiver i;
    private CountDownLatch k;
    private String l;
    private HwControlLocalAppHelper m;
    private kfj.d n;
    private Context o;
    private int p;
    private String q;
    private HiAiKitCreateListener r;
    private String s;
    private int t;
    private bzo u;
    private int v;
    private AppBundleLauncher.InstallCallback w;
    private long x;
    private ExtendHandler y;
    private boolean z;
    private static final String[] d = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14342a = new Object();
    private static final Object c = new Object();
    private static final Object e = new Object();

    static /* synthetic */ int s(kfi kfiVar) {
        int i = kfiVar.v;
        kfiVar.v = i + 1;
        return i;
    }

    private void f() {
        LogUtil.a("MLASR_Manager", "getLanguageList start :");
        MLAsrRecognizer.createAsrRecognizer(this.o).getLanguages(new MLAsrRecognizer.LanguageCallback() { // from class: kfi.9
            @Override // com.huawei.hms.mlsdk.asr.MLAsrRecognizer.LanguageCallback
            public void onResult(List<String> list) {
                if (list != null && list.size() > 0) {
                    kfi.this.g = String.join(",", list);
                    LogUtil.a("MLASR_Manager", "sendLanguageList :" + kfi.this.g);
                    kfk.d(kfi.this.g);
                    return;
                }
                LogUtil.a("MLASR_Manager", "getLanguageList is null");
            }

            @Override // com.huawei.hms.mlsdk.asr.MLAsrRecognizer.LanguageCallback
            public void onError(int i, String str) {
                LogUtil.b("HwVoiceKitManager", "getLanguageList errorCode:" + i + "errorMsg:" + str);
                sqo.x("getLanguageList failed");
            }
        });
    }

    private kfi(Context context) {
        super(context);
        this.aw = "";
        this.y = null;
        this.v = 0;
        this.x = -1L;
        this.ad = false;
        this.ah = false;
        this.aa = false;
        this.t = 0;
        this.ai = false;
        this.ac = false;
        this.ag = false;
        this.ab = false;
        this.f = new ArrayList(16);
        this.an = new ArrayList(16);
        this.h = false;
        this.n = null;
        this.ax = "";
        this.ao = 0;
        this.af = true;
        this.bb = "";
        this.l = "";
        this.aj = "";
        this.am = "";
        this.bc = "";
        this.g = "";
        this.ar = new BroadcastReceiver() { // from class: kfi.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                int resultCode = getResultCode();
                LogUtil.a("HwVoiceKitManager", "doMusicSearch onReceive getResultCode: ", Integer.valueOf(resultCode));
                if (resultCode == 0) {
                    LogUtil.a("HwVoiceKitManager", "start play");
                    kfl.c(kfi.this.o, kfi.this.am);
                    kfi.this.am = "";
                }
                if (resultCode == -1) {
                    LogUtil.a("HwVoiceKitManager", "start search");
                }
            }
        };
        this.i = new BroadcastReceiver() { // from class: kfi.8
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, final Intent intent) {
                if (CommonUtil.ce()) {
                    if (context2 == null || intent == null) {
                        LogUtil.h("HwVoiceKitManager", "mConnectStateChangedReceiver() context or intent is null ");
                        return;
                    }
                    LogUtil.a("HwVoiceKitManager", "mConnectStateChangedReceiver() context:", context2, ", intent:", intent.getAction());
                    if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                        ThreadPoolManager.d().execute(new Runnable() { // from class: kfi.8.4
                            @Override // java.lang.Runnable
                            public void run() {
                                Bundle extras = intent.getExtras();
                                if (extras == null) {
                                    LogUtil.h("HwVoiceKitManager", "mConnectStateChangedReceiver() bundle is null ");
                                    return;
                                }
                                try {
                                    Parcelable parcelable = extras.getParcelable("deviceinfo");
                                    if (parcelable instanceof DeviceInfo) {
                                        kfi.this.b((DeviceInfo) parcelable);
                                    }
                                } catch (BadParcelableException unused) {
                                    LogUtil.b("HwVoiceKitManager", "mConnectStateChangedReceiver onReceive() BadParcelableException");
                                }
                            }
                        });
                    }
                }
            }
        };
        this.as = new ScoUtils.OnScoConnectListener() { // from class: kfi.10
            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwvoicekitmgr.ScoUtils.OnScoConnectListener
            public void onConnectSuccess() {
                LogUtil.a("HwVoiceKitManager", "mScoConnectListener onConnectSuccess, mTtsContentText:", kfi.this.ax);
                if (kfi.this.ac) {
                    kfi.this.y.sendEmptyMessage(8);
                } else {
                    kfi.this.y.sendEmptyMessage(4, 1200L);
                }
            }

            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwvoicekitmgr.ScoUtils.OnScoConnectListener
            public void onConnectClose() {
                LogUtil.a("HwVoiceKitManager", "mScoConnectListener onConnectClose");
                if (kfi.this.u.isSpeaking()) {
                    kfi.this.u.stopSpeak();
                }
                kfi.this.u.cancelSpeak();
            }

            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwvoicekitmgr.ScoUtils.OnScoConnectListener
            public void connectTimeout() {
                LogUtil.a("HwVoiceKitManager", "mScoConnectListener connectTimeout");
                if (kfi.this.ai) {
                    kfi.this.y.sendEmptyMessage(5);
                }
            }
        };
        this.al = new HiAiKitRecognizeListener() { // from class: kfi.15
            @Override // com.huawei.health.baseapi.hiaiengine.HiAiKitRecognizeListener
            public void onInit() {
                kfi.this.m();
            }

            @Override // com.huawei.health.baseapi.hiaiengine.HiAiKitRecognizeListener
            public void onError(int i, String str) {
                ReleaseLogUtil.e("R_HwVoiceKitManager", "mKitRecognizeListener onError");
                kfi.this.c(i, str);
            }

            @Override // com.huawei.health.baseapi.hiaiengine.HiAiKitRecognizeListener
            public void onResult(String str) {
                ReleaseLogUtil.e("R_HwVoiceKitManager", "mKitRecognizeListener onResult");
                kfi.this.z();
                kfi.this.b(str);
            }

            @Override // com.huawei.health.baseapi.hiaiengine.HiAiKitRecognizeListener
            public void onPartialResult(String str) {
                LogUtil.a("HwVoiceKitManager", "mKitRecognizeListener onPartialResult");
                kfl.e(str);
            }

            @Override // com.huawei.health.baseapi.hiaiengine.HiAiKitRecognizeListener
            public void onSpeechEnd() {
                ReleaseLogUtil.e("R_HwVoiceKitManager", "mKitRecognizeListener onSpeechEnd");
                kfi.this.z();
            }

            @Override // com.huawei.health.baseapi.hiaiengine.HiAiKitRecognizeListener
            public void startRecord() {
                ReleaseLogUtil.e("R_HwVoiceKitManager", "mKitRecognizeListener startRecord");
                kfi.this.ag = true;
                kfi.this.x();
            }
        };
        this.r = new HiAiKitCreateListener() { // from class: kfi.11
            @Override // com.huawei.health.baseapi.hiaiengine.HiAiKitCreateListener
            public void onClientInit(int i) {
                if (i != 255) {
                    if (!jbx.a(kfi.this.o)) {
                        ReleaseLogUtil.d("R_HwVoiceKitManager", "mCreateListener onInit but network is off");
                        kfk.e(2);
                        return;
                    } else {
                        ReleaseLogUtil.e("R_HwVoiceKitManager", "mCreateListener onInit success");
                        kfi.this.o();
                        kfk.e(1);
                        return;
                    }
                }
                ReleaseLogUtil.d("R_HwVoiceKitManager", "mCreateListener onInit voiceKitSdk is null");
                kfk.e(i);
            }

            @Override // com.huawei.health.baseapi.hiaiengine.HiAiKitCreateListener
            public void onClientError(int i, String str) {
                ReleaseLogUtil.d("R_HwVoiceKitManager", "mCreateListener onError errorCode:", Integer.valueOf(i), ", errorMessage:", str);
                sqo.al("HiAiKitCreateListener onClientError");
                kfk.e(255);
            }
        };
        this.ak = new HiAiKitInitTtsListener() { // from class: kfi.12
            @Override // com.huawei.health.baseapi.hiaiengine.HiAiKitInitTtsListener
            public void onInit() {
                kfi.this.aa = true;
                ReleaseLogUtil.e("R_HwVoiceKitManager", "mKitInitTtsListener onInit:", Boolean.valueOf(kfi.this.aa));
            }

            @Override // com.huawei.health.baseapi.hiaiengine.HiAiKitInitTtsListener
            public void onTtsError(int i, String str) {
                kfi.this.aa = false;
                ReleaseLogUtil.d("R_HwVoiceKitManager", "mKitInitTtsListener onTtsError errorCode:", Integer.valueOf(i), ", errorMsg:", str);
                sqo.al("mKitInitTtsListener onTtsError");
                if (kfi.this.ag) {
                    return;
                }
                kfi.this.w();
            }

            @Override // com.huawei.health.baseapi.hiaiengine.HiAiKitInitTtsListener
            public void onTtsComplete(String str) {
                ReleaseLogUtil.e("R_HwVoiceKitManager", "mKitInitTtsListener onTtsComplete");
                kfi.this.w();
            }

            @Override // com.huawei.health.baseapi.hiaiengine.HiAiKitInitTtsListener
            public void onTtsStart(String str) {
                ReleaseLogUtil.e("R_HwVoiceKitManager", "mKitInitTtsListener onTtsStart");
                if (kfi.this.u == null || !kfi.this.ac) {
                    return;
                }
                kfi.this.u.stopSpeak();
                kfi.this.u.cancelSpeak();
            }
        };
        this.w = new AppBundleLauncher.InstallCallback() { // from class: kfi.13
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public boolean call(Context context2, Intent intent) {
                LogUtil.a("HwVoiceKitManager", "plugin install success");
                kfi.this.g();
                return false;
            }
        };
        this.o = context;
        this.y = HandlerCenter.yt_(new c(), "HwVoiceKitManager");
        this.m = new HwControlLocalAppHelper(this.o);
        ScoUtils e2 = ScoUtils.e(this.o);
        this.aq = e2;
        e2.b(this.as);
        k();
        j();
        i();
        BroadcastManagerUtil.bFC_(this.o, this.i, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    private void i() {
        try {
            AGConnectServicesConfig fromContext = AGConnectServicesConfig.fromContext(BaseApplication.getContext());
            LogUtil.a("MLASR_Manager", "initMlKitEngine start init apiKey.");
            MLApplication.initialize(BaseApplication.getContext());
            MLApplication.getInstance().setApiKey(fromContext.getString(AgConnectInfo.AgConnectKey.API_KEY));
        } catch (Exception e2) {
            LogUtil.h("MLASR_Manager", "initMlKitEngine failed ; Exception :" + ExceptionUtils.d(e2));
            sqo.x("initMlKitEngine failed");
        }
    }

    private void h() {
        if (!ScreenUtil.a()) {
            PowerKitManager.e().a("HwVoiceKitManager", 512, "user-VoiceKit");
        }
        if (!jbx.a(this.o)) {
            ReleaseLogUtil.d("R_HwVoiceKitManager", "init mlkit but network is off");
            kfk.e(2);
        } else {
            NetworkKit.init(BaseApplication.getContext(), new NetworkKit.Callback() { // from class: kfi.14
                @Override // com.huawei.hms.network.NetworkKit.Callback
                public void onResult(boolean z) {
                    LogUtil.a("MLASR_Manager", "network result: ", Boolean.valueOf(z));
                }
            });
            this.av = new SpeechRecognizerManager(new SpeechRecognizerManager.onResultsReady() { // from class: kfi.3
                @Override // com.huawei.hwdevice.phoneprocess.mgr.hwmlkitmgr.SpeechRecognizerManager.onResultsReady
                public void onStartListening() {
                    kfi.this.ab = true;
                    LogUtil.a("MLASR_Manager", "init mlkit service success");
                    kfk.e(1);
                    kfi.this.y();
                }

                @Override // com.huawei.hwdevice.phoneprocess.mgr.hwmlkitmgr.SpeechRecognizerManager.onResultsReady
                public void onResults(ArrayList<String> arrayList) {
                    if (arrayList != null && arrayList.size() > 0) {
                        LogUtil.a("MLASR_Manager", "SpeechRecognizerManager onResults: ", arrayList.get(0));
                        kfk.a(arrayList.get(0), -1);
                    } else {
                        LogUtil.h("MLASR_Manager", "SpeechRecognizerManager onResults empty");
                    }
                }

                @Override // com.huawei.hwdevice.phoneprocess.mgr.hwmlkitmgr.SpeechRecognizerManager.onResultsReady
                public void onError(int i) {
                    sqo.x("mSpeechManager onError");
                    kfi.this.ab = false;
                    if (i == 11202) {
                        kfk.e(2);
                    } else {
                        kfk.e(255);
                    }
                    if (kfi.this.av != null) {
                        kfi.this.av.d();
                    }
                    PowerKitManager.e().e("HwVoiceKitManager", 512, "user-VoiceKit");
                }
            }, this.aj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        synchronized (e) {
            List<String> list = this.an;
            if (list != null) {
                list.clear();
            }
        }
        ThreadPoolManager.d().d("MlKitManagerWriteAudio", new Runnable() { // from class: kfi.4
            @Override // java.lang.Runnable
            public void run() {
                while (kfi.this.ab) {
                    kfi.this.ac();
                }
            }
        });
    }

    private void j() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: kfi.5
            @Override // java.lang.Runnable
            public void run() {
                kfi.this.u = bzo.c();
                if (CommonUtil.ar() || CommonUtil.bf()) {
                    kfi.this.u.initHiAiEngine();
                }
            }
        });
    }

    private void k() {
        String e2 = jah.c().e("domain_smarthome_scope");
        LogUtil.a("HwVoiceKitManager", "scopeUrl: ", e2);
        if (TextUtils.isEmpty(e2)) {
            LogUtil.h("HwVoiceKitManager", "initSignClient scopeUrl isEmpty");
            return;
        }
        HuaweiIdAuthParamsHelper huaweiIdAuthParamsHelper = new HuaweiIdAuthParamsHelper(HuaweiIdAuthParams.DEFAULT_AUTH_REQUEST_PARAM);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(new Scope(e2 + "/auth/smarthome/skill"));
        arrayList.add(new Scope(e2 + "/auth/smarthome/devices"));
        this.at = HuaweiIdAuthManager.getService(this.o, huaweiIdAuthParamsHelper.setAuthorizationCode().setShippingAddress().setScopeList(arrayList).setAccessToken().setUid().createParams());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        LogUtil.a("HwVoiceKitManager", "mKitRecognizeListener onInit");
        this.x = System.currentTimeMillis();
        this.m.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, String str) {
        LogUtil.b("HwVoiceKitManager", "processOnError errorCode:", Integer.valueOf(i), " errorMessage:", str);
        kfk.e(i == 1 ? 2 : 255);
    }

    private void u() {
        kfk.a("", 1);
        if (this.aa) {
            this.aq.e();
            this.ax = "";
            this.bc = "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        String aa = SharedPerferenceUtils.aa(this.o);
        LogUtil.a("HwVoiceKitManager", "voiceUuid: ", aa);
        if (this.y != null) {
            kts.b(16);
            this.ah = true;
            this.y.sendEmptyMessage(12, 5000L);
        }
        this.u.updateSwitch(AuthorizationUtils.a(this.o));
        this.u.initKitRecognizeEngine(aa, this.l, this.al);
        if (this.z) {
            this.u.initKitTtsEngine(this.ak);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        this.aw = String.format(Locale.ENGLISH, "{\"contexts\":[{\"header\":{\"name\":\"HuaweiAT\",\"namespace\":\"System\"},\"payload\":{\"uid\":\"%s\",\"huaweiAT\":\"%s\"}}]}", str, str2);
        ReleaseLogUtil.e("R_HwVoiceKitManager", "updateVoiceContext");
    }

    public static kfi d() {
        kfi kfiVar;
        synchronized (f14342a) {
            if (j == null) {
                j = new kfi(BaseApplication.getContext());
            }
            kfiVar = j;
        }
        return kfiVar;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 49;
    }

    private void r() {
        this.ai = false;
        this.ag = false;
        this.ac = false;
        this.bb = "";
        this.au = null;
        synchronized (c) {
            this.f.clear();
        }
    }

    private void v() {
        LogUtil.a("HwVoiceKitManager", "startVoiceKit");
        t();
        r();
        if (this.u != null) {
            this.u.startRecognize(kfl.e(this.o, this.aj), this.aw, this.ad ? "opus" : "pcm");
            if (this.z) {
                kfk.a(1);
                return;
            }
            return;
        }
        LogUtil.h("HwVoiceKitManager", "startVoiceKit but voice client is null");
    }

    private void t() {
        this.y.removeMessages(1);
        Message obtain = Message.obtain();
        obtain.what = 1;
        this.y.sendMessage(obtain, 60000L);
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr == null || bArr.length <= 2) {
            LogUtil.h("HwVoiceKitManager", "onResponse error data.");
            return;
        }
        String d2 = cvx.d(bArr);
        if (d2 == null || d2.length() <= 4) {
            LogUtil.h("HwVoiceKitManager", "stringInfo length too short");
            return;
        }
        try {
            String substring = d2.substring(4);
            List<cwd> e2 = new cwl().a(substring).e();
            byte b2 = bArr[1];
            if (b2 != 2) {
                ReleaseLogUtil.e("R_HwVoiceKitManager", "getResult() the commandId is: ", Integer.valueOf(b2));
                LogUtil.a("HwVoiceKitManager", "getResult() message : ", substring);
            } else {
                LogUtil.a("HwVoiceKitManager", "getResult() the commandId is: ", Integer.valueOf(b2));
            }
            if (koq.b(e2)) {
                LogUtil.h("HwVoiceKitManager", Integer.valueOf(b2), ": tlvList is null");
                return;
            }
            if (b2 == 1) {
                e(e2);
                return;
            }
            if (b2 == 2) {
                d(e2);
                return;
            }
            if (b2 == 7) {
                a(e2);
            } else if (b2 != 8) {
                LogUtil.h("HwVoiceKitManager", "Invalid command: ", Integer.valueOf(b2));
            } else {
                c(e2);
            }
        } catch (cwg unused) {
            LogUtil.b("HwVoiceKitManager", "TlvException");
            sqo.al("TlvException");
        }
    }

    private void e(List<cwd> list) {
        this.ad = false;
        this.z = false;
        this.t = 0;
        this.aj = ProfileRequestConstants.X_LANGUAGE_VALUE;
        kfl.d(-1);
        int i = 0;
        for (cwd cwdVar : list) {
            int c2 = jds.c(cwdVar.c(), 16);
            int c3 = jds.c(cwdVar.e(), 16);
            if (c3 == 1) {
                i = c2;
            } else if (c3 == 3) {
                this.ad = c2 == 1;
            } else if (c3 == 6) {
                this.z = c2 == 1;
            } else if (c3 == 7) {
                this.t = c2;
            } else if (c3 != 9) {
                if (c3 == 10) {
                    kfl.d(c2);
                    LogUtil.a("HwVoiceKitManager", "handleInitMassage deviceAIVolume:", Integer.valueOf(kfl.a()));
                } else {
                    LogUtil.h("HwVoiceKitManager", "Invalid init types");
                }
            } else if (i == 1 || i == 3) {
                String e2 = cvx.e(cwdVar.c());
                this.aj = e2;
                LogUtil.a("HwVoiceKitManager", "handleInitMassage mLanguage:", e2);
            }
        }
        this.p = i;
        c(i);
    }

    private void d(List<cwd> list) {
        for (cwd cwdVar : list) {
            int c2 = jds.c(cwdVar.e(), 16);
            if (c2 == 1) {
                int c3 = jds.c(cwdVar.c(), 16);
                LogUtil.a("HwVoiceKitManager", "audio message number: ", Integer.valueOf(c3));
                if (c3 == 0 && this.p == 1) {
                    v();
                }
            } else if (c2 == 2) {
                if (this.u == null) {
                    LogUtil.h("HwVoiceKitManager", "WRITE_AUDIO_MODULE_CONTENT but voice client is null");
                    return;
                }
                synchronized (e) {
                    if (this.ab) {
                        this.an.add(cwdVar.c());
                    }
                }
                synchronized (c) {
                    if (this.ag) {
                        this.f.add(cwdVar.c());
                    }
                }
            } else if (c2 == 3) {
                LogUtil.a("HwVoiceKitManager", "useMlKit: ", Integer.valueOf(jds.c(cwdVar.c(), 16)));
            } else {
                LogUtil.h("HwVoiceKitManager", "Invalid init types.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        ThreadPoolManager.d().d("VoiceKitManagerWriteAudio", new Runnable() { // from class: kfi.1
            @Override // java.lang.Runnable
            public void run() {
                while (kfi.this.ag) {
                    kfi.this.aa();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aa() {
        if (this.u == null || this.f.size() <= 0) {
            return;
        }
        synchronized (c) {
            byte[] a2 = !this.f.isEmpty() ? cvx.a(this.f.get(0)) : null;
            if (a2 != null) {
                Message obtain = Message.obtain();
                obtain.what = 7;
                obtain.obj = a2;
                this.y.sendMessage(obtain);
                this.f.remove(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ac() {
        if (this.av == null || this.an.size() <= 0) {
            return;
        }
        synchronized (c) {
            byte[] a2 = !this.an.isEmpty() ? cvx.a(this.an.get(0)) : null;
            if (a2 != null) {
                Message obtain = Message.obtain();
                obtain.what = 13;
                obtain.obj = a2;
                boolean z = true;
                if (this.an.size() != 1) {
                    z = false;
                }
                this.h = z;
                this.y.sendMessage(obtain);
                this.an.remove(0);
            }
        }
    }

    private void a(List<cwd> list) {
        for (cwd cwdVar : list) {
            int c2 = jds.c(cwdVar.e(), 16);
            if (c2 == 1) {
                d(cvx.e(cwdVar.c()));
            } else if (c2 == 2) {
                this.ai = false;
                this.ac = true;
                w();
            } else {
                LogUtil.h("HwVoiceKitManager", "handleResultCode Invalid type");
            }
        }
    }

    private void c(List<cwd> list) {
        for (cwd cwdVar : list) {
            int c2 = jds.c(cwdVar.e(), 16);
            if (c2 == 1) {
                a(jds.c(cwdVar.c(), 16));
            } else if (c2 == 3) {
                d(jds.c(cvx.e(cwdVar.c()), 10));
            } else {
                LogUtil.h("HwVoiceKitManager", "handleWatchInfoQuery Invalid type");
            }
        }
    }

    private void a(int i) {
        String[] split = "none-pause-running-Ring".split(Constants.LINK);
        if (i >= split.length) {
            LogUtil.h("HwVoiceKitManager", "updateTimerStatus timerStatus error:", Integer.valueOf(i));
            return;
        }
        if (this.au == null) {
            LogUtil.h("HwVoiceKitManager", "updateTimerStatus mTimerStateCondition is null");
            return;
        }
        String str = split[i];
        String format = String.format(Locale.ENGLISH, "{\"contexts\":[{\"header\":{\"name\":\"TimerInfoUpload\",\"namespace\":\"Timer\"},\"payload\":{\"curTimerStatus\":\"%s\"}}]}", str);
        LogUtil.a("HwVoiceKitManager", "updateTimerStatus timerStatusContext:", format);
        bzo bzoVar = this.u;
        if (bzoVar != null) {
            bzoVar.updateVoiceContext(format);
        }
        for (int i2 = 0; i2 < this.au.length(); i2++) {
            if (str.equals(this.au.optString(i2))) {
                String replace = this.bb.replace("TimerInfoQuery", "");
                this.bb = replace;
                b(replace);
                return;
            }
        }
    }

    private void d(int i) {
        if (i < 0 || i > 100) {
            LogUtil.h("HwVoiceKitManager", "updateVolumeValue volumeValue error:", Integer.valueOf(i));
            return;
        }
        LogUtil.a("HwVoiceKitManager", "updateVolumeValue volume value:", Integer.valueOf(i));
        if (this.ap == null) {
            LogUtil.h("HwVoiceKitManager", "updateVolumeValue mResponsesList is null");
            return;
        }
        for (int i2 = 0; i2 < this.ap.length(); i2++) {
            JSONObject optJSONObject = this.ap.optJSONObject(i2);
            b(optJSONObject.optJSONObject("commandUserInteractionDisplayText"), i);
            b(optJSONObject.optJSONObject("commandUserInteractionSpeak"), i);
        }
        this.k.countDown();
    }

    private void b(JSONObject jSONObject, int i) {
        if (jSONObject == null) {
            LogUtil.h("HwVoiceKitManager", "updateResponseVolume textJson is null");
            return;
        }
        try {
            jSONObject.put(Constant.TEXT, jSONObject.optString(Constant.TEXT).replace("#{afterVolume}", String.valueOf(i)));
        } catch (JSONException unused) {
            LogUtil.b("HwVoiceKitManager", "updateResponseVolume JSONException, responseVolume update failed");
            sqo.al("updateResponseVolume JSONException, responseVolume update failed");
        }
    }

    private void d(String str) {
        if (this.ap == null) {
            LogUtil.h("HwVoiceKitManager", "handleResultCode mResponsesList is null");
            return;
        }
        LogUtil.a("HwVoiceKitManager", "matchResultText resultCode: ", str);
        for (int i = 0; i < this.ap.length(); i++) {
            JSONObject optJSONObject = this.ap.optJSONObject(i);
            if (optJSONObject != null && optJSONObject.optString("resultCode").equals(str)) {
                String optString = optJSONObject.optString("ttsText");
                if (!TextUtils.isEmpty(optString)) {
                    c(optString, true, "");
                } else if (this.ae) {
                    b(optJSONObject);
                } else {
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("commandUserInteractionSpeak");
                    if (optJSONObject2 != null) {
                        c(optJSONObject2.optString(Constant.TEXT), false, "");
                    }
                    JSONObject optJSONObject3 = optJSONObject.optJSONObject("commandUserInteractionDisplayText");
                    if (optJSONObject3 != null) {
                        kfk.b(this.s, optJSONObject3.optString(Constant.TEXT));
                        this.s = "";
                    }
                }
            }
        }
    }

    private void b(JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("HwVoiceKitManager", "displayWatchInfo jsonObject is null!");
            return;
        }
        LogUtil.a("HwVoiceKitManager", "displayWatchInfo");
        JSONObject optJSONObject = jSONObject.optJSONObject("commandUserInteractionSpeak");
        String optString = optJSONObject != null ? optJSONObject.optString(Constant.TEXT) : "";
        JSONObject optJSONObject2 = jSONObject.optJSONObject("commandUserInteractionDisplayText");
        String optString2 = optJSONObject2 != null ? optJSONObject2.optString(Constant.TEXT) : "";
        if (optString.contains("#{afterVolume}") || optString2.contains("#{afterVolume}")) {
            Boolean bool = false;
            try {
                LogUtil.a("HwVoiceKitManager", "matchResultText mCountDownLatch count is ", Long.valueOf(this.k.getCount()));
                bool = Boolean.valueOf(this.k.await(1200L, TimeUnit.MILLISECONDS));
            } catch (InterruptedException unused) {
                LogUtil.h("HwVoiceKitManager", "matchResultText mCountDownLatch InterruptedException");
                sqo.al("matchResultText mCountDownLatch InterruptedException");
            }
            if (!bool.booleanValue()) {
                LogUtil.h("HwVoiceKitManager", "matchResultText watchInfo update failed");
                return;
            } else {
                optString = optJSONObject != null ? optJSONObject.optString(Constant.TEXT) : "";
                optString2 = optJSONObject2 != null ? optJSONObject2.optString(Constant.TEXT) : "";
            }
        }
        if (!TextUtils.isEmpty(optString)) {
            c(optString, false, "");
        }
        if (TextUtils.isEmpty(optString2)) {
            return;
        }
        kfk.b(this.s, optString2);
        this.s = "";
    }

    private void c(String str, boolean z, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwVoiceKitManager", "processTts ttsText isEmpty");
            return;
        }
        LogUtil.a("HwVoiceKitManager", "processTts ttsText:", str, " , isSendText:", Boolean.valueOf(z), ", mIsInitTtsSuccess:", Boolean.valueOf(this.aa));
        if (z) {
            kfk.b("", kfl.a(str));
        }
        if (this.aa) {
            int a2 = this.aq.a();
            LogUtil.a("HwVoiceKitManager", "scoConnectState:", Integer.valueOf(a2));
            this.ax = str;
            this.bc = str2;
            if (a2 == 12 && this.aq.d()) {
                this.y.sendEmptyMessage(4, 1200L);
            }
        }
    }

    private void c(int i) {
        if (i == 1) {
            if (!CommonUtil.ar() && !CommonUtil.bf()) {
                LogUtil.h("HwVoiceKitManager", "system less than emui10.");
            }
            if (this.u == null) {
                LogUtil.h("HwVoiceKitManager", "handleVoiceKit mHiAiEngineProxy is null");
                kfk.e(255);
                return;
            }
            a();
            this.v = 0;
            this.aa = false;
            s();
            e();
            t();
            if (!this.u.isPluginAvaiable()) {
                LogUtil.a("HwVoiceKitManager", "load HiAi plug");
                q();
                this.u.b(this.w);
                return;
            }
            g();
            return;
        }
        if (i == 2) {
            this.ac = true;
            w();
            e();
            PowerKitManager.e().e("HwVoiceKitManager", 512, "user-VoiceKit");
            return;
        }
        if (i == 3) {
            h();
            return;
        }
        if (i != 4) {
            if (i == 5) {
                f();
                return;
            } else {
                LogUtil.h("HwVoiceKitManager", "Invalid action.");
                return;
            }
        }
        this.ab = false;
        SpeechRecognizerManager speechRecognizerManager = this.av;
        if (speechRecognizerManager != null) {
            speechRecognizerManager.d();
        }
        PowerKitManager.e().e("HwVoiceKitManager", 512, "user-VoiceKit");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (!ScreenUtil.a()) {
            PowerKitManager.e().a("HwVoiceKitManager", 512, "user-VoiceKit");
        }
        String d2 = kfl.d("37-1037-2037");
        String d3 = kfl.d("38-1038-2038");
        LogUtil.a("HwVoiceKitManager", "handleVoiceKit mLanguage:", this.aj);
        Intent intent = new Intent();
        intent.putExtra("authAk", d2);
        intent.putExtra("authSk", d3);
        if (!this.aj.equals(ProfileRequestConstants.X_LANGUAGE_VALUE)) {
            LogUtil.a("HwVoiceKitManager", "overseas get conutry code is ", this.l);
            intent.putExtra("IssueCountry.countryCode", this.l);
            this.u.initServiceRoute();
        }
        this.u.createKit(this.o, intent, this.aj, this.r);
        kfl.c(-1);
    }

    private void a() {
        LogUtil.a("HwVoiceKitManager", "enter getCountryCode");
        String commonCountryCode = GRSManager.a(this.o).getCommonCountryCode();
        this.l = commonCountryCode;
        LogUtil.a("HwVoiceKitManager", "getCountryCode mCountryCode:", commonCountryCode);
    }

    private boolean e(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.optJSONObject("header") == null || jSONObject.optJSONObject("payload") == null) {
            return true;
        }
        return TextUtils.isEmpty(jSONObject.optJSONObject("header").optString("name"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        JSONArray c2 = c(str);
        if (c2 == null) {
            return;
        }
        int i = 0;
        this.ae = false;
        this.q = null;
        this.s = null;
        JSONObject jSONObject = null;
        while (true) {
            if (i >= c2.length()) {
                break;
            }
            JSONObject optJSONObject = c2.optJSONObject(i);
            if (!e(optJSONObject)) {
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("header");
                JSONObject optJSONObject3 = optJSONObject.optJSONObject("payload");
                String optString = optJSONObject2.optString("name");
                ReleaseLogUtil.e("R_HwVoiceKitManager", "processOnResult getName: ", optString);
                if ("DisplayText".equals(optString)) {
                    this.q = optJSONObject3.optString(Constant.TEXT);
                } else if ("DisplayASR".equals(optString)) {
                    this.s = optJSONObject3.optString(Constant.TEXT);
                    if (this.t < 1 && !this.af) {
                        this.q = "我不太理解你的意思，再给我一点时间学习吧。";
                        break;
                    }
                } else if (("DisplayHWCardData".equals(optString) && "UserInteraction".equals(optJSONObject2.optString(e.j))) || "DisplayHWCard".equals(optString)) {
                    kfl.d(optJSONObject3, this.s, this.q);
                    this.s = null;
                    this.q = null;
                } else if ("CallCapabilityValidation".equals(optString) || "CallHistoryUpload".equals(optString) || "TimerInfoQuery".equals(optString)) {
                    if (a(optString, optJSONObject3)) {
                        this.bb = str;
                        return;
                    }
                } else if ("StartRecord".equals(optString)) {
                    this.ai = true;
                } else if ("Speak".equals(optString)) {
                    this.q = b(this.q, optJSONObject3);
                } else if ("DialogFinished".equals(optString)) {
                    a(str);
                } else {
                    jSONObject = a(optJSONObject2, optJSONObject3);
                }
            }
            i++;
        }
        e(this.q, this.s, jSONObject);
    }

    private String b(String str, JSONObject jSONObject) {
        String optString = jSONObject.optString(Constant.TEXT);
        String optString2 = jSONObject.optString("language");
        if (optString.contains("#{contactName} #{phoneNumber}")) {
            str = f(optString);
            optString = str;
        }
        c(optString, false, optString2);
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        if (this.ag) {
            ReleaseLogUtil.e("R_HwVoiceKitManager", "toStopSpeech");
            this.ag = false;
            u();
        }
    }

    private boolean a(String str, JSONObject jSONObject) {
        boolean z = false;
        if (!this.af) {
            return false;
        }
        if ("CallCapabilityValidation".equals(str)) {
            z = f(jSONObject);
        } else if ("CallHistoryUpload".equals(str)) {
            z = ad();
        } else if ("TimerInfoQuery".equals(str)) {
            z = i(jSONObject);
        } else if ("checkVolume".equals(str)) {
            z = d(jSONObject);
        } else {
            LogUtil.h("HwVoiceKitManager", "isReturnForResult other name");
        }
        LogUtil.a("HwVoiceKitManager", "isReturnForResult name:", str, ", isReturn:", Boolean.valueOf(z));
        return z;
    }

    private boolean d(JSONObject jSONObject) {
        try {
            kfk.c(new JSONObject("{\"Exercise\":\"0\",\"Ringtone\":\"1\",\"Hivoice\":\"2\",\"Media\":\"1\"}").optString(jSONObject.optString("streamType")));
            this.ae = true;
            this.k = new CountDownLatch(1);
            return true;
        } catch (JSONException unused) {
            LogUtil.b("HwVoiceKitManager", "isCheckVolumeValue JSONException");
            sqo.al("isCheckVolumeValue JSONException");
            return false;
        }
    }

    private boolean i(JSONObject jSONObject) {
        int optInt = jSONObject.optInt("uploadFlag");
        if (optInt == 1) {
            this.au = jSONObject.optJSONArray("timerStateCondition");
            kfk.b();
        }
        return optInt == 1;
    }

    private String f(String str) {
        kfj.d dVar = this.n;
        if (dVar == null) {
            return str;
        }
        String b2 = dVar.b();
        String d2 = this.n.d();
        if (!str.contains("contactName")) {
            return str;
        }
        return str.replace("#{contactName} #{phoneNumber}", b2 + " " + d2);
    }

    private void e(String str, String str2, JSONObject jSONObject) {
        int i;
        this.y.removeMessages(1);
        Object[] objArr = new Object[6];
        objArr[0] = "processOnResult displayAsr:";
        objArr[1] = str2;
        objArr[2] = ", displayText:";
        objArr[3] = str;
        objArr[4] = ", apiJsonObject:";
        objArr[5] = Boolean.valueOf(jSONObject == null);
        LogUtil.a("HwVoiceKitManager", objArr);
        if (jSONObject == null) {
            kfk.b(kfl.b(str2), kfl.a(str));
        }
        h(jSONObject);
        p();
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str) && (i = this.ao) != 0) {
            if (i == 1) {
                this.ao = 255;
            }
            kfk.e(this.ao);
            this.ac = true;
        }
    }

    private JSONArray c(String str) {
        JSONObject jSONObject;
        if (e(str)) {
            return null;
        }
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException unused) {
            LogUtil.b("HwVoiceKitManager", "processOnResult JSONException");
            sqo.al("processOnResult JSONException");
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.optJSONArray("directives");
    }

    private JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) {
        String optString = jSONObject.optString("name");
        try {
            if ("API".equals(optString)) {
                jSONObject2 = jSONObject2.optJSONObject("callParams");
            } else if (!"OpenApp".equals(optString) && !"ExitApp".equals(optString) && !"AlarmCreate".equals(optString) && !"MediaControl".equals(optString)) {
                if ("OpenCallHistory".equals(optString)) {
                    if (jSONObject2 != null) {
                        jSONObject2.put("commandCode", 10);
                    }
                } else if ("ContactSelect".equals(optString)) {
                    if (jSONObject2 != null) {
                        jSONObject2.put("commandCode", 11);
                    }
                } else if ("VoiceCall".equals(optString)) {
                    if (jSONObject2 != null) {
                        jSONObject2.put("commandCode", 12);
                    }
                } else if ("RedialVoiceCall".equals(optString)) {
                    jSONObject2 = new JSONObject();
                    jSONObject2.put("commandCode", 12);
                    kfj.d dVar = this.n;
                    if (dVar != null) {
                        jSONObject2.put("contactName", dVar.b());
                        jSONObject2.put("phoneNumber", this.n.d());
                    }
                } else {
                    jSONObject2 = b(jSONObject, jSONObject2);
                }
            }
            return jSONObject2;
        } catch (JSONException unused) {
            LogUtil.h("HwVoiceKitManager", "processJumpResult JSONException");
            sqo.al("processJumpResult JSONException");
            return null;
        }
    }

    private JSONObject b(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        String optString = jSONObject.optString("name");
        return ((!"DisplayHWCardData".equals(optString) || "UserInteraction".equals(jSONObject.optString(e.j))) && !"MediaCardData".equals(optString)) ? e(jSONObject, jSONObject2) : jSONObject2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private JSONObject e(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        char c2;
        JSONObject jSONObject3;
        String optString = jSONObject.optString("name");
        optString.hashCode();
        int i = -1;
        switch (optString.hashCode()) {
            case -654455343:
                if (optString.equals("TimerPause")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -652491478:
                if (optString.equals("TimerReset")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 21347114:
                if (optString.equals("TimerRestart")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 829748353:
                if (optString.equals("TimerCreate")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 1247615922:
                if (optString.equals("TimerResume")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 != 0) {
            if (c2 == 1) {
                i = 4;
            } else if (c2 == 2) {
                i = 5;
            } else if (c2 == 3) {
                jSONObject3 = jSONObject2;
                i = 1;
            } else if (c2 != 4) {
                jSONObject3 = null;
            } else {
                jSONObject3 = jSONObject2;
                i = 3;
            }
            jSONObject3 = jSONObject2;
        } else {
            jSONObject3 = jSONObject2;
            i = 2;
        }
        if (jSONObject3 != null) {
            jSONObject3.put("commandCode", 13);
            jSONObject3.put("timerCode", i);
            return jSONObject3;
        }
        return h(jSONObject, jSONObject2);
    }

    private JSONObject h(JSONObject jSONObject, JSONObject jSONObject2) {
        String optString = jSONObject.optString("name");
        if ("GetPosition".equals(optString)) {
            l();
            return null;
        }
        if ("UnlockScreen".equals(optString)) {
            j(jSONObject2);
            return null;
        }
        if ("RequestHuaweiAT".equals(optString)) {
            ab();
            return null;
        }
        if ("Messenger".equals(optString)) {
            JSONObject optJSONObject = jSONObject2.optJSONObject("callParams");
            try {
                optJSONObject.put("responses", jSONObject2.optJSONArray("responses"));
            } catch (JSONException unused) {
                LogUtil.b("HwVoiceKitManager", "processUpdateResult JSONException");
                optJSONObject = null;
            }
            if (optJSONObject != null) {
                return c(optJSONObject, jSONObject2);
            }
            return null;
        }
        if ("ExeNativeSkill".equals(optString)) {
            return g(jSONObject2);
        }
        LogUtil.h("HwVoiceKitManager", "processUpdateResult Invalid payload name: ", optString);
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0083 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.json.JSONObject c(org.json.JSONObject r11, org.json.JSONObject r12) {
        /*
            r10 = this;
            java.lang.String r0 = "functionName"
            java.lang.String r0 = r11.optString(r0)
            r0.hashCode()
            int r1 = r0.hashCode()
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 0
            r6 = 1
            r7 = 2
            r8 = -1
            switch(r1) {
                case -71542462: goto L54;
                case -70756980: goto L48;
                case 297863249: goto L3c;
                case 322213191: goto L30;
                case 695778843: goto L24;
                case 696564325: goto L18;
                default: goto L17;
            }
        L17:
            goto L60
        L18:
            java.lang.String r1 = "turnOffQuietMode"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L22
            goto L60
        L22:
            r0 = r2
            goto L61
        L24:
            java.lang.String r1 = "turnOnQuietMode"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L2e
            goto L60
        L2e:
            r0 = r3
            goto L61
        L30:
            java.lang.String r1 = "turnOffSleepScene"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L3a
            goto L60
        L3a:
            r0 = r4
            goto L61
        L3c:
            java.lang.String r1 = "turnOnSleepScene"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L46
            goto L60
        L46:
            r0 = r7
            goto L61
        L48:
            java.lang.String r1 = "turnOffNodisturb"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L52
            goto L60
        L52:
            r0 = r6
            goto L61
        L54:
            java.lang.String r1 = "turnOnNodisturb"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L5e
            goto L60
        L5e:
            r0 = r5
            goto L61
        L60:
            r0 = r8
        L61:
            r1 = 0
            if (r0 == 0) goto L7e
            if (r0 == r6) goto L79
            if (r0 == r7) goto L77
            if (r0 == r4) goto L7f
            if (r0 == r3) goto L74
            if (r0 == r2) goto L72
            r0 = r1
            r5 = r8
        L70:
            r6 = r5
            goto L81
        L72:
            r6 = r7
            goto L79
        L74:
            r0 = r11
            r5 = r7
            goto L81
        L77:
            r0 = r11
            goto L81
        L79:
            r0 = r11
            r9 = r6
            r6 = r5
            r5 = r9
            goto L81
        L7e:
            r5 = r6
        L7f:
            r0 = r11
            goto L70
        L81:
            if (r0 == 0) goto La3
            java.lang.String r11 = "commandCode"
            r12 = 14
            r0.put(r11, r12)     // Catch: org.json.JSONException -> L97
            java.lang.String r11 = "settingItem"
            r0.put(r11, r5)     // Catch: org.json.JSONException -> L97
            java.lang.String r11 = "optCode"
            r0.put(r11, r6)     // Catch: org.json.JSONException -> L97
            r1 = r0
            goto La7
        L97:
            java.lang.String r11 = "processSystemSetting JSONException"
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            java.lang.String r12 = "HwVoiceKitManager"
            com.huawei.hwlogsmodel.LogUtil.b(r12, r11)
            goto La7
        La3:
            org.json.JSONObject r1 = r10.g(r11, r12)
        La7:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kfi.c(org.json.JSONObject, org.json.JSONObject):org.json.JSONObject");
    }

    private JSONObject g(JSONObject jSONObject) {
        int i;
        String optString = jSONObject.optString("type");
        JSONObject optJSONObject = jSONObject.optJSONObject(e.n);
        if ("shutdown".equals(optString)) {
            i = 2;
        } else if ("reboot".equals(optString)) {
            i = 3;
        } else {
            i = -1;
            optJSONObject = null;
        }
        if (optJSONObject != null) {
            try {
                optJSONObject.put("commandCode", 14);
                optJSONObject.put("settingItem", 3);
                optJSONObject.put("optCode", i);
                c(this.q, false, "");
                kfk.b(this.s, this.q);
            } catch (JSONException unused) {
                LogUtil.b("HwVoiceKitManager", "processShutdownReboot JSONException");
                sqo.al("processShutdownReboot JSONException");
                return null;
            }
        }
        return optJSONObject;
    }

    private JSONObject g(JSONObject jSONObject, JSONObject jSONObject2) {
        char c2;
        JSONObject jSONObject3;
        String optString = jSONObject.optString("functionName");
        optString.hashCode();
        int hashCode = optString.hashCode();
        int i = 0;
        if (hashCode == -1936123207) {
            if (optString.equals("turnDownVolume")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode != 670514716) {
            if (hashCode == 1118722354 && optString.equals("turnUpVolume")) {
                c2 = 2;
            }
            c2 = 65535;
        } else {
            if (optString.equals("setVolume")) {
                c2 = 1;
            }
            c2 = 65535;
        }
        if (c2 == 0) {
            jSONObject3 = jSONObject;
            i = 1;
        } else if (c2 == 1) {
            jSONObject3 = jSONObject;
            i = 2;
        } else if (c2 != 2) {
            jSONObject3 = null;
            i = -1;
        } else {
            jSONObject3 = jSONObject;
        }
        if (jSONObject3 != null) {
            try {
                jSONObject3.put("commandCode", 15);
                jSONObject3.put("optType", i);
                return jSONObject3;
            } catch (JSONException unused) {
                LogUtil.b("HwVoiceKitManager", "processVolumeSetting JSONException");
                sqo.al("processVolumeSetting JSONException");
                return null;
            }
        }
        return d(jSONObject, jSONObject2);
    }

    private JSONObject d(JSONObject jSONObject, JSONObject jSONObject2) {
        String optString = jSONObject.optString("functionName");
        if ("checkVolume".equals(optString)) {
            if (a(optString, jSONObject)) {
                this.ap = jSONObject2.optJSONArray("responses");
            }
            return new JSONObject();
        }
        LogUtil.h("HwVoiceKitManager", "processCheckVolume Invalid payload name: ", optString);
        return null;
    }

    private boolean e(String str) {
        this.af = true;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwVoiceKitManager", "checkVoiceResponse is null");
            return true;
        }
        LogUtil.h("HwVoiceKitManager", "checkVoiceResponse voiceResponse: ", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray optJSONArray = jSONObject.optJSONArray("directives");
            if (optJSONArray != null && optJSONArray.length() != 0) {
                this.ao = jSONObject.optInt("errorCode");
                JSONArray optJSONArray2 = jSONObject.optJSONArray("contexts");
                if (optJSONArray2 != null && optJSONArray2.length() != 0) {
                    this.af = kfl.d(optJSONArray2, this.t, this.m);
                    return false;
                }
                LogUtil.h("HwVoiceKitManager", "checkVoiceResponse contextsList is null");
                return false;
            }
            LogUtil.h("HwVoiceKitManager", "checkVoiceResponse directivesList is null");
            return true;
        } catch (JSONException unused) {
            LogUtil.h("HwVoiceKitManager", "checkVoiceResponse JSONException");
            return true;
        }
    }

    private boolean f(JSONObject jSONObject) {
        if (!jSONObject.optBoolean("errorReturnCode") && EnvironmentInfo.o() && !n()) {
            LogUtil.a("HwVoiceKitManager", "not need updateCallCapability");
        } else {
            String format = String.format(Locale.ENGLISH, "{\"contexts\":[{\"header\":{\"name\":\"CallCapability\",\"namespace\":\"System\"},\"payload\":{\"simCardStatus\":%b,\"flightModeStatus\":%b}}]}", Boolean.valueOf(EnvironmentInfo.o()), Boolean.valueOf(!n()));
            LogUtil.a("HwVoiceKitManager", "simContext: ", format);
            bzo bzoVar = this.u;
            if (bzoVar != null) {
                bzoVar.updateVoiceContext(format);
            }
        }
        return !EnvironmentInfo.o() || n();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x007d, code lost:
    
        r12 = new kfj.d();
        r12.a(r3);
        r12.d(r10);
        r11 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0089, code lost:
    
        r2 = java.lang.String.format(java.util.Locale.ENGLISH, "{\"contexts\":[{\"header\":{\"name\":\"CallHistory\",\"namespace\":\"System\"},\"payload\":{\"contactName\":\"%s\",\"callHistoryExists\":%b}}]}", r3, java.lang.Boolean.valueOf(r11));
        com.huawei.hwlogsmodel.LogUtil.a("HwVoiceKitManager", "updateCallHistory historyContext:", r2);
        r0 = r13.u;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x00a6, code lost:
    
        if (r0 == null) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x00a8, code lost:
    
        r0.updateVoiceContext(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x00ab, code lost:
    
        r13.n = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x00af, code lost:
    
        return !r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0074, code lost:
    
        if (r2 == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0061, code lost:
    
        if (r2 == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0063, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x007b, code lost:
    
        if (android.text.TextUtils.isEmpty(r10) != false) goto L27;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b4  */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r12v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean ad() {
        /*
            r13 = this;
            java.lang.String r0 = "HwVoiceKitManager"
            android.content.Context r1 = r13.o
            android.content.ContentResolver r2 = r1.getContentResolver()
            java.lang.String r1 = "type"
            java.lang.String r8 = "name"
            java.lang.String r9 = "number"
            java.lang.String[] r4 = new java.lang.String[]{r8, r9, r1}
            r1 = 1
            java.lang.String r3 = java.lang.String.valueOf(r1)
            r5 = 3
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r6 = 5
            java.lang.String r6 = java.lang.String.valueOf(r6)
            r7 = 2
            java.lang.String r7 = java.lang.String.valueOf(r7)
            java.lang.String r10 = defpackage.kfi.b
            java.lang.String[] r6 = new java.lang.String[]{r3, r5, r6, r7, r10}
            java.lang.String r10 = ""
            r11 = 0
            r12 = 0
            android.net.Uri r3 = android.provider.CallLog.Calls.CONTENT_URI     // Catch: java.lang.Throwable -> L67 android.database.SQLException -> L69
            java.lang.String r5 = "(type = ? or type = ? or type = ? or type = ? )and subscription_component_name = ?"
            java.lang.String r7 = "date DESC"
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L67 android.database.SQLException -> L69
            if (r2 == 0) goto L60
            int r3 = r2.getCount()     // Catch: android.database.SQLException -> L5e java.lang.Throwable -> Lb0
            if (r3 <= 0) goto L60
            boolean r3 = r2.moveToFirst()     // Catch: android.database.SQLException -> L5e java.lang.Throwable -> Lb0
            if (r3 == 0) goto L60
            int r3 = r2.getColumnIndex(r8)     // Catch: android.database.SQLException -> L5e java.lang.Throwable -> Lb0
            java.lang.String r3 = r2.getString(r3)     // Catch: android.database.SQLException -> L5e java.lang.Throwable -> Lb0
            if (r3 != 0) goto L54
            r3 = r10
        L54:
            int r4 = r2.getColumnIndex(r9)     // Catch: android.database.SQLException -> L6b java.lang.Throwable -> Lb0
            java.lang.String r4 = r2.getString(r4)     // Catch: android.database.SQLException -> L6b java.lang.Throwable -> Lb0
            r10 = r4
            goto L61
        L5e:
            r3 = r10
            goto L6b
        L60:
            r3 = r10
        L61:
            if (r2 == 0) goto L77
        L63:
            r2.close()
            goto L77
        L67:
            r0 = move-exception
            goto Lb2
        L69:
            r3 = r10
            r2 = r12
        L6b:
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> Lb0
            java.lang.String r5 = "getCallHistory Exception"
            r4[r11] = r5     // Catch: java.lang.Throwable -> Lb0
            com.huawei.hwlogsmodel.LogUtil.b(r0, r4)     // Catch: java.lang.Throwable -> Lb0
            if (r2 == 0) goto L77
            goto L63
        L77:
            boolean r2 = android.text.TextUtils.isEmpty(r10)
            if (r2 != 0) goto L89
            kfj$d r12 = new kfj$d
            r12.<init>()
            r12.a(r3)
            r12.d(r10)
            r11 = r1
        L89:
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r11)
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r4}
            java.lang.String r4 = "{\"contexts\":[{\"header\":{\"name\":\"CallHistory\",\"namespace\":\"System\"},\"payload\":{\"contactName\":\"%s\",\"callHistoryExists\":%b}}]}"
            java.lang.String r2 = java.lang.String.format(r2, r4, r3)
            java.lang.String r3 = "updateCallHistory historyContext:"
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r2}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r3)
            bzo r0 = r13.u
            if (r0 == 0) goto Lab
            r0.updateVoiceContext(r2)
        Lab:
            r13.n = r12
            r0 = r11 ^ 1
            return r0
        Lb0:
            r0 = move-exception
            r12 = r2
        Lb2:
            if (r12 == 0) goto Lb7
            r12.close()
        Lb7:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kfi.ad():boolean");
    }

    private boolean n() {
        return Settings.System.getInt(this.o.getContentResolver(), "airplane_mode_on", 0) == 1;
    }

    private void ab() {
        String str = this.aw;
        if (TextUtils.isEmpty(str)) {
            s();
            str = String.format(Locale.ENGLISH, "{\"contexts\":[{\"header\":{\"name\":\"HuaweiAT\",\"namespace\":\"System\"},\"payload\":{\"uid\":\"%s\",\"huaweiAT\":\"%s\"}}]}", "", "");
        }
        bzo bzoVar = this.u;
        if (bzoVar != null) {
            bzoVar.updateVoiceContext(str);
        }
    }

    private void j(JSONObject jSONObject) {
        boolean optBoolean = jSONObject.optBoolean("needSuccessResponse");
        bzo bzoVar = this.u;
        if (bzoVar == null || !optBoolean) {
            return;
        }
        bzoVar.updateVoiceContext("{\"header\":{\"namespace\":\"System\",\"name\":\"ClientContext\"},\"payload\":{\"screenLockerStatus\":false}}");
    }

    private void l() {
        double parseDouble;
        if (this.u == null) {
            return;
        }
        int i = 1;
        double d2 = 0.0d;
        if (kch.d(this.o)) {
            if (jdi.c(this.o, d)) {
                String lngAndLat = this.u.getLngAndLat(this.o);
                if (!TextUtils.isEmpty(lngAndLat)) {
                    String[] split = lngAndLat.split(",");
                    if (split.length != 2) {
                        LogUtil.h("HwVoiceKitManager", "processLocation can not get locations");
                        return;
                    }
                    try {
                        i = 0;
                        d2 = Double.parseDouble(split[0]);
                        parseDouble = Double.parseDouble(split[1]);
                        DecimalFormat decimalFormat = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.US));
                        String format = String.format(Locale.ENGLISH, "{\"contexts\":[{\"header\":{\"namespace\":\"GeoInformation\",\"name\":\"PositionInfo\"},\"payload\":{\"position\":{\"longitude\":\"%s\",\"latitude\":\"%s\",\"locationSystem\":\"WGS84\"},\"errorCode\":%d}}]}", decimalFormat.format(d2), decimalFormat.format(parseDouble), Integer.valueOf(i));
                        LogUtil.a("HwVoiceKitManager", "processLocation voiceContext:", format);
                        this.u.updateVoiceContext(format);
                    } catch (NumberFormatException unused) {
                        LogUtil.b("HwVoiceKitManager", "processLocation NumberFormatException");
                        sqo.al("processLocation NumberFormatException");
                        return;
                    }
                }
                i = 3;
            } else {
                i = 2;
            }
        }
        parseDouble = 0.0d;
        DecimalFormat decimalFormat2 = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.US));
        String format2 = String.format(Locale.ENGLISH, "{\"contexts\":[{\"header\":{\"namespace\":\"GeoInformation\",\"name\":\"PositionInfo\"},\"payload\":{\"position\":{\"longitude\":\"%s\",\"latitude\":\"%s\",\"locationSystem\":\"WGS84\"},\"errorCode\":%d}}]}", decimalFormat2.format(d2), decimalFormat2.format(parseDouble), Integer.valueOf(i));
        LogUtil.a("HwVoiceKitManager", "processLocation voiceContext:", format2);
        this.u.updateVoiceContext(format2);
    }

    private void p() {
        bzo bzoVar = this.u;
        if (bzoVar == null) {
            LogUtil.h("HwVoiceKitManager", "sendStartRecord mHiAiEngineProxy is null");
            return;
        }
        if (bzoVar.isSpeaking() || !this.ai) {
            LogUtil.h("HwVoiceKitManager", "sendStartRecord isSpeaking or mIsStartRecord is false");
        } else {
            if (this.z && this.aa) {
                return;
            }
            LogUtil.a("HwVoiceKitManager", "sendStartRecord sendEmptyMessageDelayed");
            this.y.sendEmptyMessage(5, 500L);
        }
    }

    private void a(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("cardParams");
        if (optJSONObject == null) {
            LogUtil.h("HwVoiceKitManager", "getDeepLink cardParams is null");
            return;
        }
        JSONObject optJSONObject2 = optJSONObject.optJSONObject("directive");
        if (optJSONObject2 == null) {
            JSONArray optJSONArray = optJSONObject.optJSONArray("items");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                a(optJSONArray);
                return;
            } else {
                LogUtil.h("HwVoiceKitManager", "getDeepLink directive is null");
                return;
            }
        }
        JSONObject optJSONObject3 = optJSONObject2.optJSONObject("deepLink");
        if (optJSONObject3 == null) {
            LogUtil.h("HwVoiceKitManager", "getDeepLink deepLinkObject is null");
            return;
        }
        String optString = optJSONObject3.optString("appPackage");
        String optString2 = optJSONObject3.optString("url");
        String optString3 = optJSONObject3.optString("appName");
        String optString4 = optJSONObject3.optString("action");
        String optString5 = optJSONObject3.optString("version");
        kfm kfmVar = new kfm();
        kfmVar.c(optString2);
        kfmVar.a(optString3);
        kfmVar.b(optString);
        kfmVar.d(optString5);
        kfmVar.e(optString4);
        kfl.c(optJSONObject3, kfmVar);
        kfl.c(this.o, kfmVar);
    }

    private void a(JSONArray jSONArray) {
        LogUtil.a("HwVoiceKitManager", "Enter processOverseaMusic");
        JSONArray optJSONArray = jSONArray.optJSONObject(0).optJSONArray("interaction");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            LogUtil.h("HwVoiceKitManager", "processOverseaMusic interactionList is null");
            return;
        }
        JSONObject optJSONObject = optJSONArray.optJSONObject(0);
        JSONObject optJSONObject2 = optJSONObject.optJSONObject("callParams");
        if (optJSONObject2 == null) {
            LogUtil.h("HwVoiceKitManager", "processOverseaMusic callParams is null");
            return;
        }
        String optString = optJSONObject.optString("appPackage");
        this.am = optString;
        if (TextUtils.isEmpty(optString)) {
            LogUtil.h("HwVoiceKitManager", "processOverseaMusic appPackage is null");
            return;
        }
        if (!kfl.c(this.am)) {
            LogUtil.h("HwVoiceKitManager", "processOverseaMusic get backup music package");
            String a2 = kfl.a(optJSONObject);
            if (TextUtils.isEmpty(a2)) {
                LogUtil.h("HwVoiceKitManager", "processOverseaMusic backupPackage not in system");
                return;
            }
            this.am = a2;
        }
        c(optJSONObject2);
    }

    private void c(JSONObject jSONObject) {
        Intent intent = new Intent();
        intent.setPackage(this.am);
        String optString = jSONObject.optString("action");
        if (!TextUtils.isEmpty(optString)) {
            intent.setAction(optString);
            intent.addFlags(32);
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(e.n);
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    String optString2 = optJSONObject.optString(MedalConstants.EVENT_KEY);
                    String optString3 = optJSONObject.optString("value");
                    if (!TextUtils.isEmpty(optString2) && !TextUtils.isEmpty(optString3)) {
                        intent.putExtra(optString2, optString3);
                    }
                }
            }
        }
        LogUtil.a("HwVoiceKitManager", "processOverseaMusic intent:", intent);
        this.o.sendOrderedBroadcast(intent, "com.android.mediacenter.permission.INTERACTION", this.ar, null, 0, null, null);
        kfl.a(this.o, this.am);
    }

    private void h(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            LogUtil.a("HwVoiceKitManager", "processApiCallParams jsonObject is empty");
            return;
        }
        LogUtil.a("HwVoiceKitManager", "processApiCallParams CallParams: ", jSONObject.toString());
        this.ap = jSONObject.optJSONArray("responses");
        kfj kfjVar = new kfj();
        String optString = jSONObject.optString("commandCode");
        if (TextUtils.isEmpty(optString)) {
            if (jSONObject.optJSONObject("cardParams") != null) {
                a(jSONObject);
                return;
            }
            String optString2 = jSONObject.optString(AwarenessRequest.Field.COMMAND);
            if (TextUtils.isEmpty(optString2)) {
                optString2 = "";
            }
            HwControlLocalAppHelper.e(optString2);
            return;
        }
        kfjVar.d(optString);
        String optString3 = jSONObject.optString("appName");
        if (!TextUtils.isEmpty(optString3)) {
            kfjVar.c(optString3);
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("dictionary");
        if (optJSONObject != null) {
            kfjVar.e(optJSONObject);
        }
        if (this.t < 1) {
            try {
                kfjVar.e(new JSONObject("{\"闹钟\":\"2\",\"支付宝|Alipay\":\"1\"}"));
                kfjVar.d(new JSONObject("{\"running\":\"1\",\"cycling\":\"3\",\"walking\":\"2\"}"));
            } catch (JSONException unused) {
                LogUtil.b("HwVoiceKitManager", "processApiCallParams JSONException");
                sqo.al("processApiCallParams JSONException");
            }
        }
        String optString4 = jSONObject.optString("repeat");
        if (!TextUtils.isEmpty(optString4)) {
            kfjVar.o(optString4);
        }
        String optString5 = jSONObject.optString("time");
        if (!TextUtils.isEmpty(optString5)) {
            kfjVar.m(optString5);
        }
        this.m.b(jSONObject, kfjVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        HuaweiIdAuthService huaweiIdAuthService = this.at;
        if (huaweiIdAuthService == null) {
            LogUtil.h("HwVoiceKitManager", "silentSignIn mSignInClient is null");
            k();
        } else {
            this.aw = "";
            huaweiIdAuthService.silentSignIn().addOnSuccessListener(new OnSuccessListener<AuthHuaweiId>() { // from class: kfi.6
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(AuthHuaweiId authHuaweiId) {
                    if (authHuaweiId == null) {
                        ReleaseLogUtil.d("R_HwVoiceKitManager", "signInHuaweiId is null");
                        return;
                    }
                    String uid = authHuaweiId.getUid();
                    String accessToken = authHuaweiId.getAccessToken();
                    ReleaseLogUtil.e("R_HwVoiceKitManager", "silentSignIn onSuccess");
                    kfi.this.d(uid, accessToken);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: kfi.7
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    if (exc == null) {
                        LogUtil.h("HwVoiceKitManager", "exception is null");
                        return;
                    }
                    LogUtil.h("HwVoiceKitManager", "silentSignIn onFailure", exc.getMessage());
                    Activity activity = BaseApplication.getActivity();
                    if (activity != null && kfi.this.at.getSignInIntent() != null) {
                        activity.startActivityForResult(kfi.this.at.getSignInIntent(), 1003);
                    }
                    kfi.s(kfi.this);
                    if (kfi.this.v <= 2) {
                        kfi.this.s();
                    } else {
                        LogUtil.h("HwVoiceKitManager", "silentSignIn max retry times");
                    }
                    if (exc instanceof ApiException) {
                        LogUtil.h("HwVoiceKitManager", "silent sign in fail, status: ", Integer.valueOf(((ApiException) exc).getStatusCode()));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        if (this.u == null) {
            return;
        }
        LogUtil.a("HwVoiceKitManager", "stopSpeak isScoOn:", Boolean.valueOf(this.aq.d()), ", isSpeaking:", Boolean.valueOf(this.u.isSpeaking()), ", phoneAIVolume:", Integer.valueOf(kfl.b()));
        if (this.u.isSpeaking()) {
            this.u.stopSpeak();
        }
        this.u.cancelSpeak();
        kfl.a(kfl.b());
        kfl.c(-1);
        this.aq.c();
        if (this.z && !this.ag) {
            kfk.a(2);
        }
        if (this.ai) {
            this.y.sendEmptyMessage(5);
        }
        this.ax = "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        bzo bzoVar = this.u;
        if (bzoVar == null) {
            LogUtil.h("HwVoiceKitManager", "breakLinkVoice mHiAiEngineProxy is null");
            return;
        }
        boolean isDestroyKit = bzoVar.isDestroyKit();
        LogUtil.a("HwVoiceKitManager", "breakLinkVoice isDestroy: ", Boolean.valueOf(isDestroyKit));
        if (isDestroyKit) {
            this.ag = false;
            this.n = null;
            kfl.c(-1);
            LogUtil.a("HwVoiceKitManager", "breakLinkVoice!");
            this.aq.c();
            this.y.removeMessages(1);
            ThreadPoolManager.d().e("VoiceKitManagerWriteAudio", null);
            this.m.d((System.currentTimeMillis() - this.x) / 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(String str) {
        this.ax = "";
        if (this.u == null || TextUtils.isEmpty(str) || this.u.isSpeaking()) {
            this.bc = "";
            LogUtil.h("HwVoiceKitManager", "toSpeak return");
            return;
        }
        if (this.ag) {
            LogUtil.h("HwVoiceKitManager", "toSpeak mIsPrepareSuccess is true");
            this.bc = "";
            w();
            return;
        }
        LogUtil.a("HwVoiceKitManager", "toSpeak isScoOn:", Boolean.valueOf(this.aq.d()));
        Intent intent = new Intent();
        if (!kat.b(this.bc) && !this.aj.equals(ProfileRequestConstants.X_LANGUAGE_VALUE)) {
            LogUtil.h("HwVoiceKitManager", "toSpeak the tanslate language is :", this.bc);
            intent.putExtra("language", this.bc);
            this.bc = "";
        }
        kfl.c(kfl.e());
        kfl.a(kfl.a());
        this.u.textToSpeak(str, intent);
    }

    private void q() {
        LogUtil.a("HwVoiceKitManager", "enter sendCheckHiAiPluginMessage");
        this.y.removeMessages(10);
        Message obtain = Message.obtain();
        obtain.what = 10;
        this.y.sendMessage(obtain, 2000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.u.isPluginAvaiable()) {
            return;
        }
        kfl.d();
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
        w();
        SpeechRecognizerManager speechRecognizerManager = this.av;
        if (speechRecognizerManager != null) {
            speechRecognizerManager.d();
        }
        try {
            Context context = this.o;
            if (context != null) {
                context.unregisterReceiver(this.i);
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.b("HwVoiceKitManager", "mConnectStateChangedReceiver is not registered");
        }
        if (this.ah) {
            kts.a(16);
            this.ah = false;
        }
        b();
        LogUtil.a("HwVoiceKitManager", "onDestroy");
    }

    private void b() {
        ExtendHandler extendHandler = this.y;
        if (extendHandler != null) {
            extendHandler.quit(false);
            this.y = null;
        }
    }

    private void a(String str) {
        this.n = null;
        this.m.a(str);
    }

    class c implements Handler.Callback {
        private c() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                LogUtil.h("HwVoiceKitManager", "handleMessage PROCESSING_TIMEOUT");
                kfi.this.w();
                kfi.this.e();
                return true;
            }
            if (i == 10) {
                kfi.this.c();
                return true;
            }
            if (i == 4) {
                if (!TextUtils.isEmpty(kfi.this.ax)) {
                    kfi kfiVar = kfi.this;
                    kfiVar.g(kfiVar.ax);
                }
                return true;
            }
            if (i == 5) {
                if (kfi.this.ai) {
                    kfk.a(3);
                    kfi.this.ai = false;
                }
                return true;
            }
            if (i == 7) {
                if (message.obj instanceof byte[]) {
                    kfi.this.u.writeAudio((byte[]) message.obj);
                }
                return true;
            }
            if (i == 8) {
                kfi.this.w();
                return true;
            }
            if (i == 12) {
                kts.a(16);
                kfi.this.ah = false;
                return true;
            }
            if (i == 13) {
                if (message.obj instanceof byte[]) {
                    LogUtil.a("HwVoiceKitManager", "handleMessage receiveVoiceStream.");
                    kfi.this.av.a((byte[]) message.obj, false);
                }
                return true;
            }
            LogUtil.h("HwVoiceKitManager", "handleMessage Invalid type: ", Integer.valueOf(message.what));
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwVoiceKitManager", "deviceInfo is null.");
        } else if (deviceInfo.getDeviceConnectState() == 2 && AppBundle.c().isBundleModule("PluginHiAiEngine") && !bzo.c().isPluginAvaiable()) {
            LogUtil.a("HwVoiceKitManager", "trigger load HiAiPluging download");
            bzo.c().b(null);
        }
    }
}
