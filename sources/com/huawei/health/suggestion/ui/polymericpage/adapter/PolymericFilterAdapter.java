package com.huawei.health.suggestion.ui.polymericpage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.model.FilterCondition;
import com.huawei.basefitnessadvice.model.FilterOption;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.spinner.HealthSpinner;
import com.huawei.ui.commonui.utils.ScrollUtil;
import defpackage.fpg;
import defpackage.gbw;
import defpackage.koq;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class PolymericFilterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private OnItemClick f3307a;
    private List<gbw> d;
    private Context e;

    public interface OnItemClick {
        void onMoreItemClickListener(int i, List<FilterCondition> list);

        void onSpinnerOptionSelectedListener(FilterCondition filterCondition);
    }

    public PolymericFilterAdapter(Context context, List<gbw> list, OnItemClick onItemClick) {
        if (context == null || koq.b(list) || onItemClick == null) {
            LogUtil.h("Suggestion_AggregateFilterAdapter", "initView() onClick() view click too fast.");
        }
        this.e = context;
        this.d = list;
        this.f3307a = onItemClick;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LogUtil.a("Suggestion_AggregateFilterAdapter", "onCreateViewHolder.", Integer.valueOf(i));
        if (i == 0) {
            return new e(LayoutInflater.from(this.e).inflate(R.layout.item_sport_all_course_filter_spinner, viewGroup, false));
        }
        if (i == 1) {
            return new c(LayoutInflater.from(this.e).inflate(R.layout.item_sport_all_course_filter_more, viewGroup, false));
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        LogUtil.a("Suggestion_AggregateFilterAdapter", "onBindViewHolder.", Integer.valueOf(viewHolder.getItemViewType()), " position:", Integer.valueOf(i));
        if (viewHolder.getItemViewType() == 0) {
            e eVar = (e) viewHolder;
            aJR_(eVar.d);
            d(eVar.f3310a, i, fpg.e(a(this.d.get(i).d())));
        }
        if (viewHolder.getItemViewType() == 1) {
            c cVar = (c) viewHolder;
            aJR_(cVar.d);
            cVar.f3309a.setText(this.d.get(i).b());
            cVar.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericFilterAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PolymericFilterAdapter.this.f3307a.onMoreItemClickListener(i, ((gbw) PolymericFilterAdapter.this.d.get(i)).c());
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    public void a() {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        this.d.clear();
    }

    public void d(List<gbw> list) {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        this.d.addAll(list);
        notifyDataSetChanged();
    }

    private void aJR_(LinearLayout linearLayout) {
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        int c2 = ScrollUtil.c(this.e);
        int c3 = nsn.c(this.e, 85.0f);
        layoutParams.width = (c2 - c3) / this.d.size();
        LogUtil.a("Suggestion_AggregateFilterAdapter", "setItemLayoutParams:", Integer.valueOf(c2), " margin:", Integer.valueOf(c3), " width:", Integer.valueOf(layoutParams.width));
        linearLayout.setLayoutParams(layoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        int e2 = this.d.get(i).e();
        if (e2 == 0) {
            return 0;
        }
        return e2 == 1 ? 1 : -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.d.size();
    }

    private void d(final HealthSpinner healthSpinner, final int i, String[] strArr) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.e, R.layout.fitness_recommed_spinner_com_item, strArr);
        arrayAdapter.setDropDownViewResource(R.layout.hwspinner_dropdown_item);
        healthSpinner.setDropDownWidth(-2);
        healthSpinner.setDropDownHorizontalOffset(nsn.c(this.e.getApplicationContext(), 14.0f));
        healthSpinner.setDropDownVerticalOffset(-nsn.c(this.e.getApplicationContext(), 14.0f));
        healthSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        healthSpinner.setTag(false);
        healthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericFilterAdapter.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
                LogUtil.a("Suggestion_AggregateFilterAdapter", "onItemSelected position:", Integer.valueOf(i2), " id:", Long.valueOf(j));
                if (PolymericFilterAdapter.this.b(healthSpinner)) {
                    FilterCondition d = ((gbw) PolymericFilterAdapter.this.d.get(i)).d();
                    if (d == null) {
                        LogUtil.b("Suggestion_AggregateFilterAdapter", "indexFilterCondition == null");
                        ViewClickInstrumentation.clickOnListView(adapterView, view, i2);
                        return;
                    }
                    FilterOption filterOption = d.getFilterOptions().get(i2);
                    if (filterOption == null) {
                        LogUtil.b("Suggestion_AggregateFilterAdapter", "indexOption == null");
                        ViewClickInstrumentation.clickOnListView(adapterView, view, i2);
                        return;
                    }
                    LogUtil.a("Suggestion_AggregateFilterAdapter", "condition:", d.getName(), " ", Integer.valueOf(d.getCategoryId()), " option:", filterOption.getName(), " ", Integer.valueOf(filterOption.getCategoryId()));
                    ArrayList arrayList = new ArrayList(10);
                    if (filterOption.getCategoryId() != -1) {
                        arrayList.add(filterOption);
                    }
                    PolymericFilterAdapter.this.f3307a.onSpinnerOptionSelectedListener(new FilterCondition.Builder().name(d.getName()).value(d.getValue()).type(d.getType()).categoryId(d.getCategoryId()).filterOptions(arrayList).build());
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i2);
                    return;
                }
                LogUtil.a("Suggestion_AggregateFilterAdapter", "spinner init.index:", Integer.valueOf(i), " pos:", Integer.valueOf(i2), " id:", Long.valueOf(j));
                healthSpinner.setTag(true);
                ViewClickInstrumentation.clickOnListView(adapterView, view, i2);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                LogUtil.a("Suggestion_AggregateFilterAdapter", "onNothingSelected.");
            }
        });
    }

    private List<Attribute> a(FilterCondition filterCondition) {
        ArrayList arrayList = new ArrayList(10);
        if (filterCondition.getFilterOptions() == null) {
            return arrayList;
        }
        for (FilterOption filterOption : filterCondition.getFilterOptions()) {
            Attribute attribute = new Attribute();
            attribute.setName(filterOption.getName());
            attribute.setId(Long.toString(filterOption.getValue()));
            arrayList.add(attribute);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(HealthSpinner healthSpinner) {
        if (healthSpinner.getTag() instanceof Boolean) {
            return ((Boolean) healthSpinner.getTag()).booleanValue();
        }
        return false;
    }

    static class e extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthSpinner f3310a;
        LinearLayout d;

        e(View view) {
            super(view);
            this.d = (LinearLayout) view.findViewById(R.id.sug_course_filter_spinner_item);
            this.f3310a = (HealthSpinner) view.findViewById(R.id.sug_filter_spinner);
        }
    }

    static class c extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f3309a;
        LinearLayout d;

        c(View view) {
            super(view);
            this.d = (LinearLayout) view.findViewById(R.id.sug_course_filter_more_item);
            this.f3309a = (HealthTextView) view.findViewById(R.id.sug_fitness_more_filter);
        }
    }
}
