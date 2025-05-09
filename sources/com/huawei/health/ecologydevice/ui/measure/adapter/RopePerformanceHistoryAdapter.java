package com.huawei.health.ecologydevice.ui.measure.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import com.huawei.health.R;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.hji;
import defpackage.hjw;
import defpackage.hjy;
import defpackage.koq;
import defpackage.kpt;
import defpackage.nsy;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class RopePerformanceHistoryAdapter extends BaseExpandableListAdapter {

    /* renamed from: a, reason: collision with root package name */
    private final Context f2335a;
    private int b;
    private int d;
    private int e;
    private final Resources h;
    private hjw i;
    private List<HiHealthData> g = new ArrayList();
    private List<List<HiHealthData>> c = new ArrayList();

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        if (i2 > 0) {
            return i2;
        }
        return 0L;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        if (i > 0) {
            return i;
        }
        return 0L;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public RopePerformanceHistoryAdapter(Context context) {
        this.f2335a = context;
        this.h = context.getResources();
        c();
    }

    private void c() {
        this.d = this.h.getDimensionPixelSize(R.dimen._2131363063_res_0x7f0a04f7);
        this.e = this.h.getDimensionPixelSize(R.dimen._2131363127_res_0x7f0a0537);
        this.b = this.h.getDimensionPixelSize(R.dimen._2131363088_res_0x7f0a0510);
        this.i = new hjw();
    }

    public void e(List<HiHealthData> list, List<List<HiHealthData>> list2) {
        if ((list instanceof ArrayList) && (list2 instanceof ArrayList)) {
            Object clone = ((ArrayList) list).clone();
            if (clone instanceof ArrayList) {
                this.g = (List) clone;
            }
            Object clone2 = ((ArrayList) list2).clone();
            if (clone2 instanceof ArrayList) {
                this.c = (List) clone2;
            }
        }
    }

    public void a() {
        if (koq.c(this.g)) {
            this.g.clear();
            this.g = null;
        }
        if (koq.c(this.c)) {
            this.c.clear();
            this.c = null;
        }
        this.i = null;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        List<HiHealthData> list = this.g;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        List<List<HiHealthData>> list = this.c;
        if (list != null && koq.d(list, i)) {
            return this.c.get(i).size();
        }
        return 0;
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public HiHealthData getGroup(int i) {
        if (koq.d(this.g, i)) {
            return this.g.get(i);
        }
        return null;
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public HiHealthData getChild(int i, int i2) {
        if (koq.d(this.c, i) && !koq.b(this.c.get(i), i2)) {
            return this.c.get(i).get(i2);
        }
        return null;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = UE_();
        }
        Object tag = view.getTag();
        if (tag instanceof b) {
            b bVar = (b) tag;
            b(bVar, i, z);
            HiHealthData group = getGroup(i);
            if (group == null) {
                return view;
            }
            bVar.d.setText(DateUtils.formatDateTime(this.f2335a, group.getStartTime(), 52));
            bVar.f2336a.setText(d(group));
        }
        return view;
    }

    private View UE_() {
        b bVar = new b();
        View inflate = LayoutInflater.from(this.f2335a).inflate(R.layout.item_rope_performance_history_father, (ViewGroup) null);
        bVar.e = nsy.cMd_(inflate, R.id.performance_history_father_bg);
        bVar.d = (HealthTextView) nsy.cMd_(inflate, R.id.performance_history_father_date);
        bVar.f2336a = (HealthTextView) nsy.cMd_(inflate, R.id.performance_history_father_statistics);
        bVar.c = (ImageView) nsy.cMd_(inflate, R.id.performance_history_father_arrow);
        bVar.b = nsy.cMd_(inflate, R.id.performance_history_father_line);
        inflate.setTag(bVar);
        return inflate;
    }

    private void b(b bVar, int i, boolean z) {
        ViewGroup.LayoutParams layoutParams = bVar.b.getLayoutParams();
        if (i == 0) {
            layoutParams.height = 0;
        } else {
            layoutParams.height = this.h.getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        }
        bVar.b.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = bVar.e.getLayoutParams();
        if (layoutParams2 instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) layoutParams2;
            if (z) {
                bVar.e.setBackground(ContextCompat.getDrawable(this.f2335a, R.drawable._2131427637_res_0x7f0b0135));
                bVar.c.setImageResource(R.drawable.ic_health_list_drop_down_arrow_sel);
                layoutParams3.height = this.b + this.d;
            } else {
                bVar.e.setBackground(ContextCompat.getDrawable(this.f2335a, R.drawable._2131427635_res_0x7f0b0133));
                bVar.c.setImageResource(R.drawable.ic_health_list_drop_down_arrow_nor);
                int i2 = this.b;
                int i3 = this.d;
                layoutParams3.height = i2 + i3 + i3;
            }
            bVar.e.setLayoutParams(layoutParams3);
        }
    }

    private String d(HiHealthData hiHealthData) {
        String string;
        double d = hiHealthData.getDouble("Track_Calorie_Sum");
        double d2 = hiHealthData.getDouble("Track_Duration_Sum");
        double d3 = hiHealthData.getDouble("Track_Count_Sum");
        String quantityString = this.h.getQuantityString(R.plurals._2130903213_res_0x7f0300ad, (int) d3, UnitUtil.e(d3, 1, 0));
        double d4 = d2 / 60000.0d;
        if (d4 > 60.0d) {
            string = this.h.getQuantityString(R.plurals._2130903223_res_0x7f0300b7, (int) d4, UnitUtil.e(d4 / 60.0d, 1, 1));
        } else {
            string = this.h.getString(R.string._2130837641_res_0x7f020089, UnitUtil.e(d4, 1, 2));
        }
        return this.h.getString(R.string._2130845546_res_0x7f021f6a, string, quantityString, hji.b(d / 1000.0d));
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = UD_();
        }
        Object tag = view.getTag();
        if (tag instanceof c) {
            c cVar = (c) tag;
            b(cVar, z);
            HiHealthData child = getChild(i, i2);
            if (child == null) {
                return view;
            }
            cVar.c.setText(DateUtils.formatDateTime(this.f2335a, child.getStartTime(), 145));
            this.i.e(kpt.e(child));
            e(cVar, this.i.c());
        }
        return view;
    }

    private void e(c cVar, Pair<float[], float[]> pair) {
        if (pair == null) {
            LogUtil.a("RopePerformanceHistoryAdapter", "performanceScore is null");
            return;
        }
        float[] fArr = pair.first;
        float[] fArr2 = pair.second;
        if (fArr == null || fArr2 == null) {
            LogUtil.a("RopePerformanceHistoryAdapter", "scores or ranks is null");
            return;
        }
        int a2 = hjy.a(fArr2);
        if (a2 == -1) {
            LogUtil.a("RopePerformanceHistoryAdapter", "invalid value");
            return;
        }
        if (a2 == 1) {
            cVar.d.setText(UnitUtil.e(fArr[a2], 1, 2));
        } else {
            cVar.d.setText(UnitUtil.e(fArr[a2], 1, 0));
        }
        Pair<Integer, String> a3 = hjy.a(a2);
        cVar.i.setText(a3.second);
        float f = fArr2[a2];
        if (f <= 0.0f || a3.first == null || a3.first.intValue() <= 0) {
            return;
        }
        cVar.f2337a.setText(this.h.getString(a3.first.intValue(), UnitUtil.e(f, 2, 1)));
    }

    private View UD_() {
        c cVar = new c();
        View inflate = LayoutInflater.from(this.f2335a).inflate(R.layout.item_rope_performance_history_child, (ViewGroup) null);
        cVar.b = nsy.cMd_(inflate, R.id.performance_history_child_bg);
        cVar.c = (HealthTextView) nsy.cMd_(inflate, R.id.performance_history_child_time);
        cVar.d = (HealthTextView) nsy.cMd_(inflate, R.id.performance_history_child_count);
        cVar.i = (HealthTextView) nsy.cMd_(inflate, R.id.performance_history_child_data_unit);
        cVar.f2337a = (HealthTextView) nsy.cMd_(inflate, R.id.performance_history_child_best_rank);
        cVar.e = (HealthDivider) nsy.cMd_(inflate, R.id.performance_history_child_middle_line);
        inflate.setTag(cVar);
        return inflate;
    }

    private void b(c cVar, boolean z) {
        ViewGroup.LayoutParams layoutParams = cVar.b.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            if (z) {
                cVar.e.setVisibility(8);
                cVar.b.setBackground(ContextCompat.getDrawable(this.f2335a, R.drawable._2131427636_res_0x7f0b0134));
                layoutParams2.height = this.e + this.d;
            } else {
                cVar.e.setVisibility(0);
                cVar.b.setBackgroundColor(ContextCompat.getColor(this.f2335a, R.color._2131296666_res_0x7f09019a));
                layoutParams2.height = this.e;
            }
            cVar.b.setLayoutParams(layoutParams2);
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f2336a;
        View b;
        ImageView c;
        HealthTextView d;
        View e;

        private b() {
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f2337a;
        View b;
        HealthTextView c;
        HealthTextView d;
        HealthDivider e;
        HealthTextView i;

        private c() {
        }
    }
}
