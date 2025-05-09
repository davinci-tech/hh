package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section16_9Series_02Adapter;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.eat;
import defpackage.eet;
import defpackage.nru;
import defpackage.nsn;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class Section16_9Series_02 extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f2641a;
    private Context b;
    private Section16_9Series_02Adapter c;
    private HealthSubHeader d;
    private HealthRecycleView e;

    public Section16_9Series_02(Context context) {
        super(context);
    }

    public Section16_9Series_02(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Section16_9Series_02(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("Section16_9Card_02", "loadView");
        this.b = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.section16_9series_01_layout, (ViewGroup) this, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.root_view_layout);
        this.f2641a = linearLayout;
        linearLayout.post(new Runnable() { // from class: com.huawei.health.knit.section.view.Section16_9Series_02.4
            @Override // java.lang.Runnable
            public void run() {
                Section16_9Series_02.this.f2641a.setMinimumWidth(nsn.n());
            }
        });
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.section_sub_header);
        this.d = healthSubHeader;
        setHeaderLayoutParam(healthSubHeader);
        this.d.setHeadTitleText(this.b.getResources().getString(R$string.IDS_yoga_breath));
        this.d.setSubHeaderBackgroundColor(context.getResources().getColor(R.color._2131296971_res_0x7f0902cb));
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.section16_9series_01_recycler_view);
        this.e = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(context, 0, false));
        return inflate;
    }

    private void setHeaderLayoutParam(HealthSubHeader healthSubHeader) {
        ViewGroup.LayoutParams layoutParams = healthSubHeader.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.setMarginEnd(((Integer) BaseActivity.getSafeRegionWidth().second).intValue());
            healthSubHeader.setLayoutParams(layoutParams2);
        }
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

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            LogUtil.a("Section16_9Card_02", "currentParams is null or empty");
            return;
        }
        LogUtil.a("Section16_9Card_02", "bindViewParams");
        HealthSubHeader healthSubHeader = this.d;
        if (healthSubHeader != null) {
            healthSubHeader.setRightArrayVisibility(0);
            this.d.setMoreTextVisibility(4);
        }
        eat eatVar = new eat();
        a(nru.d(hashMap, "CLICK_EVENT_LISTENER", (Object) null), eatVar);
        b(nru.d(hashMap, "NAME", (Object) null), eatVar);
        d(nru.d(hashMap, "BACKGROUND_IMAGE", (Object) null), eatVar);
        c(nru.d(hashMap, "CORNER_VIEW", (Object) null), eatVar);
        e(nru.d(hashMap, "TRAIN_NUMBER", (Object) null), eatVar);
        setAdapterData(eatVar);
    }

    private void setAdapterData(eat eatVar) {
        Section16_9Series_02Adapter section16_9Series_02Adapter = this.c;
        if (section16_9Series_02Adapter == null) {
            Section16_9Series_02Adapter section16_9Series_02Adapter2 = new Section16_9Series_02Adapter(this.b, eatVar);
            this.c = section16_9Series_02Adapter2;
            this.e.setAdapter(section16_9Series_02Adapter2);
            return;
        }
        section16_9Series_02Adapter.c(eatVar);
    }

    private void e(Object obj, eat eatVar) {
        if (eet.h(obj)) {
            LogUtil.a("Section16_9Card_02", "train number list is set");
            eatVar.j((List) obj);
        }
    }

    private void c(Object obj, eat eatVar) {
        if (eet.e(obj)) {
            LogUtil.a("Section16_9Card_02", "corner list is set");
            eatVar.c((List) obj);
        }
    }

    private void b(Object obj, eat eatVar) {
        if (eet.h(obj)) {
            LogUtil.a("Section16_9Card_02", "name list is set");
            eatVar.g((List) obj);
        }
    }

    private void d(Object obj, eat eatVar) {
        if (eet.e(obj)) {
            LogUtil.a("Section16_9Card_02", "background image list is set");
            eatVar.e((List) obj);
        }
    }

    private void a(final Object obj, eat eatVar) {
        if (!eet.a(obj) || this.d == null) {
            return;
        }
        LogUtil.a("Section16_9Card_02", "click event is set");
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.Section16_9Series_02.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ((OnClickSectionListener) obj).onClick("ALL_CLICK_EVENT");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        eatVar.a((OnClickSectionListener) obj);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section16_9Card_02";
    }
}
