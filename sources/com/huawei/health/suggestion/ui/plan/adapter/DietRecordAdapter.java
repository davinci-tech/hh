package com.huawei.health.suggestion.ui.plan.adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ffy;
import defpackage.ful;
import defpackage.fuq;
import defpackage.koq;
import defpackage.moe;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class DietRecordAdapter extends RecyclerView.Adapter<d> {

    /* renamed from: a, reason: collision with root package name */
    private List<fuq> f3247a;
    private Context d;

    public DietRecordAdapter(Context context, List<fuq> list) {
        this.d = context;
        this.f3247a = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aGL_, reason: merged with bridge method [inline-methods] */
    public d onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new d(LayoutInflater.from(this.d).inflate(R.layout.item_diet_record, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(d dVar, int i) {
        if (koq.b(this.f3247a)) {
            LogUtil.h("Suggestion_DietRecordAdapter", "onBindViewHolder() data is null");
            return;
        }
        if (koq.b(this.f3247a, i)) {
            LogUtil.h("Suggestion_DietRecordAdapter", "onBindViewHolder() outOfBounds, position = ", Integer.valueOf(i));
            return;
        }
        fuq fuqVar = this.f3247a.get(i);
        c(dVar.b, fuqVar.d());
        LogUtil.a("Suggestion_DietRecordAdapter", "mData.getTimeStamp: ", Long.valueOf(fuqVar.e()));
        if (!DateUtils.isToday(fuqVar.e())) {
            LogUtil.a("Suggestion_DietRecordAdapter", "is not today");
            dVar.f3248a.setVisibility(8);
        } else if (fuqVar.a() < 0.0f || fuqVar.c() <= 0.0f) {
            dVar.f3248a.setVisibility(8);
        } else {
            dVar.f3248a.setVisibility(0);
            dVar.f3248a.setText(this.d.getResources().getString(R.string._2130848759_res_0x7f022bf7, UnitUtil.e(Math.ceil(fuqVar.a()), 1, 0), ffy.b(R.plurals._2130903474_res_0x7f0301b2, (int) Math.ceil(fuqVar.c()), UnitUtil.e(Math.ceil(fuqVar.c()), 1, 0))));
        }
        dVar.e.setText(ffy.b(R.plurals._2130903474_res_0x7f0301b2, (int) fuqVar.g(), moe.i(fuqVar.g())));
        if (!DateUtils.isToday(fuqVar.e()) || fuqVar.c() <= 0.0f || fuqVar.g() <= fuqVar.c()) {
            dVar.e.setTextColor(this.d.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        } else {
            dVar.e.setTextColor(this.d.getResources().getColor(R.color._2131297067_res_0x7f09032b));
        }
        List<ful> b = fuqVar.b();
        if (koq.c(b)) {
            DietRecordItemAdapter dietRecordItemAdapter = new DietRecordItemAdapter(this.d, b);
            dVar.d.setLayoutManager(new LinearLayoutManager(this.d));
            dVar.d.setAdapter(dietRecordItemAdapter);
        }
    }

    private void c(HealthTextView healthTextView, int i) {
        if (i == 10) {
            healthTextView.setText(R.string._2130848749_res_0x7f022bed);
            return;
        }
        if (i == 11) {
            healthTextView.setText(R.string._2130848756_res_0x7f022bf4);
            return;
        }
        if (i == 20) {
            healthTextView.setText(R.string._2130848750_res_0x7f022bee);
            return;
        }
        if (i == 21) {
            healthTextView.setText(R.string._2130848757_res_0x7f022bf5);
            return;
        }
        if (i == 30) {
            healthTextView.setText(R.string._2130848751_res_0x7f022bef);
        } else if (i == 31) {
            healthTextView.setText(R.string._2130848758_res_0x7f022bf6);
        } else {
            LogUtil.h("Suggestion_DietRecordAdapter", "displayTitle() wrong mealType: ", Integer.valueOf(i));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<fuq> list = this.f3247a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    static class d extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f3248a;
        private HealthTextView b;
        private RecyclerView d;
        private HealthTextView e;

        public d(View view) {
            super(view);
            this.b = (HealthTextView) view.findViewById(R.id.diet_record_item_title);
            this.f3248a = (HealthTextView) view.findViewById(R.id.diet_record_item_recommend_calories);
            this.e = (HealthTextView) view.findViewById(R.id.diet_record_item_total_calories);
            this.d = (RecyclerView) view.findViewById(R.id.diet_record_item_recycler_view);
        }
    }
}
