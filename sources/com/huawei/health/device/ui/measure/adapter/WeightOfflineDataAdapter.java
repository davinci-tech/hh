package com.huawei.health.device.ui.measure.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ckm;
import defpackage.koq;
import health.compact.a.UnitUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes3.dex */
public class WeightOfflineDataAdapter extends BaseAdapter {
    private Context b;
    private Handler e;
    private ArrayList<ckm> d = new ArrayList<>(10);

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<Boolean> f2251a = new ArrayList<>(10);

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public WeightOfflineDataAdapter(Context context, ArrayList<ckm> arrayList, Handler handler) {
        this.b = context;
        this.d.addAll(arrayList);
        this.e = handler;
    }

    public void e(ArrayList<ckm> arrayList) {
        ArrayList<ckm> arrayList2 = this.d;
        if (arrayList2 == null || this.f2251a == null) {
            LogUtil.a("WeightOfflineDataAdapter", "setData(), mList or mCheckList is null.");
            return;
        }
        arrayList2.clear();
        this.f2251a.clear();
        this.d.addAll(arrayList);
        ArrayList arrayList3 = new ArrayList(this.d.size());
        for (int i = 0; i < this.d.size(); i++) {
            arrayList3.add(false);
        }
        this.f2251a.addAll(arrayList3);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList<ckm> arrayList = this.d;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (this.d == null) {
            LogUtil.a("WeightOfflineDataAdapter", "getItem(), mList is null.");
            return null;
        }
        if (i < 0 || i > r0.size() - 1) {
            LogUtil.a("WeightOfflineDataAdapter", "getItem() Out of bounds exception... ");
            return null;
        }
        return this.d.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view2;
        final d dVar;
        ArrayList<ckm> arrayList = this.d;
        if (arrayList == null || this.f2251a == null) {
            LogUtil.a("WeightOfflineDataAdapter", "getView() mList or mCheckList is null.");
            return null;
        }
        boolean z = true;
        if (i <= arrayList.size() - 1 && i <= this.f2251a.size() - 1) {
            z = false;
        }
        if (i < 0 || z) {
            LogUtil.a("WeightOfflineDataAdapter", "getView() position Out of bounds exception... ");
            return null;
        }
        if (view == null) {
            dVar = new d();
            view2 = LayoutInflater.from(this.b).inflate(R.layout.weight_offline_data_sync_item, (ViewGroup) null);
            dVar.f2253a = (HealthTextView) view2.findViewById(R.id.weight_offline_data_item_weight_value);
            dVar.b = (HealthTextView) view2.findViewById(R.id.weight_offline_data_item_measure_time);
            dVar.d = (HealthCheckBox) view2.findViewById(R.id.weight_offline_data_list_checkbox);
            dVar.e = (HealthDivider) view2.findViewById(R.id.weight_conflict_list_divider);
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
        dVar.b.setText(this.b.getResources().getString(R.string.IDS_device_measure_time, e(this.d.get(i).getStartTime())));
        dVar.f2253a.setText(this.b.getResources().getString(R.string.IDS_device_need_claim_weight_data, b(i)));
        dVar.d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.health.device.ui.measure.adapter.WeightOfflineDataAdapter.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                dVar.d.setChecked(z2);
                WeightOfflineDataAdapter.this.f2251a.set(i, Boolean.valueOf(z2));
                Message obtain = Message.obtain();
                obtain.what = -1;
                obtain.obj = Boolean.valueOf(z2);
                WeightOfflineDataAdapter.this.e.sendMessage(obtain);
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        dVar.d.setChecked(this.f2251a.get(i).booleanValue());
        c(i, dVar);
        return view2;
    }

    private String b(int i) {
        if (koq.b(this.d, i)) {
            LogUtil.b("WeightOfflineDataAdapter", "getWeightValue isOutOfBounds true!");
            return "";
        }
        if (UnitUtil.h()) {
            return UnitUtil.e(UnitUtil.h(this.d.get(i).getWeight()), 1, 1) + this.b.getResources().getString(R.string.IDS_device_measure_weight_value_unit_eng);
        }
        return UnitUtil.e(this.d.get(i).getWeight(), 1, 1) + this.b.getResources().getString(R.string.IDS_device_measure_weight_value_unit);
    }

    private void c(int i, d dVar) {
        if (i == this.d.size() - 1) {
            dVar.e.setVisibility(8);
        } else {
            dVar.e.setVisibility(0);
        }
    }

    public ArrayList<Boolean> d() {
        return this.f2251a;
    }

    public int c() {
        ArrayList<Boolean> arrayList = this.f2251a;
        int i = 0;
        if (arrayList != null && arrayList.size() != 0) {
            Iterator<Boolean> it = this.f2251a.iterator();
            while (it.hasNext()) {
                if (it.next().booleanValue()) {
                    i++;
                }
            }
        }
        return i;
    }

    private String e(long j) {
        return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyy-MM-dd")).format(Long.valueOf(j)) + " " + DateFormat.getTimeFormat(this.b.getApplicationContext()).format(Long.valueOf(j));
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f2253a;
        HealthTextView b;
        HealthCheckBox d;
        HealthDivider e;

        private d() {
        }
    }
}
