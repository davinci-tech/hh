package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
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
import com.huawei.health.knit.section.adapter.SectionExpandableAdapter;
import com.huawei.health.knit.section.adapter.SectionMemberTypeAdapter;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.datatype.Product;
import com.huawei.trade.datatype.SubscriptionPromotion;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.dpx;
import defpackage.eao;
import defpackage.efg;
import defpackage.gpn;
import defpackage.koq;
import defpackage.njn;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionMemberType extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private SectionExpandableAdapter f2700a;
    private SectionExpandableListView b;
    private boolean c;
    private Context d;
    private SectionMemberTypeAdapter e;
    private Product f;
    private RelativeLayout g;
    private Bundle h;
    private HealthRecycleView i;
    private boolean j;
    private HealthTextView l;
    private HealthSubHeader o;

    public SectionMemberType(Context context) {
        this(context, null);
    }

    public SectionMemberType(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionMemberType(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = true;
        this.j = false;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_member_type, (ViewGroup) this, false);
        this.d = context;
        this.h = (getKnitFragment() == null || getKnitFragment().getPageResTrigger() == null) ? null : getKnitFragment().getPageResTrigger().getExtra();
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.section_member_type_header);
        this.o = healthSubHeader;
        healthSubHeader.setSubHeaderBackgroundColor(ContextCompat.getColor(context, R.color._2131296971_res_0x7f0902cb));
        this.l = (HealthTextView) inflate.findViewById(R.id.section_member_renew_remind);
        this.i = (HealthRecycleView) inflate.findViewById(R.id.section_member_type_recyclerview);
        this.g = (RelativeLayout) inflate.findViewById(R.id.remind_text_rl);
        this.b = (SectionExpandableListView) inflate.findViewById(R.id.section_member_expand_list_view);
        this.g.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionMemberType.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
                if (marketRouterApi != null) {
                    marketRouterApi.router("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.vip?isImmerse&h5pro=true&pName=com.huawei.health&path=CancelService&urlType=4");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.b.setGroupIndicator(null);
        this.i.setHasFixedSize(true);
        this.i.setNestedScrollingEnabled(false);
        this.i.a(false);
        this.i.d(false);
        this.i.setLayoutManager(new HealthLinearLayoutManager(context, 0, false));
        this.i.addItemDecoration(new d(context.getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532), context.getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446)));
        SectionMemberTypeAdapter sectionMemberTypeAdapter = new SectionMemberTypeAdapter();
        this.e = sectionMemberTypeAdapter;
        this.i.setAdapter(sectionMemberTypeAdapter);
        return inflate;
    }

    private Drawable ajk_(Context context) {
        Drawable drawable = getResources().getDrawable(nsn.v(context) ? R.drawable._2131430302_res_0x7f0b0b9e : R.drawable._2131430303_res_0x7f0b0b9f);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        List<Product> d2 = nru.d(hashMap, "PRODUCT", Product.class, null);
        int d3 = nru.d((Map) hashMap, CommonConstant.RETKEY.STATUS, 0);
        boolean d4 = nru.d((Map) hashMap, "IS_SHOW_TOAST", false);
        this.j = d4;
        this.o.setVisibility(d4 ? 8 : 0);
        OnClickSectionListener a2 = nru.a(hashMap, "CLICK_EVENT_LISTENER", null);
        Object[] objArr = new Object[4];
        objArr[0] = "products: ";
        objArr[1] = Integer.valueOf(d2 != null ? d2.size() : 0);
        objArr[2] = " selectedIndex: ";
        objArr[3] = Integer.valueOf(d3);
        LogUtil.a("SectionMemberProduct", objArr);
        a(d2, d3, a2);
    }

    private void a(List<Product> list, int i, OnClickSectionListener onClickSectionListener) {
        LogUtil.a("SectionMemberProduct", "refreshLayout selectedIndex: ", Integer.valueOf(i));
        if (koq.b(list, i)) {
            return;
        }
        b();
        this.f = list.get(i);
        d(list, i);
        this.e.e(list);
        this.e.a(i);
        this.e.b(onClickSectionListener);
        this.e.notifyDataSetChanged();
        this.i.scrollToPosition(i);
        Handler outHandler = getKnitFragment() != null ? getKnitFragment().getOutHandler() : null;
        if (outHandler != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("PRODUCTS", new ArrayList<>(list));
            bundle.putInt("MEMBER_TYPE_SELECT_INDEX", i);
            outHandler.sendMessage(outHandler.obtainMessage(100, bundle));
        }
    }

    private void b() {
        if (this.o == null) {
            return;
        }
        UserMemberInfo a2 = gpn.a();
        this.o.setHeadTitleText(getResources().getString(a2 == null || a2.getMemberFlag() == -1 ? R$string.IDS_vip_open_vip : R$string.IDS_vip_renew_vip));
    }

    private void d(List<Product> list, int i) {
        Product product = list.get(i);
        LogUtil.a("SectionMemberProduct", "now product is ", product.toString());
        List<Product> giveaways = product.getGiveaways();
        if (koq.c(giveaways)) {
            this.g.setVisibility(8);
            this.l.setVisibility(8);
            this.b.setVisibility(0);
            ArrayList arrayList = new ArrayList();
            for (Product product2 : giveaways) {
                LogUtil.a("SectionMemberProduct", "now gift is ", product2.toString());
                eao eaoVar = new eao(product2.getProductUrl(), product2.getProductName(), product2.getProductDesc());
                arrayList.add(eaoVar);
                LogUtil.a("SectionMemberProduct", "now item is ", eaoVar.toString());
            }
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(arrayList);
            String c = c(product);
            SectionExpandableAdapter sectionExpandableAdapter = new SectionExpandableAdapter(this.d, arrayList2, c, R.drawable._2131427692_res_0x7f0b016c, R.drawable._2131427715_res_0x7f0b0183);
            this.f2700a = sectionExpandableAdapter;
            sectionExpandableAdapter.a(arrayList2, c, R.drawable._2131428279_res_0x7f0b03b7, R.drawable._2131428280_res_0x7f0b03b8);
            this.b.setAdapter(this.f2700a);
            this.b.expandGroup(0);
            return;
        }
        if (product == null || product.getPurchasePolicy() != Product.SUBSCRIPTION_PURCHASE_FLAG) {
            this.b.setVisibility(8);
            this.g.setVisibility(8);
            this.l.setVisibility(8);
            return;
        }
        this.b.setVisibility(8);
        this.g.setVisibility(0);
        this.l.setVisibility(0);
        String str = b(product) + " .";
        SpannableString spannableString = new SpannableString(str);
        Drawable ajk_ = ajk_(BaseApplication.e());
        ajk_.setBounds(0, 0, ajk_.getIntrinsicWidth(), ajk_.getIntrinsicHeight());
        spannableString.setSpan(new efg(ajk_), str.length() - 1, str.length(), 17);
        nsy.cMs_(this.l, spannableString, true);
    }

    private String c(Product product) {
        if (!TextUtils.isEmpty(product.getGiveawayDesc())) {
            return product.getGiveawayDesc();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(njn.e(product.getCurrency()));
        long j = 0;
        for (Product product2 : product.getGiveaways()) {
            if (product2 != null) {
                j += product2.getMicroPrice();
            }
        }
        sb.append(dpx.b(j, "#.##"));
        return BaseApplication.e().getString(R$string.IDS_member_giveaways_default_desc, sb.toString());
    }

    private String b(Product product) {
        char c;
        int i;
        String b;
        if (product.getPurchasePolicy() != Product.SUBSCRIPTION_PURCHASE_FLAG) {
            return "";
        }
        String duration = product.getDuration();
        String substring = (duration == null || TextUtils.isEmpty(duration)) ? "M" : duration.substring(duration.length() - 1);
        substring.hashCode();
        int hashCode = substring.hashCode();
        if (hashCode == 68) {
            if (substring.equals("D")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 87) {
            if (hashCode == 89 && substring.equals("Y")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (substring.equals("W")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            i = R$string.IDS_vip_auto_renewal_day;
        } else if (c == 1) {
            i = R$string.IDS_vip_auto_renewal_week;
        } else if (c == 2) {
            i = R$string.IDS_vip_auto_renewal_year;
        } else {
            i = R$string.IDS_vip_auto_renewal;
        }
        SubscriptionPromotion subscriptionPromotion = product.getSubscriptionPromotion();
        if (subscriptionPromotion == null || subscriptionPromotion.getDefaultRenewPrice() == product.getMicroPrice()) {
            b = dpx.b(product.getMicroPrice(), "#.##");
        } else {
            b = dpx.b(subscriptionPromotion.getDefaultRenewPrice(), "#.##");
        }
        if (LanguageUtil.ak(BaseApplication.e()) || LanguageUtil.bn(BaseApplication.e())) {
            return BaseApplication.e().getString(i, b);
        }
        return njn.a(product.getCurrency(), b, i);
    }

    static class d extends RecyclerView.ItemDecoration {

        /* renamed from: a, reason: collision with root package name */
        private int f2701a;
        private int d;

        public d(int i, int i2) {
            this.f2701a = i;
            this.d = i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            int i;
            int i2;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter == null) {
                return;
            }
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            int itemCount = adapter.getItemCount();
            if (itemCount == 1) {
                rect.left = this.d;
                rect.right = this.d;
                return;
            }
            if (childAdapterPosition == 0) {
                i = this.d;
                i2 = this.f2701a / 2;
            } else if (childAdapterPosition == itemCount - 1) {
                i = this.f2701a / 2;
                i2 = this.d;
            } else {
                i = this.f2701a / 2;
                i2 = i;
            }
            if (LanguageUtil.bc(BaseApplication.e())) {
                rect.left = i2;
                rect.right = i;
            } else {
                rect.left = i;
                rect.right = i2;
            }
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionMemberProduct";
    }
}
