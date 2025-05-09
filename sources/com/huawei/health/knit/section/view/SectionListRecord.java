package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.common.NoAnimationsLinearLayoutManager;
import com.huawei.health.knit.section.adapter.SectionListAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ebq;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionListRecord extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f2698a;
    private List<String> b;
    private List<Drawable> c;
    private List<String> d;
    private OnClickSectionListener e;
    private RelativeLayout f;
    private HealthRecycleView g;
    private View h;
    private Context i;
    private List<String> j;
    private List<String> k;
    private HealthSubHeader l;
    private HealthButton m;
    private List<Integer> n;
    private SectionListAdapter o;

    public SectionListRecord(Context context) {
        super(context);
    }

    public SectionListRecord(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SectionListRecord(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionListRecord", "onCreateView");
        this.i = context;
        d();
        return this.h;
    }

    private void d() {
        View inflate = LayoutInflater.from(this.i).inflate(R.layout.section_list_record_layout, (ViewGroup) this, false);
        this.h = inflate;
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.section_blood_pressure_rl);
        this.f = relativeLayout;
        relativeLayout.setMinimumWidth(nsn.h(this.i));
        this.l = (HealthSubHeader) this.h.findViewById(R.id.section_blood_pressure_sub_header);
        this.c = (List) this.h.findViewById(R.id.left_image);
        this.g = (HealthRecycleView) this.h.findViewById(R.id.abnormal_blood_pressure_recyclerview);
        this.m = (HealthButton) this.h.findViewById(R.id.show_more_button);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionListRecord", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.b("SectionListRecord", "no need to bind");
            return;
        }
        nsy.d(this.l, 0, nru.b(hashMap, "TITLE", ""), 8, 8);
        this.l.setGuideLine();
        this.c = nru.d(hashMap, "LEFT_IMAGE", Drawable.class, new ArrayList());
        this.b = nru.d(hashMap, "LEFT_TOP_VALUE", String.class, new ArrayList());
        this.j = nru.d(hashMap, "LEFT_TOP_VALUE_UNIT", String.class, new ArrayList());
        this.d = nru.d(hashMap, "LEFT_TOP_STATE", String.class, new ArrayList());
        this.f2698a = nru.d(hashMap, "LEFT_TOP_STATE_COLOR", Integer.class, new ArrayList());
        this.k = nru.d(hashMap, "RIGHT_TIME", String.class, new ArrayList());
        this.n = nru.d(hashMap, "RIGHT_ICON", Integer.class, new ArrayList());
        nsy.cMr_(this.m, nru.b(hashMap, "SHOWMORE", ""));
        OnClickSectionListener onClickSectionListener = (OnClickSectionListener) nru.c(hashMap, "CLICK_EVENT_LISTENER", OnClickSectionListener.class, null);
        this.e = onClickSectionListener;
        HealthButton healthButton = this.m;
        if (healthButton != null && onClickSectionListener != null) {
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionListRecord.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SectionListRecord.this.e.onClick("SHOWMORE");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        if (this.o == null) {
            this.o = new SectionListAdapter();
            this.g.setLayoutManager(new NoAnimationsLinearLayoutManager(this.i));
            this.g.setNestedScrollingEnabled(false);
            this.g.a(false);
            this.g.d(false);
            this.g.setAdapter(this.o);
        }
        this.o.d(new ebq(this.c, this.b, this.j, this.d, this.f2698a, this.k, this.n, this.e));
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionListRecord";
    }
}
