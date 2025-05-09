package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.location.LocationRequestCompat;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.SleepManagementLineChart;
import defpackage.edf;
import defpackage.edg;
import defpackage.eef;
import defpackage.eel;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nmz;
import defpackage.nnx;
import defpackage.nov;
import defpackage.now;
import defpackage.nru;
import defpackage.nsi;
import health.compact.a.CommonUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionScoringMgmt extends SectionScoring {
    private Context aa;
    private HealthTextView ab;
    private SleepManagementLineChart ac;
    private eef ad;
    private FrameLayout v;
    private HwHealthBarChart w;

    private boolean d(int i) {
        return i == 5;
    }

    private int e(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
                return 0;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return 1;
            default:
                return i;
        }
    }

    public SectionScoringMgmt(Context context) {
        this(context, null);
    }

    public SectionScoringMgmt(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionScoringMgmt(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.SectionScoring, com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionScoringMgmt", "onCreateView");
        this.aa = context;
        View onCreateView = super.onCreateView(context);
        this.ac = (SleepManagementLineChart) onCreateView.findViewById(R.id.line_chart);
        this.w = (HwHealthBarChart) onCreateView.findViewById(R.id.bar_chart);
        this.ab = (HealthTextView) onCreateView.findViewById(R.id.chart_title_date);
        this.v = (FrameLayout) onCreateView.findViewById(R.id.chart_linear_layout);
        return onCreateView;
    }

    @Override // com.huawei.health.knit.section.view.SectionScoring, com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionScoringMgmt", "bindParamsToView");
        this.u.setVisibility(8);
        this.e.setVisibility(8);
        this.c = false;
        setLayoutVisible(hashMap);
        setScoringContent(hashMap);
        setSuggestContent(hashMap);
        setCommonContent(hashMap);
        setLevelContent(hashMap);
        setDetectAbnormal(hashMap);
        setChartView(hashMap);
    }

    @Override // com.huawei.health.knit.section.view.SectionScoring
    protected void setLayoutVisible(Map<String, Object> map) {
        Object obj = map.get("IS_PILLOW");
        this.i = nru.d((Map) map, "IS_DATA_TYPE_DAY", false);
        b();
        if (obj instanceof Boolean) {
            this.j = ((Boolean) obj).booleanValue();
            d();
            return;
        }
        Object obj2 = map.get("IS_CORE_SLEEP");
        if (obj2 instanceof Boolean) {
            b(((Boolean) obj2).booleanValue());
            this.x.setVisibility(8);
            this.f2725a.setVisibility(0);
        }
        Object obj3 = map.get("IS_PHONE_SLEEP");
        if (obj3 instanceof Boolean) {
            e(((Boolean) obj3).booleanValue());
            this.x.setVisibility(8);
            this.f2725a.setVisibility(0);
        }
        c();
    }

    protected void c() {
        if (this.r == null || this.t == null || this.s == null || this.r.getVisibility() == 8) {
            return;
        }
        if (this.i) {
            this.r.setPadding((int) getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532), 0, 0, 0);
        } else {
            this.r.setPadding(0, 0, 0, 0);
        }
    }

    private void setChartView(HashMap<String, Object> hashMap) {
        Object obj = hashMap.get("SHOW_VALUE");
        if (!(obj instanceof eef)) {
            LogUtil.b("SectionScoringMgmt", "data is invalid");
            return;
        }
        eef eefVar = (eef) obj;
        if (eefVar.equals(this.ad)) {
            LogUtil.a("SectionScoringMgmt", "smartSleepChartData not changed，data: ", eefVar);
            return;
        }
        this.ad = eefVar;
        LogUtil.a("SectionScoringMgmt", "new data: ", eefVar);
        String c = c(eefVar);
        String a2 = a(eefVar);
        String[] d = d(eefVar);
        long[] b = b(eefVar);
        int[] c2 = c(eefVar, b);
        int e = e(eefVar.d());
        boolean d2 = d(eefVar.d());
        LogUtil.a("SectionScoringMgmt", "start draw");
        if (e == 0) {
            e(c, a2, d, b, c2);
        } else if (e == 1) {
            c(c, a2, d, b, c2, d2);
        }
    }

    private void e(String str, String str2, String[] strArr, long[] jArr, int[] iArr) {
        SleepManagementLineChart sleepManagementLineChart = this.ac;
        if (sleepManagementLineChart != null && this.w != null) {
            sleepManagementLineChart.setVisibility(0);
            this.w.setVisibility(8);
        }
        a(str, str2);
        e();
        b(0.0f, 100.0f, 0.0f, 100.0f);
        List<HwHealthBaseEntry> e = e(jArr, strArr);
        b(a(e, iArr), d(e));
    }

    private void a(String str, String str2) {
        SpannableString spannableString = new SpannableString(String.format("%s %s", str, str2));
        nsi.cKI_(spannableString, str2, R.color._2131299241_res_0x7f090ba9);
        nsi.cKL_(spannableString, str2, R$string.textFontFamilyRegular);
        this.ab.setText(spannableString);
    }

    private void c(String str, String str2, String[] strArr, long[] jArr, int[] iArr, boolean z) {
        SleepManagementLineChart sleepManagementLineChart = this.ac;
        if (sleepManagementLineChart != null && this.w != null) {
            sleepManagementLineChart.setVisibility(8);
            this.w.setVisibility(0);
        }
        a(str, str2);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (long j : jArr) {
            arrayList2.add(Integer.valueOf((int) j));
        }
        float f = 0.0f;
        float f2 = 2.1474836E9f;
        for (int i = 0; i < arrayList2.size(); i++) {
            if (((Integer) arrayList2.get(i)).intValue() >= f) {
                f = ((Integer) arrayList2.get(i)).intValue();
            }
            if (((Integer) arrayList2.get(i)).intValue() <= f2) {
                f2 = ((Integer) arrayList2.get(i)).intValue();
            }
        }
        float f3 = 100.0f / f;
        ArrayList arrayList3 = new ArrayList();
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            if (((Integer) arrayList2.get(i2)).intValue() == -1) {
                arrayList3.add(Float.valueOf(-1.0f));
            } else {
                arrayList3.add(Float.valueOf(((Integer) arrayList2.get(i2)).intValue() * f3));
            }
        }
        for (int i3 = 0; i3 < 7; i3++) {
            if (((Float) arrayList3.get(i3)).floatValue() != -1.0f) {
                float floatValue = ((Float) arrayList3.get(i3)).floatValue();
                int i4 = iArr[i3];
                nnx nnxVar = new nnx(floatValue, i4, i4);
                nnxVar.a(e(i3, 7, strArr));
                arrayList.add(new HwHealthBarEntry(i3 + 0.5f, nnxVar));
            }
        }
        d(arrayList, iArr[6], z);
    }

    private String e(int i, int i2, String[] strArr) {
        if (i != i2 - 1) {
            return "";
        }
        if (TextUtils.isEmpty(strArr[1])) {
            LogUtil.a("SectionScoringMgmt", "label text is null");
            return strArr[0];
        }
        return strArr[0] + ";" + strArr[1];
    }

    private void d(List<HwHealthBarEntry> list, int i, boolean z) {
        HwHealthBarChart hwHealthBarChart = this.w;
        if (hwHealthBarChart == null) {
            LogUtil.h("SectionScoringMgmt", "initSet barChart is null");
            return;
        }
        hwHealthBarChart.setTouchEnabled(false);
        HwHealthBarDataSet hwHealthBarDataSet = new HwHealthBarDataSet(list, "bar breif", "bar label", "bar unit");
        hwHealthBarDataSet.b(HwHealthBarDataSet.DrawColorMode.DATA_COLOR);
        hwHealthBarDataSet.setBarDrawWidthDp(8.0f);
        hwHealthBarDataSet.setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
        hwHealthBarDataSet.setDrawValues(true);
        hwHealthBarDataSet.setValueFormatter(new IValueFormatter() { // from class: com.huawei.health.knit.section.view.SectionScoringMgmt.2
            @Override // com.github.mikephil.charting.formatter.IValueFormatter
            public String getFormattedValue(float f, Entry entry, int i2, ViewPortHandler viewPortHandler) {
                if (!(entry instanceof HwHealthBarEntry)) {
                    return "";
                }
                IStorageModel acquireModel = ((HwHealthBarEntry) entry).acquireModel();
                return !(acquireModel instanceof nnx) ? "" : ((nnx) acquireModel).a();
            }
        });
        hwHealthBarDataSet.setValueTextSize(10.0f);
        hwHealthBarDataSet.setValueTextColor(i);
        this.w.getXAxis().setEnabled(false);
        this.w.getXAxis().setAxisMinimum(0.0f);
        this.w.getXAxis().setAxisMaximum(7.0f);
        HwHealthYAxis axisFirstParty = this.w.getAxisFirstParty();
        axisFirstParty.setDrawLabels(false);
        axisFirstParty.setDrawGridLines(false);
        axisFirstParty.setDrawAxisLine(false);
        axisFirstParty.setAxisMinimum(0.0f);
        axisFirstParty.setAxisMaximum(150.0f);
        ArrayList arrayList = new ArrayList();
        arrayList.add(hwHealthBarDataSet);
        this.w.setData(new nmz(arrayList));
        HwHealthBarChart hwHealthBarChart2 = this.w;
        edg edgVar = new edg(hwHealthBarChart2, hwHealthBarChart2.getAnimator(), this.w.getViewPortHandler(), z);
        edgVar.d(true, false);
        this.w.setRenderer(edgVar);
        this.w.refresh();
    }

    private int[] c(eef eefVar, long[] jArr) {
        int[] iArr = new int[7];
        if (jArr == null) {
            return iArr;
        }
        for (int i = 0; i < jArr.length; i++) {
            long j = jArr[i];
            if (j == -1) {
                iArr[i] = -1;
            } else {
                String b = b(j, eefVar.d(), i);
                if (TextUtils.isEmpty(b)) {
                    iArr[i] = -1;
                } else {
                    iArr[i] = Color.parseColor(b);
                }
            }
        }
        return iArr;
    }

    private String b(long j, int i, int i2) {
        switch (i) {
            case 1:
                return (j < 10 || j > 30) ? j < 10 ? "#46B1E3" : "#ED6F20" : "#64BB5C";
            case 2:
                return (j < 20 || j > 60) ? j < 10 ? "#46B1E3" : "#ED6F20" : "#64BB5C";
            case 3:
                return j > 720 ? "#ED6F20" : "#64BB5C";
            case 4:
                return j >= 300 ? "#64BB5C" : "#ED6F20";
            case 5:
                return d(j, i2, 0, 1);
            case 6:
                return d(j, i2, 360, 600);
            case 7:
                return d(j, i2, 70, 100);
            case 8:
                return d(j, i2, 85, 100);
            case 9:
                if (j < 30) {
                    return c(i2);
                }
                return b(i2);
            default:
                return "";
        }
    }

    private String d(long j, int i, int i2, int i3) {
        long j2 = i2;
        if (j >= j2 && j <= i3) {
            return c(i);
        }
        if (j < j2) {
            return a(i);
        }
        return b(i);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00d5, code lost:
    
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String[] d(defpackage.eef r9) {
        /*
            r8 = this;
            r0 = 2
            java.lang.String[] r1 = new java.lang.String[r0]
            eel[] r2 = r9.c()
            int r3 = r2.length
            r4 = 7
            if (r3 == r4) goto Lc
            return r1
        Lc:
            r3 = 6
            r2 = r2[r3]
            java.lang.String r3 = "SectionScoringMgmt"
            if (r2 != 0) goto L1d
            java.lang.String r9 = "lastBean is null"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.h(r3, r9)
            return r1
        L1d:
            int r9 = r9.d()
            r4 = 1
            r5 = 0
            switch(r9) {
                case 1: goto Lca;
                case 2: goto Lca;
                case 3: goto Lbd;
                case 4: goto Lbd;
                case 5: goto L9c;
                case 6: goto L49;
                case 7: goto L28;
                case 8: goto Lca;
                case 9: goto L49;
                default: goto L26;
            }
        L26:
            goto Ld5
        L28:
            android.content.Context r9 = r8.aa
            android.content.res.Resources r9 = r9.getResources()
            int r0 = r2.e()
            int r2 = r2.e()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            r3 = 2130903317(0x7f030115, float:1.7413449E38)
            java.lang.String r9 = r9.getQuantityString(r3, r0, r2)
            r1[r5] = r9
            goto Ld5
        L49:
            int r9 = r2.e()
            int r9 = r9 / 60
            int r0 = r2.e()
            int r0 = r0 % 60
            android.content.Context r2 = r8.aa
            android.content.res.Resources r2 = r2.getResources()
            double r6 = (double) r0
            java.lang.String r6 = health.compact.a.UnitUtil.e(r6, r4, r5)
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            r7 = 2130903200(0x7f0300a0, float:1.7413211E38)
            java.lang.String r0 = r2.getQuantityString(r7, r0, r6)
            android.content.Context r2 = r8.aa
            android.content.res.Resources r2 = r2.getResources()
            double r6 = (double) r9
            java.lang.String r6 = health.compact.a.UnitUtil.e(r6, r4, r5)
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            r7 = 2130903199(0x7f03009f, float:1.741321E38)
            java.lang.String r2 = r2.getQuantityString(r7, r9, r6)
            if (r9 < 0) goto L92
            r6 = 24
            if (r9 <= r6) goto L88
            goto L92
        L88:
            if (r9 != 0) goto L8d
            r1[r5] = r0
            goto Ld5
        L8d:
            r1[r5] = r2
            r1[r4] = r0
            goto Ld5
        L92:
            java.lang.String r9 = "latency data is invalid"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.b(r3, r9)
            goto Ld5
        L9c:
            android.content.Context r9 = r8.aa
            android.content.res.Resources r9 = r9.getResources()
            int r0 = r2.e()
            int r2 = r2.e()
            double r2 = (double) r2
            java.lang.String r2 = health.compact.a.UnitUtil.e(r2, r4, r5)
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            r3 = 2130903213(0x7f0300ad, float:1.7413238E38)
            java.lang.String r9 = r9.getQuantityString(r3, r0, r2)
            r1[r5] = r9
            goto Ld5
        Lbd:
            long r2 = r2.d()
            java.lang.String r9 = "HH:mm"
            java.lang.String r9 = r8.b(r2, r9)
            r1[r5] = r9
            goto Ld5
        Lca:
            int r9 = r2.e()
            double r2 = (double) r9
            java.lang.String r9 = health.compact.a.UnitUtil.e(r2, r0, r5)
            r1[r5] = r9
        Ld5:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.knit.section.view.SectionScoringMgmt.d(eef):java.lang.String[]");
    }

    private String a(eef eefVar) {
        eel[] c = eefVar.c();
        if (c.length != 7) {
            return "";
        }
        eel eelVar = c[6];
        if (eelVar == null) {
            LogUtil.b("SectionScoringMgmt", "last bean is null");
            return "";
        }
        long a2 = eelVar.a();
        return Constants.LEFT_BRACKET_ONLY + b(c(a2), "MM/dd") + com.huawei.openalliance.ad.constant.Constants.LINK + b(a2, "MM/dd") + Constants.RIGHT_BRACKET_ONLY;
    }

    private String b(long j, String str) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return new SimpleDateFormat(str).format(calendar.getTime());
    }

    private long c(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.add(6, -6);
        return calendar.getTimeInMillis();
    }

    private String c(eef eefVar) {
        switch (eefVar.d()) {
            case 1:
                return f(R$string.IDS_fitness_core_sleep_rem_sleep_percent);
            case 2:
                return f(R$string.IDS_fitness_core_sleep_deep_sleep_percent);
            case 3:
                return f(R$string.IDS_fitness_core_sleep_go_to_sleep_time);
            case 4:
                return f(R$string.IDS_manual_sleep_waking_time);
            case 5:
                return f(R$string.IDS_details_sleep_sleep_latency_time);
            case 6:
                return f(R$string.IDS_fitness_core_sleep_sleep_duration);
            case 7:
                return f(R$string.IDS_fitness_core_sleep_deep_sleep_continuity);
            case 8:
                return f(R$string.IDS_manual_sleep_sleep_efficiency);
            case 9:
                return this.aa.getResources().getString(R$string.IDS_sleep_latency_title);
            default:
                return "";
        }
    }

    private String f(int i) {
        return this.aa.getResources().getString(i);
    }

    private long[] b(eef eefVar) {
        int d = eefVar.d();
        switch (d) {
            case 1:
            case 2:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return e(eefVar.c(), Integer.class, d);
            case 3:
            case 4:
                return e(eefVar.c(), Long.class, d);
            default:
                return new long[0];
        }
    }

    private <T> long[] e(eel[] eelVarArr, Class<T> cls, int i) {
        long[] jArr = new long[7];
        for (int i2 = 0; i2 < eelVarArr.length; i2++) {
            eel eelVar = eelVarArr[i2];
            if (eelVar == null) {
                jArr[i2] = -1;
            } else if (cls == Long.class) {
                jArr[i2] = c(eelVar.d(), i);
            } else if (cls == Integer.class) {
                jArr[i2] = eelVar.e();
            }
        }
        return jArr;
    }

    private long c(long j, int i) {
        String[] split = b(j, "HH:mm").split(":");
        if (split.length != 2) {
            return 0L;
        }
        int h = CommonUtil.h(split[0]);
        if (i != 3) {
            if (i == 4) {
                return (j - jdl.t(j)) / 60000;
            }
            return 0L;
        }
        if (h >= 12 && h <= 23) {
            return (j - jdl.e(j, 12, 0)) / 60000;
        }
        return (j - jdl.e(jdl.b(j, -1), 12, 0)) / 60000;
    }

    private float d(List<HwHealthBaseEntry> list) {
        if (koq.b(list)) {
            return 0.0f;
        }
        return list.get(list.size() - 1).getX();
    }

    private void b(HwHealthLineDataSet hwHealthLineDataSet, float f) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(hwHealthLineDataSet);
        this.ac.setData(new now(arrayList));
        this.ac.highlightValue(f, false);
        SleepManagementLineChart sleepManagementLineChart = this.ac;
        sleepManagementLineChart.setRenderer(new edf(sleepManagementLineChart, sleepManagementLineChart.getAnimator(), this.ac.getViewPortHandler(), this.aa));
        this.ac.refresh();
    }

    private void e() {
        if (this.ac.getRenderer() instanceof nov) {
            ((nov) this.ac.getRenderer()).a(1);
        }
        this.ac.disableLabelsForce();
        this.ac.setTouchEnabled(false);
        this.ac.getDescription().setEnabled(true);
        this.ac.setAllowMultiSetDrawFillEnabled(false);
    }

    private List<HwHealthBaseEntry> e(long[] jArr, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        if (jArr != null) {
            int i = 7;
            if (jArr.length == 7) {
                long j = Long.MIN_VALUE;
                for (long j2 : jArr) {
                    if (j2 == -1) {
                        i--;
                    } else if (j2 > j) {
                        j = j2;
                    }
                }
                long j3 = 2147483647L;
                for (long j4 : jArr) {
                    if (j4 != -1 && j4 < j3) {
                        j3 = j4;
                    }
                }
                if (j == Long.MIN_VALUE || j3 == LocationRequestCompat.PASSIVE_INTERVAL) {
                    LogUtil.h("SectionScoringMgmt", "dataArray all data is invalid");
                    return arrayList;
                }
                if (i == 1) {
                    LogUtil.h("SectionScoringMgmt", "dataArray only one data");
                    arrayList.add(new HwHealthBaseEntry(92.85714f, 32.5f, strArr[0]));
                    return arrayList;
                }
                float f = j == j3 ? 0.0f : 53.0f / (j - j3);
                float f2 = -7.142857f;
                for (int i2 = 0; i2 < jArr.length; i2++) {
                    f2 += 14.285714f;
                    if (jArr[i2] != -1) {
                        float f3 = f == 0.0f ? 38.5f : ((r7 - j3) * f) + 12.0f;
                        if (i2 == 0) {
                            arrayList.add(new HwHealthBaseEntry(f2, f3));
                        } else if (i2 == 6) {
                            arrayList.add(new HwHealthBaseEntry(f2, f3, strArr[0]));
                        } else {
                            arrayList.add(new HwHealthBaseEntry(f2, f3));
                        }
                    }
                }
                return arrayList;
            }
        }
        LogUtil.h("SectionScoringMgmt", "dataArray error");
        return arrayList;
    }

    private void b(float f, float f2, float f3, float f4) {
        SleepManagementLineChart sleepManagementLineChart = this.ac;
        if (sleepManagementLineChart == null) {
            LogUtil.h("SectionScoringMgmt", "initAxis mLineChart is null");
            return;
        }
        XAxis xAxis = sleepManagementLineChart.getXAxis();
        xAxis.setEnabled(false);
        xAxis.setAxisMinimum(f);
        xAxis.setAxisMaximum(f2);
        HwHealthYAxis axisFirstParty = this.ac.getAxisFirstParty();
        axisFirstParty.setDrawLabels(false);
        axisFirstParty.setDrawAxisLine(false);
        axisFirstParty.setDrawGridLines(false);
        axisFirstParty.setAxisMinimum(f3);
        axisFirstParty.setAxisMaximum(f4);
    }

    private HwHealthLineDataSet a(List<HwHealthBaseEntry> list, int[] iArr) {
        if (this.ac == null) {
            LogUtil.h("SectionScoringMgmt", "initLineDataSet mLineChart is null");
            return null;
        }
        LogUtil.a("SectionScoringMgmt", "initLineDataSet valueList size is ", Integer.valueOf(list.size()));
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(this.aa, list, "line brief", "line label", "line unit") { // from class: com.huawei.health.knit.section.view.SectionScoringMgmt.4
            @Override // com.github.mikephil.charting.data.DataSet, com.github.mikephil.charting.interfaces.datasets.IDataSet
            public List<HwHealthBaseEntry> getEntriesForXValue(float f) {
                ArrayList arrayList = new ArrayList();
                int size = this.mEntries.size() - 1;
                int i = 0;
                while (true) {
                    if (i > size) {
                        break;
                    }
                    int i2 = (size + i) / 2;
                    HwHealthBaseEntry hwHealthBaseEntry = (HwHealthBaseEntry) this.mEntries.get(i2);
                    if (d(f, hwHealthBaseEntry.getX())) {
                        while (i2 > 0 && d(((HwHealthBaseEntry) this.mEntries.get(i2 - 1)).getX(), f)) {
                            i2--;
                        }
                        int size2 = this.mEntries.size();
                        while (i2 < size2) {
                            HwHealthBaseEntry hwHealthBaseEntry2 = (HwHealthBaseEntry) this.mEntries.get(i2);
                            if (!d(hwHealthBaseEntry2.getX(), f)) {
                                break;
                            }
                            arrayList.add(hwHealthBaseEntry2);
                            i2++;
                        }
                    } else if (c(f, hwHealthBaseEntry.getX())) {
                        i = i2 + 1;
                    } else {
                        size = i2 - 1;
                    }
                }
                return arrayList;
            }

            private boolean d(float f, float f2) {
                return ((double) Math.abs(f - f2)) < Math.pow(10.0d, -3.0d);
            }

            private boolean c(float f, float f2) {
                return ((double) (f - f2)) > Math.pow(10.0d, -3.0d);
            }
        };
        d(hwHealthLineDataSet, iArr);
        hwHealthLineDataSet.setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
        if (list.size() == 1) {
            hwHealthLineDataSet.a(new HwHealthLineDataSet.LineLinkerFilter() { // from class: com.huawei.health.knit.section.view.SectionScoringMgmt.3
                @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
                public boolean drawLine(int i, int i2, int i3) {
                    return false;
                }
            });
        }
        hwHealthLineDataSet.d(new HwHealthLineDataSet.NodeDrawStyle() { // from class: com.huawei.health.knit.section.view.SectionScoringMgmt.1
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float calcuNodeWidthPixel(boolean z) {
                return 2.5f;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float initNodeStyle(int i) {
                return 1.0f;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public boolean needDrawNodeFill(boolean z) {
                return true;
            }
        });
        return hwHealthLineDataSet;
    }

    private void d(HwHealthLineDataSet hwHealthLineDataSet, int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            if (i != -1) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        hwHealthLineDataSet.setCircleColors(arrayList);
    }

    @Override // com.huawei.health.knit.section.view.SectionScoring
    public void setSuggestRootLayoutVisibility(int i) {
        if (this.u != null) {
            this.u.setVisibility(8);
            if (this.j) {
                this.e.setVisibility(8);
            } else if (i == 0 && this.c) {
                this.e.setVisibility(0);
            } else {
                this.e.setVisibility(8);
            }
        }
    }

    @Override // com.huawei.health.knit.section.view.SectionScoring, com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionScoringMgmt";
    }

    private String c(int i) {
        return i == 6 ? "#64BB5C" : "#9964BB5C";
    }

    private String a(int i) {
        return i == 6 ? "#46B1E3" : "#9946B1E3";
    }

    private String b(int i) {
        return i == 6 ? "#ED6F20" : "#99ED6F20";
    }
}
