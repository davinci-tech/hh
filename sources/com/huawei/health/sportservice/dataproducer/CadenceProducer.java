package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import defpackage.ffn;
import defpackage.fgm;
import defpackage.fhl;
import defpackage.koq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@SportComponentType(classify = 2, name = ComponentName.CADENCE_PRODUCER)
/* loaded from: classes8.dex */
public class CadenceProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_CadenceProducer";
    private int mCadence;
    private final CopyOnWriteArrayList<ffn> mRidePostureList = new CopyOnWriteArrayList<>();
    private final fgm mSportCallbackOption = new fgm();

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        int intValue = ((Integer) obj).intValue();
        if (intValue == 0) {
            this.mCadence = 0;
        } else {
            this.mCadence = (int) fhl.b(intValue, 1);
        }
        onStagingAndNotification();
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        BaseSportManager.getInstance().stagingAndNotification("CADENCE_DATA", Integer.valueOf(this.mCadence));
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if (data instanceof MotionPath) {
            MotionPath motionPath = (MotionPath) data;
            if (koq.c(this.mRidePostureList)) {
                LogUtil.a(TAG, "mRidePostureList", this.mRidePostureList.toString());
                motionPath.saveStepRateList(constructStepRateList(this.mRidePostureList));
            } else {
                LogUtil.a(TAG, "mStepRateList is null");
            }
            motionPath.saveRidePostureDataList(this.mRidePostureList);
            BaseSportManager.getInstance().stagingData(JsUtil.DataFunc.MOTION_PATH_DATA, motionPath);
        }
        if (BaseSportManager.getInstance().getSportType() != 265) {
            return;
        }
        Object data2 = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data2 instanceof MotionPathSimplify) {
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data2;
            Object data3 = BaseSportManager.getInstance().getData("TIME_ONE_SECOND_DURATION");
            if (data3 instanceof Long) {
                motionPathSimplify.saveTotalSteps(getTotalStepsForCadenceList(this.mRidePostureList, (int) (((Long) data3).longValue() / 1000)));
                BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
            }
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void recoveryData() {
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if (!(data instanceof MotionPath)) {
            LogUtil.b(TAG, "recovery failed. MotionPath type is wrong");
            return;
        }
        MotionPath motionPath = (MotionPath) data;
        if (motionPath.requestRidePostureDataList() != null) {
            this.mRidePostureList.addAll(motionPath.requestRidePostureDataList());
        }
        LogUtil.a(TAG, "recovery mRidePostureList size is ", Integer.valueOf(this.mRidePostureList.size()));
    }

    private ArrayList<StepRateData> constructStepRateList(CopyOnWriteArrayList<ffn> copyOnWriteArrayList) {
        ArrayList<StepRateData> arrayList = new ArrayList<>();
        if (koq.b(copyOnWriteArrayList)) {
            LogUtil.h(TAG, "cadenceList is empty");
            return arrayList;
        }
        Iterator<ffn> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            ffn next = it.next();
            arrayList.add(new StepRateData(next.acquireTime(), next.e() * 2));
        }
        return arrayList;
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().subscribeToSource("CADENCE_DATA", this);
        ArrayList arrayList = new ArrayList();
        arrayList.add("TIME_FIVE_SECOND_TIMESTAMP");
        arrayList.add("TIME_FIVE_SECOND_DURATION");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.dataproducer.CadenceProducer$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                CadenceProducer.this.m436x31f8c77c(list, map);
            }
        });
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-dataproducer-CadenceProducer, reason: not valid java name */
    /* synthetic */ void m436x31f8c77c(List list, Map map) {
        if (list.contains("TIME_FIVE_SECOND_DURATION")) {
            fillPoint();
            this.mRidePostureList.add(new ffn(((Long) map.get("TIME_FIVE_SECOND_TIMESTAMP")).longValue(), this.mCadence));
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void fillList(long j, long j2) {
        LogUtil.a(TAG, "fillList,timestamp", Long.valueOf(j2));
        this.mRidePostureList.add(0, new ffn(j2, 0));
    }

    private int getTotalStepsForCadenceList(List<ffn> list, int i) {
        if (koq.c(list)) {
            int i2 = 0;
            int i3 = 0;
            for (ffn ffnVar : list) {
                if (ffnVar != null) {
                    i3 += ffnVar.e();
                    i2++;
                }
            }
            if (i2 != 0) {
                return Math.round(((i3 / i2) * i) / 60.0f);
            }
        }
        LogUtil.a(TAG, "getTotalSteps is 0");
        return 0;
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        onSaveData();
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
        fhl.d(1);
    }
}
