package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.edy;
import defpackage.koq;
import defpackage.nru;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionEntryCard extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2683a;
    private ViewTreeObserver.OnGlobalLayoutListener b;
    private int c;
    private int d;
    private boolean e;
    private LinearLayout f;
    private ViewTreeObserver.OnGlobalLayoutListener g;
    private LinearLayout h;
    private LinearLayout i;
    private LinearLayout j;
    private LinearLayout k;
    private LinearLayout l;
    private ImageView m;
    private ImageView n;
    private ImageView o;
    private HealthTextView p;
    private HealthTextView q;
    private ImageView r;
    private HealthTextView s;
    private HealthTextView t;

    @Override // com.huawei.health.knit.section.view.BaseSection
    public boolean isSupportShare() {
        return false;
    }

    public SectionEntryCard(Context context) {
        this(context, null);
    }

    public SectionEntryCard(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionEntryCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2683a = true;
        this.e = true;
        this.b = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.knit.section.view.SectionEntryCard.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                SectionEntryCard sectionEntryCard = SectionEntryCard.this;
                sectionEntryCard.ajg_(sectionEntryCard.j, SectionEntryCard.this.f);
            }
        };
        this.g = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.knit.section.view.SectionEntryCard.4
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                SectionEntryCard sectionEntryCard = SectionEntryCard.this;
                sectionEntryCard.ajg_(sectionEntryCard.j, SectionEntryCard.this.f);
            }
        };
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("Section_Entry_Card", "onCreateView");
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_entry_card_layout, (ViewGroup) this, false);
        this.h = (LinearLayout) inflate.findViewById(R.id.section_button_view1);
        this.m = (ImageView) inflate.findViewById(R.id.section_icon1);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.section_title1);
        this.p = healthTextView;
        healthTextView.setSplittable(false);
        this.i = (LinearLayout) inflate.findViewById(R.id.section_button_view2);
        this.n = (ImageView) inflate.findViewById(R.id.section_icon2);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.section_title2);
        this.t = healthTextView2;
        healthTextView2.setSplittable(false);
        this.k = (LinearLayout) inflate.findViewById(R.id.section_button_view3);
        this.o = (ImageView) inflate.findViewById(R.id.section_icon3);
        HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.section_title3);
        this.q = healthTextView3;
        healthTextView3.setSplittable(false);
        this.l = (LinearLayout) inflate.findViewById(R.id.section_button_view4);
        this.r = (ImageView) inflate.findViewById(R.id.section_icon4);
        HealthTextView healthTextView4 = (HealthTextView) inflate.findViewById(R.id.section_title4);
        this.s = healthTextView4;
        healthTextView4.setSplittable(false);
        setTextSizeForOversea(context);
        return inflate;
    }

    private void setTextSizeForOversea(Context context) {
        if (LanguageUtil.ao(context) || LanguageUtil.g(context)) {
            this.p.setTextSize(1, 11.0f);
            this.t.setTextSize(1, 11.0f);
            this.q.setTextSize(1, 11.0f);
            this.s.setTextSize(1, 11.0f);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("Section_Entry_Card", "no need to bind");
            return;
        }
        LogUtil.a("Section_Entry_Card", hashMap);
        List d = nru.d(hashMap, "SECTION_ENTRY_BUTTON_BEAN_LIST", edy.class, null);
        if (koq.b(d)) {
            LogUtil.a("Section_Entry_Card", "beanList is empty");
            return;
        }
        int size = d.size();
        this.d = size;
        if (size > 4) {
            throw new IllegalArgumentException("The item number of SectionEntryButtonBean List more than 4");
        }
        if (this.f2683a) {
            this.f2683a = false;
            ObserverManagerUtil.c("SectionEntryCardVisible", Integer.valueOf(size));
        }
        int i = this.d;
        if (i == 1) {
            this.i.setVisibility(8);
            this.k.setVisibility(8);
            this.l.setVisibility(8);
            aje_((edy) d.get(0), this.h, this.m, this.p);
            return;
        }
        if (i == 2) {
            this.k.setVisibility(8);
            this.l.setVisibility(8);
            setSubTitle(hashMap);
            edy edyVar = (edy) d.get(0);
            edy edyVar2 = (edy) d.get(1);
            aje_(edyVar, this.h, this.m, this.p);
            aje_(edyVar2, this.i, this.n, this.t);
            ajf_(this.h, this.i);
            return;
        }
        if (i == 3) {
            this.i.setVisibility(8);
            edy edyVar3 = (edy) d.get(0);
            edy edyVar4 = (edy) d.get(1);
            edy edyVar5 = (edy) d.get(2);
            aje_(edyVar3, this.h, this.m, this.p);
            aje_(edyVar4, this.k, this.o, this.q);
            aje_(edyVar5, this.l, this.r, this.s);
            ajf_(this.k, this.l);
            return;
        }
        edy edyVar6 = (edy) d.get(0);
        edy edyVar7 = (edy) d.get(1);
        edy edyVar8 = (edy) d.get(2);
        edy edyVar9 = (edy) d.get(3);
        aje_(edyVar6, this.h, this.m, this.p);
        aje_(edyVar7, this.i, this.n, this.t);
        aje_(edyVar8, this.k, this.o, this.q);
        aje_(edyVar9, this.l, this.r, this.s);
    }

    private void setSubTitle(HashMap<String, Object> hashMap) {
        if (hashMap.containsKey("RIGHT_ICON_TEXT")) {
            if (LanguageUtil.h(BaseApplication.getContext())) {
                nsy.cMA_(findViewById(R.id.section_subtitle1), 0);
                nsy.cMr_((TextView) findViewById(R.id.section_subtitle1), getContext().getString(nru.d((Map) hashMap, "RIGHT_ICON_TEXT", 0)));
            } else {
                nsy.cMA_(findViewById(R.id.section_subtitle2), 0);
                nsy.cMr_((TextView) findViewById(R.id.section_subtitle2), getContext().getString(nru.d((Map) hashMap, "RIGHT_ICON_TEXT", 0)));
            }
        }
    }

    private void ajf_(LinearLayout linearLayout, LinearLayout linearLayout2) {
        this.c = 2;
        this.j = linearLayout;
        this.f = linearLayout2;
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(this.b);
        this.f.getViewTreeObserver().addOnGlobalLayoutListener(this.g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ajg_(LinearLayout linearLayout, LinearLayout linearLayout2) {
        int i = this.c - 1;
        this.c = i;
        if (i == 0 && (linearLayout.getLayoutParams() instanceof LinearLayout.LayoutParams) && (linearLayout2.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) linearLayout2.getLayoutParams();
            int height = linearLayout.getHeight();
            int height2 = linearLayout2.getHeight();
            if (height == 0 || height2 == 0) {
                return;
            }
            if (height > height2) {
                layoutParams2.height = height;
                linearLayout2.setLayoutParams(layoutParams2);
            } else {
                layoutParams.height = height2;
                linearLayout.setLayoutParams(layoutParams);
            }
            linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this.b);
            linearLayout2.getViewTreeObserver().removeOnGlobalLayoutListener(this.g);
        }
    }

    private void aje_(edy edyVar, LinearLayout linearLayout, ImageView imageView, HealthTextView healthTextView) {
        nsy.cMj_(linearLayout, getContext().getDrawable(edyVar.e()));
        nsy.cMj_(imageView, getContext().getDrawable(edyVar.a()));
        if (LanguageUtil.bc(getContext())) {
            imageView.setScaleX(-1.0f);
        }
        nsy.cMr_(healthTextView, getContext().getString(edyVar.c()));
        nsy.cMn_(linearLayout, edyVar.agO_());
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_Entry_Card";
    }
}
