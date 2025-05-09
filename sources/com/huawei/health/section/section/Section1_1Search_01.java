package com.huawei.health.section.section;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.health.section.adapter.Section1_1Search_01Adapter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ccq;
import defpackage.eet;
import defpackage.fbh;
import defpackage.fbm;
import defpackage.koq;
import defpackage.nru;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class Section1_1Search_01 extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private String f2977a;
    private Section1_1Search_01Adapter b;
    private View c;
    private Context d;
    private Observer e;
    private HealthRecycleView i;
    private HealthSubHeader j;

    public Section1_1Search_01(Context context) {
        this(context, null);
    }

    public Section1_1Search_01(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Section1_1Search_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section1_1list_01_layout, (ViewGroup) this, false);
        this.c = inflate;
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.section_sub_header);
        this.j = healthSubHeader;
        ccq.c(healthSubHeader);
        this.j.setSubHeaderBackgroundColor(ContextCompat.getColor(this.c.getContext(), R.color._2131296971_res_0x7f0902cb));
        this.c.findViewById(R.id.section1_1list_01_recycler_layout).setVisibility(0);
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.c.findViewById(R.id.section1_1list_01_recycler_view);
        this.i = healthRecycleView;
        ccq.a(healthRecycleView, context);
        return this.c;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        eet.e(this.d, this.i, this.b);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        fbm fbmVar = new fbm();
        ccq.d(this.j, hashMap);
        fbmVar.a(nru.a(hashMap, "CLICK_EVENT_LISTENER", null));
        fbmVar.l(nru.d(hashMap, "ITEM_TITLE", String.class, new ArrayList()));
        fbmVar.g(nru.d(hashMap, "ITEM_TAG", String.class, new ArrayList()));
        fbmVar.j(nru.d(hashMap, "ITEM_SUBTITLE", String.class, new ArrayList()));
        fbmVar.d(nru.d(hashMap, "ITEM_IMAGE", Object.class, new ArrayList()));
        fbmVar.i(nru.d(hashMap, "ITEM_RIGHT_BTN", String.class, new ArrayList()));
        fbmVar.a(nru.b(hashMap, "HIGHLIGHTED_TEXT", ""));
        fbmVar.f(nru.d(hashMap, "ITEM_PARENT_TITLE", String.class, new ArrayList()));
        fbmVar.a(nru.d(hashMap, "ITEM_DESCRIPTION", String.class, new ArrayList()));
        fbmVar.b(nru.d(hashMap, "ITEM_JOIN_NUMBER", String.class, new ArrayList()));
        fbmVar.h(nru.d(hashMap, "ITEM_PAGE_ATTRIBUTE", String.class, new ArrayList()));
        fbmVar.c(nru.d(hashMap, "RIGHT_ICON_IMAGE", Object.class, new ArrayList()));
        Object obj = hashMap.get("ITEM_BI_EVENT_MAP");
        if (koq.e(obj, Map.class)) {
            fbmVar.e((List) obj);
        }
        String b = nru.b(hashMap, "BI_OBSERVER_LABEL", null);
        if (b != null && !TextUtils.equals(b, this.f2977a)) {
            String str = this.f2977a;
            if (str != null && this.e != null) {
                LogUtil.a("Section1_1Search_01", "unregister old observer, old label: ", str);
                ObserverManagerUtil.e(this.e, this.f2977a);
            }
            Observer a2 = fbh.a(this.i);
            this.e = a2;
            this.f2977a = b;
            ObserverManagerUtil.d(a2, b);
            LogUtil.a("Section1_1Search_01", "register biObserver, label: ", this.f2977a);
        }
        this.b = new Section1_1Search_01Adapter(this.d, fbmVar);
        int d = nru.d((Map) hashMap, "ITEM_LIMIT", 0);
        if (d > 0) {
            this.b.d(d);
        }
        this.i.setAdapter(this.b);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section1_1Search_01";
    }
}
