package com.huawei.ui.main.stories.fitness.activity.heartrate.adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.fitness.activity.heartrate.HeartRateWarningDetailsActivity;
import com.huawei.ui.main.stories.fitness.activity.heartrate.adapter.HeartRateSubItemAdapter;
import com.huawei.ui.main.stories.fitness.activity.heartrate.helper.HeartRateDetailData;
import defpackage.koq;
import defpackage.nsj;
import defpackage.pru;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class HeartRateSubItemAdapter extends RecyclerView.Adapter<RecyclerHolder> {
    private List<pru> d;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dsx_, reason: merged with bridge method [inline-methods] */
    public RecyclerHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RecyclerHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_heart_rate_abnormal_sub, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(RecyclerHolder recyclerHolder, int i) {
        if (koq.b(this.d, i)) {
            LogUtil.h("HealthHeartRate_HeartRateSubItemAdapter", "mDayDataList is empty");
            return;
        }
        pru pruVar = this.d.get(i);
        if (pruVar == null) {
            LogUtil.h("HealthHeartRate_HeartRateSubItemAdapter", "dayData is null");
            return;
        }
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.item_heart_rate_sub_count);
        healthTextView.setText(pruVar.d());
        final Context context = healthTextView.getContext();
        long e = pruVar.e();
        long b = pruVar.b();
        String formatDateTime = DateUtils.formatDateTime(context, e, 131092);
        String e2 = nsj.e(context, e, b, 1);
        ((HealthTextView) recyclerHolder.cwK_(R.id.item_heart_rate_sub_date)).setText(formatDateTime);
        ((HealthTextView) recyclerHolder.cwK_(R.id.item_heart_rate_sub_time)).setText(e2);
        ImageView imageView = (ImageView) recyclerHolder.cwK_(R.id.item_heart_rate_sub_arrow);
        final ArrayList<HeartRateDetailData> c = pruVar.c();
        if (koq.b(c)) {
            imageView.setVisibility(8);
        }
        imageView.setBackgroundResource(LanguageUtil.bc(context) ? R.drawable._2131427841_res_0x7f0b0201 : R.drawable._2131427842_res_0x7f0b0202);
        recyclerHolder.cwK_(R.id.item_heart_rate_sub_layout).setOnClickListener(new View.OnClickListener() { // from class: prv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HeartRateSubItemAdapter.dsw_(c, context, view);
            }
        });
        if (i == getItemCount() - 1) {
            recyclerHolder.cwK_(R.id.item_heart_rate_sub_line).setVisibility(8);
        }
    }

    public static /* synthetic */ void dsw_(ArrayList arrayList, Context context, View view) {
        if (koq.b(arrayList)) {
            LogUtil.h("HealthHeartRate_HeartRateSubItemAdapter", "detailListData is empty");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            HeartRateWarningDetailsActivity.a(context, arrayList);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<pru> list = this.d;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void b(List<pru> list) {
        this.d = list;
        notifyDataSetChanged();
    }
}
