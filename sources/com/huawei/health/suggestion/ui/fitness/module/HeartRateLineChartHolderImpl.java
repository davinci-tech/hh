package com.huawei.health.suggestion.ui.fitness.module;

import android.content.Context;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.ui.commonui.view.trackview.TrackLineChartHolder;
import defpackage.ffn;
import defpackage.ffs;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public class HeartRateLineChartHolderImpl extends TrackLineChartHolder {

    /* renamed from: a, reason: collision with root package name */
    private int f3179a;
    private float b;
    private ArrayList<HeartRateData> c;
    private ArrayList<HeartRateData> d;
    private ArrayList<HeartRateData> e;
    private float j;

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireCadenceInterval() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public float acquireCadenceSumTime() {
        return 0.0f;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireJumpDataInterval() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireJumpTotalTime() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquirePaddleFreqInterval() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquirePowerDataInterval() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquirePullFreqDataInterval() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public float acquireRealTimePaceDataSumTime() {
        return 0.0f;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireRunningPostureDataInterval() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public float acquireRunningPostureDataSumTime() {
        return 0.0f;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireSkippingSpeedInterval() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public float acquireSpo2SumTime() {
        return 0.0f;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireStepRateDataInterval() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public float acquireStepRateSumTime() {
        return 0.0f;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireSwolfDataInterval() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireTrackAltitudeDataInterval() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireTrackAltitudeShowType() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireTrackRealTimeSpeedDataInterval() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireWeightDataInterval() {
        return 0;
    }

    public HeartRateLineChartHolderImpl(Context context) {
        super(context);
        this.b = -1.0f;
        this.j = -1.0f;
    }

    public void b(ArrayList<HeartRateData> arrayList) {
        this.e = arrayList;
    }

    public void e(ArrayList<HeartRateData> arrayList) {
        this.d = arrayList;
    }

    public void a(ArrayList<HeartRateData> arrayList) {
        this.c = arrayList;
    }

    public void a(int i) {
        this.f3179a = i;
    }

    public void c() {
        this.b = -1.0f;
    }

    public void d(float f) {
        this.b = f;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<HeartRateData> acquireHeartRateData() {
        return this.e;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireHeartRateDataInterval() {
        return this.f3179a;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public float acquireHeartRateDataSumTime() {
        return this.b;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public float acquireRecoverHeartRateDataSumTime() {
        return this.j;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<HeartRateData> acquireInvalidHeartRateData() {
        return this.d;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<HeartRateData> acquireInvalidUnixHeartRateData() {
        return this.c;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<StepRateData> acquireStepRateData() {
        return new ArrayList<>(0);
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<knw> acquirePaddleFrequencyData() {
        return Collections.emptyList();
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<ffn> acquireRidePostureData() {
        return Collections.emptyList();
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<koc> acquirePoweData() {
        return Collections.emptyList();
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<kom> acquireWeightData() {
        return new ArrayList();
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<kob> acquireSkippingSpeedData() {
        return Collections.emptyList();
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<knz> acquireTrackAltitudeData() {
        return new ArrayList<>(0);
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<ixt> acquireTrackJumpData() {
        return new ArrayList<>(0);
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<koi> acquireTrackRealTimeSpeedData() {
        return new ArrayList<>(0);
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<HeartRateData> acquireTrackHeartRateData() {
        return Collections.emptyList();
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<kol> acquireSwolfData() {
        return Collections.emptyList();
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<kog> acquirePullFreqData() {
        return Collections.emptyList();
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<koh> acquireTrackRealTimePaceData() {
        return Collections.emptyList();
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<ffs> acquireRunningPostureData() {
        return Collections.emptyList();
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<kof> acquireTrackSpo2Data() {
        return Collections.emptyList();
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<kny> getHeartRateAreaList() {
        return Collections.emptyList();
    }
}
