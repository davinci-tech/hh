package com.huawei.pluginachievement.ui.kakatask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.pluginachievement.manager.model.GiftInfo;
import com.huawei.pluginachievement.ui.views.KakaToGiftView;
import defpackage.koq;
import java.util.List;

/* loaded from: classes9.dex */
public class AchieveKakaGiftAdapter extends RecyclerView.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f8443a;
    private List<GiftInfo> d;
    private OnItemClickListener e;

    public interface OnItemClickListener {
        void onItemClick(View view, String str, int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new GiftHolder(LayoutInflater.from(this.f8443a).inflate(R.layout.achieve_kk_gift_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        if (list.isEmpty()) {
            LogUtil.a("PLGACHIEVE_AchieveKakaGiftAdapter", "onBindViewHolder payloads isEmpty!");
            onBindViewHolder(viewHolder, i);
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaGiftAdapter", "onBindViewHolder item position ", Integer.valueOf(i));
        if ("refreshTime".equals(list.get(0)) && (viewHolder instanceof GiftHolder)) {
            ((GiftHolder) viewHolder).d.d();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        LogUtil.a("PLGACHIEVE_AchieveKakaGiftAdapter", "onBindViewHolder position ", Integer.valueOf(i));
        if (koq.d(this.d, i) && (viewHolder instanceof GiftHolder)) {
            viewHolder.setIsRecyclable(false);
            GiftHolder giftHolder = (GiftHolder) viewHolder;
            giftHolder.d.setGiftInfo(this.d.get(i));
            giftHolder.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.kakatask.AchieveKakaGiftAdapter.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AchieveKakaGiftAdapter.this.e.onItemClick(view, JsbMapKeyNames.H5_TEXT_DETAIL, i);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            giftHolder.d.setExchangeBtnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.kakatask.AchieveKakaGiftAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AchieveKakaGiftAdapter.this.e.onItemClick(view, "redeem", i);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.d)) {
            return 0;
        }
        return this.d.size();
    }

    public static class GiftHolder extends RecyclerView.ViewHolder {
        KakaToGiftView d;

        public GiftHolder(View view) {
            super(view);
            this.d = (KakaToGiftView) view.findViewById(R.id.exchange_gift);
        }
    }
}
