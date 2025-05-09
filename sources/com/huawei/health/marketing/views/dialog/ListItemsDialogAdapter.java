package com.huawei.health.marketing.views.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.marketing.views.dialog.ListItemsDialogAdapter;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.eli;
import defpackage.koq;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class ListItemsDialogAdapter extends RecyclerView.Adapter<ItemHolder> {
    private final Context c;
    private final List<eli.d> e = new ArrayList();

    public ListItemsDialogAdapter(Context context) {
        this.c = context;
    }

    public void d(List<eli.d> list) {
        this.e.clear();
        if (koq.c(list)) {
            this.e.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aqL_, reason: merged with bridge method [inline-methods] */
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemHolder(LayoutInflater.from(this.c).inflate(R.layout.list_itmes_dialog_item_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(ItemHolder itemHolder, int i) {
        itemHolder.c(this.e.get(i), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        ImageView d;

        public ItemHolder(View view) {
            super(view);
            this.d = (ImageView) view.findViewById(R.id.background);
        }

        public void c(final eli.d dVar, final int i) {
            this.d.setImageDrawable(dVar.aqJ_());
            if (dVar.e() != null) {
                this.itemView.setOnClickListener(new View.OnClickListener() { // from class: elp
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ListItemsDialogAdapter.ItemHolder.aqM_(eli.d.this, i, view);
                    }
                });
            }
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) this.itemView.getLayoutParams();
            if (i < ListItemsDialogAdapter.this.getItemCount() - 1) {
                layoutParams.bottomMargin = this.itemView.getContext().getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
            }
            if (i == ListItemsDialogAdapter.this.getItemCount() - 1) {
                layoutParams.bottomMargin = this.itemView.getContext().getResources().getDimensionPixelSize(R.dimen._2131362954_res_0x7f0a048a);
            }
        }

        public static /* synthetic */ void aqM_(final eli.d dVar, final int i, View view) {
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: elm
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    ListItemsDialogAdapter.ItemHolder.e(eli.d.this, i, i2, obj);
                }
            }, AnalyticsValue.MARKETING_RESOURCE.value());
            ViewClickInstrumentation.clickOnView(view);
        }

        public static /* synthetic */ void e(eli.d dVar, int i, int i2, Object obj) {
            if (i2 == 0) {
                MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
                if (marketRouterApi != null) {
                    marketRouterApi.router(dVar.e());
                    return;
                }
                return;
            }
            LogUtil.h("ListItemsDialogAdapter", "getViewClickListener errorCode = ", Integer.valueOf(i2), ", item positionId = ", Integer.valueOf(i));
        }
    }
}
