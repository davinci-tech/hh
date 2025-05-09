package com.huawei.health.functionsetcard.dailymoment;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.functionsetcard.dailymoment.operation.IGetTitleCallback;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.functionsetcard.view.RatioRelativeLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import defpackage.dnw;
import defpackage.dnx;
import defpackage.dnz;
import defpackage.doa;
import defpackage.dod;
import defpackage.doe;
import defpackage.doh;
import defpackage.dpw;
import defpackage.ixx;
import defpackage.jdx;
import defpackage.koq;
import defpackage.nkx;
import defpackage.nsn;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class DailyMomentCardAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f2357a;
    private HealthCardView b;
    private List<SingleDailyMomentContent> c;
    private ResourceBriefInfo d;
    private DailyMomentViewHolder f;
    private long g;
    private Map<Integer, dnw> i = new HashMap();
    private boolean e = true;
    private final View.OnTouchListener j = new View.OnTouchListener() { // from class: com.huawei.health.functionsetcard.dailymoment.DailyMomentCardAdapter.3
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (DailyMomentCardAdapter.this.f == null) {
                return false;
            }
            int action = motionEvent.getAction();
            if (action == 1 || action == 3 || action == 4) {
                DailyMomentCardAdapter.this.f.d();
            }
            if (action == 0) {
                DailyMomentCardAdapter.this.f.a();
            }
            return false;
        }
    };

    public DailyMomentCardAdapter(Context context) {
        this.f2357a = context;
    }

    public void c() {
        LogUtil.a("DailyMomentCardAdapter", "notifyDataSetChanged");
        DailyMomentViewHolder dailyMomentViewHolder = this.f;
        if (dailyMomentViewHolder == null || dailyMomentViewHolder.e == null) {
            return;
        }
        this.c = dnx.d().e();
        this.f.e.notifyDataSetChanged();
        this.f.e();
    }

    public DailyMomentViewHolder WX_(ViewGroup viewGroup) {
        DailyMomentViewHolder dailyMomentViewHolder = new DailyMomentViewHolder(LayoutInflater.from(this.f2357a).inflate(R.layout.daily_moment_card_layout, viewGroup, false));
        this.f = dailyMomentViewHolder;
        return dailyMomentViewHolder;
    }

    public void b(DailyMomentViewHolder dailyMomentViewHolder, final dnx dnxVar) {
        ReleaseLogUtil.e("UIHLH_DailyMomentCardAdapter", "onBindViewHolder, holder: ", dailyMomentViewHolder, ", dailyMomentManager: ", dnxVar);
        this.g = System.currentTimeMillis();
        this.c = dnxVar.e();
        this.d = dnxVar.h();
        dailyMomentViewHolder.c();
        dailyMomentViewHolder.f2359a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.functionsetcard.dailymoment.DailyMomentCardAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DailyMomentCardAdapter.this.b();
                dnxVar.f();
                doa.e(DailyMomentCardAdapter.this.f2357a, AnalyticsValue.DAILY_MOMENT_11100303.value(), null, null);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        jdx.b(new Runnable() { // from class: com.huawei.health.functionsetcard.dailymoment.DailyMomentCardAdapter.1
            @Override // java.lang.Runnable
            public void run() {
                if (doh.b(DailyMomentCardAdapter.this.f2357a, DailyMomentCardAdapter.this.c) != 0) {
                    LogUtil.a("DailyMomentCardAdapter", "updateLatestShowTime failed");
                }
            }
        });
        List<SingleDailyMomentContent> list = this.c;
        if (list != null) {
            int i = -1;
            for (SingleDailyMomentContent singleDailyMomentContent : list) {
                doa.e(this.f2357a, AnalyticsValue.DAILY_MOMENT_11100305.value(), singleDailyMomentContent, null);
                i++;
                e(singleDailyMomentContent, 1, i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(SingleDailyMomentContent singleDailyMomentContent, int i, int i2) {
        if (this.d == null || singleDailyMomentContent == null) {
            LogUtil.a("DailyMomentCardAdapter", "setMarketingBiEvent briefInfo or singleDailyMomentContent is null");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("resourcePositionId", 5001);
        hashMap.put("resourceId", this.d.getResourceId());
        hashMap.put("resourceName", this.d.getResourceName());
        hashMap.put("itemCardName", Integer.valueOf(singleDailyMomentContent.getThemeType()));
        hashMap.put("itemId", "");
        hashMap.put("smartRecommend", Boolean.valueOf(singleDailyMomentContent.isSmartRecommend()));
        hashMap.put("algId", singleDailyMomentContent.getAlgId());
        hashMap.put("pullOrder", Integer.valueOf(i2 + 1));
        if (i == 2) {
            long currentTimeMillis = System.currentTimeMillis();
            hashMap.put("durationTime", Long.valueOf(currentTimeMillis - this.g));
            this.g = currentTimeMillis;
        }
        ixx.d().c(BaseApplication.getContext(), AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, singleDailyMomentContent.getAbInfo(), 0);
    }

    public void b(int i, dnw dnwVar) {
        this.i.put(Integer.valueOf(i), dnwVar);
    }

    public void b() {
        Map<Integer, dnw> map = this.i;
        if (map == null) {
            return;
        }
        for (dnw dnwVar : map.values()) {
            if (dnwVar != null) {
                dnwVar.c();
            }
        }
    }

    public void a() {
        DailyMomentViewHolder dailyMomentViewHolder;
        List<SingleDailyMomentContent> list = this.c;
        if ((list != null && list.size() <= 1) || (dailyMomentViewHolder = this.f) == null || dailyMomentViewHolder.j == null) {
            return;
        }
        this.e = true;
        this.f.j.setIsScroll(true);
        this.f.d();
    }

    public void d() {
        DailyMomentViewHolder dailyMomentViewHolder = this.f;
        if (dailyMomentViewHolder == null || dailyMomentViewHolder.j == null) {
            return;
        }
        this.e = false;
        this.f.j.setIsScroll(false);
        this.f.a();
    }

    public boolean e() {
        Map<Integer, dnw> map = this.i;
        if (map == null || map.isEmpty()) {
            return false;
        }
        for (dnw dnwVar : this.i.values()) {
            if (dnwVar != null && dnwVar.d()) {
                return true;
            }
        }
        return false;
    }

    public class DailyMomentViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private LinearLayout f2359a;
        private RatioRelativeLayout b;
        private HealthDotsPageIndicator c;
        private a e;
        private HealthViewPager j;

        public DailyMomentViewHolder(View view) {
            super(view);
            if (view instanceof ViewGroup) {
                ((ViewGroup) view).setLayoutParams(Xb_(DailyMomentCardAdapter.this.f2357a));
                this.f2359a = (LinearLayout) view.findViewById(R.id.close_button_layout);
                this.j = (HealthViewPager) view.findViewById(R.id.daily_moment_view_pager);
                this.c = (HealthDotsPageIndicator) view.findViewById(R.id.daily_moment_card_dots);
                DailyMomentCardAdapter.this.b = (HealthCardView) view.findViewById(R.id.daily_moment_health_card);
                this.b = (RatioRelativeLayout) view.findViewById(R.id.daily_moment_root_layout);
                DailyMomentCardAdapter.this.b.setForceClipRoundCorner(true);
            }
        }

        public void c() {
            a aVar = this.e;
            if (aVar != null) {
                aVar.notifyDataSetChanged();
                return;
            }
            a aVar2 = DailyMomentCardAdapter.this.new a(this.b);
            this.e = aVar2;
            this.j.setAdapter(aVar2);
            ReleaseLogUtil.e("UIHLH_DailyMomentCardAdapter", "onBindViewHolder, mPageAdapter: ", this.e, ", mViewPager: ", this.j);
            if (!koq.b(DailyMomentCardAdapter.this.c)) {
                this.j.setOffscreenPageLimit(DailyMomentCardAdapter.this.c.size());
            }
            e();
            this.j.addOnPageChangeListener(new HwViewPager.OnPageChangeListener() { // from class: com.huawei.health.functionsetcard.dailymoment.DailyMomentCardAdapter.DailyMomentViewHolder.5
                @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
                public void onPageScrolled(int i, float f, int i2) {
                }

                @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
                public void onPageSelected(int i) {
                }

                @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i) {
                    if (i != 1) {
                        return;
                    }
                    doa.e(DailyMomentCardAdapter.this.f2357a, AnalyticsValue.DAILY_MOMENT_11100304.value(), null, null);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            if (DailyMomentCardAdapter.this.c != null && DailyMomentCardAdapter.this.c.size() > 1) {
                this.c.setViewPager(this.j);
                this.c.setVisibility(0);
                DailyMomentCardAdapter.this.a();
            } else {
                this.c.setVisibility(4);
                DailyMomentCardAdapter.this.d();
            }
        }

        private RelativeLayout.LayoutParams Xb_(Context context) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            int d = dpw.d(context);
            layoutParams.bottomMargin = dpw.c(context, dpw.c(context));
            int i = d / 2;
            layoutParams.setMarginStart(i);
            layoutParams.setMarginEnd(i);
            return layoutParams;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            if (DailyMomentCardAdapter.this.e && DailyMomentCardAdapter.this.c != null && DailyMomentCardAdapter.this.f.c != null && DailyMomentCardAdapter.this.f.c.getVisibility() == 0) {
                if (DailyMomentCardAdapter.this.c.size() > 1) {
                    DailyMomentCardAdapter.this.f.c.a(3000);
                } else {
                    DailyMomentCardAdapter.this.f.c.c();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            if (DailyMomentCardAdapter.this.f.c == null || DailyMomentCardAdapter.this.f.c.getVisibility() != 0) {
                return;
            }
            DailyMomentCardAdapter.this.f.c.c();
        }
    }

    class a extends HealthPagerAdapter {
        private RatioRelativeLayout b;

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        }

        a(RatioRelativeLayout ratioRelativeLayout) {
            this.b = ratioRelativeLayout;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            super.destroyItem(viewGroup, i, obj);
            if (obj instanceof View) {
                viewGroup.removeView((View) obj);
            }
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public int getCount() {
            if (DailyMomentCardAdapter.this.c != null) {
                return DailyMomentCardAdapter.this.c.size();
            }
            return 0;
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View WY_;
            ReleaseLogUtil.e("UIHLH_DailyMomentCardAdapter", "instantiateItem, index = " + i);
            if (koq.b(DailyMomentCardAdapter.this.c, i)) {
                return new Object();
            }
            if (viewGroup instanceof HealthViewPager) {
                SingleDailyMomentContent singleDailyMomentContent = (SingleDailyMomentContent) DailyMomentCardAdapter.this.c.get(i);
                if (singleDailyMomentContent == null || singleDailyMomentContent.getCoverLinkType() == null) {
                    return new Object();
                }
                if (!singleDailyMomentContent.getCoverLinkType().equals(SingleDailyMomentContent.SLEEP_MUSIC_TYPE)) {
                    WY_ = WZ_(singleDailyMomentContent, i);
                } else {
                    WY_ = WY_(singleDailyMomentContent, i);
                }
                ((HealthViewPager) viewGroup).addView(WY_);
                dpw.YH_(DailyMomentCardAdapter.this.f2357a, this.b, (ImageView) WY_.findViewById(R.id.background_image), true, true);
                return WY_;
            }
            return new Object();
        }

        private View WZ_(final SingleDailyMomentContent singleDailyMomentContent, final int i) {
            ReleaseLogUtil.e("UIHLH_DailyMomentCardAdapter", "enter buildDailyMomentPage, position: ", Integer.valueOf(i), ", dataBean: ", singleDailyMomentContent.toString());
            View inflate = View.inflate(DailyMomentCardAdapter.this.f2357a, R.layout.daily_moment_card_page, null);
            doe.Xl_(singleDailyMomentContent.getCoverPicture(), (ImageView) inflate.findViewById(R.id.background_image));
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.huawei.health.functionsetcard.dailymoment.DailyMomentCardAdapter.a.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    dod.d(DailyMomentCardAdapter.this.f2357a, singleDailyMomentContent, false, i, DailyMomentCardAdapter.this.d);
                    doa.e(DailyMomentCardAdapter.this.f2357a, AnalyticsValue.DAILY_MOMENT_11100306.value(), singleDailyMomentContent, null);
                    DailyMomentCardAdapter.this.e(singleDailyMomentContent, 2, i);
                    ViewClickInstrumentation.clickOnView(view);
                }
            };
            View.OnClickListener onClickListener2 = new View.OnClickListener() { // from class: com.huawei.health.functionsetcard.dailymoment.DailyMomentCardAdapter.a.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    dod.d(DailyMomentCardAdapter.this.f2357a, singleDailyMomentContent, true, i, DailyMomentCardAdapter.this.d);
                    doa.e(DailyMomentCardAdapter.this.f2357a, AnalyticsValue.DAILY_MOMENT_11100307.value(), singleDailyMomentContent, null);
                    DailyMomentCardAdapter.this.e(singleDailyMomentContent, 2, i);
                    ViewClickInstrumentation.clickOnView(view);
                }
            };
            Activity activity = BaseApplication.getActivity();
            if (activity instanceof BaseActivity) {
                BaseActivity baseActivity = (BaseActivity) activity;
                onClickListener = nkx.cwZ_(onClickListener, baseActivity, true, "");
                onClickListener2 = nkx.cwZ_(onClickListener2, baseActivity, true, "");
            }
            inflate.setOnClickListener(onClickListener);
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.daily_moment_title);
            HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.daily_moment_description);
            HealthButton healthButton = (HealthButton) inflate.findViewById(R.id.action_button_text);
            d(healthTextView, singleDailyMomentContent);
            e(healthTextView2, singleDailyMomentContent);
            a(healthButton, singleDailyMomentContent);
            healthButton.setOnClickListener(onClickListener2);
            healthButton.setOnTouchListener(DailyMomentCardAdapter.this.j);
            inflate.setOnTouchListener(DailyMomentCardAdapter.this.j);
            return inflate;
        }

        private View WY_(final SingleDailyMomentContent singleDailyMomentContent, final int i) {
            ReleaseLogUtil.e("UIHLH_DailyMomentCardAdapter", "enter buildDailyMomentMediaPage, index: ", Integer.valueOf(i), ", dailyMomentData: ", singleDailyMomentContent.toString());
            View inflate = View.inflate(DailyMomentCardAdapter.this.f2357a, R.layout.daily_moment_play_media_card_page, null);
            doe.Xl_(singleDailyMomentContent.getCoverPicture(), (ImageView) inflate.findViewById(R.id.background_image));
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.huawei.health.functionsetcard.dailymoment.DailyMomentCardAdapter.a.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("DailyMomentCardAdapter", "dailyMomentPage is clicked");
                    dod.d(DailyMomentCardAdapter.this.f2357a, singleDailyMomentContent, false, i, DailyMomentCardAdapter.this.d);
                    doa.e(DailyMomentCardAdapter.this.f2357a, AnalyticsValue.DAILY_MOMENT_11100306.value(), singleDailyMomentContent, null);
                    DailyMomentCardAdapter.this.e(singleDailyMomentContent, 2, i);
                    ViewClickInstrumentation.clickOnView(view);
                }
            };
            Activity activity = BaseApplication.getActivity();
            if (activity instanceof BaseActivity) {
                onClickListener = nkx.cwZ_(onClickListener, (BaseActivity) activity, true, "");
            }
            inflate.setOnClickListener(onClickListener);
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.daily_moment_title);
            HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.daily_moment_description);
            d(healthTextView, singleDailyMomentContent);
            e(healthTextView2, singleDailyMomentContent);
            ((ImageView) inflate.findViewById(R.id.daily_moment_media_button)).setOnTouchListener(DailyMomentCardAdapter.this.j);
            new dnw(inflate, singleDailyMomentContent, i, DailyMomentCardAdapter.this);
            inflate.setOnTouchListener(DailyMomentCardAdapter.this.j);
            return inflate;
        }

        private void d(final HealthTextView healthTextView, SingleDailyMomentContent singleDailyMomentContent) {
            if (singleDailyMomentContent.getThemeVisibility()) {
                if (nsn.m()) {
                    healthTextView.setTextSize(1, 23.2f);
                }
                if (singleDailyMomentContent.getThemeType() == 1) {
                    String e = dnz.e(DailyMomentCardAdapter.this.f2357a, singleDailyMomentContent, new IGetTitleCallback() { // from class: com.huawei.health.functionsetcard.dailymoment.DailyMomentCardAdapter.a.4
                        @Override // com.huawei.health.functionsetcard.dailymoment.operation.IGetTitleCallback
                        public void onFailure(int i, String str) {
                            LogUtil.a("DailyMomentCardAdapter", "get title failed, errorCode: " + i);
                        }

                        @Override // com.huawei.health.functionsetcard.dailymoment.operation.IGetTitleCallback
                        public void onSuccess(final String str) {
                            if (str == null || str.length() <= 0) {
                                return;
                            }
                            LogUtil.a("DailyMomentCardAdapter", "title string is set, result: " + str);
                            HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.functionsetcard.dailymoment.DailyMomentCardAdapter.a.4.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    healthTextView.setText(str);
                                }
                            });
                        }
                    });
                    LogUtil.a("DailyMomentCardAdapter", "return title: " + e);
                    if (TextUtils.isEmpty(e)) {
                        healthTextView.setText(R$string.IDS_daily_title);
                        return;
                    } else {
                        healthTextView.setText(e);
                        return;
                    }
                }
                if (singleDailyMomentContent.getThemeType() == 2) {
                    healthTextView.setText(singleDailyMomentContent.getCustomTheme());
                    return;
                } else {
                    LogUtil.h("DailyMomentCardAdapter", "theme type is invalid");
                    healthTextView.setVisibility(4);
                    return;
                }
            }
            healthTextView.setVisibility(4);
        }

        private void e(HealthTextView healthTextView, SingleDailyMomentContent singleDailyMomentContent) {
            if (TextUtils.isEmpty(singleDailyMomentContent.getSubTheme()) || (singleDailyMomentContent.getSubThemeVisibility() != null && !singleDailyMomentContent.getSubThemeVisibility().booleanValue())) {
                healthTextView.setVisibility(4);
                return;
            }
            healthTextView.setVisibility(0);
            if (nsn.m()) {
                healthTextView.setTextSize(1, 17.4f);
            }
            healthTextView.setText(singleDailyMomentContent.getSubTheme());
        }

        private void a(HealthButton healthButton, SingleDailyMomentContent singleDailyMomentContent) {
            if (singleDailyMomentContent.getButtonVisibility()) {
                if (singleDailyMomentContent.getButtonType() == 1) {
                    LogUtil.h("DailyMomentCardAdapter", "button type is media player, no button text!");
                    return;
                } else if (singleDailyMomentContent.getButtonType() == 2) {
                    healthButton.setAutoTextInfo(14, 1, 1);
                    healthButton.setText(singleDailyMomentContent.getCustomButton());
                    return;
                } else {
                    LogUtil.h("DailyMomentCardAdapter", "button type is invalid");
                    healthButton.setVisibility(8);
                    return;
                }
            }
            healthButton.setVisibility(8);
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public void startUpdate(ViewGroup viewGroup) {
            super.startUpdate(viewGroup);
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
        public void finishUpdate(ViewGroup viewGroup) {
            super.finishUpdate(viewGroup);
        }
    }
}
