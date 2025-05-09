package com.huawei.ui.main.stories.fitness.activity.bloodoxygen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.nsj;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class BloodOxygenHistoryRecordAdapter extends BaseExpandableListAdapter {
    private List<List<HiHealthData>> b;
    private Context c;
    private List<String> e;

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
        return false;
    }

    public BloodOxygenHistoryRecordAdapter(Context context, List<String> list, List<List<HiHealthData>> list2) {
        if (context == null) {
            LogUtil.h("BloodOxygenHistoryRecordAdapter", "context is null");
            return;
        }
        this.c = context;
        if (list == null) {
            this.e = new ArrayList();
            this.b = new ArrayList();
            return;
        }
        this.e = list;
        if (list2 == null) {
            this.b = new ArrayList();
        } else {
            this.b = list2;
        }
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.e.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        if (koq.b(this.e, i) || koq.b(this.b, i)) {
            LogUtil.h("BloodOxygenHistoryRecordAdapter", "getChildrenCount is out of bounds");
            return 0;
        }
        return this.b.get(i).size();
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int i) {
        if (koq.b(this.e, i)) {
            LogUtil.h("BloodOxygenHistoryRecordAdapter", "getGroup is out of bounds");
            return null;
        }
        return this.e.get(i);
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int i, int i2) {
        if (koq.b(this.e, i) || koq.b(this.b, i) || koq.b(this.b.get(i), i2)) {
            LogUtil.h("BloodOxygenHistoryRecordAdapter", "getChild is out of bounds");
            return null;
        }
        return this.b.get(i).get(i2);
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = LayoutInflater.from(this.c).inflate(R.layout.activity_blood_oxygen_history_data_group, viewGroup, false);
            bVar = new b();
            bVar.e = (RelativeLayout) view.findViewById(R.id.blood_oxygen_history_data_group_data);
            bVar.d = (HealthTextView) view.findViewById(R.id.blood_oxygen_history_data_group_date_textview);
            bVar.b = (ImageView) view.findViewById(R.id.blood_oxygen_history_data_group_img_arrow);
            bVar.c = view.findViewById(R.id.blood_oxygen_history_data_group_divider);
            bVar.f9749a = (HealthDivider) view.findViewById(R.id.blood_oxygen_history_data_group_under_divider);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        if (koq.b(this.e, i)) {
            LogUtil.h("BloodOxygenHistoryRecordAdapter", "getGroupView is out of bounds");
            return view;
        }
        bVar.d.setText(this.e.get(i));
        if (z) {
            bVar.f9749a.setVisibility(0);
            bVar.e.setBackgroundResource(R.drawable._2131427515_res_0x7f0b00bb);
        } else {
            bVar.f9749a.setVisibility(8);
            bVar.e.setBackgroundResource(R.drawable._2131427514_res_0x7f0b00ba);
        }
        bVar.e.setPadding(nsn.c(this.c, 12.0f), nsn.c(this.c, 4.0f), nsn.c(this.c, 12.0f), 0);
        bVar.c.setVisibility(i == 0 ? 8 : 0);
        bVar.b.setImageResource(z ? R.drawable.ic_health_list_drop_down_arrow_nor : R.drawable.ic_health_list_drop_down_arrow_sel);
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        d dVar;
        if (view == null) {
            view = LayoutInflater.from(this.c).inflate(R.layout.activity_blood_oxygen_history_data_item, viewGroup, false);
            dVar = new d();
            dVar.e = (RelativeLayout) view.findViewById(R.id.blood_oxygen_history_data_item_detail);
            dVar.c = (HealthTextView) view.findViewById(R.id.blood_oxygen_history_data_item_percent);
            dVar.b = (HealthTextView) view.findViewById(R.id.blood_oxygen_history_data_item_time);
            dVar.d = (HealthDivider) view.findViewById(R.id.blood_oxygen_history_data_item_divider);
            dVar.f9750a = (HealthTextView) view.findViewById(R.id.blood_oxygen_history_data_item_date);
            view.setTag(dVar);
        } else {
            dVar = (d) view.getTag();
        }
        if (koq.b(this.e, i) || koq.b(this.b, i) || koq.b(this.b.get(i), i2)) {
            LogUtil.h("BloodOxygenHistoryRecordAdapter", "getChildView is out of bounds");
            return view;
        }
        dVar.c.setText(UnitUtil.e(CommonUtil.m(this.c, String.valueOf(this.b.get(i).get(i2).getIntValue())), 2, 0));
        if (z) {
            dVar.e.setBackgroundResource(R.drawable._2131427513_res_0x7f0b00b9);
            dVar.e.setPadding(nsn.c(this.c, 12.0f), 0, nsn.c(this.c, 12.0f), nsn.c(this.c, 4.0f));
        } else {
            dVar.e.setBackgroundColor(this.c.getResources().getColor(R.color._2131297808_res_0x7f090610));
        }
        dVar.d.setVisibility(z ? 8 : 0);
        long endTime = this.b.get(i).get(i2).getEndTime();
        dVar.f9750a.setText(nsj.c(this.c, endTime, 131096));
        dVar.b.setText(nsj.c(this.c, endTime, 1));
        return view;
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        HealthDivider f9749a;
        ImageView b;
        View c;
        HealthTextView d;
        RelativeLayout e;

        b() {
        }
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f9750a;
        HealthTextView b;
        HealthTextView c;
        HealthDivider d;
        RelativeLayout e;

        d() {
        }
    }
}
