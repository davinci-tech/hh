package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MultiGridsTemplate;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceContentBase;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginfitnessadvice.audio.SleepAudioPackage;
import com.huawei.pluginfitnessadvice.audio.SleepAudioSeries;
import com.huawei.trade.TradeViewApi;
import com.huawei.trade.datatype.TradeViewInfo;
import com.huawei.ui.commonui.R$anim;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.PurchaseSuccessDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity;
import com.huawei.ui.main.stories.template.BaseActivity;
import defpackage.asc;
import defpackage.bkz;
import defpackage.eil;
import defpackage.fdu;
import defpackage.ffv;
import defpackage.gnm;
import defpackage.gpn;
import defpackage.ixx;
import defpackage.jdx;
import defpackage.koq;
import defpackage.njn;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nrt;
import defpackage.nrv;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class SeriesCourseListActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private CoordinatorLayout f9786a;
    private ImageView aa;
    private SeriesCourseSummaryFragment ab;
    private ImageView ae;
    private String af;
    private ImageView ag;
    private LinearLayout ah;
    private TextView ai;
    private RelativeLayout aj;
    private Toolbar ak;
    private ImageView al;
    private FrameLayout am;
    private String an;
    private View ao;
    private String ap;
    private ViewPager as;
    private AppBarLayout b;
    private Context c;
    private HealthTextView d;
    private f e;
    private ImageView f;
    private SleepAudioPackage g;
    private LinearLayout h;
    private HealthTextView i;
    private HealthTextView j;
    private int m;
    private boolean o;
    private int p;
    private View q;
    private RecommendFragment r;
    private ViewGroup s;
    private RelativeLayout t;
    private TabLayout v;
    private long w;
    private SeriesCourseListFragment x;
    private FragmentPagerAdapter y;
    private String z;
    private volatile boolean n = false;
    private volatile boolean k = false;
    private volatile boolean l = false;
    private volatile boolean ad = false;
    private volatile boolean u = false;
    private int ac = BaseApplication.getContext().getColor(R.color._2131299296_res_0x7f090be0);

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        if (z) {
            this.l = true;
            if (nrt.a(this.c)) {
                this.aa.setBackground(ContextCompat.getDrawable(this.c, R.drawable._2131427885_res_0x7f0b022d));
                this.ac = ContextCompat.getColor(this.c, R.color._2131297799_res_0x7f090607);
            } else {
                this.aa.setBackground(ContextCompat.getDrawable(this.c, R.drawable._2131427886_res_0x7f0b022e));
                this.ac = ContextCompat.getColor(this.c, R.color._2131297799_res_0x7f090607);
            }
            this.ai.setText(this.af);
            m();
        } else {
            this.l = false;
            this.aa.setBackground(ContextCompat.getDrawable(this.c, R.drawable._2131427885_res_0x7f0b022d));
            this.ac = ContextCompat.getColor(this.c, R.color._2131299296_res_0x7f090be0);
            this.ai.setText("");
            m();
        }
        this.ak.setBackgroundColor(this.ac);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("HiH_SeriesCourseListActivity", "onCreate");
        try {
            super.onCreate(bundle);
        } catch (BadParcelableException unused) {
            LogUtil.b("HiH_SeriesCourseListActivity", "BadParcelableException for savedInstanceState");
        }
        this.c = this;
        this.o = true;
        setContentView(R.layout.series_course_list_activity);
        getWindow().setBackgroundDrawable(null);
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.a("HiH_SeriesCourseListActivity", "intent error");
            return;
        }
        this.ap = intent.getStringExtra("id");
        this.m = intent.getIntExtra("from", 0);
        LogUtil.a("HiH_SeriesCourseListActivity", "id: ", this.ap);
        if (TextUtils.isEmpty(this.ap)) {
            LogUtil.a("HiH_SeriesCourseListActivity", "mTopicId error");
            finish();
            return;
        }
        this.am = (FrameLayout) findViewById(R.id.toast_container);
        this.f9786a = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        this.b = (AppBarLayout) findViewById(R.id.appbarlayout);
        this.ah = (LinearLayout) findViewById(R.id.title_layout);
        this.aa = (ImageView) findViewById(R.id.back_img);
        this.ai = (TextView) findViewById(R.id.title_string);
        this.ae = (ImageView) findViewById(R.id.favorite_img);
        this.ag = (ImageView) findViewById(R.id.share_img);
        this.ak = (Toolbar) findViewById(R.id.toolbar);
        this.aj = (RelativeLayout) findViewById(R.id.top_image_layout);
        Pair<Integer, Integer> safeRegionWidth = com.huawei.ui.commonui.base.BaseActivity.getSafeRegionWidth();
        int paddingStart = this.aj.getPaddingStart();
        int intValue = ((Integer) safeRegionWidth.first).intValue();
        int paddingEnd = this.aj.getPaddingEnd();
        int intValue2 = ((Integer) safeRegionWidth.second).intValue();
        RelativeLayout relativeLayout = this.aj;
        relativeLayout.setPadding(paddingStart - intValue, relativeLayout.getPaddingTop(), paddingEnd - intValue2, this.aj.getPaddingBottom());
        this.al = (ImageView) findViewById(R.id.head_img);
        this.i = (HealthTextView) findViewById(R.id.course_title);
        this.d = (HealthTextView) findViewById(R.id.course_tag);
        this.f = (ImageView) findViewById(R.id.description_img);
        this.h = (LinearLayout) findViewById(R.id.description_layout);
        this.j = (HealthTextView) findViewById(R.id.description_text);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sub_tab_widget_for_sleep);
        this.v = tabLayout;
        tabLayout.setTabIndicatorFullWidth(false);
        this.v.setVisibility(8);
        ViewPager viewPager = (ViewPager) findViewById(R.id.series_course_list_view_pager);
        this.as = viewPager;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.5
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f2, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                LogUtil.a("HiH_SeriesCourseListActivity", "page is selected: ", Integer.valueOf(i));
                SeriesCourseListActivity.this.v.getTabAt(i).select();
                ffv.b(SeriesCourseListActivity.this.g, SeriesCourseListActivity.this.ad, SeriesCourseListActivity.this.u, SeriesCourseListActivity.this.z, i);
            }
        });
        this.v.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.14
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtil.a("HiH_SeriesCourseListActivity", "onTabSelected: ", Integer.valueOf(tab.getPosition()));
                SeriesCourseListActivity.this.as.setCurrentItem(tab.getPosition(), true);
                ViewClickInstrumentation.selectClickOnTabLayout(this, tab);
            }
        });
        this.b.addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) new AppBarStateChangeListener(this));
        f();
        g();
        if (e()) {
            e(this.o);
        }
        getOnBackPressedDispatcher().addCallback(new AnonymousClass24(true));
    }

    /* renamed from: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity$24, reason: invalid class name */
    public class AnonymousClass24 extends OnBackPressedCallback {
        AnonymousClass24(boolean z) {
            super(z);
        }

        @Override // androidx.activity.OnBackPressedCallback
        public void handleOnBackPressed() {
            njn.cvi_(SeriesCourseListActivity.this.q, new IBaseResponseCallback() { // from class: plt
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    SeriesCourseListActivity.AnonymousClass24.this.c(i, obj);
                }
            });
        }

        public /* synthetic */ void c(int i, Object obj) {
            if (i == 0) {
                SeriesCourseListActivity.this.finish();
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        boolean z = this.o;
        if (!z) {
            e(z);
        }
        this.o = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(final SleepAudioPackage sleepAudioPackage) {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.25
                @Override // java.lang.Runnable
                public void run() {
                    SeriesCourseListActivity.this.g(sleepAudioPackage);
                }
            });
            return;
        }
        LogUtil.a("HiH_SeriesCourseListActivity", "updateRecycleView, data: ", sleepAudioPackage.toString());
        SeriesCourseListFragment seriesCourseListFragment = this.x;
        if (seriesCourseListFragment != null) {
            seriesCourseListFragment.e(sleepAudioPackage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final SleepAudioPackage sleepAudioPackage) {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.22
                @Override // java.lang.Runnable
                public void run() {
                    SeriesCourseListActivity.this.d(sleepAudioPackage);
                }
            });
        } else {
            if (sleepAudioPackage == null) {
                LogUtil.b("HiH_SeriesCourseListActivity", "data is null");
                return;
            }
            LogUtil.a("HiH_SeriesCourseListActivity", "initFragment, data: ", sleepAudioPackage.toString());
            b(sleepAudioPackage);
            j();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(SleepAudioPackage sleepAudioPackage) {
        if (sleepAudioPackage == null) {
            LogUtil.b("HiH_SeriesCourseListActivity", "data is null");
            return;
        }
        SleepAudioSeries sleepAudioSeries = sleepAudioPackage.getSleepAudioSeries();
        if (sleepAudioSeries == null) {
            LogUtil.h("HiH_SeriesCourseListActivity", "series is null");
            return;
        }
        e(sleepAudioPackage, sleepAudioSeries);
        if (ffv.b(sleepAudioSeries)) {
            a(sleepAudioSeries);
            return;
        }
        boolean z = ffv.c(sleepAudioSeries) && !ffv.a(sleepAudioSeries);
        LogUtil.a("HiH_SeriesCourseListActivity", "isPag: ", Boolean.valueOf(z));
        if (z) {
            if (this.q != null) {
                LogUtil.a("HiH_SeriesCourseListActivity", "mPurchaseView has showed");
                return;
            }
            TradeViewApi tradeViewApi = (TradeViewApi) Services.a("TradeService", TradeViewApi.class);
            if (tradeViewApi == null) {
                LogUtil.h("HiH_SeriesCourseListActivity", "getVipMessages VipApi is null");
                return;
            }
            k();
            String num = Integer.toString(sleepAudioSeries.getId());
            LogUtil.h("HiH_SeriesCourseListActivity", "pay course id: ", num);
            TradeViewInfo tradeViewInfo = new TradeViewInfo(num, 12, "");
            tradeViewInfo.setButtonName(this.c.getResources().getString(R$string.series_purchase_button_name));
            tradeViewInfo.setTrigResName(sleepAudioSeries.getName());
            if (getIntent() != null) {
                HashMap hashMap = new HashMap(16);
                hashMap.put(WebViewHelp.BI_KEY_PULL_FROM, getIntent().getStringExtra(WebViewHelp.BI_KEY_PULL_FROM));
                hashMap.put("resourceId", getIntent().getStringExtra("resourceId"));
                hashMap.put("resourceName", getIntent().getStringExtra("resourceName"));
                hashMap.put("pullOrder", getIntent().getStringExtra("pullOrder"));
                tradeViewInfo.setBiParams(hashMap);
            }
            View tradeView = tradeViewApi.getTradeView(this, tradeViewInfo);
            this.q = tradeView;
            LogUtil.a("HiH_SeriesCourseListActivity", "mPurchaseView: ", tradeView);
            this.am.removeAllViews();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(12, -1);
            this.am.addView(this.q, layoutParams);
            return;
        }
        b();
    }

    private void e(SleepAudioPackage sleepAudioPackage, SleepAudioSeries sleepAudioSeries) {
        boolean z = this.o;
        if (z) {
            LogUtil.a("HiH_SeriesCourseListActivity", "checkPurchaseSuccess mIsInit:", Boolean.valueOf(z));
            return;
        }
        int purchasedStatus = sleepAudioSeries.getPurchasedStatus();
        int isVip = sleepAudioSeries.getIsVip();
        LogUtil.a("HiH_SeriesCourseListActivity", "checkPurchaseSuccess curPurchaseStatus: ", Integer.valueOf(purchasedStatus), " mPurchaseStatus ", Integer.valueOf(this.p));
        if (isVip == 2 && purchasedStatus == 1 && this.p == 2) {
            a(sleepAudioPackage, sleepAudioSeries);
            this.p = purchasedStatus;
        }
    }

    private void a(SleepAudioPackage sleepAudioPackage, SleepAudioSeries sleepAudioSeries) {
        PurchaseSuccessDialog c2 = new PurchaseSuccessDialog.Builder(this.c).b(R$string.IDS_go_check_my_health_course).b(sleepAudioSeries.getName()).a(sleepAudioSeries.getImgIcon()).c(c(sleepAudioPackage, sleepAudioSeries)).e(new e(this)).czH_(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.23
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SeriesCourseListActivity.this.a(z);
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        }).czI_(new View.OnClickListener() { // from class: plq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SeriesCourseListActivity.this.dqY_(view);
            }
        }).c();
        a(true);
        c2.show();
    }

    public /* synthetic */ void dqY_(View view) {
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getContext(), "com.huawei.health.suggestion.ui.fitness.activity.MyFitnessCourseActivity");
        intent.putExtra("titleName", this.c.getString(com.huawei.ui.main.R$string.IDS_hw_sleep_relax));
        intent.putExtra("courseCategoryKey", 7);
        intent.putExtra("intent_behavior_key", 2);
        gnm.aPB_(this.c, intent);
        overridePendingTransition(R$anim.activity_no_animation, android.R.anim.fade_out);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        this.k = z;
        d(z);
        m();
    }

    private String c(SleepAudioPackage sleepAudioPackage, SleepAudioSeries sleepAudioSeries) {
        if (sleepAudioSeries == null) {
            LogUtil.h("HiH_SeriesCourseListActivity", "sleepAudioSeries is null");
            return "";
        }
        int displayStyle = sleepAudioSeries.getDisplayStyle();
        if (displayStyle == 1) {
            if (bkz.e(sleepAudioPackage.getSleepAudioInfoList())) {
                LogUtil.h("HiH_SeriesCourseListActivity", "sleepAudioInfoList is null");
                return "";
            }
            return ffv.c(sleepAudioPackage.getSleepAudioInfoList());
        }
        if (displayStyle == 2) {
            if (koq.b(sleepAudioPackage.getSleepAudioGroupList())) {
                LogUtil.h("HiH_SeriesCourseListActivity", "sleepAudioGroupList is null");
                return "";
            }
            return ffv.a(sleepAudioPackage.getSleepAudioGroupList());
        }
        LogUtil.h("HiH_SeriesCourseListActivity", "wrong style:", Integer.valueOf(displayStyle));
        return "";
    }

    private void a(SleepAudioSeries sleepAudioSeries) {
        if (!ffv.b(sleepAudioSeries)) {
            LogUtil.a("HiH_SeriesCourseListActivity", "not vip series");
            return;
        }
        LogUtil.a("HiH_SeriesCourseListActivity", "refreshVipInfo");
        k();
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi == null) {
            LogUtil.h("HiH_SeriesCourseListActivity", "refreshVipStateData VipApi is null");
        } else {
            vipApi.getVipInfo(new h());
        }
    }

    /* loaded from: classes6.dex */
    static class h implements VipCallback {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<SeriesCourseListActivity> f9798a;

        private h(SeriesCourseListActivity seriesCourseListActivity) {
            this.f9798a = new WeakReference<>(seriesCourseListActivity);
        }

        @Override // com.huawei.health.vip.api.VipCallback
        public void onSuccess(Object obj) {
            SeriesCourseListActivity seriesCourseListActivity = this.f9798a.get();
            if (seriesCourseListActivity == null || seriesCourseListActivity.isDestroyed() || seriesCourseListActivity.isFinishing()) {
                LogUtil.h("HiH_SeriesCourseListActivity", "onSuccess, but activity is destroyed");
                return;
            }
            if (obj == null) {
                LogUtil.a("HiH_SeriesCourseListActivity", "getVipInfo onSuccess result is null");
                seriesCourseListActivity.l();
                return;
            }
            UserMemberInfo userMemberInfo = obj instanceof UserMemberInfo ? (UserMemberInfo) obj : null;
            if (userMemberInfo == null || userMemberInfo.getMemberFlag() != 1) {
                LogUtil.h("HiH_SeriesCourseListActivity", "TradeViewApi memberInfo == null or != VipConstants.MEMBER_FLAG_VIP");
                seriesCourseListActivity.l();
                return;
            }
            LogUtil.a("HiH_SeriesCourseListActivity", "memberInfo: ", userMemberInfo.toString());
            if (!gpn.d(userMemberInfo)) {
                seriesCourseListActivity.c();
            } else {
                LogUtil.h("HiH_SeriesCourseListActivity", "isVipExpired");
                seriesCourseListActivity.l();
            }
        }

        @Override // com.huawei.health.vip.api.VipCallback
        public void onFailure(int i, String str) {
            LogUtil.h("HiH_SeriesCourseListActivity", "getVipInfo errorCode=", Integer.valueOf(i), " errMsg=", str);
            SeriesCourseListActivity seriesCourseListActivity = this.f9798a.get();
            if (seriesCourseListActivity != null && !seriesCourseListActivity.isDestroyed() && !seriesCourseListActivity.isFinishing()) {
                seriesCourseListActivity.l();
            } else {
                LogUtil.h("HiH_SeriesCourseListActivity", "onFailure, but activity is destroyed");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        SleepAudioSeries sleepAudioSeries;
        if (!HandlerExecutor.c()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.28
                @Override // java.lang.Runnable
                public void run() {
                    SeriesCourseListActivity.this.l();
                }
            });
            return;
        }
        if (this.ao != null) {
            LogUtil.a("HiH_SeriesCourseListActivity", "showVipToast mVipView is showed");
            return;
        }
        LogUtil.a("HiH_SeriesCourseListActivity", "showVipToast");
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi == null) {
            LogUtil.h("HiH_SeriesCourseListActivity", "getVipMessages VipApi is null");
            return;
        }
        SleepAudioPackage sleepAudioPackage = this.g;
        View vipPayView = vipApi.getVipPayView(this, "", "#/PayPopup?payResourceType=7&benefitName=" + ((sleepAudioPackage == null || (sleepAudioSeries = sleepAudioPackage.getSleepAudioSeries()) == null) ? "" : sleepAudioSeries.getName()), dqW_(getIntent()));
        this.ao = vipPayView;
        LogUtil.a("HiH_SeriesCourseListActivity", "mVipView: ", vipPayView);
        this.am.removeAllViews();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12, -1);
        this.am.addView(this.ao, layoutParams);
    }

    private Map<String, Object> dqW_(Intent intent) {
        HashMap hashMap = new HashMap(16);
        if (intent == null) {
            LogUtil.h("HiH_SeriesCourseListActivity", "getVipMap: ", "intent is null");
            return hashMap;
        }
        hashMap.put(WebViewHelp.BI_KEY_PULL_FROM, intent.getStringExtra(WebViewHelp.BI_KEY_PULL_FROM));
        hashMap.put("resourceId", intent.getStringExtra("resourceId"));
        hashMap.put("resourceName", intent.getStringExtra("resourceName"));
        hashMap.put("pullOrder", intent.getStringExtra("pullOrder"));
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.29
                @Override // java.lang.Runnable
                public void run() {
                    SeriesCourseListActivity.this.b();
                }
            });
            return;
        }
        LogUtil.a("HiH_SeriesCourseListActivity", "cancelPurchaseToast");
        TradeViewApi tradeViewApi = (TradeViewApi) Services.a("TradeService", TradeViewApi.class);
        if (tradeViewApi == null) {
            LogUtil.h("HiH_SeriesCourseListActivity", "tradeViewApi is null");
            return;
        }
        View view = this.q;
        if (view == null) {
            return;
        }
        tradeViewApi.cancelView(view);
        this.q = null;
        FrameLayout frameLayout = this.am;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.30
                @Override // java.lang.Runnable
                public void run() {
                    SeriesCourseListActivity.this.c();
                }
            });
            return;
        }
        LogUtil.a("HiH_SeriesCourseListActivity", "cancelVipToast");
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi == null) {
            LogUtil.h("HiH_SeriesCourseListActivity", "getVipMessages VipApi is null");
            return;
        }
        View view = this.ao;
        if (view == null) {
            return;
        }
        vipApi.destoryVipPayView(view);
        this.ao = null;
        FrameLayout frameLayout = this.am;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final SleepAudioSeries sleepAudioSeries, final WeakReference<SeriesCourseListActivity> weakReference) {
        String positionId = sleepAudioSeries.getPositionId();
        if (TextUtils.isEmpty(positionId)) {
            ReleaseLogUtil.d("R_SeriesCourseListActivity", "invalid position");
            return;
        }
        ReleaseLogUtil.e("R_SeriesCourseListActivity", "requestMarketResource");
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            ReleaseLogUtil.d("R_SeriesCourseListActivity", "get marketingApi failed");
            return;
        }
        try {
            final int parseInt = Integer.parseInt(positionId);
            ReleaseLogUtil.e("R_SeriesCourseListActivity", "positionId: ", Integer.valueOf(parseInt));
            marketingApi.getResourceResultInfo(parseInt).addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.3
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                    ReleaseLogUtil.e("R_SeriesCourseListActivity", "requestMarketResource onSuccess");
                    if (map == null || map.isEmpty()) {
                        ReleaseLogUtil.d("R_SeriesCourseListActivity", "marketingResponse is invalid");
                        return;
                    }
                    Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules(map);
                    if (filterMarketingRules == null || filterMarketingRules.isEmpty()) {
                        ReleaseLogUtil.d("R_SeriesCourseListActivity", "filterResultInfoMap is invalid");
                        return;
                    }
                    ResourceResultInfo resourceResultInfo = filterMarketingRules.get(Integer.valueOf(parseInt));
                    if (resourceResultInfo == null) {
                        ReleaseLogUtil.d("R_SeriesCourseListActivity", "resourceResultInfo is invalid");
                        return;
                    }
                    SeriesCourseListActivity seriesCourseListActivity = (SeriesCourseListActivity) weakReference.get();
                    if (seriesCourseListActivity != null) {
                        seriesCourseListActivity.d(resourceResultInfo, sleepAudioSeries.getPositionName(), parseInt);
                    } else {
                        ReleaseLogUtil.d("R_SeriesCourseListActivity", "requestTabInfo context is null");
                    }
                }
            });
        } catch (NumberFormatException unused) {
            LogUtil.b("HiH_SeriesCourseListActivity", "Format exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final SleepAudioPackage sleepAudioPackage) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    SeriesCourseListActivity.this.e(sleepAudioPackage);
                }
            });
            return;
        }
        if (this.x == null) {
            this.x = SeriesCourseListFragment.c(this.ap, sleepAudioPackage);
            TabLayout tabLayout = this.v;
            tabLayout.addTab(tabLayout.newTab().setText(this.c.getResources().getString(R$string.IDS_sleep_decompression_tab_title_chapter)), true);
        }
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.4
            @Override // androidx.fragment.app.FragmentPagerAdapter
            public Fragment getItem(int i) {
                return SeriesCourseListActivity.this.d(i);
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public int getCount() {
                if (SeriesCourseListActivity.this.ad && SeriesCourseListActivity.this.u) {
                    return 3;
                }
                return (SeriesCourseListActivity.this.ad || SeriesCourseListActivity.this.u) ? 2 : 1;
            }
        };
        this.y = fragmentPagerAdapter;
        this.as.setAdapter(fragmentPagerAdapter);
        this.y.notifyDataSetChanged();
        a();
    }

    private void a() {
        final SleepAudioSeries sleepAudioSeries = this.g.getSleepAudioSeries();
        if (sleepAudioSeries == null) {
            LogUtil.a("HiH_SeriesCourseListActivity", "sleepAudioSeries is null");
            return;
        }
        a(this.g);
        c(sleepAudioSeries);
        e(sleepAudioSeries);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.2
            @Override // java.lang.Runnable
            public void run() {
                SeriesCourseListActivity.this.e(sleepAudioSeries, (WeakReference<SeriesCourseListActivity>) new WeakReference(SeriesCourseListActivity.this));
            }
        });
    }

    private void e(final SleepAudioSeries sleepAudioSeries) {
        if (!this.l) {
            this.ag.setBackground(this.c.getResources().getDrawable(R$drawable.series_share_light));
        } else if (nrt.a(this.c)) {
            this.ag.setBackground(this.c.getResources().getDrawable(R$drawable.series_share_dark));
        } else {
            this.ag.setBackground(this.c.getResources().getDrawable(R$drawable.series_share_light));
        }
        this.ag.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HiH_SeriesCourseListActivity", "share is clicked");
                SeriesCourseListActivity.this.d(sleepAudioSeries);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Fragment d(int i) {
        if (this.ad && this.u) {
            if (i == 0) {
                return this.ab;
            }
            if (i == 1) {
                return this.x;
            }
            if (i == 2) {
                return this.r;
            }
        }
        if (this.ad) {
            if (i == 0) {
                return this.ab;
            }
            if (i == 1) {
                return this.x;
            }
        }
        if (this.u) {
            if (i == 0) {
                return this.x;
            }
            if (i == 1) {
                return this.r;
            }
        }
        return this.x;
    }

    private boolean c(SleepAudioPackage sleepAudioPackage) {
        if (sleepAudioPackage.getSleepAudioSeries() == null) {
            return false;
        }
        return !bkz.e(r1.getIntroductionImgs());
    }

    private void j() {
        if (!c(this.g)) {
            health.compact.a.LogUtil.c("HiH_SeriesCourseListActivity", "mData is null");
            e(this.g);
        } else {
            d();
        }
    }

    private void d() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.9
            @Override // java.lang.Runnable
            public void run() {
                if (SeriesCourseListActivity.this.g != null) {
                    SleepAudioSeries sleepAudioSeries = SeriesCourseListActivity.this.g.getSleepAudioSeries();
                    if (sleepAudioSeries == null) {
                        health.compact.a.LogUtil.c("HiH_SeriesCourseListActivity", "seriesInfo is null");
                        SeriesCourseListActivity seriesCourseListActivity = SeriesCourseListActivity.this;
                        seriesCourseListActivity.e(seriesCourseListActivity.g);
                        return;
                    }
                    List<String> introductionImgs = sleepAudioSeries.getIntroductionImgs();
                    if (koq.b(introductionImgs)) {
                        health.compact.a.LogUtil.c("HiH_SeriesCourseListActivity", "urlList is null");
                        SeriesCourseListActivity seriesCourseListActivity2 = SeriesCourseListActivity.this;
                        seriesCourseListActivity2.e(seriesCourseListActivity2.g);
                        return;
                    }
                    health.compact.a.LogUtil.c("HiH_SeriesCourseListActivity", "urlList: ", introductionImgs);
                    HashMap hashMap = new HashMap();
                    CountDownLatch countDownLatch = new CountDownLatch(introductionImgs.size());
                    for (int i = 0; i < introductionImgs.size(); i++) {
                        nrf.d(introductionImgs.get(i), SeriesCourseListActivity.this.c(hashMap, countDownLatch, introductionImgs.get(i)));
                    }
                    SeriesCourseListActivity seriesCourseListActivity3 = SeriesCourseListActivity.this;
                    seriesCourseListActivity3.b(countDownLatch, seriesCourseListActivity3, hashMap, introductionImgs);
                    return;
                }
                health.compact.a.LogUtil.c("HiH_SeriesCourseListActivity", "mData is null");
                SeriesCourseListActivity seriesCourseListActivity4 = SeriesCourseListActivity.this;
                seriesCourseListActivity4.e(seriesCourseListActivity4.g);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(CountDownLatch countDownLatch, SeriesCourseListActivity seriesCourseListActivity, Map<String, Drawable> map, List<String> list) {
        try {
            if (!countDownLatch.await(2000L, TimeUnit.MILLISECONDS)) {
                health.compact.a.LogUtil.a("HiH_SeriesCourseListActivity", "loadImage wait timeout");
                e(this.g);
                return;
            }
            health.compact.a.LogUtil.c("HiH_SeriesCourseListActivity", "loadImage complete");
            if (map.size() != list.size()) {
                health.compact.a.LogUtil.c("HiH_SeriesCourseListActivity", "load failed");
                e(this.g);
            } else {
                a(map, seriesCourseListActivity, list);
            }
        } catch (InterruptedException unused) {
            health.compact.a.LogUtil.e("HiH_SeriesCourseListActivity", "interrupted while waiting for loadImage");
            e(this.g);
        }
    }

    /* loaded from: classes6.dex */
    static class d extends CustomTarget<Drawable> {

        /* renamed from: a, reason: collision with root package name */
        private CountDownLatch f9797a;
        private Map<String, Drawable> b;
        private String e;

        d(CountDownLatch countDownLatch, String str, Map<String, Drawable> map) {
            this.e = str;
            this.f9797a = countDownLatch;
            this.b = map;
        }

        @Override // com.bumptech.glide.request.target.Target
        /* renamed from: dqZ_, reason: merged with bridge method [inline-methods] */
        public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
            health.compact.a.LogUtil.c("HiH_SeriesCourseListActivity", "onResourceReady");
            this.b.put(this.e, drawable);
            this.f9797a.countDown();
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadCleared(Drawable drawable) {
            this.f9797a.countDown();
        }

        @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
        public void onLoadFailed(Drawable drawable) {
            health.compact.a.LogUtil.c("HiH_SeriesCourseListActivity", "onLoadFailed");
            this.f9797a.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CustomTarget<Drawable> c(Map<String, Drawable> map, CountDownLatch countDownLatch, String str) {
        return new d(countDownLatch, str, map);
    }

    public void a(final Map<String, Drawable> map, final SeriesCourseListActivity seriesCourseListActivity, final List<String> list) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.6
                @Override // java.lang.Runnable
                public void run() {
                    SeriesCourseListActivity.this.a(map, seriesCourseListActivity, list);
                }
            });
            return;
        }
        LogUtil.a("HiH_SeriesCourseListActivity", "addSummaryTab");
        if (this.ab == null) {
            this.ab = SeriesCourseSummaryFragment.d(this.ap, this.g);
            TabLayout tabLayout = this.v;
            tabLayout.addTab(tabLayout.newTab().setText(this.c.getResources().getString(R$string.IDS_sleep_decompression_tab_title_summary)), 0, false);
            this.v.setVisibility(0);
            this.ad = true;
        }
        if (this.x == null) {
            this.x = SeriesCourseListFragment.c(this.ap, this.g);
            TabLayout tabLayout2 = this.v;
            tabLayout2.addTab(tabLayout2.newTab().setText(this.c.getResources().getString(R$string.IDS_sleep_decompression_tab_title_chapter)), true);
        }
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.10
            @Override // androidx.fragment.app.FragmentPagerAdapter
            public Fragment getItem(int i) {
                return SeriesCourseListActivity.this.d(i);
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public int getCount() {
                if (SeriesCourseListActivity.this.ad && SeriesCourseListActivity.this.u) {
                    return 3;
                }
                return (SeriesCourseListActivity.this.ad || SeriesCourseListActivity.this.u) ? 2 : 1;
            }
        };
        this.y = fragmentPagerAdapter;
        this.as.setAdapter(fragmentPagerAdapter);
        if (this.ad) {
            this.as.setCurrentItem(1);
            this.v.getTabAt(1).select();
        }
        this.y.notifyDataSetChanged();
        this.ab.e(map, seriesCourseListActivity, list);
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ResourceResultInfo resourceResultInfo, String str, int i) {
        ResourceBriefInfo e2 = e(resourceResultInfo);
        if (e2 == null) {
            LogUtil.a("HiH_SeriesCourseListActivity", "no valid res");
            return;
        }
        ResourceContentBase content = e2.getContent();
        if (content == null || TextUtils.isEmpty(content.getContent())) {
            LogUtil.a("HiH_SeriesCourseListActivity", "no recommend res");
            return;
        }
        int contentType = e2.getContentType();
        LogUtil.a("HiH_SeriesCourseListActivity", "contentType: ", Integer.valueOf(contentType));
        if (contentType != 21 && contentType != 20) {
            LogUtil.a("HiH_SeriesCourseListActivity", "contentType error");
            return;
        }
        MultiGridsTemplate multiGridsTemplate = (MultiGridsTemplate) nrv.b(content.getContent(), MultiGridsTemplate.class);
        if (multiGridsTemplate == null) {
            LogUtil.a("HiH_SeriesCourseListActivity", "recommend res is invalid");
            return;
        }
        List<SingleGridContent> gridContents = multiGridsTemplate.getGridContents();
        if (koq.c(gridContents)) {
            Iterator<SingleGridContent> it = gridContents.iterator();
            while (it.hasNext()) {
                eil.c(it.next(), e2);
            }
        }
        e(e2, multiGridsTemplate, str, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final ResourceBriefInfo resourceBriefInfo, final MultiGridsTemplate multiGridsTemplate, final String str, final int i) {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.7
                @Override // java.lang.Runnable
                public void run() {
                    SeriesCourseListActivity.this.e(resourceBriefInfo, multiGridsTemplate, str, i);
                }
            });
            return;
        }
        if (this.r == null) {
            RecommendFragment c2 = RecommendFragment.c(resourceBriefInfo, this.ap, i);
            this.r = c2;
            c2.d(multiGridsTemplate, resourceBriefInfo.getRecommendList());
            this.z = str;
            if (TextUtils.isEmpty(str)) {
                this.z = this.c.getResources().getString(R$string.IDS_sleep_decompression_tab_title_recommend);
            }
            try {
                TabLayout tabLayout = this.v;
                tabLayout.addTab(tabLayout.newTab().setText(this.z), false);
                this.v.setVisibility(0);
                this.u = true;
                this.y.notifyDataSetChanged();
            } catch (IndexOutOfBoundsException unused) {
                LogUtil.b("HiH_SeriesCourseListActivity", "IndexOutOfBoundsException");
                this.r = null;
            }
        }
    }

    private ResourceBriefInfo e(ResourceResultInfo resourceResultInfo) {
        if (resourceResultInfo == null) {
            LogUtil.b("HiH_SeriesCourseListActivity", "request marketing data failed");
            return null;
        }
        List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
        if (koq.b(resources)) {
            LogUtil.b("HiH_SeriesCourseListActivity", "allResourceList is empty");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (ResourceBriefInfo resourceBriefInfo : resources) {
            if (b(resourceBriefInfo)) {
                arrayList.add(resourceBriefInfo);
            }
        }
        if (arrayList.isEmpty()) {
            LogUtil.b("HiH_SeriesCourseListActivity", "resources are invalid");
            return null;
        }
        ResourceBriefInfo e2 = e(arrayList);
        if (e2 != null && e2.getContent() != null) {
            return e2;
        }
        LogUtil.b("HiH_SeriesCourseListActivity", "prioritizedResource is invalid");
        return null;
    }

    private boolean b(ResourceBriefInfo resourceBriefInfo) {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis > resourceBriefInfo.getEffectiveTime() && currentTimeMillis < resourceBriefInfo.getExpirationTime();
    }

    private ResourceBriefInfo e(List<ResourceBriefInfo> list) {
        Collections.sort(list, new Comparator<ResourceBriefInfo>() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.11
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(ResourceBriefInfo resourceBriefInfo, ResourceBriefInfo resourceBriefInfo2) {
                return resourceBriefInfo2.getPriority() - resourceBriefInfo.getPriority();
            }
        });
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(SleepAudioPackage sleepAudioPackage) {
        if (sleepAudioPackage == null) {
            LogUtil.b("HiH_SeriesCourseListActivity", "data is null");
            return;
        }
        SleepAudioSeries sleepAudioSeries = sleepAudioPackage.getSleepAudioSeries();
        if (sleepAudioSeries == null) {
            LogUtil.a("HiH_SeriesCourseListActivity", "sleepAudioSeries is null");
            return;
        }
        String backGroundImg = sleepAudioSeries.getBackGroundImg();
        this.an = backGroundImg;
        nrf.cIS_(this.al, backGroundImg, 0, 0, R$color.no_fragment_title_bgcolor_alpha);
        String name = sleepAudioSeries.getName();
        this.af = name;
        HealthTextView healthTextView = this.i;
        if (healthTextView != null) {
            healthTextView.setText(name);
        }
        if (this.d != null) {
            String e2 = ffv.e(sleepAudioSeries);
            if (TextUtils.isEmpty(e2)) {
                this.d.setVisibility(8);
            } else {
                ffv.a(this.d, e2);
            }
        }
        if (this.j != null) {
            String e3 = ffv.e(sleepAudioSeries, ffv.a(sleepAudioPackage));
            if (TextUtils.isEmpty(e3)) {
                this.h.setVisibility(8);
                return;
            }
            this.h.setVisibility(0);
            this.j.setText(e3);
            this.f.setBackground(this.c.getResources().getDrawable(R$drawable.course_play_number_img));
        }
    }

    private void e(final boolean z) {
        ReleaseLogUtil.e("R_SeriesCourseListActivity", "loadData");
        final CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            ReleaseLogUtil.d("R_SeriesCourseListActivity", "mCourseApi not found");
            o();
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.15
                @Override // java.lang.Runnable
                public void run() {
                    ReleaseLogUtil.e("R_SeriesCourseListActivity", "start queryAudiosPackageBySeriesId");
                    courseApi.queryAudiosPackageBySeriesId(SeriesCourseListActivity.this.ap, new a(SeriesCourseListActivity.this, z));
                }
            });
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        LogUtil.a("HiH_SeriesCourseListActivity", "onNewIntent");
        super.onNewIntent(intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        LogUtil.a("HiH_SeriesCourseListActivity", "onConfigurationChanged");
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        a(0);
    }

    private void a(final int i) {
        HandlerExecutor.d(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.12
            @Override // java.lang.Runnable
            public void run() {
                jdx.b(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.12.5
                    @Override // java.lang.Runnable
                    public void run() {
                        ffv.c(SeriesCourseListActivity.this.g, SeriesCourseListActivity.this.w, SeriesCourseListActivity.this.m, i);
                    }
                });
            }
        }, 2000L);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        a(1);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("HiH_SeriesCourseListActivity", "onDestroy");
        super.onDestroy();
        nsy.cMf_(this.am, this.e);
        c();
    }

    private void g() {
        LogUtil.a("HiH_SeriesCourseListActivity", "initNetWorkLayout");
        this.s = (ViewGroup) findViewById(R.id.no_network_layout);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.loading_layout);
        this.t = relativeLayout;
        relativeLayout.setVisibility(0);
    }

    private boolean e() {
        if (!CommonUtil.aa(this)) {
            LogUtil.a("HiH_SeriesCourseListActivity", "not isNetworkConnected");
            b(1);
            return false;
        }
        n();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.13
                @Override // java.lang.Runnable
                public void run() {
                    SeriesCourseListActivity.this.n();
                }
            });
            return;
        }
        LogUtil.a("HiH_SeriesCourseListActivity", "showLoadingLayout");
        this.s.setVisibility(8);
        this.t.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        LogUtil.a("HiH_SeriesCourseListActivity", "showScrollView");
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.20
                @Override // java.lang.Runnable
                public void run() {
                    SeriesCourseListActivity.this.o();
                }
            });
        } else {
            this.t.setVisibility(8);
            this.s.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final int i) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.17
                @Override // java.lang.Runnable
                public void run() {
                    SeriesCourseListActivity.this.b(i);
                }
            });
            return;
        }
        LogUtil.a("HiH_SeriesCourseListActivity", "no network");
        this.s.setVisibility(0);
        this.t.setVisibility(8);
        HealthTextView healthTextView = (HealthTextView) this.s.findViewById(R.id.tips_no_net_work);
        if (i == 1) {
            nsy.cMq_(healthTextView, com.huawei.health.servicesui.R$string.IDS_hwh_home_group_network_disconnection);
        } else {
            if (i != 2) {
                return;
            }
            nsy.cMq_(healthTextView, com.huawei.health.servicesui.R$string.IDS_heart_rate_measuring_status_data_fail);
        }
    }

    private void f() {
        LogUtil.a("HiH_SeriesCourseListActivity", "initTitleBar");
        this.aa.setBackground(ContextCompat.getDrawable(this.c, R.drawable._2131427885_res_0x7f0b022d));
        int color = ContextCompat.getColor(this.c, R.color._2131299296_res_0x7f090be0);
        this.ac = color;
        this.ak.setBackgroundColor(color);
        this.aa.setClickable(true);
        this.aa.setOnClickListener(new AnonymousClass18());
        this.ae.setVisibility(4);
    }

    /* renamed from: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity$18, reason: invalid class name */
    public class AnonymousClass18 implements View.OnClickListener {
        AnonymousClass18() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            njn.cvi_(SeriesCourseListActivity.this.q, new IBaseResponseCallback() { // from class: plu
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    SeriesCourseListActivity.AnonymousClass18.this.e(i, obj);
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }

        public /* synthetic */ void e(int i, Object obj) {
            if (i == 0) {
                SeriesCourseListActivity.this.finish();
            }
        }
    }

    private void c(SleepAudioSeries sleepAudioSeries) {
        this.k = ffv.e(sleepAudioSeries.getUserAttributes().getFavorites());
        if (this.k) {
            this.ae.setBackground(this.c.getResources().getDrawable(R$drawable.course_favorite_selected));
            this.ae.setVisibility(0);
            this.ae.setOnClickListener(new c(this));
        } else {
            this.ae.setBackground(this.c.getResources().getDrawable(R$drawable.course_favorite));
            this.ae.setVisibility(0);
            this.ae.setOnClickListener(new c(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (!this.l) {
            if (this.k) {
                this.ae.setBackground(this.c.getResources().getDrawable(R$drawable.course_favorite_selected));
            } else {
                this.ae.setBackground(this.c.getResources().getDrawable(R$drawable.course_favorite));
            }
            this.ag.setBackground(this.c.getResources().getDrawable(R$drawable.series_share_light));
            return;
        }
        if (nrt.a(this.c)) {
            return;
        }
        if (this.k) {
            this.ae.setBackground(this.c.getResources().getDrawable(R$drawable.course_favorite_selected_dark));
        } else {
            this.ae.setBackground(this.c.getResources().getDrawable(R$drawable.course_favorite_dark));
        }
        this.ag.setBackground(this.c.getResources().getDrawable(R$drawable.series_share_dark));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final SleepAudioSeries sleepAudioSeries) {
        final fdu fduVar = new fdu(2);
        fduVar.b(0);
        fduVar.b((Map<String, Object>) new HashMap(10));
        fduVar.b("series_course");
        String shareTitle = sleepAudioSeries.getShareTitle();
        String shareDescription = sleepAudioSeries.getShareDescription();
        if (TextUtils.isEmpty(shareTitle)) {
            shareTitle = sleepAudioSeries.getName();
        }
        if (TextUtils.isEmpty(shareDescription)) {
            shareDescription = "";
        }
        if (TextUtils.isEmpty(shareTitle) && TextUtils.isEmpty(shareDescription)) {
            LogUtil.h("HiH_SeriesCourseListActivity", "getSharedLink, title or des is empty");
            nrh.b(com.huawei.haf.application.BaseApplication.e(), R$string.IDS_motiontrack_share_fail_tip);
            return;
        }
        fduVar.c(shareTitle);
        fduVar.a(shareDescription);
        final String shareIcon = sleepAudioSeries.getShareIcon();
        if (TextUtils.isEmpty(shareIcon)) {
            shareIcon = sleepAudioSeries.getImgIcon();
        }
        if (TextUtils.isEmpty(shareIcon)) {
            dqX_(fduVar, sleepAudioSeries, dqV_());
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.19
                @Override // java.lang.Runnable
                public void run() {
                    Bitmap cHT_ = nrf.cHT_(SeriesCourseListActivity.this.c, shareIcon);
                    if (cHT_ != null) {
                        SeriesCourseListActivity.this.dqX_(fduVar, sleepAudioSeries, cHT_);
                    } else {
                        SeriesCourseListActivity seriesCourseListActivity = SeriesCourseListActivity.this;
                        seriesCourseListActivity.dqX_(fduVar, sleepAudioSeries, seriesCourseListActivity.dqV_());
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dqX_(final fdu fduVar, final SleepAudioSeries sleepAudioSeries, Bitmap bitmap) {
        fduVar.awp_(bitmap);
        final GrsQueryCallback grsQueryCallback = new GrsQueryCallback() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.16
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                SeriesCourseListActivity.this.d(str, fduVar, sleepAudioSeries);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.b("HiH_SeriesCourseListActivity", "get url failed, res: ", Integer.valueOf(i));
                SeriesCourseListActivity.this.d("", fduVar, sleepAudioSeries);
            }
        };
        asc.e().b(new Runnable() { // from class: plp
            @Override // java.lang.Runnable
            public final void run() {
                GRSManager.a(com.huawei.haf.application.BaseApplication.e()).e("domainContentcenterDbankcdnNew", GrsQueryCallback.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final String str, final fdu fduVar, final SleepAudioSeries sleepAudioSeries) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity.21
                @Override // java.lang.Runnable
                public void run() {
                    SeriesCourseListActivity.this.d(str, fduVar, sleepAudioSeries);
                }
            });
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HiH_SeriesCourseListActivity", "getSharedLink, url is empty");
            nrh.b(com.huawei.haf.application.BaseApplication.e(), R$string.IDS_motiontrack_share_fail_tip);
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (CommonUtil.bv()) {
            sb.append(str);
            sb.append("/cch5/health/com.huawei.health.h5.sleeping-music/static/dist/index.html#/shareSeries?seriesId=");
            sb.append(sleepAudioSeries.getId());
        } else if (CommonUtil.cc()) {
            sb.append(str);
            sb.append("/sandbox/cch5/testappCCH5/com.huawei.health.h5.sleeping-music/static/dist/index.html#/shareSeries?seriesId=");
            sb.append(sleepAudioSeries.getId());
        } else {
            sb.append(str);
            sb.append("/sandbox/cch5/health/com.huawei.health.h5.sleeping-music/static/dist/index.html#/shareSeries?seriesId=");
            sb.append(sleepAudioSeries.getId());
        }
        LogUtil.a("HiH_SeriesCourseListActivity", "url: ", sb.toString());
        fduVar.f(sb.toString());
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.BI_MODULE_ID, "114000117");
        hashMap.put("name", fduVar.t());
        fduVar.b((Map<String, Object>) hashMap);
        ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(fduVar, BaseApplication.getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap dqV_() {
        Drawable loadIcon = com.huawei.haf.application.BaseApplication.e().getApplicationInfo().loadIcon(com.huawei.haf.application.BaseApplication.e().getPackageManager());
        if (!(loadIcon instanceof BitmapDrawable)) {
            LogUtil.h("HiH_SeriesCourseListActivity", "getAppIcon, drawable is not BitmapDrawable");
            return null;
        }
        LogUtil.a("HiH_SeriesCourseListActivity", "getAppIcon, app icon");
        return ((BitmapDrawable) loadIcon).getBitmap();
    }

    private String i() {
        SleepAudioPackage sleepAudioPackage = this.g;
        if (sleepAudioPackage == null) {
            LogUtil.b("HiH_SeriesCourseListActivity", "data is null");
            return "";
        }
        SleepAudioSeries sleepAudioSeries = sleepAudioPackage.getSleepAudioSeries();
        if (sleepAudioSeries == null) {
            LogUtil.a("HiH_SeriesCourseListActivity", "sleepAudioSeries is null");
            return "";
        }
        return sleepAudioSeries.getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        try {
            if (z) {
                courseApi.operateFavoriteAudio(Integer.parseInt(this.ap), 1, new b(this, 1));
            } else {
                courseApi.operateFavoriteAudio(Integer.parseInt(this.ap), 2, new b(this, 2));
            }
        } catch (NumberFormatException unused) {
            LogUtil.b("HiH_SeriesCourseListActivity", "Format exception");
        }
    }

    /* loaded from: classes6.dex */
    public static class e extends UiCallback<Boolean> {
        private WeakReference<SeriesCourseListActivity> d;

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
        }

        e(SeriesCourseListActivity seriesCourseListActivity) {
            this.d = new WeakReference<>(seriesCourseListActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final Boolean bool) {
            final SeriesCourseListActivity seriesCourseListActivity = this.d.get();
            if (seriesCourseListActivity != null) {
                seriesCourseListActivity.k = bool.booleanValue();
                HandlerExecutor.e(new Runnable() { // from class: plv
                    @Override // java.lang.Runnable
                    public final void run() {
                        SeriesCourseListActivity.this.d(bool.booleanValue());
                    }
                });
                HandlerExecutor.e(new Runnable() { // from class: plw
                    @Override // java.lang.Runnable
                    public final void run() {
                        SeriesCourseListActivity.this.m();
                    }
                });
                return;
            }
            LogUtil.b("HiH_SeriesCourseListActivity", "FavoriteChangeCallback activity == null");
        }
    }

    /* loaded from: classes6.dex */
    static class c implements View.OnClickListener {
        private WeakReference<SeriesCourseListActivity> b;

        c(SeriesCourseListActivity seriesCourseListActivity) {
            this.b = new WeakReference<>(seriesCourseListActivity);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("HiH_SeriesCourseListActivity", "favorite is clicked");
            SeriesCourseListActivity seriesCourseListActivity = this.b.get();
            if (seriesCourseListActivity != null) {
                if (!seriesCourseListActivity.n) {
                    seriesCourseListActivity.n = true;
                    seriesCourseListActivity.d(!seriesCourseListActivity.k);
                    seriesCourseListActivity.k = true ^ seriesCourseListActivity.k;
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("name", i());
        ixx.d().d(this.c, "2030107", hashMap, 0);
    }

    /* loaded from: classes6.dex */
    static class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {
        private SeriesCourseListActivity c;
        private State d = State.IDLE;

        enum State {
            EXPANDED,
            COLLAPSED,
            IDLE
        }

        public AppBarStateChangeListener(SeriesCourseListActivity seriesCourseListActivity) {
            this.c = seriesCourseListActivity;
        }

        @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
        public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            if (i == 0) {
                if (this.d != State.EXPANDED) {
                    LogUtil.a("HiH_SeriesCourseListActivity", "EXPANDED");
                    this.c.c(false);
                }
                this.d = State.EXPANDED;
                return;
            }
            if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
                if (this.d != State.COLLAPSED) {
                    LogUtil.a("HiH_SeriesCourseListActivity", "COLLAPSED");
                    this.c.c(true);
                }
                this.d = State.COLLAPSED;
                return;
            }
            if (this.d != State.IDLE) {
                LogUtil.a("HiH_SeriesCourseListActivity", "IDLE");
            }
            this.d = State.IDLE;
        }
    }

    /* loaded from: classes6.dex */
    static class b extends UiCallback<Boolean> {
        private WeakReference<SeriesCourseListActivity> b;
        private int d;

        public b(SeriesCourseListActivity seriesCourseListActivity, int i) {
            this.b = new WeakReference<>(seriesCourseListActivity);
            this.d = i;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Boolean bool) {
            SeriesCourseListActivity seriesCourseListActivity = this.b.get();
            if (seriesCourseListActivity == null) {
                LogUtil.a("HiH_SeriesCourseListActivity", "onSuccess: activity is null");
                return;
            }
            int i = this.d;
            if (i == 2) {
                seriesCourseListActivity.n = false;
                LogUtil.a("HiH_SeriesCourseListActivity", "cancel favorite success: ", "delete");
                if (seriesCourseListActivity.l && !nrt.a(seriesCourseListActivity.c)) {
                    seriesCourseListActivity.ae.setBackground(ContextCompat.getDrawable(seriesCourseListActivity, R$drawable.course_favorite_dark));
                } else {
                    seriesCourseListActivity.ae.setBackground(ContextCompat.getDrawable(seriesCourseListActivity, R$drawable.course_favorite));
                }
                seriesCourseListActivity.e(0);
                return;
            }
            if (i == 1) {
                seriesCourseListActivity.n = false;
                LogUtil.a("HiH_SeriesCourseListActivity", "favorite success: ", "add");
                if (seriesCourseListActivity.l && !nrt.a(seriesCourseListActivity.c)) {
                    seriesCourseListActivity.ae.setBackground(ContextCompat.getDrawable(seriesCourseListActivity, R$drawable.course_favorite_selected_dark));
                } else {
                    seriesCourseListActivity.ae.setBackground(ContextCompat.getDrawable(seriesCourseListActivity, R$drawable.course_favorite_selected));
                }
                seriesCourseListActivity.e(1);
                return;
            }
            LogUtil.b("HiH_SeriesCourseListActivity", "invalid type");
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            SeriesCourseListActivity seriesCourseListActivity = this.b.get();
            if (seriesCourseListActivity == null) {
                LogUtil.a("HiH_SeriesCourseListActivity", "onFailure: activity is null");
                return;
            }
            int i2 = this.d;
            if (i2 == 2) {
                seriesCourseListActivity.n = false;
                LogUtil.a("HiH_SeriesCourseListActivity", Integer.valueOf(i), " : ", str);
            } else if (i2 == 1) {
                seriesCourseListActivity.n = false;
                LogUtil.a("HiH_SeriesCourseListActivity", Integer.valueOf(i), " : ", str);
            } else {
                LogUtil.b("HiH_SeriesCourseListActivity", "invalid type");
            }
        }
    }

    /* loaded from: classes6.dex */
    static class a extends UiCallback<List<SleepAudioPackage>> {
        private WeakReference<SeriesCourseListActivity> b;
        private boolean e;

        a(SeriesCourseListActivity seriesCourseListActivity, boolean z) {
            this.b = new WeakReference<>(seriesCourseListActivity);
            this.e = z;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("HiH_SeriesCourseListActivity", "queryAudiosPackageBySeriesId fail, errorCode： ", Integer.valueOf(i), ", errorInfo: ", str);
            SeriesCourseListActivity seriesCourseListActivity = this.b.get();
            if (seriesCourseListActivity == null) {
                return;
            }
            seriesCourseListActivity.o();
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<SleepAudioPackage> list) {
            ReleaseLogUtil.e("R_SeriesCourseListActivity", " SeriesDetailUiCallback onSuccess");
            SeriesCourseListActivity seriesCourseListActivity = this.b.get();
            if (seriesCourseListActivity == null) {
                ReleaseLogUtil.d("R_SeriesCourseListActivity", " SeriesDetailUiCallback activity is null");
                return;
            }
            if (!bkz.e(list)) {
                seriesCourseListActivity.w = SystemClock.elapsedRealtime();
                seriesCourseListActivity.g = list.get(0);
                SleepAudioSeries sleepAudioSeries = seriesCourseListActivity.g.getSleepAudioSeries();
                if (sleepAudioSeries != null) {
                    sleepAudioSeries.setFrom(seriesCourseListActivity.m);
                    Intent intent = seriesCourseListActivity.getIntent();
                    if (intent != null) {
                        sleepAudioSeries.setPullFrom(intent.getStringExtra(WebViewHelp.BI_KEY_PULL_FROM));
                        sleepAudioSeries.setResourceId(intent.getStringExtra("resourceId"));
                        sleepAudioSeries.setResourceName(intent.getStringExtra("resourceName"));
                    }
                }
                if (this.e) {
                    seriesCourseListActivity.p = sleepAudioSeries.getPurchasedStatus();
                    seriesCourseListActivity.d(seriesCourseListActivity.g);
                    seriesCourseListActivity.o();
                    return;
                } else {
                    seriesCourseListActivity.g(seriesCourseListActivity.g);
                    seriesCourseListActivity.b(seriesCourseListActivity.g);
                    seriesCourseListActivity.a(seriesCourseListActivity.g);
                    return;
                }
            }
            ReleaseLogUtil.e("R_SeriesCourseListActivity", "data is empty");
            seriesCourseListActivity.o();
        }
    }

    private void k() {
        if (this.e == null) {
            f fVar = new f(this);
            this.e = fVar;
            nsy.cMa_(this.am, fVar);
        }
    }

    /* loaded from: classes6.dex */
    static class f implements ViewTreeObserver.OnGlobalLayoutListener {
        private final WeakReference<SeriesCourseListActivity> e;

        public f(SeriesCourseListActivity seriesCourseListActivity) {
            this.e = new WeakReference<>(seriesCourseListActivity);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            SeriesCourseListActivity seriesCourseListActivity = this.e.get();
            if (seriesCourseListActivity == null || seriesCourseListActivity.isDestroyed()) {
                return;
            }
            LogUtil.a("R_SeriesCourseListActivity", " ViewTreeObserverListener activity.mToastContainer.getHeight() = ", Integer.valueOf(seriesCourseListActivity.am.getHeight()));
            if (seriesCourseListActivity.am.getHeight() != seriesCourseListActivity.mMinibarBottomMargin) {
                seriesCourseListActivity.mMinibarBottomMargin = seriesCourseListActivity.am.getHeight();
                seriesCourseListActivity.updateMinibar();
            }
        }
    }
}
