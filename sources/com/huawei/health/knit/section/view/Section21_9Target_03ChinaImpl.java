package com.huawei.health.knit.section.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section21_9Target_03_Adapter_01;
import com.huawei.health.knit.section.adapter.Section21_9Target_03_Adapter_02;
import com.huawei.health.knit.section.view.Section21_9Target_03ChinaImpl;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ebm;
import defpackage.ebn;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class Section21_9Target_03ChinaImpl extends Section21_9Target_03Impl {

    /* renamed from: a, reason: collision with root package name */
    private HashMap<String, Object> f2653a;
    private boolean b;
    private Section21_9Target_03_Adapter_02 c;
    private LinearLayout d;
    private OnClickSectionListener e;
    private HealthRecycleView g;
    private HealthSubHeader h;
    private Section21_9Target_03_Adapter_01 i;

    public Section21_9Target_03ChinaImpl(Context context) {
        super(context);
        this.b = false;
        this.f2653a = new HashMap<>();
    }

    @Override // com.huawei.health.knit.section.view.Section21_9Target_03Impl
    public View onCreateView(final Context context, ViewGroup viewGroup) {
        LogUtil.a("Section_Section21_9Target_03ChinaImpl", "onCreateView");
        View inflate = LayoutInflater.from(context).inflate(R.layout.section21_9target_03_layout, viewGroup, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.section_root_view);
        this.d = linearLayout;
        linearLayout.post(new Runnable() { // from class: efs
            @Override // java.lang.Runnable
            public final void run() {
                Section21_9Target_03ChinaImpl.this.b(context);
            }
        });
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.section_sub_header);
        this.h = healthSubHeader;
        healthSubHeader.setMoreTextVisibility(4);
        this.h.setSubHeaderBackgroundColor(context.getResources().getColor(R.color._2131296971_res_0x7f0902cb));
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.section21_9target_03_data_recycler_view);
        this.g = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(context, 0, false));
        return inflate;
    }

    public /* synthetic */ void b(Context context) {
        this.d.setMinimumWidth(nsn.h(context));
    }

    @Override // com.huawei.health.knit.section.view.Section21_9Target_03Impl
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("Section_Section21_9Target_03ChinaImpl", "bindParamsToView");
        this.b = !c(hashMap, this.f2653a);
        this.f2653a.putAll(hashMap);
        if (c(hashMap)) {
            b(hashMap);
        } else {
            d(hashMap);
        }
    }

    private boolean c(HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            if (!entry.getKey().equals("CLICK_EVENT_LISTENER") && !entry.getKey().equals("BUTTON_CLICK_EVENT") && !entry.getValue().equals(hashMap2.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    private boolean c(HashMap<String, Object> hashMap) {
        return hashMap.containsKey("IS_LOAD_DEFAULT_VIEW") && (hashMap.get("IS_LOAD_DEFAULT_VIEW") instanceof Boolean) && ((Boolean) hashMap.get("IS_LOAD_DEFAULT_VIEW")).booleanValue();
    }

    private void b(HashMap<String, Object> hashMap) {
        ebn ebnVar = new ebn();
        ebnVar.e(nru.d(hashMap, "BACKGROUND_IMAGE", Object.class, null));
        ebnVar.d(nru.d(hashMap, "NAME", String.class, null));
        ebnVar.a(nru.d(hashMap, "DESCRIPTION", String.class, null));
        ebnVar.b((List) hashMap.get("CORNER_VIEW"));
        nsy.d(this.h, 0, nru.b(hashMap, "TITLE", ""), 4, 0);
        this.e = nru.a(hashMap, "CLICK_EVENT_LISTENER", null);
        this.h.setOnClickListener(new View.OnClickListener() { // from class: efp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Section21_9Target_03ChinaImpl.this.aii_(view);
            }
        });
        ebnVar.a(this.e);
        if (this.c == null || this.b) {
            Section21_9Target_03_Adapter_02 section21_9Target_03_Adapter_02 = new Section21_9Target_03_Adapter_02(this.mContext, ebnVar);
            this.c = section21_9Target_03_Adapter_02;
            this.g.setAdapter(section21_9Target_03_Adapter_02);
        }
    }

    public /* synthetic */ void aii_(View view) {
        this.e.onClick("MORE_CLICK_EVENT");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(HashMap<String, Object> hashMap) {
        ebm ebmVar = new ebm();
        ebmVar.a(nru.d(hashMap, "BACKGROUND_IMAGE", Object.class, null));
        ebmVar.d(nru.d(hashMap, "NAME", String.class, null));
        ebmVar.e(nru.d(hashMap, "COMPLETE_SITUATION", String.class, null));
        ebmVar.f(nru.d(hashMap, "TODAY_TASK", String.class, null));
        ebmVar.b(nru.d(hashMap, "FINISH_RATE", String.class, null));
        ebmVar.c(nru.d(hashMap, "PROGRESS_VALUE", Integer.class, null));
        ebmVar.j(nru.d(hashMap, "BUTTON_TEXT", String.class, null));
        ebmVar.g(nru.d(hashMap, "BUTTON_TEXT_COLOR", Integer.class, null));
        ebmVar.h(nru.d(hashMap, "SUMMARY_TEXT", String.class, null));
        ebmVar.i(nru.d(hashMap, CommonConstant.RETKEY.STATUS, String.class, null));
        nsy.d(this.h, 0, nru.b(hashMap, "TITLE", ""), 4, 0);
        this.e = nru.a(hashMap, "CLICK_EVENT_LISTENER", null);
        this.h.setOnClickListener(new View.OnClickListener() { // from class: efv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Section21_9Target_03ChinaImpl.this.aij_(view);
            }
        });
        ebmVar.d(this.e);
        if (this.i == null || this.b) {
            Section21_9Target_03_Adapter_01 section21_9Target_03_Adapter_01 = new Section21_9Target_03_Adapter_01(this.mContext, ebmVar);
            this.i = section21_9Target_03_Adapter_01;
            this.g.setAdapter(section21_9Target_03_Adapter_01);
        }
    }

    public /* synthetic */ void aij_(View view) {
        this.e.onClick("MORE_CLICK_EVENT");
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.knit.section.view.Section21_9Target_03Impl
    public String getLogTag() {
        return "Section_Section21_9Target_03ChinaImpl";
    }
}
