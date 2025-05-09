package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.bloodpressure.constant.DynamicBpStatus;
import com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.seekbar.HealthSeekBarExtend;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ixx;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionDynamicBp extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2682a;
    private HealthCardView b;
    private HealthTextView c;
    private HealthTextView d;
    private ImageView e;
    private View f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private Context j;
    private LinearLayout k;
    private RelativeLayout l;
    private HealthImageView m;
    private FrameLayout n;
    private LinearLayout o;
    private Map<String, Object> p;
    private View q;
    private ImageView r;
    private Typeface s;
    private HealthTextView t;
    private HealthTextView u;
    private HealthSeekBarExtend v;
    private HealthTextView w;
    private HealthSubHeader x;
    private HealthProgressBar y;

    public SectionDynamicBp(Context context) {
        super(context);
        this.p = new HashMap();
    }

    public SectionDynamicBp(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.p = new HashMap();
    }

    public SectionDynamicBp(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.p = new HashMap();
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.j = context;
        e();
        return this.q;
    }

    private void e() {
        View inflate = LayoutInflater.from(this.j).inflate(R.layout.section_dynamic_bloodpressure, (ViewGroup) this, false);
        this.q = inflate;
        this.x = (HealthSubHeader) inflate.findViewById(R.id.section_blood_pressure_dynamic_sub_header);
        this.e = (ImageView) this.q.findViewById(R.id.dynamic_head_arrow);
        this.w = (HealthTextView) this.q.findViewById(R.id.dynamic_head_subtitle);
        this.v = (HealthSeekBarExtend) this.q.findViewById(R.id.dynamic_seek_bar_extend);
        this.y = (HealthProgressBar) this.q.findViewById(R.id.dynamic_progress_bar);
        this.o = (LinearLayout) this.q.findViewById(R.id.dynamic_plan_progress_layout);
        this.l = (RelativeLayout) this.q.findViewById(R.id.dynamic_content_layout);
        this.f2682a = (HealthTextView) this.q.findViewById(R.id.dynamic_content_desc);
        this.c = (HealthTextView) this.q.findViewById(R.id.dynamic_bottom_des_one);
        this.d = (HealthTextView) this.q.findViewById(R.id.dynamic_bottom_des_two);
        this.t = (HealthTextView) this.q.findViewById(R.id.dynamic_head_date);
        this.h = (HealthTextView) this.q.findViewById(R.id.dynamic_content_value);
        this.i = (HealthTextView) this.q.findViewById(R.id.dynamic_content_unit);
        this.g = (HealthTextView) this.q.findViewById(R.id.dynamic_content_level);
        this.f = this.q.findViewById(R.id.dynamic_content_divide_line);
        this.u = (HealthTextView) this.q.findViewById(R.id.dynamic_plan_progress);
        this.b = (HealthCardView) this.q.findViewById(R.id.dynamic_bp_cardview);
        this.r = (ImageView) this.q.findViewById(R.id.dynamic_head_pic);
        this.m = (HealthImageView) this.q.findViewById(R.id.dynamic_red_dot);
        this.k = (LinearLayout) this.q.findViewById(R.id.dynamic_content_left);
        this.n = (FrameLayout) this.q.findViewById(R.id.dynamic_chart);
        c();
        d();
    }

    private void c() {
        if (LanguageUtil.q(this.j) || LanguageUtil.w(this.j) || LanguageUtil.au(this.j) || LanguageUtil.ag(this.j) || LanguageUtil.x(this.j) || LanguageUtil.c(this.j) || LanguageUtil.at(this.j)) {
            this.w.setTextSize(1, 12.0f);
            this.t.setTextSize(1, 12.0f);
        } else if (LanguageUtil.h(this.j) || LanguageUtil.bn(this.j) || LanguageUtil.y(this.j)) {
            this.w.setTextSize(1, 16.0f);
        }
    }

    private void d() {
        if (nsn.t()) {
            this.h.setTextSize(1, 22.0f);
            this.i.setTextSize(1, 18.0f);
            this.g.setTextSize(1, 16.0f);
            this.w.setTextSize(1, 16.0f);
            this.t.setTextSize(1, 14.0f);
            this.k.setOrientation(1);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionDynamicBp", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.b("SectionDynamicBp", "currentParams is empty");
            return;
        }
        b();
        b(hashMap);
        DynamicBpStatus dynamicBpStatus = (DynamicBpStatus) nru.c(hashMap, "PLAN_STATUS", DynamicBpStatus.class, DynamicBpStatus.NO_PLAN_DATA);
        LogUtil.a("SectionDynamicBp", "planType: ", dynamicBpStatus);
        switch (AnonymousClass1.e[dynamicBpStatus.ordinal()]) {
            case 1:
                nsy.cMA_(this.l, 8);
                nsy.cMA_(this.f2682a, 0);
                nsy.cMA_(this.t, 8);
                nsy.cMr_(this.f2682a, nru.b(hashMap, "PLAN_DESC", ""));
                break;
            case 2:
                setFuturePlanView(hashMap);
                break;
            case 3:
                setNoValidDataView(hashMap);
                break;
            case 4:
            case 5:
                nsy.cMA_(this.d, 0);
                nsy.cMr_(this.d, nru.b(hashMap, "PLAN_DESC_TWO", ""));
            case 6:
                setCompletePlanView(hashMap);
                break;
            case 7:
                setInProgressView(hashMap);
                break;
            case 8:
                setNoDataView(hashMap);
                break;
        }
        e(hashMap);
    }

    /* renamed from: com.huawei.health.knit.section.view.SectionDynamicBp$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[DynamicBpStatus.values().length];
            e = iArr;
            try {
                iArr[DynamicBpStatus.NO_PLAN_DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[DynamicBpStatus.FUTURE_PLAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[DynamicBpStatus.FINISH_NO_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                e[DynamicBpStatus.NOT_ENOUGH_DATA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                e[DynamicBpStatus.PLAN_TERMINATE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                e[DynamicBpStatus.COMPLETE_PLAN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                e[DynamicBpStatus.PLAN_IN_PROGRESS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                e[DynamicBpStatus.PLAN_IN_PROGRESS_NO_DATA.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private void e(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionDynamicBp", "bindViewTreeObserver");
        Object d = nru.d(hashMap, "READ_BIMAP", (Object) null);
        if (d instanceof HashMap) {
            this.p = (HashMap) d;
        }
        View view = this.q;
        ViewTreeVisibilityListener.Zy_(view, new ViewTreeVisibilityListener(view, this));
    }

    private void b(HashMap<String, Object> hashMap) {
        Object d = nru.d(hashMap, "CLICK_EVENT_LISTENER", (Object) null);
        if (d instanceof View.OnClickListener) {
            this.b.setOnClickListener((View.OnClickListener) d);
        }
        nsy.d(this.x, 0, nru.b(hashMap, "TITLE", ""), 8, 8);
        nsy.cMr_(this.w, nru.b(hashMap, "SUBTITLE", ""));
        this.e.setImageResource(((Integer) nru.c(hashMap, "RIGHT_ICON", Integer.class, null)).intValue());
        this.r.setImageDrawable(nru.cKj_(hashMap, "HEAD_PIC", null));
        nsy.cMr_(this.t, nru.b(hashMap, "PLAN_TIME", ""));
        this.m.setVisibility(8);
    }

    private void setCompletePlanView(HashMap<String, Object> hashMap) {
        nsy.cMA_(this.c, 0);
        nsy.cMA_(this.v, 0);
        d(12, 15);
        nsy.cMr_(this.h, nru.b(hashMap, "VALUE", ""));
        nsy.cMr_(this.i, nru.b(hashMap, "UNIT", ""));
        nsy.cMr_(this.g, nru.b(hashMap, "LEVEL", ""));
        nsy.cMu_(this.g, this.j.getColor(nru.d((Map) hashMap, "LEVEL_COLOR", R.color._2131297090_res_0x7f090342)));
        nsy.cMr_(this.c, nru.b(hashMap, "PLAN_DESC_ONE", ""));
        this.m.setVisibility(((Boolean) nru.c(hashMap, "READ_STATUS", Boolean.class, true)).booleanValue() ? 8 : 0);
        this.v.setProgress((int) nru.e(hashMap, "PROGRESS", 0.0f));
    }

    private void d(int i, int i2) {
        FrameLayout frameLayout = this.n;
        if (frameLayout != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) frameLayout.getLayoutParams();
            layoutParams.removeRule(i);
            layoutParams.addRule(i2);
            this.n.setLayoutParams(layoutParams);
        }
    }

    private void setInProgressView(HashMap<String, Object> hashMap) {
        nsy.cMA_(this.c, 0);
        nsy.cMA_(this.o, 0);
        d(15, 12);
        nsy.cMA_(this.f, 8);
        nsy.cMA_(this.g, 8);
        nsy.cMr_(this.h, nru.b(hashMap, "VALUE", ""));
        nsy.cMr_(this.i, nru.b(hashMap, "UNIT", ""));
        nsy.cMr_(this.c, nru.b(hashMap, "PLAN_DESC_ONE", ""));
        this.y.setProgress(nru.d((Map) hashMap, "PROGRESS", 0));
        nsy.cMr_(this.u, nru.e(hashMap, "PROGRESS_DESC", ""));
    }

    private void setFuturePlanView(HashMap<String, Object> hashMap) {
        nsy.cMA_(this.l, 8);
        nsy.cMA_(this.f2682a, 0);
        nsy.cMA_(this.c, 0);
        nsy.cMr_(this.f2682a, nru.b(hashMap, "PLAN_DESC", ""));
        Typeface create = Typeface.create(this.j.getString(R$string.textFontFamilyMedium), 0);
        this.s = create;
        this.f2682a.setTypeface(create);
        nsy.cMr_(this.c, nru.b(hashMap, "FUTURE_PLAN_DESC", ""));
    }

    private void setNoDataView(HashMap<String, Object> hashMap) {
        nsy.cMA_(this.o, 0);
        d(15, 12);
        this.y.setProgress(nru.d((Map) hashMap, "PROGRESS", 0));
        nsy.cMr_(this.u, nru.e(hashMap, "PROGRESS_DESC", ""));
        nsy.cMr_(this.h, nru.b(hashMap, "VALUE", ""));
        nsy.cMr_(this.i, nru.b(hashMap, "UNIT", ""));
        nsy.cMA_(this.g, 8);
        nsy.cMA_(this.f, 8);
    }

    private void setNoValidDataView(HashMap<String, Object> hashMap) {
        nsy.cMA_(this.c, 0);
        nsy.cMr_(this.h, nru.b(hashMap, "VALUE", ""));
        nsy.cMr_(this.i, nru.b(hashMap, "UNIT", ""));
        nsy.cMA_(this.g, 8);
        nsy.cMA_(this.f, 8);
        nsy.cMr_(this.c, nru.b(hashMap, "PLAN_DESC_ONE", ""));
    }

    private void b() {
        nsy.cMA_(this.f2682a, 8);
        nsy.cMA_(this.v, 8);
        nsy.cMA_(this.o, 8);
        nsy.cMA_(this.c, 8);
        nsy.cMA_(this.d, 8);
        nsy.cMA_(this.t, 0);
        nsy.cMA_(this.g, 0);
        nsy.cMA_(this.f, 0);
        nsy.cMA_(this.l, 0);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
    public void biEvent() {
        a(0);
    }

    private void a(int i) {
        this.p.put("event", Integer.valueOf(i));
        LogUtil.a("SectionDynamicBp", "uploadBiEvent card , BLOOD_PRESSURE_DETAIL_2010025: ", this.p.toString());
        ixx.d().d(this.j, AnalyticsValue.HEALTH_HOME_BLOOD_PRESSURE_DETAIL_2010025.value(), this.p, 0);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionDynamicBp";
    }
}
