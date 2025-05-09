package com.huawei.ui.main.stories.health.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.model.weight.card.CardConstants;
import defpackage.cfe;
import defpackage.jcf;
import defpackage.koq;
import defpackage.nsf;
import defpackage.nsj;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.qsj;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class WeightExpandableListViewAdapter extends BaseExpandableListAdapter {
    private boolean b;
    private final Context c;
    private final Handler d;
    private final Resources h;
    private final boolean j;

    /* renamed from: a, reason: collision with root package name */
    private List<String[]> f10143a = new ArrayList(16);
    private List<List<cfe>> e = new ArrayList(16);
    private ArrayList<List<Boolean>> g = new ArrayList<>(16);
    private HashMap<String, HealthCheckBox> i = new HashMap<>(16);

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

    public WeightExpandableListViewAdapter(Handler handler) {
        Context context = BaseApplication.getContext();
        this.c = context;
        this.h = context.getResources();
        this.d = handler;
        this.j = Utils.o();
    }

    public void d(ArrayList<String[]> arrayList, ArrayList<List<cfe>> arrayList2) {
        this.f10143a.clear();
        this.e.clear();
        this.g.clear();
        if (koq.c(arrayList) && koq.c(arrayList2)) {
            this.f10143a = (List) arrayList.clone();
            this.e = (List) arrayList2.clone();
            for (int i = 0; i < arrayList.size(); i++) {
                List<cfe> list = arrayList2.get(i);
                if (!koq.b(list)) {
                    ArrayList arrayList3 = new ArrayList(16);
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        arrayList3.add(false);
                    }
                    this.g.add(arrayList3);
                }
            }
        }
        notifyDataSetChanged();
    }

    public int a() {
        if (koq.b(this.f10143a)) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.f10143a.size(); i2++) {
            List<cfe> list = this.e.get(i2);
            if (!koq.b(list)) {
                i += list.size();
            }
        }
        return i;
    }

    public int c() {
        int i = 0;
        if (koq.b(this.g)) {
            return 0;
        }
        Iterator<List<Boolean>> it = this.g.iterator();
        while (it.hasNext()) {
            Iterator<Boolean> it2 = it.next().iterator();
            while (it2.hasNext()) {
                if (it2.next().booleanValue()) {
                    i++;
                }
            }
        }
        return i;
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public cfe getChild(int i, int i2) {
        if (koq.b(this.e, i)) {
            LogUtil.h("HealthWeight_WeightExpandableListViewAdapter", "getChild mChildList is out of bounds");
            return null;
        }
        List<cfe> list = this.e.get(i);
        if (koq.b(list, i2)) {
            LogUtil.h("HealthWeight_WeightExpandableListViewAdapter", "getChild weightBeanList is out of bounds");
            return null;
        }
        return list.get(i2);
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        e eVar;
        if (view == null) {
            view = dDn_();
            eVar = dDm_(view);
        } else {
            String str = String.valueOf(i) + i2;
            if (this.i.containsKey(str)) {
                LogUtil.a("HealthWeight_WeightExpandableListViewAdapter", "mSelectBoxMap containsKey, need new holder again, viewKey = ", str);
                HealthCheckBox healthCheckBox = (HealthCheckBox) view.findViewById(R.id.child_item_check_box);
                if (healthCheckBox != null) {
                    LogUtil.a("HealthWeight_WeightExpandableListViewAdapter", "checkBox != null, reset check status");
                    healthCheckBox.setOnCheckedChangeListener(null);
                    healthCheckBox.setChecked(false);
                }
                this.i.remove(str);
            }
            eVar = (e) view.getTag();
        }
        eVar.d.setBackgroundResource(LanguageUtil.bc(this.c) ? R.drawable._2131427841_res_0x7f0b0201 : R.drawable._2131427842_res_0x7f0b0202);
        if (koq.b(this.e, i) || a(i2, i)) {
            LogUtil.h("HealthWeight_WeightExpandableListViewAdapter", "getChildView groupPosition or childPosition param exception");
            return view;
        }
        if (koq.d(this.e, i)) {
            cfe cfeVar = this.e.get(i).get(i2);
            if (cfeVar != null) {
                eVar.h.setText(nsj.c(this.c, cfeVar.au(), 1));
            }
            eVar.f10145a.setVisibility(z ? 8 : 0);
            qsj.dIj_(cfeVar, eVar.j);
            d(eVar, i, i2);
            b(cfeVar, eVar, i, i2);
        }
        return view;
    }

    private View dDn_() {
        View inflate = View.inflate(this.c, R.layout.health_data_weight_expandlistview_child_item, null);
        BaseActivity.setViewSafeRegion(false, inflate);
        return inflate;
    }

    private e dDm_(View view) {
        e eVar = new e();
        eVar.j = (ImageView) view.findViewById(R.id.child_item_image);
        eVar.n = (HealthTextView) view.findViewById(R.id.child_item_weight);
        eVar.o = (HealthTextView) view.findViewById(R.id.child_item_weight_unit);
        eVar.b = (ImageView) view.findViewById(R.id.child_item_fat_image);
        eVar.c = (HealthTextView) view.findViewById(R.id.child_item_fat);
        eVar.g = (ImageView) view.findViewById(R.id.child_item_skeletal_muscle_image);
        eVar.i = (HealthTextView) view.findViewById(R.id.child_item_skeletal_muscle);
        eVar.f = (HealthTextView) view.findViewById(R.id.child_item_skeletal_muscle_unit);
        eVar.h = (HealthTextView) view.findViewById(R.id.child_item_time);
        eVar.d = (ImageView) view.findViewById(R.id.child_item_arrow);
        eVar.e = (HealthCheckBox) view.findViewById(R.id.child_item_check_box);
        eVar.f10145a = (HealthDivider) view.findViewById(R.id.child_item_divider);
        view.setTag(eVar);
        return eVar;
    }

    private void d(e eVar, int i, int i2) {
        eVar.e.setOnCheckedChangeListener(null);
        eVar.e.setChecked(false);
        if (this.b) {
            eVar.e.setEnabled(true);
            eVar.e.setVisibility(0);
            eVar.d.setVisibility(8);
            if (koq.b(this.g, i)) {
                LogUtil.h("HealthWeight_WeightExpandableListViewAdapter", "isShowCheckBox groupPosition is out of bounds");
                return;
            }
            List<Boolean> list = this.g.get(i);
            if (koq.b(list, i2)) {
                LogUtil.h("HealthWeight_WeightExpandableListViewAdapter", "isShowCheckBox childPosition is out of bounds");
                return;
            }
            boolean booleanValue = list.get(i2).booleanValue();
            if (booleanValue) {
                this.i.put(String.valueOf(i) + i2, eVar.e);
            }
            eVar.e.setChecked(booleanValue);
            return;
        }
        eVar.e.setEnabled(false);
        eVar.e.setVisibility(8);
        eVar.d.setVisibility(0);
    }

    private boolean a(int i, int i2) {
        if (i < 0) {
            return true;
        }
        return koq.d(this.e, i2) && i > this.e.get(i2).size();
    }

    private void b(cfe cfeVar, e eVar, int i, int i2) {
        if (cfeVar == null) {
            LogUtil.h("HealthWeight_WeightExpandableListViewAdapter", "setViewVisible bean is null");
            return;
        }
        double ax = cfeVar.ax();
        eVar.n.setText(b(ax, cfeVar.getFractionDigitByType(0)));
        eVar.o.setText(b(ax));
        double a2 = cfeVar.a();
        if (cfeVar.isVisible(1, this.j)) {
            eVar.b.setVisibility(0);
            eVar.c.setVisibility(0);
            eVar.c.setText(UnitUtil.e(a2, 2, 1));
        } else {
            eVar.b.setVisibility(8);
            eVar.c.setVisibility(8);
        }
        double aj = cfeVar.aj();
        if (cfeVar.isVisible(10, this.j)) {
            eVar.g.setVisibility(0);
            eVar.i.setVisibility(0);
            eVar.f.setVisibility(0);
            eVar.i.setText(b(aj, 1));
            eVar.f.setText(b(aj));
        } else {
            eVar.g.setVisibility(8);
            eVar.i.setVisibility(8);
            eVar.f.setVisibility(8);
        }
        e(eVar, i, i2);
        c(eVar);
    }

    private void e(final e eVar, final int i, final int i2) {
        eVar.e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.health.adapter.WeightExpandableListViewAdapter$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WeightExpandableListViewAdapter.this.dDo_(i, i2, eVar, compoundButton, z);
            }
        });
    }

    /* synthetic */ void dDo_(int i, int i2, e eVar, CompoundButton compoundButton, boolean z) {
        LogUtil.a("HealthWeight_WeightExpandableListViewAdapter", "holder.mCheckBox isCheck = ", Boolean.valueOf(z), ",groupPosition = ", Integer.valueOf(i), ", childPosition = ", Integer.valueOf(i2));
        String str = String.valueOf(i) + i2;
        if (z) {
            LogUtil.a("HealthWeight_WeightExpandableListViewAdapter", "select checkBox key = ", str);
            this.i.put(str, eVar.e);
        } else {
            this.i.remove(str);
        }
        if (koq.d(this.g, i)) {
            List<Boolean> list = this.g.get(i);
            if (koq.d(list, i2) && this.d != null) {
                list.set(i2, Boolean.valueOf(z));
                Message obtain = Message.obtain();
                obtain.what = -1;
                obtain.obj = Boolean.valueOf(z);
                this.d.sendMessage(obtain);
            }
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    private String b(double d, int i) {
        return UnitUtil.e(UnitUtil.c(d, i), 1, i);
    }

    private String b(double d) {
        return qsj.e(UnitUtil.a(d), false);
    }

    private void c(e eVar) {
        int measuredWidth;
        if (eVar.b.getVisibility() == 8 || eVar.g.getVisibility() == 8) {
            return;
        }
        float f = 14;
        eVar.c.setTextSize(1, f);
        eVar.i.setTextSize(1, f);
        eVar.f.setTextSize(1, f);
        int i = 0;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        eVar.j.measure(makeMeasureSpec, makeMeasureSpec);
        eVar.b.measure(makeMeasureSpec, makeMeasureSpec);
        eVar.g.measure(makeMeasureSpec, makeMeasureSpec);
        eVar.d.measure(makeMeasureSpec, makeMeasureSpec);
        eVar.e.measure(makeMeasureSpec, makeMeasureSpec);
        eVar.h.measure(makeMeasureSpec, makeMeasureSpec);
        if (eVar.e.getVisibility() == 0) {
            measuredWidth = eVar.e.getMeasuredWidth();
        } else {
            measuredWidth = eVar.d.getMeasuredWidth();
        }
        int c = nsn.c(this.c, 16.0f);
        int c2 = nsn.c(this.c, 6.0f);
        int c3 = nsn.c(this.c, 4.0f);
        int n = nsn.n();
        int dimensionPixelSize = this.h.getDimensionPixelSize(R.dimen._2131364635_res_0x7f0a0b1b);
        int dimensionPixelSize2 = this.h.getDimensionPixelSize(R.dimen._2131364634_res_0x7f0a0b1a);
        int measuredWidth2 = eVar.j.getMeasuredWidth();
        int measuredWidth3 = eVar.h.getMeasuredWidth();
        int measuredWidth4 = eVar.b.getMeasuredWidth();
        int measuredWidth5 = eVar.g.getMeasuredWidth();
        while (i <= 4) {
            int i2 = i;
            if (Float.compare(eVar.c.getPaint().measureText(eVar.c.getText().toString()) + eVar.i.getPaint().measureText(eVar.i.getText().toString()) + eVar.f.getPaint().measureText(eVar.f.getText().toString()), (((((((((((((n - dimensionPixelSize) - dimensionPixelSize2) - measuredWidth2) - measuredWidth3) - measuredWidth) - c) - c3) - measuredWidth4) - measuredWidth5) - c2) - c2) - c3) - c3) - c3) == 1) {
                float f2 = 14 - i2;
                eVar.c.setTextSize(1, f2);
                eVar.i.setTextSize(1, f2);
                eVar.f.setTextSize(1, f2);
            }
            i = i2 + 1;
        }
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        if (koq.b(this.e, i)) {
            LogUtil.h("HealthWeight_WeightExpandableListViewAdapter", "getChildrenCount is out of bounds");
            return 0;
        }
        return this.e.get(i).size();
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int i) {
        if (koq.b(this.f10143a, i)) {
            LogUtil.h("HealthWeight_WeightExpandableListViewAdapter", "getGroup is out of bounds");
            return null;
        }
        return this.f10143a.get(i);
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.f10143a.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = View.inflate(this.c, R.layout.health_data_weight_expandlistview_father_item, null);
            bVar = new b();
            bVar.e = (LinearLayout) view.findViewById(R.id.health_data_weight_father_ll);
            bVar.b = (HealthTextView) view.findViewById(R.id.hw_show_health_data_weight_history_listview_father_textview1);
            bVar.c = (HealthTextView) view.findViewById(R.id.hw_show_health_data_weight_history_listview_father_avg_textview);
            bVar.c.setText(this.h.getString(R$string.IDS_hw_health_show_healthdata_weight_average));
            bVar.h = (HealthTextView) view.findViewById(R.id.hw_show_health_data_weight_history_listview_father_textview2);
            bVar.f = (HealthTextView) view.findViewById(R.id.health_data_weight_history_list_view_father_textview2_unit);
            bVar.f10144a = (ImageView) nsy.cMd_(view, R.id.father_img_arrow);
            bVar.d = (HealthDivider) nsy.cMd_(view, R.id.weight_father_divider);
            BaseActivity.setViewSafeRegion(false, bVar.e);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        double d = d(i, bVar);
        a(i, z, bVar, d);
        a(bVar, d, z);
        return view;
    }

    private void a(b bVar, double d, boolean z) {
        int a2 = UnitUtil.a();
        int i = a2 != 1 ? a2 != 3 ? R.plurals._2130903215_res_0x7f0300af : R.plurals._2130903216_res_0x7f0300b0 : R.plurals._2130903105_res_0x7f030041;
        double a3 = UnitUtil.a(d);
        jcf.bED_(bVar.e, nsf.b(R$string.accessibility_weight_history_average, bVar.b.getText(), nsf.a(i, UnitUtil.e(a3, Locale.getDefault()), UnitUtil.e(a3, 1, 1))), z);
    }

    private double d(int i, b bVar) {
        if (koq.b(this.f10143a, i)) {
            ReleaseLogUtil.a("HealthWeight_WeightExpandableListViewAdapter", "getAverageWeight groupPosition ", Integer.valueOf(i), " mFatherList ", this.f10143a);
            return 0.0d;
        }
        String[] strArr = this.f10143a.get(i);
        if (koq.e(strArr, 0)) {
            bVar.b.setText(strArr[0]);
        } else {
            ReleaseLogUtil.a("HealthWeight_WeightExpandableListViewAdapter", "getAverageWeight mTitle stringArray ", strArr);
        }
        if (koq.e(strArr, 1)) {
            return nsn.j(strArr[1]);
        }
        ReleaseLogUtil.a("HealthWeight_WeightExpandableListViewAdapter", "getAverageWeight stringArray ", strArr);
        return 0.0d;
    }

    private void a(int i, boolean z, b bVar, double d) {
        bVar.h.setText(" " + UnitUtil.e(UnitUtil.a(d), 1, 1));
        bVar.f.setText(b(d));
        bVar.d.setVisibility(i == 0 ? 8 : 0);
        bVar.f10144a.setImageResource(z ? R.drawable.ic_health_list_drop_down_arrow_nor : R.drawable.ic_health_list_drop_down_arrow_sel);
        CardConstants.e(bVar.b);
    }

    public boolean e() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public ArrayList<List<Boolean>> b() {
        return this.g;
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        ImageView f10144a;
        HealthTextView b;
        HealthTextView c;
        HealthDivider d;
        LinearLayout e;
        HealthTextView f;
        HealthTextView h;

        private b() {
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        HealthDivider f10145a;
        ImageView b;
        HealthTextView c;
        ImageView d;
        HealthCheckBox e;
        HealthTextView f;
        ImageView g;
        HealthTextView h;
        HealthTextView i;
        ImageView j;
        HealthTextView n;
        HealthTextView o;

        private e() {
        }
    }
}
