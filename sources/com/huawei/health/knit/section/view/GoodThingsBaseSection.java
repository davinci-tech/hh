package com.huawei.health.knit.section.view;

import android.content.Context;
import android.content.res.Configuration;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener;
import com.huawei.health.knit.section.adapter.HorizontalAdapter;
import com.huawei.health.knit.section.view.GoodThingsBaseSection;
import com.huawei.health.marketing.utils.ColumnLayoutItemDecoration;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.eak;
import defpackage.eej;
import defpackage.ixx;
import defpackage.nmi;
import defpackage.nrf;
import defpackage.nru;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class GoodThingsBaseSection extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2628a;
    private Context b;
    private boolean c;
    private String d;
    private BaseRecyclerAdapter e;
    private HorizontalAdapter f;
    private HealthRecycleView g;
    private View h;
    private OnClickSectionListener i;
    private int j;
    private String k;
    private String l;
    private HealthTextView m;
    private LinearLayout n;
    private String o;
    private Button s;

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected boolean isSupportSafeRegion() {
        return false;
    }

    public GoodThingsBaseSection(Context context) {
        super(context);
        this.c = false;
    }

    public GoodThingsBaseSection(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = false;
    }

    public GoodThingsBaseSection(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = false;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section1_1list_02_layout, (ViewGroup) this, false);
        this.h = inflate;
        ahF_(inflate);
        this.b = context;
        return this.h;
    }

    private void ahF_(View view) {
        LogUtil.a("GoodThingsBaseSection", "intView");
        this.s = (Button) view.findViewById(R.id.not_interest_button);
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.section1_1list_02_recycler_view);
        this.g = healthRecycleView;
        healthRecycleView.setHasFixedSize(true);
        this.g.setNestedScrollingEnabled(false);
        this.g.a(false);
        this.g.d(false);
        this.f2628a = (HealthTextView) view.findViewById(R.id.section_bottom_text);
        this.m = (HealthTextView) view.findViewById(R.id.title);
        this.n = (LinearLayout) view.findViewById(R.id.lin_recycleview);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        this.i = nru.a(hashMap, "CLICK_EVENT_LISTENER", null);
        setSupportShare(nru.d((Map) hashMap, "IS_SUPPORT_SHARE", false));
        d(hashMap);
        c(hashMap);
        a(hashMap);
        e(hashMap);
    }

    private void e(HashMap<String, Object> hashMap) {
        LogUtil.a("GoodThingsBaseSection", "bindViewTreeObserver");
        this.o = nru.b(hashMap, "RESOURCE_ID", "");
        this.j = nru.d((Map) hashMap, "POSITION_ID", 0);
        this.l = nru.b(hashMap, "RESOURCE_NAME", "");
        this.d = nru.b(hashMap, "algId", "");
        this.k = nru.b(hashMap, "smartRecommend", "");
        View view = this.h;
        ViewTreeVisibilityListener.Zy_(view, new ViewTreeVisibilityListener(view, this));
    }

    private void c(HashMap<String, Object> hashMap) {
        LogUtil.a("GoodThingsBaseSection", "bindBottomView");
        if (nru.d((Map) hashMap, "RECYCLER_LAYOUT_MANAGER_ORITENTION", 1) == 0) {
            this.f2628a.setVisibility(8);
            a();
        } else {
            this.n.setBackground(getResources().getDrawable(R.drawable.good_things_background_radius));
            this.f2628a.setText(nru.b(hashMap, "BOTTOM_TEXT", null));
            this.f2628a.setOnClickListener(new View.OnClickListener() { // from class: efc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GoodThingsBaseSection.this.ahG_(view);
                }
            });
        }
    }

    public /* synthetic */ void ahG_(View view) {
        OnClickSectionListener onClickSectionListener = this.i;
        if (onClickSectionListener != null) {
            onClickSectionListener.onClick("BOTTOM_BUTTON_CLICKED");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, 0);
        this.n.setPadding(0, 0, 0, 0);
        this.n.setLayoutParams(layoutParams);
        this.g.setOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.health.knit.section.view.GoodThingsBaseSection.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                LogUtil.c("GoodThingsBaseSection", "bindBottomView newState: ", Integer.valueOf(i));
                if (GoodThingsBaseSection.this.g.getLayoutManager() instanceof LinearLayoutManager) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) GoodThingsBaseSection.this.g.getLayoutManager();
                    int findLastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    int itemCount = linearLayoutManager.getItemCount();
                    LogUtil.c("GoodThingsBaseSection", "bindBottomView lastItem: ", Integer.valueOf(findLastCompletelyVisibleItemPosition), " ", Integer.valueOf(itemCount));
                    if (i != 0 || findLastCompletelyVisibleItemPosition != itemCount - 1 || GoodThingsBaseSection.this.i == null || GoodThingsBaseSection.this.c) {
                        return;
                    }
                    LogUtil.c("GoodThingsBaseSection", "bindBottomView last: ");
                    GoodThingsBaseSection.this.i.onClick("BOTTOM_BUTTON_CLICKED");
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
            }
        });
    }

    private void d(HashMap<String, Object> hashMap) {
        LogUtil.a("GoodThingsBaseSection", "bindSubHeader");
        this.m.setText(nru.b(hashMap, "TITLE", null));
        this.s.setOnClickListener(new View.OnClickListener() { // from class: efd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GoodThingsBaseSection.this.ahI_(view);
            }
        });
    }

    public /* synthetic */ void ahI_(View view) {
        OnClickSectionListener onClickSectionListener = this.i;
        if (onClickSectionListener != null) {
            onClickSectionListener.onClick("SUBHEADER_ACTION_CLICKED");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(HashMap<String, Object> hashMap) {
        int d = nru.d((Map) hashMap, "RECYCLER_LAYOUT_MANAGER_ORITENTION", 1);
        List<eak> d2 = nru.d(hashMap, "RECYCLER_LIST", eak.class, new ArrayList());
        this.c = nru.d((Map) hashMap, "HORIZONTAL_DATA_HAS_NEXT", false);
        int d3 = nru.d((Map) hashMap, "HORIZONTAL_DATA_ITEM_SIZE", 0);
        if (d == 0) {
            LogUtil.a("GoodThingsBaseSection", "bindRecyclerView: ", Boolean.valueOf(this.c));
            if (this.f == null) {
                HorizontalAdapter horizontalAdapter = new HorizontalAdapter(this.b, d2);
                this.f = horizontalAdapter;
                horizontalAdapter.c((!this.c || d2.size() < 4) ? d2.size() : Integer.MAX_VALUE);
                this.g.setLayoutManager(new HealthLinearLayoutManager(getContext(), 0, false));
                this.g.setAdapter(this.f);
                this.f.c(new HorizontalAdapter.OnItemClickListener() { // from class: efj
                    @Override // com.huawei.health.knit.section.adapter.HorizontalAdapter.OnItemClickListener
                    public final void onItemClick(View view, int i) {
                        GoodThingsBaseSection.this.ahH_(view, i);
                    }
                });
                return;
            }
            LogUtil.a("GoodThingsBaseSection", "bindRecyclerView again: ", Boolean.valueOf(this.c));
            this.f.c((!this.c || d2.size() < 4) ? d2.size() : Integer.MAX_VALUE);
            this.f.c(d2);
            this.f.notifyItemRangeInserted(d3, d2.size());
            return;
        }
        BaseRecyclerAdapter baseRecyclerAdapter = this.e;
        if (baseRecyclerAdapter == null) {
            b(hashMap);
            BaseRecyclerAdapter d4 = d(nru.d((Map) hashMap, "RECYCLER_LIST_ITEM_RESID", 0), d2, d);
            this.e = d4;
            this.g.setAdapter(d4);
            return;
        }
        baseRecyclerAdapter.refreshDataChange(d2);
    }

    public /* synthetic */ void ahH_(View view, int i) {
        OnClickSectionListener onClickSectionListener = this.i;
        if (onClickSectionListener != null) {
            onClickSectionListener.onClick(i);
        }
    }

    private void b(HashMap<String, Object> hashMap) {
        this.g.setLayoutManager(new HealthLinearLayoutManager(getContext(), nru.d((Map) hashMap, "RECYCLER_LAYOUT_MANAGER_ORITENTION", 1), false));
        this.g.addItemDecoration(new ColumnLayoutItemDecoration(getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8), 0, 1, new int[]{getResources().getDimensionPixelSize(R.dimen._2131362009_res_0x7f0a00d9), 0, getResources().getDimensionPixelSize(R.dimen._2131362007_res_0x7f0a00d7), 0}));
    }

    private BaseRecyclerAdapter d(int i, List<eak> list, final int i2) {
        BaseRecyclerAdapter<eak> baseRecyclerAdapter = new BaseRecyclerAdapter<eak>(list, new int[]{i}) { // from class: com.huawei.health.knit.section.view.GoodThingsBaseSection.4
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void convert(RecyclerHolder recyclerHolder, int i3, eak eakVar) {
                ViewGroup.LayoutParams layoutParams = recyclerHolder.itemView.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.height = -2;
                    layoutParams.width = -2;
                    ((HealthTextView) recyclerHolder.itemView.findViewById(R.id.provider)).setTextSize(1, 10.0f);
                }
                GoodThingsBaseSection.this.c(recyclerHolder);
                recyclerHolder.b(R.id.title, eakVar.i());
                recyclerHolder.b(R.id.provider, eakVar.f());
                recyclerHolder.c(R.id.couponInfo, GoodThingsBaseSection.this.ahE_(eakVar));
                recyclerHolder.b(R.id.desc, eakVar.a());
                String b = GoodThingsBaseSection.this.b(eakVar);
                recyclerHolder.b(R.id.sales_num, b);
                recyclerHolder.a(R.id.couponInfo, TextUtils.isEmpty(eakVar.b()) ? 8 : 0);
                recyclerHolder.a(R.id.desc, TextUtils.isEmpty(eakVar.a()) ? 8 : 0);
                recyclerHolder.a(R.id.sales_num, TextUtils.isEmpty(b) ? 8 : 0);
                recyclerHolder.c(R.id.text1, eej.agV_(eakVar.h(), eakVar.c(), GoodThingsBaseSection.this.getResources().getDimensionPixelSize(R.dimen._2131362869_res_0x7f0a0435)));
                String j = eakVar.j();
                if (j != null) {
                    SpannableString spannableString = new SpannableString(j);
                    spannableString.setSpan(new StrikethroughSpan(), 0, j.length(), 17);
                    recyclerHolder.c(R.id.text2, spannableString);
                }
                GoodThingsBaseSection.this.e(recyclerHolder, j);
                recyclerHolder.b(R.id.image, eakVar.d(), nrf.e);
                int i4 = (int) ((GoodThingsBaseSection.this.getContext().getResources().getDisplayMetrics().density * 8.0f) + 0.5f);
                if (i2 == 1) {
                    float f = i4;
                    nrf.a(eakVar.d(), (HealthImageView) recyclerHolder.cwK_(R.id.image), f, f, f, f);
                } else {
                    float f2 = i4;
                    nrf.a(eakVar.d(), (HealthImageView) recyclerHolder.cwK_(R.id.image), f2, f2, 0.0f, 0.0f);
                }
                if (recyclerHolder.cwK_(R.id.divider) != null) {
                    recyclerHolder.cwK_(R.id.divider).setVisibility(i3 != getItemCount() - 1 ? 0 : 8);
                }
            }
        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() { // from class: efe
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
            public final void onItemClicked(RecyclerHolder recyclerHolder, int i3, Object obj) {
                GoodThingsBaseSection.this.b(recyclerHolder, i3, obj);
            }
        });
        return baseRecyclerAdapter;
    }

    public /* synthetic */ void b(RecyclerHolder recyclerHolder, int i, Object obj) {
        OnClickSectionListener onClickSectionListener = this.i;
        if (onClickSectionListener != null) {
            onClickSectionListener.onClick(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(RecyclerHolder recyclerHolder) {
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.itemView.findViewById(R.id.title);
        if (nsn.e(1.45f)) {
            LogUtil.a("GoodThingsBaseSection", "largeFontLayout : ", Float.valueOf(nsn.c()));
            if (nsn.r()) {
                ((LinearLayout) recyclerHolder.itemView.findViewById(R.id.center_layout)).setVisibility(8);
                float convertDpToPixel = Utils.convertDpToPixel(nsn.c() * 2.0f * nsn.c());
                ((LinearLayout) recyclerHolder.itemView.findViewById(R.id.product_msg)).getLayoutParams().height = ((int) convertDpToPixel) + this.b.getResources().getDimensionPixelOffset(R.dimen._2131363127_res_0x7f0a0537);
            }
            healthTextView.setMaxLines(1);
            return;
        }
        healthTextView.setMaxLines(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(RecyclerHolder recyclerHolder, String str) {
        final HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.text2);
        if (healthTextView == null) {
            return;
        }
        recyclerHolder.a(R.id.text2, TextUtils.isEmpty(str) ? 8 : 0);
        healthTextView.post(new Runnable() { // from class: com.huawei.health.knit.section.view.GoodThingsBaseSection.3
            @Override // java.lang.Runnable
            public void run() {
                healthTextView.requestLayout();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(eak eakVar) {
        String e = eakVar.e();
        LogUtil.c("GoodThingsBaseSection", "salesNum: ", e);
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("GoodThingsBaseSection", "handleSoldNum salesNum is empty");
            return "";
        }
        switch (e.length()) {
            case 1:
                int parseInt = Integer.parseInt(e);
                if (Integer.parseInt(e) != 0) {
                    break;
                }
                break;
            case 2:
            case 3:
            case 4:
                char charAt = e.charAt(0);
                break;
            case 5:
            case 6:
                break;
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SpannableString ahE_(eak eakVar) {
        String b = eakVar.b();
        LogUtil.c("GoodThingsBaseSection", "coupon message: ", b);
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        SpannableString spannableString = new SpannableString(b + " ");
        int color = ContextCompat.getColor(getContext(), R.color._2131296777_res_0x7f090209);
        int color2 = ContextCompat.getColor(getContext(), R.color.emui_accent);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131363063_res_0x7f0a04f7);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen._2131363003_res_0x7f0a04bb);
        float dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen._2131362945_res_0x7f0a0481);
        spannableString.setSpan(new nmi.c().d(color).b(dimensionPixelSize).a(dimensionPixelOffset).e(getResources().getDimensionPixelSize(R.dimen._2131362362_res_0x7f0a023a)).a(getResources().getDimensionPixelSize(R.dimen._2131363063_res_0x7f0a04f7)).i(getResources().getDimensionPixelSize(R.dimen._2131365067_res_0x7f0a0ccb)).c(dimensionPixelSize2).f(color2).c(), 0, b.length(), 17);
        return spannableString;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
    public void biEvent() {
        a(this.j, this.o, this.l);
    }

    private void a(int i, String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", 1);
        hashMap.put("resourceId", str);
        hashMap.put("resourcePositionId", Integer.valueOf(i));
        hashMap.put("resourceName", str2);
        hashMap.put("pullOrder", 1);
        hashMap.put("algId", this.d);
        hashMap.put("smartRecommend", this.k);
        ixx.d().d(BaseApplication.e(), AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "GoodThingsBaseSection";
    }
}
