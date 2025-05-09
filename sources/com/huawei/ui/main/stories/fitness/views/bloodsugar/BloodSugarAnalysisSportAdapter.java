package com.huawei.ui.main.stories.fitness.views.bloodsugar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import defpackage.nsn;
import defpackage.pzz;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class BloodSugarAnalysisSportAdapter extends RecyclerView.Adapter<BloodSugarAnalysisSportHolder> {
    private List<pzz> e = new ArrayList();

    public void b(List<pzz> list) {
        if (koq.b(list)) {
            LogUtil.h("BloodSugarAnalysisSportAdapter", " setData list is null!");
            return;
        }
        this.e.clear();
        this.e.addAll(list);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dvJ_, reason: merged with bridge method [inline-methods] */
    public BloodSugarAnalysisSportHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate;
        if (nsn.s()) {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.health_data_blood_sugar_analysis_sport_large, viewGroup, false);
        } else {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.health_data_blood_sugar_analysis_sport_item, viewGroup, false);
        }
        return new BloodSugarAnalysisSportHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(BloodSugarAnalysisSportHolder bloodSugarAnalysisSportHolder, int i) {
        if (koq.b(this.e)) {
            LogUtil.h("BloodSugarAnalysisSportAdapter", " mDataList is null!");
        } else {
            if (i < 0 || this.e.size() <= i) {
                return;
            }
            bloodSugarAnalysisSportHolder.c(this.e.get(i));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e.size();
    }
}
