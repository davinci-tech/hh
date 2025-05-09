package com.huawei.ui.commonui.view.trackview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import com.huawei.health.R;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSleepType;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import defpackage.ffn;
import defpackage.ffs;
import defpackage.ffw;
import defpackage.fgf;
import defpackage.ixt;
import defpackage.knw;
import defpackage.kny;
import defpackage.knz;
import defpackage.kob;
import defpackage.koc;
import defpackage.kof;
import defpackage.kog;
import defpackage.koh;
import defpackage.koi;
import defpackage.kol;
import defpackage.kom;
import defpackage.koq;
import defpackage.nmw;
import defpackage.nnc;
import defpackage.nnd;
import defpackage.nqo;
import defpackage.nqp;
import defpackage.nqq;
import defpackage.nqr;
import defpackage.nqs;
import defpackage.nrm;
import defpackage.ntl;
import defpackage.ntn;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class TrackLineChartHolder {
    private static final String TAG = "Track_TrackLineChartHolder";
    public static final float VALID_CHART_DATA = -10000.0f;
    private static TrackLineChartHolder mTrackLineChartHolder;
    protected Context mContext;
    private int mSportType = 0;
    private String mSportMode = "";
    private b mDefaultShowMode = new b();
    private List<Mode> mModes = new ArrayList(16);
    private int mCurveColor = Color.argb(51, 0, 0, 0);

    public enum Mode {
        MODE_FIRST_AXIS,
        MODE_SECOND_AXIS,
        MODE_THIRD_PARTY_AXIS,
        MODE_NONE
    }

    protected abstract int acquireCadenceInterval();

    protected abstract float acquireCadenceSumTime();

    protected abstract ArrayList<HeartRateData> acquireHeartRateData();

    protected abstract int acquireHeartRateDataInterval();

    protected abstract float acquireHeartRateDataSumTime();

    protected abstract ArrayList<HeartRateData> acquireInvalidHeartRateData();

    protected abstract ArrayList<HeartRateData> acquireInvalidUnixHeartRateData();

    protected abstract int acquireJumpDataInterval();

    protected abstract int acquireJumpTotalTime();

    protected abstract int acquirePaddleFreqInterval();

    protected abstract List<knw> acquirePaddleFrequencyData();

    protected abstract List<koc> acquirePoweData();

    protected abstract int acquirePowerDataInterval();

    protected abstract List<kog> acquirePullFreqData();

    protected abstract int acquirePullFreqDataInterval();

    protected abstract float acquireRealTimePaceDataSumTime();

    protected abstract float acquireRecoverHeartRateDataSumTime();

    protected abstract List<ffn> acquireRidePostureData();

    protected abstract List<ffs> acquireRunningPostureData();

    protected abstract int acquireRunningPostureDataInterval();

    protected abstract float acquireRunningPostureDataSumTime();

    protected abstract List<kob> acquireSkippingSpeedData();

    protected abstract int acquireSkippingSpeedInterval();

    protected abstract float acquireSpo2SumTime();

    protected abstract ArrayList<StepRateData> acquireStepRateData();

    protected abstract int acquireStepRateDataInterval();

    protected abstract float acquireStepRateSumTime();

    protected abstract List<kol> acquireSwolfData();

    protected abstract int acquireSwolfDataInterval();

    protected abstract ArrayList<knz> acquireTrackAltitudeData();

    protected abstract int acquireTrackAltitudeDataInterval();

    protected abstract int acquireTrackAltitudeShowType();

    protected abstract List<HeartRateData> acquireTrackHeartRateData();

    protected abstract ArrayList<ixt> acquireTrackJumpData();

    protected abstract List<koh> acquireTrackRealTimePaceData();

    protected abstract ArrayList<koi> acquireTrackRealTimeSpeedData();

    protected abstract int acquireTrackRealTimeSpeedDataInterval();

    protected abstract List<kof> acquireTrackSpo2Data();

    protected abstract List<kom> acquireWeightData();

    protected abstract int acquireWeightDataInterval();

    protected abstract List<kny> getHeartRateAreaList();

    public static class b {
        public boolean e = false;
        public boolean d = false;
        public boolean c = true;

        /* renamed from: a, reason: collision with root package name */
        public boolean f8982a = false;

        public b b(boolean z) {
            this.e = z;
            return this;
        }

        public b a(boolean z) {
            this.f8982a = z;
            return this;
        }

        public b d(boolean z) {
            this.d = z;
            return this;
        }

        public b c(boolean z) {
            this.c = z;
            return this;
        }
    }

    public TrackLineChartHolder(Context context) {
        if (context != null) {
            this.mContext = context;
            ntl.d(this.mModes);
        }
    }

    public void setSportType(int i) {
        this.mSportType = i;
    }

    public void setSportMode(String str) {
        this.mSportMode = str;
    }

    public static TrackLineChartHolder getInstance() {
        return mTrackLineChartHolder;
    }

    public static void setInstance(TrackLineChartHolder trackLineChartHolder) {
        mTrackLineChartHolder = trackLineChartHolder;
    }

    public HwHealthLineDataSet addHeartRateDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        return addHeartRateDataLayer(hwHealthBaseCombinedChart, this.mDefaultShowMode);
    }

    public HwHealthLineDataSet addHeartRateDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar) {
        if (hwHealthBaseCombinedChart == null || bVar == null || this.mContext == null) {
            LogUtil.h(TAG, "chart or showMode ,mContext is null");
            return null;
        }
        return addHeartRateDataLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar);
    }

    private HwHealthLineDataSet addHeartRateDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, Mode mode, b bVar) {
        HwHealthLineDataSet hwHealthLineDataSet;
        LogUtil.a(TAG, "addHeartRateDataLayer mode:", mode);
        ArrayList arrayList = new ArrayList(16);
        ArrayList<HeartRateData> acquireHeartRateData = acquireHeartRateData();
        if (koq.b(acquireHeartRateData)) {
            LogUtil.h(TAG, "initHeartRate heartRateList empty,return");
            return null;
        }
        Iterator<HeartRateData> it = acquireHeartRateData.iterator();
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        double d2 = 0.0d;
        int i3 = 0;
        while (it.hasNext()) {
            HeartRateData next = it.next();
            if (next != null) {
                arrayList.add(new HwHealthBaseEntry(next.acquireTime(), next.acquireHeartRate()));
                if (next.acquireHeartRate() > i) {
                    i = next.acquireHeartRate();
                }
                if (next.acquireHeartRate() < i2) {
                    i2 = next.acquireHeartRate();
                }
                d2 += next.acquireHeartRate();
                i3++;
            }
        }
        if (!hwHealthBaseCombinedChart.containsMarkViewShow()) {
            hwHealthBaseCombinedChart.highlightDefValue(-1, true);
        }
        HwHealthLineDataSet hwHealthLineDataSet2 = new HwHealthLineDataSet(BaseApplication.getContext(), arrayList, this.mContext.getResources().getString(R$string.IDS_main_watch_heart_rate_string), this.mContext.getResources().getString(R$string.IDS_main_watch_heart_rate_string), this.mContext.getResources().getString(R$string.IDS_main_watch_heart_rate_unit_string), acquireHeartRateDataInterval());
        if (acquireHeartRateDataSumTime() > 0.0f) {
            LogUtil.c(TAG, "acquireHeartRateDataSumTime:", Float.valueOf(acquireHeartRateDataSumTime()));
            hwHealthLineDataSet2.setXMaxForcedValue(acquireHeartRateDataSumTime());
        }
        List<kny> heartRateAreaList = getHeartRateAreaList();
        if (!bVar.d && koq.c(heartRateAreaList)) {
            hwHealthBaseCombinedChart.setYRenderFillArea(ffw.d(heartRateAreaList));
        } else {
            setHeartRateFillDrawableDefaultGradient(hwHealthLineDataSet2, true);
        }
        hwHealthLineDataSet2.setColor(Color.argb(255, 252, 49, 89));
        hwHealthLineDataSet2.d(Color.argb(229, a.L, 70, 94));
        LogUtil.a(TAG, "addHeartRateDataLayer customAxisByDataBoard mode=", mode);
        if (mode != Mode.MODE_NONE) {
            hwHealthLineDataSet = hwHealthLineDataSet2;
            ntl.d(new d(hwHealthBaseCombinedChart, hwHealthLineDataSet2, mode, i, i3 > 0 ? (float) (d2 / i3) : 0.0f, i2, bVar.e, false, false));
        } else {
            hwHealthLineDataSet = hwHealthLineDataSet2;
        }
        handleHeartRateDataSet(hwHealthBaseCombinedChart, bVar, hwHealthLineDataSet);
        return hwHealthLineDataSet;
    }

    public List<HeartRateData> insertHeartRatePoint(List<HeartRateData> list, int i) {
        if (koq.b(list)) {
            LogUtil.h(TAG, "insertHeartRatePoint heartRateDataList is null");
            return null;
        }
        int size = list.size();
        ArrayList<HeartRateData> acquireInvalidUnixHeartRateData = acquireInvalidUnixHeartRateData();
        for (int i2 = 1; i2 < size; i2++) {
            int i3 = i2 - 1;
            long acquireTime = (list.get(i2).acquireTime() - list.get(i3).acquireTime()) / 1000;
            boolean c2 = ntl.c((int) list.get(i3).acquireTime(), (int) list.get(i2).acquireTime(), acquireInvalidUnixHeartRateData);
            if (acquireTime > i && c2 && i != 0) {
                ntl.a(list, ((int) acquireTime) / i, list.get(i3), i);
            }
        }
        return list;
    }

    private void handleHeartRateDataSet(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar, HwHealthLineDataSet hwHealthLineDataSet) {
        if (bVar.e) {
            setHeartRateFillDrawableDefaultGradient(hwHealthLineDataSet, false);
        }
        setThemeAndOrientation(hwHealthBaseCombinedChart, bVar, hwHealthLineDataSet);
        ntl.e(hwHealthLineDataSet);
        new nqp().initStyle(hwHealthBaseCombinedChart, hwHealthLineDataSet);
        addHeartRateDataSet(hwHealthBaseCombinedChart, hwHealthLineDataSet);
    }

    private void setHeartRateFillDrawableDefaultGradient(HwHealthLineDataSet hwHealthLineDataSet, boolean z) {
        hwHealthLineDataSet.b(Color.argb(127, 252, 49, 89), Color.argb(0, 252, 49, 89), z);
    }

    public void removeDataSet(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
        if (hwHealthBaseCombinedChart == null || hwHealthBaseBarLineDataSet == null) {
            LogUtil.h(TAG, "chart or set is null");
        } else {
            ((nnd) hwHealthBaseCombinedChart.getData()).removeDataSet((HwHealthBaseBarLineDataSet<HwHealthBaseEntry>) hwHealthBaseBarLineDataSet);
            ntl.c(hwHealthBaseCombinedChart, this.mModes);
        }
    }

    public HwHealthLineDataSet addStepRateDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        return addStepRateDataLayer(hwHealthBaseCombinedChart, this.mDefaultShowMode);
    }

    public HwHealthLineDataSet addStepRateDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar) {
        if (hwHealthBaseCombinedChart == null || bVar == null || this.mContext == null) {
            LogUtil.h(TAG, "chart or showMode ,mContext is null");
            return null;
        }
        return addStepRateDataLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar);
    }

    private HwHealthLineDataSet addStepRateDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, Mode mode, b bVar) {
        LogUtil.a(TAG, "addStepRateDataLayer mode:", mode);
        ArrayList<HwHealthBaseEntry> arrayList = new ArrayList<>(16);
        ArrayList<StepRateData> acquireStepRateData = acquireStepRateData();
        if (koq.b(acquireStepRateData)) {
            LogUtil.h(TAG, "initStepRate stepRateList empty,return");
            return null;
        }
        Iterator<StepRateData> it = acquireStepRateData.iterator();
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        double d2 = 0.0d;
        int i3 = 0;
        while (it.hasNext()) {
            StepRateData next = it.next();
            if (next != null) {
                arrayList.add(new HwHealthBaseEntry(next.acquireTime(), next.acquireStepRate()));
                if (next.acquireStepRate() > i) {
                    i = next.acquireStepRate();
                }
                if (next.acquireStepRate() < i2) {
                    i2 = next.acquireStepRate();
                }
                d2 += next.acquireStepRate();
                i3++;
            }
        }
        if (!hwHealthBaseCombinedChart.containsMarkViewShow()) {
            hwHealthBaseCombinedChart.highlightDefValue(-1, true);
        }
        HwHealthLineDataSet charSet = getCharSet(arrayList);
        if (mode != Mode.MODE_NONE) {
            LogUtil.a(TAG, "addStepRateDataLayer customAxisByDataBoard mode=", mode);
            ntl.d(new d(hwHealthBaseCombinedChart, charSet, mode, i, i3 > 0 ? (float) (d2 / i3) : 0.0f, i2, bVar.e, false, false));
        }
        float acquireStepRateSumTime = acquireStepRateSumTime();
        if (acquireStepRateSumTime > 0.0f) {
            LogUtil.c(TAG, "addStepRateDataLayer acquireStepRateSumTime:", Float.valueOf(acquireStepRateSumTime));
            charSet.setXMaxForcedValue(acquireStepRateSumTime);
        }
        if (bVar.e) {
            setStepRateFillDrawableDefaultGradient(charSet, false);
        }
        handleStepRateDataSet(hwHealthBaseCombinedChart, bVar, charSet);
        return charSet;
    }

    private void handleStepRateDataSet(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar, HwHealthLineDataSet hwHealthLineDataSet) {
        setThemeAndOrientation(hwHealthBaseCombinedChart, bVar, hwHealthLineDataSet);
        ntl.e(hwHealthLineDataSet);
        new nqp().initStyle(hwHealthBaseCombinedChart, hwHealthLineDataSet);
        addDataSet(hwHealthBaseCombinedChart, hwHealthLineDataSet);
    }

    private void setStepRateFillDrawableDefaultGradient(HwHealthLineDataSet hwHealthLineDataSet, boolean z) {
        hwHealthLineDataSet.b(Color.argb(127, 255, 176, 0), Color.argb(0, 255, 176, 0), z);
    }

    public HwHealthLineDataSet addPaddleFreqDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        return addPaddleFreqDataLayer(hwHealthBaseCombinedChart, this.mDefaultShowMode);
    }

    public HwHealthLineDataSet addPaddleFreqDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar) {
        if (hwHealthBaseCombinedChart == null || bVar == null || this.mContext == null) {
            LogUtil.h(TAG, "chart or showMode ,mContext is null");
            return null;
        }
        return addPaddleFreqDataLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar);
    }

    private HwHealthLineDataSet addPaddleFreqDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, Mode mode, b bVar) {
        LogUtil.a(TAG, "addPaddleFreqDataLayer mode:", mode);
        ArrayList<HwHealthBaseEntry> arrayList = new ArrayList<>(16);
        List<knw> acquirePaddleFrequencyData = acquirePaddleFrequencyData();
        if (koq.b(acquirePaddleFrequencyData)) {
            LogUtil.h(TAG, "initPaddleFrequency paddleFrequencyList empty,return");
            return null;
        }
        float f = Float.MIN_VALUE;
        double d2 = 0.0d;
        float f2 = Float.MAX_VALUE;
        int i = 0;
        for (knw knwVar : acquirePaddleFrequencyData) {
            if (knwVar != null) {
                arrayList.add(new HwHealthBaseEntry(knwVar.acquireTime(), knwVar.b()));
                if (knwVar.b() > f) {
                    f = knwVar.b();
                }
                if (knwVar.b() < f2) {
                    f2 = knwVar.b();
                }
                d2 += knwVar.b();
                i++;
            }
        }
        if (!hwHealthBaseCombinedChart.containsMarkViewShow()) {
            hwHealthBaseCombinedChart.highlightDefValue(-1, true);
        }
        HwHealthLineDataSet hwHealthLinePaddleDataSet = getHwHealthLinePaddleDataSet(arrayList);
        if (mode != Mode.MODE_NONE) {
            LogUtil.a(TAG, "addPaddleFreqDataLayer customAxisByDataBoard mode=", mode);
            ntl.d(new d(hwHealthBaseCombinedChart, hwHealthLinePaddleDataSet, mode, f, i > 0 ? (float) (d2 / i) : 0.0f, f2, bVar.e, false, false));
        }
        if (bVar.e) {
            setPaddleFreqFillDrawableDefaultGradient(hwHealthLinePaddleDataSet, false);
        }
        handlePaddleFreqDataSet(hwHealthBaseCombinedChart, bVar, hwHealthLinePaddleDataSet);
        return hwHealthLinePaddleDataSet;
    }

    private HwHealthLineDataSet getCharSet(ArrayList<HwHealthBaseEntry> arrayList) {
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(BaseApplication.getContext(), arrayList, this.mContext.getResources().getString(R$string.IDS_motiontrack_detail_fm_heart_bupin), this.mContext.getResources().getString(R$string.IDS_motiontrack_detail_fm_heart_bupin), this.mContext.getResources().getQuantityString(R.plurals._2130903288_res_0x7f0300f8, 0), acquireStepRateDataInterval());
        hwHealthLineDataSet.setColor(Color.argb(255, 253, 191, 54));
        setStepRateFillDrawableDefaultGradient(hwHealthLineDataSet, true);
        return hwHealthLineDataSet;
    }

    private HwHealthLineDataSet getHwHealthLinePaddleDataSet(ArrayList<HwHealthBaseEntry> arrayList) {
        String string = this.mContext.getResources().getString("291".equals(this.mSportMode) ? R$string.IDS_rowing_machine_action_frequency : R$string.IDS_indoor_equip_paddle_frequency);
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(BaseApplication.getContext(), arrayList, string, string, this.mContext.getResources().getString(R$string.IDS_indoor_equip_paddle_unit_times_minute), acquirePaddleFreqInterval());
        hwHealthLineDataSet.setColor(Color.argb(255, 253, 191, 54));
        setPaddleFreqFillDrawableDefaultGradient(hwHealthLineDataSet, true);
        return hwHealthLineDataSet;
    }

    private void handlePaddleFreqDataSet(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar, HwHealthLineDataSet hwHealthLineDataSet) {
        setThemeAndOrientation(hwHealthBaseCombinedChart, bVar, hwHealthLineDataSet);
        ntl.e(hwHealthLineDataSet);
        new nqp().initStyle(hwHealthBaseCombinedChart, hwHealthLineDataSet);
        addDataSet(hwHealthBaseCombinedChart, hwHealthLineDataSet);
    }

    private void setPaddleFreqFillDrawableDefaultGradient(HwHealthLineDataSet hwHealthLineDataSet, boolean z) {
        hwHealthLineDataSet.b(Color.argb(127, 255, 176, 0), Color.argb(0, 255, 176, 0), z);
    }

    public HwHealthLineDataSet addCadenceRateDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        return addCadenceRateDataLayer(hwHealthBaseCombinedChart, this.mDefaultShowMode);
    }

    public HwHealthLineDataSet addCadenceRateDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar) {
        if (hwHealthBaseCombinedChart == null || bVar == null || this.mContext == null) {
            LogUtil.h(TAG, "chart or showMode ,mContext is null");
            return null;
        }
        return addCadenceRateDataLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar);
    }

    private HwHealthLineDataSet addCadenceRateDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, Mode mode, b bVar) {
        LogUtil.a(TAG, "addCadenceRateDataLayer mode:", mode);
        ArrayList<HwHealthBaseEntry> arrayList = new ArrayList<>(16);
        List<ffn> acquireRidePostureData = acquireRidePostureData();
        if (koq.b(acquireRidePostureData)) {
            LogUtil.h(TAG, "initCadenceRate ridePostureDataList empty,return");
            return null;
        }
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        double d2 = 0.0d;
        int i3 = 0;
        for (ffn ffnVar : acquireRidePostureData) {
            if (ffnVar != null) {
                arrayList.add(new HwHealthBaseEntry(ffnVar.acquireTime(), ffnVar.e()));
                if (ffnVar.e() > i) {
                    i = ffnVar.e();
                }
                if (ffnVar.e() < i2) {
                    i2 = ffnVar.e();
                }
                d2 += ffnVar.e();
                i3++;
            }
        }
        if (!hwHealthBaseCombinedChart.containsMarkViewShow()) {
            hwHealthBaseCombinedChart.highlightDefValue(-1, true);
        }
        HwHealthLineDataSet hwHealthLineCadenceDataSet = getHwHealthLineCadenceDataSet(arrayList);
        if (mode != Mode.MODE_NONE) {
            LogUtil.a(TAG, "addCadenceRateDataLayer customAxisByDataBoard mode=", mode);
            ntl.d(new d(hwHealthBaseCombinedChart, hwHealthLineCadenceDataSet, mode, i, i3 > 0 ? (float) (d2 / i3) : 0.0f, i2, bVar.e, false, false));
        }
        float acquireCadenceSumTime = acquireCadenceSumTime();
        if (acquireCadenceSumTime > 0.0f) {
            LogUtil.c(TAG, "addCadenceRateDataLayer acquireRunningPostureDataSumTime:", LogAnonymous.d((long) acquireCadenceSumTime));
            hwHealthLineCadenceDataSet.setXMaxForcedValue(acquireCadenceSumTime);
        }
        if (bVar.e) {
            setCadenceRateFillDrawableDefaultGradient(hwHealthLineCadenceDataSet, false);
        }
        handleCadenceRateDataSet(hwHealthBaseCombinedChart, bVar, hwHealthLineCadenceDataSet);
        return hwHealthLineCadenceDataSet;
    }

    private HwHealthLineDataSet getHwHealthLineCadenceDataSet(ArrayList<HwHealthBaseEntry> arrayList) {
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(BaseApplication.getContext(), arrayList, this.mContext.getResources().getString(R$string.IDS_indoor_equip_cadence), this.mContext.getResources().getString(R$string.IDS_indoor_equip_cadence), this.mContext.getResources().getString(R$string.IDS_indoor_equip_cadence_unit_times_minute), acquireCadenceInterval());
        hwHealthLineDataSet.setColor(Color.argb(255, FitnessSleepType.HW_FITNESS_NOON, 111, 33));
        setCadenceRateFillDrawableDefaultGradient(hwHealthLineDataSet, true);
        return hwHealthLineDataSet;
    }

    private void handleCadenceRateDataSet(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar, HwHealthLineDataSet hwHealthLineDataSet) {
        setThemeAndOrientation(hwHealthBaseCombinedChart, bVar, hwHealthLineDataSet);
        ntl.e(hwHealthLineDataSet);
        new nqp().initStyle(hwHealthBaseCombinedChart, hwHealthLineDataSet);
        addDataSet(hwHealthBaseCombinedChart, hwHealthLineDataSet);
    }

    private void setCadenceRateFillDrawableDefaultGradient(HwHealthLineDataSet hwHealthLineDataSet, boolean z) {
        hwHealthLineDataSet.b(Color.argb(127, FitnessSleepType.HW_FITNESS_NOON, 111, 33), Color.argb(0, FitnessSleepType.HW_FITNESS_NOON, 111, 33), z);
    }

    public HwHealthLineDataSet addPowerDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        return addPowerDataLayer(hwHealthBaseCombinedChart, this.mDefaultShowMode);
    }

    public HwHealthLineDataSet addWeightDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        return addWeightDataLayer(hwHealthBaseCombinedChart, this.mDefaultShowMode);
    }

    public HwHealthLineDataSet addWeightDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar) {
        if (hwHealthBaseCombinedChart == null || bVar == null || this.mContext == null) {
            LogUtil.h(TAG, "chart or showMode ,mContext is null");
            return null;
        }
        return addWeightDataLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar);
    }

    public HwHealthLineDataSet addSkippingSpeedDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        return addSkippingSpeedDataLayer(hwHealthBaseCombinedChart, this.mDefaultShowMode);
    }

    public HwHealthLineDataSet addPowerDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar) {
        if (hwHealthBaseCombinedChart == null || bVar == null || this.mContext == null) {
            LogUtil.h(TAG, "chart or showMode ,mContext is null");
            return null;
        }
        return addPowerDataLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar);
    }

    private HwHealthLineDataSet addWeightDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, Mode mode, b bVar) {
        LogUtil.a(TAG, "addWeightDataLayer mode:", mode);
        ArrayList<HwHealthBaseEntry> arrayList = new ArrayList<>(16);
        List<kom> acquireWeightData = acquireWeightData();
        if (koq.b(acquireWeightData)) {
            LogUtil.h(TAG, "initWeightData weightDataList empty,return");
            return null;
        }
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        double d2 = 0.0d;
        int i3 = 0;
        for (kom komVar : acquireWeightData) {
            if (komVar != null) {
                arrayList.add(new HwHealthBaseEntry(komVar.acquireTime(), komVar.d()));
                if (komVar.d() > i) {
                    i = komVar.d();
                }
                if (komVar.d() < i2) {
                    i2 = komVar.d();
                }
                d2 += komVar.d();
                i3++;
            }
        }
        if (!hwHealthBaseCombinedChart.containsMarkViewShow()) {
            hwHealthBaseCombinedChart.highlightDefValue(-1, true);
        }
        HwHealthLineDataSet hwHealthLineWeightDataSet = getHwHealthLineWeightDataSet(arrayList);
        if (mode != Mode.MODE_NONE) {
            LogUtil.a(TAG, "addWeightDataLayer customAxisByDataBoard mode=", mode);
            ntl.d(new d(hwHealthBaseCombinedChart, hwHealthLineWeightDataSet, mode, i, i3 > 0 ? (float) (d2 / i3) : 0.0f, i2, bVar.e, false, false));
        }
        if (bVar.e) {
            hwHealthLineWeightDataSet.b(Color.argb(128, 0, a.A, 38), Color.argb(0, 0, a.A, 38), false);
        }
        handlePowerDataSet(hwHealthBaseCombinedChart, bVar, hwHealthLineWeightDataSet);
        return hwHealthLineWeightDataSet;
    }

    private HwHealthLineDataSet getHwHealthLineWeightDataSet(ArrayList<HwHealthBaseEntry> arrayList) {
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(BaseApplication.getContext(), arrayList, this.mContext.getResources().getString(R$string.IDS_rowing_machine_weight), this.mContext.getResources().getString(R$string.IDS_rowing_machine_weight), this.mContext.getResources().getString(UnitUtil.h() ? R$string.IDS_lbs : R$string.IDS_rowing_machine_unit_kg), acquireWeightDataInterval());
        hwHealthLineDataSet.setColor(Color.argb(255, 0, a.A, 38));
        hwHealthLineDataSet.b(Color.argb(128, 0, a.A, 38), Color.argb(0, 0, a.A, 38), true);
        return hwHealthLineDataSet;
    }

    private HwHealthLineDataSet addPowerDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, Mode mode, b bVar) {
        LogUtil.a(TAG, "addPowerDataLayer mode:", mode);
        ArrayList<HwHealthBaseEntry> arrayList = new ArrayList<>(16);
        List<koc> acquirePoweData = acquirePoweData();
        if (koq.b(acquirePoweData)) {
            LogUtil.h(TAG, "initPowerData powerDataList empty,return");
            return null;
        }
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        double d2 = 0.0d;
        int i3 = 0;
        for (koc kocVar : acquirePoweData) {
            if (kocVar != null) {
                arrayList.add(new HwHealthBaseEntry(kocVar.acquireTime(), kocVar.b()));
                if (kocVar.b() > i) {
                    i = kocVar.b();
                }
                if (kocVar.b() < i2) {
                    i2 = kocVar.b();
                }
                d2 += kocVar.b();
                i3++;
            }
        }
        if (!hwHealthBaseCombinedChart.containsMarkViewShow()) {
            hwHealthBaseCombinedChart.highlightDefValue(-1, true);
        }
        HwHealthLineDataSet hwHealthLinePowerDataSet = getHwHealthLinePowerDataSet(arrayList);
        if (mode != Mode.MODE_NONE) {
            LogUtil.a(TAG, "addPowerDataLayer customAxisByDataBoard mode=", mode);
            ntl.d(new d(hwHealthBaseCombinedChart, hwHealthLinePowerDataSet, mode, i, i3 > 0 ? (float) (d2 / i3) : 0.0f, i2, bVar.e, false, false));
        }
        if (bVar.e) {
            setPowerDataFillDrawableDefaultGradient(hwHealthLinePowerDataSet, false);
        }
        handlePowerDataSet(hwHealthBaseCombinedChart, bVar, hwHealthLinePowerDataSet);
        return hwHealthLinePowerDataSet;
    }

    private HwHealthLineDataSet getHwHealthLinePowerDataSet(ArrayList<HwHealthBaseEntry> arrayList) {
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(BaseApplication.getContext(), arrayList, this.mContext.getResources().getString(R$string.IDS_indoor_equip_power), this.mContext.getResources().getString(R$string.IDS_indoor_equip_power), this.mContext.getResources().getString(R$string.IDS_indoor_equip_power_unit_watt), acquirePowerDataInterval());
        hwHealthLineDataSet.setColor(Color.argb(255, 0, 124, 255));
        setPowerDataFillDrawableDefaultGradient(hwHealthLineDataSet, true);
        return hwHealthLineDataSet;
    }

    private void handlePowerDataSet(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar, HwHealthLineDataSet hwHealthLineDataSet) {
        setThemeAndOrientation(hwHealthBaseCombinedChart, bVar, hwHealthLineDataSet);
        ntl.e(hwHealthLineDataSet);
        new nqp().initStyle(hwHealthBaseCombinedChart, hwHealthLineDataSet);
        addDataSet(hwHealthBaseCombinedChart, hwHealthLineDataSet);
    }

    private void setPowerDataFillDrawableDefaultGradient(HwHealthLineDataSet hwHealthLineDataSet, boolean z) {
        hwHealthLineDataSet.b(Color.argb(127, 0, 124, 255), Color.argb(0, 0, 124, 255), z);
    }

    public HwHealthLineDataSet addSkippingSpeedDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar) {
        if (hwHealthBaseCombinedChart == null || bVar == null || this.mContext == null) {
            LogUtil.h(TAG, "chart or showMode ,mContext is null");
            return null;
        }
        return addSkippingSpeedLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar);
    }

    private HwHealthLineDataSet addSkippingSpeedLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, Mode mode, b bVar) {
        LogUtil.a(TAG, "addSkippingSpeedLayer mode:", mode);
        ArrayList<HwHealthBaseEntry> arrayList = new ArrayList<>(16);
        List<kob> acquireSkippingSpeedData = acquireSkippingSpeedData();
        if (koq.b(acquireSkippingSpeedData)) {
            LogUtil.h(TAG, "initPowerData skippingSpeedList empty,return");
            return null;
        }
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        double d2 = 0.0d;
        int i3 = 0;
        for (kob kobVar : acquireSkippingSpeedData) {
            if (kobVar != null) {
                arrayList.add(new HwHealthBaseEntry(kobVar.acquireTime(), kobVar.c()));
                if (kobVar.c() > i) {
                    i = kobVar.c();
                }
                if (kobVar.c() < i2) {
                    i2 = kobVar.c();
                }
                d2 += kobVar.c();
                i3++;
            }
        }
        if (!hwHealthBaseCombinedChart.containsMarkViewShow()) {
            hwHealthBaseCombinedChart.highlightDefValue(-1, true);
        }
        HwHealthLineDataSet hwHealthLineSkippingSpeedSet = getHwHealthLineSkippingSpeedSet(arrayList);
        if (mode != Mode.MODE_NONE) {
            LogUtil.a(TAG, "addPowerDataLayer customAxisByDataBoard mode=", mode);
            ntl.d(new d(hwHealthBaseCombinedChart, hwHealthLineSkippingSpeedSet, mode, i, i3 > 0 ? (float) (d2 / i3) : 0.0f, i2, bVar.e, false, false));
        }
        if (bVar.e) {
            setSpeedFillDrawableDefaultGradient(hwHealthLineSkippingSpeedSet, false);
        }
        handleSkippingSpeedSet(hwHealthBaseCombinedChart, bVar, hwHealthLineSkippingSpeedSet);
        return hwHealthLineSkippingSpeedSet;
    }

    private HwHealthLineDataSet getHwHealthLineSkippingSpeedSet(ArrayList<HwHealthBaseEntry> arrayList) {
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(BaseApplication.getContext(), arrayList, this.mContext.getResources().getString(R$string.IDS_motiontrack_show_sport_tip_icon_text_pace), this.mContext.getResources().getString(R$string.IDS_motiontrack_show_sport_tip_icon_text_pace), this.mContext.getResources().getString(R$string.IDS_indoor_skipper_number_minute), acquireSkippingSpeedInterval());
        hwHealthLineDataSet.setColor(Color.argb(255, 0, a.A, 38));
        setSpeedFillDrawableDefaultGradient(hwHealthLineDataSet, true);
        return hwHealthLineDataSet;
    }

    private void handleSkippingSpeedSet(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar, HwHealthLineDataSet hwHealthLineDataSet) {
        setThemeAndOrientation(hwHealthBaseCombinedChart, bVar, hwHealthLineDataSet);
        ntl.e(hwHealthLineDataSet);
        new nqp().initStyle(hwHealthBaseCombinedChart, hwHealthLineDataSet);
        addDataSet(hwHealthBaseCombinedChart, hwHealthLineDataSet);
    }

    public void addRecoverHeartRateLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        addRecoverHeartRateLayer(hwHealthBaseCombinedChart, this.mDefaultShowMode);
    }

    public void addRecoverHeartRateLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar) {
        if (hwHealthBaseCombinedChart == null || bVar == null) {
            return;
        }
        addRecoverHeartRateLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar);
    }

    private void addRecoverHeartRateLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, Mode mode, b bVar) {
        HwHealthLineDataSet hwHealthLineDataSet;
        ArrayList arrayList = new ArrayList(16);
        List<HeartRateData> acquireTrackHeartRateData = acquireTrackHeartRateData();
        if (koq.b(acquireTrackHeartRateData)) {
            LogUtil.h(TAG, "initHeartRate heartRateList empty,return");
            return;
        }
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        double d2 = 0.0d;
        int i3 = 0;
        for (HeartRateData heartRateData : acquireTrackHeartRateData) {
            if (heartRateData != null) {
                arrayList.add(new HwHealthBaseEntry(acquireHeartRateDataInterval() * i3, heartRateData.acquireHeartRate()));
                if (heartRateData.acquireHeartRate() > i2) {
                    i2 = heartRateData.acquireHeartRate();
                }
                if (heartRateData.acquireHeartRate() < i) {
                    i = heartRateData.acquireHeartRate();
                }
                d2 += heartRateData.acquireHeartRate();
                i3++;
            }
        }
        hwHealthBaseCombinedChart.refresh();
        HwHealthLineDataSet hwHealthLineDataSet2 = new HwHealthLineDataSet(BaseApplication.getContext(), arrayList, this.mContext.getResources().getString(R$string.IDS_main_watch_heart_rate_string), this.mContext.getResources().getString(R$string.IDS_main_watch_heart_rate_string), this.mContext.getResources().getString(R$string.IDS_main_watch_heart_rate_unit_string), acquireHeartRateDataInterval());
        if (acquireRecoverHeartRateDataSumTime() > 0.0f) {
            hwHealthLineDataSet2.setXMaxForcedValue(acquireRecoverHeartRateDataSumTime());
        }
        hwHealthLineDataSet2.setDrawStartEndNode(true);
        hwHealthLineDataSet2.setDrawStartEndValue(true);
        hwHealthLineDataSet2.setDrawCircles(true);
        hwHealthLineDataSet2.setValueFormatter(new nmw());
        hwHealthLineDataSet2.setColor(Color.argb(255, 252, 49, 89));
        hwHealthLineDataSet2.d(Color.argb(229, a.L, 70, 94));
        setHeartRateFillDrawableDefaultGradient(hwHealthLineDataSet2, true);
        LogUtil.a(TAG, "addHeartRateDataLayer customAxisByDataBoard mode=", mode);
        if (mode != Mode.MODE_NONE) {
            hwHealthLineDataSet = hwHealthLineDataSet2;
            ntl.d(new d(hwHealthBaseCombinedChart, hwHealthLineDataSet2, mode, i2, i3 > 0 ? (float) (d2 / i3) : 0.0f, i, bVar.e, false, false));
        } else {
            hwHealthLineDataSet = hwHealthLineDataSet2;
        }
        handleHeartRateDataSet(hwHealthBaseCombinedChart, bVar, hwHealthLineDataSet);
    }

    public HwHealthBarDataSet addJumpTimeDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        return addJumpTimeDataLayer(hwHealthBaseCombinedChart, this.mDefaultShowMode);
    }

    public HwHealthBarDataSet addJumpTimeDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar) {
        if (hwHealthBaseCombinedChart == null || bVar == null) {
            return null;
        }
        return addJumpTimeDataLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar);
    }

    private HwHealthBarDataSet addJumpTimeDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, Mode mode, b bVar) {
        LogUtil.a(TAG, "addJumpTimeDataLayer mode:", mode);
        ArrayList arrayList = new ArrayList(16);
        ArrayList<ixt> acquireTrackJumpData = acquireTrackJumpData();
        if (koq.b(acquireTrackJumpData)) {
            LogUtil.h(TAG, "init trackJumpData empty,return");
            return null;
        }
        Iterator<ixt> it = acquireTrackJumpData.iterator();
        int i = 0;
        double d2 = 0.0d;
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MAX_VALUE;
        while (it.hasNext()) {
            ixt next = it.next();
            if (next != null) {
                arrayList.add(new HwHealthBarEntry(next.b(), new nnc(next.e())));
                if (next.e() > i2) {
                    i2 = next.e();
                }
                d2 += next.e();
                i++;
                i3 = 0;
            }
        }
        if (!hwHealthBaseCombinedChart.containsMarkViewShow()) {
            hwHealthBaseCombinedChart.highlightDefValue(-1, true);
        }
        HwHealthBarDataSet d3 = ntl.d(arrayList, this.mContext);
        float acquireJumpTotalTime = acquireJumpTotalTime();
        if (acquireJumpTotalTime > 0.0f) {
            LogUtil.c(TAG, "addSpo2DataLayer acquire total SumTime:", Float.valueOf(acquireJumpTotalTime));
            d3.setXMaxForcedValue(acquireJumpTotalTime);
        }
        if (mode != Mode.MODE_NONE) {
            LogUtil.a(TAG, "addJumpTimeDataLayer customAxisByDataBoard mode=", mode, " max=", Integer.valueOf(i2), " min=", Integer.valueOf(i3));
            ntl.d(new d(hwHealthBaseCombinedChart, d3, mode, i2, i > 0 ? (float) (d2 / i) : 0.0f, i3, bVar.e, false, false));
        }
        ntl.d(hwHealthBaseCombinedChart, bVar, d3, 255, 0.2f, 255, 9.0f, this.mModes);
        return d3;
    }

    public HwHealthBarDataSet addJumpHeightDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        return addJumpHeightDataLayer(hwHealthBaseCombinedChart, this.mDefaultShowMode);
    }

    public HwHealthBarDataSet addJumpHeightDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar) {
        if (hwHealthBaseCombinedChart == null || bVar == null) {
            return null;
        }
        return addJumpHeightDataLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar);
    }

    private HwHealthBarDataSet addJumpHeightDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, Mode mode, b bVar) {
        HwHealthBarEntry hwHealthBarEntry;
        int c2;
        LogUtil.a(TAG, "addJumpHeightDataLayer mode:", mode);
        ArrayList arrayList = new ArrayList(16);
        ArrayList<ixt> acquireTrackJumpData = acquireTrackJumpData();
        if (koq.b(acquireTrackJumpData)) {
            LogUtil.h(TAG, "init trackJumpData empty,return");
            return null;
        }
        Iterator<ixt> it = acquireTrackJumpData.iterator();
        int i = 0;
        double d2 = 0.0d;
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MAX_VALUE;
        while (it.hasNext()) {
            ixt next = it.next();
            if (next != null) {
                if (UnitUtil.h()) {
                    hwHealthBarEntry = new HwHealthBarEntry(next.b(), new nnc((int) UnitUtil.e(next.c(), 0)));
                } else {
                    hwHealthBarEntry = new HwHealthBarEntry(next.b(), new nnc(next.c()));
                }
                arrayList.add(hwHealthBarEntry);
                if (UnitUtil.h()) {
                    c2 = (int) UnitUtil.e(next.c(), 0);
                } else {
                    c2 = next.c();
                }
                if (c2 > i2) {
                    i2 = c2;
                }
                d2 += c2;
                i++;
                i3 = 0;
            }
        }
        if (!hwHealthBaseCombinedChart.containsMarkViewShow()) {
            hwHealthBaseCombinedChart.highlightDefValue(-1, true);
        }
        HwHealthBarDataSet handleJumpHeightUnit = handleJumpHeightUnit(arrayList);
        if (mode != Mode.MODE_NONE) {
            LogUtil.a(TAG, "addJumpTimeDataLayer customAxisByDataBoard mode=", mode, " max=", Integer.valueOf(i2), " min=", Integer.valueOf(i3));
            ntl.d(new d(hwHealthBaseCombinedChart, handleJumpHeightUnit, mode, i2, i > 0 ? (float) (d2 / i) : 0.0f, i3, bVar.e, false, false));
        }
        ntl.d(hwHealthBaseCombinedChart, bVar, handleJumpHeightUnit, 255, 0.2f, 255, 9.0f, this.mModes);
        return handleJumpHeightUnit;
    }

    private HwHealthBarDataSet handleJumpHeightUnit(List<HwHealthBarEntry> list) {
        String c2 = ntl.c(this.mContext);
        String string = this.mContext.getResources().getString(R$string.IDS_aw_version2_jump_height);
        return ntl.b(acquireJumpTotalTime(), new HwHealthBarDataSet(list, string, string, c2));
    }

    protected ArrayList<knz> acquireTrackAltitudeDataFilter() {
        return ntl.d(acquireTrackAltitudeData());
    }

    protected ArrayList<koi> acquireTrackSpeedDataFilter() {
        return ntl.e(acquireTrackRealTimeSpeedData());
    }

    public HwHealthLineDataSet addTrackAltitudeDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        return addTrackAltitudeDataLayer(hwHealthBaseCombinedChart, this.mDefaultShowMode);
    }

    public HwHealthLineDataSet addTrackSpeedDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        return addTrackSpeedDataLayer(hwHealthBaseCombinedChart, this.mDefaultShowMode);
    }

    public HwHealthLineDataSet addTrackAltitudeDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar) {
        if (hwHealthBaseCombinedChart == null || bVar == null || this.mContext == null) {
            LogUtil.h(TAG, "chart or showMode ,mContext is null");
            return null;
        }
        return addTrackAltitudeDataLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar);
    }

    public HwHealthLineDataSet addTrackSpeedDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar) {
        if (hwHealthBaseCombinedChart == null || bVar == null || this.mContext == null) {
            LogUtil.h(TAG, "chart or showMode ,mContext is null");
            return null;
        }
        return addTrackSpeedDataLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar);
    }

    private HwHealthLineDataSet addTrackAltitudeDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, Mode mode, b bVar) {
        HwHealthLineDataSet hwHealthLineDataSet;
        LogUtil.a(TAG, "addTrackAltitudeDataLayer mode:", mode);
        ArrayList<HwHealthBaseEntry> arrayList = new ArrayList<>(16);
        ArrayList<knz> acquireTrackAltitudeDataFilter = acquireTrackAltitudeDataFilter();
        if (koq.b(acquireTrackAltitudeDataFilter)) {
            LogUtil.h(TAG, "initTrackAltitude trackAltitudeList empty,return");
            return null;
        }
        if (acquireTrackAltitudeShowType() == 0) {
            acquireTrackAltitudeDataFilter = ntl.a(acquireTrackAltitudeDataFilter);
        }
        Iterator<knz> it = acquireTrackAltitudeDataFilter.iterator();
        double d2 = 0.0d;
        int i = 0;
        float f = Float.MIN_VALUE;
        float f2 = Float.MAX_VALUE;
        while (it.hasNext()) {
            knz next = it.next();
            if (next != null) {
                Iterator<knz> it2 = it;
                arrayList.add(new HwHealthBaseEntry(next.acquireTime(), (float) next.c()));
                if (next.c() > f) {
                    f = (float) next.c();
                }
                if (next.c() < f2) {
                    f2 = (float) next.c();
                }
                d2 += next.c();
                i++;
                it = it2;
            }
        }
        if (!hwHealthBaseCombinedChart.containsMarkViewShow()) {
            hwHealthBaseCombinedChart.highlightDefValue(-1, true);
        }
        HwHealthLineDataSet handleAltitudeUnit = handleAltitudeUnit(arrayList);
        handleAltitudeUnit.setColor(Color.argb(255, 29, 204, 205));
        setAltitudeFillDrawableDefaultGradient(handleAltitudeUnit, true);
        boolean z = acquireTrackAltitudeShowType() == 1;
        if (mode != Mode.MODE_NONE) {
            LogUtil.a(TAG, "addTrackAltitudeDataLayer customAxisByDataBoard mode=", mode, " max=", LogAnonymous.b((int) f), " min=", LogAnonymous.b((int) f2));
            float f3 = i > 0 ? (float) (d2 / i) : 0.0f;
            hwHealthLineDataSet = handleAltitudeUnit;
            ntl.d(new d(hwHealthBaseCombinedChart, handleAltitudeUnit, mode, f, f3, f2, bVar.e, z, false));
        } else {
            hwHealthLineDataSet = handleAltitudeUnit;
        }
        handleAltitudeDataSet(hwHealthBaseCombinedChart, bVar, hwHealthLineDataSet);
        return hwHealthLineDataSet;
    }

    private void handleAltitudeDataSet(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar, HwHealthLineDataSet hwHealthLineDataSet) {
        if (bVar.e) {
            setAltitudeFillDrawableDefaultGradient(hwHealthLineDataSet, false);
        }
        setThemeAndOrientation(hwHealthBaseCombinedChart, bVar, hwHealthLineDataSet);
        ntl.e(hwHealthLineDataSet);
        new nqp().initStyle(hwHealthBaseCombinedChart, hwHealthLineDataSet);
        addDataSet(hwHealthBaseCombinedChart, hwHealthLineDataSet);
    }

    private HwHealthLineDataSet handleAltitudeUnit(ArrayList<HwHealthBaseEntry> arrayList) {
        String string;
        if (acquireTrackAltitudeShowType() == 0) {
            string = this.mContext.getResources().getString(R$string.IDS_hwh_motiontrack_climbed_withot_unit);
        } else {
            string = this.mContext.getResources().getString(R$string.IDS_hwh_motiontrack_alti);
        }
        String str = string;
        return ntl.c(new HwHealthLineDataSet(BaseApplication.getContext(), arrayList, str, str, ntl.a(this.mContext), acquireTrackAltitudeDataInterval()));
    }

    private void setAltitudeFillDrawableDefaultGradient(HwHealthLineDataSet hwHealthLineDataSet, boolean z) {
        hwHealthLineDataSet.b(Color.argb(127, 29, 204, 205), Color.argb(0, 29, 204, 205), z);
    }

    private HwHealthLineDataSet addTrackSpeedDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, Mode mode, b bVar) {
        String str = TAG;
        LogUtil.a(TAG, "addTrackSpeedDataLayer mode:", mode);
        ArrayList<HwHealthBaseEntry> arrayList = new ArrayList<>(16);
        ArrayList<koi> acquireTrackSpeedDataFilter = acquireTrackSpeedDataFilter();
        if (koq.b(acquireTrackSpeedDataFilter)) {
            LogUtil.h(TAG, "initTrackSpeed trackSpeedList empty,return");
            return null;
        }
        Iterator<koi> it = acquireTrackSpeedDataFilter.iterator();
        float f = Float.MIN_VALUE;
        float f2 = Float.MAX_VALUE;
        int i = 0;
        double d2 = 0.0d;
        while (it.hasNext()) {
            koi next = it.next();
            if (next != null) {
                String str2 = str;
                Iterator<koi> it2 = it;
                arrayList.add(new HwHealthBaseEntry(next.acquireTime(), (float) next.e()));
                if (next.e() > f) {
                    f = (float) next.e();
                }
                if (next.e() < f2) {
                    f2 = (float) next.e();
                }
                d2 += next.e();
                i++;
                str = str2;
                it = it2;
            }
        }
        String str3 = str;
        if (!hwHealthBaseCombinedChart.containsMarkViewShow()) {
            hwHealthBaseCombinedChart.highlightDefValue(-1, true);
        }
        HwHealthLineDataSet hwHealthLineDataSet = getHwHealthLineDataSet(arrayList);
        if (mode != Mode.MODE_NONE) {
            LogUtil.a(str3, "addTrackSpeedDataLayer customAxisByDataBoard mode=", mode);
            ntl.d(new d(hwHealthBaseCombinedChart, hwHealthLineDataSet, mode, f, i > 0 ? (float) (d2 / i) : 0.0f, f2, bVar.e, false, false));
        }
        handleSpeedDataSet(hwHealthBaseCombinedChart, bVar, hwHealthLineDataSet);
        return hwHealthLineDataSet;
    }

    private void handleSpeedDataSet(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar, HwHealthLineDataSet hwHealthLineDataSet) {
        if (bVar.e) {
            setSpeedFillDrawableDefaultGradient(hwHealthLineDataSet, false);
        }
        setThemeAndOrientation(hwHealthBaseCombinedChart, bVar, hwHealthLineDataSet);
        ntl.e(hwHealthLineDataSet);
        new nqp().initStyle(hwHealthBaseCombinedChart, hwHealthLineDataSet);
        addDataSet(hwHealthBaseCombinedChart, hwHealthLineDataSet);
    }

    private void setSpeedFillDrawableDefaultGradient(HwHealthLineDataSet hwHealthLineDataSet, boolean z) {
        hwHealthLineDataSet.b(Color.argb(128, 0, a.A, 38), Color.argb(0, 0, a.A, 38), z);
    }

    private HwHealthLineDataSet getHwHealthLineDataSet(ArrayList<HwHealthBaseEntry> arrayList) {
        HwHealthLineDataSet hwHealthLineDataSet;
        if (!UnitUtil.h()) {
            hwHealthLineDataSet = new HwHealthLineDataSet(BaseApplication.getContext(), arrayList, this.mContext.getResources().getString(R$string.IDS_motiontrack_show_sport_tip_icon_text_pace), this.mContext.getResources().getString(R$string.IDS_motiontrack_show_sport_tip_icon_text_pace), this.mContext.getResources().getString(R$string.IDS_motiontrack_show_detail_average_speed), acquireTrackRealTimeSpeedDataInterval());
        } else {
            hwHealthLineDataSet = new HwHealthLineDataSet(BaseApplication.getContext(), arrayList, this.mContext.getResources().getString(R$string.IDS_motiontrack_show_sport_tip_icon_text_pace), this.mContext.getResources().getString(R$string.IDS_motiontrack_show_sport_tip_icon_text_pace), this.mContext.getResources().getString(R$string.IDS_motiontrack_show_detail_average_speed_imp), acquireTrackRealTimeSpeedDataInterval());
        }
        hwHealthLineDataSet.a(2);
        hwHealthLineDataSet.setColor(Color.argb(255, 0, a.A, 38));
        setSpeedFillDrawableDefaultGradient(hwHealthLineDataSet, true);
        return hwHealthLineDataSet;
    }

    public boolean isExistHeartRateData() {
        if (koq.b(acquireHeartRateData())) {
            return false;
        }
        Iterator<HeartRateData> it = acquireHeartRateData().iterator();
        while (it.hasNext()) {
            HeartRateData next = it.next();
            if (next != null && next.acquireHeartRate() != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistStepRateData() {
        if (koq.b(acquireStepRateData())) {
            return false;
        }
        Iterator<StepRateData> it = acquireStepRateData().iterator();
        while (it.hasNext()) {
            StepRateData next = it.next();
            if (next != null && next.acquireStepRate() != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistCadenceRateData() {
        if (koq.b(acquireRidePostureData())) {
            return false;
        }
        for (ffn ffnVar : acquireRidePostureData()) {
            if (ffnVar != null && ffnVar.e() != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistPaddleFreqData() {
        if (koq.b(acquirePaddleFrequencyData())) {
            return false;
        }
        for (knw knwVar : acquirePaddleFrequencyData()) {
            if (knwVar != null && knwVar.b() != 0.0f) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistWeightFreqData() {
        if (koq.b(acquireWeightData())) {
            return false;
        }
        for (kom komVar : acquireWeightData()) {
            if (komVar != null && komVar.acquireTime() != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistPowerData() {
        if (koq.b(acquirePoweData())) {
            return false;
        }
        for (koc kocVar : acquirePoweData()) {
            if (kocVar != null && kocVar.b() != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistSkippingSpeedData() {
        if (koq.b(acquireSkippingSpeedData())) {
            return false;
        }
        for (kob kobVar : acquireSkippingSpeedData()) {
            if (kobVar != null && kobVar.c() != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistAltitudeData() {
        if (koq.b(acquireTrackAltitudeData())) {
            return false;
        }
        Iterator<knz> it = acquireTrackAltitudeData().iterator();
        while (it.hasNext()) {
            knz next = it.next();
            if (next != null && !CommonUtil.c(next.c())) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistSpeedData() {
        if (koq.b(acquireTrackRealTimeSpeedData())) {
            return false;
        }
        Iterator<koi> it = acquireTrackRealTimeSpeedData().iterator();
        while (it.hasNext()) {
            koi next = it.next();
            if (next != null && !CommonUtil.c(next.e())) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistSwolfData() {
        if (koq.b(acquireSwolfData())) {
            return false;
        }
        for (kol kolVar : acquireSwolfData()) {
            if (kolVar != null && kolVar.c() != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistPullFreqData() {
        if (koq.b(acquirePullFreqData())) {
            return false;
        }
        for (kog kogVar : acquirePullFreqData()) {
            if (kogVar != null && kogVar.d() != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistRealTimePaceData() {
        if (koq.b(acquireTrackRealTimePaceData())) {
            return false;
        }
        for (koh kohVar : acquireTrackRealTimePaceData()) {
            if (kohVar != null && kohVar.a() != 0) {
                return true;
            }
        }
        return koq.c(acquireTrackRealTimePaceData());
    }

    public boolean isExistSpo2Data() {
        if (koq.b(acquireTrackSpo2Data())) {
            return false;
        }
        for (kof kofVar : acquireTrackSpo2Data()) {
            if (kofVar != null && kofVar.b() != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistRunningPostureData() {
        if (koq.b(acquireRunningPostureData())) {
            return false;
        }
        for (ffs ffsVar : acquireRunningPostureData()) {
            if (ffsVar != null && !ntl.c(ffsVar)) {
                return true;
            }
        }
        return false;
    }

    public boolean isGcBalanceValid() {
        return fgf.c(acquireRunningPostureData());
    }

    public boolean isActivePeakValid() {
        return fgf.b(acquireRunningPostureData());
    }

    public boolean isVerticalRatioValid() {
        return fgf.h(acquireRunningPostureData());
    }

    public boolean isContractTimeValid() {
        return fgf.a(acquireRunningPostureData());
    }

    public boolean isImpactHangRateValid() {
        return fgf.j(acquireRunningPostureData());
    }

    public boolean isHangTimeValid() {
        return fgf.f(acquireRunningPostureData());
    }

    public boolean isVerticalOscillationValid() {
        return fgf.i(acquireRunningPostureData());
    }

    public boolean isGroundImpactAccelerationValid() {
        return fgf.d(acquireRunningPostureData());
    }

    public boolean isExistNewRunningPostureData() {
        if (koq.b(acquireRunningPostureData())) {
            return false;
        }
        for (ffs ffsVar : acquireRunningPostureData()) {
            if (ffsVar != null && !ntl.d(ffsVar)) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistBoltData() {
        if (koq.b(acquireRunningPostureData())) {
            return false;
        }
        for (ffs ffsVar : acquireRunningPostureData()) {
            if (ffsVar != null && ntl.e(ffsVar)) {
                return true;
            }
        }
        return false;
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        public int f8983a;
        public int e;

        public c(int i, int i2) {
            this.f8983a = i;
            this.e = i2;
        }
    }

    public HwHealthLineDataSet addTrackSwolfDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        return addTrackSwolfDataLayer(hwHealthBaseCombinedChart, this.mDefaultShowMode);
    }

    public HwHealthLineDataSet addTrackSwolfDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar) {
        if (hwHealthBaseCombinedChart == null || bVar == null || this.mContext == null) {
            LogUtil.h(TAG, "chart or showMode ,mContext is null");
            return null;
        }
        return addTrackSwolfDataLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar);
    }

    private HwHealthLineDataSet addTrackSwolfDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, Mode mode, b bVar) {
        HwHealthLineDataSet hwHealthLineDataSet;
        LogUtil.a(TAG, "addTrackSwolfDataLayer mode:", mode);
        ArrayList arrayList = new ArrayList(16);
        List<kol> acquireSwolfData = acquireSwolfData();
        if (koq.b(acquireSwolfData)) {
            LogUtil.h(TAG, "inittrackSwolfList trackSwolfList empty,return");
            return null;
        }
        double d2 = 0.0d;
        float f = Float.MAX_VALUE;
        float f2 = Float.MIN_VALUE;
        int i = 0;
        for (kol kolVar : acquireSwolfData) {
            if (kolVar != null) {
                arrayList.add(new HwHealthBaseEntry(kolVar.acquireTime(), kolVar.c()));
                if (kolVar.c() > f2) {
                    f2 = kolVar.c();
                }
                if (kolVar.c() < f) {
                    f = kolVar.c();
                }
                d2 += kolVar.c();
                i++;
            }
        }
        if (!hwHealthBaseCombinedChart.containsMarkViewShow()) {
            hwHealthBaseCombinedChart.highlightDefValue(-1, true);
        }
        HwHealthLineDataSet hwHealthLineDataSet2 = new HwHealthLineDataSet(BaseApplication.getContext(), arrayList, this.mContext.getResources().getString(R$string.IDS_hwh_motiontrack_swim_SWOLF), this.mContext.getResources().getString(R$string.IDS_hwh_motiontrack_swim_SWOLF), "", acquireSwolfDataInterval());
        hwHealthLineDataSet2.setColor(Color.argb(255, 69, 154, 255));
        hwHealthLineDataSet2.b(Color.argb(128, 69, 154, 255), Color.argb(0, 69, 154, 255), true);
        if (mode != Mode.MODE_NONE) {
            LogUtil.a(TAG, "addTrackSwolfDataLayer customAxisByDataBoard mode=", mode);
            hwHealthLineDataSet = hwHealthLineDataSet2;
            ntl.d(new d(hwHealthBaseCombinedChart, hwHealthLineDataSet2, mode, f2, i > 0 ? (float) (d2 / i) : 0.0f, f, bVar.e, false, false));
        } else {
            hwHealthLineDataSet = hwHealthLineDataSet2;
        }
        if (bVar.e) {
            hwHealthLineDataSet.b(Color.argb(216, 69, 154, 255), Color.argb(38, 69, 154, 255), false);
        }
        handleSwolfDataSet(hwHealthBaseCombinedChart, bVar, hwHealthLineDataSet);
        return hwHealthLineDataSet;
    }

    private void handleSwolfDataSet(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar, HwHealthLineDataSet hwHealthLineDataSet) {
        setThemeAndOrientation(hwHealthBaseCombinedChart, bVar, hwHealthLineDataSet);
        new nqs().initStyle(hwHealthBaseCombinedChart, hwHealthLineDataSet);
        addDataSet(hwHealthBaseCombinedChart, hwHealthLineDataSet);
    }

    public HwHealthLineDataSet addTrackPullFreqDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        return addTrackPullFreqDataLayer(hwHealthBaseCombinedChart, this.mDefaultShowMode);
    }

    public HwHealthLineDataSet addTrackPullFreqDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar) {
        if (hwHealthBaseCombinedChart == null || bVar == null || this.mContext == null) {
            LogUtil.h(TAG, "chart or showMode ,mContext is null");
            return null;
        }
        return addTrackPullFreqDataLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar);
    }

    private HwHealthLineDataSet addTrackPullFreqDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, Mode mode, b bVar) {
        LogUtil.a(TAG, "addTrackPullFreqDataLayer mode:", mode);
        ArrayList<HwHealthBaseEntry> arrayList = new ArrayList<>(16);
        List<kog> acquirePullFreqData = acquirePullFreqData();
        if (koq.b(acquirePullFreqData)) {
            LogUtil.h(TAG, "trackPullFreqDataList empty,return");
            return null;
        }
        float f = Float.MIN_VALUE;
        double d2 = 0.0d;
        float f2 = Float.MAX_VALUE;
        int i = 0;
        for (kog kogVar : acquirePullFreqData) {
            if (kogVar != null) {
                arrayList.add(new HwHealthBaseEntry(kogVar.acquireTime(), kogVar.d()));
                if (kogVar.d() > f) {
                    f = kogVar.d();
                }
                if (kogVar.d() < f2) {
                    f2 = kogVar.d();
                }
                d2 += kogVar.d();
                i++;
            }
        }
        if (!hwHealthBaseCombinedChart.containsMarkViewShow()) {
            hwHealthBaseCombinedChart.highlightDefValue(-1, true);
        }
        HwHealthLineDataSet pullFreqDataSet = getPullFreqDataSet(arrayList);
        if (mode != Mode.MODE_NONE) {
            LogUtil.a(TAG, "addTrackPullFreqDataLayer customAxisByDataBoard mode=", mode);
            ntl.d(new d(hwHealthBaseCombinedChart, pullFreqDataSet, mode, f, i > 0 ? (float) (d2 / i) : 0.0f, f2, bVar.e, false, false));
        }
        if (bVar.e) {
            pullFreqDataSet.b(Color.argb(216, 255, 176, 0), Color.argb(38, 255, 176, 0), false);
        }
        handlePullFreqDataSet(hwHealthBaseCombinedChart, bVar, pullFreqDataSet);
        return pullFreqDataSet;
    }

    private void handlePullFreqDataSet(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar, HwHealthLineDataSet hwHealthLineDataSet) {
        setThemeAndOrientation(hwHealthBaseCombinedChart, bVar, hwHealthLineDataSet);
        new nqp().initStyle(hwHealthBaseCombinedChart, hwHealthLineDataSet);
        addDataSet(hwHealthBaseCombinedChart, hwHealthLineDataSet);
    }

    private HwHealthLineDataSet getPullFreqDataSet(ArrayList<HwHealthBaseEntry> arrayList) {
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(BaseApplication.getContext(), arrayList, this.mContext.getResources().getString(R$string.IDS_hwh_motiontrack_pull_frequence), this.mContext.getResources().getString(R$string.IDS_hwh_motiontrack_pull_frequence), this.mContext.getResources().getQuantityString(R.plurals._2130903224_res_0x7f0300b8, 10), acquirePullFreqDataInterval());
        hwHealthLineDataSet.setColor(Color.argb(255, 255, 176, 0));
        hwHealthLineDataSet.b(Color.argb(217, 255, 176, 0), Color.argb(38, 255, 176, 0), true);
        return hwHealthLineDataSet;
    }

    public HwHealthLineDataSet addTrackRealTimePaceDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        return addTrackRealTimePaceDataLayer(hwHealthBaseCombinedChart, this.mDefaultShowMode);
    }

    public HwHealthLineDataSet addTrackRealTimePaceDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar) {
        if (hwHealthBaseCombinedChart == null || bVar == null || this.mContext == null) {
            LogUtil.h(TAG, "chart or showMode ,mContext is null");
            return null;
        }
        return addTrackRealTimePaceDataLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar);
    }

    private HwHealthLineDataSet addTrackRealTimePaceDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, Mode mode, b bVar) {
        int i;
        LogUtil.a(TAG, "addTrackRealTimePaceDataLayer mode:", mode);
        ArrayList<HwHealthBaseEntry> arrayList = new ArrayList<>(16);
        List<koh> acquireTrackRealTimePaceData = acquireTrackRealTimePaceData();
        if (koq.b(acquireTrackRealTimePaceData)) {
            LogUtil.h(TAG, "addTrackRealTimePaceDataLayer trackSwolfList empty,return");
            return null;
        }
        float f = Float.MIN_VALUE;
        double d2 = 0.0d;
        float f2 = Float.MAX_VALUE;
        int i2 = 0;
        for (koh kohVar : acquireTrackRealTimePaceData) {
            if (kohVar != null) {
                arrayList.add(new HwHealthBaseEntry(kohVar.acquireTime(), kohVar.a()));
                if (kohVar.a() > f) {
                    f = kohVar.a();
                }
                if (kohVar.a() < f2) {
                    f2 = kohVar.a();
                }
                d2 += kohVar.a();
                i2++;
            }
        }
        if (!hwHealthBaseCombinedChart.containsMarkViewShow()) {
            hwHealthBaseCombinedChart.highlightDefValue(-1, true);
        }
        HwHealthLineDataSet handleRealTimePaceDataSet = handleRealTimePaceDataSet(arrayList);
        handleRealTimePaceDataSet.setColor(Color.argb(255, 0, a.A, 38));
        handleRealTimePaceDataSet.b(Color.argb(128, 0, a.A, 38), Color.argb(0, 0, a.A, 38), true);
        if (mode != Mode.MODE_NONE) {
            LogUtil.a(TAG, "addTrackRealTimePaceDataLayer customAxisByDataBoard mode=", mode);
            i = 128;
            ntl.d(new d(hwHealthBaseCombinedChart, handleRealTimePaceDataSet, mode, f, i2 > 0 ? (float) (d2 / i2) : 0.0f, f2, bVar.e, false, true));
        } else {
            i = 128;
        }
        if (bVar.e) {
            handleRealTimePaceDataSet.b(Color.argb(i, 0, a.A, 38), Color.argb(0, 0, a.A, 38), false);
        }
        setThemeAndOrientation(hwHealthBaseCombinedChart, bVar, handleRealTimePaceDataSet);
        ntl.e(handleRealTimePaceDataSet);
        new nqo().initStyle(hwHealthBaseCombinedChart, handleRealTimePaceDataSet);
        addDataSet(hwHealthBaseCombinedChart, handleRealTimePaceDataSet);
        return handleRealTimePaceDataSet;
    }

    private HwHealthLineDataSet handleRealTimePaceDataSet(ArrayList<HwHealthBaseEntry> arrayList) {
        Resources resources = this.mContext.getResources();
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(BaseApplication.getContext(), arrayList, this.mContext.getResources().getString(R$string.IDS_motiontrack_show_map_sport_peisu_1), this.mContext.getResources().getString(R$string.IDS_motiontrack_show_map_sport_peisu_1), resources != null ? ntl.cNf_(resources, this.mSportType, "", this.mContext) : "");
        if (acquireRealTimePaceDataSumTime() > 0.0f) {
            LogUtil.c(TAG, "acquireRealTimePaceDataSumTime:", LogAnonymous.d((long) acquireRealTimePaceDataSumTime()));
            hwHealthLineDataSet.setXMaxForcedValue(acquireRealTimePaceDataSumTime());
        }
        return hwHealthLineDataSet;
    }

    public HwHealthLineDataSet addRunningPostureDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, int i) {
        b bVar = new b();
        if (i != 8) {
            bVar.a(true);
        }
        return addRunningPostureDataLayer(hwHealthBaseCombinedChart, bVar, i);
    }

    public HwHealthLineDataSet addRunningPostureDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, int i, float f) {
        b bVar = new b();
        if (i != 8) {
            bVar.a(true);
        }
        if (hwHealthBaseCombinedChart == null || this.mContext == null) {
            LogUtil.h(TAG, "chart or showMode ,mContext is null");
            return null;
        }
        return addRunningPostureDataLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar, i, f);
    }

    public HwHealthLineDataSet addRunningPostureDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar, int i, float f) {
        if (hwHealthBaseCombinedChart == null || this.mContext == null) {
            LogUtil.h(TAG, "chart or showMode ,mContext is null");
            return null;
        }
        return addRunningPostureDataLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar, i, f);
    }

    public HwHealthLineDataSet addRunningPostureDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar, int i) {
        if (hwHealthBaseCombinedChart == null || bVar == null || this.mContext == null) {
            LogUtil.h(TAG, "chart or showMode ,mContext is null");
            return null;
        }
        return addRunningPostureDataLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar, i, Float.MAX_VALUE);
    }

    private HwHealthLineDataSet addRunningPostureDataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, Mode mode, b bVar, int i, float f) {
        HwHealthBaseCombinedChart hwHealthBaseCombinedChart2;
        b bVar2 = bVar;
        LogUtil.a(TAG, "addRunningPostureDataLayer mode:", mode);
        ArrayList<HwHealthBaseEntry> arrayList = new ArrayList<>(16);
        List<ffs> acquireRunningPostureData = acquireRunningPostureData();
        if (koq.b(acquireRunningPostureData)) {
            LogUtil.h(TAG, "addRunningPostureDataLayer runningPostureList empty,return");
            return null;
        }
        if (!hwHealthBaseCombinedChart.containsMarkViewShow()) {
            hwHealthBaseCombinedChart.highlightDefValue(-1, true);
        }
        int i2 = 0;
        float f2 = Float.MIN_VALUE;
        double d2 = 0.0d;
        float f3 = Float.MAX_VALUE;
        for (ffs ffsVar : acquireRunningPostureData) {
            if (ffsVar != null) {
                float c2 = ntl.c(ffsVar, i);
                if (Math.abs(c2 + 10000.0f) >= 1.0E-7d) {
                    arrayList.add(new HwHealthBaseEntry(ffsVar.g(), c2));
                    if (c2 > f2) {
                        f2 = c2;
                    }
                    if (c2 < f3) {
                        f3 = c2;
                    }
                    d2 += c2;
                    i2++;
                }
            }
            bVar2 = bVar;
        }
        b bVar3 = bVar2;
        HwHealthLineDataSet handleRunningPostureDataSet = handleRunningPostureDataSet(bVar3, i, arrayList);
        if (f3 >= f) {
            f3 = f - 1.0f;
        }
        if (mode != Mode.MODE_NONE) {
            if (i == 19) {
                ntl.c(new d(hwHealthBaseCombinedChart, handleRunningPostureDataSet, mode, f2, i2 > 0 ? (float) (d2 / i2) : 0.0f, f3, bVar3.e, false, false));
            } else {
                ntl.d(new d(hwHealthBaseCombinedChart, handleRunningPostureDataSet, mode, f2, i2 > 0 ? (float) (d2 / i2) : 0.0f, f3, bVar3.e, false, false));
            }
        }
        if (bVar3.d || Math.abs(Float.MAX_VALUE - f) <= 1.0E-6d) {
            hwHealthBaseCombinedChart2 = hwHealthBaseCombinedChart;
        } else {
            hwHealthBaseCombinedChart2 = hwHealthBaseCombinedChart;
            hwHealthBaseCombinedChart2.enableManualFloatLine(getAvgLine(f, i), this.mCurveColor);
            hwHealthBaseCombinedChart2.enableManualFloatLineText(ntn.b(i));
            hwHealthBaseCombinedChart2.d(new HwHealthBaseCombinedChart.DataParser() { // from class: com.huawei.ui.commonui.view.trackview.TrackLineChartHolder.4
                @Override // com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.DataParser
                public boolean isSupportOverlaying() {
                    return false;
                }

                @Override // com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.DataParser
                public boolean isSupportSampling() {
                    return false;
                }

                @Override // com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.DataParser
                public void onOverlaying(List<HwHealthBaseEntry> list, HwHealthLineDataSet hwHealthLineDataSet) {
                }

                @Override // com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.DataParser
                public int onSampling(List<HwHealthBaseEntry> list, int i3, HwHealthLineDataSet hwHealthLineDataSet) {
                    return 0;
                }
            });
        }
        setThemeAndOrientation(hwHealthBaseCombinedChart2, bVar3, handleRunningPostureDataSet);
        ntn.b(handleRunningPostureDataSet);
        new nqq().initStyle(hwHealthBaseCombinedChart2, handleRunningPostureDataSet);
        if (bVar3.f8982a) {
            addDotGraphDataSet(hwHealthBaseCombinedChart2, handleRunningPostureDataSet);
        } else {
            addDataSet(hwHealthBaseCombinedChart2, handleRunningPostureDataSet);
        }
        return handleRunningPostureDataSet;
    }

    private float getAvgLine(float f, int i) {
        return (i == 18 && UnitUtil.h()) ? (float) UnitUtil.e(f, 0) : f;
    }

    private HwHealthLineDataSet handleRunningPostureDataSet(b bVar, int i, ArrayList<HwHealthBaseEntry> arrayList) {
        HwHealthLineDataSet a2;
        int i2;
        int i3;
        HwHealthLineDataSet c2;
        HwHealthLineDataSet hwHealthLineDataSet;
        int i4;
        int i5;
        int i6;
        int i7 = 0;
        if (i == 7) {
            a2 = ntn.b(arrayList, this.mContext, bVar);
            i2 = a.z;
            i3 = 148;
        } else if (i == 8) {
            a2 = new HwHealthLineDataSet(BaseApplication.getContext(), arrayList, this.mContext.getResources().getString(R$string.IDS_running_posture_ground_impact_acceleration), this.mContext.getResources().getString(R$string.IDS_running_posture_ground_impact_acceleration), this.mContext.getResources().getString(R$string.IDS_gravity_unit), acquireRunningPostureDataInterval());
            i2 = 124;
            i3 = 255;
        } else {
            if (i == 16) {
                c2 = ntn.d(arrayList, this.mContext, bVar);
            } else if (i == 19) {
                c2 = ntn.e(arrayList, this.mContext, bVar);
                c2.a(1);
            } else if (i == 20) {
                c2 = ntn.j(arrayList, this.mContext, bVar);
                c2.a(1);
            } else if (i == 18) {
                c2 = ntn.i(arrayList, this.mContext, bVar);
                c2.a(1);
            } else if (i == 21) {
                c2 = ntn.c(arrayList, this.mContext, bVar);
                c2.a(1);
            } else {
                a2 = ntn.a(arrayList, this.mContext, bVar);
                a2.a(1);
                i7 = 150;
                i2 = a.A;
                i3 = 115;
            }
            hwHealthLineDataSet = c2;
            i4 = 219;
            i5 = 177;
            i6 = 69;
            ntl.c(acquireRunningPostureDataSumTime(), TAG, hwHealthLineDataSet, 255, i6, i5, i4, bVar, 0.5f, 0.15f);
            return hwHealthLineDataSet;
        }
        hwHealthLineDataSet = a2;
        i5 = i2;
        i4 = i3;
        i6 = i7;
        ntl.c(acquireRunningPostureDataSumTime(), TAG, hwHealthLineDataSet, 255, i6, i5, i4, bVar, 0.5f, 0.15f);
        return hwHealthLineDataSet;
    }

    private void setThemeAndOrientation(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
        if (!bVar.c) {
            hwHealthBaseCombinedChart.setGridColor(Color.argb(51, 255, 255, 255), Color.argb(51, 255, 255, 255));
            hwHealthBaseCombinedChart.setLabelColor(Color.rgb(255, 255, 255));
        }
        ntl.a(bVar, hwHealthBaseCombinedChart, hwHealthBaseBarLineDataSet, 2.0f, 1.0f, 9.0f);
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        public final boolean f8984a;
        public final boolean b;
        private final HwHealthBaseCombinedChart c;
        private final boolean d;
        private final float e;
        private final float f;
        private final Mode g;
        private final HwHealthBaseBarLineDataSet i;
        private final float j;

        public d(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, Mode mode, float f, float f2, float f3, boolean z, boolean z2, boolean z3) {
            this.c = hwHealthBaseCombinedChart;
            this.i = hwHealthBaseBarLineDataSet;
            this.g = mode;
            this.f = f;
            this.e = f2;
            this.j = f3;
            this.d = z;
            this.f8984a = z2;
            this.b = z3;
        }

        public HwHealthBaseCombinedChart b() {
            return this.c;
        }

        public HwHealthBaseBarLineDataSet j() {
            return this.i;
        }

        public Mode a() {
            return this.g;
        }

        public float c() {
            return this.f;
        }

        public float e() {
            return this.j;
        }

        public float d() {
            return this.e;
        }

        public boolean g() {
            return this.d;
        }
    }

    public HwHealthBarDataSet addSpo2DataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        return addSpo2DataLayer(hwHealthBaseCombinedChart, this.mDefaultShowMode);
    }

    public HwHealthBarDataSet addSpo2DataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, b bVar) {
        if (hwHealthBaseCombinedChart == null || bVar == null) {
            return null;
        }
        return addSpo2DataLayer(hwHealthBaseCombinedChart, ntl.b(hwHealthBaseCombinedChart), bVar);
    }

    private HwHealthBarDataSet addSpo2DataLayer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, Mode mode, b bVar) {
        LogUtil.a(TAG, "addSpo2DataLayer mode:", mode);
        List<HwHealthBarEntry> buildSpo2ChartValues = buildSpo2ChartValues(bVar.d);
        if (koq.b(buildSpo2ChartValues)) {
            LogUtil.h(TAG, "initStepRate stepRateList empty,return");
            return null;
        }
        if (!hwHealthBaseCombinedChart.containsMarkViewShow()) {
            hwHealthBaseCombinedChart.highlightDefValue(-1, true);
        }
        HwHealthBarDataSet hwHealthBarDataSet = new HwHealthBarDataSet(buildSpo2ChartValues, this.mContext.getResources().getString(R$string.IDS_hw_health_blood_oxygen), this.mContext.getResources().getString(R$string.IDS_hw_health_blood_oxygen), "");
        hwHealthBarDataSet.setColors(nrm.d(this.mContext, 100, bVar.d));
        hwHealthBarDataSet.b(HwHealthBarDataSet.DrawColorMode.DATA_COLOR);
        hwHealthBarDataSet.e(Color.argb(255, 141, a.M, 141));
        float acquireSpo2SumTime = acquireSpo2SumTime();
        if (acquireSpo2SumTime > 0.0f) {
            LogUtil.c(TAG, "addSpo2DataLayer acquireSpo2SumTime:", Float.valueOf(acquireSpo2SumTime));
            hwHealthBarDataSet.setXMaxForcedValue(acquireSpo2SumTime);
        }
        hwHealthBarDataSet.setBarDrawWidthDp(1.5f);
        hwHealthBarDataSet.setSearchByBarWidth(true);
        if (mode != Mode.MODE_NONE) {
            int i = 70;
            LogUtil.a(TAG, "addSpo2DataLayer customAxisByDataBoard mode=", mode, " max=", 100, " min=", 70);
            if (bVar.e) {
                hwHealthBarDataSet.setLabelCount(ntl.b(100, 70, bVar.e).e, true);
            } else {
                hwHealthBarDataSet.setForcedLabels(nrm.e(bVar.d));
                i = 68;
            }
            ntl.b(hwHealthBaseCombinedChart, hwHealthBarDataSet, mode, 100, i);
        }
        setThemeAndOrientation(hwHealthBaseCombinedChart, bVar, hwHealthBarDataSet);
        new nqr().initStyle(hwHealthBaseCombinedChart, hwHealthBarDataSet);
        ntl.d(hwHealthBaseCombinedChart, hwHealthBarDataSet, this.mModes);
        return hwHealthBarDataSet;
    }

    private List<HwHealthBarEntry> buildSpo2ChartValues(boolean z) {
        List<kof> acquireTrackSpo2Data = acquireTrackSpo2Data();
        if (koq.b(acquireTrackSpo2Data)) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(acquireTrackSpo2Data.size());
        ntl.e(z, acquireTrackSpo2Data, this.mContext, arrayList, TAG);
        return arrayList;
    }

    private void addHeartRateDataSet(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, HwHealthLineDataSet hwHealthLineDataSet) {
        if (hwHealthBaseCombinedChart == null) {
            return;
        }
        ntl.d(hwHealthLineDataSet, acquireInvalidHeartRateData());
        ntl.a(hwHealthBaseCombinedChart, hwHealthLineDataSet, this.mModes);
    }

    private void addDotGraphDataSet(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, HwHealthLineDataSet hwHealthLineDataSet) {
        if (hwHealthBaseCombinedChart == null) {
            return;
        }
        ntl.c(hwHealthBaseCombinedChart, hwHealthLineDataSet);
        ntl.c(hwHealthBaseCombinedChart, this.mModes);
    }

    private void addDataSet(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, HwHealthLineDataSet hwHealthLineDataSet) {
        if (hwHealthBaseCombinedChart == null) {
            return;
        }
        ntl.b(hwHealthLineDataSet);
        ntl.a(hwHealthBaseCombinedChart, hwHealthLineDataSet, this.mModes);
    }
}
