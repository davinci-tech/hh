package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.chart.HealthRingChart;
import com.huawei.ui.commonui.chart.HealthRingChartAdapter;
import defpackage.nkz;
import defpackage.nld;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class ActionRingChartView extends LinearLayout {
    private View d;
    private Context e;

    public ActionRingChartView(Context context) {
        this(context, null);
    }

    public ActionRingChartView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionRingChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = context;
        a();
    }

    private void a() {
        this.d = View.inflate(this.e, R.layout.action_ring_chart_layout, this);
    }

    public void setData(double[] dArr) {
        if (dArr == null || dArr.length != 5) {
            LogUtil.h("ActionRingChartView", "datas is null or length is not equal 5");
            return;
        }
        HealthRingChart healthRingChart = (HealthRingChart) this.d.findViewById(R.id.action_ring_view);
        List<nkz> d = d(dArr);
        HealthRingChartAdapter healthRingChartAdapter = new HealthRingChartAdapter(this.e, new nld().c(true).b(false), d);
        healthRingChartAdapter.a(new HealthRingChartAdapter.DataFormatter() { // from class: gfk
            @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
            public final String format(nkz nkzVar) {
                String e;
                e = UnitUtil.e(nkzVar.i(), 2, 0);
                return e;
            }
        });
        healthRingChart.setAdapter(healthRingChartAdapter);
    }

    private List<nkz> d(double[] dArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(ContextCompat.getColor(this.e, R.color._2131298929_res_0x7f090a71)));
        arrayList.add(Integer.valueOf(ContextCompat.getColor(this.e, R.color._2131298927_res_0x7f090a6f)));
        arrayList.add(Integer.valueOf(ContextCompat.getColor(this.e, R.color._2131298925_res_0x7f090a6d)));
        arrayList.add(Integer.valueOf(ContextCompat.getColor(this.e, R.color._2131298931_res_0x7f090a73)));
        arrayList.add(Integer.valueOf(ContextCompat.getColor(this.e, R.color._2131298933_res_0x7f090a75)));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.e, R.color._2131298928_res_0x7f090a70)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.e, R.color._2131298926_res_0x7f090a6e)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.e, R.color._2131298924_res_0x7f090a6c)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.e, R.color._2131298930_res_0x7f090a72)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.e, R.color._2131298932_res_0x7f090a74)));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(this.e.getResources().getString(R$string.IDS_fitness_perfect));
        arrayList3.add(this.e.getResources().getString(R$string.IDS_fitness_great));
        arrayList3.add(this.e.getResources().getString(R$string.IDS_fitness_good));
        arrayList3.add(this.e.getResources().getString(R$string.IDS_fitness_comeon));
        arrayList3.add(this.e.getResources().getString(R$string.IDS_fitness_undone));
        ArrayList arrayList4 = new ArrayList();
        for (int i = 0; i < arrayList3.size(); i++) {
            arrayList4.add(new nkz((String) arrayList3.get(i), dArr[i], ((Integer) arrayList.get(i)).intValue(), ((Integer) arrayList2.get(i)).intValue()));
        }
        return arrayList4;
    }
}
