package com.huawei.health.sportservice.dataproducer;

import android.os.Bundle;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import defpackage.ffs;
import defpackage.fft;
import defpackage.fgm;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@SportComponentType(classify = 2, name = ComponentName.RUNNING_POSTURE_PRODUCER)
/* loaded from: classes8.dex */
public class RunningPostureProducer extends BaseProducer implements SportLifecycle {
    public static final int FIVE_SECOND = 5000;
    private static final String TAG = "SportService_RunningPostureProducer";
    private long mLastUpdateTime;
    private ffs mRunningPosture;
    private CopyOnWriteArrayList<ffs> mRunningPostureList = new CopyOnWriteArrayList<>();
    private fgm mSportCallbackOption = new fgm();

    private int getValue(int i) {
        if (i == -1.0f) {
            return 0;
        }
        return i;
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        this.mLastUpdateTime = ((Long) BaseSportManager.getInstance().getData("TIME_ONE_SECOND_DURATION")).longValue();
        ffs ffsVar = (ffs) obj;
        this.mRunningPosture = ffsVar;
        LogUtil.a(TAG, "onSourceDataChanged mRunningPosture", ffsVar.toString());
        onStagingAndNotification();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        BaseSportManager.getInstance().stagingAndNotification("RUNNING_POSTURE_DATA", -1);
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        if (BaseSportManager.getInstance().getStatus() == 1) {
            ffs ffsVar = new ffs(((int) ((Long) BaseSportManager.getInstance().getData("TIME_ONE_SECOND_DURATION")).longValue()) / 1000, getValue(this.mRunningPosture.b()), getValue(this.mRunningPosture.e()), getValue(this.mRunningPosture.i()), this.mRunningPosture.a() == -101 ? 0 : this.mRunningPosture.a(), getValue(this.mRunningPosture.d()), getValue(this.mRunningPosture.h()), getValue(this.mRunningPosture.c()));
            ffsVar.g(getValue(this.mRunningPosture.l()));
            ffsVar.i(getValue(this.mRunningPosture.o()));
            ffsVar.d(getValue(Float.valueOf(this.mRunningPosture.f())));
            ffsVar.a(getValue(Float.valueOf(this.mRunningPosture.m())));
            ffsVar.j(getValue(this.mRunningPosture.k()));
            ffsVar.e(getValue(Float.valueOf(this.mRunningPosture.t())));
            ffsVar.c(getValue(Float.valueOf(this.mRunningPosture.r())));
            ffsVar.b(getValue(Float.valueOf(this.mRunningPosture.n())));
            LogUtil.a(TAG, "onStagingAndNotification mRunningPosture", ffsVar);
            BaseSportManager.getInstance().stagingAndNotification("RUNNING_POSTURE_DATA", ffsVar);
        }
    }

