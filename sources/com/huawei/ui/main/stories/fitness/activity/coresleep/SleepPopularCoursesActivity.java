package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceContentBase;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.scrollview.ScrollViewListener;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepPopularCoursesActivity;
import com.huawei.ui.main.stories.fitness.activity.coresleep.model.SingleTab;
import com.huawei.ui.main.stories.fitness.activity.coresleep.model.SleepCourseBean;
import com.huawei.ui.main.stories.fitness.activity.coresleep.model.SleepSeriesCourseBean;
import defpackage.ixu;
import defpackage.koq;
import defpackage.mfm;
import defpackage.nqx;
import defpackage.nrt;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class SleepPopularCoursesActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private CustomTitleBar f9826a;
    private HealthScrollView b;
    private String c;
    private Context d;
    private HealthTextView e;
    private ViewGroup f;
    private nqx h;
    private int i;
    private HealthSubTabWidget l;
    private HealthTextView m;
    private LinearLayout o;
    private boolean g = true;
    private final ScrollViewListener j = new ScrollViewListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepPopularCoursesActivity.4
        @Override // com.huawei.ui.commonui.scrollview.ScrollViewListener
        public void onScrollChanged(HealthScrollView healthScrollView, int i, int i2, int i3, int i4) {
            if (((int) (SleepPopularCoursesActivity.this.f9826a.getMeasuredHeight() * 1.0E-4d)) > i2) {
                SleepPopularCoursesActivity.this.o.setVisibility(0);
                SleepPopularCoursesActivity.this.f9826a.setTitleText("");
            } else {
                SleepPopularCoursesActivity.this.o.setVisibility(8);
                SleepPopularCoursesActivity.this.f9826a.setTitleText(SleepPopularCoursesActivity.this.c);
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("SleepPopularCoursesActivity", "onCreate");
        super.onCreate(bundle);
        this.d = this;
        cancelAdaptRingRegion();
        setContentView(R.layout.activity_sleep_popular_courses);
        Intent intent = getIntent();
        if (intent == null) {
            ReleaseLogUtil.c("SleepPopularCoursesActivity", "intent is null");
            return;
        }
        this.i = intent.getIntExtra(CommonUtil.PAGE_TYPE, 0);
        d();
        c();
        b();
        if (e()) {
            a();
        }
    }

    private void d() {
        LogUtil.a("SleepPopularCoursesActivity", "initView");
        ImageView imageView = (ImageView) mfm.cgL_(this, R.id.sleep_course_background_image);
        if (nrt.a(this.d)) {
            imageView.setImageResource(R.drawable.ic_popular_courses_dark_background);
        } else {
            imageView.setImageResource(R.drawable.ic_popular_courses_background);
        }
        this.f = (ViewGroup) findViewById(R.id.no_network_layout);
        CustomTitleBar customTitleBar = (CustomTitleBar) mfm.cgL_(this, R.id.sleep_popular_courses_title_bar);
        this.f9826a = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
        this.f9826a.setLeftButtonTextColor(ContextCompat.getDrawable(this.d, R.drawable._2131427885_res_0x7f0b022d), getColor(R.color._2131297618_res_0x7f090552), nsf.h(R$string.accessibility_go_back));
        this.o = (LinearLayout) mfm.cgL_(this, R.id.popular_courses_top_layout);
        this.e = (HealthTextView) mfm.cgL_(this, R.id.popular_courses_text);
        this.m = (HealthTextView) mfm.cgL_(this, R.id.popular_courses_sub_text);
        HealthScrollView healthScrollView = (HealthScrollView) mfm.cgL_(this, R.id.series_course_scroll_view);
        this.b = healthScrollView;
        healthScrollView.setScrollViewListener(this.j);
        this.l = (HealthSubTabWidget) findViewById(R.id.series_course_sub_tab);
        HealthViewPager healthViewPager = (HealthViewPager) findViewById(R.id.series_course_sub_tab_vp);
        healthViewPager.setIsAutoHeight(true);
        this.h = new nqx(this, healthViewPager, this.l);
        healthViewPager.setIsAutoHeight(true);
        healthViewPager.addOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepPopularCoursesActivity.2
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                LogUtil.a("SleepPopularCoursesActivity", "page is selected: ", Integer.valueOf(i));
            }
        });
    }

    private void c() {
        LogUtil.a("SleepPopularCoursesActivity", "initListener");
        this.f9826a.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: pok
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepPopularCoursesActivity.this.drm_(view);
            }
        });
    }

    public /* synthetic */ void drm_(View view) {
        if (nsn.a(500)) {
            LogUtil.b("SleepPopularCoursesActivity", "click title bar left button too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            finish();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void a() {
        final int i;
        LogUtil.a("SleepPopularCoursesActivity", "requestData");
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            ReleaseLogUtil.c("SleepPopularCoursesActivity", "marketingApi is null");
            return;
        }
        int i2 = this.i;
        if (i2 == 1) {
            i = 4055;
        } else if (i2 == 3) {
            i = 4057;
        } else {
            if (i2 != 30) {
                LogUtil.b("SleepPopularCoursesActivity", "mPageType is invalid");
                return;
            }
            i = 4059;
        }
        marketingApi.getResourceResultInfo(i).addOnSuccessListener(new OnSuccessListener() { // from class: pof
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                SleepPopularCoursesActivity.this.d(i, marketingApi, (Map) obj);
            }
        });
    }

    public /* synthetic */ void d(int i, MarketingApi marketingApi, Map map) {
        if (map == null) {
            LogUtil.b("SleepPopularCoursesActivity", "no section map for ", Integer.valueOf(i));
            return;
        }
        Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules((Map<Integer, ResourceResultInfo>) map);
        if (filterMarketingRules == null) {
            LogUtil.b("SleepPopularCoursesActivity", "no section map for ", Integer.valueOf(i), ", after ruling");
            return;
        }
        ResourceResultInfo resourceResultInfo = filterMarketingRules.get(Integer.valueOf(i));
        if (resourceResultInfo == null) {
            LogUtil.b("SleepPopularCoursesActivity", "no resourceResultInfo for ", Integer.valueOf(i));
            return;
        }
        List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
        if (koq.b(resources)) {
            LogUtil.b("SleepPopularCoursesActivity", "no briefInfoList for ", Integer.valueOf(i));
        } else {
            c(resources);
        }
    }

    private void c(List<ResourceBriefInfo> list) {
        LogUtil.a("SleepPopularCoursesActivity", "parseBriefInfo");
        LogUtil.a("SleepPopularCoursesActivity", "briefInfoList.size() = ", Integer.valueOf(list.size()), " briefInfoList = ", list);
        ResourceContentBase content = list.get(0).getContent();
        if (content == null) {
            LogUtil.b("SleepPopularCoursesActivity", "requestData failed cause contentBase is null!");
            return;
        }
        String content2 = content.getContent();
        if (TextUtils.isEmpty(content2)) {
            LogUtil.b("SleepPopularCoursesActivity", "requestData failed cause content is empty!");
            return;
        }
        LogUtil.a("SleepPopularCoursesActivity", "content = ", content2);
        SleepCourseBean sleepCourseBean = (SleepCourseBean) ixu.e(content2, new TypeToken<SleepCourseBean>() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepPopularCoursesActivity.5
        });
        if (sleepCourseBean == null) {
            LogUtil.b("SleepPopularCoursesActivity", "sleepBean is null");
        } else {
            LogUtil.a("SleepPopularCoursesActivity", "sleepBean = ", sleepCourseBean);
            a(sleepCourseBean);
        }
    }

    private void a(SleepCourseBean sleepCourseBean) {
        LogUtil.a("SleepPopularCoursesActivity", "handleData");
        List<SleepSeriesCourseBean> gridContents = sleepCourseBean.getGridContents();
        List<SingleTab> tabs = sleepCourseBean.getTabs();
        if (koq.b(gridContents)) {
            LogUtil.b("SleepPopularCoursesActivity", "seriesBeans is null");
            return;
        }
        if (koq.b(tabs)) {
            LogUtil.a("SleepPopularCoursesActivity", "singleTabs is null");
            this.g = false;
        }
        this.c = sleepCourseBean.getName();
        String subTitle = sleepCourseBean.getSubTitle();
        this.e.setText(this.c);
        this.m.setText(subTitle);
        if (!this.g) {
            a(gridContents);
            return;
        }
        List<List<SleepSeriesCourseBean>> a2 = a(gridContents, tabs);
        int size = tabs.size();
        for (int i = 0; i < size; i++) {
            List<SleepSeriesCourseBean> list = a2.get(i);
            LogUtil.a("SleepPopularCoursesActivity", "seriesCourseBeans: ", list, "+ i ", Integer.valueOf(i));
            a(list);
        }
    }

    private void a(final List<SleepSeriesCourseBean> list) {
        LogUtil.a("SleepPopularCoursesActivity", "initFragment");
        if (!HandlerExecutor.c()) {
            HandlerExecutor.a(new Runnable() { // from class: poh
                @Override // java.lang.Runnable
                public final void run() {
                    SleepPopularCoursesActivity.this.d(list);
                }
            });
        } else if (list == null) {
            LogUtil.b("SleepPopularCoursesActivity", "data is null");
        } else {
            b(list);
        }
    }

    public /* synthetic */ void d(List list) {
        a((List<SleepSeriesCourseBean>) list);
    }

    private void b(List<SleepSeriesCourseBean> list) {
        LogUtil.a("SleepPopularCoursesActivity", "initSubTab");
        SeriesCourseTabFragment c = SeriesCourseTabFragment.c(list);
        if (this.g) {
            this.h.c(this.l.c(list.get(0).getTabName()), c, false);
            this.l.setVisibility(0);
        } else {
            this.h.c(this.l.c(""), c, false);
            this.l.setVisibility(8);
        }
    }

    private List<List<SleepSeriesCourseBean>> a(List<SleepSeriesCourseBean> list, List<SingleTab> list2) {
        LogUtil.a("SleepPopularCoursesActivity", "parseTabList");
        ArrayList arrayList = new ArrayList();
        int size = list2.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(list2.get(i));
        }
        Collections.sort(arrayList, new Comparator() { // from class: poi
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return SleepPopularCoursesActivity.a((SingleTab) obj, (SingleTab) obj2);
            }
        });
        int size2 = list.size();
        ArrayList arrayList2 = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            String tabName = ((SingleTab) arrayList.get(i2)).getTabName();
            ArrayList arrayList3 = new ArrayList();
            for (int i3 = 0; i3 < size2; i3++) {
                if (list.get(i3).getTabName().equals(tabName)) {
                    arrayList3.add(list.get(i3));
                }
            }
            arrayList2.add(arrayList3);
        }
        LogUtil.a("SleepPopularCoursesActivity", "packageList: ", arrayList2);
        return arrayList2;
    }

    public static /* synthetic */ int a(SingleTab singleTab, SingleTab singleTab2) {
        return singleTab2.getTabPriority() - singleTab.getTabPriority();
    }

    private void b() {
        LogUtil.a("SleepPopularCoursesActivity", "initNetWorkLayout");
        this.f.setOnClickListener(new View.OnClickListener() { // from class: pon
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepPopularCoursesActivity.this.drn_(view);
            }
        });
    }

    public /* synthetic */ void drn_(View view) {
        f();
        if (e()) {
            a();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean e() {
        LogUtil.a("SleepPopularCoursesActivity", "checkNetWorkConnected");
        if (!health.compact.a.CommonUtil.aa(this.d)) {
            a(1);
            return false;
        }
        f();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogUtil.a("SleepPopularCoursesActivity", "showLoadingLayout");
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: poj
                @Override // java.lang.Runnable
                public final void run() {
                    SleepPopularCoursesActivity.this.f();
                }
            });
        } else {
            this.f.setVisibility(8);
            this.b.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void a(final int i) {
        LogUtil.a("SleepPopularCoursesActivity", "showNetWorkLayout");
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: pog
                @Override // java.lang.Runnable
                public final void run() {
                    SleepPopularCoursesActivity.this.a(i);
                }
            });
            return;
        }
        this.f.setVisibility(0);
        this.b.setVisibility(8);
        HealthTextView healthTextView = (HealthTextView) this.f.findViewById(R.id.tips_no_net_work);
        if (i == 1) {
            nsy.cMq_(healthTextView, R$string.IDS_hwh_home_group_network_disconnection);
        } else {
            if (i != 2) {
                return;
            }
            nsy.cMq_(healthTextView, R$string.IDS_heart_rate_measuring_status_data_fail);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
