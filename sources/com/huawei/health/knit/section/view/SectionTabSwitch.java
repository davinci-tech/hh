package com.huawei.health.knit.section.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.model.KnitPageConfig;
import com.huawei.health.knit.model.KnitPageGroupConfig;
import com.huawei.health.knit.model.KnitSubPageConfig;
import com.huawei.health.knit.section.listener.BasePageResTrigger;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.tablayout.HealthTabLayout;
import com.huawei.ui.commonui.viewpager.HealthNativeViewPager;
import defpackage.eab;
import defpackage.ixx;
import defpackage.koq;
import defpackage.moj;
import defpackage.nrf;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes3.dex */
public class SectionTabSwitch extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f2745a;
    private String b;
    private String c;
    private List<Integer> d;
    private boolean e;
    private HealthTabLayout f;
    private HealthCardView g;
    private int h;
    private HealthImageView i;
    private HealthImageView j;
    private d l;
    private View m;
    private HealthNativeViewPager n;
    private Drawable o;

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected boolean isSupportSafeRegion() {
        return false;
    }

    public SectionTabSwitch(Context context) {
        this(context, null);
    }

    public SectionTabSwitch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionTabSwitch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        b();
        return this.m;
    }

    private void b() {
        if (this.m == null) {
            try {
                LogUtil.h("SectionTabSwitch", "initView mainView is null, start to inflate");
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.section_tab_switch, (ViewGroup) this, false);
                this.m = inflate;
                this.f = (HealthTabLayout) inflate.findViewById(R.id.section_tab_switch_tablayout);
                HealthCardView healthCardView = (HealthCardView) this.m.findViewById(R.id.tab_card_view);
                this.g = healthCardView;
                if (healthCardView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
                    int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
                    int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.g.getLayoutParams();
                    marginLayoutParams.setMarginStart(dimensionPixelSize + ((Integer) safeRegionWidth.first).intValue());
                    marginLayoutParams.setMarginEnd(dimensionPixelSize2 + ((Integer) safeRegionWidth.second).intValue());
                    this.g.setLayoutParams(marginLayoutParams);
                }
                HealthNativeViewPager healthNativeViewPager = (HealthNativeViewPager) this.m.findViewById(R.id.section_tab_switch_view_pager);
                this.n = healthNativeViewPager;
                healthNativeViewPager.setOffscreenPageLimit(1);
                this.n.setIsAutoAdjustItemHeight(true);
                LogUtil.h("SectionTabSwitch", "initView mainView is null, end to inflate");
            } catch (IllegalArgumentException e) {
                LogUtil.b("SectionTabSwitch", "initSectionWebView exception. msg = ", e.getMessage());
            } catch (NullPointerException e2) {
                LogUtil.b("SectionTabSwitch", "NullPointerException occurs. msg = ", e2.getMessage());
            }
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionTabSwitch", "start to bindViewParams, ", this);
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionTabSwitch", "no need to bind");
            return;
        }
        c(hashMap);
        if (!this.e) {
            this.e = true;
            a();
        } else {
            f();
        }
        j();
        i();
        LogUtil.a("SectionTabSwitch", "end to bind");
    }

    private void c(HashMap<String, Object> hashMap) {
        this.c = nru.b(hashMap, "LEFT_ICON_TEXT", "");
        this.f2745a = nru.d(hashMap, "RIGHT_ICON_IMAGE", Integer.class, new ArrayList());
        this.b = nru.b(hashMap, "RIGHT_ICON_TEXT", "");
        this.d = nru.d(hashMap, "ITEM_STATUS_BACKGROUND", Integer.class, new ArrayList());
        this.o = nru.cKj_(hashMap, "ITEM_TAG", null);
        this.h = nru.d((Map) hashMap, "ITEM_RIGHT_BTN", 3);
    }

    private void a() {
        FragmentActivity activity = getKnitFragment().getActivity();
        if (activity == null || activity.isDestroyed()) {
            return;
        }
        d dVar = new d(getKnitFragment().getChildFragmentManager(), getKnitFragmentList());
        this.l = dVar;
        this.n.setAdapter(dVar);
        this.f.setupWithViewPager(this.n, true);
        e();
    }

    private void e() {
        if (this.f == null) {
            LogUtil.b("SectionTabSwitch", "mTabLayout is null");
            return;
        }
        View analysisRecView = getAnalysisRecView();
        View musicTabView = getMusicTabView();
        ArrayList arrayList = new ArrayList();
        arrayList.add(analysisRecView);
        arrayList.add(musicTabView);
        this.f.setCustomTabs(arrayList);
        c();
    }

    private View getAnalysisRecView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.sleep_tab_layout, (ViewGroup) null);
        HealthCardView healthCardView = (HealthCardView) inflate.findViewById(R.id.tab_layout);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.sleep_tab_title);
        if (nsn.t()) {
            nsn.b(healthTextView);
        }
        if (this.d.size() == 2) {
            healthCardView.setCardBackgroundColor(ContextCompat.getColor(getContext(), this.d.get(0).intValue()));
            healthTextView.setTextColor(ContextCompat.getColor(getContext(), this.d.get(1).intValue()));
        }
        d(healthTextView);
        healthTextView.setText(this.c);
        HealthImageView healthImageView = (HealthImageView) inflate.findViewById(R.id.sleep_tab_icon);
        Drawable drawable = this.o;
        if (drawable != null) {
            healthImageView.setImageDrawable(drawable);
            nsy.cMA_(healthImageView, 0);
        } else {
            nsy.cMA_(healthImageView, 8);
        }
        return inflate;
    }

    private View getMusicTabView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.sleep_tab_layout, (ViewGroup) null);
        ((HealthCardView) inflate.findViewById(R.id.tab_layout)).setCardBackgroundColor((ColorStateList) null);
        this.j = (HealthImageView) inflate.findViewById(R.id.sleep_tab_icon_gif);
        this.i = (HealthImageView) inflate.findViewById(R.id.sleep_tab_icon);
        if (this.f2745a.size() == 2) {
            this.i.setImageDrawable(ContextCompat.getDrawable(getContext(), this.f2745a.get(1).intValue()));
            nrf.d(this.f2745a.get(0).intValue(), this.j);
            this.j.setVisibility(0);
            ViewGroup.LayoutParams layoutParams = this.j.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                if (LanguageUtil.bc(BaseApplication.getContext())) {
                    layoutParams2.setMarginStart(0);
                    layoutParams2.setMarginEnd(0);
                } else {
                    layoutParams2.setMarginStart(0);
                    layoutParams2.setMarginEnd(BaseApplication.getContext().getResources().getDimensionPixelOffset(R.dimen._2131363163_res_0x7f0a055b));
                }
            }
        }
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.sleep_tab_title);
        if (nsn.t()) {
            nsn.b(healthTextView);
        }
        d(healthTextView);
        healthTextView.setText(this.b);
        return inflate;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public List<Bitmap> getShareBitmap() {
        KnitFragment c = c(0);
        if (c != null) {
            return c.getShareBitmap();
        }
        return Collections.emptyList();
    }

    public KnitFragment c(int i) {
        d dVar = this.l;
        if (dVar != null) {
            return (KnitFragment) dVar.getItem(i);
        }
        return null;
    }

    private void f() {
        HealthTabLayout healthTabLayout = this.f;
        if (healthTabLayout == null || healthTabLayout.getTabCount() < 2) {
            return;
        }
        TabLayout.Tab tabAt = this.f.getTabAt(0);
        TabLayout.Tab tabAt2 = this.f.getTabAt(1);
        if (this.h == 2 && tabAt2 != null) {
            tabAt2.select();
        }
        if (this.h != 1 || tabAt == null) {
            return;
        }
        tabAt.select();
    }

    private void j() {
        TabLayout.Tab tabAt;
        View customView;
        if (this.f.getTabAt(0) == null || (tabAt = this.f.getTabAt(0)) == null || (customView = tabAt.getCustomView()) == null) {
            return;
        }
        HealthImageView healthImageView = (HealthImageView) customView.findViewById(R.id.sleep_tab_icon);
        Drawable drawable = this.o;
        if (drawable == null) {
            nsy.cMA_(healthImageView, 8);
        } else {
            nsy.cMm_(healthImageView, drawable);
            nsy.cMA_(healthImageView, 0);
        }
    }

    private void d(final HealthTextView healthTextView) {
        if (healthTextView == null || this.f == null) {
            return;
        }
        healthTextView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.knit.section.view.SectionTabSwitch.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (SectionTabSwitch.this.f == null) {
                    return;
                }
                if (nsy.cMe_(healthTextView) && (SectionTabSwitch.this.f.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) SectionTabSwitch.this.f.getLayoutParams();
                    layoutParams.topMargin = nsn.c(SectionTabSwitch.this.getContext(), 0.0f);
                    layoutParams.bottomMargin = nsn.c(SectionTabSwitch.this.getContext(), 0.0f);
                    SectionTabSwitch.this.f.setLayoutParams(layoutParams);
                }
                healthTextView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private List<Fragment> getKnitFragmentList() {
        ArrayList arrayList = new ArrayList();
        KnitPageGroupConfig a2 = eab.a(getContext(), KnitFragment.HEALTH_DETAIL_CONFIG_NAME);
        if (a2 == null) {
            return new ArrayList();
        }
        ArrayList<KnitPageConfig> pagesConfig = a2.getPagesConfig();
        if (koq.b(pagesConfig)) {
            return new ArrayList();
        }
        for (int i = 0; i < pagesConfig.size(); i++) {
            KnitPageConfig knitPageConfig = pagesConfig.get(i);
            if (knitPageConfig != null) {
                int pageType = knitPageConfig.getPageType();
                ArrayList<KnitSubPageConfig> subPagesConfig = knitPageConfig.getSubPagesConfig();
                if (koq.b(subPagesConfig)) {
                    continue;
                } else {
                    KnitSubPageConfig knitSubPageConfig = subPagesConfig.get(0);
                    if (knitSubPageConfig == null) {
                        LogUtil.a("SectionTabSwitch", "config is null");
                        return new ArrayList();
                    }
                    if (pageType == 107) {
                        KnitFragment newInstance = KnitFragment.newInstance(moj.e(knitSubPageConfig), new BasePageResTrigger(getContext(), knitSubPageConfig.getResPosId(), null));
                        newInstance.registerMajorKnitFragment(getKnitFragment());
                        newInstance.setIsRegisterForeverObserver(true);
                        arrayList.add(newInstance);
                    }
                    if (pageType == 108) {
                        KnitFragment newInstance2 = KnitFragment.newInstance(moj.e(knitSubPageConfig), new BasePageResTrigger(getContext(), knitSubPageConfig.getResPosId(), null));
                        newInstance2.registerMajorKnitFragment(getKnitFragment());
                        newInstance2.setIsRegisterForeverObserver(true);
                        arrayList.add(newInstance2);
                    }
                }
            }
        }
        return arrayList;
    }

    private void c() {
        this.f.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.huawei.health.knit.section.view.SectionTabSwitch.1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                SectionTabSwitch.this.i();
                if (a(tab)) {
                    ObserverManagerUtil.c("BUBBLE_DISMISS", "BUBBLE_ON_MUSIC");
                    nsy.cMA_(SectionTabSwitch.this.i, 0);
                    nsy.cMA_(SectionTabSwitch.this.j, 8);
                    SectionTabSwitch.this.d();
                } else {
                    ObserverManagerUtil.c("BUBBLE_DISMISS", "SLEEP_RECOMMEND");
                }
                ViewClickInstrumentation.selectClickOnTabLayout(this, tab);
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
                SectionTabSwitch.this.i();
            }

            private boolean a(TabLayout.Tab tab) {
                if (SectionTabSwitch.this.f.getTabCount() != 2) {
                    return false;
                }
                return Objects.equals(SectionTabSwitch.this.f.getTabAt(1), tab);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.f.getTabAt(0) == null || this.f.getTabAt(1) == null) {
            return;
        }
        TabLayout.Tab tabAt = this.f.getTabAt(0);
        TabLayout.Tab tabAt2 = this.f.getTabAt(1);
        if (tabAt == null || tabAt2 == null) {
            return;
        }
        View customView = tabAt.getCustomView();
        View customView2 = tabAt2.getCustomView();
        if (customView == null || customView2 == null) {
            return;
        }
        ajO_(tabAt, customView, customView2);
    }

    private void ajO_(TabLayout.Tab tab, View view, View view2) {
        Typeface create = Typeface.create(BaseApplication.getContext().getString(R$string.textFontFamilyRegular), 0);
        Typeface create2 = Typeface.create(BaseApplication.getContext().getString(R$string.textFontFamilyMedium), 0);
        HealthCardView healthCardView = (HealthCardView) view.findViewById(R.id.tab_layout);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.sleep_tab_title);
        HealthCardView healthCardView2 = (HealthCardView) view2.findViewById(R.id.tab_layout);
        HealthTextView healthTextView2 = (HealthTextView) view2.findViewById(R.id.sleep_tab_title);
        if (healthCardView == null || healthTextView == null || healthTextView2 == null || healthCardView2 == null) {
            LogUtil.b("SectionTabSwitch", "view is null");
            return;
        }
        if (tab.isSelected()) {
            if (this.d.size() == 2) {
                healthCardView.setCardBackgroundColor(ContextCompat.getColor(getContext(), this.d.get(0).intValue()));
                healthTextView.setTextColor(ContextCompat.getColor(getContext(), this.d.get(1).intValue()));
                healthTextView.setTypeface(create2);
                healthTextView2.setTypeface(create);
            }
            healthCardView2.setCardBackgroundColor((ColorStateList) null);
            healthTextView2.setTextColor(ContextCompat.getColor(getContext(), R.color._2131299107_res_0x7f090b23));
            nsy.cMA_(this.i, 8);
            nsy.cMA_(this.j, 0);
        } else {
            if (this.d.size() == 2) {
                healthCardView2.setCardBackgroundColor(ContextCompat.getColor(getContext(), this.d.get(0).intValue()));
                healthTextView2.setTextColor(ContextCompat.getColor(getContext(), this.d.get(1).intValue()));
                healthTextView2.setTypeface(create2);
                healthTextView.setTypeface(create);
            }
            healthCardView.setCardBackgroundColor((ColorStateList) null);
            healthTextView.setTextColor(ContextCompat.getColor(getContext(), R.color._2131299107_res_0x7f090b23));
        }
        if (this.f2745a.size() == 2) {
            this.i.setImageDrawable(ContextCompat.getDrawable(getContext(), this.f2745a.get(1).intValue()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        ixx.d().d(getContextRef(), AnalyticsValue.SLEEP_MUSIC_TAB_2030113.value(), hashMap, 0);
    }

    static class d extends FragmentStatePagerAdapter {
        private List<Fragment> c;

        d(FragmentManager fragmentManager, List<Fragment> list) {
            super(fragmentManager);
            this.c = list;
        }

        @Override // androidx.fragment.app.FragmentStatePagerAdapter
        public Fragment getItem(int i) {
            if (koq.b(this.c, i)) {
                return null;
            }
            Fragment fragment = koq.b(this.c) ? null : this.c.get(i);
            LogUtil.a("SectionTabSwitch", "getItem() position : ", Integer.valueOf(i), "    fragment : ", fragment);
            return fragment;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            List<Fragment> list = this.c;
            if (list == null) {
                return 0;
            }
            return list.size();
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionTabSwitch";
    }
}
