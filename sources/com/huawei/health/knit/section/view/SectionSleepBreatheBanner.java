package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.jdl;
import defpackage.nru;
import defpackage.nsy;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionSleepBreatheBanner extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f2735a;
    private HealthTextView b;
    protected HealthTextView c;
    private LinearLayout d;
    private ImageView e;
    private View g;
    private HealthTextView i;

    @Override // com.huawei.health.knit.section.view.BaseSection
    public boolean isSupportShare() {
        return false;
    }

    public SectionSleepBreatheBanner(Context context) {
        this(context, null);
    }

    public SectionSleepBreatheBanner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionSleepBreatheBanner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        try {
            View inflate = LayoutInflater.from(context).inflate(R.layout.sleep_breathe_banner_layout, (ViewGroup) this, false);
            this.e = (ImageView) inflate.findViewById(R.id.icon);
            this.c = (HealthTextView) inflate.findViewById(R.id.title_text);
            this.b = (HealthTextView) inflate.findViewById(R.id.primary_tip);
            this.g = inflate.findViewById(R.id.time_arrow_area);
            this.i = (HealthTextView) inflate.findViewById(R.id.time_text);
            this.d = (LinearLayout) inflate.findViewById(R.id.card_line_chart_container);
            this.f2735a = (ImageView) inflate.findViewById(R.id.center_arrow);
            return inflate;
        } catch (NullPointerException e) {
            ReleaseLogUtil.c("SectionSleepBreatheBanner", "Exception msg = ", ExceptionUtils.d(e));
            return new View(context);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.h("SectionSleepBreatheBanner", "no need to bind");
            return;
        }
        if (this.d == null) {
            LogUtil.h("SectionSleepBreatheBanner", "mChartContainer is null");
            return;
        }
        nsy.cMr_(this.c, nru.b(hashMap, "TITLE", ""));
        nsy.cMm_(this.e, nru.cKj_(hashMap, "LEFT_ICON_IMAGE", null));
        nsy.cMr_(this.b, nru.b(hashMap, "SUBTITLE", ""));
        long d = nru.d((Map) hashMap, "BAR_CHART_DATE_TEXT", 0L);
        LogUtil.a("SectionSleepBreatheBanner", "bindParamsToView dataTime:", Long.valueOf(d));
        if (d == 0) {
            nsy.cMx_(this.b, BaseApplication.e().getResources().getDimension(R.dimen._2131365062_res_0x7f0a0cc6), 0);
            nsy.cMA_(this.g, 8);
            nsy.cMA_(this.f2735a, 0);
            nsy.cMA_(this.d, 8);
        } else {
            nsy.cMx_(this.b, BaseApplication.e().getResources().getDimension(R.dimen._2131365061_res_0x7f0a0cc5), 0);
            nsy.cMA_(this.g, 0);
            nsy.cMA_(this.f2735a, 8);
            nsy.cMA_(this.d, 0);
            nsy.cMr_(this.i, UnitUtil.a(new Date(e(d)), 24));
            this.d.removeAllViews();
            Object d2 = nru.d(hashMap, "SECTION_CHART", (Object) null);
            if (d2 instanceof View) {
                this.d.addView((View) d2);
            }
        }
        Object d3 = nru.d(hashMap, "CLICK_EVENT_LISTENER", (Object) null);
        if (d3 instanceof View.OnClickListener) {
            getView().setOnClickListener((View.OnClickListener) d3);
        }
    }

    private long e(long j) {
        return j >= jdl.e(j, 20, 0) ? jdl.y(j) : j;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionSleepBreatheBanner";
    }
}
