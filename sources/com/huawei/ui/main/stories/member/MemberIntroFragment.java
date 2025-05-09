package com.huawei.ui.main.stories.member;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.PathInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.health.utils.vippage.MemberIntroBootPagerHelper;
import com.huawei.health.knit.model.mainpage.MainPageConfig;
import com.huawei.health.knit.model.mainpage.MainPageGroupConfig;
import com.huawei.health.knit.section.listener.BasePageResTrigger;
import com.huawei.health.knit.section.model.MarketingIdInfo;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.marketing.views.IViewVisibilityChange;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.DeviceBenefits;
import com.huawei.trade.datatype.Product;
import com.huawei.trade.datatype.ProductInfo;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.scrollview.ScrollViewListener;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.stories.member.MemberIntroFragment;
import com.huawei.ui.main.stories.utils.MemberCardUpdateDialog;
import defpackage.dpx;
import defpackage.dqj;
import defpackage.eab;
import defpackage.eie;
import defpackage.eim;
import defpackage.gpn;
import defpackage.ixx;
import defpackage.koq;
import defpackage.njn;
import defpackage.nrs;
import defpackage.nrv;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class MemberIntroFragment extends BaseFragment implements IViewVisibilityChange {
    private static final PathInterpolator e = new PathInterpolator(0.0f, 0.2f, 0.2f, 1.0f);

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10385a;
    private HealthImageView aa;
    private ViewGroup ac;
    private List<Product> ad;
    private CustomTitleBar ae;
    private int af;
    private View ag;
    private Product ai;
    private String ak;
    private String al;
    private String am;
    private LinearLayout an;
    private HealthTextView b;
    private HealthTextView c;
    private Context d;
    private RelativeLayout f;
    private long h;
    private boolean i;
    private KnitFragment m;
    private View o;
    private MemberCardUpdateDialog s;
    private RelativeLayout t;
    private HealthTextView u;
    private HealthTextView y;
    private LinearLayout z;
    private final String v = "MEMBER_TYPE_TAG_";
    private final Handler j = new b(this);
    private boolean ah = true;
    private boolean l = false;
    private boolean n = false;
    private boolean k = false;
    private int ab = 0;
    private String p = "";
    private int g = 1;
    private int x = 0;
    private final View.OnClickListener w = new View.OnClickListener() { // from class: com.huawei.ui.main.stories.member.MemberIntroFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("MemberIntroFragment", "open btn clicked");
            MemberIntroFragment.this.h();
            dqj.c(MemberIntroFragment.this.ak, !TextUtils.isEmpty(MemberIntroFragment.this.al) ? MemberIntroFragment.this.al : "1".equals(MemberIntroFragment.this.am) ? "VipRate" : "VipPage", MemberIntroFragment.this.g);
            MemberIntroFragment memberIntroFragment = MemberIntroFragment.this;
            ProductInfo d2 = memberIntroFragment.d(dpx.e(memberIntroFragment.ai));
            if (d2.getPurchasePolicy() == Product.SUBSCRIPTION_PURCHASE_FLAG) {
                dpx.d(MemberIntroFragment.this.getContext(), MemberIntroFragment.this.ai, d2);
            } else {
                MemberIntroFragment.this.o();
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private final View.OnClickListener q = new View.OnClickListener() { // from class: com.huawei.ui.main.stories.member.MemberIntroFragment.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!nsn.o()) {
                MemberIntroFragment.this.e();
                ViewClickInstrumentation.clickOnView(view);
            } else {
                LogUtil.a("MemberIntroFragment", "isFastClick");
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    };
    private final Observer aj = new Observer() { // from class: com.huawei.ui.main.stories.member.MemberIntroFragment.4
        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            if (TextUtils.equals(str, "USER_MEMBER_INFO_UPDATE") && koq.e(objArr, 0)) {
                Object obj = objArr[0];
                if (obj instanceof UserMemberInfo) {
                    final UserMemberInfo userMemberInfo = (UserMemberInfo) obj;
                    HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.main.stories.member.MemberIntroFragment.4.4
                        @Override // java.lang.Runnable
                        public void run() {
                            MemberIntroFragment.this.b(userMemberInfo);
                        }
                    });
                }
            }
        }
    };
    private final Observer r = new AnonymousClass5();

    /* renamed from: com.huawei.ui.main.stories.member.MemberIntroFragment$5, reason: invalid class name */
    public class AnonymousClass5 implements Observer {
        AnonymousClass5() {
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, final Object... objArr) {
            if (koq.e(objArr, 0)) {
                LogUtil.a("MemberIntroFragment", "viewStub start args = ", objArr);
                HandlerExecutor.e(new Runnable() { // from class: ris
                    @Override // java.lang.Runnable
                    public final void run() {
                        MemberIntroFragment.AnonymousClass5.this.e(objArr);
                    }
                });
            }
        }

        public /* synthetic */ void e(Object[] objArr) {
            if (MemberIntroFragment.this.s == null) {
                MemberIntroFragment.this.s = new MemberCardUpdateDialog(MemberIntroFragment.this.d);
            }
            MemberIntroFragment.this.s.d(objArr[0]);
            MemberIntroFragment.this.s.show();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("MemberIntroFragment", "onCreateView");
        this.d = getActivity();
        this.ag = layoutInflater.inflate(R.layout.fragment_member_intro, viewGroup, false);
        this.h = System.currentTimeMillis();
        this.ae = (CustomTitleBar) this.ag.findViewById(R.id.member_title_bar);
        this.z = (LinearLayout) this.ag.findViewById(R.id.member_intro_root_lyt);
        RelativeLayout relativeLayout = (RelativeLayout) this.ag.findViewById(R.id.loading_layout);
        this.t = relativeLayout;
        relativeLayout.setVisibility(0);
        this.f = (RelativeLayout) this.ag.findViewById(R.id.member_intro_error_layout);
        f();
        p();
        l();
        return this.ag;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        y();
        this.j.removeMessages(102);
    }

    public void d(int i) {
        this.ab = i;
    }

    private void f() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        this.l = arguments.getBoolean("IS_SHOW_TITLE_RETURN_BUTTOM", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (TextUtils.isEmpty(this.p)) {
            LogUtil.h("MemberIntroFragment", "type select url is empty");
            return;
        }
        Uri parse = Uri.parse(this.p);
        this.am = parse.getQueryParameter("trigResMemberPrice");
        this.al = parse.getQueryParameter("vipOpenFrom");
        this.ak = parse.getQueryParameter("trigResName");
    }

    private void l() {
        j();
        k();
        i();
        n();
        g();
    }

    private void j() {
        LogUtil.a("MemberIntroFragment", "initGuideLayout");
        LinearLayout linearLayout = this.z;
        linearLayout.addOnAttachStateChangeListener(new d(this.d, linearLayout));
    }

    private void p() {
        ObserverManagerUtil.d(this.aj, "USER_MEMBER_INFO_UPDATE");
        ObserverManagerUtil.d(this.r, "MEMBER_OPEN_BMI_CHANGE");
    }

    private void y() {
        ObserverManagerUtil.c(this.aj);
        ObserverManagerUtil.c(this.r);
    }

    private void n() {
        this.ae.setLeftButtonVisibility(this.l ? 0 : 8);
        this.ae.setTitleText(this.d.getString(R.string._2130847720_res_0x7f0227e8));
        if (nrs.a(this.d)) {
            this.ae.setTitleSize(this.d.getResources().getDimension(R.dimen._2131365075_res_0x7f0a0cd3));
        } else {
            this.ae.setTitleSize(this.d.getResources().getDimension(R.dimen._2131365076_res_0x7f0a0cd4));
        }
        this.ae.setTitleTextColor(this.d.getResources().getColor(R.color._2131296952_res_0x7f0902b8));
        this.ae.setTitleBarBackgroundColor(ContextCompat.getColor(this.d, R.color._2131296988_res_0x7f0902dc));
        this.ae.setRightButtonVisibility(8);
        this.ae.setVisibility(0);
        if (this.l) {
            this.ae.setTitleMarginStart(this.d.getResources().getDimensionPixelSize(R.dimen._2131362860_res_0x7f0a042c));
        } else {
            this.ae.setTitleMarginStart(this.d.getResources().getDimensionPixelSize(R.dimen._2131362635_res_0x7f0a034b));
        }
        this.ae.setClickable(true);
        this.ae.bringToFront();
    }

    private void g() {
        nsy.cMn_(this.f, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.member.MemberIntroFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.o()) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.a("MemberIntroFragment", "click error layout");
                ObserverManagerUtil.c("REFRESH_DISCOVER_FRAGMENT", new Object[0]);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void k() {
        this.o = this.ag.findViewById(R.id.member_intro_knit_container);
        this.ac = (ViewGroup) this.ag.findViewById(R.id.member_intro_open_member_layout);
        this.an = (LinearLayout) this.ag.findViewById(R.id.member_intro_member_type_select_layout);
        this.f10385a = (HealthTextView) this.ag.findViewById(R.id.member_intro_currency_text);
        this.c = (HealthTextView) this.ag.findViewById(R.id.member_intro_amount_text);
        this.y = (HealthTextView) this.ag.findViewById(R.id.member_intro_member_type_select_text);
        this.an.setOnClickListener(this.q);
        HealthTextView healthTextView = (HealthTextView) this.ag.findViewById(R.id.member_intro_open_member_text);
        this.u = healthTextView;
        healthTextView.setOnClickListener(this.w);
        b(gpn.a());
        this.b = (HealthTextView) this.ag.findViewById(R.id.member_agreement_text);
        this.aa = (HealthImageView) this.ag.findViewById(R.id.member_agreement_icon_question);
        r();
        this.b.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void r() {
        this.aa.setImageResource(nsn.v(BaseApplication.e()) ? R.drawable._2131430302_res_0x7f0b0b9e : R.drawable._2131430303_res_0x7f0b0b9f);
        this.aa.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.member.MemberIntroFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
                if (marketRouterApi != null) {
                    marketRouterApi.router("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.vip?isImmerse&h5pro=true&pName=com.huawei.health&path=CancelService&urlType=4");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(UserMemberInfo userMemberInfo) {
        boolean z = userMemberInfo == null || userMemberInfo.getMemberFlag() == -1;
        LogUtil.a("MemberIntroFragment", "refreshOpenMemberBtn isNoVip: ", Boolean.valueOf(z));
        nsy.cMr_(this.u, BaseApplication.e().getResources().getString(z ? R.string._2130845705_res_0x7f022009 : R.string._2130844867_res_0x7f021cc3));
    }

    private void i() {
        KnitFragment c2 = c();
        this.m = c2;
        if (c2 == null) {
            LogUtil.a("MemberIntroFragment", "getMainPageKnitFragment returns null");
            return;
        }
        c2.setLoadingCallback(new a(this));
        this.m.setOutHandler(this.j);
        this.m.setScrollViewListener(new g(this));
        getChildFragmentManager().beginTransaction().add(R.id.member_intro_knit_container, this.m).commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        nsy.cMA_(this.z, 0);
        nsy.cMA_(this.f, 8);
        nsy.cMA_(this.t, 8);
        this.ah = true;
        if (this.n) {
            HandlerExecutor.d(new Runnable() { // from class: rik
                @Override // java.lang.Runnable
                public final void run() {
                    MemberIntroFragment.this.b();
                }
            }, 500L);
        }
        if (this.k) {
            HandlerExecutor.a(new Runnable() { // from class: rir
                @Override // java.lang.Runnable
                public final void run() {
                    MemberIntroFragment.this.e();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        nsy.cMA_(this.z, 4);
        nsy.cMA_(this.t, 8);
        nsy.cMA_(this.f, 0);
        this.ah = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ProductInfo d(ProductInfo productInfo) {
        ProductInfo productInfo2 = new ProductInfo();
        if (productInfo == null) {
            return productInfo2;
        }
        productInfo.setTrigResName("会员开通");
        productInfo.setTrigResType("1000");
        productInfo.setTrigResMemberPrice("0");
        return productInfo;
    }

    private KnitFragment c() {
        MainPageGroupConfig c2 = eab.c(getContext(), "MainPageSectionsConfig.json");
        ArrayList<MainPageConfig> pagesConfig = c2 != null ? c2.getPagesConfig() : null;
        if (pagesConfig == null) {
            return null;
        }
        for (int i = 0; i < pagesConfig.size(); i++) {
            MainPageConfig mainPageConfig = pagesConfig.get(i);
            if (mainPageConfig != null) {
                int pageType = mainPageConfig.getPageType();
                int resPosId = mainPageConfig.getResPosId();
                String a2 = nrv.a(mainPageConfig);
                if (pageType == 32) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(KnitFragment.KEY_BOOT_DRAWING_SECTIONS_COUNT, 2);
                    bundle.putLong(KnitFragment.KEY_LOADING_TIME_OUT_DELAY, PreConnectManager.CONNECT_INTERNAL);
                    return KnitFragment.newInstance(a2, new MemberIntroPageResTrigger(getContext(), resPosId, null, this).setExtra(dPl_()), bundle);
                }
            }
        }
        return null;
    }

    private Bundle dPl_() {
        LogUtil.a("MemberIntroFragment", "setObserverTag: ", "MEMBER_TYPE_TAG_");
        Bundle bundle = new Bundle();
        bundle.putString("MEMBER_PRODUCT_TAG", "MEMBER_TYPE_TAG_");
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<Product> list, int i) {
        ViewGroup viewGroup;
        LogUtil.a("MemberIntroFragment", "refreshOpenMemberLayout selectedIndex: ", Integer.valueOf(i));
        if (koq.b(list, i)) {
            return;
        }
        this.ad = list;
        this.af = i;
        Product product = list.get(i);
        if (product == null) {
            return;
        }
        this.ai = product;
        ObserverManagerUtil.c("MEMBER_TYPE_SELECTED", product);
        if (product.getPurchasePolicy() == Product.SUBSCRIPTION_PURCHASE_FLAG) {
            nsy.cMq_(this.y, R.string._2130846682_res_0x7f0223da);
        } else {
            nsy.cMr_(this.y, product.getProductName());
        }
        nsy.cMr_(this.f10385a, njn.e(product.getCurrency()));
        nsy.cMr_(this.c, dpx.d(product));
        nsy.cMr_(this.b, dpx.b(c(product)));
        nsy.cMA_(this.aa, c(product) ? 0 : 8);
        if (!this.ah || (viewGroup = this.ac) == null || viewGroup.getVisibility() == 0) {
            return;
        }
        s();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a("MemberIntroFragment", "onConfigurationChanged");
    }

    private boolean c(Product product) {
        return product != null && product.getPurchasePolicy() == Product.SUBSCRIPTION_PURCHASE_FLAG;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        Product product = this.ai;
        if (product == null) {
            return;
        }
        ProductInfo e2 = dpx.e(product);
        e2.setAreaInfo(dpx.e());
        e2.setBiParams(dqj.b());
        String a2 = nrv.a(d(e2));
        LogUtil.a("MemberIntroFragment", "jumpOpenPage: ", a2);
        ((PayApi) Services.c("TradeService", PayApi.class)).buyByProductId(BaseApplication.e(), this, 10000, a2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m, reason: merged with bridge method [inline-methods] */
    public void e() {
        LogUtil.a("MemberIntroFragment", "jumpTypeSelectActivity: ", Integer.valueOf(this.af));
        if (BaseApplication.wa_() == null) {
            LogUtil.b("MemberIntroFragment", "jumpTypeSelectActivity getTopActivity null");
            return;
        }
        Intent intent = new Intent(BaseApplication.wa_(), (Class<?>) MemberTypeSelectActivity.class);
        intent.putExtra("MEMBER_TYPE_SELECT_INDEX", this.af);
        intent.putExtra("memberTypeSelectUri", this.p);
        intent.putExtra("slidingScreens", this.g);
        if (!isAdded()) {
            LogUtil.b("MemberIntroFragment", "jumpTypeSelectActivity mHost is null");
            return;
        }
        startActivityForResult(intent, 10001);
        dpx.a(this.d);
        this.k = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        Product product;
        super.onActivityResult(i, i2, intent);
        LogUtil.a("MemberIntroFragment", "onActivityResult resultCode: ", Integer.valueOf(i2), "requestCode ", Integer.valueOf(i));
        if (i == 10000) {
            int intExtra = intent != null ? intent.getIntExtra("shoppingResult", -1) : -1;
            UserMemberInfo a2 = gpn.a();
            boolean z = a2 == null || a2.getMemberFlag() == -1;
            if (intExtra != 0 || (product = this.ai) == null) {
                return;
            }
            dqj.d(this.ai.getProductName(), 8, dpx.a(product) ? 2 : 3, !z);
            return;
        }
        if (i == 10001 && intent != null) {
            int intExtra2 = intent.getIntExtra("MEMBER_TYPE_SELECT_INDEX", -1);
            LogUtil.a("MemberIntroFragment", "onActivityResult index: ", Integer.valueOf(intExtra2));
            if (koq.d(this.ad, intExtra2)) {
                this.af = intExtra2;
                ObserverManagerUtil.c("MEMBER_TYPE_TAG_", Integer.valueOf(intExtra2));
            }
        }
    }

    static class b extends BaseHandler<MemberIntroFragment> {
        public b(MemberIntroFragment memberIntroFragment) {
            super(memberIntroFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dPm_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(MemberIntroFragment memberIntroFragment, Message message) {
            int i = message.what;
            if (i == 100) {
                Object obj = message.obj;
                if (obj instanceof Bundle) {
                    Bundle bundle = (Bundle) obj;
                    memberIntroFragment.a(bundle.getParcelableArrayList("PRODUCTS"), bundle.getInt("MEMBER_TYPE_SELECT_INDEX", -1));
                    return;
                }
                return;
            }
            if (i == 102 && !dqj.d(500)) {
                dqj.c(BaseApplication.e(), memberIntroFragment.ab);
            }
        }
    }

    static class MemberIntroPageResTrigger extends BasePageResTrigger {
        private WeakReference<MemberIntroFragment> mReference;

        @Override // com.huawei.health.knit.section.listener.BasePageResTrigger, com.huawei.health.knit.section.listener.IPageResTrigger
        public int getPageCategory() {
            return 2;
        }

        public MemberIntroPageResTrigger(Context context, int i, MarketingIdInfo marketingIdInfo, MemberIntroFragment memberIntroFragment) {
            super(context, i, marketingIdInfo);
            this.mReference = new WeakReference<>(memberIntroFragment);
        }

        @Override // com.huawei.health.knit.section.listener.BasePageResTrigger, com.huawei.health.knit.section.listener.IPageResTrigger
        public void onPageCreated(Activity activity, View view) {
            super.onPageCreated(activity, view);
        }
    }

    public static class g implements ScrollViewListener {

        /* renamed from: a, reason: collision with root package name */
        private long f10390a = 1000;
        private final WeakReference<Fragment> e;

        public g(Fragment fragment) {
            this.e = new WeakReference<>(fragment);
        }

        @Override // com.huawei.ui.commonui.scrollview.ScrollViewListener
        public void onScrollChanged(HealthScrollView healthScrollView, int i, int i2, int i3, int i4) {
            a(healthScrollView, i, i2, i3, i4);
            Fragment fragment = this.e.get();
            if (fragment == null || !(fragment instanceof MemberIntroFragment)) {
                return;
            }
            if (!healthScrollView.canScrollVertically(1) && System.currentTimeMillis() - this.f10390a > 2000) {
                this.f10390a = SystemClock.elapsedRealtime();
            }
            MemberIntroFragment memberIntroFragment = (MemberIntroFragment) fragment;
            if (memberIntroFragment.getUserVisibleHint()) {
                int scrollY = ((healthScrollView.getScrollY() + healthScrollView.getHeight()) - healthScrollView.getPaddingTop()) - healthScrollView.getPaddingBottom();
                if (memberIntroFragment.x == 0) {
                    memberIntroFragment.x = healthScrollView.getHeight();
                }
                memberIntroFragment.g = (int) Math.ceil((scrollY * 1.0d) / memberIntroFragment.x);
                if (healthScrollView.getChildAt(0).getHeight() != scrollY || System.currentTimeMillis() - this.f10390a <= 1000) {
                    return;
                }
                LogUtil.a("MemberIntroFragment", "scrolled to bottom");
                HashMap hashMap = new HashMap();
                hashMap.put("scroll", "1");
                hashMap.put(CommonUtil.PAGE_TYPE, 2);
                ixx.d().d(BaseApplication.e(), AnalyticsValue.HEALTH_DISCOVER_SCROLL_TO_BOTTOM_2020014.value(), hashMap, 0);
                ixx.d().c(BaseApplication.e());
                this.f10390a = System.currentTimeMillis();
            }
        }

        private void a(HealthScrollView healthScrollView, int i, int i2, int i3, int i4) {
            Fragment fragment = this.e.get();
            if (fragment instanceof MemberIntroFragment) {
                MemberIntroFragment memberIntroFragment = (MemberIntroFragment) fragment;
                if (i2 <= 0) {
                    memberIntroFragment.ae.setTitleTextColor(ContextCompat.getColor(memberIntroFragment.d, R.color._2131296952_res_0x7f0902b8));
                    memberIntroFragment.ae.setTitleBarBackgroundColor(ContextCompat.getColor(memberIntroFragment.d, R.color._2131296988_res_0x7f0902dc));
                } else {
                    memberIntroFragment.ae.setTitleTextColor(ContextCompat.getColor(memberIntroFragment.d, R.color._2131299236_res_0x7f090ba4));
                    memberIntroFragment.ae.setTitleBarBackgroundColor(ContextCompat.getColor(memberIntroFragment.d, R.color._2131296690_res_0x7f0901b2));
                }
            }
        }
    }

    @Override // com.huawei.health.marketing.views.IViewVisibilityChange
    public void onVisibilityChange(boolean z) {
        LogUtil.a("MemberIntroFragment", "onVisibilityChange: ", Boolean.valueOf(z));
        this.i = z;
        if (z) {
            eim.e(new e(this));
            this.h = System.currentTimeMillis();
        }
        if (z || this.h == 0) {
            return;
        }
        t();
    }

    static class e implements IBaseResponseCallback {
        private WeakReference<MemberIntroFragment> c;

        public e(MemberIntroFragment memberIntroFragment) {
            this.c = new WeakReference<>(memberIntroFragment);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            MemberIntroFragment memberIntroFragment = this.c.get();
            if (memberIntroFragment == null) {
                LogUtil.h("MemberIntroFragment", "MemberOuterCallback is null");
                return;
            }
            boolean c = (i == 0 && koq.e(obj, DeviceBenefits.class)) ? gpn.c((List<DeviceBenefits>) obj) : false;
            LogUtil.a("MemberIntroFragment", "onVisibilityChange hasMemberEquity", Boolean.valueOf(c));
            dqj.e(c);
            memberIntroFragment.j.sendEmptyMessageDelayed(102, 1000L);
        }
    }

    static class a implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<MemberIntroFragment> f10389a;

        a(MemberIntroFragment memberIntroFragment) {
            this.f10389a = new WeakReference<>(memberIntroFragment);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("MemberIntroFragment", "LoadingCallback errorCode ", Integer.valueOf(i));
            MemberIntroFragment memberIntroFragment = this.f10389a.get();
            if (memberIntroFragment == null) {
                LogUtil.h("MemberIntroFragment", "member intro fragment is null");
                return;
            }
            if (!health.compact.a.CommonUtil.aa(BaseApplication.e())) {
                memberIntroFragment.q();
            } else {
                memberIntroFragment.i = true;
                memberIntroFragment.v();
            }
            LogUtil.a("MemberIntroFragment", "LoadingCallback mFragmentIsVisibleToUser ", Boolean.valueOf(memberIntroFragment.i));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            this.h = System.currentTimeMillis();
        }
        this.x = 0;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            t();
        }
    }

    public void e(boolean z) {
        this.n = z;
    }

    public void b(boolean z) {
        this.k = z;
    }

    public void a(String str) {
        this.p = str;
    }

    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void b() {
        KnitFragment knitFragment = this.m;
        if (knitFragment == null) {
            return;
        }
        knitFragment.getHealthScrollView().fullScroll(OldToNewMotionPath.SPORT_TYPE_TENNIS);
        this.n = false;
    }

    private void t() {
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("MemberIntroFragment", "setStayEvent: ", Long.valueOf(this.h), " ", Long.valueOf(currentTimeMillis));
        dqj.d(this.h, currentTimeMillis);
    }

    private void s() {
        if (this.ac == null || this.ai == null || !this.ah || Utils.f()) {
            return;
        }
        LogUtil.a("MemberIntroFragment", "showOpenMemberLayout");
        this.ac.animate().cancel();
        nsy.cMA_(this.ac, 0);
        this.ac.animate().alpha(1.0f).setInterpolator(e).setDuration(250L).setListener(null);
    }

    static final class d implements View.OnAttachStateChangeListener {
        private final WeakReference<LinearLayout> d;
        private final WeakReference<Context> e;

        public d(Context context, LinearLayout linearLayout) {
            this.d = new WeakReference<>(linearLayout);
            this.e = new WeakReference<>(context);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(new c(this.e, this.d));
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            LinearLayout linearLayout = this.d.get();
            if (linearLayout == null) {
                LogUtil.h("MemberIntroFragment", "rootLayout is null in onViewDetachedFromWindow.");
            } else {
                linearLayout.removeOnAttachStateChangeListener(this);
            }
        }
    }

    static final class c implements ViewTreeObserver.OnGlobalLayoutListener {
        private final WeakReference<Context> b;
        private final WeakReference<LinearLayout> d;

        public c(WeakReference<Context> weakReference, WeakReference<LinearLayout> weakReference2) {
            this.d = weakReference2;
            this.b = weakReference;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            LinearLayout linearLayout = this.d.get();
            if (linearLayout == null) {
                LogUtil.h("MemberIntroFragment", "rootLayout is null in onGlobalLayout.");
                return;
            }
            if (eie.alG_(linearLayout)) {
                Context context = this.b.get();
                if (context == null) {
                    LogUtil.h("MemberIntroFragment", "context is null in onGlobalLayout.");
                    return;
                }
                LogUtil.a("MemberIntroFragment", "showBootPage in onGlobalLayout.");
                new MemberIntroBootPagerHelper(context).showBootPage();
                linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
    }
}
