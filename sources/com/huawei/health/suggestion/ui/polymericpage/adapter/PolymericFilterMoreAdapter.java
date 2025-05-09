package com.huawei.health.suggestion.ui.polymericpage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.model.FilterCondition;
import com.huawei.basefitnessadvice.model.FilterOption;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.view.FilterFlowLayout;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class PolymericFilterMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private Map<Long, List<Long>> f3311a;
    private List<FilterCondition> b;
    private Context c;

    public PolymericFilterMoreAdapter(Context context, List<FilterCondition> list, Map<Long, List<Long>> map) {
        this.c = (Context) new WeakReference(context).get();
        this.b = list;
        this.f3311a = map;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new e(LayoutInflater.from(this.c).inflate(R.layout.item__course_filter_more, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        List<Long> list;
        e eVar = (e) viewHolder;
        final FilterCondition filterCondition = this.b.get(i);
        eVar.f3312a.setHeadTitleText(filterCondition.getName());
        List<FilterOption> filterOptions = filterCondition.getFilterOptions();
        if (this.f3311a.get(Long.valueOf(filterCondition.getValue())) == null) {
            list = new ArrayList<>();
        } else {
            list = this.f3311a.get(Long.valueOf(filterCondition.getValue()));
        }
        eVar.d.setData(this.c, a(filterOptions), list);
        eVar.d.setOnCheckedChangeListener(new FilterFlowLayout.OnCheckChangeListener() { // from class: com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericFilterMoreAdapter.3
            @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
            public void onChecked(int i2) {
                List list2 = (List) PolymericFilterMoreAdapter.this.f3311a.get(Long.valueOf(filterCondition.getValue()));
                if (list2 == null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Long.valueOf(i2));
                    PolymericFilterMoreAdapter.this.f3311a.put(Long.valueOf(filterCondition.getValue()), arrayList);
                    return;
                }
                list2.add(Long.valueOf(i2));
            }

            @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
            public void onCancelCheck(int i2) {
                List list2 = (List) PolymericFilterMoreAdapter.this.f3311a.get(Long.valueOf(filterCondition.getValue()));
                if (list2 != null) {
                    list2.remove(Long.valueOf(i2));
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<FilterCondition> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public Map<Long, List<Long>> b() {
        return this.f3311a;
    }

    public void c() {
        Map<Long, List<Long>> map = this.f3311a;
        if (map == null || map.size() == 0) {
            return;
        }
        this.f3311a.clear();
    }

    private List<Attribute> a(List<FilterOption> list) {
        ArrayList arrayList = new ArrayList(10);
        for (FilterOption filterOption : list) {
            Attribute attribute = new Attribute();
            attribute.setName(filterOption.getName());
            attribute.setId(Long.toString(filterOption.getValue()));
            arrayList.add(attribute);
        }
        return arrayList;
    }

    static class e extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthSubHeader f3312a;
        FilterFlowLayout d;

        e(View view) {
            super(view);
            this.f3312a = (HealthSubHeader) view.findViewById(R.id.workout_filter_classify_title);
            this.d = (FilterFlowLayout) view.findViewById(R.id.workout_filter_classify_group);
        }
    }
}
