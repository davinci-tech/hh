package com.huawei.health.suggestion.ui.plan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ffy;
import defpackage.ful;
import defpackage.koq;
import defpackage.moe;
import defpackage.nrf;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class DietRecordItemAdapter extends RecyclerView.Adapter<c> {

    /* renamed from: a, reason: collision with root package name */
    private List<ful> f3249a;
    private Context c;

    public DietRecordItemAdapter(Context context, List<ful> list) {
        this.c = context;
        this.f3249a = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aGM_, reason: merged with bridge method [inline-methods] */
    public c onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new c(LayoutInflater.from(this.c).inflate(R.layout.item_diet_record_summary, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(c cVar, int i) {
        if (koq.b(this.f3249a)) {
            LogUtil.h("Suggestion_DietRecordItemAdapter", "onBindViewHolder() data is null");
            return;
        }
        if (koq.b(this.f3249a, i)) {
            LogUtil.h("Suggestion_DietRecordItemAdapter", "onBindViewHolder() outOfBounds position: ", Integer.valueOf(i));
            return;
        }
        ful fulVar = this.f3249a.get(i);
        cVar.j.setText(fulVar.a());
        if (fulVar.e() >= 75.0f) {
            cVar.c.setVisibility(0);
        } else {
            cVar.c.setVisibility(8);
        }
        cVar.b.setText(ffy.b(R.plurals._2130903474_res_0x7f0301b2, (int) fulVar.b(), moe.i(fulVar.b())));
        cVar.f3250a.setText(UnitUtil.e(fulVar.c(), 1, 0));
        cVar.f.setText(fulVar.i());
        nrf.cIS_(cVar.e, fulVar.g(), nrf.d, 0, R.drawable._2131428037_res_0x7f0b02c5);
        if (i == this.f3249a.size() - 1) {
            cVar.d.setVisibility(4);
        } else {
            cVar.d.setVisibility(0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ful> list = this.f3249a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    static class c extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f3250a;
        private HealthTextView b;
        private HealthTextView c;
        private HealthDivider d;
        private ImageView e;
        private HealthTextView f;
        private HealthTextView j;

        public c(View view) {
            super(view);
            this.e = (ImageView) view.findViewById(R.id.diet_record_item_icon);
            this.j = (HealthTextView) view.findViewById(R.id.diet_record_item_name);
            this.f3250a = (HealthTextView) view.findViewById(R.id.diet_record_item_count);
            this.f = (HealthTextView) view.findViewById(R.id.diet_record_item_unit);
            this.c = (HealthTextView) view.findViewById(R.id.diet_record_item_high_gi);
            this.b = (HealthTextView) view.findViewById(R.id.diet_record_item_calories);
            this.d = (HealthDivider) view.findViewById(R.id.diet_record_item_divider);
        }
    }
}
