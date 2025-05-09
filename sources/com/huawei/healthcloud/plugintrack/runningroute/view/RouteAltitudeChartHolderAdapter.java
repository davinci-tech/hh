package com.huawei.healthcloud.plugintrack.runningroute.view;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class RouteAltitudeChartHolderAdapter extends TrackLineChartHolder {
    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireCadenceInterval() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public float acquireCadenceSumTime() {
        return 0.0f;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<HeartRateData> acquireHeartRateData() {
        return null;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireHeartRateDataInterval() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public float acquireHeartRateDataSumTime() {
        return 0.0f;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<HeartRateData> acquireInvalidHeartRateData() {
        return null;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<HeartRateData> acquireInvalidUnixHeartRateData() {
        return null;
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
    public List<knw> acquirePaddleFrequencyData() {
        return null;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<koc> acquirePoweData() {
        return null;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquirePowerDataInterval() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<kog> acquirePullFreqData() {
        return null;
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
    public float acquireRecoverHeartRateDataSumTime() {
        return 0.0f;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<ffn> acquireRidePostureData() {
        return null;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<ffs> acquireRunningPostureData() {
        return null;
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
    public List<kob> acquireSkippingSpeedData() {
        return null;
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
    public ArrayList<StepRateData> acquireStepRateData() {
        return null;
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
    public List<kol> acquireSwolfData() {
        return null;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireSwolfDataInterval() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<knz> acquireTrackAltitudeData() {
        return null;
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
    public List<HeartRateData> acquireTrackHeartRateData() {
        return null;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<ixt> acquireTrackJumpData() {
        return null;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<koh> acquireTrackRealTimePaceData() {
        return null;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public ArrayList<koi> acquireTrackRealTimeSpeedData() {
        return null;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public int acquireTrackRealTimeSpeedDataInterval() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<kof> acquireTrackSpo2Data() {
        return null;
    }

    public RouteAltitudeChartHolderAdapter(Context context) {
        super(context);
    }

    @Override // com.huawei.ui.commonui.view.trackview.TrackLineChartHolder
    public List<kny> getHeartRateAreaList() {
        return Collections.emptyList();
    }
}
