package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import defpackage.fgm;
import defpackage.kom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@SportComponentType(classify = 2, name = ComponentName.WEIGHT_PRODUCER)
/* loaded from: classes8.dex */
public class WeightProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_WeightProducer";
    private int mPeakWeight;
    private int mWeight;
    private CopyOnWriteArrayList<kom> mWeightList = new CopyOnWriteArrayList<>();
    private fgm mSportCallbackOption = new fgm();

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        int intValue = ((Integer) obj).intValue();
        this.mWeight = intValue;
        if (intValue > this.mPeakWeight) {
            this.mPeakWeight = intValue;
        }
        onStagingAndNotification();
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        BaseSportManager.getInstance().stagingAndNotification("WEIGHT_DATA", Integer.valueOf(this.mWeight));
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        String sportMode = BaseSportManager.getInstance().getSportMode();
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data;
            LogUtil.a(TAG, "onSaveData() isRowerStrength: ", sportMode);
            motionPathSimplify.addExtendDataMap("sportMode", sportMode);
            if (sportMode.equals("291")) {
                motionPathSimplify.addExtendDataMap("peak_weight", String.valueOf(this.mPeakWeight));
                motionPathSimplify.saveChiefSportDataType(10);
                motionPathSimplify.addExtendDataMap("rowMachinePowerModeTime", String.valueOf(((((Long) BaseSportManager.getInstance().getData("TIME_ONE_SECOND_DURATION")).longValue() * 1.0d) / 1000.0d) / 60.0d));
            }
            BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
        }
        if (BaseSportManager.getInstance().getSportMode().equals("291")) {
            Object data2 = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
            if (data2 instanceof MotionPath) {
                MotionPath motionPath = (MotionPath) data2;
                motionPath.saveWeightList(this.mWeightList);
                BaseSportManager.getInstance().stagingData(JsUtil.DataFunc.MOTION_PATH_DATA, motionPath);
            }
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().subscribeToSource("WEIGHT_DATA", this);
        ArrayList arrayList = new ArrayList();
        arrayList.add("TIME_FIVE_SECOND_TIMESTAMP");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.dataproducer.WeightProducer$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                WeightProducer.this.m453x8d8a7dd1(list, map);
            }
        });
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-dataproducer-WeightProducer, reason: not valid java name */
    /* synthetic */ void m453x8d8a7dd1(List list, Map map) {
        Object data = BaseSportManager.getInstance().getData("TIME_FIVE_SECOND_TIMESTAMP");
        if (data instanceof Long) {
            this.mWeightList.add(new kom(((Long) data).longValue(), this.mWeight));
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        onSaveData();
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }
}
