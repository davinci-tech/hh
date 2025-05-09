package com.huawei.health.knit.section.view;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section3_2List_01_Adapter;
import com.huawei.health.knit.section.utils.SpacesItemDecoration;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.eet;
import defpackage.fbh;
import defpackage.koq;
import defpackage.nrr;
import defpackage.nrt;
import defpackage.nru;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class Section3_2List_01 extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private String f2656a;
    private Map<String, List<Object>> b;
    private Context c;
    private List<Map<String, Object>> d;
    private HealthSubHeader e;
    private boolean f;
    private Observer g;
    private String h;
    private int i;
    private LinearLayout j;
    private Section3_2List_01_Adapter k;
    private HealthRecycleView l;
    private Pair<Integer, Integer> m;
    private OnClickSectionListener o;

    public Section3_2List_01(Context context) {
        super(context);
        this.m = BaseActivity.getSafeRegionWidth();
        this.f = false;
        this.i = Integer.MAX_VALUE;
    }

    public Section3_2List_01(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m = BaseActivity.getSafeRegionWidth();
        this.f = false;
        this.i = Integer.MAX_VALUE;
    }

    public Section3_2List_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m = BaseActivity.getSafeRegionWidth();
        this.f = false;
        this.i = Integer.MAX_VALUE;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        b(hashMap);
        d();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        eet.e(this.c, this.l, this.k);
    }

    private void b(HashMap<String, Object> hashMap) {
        this.b = new HashMap();
        this.e.setHeadTitleText(nru.b(hashMap, "TITLE", ""));
        String b = nru.b(hashMap, "SUBTITLE", "");
        setSubTitleVisible(nru.d((Map) hashMap, "IS_SUBTITLE_VISIBLE", false));
        setScholasticSubTitle(b);
        this.b.put("SCHOLASTIC_TYPE_IMAGE", nru.d(hashMap, "SCHOLASTIC_TYPE_IMAGE", Object.class, new ArrayList()));
        this.b.put("SCHOLASTIC_TYPE_CONTENT", nru.d(hashMap, "SCHOLASTIC_TYPE_CONTENT", Object.class, new ArrayList()));
        OnClickSectionListener a2 = nru.a(hashMap, "CLICK_EVENT_LISTENER", null);
        if (a2 != null) {
            this.e.setMoreViewClickListener(a2);
            this.o = a2;
        }
        int d = nru.d((Map) hashMap, "ITEM_LIMIT", 0);
        if (d > 0) {
            this.i = d;
        }
        this.f2656a = nru.b(hashMap, "HIGHLIGHTED_TEXT", "");
        Object obj = hashMap.get("ITEM_BI_EVENT_MAP");
        if (koq.e(obj, Map.class)) {
            this.d = (List) obj;
        } else {
            this.d = new ArrayList();
        }
        String b2 = nru.b(hashMap, "BI_OBSERVER_LABEL", null);
        if (b2 == null || TextUtils.equals(b2, this.h)) {
            return;
        }
        String str = this.h;
        if (str != null && this.g != null) {
            LogUtil.a("Section_Section3_2List_01", "unregister old observer, old label: ", str);
            ObserverManagerUtil.e(this.g, this.h);
        }
        Observer a3 = fbh.a(this.l);
        this.g = a3;
        this.h = b2;
        ObserverManagerUtil.d(a3, b2);
        LogUtil.a("Section_Section3_2List_01", "register biObserver, label: ", this.h);
    }

    private void d() {
        Section3_2List_01_Adapter section3_2List_01_Adapter;
        if (this.k == null) {
            this.k = new Section3_2List_01_Adapter(this.c);
        }
        this.k.a(this.o);
        this.k.b(this.f2656a);
        this.k.e(this.d);
        this.k.d(this.i);
        Map<String, List<Object>> map = this.b;
        if (map != null && (section3_2List_01_Adapter = this.k) != null) {
            section3_2List_01_Adapter.d(map);
            this.l.setAdapter(this.k);
        } else {
            LogUtil.h("Section_Section3_2List_01", "subDataList is null or scholasticPageSectionAdapter is null");
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.c = context;
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.configuredscholasticpagesection_layout, (ViewGroup) this, false);
        this.e = (HealthSubHeader) inflate.findViewById(R.id.configure_page_header);
        this.j = (LinearLayout) inflate.findViewById(R.id.layout_configured_page);
        if (nrt.a(this.c)) {
            this.j.setBackgroundColor(this.c.getResources().getColor(R$color.emui_color_bg));
        }
        this.e.setSubHeaderBackgroundColor(ContextCompat.getColor(inflate.getContext(), R.color._2131296971_res_0x7f0902cb));
        this.l = (HealthRecycleView) inflate.findViewById(R.id.configured_page_recycle_view);
        this.e.setMoreTextVisibility(0);
        this.l.setNestedScrollingEnabled(false);
        this.l.setHasFixedSize(true);
        this.l.a(false);
        this.l.d(false);
        e();
        this.l.setLayoutManager(new LinearLayoutManager(this.c));
        setRecyclerViewLayout(this.l);
        return inflate;
    }

    private void setRecyclerViewLayout(HealthRecycleView healthRecycleView) {
        int i;
        int b = nrr.b(this.c);
        if (nsn.ag(this.c)) {
            i = 2;
        } else {
            i = 1;
            b = 0;
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.c, i);
        gridLayoutManager.setOrientation(1);
        healthRecycleView.setLayoutManager(gridLayoutManager);
        if (healthRecycleView.getItemDecorationCount() > 0) {
            healthRecycleView.removeItemDecorationAt(0);
        }
        healthRecycleView.addItemDecoration(new SpacesItemDecoration(b, BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8), i, new int[]{0, nsn.c(BaseApplication.getContext(), 4.0f), 0, nsn.c(BaseApplication.getContext(), 4.0f)}));
        healthRecycleView.setHasFixedSize(true);
        healthRecycleView.setNestedScrollingEnabled(false);
        healthRecycleView.setVisibility(0);
    }

    private void setScholasticSubTitle(String str) {
        if (TextUtils.isEmpty(str) || !this.f) {
            this.e.setMoreTextVisibility(4);
            this.e.setMoreLayoutVisibility(4);
        } else {
            this.e.setMoreTextVisibility(0);
            this.e.setMoreLayoutVisibility(0);
            this.e.setMoreText(str);
        }
    }

    public void setSubTitleVisible(boolean z) {
        this.f = z;
    }

    private void e() {
        this.l.setBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable.configured_item_image_text_background));
        ViewGroup.LayoutParams layoutParams = this.l.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            int dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
            int dimensionPixelSize2 = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
            layoutParams2.setMarginStart(dimensionPixelSize + ((Integer) this.m.first).intValue());
            layoutParams2.setMarginEnd(dimensionPixelSize2 + ((Integer) this.m.second).intValue());
            this.l.setLayoutParams(layoutParams2);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_Section3_2List_01";
    }
}
