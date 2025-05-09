package com.huawei.ui.main.stories.discover.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.preload.H5PreloadIntervalStrategy;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.health.marketing.request.ColumnRequestUtils;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.h5pro.preload.H5ProPkgPreloadSyncTask;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.scrollview.HealthBottomView;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.discover.fragment.DiscoverFragment;
import com.huawei.ui.main.stories.member.MemberIntroFragment;
import com.huawei.ui.main.stories.soical.BaseSocialFragment;
import com.huawei.ui.main.stories.soical.MemberCenterFragment;
import com.huawei.ui.main.stories.soical.SocialFragmentFactoryApi;
import defpackage.ary;
import defpackage.ase;
import defpackage.eil;
import defpackage.gpn;
import defpackage.gpo;
import defpackage.ixx;
import defpackage.jdx;
import defpackage.koq;
import defpackage.moj;
import defpackage.nsn;
import defpackage.pft;
import health.compact.a.CommonUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class DiscoverFragment extends BaseFragment {
    private Context d;
    private HealthBottomView e;
    private LinearLayout f;
    private HealthViewPager g;
    private List<Fragment> h;
    private View i;
    private OnFitnessStatusChangeCallback m;
    private boolean n;
    private Fragment p;
    private String[] q;
    private BaseSocialFragment r;
    private UserMemberInfo t;
    private Fragment w;
    private Handler j = new Handler(Looper.getMainLooper());
    private boolean o = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f9702a = true;
    private int b = -1;
    private boolean c = true;
    private final Observer l = new Observer() { // from class: com.huawei.ui.main.stories.discover.fragment.DiscoverFragment.1
        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            if (!("com.huawei.plugin.account.logout".equals(str) && DiscoverFragment.this.o && DiscoverFragment.this.i != null) && (!"REFRESH_DISCOVER_FRAGMENT".equals(str) || DiscoverFragment.this.i == null)) {
                return;
            }
            LogUtil.a("UIDV_DiscoverFragment", "refresh page");
            DiscoverFragment.this.h();
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.main.stories.discover.fragment.DiscoverFragment.1.5
                @Override // java.lang.Runnable
                public void run() {
                    DiscoverFragment.this.j();
                }
            });
        }
    };
    private Observer k = new Observer() { // from class: com.huawei.ui.main.stories.discover.fragment.DiscoverFragment.2
        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            if (!koq.e(objArr, 0)) {
                LogUtil.a("UIDV_DiscoverFragment", "null args!");
                return;
            }
            Object obj = objArr[0];
            if (obj instanceof Boolean) {
                boolean booleanValue = ((Boolean) obj).booleanValue();
                LogUtil.a("UIDV_DiscoverFragment", "isNewShowVipPage", Boolean.valueOf(booleanValue));
                if (DiscoverFragment.this.o == booleanValue || DiscoverFragment.this.c) {
                    return;
                }
                DiscoverFragment.this.o = booleanValue;
                HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.main.stories.discover.fragment.DiscoverFragment.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DiscoverFragment.this.j();
                    }
                });
            }
        }
    };
    private final BroadcastReceiver s = new e(this);
    private final SocialFragmentFactoryApi u = (SocialFragmentFactoryApi) Services.a("OperationBundle", SocialFragmentFactoryApi.class);

    public DiscoverFragment() {
    }

    public DiscoverFragment(HealthBottomView healthBottomView) {
        this.e = healthBottomView;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onCreate(bundle);
        ReleaseLogUtil.b("UIDV_DiscoverFragment", "onCreate finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void m() {
        FragmentActivity activity;
        if (this.e != null || (activity = getActivity()) == null || activity.isDestroyed()) {
            return;
        }
        this.e = (HealthBottomView) activity.findViewById(R.id.hw_main_tabs);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        View inflate = layoutInflater.inflate(R.layout.fragment_discover, viewGroup, false);
        this.i = inflate;
        this.f = (LinearLayout) inflate.findViewById(R.id.discover_pager_root_view);
        this.d = getContext();
        this.t = gpn.a();
        l();
        doC_(this.i);
        ThreadPoolManager.d().execute(new Runnable() { // from class: pga
            @Override // java.lang.Runnable
            public final void run() {
                ColumnRequestUtils.getFavoriteData(null);
            }
        });
        m();
        ReleaseLogUtil.b("UIDV_DiscoverFragment", "onCreateView finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
        return this.i;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        if (this.c) {
            setUserVisibleHint(getUserVisibleHint());
        } else {
            ObserverManagerUtil.c("REFRESH_MEMBER_INFO_CARD_VIEW", true);
            ObserverManagerUtil.c("USER_MEMBER_INFO_UPDATE", true);
            f();
        }
        ReleaseLogUtil.b("UIDV_DiscoverFragment", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Context context = this.d;
        if (context == null) {
            return;
        }
        Display defaultDisplay = ((WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getRealMetrics(displayMetrics);
        if (CommonUtil.e(displayMetrics.widthPixels, "DiscoverFragment") || i()) {
            j();
        }
    }

    private boolean i() {
        FragmentActivity activity = getActivity();
        if (activity instanceof BaseActivity) {
            return ((BaseActivity) activity).isHonorFoldableDevice();
        }
        LogUtil.h("UIDV_DiscoverFragment", "activity is not BaseActivity");
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("UIDV_DiscoverFragment", "onDestroy ", this);
        s();
        r();
        this.j.removeCallbacksAndMessages(null);
        if (this.m != null) {
            ary.a().c(this.m, "PLAN_DELETE");
            this.m = null;
        }
        this.d = null;
        this.i = null;
        this.g = null;
        this.h = null;
        this.r = null;
        this.q = null;
        this.w = null;
        ReleaseLogUtil.b("UIDV_DiscoverFragment", "onDestroy");
    }

    private void l() {
        ObserverManagerUtil.d(this.l, "com.huawei.plugin.account.logout");
        ObserverManagerUtil.d(this.l, "REFRESH_DISCOVER_FRAGMENT");
        if (this.f9702a) {
            this.j.postDelayed(new Runnable() { // from class: com.huawei.ui.main.stories.discover.fragment.DiscoverFragment.4
                @Override // java.lang.Runnable
                public void run() {
                    ObserverManagerUtil.d(DiscoverFragment.this.k, "AB_TEST_STRATEGY_SET_SP_DONE");
                    DiscoverFragment.this.n();
                }
            }, 5000L);
            this.f9702a = false;
        }
    }

    private void s() {
        ObserverManagerUtil.c(this.l);
        ObserverManagerUtil.e(this.k, "AB_TEST_STRATEGY_SET_SP_DONE");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        LogUtil.a("UIDV_DiscoverFragment", "registerSwitchAccountReceiver enter");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.plugin.account.login");
        intentFilter.addAction("com.huawei.plugin.account.logout");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(this.s, intentFilter);
        LogUtil.a("UIDV_DiscoverFragment", "registerSwitchAccountReceiver end");
    }

    private void r() {
        try {
            LogUtil.a("UIDV_DiscoverFragment", "unregisterSwitchAccountReceiver mSwitchAccountReceiver != null");
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.s);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("UIDV_DiscoverFragment", "unregisterSwitchAccountReceiver IllegalArgumentException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        UserMemberInfo userMemberInfo = new UserMemberInfo();
        userMemberInfo.setMemberFlag(-1);
        SharedPreferenceManager.e(this.d, Integer.toString(10000), "USER_VIP_INFO_KEY", moj.e(userMemberInfo), new StorageParams());
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        LogUtil.a("UIDV_DiscoverFragment", "setUserVisibleHint:", Boolean.valueOf(z), ",isFirst:", Boolean.valueOf(this.c), ",mDiscoverView:", this.i);
        if (z && this.c && this.i != null) {
            j();
            o();
            this.c = false;
        }
        if (z) {
            q();
            nsn.e(this.d, this.o ? 4 : 2);
        }
        BaseSocialFragment baseSocialFragment = this.r;
        if (baseSocialFragment != null && this.g != null) {
            baseSocialFragment.fragmentVisibleStatus(z);
        }
        if (this.w instanceof MemberIntroFragment) {
            if (z) {
                ObserverManagerUtil.c("USER_MEMBER_INFO_UPDATE", true);
            }
            ((MemberIntroFragment) this.w).onVisibilityChange(z);
        }
        if (this.w instanceof MemberCenterFragment) {
            if (z) {
                ObserverManagerUtil.c("USER_MEMBER_INFO_UPDATE", true);
            }
            ((MemberCenterFragment) this.w).onVisibilityChange(z);
        }
        if (this.i != null) {
            eil.alV_(getActivity().getWindow().getDecorView(), z, 3002, "Discover");
        }
        ObserverManagerUtil.c("MEMBER_PAGE_IS_VISIBLE_TO_USER", Boolean.valueOf(z));
    }

    private void q() {
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            marketingApi.triggerMarketingResourceEvent(new MarketingOption.Builder().setContext(getActivity()).setPageId(16).setTypeId(45).build());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogUtil.a("UIDV_DiscoverFragment", "initView start");
        LinearLayout linearLayout = this.f;
        if (linearLayout == null) {
            return;
        }
        linearLayout.removeAllViews();
        if (this.d == null || getActivity() == null || getActivity().isFinishing()) {
            LogUtil.h("UIDV_DiscoverFragment", "initView Activity is destroyed");
            return;
        }
        HealthViewPager healthViewPager = new HealthViewPager(this.d);
        this.g = healthViewPager;
        healthViewPager.setId(View.generateViewId());
        this.g.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.f.addView(this.g);
        g();
        this.g.setAdapter(new pft(getChildFragmentManager(), this.h, this.q));
        this.g.setIsScroll(false);
        e();
        if (this.c) {
            f();
        }
        LogUtil.a("UIDV_DiscoverFragment", "initView end");
    }

    private void e() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        ixx.d().d(getContext(), AnalyticsValue.HEALTH_DISCOVER_TAB_2020001.value(), hashMap, 0);
    }

    private void g() {
        this.o = gpo.b();
        this.h = new ArrayList();
        BaseSocialFragment baseSocialFragment = this.r;
        if (baseSocialFragment != null) {
            a((Fragment) baseSocialFragment);
        }
        Fragment fragment = this.w;
        if (fragment != null) {
            a(fragment);
        }
        if (CommonUtil.bu()) {
            Fragment storeDemoNewSocialFragment = this.u.getStoreDemoNewSocialFragment();
            this.p = storeDemoNewSocialFragment;
            this.h.add(storeDemoNewSocialFragment);
            LogUtil.a("UIDV_DiscoverFragment", " initFragment, is storeDemo version");
        } else if (!this.o) {
            BaseSocialFragment newSocialFragment = this.u.getNewSocialFragment();
            this.r = newSocialFragment;
            this.h.add(newSocialFragment);
        } else {
            boolean b = b(this.t);
            d();
            Fragment memberCenterFragment = (b || this.n) ? new MemberCenterFragment() : new MemberIntroFragment();
            this.w = memberCenterFragment;
            LogUtil.a("UIDV_DiscoverFragment", " mVipFragment ", memberCenterFragment.getClass());
            t();
            k();
            this.h.add(this.w);
            int i = this.b;
            if (i != -1) {
                this.e.c(i, false);
            } else {
                this.e.c(2, false);
            }
            SharedPreferenceManager.e(this.d, Integer.toString(10000), "show_member_tab", String.valueOf(System.currentTimeMillis()), new StorageParams());
        }
        this.q = new String[]{BaseApplication.getContext().getString(R$string.IDS_settings_recommend)};
    }

    private void a(Fragment fragment) {
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        beginTransaction.remove(fragment);
        beginTransaction.commitAllowingStateLoss();
    }

    private void d() {
        this.n = ase.c();
        if (this.m == null) {
            this.m = new OnFitnessStatusChangeCallback() { // from class: pfz
                @Override // com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback
                public final void onUpdate() {
                    DiscoverFragment.this.b();
                }
            };
            ary.a().e(this.m, "PLAN_DELETE");
        }
    }

    public /* synthetic */ void b() {
        LogUtil.a("UIDV_DiscoverFragment", "finish plan.");
        this.n = false;
        if (this.f != null) {
            HandlerExecutor.e(new Runnable() { // from class: pgb
                @Override // java.lang.Runnable
                public final void run() {
                    DiscoverFragment.this.j();
                }
            });
        }
    }

    public void a() {
        HealthViewPager healthViewPager;
        if (this.r == null || (healthViewPager = this.g) == null) {
            return;
        }
        healthViewPager.setCurrentItem(1);
    }

    private void t() {
        Fragment fragment;
        UserMemberInfo userMemberInfo = this.t;
        if (userMemberInfo == null || (fragment = this.w) == null || !(fragment instanceof MemberIntroFragment)) {
            return;
        }
        if (userMemberInfo.getMemberFlag() == -1) {
            ((MemberIntroFragment) this.w).d(0);
        } else {
            if (this.t.getNowTime() <= this.t.getExpireTime() || this.n) {
                return;
            }
            ((MemberIntroFragment) this.w).d(2);
        }
    }

    private void k() {
        Fragment fragment;
        UserMemberInfo userMemberInfo = this.t;
        if (userMemberInfo == null || (fragment = this.w) == null || !(fragment instanceof MemberCenterFragment)) {
            return;
        }
        if (userMemberInfo != null && userMemberInfo.getAutoRenew() == 1) {
            ((MemberCenterFragment) this.w).a(0);
        } else {
            ((MemberCenterFragment) this.w).a(1);
        }
    }

    public void e(boolean z) {
        HealthViewPager healthViewPager;
        if (!z || this.w == null || (healthViewPager = this.g) == null) {
            return;
        }
        healthViewPager.setCurrentItem(0, false);
        Fragment fragment = this.w;
        if (fragment instanceof MemberIntroFragment) {
            ((MemberIntroFragment) fragment).b();
        }
    }

    private void doC_(View view) {
        View findViewById = view.findViewById(R.id.statusbar_panel);
        if (!CommonUtil.bu()) {
            findViewById.setVisibility(8);
            return;
        }
        Bundle arguments = getArguments();
        if (arguments == null) {
            LogUtil.a("UIDV_DiscoverFragment", "setStatusBarHeight arguments == null");
            return;
        }
        int i = arguments.getInt("statusBarHeight");
        LogUtil.a("UIDV_DiscoverFragment", "setStatusBarHeight statusBarHeight", Integer.valueOf(i));
        if (i != 0) {
            findViewById.setLayoutParams(new LinearLayout.LayoutParams(-1, i));
        } else {
            findViewById.setLayoutParams(new LinearLayout.LayoutParams(-1, nsn.c(this.d, 24.0f)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.o) {
            LogUtil.a("UIDV_DiscoverFragment", "checkVipUser");
            if (HandlerExecutor.c()) {
                jdx.b(new Runnable() { // from class: com.huawei.ui.main.stories.discover.fragment.DiscoverFragment.5
                    @Override // java.lang.Runnable
                    public void run() {
                        DiscoverFragment.this.f();
                    }
                });
            } else {
                ((VipApi) Services.c("vip", VipApi.class)).getVipInfo(new AnonymousClass3());
            }
        }
    }

    /* renamed from: com.huawei.ui.main.stories.discover.fragment.DiscoverFragment$3, reason: invalid class name */
    public class AnonymousClass3 implements VipCallback {
        @Override // com.huawei.health.vip.api.VipCallback
        public void onFailure(int i, String str) {
        }

        AnonymousClass3() {
        }

        @Override // com.huawei.health.vip.api.VipCallback
        public void onSuccess(Object obj) {
            LogUtil.a("UIDV_DiscoverFragment", "checkVipUser onSuccess: mUserMemberInfo ", DiscoverFragment.this.t);
            if (obj instanceof UserMemberInfo) {
                ObserverManagerUtil.c("USER_MEMBER_INFO_UPDATE", obj);
                if (DiscoverFragment.this.t == null || DiscoverFragment.this.a(obj)) {
                    DiscoverFragment.this.t = (UserMemberInfo) obj;
                    LogUtil.a("UIDV_DiscoverFragment", "checkVipUser refresh result ", obj);
                    HandlerExecutor.e(new Runnable() { // from class: pgc
                        @Override // java.lang.Runnable
                        public final void run() {
                            DiscoverFragment.AnonymousClass3.this.a();
                        }
                    });
                }
            }
        }

        public /* synthetic */ void a() {
            DiscoverFragment.this.j();
        }
    }

    private boolean b(Object obj) {
        if ((obj instanceof UserMemberInfo) && !LoginInit.getInstance(this.d).isBrowseMode()) {
            UserMemberInfo userMemberInfo = (UserMemberInfo) obj;
            if (userMemberInfo.getMemberFlag() == 1 && !gpn.d(userMemberInfo)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(Object obj) {
        if (!this.o) {
            return false;
        }
        if ((this.w instanceof MemberIntroFragment) && b(obj)) {
            return true;
        }
        return (!(this.w instanceof MemberCenterFragment) || b(obj) || this.n) ? false : true;
    }

    private void o() {
        H5ProPkgPreloadSyncTask.startTask(BaseApplication.getContext(), "com.huawei.health.h5.my-annual-flag", new H5PreloadIntervalStrategy(3600000L));
    }

    public void c(int i) {
        this.b = i;
    }

    static class e extends BroadcastReceiver {
        private final WeakReference<DiscoverFragment> c;

        e(DiscoverFragment discoverFragment) {
            this.c = new WeakReference<>(discoverFragment);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("UIDV_DiscoverFragment", "mSwitchAccountReceiver  onReceive");
            if (intent == null) {
                LogUtil.h("UIDV_DiscoverFragment", "login receive.intent null.");
                return;
            }
            final DiscoverFragment discoverFragment = this.c.get();
            if (discoverFragment == null) {
                LogUtil.h("UIDV_DiscoverFragment", "login receive fragment null.");
            } else {
                if (!"com.huawei.plugin.account.login".equals(intent.getAction()) || discoverFragment.i == null || discoverFragment.c) {
                    return;
                }
                HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.main.stories.discover.fragment.DiscoverFragment.e.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ObserverManagerUtil.c("REFRESH_MEMBER_INFO_CARD_VIEW", true);
                        ObserverManagerUtil.c("USER_MEMBER_INFO_UPDATE", true);
                        discoverFragment.f();
                        discoverFragment.j();
                    }
                });
            }
        }
    }
}
