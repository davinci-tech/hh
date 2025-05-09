package com.huawei.health.device.ui.measure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import defpackage.cfi;
import defpackage.koq;
import java.util.List;

/* loaded from: classes3.dex */
public class WeightResultConfirmAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f2254a;
    private int b = -1;
    private int d = -1;
    private List<cfi> e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public WeightResultConfirmAdapter(Context context, List<cfi> list) {
        this.e = list;
        this.f2254a = context;
    }

    public void b(List<cfi> list) {
        this.e = list;
        notifyDataSetChanged();
    }

    public void a(int i) {
        this.b = i;
        this.d = i;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<cfi> list = this.e;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (koq.b(this.e, i)) {
            LogUtil.h("PluginDevice_WeightResultConfrimAdapter", "getItem position error.");
            return null;
        }
        return this.e.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view2;
        b bVar;
        if (i < 0 || i >= this.e.size()) {
            LogUtil.h("PluginDevice_WeightResultConfrimAdapter", "getView position error.");
            return null;
        }
        if (view == null) {
            bVar = new b();
            view2 = LayoutInflater.from(this.f2254a).inflate(R.layout.weight_measure_result_confrim_dialog_list_item, (ViewGroup) null);
            bVar.c = (HealthTextView) view2.findViewById(R.id.weight_measure_reslut_confrim_item_user_text);
            bVar.d = (HealthRadioButton) view2.findViewById(R.id.weight_measure_reslut_confrim_item_radiobutton);
            view2.setTag(bVar);
        } else if (view.getTag() instanceof b) {
            view2 = view;
            bVar = (b) view.getTag();
        } else {
            LogUtil.h("PluginDevice_WeightResultConfrimAdapter", "getView viewHolder is null.");
            return view;
        }
        if (this.b == i) {
            bVar.c.setText(this.f2254a.getString(R.string.IDS_hw_device_hygride_current_measure_user_tips, this.e.get(i).h()));
        } else {
            bVar.c.setText(this.e.get(i).h());
        }
        bVar.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.adapter.WeightResultConfirmAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view3) {
                int i2 = WeightResultConfirmAdapter.this.d;
                int i3 = i;
                if (i2 != i3) {
                    WeightResultConfirmAdapter.this.d = i3;
                } else {
                    WeightResultConfirmAdapter weightResultConfirmAdapter = WeightResultConfirmAdapter.this;
                    weightResultConfirmAdapter.d = weightResultConfirmAdapter.b;
                }
                WeightResultConfirmAdapter.this.notifyDataSetChanged();
                ViewClickInstrumentation.clickOnView(view3);
            }
        });
        if (i == this.d) {
            bVar.d.setChecked(true);
        } else {
            bVar.d.setChecked(false);
        }
        return view2;
    }

    public int d() {
        LogUtil.a("PluginDevice_WeightResultConfrimAdapter", "getSelectPosition mTempPosition:", Integer.valueOf(this.d));
        return this.d;
    }

    public static class b {
        private HealthTextView c;
        private HealthRadioButton d;
    }
}
