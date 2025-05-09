package com.huawei.health.suggestion.ui.plan.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder;
import defpackage.eil;

/* loaded from: classes4.dex */
public class PlanResourceSlotViewHolder extends AbsFitnessViewHolder<Object> {
    private Integer c;
    private Context d;
    private LinearLayout e;

    public PlanResourceSlotViewHolder(View view) {
        super(view);
        this.c = -1;
        this.d = view.getContext();
        this.e = (LinearLayout) view.findViewById(R.id.plan_resource_slot);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    public void init(Object obj) {
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            int i = intValue > 0 ? intValue : -intValue;
            if ((intValue >= 0 || i != this.c.intValue()) && this.c.equals(Integer.valueOf(i))) {
                return;
            }
            this.e.removeAllViews();
            eil.alQ_(this.d, this.e, i);
            this.c = Integer.valueOf(i);
        }
    }
}
