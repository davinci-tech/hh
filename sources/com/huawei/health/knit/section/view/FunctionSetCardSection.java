package com.huawei.health.knit.section.view;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.huawei.health.R;
import com.huawei.health.functionsetcard.FunctionSetPagerSnapHelper;
import com.huawei.health.functionsetcard.FunctionSetViewAdapter;
import com.huawei.health.functionsetcard.FunctionSetViewTouchHelperCallback;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.dnx;
import defpackage.koq;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class FunctionSetCardSection extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthColumnSystem f2626a;
    private HealthTextView b;
    private View c;
    private HealthTextView d;
    private Context e;
    private RelativeLayout f;
    private ItemTouchHelper g;
    private FunctionSetViewTouchHelperCallback h;
    private List<FunctionSetSubCardData> i;
    private GridLayoutManager j;
    private FunctionSetViewAdapter k;
    private HealthButton m;
    private HealthRecycleView n;
    private LinearLayout o;

    public FunctionSetCardSection(Context context) {
        super(context);
        this.i = new ArrayList(10);
    }

    public FunctionSetCardSection(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = new ArrayList(10);
    }

    public FunctionSetCardSection(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = new ArrayList(10);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.e = context;
        this.c = LayoutInflater.from(context).inflate(R.layout.home_item_layout_function_set, (ViewGroup) this, false);
        b();
        return this.c;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        HealthRecycleView healthRecycleView;
        HealthRecycleView healthRecycleView2;
        LogUtil.a("FunctionSetCardSection", "bindParamsToView");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("FunctionSetCardSection", "no need to bind");
            return;
        }
        setTextAndListener(hashMap);
        if (nru.d((Map) hashMap, "SET_ADAPTER", false) && (healthRecycleView2 = this.n) != null) {
            healthRecycleView2.setAdapter(this.k);
        }
        if (nru.d((Map) hashMap, "REFRESH_NO_CARD_LAYOUT_SHOW_STATUS", false)) {
            d();
        }
        if (nru.d((Map) hashMap, "NOTIFY_DATA_SET_CHANGED", false) && this.k != null && (healthRecycleView = this.n) != null) {
            healthRecycleView.post(new Runnable() { // from class: com.huawei.health.knit.section.view.FunctionSetCardSection.1
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetCardSection.this.k.notifyDataSetChanged();
                }
            });
        }
        e(hashMap);
        a(hashMap);
    }

    private void e(HashMap<String, Object> hashMap) {
        HealthRecycleView healthRecycleView;
        final List d = nru.d(hashMap, "NOTIFY_ITEM_MOVED", Integer.class, null);
        if (d == null || d.size() != 2 || this.k == null || (healthRecycleView = this.n) == null) {
            return;
        }
        healthRecycleView.post(new Runnable() { // from class: com.huawei.health.knit.section.view.FunctionSetCardSection.5
            @Override // java.lang.Runnable
            public void run() {
                FunctionSetCardSection.this.k.notifyItemMoved(((Integer) d.get(0)).intValue(), ((Integer) d.get(1)).intValue());
            }
        });
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        a();
    }

    private void a() {
        HealthColumnSystem healthColumnSystem = this.f2626a;
        if (healthColumnSystem != null) {
            healthColumnSystem.e(this.e);
            GridLayoutManager gridLayoutManager = this.j;
            if (gridLayoutManager != null) {
                gridLayoutManager.setSpanCount(this.f2626a.f() / 2);
            }
        }
    }

    private void b() {
        this.f2626a = new HealthColumnSystem(this.e, 1);
        this.j = new GridLayoutManager(this.e, this.f2626a.f() / 2);
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.c.findViewById(R.id.function_set_view);
        this.n = healthRecycleView;
        healthRecycleView.setFocusableInTouchMode(false);
        this.n.requestFocus();
        if (this.n.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.n.getLayoutParams();
            layoutParams.setMarginStart(this.e.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e) - (this.e.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8) / 2));
            layoutParams.setMarginEnd(this.e.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d) - (this.e.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8) / 2));
        }
        this.k = new FunctionSetViewAdapter(this.i, this.e);
        dnx.d().c(this.k);
        this.n.setLayoutManager(this.j);
        this.n.setIsFling(true);
        this.n.setAdapter(this.k);
        FunctionSetPagerSnapHelper functionSetPagerSnapHelper = new FunctionSetPagerSnapHelper(this.i.size());
        if (!nsn.ag(this.e.getApplicationContext())) {
            functionSetPagerSnapHelper.attachToRecyclerView(this.n);
        }
        this.h = new FunctionSetViewTouchHelperCallback(this.k, this.n);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(this.h);
        this.g = itemTouchHelper;
        itemTouchHelper.attachToRecyclerView(this.n);
        this.f = (RelativeLayout) this.c.findViewById(R.id.function_set_no_card);
        this.o = (LinearLayout) this.c.findViewById(R.id.modify_cards_layout);
        this.b = (HealthTextView) this.c.findViewById(R.id.add_card);
        this.m = (HealthButton) this.c.findViewById(R.id.btn_modify_cards);
        e();
        d();
    }

    private void e() {
        this.d = (HealthTextView) this.c.findViewById(R.id.empty_card_prompt_text);
        if (LanguageUtil.r(this.e.getApplicationContext())) {
            this.d.setTextSize(1, 9.0f);
        } else {
            this.d.setTextSize(0, this.e.getResources().getDimensionPixelSize(R.dimen._2131365062_res_0x7f0a0cc6));
        }
    }

    private void d() {
        if (koq.b(this.i)) {
            this.f.setVisibility(0);
            this.m.setVisibility(8);
            b(R.dimen._2131362565_res_0x7f0a0305);
        } else {
            this.f.setVisibility(8);
            this.m.setVisibility(0);
            b(R.dimen._2131362566_res_0x7f0a0306);
        }
    }

    private void b(int i) {
        if (this.o.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.o.getLayoutParams();
            layoutParams.bottomMargin = BaseApplication.getContext().getResources().getDimensionPixelOffset(i);
            this.o.setLayoutParams(layoutParams);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void clear() {
        LogUtil.a("FunctionSetCardSection", "onDestroy");
        ItemTouchHelper itemTouchHelper = this.g;
        if (itemTouchHelper != null) {
            itemTouchHelper.attachToRecyclerView(null);
            this.g = null;
        }
        HealthRecycleView healthRecycleView = this.n;
        if (healthRecycleView != null) {
            healthRecycleView.setOnScrollListener(null);
            this.n = null;
        }
        this.h = null;
        FunctionSetViewAdapter functionSetViewAdapter = this.k;
        if (functionSetViewAdapter != null) {
            functionSetViewAdapter.c();
        }
        this.k = null;
    }

    private void setTextAndListener(HashMap<String, Object> hashMap) {
        nsy.cMr_(this.b, nru.b(hashMap, "ADD_CARD_TEXT", ""));
        nsy.cMr_(this.d, nru.b(hashMap, "EMPTY_CARD_PROMPT_TEXT", ""));
        nsy.cMr_(this.m, nru.b(hashMap, "BTN_MODIFY_CARDS", ""));
        nsy.cMn_(this.f, (View.OnClickListener) nru.c(hashMap, "FUNCTION_SET_NO_CARD_CLICK_EVENT", View.OnClickListener.class, null));
        nsy.cMn_(this.m, (View.OnClickListener) nru.c(hashMap, "FUNCTION_SET_NO_CARD_CLICK_EVENT", View.OnClickListener.class, null));
        List d = nru.d(hashMap, "DATA_LIST", FunctionSetSubCardData.class, null);
        this.i.clear();
        if (koq.b(d)) {
            return;
        }
        this.i.addAll(d);
    }

    private void a(HashMap<String, Object> hashMap) {
        HealthRecycleView healthRecycleView;
        HealthRecycleView healthRecycleView2;
        HealthRecycleView healthRecycleView3;
        final int d = nru.d((Map) hashMap, "NOTIFY_ITEM_INSERTED", -1);
        if (d != -1 && this.k != null && (healthRecycleView3 = this.n) != null) {
            healthRecycleView3.post(new Runnable() { // from class: com.huawei.health.knit.section.view.FunctionSetCardSection.4
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetCardSection.this.k.notifyItemInserted(d);
                }
            });
        }
        final int d2 = nru.d((Map) hashMap, "NOTIFY_ITEM_REMOVED", -1);
        if (d2 != -1 && this.k != null && (healthRecycleView2 = this.n) != null) {
            healthRecycleView2.post(new Runnable() { // from class: com.huawei.health.knit.section.view.FunctionSetCardSection.3
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetCardSection.this.k.notifyItemRemoved(d2);
                }
            });
        }
        final List d3 = nru.d(hashMap, "NOTIFY_ITEM_RANGE_CHANGED", Integer.class, null);
        if (d3 == null || d3.size() != 2 || this.k == null || (healthRecycleView = this.n) == null) {
            return;
        }
        healthRecycleView.post(new Runnable() { // from class: com.huawei.health.knit.section.view.FunctionSetCardSection.2
            @Override // java.lang.Runnable
            public void run() {
                FunctionSetCardSection.this.k.notifyItemRangeChanged(((Integer) d3.get(0)).intValue(), ((Integer) d3.get(1)).intValue());
            }
        });
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "FunctionSetCardSection";
    }
}
