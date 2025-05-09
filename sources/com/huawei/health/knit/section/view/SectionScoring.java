package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.SectionScoring;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.ratingbar.HealthStarRatingBar;
import defpackage.eet;
import defpackage.nrt;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionScoring extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    protected HealthCardView f2725a;
    private HealthTextView aa;
    private LinearLayout ab;
    private HealthTextView ac;
    private HealthTextView ad;
    private HealthTextView ae;
    private LinearLayout af;
    private HealthTextView ag;
    private HealthStarRatingBar ah;
    private HealthTextView ai;
    protected LinearLayout b;
    protected boolean c;
    protected LinearLayout d;
    protected LinearLayout e;
    protected LinearLayout f;
    protected LinearLayout g;
    protected LinearLayout h;
    protected boolean i;
    protected boolean j;
    protected HealthTextView k;
    protected HealthTextView l;
    protected HealthTextView m;
    protected HealthTextView n;
    protected HealthTextView o;
    protected HealthTextView p;
    protected HealthTextView q;
    protected LinearLayout r;
    protected HealthTextView s;
    protected HealthTextView t;
    protected LinearLayout u;
    private HealthTextView v;
    private HealthTextView w;
    protected LinearLayout x;
    protected HealthTextView y;
    private ImageView z;

    public SectionScoring(Context context) {
        this(context, null);
    }

    public SectionScoring(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionScoring(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = false;
        this.c = false;
        this.i = false;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(final Context context) {
        LogUtil.a("Section_Scoring", "onCreateView");
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_scoring_layout, (ViewGroup) this, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.section_root_view);
        this.af = linearLayout;
        linearLayout.post(new Runnable() { // from class: ehf
            @Override // java.lang.Runnable
            public final void run() {
                SectionScoring.this.a(context);
            }
        });
        this.b = (LinearLayout) inflate.findViewById(R.id.core_sleep_scoring_layout);
        this.x = (LinearLayout) inflate.findViewById(R.id.suggest_title_layout);
        HealthCardView healthCardView = (HealthCardView) inflate.findViewById(R.id.chart_layout);
        this.f2725a = healthCardView;
        healthCardView.setVisibility(8);
        this.r = (LinearLayout) inflate.findViewById(R.id.scoring_layout);
        this.u = (LinearLayout) inflate.findViewById(R.id.suggest_root_layout);
        this.e = (LinearLayout) inflate.findViewById(R.id.detect_abnormal_layout);
        this.f = (LinearLayout) inflate.findViewById(R.id.level_layout);
        this.o = (HealthTextView) inflate.findViewById(R.id.level_title);
        this.m = (HealthTextView) inflate.findViewById(R.id.level_value);
        this.g = (LinearLayout) inflate.findViewById(R.id.level_layout_week_month);
        this.n = (HealthTextView) inflate.findViewById(R.id.level_title_week_month);
        this.p = (HealthTextView) inflate.findViewById(R.id.level_value_week_month);
        this.q = (HealthTextView) inflate.findViewById(R.id.scoring_title);
        this.t = (HealthTextView) inflate.findViewById(R.id.scoring_value);
        this.ag = (HealthTextView) inflate.findViewById(R.id.scoring_unit);
        this.ah = (HealthStarRatingBar) inflate.findViewById(R.id.scoring_rating);
        this.s = (HealthTextView) inflate.findViewById(R.id.scoring_desc);
        this.y = (HealthTextView) inflate.findViewById(R.id.suggest_title);
        this.ae = (HealthTextView) inflate.findViewById(R.id.suggest_content);
        this.ac = (HealthTextView) inflate.findViewById(R.id.detect_abnormal);
        if (nrt.a(context)) {
            this.ac.setTextColor(context.getColor(R.color._2131296998_res_0x7f0902e6));
        } else {
            this.ac.setTextColor(context.getColor(R.color._2131296921_res_0x7f090299));
        }
        this.ab = (LinearLayout) inflate.findViewById(R.id.harvard_logo_layout);
        this.z = (ImageView) inflate.findViewById(R.id.harvard_image);
        this.ai = (HealthTextView) inflate.findViewById(R.id.harvard_logo_text);
        this.d = (LinearLayout) inflate.findViewById(R.id.common_sleep_scoring_layout);
        this.ad = (HealthTextView) inflate.findViewById(R.id.common_sleep_total_sleep_title);
        this.aa = (HealthTextView) inflate.findViewById(R.id.common_sleep_sleep_time);
        e();
        return inflate;
    }

    public /* synthetic */ void a(Context context) {
        this.af.setMinimumWidth(nsn.h(context));
    }

    private void e() {
        if (LanguageUtil.bl(BaseApplication.getContext())) {
            this.y.setBreakStrategy(2);
            this.y.setHyphenationFrequency(2);
            this.ae.setBreakStrategy(2);
            this.ae.setHyphenationFrequency(2);
        }
    }

    private void c() {
        Context context = BaseApplication.getContext();
        if (LanguageUtil.av(context) || LanguageUtil.bf(context) || LanguageUtil.bq(context) || LanguageUtil.ar(context) || LanguageUtil.bb(context) || LanguageUtil.bh(context) || LanguageUtil.o(context) || LanguageUtil.r(context) || LanguageUtil.ap(context) || LanguageUtil.bo(context)) {
            this.k.setBreakStrategy(2);
            this.k.setHyphenationFrequency(2);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("Section_Scoring", "bindParamsToView");
        this.e.setVisibility(8);
        this.c = false;
        setLayoutVisible(hashMap);
        setScoringContent(hashMap);
        setHarvardContent(hashMap);
        setSuggestContent(hashMap);
        setCommonContent(hashMap);
        setLevelContent(hashMap);
        setDetectAbnormal(hashMap);
    }

    protected void setLayoutVisible(Map<String, Object> map) {
        this.i = nru.d((Map) map, "IS_DATA_TYPE_DAY", false);
        b();
        Object obj = map.get("IS_CORE_SLEEP");
        if (obj instanceof Boolean) {
            b(((Boolean) obj).booleanValue());
        }
        Object obj2 = map.get("IS_PILLOW");
        if (obj2 instanceof Boolean) {
            this.j = ((Boolean) obj2).booleanValue();
            d();
        }
        Object obj3 = map.get("IS_PHONE_SLEEP");
        if (obj3 instanceof Boolean) {
            e(((Boolean) obj3).booleanValue());
        }
    }

    protected void b() {
        nsy.cMA_(this.f, 8);
        nsy.cMA_(this.g, 8);
        if (this.i) {
            this.h = this.f;
            this.l = this.o;
            this.k = this.m;
        } else {
            this.h = this.g;
            this.l = this.n;
            this.k = this.p;
        }
        c();
        LogUtil.a("Section_Scoring", "mIsDataTypeDay: ", Boolean.valueOf(this.i));
    }

    protected void setScoringContent(Map<String, Object> map) {
        HealthTextView healthTextView;
        HealthTextView healthTextView2;
        if (map.containsKey("SCORING_TITLE") && eet.f(map.get("SCORING_TITLE")) && (healthTextView2 = this.q) != null) {
            healthTextView2.setText((String) map.get("SCORING_TITLE"));
        }
        if (map.containsKey("SCORING_VALUE") && eet.f(map.get("SCORING_VALUE")) && (healthTextView = this.t) != null) {
            healthTextView.setText((String) map.get("SCORING_VALUE"));
            this.k.setText((CharSequence) null);
        }
        if (map.containsKey("SCORING_UNIT") && this.ag != null) {
            if (eet.f(map.get("SCORING_UNIT"))) {
                this.ag.setText((String) map.get("SCORING_UNIT"));
                this.ag.setVisibility(0);
            } else {
                this.ag.setVisibility(8);
            }
        }
        if (map.containsKey("SCORING_RATING") && this.ah != null) {
            if (eet.c(map.get("SCORING_RATING"))) {
                this.ah.setStar(((Integer) map.get("SCORING_RATING")).intValue());
                this.ah.setVisibility(0);
            } else {
                this.ah.setVisibility(8);
            }
        }
        if (!map.containsKey("SCORING_DESC") || this.s == null) {
            return;
        }
        if (eet.f(map.get("SCORING_DESC"))) {
            this.s.setText((String) map.get("SCORING_DESC"));
            this.s.setVisibility(0);
        } else {
            this.s.setVisibility(8);
        }
    }

    private void setHarvardContent(Map<String, Object> map) {
        if (map.containsKey("HARVARD_LOGO_TEXT") && this.ai != null && this.ab != null) {
            if (eet.f(map.get("HARVARD_LOGO_TEXT"))) {
                this.ai.setText((String) map.get("HARVARD_LOGO_TEXT"));
                this.ab.setVisibility(0);
            } else {
                this.ab.setVisibility(8);
            }
        }
        if (!map.containsKey("HARVARD_IMAGE") || this.z == null) {
            return;
        }
        if (map.get("HARVARD_IMAGE") instanceof Drawable) {
            this.z.setVisibility(0);
            this.z.setImageDrawable((Drawable) map.get("HARVARD_IMAGE"));
        } else {
            this.z.setVisibility(8);
        }
    }

    protected void setSuggestContent(Map<String, Object> map) {
        HealthTextView healthTextView;
        HealthTextView healthTextView2;
        if (map.containsKey("SUGGEST_TITLE") && eet.f(map.get("SUGGEST_TITLE")) && (healthTextView2 = this.y) != null) {
            healthTextView2.setText((String) map.get("SUGGEST_TITLE"));
        }
        if (map.containsKey("SUGGEST_CONTENT") && eet.f(map.get("SUGGEST_CONTENT")) && (healthTextView = this.ae) != null) {
            healthTextView.setText((String) map.get("SUGGEST_CONTENT"));
        }
    }

    protected void setCommonContent(Map<String, Object> map) {
        HealthTextView healthTextView;
        HealthTextView healthTextView2;
        if (map.containsKey("COMMON_TITLE_TEXT") && eet.f(map.get("COMMON_TITLE_TEXT")) && (healthTextView2 = this.ad) != null) {
            healthTextView2.setText((String) map.get("COMMON_TITLE_TEXT"));
        }
        if (map.containsKey("COMMON_SLEEP_TEXT") && eet.b(map.get("COMMON_SLEEP_TEXT")) && (healthTextView = this.aa) != null) {
            healthTextView.setText((SpannableString) map.get("COMMON_SLEEP_TEXT"));
            this.t.setText((CharSequence) null);
        }
    }

    protected void setLevelContent(Map<String, Object> map) {
        HealthTextView healthTextView;
        HealthTextView healthTextView2;
        HealthTextView healthTextView3;
        if (map.containsKey("LEVEL_TITLE") && eet.f(map.get("LEVEL_TITLE")) && (healthTextView3 = this.l) != null) {
            healthTextView3.setText((String) map.get("LEVEL_TITLE"));
        }
        if (map.containsKey("LEVEL_VALUE") && eet.f(map.get("LEVEL_VALUE")) && (healthTextView2 = this.k) != null) {
            healthTextView2.setText((String) map.get("LEVEL_VALUE"));
            this.t.setText((CharSequence) null);
        }
        if (map.containsKey("LEVEL_VALUE_COLOR") && eet.c(map.get("LEVEL_VALUE_COLOR")) && (healthTextView = this.k) != null) {
            healthTextView.setTextColor(((Integer) map.get("LEVEL_VALUE_COLOR")).intValue());
        }
    }

    protected void setDetectAbnormal(final Map<String, Object> map) {
        HealthTextView healthTextView;
        if (map.containsKey("DETECT_ABNORMAL")) {
            Object obj = map.get("DETECT_ABNORMAL");
            this.c = true;
            this.e.setVisibility(0);
            if (map.containsKey("DETECT_ABNORMAL_JUMP") && eet.f(map.get("DETECT_ABNORMAL_JUMP")) && eet.f(obj) && this.ac != null) {
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                String str = (String) obj;
                spannableStringBuilder.append((CharSequence) str);
                spannableStringBuilder.append((CharSequence) map.get("DETECT_ABNORMAL_JUMP"));
                spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.huawei.health.knit.section.view.SectionScoring.4
                    @Override // android.text.style.ClickableSpan
                    public void onClick(View view) {
                        if (eet.a(map.get("CLICK_EVENT_LISTENER"))) {
                            ((OnClickSectionListener) map.get("CLICK_EVENT_LISTENER")).onClick("COMMON_CLICK_EVENT");
                        }
                        ViewClickInstrumentation.clickOnView(view);
                    }

                    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                    public void updateDrawState(TextPaint textPaint) {
                        super.updateDrawState(textPaint);
                        textPaint.setUnderlineText(false);
                    }
                }, str.length(), str.length() + ((String) map.get("DETECT_ABNORMAL_JUMP")).length(), 33);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(BaseApplication.getContext().getColor(R.color.emui_accent)), str.length(), str.length() + ((String) map.get("DETECT_ABNORMAL_JUMP")).length(), 33);
                this.ac.setMovementMethod(LinkMovementMethod.getInstance());
                this.ac.setText(spannableStringBuilder);
                return;
            }
            if (!eet.f(obj) || (healthTextView = this.ac) == null) {
                return;
            }
            healthTextView.setText((String) obj);
        }
    }

    public void setSuggestRootLayoutVisibility(int i) {
        LinearLayout linearLayout = this.u;
        if (linearLayout != null) {
            if (this.j) {
                linearLayout.setVisibility(8);
                this.e.setVisibility(8);
                return;
            }
            linearLayout.setVisibility(i);
            if (i == 0 && this.c) {
                this.e.setVisibility(0);
            } else {
                this.e.setVisibility(8);
            }
        }
    }

    public String getSleepScoring() {
        HealthTextView healthTextView = this.t;
        return healthTextView != null ? healthTextView.getText().toString().trim() : "";
    }

    public String getSleepStatus() {
        HealthTextView healthTextView = this.k;
        return healthTextView != null ? healthTextView.getText().toString().trim() : "";
    }

    public String getScoringDesc() {
        HealthTextView healthTextView = this.s;
        return healthTextView != null ? healthTextView.getText().toString().trim() : "";
    }

    public String getSleepTimeHour() {
        HealthTextView healthTextView = this.w;
        return healthTextView != null ? healthTextView.getText().toString().trim() : "";
    }

    public String getCommonSleepText() {
        HealthTextView healthTextView = this.aa;
        return healthTextView != null ? healthTextView.getText().toString().trim() : "";
    }

    public String getSleepTimeMinute() {
        HealthTextView healthTextView = this.v;
        return healthTextView != null ? healthTextView.getText().toString().trim() : "";
    }

    protected void b(boolean z) {
        LinearLayout linearLayout;
        HealthTextView healthTextView;
        LinearLayout linearLayout2 = this.b;
        if (linearLayout2 == null || this.d == null || this.h == null || (linearLayout = this.r) == null) {
            return;
        }
        if (z) {
            linearLayout2.setVisibility(0);
            if (this.i && (healthTextView = this.q) != null) {
                healthTextView.setVisibility(8);
            }
            this.d.setVisibility(8);
            this.h.setVisibility(8);
            this.r.setVisibility(0);
            return;
        }
        linearLayout.setVisibility(8);
        this.b.setVisibility(8);
        this.d.setVisibility(0);
    }

    protected void e(boolean z) {
        LinearLayout linearLayout;
        LinearLayout linearLayout2 = this.b;
        if (linearLayout2 == null || this.d == null || (linearLayout = this.h) == null || this.r == null) {
            return;
        }
        if (z) {
            linearLayout2.setVisibility(0);
            this.d.setVisibility(8);
            this.h.setVisibility(0);
            this.r.setVisibility(8);
            return;
        }
        linearLayout.setVisibility(8);
        this.b.setVisibility(8);
        this.d.setVisibility(0);
    }

    protected void d() {
        if (this.h == null || this.r == null || this.u == null || this.x == null || !this.j) {
            return;
        }
        this.h.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.h.setVisibility(0);
        this.r.setVisibility(8);
        this.u.setVisibility(8);
        this.x.setVisibility(8);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_Scoring";
    }
}
