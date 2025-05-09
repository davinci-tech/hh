package com.huawei.health.section.section;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.section.adapter.Section1_1Search_02Adapter;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.eet;
import defpackage.fbq;
import defpackage.nru;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class Section1_1Search_02 extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private Context f2978a;
    private View b;
    private HealthSubHeader c;
    private HealthRecycleView d;
    private Section1_1Search_02Adapter e;

    public Section1_1Search_02(Context context) {
        this(context, null);
    }

    public Section1_1Search_02(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Section1_1Search_02(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2978a = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section1_1list_01_layout, (ViewGroup) this, false);
        this.b = inflate;
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.section_sub_header);
        this.c = healthSubHeader;
        healthSubHeader.setMoreTextVisibility(4);
        this.c.setMoreLayoutVisibility(4);
        this.c.setSubHeaderBackgroundColor(ContextCompat.getColor(this.b.getContext(), R.color._2131296971_res_0x7f0902cb));
        this.b.findViewById(R.id.section1_1list_01_recycler_layout).setVisibility(0);
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.b.findViewById(R.id.section1_1list_01_recycler_view);
        this.d = healthRecycleView;
        healthRecycleView.setHasFixedSize(true);
        this.d.setNestedScrollingEnabled(false);
        this.d.a(false);
        this.d.d(false);
        eet.c(context, this.d, new HealthLinearLayoutManager(context), false, 0);
        return this.b;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        eet.e(this.f2978a, this.d, this.e);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        fbq fbqVar = new fbq();
        this.c.setHeadTitleText(nru.b(hashMap, "TITLE", ""));
        fbqVar.a(nru.a(hashMap, "CLICK_EVENT_LISTENER", null));
        fbqVar.d(nru.d(hashMap, "ITEMS", SingleGridContent.class, new ArrayList()));
        Section1_1Search_02Adapter section1_1Search_02Adapter = new Section1_1Search_02Adapter(this.f2978a, fbqVar);
        this.e = section1_1Search_02Adapter;
        this.d.setAdapter(section1_1Search_02Adapter);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section1_1Search_02";
    }
}
