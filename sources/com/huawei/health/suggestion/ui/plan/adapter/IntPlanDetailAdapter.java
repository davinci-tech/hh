package com.huawei.health.suggestion.ui.plan.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder;
import com.huawei.health.suggestion.ui.plan.viewholder.AiPlanH5ViewHolder;
import com.huawei.health.suggestion.ui.plan.viewholder.AiPlanWeekDetailViewHolder;
import com.huawei.health.suggestion.ui.plan.viewholder.IntAiDialogViewHolder;
import com.huawei.health.suggestion.ui.plan.viewholder.IntCurrentPlanViewHolder;
import com.huawei.health.suggestion.ui.plan.viewholder.IntPlanListViewHolder;
import com.huawei.health.suggestion.ui.plan.viewholder.IntPlanWeekDetailViewHolder;
import com.huawei.health.suggestion.ui.plan.viewholder.PlanResourceSlotViewHolder;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.fuo;
import defpackage.gdw;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class IntPlanDetailAdapter extends RecyclerView.Adapter<AbsFitnessViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private AiPlanH5ViewHolder f3251a;
    private FragmentManager b;
    private Context c;
    private AiPlanWeekDetailViewHolder d;
    private SparseArray<gdw> e = new SparseArray<>();
    private fuo f;
    private IntCurrentPlanViewHolder g;
    private final HealthRecycleView i;
    private LayoutInflater j;

    public IntPlanDetailAdapter(Context context, FragmentManager fragmentManager, HealthRecycleView healthRecycleView) {
        this.c = context;
        this.j = LayoutInflater.from(context);
        this.b = fragmentManager;
        this.i = healthRecycleView;
    }

    public IntPlanDetailAdapter(Context context, FragmentManager fragmentManager, HealthRecycleView healthRecycleView, fuo fuoVar) {
        this.c = context;
        this.j = LayoutInflater.from(context);
        this.b = fragmentManager;
        this.i = healthRecycleView;
        this.f = fuoVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onViewAttachedToWindow(AbsFitnessViewHolder absFitnessViewHolder) {
        super.onViewAttachedToWindow(absFitnessViewHolder);
        if (absFitnessViewHolder instanceof AiPlanWeekDetailViewHolder) {
            ((AiPlanWeekDetailViewHolder) absFitnessViewHolder).h();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aGQ_, reason: merged with bridge method [inline-methods] */
    public AbsFitnessViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                return new IntAiDialogViewHolder(this.j.inflate(R.layout.sug_layout_intplan_assistant_dialog, viewGroup, false));
            case 1:
                IntCurrentPlanViewHolder intCurrentPlanViewHolder = new IntCurrentPlanViewHolder(this.j.inflate(R.layout.sug_layout_intplan_progress, viewGroup, false), 0);
                this.g = intCurrentPlanViewHolder;
                return intCurrentPlanViewHolder;
            case 2:
                return new IntPlanWeekDetailViewHolder(this.j.inflate(R.layout.sug_layout_intplan_week_detail, viewGroup, false));
            case 3:
                return new IntPlanListViewHolder(this.j.inflate(R.layout.sug_layout_intplan_recommend_list_item, viewGroup, false), "plan");
            case 4:
                AiPlanWeekDetailViewHolder aiPlanWeekDetailViewHolder = new AiPlanWeekDetailViewHolder(this.j.inflate(R.layout.sug_layout_intplan_week_detail, viewGroup, false), this.b, this.i, this.f);
                this.d = aiPlanWeekDetailViewHolder;
                return aiPlanWeekDetailViewHolder;
            case 5:
                return new PlanResourceSlotViewHolder(this.j.inflate(R.layout.plan_resouce_slot, viewGroup, false));
            case 6:
            default:
                return null;
            case 7:
                return new AbsFitnessViewHolder(new View(this.c)) { // from class: com.huawei.health.suggestion.ui.plan.adapter.IntPlanDetailAdapter.3
                    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
                    /* renamed from: init */
                    public void a(Object obj) {
                    }
                };
            case 8:
                AiPlanH5ViewHolder aiPlanH5ViewHolder = new AiPlanH5ViewHolder(this.j.inflate(R.layout.sug_layout_intplan_h5, viewGroup, false));
                this.f3251a = aiPlanH5ViewHolder;
                return aiPlanH5ViewHolder;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(AbsFitnessViewHolder absFitnessViewHolder, int i) {
        LogUtil.a("Suggestion_PlanDetailAdapter", "onBindViewHolder position ", Integer.valueOf(i), " holder ", absFitnessViewHolder);
        if (this.e.size() == 0) {
            LogUtil.a("Suggestion_PlanDetailAdapter", "onBindViewHolder sparseArray size is 0");
            return;
        }
        gdw valueAt = this.e.valueAt(i);
        LogUtil.a("Suggestion_PlanDetailAdapter", "onBindViewHolder recycleItemData ", valueAt);
        if (valueAt == null) {
            LogUtil.h("Suggestion_PlanDetailAdapter", "onBindViewHolder recycleItemData is null");
            return;
        }
        if (absFitnessViewHolder instanceof IntPlanListViewHolder) {
            IntPlanListViewHolder intPlanListViewHolder = (IntPlanListViewHolder) absFitnessViewHolder;
            intPlanListViewHolder.a(valueAt.c() == 10);
            intPlanListViewHolder.b(valueAt.c() == 11);
        }
        absFitnessViewHolder.a(valueAt.b());
    }

    public void c(String str) {
        AiPlanH5ViewHolder aiPlanH5ViewHolder = this.f3251a;
        if (aiPlanH5ViewHolder == null) {
            LogUtil.h("Suggestion_PlanDetailAdapter", "refreshH5WebView mAiPlanH5ViewHolder is null sourceTag ", str);
        } else {
            aiPlanH5ViewHolder.d(str);
        }
    }

    public void e(gdw gdwVar) {
        if (gdwVar == null) {
            LogUtil.h("Suggestion_PlanDetailAdapter", "notifyNewItem itemData is NULL");
            return;
        }
        LogUtil.a("Suggestion_PlanDetailAdapter", "notifyNewItem itemData enter type:", Integer.valueOf(gdwVar.a()));
        int c = gdwVar.c();
        if (c == -1) {
            LogUtil.a("Suggestion_PlanDetailAdapter", "notifyNewItem valid value itemData=", Integer.valueOf(gdwVar.a()));
            d(gdwVar);
            return;
        }
        int indexOfKey = this.e.indexOfKey(c);
        if (indexOfKey < 0) {
            LogUtil.a("Suggestion_PlanDetailAdapter", "mDataArray not contains viewId:", Integer.valueOf(c));
            b(gdwVar);
            if (gdwVar.a() != 4 || this.d == null) {
                return;
            }
            LogUtil.h("Suggestion_PlanDetailAdapter", "add AiPlanWeekDetailViewHolder");
            this.d.b();
            return;
        }
        a(gdwVar, indexOfKey);
    }

    public void c(gdw gdwVar) {
        if (gdwVar == null) {
            LogUtil.h("Suggestion_PlanDetailAdapter", "notifyDeleteItem itemData is NULL");
            return;
        }
        LogUtil.a("Suggestion_PlanDetailAdapter", "notifyDeleteItem itemData enter type:", Integer.valueOf(gdwVar.a()));
        int c = gdwVar.c();
        if (c == -1) {
            LogUtil.a("Suggestion_PlanDetailAdapter", "notifyDeleteItem valid value itemData=", Integer.valueOf(gdwVar.a()));
            a(gdwVar);
            if (gdwVar.a() == 4 && this.d != null) {
                LogUtil.h("Suggestion_PlanDetailAdapter", "remove AiPlanWeekDetailViewHolder");
                this.d.j();
                this.d.a();
            }
            if (gdwVar.a() != 8 || PluginSuggestion.getInstance() == null || PluginSuggestion.getInstance().getH5ProWebView() == null) {
                return;
            }
            LogUtil.h("Suggestion_PlanDetailAdapter", "notifyDeleteItem remove AiPlanH5ViewHolder mRecyclerView ", this.i);
            PluginSuggestion.getInstance().getH5ProWebView().onDetachedFromWindow();
            PluginSuggestion.getInstance().setH5ProWebView(null);
            HealthRecycleView healthRecycleView = this.i;
            if (healthRecycleView != null) {
                healthRecycleView.removeAllViews();
                this.i.getRecycledViewPool().clear();
                return;
            }
            return;
        }
        int indexOfKey = this.e.indexOfKey(c);
        if (indexOfKey >= 0) {
            d(gdwVar, indexOfKey);
        }
    }

    private void b(gdw gdwVar) {
        int c = gdwVar.c();
        this.e.append(c, gdwVar);
        notifyItemInserted(this.e.indexOfKey(c));
    }

    private void d(gdw gdwVar) {
        for (int i = 0; i < this.e.size(); i++) {
            gdw valueAt = this.e.valueAt(i);
            if (valueAt == null) {
                LogUtil.h("Suggestion_PlanDetailAdapter", "sourceData is null");
            } else if (valueAt.a() == gdwVar.a()) {
                valueAt.b(gdwVar.b());
                notifyItemChanged(i);
            }
        }
    }

    private void a(gdw gdwVar) {
        LogUtil.a("Suggestion_PlanDetailAdapter", "deleteItemByViewType ", Integer.valueOf(gdwVar.a()));
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.e.size(); i++) {
            gdw valueAt = this.e.valueAt(i);
            if (valueAt == null) {
                LogUtil.h("Suggestion_PlanDetailAdapter", "sourceData is null");
            } else if (valueAt.a() == gdwVar.a()) {
                arrayList.add(Integer.valueOf(this.e.keyAt(i)));
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.e.remove(((Integer) it.next()).intValue());
        }
        notifyDataSetChanged();
    }

    private void a(gdw gdwVar, int i) {
        if (gdwVar.b() == null || i < 0) {
            LogUtil.h("Suggestion_PlanDetailAdapter", "updateSingleItem itemData is NULL or data is NULL");
        } else {
            this.e.valueAt(i).b(gdwVar.b());
            notifyItemChanged(i);
        }
    }

    private void d(gdw gdwVar, int i) {
        if (gdwVar == null || i < 0) {
            LogUtil.h("Suggestion_PlanDetailAdapter", "deleteSingleItem itemData is NULL or data is NULL");
        } else {
            this.e.removeAt(i);
            notifyItemRemoved(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        if (this.e.valueAt(i) == null) {
            return super.getItemId(i);
        }
        return this.e.valueAt(i).c();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        gdw valueAt = this.e.valueAt(i);
        if (valueAt == null) {
            LogUtil.h("Suggestion_PlanDetailAdapter", "getItemViewType null position:", Integer.valueOf(i));
            return -1;
        }
        return valueAt.a();
    }

    public View aGP_() {
        IntCurrentPlanViewHolder intCurrentPlanViewHolder = this.g;
        if (intCurrentPlanViewHolder == null) {
            LogUtil.h("Suggestion_PlanDetailAdapter", "getReportRedDotView mIntCurrentPlanViewHolder == null");
            return null;
        }
        return intCurrentPlanViewHolder.aJG_();
    }
}
