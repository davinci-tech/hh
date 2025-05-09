package com.huawei.health.suggestion.ui.plan.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.plan.adapter.DietDetailsMutipleAdapter;
import com.huawei.health.suggestion.ui.plan.viewholder.AiPlanWeekDetailViewHolder;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.ui.commonui.base.BaseFragment;
import defpackage.fiu;
import defpackage.fyw;
import defpackage.ixx;
import defpackage.koq;
import defpackage.quh;
import defpackage.quk;
import defpackage.qul;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class DietDetailsFragment extends BaseFragment {
    private DietDetailsMutipleAdapter b;
    private ExpandableListView d;
    private AiPlanWeekDetailViewHolder e;
    private String h;

    /* renamed from: a, reason: collision with root package name */
    private final List<Integer> f3263a = Arrays.asList(10, 20, 30);
    private List<List<fiu>> g = new ArrayList();
    private List<String> f = new ArrayList();
    private final Map<String, Integer> c = new HashMap(16);

    public static DietDetailsFragment c() {
        Bundle bundle = new Bundle();
        DietDetailsFragment dietDetailsFragment = new DietDetailsFragment();
        dietDetailsFragment.setArguments(bundle);
        return dietDetailsFragment;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(getContext(), AnalyticsValue.INT_PLAN_2040150.value(), hashMap, 0);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.diet_expandable_list, viewGroup, false);
        this.d = (ExpandableListView) inflate.findViewById(R.id.hw_show_diet_expandable_lv);
        DietDetailsMutipleAdapter dietDetailsMutipleAdapter = new DietDetailsMutipleAdapter(getContext());
        this.b = dietDetailsMutipleAdapter;
        this.d.setAdapter(dietDetailsMutipleAdapter);
        b();
        return inflate;
    }

    private void b() {
        Log.d("DietDetailsFragment", "addListener");
        this.d.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() { // from class: com.huawei.health.suggestion.ui.plan.fragment.DietDetailsFragment$$ExternalSyntheticLambda0
            @Override // android.widget.ExpandableListView.OnGroupExpandListener
            public final void onGroupExpand(int i) {
                DietDetailsFragment.this.b(i);
            }
        });
        this.d.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() { // from class: com.huawei.health.suggestion.ui.plan.fragment.DietDetailsFragment$$ExternalSyntheticLambda1
            @Override // android.widget.ExpandableListView.OnGroupCollapseListener
            public final void onGroupCollapse(int i) {
                DietDetailsFragment.this.e(i);
            }
        });
        this.d.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() { // from class: com.huawei.health.suggestion.ui.plan.fragment.DietDetailsFragment$$ExternalSyntheticLambda2
            @Override // android.widget.ExpandableListView.OnGroupClickListener
            public final boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                return DietDetailsFragment.this.aHE_(expandableListView, view, i, j);
            }
        });
        this.d.setOnChildClickListener(new ExpandableListView.OnChildClickListener() { // from class: com.huawei.health.suggestion.ui.plan.fragment.DietDetailsFragment$$ExternalSyntheticLambda3
            @Override // android.widget.ExpandableListView.OnChildClickListener
            public final boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
                return DietDetailsFragment.this.aHF_(expandableListView, view, i, i2, j);
            }
        });
    }

    /* synthetic */ void b(int i) {
        if (!koq.b(this.f, i) && i >= 0 && i < this.f.size()) {
            this.c.put(this.f.get(i), 1);
            this.e.e(1, aHD_(this.d, this.b));
        }
    }

    /* synthetic */ void e(int i) {
        if (koq.b(this.f, i)) {
            return;
        }
        this.c.put(this.f.get(i), 0);
        this.e.e(1, aHD_(this.d, this.b));
    }

    /* synthetic */ boolean aHE_(ExpandableListView expandableListView, View view, int i, long j) {
        if (fyw.b(this.h)) {
            ViewClickInstrumentation.groupClickOnExpandableListView(expandableListView, view, i);
            return true;
        }
        if (!this.d.isGroupExpanded(i)) {
            HashMap hashMap = new HashMap(3);
            hashMap.put("click", 1);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.INT_PLAN_2040155.value(), hashMap, 0);
            hashMap.put("source", 2);
            hashMap.put("type", Integer.valueOf(i + 1));
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.INT_PLAN_2040156.value(), hashMap, 0);
            JumpUtil.d(this.h, new String[0]);
            Log.d("DietDetailsFragment", "jumpToDietDiaryH5");
            ViewClickInstrumentation.groupClickOnExpandableListView(expandableListView, view, i);
            return true;
        }
        ViewClickInstrumentation.groupClickOnExpandableListView(expandableListView, view, i);
        return false;
    }

    /* synthetic */ boolean aHF_(ExpandableListView expandableListView, View view, int i, int i2, long j) {
        if (this.g.isEmpty()) {
            ViewClickInstrumentation.childClickOnExpandableListView(expandableListView, view, i, i2);
            return false;
        }
        this.b.b(i, i2);
        ViewClickInstrumentation.childClickOnExpandableListView(expandableListView, view, i, i2);
        return true;
    }

    private void e(List<String> list, List<List<fiu>> list2, quh quhVar) {
        Log.d("DietDetailsFragment", "expand " + list2);
        this.c.clear();
        if (list2.isEmpty()) {
            Log.d("DietDetailsFragment", "recommendFoods isEmpty");
            return;
        }
        b(list, quhVar, list2);
        for (int i = 0; i < list.size(); i++) {
            LogUtil.b("DietDetailsFragment", "mExpandSecondTabMap is", this.c.toString(), "heats", list.toString());
            if (this.c.get(list.get(i)).intValue() == 1) {
                this.d.expandGroup(i);
            } else {
                this.d.collapseGroup(i);
            }
        }
        this.f = list;
        this.g = list2;
        this.e.e(1, aHD_(this.d, this.b));
    }

    public int aHD_(ExpandableListView expandableListView, DietDetailsMutipleAdapter dietDetailsMutipleAdapter) {
        if (dietDetailsMutipleAdapter == null) {
            return 0;
        }
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < dietDetailsMutipleAdapter.getGroupCount(); i3++) {
            View groupView = dietDetailsMutipleAdapter.getGroupView(i3, false, null, expandableListView);
            if (groupView != null) {
                groupView.measure(0, 0);
                i2 += groupView.getMeasuredHeight();
                LogUtil.d("DietDetailsFragment", "getListHeight secondTab totallist = ", Integer.valueOf(i2));
                i += dietDetailsMutipleAdapter.getChildrenCount(0) - 1;
            }
        }
        LogUtil.d("DietDetailsFragment", "getListHeight secondTab list = ", Integer.valueOf(i));
        for (int i4 = 0; i4 < this.f.size(); i4++) {
            Log.d("DietDetailsFragment", "mExpandSecondTabMap.get(heats.get(i)) " + this.f.get(i4) + "" + this.c.toString());
            Integer num = this.c.get(this.f.get(i4));
            if (num != null && num.intValue() == 1) {
                int i5 = i;
                int i6 = i2;
                int i7 = 0;
                while (i7 < dietDetailsMutipleAdapter.getChildrenCount(i4)) {
                    int i8 = i7;
                    View childView = dietDetailsMutipleAdapter.getChildView(i4, i7, false, null, expandableListView);
                    if (childView != null) {
                        childView.measure(0, 0);
                        i6 += childView.getMeasuredHeight();
                        LogUtil.d("DietDetailsFragment", "getListHeight secondTab totallist = ", Integer.valueOf(i6));
                        i5 += dietDetailsMutipleAdapter.getChildrenCount(i4) - 1;
                    }
                    i7 = i8 + 1;
                }
                i = i5;
                i2 = i6;
            }
        }
        int dividerHeight = i2 + (expandableListView.getDividerHeight() * i);
        LogUtil.d("DietDetailsFragment", "getListHeight second tab list height = ", Integer.valueOf(dividerHeight));
        LogUtil.d("DietDetailsFragment", "getListHeight secondTab final totallist = ", Integer.valueOf(dividerHeight));
        return dividerHeight;
    }

    public void c(AiPlanWeekDetailViewHolder aiPlanWeekDetailViewHolder) {
        this.e = aiPlanWeekDetailViewHolder;
    }

    public void d(IntPlan intPlan, List<String> list, List<List<fiu>> list2, quh quhVar, String str) {
        LogUtil.b("DietDetailsFragment", "setData");
        DietDetailsMutipleAdapter dietDetailsMutipleAdapter = this.b;
        if (dietDetailsMutipleAdapter == null) {
            LogUtil.e("DietDetailsFragment", "adapter not init or destroyed.");
            return;
        }
        this.h = str;
        dietDetailsMutipleAdapter.a(intPlan, list, list2, quhVar, this.e, str);
        this.b.notifyDataSetChanged();
        e(list, list2, quhVar);
    }

    private void b(List<String> list, quh quhVar, List<List<fiu>> list2) {
        for (int i = 0; i < list.size(); i++) {
            LogUtil.b("DietDetailsFragment", "heatIndex", Integer.valueOf(i), " heat", list.get(i));
            ArrayList arrayList = new ArrayList();
            List<qul> arrayList2 = new ArrayList<>();
            if (quhVar != null) {
                arrayList2 = quhVar.a();
            }
            List<fiu> list3 = list2.get(i);
            if (koq.b(arrayList2)) {
                LogUtil.b("DietDetailsFragment", "dietMap = null");
                this.c.put(list.get(i), 1);
            } else {
                for (qul qulVar : arrayList2) {
                    LogUtil.b("DietDetailsFragment", " meal.getWhichMeal() " + qulVar.h());
                    if (this.f3263a.get(i).intValue() == qulVar.h()) {
                        Iterator<quk> it = qulVar.c().iterator();
                        while (it.hasNext()) {
                            arrayList.add(it.next().a());
                        }
                    }
                }
                Iterator<fiu> it2 = list2.get(i).iterator();
                int i2 = 0;
                while (it2.hasNext()) {
                    if (arrayList.contains(it2.next().a())) {
                        i2++;
                    }
                }
                LogUtil.b("DietDetailsFragment", "allRecorded " + i2);
                this.c.put(list.get(i), Integer.valueOf(i2 == list3.size() ? 0 : 1));
            }
        }
    }
}
