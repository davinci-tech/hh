package com.huawei.ui.main.stories.soical;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.health.utils.vippage.MemberCenterBootPagerHelper;
import com.huawei.health.knit.model.mainpage.MainPageConfig;
import com.huawei.health.knit.model.mainpage.MainPageGroupConfig;
import com.huawei.health.knit.section.listener.BasePageResTrigger;
import com.huawei.health.knit.section.listener.MemberCenterPageResTrigger;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.marketing.views.IViewVisibilityChange;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.trade.datatype.DeviceBenefits;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.scrollview.ScrollViewListener;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.stories.member.MemberTypeSelectActivity;
import com.huawei.ui.main.stories.soical.MemberCenterFragment;
import defpackage.dpx;
import defpackage.dqj;
import defpackage.eab;
import defpackage.eie;
import defpackage.eim;
import defpackage.gpn;
import defpackage.ixx;
import defpackage.jdx;
import defpackage.koq;
import defpackage.nrs;
import defpackage.nrv;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class MemberCenterFragment extends BaseFragment implements View.OnClickListener, ScrollViewListener, IViewVisibilityChange {

    /* renamed from: a, reason: collision with root package name */
    private long f10466a;
    private RelativeLayout d;
    private Context e;
    private long f;
    private RelativeLayout g;
    private KnitFragment h;
    private LinearLayout i;
    private View j;
    private LinearLayout k;
    private BasePageResTrigger l;
    private CustomTitleBar n;
    private boolean c = false;
    private boolean b = false;
    private String m = "";
    private int o = 1;

    @Override // com.huawei.ui.commonui.scrollview.ScrollViewListener
    public void onScrollChanged(HealthScrollView healthScrollView, int i, int i2, int i3, int i4) {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_member_center, viewGroup, false);
        this.j = inflate;
        this.k = (LinearLayout) inflate.findViewById(R.id.social_root_lyt);
        this.n = (CustomTitleBar) this.j.findViewById(R.id.member_title_bar);
        RelativeLayout relativeLayout = (RelativeLayout) this.j.findViewById(R.id.loading_layout);
        this.g = relativeLayout;
        relativeLayout.setVisibility(0);
        this.d = (RelativeLayout) this.j.findViewById(R.id.member_center_error_layout);
        this.e = getActivity();
        this.f10466a = System.currentTimeMillis();
        b();
        h();
        j();
        i();
        m();
        c();
        return this.j;
    }

    public void a(int i) {
        this.o = i;
    }

    public void c(boolean z) {
        this.b = z;
    }

    public void c(String str) {
        this.m = str;
    }

    private void b() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        this.c = arguments.getBoolean("IS_SHOW_TITLE_RETURN_BUTTOM", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (HandlerExecutor.c()) {
            jdx.b(new Runnable() { // from class: com.huawei.ui.main.stories.soical.MemberCenterFragment.3
                @Override // java.lang.Runnable
                public void run() {
                    MemberCenterFragment.this.c();
                }
            });
            return;
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("MemberCenterFragment", "refreshFitnessPlan, getRecommendPlans : planApi is null.");
        } else {
            planApi.getAllFitnessPackage(-1, new UiCallback<List<FitnessPackageInfo>>() { // from class: com.huawei.ui.main.stories.soical.MemberCenterFragment.1
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("MemberCenterFragment", "onFailure errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<FitnessPackageInfo> list) {
                    LogUtil.a("MemberCenterFragment", "onSuccess invoke");
                }
            });
        }
    }

    private void m() {
        this.n.setLeftButtonVisibility(this.c ? 0 : 8);
        this.n.setTitleText(this.e.getString(R.string._2130847720_res_0x7f0227e8));
        if (nrs.a(this.e)) {
            this.n.setTitleSize(this.e.getResources().getDimension(R.dimen._2131365075_res_0x7f0a0cd3));
        } else {
            this.n.setTitleSize(this.e.getResources().getDimension(R.dimen._2131365076_res_0x7f0a0cd4));
        }
        this.n.setTitleTextColor(this.e.getResources().getColor(R.color._2131296952_res_0x7f0902b8));
        this.n.setTitleBarBackgroundColor(ContextCompat.getColor(this.e, R.color._2131296988_res_0x7f0902dc));
        this.n.setRightButtonVisibility(8);
        this.n.setVisibility(0);
        if (this.c) {
            this.n.setTitleMarginStart(this.e.getResources().getDimensionPixelSize(R.dimen._2131362860_res_0x7f0a042c));
        } else {
            this.n.setTitleMarginStart(this.e.getResources().getDimensionPixelSize(R.dimen._2131362635_res_0x7f0a034b));
        }
        this.n.setClickable(true);
        this.n.bringToFront();
        this.h.setScrollViewListener(d());
    }

    private ScrollViewListener d() {
        return new ScrollViewListener() { // from class: com.huawei.ui.main.stories.soical.MemberCenterFragment.2
            @Override // com.huawei.ui.commonui.scrollview.ScrollViewListener
            public void onScrollChanged(HealthScrollView healthScrollView, int i, int i2, int i3, int i4) {
                if (!healthScrollView.canScrollVertically(1) && System.currentTimeMillis() - MemberCenterFragment.this.f > 2000) {
                    MemberCenterFragment.this.f = SystemClock.elapsedRealtime();
                }
                if (i2 <= 0) {
                    MemberCenterFragment.this.n.setTitleTextColor(ContextCompat.getColor(MemberCenterFragment.this.e, R.color._2131296952_res_0x7f0902b8));
                    MemberCenterFragment.this.n.setTitleBarBackgroundColor(ContextCompat.getColor(MemberCenterFragment.this.e, R.color._2131296988_res_0x7f0902dc));
                } else {
                    MemberCenterFragment.this.n.setTitleTextColor(ContextCompat.getColor(MemberCenterFragment.this.e, R.color._2131299236_res_0x7f090ba4));
                    MemberCenterFragment.this.n.setTitleBarBackgroundColor(ContextCompat.getColor(MemberCenterFragment.this.e, R.color._2131296690_res_0x7f0901b2));
                }
                if (MemberCenterFragment.this.getUserVisibleHint()) {
                    MemberCenterFragment.this.d(healthScrollView);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HealthScrollView healthScrollView) {
        int scrollY = healthScrollView.getScrollY();
        int height = healthScrollView.getHeight();
        int paddingTop = healthScrollView.getPaddingTop();
        if (healthScrollView.getChildAt(0).getHeight() != ((scrollY + height) - paddingTop) - healthScrollView.getPaddingBottom() || System.currentTimeMillis() - this.f <= 1000) {
            return;
        }
        LogUtil.a("MemberCenterFragment", "scrolled to bottom");
        HashMap hashMap = new HashMap();
        hashMap.put("scroll", "1");
        hashMap.put(CommonUtil.PAGE_TYPE, 2);
        ixx.d().d(BaseApplication.e(), AnalyticsValue.HEALTH_DISCOVER_SCROLL_TO_BOTTOM_2020014.value(), hashMap, 0);
        ixx.d().c(BaseApplication.e());
        this.f = System.currentTimeMillis();
    }

    private void i() {
        LogUtil.a("MemberCenterFragment", "initKnitFragment");
        this.i = (LinearLayout) this.j.findViewById(R.id.knit_fragment_container);
        if (this.h != null) {
            return;
        }
        KnitFragment a2 = a();
        this.h = a2;
        if (a2 == null) {
            LogUtil.b("MemberCenterFragment", "knitFragment is null");
            return;
        }
        a2.setIsHideLoadingOnTimeout(false);
        this.h.setLoadingCallback(new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.soical.MemberCenterFragment.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("MemberCenterFragment", "loading callback: ", Integer.valueOf(i));
                MemberCenterFragment.this.b(i);
            }
        });
        getChildFragmentManager().beginTransaction().add(R.id.knit_fragment_container, this.h).commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.main.stories.soical.MemberCenterFragment.5
            @Override // java.lang.Runnable
            public void run() {
                if (!health.compact.a.CommonUtil.aa(MemberCenterFragment.this.e)) {
                    MemberCenterFragment.this.l();
                } else {
                    MemberCenterFragment.this.k();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        nsy.cMA_(this.k, 0);
        nsy.cMA_(this.d, 8);
        nsy.cMA_(this.g, 8);
        if (this.b) {
            HandlerExecutor.a(new Runnable() { // from class: rwe
                @Override // java.lang.Runnable
                public final void run() {
                    MemberCenterFragment.this.e();
                }
            });
            this.b = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public void e() {
        LogUtil.a("MemberCenterFragment", "jumpTypeSelectActivity: ");
        Intent intent = new Intent(this.e, (Class<?>) MemberTypeSelectActivity.class);
        intent.putExtra("memberTypeSelectUri", this.m);
        if (!isAdded()) {
            LogUtil.b("MemberCenterFragment", "jumpTypeSelectActivity mHost is null");
        } else {
            startActivity(intent);
            dpx.a(this.e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        nsy.cMA_(this.k, 4);
        nsy.cMA_(this.g, 8);
        nsy.cMA_(this.d, 0);
    }

    private void h() {
        final MemberCenterBootPagerHelper memberCenterBootPagerHelper = new MemberCenterBootPagerHelper(this.e);
        this.k.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.huawei.ui.main.stories.soical.MemberCenterFragment.6
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(final View view) {
                view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.main.stories.soical.MemberCenterFragment.6.5
                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                    public void onGlobalLayout() {
                        if (eie.alG_(MemberCenterFragment.this.k)) {
                            LogUtil.a("MemberCenterFragment", "showBootPage enter.");
                            memberCenterBootPagerHelper.showBootPage();
                        }
                        view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
                LogUtil.a("MemberCenterFragment", "layout detached from window");
                MemberCenterFragment.this.k.removeOnAttachStateChangeListener(this);
            }
        });
    }

    private void j() {
        nsy.cMn_(this.d, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.soical.MemberCenterFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.o()) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.a("MemberCenterFragment", "click error layout");
                ObserverManagerUtil.c("REFRESH_DISCOVER_FRAGMENT", new Object[0]);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private KnitFragment a() {
        MainPageGroupConfig c2 = eab.c(getContext(), "MainPageSectionsConfig.json");
        if (c2 == null) {
            LogUtil.b("MemberCenterFragment", "addSubTagFragments cause pageGroupConfig is null!");
            return null;
        }
        ArrayList<MainPageConfig> pagesConfig = c2.getPagesConfig();
        if (koq.b(pagesConfig)) {
            LogUtil.b("MemberCenterFragment", "addSubTagFragments cause pageConfigs is empty!");
            return null;
        }
        for (int i = 0; i < pagesConfig.size(); i++) {
            MainPageConfig mainPageConfig = pagesConfig.get(i);
            if (mainPageConfig == null) {
                LogUtil.b("MemberCenterFragment", "addSubTagFragments cause pageConfig1 is null!");
            } else {
                int pageType = mainPageConfig.getPageType();
                int resPosId = mainPageConfig.getResPosId();
                String a2 = nrv.a(mainPageConfig);
                if (pageType == 31) {
                    MemberCenterPageResTrigger memberCenterPageResTrigger = new MemberCenterPageResTrigger(getContext(), resPosId, null);
                    this.l = memberCenterPageResTrigger;
                    return KnitFragment.newInstance(a2, memberCenterPageResTrigger);
                }
            }
        }
        return null;
    }

    @Override // com.huawei.health.marketing.views.IViewVisibilityChange
    public void onVisibilityChange(boolean z) {
        LogUtil.a("MemberCenterFragment", "onVisibilityChange: ", Boolean.valueOf(z));
        if (z) {
            eim.e(new c(this));
            this.f10466a = System.currentTimeMillis();
        }
        if (z || this.f10466a == 0) {
            return;
        }
        g();
    }

    static class c implements IBaseResponseCallback {
        private WeakReference<MemberCenterFragment> d;

        public c(MemberCenterFragment memberCenterFragment) {
            this.d = new WeakReference<>(memberCenterFragment);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            MemberCenterFragment memberCenterFragment = this.d.get();
            if (memberCenterFragment == null) {
                LogUtil.h("MemberCenterFragment", "MemberOuterCallback is null");
                return;
            }
            boolean c = (i == 0 && koq.e(obj, DeviceBenefits.class)) ? gpn.c((List<DeviceBenefits>) obj) : false;
            LogUtil.a("MemberCenterFragment", "onVisibilityChange hasMemberEquity", Boolean.valueOf(c));
            dqj.e(c);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException unused) {
                LogUtil.b("MemberCenterFragment", "InterruptedException");
            }
            dqj.d(memberCenterFragment.e, memberCenterFragment.o);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            this.f10466a = System.currentTimeMillis();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            g();
        }
    }

    private void g() {
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("MemberCenterFragment", "setStayEvent: ", Long.valueOf(this.f10466a), " ", Long.valueOf(currentTimeMillis));
        dqj.e(this.f10466a, currentTimeMillis);
    }
}
