package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.health.R;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.view.trackview.TrackLineChartHolder;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class ntl {
    public static void a(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, HwHealthLineDataSet hwHealthLineDataSet) {
        List dataSets;
        nnd nndVar = (nnd) hwHealthBaseCombinedChart.getData();
        if (nndVar == null) {
            nndVar = new nnd();
            nndVar.e(new HwHealthBaseCombinedChart.DrawOrder[]{HwHealthBaseCombinedChart.DrawOrder.LINE, HwHealthBaseCombinedChart.DrawOrder.BAR, HwHealthBaseCombinedChart.DrawOrder.POINT});
        }
        if (nndVar.f() == null) {
            dataSets = new ArrayList(10);
            nndVar.d(new now(dataSets));
        } else {
            dataSets = nndVar.f().getDataSets();
        }
        dataSets.add(hwHealthLineDataSet);
        hwHealthBaseCombinedChart.setData(nndVar);
    }

    public static void c(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, HwHealthLineDataSet hwHealthLineDataSet) {
        List dataSets;
        nnd nndVar = (nnd) hwHealthBaseCombinedChart.getData();
        if (nndVar == null) {
            nndVar = new nnd();
            nndVar.e(new HwHealthBaseCombinedChart.DrawOrder[]{HwHealthBaseCombinedChart.DrawOrder.POINT, HwHealthBaseCombinedChart.DrawOrder.LINE, HwHealthBaseCombinedChart.DrawOrder.BAR});
        }
        if (nndVar.h() == null) {
            dataSets = new ArrayList(10);
            nndVar.b(new noz(dataSets));
        } else {
            dataSets = nndVar.h().getDataSets();
        }
        dataSets.add(hwHealthLineDataSet);
        hwHealthBaseCombinedChart.setData(nndVar);
    }

    public static void d(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
        List dataSets;
        nnd nndVar = (nnd) hwHealthBaseCombinedChart.getData();
        if (nndVar == null) {
            nndVar = new nnd();
            nndVar.e(new HwHealthBaseCombinedChart.DrawOrder[]{HwHealthBaseCombinedChart.DrawOrder.LINE, HwHealthBaseCombinedChart.DrawOrder.BAR, HwHealthBaseCombinedChart.DrawOrder.POINT});
        }
        if (nndVar.j() == null) {
            dataSets = new ArrayList(10);
            nndVar.c(new nmz(dataSets));
        } else {
            dataSets = nndVar.j().getDataSets();
        }
        dataSets.add((HwHealthBarDataSet) hwHealthBaseBarLineDataSet);
        hwHealthBaseCombinedChart.setData(nndVar);
    }

    public static void b(HwHealthLineDataSet hwHealthLineDataSet) {
        hwHealthLineDataSet.a(new HwHealthLineDataSet.LineLinkerFilter() { // from class: ntl.4
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
            public boolean drawLine(int i, int i2, int i3) {
                return i2 - i <= i3;
            }
        });
    }

    public static void e(boolean z, List<kof> list, Context context, List<HwHealthBarEntry> list2, String str) {
        float f = z ? 68.0f : 70.0f;
        for (kof kofVar : list) {
            if (kofVar == null || !nrm.b(kofVar.b())) {
                LogUtil.b(str, "spo2 data is error or invalid. pls check");
            } else {
                list2.add(new HwHealthBarEntry(kofVar.acquireTime(), new nnc(kofVar.b(), f, nrm.d(context, kofVar.b(), z), nrm.a(context, kofVar.b()))));
            }
        }
    }

    public static void d(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, TrackLineChartHolder.b bVar, float f) {
        if (bVar.d) {
            hwHealthBaseCombinedChart.enableSpacePreserveForDataOverlay(true);
            hwHealthBaseCombinedChart.setAvoidFirstLastClipping(false);
            hwHealthBaseCombinedChart.enableMarkerView(true);
            hwHealthBaseCombinedChart.setExtraTopOffset(f);
            return;
        }
        hwHealthBaseCombinedChart.setAvoidFirstLastClipping(true);
        hwHealthBaseCombinedChart.enableOnlyShowMinutes(true);
        hwHealthBaseCombinedChart.enableOneMinuteOmit(true);
    }

    public static void d(HwHealthLineDataSet hwHealthLineDataSet, final ArrayList<HeartRateData> arrayList) {
        hwHealthLineDataSet.a(new HwHealthLineDataSet.LineLinkerFilter() { // from class: ntl.5
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
            public boolean drawLine(int i, int i2, int i3) {
                if (i2 - i > i3) {
                    return ntl.c(i, i2, arrayList);
                }
                return true;
            }
        });
    }

    public static boolean c(int i, int i2, ArrayList<HeartRateData> arrayList) {
        if (koq.b(arrayList)) {
            return true;
        }
        Iterator<HeartRateData> it = arrayList.iterator();
        while (it.hasNext()) {
            HeartRateData next = it.next();
            if (next != null && next.acquireTime() > i && next.acquireTime() < i2) {
                return false;
            }
        }
        return true;
    }

    public static void a(TrackLineChartHolder.b bVar, HwHealthBaseCombinedChart hwHealthBaseCombinedChart, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2, float f3) {
        if (bVar.d) {
            if (hwHealthBaseBarLineDataSet instanceof HwHealthLineDataSet) {
                ((HwHealthLineDataSet) hwHealthBaseBarLineDataSet).setLineWidth(f);
            }
            hwHealthBaseCombinedChart.enableSpacePreserveForDataOverlay(true);
            hwHealthBaseCombinedChart.setAvoidFirstLastClipping(false);
            hwHealthBaseCombinedChart.enableMarkerView(true);
            hwHealthBaseCombinedChart.setExtraTopOffset(f3);
            return;
        }
        hwHealthBaseCombinedChart.setAvoidFirstLastClipping(true);
        hwHealthBaseCombinedChart.enableOnlyShowMinutes(true);
        hwHealthBaseCombinedChart.enableOneMinuteOmit(true);
        if (hwHealthBaseBarLineDataSet instanceof HwHealthLineDataSet) {
            ((HwHealthLineDataSet) hwHealthBaseBarLineDataSet).setLineWidth(f2);
        }
    }

    public static void c(float f, String str, HwHealthLineDataSet hwHealthLineDataSet, int i, int i2, int i3, int i4, TrackLineChartHolder.b bVar, float f2, float f3) {
        if (f > 0.0f) {
            LogUtil.c(str, "addRunningPostureDataLayer acquireRunningPostureDataSumTime:", LogAnonymous.d((long) f));
            hwHealthLineDataSet.setXMaxForcedValue(f);
        }
        if (!bVar.f8982a) {
            hwHealthLineDataSet.setColor(Color.argb(i, i2, i3, i4));
            hwHealthLineDataSet.b(Color.argb((int) (i * f2), i2, i3, i4), Color.argb(0, i2, i3, i4), true);
        }
        if (bVar.e) {
            float f4 = i;
            hwHealthLineDataSet.b(Color.argb((int) (f2 * f4), i2, i3, i4), Color.argb((int) (f4 * f3), i2, i3, i4), false);
        }
    }

    public static String cNf_(Resources resources, int i, String str, Context context) {
        if (i == 266 || i == 262) {
            if (UnitUtil.h()) {
                return context.getResources().getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100);
            }
            return context.getResources().getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 100, 100);
        }
        if (i == 274) {
            if (UnitUtil.h()) {
                return context.getResources().getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100);
            }
            return context.getResources().getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 500, 500);
        }
        if (i == 198 || i == 199 || i == 200) {
            return nsf.a(R.plurals._2130903225_res_0x7f0300b9, 500, 500);
        }
        if (UnitUtil.h()) {
            return "/" + resources.getString(R$string.IDS_motiontrack_show_sport_unit_mi);
        }
        return "/" + resources.getString(R$string.IDS_motiontrack_show_sport_unit_km);
    }

    public static void c(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, List<TrackLineChartHolder.Mode> list) {
        nnd nndVar = (nnd) hwHealthBaseCombinedChart.getData();
        HashMap hashMap = new HashMap(16);
        HashMap hashMap2 = new HashMap(16);
        List<T> dataSets = nndVar.getDataSets();
        HashMap hashMap3 = new HashMap(16);
        for (int i = 0; i < dataSets.size() && i < list.size(); i++) {
            HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet = (HwHealthBaseBarLineDataSet) dataSets.get(i);
            HwHealthYAxis axis = hwHealthBaseCombinedChart.getAxis(hwHealthBaseBarLineDataSet.getAxisDependencyExt());
            nnj nnjVar = new nnj();
            nnjVar.d(hwHealthBaseCombinedChart.getAxisDataRenderArg(hwHealthBaseBarLineDataSet.getAxisDependencyExt()));
            hashMap.put(hwHealthBaseBarLineDataSet, Float.valueOf(axis.getAxisMinimum()));
            hashMap2.put(hwHealthBaseBarLineDataSet, Float.valueOf(axis.getAxisMaximum()));
            hashMap3.put(hwHealthBaseBarLineDataSet, nnjVar);
        }
        for (int i2 = 0; i2 < dataSets.size() && i2 < list.size(); i2++) {
            HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet2 = (HwHealthBaseBarLineDataSet) dataSets.get(i2);
            LogUtil.a("Track_TrackLineChartHolder", "rmDataSet customAxis mode=", list.get(i2), " max=", hashMap2.get(hwHealthBaseBarLineDataSet2), " min=", hashMap.get(hwHealthBaseBarLineDataSet2));
            b(hwHealthBaseCombinedChart, hwHealthBaseBarLineDataSet2, list.get(i2), ((Float) hashMap2.get(hwHealthBaseBarLineDataSet2)).floatValue(), ((Float) hashMap.get(hwHealthBaseBarLineDataSet2)).floatValue());
            d(hwHealthBaseCombinedChart, hwHealthBaseBarLineDataSet2, list.get(i2), (nnj) hashMap3.get(hwHealthBaseBarLineDataSet2));
        }
        nndVar.notifyDataChanged();
    }

    public static void d(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, TrackLineChartHolder.Mode mode, nnj nnjVar) {
        LogUtil.a("Track_TrackLineChartHolder", "customAxisRenderArg mode:", mode);
        if (mode == TrackLineChartHolder.Mode.MODE_FIRST_AXIS) {
            hwHealthBaseCombinedChart.setAxisDataRenderArg(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY, nnjVar);
        } else if (mode == TrackLineChartHolder.Mode.MODE_SECOND_AXIS) {
            hwHealthBaseCombinedChart.setAxisDataRenderArg(HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY, nnjVar);
        } else {
            hwHealthBaseCombinedChart.setAxisDataRenderArg(HwHealthYAxis.HwHealthAxisDependency.THIRD_PARTY, nnjVar);
        }
        nnjVar.e().initStyle(hwHealthBaseCombinedChart, hwHealthBaseBarLineDataSet);
    }

    public static void b(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, IHwHealthBarLineDataSet iHwHealthBarLineDataSet, TrackLineChartHolder.Mode mode, float f, float f2) {
        HwHealthYAxis axisThirdParty;
        LogUtil.a("Track_TrackLineChartHolder", "customAxis mode:", mode);
        if (mode == TrackLineChartHolder.Mode.MODE_FIRST_AXIS) {
            axisThirdParty = hwHealthBaseCombinedChart.getAxisFirstParty();
            iHwHealthBarLineDataSet.setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
        } else if (mode == TrackLineChartHolder.Mode.MODE_SECOND_AXIS) {
            axisThirdParty = hwHealthBaseCombinedChart.getAxisSecondParty();
            iHwHealthBarLineDataSet.setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY);
        } else {
            axisThirdParty = hwHealthBaseCombinedChart.getAxisThirdParty();
            iHwHealthBarLineDataSet.setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.THIRD_PARTY);
        }
        float f3 = f - f2;
        if (Math.abs(f3) >= 1.0E-4f) {
            axisThirdParty.setAxisMinimum(f2);
            axisThirdParty.setAxisMaximum(f);
        } else if (Math.abs(f) < 1.0E-4f) {
            axisThirdParty.setAxisMaximum(f + 1.0f);
            axisThirdParty.setAxisMinimum(f2);
        } else {
            axisThirdParty.setAxisMinimum(f3);
            axisThirdParty.setAxisMaximum(f);
        }
    }

    public static TrackLineChartHolder.c b(int i, int i2, boolean z) {
        int e = e(i, i2, 3);
        int e2 = e(i, i2, 5);
        if (z) {
            return new TrackLineChartHolder.c(e2, 3);
        }
        if (i - i2 >= 4) {
            return new TrackLineChartHolder.c(e2, 5);
        }
        if (e2 <= e) {
            return new TrackLineChartHolder.c(e2, 5);
        }
        return new TrackLineChartHolder.c(e2, 3);
    }

    public static TrackLineChartHolder.c d(int i, int i2, boolean z) {
        if (z) {
            return new TrackLineChartHolder.c(i, 3);
        }
        if (i - i2 >= 4) {
            return new TrackLineChartHolder.c(i, 5);
        }
        return new TrackLineChartHolder.c(i, 5);
    }

    public static int e(int i, int i2, int i3) {
        int i4;
        int i5;
        return (i3 > 1 && (i5 = (i - i2) % (i4 = i3 + (-1))) != 0) ? i + (i4 - i5) : i;
    }

    public static void e(final HwHealthLineDataSet hwHealthLineDataSet) {
        hwHealthLineDataSet.d(new HwHealthLineDataSet.NodeDrawStyle() { // from class: ntl.3
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float initNodeStyle(int i) {
                return 0.0f;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public boolean needDrawNodeFill(boolean z) {
                return z;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float calcuNodeWidthPixel(boolean z) {
                if (z) {
                    return HwHealthLineDataSet.this.getLineWidth();
                }
                return Utils.convertDpToPixel(4.0f);
            }
        });
    }

    public static void a(List<HeartRateData> list, int i, HeartRateData heartRateData, int i2) {
        for (int i3 = 1; i3 < i; i3++) {
            list.add(new HeartRateData(heartRateData.acquireTime() + (i2 * i3 * 1000), heartRateData.acquireHeartRate()));
        }
    }

    public static void a(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, HwHealthLineDataSet hwHealthLineDataSet, List<TrackLineChartHolder.Mode> list) {
        a(hwHealthBaseCombinedChart, hwHealthLineDataSet);
        c(hwHealthBaseCombinedChart, list);
    }

    public static void d(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, List<TrackLineChartHolder.Mode> list) {
        if (hwHealthBaseCombinedChart == null) {
            return;
        }
        d(hwHealthBaseCombinedChart, hwHealthBaseBarLineDataSet);
        c(hwHealthBaseCombinedChart, list);
    }

    public static void d(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, TrackLineChartHolder.b bVar, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, int i, float f, int i2, float f2, List<TrackLineChartHolder.Mode> list) {
        if (!bVar.c) {
            int i3 = (int) (i * f);
            hwHealthBaseCombinedChart.setGridColor(Color.argb(i3, i2, i2, i2), Color.argb(i3, i2, i2, i2));
            hwHealthBaseCombinedChart.setLabelColor(Color.rgb(i2, i2, i2));
        }
        d(hwHealthBaseCombinedChart, bVar, f2);
        d(hwHealthBaseCombinedChart, hwHealthBaseBarLineDataSet, list);
        new nqp().initStyle(hwHealthBaseCombinedChart, hwHealthBaseBarLineDataSet);
    }

    public static boolean d(ffs ffsVar) {
        return ffsVar.l() <= 0 && ffsVar.o() <= 0;
    }

    public static boolean e(ffs ffsVar) {
        return ffsVar.n() > 0.0f || ffsVar.m() > 0.0f || ffsVar.r() > 0.0f || ffsVar.t() > 0.0f;
    }

    public static boolean c(ffs ffsVar) {
        return ffsVar.b() == 0 && ffsVar.e() == 0 && ffsVar.i() == 0 && ffsVar.a() == 0;
    }

    public static TrackLineChartHolder.Mode b(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        nnd nndVar = (nnd) hwHealthBaseCombinedChart.getData();
        if (nndVar == null) {
            return TrackLineChartHolder.Mode.MODE_FIRST_AXIS;
        }
        List<T> dataSets = nndVar.getDataSets();
        if (koq.b(dataSets)) {
            return TrackLineChartHolder.Mode.MODE_FIRST_AXIS;
        }
        if (dataSets.size() == 1) {
            return TrackLineChartHolder.Mode.MODE_SECOND_AXIS;
        }
        if (dataSets.size() == 2) {
            return TrackLineChartHolder.Mode.MODE_THIRD_PARTY_AXIS;
        }
        return TrackLineChartHolder.Mode.MODE_NONE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0053 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0054 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static float c(defpackage.ffs r3, int r4) {
        /*
            r0 = 7
            r1 = -971227136(0xffffffffc61c4000, float:-10000.0)
            r2 = 0
            if (r4 != r0) goto Lc
            int r3 = r3.b()
            goto L1d
        Lc:
            r0 = 8
            if (r4 != r0) goto L15
            int r3 = r3.e()
            goto L1d
        L15:
            r0 = 16
            if (r4 != r0) goto L1f
            int r3 = r3.l()
        L1d:
            float r3 = (float) r3
            goto L4f
        L1f:
            r0 = 17
            if (r4 != r0) goto L30
            int r3 = r3.o()
            float r3 = defpackage.ffw.c(r3)
            int r4 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r4 != 0) goto L4f
            return r1
        L30:
            r0 = 19
            if (r4 != r0) goto L39
            float r3 = r3.n()
            goto L4f
        L39:
            r0 = 18
            if (r4 != r0) goto L42
            float r3 = b(r3)
            goto L4f
        L42:
            r0 = 20
            if (r4 != r0) goto L4b
            float r3 = r3.r()
            goto L4f
        L4b:
            float r3 = r3.m()
        L4f:
            int r4 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r4 > 0) goto L54
            return r1
        L54:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ntl.c(ffs, int):float");
    }

    private static float b(ffs ffsVar) {
        if (!UnitUtil.h()) {
            return ffsVar.t();
        }
        return (float) UnitUtil.e(ffsVar.t(), 0);
    }

    public static HwHealthBarDataSet d(List<HwHealthBarEntry> list, Context context) {
        HwHealthBarDataSet hwHealthBarDataSet = new HwHealthBarDataSet(list, context.getResources().getString(R$string.IDS_aw_version2_duration_of_passage), context.getResources().getString(R$string.IDS_aw_version2_duration_of_passage), context.getResources().getString(R$string.IDS_msec_unit));
        hwHealthBarDataSet.setColor(-16745217);
        hwHealthBarDataSet.e(-16750120);
        hwHealthBarDataSet.setBarDrawWidthDp(1.5f);
        hwHealthBarDataSet.setSearchByBarWidth(true);
        return hwHealthBarDataSet;
    }

    public static String c(Context context) {
        if (!UnitUtil.h()) {
            return context.getResources().getString(R$string.IDS_cm);
        }
        return context.getResources().getString(R$string.IDS_ins);
    }

    public static HwHealthBarDataSet b(float f, HwHealthBarDataSet hwHealthBarDataSet) {
        if (f > 0.0f) {
            LogUtil.c("Track_TrackLineChartHolder", "add Height SumTime:", Float.valueOf(f));
            hwHealthBarDataSet.setXMaxForcedValue(f);
        }
        hwHealthBarDataSet.setSearchByBarWidth(true);
        hwHealthBarDataSet.setColor(-301790);
        hwHealthBarDataSet.e(-2140647);
        hwHealthBarDataSet.setBarDrawWidthDp(1.5f);
        return hwHealthBarDataSet;
    }

    public static ArrayList<knz> d(ArrayList<knz> arrayList) {
        if (koq.b(arrayList)) {
            return new ArrayList<>(16);
        }
        if (!UnitUtil.h()) {
            return arrayList;
        }
        ArrayList<knz> arrayList2 = new ArrayList<>(16);
        Iterator<knz> it = arrayList.iterator();
        while (it.hasNext()) {
            knz next = it.next();
            arrayList2.add(new knz(next.acquireTime(), UnitUtil.e(next.c(), 1)));
        }
        return arrayList2;
    }

    public static ArrayList<koi> e(ArrayList<koi> arrayList) {
        if (koq.b(arrayList)) {
            return new ArrayList<>(16);
        }
        if (!UnitUtil.h()) {
            return arrayList;
        }
        ArrayList<koi> arrayList2 = new ArrayList<>(16);
        Iterator<koi> it = arrayList.iterator();
        while (it.hasNext()) {
            koi next = it.next();
            arrayList2.add(new koi(next.acquireTime(), UnitUtil.e(next.e(), 3)));
        }
        return arrayList2;
    }

    public static ArrayList<knz> a(ArrayList<knz> arrayList) {
        ArrayList<knz> arrayList2 = new ArrayList<>(16);
        Iterator<knz> it = arrayList.iterator();
        float f = Float.MAX_VALUE;
        while (it.hasNext()) {
            knz next = it.next();
            if (next.c() < f) {
                f = (float) next.c();
            }
        }
        int floor = (int) Math.floor(f);
        LogUtil.a("Track_TrackLineChartHolder", "baseLine:", Integer.valueOf(floor), " min:", Float.valueOf(f));
        Iterator<knz> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            knz next2 = it2.next();
            arrayList2.add(new knz(next2.acquireTime(), next2.c() - floor));
        }
        return arrayList2;
    }

    public static void d(List<TrackLineChartHolder.Mode> list) {
        list.clear();
        list.add(TrackLineChartHolder.Mode.MODE_FIRST_AXIS);
        list.add(TrackLineChartHolder.Mode.MODE_SECOND_AXIS);
        list.add(TrackLineChartHolder.Mode.MODE_THIRD_PARTY_AXIS);
        list.add(TrackLineChartHolder.Mode.MODE_NONE);
    }

    public static String a(Context context) {
        if (!UnitUtil.h()) {
            return context.getResources().getString(R$string.IDS_fitness_data_list_activity_meter_unit);
        }
        return context.getResources().getString(R$string.IDS_ft);
    }

    public static HwHealthLineDataSet c(HwHealthLineDataSet hwHealthLineDataSet) {
        if (UnitUtil.h()) {
            hwHealthLineDataSet.a(2);
        } else {
            hwHealthLineDataSet.a(1);
        }
        return hwHealthLineDataSet;
    }

    public static void d(TrackLineChartHolder.d dVar) {
        int ceil;
        int i;
        double floor;
        if (dVar.f8984a) {
            float max = Math.max(Math.min(dVar.c() - dVar.d(), dVar.d() - dVar.e()) * 2.0f, 35.0f);
            ceil = (int) Math.ceil(Math.max(dVar.d() + (0.65f * max), dVar.c() + (Math.pow(Math.max(dVar.d(), 5.0f), 0.5d) * 0.5d)));
            floor = Math.floor(Math.min(dVar.d() - (max * 0.75f), dVar.e()));
        } else if (!dVar.b) {
            float min = Math.min(dVar.c() - dVar.d(), dVar.d() - dVar.e()) * 2.0f;
            ceil = (int) Math.ceil(Math.max(dVar.d() + (0.65f * min), dVar.c() + (Math.pow(Math.max(dVar.d(), 5.0f), 0.5d) * 0.5d)));
            floor = Math.floor(Math.max(0.0f, Math.min(dVar.e() - (min * 0.05f), dVar.d() * 0.8f)));
        } else {
            float min2 = Math.min(dVar.c() - dVar.d(), dVar.d() - dVar.e()) * 2.0f;
            int floor2 = (int) Math.floor(Math.max(Math.min(dVar.d() - (0.65f * min2), dVar.e() - (Math.pow(Math.max(dVar.d(), 5.0f), 0.5d) * 0.5d)), 0.0d));
            ceil = (int) Math.ceil(Math.max(dVar.d() + (min2 * 2.0f), dVar.d() * 3.3f));
            i = floor2;
            LogUtil.a("Track_TrackLineChartHolder", "customAxisByDataBoard mode=", dVar.a(), " maxAxisInteger=", Integer.valueOf(ceil), " minAxisInteger=", Integer.valueOf(i));
            TrackLineChartHolder.c b = b(ceil, i, dVar.g());
            int i2 = b.f8983a;
            dVar.j().setLabelCount(b.e, true);
            LogUtil.a("Track_TrackLineChartHolder", "customAxisByDataBoard(after correct) mode=", dVar.a(), " maxAxisInteger=", Integer.valueOf(i2), " minAxisInteger=", Integer.valueOf(i));
            b(dVar.b(), dVar.j(), dVar.a(), i2, i);
        }
        i = (int) floor;
        LogUtil.a("Track_TrackLineChartHolder", "customAxisByDataBoard mode=", dVar.a(), " maxAxisInteger=", Integer.valueOf(ceil), " minAxisInteger=", Integer.valueOf(i));
        TrackLineChartHolder.c b2 = b(ceil, i, dVar.g());
        int i22 = b2.f8983a;
        dVar.j().setLabelCount(b2.e, true);
        LogUtil.a("Track_TrackLineChartHolder", "customAxisByDataBoard(after correct) mode=", dVar.a(), " maxAxisInteger=", Integer.valueOf(i22), " minAxisInteger=", Integer.valueOf(i));
        b(dVar.b(), dVar.j(), dVar.a(), i22, i);
    }

    public static void c(TrackLineChartHolder.d dVar) {
        int b = nsn.b(dVar.c(), dVar.e());
        int d = nsn.d(dVar.c(), dVar.e());
        LogUtil.a("Track_TrackLineChartHolder", "customAxisByDataBoard mode=", dVar.a(), " maxAxisInteger=", Integer.valueOf(b), " minAxisInteger=", Integer.valueOf(d));
        TrackLineChartHolder.c d2 = d(b, d, dVar.g());
        int i = d2.f8983a;
        dVar.j().setLabelCount(d2.e, true);
        LogUtil.a("Track_TrackLineChartHolder", "customAxisByDataBoard(after correct) mode=", dVar.a(), " maxAxisInteger=", Integer.valueOf(i), " minAxisInteger=", Integer.valueOf(d));
        b(dVar.b(), dVar.j(), dVar.a(), i, d);
    }
}
