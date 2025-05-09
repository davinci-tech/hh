package com.huawei.health.knit.section.view;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section1_1Card_01_Outer_Adapter;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ccq;
import defpackage.eav;
import defpackage.eet;
import defpackage.nru;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class Section1_1Card_01 extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private List<Object> f2643a;
    private boolean b;
    private Context c;
    private HashMap<String, Object> d;
    private List<Object> e;
    private int f;
    private List<Object> g;
    private View h;
    private OnClickSectionListener i;
    private String j;
    private Section1_1Card_01_Outer_Adapter k;
    private HealthSubHeader l;
    private List<Map<Object, Object>> m;
    private List<Object> n;
    private HealthRecycleView o;

    public Section1_1Card_01(Context context) {
        this(context, null);
    }

    public Section1_1Card_01(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Section1_1Card_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = false;
        this.d = new HashMap<>();
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("Section_Section1_1Card_01", "no need to bind");
            return;
        }
        if (this.o == null) {
            LogUtil.a("Section_Section1_1Card_01", "hide section");
            nsy.cMA_(this.h, 8);
            return;
        }
        this.b = !eet.c(hashMap, this.d);
        this.d.putAll(hashMap);
        this.f = nru.d((Map) hashMap, "PAGE_TYPE", 0);
        setTittle(nru.d(hashMap, "TITLE", (Object) null));
        setSubTittleVisiBle(nru.d(hashMap, "SUBTITLE", (Object) null));
        this.n = nru.d(hashMap, "SERIES_COURSE_TITLE", Object.class, new ArrayList());
        this.g = nru.d(hashMap, "SERIES_COURSE_SUBTITLE", Object.class, new ArrayList());
        this.e = nru.d(hashMap, "BACKGROUND", Object.class, new ArrayList());
        this.m = (List) nru.d(hashMap, "RECYCLERVIEW", new ArrayList());
        this.f2643a = nru.d(hashMap, "SHOWMORE", Object.class, new ArrayList());
        setClickListenerEvent(nru.d(hashMap, "CLICK_EVENT_LISTENER", (Object) null));
        d();
        this.o.setOnScrollListener(new MarketingBiUtils.FitnessOnScrollListener(this.f, this.j, 9));
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("Section_Section1_1Card_01", "attachContentView");
        this.c = context;
        b();
        HealthSubHeader healthSubHeader = (HealthSubHeader) this.h.findViewById(R.id.section_sub_header);
        this.l = healthSubHeader;
        healthSubHeader.setMoreTextVisibility(4);
        this.l.setSubHeaderBackgroundColor(context.getResources().getColor(R.color._2131296971_res_0x7f0902cb));
        HealthRecycleView healthRecycleView = this.o;
        if (healthRecycleView != null) {
            ccq.b(healthRecycleView);
        }
        return this.h;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a("Section_Section1_1Card_01", "onConfigurationChanged");
        b();
    }

    private void b() {
        if (this.h == null) {
            View inflate = LayoutInflater.from(this.c).inflate(R.layout.section1_1card_layout, (ViewGroup) this, false);
            this.h = inflate;
            this.o = (HealthRecycleView) inflate.findViewById(R.id.fragment_series_course_recycler_view);
        }
        HealthRecycleView healthRecycleView = this.o;
        if (healthRecycleView == null) {
            LogUtil.h("Section_Section1_1Card_01", "recyclerView is null");
            return;
        }
        if (healthRecycleView.getItemDecorationCount() > 0 && this.o.getItemDecorationAt(0) != null) {
            this.o.removeItemDecorationAt(0);
        }
        this.o.setLayoutManager(new HealthLinearLayoutManager(this.c, 0, false));
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.section_sub_header) {
            onClick("SUBTITLE");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        if (this.k == null) {
            this.k = new Section1_1Card_01_Outer_Adapter(this.c);
        }
        if (this.b) {
            this.k.e(new eav(this.e, this.n, this.g, this.m, this.f2643a, this.i));
            this.o.setAdapter(this.k);
        }
    }

    private void setClickListenerEvent(Object obj) {
        if (this.l == null || !eet.a(obj)) {
            return;
        }
        this.i = (OnClickSectionListener) obj;
        this.l.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.Section1_1Card_01.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Section1_1Card_01.this.i.onClick("SUBTITILE_CLICK_EVENT");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void setSubTittleVisiBle(Object obj) {
        HealthSubHeader healthSubHeader = this.l;
        if (healthSubHeader == null || obj == null) {
            return;
        }
        if (obj instanceof String) {
            healthSubHeader.setMoreTextVisibility(0);
            this.l.setMoreText((String) obj);
        } else {
            healthSubHeader.setMoreTextVisibility(4);
        }
    }

    private void setTittle(Object obj) {
        if (this.l == null || !eet.f(obj)) {
            return;
        }
        String str = (String) obj;
        this.j = str;
        this.l.setHeadTitleText(str);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_Section1_1Card_01";
    }
}
