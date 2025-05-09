package com.huawei.health.marketing.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.MultiTabHorizontalTemplate;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleGrid4Tab;
import com.huawei.health.marketing.datatype.SingleTab;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.datatype.templates.CornerTemplate;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.eiv;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class MultiTabHorizontalLayout extends BaseLifeCycleLinearLayout implements ResourceBriefInfoOwnable {

    /* renamed from: a, reason: collision with root package name */
    private HealthRecycleView f2878a;
    private final Context b;
    private final Map<String, List<SingleGrid4Tab>> c;
    private int d;
    private e e;
    private int f;
    private String g;
    private ResourceBriefInfo h;
    private String i;
    private HealthImageView j;
    private c k;
    private HealthRecycleView l;
    private long m;
    private View n;
    private List<SingleTab> o;
    private MultiTabHorizontalTemplate t;

    public MultiTabHorizontalLayout(Context context) {
        this(context, null);
    }

    public MultiTabHorizontalLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MultiTabHorizontalLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public MultiTabHorizontalLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.c = new HashMap();
        this.d = 0;
        this.b = context;
    }

    public void e(int i, ResourceBriefInfo resourceBriefInfo, BaseTemplate baseTemplate) {
        LogUtil.a("MultiTabHorizontalLayout", "initData");
        if (resourceBriefInfo != null) {
            LogUtil.a("MultiTabHorizontalLayout", "resourceBriefInfo: " + resourceBriefInfo.toString());
        }
        this.f = i;
        this.h = resourceBriefInfo;
        if (resourceBriefInfo != null) {
            this.i = resourceBriefInfo.getResourceId();
            this.g = this.h.getResourceName();
        }
        this.m = System.currentTimeMillis();
        if (baseTemplate instanceof MultiTabHorizontalTemplate) {
            MultiTabHorizontalTemplate multiTabHorizontalTemplate = (MultiTabHorizontalTemplate) baseTemplate;
            this.t = multiTabHorizontalTemplate;
            this.o = multiTabHorizontalTemplate.getTabs();
            List<SingleGrid4Tab> gridContents = this.t.getGridContents();
            if (gridContents == null) {
                return;
            }
            for (SingleGrid4Tab singleGrid4Tab : gridContents) {
                if (singleGrid4Tab != null) {
                    String tabName = singleGrid4Tab.getTabName();
                    if (!this.c.containsKey(tabName)) {
                        this.c.put(tabName, new ArrayList());
                    }
                    this.c.get(tabName).add(singleGrid4Tab);
                }
            }
            Iterator<String> it = this.c.keySet().iterator();
            while (it.hasNext()) {
                List<SingleGrid4Tab> list = this.c.get(it.next());
                if (list != null) {
                    Collections.sort(list, new Comparator<SingleGrid4Tab>() { // from class: com.huawei.health.marketing.views.MultiTabHorizontalLayout.2
                        @Override // java.util.Comparator
                        /* renamed from: e, reason: merged with bridge method [inline-methods] */
                        public int compare(SingleGrid4Tab singleGrid4Tab2, SingleGrid4Tab singleGrid4Tab3) {
                            return singleGrid4Tab3.getPriority() - singleGrid4Tab2.getPriority();
                        }
                    });
                }
            }
        }
        if (this.c.size() == 0) {
            return;
        }
        b();
    }

    private void b() {
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.multi_tab_horizontal_layout, this);
        this.n = inflate;
        this.l = (HealthRecycleView) inflate.findViewById(R.id.tab_recycler_view);
        this.j = (HealthImageView) this.n.findViewById(R.id.tab_right_btn);
        this.f2878a = (HealthRecycleView) this.n.findViewById(R.id.grid_recycler_view);
        nsy.cMm_(this.j, eiv.alY_());
        this.j.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.marketing.views.MultiTabHorizontalLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (koq.b(MultiTabHorizontalLayout.this.o, MultiTabHorizontalLayout.this.d) || MultiTabHorizontalLayout.this.o.get(MultiTabHorizontalLayout.this.d) == null) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                MultiTabHorizontalLayout multiTabHorizontalLayout = MultiTabHorizontalLayout.this;
                multiTabHorizontalLayout.d(((SingleTab) multiTabHorizontalLayout.o.get(MultiTabHorizontalLayout.this.d)).getTabLink());
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.l.setLayoutManager(new HealthLinearLayoutManager(this.b, 0, false));
        this.f2878a.setLayoutManager(new HealthLinearLayoutManager(this.b, 0, false));
        this.k = new c();
        this.e = new e();
        this.l.setAdapter(this.k);
        this.f2878a.setAdapter(this.e);
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public boolean isOwnedByBriefInfo(ResourceBriefInfo resourceBriefInfo) {
        boolean z = (resourceBriefInfo == null || this.h == null || !resourceBriefInfo.getResourceId().equals(this.h.getResourceId())) ? false : true;
        LogUtil.a("MultiTabHorizontalLayout", "isOwnedByBriefInfo: " + z);
        return z;
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public int getResourceBriefPriority() {
        ResourceBriefInfo resourceBriefInfo = this.h;
        if (resourceBriefInfo != null) {
            return resourceBriefInfo.getPriority();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        ((MarketRouterApi) Services.c("FeatureMarketing", MarketRouterApi.class)).router(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("resourcePositionId", Integer.valueOf(this.f));
        hashMap.put("resourceId", this.i);
        hashMap.put("resourceName", this.g);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("itemCardName", str);
        LogUtil.a("MultiTabHorizontalLayout", "biEvent, event: ", Integer.valueOf(i), " name: ", str);
        if (i == 2) {
            hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - this.m));
            this.m = System.currentTimeMillis();
        }
        if (this.h != null) {
            hashMap.put("algId", "");
            hashMap.put("smartRecommend", Boolean.valueOf(this.h.getSmartRecommend()));
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("itemId", str2);
        }
        hashMap.put("pullOrder", 1);
        ixx.d().d(this.b, AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    class c extends RecyclerView.Adapter<b> {
        private c() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: apH_, reason: merged with bridge method [inline-methods] */
        public b onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.multi_tab_horizontal_tab_item, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(b bVar, final int i) {
            if (koq.b(MultiTabHorizontalLayout.this.o, i) || MultiTabHorizontalLayout.this.o.get(i) == null || bVar.c == null) {
                return;
            }
            nsy.cMA_(bVar.f2881a, i == getItemCount() - 1 ? 8 : 0);
            if (i == 0) {
                ((RelativeLayout.LayoutParams) bVar.c.getLayoutParams()).leftMargin = 0;
            }
            boolean z = MultiTabHorizontalLayout.this.d == i;
            bVar.c.setText(((SingleTab) MultiTabHorizontalLayout.this.o.get(i)).getTabName());
            bVar.c.setTextColor(ContextCompat.getColor(BaseApplication.e(), z ? R.color._2131299236_res_0x7f090ba4 : R.color._2131299241_res_0x7f090ba9));
            bVar.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.marketing.views.MultiTabHorizontalLayout.c.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (MultiTabHorizontalLayout.this.d == i) {
                        LogUtil.a("MultiTabHorizontalLayout", "same position, do nothing");
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    MultiTabHorizontalLayout.this.d = i;
                    if (MultiTabHorizontalLayout.this.k != null) {
                        MultiTabHorizontalLayout.this.k.notifyDataSetChanged();
                    }
                    if (MultiTabHorizontalLayout.this.e != null) {
                        MultiTabHorizontalLayout.this.e.notifyDataSetChanged();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (MultiTabHorizontalLayout.this.o != null) {
                return MultiTabHorizontalLayout.this.o.size();
            }
            return 0;
        }
    }

    static class b extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private final View f2881a;
        private final HealthTextView c;

        public b(View view) {
            super(view);
            this.c = (HealthTextView) view.findViewById(R.id.multi_tab_tab);
            this.f2881a = view.findViewById(R.id.multi_tab_line);
        }
    }

    class e extends RecyclerView.Adapter<a> {
        private e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: apF_, reason: merged with bridge method [inline-methods] */
        public a onCreateViewHolder(ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_marketing_multi_tab_slide, viewGroup, false);
            nsy.cMA_(inflate.findViewById(R.id.item_square_landscape_icon), 8);
            nsy.cMA_(inflate.findViewById(R.id.item_square_landscape_hot_amount), 8);
            return new a(inflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(a aVar, int i) {
            final SingleGrid4Tab singleGrid4Tab;
            if (koq.b(MultiTabHorizontalLayout.this.o, MultiTabHorizontalLayout.this.d) || MultiTabHorizontalLayout.this.o.get(MultiTabHorizontalLayout.this.d) == null) {
                return;
            }
            List list = (List) MultiTabHorizontalLayout.this.c.get(((SingleTab) MultiTabHorizontalLayout.this.o.get(MultiTabHorizontalLayout.this.d)).getTabName());
            if (koq.b(list, i) || (singleGrid4Tab = (SingleGrid4Tab) list.get(i)) == null) {
                return;
            }
            int c = nsn.c(MultiTabHorizontalLayout.this.b, 84.0f);
            ViewGroup.LayoutParams layoutParams = aVar.b.getLayoutParams();
            layoutParams.width = c;
            layoutParams.height = c;
            aVar.b.setLayoutParams(layoutParams);
            nrf.c(aVar.b, singleGrid4Tab.getPicture(), 0, 1, 0);
            eiv.d(aVar.f2880a, singleGrid4Tab.getTheme(), singleGrid4Tab.isThemeVisibility());
            eiv.d(aVar.d, (CornerTemplate) singleGrid4Tab, true);
            RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) aVar.e.getLayoutParams();
            layoutParams2.setMarginStart(i == 0 ? nsn.c(MultiTabHorizontalLayout.this.b, 12.0f) : 0);
            layoutParams2.setMarginEnd(nsn.c(MultiTabHorizontalLayout.this.b, 12.0f));
            layoutParams2.width = c;
            layoutParams2.height = -2;
            aVar.e.setLayoutParams(layoutParams2);
            aVar.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.marketing.views.MultiTabHorizontalLayout.e.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MultiTabHorizontalLayout.this.d(singleGrid4Tab.getLinkValue());
                    MultiTabHorizontalLayout.this.a(2, singleGrid4Tab.getTheme(), singleGrid4Tab.getDynamicDataId());
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            SingleTab singleTab;
            if (koq.b(MultiTabHorizontalLayout.this.o, MultiTabHorizontalLayout.this.d) || (singleTab = (SingleTab) MultiTabHorizontalLayout.this.o.get(MultiTabHorizontalLayout.this.d)) == null || MultiTabHorizontalLayout.this.c.get(singleTab.getTabName()) == null) {
                return 0;
            }
            return ((List) MultiTabHorizontalLayout.this.c.get(singleTab.getTabName())).size();
        }
    }

    static class a extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private final HealthTextView f2880a;
        private final HealthImageView b;
        private final HealthTextView d;
        private final View e;

        public a(View view) {
            super(view);
            this.e = view.findViewById(R.id.item_square_landscape_slide_root_layout);
            this.f2880a = (HealthTextView) view.findViewById(R.id.item_square_landscape_slide_title);
            this.b = (HealthImageView) view.findViewById(R.id.item_square_landscape_slide_image);
            this.d = (HealthTextView) view.findViewById(R.id.item_square_landscape_corner);
        }
    }
}
