package com.huawei.health.section.section;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.health.marketing.request.GlobalSearchContent;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.koq;
import defpackage.nru;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionSimpleList extends BaseSection {
    private HealthRecycleView b;
    private HealthSubHeader c;

    public SectionSimpleList(Context context) {
        super(context);
    }

    public SectionSimpleList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SectionSimpleList(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_simple_list, (ViewGroup) this, false);
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.simple_list_view);
        this.b = healthRecycleView;
        healthRecycleView.setHasFixedSize(true);
        this.b.setNestedScrollingEnabled(false);
        this.b.a(false);
        this.b.d(false);
        this.b.setLayoutManager(new LinearLayoutManager(context, 1, false));
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.section_sub_header);
        this.c = healthSubHeader;
        healthSubHeader.setMoreTextVisibility(4);
        this.c.setMoreLayoutVisibility(4);
        this.c.setSubHeaderBackgroundColor(ContextCompat.getColor(context, R.color._2131296971_res_0x7f0902cb));
        return inflate;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        List<GlobalSearchContent> d = nru.d(hashMap, "SECTION_SIMPLE_LIST_DATA", GlobalSearchContent.class, null);
        boolean b = koq.b(d);
        this.b.setVisibility(!b ? 0 : 8);
        SimpleListAdapter simpleListAdapter = new SimpleListAdapter(this.b.getContext());
        simpleListAdapter.b(d);
        this.b.setAdapter(simpleListAdapter);
        this.c.setVisibility(b ? 8 : 0);
        this.c.setHeadTitleText(nru.b(hashMap, "TITLE", ""));
        String b2 = nru.b(hashMap, "SHOWMORE", null);
        final OnClickSectionListener a2 = nru.a(hashMap, "SHOW_MORE_CLICK_EVENT", null);
        if (b2 != null && a2 != null) {
            this.c.setMoreText(b2);
            this.c.setMoreTextVisibility(0);
            this.c.setMoreLayoutVisibility(0);
            this.c.setMoreViewClickListener(new View.OnClickListener() { // from class: com.huawei.health.section.section.SectionSimpleList.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    a2.onClick("SHOW_MORE_CLICK_EVENT");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            return;
        }
        this.c.setMoreText("");
        this.c.setMoreTextVisibility(4);
        this.c.setMoreLayoutVisibility(4);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionSimpleList";
    }
}
