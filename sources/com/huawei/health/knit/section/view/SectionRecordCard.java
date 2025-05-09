package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.SectionListRecordAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.eby;
import defpackage.eds;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionRecordCard extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private OnClickSectionListener f2721a;
    private String b;
    private Integer c;
    private String d;
    private String e;
    private String f;
    private Context g;
    private List<eds> h;
    private eby i;
    private View j;
    private RelativeLayout k;
    private String l;
    private String m;
    private String n;
    private HealthRecycleView o;
    private SectionListRecordAdapter p;
    private String q;
    private Integer r;
    private HealthSubHeader s;
    private HealthButton t;
    private String u;

    public SectionRecordCard(Context context) {
        super(context);
    }

    public SectionRecordCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SectionRecordCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionRecordCard", "onCreateView");
        this.g = context;
        c();
        return this.j;
    }

    private void c() {
        View inflate = LayoutInflater.from(this.g).inflate(R.layout.section_record_card_layout, (ViewGroup) this, false);
        this.j = inflate;
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.section_layout_root);
        this.k = relativeLayout;
        relativeLayout.setMinimumWidth(nsn.h(this.g));
        this.s = (HealthSubHeader) this.j.findViewById(R.id.section_sub_header);
        this.o = (HealthRecycleView) this.j.findViewById(R.id.abnormal_section_recyclerview);
        this.t = (HealthButton) this.j.findViewById(R.id.show_more_button);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionRecordCard", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.b("SectionRecordCard", "no need to bind");
            return;
        }
        nsy.d(this.s, 0, nru.b(hashMap, "TITLE", ""), 8, 8);
        nsy.cMr_(this.t, nru.b(hashMap, "SHOWMORE", ""));
        this.h = nru.d(hashMap, "VIEW_LIST", eds.class, new ArrayList());
        OnClickSectionListener onClickSectionListener = (OnClickSectionListener) nru.c(hashMap, "CLICK_EVENT_LISTENER", OnClickSectionListener.class, null);
        this.f2721a = onClickSectionListener;
        HealthButton healthButton = this.t;
        if (healthButton != null && onClickSectionListener != null) {
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionRecordCard.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SectionRecordCard.this.f2721a.onClick("SHOWMORE");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        c(hashMap);
        e(hashMap);
        d();
        LogUtil.a("SectionRecordCard", "setDefaultViewData");
        if (this.p == null) {
            this.p = new SectionListRecordAdapter(this.i);
            this.o.setLayoutManager(new LinearLayoutManager(this.g));
            this.o.setNestedScrollingEnabled(false);
            this.o.a(false);
            this.o.d(false);
            this.o.setAdapter(this.p);
            LogUtil.a("SectionRecordCard", "recyclerView.setAdapter");
        }
        this.p.e(this.h);
    }

    private void c(HashMap<String, Object> hashMap) {
        this.u = (String) nru.c(hashMap, "TITLE_TEXT", String.class, "");
        this.r = (Integer) nru.c(hashMap, "RIGHT_ICON", Integer.class, null);
        this.f = (String) nru.c(hashMap, "LEFT_TEXT", String.class, "");
        this.m = (String) nru.c(hashMap, "MIDDLE_UNIT", String.class, "");
        this.l = (String) nru.c(hashMap, "MIDDLE_TEXT", String.class, "");
        this.q = (String) nru.c(hashMap, "RIGHT_UNIT", String.class, "");
        this.n = (String) nru.c(hashMap, "RIGHT_TEXT", String.class, "");
    }

    private void d() {
        this.i = new eby(this.u, this.r, this.f, this.l, this.n, this.b, this.m, this.c, this.d, this.q, this.e, this.f2721a);
    }

    private void e(HashMap<String, Object> hashMap) {
        this.b = (String) nru.c(hashMap, "EXTRA_LEFT_TEXT", String.class, "");
        this.c = (Integer) nru.c(hashMap, "EXTRA_RIGHT_ICON", Integer.class, null);
        this.d = (String) nru.c(hashMap, "EXTRA_BOTTOM_TEXT", String.class, "");
        this.e = (String) nru.c(hashMap, "EXTRA_BOTTOM_UNIT", String.class, "");
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionRecordCard";
    }
}
