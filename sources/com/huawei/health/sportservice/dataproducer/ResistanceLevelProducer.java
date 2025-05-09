package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import defpackage.fgm;
import defpackage.fhs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@SportComponentType(classify = 2, name = ComponentName.RESISTANCE_LEVEL_PRODUCER)
/* loaded from: classes8.dex */
public class ResistanceLevelProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_ResistanceLevelProducer";
    private int mResistanceLevel = -1;
    private int mIntervalFor60Second = 0;
    private final CopyOnWriteArrayList<Integer> mResistanceLevelList = new CopyOnWriteArrayList<>();
    private int mSportType = 0;
    private fgm mSportCallbackOption = new fgm();

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        if (obj instanceof Integer) {
            this.mResistanceLevel = ((Integer) obj).intValue();
            onStagingAndNotification();
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        BaseSportManager.getInstance().stagingAndNotification("RESISTANCE_LEVEL_DATA", Integer.valueOf(this.mResistanceLevel));
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if (data instanceof MotionPath) {
            MotionPath motionPath = (MotionPath) data;
            if (!fhs.e(this.mSportType) || BaseSportManager.getInstance().getSportMode().equals("291")) {
                return;
            }
            motionPath.saveResistanceList(this.mResistanceLevelList);
            LogUtil.a(TAG, "mResistanceLevelList.size()", Integer.valueOf(this.mResistanceLevelList.size()), "mResistanceLevelList= ", this.mResistanceLevelList.toString());
            BaseSportManager.getInstance().stagingData(JsUtil.DataFunc.MOTION_PATH_DATA, motionPath);
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
        if (motionPath.requestResistanceList() != null) {
            this.mResistanceLevelList.addAll(motionPath.requestResistanceList());
        }
        LogUtil.a(TAG, "recovery mResistanceLevelList size is ", Integer.valueOf(this.mResistanceLevelList.size()));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        this.mSportType = BaseSportManager.getInstance().getSportType();
        BaseSportManager.getInstance().subscribeToSource("RESISTANCE_LEVEL_DATA", this);
        ArrayList arrayList = new ArrayList();
        arrayList.add("TIME_ONE_SECOND_DURATION");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.dataproducer.ResistanceLevelProducer$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                ResistanceLevelProducer.this.m441x37d573b6(list, map);
            }
        });
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-dataproducer-ResistanceLevelProducer, reason: not valid java name */
    /* synthetic */ void m441x37d573b6(List list, Map map) {
        long longValue = ((Long) map.get("TIME_ONE_SECOND_DURATION")).longValue();
        int i = this.mIntervalFor60Second + 1;
        this.mIntervalFor60Second = i;
        if (i < 60 || this.mResistanceLevel == -1 || BaseSportManager.getInstance().getStatus() != 1 || longValue / 1000 <= this.mResistanceLevelList.size() * 60) {
            return;
        }
        this.mIntervalFor60Second = 0;
        LogUtil.a(TAG, "SportDataNotify duration = ", Long.valueOf(longValue), "  mResistanceLevel == ", Integer.valueOf(this.mResistanceLevel));
        this.mResistanceLevelList.add(Integer.valueOf(this.mResistanceLevel));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        onSaveData();
    }
}
