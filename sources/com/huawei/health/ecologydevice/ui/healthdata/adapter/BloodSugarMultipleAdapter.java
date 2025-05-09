package com.huawei.health.ecologydevice.ui.healthdata.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hihealth.HiHealthData;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.deb;
import defpackage.koq;
import defpackage.nsj;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class BloodSugarMultipleAdapter extends BaseExpandableListAdapter {
    private int b;
    private int c;
    private final Context d;
    private int e;
    private final Resources f;
    private List<String> i = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private List<List<HiHealthData>> f2320a = new ArrayList();

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        return i2;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        return i;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public BloodSugarMultipleAdapter(Context context) {
        this.d = context;
        this.f = context.getResources();
        a();
    }

    private void a() {
        this.e = this.f.getDimensionPixelSize(R.dimen._2131363063_res_0x7f0a04f7);
        this.c = this.f.getDimensionPixelSize(R.dimen._2131363088_res_0x7f0a0510);
        this.b = this.f.getDimensionPixelSize(R.dimen._2131363060_res_0x7f0a04f4);
    }

    public void c(List<String> list, List<List<HiHealthData>> list2) {
        if ((list instanceof ArrayList) && (list2 instanceof ArrayList)) {
            Object clone = ((ArrayList) list).clone();
            if (clone instanceof ArrayList) {
                this.i = (List) clone;
            }
            Object clone2 = ((ArrayList) list2).clone();
            if (clone2 instanceof ArrayList) {
                this.f2320a = (List) clone2;
            }
        }
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public HiHealthData getChild(int i, int i2) {
        if (koq.d(this.f2320a, i) && !koq.b(this.f2320a.get(i), i2)) {
            return this.f2320a.get(i).get(i2);
        }
        return null;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        List<List<HiHealthData>> list = this.f2320a;
        if (list != null && koq.d(list, i)) {
            return this.f2320a.get(i).size();
        }
        return 0;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = Ui_();
        }
        Object tag = view.getTag();
        if (tag instanceof b) {
            b bVar = (b) tag;
            e(bVar, z);
            HiHealthData child = getChild(i, i2);
            if (child == null) {
                return view;
            }
            bVar.g.setText(UnitUtil.e(child.getValue(), 1, 1));
            int intValue = BigDecimal.valueOf(child.getType()).intValue();
            bVar.f.setText(deb.b(deb.c(this.d, intValue, BigDecimal.valueOf(child.getValue()).floatValue())));
            bVar.f.setTextColor(b(child));
            bVar.d.setText(deb.a(intValue));
            bVar.f2321a.setText(this.f.getString(R.string._2130845915_res_0x7f0220db));
            bVar.i.setText(nsj.i(child.getEndTime()));
            if (LanguageUtil.bc(this.d)) {
                bVar.j.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            } else {
                bVar.j.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
            }
        }
        return view;
    }

    private View Ui_() {
        b bVar = new b();
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.item_blood_sugar_history_child, (ViewGroup) null);
        bVar.e = inflate.findViewById(R.id.blood_sugar_history_child_bg);
        bVar.c = (ImageView) inflate.findViewById(R.id.blood_sugar_history_child_left_img);
        bVar.g = (HealthTextView) inflate.findViewById(R.id.blood_sugar_history_child_value);
        bVar.f = (HealthTextView) inflate.findViewById(R.id.blood_sugar_history_child_status_text);
        bVar.d = (HealthTextView) inflate.findViewById(R.id.blood_sugar_history_child_period);
        bVar.f2321a = (HealthTextView) inflate.findViewById(R.id.blood_sugar_history_child_confirmed);
        bVar.i = (HealthTextView) inflate.findViewById(R.id.blood_sugar_history_child_time);
        bVar.j = (ImageView) inflate.findViewById(R.id.blood_sugar_history_child_right_arrow);
        bVar.b = (HealthDivider) inflate.findViewById(R.id.blood_sugar_history_child_middle_line);
        inflate.setTag(bVar);
        return inflate;
    }

    private void e(b bVar, boolean z) {
        bVar.c.setImageResource(R.drawable._2131429770_res_0x7f0b098a);
        ViewGroup.LayoutParams layoutParams = bVar.e.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            if (z) {
                bVar.b.setVisibility(8);
                bVar.e.setBackground(ContextCompat.getDrawable(this.d, R.drawable._2131427636_res_0x7f0b0134));
                layoutParams2.height = this.c + this.e;
            } else {
                bVar.b.setVisibility(0);
                bVar.e.setBackgroundColor(ContextCompat.getColor(this.d, R.color._2131296666_res_0x7f09019a));
                layoutParams2.height = this.c;
            }
            bVar.e.setLayoutParams(layoutParams2);
        }
    }

    private int b(HiHealthData hiHealthData) {
        int length;
        List<Float> c = deb.c(BigDecimal.valueOf(hiHealthData.getType()).intValue());
        int[] e2 = deb.e();
        for (int i = 0; i < c.size(); i++) {
            if (hiHealthData.getValue() <= c.get(i).floatValue() && (length = (e2.length / 2) + i) < e2.length) {
                return e2[length];
            }
        }
        return ContextCompat.getColor(this.d, R.color._2131296798_res_0x7f09021e);
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public String getGroup(int i) {
        return !koq.d(this.i, i) ? "" : this.i.get(i);
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        List<String> list = this.i;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = Uj_();
        }
        Object tag = view.getTag();
        if (tag instanceof e) {
            e eVar = (e) tag;
            a(eVar, i, z);
            String group = getGroup(i);
            if (group == null) {
                return view;
            }
            eVar.e.setText(group);
            int childrenCount = getChildrenCount(i);
            eVar.d.setText(this.f.getQuantityString(R.plurals._2130903345_res_0x7f030131, childrenCount, Integer.valueOf(childrenCount)));
        }
        return view;
    }

    private View Uj_() {
        e eVar = new e();
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.item_blood_sugar_history_father, (ViewGroup) null);
        eVar.c = inflate.findViewById(R.id.blood_sugar_history_father_bg);
        eVar.e = (HealthTextView) inflate.findViewById(R.id.blood_sugar_history_father_date);
        eVar.d = (HealthTextView) inflate.findViewById(R.id.blood_sugar_history_father_count);
        eVar.b = (ImageView) inflate.findViewById(R.id.blood_sugar_history_father_arrow);
        eVar.g = inflate.findViewById(R.id.blood_sugar_history_father_line);
        eVar.f2322a = inflate.findViewById(R.id.blood_sugar_history_bottom_image_interval);
        inflate.setTag(eVar);
        return inflate;
    }

    private void a(e eVar, int i, boolean z) {
        ViewGroup.LayoutParams layoutParams = eVar.g.getLayoutParams();
        if (i == 0) {
            layoutParams.height = 0;
        } else {
            layoutParams.height = this.f.getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        }
        eVar.g.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = eVar.c.getLayoutParams();
        if (layoutParams2 instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) layoutParams2;
            if (z) {
                eVar.c.setBackground(ContextCompat.getDrawable(this.d, R.drawable._2131427637_res_0x7f0b0135));
                eVar.b.setImageResource(R.drawable.ic_health_list_drop_down_arrow_sel);
                eVar.f2322a.setVisibility(0);
                layoutParams3.height = this.b + this.e;
            } else {
                eVar.c.setBackground(ContextCompat.getDrawable(this.d, R.drawable._2131427635_res_0x7f0b0133));
                eVar.b.setImageResource(R.drawable.ic_health_list_drop_down_arrow_nor);
                eVar.f2322a.setVisibility(8);
                int i2 = this.b;
                int i3 = this.e;
                layoutParams3.height = i2 + i3 + i3;
            }
            eVar.c.setLayoutParams(layoutParams3);
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        View f2322a;
        ImageView b;
        View c;
        HealthTextView d;
        HealthTextView e;
        View g;

        private e() {
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f2321a;
        HealthDivider b;
        ImageView c;
        HealthTextView d;
        View e;
        HealthTextView f;
        HealthTextView g;
        HealthTextView i;
        ImageView j;

        private b() {
        }
    }
}
