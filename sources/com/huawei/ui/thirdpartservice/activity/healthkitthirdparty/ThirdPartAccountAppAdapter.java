package com.huawei.ui.thirdpartservice.activity.healthkitthirdparty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.health.R;
import com.huawei.health.main.model.AppLangItem;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.thirdpartservice.activity.healthkitthirdparty.ThirdPartAccountAppAdapter;
import defpackage.koq;
import defpackage.nrf;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes8.dex */
public class ThirdPartAccountAppAdapter extends RecyclerView.Adapter<AppViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private Context f10544a;
    private ItemClickListener b = null;
    private List<AppLangItem> d;

    public interface ItemClickListener {
        void onItemClick(View view, int i);
    }

    public ThirdPartAccountAppAdapter(List<AppLangItem> list) {
        this.d = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dWT_, reason: merged with bridge method [inline-methods] */
    public AppViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_third_part_account_app, viewGroup, false);
        this.f10544a = viewGroup.getContext();
        return new AppViewHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(final AppViewHolder appViewHolder, final int i) {
        if (koq.b(this.d, i)) {
            LogUtil.c("ThirdPartAccountAppAdapter", "onBindViewHolder isOutOfBounds");
            return;
        }
        AppLangItem appLangItem = this.d.get(i);
        if (appLangItem != null && appLangItem.getAppName() != null) {
            appViewHolder.d.setText(appLangItem.getAppName());
            nrf.cIv_(appLangItem.getAppIconPath(), RequestOptions.bitmapTransform(new RoundedCorners((int) this.f10544a.getResources().getDimension(R.dimen._2131362360_res_0x7f0a0238))), appViewHolder.e);
        }
        if (LanguageUtil.bc(this.f10544a)) {
            appViewHolder.b.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        if (i == this.d.size() - 1) {
            appViewHolder.f10545a.setVisibility(8);
        }
        appViewHolder.c.setOnClickListener(new View.OnClickListener() { // from class: sfi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThirdPartAccountAppAdapter.this.dWS_(appViewHolder, i, view);
            }
        });
    }

    public /* synthetic */ void dWS_(AppViewHolder appViewHolder, int i, View view) {
        ItemClickListener itemClickListener = this.b;
        if (itemClickListener != null) {
            itemClickListener.onItemClick(appViewHolder.e, i);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<AppLangItem> list = this.d;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static class AppViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthDivider f10545a;
        private ImageView b;
        private LinearLayout c;
        private HealthTextView d;
        private ImageView e;

        public AppViewHolder(View view) {
            super(view);
            this.c = (LinearLayout) view.findViewById(R.id.ll_third_part_account_item);
            this.e = (ImageView) view.findViewById(R.id.iv_account_app_icon);
            this.d = (HealthTextView) view.findViewById(R.id.htv_account_app_name);
            this.b = (ImageView) view.findViewById(R.id.account_app_arrow_gray);
            this.f10545a = (HealthDivider) view.findViewById(R.id.third_part_account_app_divider);
        }
    }

    public void a(ItemClickListener itemClickListener) {
        this.b = itemClickListener;
    }
}
