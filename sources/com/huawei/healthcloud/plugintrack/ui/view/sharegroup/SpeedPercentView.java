package com.huawei.healthcloud.plugintrack.ui.view.sharegroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.view.sharegroup.SpeedPercentView;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.chart.HealthRingChart;
import com.huawei.ui.commonui.chart.HealthRingChartAdapter;
import defpackage.hji;
import defpackage.hjw;
import defpackage.koq;
import defpackage.nkz;
import defpackage.nld;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class SpeedPercentView extends LinearLayout {
    private Context b;
    private View c;

    public SpeedPercentView(Context context) {
        this(context, null);
    }

    public SpeedPercentView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SpeedPercentView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = context;
        b();
    }

    private void b() {
        this.c = View.inflate(this.b, R.layout.speed_percent_chart_show_layout, this);
    }

    public void setData(hjw hjwVar) {
        List<Integer> e;
        char c;
        if (hjwVar == null || hjwVar.e() == null) {
            LogUtil.b("Track_SpeedPercentView", "simplify is null");
            return;
        }
        boolean a2 = hji.a(hjwVar.e().requestSportType(), hjwVar.e().requestSportDataSource());
        LogUtil.a("Track_SpeedPercentView", "isResistance", Boolean.valueOf(a2), "data source", Integer.valueOf(hjwVar.e().requestSportDataSource()), "requestSportType", Integer.valueOf(hjwVar.e().requestSportType()));
        ArrayList<Float> arrayList = new ArrayList<>(16);
        b(arrayList, a2);
        List<Integer> arrayList2 = new ArrayList<>();
        ArrayList<Integer> arrayList3 = new ArrayList<>(16);
        if (a2) {
            e(hjwVar, arrayList3);
            List<Double> arrayList4 = new ArrayList<>(5);
            a(arrayList3, arrayList4);
            List<Integer> requestResistanceList = hjwVar.d().requestResistanceList();
            e = hji.d(arrayList4, requestResistanceList, true);
            arrayList2 = hji.d(arrayList4, requestResistanceList, false);
            c = 1;
        } else {
            e = hji.e(hjwVar);
            c = 2;
        }
        if (koq.b(e) || (koq.b(arrayList2) && c == 1)) {
            LogUtil.h("Track_SpeedPercentView", "rankedList simplify is empty");
            return;
        }
        HealthRingChart healthRingChart = (HealthRingChart) this.c.findViewById(R.id.moving_speed_circle);
        if (!a2) {
            arrayList2 = e;
        }
        e(a2, healthRingChart, a(arrayList, arrayList2, arrayList3));
    }

    private void e(boolean z, HealthRingChart healthRingChart, List<nkz> list) {
        HealthRingChartAdapter.DataFormatter dataFormatter;
        HealthRingChartAdapter healthRingChartAdapter = new HealthRingChartAdapter(this.b, new nld().c(false).b(true), list);
        if (z) {
            dataFormatter = new HealthRingChartAdapter.DataFormatter() { // from class: hmc
                @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
                public final String format(nkz nkzVar) {
                    return SpeedPercentView.this.e(nkzVar);
                }
            };
        } else {
            dataFormatter = new HealthRingChartAdapter.DataFormatter() { // from class: hmb
                @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
                public final String format(nkz nkzVar) {
                    String e;
                    e = UnitUtil.e(nkzVar.i(), 2, 0);
                    return e;
                }
            };
        }
        healthRingChartAdapter.a(dataFormatter);
        healthRingChart.setAdapter(healthRingChartAdapter);
        if (LanguageUtil.j(this.b)) {
            healthRingChart.setDesc(this.b.getResources().getString(z ? R.string._2130843485_res_0x7f02175d : R.string._2130843164_res_0x7f02161c));
        }
    }

    public /* synthetic */ String e(nkz nkzVar) {
        return this.b.getResources().getQuantityString(R.plurals._2130903289_res_0x7f0300f9, (int) nkzVar.i(), Integer.valueOf((int) nkzVar.i()));
    }

    private void a(ArrayList<Integer> arrayList, List<Double> list) {
        if (koq.b(arrayList, 8)) {
            LogUtil.h("Track_SpeedPercentView", "initRankList resistanceLevels.size < 9.");
            return;
        }
        list.add(Double.valueOf(arrayList.get(0).intValue()));
        list.add(Double.valueOf(arrayList.get(2).intValue()));
        list.add(Double.valueOf(arrayList.get(4).intValue()));
        list.add(Double.valueOf(arrayList.get(6).intValue()));
        list.add(Double.valueOf(arrayList.get(8).intValue()));
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(defpackage.hjw r5, java.util.ArrayList<java.lang.Integer> r6) {
        /*
            r4 = this;
            com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify r5 = r5.e()
            java.util.Map r5 = r5.requestExtendDataMap()
            java.lang.String r0 = "minRes"
            boolean r1 = r5.containsKey(r0)
            r2 = 1
            r3 = 15
            if (r1 == 0) goto L3c
            java.lang.Object r0 = r5.get(r0)     // Catch: java.lang.NumberFormatException -> L2c
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.NumberFormatException -> L2c
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L2c
            java.lang.String r1 = "maxRes"
            java.lang.Object r5 = r5.get(r1)     // Catch: java.lang.NumberFormatException -> L2a
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.NumberFormatException -> L2a
            int r3 = java.lang.Integer.parseInt(r5)     // Catch: java.lang.NumberFormatException -> L2a
            goto L3d
        L2a:
            r5 = move-exception
            goto L2e
        L2c:
            r5 = move-exception
            r0 = r2
        L2e:
            java.lang.String r5 = health.compact.a.LogAnonymous.b(r5)
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            java.lang.String r1 = "Track_SpeedPercentView"
            com.huawei.hwlogsmodel.LogUtil.b(r1, r5)
            goto L3d
        L3c:
            r0 = r2
        L3d:
            int r5 = r3 % 5
            if (r5 == 0) goto L44
            int r5 = 5 - r5
            int r3 = r3 + r5
        L44:
            if (r0 != 0) goto L47
            r0 = r2
        L47:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)
            r6.add(r5)
            int r5 = r3 / 5
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            r6.add(r0)
            int r0 = r5 + 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6.add(r0)
            int r0 = r5 * 2
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            r6.add(r1)
            int r0 = r0 + r2
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6.add(r0)
            int r0 = r5 * 3
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            r6.add(r1)
            int r0 = r0 + r2
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6.add(r0)
            int r5 = r5 * 4
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            r6.add(r0)
            int r5 = r5 + r2
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r6.add(r5)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)
            r6.add(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.healthcloud.plugintrack.ui.view.sharegroup.SpeedPercentView.e(hjw, java.util.ArrayList):void");
    }

    private List<nkz> a(List<Float> list, List<Integer> list2, List<Integer> list3) {
        ArrayList arrayList = new ArrayList();
        c(arrayList);
        ArrayList arrayList2 = new ArrayList();
        e(arrayList2);
        ArrayList arrayList3 = new ArrayList();
        c(list, list3, arrayList3);
        ArrayList arrayList4 = new ArrayList();
        int size = list2.size();
        int i = 0;
        while (i < size) {
            arrayList4.add(new nkz(i < arrayList3.size() ? arrayList3.get(i) : "", list2.get(i).intValue(), i < arrayList.size() ? arrayList.get(i).intValue() : 0, i < arrayList2.size() ? arrayList2.get(i).intValue() : 0));
            i++;
        }
        return arrayList4;
    }

    private void e(List<Integer> list) {
        list.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298928_res_0x7f090a70)));
        list.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298926_res_0x7f090a6e)));
        list.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298924_res_0x7f090a6c)));
        list.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298930_res_0x7f090a72)));
        list.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298932_res_0x7f090a74)));
    }

    private void c(List<Integer> list) {
        list.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298929_res_0x7f090a71)));
        list.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298927_res_0x7f090a6f)));
        list.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298925_res_0x7f090a6d)));
        list.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298931_res_0x7f090a73)));
        list.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298933_res_0x7f090a75)));
    }

    private void c(List<Float> list, List<Integer> list2, List<String> list3) {
        if (list2.size() != 0) {
            for (int size = list2.size() / 2; size > 0; size--) {
                int i = size * 2;
                list3.add(this.b.getResources().getString(R.string._2130843658_res_0x7f02180a, UnitUtil.e(list2.get(i - 2).intValue(), 1, 0), UnitUtil.e(list2.get(i - 1).intValue(), 1, 0)));
            }
            return;
        }
        if (koq.b(list, 3)) {
            LogUtil.h("Track_SpeedPercentView", "initTitles init.size  < 4.");
            return;
        }
        list3.add(String.format(this.b.getResources().getString(R.string._2130843166_res_0x7f02161e), 5, UnitUtil.e(list.get(3).floatValue(), 1, 1)));
        list3.add(String.format(this.b.getResources().getString(R.string._2130843167_res_0x7f02161f), 4, UnitUtil.e(list.get(2).floatValue(), 1, 1), UnitUtil.e(list.get(3).floatValue(), 1, 1)));
        list3.add(String.format(this.b.getResources().getString(R.string._2130843167_res_0x7f02161f), 3, UnitUtil.e(list.get(1).floatValue(), 1, 1), UnitUtil.e(list.get(2).floatValue(), 1, 1)));
        list3.add(String.format(this.b.getResources().getString(R.string._2130843167_res_0x7f02161f), 2, UnitUtil.e(list.get(0).floatValue(), 1, 1), UnitUtil.e(list.get(1).floatValue(), 1, 1)));
        list3.add(String.format(this.b.getResources().getString(R.string._2130843168_res_0x7f021620), 1, UnitUtil.e(list.get(0).floatValue(), 1, 1)));
    }

    private void b(ArrayList<Float> arrayList, boolean z) {
        Float valueOf = Float.valueOf(3.0f);
        if (!z) {
            if (UnitUtil.h()) {
                arrayList.add(Float.valueOf((float) UnitUtil.e(1.0d, 0)));
                arrayList.add(Float.valueOf((float) UnitUtil.e(3.0d, 0)));
                arrayList.add(Float.valueOf((float) UnitUtil.e(5.0d, 0)));
                arrayList.add(Float.valueOf((float) UnitUtil.e(7.0d, 0)));
                return;
            }
            arrayList.add(Float.valueOf(1.0f));
            arrayList.add(valueOf);
            arrayList.add(Float.valueOf(5.0f));
            arrayList.add(Float.valueOf(7.0f));
            return;
        }
        arrayList.add(valueOf);
        arrayList.add(Float.valueOf(6.0f));
        arrayList.add(Float.valueOf(9.0f));
        arrayList.add(Float.valueOf(12.0f));
    }
}
