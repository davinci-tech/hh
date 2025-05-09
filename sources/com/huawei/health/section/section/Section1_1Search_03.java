package com.huawei.health.section.section;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.ebs;
import defpackage.eet;
import defpackage.fbh;
import defpackage.koq;
import defpackage.nru;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class Section1_1Search_03 extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2979a;
    private Context b;
    private LinearLayout c;
    private LinearLayout d;
    private List<ebs> e;
    private HealthTextView f;
    private HealthTextView g;
    private LinearLayout h;
    private LinearLayout i;
    private HealthTextView j;
    private HealthTextView k;
    private View l;
    private HealthTextView m;
    private HealthTextView[] n;
    private OnClickSectionListener o;
    private final View.OnClickListener p;

    public Section1_1Search_03(Context context) {
        this(context, null);
    }

    public Section1_1Search_03(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Section1_1Search_03(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.p = new View.OnClickListener() { // from class: com.huawei.health.section.section.Section1_1Search_03.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view == Section1_1Search_03.this.j || view == Section1_1Search_03.this.g || view == Section1_1Search_03.this.f) {
                    Section1_1Search_03.this.setSelectedTextView((HealthTextView) view);
                } else if ((view == Section1_1Search_03.this.k || view == Section1_1Search_03.this.m || view == Section1_1Search_03.this.f2979a) && Section1_1Search_03.this.o != null) {
                    Section1_1Search_03.this.o.onClick("COMMON_CLICK_EVENT");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.b = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section1_1search_03_layout, (ViewGroup) this, false);
        this.l = inflate;
        this.k = (HealthTextView) inflate.findViewById(R.id.section_sub_header);
        this.m = (HealthTextView) this.l.findViewById(R.id.section_sub_title);
        this.d = (LinearLayout) this.l.findViewById(R.id.left_area);
        this.j = (HealthTextView) this.l.findViewById(R.id.left_text);
        this.h = (LinearLayout) this.l.findViewById(R.id.right_area);
        this.f = (HealthTextView) this.l.findViewById(R.id.right_text);
        this.i = (LinearLayout) this.l.findViewById(R.id.middle_area);
        this.g = (HealthTextView) this.l.findViewById(R.id.middle_text);
        this.f2979a = (HealthTextView) this.l.findViewById(R.id.brief_content);
        this.n = new HealthTextView[]{this.j, this.g, this.f};
        LinearLayout linearLayout = (LinearLayout) this.l.findViewById(R.id.gradient_layout);
        this.c = linearLayout;
        linearLayout.getBackground().setAlpha(179);
        return this.l;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("Section1_1Search_03", "bindParamsToView ");
        this.d.setVisibility(8);
        this.i.setVisibility(8);
        this.h.setVisibility(8);
        this.m.setVisibility(8);
        nsy.cMr_(this.k, nru.b(hashMap, "TITLE", ""));
        setSubTitle(nru.d(hashMap, "SUBTITLE", (Object) null));
        this.e = nru.d(hashMap, "SECTION_HEALTH_DICT_BEAN", ebs.class, new ArrayList());
        this.o = nru.a(hashMap, "CLICK_EVENT_LISTENER", null);
        a();
        b();
        setBiEvent(nru.d(hashMap, "ITEM_BI_EVENT_MAP", (Object) null));
    }

    private void setBiEvent(Object obj) {
        if (obj instanceof Map) {
            fbh.e(this.b, (Map<String, Object>) obj);
        }
    }

    private void setSubTitle(Object obj) {
        if (eet.f(obj)) {
            nsy.cMs_(this.m, (String) obj, true);
        } else {
            nsy.cMA_(this.m, 8);
        }
    }

    private void a() {
        if (koq.b(this.e)) {
            return;
        }
        nsy.cMA_(this.d, 0);
        nsy.cMr_(this.j, this.e.get(0).b());
        c(this.j, true);
        if (this.e.size() > 1) {
            nsy.cMA_(this.i, 0);
            nsy.cMr_(this.g, this.e.get(1).b());
            c(this.g, false);
        }
        if (this.e.size() > 2) {
            nsy.cMA_(this.h, 0);
            nsy.cMr_(this.f, this.e.get(2).b());
            c(this.f, false);
        }
        nsy.cMr_(this.f2979a, this.e.get(0).a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelectedTextView(HealthTextView healthTextView) {
        if (this.n == null) {
            return;
        }
        int i = 0;
        while (true) {
            HealthTextView[] healthTextViewArr = this.n;
            if (i >= healthTextViewArr.length) {
                return;
            }
            HealthTextView healthTextView2 = healthTextViewArr[i];
            boolean z = healthTextView2 == healthTextView;
            c(healthTextView2, z);
            if (z && koq.d(this.e, i)) {
                this.f2979a.setText(this.e.get(i).a());
            }
            i++;
        }
    }

    private void c(HealthTextView healthTextView, boolean z) {
        if (healthTextView == null) {
            return;
        }
        healthTextView.setTextColor(ContextCompat.getColor(this.b, z ? R.color._2131299236_res_0x7f090ba4 : R.color._2131299244_res_0x7f090bac));
        healthTextView.setTypeface(Typeface.create(getResources().getString(z ? R$string.textFontFamilyMedium : R$string.textFontFamilyRegular), 0));
    }

    private void b() {
        nsy.cMn_(this.j, this.p);
        nsy.cMn_(this.g, this.p);
        nsy.cMn_(this.f, this.p);
        nsy.cMn_(this.k, this.p);
        nsy.cMn_(this.m, this.p);
        nsy.cMn_(this.f2979a, this.p);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section1_1Search_03";
    }
}
