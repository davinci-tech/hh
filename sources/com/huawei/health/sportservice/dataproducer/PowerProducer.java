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
import defpackage.fhl;
import defpackage.koc;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@SportComponentType(classify = 2, name = ComponentName.POWER_PRODUCER)
/* loaded from: classes8.dex */
public class PowerProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_PowerProducer";
    private int mPeakPower;
    private int mPower;
    private final CopyOnWriteArrayList<koc> mPowerList = new CopyOnWriteArrayList<>();
    private fgm mSportCallbackOption = new fgm();

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            if (intValue == 0) {
                this.mPower = 0;
            } else {
                this.mPower = (int) fhl.b(intValue, 0);
            }
            int i = this.mPower;
            if (i > this.mPeakPower) {
                this.mPeakPower = i;
            }
            onStagingAndNotification();
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        BaseSportManager.getInstance().stagingAndNotification("POWER_DATA", Integer.valueOf(this.mPower));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        LogUtil.a(TAG, "onPauseSport staging and notify a invalid power data");
        BaseSportManager.getInstance().stagingAndNotification("POWER_DATA", -1);
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    public void recoveryData() {
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if (!(data instanceof MotionPath)) {
            LogUtil.b(TAG, "recovery failed. MotionPath type is wrong");
            return;
        }
        MotionPath motionPath = (MotionPath) data;
        if (motionPath.requestPowerList() != null) {
            this.mPowerList.addAll(motionPath.requestPowerList());
        }
        LogUtil.a(TAG, "recovery mPowerList size is ", Integer.valueOf(this.mPowerList.size()), "peakPower is ", Integer.valueOf(this.mPeakPower));
        if (BaseSportManager.getInstance().getSportMode().equals("291")) {
            Object data2 = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
            if (data2 instanceof MotionPathSimplify) {
                int extendDataInt = ((MotionPathSimplify) data2).getExtendDataInt("peak_power");
                this.mPeakPower = extendDataInt;
                LogUtil.a(TAG, "onRecover mPeakPower: ", Integer.valueOf(extendDataInt));
            }
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if (data instanceof MotionPath) {
            MotionPath motionPath = (MotionPath) data;
            motionPath.savePowerList(this.mPowerList);
            LogUtil.a(TAG, "motionPath.savePowerList", Integer.valueOf(this.mPowerList.size()));
            BaseSportManager.getInstance().stagingData(JsUtil.DataFunc.MOTION_PATH_DATA, motionPath);
        }
        if (BaseSportManager.getInstance().getSportMode().equals("291")) {
            Object data2 = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
            if (data2 instanceof MotionPathSimplify) {
                MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data2;
                LogUtil.a(TAG, "onSaveData() mPeakPower: ", Integer.valueOf(this.mPeakPower));
                motionPathSimplify.addExtendDataMap("peak_power", String.valueOf(this.mPeakPower));
                BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
            }
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().subscribeToSource("POWER_DATA", this);
        ArrayList arrayList = new ArrayList();
        arrayList.add("TIME_FIVE_SECOND_TIMESTAMP");
        arrayList.add("TIME_FIVE_SECOND_DURATION");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.dataproducer.PowerProducer$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                PowerProducer.this.m440x37bed730(list, map);
            }
        });
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-dataproducer-PowerProducer, reason: not valid java name */
    /* synthetic */ void m440x37bed730(List list, Map map) {
        if (list.contains("TIME_FIVE_SECOND_DURATION")) {
            long longValue = ((Long) map.get("TIME_FIVE_SECOND_TIMESTAMP")).longValue();
            fillPoint();
            this.mPowerList.add(new koc(longValue, this.mPower));
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void fillList(long j, long j2) {
        LogUtil.a(TAG, "power fillList, duration", Long.valueOf(j), "timestamp", Long.valueOf(j2));
        this.mPowerList.add(0, new koc(j2, 0));
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    boolean isNeedFillList() {
        return this.mPowerList.isEmpty();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        onSaveData();
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
        fhl.d(0);
    }
}
