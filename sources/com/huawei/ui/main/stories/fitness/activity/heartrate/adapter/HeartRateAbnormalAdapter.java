package com.huawei.ui.main.stories.fitness.activity.heartrate.adapter;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.recycleview.RecyclerViewHolder;
import com.huawei.ui.main.stories.fitness.activity.heartrate.adapter.HeartRateAbnormalAdapter;
import defpackage.koq;
import defpackage.pru;
import defpackage.prw;
import java.util.List;

/* loaded from: classes6.dex */
public class HeartRateAbnormalAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<prw> b = null;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dsv_, reason: merged with bridge method [inline-methods] */
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RecyclerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_heart_rate_abnormal, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int i) {
        if (koq.b(this.b, i)) {
            LogUtil.h("HealthHeartRate_HeartRateAbnormalAdapter", "onBindViewHolder position invalid");
            return;
        }
        prw prwVar = this.b.get(i);
        if (prwVar == null) {
            LogUtil.h("HealthHeartRate_HeartRateAbnormalAdapter", "onBindViewHolder monthData is null");
            return;
        }
        HealthTextView healthTextView = (HealthTextView) recyclerViewHolder.cEO_(R.id.item_heart_rate_date);
        healthTextView.setText(DateUtils.formatDateTime(healthTextView.getContext(), prwVar.d(), 52));
        boolean c = prwVar.c();
        c(recyclerViewHolder, c);
        e(recyclerViewHolder, i);
        c(recyclerViewHolder, prwVar.a(), c);
    }

    private void e(final RecyclerViewHolder recyclerViewHolder, int i) {
        final LinearLayout linearLayout = (LinearLayout) recyclerViewHolder.cEO_(R.id.item_heart_rate_title);
        linearLayout.setTag(Integer.valueOf(i));
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: prs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HeartRateAbnormalAdapter.this.dsu_(linearLayout, recyclerViewHolder, view);
            }
        });
    }

    public /* synthetic */ void dsu_(LinearLayout linearLayout, RecyclerViewHolder recyclerViewHolder, View view) {
        int intValue = ((Integer) linearLayout.getTag()).intValue();
        if (koq.b(this.b, intValue)) {
            LogUtil.h("HealthHeartRate_HeartRateAbnormalAdapter", "onBindViewHolder mItemExpanded is OutOfBounds");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        prw prwVar = this.b.get(intValue);
        if (prwVar == null) {
            LogUtil.h("HealthHeartRate_HeartRateAbnormalAdapter", "onBindViewHolder setOnClickListener monthData is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        boolean c = prwVar.c();
        if (c) {
            recyclerViewHolder.cEO_(R.id.item_heart_rate_recycler).setVisibility(8);
        } else {
            c(recyclerViewHolder, prwVar.a(), true);
        }
        boolean z = !c;
        c(recyclerViewHolder, z);
        prwVar.c(z);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(RecyclerViewHolder recyclerViewHolder, List<pru> list, boolean z) {
        HealthRecycleView healthRecycleView = (HealthRecycleView) recyclerViewHolder.cEO_(R.id.item_heart_rate_recycler);
        healthRecycleView.setVisibility(z ? 0 : 8);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(healthRecycleView.getContext()));
        healthRecycleView.setIsScroll(false);
        healthRecycleView.setIsConsumption(true);
        HeartRateSubItemAdapter heartRateSubItemAdapter = new HeartRateSubItemAdapter();
        heartRateSubItemAdapter.b(list);
        healthRecycleView.setAdapter(heartRateSubItemAdapter);
    }

    private void c(RecyclerViewHolder recyclerViewHolder, boolean z) {
        ((ImageView) recyclerViewHolder.cEO_(R.id.item_heart_rate_arrow)).setImageResource(z ? R.drawable.ic_health_list_drop_down_arrow_sel : R.drawable.ic_health_list_drop_down_arrow_nor);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<prw> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void e(List<prw> list) {
        this.b = list;
        notifyDataSetChanged();
    }

    public void b(int i) {
        if (koq.d(this.b, i)) {
            this.b.get(i).c(true);
            notifyItemChanged(i);
        }
    }

    public boolean b() {
        if (koq.b(this.b)) {
            LogUtil.h("HealthHeartRate_HeartRateAbnormalAdapter", "getFirstSubItemCount mHistoryDataTree is null");
            return false;
        }
        prw prwVar = this.b.get(0);
        if (prwVar != null && !koq.b(prwVar.a())) {
            return prwVar.a().size() > 0;
        }
        LogUtil.h("HealthHeartRate_HeartRateAbnormalAdapter", "modelGroup is null");
        return false;
    }
}
