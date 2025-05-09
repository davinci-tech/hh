package com.huawei.health.knit.section.view;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.SectionPopularRoutesAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ccq;
import defpackage.ebw;
import defpackage.eet;
import defpackage.nru;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class SectionPopularRoutes extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private SectionPopularRoutesAdapter f2717a;
    private Context b;
    private HealthSubHeader c;
    private HealthRecycleView d;
    private LinearLayout e;

    public SectionPopularRoutes(Context context) {
        this(context, null);
    }

    public SectionPopularRoutes(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionPopularRoutes(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_popular_routes_layout, (ViewGroup) this, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.root_view_layout);
        this.e = linearLayout;
        linearLayout.post(new Runnable() { // from class: com.huawei.health.knit.section.view.SectionPopularRoutes.2
            @Override // java.lang.Runnable
            public void run() {
                SectionPopularRoutes.this.e.setMinimumWidth(nsn.n());
            }
        });
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.section_sub_header);
        this.c = healthSubHeader;
        healthSubHeader.setSubHeaderBackgroundColor(context.getResources().getColor(R.color._2131296971_res_0x7f0902cb));
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.section_popular_routes_recycler_view);
        this.d = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(context, 0, false));
        return inflate;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        eet.e(this.b, this.d, this.f2717a);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionPopularRoutes", "bindViewParams");
        setSubHeaderTitle(nru.d(hashMap, "TITLE", (Object) null));
        setSubTitle(nru.d(hashMap, "SUBTITLE", (Object) null));
        setSubViewStatus(nru.d(hashMap, "SUBTITLE", (Object) null));
        setRightArrayVisibility(nru.d(hashMap, "RIGHT_ARRAY", (Object) null));
        ebw ebwVar = new ebw();
        c(nru.d(hashMap, "CLICK_EVENT_LISTENER", (Object) null), ebwVar);
        ebwVar.c(nru.d(hashMap, "IMAGE", String.class, new ArrayList()));
        ebwVar.e(nru.d(hashMap, "NAME", String.class, new ArrayList()));
        ebwVar.b(nru.d(hashMap, "TOTAL_DISTANCE", Double.class, new ArrayList()));
        ebwVar.d(nru.d(hashMap, "PARTICIPATE_USER_NUM", Long.class, new ArrayList()));
        setAdapterData(ebwVar);
    }

    private void setAdapterData(ebw ebwVar) {
        SectionPopularRoutesAdapter sectionPopularRoutesAdapter = this.f2717a;
        if (sectionPopularRoutesAdapter == null) {
            SectionPopularRoutesAdapter sectionPopularRoutesAdapter2 = new SectionPopularRoutesAdapter(this.b, ebwVar);
            this.f2717a = sectionPopularRoutesAdapter2;
            this.d.setAdapter(sectionPopularRoutesAdapter2);
            return;
        }
        sectionPopularRoutesAdapter.a(ebwVar);
    }

    private void c(final Object obj, ebw ebwVar) {
        if (!eet.a(obj) || this.c == null) {
            return;
        }
        LogUtil.a("SectionPopularRoutes", "click event is set");
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionPopularRoutes.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ((OnClickSectionListener) obj).onClick("ALL_CLICK_EVENT");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        ebwVar.a((OnClickSectionListener) obj);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.section_sub_header) {
            onClick("ALL_CLICK_EVENT");
        } else {
            LogUtil.b("view id is invalid", new Object[0]);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void setSubTitle(Object obj) {
        if (this.c != null) {
            if (eet.f(obj)) {
                LogUtil.a("SectionPopularRoutes", "subtitle is set");
                this.c.setMoreTextVisibility(0);
                this.c.setMoreText((String) obj);
            } else {
                LogUtil.a("SectionPopularRoutes", "subtitle is invisible");
                this.c.setMoreTextVisibility(4);
            }
        }
    }

    private void setSubViewStatus(Object obj) {
        HealthSubHeader healthSubHeader;
        if (obj == null || (healthSubHeader = this.c) == null) {
            return;
        }
        ccq.c(obj, healthSubHeader, "SectionPopularRoutes", 8, "gone");
    }

    private void setRightArrayVisibility(Object obj) {
        HealthSubHeader healthSubHeader;
        if (obj == null || (healthSubHeader = this.c) == null) {
            return;
        }
        healthSubHeader.setRightArrayVisibility(0);
    }

    private void setSubHeaderTitle(Object obj) {
        if (!eet.f(obj) || this.c == null) {
            return;
        }
        LogUtil.a("SectionPopularRoutes", "title is set");
        this.c.setHeadTitleText((String) obj);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionPopularRoutes";
    }
}
