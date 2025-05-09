package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.ObserverRecycleViewAdapter;
import com.huawei.health.marketing.utils.ColumnLayoutItemDecoration;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.eau;
import defpackage.nru;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class ObserverRecyclerViewSection extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private ObserverRecycleViewAdapter f2637a;
    private HealthRecycleView b;
    private LinearLayout c;
    private Context d;

    public ObserverRecyclerViewSection(Context context) {
        this(context, null);
    }

    public ObserverRecyclerViewSection(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ObserverRecyclerViewSection(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("ObserverRecycleView", "loadView");
        View inflate = LayoutInflater.from(context).inflate(R.layout.observer_recycleview_layout, (ViewGroup) this, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.bp_view_layout);
        this.c = linearLayout;
        linearLayout.post(new Runnable() { // from class: com.huawei.health.knit.section.view.ObserverRecyclerViewSection.4
            @Override // java.lang.Runnable
            public void run() {
                ObserverRecyclerViewSection.this.c.setMinimumWidth(nsn.n());
            }
        });
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.sectionbprecycleview_recycler_view);
        this.b = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(context, 0, false));
        return inflate;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("ObserverRecycleView", "bindViewParams");
        eau eauVar = new eau();
        eauVar.c(nru.d(hashMap, "CARD_TITLE_TEXT", String.class, new ArrayList()));
        eauVar.d(nru.d(hashMap, "CARD_DATA_TEXT", String.class, new ArrayList()));
        eauVar.b(nru.d(hashMap, "CARD_PIECE_TEXT", String.class, new ArrayList()));
        eauVar.e(nru.d(hashMap, "LEFT_MIDDLE_TEXT", String.class, new ArrayList()));
        eauVar.c(nru.d((Map) hashMap, "SELECTED_BACKGROUND", R.drawable._2131427569_res_0x7f0b00f1));
        eauVar.b(nru.d((Map) hashMap, "UNSELECTED_BACKGROUND", R.drawable._2131427568_res_0x7f0b00f0));
        eauVar.a(nru.d((Map) hashMap, "SELECTED_TEXT_COLOR", -16777216));
        eauVar.e(nru.d((Map) hashMap, "UNSELECTED_TEXT_COLOR", -16777216));
        eauVar.ace_((Typeface) nru.c(hashMap, "SELECTED_TEXT_TYPEFACE", Typeface.class, Typeface.create(this.d.getResources().getString(R$string.textFontFamilyMedium), 0)));
        eauVar.acf_((Typeface) nru.c(hashMap, "SELECTED_TEXT_TYPEFACE", Typeface.class, Typeface.create(this.d.getResources().getString(R$string.textFontFamilyRegular), 0)));
        eauVar.a((OnClickSectionListener) nru.c(hashMap, "CLICK_EVENT_LISTENER", OnClickSectionListener.class, null));
        if (this.f2637a == null) {
            LogUtil.a("ObserverRecycleView", "adapter == null");
            this.f2637a = new ObserverRecycleViewAdapter(this.d, eauVar);
            this.b.addItemDecoration(new ColumnLayoutItemDecoration(getResources().getDimensionPixelOffset(R.dimen._2131362008_res_0x7f0a00d8), 0, 2, new int[]{getResources().getDimensionPixelOffset(R.dimen._2131362009_res_0x7f0a00d9), 0, getResources().getDimensionPixelOffset(R.dimen._2131362009_res_0x7f0a00d9), 0}));
            this.b.setAdapter(this.f2637a);
        }
        this.f2637a.b(eauVar);
        this.f2637a.notifyDataSetChanged();
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "ObserverRecycleView";
    }
}
