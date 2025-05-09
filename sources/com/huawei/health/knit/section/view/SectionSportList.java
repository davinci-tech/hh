package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listview.HealthExpandableListView;
import defpackage.nru;
import defpackage.nsf;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class SectionSportList extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2740a;
    private View b;
    private HealthTextView c;
    private HealthExpandableListView e;

    public SectionSportList(Context context) {
        super(context);
    }

    public SectionSportList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SectionSportList(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SCUI_SectionSportList", "onCreateView: ");
        if (this.b == null) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.section_sport_list, (ViewGroup) this, false);
            this.b = inflate;
            HealthExpandableListView healthExpandableListView = (HealthExpandableListView) inflate.findViewById(R.id.sport_record_simplify);
            this.e = healthExpandableListView;
            healthExpandableListView.setClickable(false);
            this.f2740a = (HealthTextView) this.b.findViewById(R.id.go_to_list);
            this.c = (HealthTextView) this.b.findViewById(R.id.sport_list_title);
        }
        return this.b;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SCUI_SectionSportList", "bindParamsToView enter ");
        setViewData(hashMap);
        this.c.setText(nru.b(hashMap, "BAR_CHART_DATE_TEXT", ""));
        this.f2740a.setOnClickListener((View.OnClickListener) nru.d(hashMap, "COMMON_CLICK_EVENT", (Object) null));
        this.e.setOnChildClickListener((ExpandableListView.OnChildClickListener) nru.d(hashMap, "MIDDLE_TIP_TEXT_CLICK_EVENT", (Object) null));
    }

    private void setViewData(HashMap<String, Object> hashMap) {
        Object d = nru.d(hashMap, "REMIND_ADAPTER", (Object) null);
        if (d instanceof BaseExpandableListAdapter) {
            BaseExpandableListAdapter baseExpandableListAdapter = (BaseExpandableListAdapter) d;
            this.e.setAdapter(baseExpandableListAdapter);
            this.e.expandGroup(0, false);
            ajJ_(this.e, baseExpandableListAdapter);
        }
    }

    private void ajJ_(HealthExpandableListView healthExpandableListView, BaseExpandableListAdapter baseExpandableListAdapter) {
        LinearLayout.LayoutParams layoutParams;
        int childrenCount = baseExpandableListAdapter.getChildrenCount(0);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f2740a.getLayoutParams();
        if (childrenCount <= 0) {
            layoutParams = new LinearLayout.LayoutParams(-1, 0);
            this.c.setVisibility(8);
            layoutParams2.setMargins(0, nsf.b(R.dimen._2131363063_res_0x7f0a04f7), 0, nsf.b(R.dimen._2131363063_res_0x7f0a04f7));
        } else {
            int i = 0;
            for (int i2 = 0; i2 < baseExpandableListAdapter.getChildrenCount(0); i2++) {
                View childView = baseExpandableListAdapter.getChildView(0, i2, false, null, healthExpandableListView);
                if (childView != null) {
                    childView.measure(0, 0);
                    i += childView.getMeasuredHeight();
                }
            }
            layoutParams = new LinearLayout.LayoutParams(-1, i);
            this.c.setVisibility(0);
            layoutParams2.setMargins(0, nsf.b(R.dimen._2131363063_res_0x7f0a04f7), 0, nsf.b(R.dimen._2131362886_res_0x7f0a0446));
        }
        healthExpandableListView.setLayoutParams(layoutParams);
        this.f2740a.setLayoutParams(layoutParams2);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SCUI_SectionSportList";
    }
}
