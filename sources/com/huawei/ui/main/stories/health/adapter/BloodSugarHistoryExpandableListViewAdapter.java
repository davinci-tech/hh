package com.huawei.ui.main.stories.health.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.view.timeperiod.BloodSugarTimePeriod;
import com.huawei.ui.main.R$string;
import defpackage.koq;
import defpackage.nsn;
import defpackage.qjv;
import defpackage.qkb;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class BloodSugarHistoryExpandableListViewAdapter extends BaseExpandableListAdapter {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<List<Boolean>> f10110a;
    private int b;
    private Drawable c;
    private int d;
    private int e;
    private Drawable f;
    private int g;
    private Context h;
    private Drawable i;
    private int j;
    private List<qkb> k;
    private Drawable l;
    private int m;
    private int n;
    private int o;
    private int p = 0;
    private OnItemClickListener q;
    private String r;
    private String s;

    public interface OnItemClickListener {
        void onItemClickListener(int i, int i2);
    }

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
        return false;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public BloodSugarHistoryExpandableListViewAdapter(Context context) {
        e(context);
    }

    private static void d(Context context, int i, boolean z, HealthTextView healthTextView, String str) {
        BloodSugarTimePeriod timePeriodByCode = BloodSugarTimePeriod.getTimePeriodByCode(i);
        if (timePeriodByCode == null) {
            return;
        }
        String string = context.getResources().getString(timePeriodByCode.getTimePeriodNameRes());
        if (!z) {
            string = string + " | " + str;
        }
        healthTextView.setText(string);
    }

    private void e(Context context) {
        this.h = context;
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen._2131363770_res_0x7f0a07ba);
        this.n = dimensionPixelSize;
        this.e = dimensionPixelSize;
        this.o = resources.getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        this.s = resources.getString(R$string.IDS_hw_health_show_healthdata_bloodsugar_mmol);
        this.r = resources.getString(R$string.IDS_hw_show_healthdata_bloodsugar_to_be_confirmed);
        this.l = resources.getDrawable(R.drawable._2131427637_res_0x7f0b0135);
        this.i = resources.getDrawable(R.drawable._2131427635_res_0x7f0b0133);
        this.f = resources.getDrawable(R.drawable._2131427636_res_0x7f0b0134);
        this.c = resources.getDrawable(R.color._2131296666_res_0x7f09019a);
        this.g = resources.getColor(R.color._2131296797_res_0x7f09021d);
        this.j = resources.getColor(R.color._2131296799_res_0x7f09021f);
        this.d = resources.getColor(R.color._2131296795_res_0x7f09021b);
        this.b = resources.getDimensionPixelSize(R.dimen._2131363790_res_0x7f0a07ce);
        this.m = resources.getDimensionPixelSize(R.dimen._2131363775_res_0x7f0a07bf);
        this.f10110a = new ArrayList<>();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        List<qkb> list = this.k;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        if (koq.b(this.k, i)) {
            LogUtil.e("BloodSugarHistoryExpand", "getChildrenCount is out of bounds");
            return 0;
        }
        ArrayList<qkb.e> d2 = this.k.get(i).d();
        if (d2 == null) {
            return 0;
        }
        return d2.size();
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public qkb getGroup(int i) {
        if (koq.d(this.k, i)) {
            return this.k.get(i);
        }
        LogUtil.e("BloodSugarHistoryExpand", "getGroup is out of bounds");
        return new qkb();
    }

    @Override // android.widget.ExpandableListAdapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public qkb.e getChild(int i, int i2) {
        if (koq.d(this.k, i)) {
            ArrayList<qkb.e> d2 = this.k.get(i).d();
            if (koq.d(d2, i2)) {
                return d2.get(i2);
            }
            LogUtil.e("BloodSugarHistoryExpand", "getChild is out of bounds");
            return new qkb.e();
        }
        LogUtil.e("BloodSugarHistoryExpand", "getChild is out of bounds");
        return new qkb.e();
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        View view2;
        d dVar;
        String quantityString;
        if (view == null) {
            dVar = new d();
            view2 = LayoutInflater.from(this.h).inflate(nsn.s() ? R.layout.layout_blood_sugar_history_father_item_large : R.layout.layout_blood_sugar_history_father_item, (ViewGroup) null);
            dCc_(view2, dVar);
            view2.setTag(dVar);
        } else {
            Object tag = view.getTag();
            if (!(tag instanceof d)) {
                return view;
            }
            d dVar2 = (d) tag;
            view2 = view;
            dVar = dVar2;
        }
        d(dVar, i, z);
        dVar.d.setVisibility(0);
        if (koq.b(this.k, i)) {
            return view2;
        }
        qkb qkbVar = this.k.get(i);
        ArrayList<qkb.e> d2 = qkbVar.d();
        HealthTextView healthTextView = dVar.d;
        if (d2 == null) {
            quantityString = this.h.getResources().getQuantityString(R.plurals._2130903345_res_0x7f030131, 0, 0);
        } else {
            quantityString = this.h.getResources().getQuantityString(R.plurals._2130903345_res_0x7f030131, d2.size(), Integer.valueOf(d2.size()));
        }
        healthTextView.setText(quantityString);
        dVar.e.setText(DateFormatUtil.d(qkbVar.a(), DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMD));
        return view2;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            b bVar2 = new b();
            View inflate = LayoutInflater.from(this.h).inflate(nsn.s() ? R.layout.layout_blood_sugar_history_child_item_large : R.layout.layout_blood_sugar_history_child_item, (ViewGroup) null);
            dCb_(bVar2, inflate);
            inflate.setTag(bVar2);
            bVar = bVar2;
            view = inflate;
        } else {
            Object tag = view.getTag();
            if (tag instanceof b) {
                bVar = (b) tag;
            }
            return view;
        }
        if (koq.b(this.k, i)) {
            return view;
        }
        ArrayList<qkb.e> d2 = this.k.get(i).d();
        if (koq.b(d2, i2)) {
            return view;
        }
        b(d2, bVar, i2, i, z);
        return view;
    }

    private void d(d dVar, int i, boolean z) {
        ViewGroup.LayoutParams layoutParams = dVar.j.getLayoutParams();
        if (i == 0) {
            layoutParams.height = this.n;
        } else {
            layoutParams.height = this.o;
        }
        dVar.j.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = dVar.b.getLayoutParams();
        if (layoutParams2 instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) layoutParams2;
            if (z) {
                dVar.c.setImageResource(R.drawable.ic_health_list_drop_down_arrow_sel);
                dVar.b.setBackground(this.l);
                dVar.f10113a.setVisibility(0);
                layoutParams3.height = this.m + this.e;
                layoutParams3.bottomMargin = 0;
            } else {
                dVar.c.setImageResource(R.drawable.ic_health_list_drop_down_arrow_nor);
                dVar.b.setBackground(this.i);
                dVar.f10113a.setVisibility(8);
                int i2 = this.m;
                int i3 = this.e;
                layoutParams3.height = i2 + i3 + i3;
                if (i == this.k.size() - 1) {
                    layoutParams3.bottomMargin = this.o;
                } else {
                    layoutParams3.bottomMargin = 0;
                }
            }
            dVar.b.setLayoutParams(layoutParams3);
        }
    }

    private void c(b bVar, int i, int i2, qkb.e eVar) {
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            bVar.h.setScaleX(-1.0f);
        }
        if (eVar.d()) {
            bVar.h.setImageResource(R.drawable._2131429769_res_0x7f0b0989);
        } else {
            bVar.h.setImageResource(R.drawable._2131429770_res_0x7f0b098a);
        }
        if (this.p != 1) {
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                bVar.f10112a.setScaleX(-1.0f);
            }
            bVar.f10112a.setVisibility(0);
            bVar.d.setVisibility(8);
            return;
        }
        bVar.f10112a.setVisibility(8);
        bVar.d.setVisibility(0);
        bVar.d.setOnCheckedChangeListener(null);
        if (koq.d(this.f10110a, i2)) {
            List<Boolean> list = this.f10110a.get(i2);
            if (koq.d(list, i)) {
                bVar.d.setChecked(list.get(i).booleanValue());
            }
        }
    }

    private void b(ArrayList<qkb.e> arrayList, b bVar, final int i, final int i2, boolean z) {
        qkb.e eVar = arrayList.get(i);
        double f = eVar.f();
        bVar.e.setText(this.h.getResources().getQuantityString(R.plurals._2130903076_res_0x7f030024, (int) f, UnitUtil.e(f, 1, 1)));
        String d2 = DateFormatUtil.d(eVar.m(), DateFormatUtil.DateFormatType.DATE_FORMAT_HOUR_MINUTE);
        d(this.h, eVar.a(), eVar.n(), bVar.g, this.r);
        bVar.b.setText(d2);
        d(f, eVar, bVar);
        c(bVar, i, i2, eVar);
        e(bVar, i2, z);
        bVar.d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.health.adapter.BloodSugarHistoryExpandableListViewAdapter$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                BloodSugarHistoryExpandableListViewAdapter.this.dCd_(i2, i, compoundButton, z2);
            }
        });
    }

    /* synthetic */ void dCd_(int i, int i2, CompoundButton compoundButton, boolean z) {
        OnItemClickListener onItemClickListener = this.q;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClickListener(i, i2);
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    private void e(b bVar, int i, boolean z) {
        if (z) {
            bVar.c.setVisibility(8);
            bVar.i.setBackground(this.f);
        } else {
            bVar.c.setVisibility(0);
            bVar.i.setBackground(this.c);
        }
        ViewGroup.LayoutParams layoutParams = bVar.i.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            if (z && i == this.k.size() - 1) {
                layoutParams2.bottomMargin = this.o;
            } else {
                layoutParams2.bottomMargin = 0;
            }
            if (z) {
                layoutParams2.height = this.b + this.e;
            } else {
                layoutParams2.height = this.b;
            }
            bVar.i.setLayoutParams(layoutParams2);
        }
    }

    private void d(double d2, qkb.e eVar, b bVar) {
        Map<String, String> a2 = qjv.a(this.h, eVar.a(), (float) d2);
        bVar.f.setText(a2.get("HEALTH_BLOOD_SUGAR_LEVEL_DESC"));
        String str = a2.get("HEALTH_BLOOD_SUGAR_LEVEL_KEY");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!str.equals(String.valueOf(1001)) && !str.equals(String.valueOf(1002))) {
            if (str.equals(String.valueOf(1004)) || str.equals(String.valueOf(1005)) || str.equals(String.valueOf(1006))) {
                bVar.f.setTextColor(this.d);
                return;
            } else {
                bVar.f.setTextColor(this.j);
                return;
            }
        }
        bVar.f.setTextColor(this.g);
    }

    public void b(int i) {
        if (i == 1 && this.p == 0) {
            a();
        }
        this.p = i;
        notifyDataSetChanged();
    }

    public void d() {
        Iterator<List<Boolean>> it = this.f10110a.iterator();
        while (it.hasNext()) {
            List<Boolean> next = it.next();
            for (int i = 0; i < next.size(); i++) {
                next.set(i, true);
            }
        }
        notifyDataSetChanged();
    }

    public void e() {
        Iterator<List<Boolean>> it = this.f10110a.iterator();
        while (it.hasNext()) {
            List<Boolean> next = it.next();
            for (int i = 0; i < next.size(); i++) {
                next.set(i, false);
            }
        }
        notifyDataSetChanged();
    }

    public void a() {
        ArrayList<List<Boolean>> arrayList = this.f10110a;
        if (arrayList == null) {
            this.f10110a = new ArrayList<>();
        } else {
            arrayList.clear();
        }
        if (koq.b(this.k)) {
            return;
        }
        for (qkb qkbVar : this.k) {
            if (qkbVar != null) {
                ArrayList<qkb.e> d2 = qkbVar.d();
                if (d2 != null) {
                    ArrayList arrayList2 = new ArrayList();
                    for (int i = 0; i < d2.size(); i++) {
                        arrayList2.add(false);
                    }
                    this.f10110a.add(arrayList2);
                } else {
                    this.f10110a.add(new ArrayList());
                }
            }
        }
    }

    public List<qkb> c() {
        return this.k;
    }

    public void d(List<qkb> list) {
        if (list == null) {
            LogUtil.e("BloodSugarHistoryExpand", "setList list is null");
            return;
        }
        this.k = new ArrayList(list);
        a();
        notifyDataSetChanged();
    }

    public ArrayList<List<Boolean>> b() {
        return this.f10110a;
    }

    public void e(ArrayList<List<Boolean>> arrayList) {
        if (arrayList == null) {
            LogUtil.e("BloodSugarHistoryExpand", "setCheckList checkList is null");
        } else {
            this.f10110a = new ArrayList<>(arrayList);
            notifyDataSetChanged();
        }
    }

    private void dCc_(View view, d dVar) {
        dVar.b = view.findViewById(R.id.hw_show_blood_sugar_history_father_bg);
        dVar.e = (HealthTextView) view.findViewById(R.id.hw_show_blood_sugar_history_father_date);
        dVar.d = (HealthTextView) view.findViewById(R.id.hw_show_blood_sugar_history_father_count);
        dVar.c = (ImageView) view.findViewById(R.id.hw_show_blood_sugar_history_father_arrow);
        dVar.j = view.findViewById(R.id.hw_show_blood_sugar_history_father_line);
        dVar.f10113a = view.findViewById(R.id.hw_show_blood_sugar_history_bottom_image_interval);
    }

    private void dCb_(b bVar, View view) {
        bVar.i = view.findViewById(R.id.hw_show_blood_sugar_history_child_bg);
        bVar.h = (ImageView) view.findViewById(R.id.hw_show_blood_sugar_history_child_left_img);
        bVar.e = (HealthTextView) view.findViewById(R.id.hw_show_blood_sugar_history_child_value);
        bVar.f = (HealthTextView) view.findViewById(R.id.hw_show_blood_sugar_history_child_status_text);
        bVar.g = (HealthTextView) view.findViewById(R.id.hw_show_blood_sugar_history_child_period);
        bVar.b = (HealthTextView) view.findViewById(R.id.hw_show_blood_sugar_history_child_time);
        bVar.f10112a = (ImageView) view.findViewById(R.id.hw_blood_sugar_history_child_right_arrow);
        bVar.d = (HealthCheckBox) view.findViewById(R.id.hw_blood_sugar_history_child_right_check);
        bVar.c = (HealthDivider) view.findViewById(R.id.hw_blood_sugar_history_child_middle_line);
    }

    public void d(OnItemClickListener onItemClickListener) {
        this.q = onItemClickListener;
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private View f10113a;
        private View b;
        private ImageView c;
        private HealthTextView d;
        private HealthTextView e;
        private View j;

        private d() {
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f10112a;
        private HealthTextView b;
        private HealthDivider c;
        private HealthCheckBox d;
        private HealthTextView e;
        private HealthTextView f;
        private HealthTextView g;
        private ImageView h;
        private View i;

        private b() {
        }
    }
}
