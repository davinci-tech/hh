package com.huawei.openalliance.ad.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.hms.ads.uiengine.IRemoteCreator;
import com.huawei.openalliance.ad.activity.PPSRewardActivity;
import com.huawei.openalliance.ad.bv;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.RewardKeys;
import com.huawei.openalliance.ad.constant.RewardMethods;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gk;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.IRewardAd;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.data.RewardEvent;
import com.huawei.openalliance.ad.inter.data.RewardItem;
import com.huawei.openalliance.ad.inter.listeners.INonwifiActionListener;
import com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener;
import com.huawei.openalliance.ad.je;
import com.huawei.openalliance.ad.nl;
import com.huawei.openalliance.ad.np;
import com.huawei.openalliance.ad.oe;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.pf;
import com.huawei.openalliance.ad.pn;
import com.huawei.openalliance.ad.uh;
import com.huawei.openalliance.ad.uj;
import com.huawei.openalliance.ad.uk;
import com.huawei.openalliance.ad.un;
import com.huawei.openalliance.ad.uo;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.au;
import com.huawei.openalliance.ad.utils.av;
import com.huawei.openalliance.ad.utils.aw;
import com.huawei.openalliance.ad.utils.bc;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.uu;
import com.huawei.openalliance.ad.vb;
import com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle;

/* loaded from: classes5.dex */
public class PPSRewardTemplateView extends AutoScaleSizeRelativeLayout implements je.a, IViewLifeCycle, com.huawei.openalliance.ad.views.interfaces.j {

