package com.huawei.health.tradeservice.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.tradeservice.activity.TradeOrderDetailActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.datatype.OrderBriefInfo;
import com.huawei.trade.datatype.ProductResourceInfo;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.gla;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class TradeOrderAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private List<OrderBriefInfo> f3467a;
    private int c;
    private Context d;

    public TradeOrderAdapter(Context context, List<OrderBriefInfo> list) {
        new ArrayList();
        this.d = context;
        this.f3467a = list;
    }

    public void b(List<OrderBriefInfo> list, int i) {
        this.f3467a = list;
        this.c = i;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aNS_, reason: merged with bridge method [inline-methods] */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate;
        if (nsn.p()) {
            inflate = LayoutInflater.from(this.d).inflate(R.layout.trade_order_item_large, viewGroup, false);
        } else {
            inflate = LayoutInflater.from(this.d).inflate(R.layout.trade_order_item, viewGroup, false);
        }
        return new ViewHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (koq.b(this.f3467a, i)) {
            LogUtil.h("TradeOrderAdapter", "mCurrentPlansList is null");
            return;
        }
        final OrderBriefInfo orderBriefInfo = this.f3467a.get(i);
        b(viewHolder, orderBriefInfo);
        if (i == this.f3467a.size() - 1) {
            viewHolder.b.setVisibility(8);
        }
        viewHolder.f3468a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.tradeservice.view.TradeOrderAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProductResourceInfo productInfo = orderBriefInfo.getProductInfo();
                Intent intent = new Intent(TradeOrderAdapter.this.d, (Class<?>) TradeOrderDetailActivity.class);
                intent.setFlags(268435456);
                intent.putExtra("orderCode", orderBriefInfo.getOrderCode());
                intent.putExtra("productId", productInfo.getProductId());
                if (TradeOrderAdapter.this.c == 1) {
                    intent.putExtra("invoice", true);
                }
                TradeOrderAdapter.this.d.startActivity(intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void b(ViewHolder viewHolder, OrderBriefInfo orderBriefInfo) {
        viewHolder.f.setText(String.format(this.d.getString(R.string._2130844798_res_0x7f021c7e), 1));
        ProductResourceInfo productInfo = orderBriefInfo.getProductInfo();
        viewHolder.e.setText(productInfo.getProductName());
        nrf.cIU_(productInfo.getProductUrl(), viewHolder.g, (int) BaseApplication.e().getResources().getDimension(R.dimen._2131362360_res_0x7f0a0238));
        if (orderBriefInfo.getPrefMode() != 2 && orderBriefInfo.getPrefMode() != 4) {
            if (orderBriefInfo.getPrefMode() != 5) {
                viewHolder.i.setText(gla.a(orderBriefInfo.getCurrency(), orderBriefInfo.getPayPrice() / 1000000.0f));
            } else {
                viewHolder.i.setText(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130845277_res_0x7f021e5d));
            }
        } else {
            viewHolder.i.setText(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getString(R.string._2130845204_res_0x7f021e14));
        }
        LogUtil.a("TradeOrderAdapter", "orderBriefInfo.getGiveawaysExist = ", Integer.valueOf(orderBriefInfo.getGiveawaysExist()));
        if (orderBriefInfo.getGiveawaysExist() != 1 || !c(orderBriefInfo.getGiveawaysInfo())) {
            viewHolder.d.setVisibility(8);
        } else {
            viewHolder.d.setVisibility(0);
        }
    }

    private boolean c(List<ProductResourceInfo> list) {
        if (list == null || list.size() < 1) {
            return false;
        }
        Iterator<ProductResourceInfo> it = list.iterator();
        while (it.hasNext()) {
            if (!it.next().getShowGiveawayFlag()) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f3467a.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private LinearLayout f3468a;
        private HealthDivider b;
        private HealthTextView d;
        private HealthTextView e;
        private HealthTextView f;
        private ImageView g;
        private HealthTextView i;

        ViewHolder(View view) {
            super(view);
            this.i = (HealthTextView) view.findViewById(R.id.tv_show_price);
            this.e = (HealthTextView) view.findViewById(R.id.tv_class_name);
            this.g = (ImageView) view.findViewById(R.id.img_goods);
            this.b = (HealthDivider) view.findViewById(R.id.divider);
            this.d = (HealthTextView) view.findViewById(R.id.tv_hava_giveaways);
            this.f3468a = (LinearLayout) view.findViewById(R.id.lin_content);
            this.f = (HealthTextView) view.findViewById(R.id.tv_show_quantity);
        }
    }
}
