package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.android.airsharing.api.PlayInfo;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section16_9Series_01Adapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ccq;
import defpackage.eat;
import defpackage.eet;
import defpackage.nru;
import defpackage.nsn;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class Section16_9Series_01 extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthRecycleView f2639a;
    private LinearLayout b;
    private HealthSubHeader c;
    private Context d;
    private Section16_9Series_01Adapter e;

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected boolean isSupportSafeRegion() {
        return false;
    }

    public Section16_9Series_01(Context context) {
        this(context, null);
    }

    public Section16_9Series_01(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Section16_9Series_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("Section_Section16_9Series_01", "bindViewParams");
        HealthSubHeader healthSubHeader = this.c;
        if (healthSubHeader != null) {
            healthSubHeader.setRightArrayVisibility(8);
            this.c.setMoreTextVisibility(4);
        }
        eat eatVar = new eat();
        setSubHeaderText(nru.d(hashMap, "TITLE", (Object) null));
        setSubViewStatus(nru.d(hashMap, "SUBTITLE", (Object) null));
        setRightArrayVisibility(nru.d(hashMap, "RIGHT_ARRAY", (Object) null));
        d(nru.d(hashMap, "CLICK_EVENT_LISTENER", (Object) null), eatVar);
        j(nru.d(hashMap, "NAME", (Object) null), eatVar);
        c(nru.d(hashMap, "BACKGROUND_IMAGE", (Object) null), eatVar);
        f(nru.d(hashMap, "NEW_IMAGE", (Object) null), eatVar);
        b(nru.d(hashMap, "CORNER_VIEW", (Object) null), eatVar);
        a(nru.d(hashMap, "DIFFICULTY", (Object) null), eatVar);
        g(nru.d(hashMap, PlayInfo.KEY_DURATION, (Object) null), eatVar);
        e(nru.d(hashMap, "CALORIES", (Object) null), eatVar);
        i(nru.d(hashMap, "TRAIN_NUMBER", (Object) null), eatVar);
        setAdapterData(eatVar);
    }

    private void d(final Object obj, eat eatVar) {
        if (!eet.a(obj) || this.c == null) {
            return;
        }
        LogUtil.a("Section_Section16_9Series_01", "click event is set");
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.Section16_9Series_01.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ((OnClickSectionListener) obj).onClick("ALL_CLICK_EVENT");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        eatVar.a((OnClickSectionListener) obj);
    }

    private void j(Object obj, eat eatVar) {
        if (eet.h(obj)) {
            LogUtil.a("Section_Section16_9Series_01", "name list is set");
            eatVar.g((List) obj);
        }
    }

    private void c(Object obj, eat eatVar) {
        if (eet.e(obj)) {
            LogUtil.a("Section_Section16_9Series_01", "background image list is set");
            eatVar.e((List) obj);
        }
    }

    private void f(Object obj, eat eatVar) {
        if (eet.e(obj)) {
            LogUtil.a("Section_Section16_9Series_01", "new image list is set");
            eatVar.f((List) obj);
        }
    }

    private void b(Object obj, eat eatVar) {
        if (eet.e(obj)) {
            LogUtil.a("Section_Section16_9Series_01", "corner list is set");
            eatVar.c((List) obj);
        }
    }

    private void setSubHeaderText(Object obj) {
        if (!eet.f(obj) || this.c == null) {
            return;
        }
        LogUtil.a("Section_Section16_9Series_01", "title is set");
        this.c.setHeadTitleText((String) obj);
    }

    private void setRightArrayVisibility(Object obj) {
        HealthSubHeader healthSubHeader;
        if (obj == null || (healthSubHeader = this.c) == null) {
            return;
        }
        healthSubHeader.setRightArrayVisibility(0);
    }

    private void setSubViewStatus(Object obj) {
        HealthSubHeader healthSubHeader;
        if (obj == null || (healthSubHeader = this.c) == null) {
            return;
        }
        ccq.c(obj, healthSubHeader, "Section_Section16_9Series_01", 4, "invisible");
    }

    private void a(Object obj, eat eatVar) {
        if (eet.h(obj)) {
            LogUtil.a("Section_Section16_9Series_01", "difficulty list is set");
            eatVar.b((List) obj);
        }
    }

    private void g(Object obj, eat eatVar) {
        if (eet.h(obj)) {
            LogUtil.a("Section_Section16_9Series_01", "duration list is set");
            eatVar.d((List) obj);
        }
    }

    private void e(Object obj, eat eatVar) {
        if (eet.h(obj)) {
            LogUtil.a("Section_Section16_9Series_01", "calories list is set");
            eatVar.a((List<String>) obj);
        }
    }

    private void i(Object obj, eat eatVar) {
        if (eet.h(obj)) {
            LogUtil.a("Section_Section16_9Series_01", "train number list is set");
            eatVar.j((List) obj);
        }
    }

    private void setAdapterData(eat eatVar) {
        Section16_9Series_01Adapter section16_9Series_01Adapter = this.e;
        if (section16_9Series_01Adapter == null) {
            Section16_9Series_01Adapter section16_9Series_01Adapter2 = new Section16_9Series_01Adapter(this.d, eatVar);
            this.e = section16_9Series_01Adapter2;
            this.f2639a.setAdapter(section16_9Series_01Adapter2);
            return;
        }
        section16_9Series_01Adapter.c(eatVar);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("Section_Section16_9Series_01", "loadView");
        View inflate = LayoutInflater.from(context).inflate(R.layout.section16_9series_01_layout, (ViewGroup) this, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.root_view_layout);
        this.b = linearLayout;
        linearLayout.post(new Runnable() { // from class: com.huawei.health.knit.section.view.Section16_9Series_01.2
            @Override // java.lang.Runnable
            public void run() {
                Section16_9Series_01.this.b.setMinimumWidth(nsn.n());
            }
        });
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.section_sub_header);
        this.c = healthSubHeader;
        healthSubHeader.setMoreTextColor(context.getResources().getColor(R.color._2131296927_res_0x7f09029f));
        this.c.setSubHeaderBackgroundColor(context.getResources().getColor(R.color._2131296971_res_0x7f0902cb));
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.section16_9series_01_recycler_view);
        this.f2639a = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(context, 0, false));
        return inflate;
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
    public String getLogTag() {
        return "Section_Section16_9Series_01";
    }
}
