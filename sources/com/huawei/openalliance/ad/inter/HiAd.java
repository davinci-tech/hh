package com.huawei.openalliance.ad.inter;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.ads.VideoConfiguration;
import com.huawei.hms.ads.consent.inter.Consent;
import com.huawei.hms.pps.EnableServiceResult;
import com.huawei.hms.pps.HwPPS;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.beans.server.AdPreReq;
import com.huawei.openalliance.ad.beans.server.AppConfigRsp;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.ConfigMapKeys;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.RTCMethods;
import com.huawei.openalliance.ad.constant.WhiteListPkgList;
import com.huawei.openalliance.ad.db.bean.SdkCfgSha256Record;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.download.app.interfaces.AutoOpenListener;
import com.huawei.openalliance.ad.download.app.l;
import com.huawei.openalliance.ad.dt;
import com.huawei.openalliance.ad.dynamictemplate.IImageLoader;
import com.huawei.openalliance.ad.es;
import com.huawei.openalliance.ad.ex;
import com.huawei.openalliance.ad.fb;
import com.huawei.openalliance.ad.fc;
import com.huawei.openalliance.ad.fd;
import com.huawei.openalliance.ad.ff;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.fl;
import com.huawei.openalliance.ad.fn;
import com.huawei.openalliance.ad.fx;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ik;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListener;
import com.huawei.openalliance.ad.inter.listeners.AppDownloadListenerV1;
import com.huawei.openalliance.ad.inter.listeners.ExtensionActionListener;
import com.huawei.openalliance.ad.inter.listeners.IAskForUnlockScreen;
import com.huawei.openalliance.ad.inter.listeners.IExSplashCallback;
import com.huawei.openalliance.ad.inter.listeners.IPPSWebEventCallback;
import com.huawei.openalliance.ad.inter.listeners.LandingPageAction;
import com.huawei.openalliance.ad.inter.listeners.LinkedAdListener;
import com.huawei.openalliance.ad.ipc.CallResult;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.jl;
import com.huawei.openalliance.ad.jx;
import com.huawei.openalliance.ad.magazine.inter.listener.IAdInvalidHandler;
import com.huawei.openalliance.ad.magazine.inter.listener.IPPSDownloadService;
import com.huawei.openalliance.ad.media.IMultiMediaPlayingManager;
import com.huawei.openalliance.ad.ms;
import com.huawei.openalliance.ad.net.http.HttpsConfig;
import com.huawei.openalliance.ad.nl;
import com.huawei.openalliance.ad.ov;
import com.huawei.openalliance.ad.utils.ae;
import com.huawei.openalliance.ad.utils.ai;
import com.huawei.openalliance.ad.utils.aj;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bf;
import com.huawei.openalliance.ad.utils.bj;
import com.huawei.openalliance.ad.utils.bl;
import com.huawei.openalliance.ad.utils.ce;
import com.huawei.openalliance.ad.utils.cg;
import com.huawei.openalliance.ad.utils.cr;
import com.huawei.openalliance.ad.utils.ct;
import com.huawei.openalliance.ad.utils.cw;
import com.huawei.openalliance.ad.utils.cx;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dc;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.de;
import com.huawei.openalliance.ad.utils.df;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.i;
import com.huawei.openalliance.ad.utils.m;
import com.huawei.openalliance.ad.utils.x;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public final class HiAd implements IHiAd, com.huawei.openalliance.ad.inter.f {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6964a = "HiAd";
    private static HiAd b;
    private static final byte[] c = new byte[0];
    private IPPSDownloadService D;
    private IAdInvalidHandler E;
    private IAskForUnlockScreen G;
    private Context d;
    private gc e;
    private fx f;
    private LandingPageAction g;
    private IMultiMediaPlayingManager h;
    private IAppDownloadManager j;
    private AppDownloadListener k;
    private AppDownloadListenerV1 l;
    private AutoOpenListener m;
    private IPPSWebEventCallback n;
    private IExSplashCallback o;
    private ExtensionActionListener p;
    private int q;
    private int r;
    private LinkedAdListener s;
    private RequestOptions u;
    private Integer v;
    private AppStatusQuery z;
    private Map<BroadcastReceiver, IntentFilter> i = new HashMap();
    private int t = -1;
    private boolean w = true;
    private boolean x = true;
    private int y = 0;
    private Map<Integer, Boolean> A = new HashMap();
    private Boolean B = null;
    private boolean C = true;
    private WeakReference<Activity> F = null;
    private BroadcastReceiver H = new BroadcastReceiver() { // from class: com.huawei.openalliance.ad.inter.HiAd.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            if (intent == null) {
                return;
            }
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAd.3.1
                @Override // java.lang.Runnable
                public void run() {
                    String action = intent.getAction();
                    for (Map.Entry entry : HiAd.this.i.entrySet()) {
                        BroadcastReceiver broadcastReceiver = (BroadcastReceiver) entry.getKey();
                        IntentFilter intentFilter = (IntentFilter) entry.getValue();
                        if (intentFilter != null && intentFilter.matchAction(action)) {
                            broadcastReceiver.onReceive(context, intent);
                        }
                    }
                }
            });
        }
    };

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    @Deprecated
    public void requestConfig(String str) {
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void syncMediaGrs(String str, String str2) {
        ct.a().b(str2);
        initGrs(str);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setVideoAutoPlayNet(int i) {
        try {
            ho.b(f6964a, "net type: %d", Integer.valueOf(i));
            fh.b(this.d).l(i);
        } catch (Throwable th) {
            ho.c(f6964a, "set net err, %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setRequestConfiguration(RequestOptions requestOptions) {
        this.u = requestOptions;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setPPSWebListener(IPPSWebEventCallback iPPSWebEventCallback) {
        this.n = iPPSWebEventCallback;
        dj.a(new h(this.n));
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setPPSDownloadService(IPPSDownloadService iPPSDownloadService) {
        this.D = iPPSDownloadService;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setOpenWebPageByBrowser(boolean z) {
        this.B = Boolean.valueOf(z);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setOpenLinkStatus(boolean z) {
        this.w = z;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setMultiMediaPlayingManager(IMultiMediaPlayingManager iMultiMediaPlayingManager) {
        this.h = iMultiMediaPlayingManager;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setLandingPageAction(LandingPageAction landingPageAction) {
        this.g = landingPageAction;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setIsPreloadWebView(boolean z) {
        this.C = z;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setImageLoader(IImageLoader iImageLoader) {
        com.huawei.openalliance.ad.dynamictemplate.a.a().a(iImageLoader);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setExtensionActionListener(ExtensionActionListener extensionActionListener) {
        this.p = extensionActionListener;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setExSplashCallback(IExSplashCallback iExSplashCallback, int i, int i2) {
        this.o = iExSplashCallback;
        this.q = i;
        this.r = i2;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setExLinkedAdListener(LinkedAdListener linkedAdListener) {
        this.s = linkedAdListener;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setCustomSSLSocketFactory(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
        HttpsConfig.a(sSLSocketFactory);
        HttpsConfig.a(x509TrustManager);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setCurActivity(Activity activity) {
        if (activity != null) {
            this.F = new WeakReference<>(activity);
        }
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setCountryCode(String str) {
        this.e.o(str);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setConsent(final String str) {
        ho.b(f6964a, "set TCF consent string");
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAd.6
            @Override // java.lang.Runnable
            public void run() {
                ms.a(HiAd.this.d).a(RTCMethods.SET_TCF_CONSENT_STRING, str, null, null);
            }
        });
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setBrowserAppDownloadListener(AppDownloadListenerV1 appDownloadListenerV1) {
        this.l = appDownloadListenerV1;
        dj.a(new f(appDownloadListenerV1));
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setBrand(int i) {
        this.v = Integer.valueOf(i);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setAutoOpenListener(AutoOpenListener autoOpenListener) {
        this.m = autoOpenListener;
        dj.a(new e(autoOpenListener));
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setAskForUnlockScreen(IAskForUnlockScreen iAskForUnlockScreen) {
        this.G = iAskForUnlockScreen;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setAppStatusQuery(AppStatusQuery appStatusQuery) {
        this.z = appStatusQuery;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setAppInstalledNotify(boolean z) {
        this.x = z;
        ho.a(f6964a, "set app installed notify: %s", Boolean.valueOf(z));
        a(this.x, this.y, MapKeyNames.FULL_SCREEN_NOTIFY);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setAppDownloadListener(AppDownloadListener appDownloadListener) {
        this.k = appDownloadListener;
        dj.a(new g(appDownloadListener));
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setAppAutoOpenForbidden(boolean z) {
        ho.b(f6964a, "set app AutoOpenForbidden: " + z);
        this.e.e(z);
        com.huawei.openalliance.ad.download.ag.f.a(this.d, z, new c(), String.class);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setAppActivateStyle(int i) {
        this.y = i;
        ho.a(f6964a, "set app activate style: %s", Integer.valueOf(i));
        a(this.x, this.y, MapKeyNames.ACTIVATE_NOTIFY_STYLE);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void setAdInvalidHandler(IAdInvalidHandler iAdInvalidHandler) {
        this.E = iAdInvalidHandler;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void placementAdPreCfgReq() {
        final String bM = this.e.bM();
        m.b(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAd.7
            @Override // java.lang.Runnable
            public void run() {
                HiAd.this.f.a(bM);
                ho.a(HiAd.f6964a, "placementAdPreCfgReq slotId is: %s", bM);
            }
        });
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void onForeground() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAd.4
            @Override // java.lang.Runnable
            public void run() {
                ce.a(HiAd.this.d).a();
                dt.h().j();
                HiAd hiAd = HiAd.this;
                hiAd.c(hiAd.d);
            }
        });
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void onBackground() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAd.5
            @Override // java.lang.Runnable
            public void run() {
                ce.a(HiAd.this.d).b();
                dt.h().i();
            }
        });
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public Boolean o() {
        return this.B;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void notifyUiModeChange(int i) {
        try {
            ho.b(f6964a, "ui mode: %d", Integer.valueOf(i));
            fh.b(this.d).m(i);
            Intent intent = new Intent(Constants.UI_CHANGE_ACTION);
            intent.setPackage(this.d.getPackageName());
            this.d.sendBroadcast(intent);
        } catch (Throwable th) {
            ho.c(f6964a, "set ui mode err, %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public boolean n() {
        if (!x.t(this.d)) {
            return false;
        }
        boolean aj = fh.b(this.d).aj();
        ho.b(f6964a, "isSetSurfaceFirst: %s", Boolean.valueOf(aj));
        return aj;
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public boolean m() {
        return this.C;
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public AppStatusQuery l() {
        return this.z;
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public boolean k() {
        return this.w;
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public Integer j() {
        return this.v;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public boolean isVideoCacheWhenPlay(int i) {
        if (this.A.containsKey(Integer.valueOf(i))) {
            return this.A.get(Integer.valueOf(i)).booleanValue();
        }
        return false;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public boolean isEnableUserInfo() {
        if (ao.b(this.d)) {
            return this.e.aL();
        }
        return false;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public boolean isAppInstalledNotify() {
        return this.x;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public boolean isAppAutoOpenForbidden() {
        return this.e.bX();
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void initOptions(Context context) {
        m.b(new d(context));
        u();
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void initLog(boolean z, int i, String str) {
        if (ao.b(this.d) && z) {
            bj.a(this.d, i, str);
        }
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void initLog(boolean z, int i) {
        initLog(z, i, null);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void initGrs(String str, String str2) {
        if (!bz.b(this.d)) {
            ct.a().a(str2);
        }
        initGrs(str);
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void initGrs(String str) {
        ct.a().c(str);
        if (aj.a()) {
            ct.a().a(this.d);
        }
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void informReady() {
        com.huawei.openalliance.ad.inter.d.a(this.d).b();
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public int i() {
        return this.r;
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public int h() {
        return this.q;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public Map<String, List<String>> getTemplates(final List<String> list) {
        Map<String, List<String>> map = (Map) dc.b(new Callable<Map<String, List<String>>>() { // from class: com.huawei.openalliance.ad.inter.HiAd.9
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Map<String, List<String>> call() {
                return fl.a(HiAd.this.d).d(list);
            }
        });
        if (!bl.a(map)) {
            return map;
        }
        ho.b(f6964a, "slotTemplates is empty.");
        return null;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public RequestOptions getRequestConfiguration() {
        if (this.u == null) {
            this.u = new RequestOptions.Builder().build();
        }
        return this.u;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public IPPSDownloadService getPPSDownloadService() {
        return this.D;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public ExtensionActionListener getExtensionActionListener() {
        return this.p;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public String getEngineVer() {
        String a2 = com.huawei.openalliance.ad.e.a();
        ho.b(f6964a, "engineVer: %s", a2);
        return a2;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public Activity getCurActivity() {
        WeakReference<Activity> weakReference = this.F;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public IAskForUnlockScreen getAskForUnlockScreen() {
        return this.G;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public IAppDownloadManager getAppDownloadManager() {
        if (this.j == null) {
            this.j = new l();
        }
        return this.j;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public int getAppActivateStyle() {
        return this.y;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public IAdInvalidHandler getAdInvalidHandler() {
        return this.E;
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public LinkedAdListener g() {
        return this.s;
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public IExSplashCallback f() {
        return this.o;
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void enableVideoCacheWhenPlay(int i, boolean z) {
        ho.b(f6964a, "enableVideoCacheWhenPlay, type: %s, enable: %s", Integer.valueOf(i), Boolean.valueOf(z));
        this.A.put(Integer.valueOf(i), Boolean.valueOf(z));
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void enableUserInfo(boolean z) {
        if (ao.b(this.d)) {
            this.e.b(z);
            a(z);
            if (z) {
                x();
            } else {
                m.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAd.17
                    @Override // java.lang.Runnable
                    public void run() {
                        es.a(HiAd.this.d).e();
                        ex.a(HiAd.this.d).e();
                        fn.a(HiAd.this.d).e();
                        fc.a(HiAd.this.d).e();
                        HiAd.this.z();
                    }
                });
            }
            if (aj.a()) {
                ct.a().a(this.d, z);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.inter.IHiAd
    public void enableSharePd(boolean z) {
        if (ao.b(this.d)) {
            this.e.c(z);
        }
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public boolean e() {
        boolean z = this.t != Process.myPid();
        if (z) {
            this.t = Process.myPid();
        }
        ho.b(f6964a, "isNewProcess:" + z);
        return z;
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public Context d() {
        return this.d;
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public void c(String str) {
        a(str, false, false, true);
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public IMultiMediaPlayingManager c() {
        IMultiMediaPlayingManager iMultiMediaPlayingManager = this.h;
        return iMultiMediaPlayingManager != null ? iMultiMediaPlayingManager : com.huawei.openalliance.ad.media.a.a(this.d);
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public void b(String str) {
        a(str, true, true, true);
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public LandingPageAction b() {
        return this.g;
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public void a(final boolean z) {
        m.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAd.2
            @Override // java.lang.Runnable
            public void run() {
                if (!ov.a()) {
                    ho.c(HiAd.f6964a, "ppskit api is not included");
                } else {
                    HiAd.this.b(z);
                }
            }
        });
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public void a(String str) {
        a(str, true, false, false);
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public void a(final VideoConfiguration videoConfiguration, final boolean z, final boolean z2, final int i) {
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAd.8
            @Override // java.lang.Runnable
            public void run() {
                new com.huawei.openalliance.ad.analysis.c(HiAd.this.d).a(videoConfiguration, z, z2, i);
            }
        });
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public void a(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (broadcastReceiver == null) {
            return;
        }
        this.i.put(broadcastReceiver, intentFilter);
    }

    @Override // com.huawei.openalliance.ad.inter.f
    public void a(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null) {
            return;
        }
        this.i.remove(broadcastReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        A();
        B();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        cg a2 = cg.a(this.d);
        a2.a();
        if (TextUtils.isEmpty(a2.j())) {
            a2.g(x.a(this.d));
        }
        dh.a(this.d, "normal");
        dh.a(this.d, "ar");
    }

    private void x() {
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAd.18
            @Override // java.lang.Runnable
            public void run() {
                HiAd.this.y();
                HiAd.this.r();
                jl.a().a(HiAd.this.d);
            }
        });
    }

    private boolean w() {
        if (!bz.a(this.d).n()) {
            return false;
        }
        ho.a(f6964a, "device ready for refresh config");
        return true;
    }

    private long v() {
        int n;
        if (w() && (n = com.huawei.openalliance.ad.dc.n()) <= 10) {
            com.huawei.openalliance.ad.dc.a(n + 1);
            return 0L;
        }
        int aT = this.e.aT();
        if (bz.a(this.d).m()) {
            ho.a(f6964a, "testDeviceConfigRefreshInterval in use.");
            aT = this.e.A();
        }
        return aT * 60000;
    }

    private void u() {
        m.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAd.14
            @Override // java.lang.Runnable
            public void run() {
                com.huawei.openalliance.ad.analysis.f.a(HiAd.this.d);
                com.huawei.openalliance.ad.e.a(HiAd.this.d);
                com.huawei.openalliance.ad.utils.d.B(HiAd.this.d);
            }
        });
    }

    private void t() {
        if (ao.e()) {
            m.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAd.13
                @Override // java.lang.Runnable
                public void run() {
                    Consent.getInstance(HiAd.this.d).getNpaAccordingToServerConsent();
                }
            });
        }
    }

    private void s() {
        m.b(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAd.12
            @Override // java.lang.Runnable
            public void run() {
                jx.a(HiAd.this.d).c();
                Log.i(HiAd.f6964a, "prerequest.");
                int Z = HiAd.this.e.Z();
                boolean j = x.j(HiAd.this.d);
                ho.b(HiAd.f6964a, "preRequest, type: %s, isTv: %s", Integer.valueOf(Z), Boolean.valueOf(j));
                if (Z == 1 || j) {
                    fb.a(HiAd.this.d).a(new AdPreReq());
                } else if (Z == 2) {
                    new NativeAdLoader(HiAd.this.d, new String[]{ConfigMapKeys.CLCT_PRE_REQ}).loadAds(4, true);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        if (System.currentTimeMillis() - this.e.cj() > 86400000) {
            ms.a(this.d).a(RTCMethods.QUERY_ACCOUNT_INFO, null, new RemoteCallResultCallback<String>() { // from class: com.huawei.openalliance.ad.inter.HiAd.11
                @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
                public void onRemoteCallResult(String str, CallResult<String> callResult) {
                    if (callResult != null) {
                        try {
                            if (callResult.getCode() == 200) {
                                if (ho.a()) {
                                    ho.a(HiAd.f6964a, "query account info from hms success!");
                                }
                                String data = callResult.getData();
                                if (cz.b(data)) {
                                    if (ho.a()) {
                                        ho.a(HiAd.f6964a, "receiveData is null");
                                        return;
                                    }
                                    return;
                                }
                                JSONObject jSONObject = new JSONObject(data);
                                boolean optBoolean = jSONObject.optBoolean(Constants.IS_COPPA);
                                boolean optBoolean2 = jSONObject.optBoolean(Constants.IS_TFUA);
                                if (ho.a()) {
                                    ho.a(HiAd.f6964a, "account info get from hms, coppa is %s, tfua is %s", Boolean.valueOf(optBoolean), Boolean.valueOf(optBoolean2));
                                }
                                cg.a(HiAd.this.d).h(optBoolean);
                                cg.a(HiAd.this.d).i(optBoolean2);
                                return;
                            }
                        } catch (Throwable th) {
                            ho.d(HiAd.f6964a, "get account info from hms err : %s", th.getClass().getSimpleName());
                            return;
                        }
                    }
                    if (ho.a()) {
                        ho.a(HiAd.f6964a, "failed to query account info from hms");
                    }
                }
            }, String.class);
            this.e.n(System.currentTimeMillis());
        } else if (ho.a()) {
            ho.a(f6964a, "query account info frequently");
        }
    }

    private void q() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        ao.a(this.d, this.H, intentFilter);
    }

    public static boolean isOaidAvaiable(Context context) {
        String a2;
        try {
            a2 = com.huawei.openalliance.ad.utils.d.a(context, i.e(context));
        } catch (Throwable th) {
            Log.i(f6964a, "isOaidAvaiable error: " + th.getClass().getSimpleName());
        }
        if (a2 == null) {
            return false;
        }
        return Integer.parseInt(a2) >= 50005300;
    }

    public static IHiAd getInstance(Context context) {
        return b(context);
    }

    public static void disableUserInfo(Context context) {
        Log.i(f6964a, "disableUserInfo, context ".concat(context == null ? "is null" : "not null"));
        if (context == null) {
            return;
        }
        fh.b(context).b(false);
        getInstance(context).enableUserInfo(false);
    }

    private void d(String str) {
        int a2 = fd.a(this.d).a(str);
        if (a2 == 2 || a2 == 3) {
            ho.b(f6964a, "invoke kit req sdk config");
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("slotid", str);
                jSONObject.put("sdk_type", com.huawei.openalliance.ad.utils.d.A(this.d));
                jSONObject.put(MapKeyNames.UI_ENGINE_VERSION, com.huawei.openalliance.ad.e.a());
                ms.a(this.d).a(RTCMethods.REQUEST_SDK_CONFIG, jSONObject.toString(), null, String.class);
            } catch (Throwable th) {
                ho.c(f6964a, "invoke kit req config failed: %s", th.getClass().getSimpleName());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context) {
        boolean v = dd.v(context);
        ho.a(f6964a, "has install permission is: %s", Boolean.valueOf(v));
        com.huawei.openalliance.ad.download.ag.f.b(context.getApplicationContext(), v, new a(), String.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        ho.b(f6964a, "enable service: " + z);
        HwPPS.getInstance(this.d).confirmAgreement(z).addOnCompleteListener(new b());
    }

    private static HiAd b(Context context) {
        HiAd hiAd;
        synchronized (c) {
            if (b == null) {
                b = new HiAd(context);
            }
            hiAd = b;
        }
        return hiAd;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, String str, boolean z2) {
        try {
            long m = z ? this.e.m(str) : this.e.aU();
            long v = v();
            if (!z2 || System.currentTimeMillis() - m > v) {
                AppConfigRsp a2 = this.f.a(str);
                if (a2 != null && a2.responseCode == 0 && 200 == a2.k()) {
                    this.e.a(str, a2, z);
                    SdkCfgSha256Record sdkCfgSha256Record = new SdkCfgSha256Record();
                    sdkCfgSha256Record.a(this.d.getPackageName());
                    sdkCfgSha256Record.b(a2.Y());
                    ff.a(this.d).a(sdkCfgSha256Record);
                    ho.a(f6964a, "sha256 save to databases, sha256 is %s", a2.Y());
                    dh.a(this.d, "normal").b(this.e.bA().longValue());
                    dh.a(this.d, "ar").b(this.e.bA().longValue());
                    d(str);
                } else if (a2 != null && 206 == a2.k()) {
                    if (z) {
                        this.e.a(str, System.currentTimeMillis());
                    } else {
                        this.e.e(System.currentTimeMillis());
                    }
                    this.e.c(str);
                    ho.b(f6964a, "SDK get config is no change");
                }
            }
            if (aj.a()) {
                ct.a().a(this.d);
            }
        } catch (Throwable th) {
            String str2 = f6964a;
            ho.b(str2, "RC get exception");
            ho.a(3, str2, th);
        }
    }

    private void a(boolean z, int i, String str) {
        com.huawei.openalliance.ad.download.ag.f.a(this.d.getApplicationContext(), z, i, str, new a(), String.class);
    }

    private void a(final String str, final boolean z, final boolean z2, boolean z3) {
        if (ao.b(this.d)) {
            if (cz.b(str)) {
                ho.d(f6964a, "adId is empty, please check it!");
            } else if (z3) {
                m.c(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAd.15
                    @Override // java.lang.Runnable
                    public void run() {
                        HiAd.this.a(z2, str, z);
                    }
                });
            } else {
                m.b(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAd.16
                    @Override // java.lang.Runnable
                    public void run() {
                        HiAd.this.a(z2, str, z);
                    }
                });
            }
        }
    }

    public static com.huawei.openalliance.ad.inter.f a(Context context) {
        return b(context);
    }

    public static com.huawei.openalliance.ad.inter.f a() {
        return b;
    }

    private void D() {
        if (WhiteListPkgList.isHwBrowserPkgName(this.d.getPackageName())) {
            ((Application) this.d.getApplicationContext()).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.huawei.openalliance.ad.inter.HiAd.10
                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(Activity activity, Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity) {
                    if (activity != null) {
                        ho.a(HiAd.f6964a, "cur activity is: %s", activity.getClass().getSimpleName());
                        HiAd.this.setCurActivity(activity);
                    }
                }
            });
        }
    }

    private void C() {
        com.huawei.openalliance.ad.download.app.e.a(this.d);
        dt.a(this.d);
    }

    static class a implements RemoteCallResultCallback<String> {
        @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
        public void onRemoteCallResult(String str, CallResult<String> callResult) {
            if (callResult.getCode() == 200) {
                ho.a(HiAd.f6964a, "success: set app installed notify in hms, %s", str);
            } else {
                ho.c(HiAd.f6964a, "error: set app installed notify in hms, %s", str);
            }
        }

        private a() {
        }
    }

    static class b implements OnCompleteListener<EnableServiceResult> {
        @Override // com.huawei.hmf.tasks.OnCompleteListener
        public void onComplete(Task<EnableServiceResult> task) {
            EnableServiceResult result;
            if (task == null || (result = task.getResult()) == null) {
                ho.b(HiAd.f6964a, "service enable result: false");
                return;
            }
            ho.b(HiAd.f6964a, "service enable result: " + result.isResult());
        }

        private b() {
        }
    }

    static class c implements RemoteCallResultCallback<String> {
        @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
        public void onRemoteCallResult(String str, CallResult<String> callResult) {
            if (callResult.getCode() == 200) {
                ho.b(HiAd.f6964a, "set kit AutoOpenForbidden success");
            }
        }

        private c() {
        }
    }

    private void B() {
        String str = cw.g(this.d) + File.separator + Constants.PPS_ROOT_PATH + File.separator;
        if (cz.b(str)) {
            return;
        }
        ae.a(str);
    }

    static class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private Context f6984a;

        @Override // java.lang.Runnable
        public void run() {
            ik.a(this.f6984a);
        }

        public d(Context context) {
            this.f6984a = context;
        }
    }

    /* loaded from: classes9.dex */
    static class e implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final AutoOpenListener f6985a;

        @Override // java.lang.Runnable
        public void run() {
            com.huawei.openalliance.ad.download.app.d.a().a(this.f6985a);
        }

        e(AutoOpenListener autoOpenListener) {
            this.f6985a = autoOpenListener;
        }
    }

    /* loaded from: classes9.dex */
    static class f implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final AppDownloadListenerV1 f6986a;

        @Override // java.lang.Runnable
        public void run() {
            com.huawei.openalliance.ad.download.app.d.a().a(this.f6986a);
        }

        f(AppDownloadListenerV1 appDownloadListenerV1) {
            this.f6986a = appDownloadListenerV1;
        }
    }

    /* loaded from: classes9.dex */
    static class g implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final AppDownloadListener f6987a;

        @Override // java.lang.Runnable
        public void run() {
            com.huawei.openalliance.ad.download.app.d.a().a(this.f6987a);
        }

        g(AppDownloadListener appDownloadListener) {
            this.f6987a = appDownloadListener;
        }
    }

    /* loaded from: classes9.dex */
    static class h implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final IPPSWebEventCallback f6988a;

        @Override // java.lang.Runnable
        public void run() {
            nl.a().a(this.f6988a);
        }

        h(IPPSWebEventCallback iPPSWebEventCallback) {
            this.f6988a = iPPSWebEventCallback;
        }
    }

    private void A() {
        String str = cw.f(this.d) + File.separator + Constants.PPS_ROOT_PATH + File.separator;
        if (!cz.b(str)) {
            ae.a(str);
        }
        dh.a();
    }

    private HiAd(final Context context) {
        this.d = context.getApplicationContext();
        String str = f6964a;
        Log.i(str, "HiAd init");
        q();
        gc b2 = fh.b(this.d);
        this.e = b2;
        boolean aL = b2.aL();
        m.a(new Runnable() { // from class: com.huawei.openalliance.ad.inter.HiAd.1
            @Override // java.lang.Runnable
            public void run() {
                if (bz.b(context)) {
                    IntentFilter intentFilter = new IntentFilter(Constants.ACTION_EXSPLASH_START_LINKED);
                    Intent a2 = ao.a(HiAd.this.d, null, intentFilter, Constants.PERMISSION_PPS_DOWNLOAD, null);
                    if (a2 != null && a2.getAction() != null && a2.getAction().equals(Constants.ACTION_EXSPLASH_START_LINKED)) {
                        Log.d(HiAd.f6964a, "HiAd: getIntent");
                        new com.huawei.openalliance.ad.inter.b(HiAd.this.d).onReceive(HiAd.this.d, a2);
                    }
                    ao.a(HiAd.this.d, new com.huawei.openalliance.ad.inter.b(HiAd.this.d), intentFilter, Constants.PERMISSION_PPS_DOWNLOAD, null);
                }
                com.huawei.openalliance.ad.inter.c.a(HiAd.this.d).b();
                cr.a(HiAd.this.d);
                cx.a(HiAd.this.d);
            }
        });
        this.f = fb.a(this.d);
        df.a(new com.huawei.openalliance.ad.analysis.c(context));
        C();
        if (aL) {
            x();
            bf.a(this.d);
            s();
            t();
            if (!WhiteListPkgList.accInitPkgList(context)) {
                m.b(new d(context));
            }
            ai.c(this.d);
            de.a(context);
        }
        if (!WhiteListPkgList.accInitPkgList(context)) {
            u();
        }
        D();
        Log.i(str, "HiAd init end");
    }
}
