package com.huawei.health.knit.section.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.datatype.Product;
import com.huawei.trade.datatype.SubscriptionPromotion;
import com.huawei.trade.datatype.SubscriptionPromotionSimpleTemplate;
import com.huawei.trade.datatype.TimeLimitedPromotion;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.dpx;
import defpackage.dqj;
import defpackage.koq;
import defpackage.njn;
import defpackage.nrf;
import defpackage.nrv;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class SectionMemberTypeAdapter extends RecyclerView.Adapter<SectionMemberTypeViewHolder> {
    private int c;
    private boolean d;
    private OnClickSectionListener e;
    private List<Product> h;
    private int f = 0;

    /* renamed from: a, reason: collision with root package name */
    private Set<String> f2575a = new HashSet();
    private boolean b = Utils.o();

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: adL_, reason: merged with bridge method [inline-methods] */
    public SectionMemberTypeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SectionMemberTypeViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_member_type_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(SectionMemberTypeViewHolder sectionMemberTypeViewHolder, int i) {
        if (koq.b(this.h, i)) {
            return;
        }
        Product product = this.h.get(i);
        LogUtil.a("SectionMemberTypeAdapter", "onBindViewHolder position: ", Integer.valueOf(i), " products: ", product);
        nsy.cMs_(sectionMemberTypeViewHolder.o, product.getProductName(), true);
        nsy.cMr_(sectionMemberTypeViewHolder.c, njn.e(product.getCurrency()));
        nsy.cMr_(sectionMemberTypeViewHolder.b, dpx.b(product.getMicroPrice(), "#.##"));
        boolean e = e(sectionMemberTypeViewHolder.e, product);
        String b = b(product);
        LogUtil.a("SectionMemberTypeAdapter", "isShowGiveaway: ", Boolean.valueOf(e));
        if (e && TextUtils.isEmpty(b)) {
            b = a(product);
        }
        nsy.cMs_(sectionMemberTypeViewHolder.g, b, true);
        if (!e) {
            String a2 = a(product);
            nsy.cMs_(sectionMemberTypeViewHolder.n, a2, true);
            if (TextUtils.isEmpty(a2) && !this.b) {
                nsy.cMA_(sectionMemberTypeViewHolder.n, 4);
            }
            nsy.cMA_(sectionMemberTypeViewHolder.e, 8);
        } else {
            nsy.cMA_(sectionMemberTypeViewHolder.n, 8);
        }
        if (sectionMemberTypeViewHolder.g != null) {
            if (LanguageUtil.bc(BaseApplication.e())) {
                sectionMemberTypeViewHolder.g.setBackgroundResource(R.drawable._2131431386_res_0x7f0b0fda);
            } else {
                sectionMemberTypeViewHolder.g.setBackgroundResource(R.drawable._2131431385_res_0x7f0b0fd9);
            }
        }
        sectionMemberTypeViewHolder.f.getPaint().setFlags(17);
        nsy.cMs_(sectionMemberTypeViewHolder.f, c(product), true);
        if (this.b && sectionMemberTypeViewHolder.i != null) {
            if (this.d) {
                nsy.cMA_(sectionMemberTypeViewHolder.f, 0);
            }
            sectionMemberTypeViewHolder.i.setOrientation(1);
        }
        b(sectionMemberTypeViewHolder, i);
        d(sectionMemberTypeViewHolder, i);
        d(sectionMemberTypeViewHolder, product);
        if (this.b) {
            sectionMemberTypeViewHolder.d.setMinimumHeight(this.c);
        }
    }

    private boolean e(HealthImageView healthImageView, Product product) {
        if (product == null) {
            return false;
        }
        List<Product> giveaways = product.getGiveaways();
        if (koq.b(giveaways)) {
            return false;
        }
        String str = "";
        for (Product product2 : giveaways) {
            if (product2.isShowGiveawayPicFlag()) {
                str = product2.getProductUrl();
            }
            if (!TextUtils.isEmpty(str)) {
                break;
            }
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        nrf.c(str, healthImageView, (int) BaseApplication.e().getResources().getDimension(R.dimen._2131362362_res_0x7f0a023a));
        healthImageView.setVisibility(0);
        return true;
    }

    private String c(Product product) {
        if (product == null) {
            LogUtil.a("SectionMemberTypeAdapter", "getOriPrice product is null");
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(njn.e(product.getCurrency()));
        long nowTime = product.getNowTime();
        if (product.getPurchasePolicy() == Product.SUBSCRIPTION_PURCHASE_FLAG) {
            SubscriptionPromotion subscriptionPromotion = product.getSubscriptionPromotion();
            if (subscriptionPromotion == null) {
                LogUtil.a("SectionMemberTypeAdapter", "getOriPrice subpromotion is null");
                return "";
            }
            try {
                if (nowTime < Long.parseLong(subscriptionPromotion.getStartTime()) || nowTime > Long.parseLong(subscriptionPromotion.getEndTime())) {
                    LogUtil.a("SectionMemberTypeAdapter", "expired subscription");
                    return "";
                }
                stringBuffer.append(dpx.b(subscriptionPromotion.getMicroOriginalPrice(), "#.##"));
            } catch (NumberFormatException unused) {
                LogUtil.h("SectionMemberTypeAdapter", "getPromotionInfo NumberFormatException");
                return "";
            }
        }
        if (product.getPurchasePolicy() == Product.REPEAT_PURCHASE_FLAG) {
            TimeLimitedPromotion promotion = product.getPromotion();
            if (promotion == null) {
                LogUtil.h("SectionMemberTypeAdapter", "getRepeatPurchasePromotion promotion is null");
                return "";
            }
            if (nowTime > promotion.getPromotionEndTime()) {
                LogUtil.a("SectionMemberTypeAdapter", "expired promotion");
                return "";
            }
            stringBuffer.append(dpx.b(promotion.getMicroOriginPrice(), "#.##"));
        }
        return stringBuffer.toString();
    }

    private String b(Product product) {
        if (product == null) {
            LogUtil.a("SectionMemberTypeAdapter", "getPromotionInfo product is null");
            return "";
        }
        if (product.getPurchasePolicy() == Product.SUBSCRIPTION_PURCHASE_FLAG) {
            return e(product);
        }
        return d(product);
    }

    private String e(Product product) {
        if (product == null) {
            LogUtil.a("SectionMemberTypeAdapter", "getSubcriptionPromotionDesc product is null");
            return "";
        }
        SubscriptionPromotion subscriptionPromotion = product.getSubscriptionPromotion();
        if (subscriptionPromotion == null || !subscriptionPromotion.isDescriptionVisibility()) {
            LogUtil.h("SectionMemberTypeAdapter", "getSubcriptionPromotionDesc promotion is null");
            return "";
        }
        long nowTime = product.getNowTime();
        try {
            if (nowTime < Long.parseLong(subscriptionPromotion.getStartTime()) || nowTime > Long.parseLong(subscriptionPromotion.getEndTime())) {
                LogUtil.a("SectionMemberTypeAdapter", "expired subscription");
                return "";
            }
            if (subscriptionPromotion.getContentType() == 1) {
                SubscriptionPromotionSimpleTemplate subscriptionPromotionSimpleTemplate = (SubscriptionPromotionSimpleTemplate) nrv.b(subscriptionPromotion.getContent(), SubscriptionPromotionSimpleTemplate.class);
                return subscriptionPromotionSimpleTemplate != null ? subscriptionPromotionSimpleTemplate.getContent() : "";
            }
            LogUtil.a("SectionMemberTypeAdapter", "unsupported subscription promotion content type");
            return "";
        } catch (NumberFormatException unused) {
            LogUtil.h("SectionMemberTypeAdapter", "getPromotionInfo NumberFormatException");
            return "";
        }
    }

    private String d(Product product) {
        if (product == null) {
            LogUtil.a("SectionMemberTypeAdapter", "getRepeatPurchasePromotion product is null");
            return "";
        }
        TimeLimitedPromotion promotion = product.getPromotion();
        if (promotion == null || !product.isPromotionDescVisibility()) {
            LogUtil.h("SectionMemberTypeAdapter", "getRepeatPurchasePromotion promotion is null");
            return "";
        }
        if (product.getNowTime() > promotion.getPromotionEndTime()) {
            LogUtil.a("SectionMemberTypeAdapter", "expired promotion");
            return "";
        }
        return product.getPromotionDesc();
    }

    private void d(SectionMemberTypeViewHolder sectionMemberTypeViewHolder, Product product) {
        if (sectionMemberTypeViewHolder.d == null) {
            return;
        }
        LogUtil.a("SectionMemberTypeAdapter", "bindViewTreeObserver");
        sectionMemberTypeViewHolder.h = product;
        ViewTreeVisibilityListener.Zz_(sectionMemberTypeViewHolder.d, new ViewTreeVisibilityListener(sectionMemberTypeViewHolder.d, sectionMemberTypeViewHolder), 500L);
    }

    private String a(Product product) {
        char c;
        float f;
        if (product == null) {
            LogUtil.a("SectionMemberTypeAdapter", "getSubTitle product is null");
            return "";
        }
        if (product.getPurchasePolicy() == Product.SUBSCRIPTION_PURCHASE_FLAG) {
            return h(product);
        }
        if (product.getPurchasePolicy() != Product.REPEAT_PURCHASE_FLAG) {
            return "";
        }
        if (product.isTitleVisibility()) {
            return product.getTitle();
        }
        float microPrice = product.getMicroPrice() / 1000000.0f;
        String duration = product.getDuration();
        duration.hashCode();
        int hashCode = duration.hashCode();
        if (hashCode == 78476) {
            if (duration.equals(Product.MONTH_DURATION_FLAG)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 78488) {
            if (hashCode == 78538 && duration.equals(Product.SEASON_DURATION_FLAG)) {
                c = 2;
            }
            c = 65535;
        } else {
            if (duration.equals(Product.YEAR_DURATION_FLAG)) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            f = 30.0f;
        } else if (c == 1) {
            f = 365.0f;
        } else {
            if (c != 2) {
                return "";
            }
            f = 90.0f;
        }
        float f2 = microPrice / f;
        new DecimalFormat("0.0");
        String e = UnitUtil.e(f2, 1, 2);
        if (LanguageUtil.ak(BaseApplication.e()) || LanguageUtil.bn(BaseApplication.e())) {
            return BaseApplication.e().getString(R$string.IDS_vip_average_day_cost, e);
        }
        return njn.a(product.getCurrency(), e, R$string.IDS_vip_average_day_cost);
    }

    private String h(Product product) {
        if (product != null && product.isTitleVisibility()) {
            return TextUtils.isEmpty(product.getTitle()) ? "" : product.getTitle();
        }
        LogUtil.a("SectionMemberTypeAdapter", "getSubTitle product is null");
        return "";
    }

    private void b(SectionMemberTypeViewHolder sectionMemberTypeViewHolder, int i) {
        if (i == this.f) {
            nsy.cMz_(sectionMemberTypeViewHolder.d, R.drawable._2131431384_res_0x7f0b0fd8);
        } else {
            nsy.cMz_(sectionMemberTypeViewHolder.d, R.drawable._2131431383_res_0x7f0b0fd7);
        }
    }

    private void d(SectionMemberTypeViewHolder sectionMemberTypeViewHolder, final int i) {
        nsy.cMn_(sectionMemberTypeViewHolder.d, new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.SectionMemberTypeAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SectionMemberTypeAdapter.this.e != null) {
                    SectionMemberTypeAdapter.this.e.onClick(i);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.h)) {
            return 0;
        }
        return this.h.size();
    }

    public void e(List<Product> list) {
        this.h = list;
        b(list);
    }

    private void b(List<Product> list) {
        if (this.b) {
            this.c = BaseApplication.e().getResources().getDimensionPixelSize(R.dimen._2131362885_res_0x7f0a0445);
            this.d = false;
            for (Product product : list) {
                boolean isEmpty = TextUtils.isEmpty(a(product));
                boolean isEmpty2 = TextUtils.isEmpty(c(product));
                if ((!isEmpty) && (!isEmpty2)) {
                    this.d = true;
                    this.c = BaseApplication.e().getResources().getDimensionPixelSize(R.dimen._2131362905_res_0x7f0a0459);
                    return;
                }
            }
        }
    }

    public void a(int i) {
        this.f = i;
    }

    public void b(OnClickSectionListener onClickSectionListener) {
        this.e = onClickSectionListener;
    }

    public class SectionMemberTypeViewHolder extends RecyclerView.ViewHolder implements ViewTreeVisibilityListener.ViewTreeListenee {
        private final HealthTextView b;
        private final HealthTextView c;
        private final View d;
        private final HealthImageView e;
        private final HealthTextView f;
        private final HealthTextView g;
        private Product h;
        private final LinearLayout i;
        private final View j;
        private final HealthTextView n;
        private final HealthTextView o;

        public SectionMemberTypeViewHolder(View view) {
            super(view);
            this.d = view;
            this.j = view.findViewById(R.id.section_member_type_root_layout);
            this.g = (HealthTextView) view.findViewById(R.id.section_member_type_promo);
            this.o = (HealthTextView) view.findViewById(R.id.section_member_type_item_title);
            this.n = (HealthTextView) view.findViewById(R.id.section_member_type_item_subtitle);
            this.i = (LinearLayout) view.findViewById(R.id.section_member_type_price_layout);
            this.c = (HealthTextView) view.findViewById(R.id.section_member_type_item_currency);
            this.b = (HealthTextView) view.findViewById(R.id.section_member_type_item_amount);
            this.f = (HealthTextView) view.findViewById(R.id.section_member_type_item_ori_price);
            this.e = (HealthImageView) view.findViewById(R.id.member_type_item_giveaway_icon);
        }

        @Override // com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
        public void checkVisibilityAndSetBiEvent() {
            Product product;
            if (!ViewTreeVisibilityListener.Zx_(this.d) || hasSetBiEvent() || (product = this.h) == null) {
                return;
            }
            LogUtil.a("SectionMemberTypeAdapter", "visible to user: ", product.getProductName());
            biEvent();
            SectionMemberTypeAdapter.this.f2575a.add(this.h.getProductId());
        }

        @Override // com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
        public boolean hasSetBiEvent() {
            return this.h != null && SectionMemberTypeAdapter.this.f2575a.contains(this.h.getProductId());
        }

        @Override // com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
        public void biEvent() {
            if (this.h == null) {
                return;
            }
            dqj.e(BaseApplication.e(), this.h);
        }
    }
}
