package com.huawei.health.knit.section.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section21_9Target_01_Adapter_01;
import com.huawei.health.knit.section.adapter.Section21_9Target_01_Adapter_02;
import com.huawei.health.knit.section.view.Section21_9Target_01;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.eba;
import defpackage.ebf;
import defpackage.eet;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class Section21_9Target_01 extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HashMap<String, Object> f2651a;
    private Context b;
    private LinearLayout c;
    private boolean d;
    private Section21_9Target_01_Adapter_02 e;
    private HealthRecycleView h;
    private Section21_9Target_01_Adapter_01 i;
    private HealthSubHeader j;

    public Section21_9Target_01(Context context) {
        this(context, null);
    }

    public Section21_9Target_01(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Section21_9Target_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = false;
        this.f2651a = new HashMap<>();
        this.b = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(final Context context) {
        LogUtil.a("Section_Section21_9Target_01", "onCreateView");
        View inflate = LayoutInflater.from(context).inflate(R.layout.section21_9target_01_layout, (ViewGroup) this, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.section_root_view);
        this.c = linearLayout;
        linearLayout.post(new Runnable() { // from class: efo
            @Override // java.lang.Runnable
            public final void run() {
                Section21_9Target_01.this.d(context);
            }
        });
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.section_sub_header);
        this.j = healthSubHeader;
        healthSubHeader.setMoreTextVisibility(4);
        this.j.setSubHeaderBackgroundColor(context.getResources().getColor(R.color._2131296971_res_0x7f0902cb));
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.section21_9target_01_data_recycler_view);
        this.h = healthRecycleView;
        eet.c(context, healthRecycleView, new HealthLinearLayoutManager(context), false, 0);
        this.h.setHasFixedSize(true);
        this.h.setNestedScrollingEnabled(false);
        this.h.a(false);
        this.h.d(false);
        return inflate;
    }

    public /* synthetic */ void d(Context context) {
        this.c.setMinimumWidth(nsn.h(context));
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("Section_Section21_9Target_01", "bindParamsToView");
        this.d = !a(hashMap, this.f2651a);
        this.f2651a.putAll(hashMap);
        hashMap.entrySet();
        if (eet.b(hashMap)) {
            b(hashMap);
        } else {
            a(hashMap);
        }
    }

    private boolean a(HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            if (!entry.getKey().equals("CLICK_EVENT_LISTENER") && !entry.getKey().equals("BUTTON_CLICK_EVENT") && !entry.getValue().equals(hashMap2.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    private void b(HashMap<String, Object> hashMap) {
        HealthSubHeader healthSubHeader;
        ebf ebfVar = new ebf();
        nsy.e(this.j, nru.b(hashMap, "TITLE", ""));
        setSubTittle(nru.b(hashMap, "SUBTITLE", ""));
        final Object d = nru.d(hashMap, "CLICK_EVENT_LISTENER", (Object) null);
        if (eet.a(d) && (healthSubHeader = this.j) != null) {
            healthSubHeader.setOnClickListener(new View.OnClickListener() { // from class: efh
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Section21_9Target_01.aie_(d, view);
                }
            });
            ebfVar.c((OnClickSectionListener) d);
        }
        a(hashMap, ebfVar);
        if (this.e == null || this.d) {
            Section21_9Target_01_Adapter_02 section21_9Target_01_Adapter_02 = new Section21_9Target_01_Adapter_02(this.b, ebfVar);
            this.e = section21_9Target_01_Adapter_02;
            this.h.setAdapter(section21_9Target_01_Adapter_02);
        }
    }

    public static /* synthetic */ void aie_(Object obj, View view) {
        ((OnClickSectionListener) obj).onClick("MORE_CLICK_EVENT");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(HashMap<String, Object> hashMap, ebf ebfVar) {
        Object d = nru.d(hashMap, "BACKGROUND_IMAGE", (Object) null);
        if (eet.e(d)) {
            ebfVar.d((List) d);
        }
        CharSequence b = nru.b(hashMap, "NAME", null);
        if (eet.h(b)) {
            ebfVar.c((List<String>) b);
        }
        CharSequence b2 = nru.b(hashMap, "DESCRIPTION", null);
        if (eet.h(b2)) {
            ebfVar.a((List) b2);
        }
    }

    private void setSubTittle(String str) {
        if (this.j != null) {
            if (TextUtils.isEmpty(str)) {
                this.j.setMoreTextVisibility(4);
            } else {
                this.j.setMoreTextVisibility(0);
                this.j.setMoreText(str);
            }
        }
    }

    private void a(HashMap<String, Object> hashMap) {
        eba ebaVar = new eba();
        HealthSubHeader healthSubHeader = this.j;
        if (healthSubHeader != null) {
            healthSubHeader.setHeadTitleText(nru.b(hashMap, "TITLE", ""));
        }
        String b = nru.b(hashMap, "SUBTITLE", "");
        if (this.j != null) {
            if (eet.f(b)) {
                this.j.setMoreTextVisibility(0);
                this.j.setMoreText(b);
            } else {
                this.j.setMoreTextVisibility(8);
            }
        }
        final OnClickSectionListener a2 = nru.a(hashMap, "CLICK_EVENT_LISTENER", null);
        this.j.setOnClickListener(new View.OnClickListener() { // from class: efi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Section21_9Target_01.aif_(OnClickSectionListener.this, view);
            }
        });
        ebaVar.b(a2);
        ebaVar.d(nru.d(hashMap, "NAME", String.class, new ArrayList()));
        ebaVar.e(nru.d(hashMap, "BACKGROUND_IMAGE", Integer.class, new ArrayList()));
        ebaVar.c(nru.d(hashMap, "COMPLETE_SITUATION", String.class, new ArrayList()));
        ebaVar.f(nru.d(hashMap, "TODAY_TASK", String.class, new ArrayList()));
        ebaVar.a(nru.d(hashMap, "FINISH_RATE", String.class, new ArrayList()));
        ebaVar.b(nru.d(hashMap, "PROGRESS_VALUE", Integer.class, new ArrayList()));
        ebaVar.h(nru.d(hashMap, "BUTTON_TEXT", String.class, new ArrayList()));
        if (this.i == null || this.d) {
            Section21_9Target_01_Adapter_01 section21_9Target_01_Adapter_01 = new Section21_9Target_01_Adapter_01(this.b, ebaVar);
            this.i = section21_9Target_01_Adapter_01;
            this.h.setAdapter(section21_9Target_01_Adapter_01);
        }
    }

    public static /* synthetic */ void aif_(OnClickSectionListener onClickSectionListener, View view) {
        onClickSectionListener.onClick("MORE_CLICK_EVENT");
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_Section21_9Target_01";
    }
}