    /* renamed from: a, reason: collision with root package name */
    private je f7941a;
    private Context b;
    private ViewGroup c;
    private com.huawei.openalliance.ad.inter.data.h d;
    private oe e;
    private ContentRecord f;
    private AdLandingPageData g;
    private PPSWebView h;
    private int i;
    private t j;
    private Dialog k;
    private Dialog l;
    private Dialog m;
    private IRewardAdStatusListener n;
    private com.huawei.openalliance.ad.views.interfaces.ab o;
    private av p;
    private aw q;
    private IRemoteCreator r;
    private bv s;
    private int t;
    private int u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void addNonwifiActionListener(INonwifiActionListener iNonwifiActionListener) {
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public AppDownloadButton getAppDownloadButton() {
        return null;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public av getAppointJs() {
        return null;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public boolean h() {
        return false;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.j
    public void k() {
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void removeRewardAdStatusListener() {
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void setContentRecord(ContentRecord contentRecord) {
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void setTemplateErrorListener(com.huawei.openalliance.ad.views.interfaces.ab abVar) {
        this.o = abVar;
    }

    public void setPopUpView(t tVar) {
        this.j = tVar;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void setOrientation(int i) {
        this.i = i;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void setNonwifiDialog(Dialog dialog) {
        this.l = dialog;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.j
    public void setDownloadDialog(Dialog dialog) {
        this.m = dialog;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void resumeView() {
        d(RewardMethods.RESUME_VIEW);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void pauseView() {
        d(RewardMethods.PAUSE_VIEW);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        je jeVar = this.f7941a;
        if (jeVar != null) {
            jeVar.j();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void onEvent(RewardEvent rewardEvent) {
        a(rewardEvent, false);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ho.b("PPSRewardTView", "onDetach");
        je jeVar = this.f7941a;
        if (jeVar != null) {
            jeVar.i();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ho.b("PPSRewardTView", "onAttach");
        je jeVar = this.f7941a;
        if (jeVar != null) {
            jeVar.h();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void m() {
        this.k = null;
        b(RewardMethods.SHOW_CLOSE_CONFIRM);
        resumeView();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void l() {
        this.k = null;
        u();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.j
    public void j() {
        if (this.h == null) {
            ho.b("PPSRewardTView", "webview is null");
            return;
        }
        ho.b("PPSRewardTView", "showWebView");
        if (!this.w) {
            this.h.loadPage();
        }
        this.w = true;
        this.h.onResume();
        this.h.setVisibility(0);
        this.h.bringToFront();
        this.h.setRealOpenTime(ao.c());
        if ("1".equals(this.d.ab())) {
            os.i(this.d.getCtrlSwitchs());
        }
        if (np.a(this.f)) {
            ((PPSRewardActivity) getContext()).setRequestedOrientation(1);
        }
        if (this.y) {
            this.y = false;
            Toast.makeText(getContext(), R.string._2130851170_res_0x7f023562, 0).show();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.j
    public void i() {
        dj.a(new uu(this));
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public WebSettings getWebViewSettings() {
        PPSWebView pPSWebView = this.h;
        if (pPSWebView != null) {
            return pPSWebView.getSettings();
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public t getPopUpView() {
        return this.j;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public Dialog getNonwifiDialog() {
        return this.l;
    }

    public Dialog getDownloadDialog() {
        return this.m;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void g() {
        if (this.h != null && bc.e(this.d.B()) && this.w) {
            this.h.onStop();
        }
        d(RewardMethods.STOP_VIEW);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void f() {
        setNonwifiDialog(null);
        u();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void e() {
        setNonwifiDialog(null);
        this.v = false;
        b("showNonWifiPlay");
        resumeView();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void destroyView() {
        d(RewardMethods.DESTROY_VIEW);
        PPSWebView pPSWebView = this.h;
        if (pPSWebView != null) {
            pPSWebView.destroy();
        }
        Dialog dialog = this.k;
        if (dialog != null) {
            dialog.dismiss();
            this.k = null;
        }
        bv bvVar = this.s;
        if (bvVar != null) {
            bvVar.b();
            this.s = null;
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.j
    public void d() {
        bv bvVar = this.s;
        if (bvVar != null) {
            bvVar.f(RewardMethods.PERFORM_DOWNLOAD, null);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public boolean c(boolean z) {
        String str;
        if (getDownloadDialog() != null) {
            ho.b("PPSRewardTView", "download dialog is displaying.");
            return true;
        }
        if (this.j == null) {
            str = "show download dialog, popupView is null.";
        } else {
            if (!this.d.Z()) {
                ho.b("PPSRewardTView", "show download confirm dialog.");
                boolean a2 = this.j.a();
                if (a2) {
                    pauseView();
                    AlertDialog dialog = this.j.getDialog();
                    this.m = dialog;
                    if (dialog != null) {
                        dialog.setOnCancelListener(new uh(this));
                    }
                    c("127");
                }
                return a2;
            }
            str = "show download dialog, hasRewarded.";
        }
        ho.c("PPSRewardTView", str);
        return false;
    }

    public void c(String str) {
        oe oeVar = this.e;
        if (oeVar != null) {
            oeVar.a(str, this.f);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void c() {
        bv bvVar = this.s;
        if (bvVar != null) {
            bvVar.f(RewardMethods.SHOW_CLOSE, null);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void b(boolean z) {
        if (this.s != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(RewardKeys.IS_FOREGROUND, z);
            this.s.f(RewardMethods.ACTIVITY_SWITCH, bundle);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.j
    public void b(String str) {
        a(str, (String) null);
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void b(long j, int i) {
        ho.b("PPSRewardTView", "onViewPhysicalShowEnd, duration: %s", Long.valueOf(j));
        if (s() || this.s == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putLong("show_duration", j);
        bundle.putInt("show_ratio", i);
        this.s.f(RewardMethods.VIEW_PHY_END, bundle);
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void b() {
        ho.b("PPSRewardTView", "onViewPhyShowStart.");
        if (s()) {
            return;
        }
        t();
        if (this.h != null && this.e.b(this.d) && this.w) {
            this.h.onResume();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void addRewardAdStatusListener(IRewardAdStatusListener iRewardAdStatusListener) {
        if (iRewardAdStatusListener == null) {
            return;
        }
        this.n = iRewardAdStatusListener;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.j
    public boolean a(int i, MaterialClickInfo materialClickInfo) {
        oe oeVar = this.e;
        if (oeVar != null) {
            return oeVar.a(i, materialClickInfo);
        }
        return false;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void a(boolean z, String str) {
        if (getDownloadDialog() != null) {
            getDownloadDialog().dismiss();
            a(RewardMethods.SHOW_DOWNLOAD_CONFIRM, str);
            if (z) {
                resumeView();
            }
            setDownloadDialog(null);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void a(boolean z) {
        if (this.s == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean(RewardKeys.IS_WIFI, z);
        bundle.putBoolean(RewardKeys.AD_SHOW_MOBILE_ALERT, this.d.ad());
        bundle.putBoolean(RewardKeys.NEED_REMIND_DATA, this.v);
        this.s.f(RewardMethods.NET_CHANGE, bundle);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.j
    public void a(String str, RewardItem rewardItem) {
        if (this.e == null) {
            ho.c("PPSRewardTView", "notify reward, rewardPresenter is null");
            return;
        }
        com.huawei.openalliance.ad.inter.data.h hVar = this.d;
        if (hVar != null && rewardItem != null) {
            hVar.a(rewardItem);
        }
        this.e.b(str, this.d, this.n);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void a(String str) {
        if (this.d == null || this.b == null || getPopUpView() == null || TextUtils.isEmpty(str)) {
            ho.c("PPSRewardTView", "invalid parameter");
            return;
        }
        setContentRecord(pn.a(this.d));
        getPopUpView();
        t.a(this.b, str, this.f);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void a(Integer num) {
        com.huawei.openalliance.ad.inter.data.h hVar;
        if (num == null || (hVar = this.d) == null || this.e == null) {
            return;
        }
        int minEffectiveShowRatio = hVar.getMinEffectiveShowRatio();
        long minEffectiveShowTime = this.d.getMinEffectiveShowTime();
        boolean a2 = com.huawei.openalliance.ad.utils.c.a(this.d.d(), num);
        if (!this.d.Q() || (a2 && !this.d.b())) {
            ho.b("PPSRewardTView", "reportAdShowEvent, source: %s", num);
            oe oeVar = this.e;
            if (oeVar != null) {
                oeVar.a(minEffectiveShowTime, minEffectiveShowRatio, num);
            }
            if (a2) {
                this.d.a(true);
            }
            if (this.d.Q()) {
                return;
            }
            this.d.g(true);
        }
    }

    public void a(RewardEvent rewardEvent, boolean z) {
        if (this.s != null) {
            Bundle bundle = new Bundle();
            bundle.putString("event_type", rewardEvent.a());
            bundle.putBoolean(RewardKeys.CLOSE_BTN_CLICKED, z);
            this.s.f(RewardMethods.ON_EVENT, bundle);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void a(IRewardAd iRewardAd, boolean z) {
        if (iRewardAd instanceof com.huawei.openalliance.ad.inter.data.h) {
            if (this.r == null) {
                ho.c("PPSRewardTView", "remote creator is null");
                com.huawei.openalliance.ad.views.interfaces.ab abVar = this.o;
                if (abVar != null) {
                    abVar.a(-4);
                    return;
                }
                return;
            }
            com.huawei.openalliance.ad.inter.data.h hVar = (com.huawei.openalliance.ad.inter.data.h) iRewardAd;
            this.d = hVar;
            ContentRecord a2 = pn.a(hVar);
            this.f = a2;
            this.g = new AdLandingPageData(a2, this.b, true);
            this.e.c(this.d);
            bv bvVar = new bv(this.b, hVar, this);
            this.s = bvVar;
            View a3 = this.e.a(this.r, bvVar, this.f, this.d);
            if (a3 != null) {
                addView(a3);
                dd.a(this.b, this.i, this.c);
                a(hVar);
                n();
                return;
            }
            ho.c("PPSRewardTView", "remote view is null.");
            com.huawei.openalliance.ad.views.interfaces.ab abVar2 = this.o;
            if (abVar2 != null) {
                abVar2.a(-3);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void a(long j, int i) {
        bv bvVar;
        if (s() || (bvVar = this.s) == null) {
            return;
        }
        bvVar.f(RewardMethods.VIEW_END_RECORD, null);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.j
    public void a(int i, Bundle bundle) {
        int i2;
        if (this.n == null) {
            ho.c("PPSRewardTView", "onStatusChange listener empty");
            return;
        }
        ho.b("PPSRewardTView", "onStatus change: %s", Integer.valueOf(i));
        if (i == 1) {
            this.n.onAdShown();
            return;
        }
        if (i == 2) {
            this.n.onAdClicked();
            return;
        }
        if (i == 3) {
            this.n.onAdCompleted();
            return;
        }
        if (i == 4) {
            this.n.onAdClosed();
            return;
        }
        if (i != 6) {
            ho.b("PPSRewardTView", "on status change, fall to default.");
            return;
        }
        int i3 = -1;
        if (bundle != null) {
            gk gkVar = new gk(bundle);
            int a2 = gkVar.a("error_code", -1);
            i2 = gkVar.a("error_extra", -1);
            i3 = a2;
        } else {
            i2 = -1;
        }
        this.n.onAdError(i3, i2);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.j
    public void a(int i) {
        if (this.k == null) {
            if (i == -1) {
                i = this.u;
            }
            Dialog a2 = com.huawei.openalliance.ad.utils.y.a(getContext(), (String) null, getResources().getQuantityString(R.plurals._2130903534_res_0x7f0301ee, i, Integer.valueOf(i)), getResources().getString(R.string._2130851147_res_0x7f02354b), getResources().getString(R.string._2130851146_res_0x7f02354a), new un(this));
            this.k = a2;
            a2.setOnCancelListener(new uo(this));
            pauseView();
        }
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void a() {
        bv bvVar;
        if (s() || (bvVar = this.s) == null) {
            return;
        }
        bvVar.f(RewardMethods.VIEW_START_RECORD, null);
    }

    private void u() {
        bv bvVar = this.s;
        if (bvVar != null) {
            bvVar.f("handleClose", null);
        }
    }

    private void t() {
        long c = ao.c();
        String valueOf = String.valueOf(c);
        ho.b("PPSRewardTView", "updateShowId: %s", valueOf);
        com.huawei.openalliance.ad.inter.data.h hVar = this.d;
        if (hVar != null) {
            hVar.g(false);
            this.d.a(false);
            this.d.h(valueOf);
            this.d.c(c);
        }
        oe oeVar = this.e;
        if (oeVar != null) {
            oeVar.a(valueOf);
        }
        bv bvVar = this.s;
        if (bvVar != null) {
            bvVar.a(c);
            Bundle bundle = new Bundle();
            bundle.putLong(RewardKeys.SHOW_TIME, c);
            this.s.f(RewardMethods.VIEW_PHY_START, bundle);
        }
        AdLandingPageData adLandingPageData = this.g;
        if (adLandingPageData == null || this.h == null) {
            return;
        }
        adLandingPageData.c(valueOf);
        this.g.a(c);
        this.h.setAdLandingPageData(this.g);
    }

    private boolean s() {
        if (this.d != null) {
            return false;
        }
        ho.b("PPSRewardTView", "ad is null! please register first");
        return true;
    }

    private void r() {
        aw awVar = new aw(this.b, this.g, new AppDownloadButton(this.b), this.h, new vb(this));
        this.q = awVar;
        awVar.a(1);
        this.h.addJavascriptInterface(this.q, Constants.PPS_JS_NAME);
        this.h.addJavascriptInterface(new au(getContext(), pf.a(this.g)), Constants.LANDING_JS_NAME);
        av avVar = new av(getContext(), pf.a(this.g), this.h);
        this.p = avVar;
        this.h.addJavascriptInterface(avVar, Constants.APPOINT_JS_NAME);
    }

    private void q() {
        PPSWebView pPSWebView = this.h;
        if (pPSWebView != null) {
            pPSWebView.setVisibility(8);
            this.h.setAdLandingPageData(this.g);
            r();
            if (this.e.b(this.d) && this.x) {
                this.h.loadPage();
                this.w = true;
            }
        }
    }

    private void o() {
        ho.b("PPSRewardTView", "init pop-up");
        if (bc.f(this.d.B()) || this.d.getAppInfo() == null) {
            ho.b("PPSRewardTView", "appInfo is null or web, skip init popup");
            return;
        }
        boolean U = fh.b(this.b).U();
        this.j = new t(getContext(), this.i);
        getPopUpView().setAdPopupData(this.g);
        getPopUpView().setPopUpClickListener(new uk(this, U));
        getPopUpView().setDismissListener(new uj(this));
    }

    private void n() {
        if (np.a(this.f)) {
            this.x = false;
            this.h.setWebViewBackgroundColor(getResources().getColor(R.color._2131297924_res_0x7f090684));
            this.h.d();
        }
        if ("4".equals(this.f.ad())) {
            this.x = false;
        }
        q();
        o();
        je jeVar = this.f7941a;
        if (jeVar != null) {
            jeVar.b(this.d.getMinEffectiveShowTime(), this.d.getMinEffectiveShowRatio());
        }
    }

    private void d(String str) {
        bv bvVar = this.s;
        if (bvVar != null) {
            bvVar.f(str, null);
        }
    }

    private void a(String str, String str2) {
        bv bvVar = this.s;
        if (bvVar != null) {
            bvVar.a(str, false, str2);
        }
    }

    private void a(com.huawei.openalliance.ad.inter.data.h hVar) {
        int ac = (int) hVar.ac();
        this.t = ac;
        this.u = np.a(this.b, ac);
        this.x = fh.b(this.b).Y();
        this.y = this.e.c();
    }

    private void a(Context context) {
        this.b = context;
        this.e = new np(context, this);
        this.f7941a = new je(this, this);
        inflate(context, R.layout.hiad_reward_v3_container_layout, this);
        PPSWebView pPSWebView = (PPSWebView) findViewById(R.id.reward_webview);
        this.h = pPSWebView;
        pPSWebView.setPPSWebEventCallback(nl.a());
        this.c = (ViewGroup) findViewById(R.id.reward_layout);
        this.r = com.huawei.openalliance.ad.e.a(context.getApplicationContext());
    }

    public PPSRewardTemplateView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.i = 1;
        this.v = true;
        this.w = false;
        this.x = true;
        this.y = false;
        a(context);
    }

    public PPSRewardTemplateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = 1;
        this.v = true;
        this.w = false;
        this.x = true;
        this.y = false;
        a(context);
    }

    public PPSRewardTemplateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = 1;
        this.v = true;
        this.w = false;
        this.x = true;
        this.y = false;
        a(context);
    }

    public PPSRewardTemplateView(Context context) {
        super(context);
        this.i = 1;
        this.v = true;
        this.w = false;
        this.x = true;
        this.y = false;
        a(context);
    }
}
