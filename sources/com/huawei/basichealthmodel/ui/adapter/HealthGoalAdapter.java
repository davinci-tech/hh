package com.huawei.basichealthmodel.ui.adapter;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.huawei.basichealthmodel.ui.adapter.HealthGoalAdapter;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import defpackage.aya;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class HealthGoalAdapter extends BaseRecyclerAdapter<aya> {

    /* renamed from: a, reason: collision with root package name */
    private int f1915a;
    private List<aya> c;
    private final HealthPlanListener d;

    public interface HealthPlanListener {
        void onPlanClick(aya ayaVar);
    }

    public HealthGoalAdapter(HealthPlanListener healthPlanListener) {
        super(new ArrayList(), R.layout.item_card_goal);
        this.f1915a = -1;
        this.d = healthPlanListener;
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, aya ayaVar) {
        if (koq.b(this.c, i)) {
            LogUtil.h("HealthLife_HealthGoalAdapter", "mList is out of bounds");
            return;
        }
        if (ayaVar == null) {
            LogUtil.h("HealthLife_HealthGoalAdapter", "bean is null");
            return;
        }
        recyclerHolder.b(R.id.item_plan_title, ayaVar.e());
        recyclerHolder.b(R.id.item_plan_description, ayaVar.b());
        ((HealthRadioButton) recyclerHolder.cwK_(R.id.item_plan_radio)).setChecked(ayaVar.c() == 1);
        final ConstraintLayout constraintLayout = (ConstraintLayout) recyclerHolder.cwK_(R.id.item_plan_layout);
        constraintLayout.setTag(Integer.valueOf(i));
        constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: axx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HealthGoalAdapter.this.kS_(constraintLayout, view);
            }
        });
    }

    public /* synthetic */ void kS_(ConstraintLayout constraintLayout, View view) {
        int intValue = ((Integer) constraintLayout.getTag()).intValue();
        if (koq.d(this.c, intValue)) {
            aya ayaVar = this.c.get(intValue);
            if (ayaVar.c() == 1) {
                ayaVar.b(0);
            } else {
                ayaVar.b(1);
            }
            notifyItemChanged(intValue);
            int i = this.f1915a;
            if (i != -1 && i != intValue) {
                aya ayaVar2 = this.c.get(i);
                if (ayaVar.c() == 1) {
                    ayaVar2.b(0);
                } else {
                    ayaVar2.b(1);
                }
                notifyItemChanged(this.f1915a);
            }
            this.f1915a = intValue;
            HealthPlanListener healthPlanListener = this.d;
            if (healthPlanListener != null) {
                healthPlanListener.onPlanClick(ayaVar);
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<aya> list = this.c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void c(List<aya> list) {
        this.c = list;
        refreshDataChange(list);
    }
}
