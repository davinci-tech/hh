package com.huawei.pluginachievement.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.mfm;
import defpackage.mkd;
import health.compact.a.LanguageUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public class HistoricalReportExpandableAdapter extends BaseExpandableListAdapter {
    private ArrayList<mkd> c;
    private Context e;

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

    public HistoricalReportExpandableAdapter(Context context, ArrayList<mkd> arrayList) {
        this.e = context;
        this.c = arrayList;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        ArrayList<mkd> arrayList = this.c;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        if (koq.b(this.c, i)) {
            LogUtil.c("PLGACHIEVE_HistoricalReportExpandableAdapter", "getChildrenCount is out of bounds");
            return 0;
        }
        mkd mkdVar = this.c.get(i);
        if (mkdVar == null) {
            LogUtil.c("PLGACHIEVE_HistoricalReportExpandableAdapter", "getChildrenCount reportDataBean is null.");
            return 0;
        }
        ArrayList<mkd.c> a2 = mkdVar.a();
        if (koq.b(a2)) {
            LogUtil.c("PLGACHIEVE_HistoricalReportExpandableAdapter", "getChildrenCount oneMonthOrWeekRecordList is empty.");
            return 0;
        }
        return a2.size();
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public mkd getGroup(int i) {
        if (koq.d(this.c, i)) {
            return this.c.get(i);
        }
        LogUtil.c("PLGACHIEVE_HistoricalReportExpandableAdapter", "getGroup is out of bounds");
        return new mkd();
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public mkd.c getChild(int i, int i2) {
        if (koq.d(this.c, i)) {
            ArrayList<mkd.c> a2 = this.c.get(i).a();
            if (koq.d(a2, i2)) {
                return a2.get(i2);
            }
            LogUtil.c("PLGACHIEVE_HistoricalReportExpandableAdapter", "getChild recordArrayList is out of bounds");
            return new mkd.c();
        }
        LogUtil.c("PLGACHIEVE_HistoricalReportExpandableAdapter", "getChild mHistoryReportDataList is out of bounds");
        return new mkd.c();
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        d dVar;
        if (view == null) {
            dVar = new d();
            view = LayoutInflater.from(this.e).inflate(R.layout.achieve_historical_report_expandable_father_item, (ViewGroup) null);
            dVar.d = (HealthTextView) mfm.cgM_(view, R.id.father_item);
            view.setTag(dVar);
        } else {
            Object tag = view.getTag();
            if (tag instanceof d) {
                dVar = (d) tag;
            }
            return view;
        }
        if (koq.b(this.c, i)) {
            LogUtil.c("PLGACHIEVE_HistoricalReportExpandableAdapter", "getGroupView mHistoryReportDataList is out of bounds");
            return view;
        }
        dVar.d.setText(this.c.get(i).e());
        dVar.d.setBackgroundColor(0);
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        View view2;
        c cVar;
        if (view == null) {
            cVar = new c();
            view2 = LayoutInflater.from(this.e).inflate(R.layout.achieve_historical_report_expandable_child_item, (ViewGroup) null);
            cVar.c = (ImageView) mfm.cgM_(view2, R.id.child_item_bg);
            cVar.f8427a = (HealthTextView) mfm.cgM_(view2, R.id.child_item_date);
            cVar.b = (ImageView) mfm.cgM_(view2, R.id.child_item_arrow);
            cVar.e = (HealthDivider) mfm.cgM_(view2, R.id.child_item_divider);
            view2.setTag(cVar);
        } else {
            Object tag = view.getTag();
            if (!(tag instanceof c)) {
                return view;
            }
            c cVar2 = (c) tag;
            view2 = view;
            cVar = cVar2;
        }
        cVar.b.setBackgroundResource(LanguageUtil.bc(this.e) ? R.drawable._2131427841_res_0x7f0b0201 : R.drawable._2131427842_res_0x7f0b0202);
        cVar.e.setVisibility(z ? 8 : 0);
        if (koq.b(this.c, i)) {
            return view2;
        }
        ArrayList<mkd.c> a2 = this.c.get(i).a();
        if (koq.b(a2, i2)) {
            LogUtil.c("PLGACHIEVE_HistoricalReportExpandableAdapter", "getChildView childList is out of bounds");
            return view2;
        }
        cVar.f8427a.setText(a2.get(i2).e());
        b(i2, z, cVar, a2);
        return view2;
    }

    private void b(int i, boolean z, c cVar, ArrayList<mkd.c> arrayList) {
        Drawable drawable;
        if (koq.b(arrayList, i)) {
            LogUtil.c("PLGACHIEVE_HistoricalReportExpandableAdapter", "setChildViewBackground childList is out of bounds");
            return;
        }
        int size = arrayList.size();
        if (size == 1) {
            drawable = this.e.getResources().getDrawable(R.drawable._2131427467_res_0x7f0b008b);
        } else if (size == 2) {
            if (z) {
                drawable = this.e.getResources().getDrawable(R.drawable._2131427468_res_0x7f0b008c);
            } else {
                drawable = this.e.getResources().getDrawable(R.drawable._2131427469_res_0x7f0b008d);
            }
        } else if (i == 0) {
            drawable = this.e.getResources().getDrawable(R.drawable._2131427469_res_0x7f0b008d);
        } else if (i == size - 1) {
            drawable = this.e.getResources().getDrawable(R.drawable._2131427468_res_0x7f0b008c);
        } else {
            drawable = this.e.getResources().getDrawable(R.color._2131296666_res_0x7f09019a);
        }
        cVar.c.setBackground(drawable);
    }

    static class d {
        HealthTextView d;

        private d() {
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f8427a;
        ImageView b;
        ImageView c;
        HealthDivider e;

        private c() {
        }
    }
}
