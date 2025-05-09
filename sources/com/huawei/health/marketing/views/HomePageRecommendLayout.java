package com.huawei.health.marketing.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.TimePeriod;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.datatype.templates.HomePageRecommendTemplate;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketing.views.HomePageRecommendLayout;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.activetips.ActiveTipsTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.eie;
import defpackage.eil;
import defpackage.jdl;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class HomePageRecommendLayout extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private SingleGridContent f2872a;
    private int b;
    private Context c;
    private int d;
    private ResourceBriefInfo e;
    private ActiveTipsTextView f;
    private HealthImageView g;
    private HomePageRecommendTemplate h;
    private HealthImageView i;
    private long j;

    public HomePageRecommendLayout(Context context) {
        super(context);
        this.d = 0;
        this.c = BaseApplication.getContext();
    }

    public String getRecommendCard() {
        SingleGridContent singleGridContent = this.f2872a;
        if (singleGridContent != null && !TextUtils.isEmpty(singleGridContent.getRecommendCard())) {
            return this.f2872a.getRecommendCard();
        }
        HomePageRecommendTemplate homePageRecommendTemplate = this.h;
        if (homePageRecommendTemplate != null) {
            return homePageRecommendTemplate.getRecommendCard();
        }
        LogUtil.a("HomePageRecommendLayout", "getRecommendCard, recommendCard is empty");
        return "";
    }

    public void setCardPosition(int i) {
        this.b = i;
    }

    public SingleGridContent getGridContent() {
        return this.f2872a;
    }

    public void b(int i, ResourceBriefInfo resourceBriefInfo, BaseTemplate baseTemplate) {
        if (!(baseTemplate instanceof HomePageRecommendTemplate)) {
            LogUtil.e("HomePageRecommendLayout", "initData failed, template is not HomePageRecommendTemplate");
            setVisibility(8);
            return;
        }
        LogUtil.c("HomePageRecommendLayout", "initData, template name:" + getRecommendCard());
        this.e = resourceBriefInfo;
        this.d = i;
        HomePageRecommendTemplate homePageRecommendTemplate = (HomePageRecommendTemplate) baseTemplate;
        this.h = homePageRecommendTemplate;
        if (homePageRecommendTemplate.getGridContents() == null || this.h.getGridContents().get(0) == null) {
            LogUtil.a("HomePageRecommendLayout", "gridContent is null, make banner invisible");
            setVisibility(8);
        } else {
            SingleGridContent singleGridContent = this.h.getGridContents().get(0);
            this.f2872a = singleGridContent;
            b(i, resourceBriefInfo, baseTemplate, singleGridContent);
        }
    }

    public void b(int i, ResourceBriefInfo resourceBriefInfo, BaseTemplate baseTemplate, SingleGridContent singleGridContent) {
        this.e = resourceBriefInfo;
        this.d = i;
        this.h = (HomePageRecommendTemplate) baseTemplate;
        this.f2872a = singleGridContent;
        long b = SharedPreferenceManager.b(Integer.toString(PrebakedEffectId.RT_ICE), Integer.toString(i).concat(getRecommendCard()), 0L);
        if (System.currentTimeMillis() < b) {
            LogUtil.c("HomePageRecommendLayout", "still in bannerExpireTime: " + b);
            setVisibility(8);
            return;
        }
        c();
    }

    private void c() {
        final View inflate = LayoutInflater.from(this.c).inflate(R.layout.layout_home_page_recommend, this);
        this.g = (HealthImageView) inflate.findViewById(R.id.theme_icon);
        this.i = (HealthImageView) inflate.findViewById(R.id.right_arrow);
        ActiveTipsTextView activeTipsTextView = (ActiveTipsTextView) inflate.findViewById(R.id.theme);
        this.f = activeTipsTextView;
        activeTipsTextView.setText(this.f2872a.getTheme());
        nrf.c(this.g, this.f2872a.getPicture(), this.c.getResources().getDimensionPixelOffset(R.dimen._2131363122_res_0x7f0a0532), 0, R.drawable._2131427992_res_0x7f0b0298);
        String backgroundColor = this.f2872a.getBackgroundColor();
        int color = TextUtils.isEmpty(backgroundColor) ? ContextCompat.getColor(this.c, R.color._2131297126_res_0x7f090366) : Color.parseColor(backgroundColor);
        this.f.setTextColor(color);
        apt_(inflate.findViewById(R.id.bg_layout), color);
        d(color);
        inflate.setOnClickListener(new View.OnClickListener() { // from class: ekb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomePageRecommendLayout.this.apv_(inflate, view);
            }
        });
        this.f.setAutoFocus(this.h.isWalkingLanternSwitch());
        aps_(inflate, this.f2872a);
    }

    public /* synthetic */ void apv_(final View view, View view2) {
        if (TextUtils.isEmpty(this.f2872a.getLinkValue())) {
            LogUtil.a("HomePageRecommendLayout", "home page recommend item have no linkValue");
            ViewClickInstrumentation.clickOnView(view2);
        } else if (nsn.cLk_(view2)) {
            LogUtil.a("HomePageRecommendLayout", "click too fast");
            ViewClickInstrumentation.clickOnView(view2);
        } else if (!eie.b(this.f2872a.getLinkValue())) {
            apr_(view, this.f2872a);
            ViewClickInstrumentation.clickOnView(view2);
        } else {
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: ekc
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    HomePageRecommendLayout.this.apu_(view, i, obj);
                }
            }, AnalyticsValue.MARKETING_RESOURCE.value());
            ViewClickInstrumentation.clickOnView(view2);
        }
    }

    public /* synthetic */ void apu_(View view, int i, Object obj) {
        if (i == 0) {
            apr_(view, this.f2872a);
        } else {
            LogUtil.a("HomePageRecommendLayout", "onClick errorCode = ", Integer.valueOf(i));
        }
    }

    private void d(int i) {
        Drawable cJH_ = nrf.cJH_(ContextCompat.getDrawable(this.c, R.drawable._2131429722_res_0x7f0b095a), i);
        cJH_.setAlpha(153);
        if (LanguageUtil.bc(this.c)) {
            BitmapDrawable cKm_ = nrz.cKm_(this.c, cJH_);
            if (cKm_ != null) {
                this.i.setImageDrawable(cKm_);
                return;
            }
            return;
        }
        this.i.setImageDrawable(cJH_);
    }

    private void apt_(View view, int i) {
        try {
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{0, i});
            gradientDrawable.setGradientType(0);
            gradientDrawable.setShape(0);
            float dimensionPixelOffset = this.c.getResources().getDimensionPixelOffset(R.dimen._2131364930_res_0x7f0a0c42);
            gradientDrawable.setCornerRadii(new float[]{0.0f, 0.0f, 0.0f, 0.0f, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset});
            gradientDrawable.setAlpha(46);
            view.setBackground(gradientDrawable);
        } catch (IllegalArgumentException unused) {
            LogUtil.e("HomePageRecommendLayout", "IllegalArgumentException");
        }
    }

    private void apr_(View view, SingleGridContent singleGridContent) {
        String linkValue = singleGridContent.getLinkValue();
        ((MarketRouterApi) Services.c("FeatureMarketing", MarketRouterApi.class)).router(eil.c(linkValue, this.d, 1, singleGridContent, this.e));
        LogUtil.c("HomePageRecommendLayout", "jumping from function card banner, target:" + linkValue);
        d(2, singleGridContent);
        view.setVisibility(8);
        String concat = Integer.toString(this.d).concat(getRecommendCard());
        long a2 = a(singleGridContent);
        if (a2 == 0) {
            a2 = jdl.e(System.currentTimeMillis());
        }
        SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_ICE), concat, a2);
        LogUtil.c("HomePageRecommendLayout", getRecommendCard() + " clicked, banner will stay expired until " + a2);
    }

    private long a(SingleGridContent singleGridContent) {
        List<TimePeriod> timePeriodList = singleGridContent.getTimePeriodList();
        if (timePeriodList == null) {
            LogUtil.c("HomePageRecommendLayout", "no time period received from cloud");
            return 0L;
        }
        for (TimePeriod timePeriod : timePeriodList) {
            if (e(timePeriod.getBeginTime()) < System.currentTimeMillis() && e(timePeriod.getEndTime()) > System.currentTimeMillis()) {
                return e(timePeriod.getEndTime());
            }
        }
        LogUtil.a("HomePageRecommendLayout", "no matched timePeriod, this should NOT happen..." + timePeriodList);
        return 0L;
    }

    private static long e(String str) {
        String[] split = str.split(":");
        if (split.length < 2) {
            LogUtil.e("HomePageRecommendLayout", "time format error, time: " + str);
            return 0L;
        }
        return jdl.e(System.currentTimeMillis(), CommonUtil.m(BaseApplication.getContext(), split[0]), CommonUtil.m(BaseApplication.getContext(), split[1]));
    }

    private void aps_(final View view, final SingleGridContent singleGridContent) {
        nsy.cMb_(view, new ViewTreeObserver.OnScrollChangedListener() { // from class: com.huawei.health.marketing.views.HomePageRecommendLayout.1
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public void onScrollChanged() {
                if (HomePageRecommendLayout.this.d != 4161 || eie.a()) {
                    if (view.getTag() != null) {
                        nsy.cMg_(view, this);
                        return;
                    }
                    if (MarketingBiUtils.alP_(view)) {
                        HomePageRecommendLayout.this.j = System.currentTimeMillis();
                        HomePageRecommendLayout.this.d(1, singleGridContent);
                        view.setTag("showed");
                        nsy.cMg_(view, this);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, SingleGridContent singleGridContent) {
        if (this.e == null) {
            LogUtil.a("HomePageRecommendLayout", "biEvent mBriefInfo is null. return.");
            return;
        }
        HashMap hashMap = new HashMap(10);
        if (!TextUtils.isEmpty(singleGridContent.getDynamicDataId())) {
            hashMap.put("itemId", singleGridContent.getDynamicDataId());
        }
        hashMap.put("itemCardName", singleGridContent.getTheme());
        if (i == 2) {
            hashMap.put("durationTime", Integer.valueOf((int) (System.currentTimeMillis() - this.j)));
        }
        if (singleGridContent.isSmartRecommend()) {
            hashMap.put("itemId", singleGridContent.getItemId());
        }
        hashMap.put("smartRecommend", Boolean.valueOf(singleGridContent.isSmartRecommend()));
        hashMap.put("algId", singleGridContent.getAlgId());
        hashMap.put("pullOrder", Integer.valueOf(this.b + 1));
        MarketingBiUtils.d(i, this.d, this.e, hashMap);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HomePageRecommendLayout)) {
            return false;
        }
        HomePageRecommendLayout homePageRecommendLayout = (HomePageRecommendLayout) obj;
        if (getGridContent() == null) {
            LogUtil.a("HomePageRecommendLayout", "singleGridContent is null when equals.");
            return false;
        }
        return getGridContent().equals(homePageRecommendLayout.getGridContent());
    }
}
