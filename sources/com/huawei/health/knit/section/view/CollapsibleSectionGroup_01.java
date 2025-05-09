package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.knit.data.KnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.knit.section.view.SectionGroup;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.nru;
import defpackage.nsy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class CollapsibleSectionGroup_01 extends SectionGroup {

    /* renamed from: a, reason: collision with root package name */
    private Context f2623a;
    private LinearLayout b;
    private HealthCardView c;
    private ImageView d;
    private HealthTextView e;
    private List<BaseSection> g;
    private View h;
    private boolean i;

    @Override // com.huawei.health.knit.section.view.SectionGroup, com.huawei.health.knit.section.view.BaseSection
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup
    public /* bridge */ /* synthetic */ HealthCardView getCardView() {
        return super.getCardView();
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup, com.huawei.health.knit.section.view.BaseSection
    public /* bridge */ /* synthetic */ KnitFragment getKnitFragment() {
        return super.getKnitFragment();
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup, android.view.View
    public /* bridge */ /* synthetic */ LinearLayout getRootView() {
        return super.getRootView();
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup
    public /* bridge */ /* synthetic */ List getSectionList() {
        return super.getSectionList();
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup, com.huawei.health.knit.section.view.BaseSection
    public /* bridge */ /* synthetic */ View onCreateView(Context context) {
        return super.onCreateView(context);
    }

    public CollapsibleSectionGroup_01(Context context) {
        super(context);
    }

    public CollapsibleSectionGroup_01(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CollapsibleSectionGroup_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2623a = context;
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup, com.huawei.health.knit.section.view.BaseSection
    public void initView(KnitFragment knitFragment, int i) {
        super.initView(knitFragment, i);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.collapsible_section_group_button_layout, (ViewGroup) getRootView(), false);
        this.h = inflate;
        this.e = (HealthTextView) inflate.findViewById(R.id.sleep_expand_title);
        this.c = getCardView();
        LinearLayout linearLayout = (LinearLayout) this.h.findViewById(R.id.sleep_expand_btn_layout);
        this.b = linearLayout;
        linearLayout.setBackgroundColor(getContext().getResources().getColor(R.color._2131299296_res_0x7f090be0));
        this.d = (ImageView) this.b.findViewById(R.id.sleep_service_expand);
        this.i = false;
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup, com.huawei.health.knit.section.view.BaseSection
    public void onBind(SectionBean sectionBean) {
        super.onBind(sectionBean);
        ViewParent parent = this.h.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.h);
        }
        getRootView().addView(this.h);
        KnitDataProvider c = sectionBean.c();
        if (c != null) {
            c.parseParams(getContextRef(), getViewMap(), sectionBean.e());
            bindParamsToView(getViewMap());
        }
        a(getViewMap());
    }

    private void e() {
        for (BaseSection baseSection : this.g) {
            LogUtil.a("CollapsibleSectionGroup_01", "updateSection : ", baseSection.getClass().getSimpleName());
            String simpleName = baseSection.getClass().getSimpleName();
            simpleName.hashCode();
            if (simpleName.equals("SectionStatistic")) {
                if (this.i) {
                    SectionStatistic sectionStatistic = (SectionStatistic) baseSection;
                    sectionStatistic.setPieChartLayoutVisibility(0);
                    sectionStatistic.setPressureAdviceTvVisibility(0);
                } else {
                    SectionStatistic sectionStatistic2 = (SectionStatistic) baseSection;
                    sectionStatistic2.setPieChartLayoutVisibility(8);
                    sectionStatistic2.setPressureAdviceTvVisibility(8);
                }
            }
        }
    }

    private void a(final HashMap<String, Object> hashMap) {
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.CollapsibleSectionGroup_01.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CollapsibleSectionGroup_01.this.i = !r0.i;
                CollapsibleSectionGroup_01.this.d(hashMap);
                if (!CollapsibleSectionGroup_01.this.i) {
                    CollapsibleSectionGroup_01.this.getKnitFragment().getHealthScrollView().fling(0);
                    CollapsibleSectionGroup_01.this.getKnitFragment().getHealthScrollView().smoothScrollTo(0, 0);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        d(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HashMap<String, Object> hashMap) {
        if (this.i) {
            this.d.setImageDrawable(getContext().getResources().getDrawable(R.drawable._2131428280_res_0x7f0b03b8));
            nsy.cMr_(this.e, nru.b(hashMap, "ITEM_BUTTON_TEXT", ""));
        } else {
            this.d.setImageDrawable(getContext().getResources().getDrawable(R.drawable._2131428279_res_0x7f0b03b7));
            nsy.cMr_(this.e, nru.b(hashMap, "BUTTON_TEXT", ""));
        }
        if (koq.b(this.g)) {
            return;
        }
        e();
        for (SectionGroup.d dVar : this.mViewList) {
            if (dVar.d() == 8) {
                LogUtil.a("CollapsibleSectionGroup_01", "set view gone, view: " + dVar.ajj_() + ", visibility: " + dVar.d());
                dVar.ajj_().setVisibility(8);
            }
        }
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup, com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null) {
            return;
        }
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            key.hashCode();
            if (key.equals("IS_GROUP_ARRAY_VISIBILITY") && (value instanceof Integer)) {
                StringBuilder sb = new StringBuilder("set array visibility: ");
                Integer num = (Integer) value;
                sb.append(num.intValue());
                LogUtil.a("CollapsibleSectionGroup_01", sb.toString());
                this.b.setVisibility(num.intValue());
            }
        }
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup
    protected void bindParamsToView(int i, HashMap<String, Object> hashMap) {
        List<BaseSection> sectionList = getSectionList();
        this.g = sectionList;
        if (koq.b(sectionList, i)) {
            LogUtil.b("CollapsibleSectionGroup_01", "position is out of bounds");
        } else {
            this.g.get(i).bindParamsToView(hashMap);
        }
    }

    @Override // com.huawei.health.knit.section.view.SectionGroup
    protected void setSectionGroupCardColor(int i) {
        HealthCardView cardView = getCardView();
        this.c = cardView;
        if (cardView != null) {
            cardView.setBackgroundColor(getContext().getResources().getColor(i));
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "CollapsibleSectionGroup_01";
    }
}
