package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section21_9Target_02_Adapter_01;
import com.huawei.health.knit.section.adapter.Section21_9Target_02_Adapter_02;
import com.huawei.health.knit.section.view.Section21_9Target_02;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ebh;
import defpackage.ebi;
import defpackage.eet;
import defpackage.nru;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class Section21_9Target_02 extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2652a;
    private HashMap<String, Object> b;
    private Section21_9Target_02_Adapter_02 c;
    private LinearLayout d;
    private Context e;
    private HealthRecycleView g;
    private HealthSubHeader i;
    private Section21_9Target_02_Adapter_01 j;

    public Section21_9Target_02(Context context) {
        this(context, null);
    }

    public Section21_9Target_02(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Section21_9Target_02(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2652a = false;
        this.b = new HashMap<>();
        this.e = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(final Context context) {
        LogUtil.a("Section_Section21_9Target_02", "onCreateView");
        View inflate = LayoutInflater.from(context).inflate(R.layout.section21_9target_02_layout, (ViewGroup) this, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.section_root_view);
        this.d = linearLayout;
        linearLayout.post(new Runnable() { // from class: efm
            @Override // java.lang.Runnable
            public final void run() {
                Section21_9Target_02.this.d(context);
            }
        });
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.section_sub_header);
        this.i = healthSubHeader;
        healthSubHeader.setMoreTextVisibility(0);
        this.i.setSubHeaderBackgroundColor(context.getResources().getColor(R.color._2131296971_res_0x7f0902cb));
        if (LanguageUtil.ba(BaseApplication.getContext()) || LanguageUtil.bj(BaseApplication.getContext())) {
            this.i.setSubHeaderTitleScaleTextSize(0.75f);
        }
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.section21_9target_02_data_recycler_view);
        this.g = healthRecycleView;
        eet.c(context, healthRecycleView, new HealthLinearLayoutManager(context), false, 0);
        this.g.setHasFixedSize(true);
        this.g.setNestedScrollingEnabled(false);
        this.g.a(false);
        this.g.d(false);
        return inflate;
    }

    public /* synthetic */ void d(Context context) {
        this.d.setMinimumWidth(nsn.h(context));
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("Section_Section21_9Target_02", "bindParamsToView");
        this.f2652a = !b(hashMap, this.b);
        this.b.putAll(hashMap);
        if (eet.b(hashMap)) {
            d(hashMap);
        } else {
            b(hashMap);
        }
    }

    private boolean b(HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            if (!entry.getKey().equals("CLICK_EVENT_LISTENER") && !entry.getKey().equals("BUTTON_CLICK_EVENT") && !entry.getValue().equals(hashMap2.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    private void d(HashMap<String, Object> hashMap) {
        ebh ebhVar = new ebh();
        setSubHeader(hashMap);
        if (hashMap.containsKey("CLICK_EVENT_LISTENER") && this.i != null) {
            final Object obj = hashMap.get("CLICK_EVENT_LISTENER");
            if (eet.a(obj)) {
                this.i.setOnClickListener(new View.OnClickListener() { // from class: efn
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        Section21_9Target_02.aig_(obj, view);
                    }
                });
                ebhVar.b((OnClickSectionListener) obj);
            }
        }
        if (eet.e(hashMap.get("BACKGROUND_IMAGE"))) {
            ebhVar.c((List) hashMap.get("BACKGROUND_IMAGE"));
        }
        if (eet.h(hashMap.get("NAME"))) {
            ebhVar.e((List) hashMap.get("NAME"));
        }
        if (eet.h(hashMap.get("DESCRIPTION"))) {
            ebhVar.a((List) hashMap.get("DESCRIPTION"));
        }
        if (eet.e(hashMap.get("BUTTON_TEXT"))) {
            ebhVar.d((List) hashMap.get("BUTTON_TEXT"));
        }
        ebhVar.b(nru.d(hashMap, "BUTTON_TEXT_COLOR", Integer.class, null));
        if (this.c == null || this.f2652a) {
            Section21_9Target_02_Adapter_02 section21_9Target_02_Adapter_02 = new Section21_9Target_02_Adapter_02(this.e, ebhVar);
            this.c = section21_9Target_02_Adapter_02;
            this.g.setAdapter(section21_9Target_02_Adapter_02);
        }
    }

    public static /* synthetic */ void aig_(Object obj, View view) {
        ((OnClickSectionListener) obj).onClick("MORE_CLICK_EVENT");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(HashMap<String, Object> hashMap) {
        HealthSubHeader healthSubHeader;
        ebi ebiVar = new ebi();
        setSubHeader(hashMap);
        if (hashMap.containsKey("CLICK_EVENT_LISTENER") && this.i != null) {
            final Object obj = hashMap.get("CLICK_EVENT_LISTENER");
            if (eet.a(obj) && (healthSubHeader = this.i) != null) {
                healthSubHeader.setOnClickListener(new View.OnClickListener() { // from class: efq
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        Section21_9Target_02.aih_(obj, view);
                    }
                });
                ebiVar.e((OnClickSectionListener) obj);
            }
        }
        if (eet.h(hashMap.get("NAME"))) {
            ebiVar.d((List) hashMap.get("NAME"));
        }
        if (eet.e(hashMap.get("BACKGROUND_IMAGE"))) {
            ebiVar.e((List<String>) hashMap.get("BACKGROUND_IMAGE"));
        }
        if (eet.e(hashMap.get("COMPLETE_SITUATION"))) {
            ebiVar.b((List) hashMap.get("COMPLETE_SITUATION"));
        }
        if (eet.h(hashMap.get("TODAY_TASK"))) {
            ebiVar.j((List) hashMap.get("TODAY_TASK"));
        }
        if (eet.h(hashMap.get("FINISH_RATE"))) {
            ebiVar.a((List) hashMap.get("FINISH_RATE"));
        }
        if (eet.e(hashMap.get("PROGRESS_VALUE"))) {
            ebiVar.c((List) hashMap.get("PROGRESS_VALUE"));
        }
        if (eet.e(hashMap.get("BUTTON_TEXT"))) {
            ebiVar.i((List) hashMap.get("BUTTON_TEXT"));
        }
        ebiVar.f(nru.d(hashMap, "BUTTON_TEXT_COLOR", Integer.class, null));
        if (this.j == null || this.f2652a) {
            Section21_9Target_02_Adapter_01 section21_9Target_02_Adapter_01 = new Section21_9Target_02_Adapter_01(this.e, ebiVar);
            this.j = section21_9Target_02_Adapter_01;
            this.g.setAdapter(section21_9Target_02_Adapter_01);
        }
    }

    public static /* synthetic */ void aih_(Object obj, View view) {
        ((OnClickSectionListener) obj).onClick("MORE_CLICK_EVENT");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void setSubHeader(HashMap<String, Object> hashMap) {
        if (hashMap.containsKey("TITLE") && this.i != null) {
            Object obj = hashMap.get("TITLE");
            if (eet.f(obj)) {
                this.i.setHeadTitleText((String) obj);
            }
        }
        if (!hashMap.containsKey("SUBTITLE") || this.i == null) {
            return;
        }
        Object obj2 = hashMap.get("SUBTITLE");
        if (eet.f(obj2)) {
            this.i.setMoreTextVisibility(0);
            this.i.setMoreText((String) obj2);
        } else {
            this.i.setMoreTextVisibility(4);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_Section21_9Target_02";
    }
}
