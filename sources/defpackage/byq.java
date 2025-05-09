package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.BuildConfig;
import com.huawei.health.HealthApplication;
import com.huawei.health.MainActivity;
import com.huawei.health.R;
import com.huawei.health.utils.MainInteractorsUtils;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.beans.inner.CountryCodeBean;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.HiAdSplash;
import com.huawei.openalliance.ad.inter.IHiAd;
import com.huawei.openalliance.ad.inter.listeners.AdActionListener;
import com.huawei.openalliance.ad.inter.listeners.AdListener;
import com.huawei.openalliance.ad.inter.listeners.ExtensionActionListener;
import com.huawei.openalliance.ad.views.PPSSplashView;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class byq {
    private static boolean c = false;
    private static IHiAd d;

    /* renamed from: a, reason: collision with root package name */
    private View f545a;
    private MainActivity e;
    private boolean f;
    private LinearLayout h;
    private boolean g = false;
    private boolean b = false;
    private Handler i = new a(this);

    public byq(MainActivity mainActivity) {
        this.e = mainActivity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.i.removeMessages(1002);
        this.i.removeMessages(1001);
        if (this.f) {
            return;
        }
        this.f = true;
        LogUtil.a("Login_HiAdSplashHelper", "jump into application, ", this);
        qbd.a(false);
        if (this.h != null) {
            RelativeLayout relativeLayout = (RelativeLayout) this.e.findViewById(R.id.hw_health_main_layout);
            if (relativeLayout == null) {
                ReleaseLogUtil.d("R_Login_HiAdSplashHelper", "jump, hw_health_main_layout is null");
            } else {
                relativeLayout.removeView(this.h);
            }
        }
        this.e.e(8);
        ReleaseLogUtil.e("R_Login_HiAdSplashHelper", "jump, hide start page");
        if (this.b) {
            LogUtil.a("Login_HiAdSplashHelper", "Ad has been clicked");
            i();
            o();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        IHiAd iHiAd;
        boolean z = this.g;
        if (z && (iHiAd = d) != null) {
            iHiAd.informReady();
            k();
        } else if (z && c) {
            k();
        } else {
            this.i.removeMessages(1002);
            this.i.sendEmptyMessageDelayed(1002, 50L);
        }
    }

    public void f() {
        this.g = true;
    }

    public static boolean c(Context context, byv byvVar) {
        boolean z = false;
        if (byvVar == null) {
            ReleaseLogUtil.d("R_Login_HiAdSplashHelper", "checkShowAds false, param is null");
            return false;
        }
        if (!LoginInit.getInstance(context).getIsLogined() || Utils.o() || CommonUtil.bu() || CommonUtil.cc()) {
            ReleaseLogUtil.e("R_Login_HiAdSplashHelper", "checkShowAds false, not login or oversea or storeDemo or test version");
            return false;
        }
        if (byvVar.a() != -1) {
            ReleaseLogUtil.e("R_Login_HiAdSplashHelper", "checkShowAds false, starting secondary page");
            return false;
        }
        if (!gok.b().d()) {
            ReleaseLogUtil.e("R_Login_HiAdSplashHelper", "checkShowAds false, language update failed");
            return false;
        }
        if (byvVar.d()) {
            ReleaseLogUtil.e("R_Login_HiAdSplashHelper", "checkShowAds false, starting secondary page");
            return false;
        }
        if (byvVar.c()) {
            ReleaseLogUtil.e("R_Login_HiAdSplashHelper", "checkShowAds false, starting secondary page");
            return false;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()).isKidAccount()) {
            ReleaseLogUtil.e("R_Login_HiAdSplashHelper", "checkShowAds false, kid account");
            return false;
        }
        if (!"health_app_first_start".equals(SharedPreferenceManager.b(context, Integer.toString(10005), "health_guide_page"))) {
            ReleaseLogUtil.e("R_Login_HiAdSplashHelper", "checkShowAds false, kid account");
            return false;
        }
        if (n()) {
            ReleaseLogUtil.e("R_Login_HiAdSplashHelper", "checkShowAds false, kid account");
            return false;
        }
        if (!CommonUtil.bv()) {
            String b2 = SharedPreferenceManager.b(context, Integer.toString(10005), "health_ad_splash_switch");
            if (!TextUtils.isEmpty(b2) && !Boolean.parseBoolean(b2)) {
                ReleaseLogUtil.e("R_Login_HiAdSplashHelper", "checkShowAds false, developer mode");
                return false;
            }
        }
        if ("true".equals(SharedPreferenceManager.b(context, Integer.toString(10000), "honor_widget"))) {
            ReleaseLogUtil.e("R_Login_HiAdSplashHelper", "checkShowAds false, honor widget");
            MainInteractorsUtils.b();
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (b(context) && !byvVar.b()) {
            z = true;
        }
        LogUtil.a("Login_HiAdSplashHelper", "checkShowAds result from sdk: " + z + ", time cost: " + (System.currentTimeMillis() - currentTimeMillis));
        return z;
    }

    private static boolean n() {
        boolean e2 = dzn.e();
        LogUtil.a("Login_HiAdSplashHelper", "checkShowAds isNeedShowPrivacy = ", Boolean.valueOf(e2));
        return e2 || gpn.f();
    }

    public static boolean b(Context context) {
        IHiAd hiAd = HiAd.getInstance(context);
        d = hiAd;
        hiAd.initGrs("com.huawei.health", Utils.o() ? CountryCodeBean.OVERSEAS : "CN");
        d.initLog(true, 4);
        d.enableUserInfo(true);
        boolean isAvailable = HiAdSplash.getInstance(context).isAvailable(h());
        LogUtil.a("Login_HiAdSplashHelper", "query HiAdSplash one isAvailable=", Boolean.valueOf(isAvailable));
        if (!isAvailable) {
            return isAvailable;
        }
        boolean s = CommonUtil.s(context);
        LogUtil.a("Login_HiAdSplashHelper", "query HiAdSplash two isAvailable=", Boolean.valueOf(s));
        if (!s) {
            return s;
        }
        boolean z = !CommonUtil.g(context, "com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity");
        LogUtil.a("Login_HiAdSplashHelper", "query HiAdSplash thr isAvailable=", Boolean.valueOf(z));
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AdSlotParam h() {
        AdSlotParam.Builder builder = new AdSlotParam.Builder();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(BuildConfig.HI_AD_ID);
        builder.setAdIds(arrayList).setDeviceType(4);
        builder.setOrientation(1).setTest(false);
        builder.setRequestOptions(m());
        return builder.build();
    }

    private static RequestOptions m() {
        LogUtil.a("Login_HiAdSplashHelper", "ppsSlotAdSetting");
        gmz d2 = gmz.d();
        String c2 = d2.c(13);
        String c3 = d2.c(14);
        String c4 = d2.c(15);
        int i = (TextUtils.isEmpty(c2) || "true".equals(c2)) ? 0 : 1;
        int i2 = (TextUtils.isEmpty(c3) || "true".equals(c3)) ? 0 : 1;
        int i3 = (TextUtils.isEmpty(c4) || "true".equals(c4)) ? 0 : 1;
        LogUtil.a("Login_HiAdSplashHelper", "initPersonalSwitch personalizedState=", Integer.valueOf(i), " hwPersonalizedState=", Integer.valueOf(i2), " thirdPersonalizedState=", Integer.valueOf(i3));
        return new RequestOptions.Builder().setRequestLocation(false).setNonPersonalizedAd(Integer.valueOf(i)).setHwNonPersonalizedAd(Integer.valueOf(i2)).setThirdNonPersonalizedAd(Integer.valueOf(i3)).setTagForUnderAgeOfPromise(0).setTagForChildProtection(0).setAdContentClassification("A").build();
    }

    public void j() {
        LogUtil.a("Login_HiAdSplashHelper", "showHiAdSplash().");
        this.g = false;
        ViewStub viewStub = (ViewStub) this.e.findViewById(R.id.viewstub_hi_ad_splash);
        if (viewStub == null) {
            LogUtil.h("Login_HiAdSplashHelper", "showHiAdSplash ViewStub is loaded fail.");
            return;
        }
        LinearLayout linearLayout = (LinearLayout) viewStub.inflate();
        this.h = linearLayout;
        this.f545a = linearLayout.findViewById(R.id.hw_health_start_page_icon_and_slogan);
        HiAdSplash.getInstance(BaseApplication.getContext()).setSloganDefTime(2000);
        PPSSplashView pPSSplashView = (PPSSplashView) this.h.findViewById(R.id.splash_ad_view);
        this.h.setOnClickListener(new View.OnClickListener() { // from class: byr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                byq.Ca_(view);
            }
        });
        ((HealthTextView) this.h.findViewById(R.id.splash_hw_copyrights)).setText(sds.a());
        pPSSplashView.setAdSlotParam(h());
        pPSSplashView.setLogo(this.f545a);
        pPSSplashView.setSloganResId(R.color._2131299296_res_0x7f090be0);
        pPSSplashView.setLogoResId(R.mipmap._2131820756_res_0x7f1100d4);
        pPSSplashView.setAdListener(new c(this));
        pPSSplashView.setAdActionListener(new e(this));
        if (d == null) {
            d = HiAd.getInstance(BaseApplication.getContext());
        }
        d.setExtensionActionListener(new b(this));
        pPSSplashView.loadAd();
        BaseDialog.updateShowAdNowStatus(this.e, true);
        this.i.removeMessages(1001);
        this.i.sendEmptyMessageDelayed(1001, PreConnectManager.CONNECT_INTERNAL);
    }

    static /* synthetic */ void Ca_(View view) {
        LogUtil.a("Login_HiAdSplashHelper", "click splashAdView");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void b() {
        LogUtil.a("Login_HiAdSplashHelper", "cleanTimeoutHandler().");
        Handler handler = this.i;
        if (handler == null) {
            LogUtil.a("Login_HiAdSplashHelper", "mTimeoutHandler is null: ");
        } else {
            handler.removeMessages(1001);
            this.i.removeMessages(1002);
        }
    }

    public void g() {
        this.f = true;
    }

    public void c() {
        this.f = false;
        k();
    }

    public void d() {
        b();
    }

    static class c implements AdListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<byq> f546a;

        c(byq byqVar) {
            this.f546a = new WeakReference<>(byqVar);
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AdListener
        public void onAdFailedToLoad(int i) {
            ReleaseLogUtil.d("R_Login_HiAdSplashHelper", "onAdFailedToLoad: ", Integer.valueOf(i));
            boolean unused = byq.c = true;
            HiAdSplash.getInstance(BaseApplication.getContext()).preloadAd(byq.h());
            byq byqVar = this.f546a.get();
            if (byqVar != null) {
                byqVar.o();
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AdListener
        public void onAdLoaded() {
            ReleaseLogUtil.e("R_Login_HiAdSplashHelper", "onAdLoaded");
            if (this.f546a.get() == null) {
                LogUtil.h("Login_HiAdSplashHelper", "onAdLoaded, helper is null");
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AdListener
        public void onAdDismissed() {
            ReleaseLogUtil.e("R_Login_HiAdSplashHelper", "onAdDismissed");
            byq byqVar = this.f546a.get();
            HiAdSplash.getInstance(BaseApplication.getContext()).preloadAd(byq.h());
            if (byqVar != null) {
                byqVar.l();
            } else {
                LogUtil.h("Login_HiAdSplashHelper", "onAdDismissed, helper is null");
            }
        }
    }

    static class e implements AdActionListener {
        private WeakReference<byq> e;

        e(byq byqVar) {
            this.e = new WeakReference<>(byqVar);
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AdActionListener
        public void onAdShowed() {
            LogUtil.a("Login_HiAdSplashHelper", "onAdShowed...");
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.AdActionListener
        public void onAdClick() {
            LogUtil.a("Login_HiAdSplashHelper", "onAdClick...");
            byq byqVar = this.e.get();
            if (byqVar != null) {
                byqVar.b = true;
            }
        }
    }

    static class b implements ExtensionActionListener {
        private WeakReference<byq> d;

        b(byq byqVar) {
            this.d = new WeakReference<>(byqVar);
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.ExtensionActionListener
        public void onPrepare(String str) {
            LogUtil.a("Login_HiAdSplashHelper", "extension prepare");
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.ExtensionActionListener
        public void onDismiss(String str) {
            LogUtil.a("Login_HiAdSplashHelper", "extension onDismiss");
            byq byqVar = this.d.get();
            if (byqVar != null) {
                byqVar.i();
                byqVar.o();
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.ExtensionActionListener
        public void onFail(String str, int i) {
            ReleaseLogUtil.d("R_Login_HiAdSplashHelper", "extension onFail: ", Integer.valueOf(i));
            boolean unused = byq.c = true;
            byq byqVar = this.d.get();
            if (byqVar != null) {
                byqVar.i();
                byqVar.o();
            }
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.ExtensionActionListener
        public void onShow(String str) {
            LogUtil.a("Login_HiAdSplashHelper", "extension onShow");
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.ExtensionActionListener
        public void onClick(String str) {
            LogUtil.a("Login_HiAdSplashHelper", "extension onClick");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        LogUtil.a("Login_HiAdSplashHelper", "updateAdState");
        this.e.h();
        BaseDialog.updateShowAdNowStatus(this.e, false);
        a();
    }

    public static void a() {
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.action.MAIN_AD_FINISH");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogUtil.a("Login_HiAdSplashHelper", "clearHiAd");
        IHiAd iHiAd = d;
        if (iHiAd != null) {
            iHiAd.setExtensionActionListener(null);
            d = null;
        }
        LinearLayout linearLayout = this.h;
        if (linearLayout == null || !(linearLayout.findViewById(R.id.splash_ad_view) instanceof PPSSplashView)) {
            return;
        }
        ((PPSSplashView) this.h.findViewById(R.id.splash_ad_view)).removeAllViews();
    }

    static class a extends BaseHandler<byq> {
        a(byq byqVar) {
            super(byqVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: Cb_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(byq byqVar, Message message) {
            if (message.what != 1002) {
                boolean hasWindowFocus = byqVar.e.hasWindowFocus();
                boolean j = HealthApplication.j();
                LogUtil.a("Login_HiAdSplashHelper", "hasFocus= ", Boolean.valueOf(hasWindowFocus), " isRunForegroud= ", Boolean.valueOf(j));
                if (hasWindowFocus || j) {
                    byqVar.k();
                    return;
                }
                return;
            }
            byqVar.l();
        }
    }
}
