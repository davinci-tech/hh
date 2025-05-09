package com.huawei.ui.main.stories.health.adapter;

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
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import defpackage.koq;
import defpackage.qjv;
import defpackage.qkg;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class BloodSugarMeasureMutipleAdapter extends BaseExpandableListAdapter {

    /* renamed from: a, reason: collision with root package name */
    private final Context f10114a;
    private int b;
    private int c;
    private int d;
    private List<String> i = new ArrayList();
    private List<List<qkg>> e = new ArrayList();

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

    public BloodSugarMeasureMutipleAdapter(Context context) {
        this.f10114a = context;
        c();
    }

    private void c() {
        Resources resources = this.f10114a.getResources();
        this.c = resources.getDimensionPixelSize(R.dimen._2131363770_res_0x7f0a07ba);
        this.b = resources.getDimensionPixelSize(R.dimen._2131363790_res_0x7f0a07ce);
        this.d = resources.getDimensionPixelSize(R.dimen._2131363775_res_0x7f0a07bf);
    }

    public void b(List<String> list, List<List<qkg>> list2) {
        if ((list instanceof ArrayList) && (list2 instanceof ArrayList)) {
            Object clone = ((ArrayList) list).clone();
            if (clone instanceof ArrayList) {
                this.i = (List) clone;
            }
            Object clone2 = ((ArrayList) list2).clone();
            if (clone2 instanceof ArrayList) {
                this.e = (List) clone2;
            }
        }
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public qkg getChild(int i, int i2) {
        if (koq.d(this.e, i) && !koq.b(this.e.get(i), i2)) {
            return this.e.get(i).get(i2);
        }
        return null;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        List<List<qkg>> list = this.e;
        if (list != null && koq.d(list, i)) {
            return this.e.get(i).size();
        }
        return 0;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = dCs_();
        }
        Object tag = view.getTag();
        if (tag instanceof c) {
            c cVar = (c) tag;
            e(cVar, z);
            qkg child = getChild(i, i2);
            if (child == null) {
                return view;
            }
            cVar.j.setText(this.f10114a.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_list_item_value, Double.valueOf(child.m())));
            cVar.b.setText(String.valueOf(qjv.a(this.f10114a, BigDecimal.valueOf(child.o()).intValue(), BigDecimal.valueOf(child.m()).floatValue()).get("HEALTH_BLOOD_SUGAR_LEVEL_DESC")));
            cVar.b.setTextColor(e(child));
            cVar.e.setText(this.f10114a.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_list_item_period_and_confirmed, qjv.c(this.f10114a, BigDecimal.valueOf(child.o()).intValue()), this.f10114a.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_to_be_confirmed)));
            cVar.f.setText(DateFormatUtil.d(child.h(), DateFormatUtil.DateFormatType.DATE_FORMAT_HOUR_MINUTE));
        }
        return view;
    }

    private View dCs_() {
        c cVar = new c();
        View inflate = LayoutInflater.from(this.f10114a).inflate(R.layout.layout_blood_sugar_history_child_item, (ViewGroup) null);
        cVar.f10115a = inflate.findViewById(R.id.hw_show_blood_sugar_history_child_bg);
        cVar.c = (ImageView) inflate.findViewById(R.id.hw_show_blood_sugar_history_child_left_img);
        cVar.j = (HealthTextView) inflate.findViewById(R.id.hw_show_blood_sugar_history_child_value);
        cVar.b = (HealthTextView) inflate.findViewById(R.id.hw_show_blood_sugar_history_child_status_text);
        cVar.e = (HealthTextView) inflate.findViewById(R.id.hw_show_blood_sugar_history_child_period);
        cVar.f = (HealthTextView) inflate.findViewById(R.id.hw_show_blood_sugar_history_child_time);
        cVar.d = (HealthDivider) inflate.findViewById(R.id.hw_blood_sugar_history_child_middle_line);
        inflate.setTag(cVar);
        return inflate;
    }

    private void e(c cVar, boolean z) {
        cVar.c.setImageResource(R.drawable._2131429770_res_0x7f0b098a);
        ViewGroup.LayoutParams layoutParams = cVar.f10115a.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            if (z) {
                cVar.d.setVisibility(8);
                cVar.f10115a.setBackground(this.f10114a.getResources().getDrawable(R.drawable._2131427636_res_0x7f0b0134));
                layoutParams2.height = this.b + this.c;
            } else {
                cVar.d.setVisibility(0);
                cVar.f10115a.setBackground(this.f10114a.getResources().getDrawable(R.color._2131296666_res_0x7f09019a));
                layoutParams2.height = this.b;
            }
            cVar.f10115a.setLayoutParams(layoutParams2);
        }
    }

    private int e(qkg qkgVar) {
        int length;
        List<Float> a2 = qjv.a(BigDecimal.valueOf(qkgVar.o()).intValue());
        int[] a3 = qjv.a(this.f10114a);
        for (int i = 0; i < a2.size(); i++) {
            if (qkgVar.m() <= a2.get(i).floatValue() && (length = (a3.length / 2) + i) < a3.length) {
                return a3[length];
            }
        }
        return ContextCompat.getColor(this.f10114a, R.color._2131296798_res_0x7f09021e);
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public String getGroup(int i) {
        if (koq.d(this.i, i)) {
            return this.i.get(i);
        }
        return null;
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
            view = dCt_();
        }
        Object tag = view.getTag();
        if (tag instanceof d) {
            d dVar = (d) tag;
            c(dVar, i, z);
            String group = getGroup(i);
            if (group == null) {
                return view;
            }
            dVar.d.setText(group);
            int childrenCount = getChildrenCount(i);
            dVar.c.setText(this.f10114a.getResources().getQuantityString(R.plurals._2130903345_res_0x7f030131, childrenCount, Integer.valueOf(childrenCount)));
        }
        return view;
    }

    private View dCt_() {
        d dVar = new d();
        View inflate = LayoutInflater.from(this.f10114a).inflate(R.layout.layout_blood_sugar_history_father_item, (ViewGroup) null);
        dVar.f10116a = inflate.findViewById(R.id.hw_show_blood_sugar_history_father_bg);
        dVar.d = (HealthTextView) inflate.findViewById(R.id.hw_show_blood_sugar_history_father_date);
        dVar.c = (HealthTextView) inflate.findViewById(R.id.hw_show_blood_sugar_history_father_count);
        dVar.e = (ImageView) inflate.findViewById(R.id.hw_show_blood_sugar_history_father_arrow);
        dVar.i = inflate.findViewById(R.id.hw_show_blood_sugar_history_father_line);
        dVar.b = inflate.findViewById(R.id.hw_show_blood_sugar_history_bottom_image_interval);
        inflate.setTag(dVar);
        return inflate;
    }

    private void c(d dVar, int i, boolean z) {
        ViewGroup.LayoutParams layoutParams = dVar.i.getLayoutParams();
        if (i == 0) {
            layoutParams.height = 0;
        } else {
            layoutParams.height = this.f10114a.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        }
        dVar.i.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = dVar.f10116a.getLayoutParams();
        if (layoutParams2 instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) layoutParams2;
            if (z) {
                dVar.f10116a.setBackground(this.f10114a.getResources().getDrawable(R.drawable._2131427637_res_0x7f0b0135));
                dVar.e.setImageResource(R.drawable.ic_health_list_drop_down_arrow_sel);
                dVar.b.setVisibility(0);
                layoutParams3.height = this.d + this.c;
            } else {
                dVar.f10116a.setBackground(this.f10114a.getResources().getDrawable(R.drawable._2131427635_res_0x7f0b0133));
                dVar.e.setImageResource(R.drawable.ic_health_list_drop_down_arrow_nor);
                dVar.b.setVisibility(8);
                int i2 = this.d;
                int i3 = this.c;
                layoutParams3.height = i2 + i3 + i3;
            }
            dVar.f10116a.setLayoutParams(layoutParams3);
        }
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        View f10116a;
        View b;
        HealthTextView c;
        HealthTextView d;
        ImageView e;
        View i;

        private d() {
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        View f10115a;
        HealthTextView b;
        ImageView c;
        HealthDivider d;
        HealthTextView e;
        HealthTextView f;
        HealthTextView j;

        private c() {
        }
    }
}
