package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.edy;
import defpackage.koq;
import defpackage.nru;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionEntryCardThreeItems extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f2685a;
    private ImageView b;
    private LinearLayout c;
    private boolean d;
    private LinearLayout e;
    private ImageView f;
    private HealthTextView g;
    private ImageView h;
    private HealthTextView i;
    private HealthTextView j;

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected boolean isSupportSafeRegion() {
        return false;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public boolean isSupportShare() {
        return false;
    }

    public SectionEntryCardThreeItems(Context context) {
        this(context, null);
    }

    public SectionEntryCardThreeItems(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionEntryCardThreeItems(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = true;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("Section_Entry_Card_ThreeViews", "onCreateView");
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_entry_card_threeitems, (ViewGroup) this, false);
        this.c = (LinearLayout) inflate.findViewById(R.id.section_button_view1);
        this.b = (ImageView) inflate.findViewById(R.id.section_icon1);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.section_title1);
        this.i = healthTextView;
        healthTextView.setSplittable(false);
        this.f2685a = (LinearLayout) inflate.findViewById(R.id.section_button_view2);
        this.f = (ImageView) inflate.findViewById(R.id.section_icon2);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.section_title2);
        this.g = healthTextView2;
        healthTextView2.setSplittable(false);
        this.e = (LinearLayout) inflate.findViewById(R.id.section_button_view3);
        this.h = (ImageView) inflate.findViewById(R.id.section_icon3);
        HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.section_title3);
        this.j = healthTextView3;
        healthTextView3.setSplittable(false);
        setTextSizeForOversea(context);
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        int dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        inflate.setPadding(((Integer) safeRegionWidth.first).intValue() + dimensionPixelSize, 0, dimensionPixelSize + ((Integer) safeRegionWidth.first).intValue(), 0);
        return inflate;
    }

    private void setTextSizeForOversea(Context context) {
        if (LanguageUtil.ao(context) || LanguageUtil.g(context)) {
            this.i.setTextSize(1, 11.0f);
            this.g.setTextSize(1, 11.0f);
            this.j.setTextSize(1, 11.0f);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("Section_Entry_Card_ThreeViews", "no need to bind");
            return;
        }
        LogUtil.a("Section_Entry_Card_ThreeViews", hashMap);
        List d = nru.d(hashMap, "SECTION_ENTRY_BUTTON_BEAN_LIST", edy.class, null);
        if (koq.b(d)) {
            LogUtil.a("Section_Entry_Card_ThreeViews", "beanList is empty");
            return;
        }
        int size = d.size();
        if (size > 4) {
            LogUtil.a("Section_Entry_Card_ThreeViews", "buttonNum is ", Integer.valueOf(size));
            return;
        }
        if (this.d) {
            this.d = false;
            ObserverManagerUtil.c("SectionEntryCardVisible", Integer.valueOf(size));
        }
        if (size == 1) {
            this.f2685a.setVisibility(8);
            this.e.setVisibility(8);
            ajh_((edy) d.get(0), this.c, this.b, this.i);
            return;
        }
        if (size == 2) {
            this.e.setVisibility(8);
            edy edyVar = (edy) d.get(0);
            edy edyVar2 = (edy) d.get(1);
            ajh_(edyVar, this.c, this.b, this.i);
            ajh_(edyVar2, this.f2685a, this.f, this.g);
            return;
        }
        if (size == 3) {
            edy edyVar3 = (edy) d.get(0);
            edy edyVar4 = (edy) d.get(1);
            edy edyVar5 = (edy) d.get(2);
            ajh_(edyVar3, this.c, this.b, this.i);
            ajh_(edyVar4, this.f2685a, this.f, this.g);
            ajh_(edyVar5, this.e, this.h, this.j);
        }
    }

    private void ajh_(edy edyVar, LinearLayout linearLayout, ImageView imageView, HealthTextView healthTextView) {
        nsy.cMj_(linearLayout, getContext().getDrawable(edyVar.e()));
        nsy.cMp_(imageView, edyVar.a());
        if (LanguageUtil.bc(getContext())) {
            imageView.setScaleX(-1.0f);
        }
        nsy.cMr_(healthTextView, getContext().getString(edyVar.c()));
        nsy.cMn_(linearLayout, edyVar.agO_());
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_Entry_Card_ThreeViews";
    }
}
