package com.huawei.ui.main.stories.soical;

import android.bluetooth.BluetoothManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.google.gson.Gson;
import com.huawei.browse.BrowsingBiEvent;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.configuredpage.api.ConfiguredPageApi;
import com.huawei.health.configuredpage.api.ConfiguredPageDataCallback;
import com.huawei.health.knit.model.mainpage.MainPageConfig;
import com.huawei.health.knit.model.mainpage.MainPageGroupConfig;
import com.huawei.health.knit.section.listener.NewSocialPageResTrigger;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.InputBoxTemplate;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.templates.TopBannerNewTemplate;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.operation.utils.OperationWebActivityIntentBuilderApi;
import com.huawei.pluginmessagecenter.provider.data.MessageChangeEvent;
import com.huawei.pluginmessagecenter.service.MessageObserver;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.scrollview.ScrollViewListener;
import com.huawei.ui.commonui.searchview.HealthSearchView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.commonui.view.KakaClaimAnimation;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.stories.soical.NewSocialFragment;
import com.huawei.ui.main.stories.soical.SocialFragmentHelper;
import com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi;
import com.huawei.ui.main.stories.soical.views.DailyTaskRecyclerViewAdapter;
import defpackage.bzs;
import defpackage.bzw;
import defpackage.cdy;
import defpackage.dpf;
import defpackage.eab;
import defpackage.efb;
import defpackage.eie;
import defpackage.fbh;
import defpackage.ixx;
import defpackage.koq;
import defpackage.mde;
import defpackage.mdf;
import defpackage.nrs;
import defpackage.nrv;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.pgq;
import defpackage.rxh;
import defpackage.rxq;
import defpackage.rxu;
import defpackage.rxv;
import defpackage.rxw;
import defpackage.sdi;
import defpackage.sdl;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class NewSocialFragment extends BaseSocialFragment implements View.OnClickListener, ScrollViewListener {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f10470a = new Object();
    private static boolean d = false;
    private rxv aa;
    private rxv ab;
    private InputBoxTemplate ac;
    private DailyTaskRecyclerViewAdapter ad;
    private boolean ae;
    private boolean af;
    private boolean ak;
    private KakaClaimAnimation al;
    private LinearLayout am;
    private boolean an;
    private BaseFragment aq;
    private View as;
    private HealthTextView at;
    private Observer au;
    private INativeAd av;
    private ImageView aw;
    private MessageObserver ax;
    private int bd;
    private HealthSearchView bf;
    private HealthScrollView bg;
    private NewSocialPageResTrigger bh;
    private CustomTitleBar bi;
    private SocialFragmentHelper bj;
    private LinearLayout bk;
    private View bl;
    private RelativeLayout bo;
    private HealthTextView bq;
    private HealthViewPager j;
    private rxq k;
    private HealthButton l;
    private HealthViewPager o;
    private int p;
    private Context r;
    private LinearLayout s;
    private InputBoxTemplate u;
    private CountDownLatch v;
    private c x;
    private HealthDotsPageIndicator y;
    private HealthRecycleView z;
    private long t = 0;
    private long ba = 0;
    private boolean aj = false;
    private long ao = 1000;
    private final List<MessageObject> i = new ArrayList();
    private final List<MessageObject> c = new ArrayList();
    private final List<MessageObject> f = new ArrayList();
    private final List<ImageView> b = new ArrayList();
    private final List<MessageObject> h = new ArrayList();
    private final ArrayList<ImageView> e = new ArrayList<>(3);
    private List<MessageObject> n = new ArrayList();
    private HandlerThread w = null;
    private Handler bm = null;
    private ViewGroup ap = null;
    private RelativeLayout bp = null;
    private boolean ah = false;
    private boolean ai = false;
    private boolean ag = false;
    private NoTitleCustomAlertDialog g = null;
    private rxu m = null;
    private PermissionsResultAction ay = null;
    private final Runnable be = new Runnable() { // from class: rwj
        @Override // java.lang.Runnable
        public final void run() {
            NewSocialFragment.this.b();
        }
    };
    private final OperationWebActivityIntentBuilderApi bb = (OperationWebActivityIntentBuilderApi) Services.a("PluginOperation", OperationWebActivityIntentBuilderApi.class);
    private final OperationInteractorsApi az = (OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class);
    private final ConfiguredPageApi q = (ConfiguredPageApi) Services.a("Main", ConfiguredPageApi.class);
    private final OperationUtilsApi bc = (OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class);
    private MessageCenterApi ar = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);

    public /* synthetic */ void b() {
        this.k.f();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("UIDV_SocialFragment", "Enter onCreateView");
        CommonUtil.u("TimeEat_NewSocialFragmentEnter onCreateView");
        this.ae = true;
        FragmentActivity activity = getActivity();
        this.r = activity;
        this.bj = new SocialFragmentHelper(activity);
        View inflate = layoutInflater.inflate(R.layout.fragment_social_new, viewGroup, false);
        this.bl = inflate;
        this.as = inflate.findViewById(R.id.fragment_social_ad);
        this.s = (LinearLayout) this.bl.findViewById(R.id.discover_knowledge_service);
        HealthScrollView healthScrollView = (HealthScrollView) this.bl.findViewById(R.id.fragment_social_sroll);
        this.bg = healthScrollView;
        healthScrollView.setScrollViewListener(this);
        HealthSearchView healthSearchView = (HealthSearchView) this.bl.findViewById(R.id.social_global_search_view);
        this.bf = healthSearchView;
        nsn.cLD_(healthSearchView);
        this.v = new CountDownLatch(2);
        HealthScrollView healthScrollView2 = (HealthScrollView) this.bl.findViewById(R.id.fragment_social_sroll);
        healthScrollView2.setScrollViewVerticalDirectionEvent(true);
        ScrollUtil.cKx_(healthScrollView2, getActivity().getWindow().getDecorView(), 3002);
        dSu_(this.bl);
        dSv_(this.bl);
        q();
        v();
        HandlerThread handlerThread = new HandlerThread("UIDV_SocialFragment");
        this.w = handlerThread;
        handlerThread.start();
        this.bm = new Handler(this.w.getLooper());
        this.ax = new d(this);
        this.x = new c(this);
        if (CommonUtil.aa(this.r)) {
            this.af = true;
            o();
            n();
            s();
        }
        if (dpf.e()) {
            p();
        } else {
            this.an = false;
            nsy.cMA_(this.bf, 8);
        }
        CommonUtil.u("TimeEat_NewSocialFragmentLeave onCreateView");
        return this.bl;
    }

    private void v() {
        Observer observer = new Observer() { // from class: com.huawei.ui.main.stories.soical.NewSocialFragment.1
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (str == null) {
                    LogUtil.b("UIDV_SocialFragment", "notify label is null");
                    return;
                }
                str.hashCode();
                if (str.equals("NEW_SOCIAL_FRAGMENT_NO_DATA")) {
                    d();
                } else if (str.equals("NEW_SOCIAL_SEARCH_TEMPLATE")) {
                    e(objArr);
                } else {
                    LogUtil.h("UIDV_SocialFragment", "unknown label: ", str);
                }
            }

            private void e(Object... objArr) {
                if (!koq.e(objArr, 0)) {
                    LogUtil.a("UIDV_SocialFragment", "null args!");
                    return;
                }
                if (objArr[0] instanceof InputBoxTemplate) {
                    boolean z = NewSocialFragment.this.ac == null;
                    InputBoxTemplate inputBoxTemplate = (InputBoxTemplate) objArr[0];
                    if (inputBoxTemplate != null) {
                        NewSocialFragment.this.ac = inputBoxTemplate;
                        dpf.b(NewSocialFragment.this.bf, NewSocialFragment.this.ac);
                    }
                    if (z && NewSocialFragment.this.ac != null && NewSocialFragment.this.aj) {
                        fbh.e(NewSocialFragment.this.r, 4001, NewSocialFragment.this.ac);
                    }
                }
            }

            private void d() {
                LogUtil.a("UIDV_SocialFragment", "handleFragmentNoData");
                if (NewSocialFragment.this.ai || NewSocialFragment.this.ag) {
                    return;
                }
                NewSocialFragment.this.ac();
                NewSocialFragment.this.ab();
            }
        };
        this.au = observer;
        ObserverManagerUtil.d(observer, "NEW_SOCIAL_SEARCH_TEMPLATE");
        ObserverManagerUtil.d(this.au, "NEW_SOCIAL_FRAGMENT_NO_DATA");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Object obj) {
        HealthSearchView healthSearchView = this.bf;
        if (healthSearchView != null && (obj instanceof InputBoxTemplate)) {
            if (this.u == null) {
                this.u = (InputBoxTemplate) obj;
            }
            if (TextUtils.isEmpty(healthSearchView.getQueryHint())) {
                dpf.b(this.bf, (InputBoxTemplate) obj);
                if (this.aj) {
                    fbh.e(this.r, 4001, this.u);
                }
            }
            nsy.cMA_(this.bf, 0);
            this.an = true;
        }
    }

    private void q() {
        if (!Utils.o() && !CommonUtil.bu() && !LoginInit.getInstance(this.r).isKidAccount()) {
            sdi.d(new String[]{"h3dkg18g18"}, new rxw(this, this.v));
        } else {
            this.v.countDown();
        }
    }

    private void n() {
        if (CommonUtil.bu()) {
            return;
        }
        if (Utils.o() && !bzs.e().switchToMarketingTwo()) {
            ah();
        } else {
            t();
        }
    }

    private void t() {
        LogUtil.a("UIDV_SocialFragment", "initKnitFragment");
        LinearLayout linearLayout = (LinearLayout) this.bl.findViewById(R.id.knit_fragment_container);
        this.am = linearLayout;
        linearLayout.setVisibility(0);
        if (this.aq != null) {
            return;
        }
        this.aq = l();
        getChildFragmentManager().beginTransaction().add(R.id.knit_fragment_container, this.aq).commit();
    }

    private KnitFragment l() {
        MainPageGroupConfig c2 = eab.c(getContext(), "MainPageSectionsConfig.json");
        if (c2 == null) {
            LogUtil.b("UIDV_SocialFragment", "addSubTagFragments cause pageGroupConfig is null!");
            return null;
        }
        ArrayList<MainPageConfig> pagesConfig = c2.getPagesConfig();
        if (koq.b(pagesConfig)) {
            LogUtil.b("UIDV_SocialFragment", "addSubTagFragments cause pageConfigs is empty!");
            return null;
        }
        for (int i = 0; i < pagesConfig.size(); i++) {
            MainPageConfig mainPageConfig = pagesConfig.get(i);
            if (mainPageConfig == null) {
                LogUtil.b("UIDV_SocialFragment", "addSubTagFragments cause pageConfig1 is null!");
            } else {
                int pageType = mainPageConfig.getPageType();
                int resPosId = mainPageConfig.getResPosId();
                String a2 = nrv.a(mainPageConfig);
                if (pageType == 16) {
                    NewSocialPageResTrigger newSocialPageResTrigger = new NewSocialPageResTrigger(getContext(), resPosId, null);
                    this.bh = newSocialPageResTrigger;
                    return KnitFragment.newInstance(a2, newSocialPageResTrigger);
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dSr_(Message message) {
        SocialFragmentHelper.a aVar;
        if (message == null || (aVar = (SocialFragmentHelper.a) message.obj) == null) {
            return;
        }
        ImageView dSL_ = aVar.dSL_();
        MessageObject e2 = aVar.e();
        this.k.c(aVar.b());
        dSs_(dSL_, e2);
        this.k.dTw_(this.j);
        this.j.getAdapter().notifyDataSetChanged();
        this.k.dTw_(this.o);
        this.o.getAdapter().notifyDataSetChanged();
        if (this.k.d() == 37) {
            this.m.d(this.h.size());
            this.m.onPageScrollStateChanged(0);
        }
        this.k.f();
        this.k.dTw_(this.z);
        this.ad.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dSx_(Message message) {
        if (message.obj != null) {
            a((List<MessageObject>) message.obj);
            if (this.m == null) {
                this.m = new rxu();
            }
            if (this.k.d() == 37) {
                this.m.c(this.j);
                this.k.b(this.m);
                this.j.setPageTransformer(false, this.m);
                this.j.addOnPageChangeListener(this.m);
            }
        }
    }

    private void s() {
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            new Handler().postDelayed(new Runnable() { // from class: rwm
                @Override // java.lang.Runnable
                public final void run() {
                    NewSocialFragment.this.e(marketingApi);
                }
            }, 1000L);
        }
    }

    public /* synthetic */ void e(MarketingApi marketingApi) {
        MarketingOption.Builder builder = new MarketingOption.Builder();
        builder.setContext(getActivity());
        builder.setPageId(16);
        marketingApi.requestMarketingResource(builder.build());
        HashMap hashMap = new HashMap();
        hashMap.put("open_specific_page", "Discover");
        builder.setTriggerEventParams(hashMap);
        builder.setTypeId(49);
        marketingApi.triggerMarketingResourceEvent(builder.build());
        b(marketingApi, builder);
    }

    private void b(MarketingApi marketingApi, MarketingOption.Builder builder) {
        builder.setTypeId(51);
        marketingApi.triggerMarketingResourceEvent(builder.build());
        builder.setTypeId(52);
        marketingApi.triggerMarketingResourceEvent(builder.build());
    }

    private void p() {
        if (this.bf == null || this.bi == null) {
            return;
        }
        r();
        this.bf.setInputType(0);
        this.bf.setIconifiedByDefault(false);
        this.an = true;
        dpf.c(this.bf);
        dpf.Yv_(this.bf, new View.OnClickListener() { // from class: rwh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NewSocialFragment.this.dSz_(view);
            }
        });
        dpf.Yn_(this.x, 4036);
    }

    public /* synthetic */ void dSz_(View view) {
        InputBoxTemplate m = m();
        fbh.d(this.r, 4001, m);
        dpf.a(this.r, m);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void r() {
        CustomTitleBar customTitleBar = this.bi;
        if (customTitleBar == null) {
            return;
        }
        customTitleBar.setRightThirdKeyVisibility(8);
        nsy.cMh_(this.bi.getRightThirdKey(), 0.0f);
        this.bi.setRightThirdKeyBackground(ContextCompat.getDrawable(this.r, R.drawable._2131431370_res_0x7f0b0fca), nsf.h(R.string._2130847322_res_0x7f02265a));
        this.bi.setRightThirdKeyOnClickListener(new View.OnClickListener() { // from class: rwp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NewSocialFragment.this.dSy_(view);
            }
        });
    }

    public /* synthetic */ void dSy_(View view) {
        InputBoxTemplate m = m();
        fbh.d(this.r, 4001, m);
        dpf.a(this.r, m);
        ViewClickInstrumentation.clickOnView(view);
    }

    private InputBoxTemplate m() {
        InputBoxTemplate inputBoxTemplate = this.ac;
        return inputBoxTemplate != null ? inputBoxTemplate : this.u;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        CommonUtil.u("TimeEat_NewSocialFragmentEnter onActivityCreated");
        w();
        CommonUtil.u("TimeEat_NewSocialFragmentLeave onActivityCreated");
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a("UIDV_SocialFragment", "onConfigurationChanged()");
        this.x.sendEmptyMessage(16384);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        this.k.dTw_(this.j);
        HealthViewPager healthViewPager = this.j;
        if (healthViewPager != null && healthViewPager.getAdapter() != null) {
            this.j.getAdapter().notifyDataSetChanged();
        }
        this.k.dTw_(this.o);
        HealthViewPager healthViewPager2 = this.o;
        if (healthViewPager2 != null && healthViewPager2.getAdapter() != null) {
            this.o.getAdapter().notifyDataSetChanged();
        }
        this.k.dTw_(this.z);
        HealthRecycleView healthRecycleView = this.z;
        if (healthRecycleView == null || healthRecycleView.getAdapter() == null) {
            return;
        }
        this.z.getAdapter().notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        this.v = new CountDownLatch(2);
        q();
        o();
    }

    private void i() {
        Task<List<mdf>> taskInfoListByScenario = bzw.e().getTaskInfoListByScenario(getContext(), 1, new int[0]);
        if (taskInfoListByScenario == null) {
            return;
        }
        taskInfoListByScenario.addOnSuccessListener(new a(this)).addOnFailureListener(new OnFailureListener() { // from class: rwo
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                LogUtil.b("UIDV_SocialFragment", "Fetch Kaka List Failed");
            }
        });
    }

    static class a implements OnSuccessListener<List<mdf>> {
        private WeakReference<NewSocialFragment> c;

        a(NewSocialFragment newSocialFragment) {
            this.c = new WeakReference<>(newSocialFragment);
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<mdf> list) {
            NewSocialFragment newSocialFragment = this.c.get();
            if (newSocialFragment == null || !newSocialFragment.isVisible() || newSocialFragment.isRemoving() || newSocialFragment.isDetached()) {
                return;
            }
            newSocialFragment.c(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<mdf> list) {
        int e2;
        if (koq.b(list)) {
            return;
        }
        LogUtil.a("UIDV_SocialFragment", "fetchKakaTasks ", Integer.valueOf(list.size()));
        int size = list.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = list.get(i).ag();
        }
        synchronized (f10470a) {
            for (MessageObject messageObject : this.h) {
                if (messageObject instanceof pgq) {
                    pgq pgqVar = (pgq) messageObject;
                    if (pgqVar.b() && (e2 = sdl.e(iArr, pgqVar.d().ag())) >= 0) {
                        mdf mdfVar = list.get(e2);
                        if (TextUtils.equals(sdl.a(pgqVar.d()), sdl.a(mdfVar)) && sdl.a(mdfVar.p()) && pgqVar.d().ah() <= mdfVar.ah()) {
                            pgqVar.a(mdfVar);
                        }
                    }
                }
            }
            this.aa.notifyDataSetChanged();
            if (this.m != null && this.k.d() == 37) {
                this.m.d(this.h.size());
                this.m.onPageScrollStateChanged(0);
            }
            this.ab.notifyDataSetChanged();
            this.ad.notifyDataSetChanged();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x007d, code lost:
    
        if (r2 == false) goto L25;
     */
    @Override // com.huawei.ui.main.stories.soical.BaseSocialFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void fragmentVisibleStatus(boolean r6) {
        /*
            r5 = this;
            r5.aj = r6
            java.lang.String r0 = "setUserVisibleHint:"
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r6)
            java.lang.String r2 = " isFirst:"
            boolean r3 = r5.ae
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1, r2, r3}
            java.lang.String r1 = "UIDV_SocialFragment"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r0 = 0
            if (r6 == 0) goto L9e
            boolean r1 = r5.ae
            if (r1 != 0) goto L9e
            long r1 = android.os.SystemClock.elapsedRealtime()
            long r3 = r5.ba
            long r1 = r1 - r3
            r3 = 4000(0xfa0, double:1.9763E-320)
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L9e
            android.view.View r1 = r5.bl
            if (r1 != 0) goto L32
            return
        L32:
            java.lang.String r1 = "setUserVisibleHint: start request"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            java.lang.String r2 = "UIDV_SocialFragment"
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            java.lang.Object r1 = com.huawei.ui.main.stories.soical.NewSocialFragment.f10470a
            monitor-enter(r1)
            java.util.List<com.huawei.health.messagecenter.model.MessageObject> r2 = r5.h     // Catch: java.lang.Throwable -> L9b
            int[] r2 = defpackage.rxh.c(r2)     // Catch: java.lang.Throwable -> L9b
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L9b
            int r1 = r2.length
            if (r1 <= 0) goto L7f
            bzw r1 = defpackage.bzw.e()
            android.content.Context r3 = r5.getContext()
            com.huawei.hmf.tasks.Task r1 = r1.checkKakaTaskIsFinished(r3, r2)
            if (r1 == 0) goto L5a
            r2 = 1
            goto L5b
        L5a:
            r2 = r0
        L5b:
            java.lang.String r3 = "groupingKakaRules"
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r2)
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r4}
            java.lang.String r4 = "UIDV_SocialFragment"
            com.huawei.hwlogsmodel.LogUtil.a(r4, r3)
            if (r2 == 0) goto L7d
            rwq r3 = new rwq
            r3.<init>()
            com.huawei.hmf.tasks.Task r1 = r1.addOnSuccessListener(r3)
            rwv r3 = new rwv
            r3.<init>()
            r1.addOnFailureListener(r3)
        L7d:
            if (r2 != 0) goto L82
        L7f:
            r5.u()
        L82:
            boolean r1 = health.compact.a.CommonUtil.bu()
            if (r1 != 0) goto L9e
            boolean r1 = health.compact.a.Utils.o()
            if (r1 == 0) goto L97
            com.huawei.health.configuredpage.api.ConfiguredPageApi r1 = r5.q
            r2 = 16
            android.widget.LinearLayout r3 = r5.s
            r1.refreshConfiguredPageUi(r2, r3)
        L97:
            r5.t()
            goto L9e
        L9b:
            r6 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L9b
            throw r6
        L9e:
            com.huawei.health.marketing.datatype.InputBoxTemplate r1 = r5.m()
            if (r6 == 0) goto Lb1
            boolean r6 = r5.ae
            if (r6 == 0) goto Lb1
            if (r1 == 0) goto Lb1
            android.content.Context r6 = r5.r
            r2 = 4001(0xfa1, float:5.607E-42)
            defpackage.fbh.e(r6, r2, r1)
        Lb1:
            r5.ae = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.soical.NewSocialFragment.fragmentVisibleStatus(boolean):void");
    }

    public /* synthetic */ void e(Boolean bool) {
        i();
        this.x.sendEmptyMessage(4321);
    }

    public /* synthetic */ void e(Exception exc) {
        this.x.sendEmptyMessage(4321);
    }

    private void o() {
        List<MessageObject> list = this.n;
        if (list != null) {
            list.clear();
        }
        this.ag = true;
        this.ba = SystemClock.elapsedRealtime();
        this.bm.post(new Runnable() { // from class: rwk
            @Override // java.lang.Runnable
            public final void run() {
                NewSocialFragment.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        x();
        if (LoginInit.getInstance(this.r).isKidAccount()) {
            this.ag = false;
            return;
        }
        if (Utils.o() && !bzs.e().switchToMarketingTwo()) {
            List<MessageObject> messages = this.ar.getMessages(29);
            this.n = rxh.d(messages);
            this.v.countDown();
            LogUtil.a("UIDV_SocialFragment", "tempMsgObjects:", Integer.valueOf(messages != null ? messages.size() : 0));
        } else {
            f();
        }
        try {
            LogUtil.a("UIDV_SocialFragment", "wait:", Boolean.valueOf(this.v.await(PreConnectManager.CONNECT_INTERNAL, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException unused) {
            LogUtil.b("UIDV_SocialFragment", "await InterruptedException");
        }
        LogUtil.a("UIDV_SocialFragment", "get data success");
        h();
    }

    private void h() {
        int size;
        List<MessageObject> a2 = sdi.a(this.n, this.av, "PPS202117");
        synchronized (f10470a) {
            this.i.clear();
            this.i.addAll(a2);
            List<MessageObject> ak = ak();
            if (ak != null) {
                Message obtain = Message.obtain();
                obtain.what = 21;
                obtain.obj = ak;
                this.x.sendMessage(obtain);
            }
            this.ag = false;
            size = this.i.size();
        }
        if (size > 0) {
            this.ai = true;
            this.x.sendEmptyMessage(30);
        } else {
            d = true;
            LogUtil.a("UIDV_SocialFragment", "AdMessageList size is 0, doRefresh！");
            this.ai = false;
            this.ar.refreshMessages();
        }
    }

    private void f() {
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        Object[] objArr = new Object[2];
        objArr[0] = "marketingApi is null ";
        objArr[1] = Boolean.valueOf(marketingApi == null);
        LogUtil.a("UIDV_SocialFragment", objArr);
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        if (marketingApi != null) {
            marketingApi.getResourceResultInfo(1002).addOnSuccessListener(new OnSuccessListener() { // from class: rwb
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    NewSocialFragment.this.d(elapsedRealtime, marketingApi, (Map) obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: rwg
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    NewSocialFragment.this.b(exc);
                }
            });
        }
    }

    public /* synthetic */ void d(long j, MarketingApi marketingApi, Map map) {
        LogUtil.a("UIDV_SocialFragment", "Waitingfor:", Long.valueOf(SystemClock.elapsedRealtime() - j));
        Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules((Map<Integer, ResourceResultInfo>) map);
        if (filterMarketingRules.containsKey(1002)) {
            ResourceResultInfo resourceResultInfo = filterMarketingRules.get(1002);
            if (resourceResultInfo == null) {
                LogUtil.h("UIDV_SocialFragment", "resourceResultInfo = null");
                this.v.countDown();
                return;
            }
            List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
            if (koq.b(resources)) {
                LogUtil.a("UIDV_SocialFragment", "resourceBriefInfoList is empty");
                this.v.countDown();
                return;
            } else {
                LogUtil.a("UIDV_SocialFragment", "resourceBriefInfoList size:", Integer.valueOf(resources.size()));
                d(resources);
                return;
            }
        }
        this.v.countDown();
    }

    public /* synthetic */ void b(Exception exc) {
        this.v.countDown();
    }

    public void a(List<MessageObject> list) {
        synchronized (f10470a) {
            this.e.clear();
        }
        this.j.removeAllViews();
        this.o.removeAllViews();
        this.z.removeAllViews();
        LogUtil.a("UIDV_SocialFragment", "no Ad message in database");
        this.as.setVisibility(8);
        this.y.setVisibility(8);
        this.k.c(list);
        this.bj.dSI_(list, this.x);
    }

    private MessageObject d(ResourceBriefInfo resourceBriefInfo, TopBannerNewTemplate topBannerNewTemplate, mdf mdfVar) {
        boolean z = resourceBriefInfo.getContentType() == 14;
        pgq pgqVar = new pgq();
        pgqVar.d(this.k.d(resourceBriefInfo.getContentType()));
        if (topBannerNewTemplate.getContentSource() == 1) {
            pgqVar.a(mdfVar);
            pgqVar.setHarmonyImageSize(z ? "4:5" : "16:9");
            pgqVar.setHarmonyImgUrl(mdfVar.ab());
            pgqVar.setImgUri(mdfVar.ab());
            pgqVar.setDetailUri(mdfVar.e());
            pgqVar.setMsgId(mdfVar.h());
            pgqVar.setCreateTime(resourceBriefInfo.getEffectiveTime());
            pgqVar.setExpireTime(resourceBriefInfo.getExpirationTime());
            pgqVar.setWeight(resourceBriefInfo.getPriority());
            pgqVar.setMsgTitle(resourceBriefInfo.getResourceName());
            pgqVar.setCategory(resourceBriefInfo.getCategory());
        } else {
            pgqVar.setHarmonyImageSize(z ? "4:5" : "16:9");
            pgqVar.setHarmonyImgUrl(topBannerNewTemplate.getPicture());
            pgqVar.setImgUri(topBannerNewTemplate.getPicture());
            pgqVar.setDetailUri(topBannerNewTemplate.getLinkValue());
            pgqVar.setMsgId(resourceBriefInfo.getResourceId());
            pgqVar.setCreateTime(resourceBriefInfo.getEffectiveTime());
            pgqVar.setExpireTime(resourceBriefInfo.getExpirationTime());
            pgqVar.setWeight(resourceBriefInfo.getPriority());
            pgqVar.setMsgTitle(resourceBriefInfo.getResourceName());
            pgqVar.setCategory(resourceBriefInfo.getCategory());
        }
        return pgqVar;
    }

    private void d(List<ResourceBriefInfo> list) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Collections.sort(list, new Comparator() { // from class: rwr
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return NewSocialFragment.b((ResourceBriefInfo) obj, (ResourceBriefInfo) obj2);
            }
        });
        int contentType = list.get(0).getContentType();
        Iterator<ResourceBriefInfo> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getContentType() != contentType) {
                it.remove();
            }
        }
        ArrayList arrayList = new ArrayList();
        Gson gson = new Gson();
        TopBannerNewTemplate topBannerNewTemplate = null;
        ResourceBriefInfo resourceBriefInfo = null;
        for (ResourceBriefInfo resourceBriefInfo2 : list) {
            if (resourceBriefInfo2 == null || resourceBriefInfo2.getContent() == null || resourceBriefInfo2.getContent().getContent() == null) {
                LogUtil.a("UIDV_SocialFragment", "resourceBriefInfo = null");
            } else {
                String content = resourceBriefInfo2.getContent().getContent();
                LogUtil.a("UIDV_SocialFragment", content);
                TopBannerNewTemplate topBannerNewTemplate2 = (TopBannerNewTemplate) gson.fromJson(content, TopBannerNewTemplate.class);
                if (!efb.a(topBannerNewTemplate2)) {
                    if (topBannerNewTemplate2.getContentSource() == 0) {
                        arrayList.add(d(resourceBriefInfo2, topBannerNewTemplate2, (mdf) null));
                    } else if (resourceBriefInfo == null || resourceBriefInfo.getPriority() < resourceBriefInfo2.getPriority()) {
                        resourceBriefInfo = resourceBriefInfo2;
                        topBannerNewTemplate = topBannerNewTemplate2;
                    }
                }
            }
        }
        c(topBannerNewTemplate, resourceBriefInfo, arrayList, elapsedRealtime);
    }

    public static /* synthetic */ int b(ResourceBriefInfo resourceBriefInfo, ResourceBriefInfo resourceBriefInfo2) {
        return resourceBriefInfo2.getPriority() - resourceBriefInfo.getPriority();
    }

    private void c(final TopBannerNewTemplate topBannerNewTemplate, final ResourceBriefInfo resourceBriefInfo, final List<MessageObject> list, final long j) {
        if (topBannerNewTemplate != null && resourceBriefInfo != null) {
            Task<List<mdf>> taskInfoListByScenario = bzw.e().getTaskInfoListByScenario(getContext(), 1, new int[0]);
            if (taskInfoListByScenario == null) {
                this.n = list;
                LogUtil.a("UIDV_SocialFragment", "messageObjectList00:", Integer.valueOf(list.size()), " WaitingforKaka0:", Long.valueOf(SystemClock.elapsedRealtime() - j));
                this.v.countDown();
                return;
            }
            taskInfoListByScenario.addOnSuccessListener(new OnSuccessListener() { // from class: rwt
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    NewSocialFragment.this.d(list, resourceBriefInfo, topBannerNewTemplate, j, (List) obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: rwu
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    NewSocialFragment.this.e(list, j, exc);
                }
            });
            return;
        }
        LogUtil.a("UIDV_SocialFragment", "messageObjectList1:", Integer.valueOf(list.size()));
        LogUtil.a("UIDV_SocialFragment", "WaitingforKaka3:", Long.valueOf(SystemClock.elapsedRealtime() - j));
        this.n = list;
        this.v.countDown();
    }

    public /* synthetic */ void d(List list, ResourceBriefInfo resourceBriefInfo, TopBannerNewTemplate topBannerNewTemplate, long j, List list2) {
        LogUtil.a("UIDV_SocialFragment", "fetch kakaInfos success");
        if (koq.c(list2)) {
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                mdf mdfVar = (mdf) it.next();
                if (mdfVar != null && this.k.c(mdfVar)) {
                    list.add(d(resourceBriefInfo, topBannerNewTemplate, mdfVar));
                }
            }
        }
        this.n = list;
        LogUtil.a("UIDV_SocialFragment", "messageObjectList0:", Integer.valueOf(list.size()));
        LogUtil.a("UIDV_SocialFragment", "WaitingforKaka1:", Long.valueOf(SystemClock.elapsedRealtime() - j));
        this.v.countDown();
    }

    public /* synthetic */ void e(List list, long j, Exception exc) {
        this.n = list;
        LogUtil.b("UIDV_SocialFragment", "WaitingforKaka2:", Long.valueOf(SystemClock.elapsedRealtime() - j));
        this.v.countDown();
    }

    private void dSv_(View view) {
        this.ay = new CustomPermissionAction(this.r) { // from class: com.huawei.ui.main.stories.soical.NewSocialFragment.4
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a("UIDV_SocialFragment", "initSocalTitleBar() mQrCodeAction onGranted");
                NewSocialFragment.this.ag();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a("UIDV_SocialFragment", "initSocalTitleBar() mQrCodeAction onDenied");
                NewSocialFragment.this.ag();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                LogUtil.a("UIDV_SocialFragment", "initSocalTitleBar() mQrCodeAction onForeverDenied");
                NewSocialFragment.this.ag();
            }
        };
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.socail_titlebar_kaka_tips);
        this.bq = healthTextView;
        this.k.dTA_(healthTextView);
        CustomTitleBar customTitleBar = (CustomTitleBar) view.findViewById(R.id.social_tab_titlebar);
        this.bi = customTitleBar;
        customTitleBar.setLeftButtonVisibility(8);
        this.bi.setTitleText(this.r.getString(R.string._2130839532_res_0x7f0207ec));
        if (nrs.a(this.r)) {
            this.bi.setTitleSize(this.r.getResources().getDimension(R.dimen._2131362673_res_0x7f0a0371));
            e(this.bi, R.dimen._2131363048_res_0x7f0a04e8);
        } else {
            this.bi.setTitleSize(this.r.getResources().getDimension(R.dimen._2131365076_res_0x7f0a0cd4));
            e(this.bi, R.dimen._2131363026_res_0x7f0a04d2);
        }
        this.bi.setTitleTextColor(this.r.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        if (!EnvironmentInfo.k()) {
            this.bi.setRightButtonVisibility(0);
        }
        this.bi.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131429707_res_0x7f0b094b), nsf.h(R.string._2130850635_res_0x7f02334b));
        this.bi.setRightButtonOnClickListener(new View.OnClickListener() { // from class: rwi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                NewSocialFragment.this.dSA_(view2);
            }
        });
        this.bi.setRightSoftkeyVisibility(sdl.b() ? 0 : 8);
        this.bi.setRightSoftkeyBackground(getResources().getDrawable(R.drawable._2131429709_res_0x7f0b094d), nsf.h(R.string._2130842400_res_0x7f021320));
        this.bi.setRightSoftkeyOnClickListener(new rxq.b(this.k));
    }

    public /* synthetic */ void dSA_(View view) {
        new PopViewList(this.r, this.bi, new ArrayList(Arrays.asList(getResources().getString(R.string.IDS_device_wifi_my_qrcode_sweep_code_add), getResources().getString(R.string.IDS_hw_device_manager_add_device)))).e(new PopViewList.PopViewClickListener() { // from class: rwx
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public final void setOnClick(int i) {
                NewSocialFragment.this.b(i);
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void b(int i, Object obj) {
        if (i == 0) {
            PermissionUtil.b(this.r, PermissionUtil.PermissionType.CAMERA, this.ay);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void b(int i) {
        if (i == 0) {
            LoginInit.getInstance(this.r).browsingToLogin(new IBaseResponseCallback() { // from class: rwd
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    NewSocialFragment.this.b(i2, obj);
                }
            }, "");
        } else {
            if (i != 1) {
                return;
            }
            j();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ag() {
        if (this.r == null || getActivity() == null) {
            LogUtil.h("UIDV_SocialFragment", "mContext is null");
            return;
        }
        try {
            ComponentName componentName = new ComponentName(this.r.getPackageName(), "com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity");
            Intent intent = new Intent();
            intent.setComponent(componentName);
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.a("UIDV_SocialFragment", "activity not found exception.");
        }
    }

    private void j() {
        ixx.d().d(this.r, AnalyticsValue.HEALTH_HEALTH_MY_DEVICE_2030030.value(), new HashMap(16), 0);
        BrowsingBiEvent browsingBiEvent = LoginInit.getBrowsingBiEvent();
        if (SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false)) {
            if (browsingBiEvent != null) {
                browsingBiEvent.showFullServiceDialog(getActivity());
            }
        } else {
            try {
                if ((this.r.getSystemService("bluetooth") instanceof BluetoothManager ? (BluetoothManager) this.r.getSystemService("bluetooth") : null) == null) {
                    ae();
                } else {
                    af();
                }
            } catch (ActivityNotFoundException unused) {
                LogUtil.a("UIDV_SocialFragment", "activity not found exception.");
            }
        }
    }

    private void ae() {
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this.r).e(R.string._2130844216_res_0x7f021a38).czC_(R.string._2130843756_res_0x7f02186c, new View.OnClickListener() { // from class: rwl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NewSocialFragment.dSw_(view);
            }
        }).e();
        e2.setCancelable(false);
        e2.show();
    }

    public static /* synthetic */ void dSw_(View view) {
        LogUtil.a("UIDV_SocialFragment", "Bluetooth not supported");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void af() {
        Intent intent = new Intent();
        intent.setClassName(this.r, "com.huawei.ui.device.activity.adddevice.OneKeyScanActivity");
        try {
            this.r.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("UIDV_SocialFragment", "ActivityNotFoundException", e2.getMessage());
        }
    }

    private void w() {
        LogUtil.a("UIDV_SocialFragment", "Enter initView");
        this.bk = (LinearLayout) this.bl.findViewById(R.id.social_root_lyt);
        this.bo = (RelativeLayout) this.bl.findViewById(R.id.social_net_work_layout);
        this.aw = (ImageView) this.bl.findViewById(R.id.social_img_no_net_work);
        this.at = (HealthTextView) this.bl.findViewById(R.id.social_tips_no_net_work);
        this.l = (HealthButton) this.bl.findViewById(R.id.social_btn_no_net_work);
        this.ap = (ViewGroup) this.bl.findViewById(R.id.layout_loading);
        this.bp = (RelativeLayout) this.bl.findViewById(R.id.titlebar_panel);
        ((RelativeLayout) this.bl.findViewById(R.id.social_reload_layout)).setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.az.setNeedUpdateActivity(this.r, false);
        y();
    }

    private void ah() {
        if (this.s == null) {
            return;
        }
        LogUtil.a("UIDV_SocialFragment", "showConfiguredPageFragment");
        this.s.setVisibility(0);
        this.q.initOperationConfiguredPage(16, this.s, new ConfiguredPageDataCallback() { // from class: com.huawei.ui.main.stories.soical.NewSocialFragment.2
            @Override // com.huawei.health.configuredpage.api.ConfiguredPageDataCallback
            public void getConfiguredDataSize(int i) {
                Message obtainMessage = NewSocialFragment.this.x.obtainMessage();
                obtainMessage.what = 12288;
                obtainMessage.obj = Integer.valueOf(i);
                NewSocialFragment.this.x.sendMessage(obtainMessage);
            }

            @Override // com.huawei.health.configuredpage.api.ConfiguredPageDataCallback
            public void onDataResponse(List<cdy> list) {
                if (koq.b(list)) {
                    LogUtil.h("UIDV_SocialFragment", "showConfiguredPageFragment() pageModuleObjectList is empty.");
                } else {
                    LogUtil.a("UIDV_SocialFragment", "showConfiguredPageFragment() size = ", Integer.valueOf(list.size()));
                }
            }
        });
    }

    private void y() {
        LogUtil.c("UIDV_SocialFragment", "loadingImage");
        this.bk.setVisibility(8);
        if (CommonUtil.aa(this.r)) {
            this.bo.setVisibility(8);
            this.ap.setVisibility(0);
        } else {
            this.bo.setVisibility(0);
            this.aw.setImageResource(R.drawable._2131430211_res_0x7f0b0b43);
            this.at.setText(this.r.getString(R.string._2130842061_res_0x7f0211cd));
            this.l.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ai() {
        LogUtil.c("UIDV_SocialFragment", "refresh view : stopLoadingImage()");
        if (!this.ah) {
            this.bo.setVisibility(0);
        }
        this.ap.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ab() {
        LogUtil.a("UIDV_SocialFragment", "refresh view : setTipsPage()");
        this.bo.setVisibility(8);
        if (CommonUtil.aa(this.r)) {
            this.bk.setVisibility(0);
            this.as.setVisibility(8);
            if (Utils.o() && !bzs.e().switchToMarketingTwo()) {
                if (this.p <= 0) {
                    ad();
                }
            } else {
                NewSocialPageResTrigger newSocialPageResTrigger = this.bh;
                if (newSocialPageResTrigger != null && newSocialPageResTrigger.hasNoData()) {
                    ad();
                }
            }
            this.l.setVisibility(8);
            return;
        }
        this.bk.setVisibility(8);
        this.bo.setVisibility(0);
        this.aw.setImageResource(R.drawable._2131430211_res_0x7f0b0b43);
        this.at.setText(this.r.getString(R.string._2130842061_res_0x7f0211cd));
        this.l.setVisibility(0);
    }

    private void ad() {
        this.bo.setVisibility(0);
        this.aw.setImageResource(R.drawable._2131430454_res_0x7f0b0c36);
        this.at.setText(this.r.getString(R.string._2130843528_res_0x7f021788));
    }

    private void aa() {
        this.ap.setVisibility(8);
        this.bk.setVisibility(8);
        this.bo.setVisibility(0);
        this.aw.setImageResource(R.drawable._2131430454_res_0x7f0b0c36);
        this.at.setText(this.r.getString(R.string._2130843528_res_0x7f021788));
        this.l.setVisibility(8);
        this.bi.setRightSoftkeyVisibility(8);
        this.bq.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ac() {
        LogUtil.c("UIDV_SocialFragment", "refresh view : setNormalPage()");
        this.ah = true;
        ai();
        this.bo.setVisibility(8);
        this.bk.setVisibility(0);
    }

    public void a() {
        if (LoginInit.getInstance(this.r).isKidAccount()) {
            aa();
            return;
        }
        y();
        if (CommonUtil.aa(this.r)) {
            LogUtil.a("UIDV_SocialFragment", "refreshData() mIsAlreadyRequestMessageData = ", Boolean.valueOf(this.af));
            this.v = new CountDownLatch(2);
            q();
            o();
            n();
            s();
            this.bm.post(new e(this.ar));
        }
    }

    private void dSu_(View view) {
        synchronized (f10470a) {
            this.c.clear();
            this.f.clear();
            this.b.clear();
            this.h.clear();
            this.e.clear();
            rxq rxqVar = new rxq(this, this.az, this.bb, this.bc);
            this.k = rxqVar;
            rxqVar.e(this.e, this.h);
            this.aa = new rxv(this, this.e, this.h, this.k);
            this.ab = new rxv(this, this.e, this.h, this.k);
            this.ad = new DailyTaskRecyclerViewAdapter(this, this.e, this.h, this.k);
        }
        HealthViewPager healthViewPager = (HealthViewPager) view.findViewById(R.id.view_pager_common_top_banner);
        this.j = healthViewPager;
        healthViewPager.setLayerType(1, null);
        this.o = (HealthViewPager) view.findViewById(R.id.view_pager_common_top_banner_cant_loop);
        this.y = (HealthDotsPageIndicator) view.findViewById(R.id.indicator);
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.recycleView_dailyTask);
        this.z = healthRecycleView;
        healthRecycleView.setLayoutManager(new HealthLinearLayoutManager(getContext(), 0, false));
        this.k.c(this.j, this.o, this.z, this.y);
        this.j.addOnPageChangeListener(new b(this));
        this.o.addOnPageChangeListener(new b(this));
        this.j.setAdapter(this.aa);
        this.o.setAdapter(this.ab);
        an();
        this.z.setAdapter(this.ad);
    }

    private void an() {
        this.j.setOnTouchListener(new View.OnTouchListener() { // from class: rwc
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return NewSocialFragment.this.dSB_(view, motionEvent);
            }
        });
        this.o.setOnTouchListener(new View.OnTouchListener() { // from class: rwn
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return NewSocialFragment.this.dSC_(view, motionEvent);
            }
        });
    }

    public /* synthetic */ boolean dSB_(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1 || action == 3 || action == 4) {
            this.k.f();
            return false;
        }
        if (action == 0) {
            this.k.c();
            return false;
        }
        LogUtil.h("UIDV_SocialFragment", "no branch!");
        return false;
    }

    public /* synthetic */ boolean dSC_(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1 || action == 3 || action == 4) {
            this.k.f();
            return false;
        }
        if (action == 0) {
            this.k.c();
            return false;
        }
        LogUtil.h("UIDV_SocialFragment", "no branch!");
        return false;
    }

    static class b implements HealthViewPager.OnPageChangeListener {
        private final WeakReference<NewSocialFragment> c;

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        public b(NewSocialFragment newSocialFragment) {
            this.c = new WeakReference<>(newSocialFragment);
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            WeakReference<NewSocialFragment> weakReference = this.c;
            NewSocialFragment newSocialFragment = weakReference != null ? weakReference.get() : null;
            if (newSocialFragment == null) {
                LogUtil.a("UIDV_SocialFragment", "onPageSelected fragment is null");
            } else {
                newSocialFragment.d(i);
            }
        }
    }

    public void d(int i) {
        if (this.as == null) {
            LogUtil.h("UIDV_SocialFragment", "ad viewpager is null");
            return;
        }
        LogUtil.a("UIDV_SocialFragment", "onPageSelected: ", Integer.valueOf(i));
        boolean alE_ = eie.alE_(this.as);
        boolean alF_ = eie.alF_(this.as, i);
        LogUtil.a("UIDV_SocialFragment", "rect visibility: ", Boolean.valueOf(alE_), " window visibility: ", Boolean.valueOf(alF_));
        synchronized (f10470a) {
            if (alE_ && alF_) {
                if (this.k != null && koq.d(this.h, i)) {
                    this.k.b(this.h.get(i), 0L, 1);
                }
            }
        }
    }

    private void dSs_(ImageView imageView, MessageObject messageObject) {
        LogUtil.a("UIDV_SocialFragment", "addImageToShow");
        if (dSt_(imageView, messageObject)) {
            synchronized (f10470a) {
                this.bj.dSH_(this.f, messageObject, this.b, imageView);
                if (this.f.size() > 0) {
                    this.as.setVisibility(0);
                    this.h.clear();
                    this.e.clear();
                    for (int i = 0; i < this.b.size() && i < 8; i++) {
                        this.h.add(this.f.get(i));
                        this.e.add(this.b.get(i));
                    }
                    d(0);
                }
                LogUtil.a("UIDV_SocialFragment", "addImageToShow mADImageShowListSize = ", Integer.valueOf(this.e.size()));
                Message obtain = Message.obtain();
                obtain.what = 28;
                obtain.obj = Integer.valueOf(this.h.size());
                this.x.sendMessage(obtain);
            }
        }
    }

    private boolean dSt_(ImageView imageView, MessageObject messageObject) {
        boolean z;
        if (imageView == null || messageObject == null) {
            LogUtil.h("UIDV_SocialFragment", "imageView||messageObject is null");
            z = false;
        } else {
            z = true;
        }
        ArrayList arrayList = new ArrayList();
        synchronized (f10470a) {
            arrayList.addAll(this.c);
        }
        boolean z2 = false;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) != null && messageObject != null && messageObject.getMsgId().equals(((MessageObject) arrayList.get(i)).getMsgId())) {
                z2 = true;
            }
        }
        if (z2) {
            return z;
        }
        LogUtil.a("UIDV_SocialFragment", "this ad is not in display list");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<MessageObject> ak() {
        LogUtil.a("UIDV_SocialFragment", "updateAdViewPagerDate");
        synchronized (f10470a) {
            rxh.c(this.i, this.c);
            if (this.bj.d(this.c, this.f) && this.c.size() == this.f.size()) {
                LogUtil.a("UIDV_SocialFragment", "no Ad message change");
                return null;
            }
            LogUtil.a("UIDV_SocialFragment", "have Ad message change");
            this.f.clear();
            this.b.clear();
            this.h.clear();
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.c);
            return arrayList;
        }
    }

    @Override // com.huawei.ui.commonui.scrollview.ScrollViewListener
    public void onScrollChanged(HealthScrollView healthScrollView, int i, int i2, int i3, int i4) {
        if (this.r == null || !getUserVisibleHint()) {
            return;
        }
        int scrollY = healthScrollView.getScrollY();
        int height = healthScrollView.getHeight();
        int paddingTop = healthScrollView.getPaddingTop();
        int paddingBottom = healthScrollView.getPaddingBottom();
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.ao;
        if (healthScrollView.getChildAt(0).getHeight() == ((scrollY + height) - paddingTop) - paddingBottom && currentTimeMillis - j > 1000) {
            HashMap hashMap = new HashMap();
            hashMap.put("scroll", "1");
            hashMap.put(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE, 1);
            ixx.d().d(this.r, AnalyticsValue.HEALTH_DISCOVER_SCROLL_TO_BOTTOM_2020014.value(), hashMap, 0);
            ixx.d().c(this.r);
            this.ao = System.currentTimeMillis();
        }
        if (this.an) {
            if (this.bd <= 0) {
                this.bd = this.bf.getHeight();
            }
            int i5 = this.bd;
            if (i5 > 0 && i2 >= i5 && !this.ak) {
                dpf.Ys_(this.bl);
                dpf.d(this.bi, false);
                dpf.d(this.bf);
                this.ak = true;
            }
            if (i2 <= 0 && this.ak) {
                dpf.Ys_(this.bl);
                dpf.e(this.bf);
                dpf.c(this.bi, false);
                this.ak = false;
            }
        }
        g();
    }

    private void g() {
        View view;
        if (this.k == null || (view = this.as) == null) {
            LogUtil.c("UIDV_SocialFragment", "mBannerInteractor or mLayAd is null,return");
        } else if (!eie.alE_(view)) {
            this.k.c();
        } else {
            this.k.f();
        }
    }

    @Override // com.huawei.ui.main.stories.soical.BaseSocialFragment, androidx.fragment.app.Fragment
    public void onPause() {
        LogUtil.a("UIDV_SocialFragment", "Enter onPause begin");
        super.onPause();
        this.k.c();
    }

    @Override // com.huawei.ui.main.stories.soical.BaseSocialFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        CommonUtil.u("TimeEat_NewSocialFragmentEnter onResume");
        this.x.removeCallbacks(this.be);
        this.x.postDelayed(this.be, 700L);
        if (LoginInit.getInstance(this.r).isKidAccount()) {
            aa();
        }
        CommonUtil.u("TimeEat_NewSocialFragmentLeave onResume");
        this.x.sendEmptyMessage(16384);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        boolean z = SystemClock.elapsedRealtime() - this.t > 2000;
        if (id == R.id.social_btn_no_net_work && z) {
            CommonUtil.q(this.r);
            this.t = SystemClock.elapsedRealtime();
        } else if (id == R.id.social_reload_layout && z) {
            a();
            this.t = SystemClock.elapsedRealtime();
        } else {
            LogUtil.h("UIDV_SocialFragment", "no branch!");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        c cVar = this.x;
        if (cVar != null) {
            cVar.removeCallbacks(this.be);
        }
        aj();
        HandlerThread handlerThread = this.w;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        HealthViewPager healthViewPager = this.j;
        if (healthViewPager != null) {
            healthViewPager.setOnTouchListener(null);
        }
        HealthViewPager healthViewPager2 = this.o;
        if (healthViewPager2 != null) {
            healthViewPager2.setOnTouchListener(null);
        }
        rxu rxuVar = this.m;
        if (rxuVar != null) {
            rxuVar.d();
        }
        Observer observer = this.au;
        if (observer != null) {
            ObserverManagerUtil.c(observer);
        }
        super.onDestroy();
        synchronized (f10470a) {
            this.i.clear();
            this.c.clear();
            this.f.clear();
            this.b.clear();
            this.h.clear();
            this.e.clear();
            this.n.clear();
        }
        this.al = null;
    }

    static class c extends BaseHandler<NewSocialFragment> {
        private WeakReference<NewSocialFragment> e;

        c(NewSocialFragment newSocialFragment) {
            super(Looper.getMainLooper(), newSocialFragment);
            this.e = new WeakReference<>(newSocialFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dSD_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(NewSocialFragment newSocialFragment, Message message) {
            if (message == null) {
                LogUtil.b("UIDV_SocialFragment", "msg is null");
                return;
            }
            int i = message.what;
            if (i == 21) {
                newSocialFragment.dSx_(message);
                return;
            }
            if (i == 23) {
                newSocialFragment.dSr_(message);
                return;
            }
            if (i == 28) {
                newSocialFragment.k.b(((Integer) message.obj).intValue());
                return;
            }
            if (i == 42) {
                newSocialFragment.c(message.obj);
                return;
            }
            if (i == 4321) {
                LogUtil.a("UIDV_SocialFragment", "REQUEST_REFRESH_BANNER");
                newSocialFragment.u();
                return;
            }
            if (i == 16384) {
                newSocialFragment.k.dTD_(newSocialFragment.bq, newSocialFragment.bi, newSocialFragment.bp);
                newSocialFragment.z();
                return;
            }
            if (i == 30) {
                newSocialFragment.ac();
                return;
            }
            if (i == 31) {
                newSocialFragment.ab();
                return;
            }
            if (i != 12288) {
                if (i != 12289) {
                    return;
                }
                newSocialFragment.ai();
            } else if (message.obj instanceof Integer) {
                newSocialFragment.p = ((Integer) message.obj).intValue();
                LogUtil.a("UIDV_SocialFragment", "handler mConfiguredPageDataSize = ", Integer.valueOf(newSocialFragment.p), " mHasBannerData = ", Boolean.valueOf(newSocialFragment.ai), " isRequestBannerNow = ", Boolean.valueOf(newSocialFragment.ag));
                if (newSocialFragment.p > 0) {
                    newSocialFragment.ac();
                }
                if (newSocialFragment.ai || newSocialFragment.ag) {
                    return;
                }
                newSocialFragment.ac();
                newSocialFragment.ab();
            }
        }
    }

    public static class d implements MessageObserver {
        private final WeakReference<NewSocialFragment> d;

        d(NewSocialFragment newSocialFragment) {
            this.d = new WeakReference<>(newSocialFragment);
        }

        @Override // com.huawei.pluginmessagecenter.service.MessageObserver
        public void onChange(int i, MessageChangeEvent messageChangeEvent) {
            LogUtil.a("UIDV_SocialFragment", "MessageObserver onChange start");
            final NewSocialFragment newSocialFragment = this.d.get();
            if (newSocialFragment == null || !newSocialFragment.isVisible() || newSocialFragment.isDetached()) {
                return;
            }
            newSocialFragment.bm.post(new Runnable() { // from class: rwy
                @Override // java.lang.Runnable
                public final void run() {
                    NewSocialFragment.d.this.a(newSocialFragment);
                }
            });
            LogUtil.a("UIDV_SocialFragment", "MessageObserver onChange end");
        }

        public /* synthetic */ void a(NewSocialFragment newSocialFragment) {
            if (!newSocialFragment.isVisible() || newSocialFragment.isDetached()) {
                return;
            }
            List arrayList = new ArrayList();
            if (!Utils.o() || bzs.e().switchToMarketingTwo()) {
                if (koq.c(newSocialFragment.n)) {
                    arrayList.addAll(newSocialFragment.n);
                }
            } else {
                arrayList = rxh.d(((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).getMessages(29));
            }
            a(newSocialFragment, sdi.a(arrayList, newSocialFragment.av, "PPS202117"));
        }

        private void a(NewSocialFragment newSocialFragment, List<MessageObject> list) {
            synchronized (NewSocialFragment.f10470a) {
                newSocialFragment.i.clear();
                newSocialFragment.i.addAll(list);
                List ak = newSocialFragment.ak();
                if (ak != null) {
                    Message obtain = Message.obtain();
                    obtain.what = 21;
                    obtain.obj = ak;
                    newSocialFragment.x.sendMessage(obtain);
                }
                newSocialFragment.ai = false;
                if (newSocialFragment.i.size() > 0) {
                    newSocialFragment.ai = true;
                    newSocialFragment.x.sendEmptyMessage(30);
                } else if (NewSocialFragment.d) {
                    newSocialFragment.x.sendEmptyMessage(12289);
                    newSocialFragment.x.sendEmptyMessage(31);
                } else {
                    LogUtil.c("UIDV_SocialFragment", "Should refresh.");
                }
            }
        }
    }

    private void x() {
        LogUtil.a("UIDV_SocialFragment", "Start register");
        if (this.ar == null || this.ax == null) {
            return;
        }
        LogUtil.a("UIDV_SocialFragment", "registerADObserver");
        this.ar.registerMessageObserver(this.ax);
    }

    private void aj() {
        if (this.ar == null || this.ax == null) {
            return;
        }
        LogUtil.a("UIDV_SocialFragment", "unregisterAdObserver");
        this.ar.unregisterMessageObserver(this.ax);
    }

    @Override // com.huawei.ui.main.stories.soical.BaseSocialFragment
    public void scrollTop() {
        HealthScrollView healthScrollView = this.bg;
        if (healthScrollView != null) {
            healthScrollView.smoothScrollTo(0, 0);
        }
    }

    public void c(final pgq pgqVar) {
        if (pgqVar == null || !pgqVar.b()) {
            return;
        }
        Task<mde> claimKakaTasksByScenario = bzw.e().claimKakaTasksByScenario(getContext(), 1, pgqVar.d().h());
        if (claimKakaTasksByScenario == null) {
            return;
        }
        claimKakaTasksByScenario.addOnSuccessListener(new OnSuccessListener() { // from class: rws
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                NewSocialFragment.this.a(pgqVar, (mde) obj);
            }
        });
    }

    public /* synthetic */ void a(final pgq pgqVar, final mde mdeVar) {
        if (TextUtils.equals(mdeVar.d(), "0")) {
            this.x.post(new Runnable() { // from class: rwf
                @Override // java.lang.Runnable
                public final void run() {
                    NewSocialFragment.this.e(mdeVar, pgqVar);
                }
            });
        }
    }

    public /* synthetic */ void e(mde mdeVar, pgq pgqVar) {
        e(mdeVar.c());
        synchronized (f10470a) {
            int indexOf = this.h.indexOf(pgqVar);
            ImageView remove = (indexOf < 0 || indexOf >= this.e.size()) ? null : this.e.remove(indexOf);
            this.h.remove(pgqVar);
            this.k.dTw_(this.j);
            this.j.getAdapter().notifyDataSetChanged();
            if (this.m != null && this.k.d() == 37) {
                this.m.d(this.h.size());
                this.m.onPageScrollStateChanged(0);
            }
            this.k.dTw_(this.o);
            this.o.getAdapter().notifyDataSetChanged();
            this.k.f();
            this.k.dTw_(this.z);
            this.ad.notifyDataSetChanged();
            if (remove != null) {
                this.b.remove(remove);
            }
            this.f.remove(pgqVar);
            this.n.remove(pgqVar);
            this.c.remove(pgqVar);
        }
        fragmentVisibleStatus(true);
    }

    private void e(int i) {
        if (this.al == null) {
            k();
        }
        KakaClaimAnimation kakaClaimAnimation = this.al;
        if (kakaClaimAnimation != null) {
            kakaClaimAnimation.e(i);
        }
    }

    private void k() {
        ViewStub viewStub = (ViewStub) getView().findViewById(R.id.kaka_convert_anim);
        if (viewStub == null) {
            LogUtil.a("UIDV_SocialFragment", "initConvertAnimLayout ViewStub is loaded fail.");
            return;
        }
        View inflate = viewStub.inflate();
        if (inflate instanceof KakaClaimAnimation) {
            this.al = (KakaClaimAnimation) inflate;
        }
    }

    static class e implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<MessageCenterApi> f10471a;

        e(MessageCenterApi messageCenterApi) {
            this.f10471a = new WeakReference<>(messageCenterApi);
        }

        @Override // java.lang.Runnable
        public void run() {
            MessageCenterApi messageCenterApi = this.f10471a.get();
            if (messageCenterApi == null) {
                return;
            }
            messageCenterApi.refreshMessages();
        }
    }

    public void e(INativeAd iNativeAd) {
        this.av = iNativeAd;
    }

    private void e(CustomTitleBar customTitleBar, int i) {
        if (nsn.r()) {
            customTitleBar.setTitleSize(this.r.getResources().getDimension(i));
        }
    }
}
