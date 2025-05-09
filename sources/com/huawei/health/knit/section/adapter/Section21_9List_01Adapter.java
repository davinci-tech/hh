package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.marketing.request.GlobalSearchContent;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.flowlayout.HealthFlowLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.koq;
import defpackage.nmk;
import defpackage.nrf;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class Section21_9List_01Adapter extends RecyclerView.Adapter<e> {

    /* renamed from: a, reason: collision with root package name */
    private Context f2537a;
    private OnClickSectionListener c;
    private List<GlobalSearchContent> d;
    private int e = Integer.MAX_VALUE;
    private int b = -1;

    public Section21_9List_01Adapter(Context context, List<GlobalSearchContent> list) {
        this.f2537a = context;
        this.d = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: acA_, reason: merged with bridge method [inline-methods] */
    public e onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new e(LayoutInflater.from(this.f2537a).inflate(R.layout.section21_9list_01_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(e eVar, final int i) {
        if (koq.b(this.d, i)) {
            LogUtil.a("Section21_9List_01Adapter", "onBindViewHolder position out of bound");
            return;
        }
        GlobalSearchContent globalSearchContent = this.d.get(i);
        if (globalSearchContent == null) {
            LogUtil.b("Section21_9List_01Adapter", "onBindViewHolder content is null");
            return;
        }
        c(eVar, globalSearchContent);
        nsy.cMs_(eVar.h, globalSearchContent.getName(), true);
        nsy.cMs_(eVar.i, globalSearchContent.getPromotionInfo(), true);
        d(eVar, globalSearchContent);
        b(eVar, globalSearchContent);
        g(eVar, globalSearchContent);
        nsy.cMn_(eVar.c, new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.Section21_9List_01Adapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Section21_9List_01Adapter.this.c.onClick(i);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void c(e eVar, GlobalSearchContent globalSearchContent) {
        String productImageUrl = globalSearchContent.getProductImageUrl();
        if (TextUtils.isEmpty(productImageUrl)) {
            return;
        }
        nrf.d(productImageUrl, eVar.f2539a);
    }

    private void d(e eVar, GlobalSearchContent globalSearchContent) {
        int priceMode = globalSearchContent.getPriceMode();
        String promoPriceAccurate = globalSearchContent.getPromoPriceAccurate();
        if (priceMode == 2) {
            a(eVar);
        } else if (!TextUtils.isEmpty(promoPriceAccurate)) {
            e(eVar, globalSearchContent);
        } else {
            a(eVar, globalSearchContent);
        }
    }

    private void a(e eVar) {
        nsy.cMA_(eVar.d, 8);
        nsy.cMA_(eVar.j, 8);
        nsy.cMA_(eVar.g, 0);
        nsy.cMr_(eVar.g, this.f2537a.getResources().getString(R$string.IDS_merchandise_no_price));
    }

    private void e(e eVar, GlobalSearchContent globalSearchContent) {
        String p = CommonUtil.p(globalSearchContent.getPriceAccurate());
        if (TextUtils.isEmpty(p)) {
            nsy.cMA_(eVar.j, 8);
        } else {
            nsy.cMA_(eVar.j, 0);
            nsy.cMs_(eVar.j, "¥" + p, true);
        }
        String p2 = CommonUtil.p(globalSearchContent.getPromoPriceAccurate());
        if (TextUtils.isEmpty(p2)) {
            nsy.cMA_(eVar.d, 8);
            nsy.cMA_(eVar.g, 8);
        } else {
            nsy.cMA_(eVar.d, 0);
            nsy.cMA_(eVar.g, 0);
            nsy.cMr_(eVar.g, p2);
        }
    }

    private void a(e eVar, GlobalSearchContent globalSearchContent) {
        nsy.cMA_(eVar.j, 8);
        String p = CommonUtil.p(globalSearchContent.getPriceAccurate());
        if (TextUtils.isEmpty(p)) {
            nsy.cMA_(eVar.d, 8);
            nsy.cMA_(eVar.g, 8);
        } else {
            nsy.cMA_(eVar.d, 0);
            nsy.cMA_(eVar.g, 0);
            nsy.cMs_(eVar.g, p, true);
        }
    }

    private void b(e eVar, GlobalSearchContent globalSearchContent) {
        if (eVar.f == null) {
            LogUtil.b("Section21_9List_01Adapter", "onBindViewHolder holder's promoLabels is null");
            return;
        }
        List<String> promoLabels = globalSearchContent.getPromoLabels();
        if (koq.b(promoLabels)) {
            LogUtil.a("Section21_9List_01Adapter", "onBindViewHolder product's promoLabels is empty");
            eVar.f.setVisibility(8);
            return;
        }
        ArrayList<nmk> arrayList = new ArrayList<>();
        for (int i = 0; i < promoLabels.size(); i++) {
            String str = promoLabels.get(i);
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(new nmk(i, str, 1));
            }
        }
        eVar.f.setVisibility(0);
        eVar.f.e(arrayList, false);
    }

    private void g(e eVar, GlobalSearchContent globalSearchContent) {
        if (globalSearchContent.getRateCount() <= 0) {
            nsy.cMA_(eVar.o, 0);
            nsy.cMA_(eVar.e, 8);
            nsy.cMr_(eVar.o, this.f2537a.getString(R$string.IDS_merchandise_no_reviews));
            return;
        }
        nsy.cMA_(eVar.o, 0);
        nsy.cMr_(eVar.o, String.valueOf(globalSearchContent.getRateCount()) + this.f2537a.getResources().getString(R$string.IDS_merchandise_review));
        if (TextUtils.isEmpty(globalSearchContent.getGoodRate())) {
            nsy.cMA_(eVar.e, 8);
            return;
        }
        nsy.cMA_(eVar.e, 0);
        nsy.cMr_(eVar.e, globalSearchContent.getGoodRate() + "%" + this.f2537a.getResources().getString(R$string.IDS_merchandise_good_rate));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.b == -1) {
            int i = this.e;
            List<GlobalSearchContent> list = this.d;
            this.b = Math.min(i, list != null ? list.size() : 0);
        }
        return this.b;
    }

    public void c(int i) {
        this.e = i;
    }

    public void d(OnClickSectionListener onClickSectionListener) {
        this.c = onClickSectionListener;
    }

    class e extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthImageView f2539a;
        private RelativeLayout c;
        private HealthTextView d;
        private HealthTextView e;
        private HealthFlowLayout f;
        private HealthTextView g;
        private HealthTextView h;
        private HealthTextView i;
        private HealthTextView j;
        private HealthTextView o;

        e(View view) {
            super(view);
            this.c = (RelativeLayout) view.findViewById(R.id.section21_9list_01_recycle_item);
            this.f2539a = (HealthImageView) view.findViewById(R.id.section21_9list_01_item_picture);
            this.h = (HealthTextView) view.findViewById(R.id.merchandise_name);
            this.i = (HealthTextView) view.findViewById(R.id.merchandise_promotion_info);
            this.d = (HealthTextView) view.findViewById(R.id.merchandise_currency_unit);
            this.g = (HealthTextView) view.findViewById(R.id.merchandise_price);
            this.j = (HealthTextView) view.findViewById(R.id.merchandise_original_price);
            this.f = (HealthFlowLayout) view.findViewById(R.id.merchandise_promo_labels);
            this.o = (HealthTextView) view.findViewById(R.id.merchandise_rate_count);
            this.e = (HealthTextView) view.findViewById(R.id.merchandise_good_rate);
            HealthTextView healthTextView = this.j;
            healthTextView.setPaintFlags(healthTextView.getPaintFlags() | 16);
            this.f.setTagHeight(Section21_9List_01Adapter.this.f2537a.getResources().getDimensionPixelSize(R.dimen._2131362922_res_0x7f0a046a));
            this.f.setChildViewPadding(Section21_9List_01Adapter.this.f2537a.getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532));
            this.f.setVerticalSpacing(Section21_9List_01Adapter.this.f2537a.getResources().getDimensionPixelSize(R.dimen._2131363003_res_0x7f0a04bb));
            this.f.setTextViewSpacing(Section21_9List_01Adapter.this.f2537a.getResources().getDimensionPixelSize(R.dimen._2131363003_res_0x7f0a04bb));
            this.f.setTextViewBuilder(new ProductPromoTagTextViewBuilder(Section21_9List_01Adapter.this.f2537a));
        }
    }
}
