package com.huawei.health.section.section;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.health.marketing.utils.ColumnLayoutItemDecoration;
import com.huawei.health.section.adapter.Section16_9List_01Adapter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ccq;
import defpackage.eie;
import defpackage.fbh;
import defpackage.fbn;
import defpackage.koq;
import defpackage.nru;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class Section16_9List_01 extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2976a;
    private Observer b;
    private Context c;
    private String d;
    private HealthRecycleView e;
    private View f;
    private HealthSubHeader g;

    public Section16_9List_01(Context context) {
        this(context, null);
    }

    public Section16_9List_01(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Section16_9List_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.cloumn_linearlayout, (ViewGroup) this, false);
        this.f = inflate;
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.marketing_grid_sub_header);
        this.g = healthSubHeader;
        healthSubHeader.setMoreTextVisibility(4);
        this.g.setMoreLayoutVisibility(4);
        this.g.setSubHeaderBackgroundColor(ContextCompat.getColor(this.f.getContext(), R.color._2131296971_res_0x7f0902cb));
        this.e = (HealthRecycleView) this.f.findViewById(R.id.marketing_grid_recycler_view);
        e();
        return this.f;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        e();
    }

    private void e() {
        LogUtil.a("Section16_9List_01", "initConfiguredItemLayout");
        boolean ag = nsn.ag(BaseApplication.getContext());
        this.f2976a = ag;
        d(eie.c(6, ag, 0), eie.b(6, this.f2976a), eie.a(6), eie.a(6, 0));
    }

    private void d(int i, int i2, int i3, int[] iArr) {
        if (this.c == null) {
            LogUtil.h("Section16_9List_01", "setRecyclerViewLayout() mContext is null.");
            return;
        }
        if (i < 1) {
            LogUtil.h("Section16_9List_01", "setRecyclerViewLayout() gridNumber should be at least 1.");
            return;
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.c, i);
        gridLayoutManager.setOrientation(1);
        this.e.setLayoutManager(gridLayoutManager);
        if (this.e.getItemDecorationCount() > 0) {
            this.e.removeItemDecorationAt(0);
        }
        this.e.addItemDecoration(new ColumnLayoutItemDecoration(i2, i3, i, iArr));
        this.e.setHasFixedSize(true);
        this.e.setNestedScrollingEnabled(false);
        this.e.a(false);
        this.e.d(false);
        this.e.setVisibility(0);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        fbn fbnVar = new fbn();
        ccq.d(this.g, hashMap);
        fbnVar.a(nru.a(hashMap, "CLICK_EVENT_LISTENER", null));
        int d = nru.d((Map) hashMap, "ITEM_LIMIT", 0);
        if (d > 0) {
            fbnVar.d(d);
        }
        fbnVar.g(nru.d(hashMap, "ITEM_VIEW_TYPE", Integer.class, new ArrayList()));
        fbnVar.f(nru.d(hashMap, "ITEM_TITLE", String.class, new ArrayList()));
        fbnVar.e(nru.d(hashMap, "ITEM_DESCRIPTION", String.class, new ArrayList()));
        fbnVar.d(nru.d(hashMap, "ITEM_IMAGE", Object.class, new ArrayList()));
        fbnVar.c(nru.d(hashMap, "ITEM_PAGE_ATTRIBUTE", String.class, new ArrayList()));
        fbnVar.i(nru.d(hashMap, "ITEM_STATUS", String.class, new ArrayList()));
        fbnVar.a(nru.d(hashMap, "ITEM_JOIN_NUMBER", String.class, new ArrayList()));
        fbnVar.j(nru.d(hashMap, "ITEM_STATUS_BACKGROUND", Drawable.class, new ArrayList()));
        fbnVar.a(nru.b(hashMap, "HIGHLIGHTED_TEXT", ""));
        Object obj = hashMap.get("ITEM_BI_EVENT_MAP");
        if (koq.e(obj, Map.class)) {
            fbnVar.b((List) obj);
        }
        String b = nru.b(hashMap, "BI_OBSERVER_LABEL", null);
        if (b != null && !TextUtils.equals(b, this.d)) {
            String str = this.d;
            if (str != null && this.b != null) {
                LogUtil.a("Section16_9List_01", "unregister old observer, old label: ", str);
                ObserverManagerUtil.e(this.b, this.d);
            }
            Observer a2 = fbh.a(this.e);
            this.b = a2;
            this.d = b;
            ObserverManagerUtil.d(a2, b);
            LogUtil.a("Section16_9List_01", "register biObserver, label: ", this.d);
        }
        this.e.setAdapter(new Section16_9List_01Adapter(this.c, fbnVar));
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section16_9List_01";
    }
}
