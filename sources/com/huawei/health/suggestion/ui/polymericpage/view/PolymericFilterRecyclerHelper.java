package com.huawei.health.suggestion.ui.polymericpage.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.basefitnessadvice.model.FilterCondition;
import com.huawei.basefitnessadvice.model.FilterOption;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.polymericpage.activity.PolymericMoreFilterActivity;
import com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericFilterAdapter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.gbw;
import defpackage.koq;
import defpackage.moj;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class PolymericFilterRecyclerHelper {

    /* renamed from: a, reason: collision with root package name */
    private Context f3315a;
    private List<FilterCondition> b;
    private HealthRecycleView c;
    private OnCourseFilterListener d;
    private LinearLayout e;
    private PolymericFilterAdapter i;
    private List<FilterCondition> j = new ArrayList(10);
    private List<FilterCondition> g = new ArrayList(10);
    private Map<Long, List<Long>> h = new HashMap();

    public interface OnCourseFilterListener {
        void onToFilterCallBack(List<FilterCondition> list);
    }

    public PolymericFilterRecyclerHelper(Context context, LinearLayout linearLayout) {
        if (context == null || linearLayout == null) {
            LogUtil.h("Suggestion_AggregateFilterRecyclerHelper", "CourseFilterRecyclerViewHelper() context or filterView is null");
            return;
        }
        this.f3315a = context;
        this.e = linearLayout;
        d();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void d() {
        LogUtil.a("Suggestion_AggregateFilterRecyclerHelper", "initView.");
        this.c = (HealthRecycleView) this.e.findViewById(R.id.recycleview_filter);
        this.c.setLayoutManager(new LinearLayoutManager(this.f3315a, 0, 0 == true ? 1 : 0) { // from class: com.huawei.health.suggestion.ui.polymericpage.view.PolymericFilterRecyclerHelper.3
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollHorizontally() {
                return false;
            }
        });
        this.c.setIsScroll(false);
        PolymericFilterAdapter polymericFilterAdapter = new PolymericFilterAdapter(this.f3315a, new ArrayList(), new PolymericFilterAdapter.OnItemClick() { // from class: com.huawei.health.suggestion.ui.polymericpage.view.PolymericFilterRecyclerHelper.1
            @Override // com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericFilterAdapter.OnItemClick
            public void onMoreItemClickListener(int i, List<FilterCondition> list) {
                if (nsn.a(500)) {
                    LogUtil.h("Suggestion_AggregateFilterRecyclerHelper", "initView() onClick() view click too fast.");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("MORE_FILTER_CONDITIONS", (ArrayList) list);
                bundle.putString("MORE_FILTER_SELECTED_DATA", moj.e(PolymericFilterRecyclerHelper.this.h));
                if (PolymericFilterRecyclerHelper.this.f3315a instanceof Activity) {
                    Activity activity = (Activity) PolymericFilterRecyclerHelper.this.f3315a;
                    Intent intent = new Intent(PolymericFilterRecyclerHelper.this.f3315a, (Class<?>) PolymericMoreFilterActivity.class);
                    intent.putExtra("RESULT_KEY_SELECTED_DATA", bundle);
                    activity.startActivityForResult(intent, 123);
                }
            }

            @Override // com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericFilterAdapter.OnItemClick
            public void onSpinnerOptionSelectedListener(FilterCondition filterCondition) {
                LogUtil.a("Suggestion_AggregateFilterRecyclerHelper", "onSpinnerOptionSelectedListener.");
                PolymericFilterRecyclerHelper.this.b(filterCondition);
                PolymericFilterRecyclerHelper.this.d.onToFilterCallBack(PolymericFilterRecyclerHelper.this.j);
            }
        });
        this.i = polymericFilterAdapter;
        this.c.setAdapter(polymericFilterAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(FilterCondition filterCondition) {
        Iterator<FilterCondition> it = this.j.iterator();
        this.g.remove(filterCondition);
        while (it.hasNext()) {
            FilterCondition next = it.next();
            if (next.getValue() == filterCondition.getValue()) {
                LogUtil.a("Suggestion_AggregateFilterRecyclerHelper", "remove ", next.getName(), Long.valueOf(next.getValue()));
                it.remove();
            }
        }
        if (filterCondition.getFilterOptions() != null && koq.c(filterCondition.getFilterOptions())) {
            LogUtil.a("Suggestion_AggregateFilterRecyclerHelper", "add ", filterCondition.getName(), Long.valueOf(filterCondition.getValue()));
            this.j.add(filterCondition);
            this.g.add(filterCondition);
            return;
        }
        LogUtil.a("Suggestion_AggregateFilterRecyclerHelper", "not add ", filterCondition.getName(), Long.valueOf(filterCondition.getValue()));
    }

    public void a(List<FilterCondition> list, String str) {
        int i;
        if (koq.b(list) || TextUtils.isEmpty(str)) {
            LogUtil.h("Suggestion_AggregateFilterRecyclerHelper", "setFilterData() filterConditions or categoryName is null");
            return;
        }
        this.j.clear();
        this.h.clear();
        this.g.clear();
        this.b = list;
        ArrayList arrayList = new ArrayList(10);
        if (list.size() <= 3) {
            for (FilterCondition filterCondition : list) {
                gbw gbwVar = new gbw();
                gbwVar.c(filterCondition);
                gbwVar.b(0);
                gbwVar.c(filterCondition.getName());
                arrayList.add(gbwVar);
            }
        } else {
            int i2 = 0;
            while (true) {
                if (i2 >= 2) {
                    break;
                }
                gbw gbwVar2 = new gbw();
                gbwVar2.c(list.get(i2));
                gbwVar2.b(0);
                gbwVar2.c(list.get(i2).getName());
                arrayList.add(gbwVar2);
                i2++;
            }
            ArrayList arrayList2 = new ArrayList(10);
            for (i = 2; i < list.size(); i++) {
                arrayList2.add(list.get(i));
            }
            gbw gbwVar3 = new gbw();
            gbwVar3.d(arrayList2);
            gbwVar3.b(1);
            gbwVar3.c(this.f3315a.getString(R.string._2130841847_res_0x7f0210f7));
            arrayList.add(gbwVar3);
        }
        this.i.a();
        this.i.d(arrayList);
    }

    public void c(Map<Long, List<Long>> map) {
        if (map == null) {
            LogUtil.h("Suggestion_AggregateFilterRecyclerHelper", "setMoreFilterConditionSelected() selectedIdMap is null");
            return;
        }
        this.j.clear();
        this.j.addAll(this.g);
        this.h = map;
        for (int i = 2; i < this.b.size(); i++) {
            FilterCondition filterCondition = this.b.get(i);
            List<FilterOption> filterOptions = filterCondition.getFilterOptions();
            List<Long> list = this.h.get(Long.valueOf(filterCondition.getValue()));
            if (!koq.b(list) && !koq.b(filterOptions)) {
                e(filterCondition, filterOptions, list);
            }
        }
        LogUtil.a("Suggestion_AggregateFilterRecyclerHelper", "setMoreFilterConditionSelected.");
        this.d.onToFilterCallBack(this.j);
    }

    private void e(FilterCondition filterCondition, List<FilterOption> list, List<Long> list2) {
        ArrayList arrayList = new ArrayList(10);
        for (FilterOption filterOption : list) {
            if (list2.contains(Long.valueOf(filterOption.getValue()))) {
                arrayList.add(filterOption);
            }
        }
        if (koq.c(arrayList)) {
            this.j.add(new FilterCondition.Builder().name(filterCondition.getName()).value(filterCondition.getValue()).type(filterCondition.getType()).categoryId(filterCondition.getCategoryId()).filterOptions(arrayList).build());
        }
    }

    public void a(List<FilterCondition> list) {
        if (list == null) {
            LogUtil.h("Suggestion_AggregateFilterRecyclerHelper", "setDefaultSelectedFilter() index < 0 or filterConditions is null");
            return;
        }
        this.j.clear();
        this.h.clear();
        this.j.addAll(list);
        for (FilterCondition filterCondition : this.j) {
            if (!koq.b(filterCondition.getFilterOptions())) {
                ArrayList arrayList = new ArrayList();
                for (FilterOption filterOption : filterCondition.getFilterOptions()) {
                    if (filterOption != null) {
                        arrayList.add(Long.valueOf(filterOption.getValue()));
                    }
                }
                this.h.put(Long.valueOf(filterCondition.getValue()), arrayList);
            }
        }
        LogUtil.a("Suggestion_AggregateFilterRecyclerHelper", "setDefaultSelectedFilter.");
    }

    public void c(OnCourseFilterListener onCourseFilterListener) {
        if (onCourseFilterListener == null) {
            LogUtil.h("Suggestion_AggregateFilterRecyclerHelper", "setOnFilterViewListener() listener is null");
        } else {
            this.d = onCourseFilterListener;
        }
    }
}