    private float getValue(Float f) {
        if (f.floatValue() == -1.0f) {
            return 0.0f;
        }
        return f.floatValue();
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        Object data2 = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if ((data instanceof MotionPath) && (data2 instanceof MotionPathSimplify)) {
            MotionPath motionPath = (MotionPath) data;
            ArrayList<ffs> arrayList = new ArrayList<>(this.mRunningPostureList);
            LogUtil.a(TAG, "list.size", Integer.valueOf(arrayList.size()));
            motionPath.saveRunningPostureList(arrayList);
            BaseSportManager.getInstance().stagingData(JsUtil.DataFunc.MOTION_PATH_DATA, motionPath);
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data2;
            if (arrayList.size() > 0) {
                getSimplifyDataLowerHalf(motionPathSimplify, arrayList);
            }
            BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void recoveryData() {
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if (data instanceof MotionPath) {
            MotionPath motionPath = (MotionPath) data;
            if (koq.c(motionPath.requestRunningPostureList())) {
                this.mRunningPostureList = new CopyOnWriteArrayList<>(motionPath.requestRunningPostureList());
            }
            LogUtil.a(TAG, "recovery mRunningPostureList.size() ", Integer.valueOf(this.mRunningPostureList.size()));
        }
    }

    private void getSimplifyDataLowerHalf(MotionPathSimplify motionPathSimplify, List<ffs> list) {
        Bundle awQ_ = fft.awQ_(list);
        motionPathSimplify.saveAverageHangTime(awQ_.getInt("averageHangTime", 0));
        motionPathSimplify.saveGroundHangTimeRate(awQ_.getFloat("groundHangTimeRate", 0.0f));
        motionPathSimplify.saveAvgGroundContactTime(awQ_.getInt("avgGroundContactTime", 0));
        motionPathSimplify.saveAvgGroundImpactAcceleration(awQ_.getInt("avgGroundImpactAcceleration", 0));
        motionPathSimplify.saveAvgSwingAngle(awQ_.getInt("avgSwingAngle", 0));
        motionPathSimplify.saveAvgEversionExcursion(awQ_.getInt("avgEversionExcursion", 0));
        motionPathSimplify.saveAvgForeFootStrikePattern(awQ_.getInt("foreFootStrikePatternPercentage", 0));
        motionPathSimplify.saveAvgWholeFootStrikePattern(awQ_.getInt("wholeFootStrikePatternPercentage", 0));
        motionPathSimplify.saveAvgHindFootStrikePattern(awQ_.getInt("hindFootStrikePatternPercentage", 0));
        float f = awQ_.getFloat("averageverticalimpactrate", -1.0f);
        if (f != -1.0f) {
            motionPathSimplify.addExtendDataMap("avg_v_i_r", String.valueOf(f));
        }
        float f2 = awQ_.getFloat("averageimpactpeak", -1.0f);
        if (f2 > 0.0f) {
            motionPathSimplify.addExtendDataMap("avg_i_p", String.valueOf(f2));
        }
        float f3 = awQ_.getFloat("averagegctimebalance", -1.0f);
        if (f3 != -1.0f) {
            motionPathSimplify.addExtendDataMap("avg_gc_tb", String.valueOf(f3));
        }
        float f4 = awQ_.getFloat("averageverticaloscillation", -1.0f);
        if (f4 != -1.0f) {
            motionPathSimplify.addExtendDataMap("avg_v_osc", String.valueOf(f4));
        }
        float f5 = awQ_.getFloat("averageverticalration", -1.0f);
        if (f5 != -1.0f) {
            motionPathSimplify.addExtendDataMap("avg_v_s_r", String.valueOf(f5));
        }
        if (f != -1.0f || f3 != -1.0f || f4 != -1.0f) {
            motionPathSimplify.addExtendDataMap("bolt_connected_flag", "2");
        }
        LogUtil.a(TAG, "saveRunningPostureData verticalImpactRate = ", Float.valueOf(f), " avgImpactPeak = ", Float.valueOf(f2), " gcTimeBalance = ", Float.valueOf(f3), " verticalOscillation = ", Float.valueOf(f4), "avgVerStrikeRatio = ", Float.valueOf(f5), " motionPathSimplify.getExtendDataFloat = ", Float.valueOf(motionPathSimplify.getExtendDataFloat("bolt_connected_flag")));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().subscribeToSource("RUNNING_POSTURE_DATA", this);
        ArrayList arrayList = new ArrayList();
        arrayList.add("TIME_FIVE_SECOND_DURATION");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.dataproducer.RunningPostureProducer$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                RunningPostureProducer.this.m444x7045c962(list, map);
            }
        });
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-dataproducer-RunningPostureProducer, reason: not valid java name */
    /* synthetic */ void m444x7045c962(List list, Map map) {
        if (this.mRunningPosture != null) {
            if (((Long) map.get("TIME_FIVE_SECOND_DURATION")).longValue() > this.mLastUpdateTime + 5000) {
                return;
            }
            ffs ffsVar = new ffs(((int) r11) / 1000, this.mRunningPosture.b(), this.mRunningPosture.e(), this.mRunningPosture.i(), this.mRunningPosture.a(), this.mRunningPosture.d(), this.mRunningPosture.h(), this.mRunningPosture.c());
            ffsVar.g(this.mRunningPosture.l());
            ffsVar.i(this.mRunningPosture.o());
            ffsVar.d(this.mRunningPosture.f());
            ffsVar.a(this.mRunningPosture.m());
            ffsVar.j(this.mRunningPosture.k());
            ffsVar.e(this.mRunningPosture.t());
            ffsVar.c(this.mRunningPosture.r());
            ffsVar.b(this.mRunningPosture.n());
            LogUtil.a(TAG, "onPreSport mRunningPosture", ffsVar);
            this.mRunningPostureList.add(ffsVar);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        onSaveData();
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }
}
