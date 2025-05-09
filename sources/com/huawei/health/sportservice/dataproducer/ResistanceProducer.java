package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import defpackage.lbc;
import health.compact.a.CommonUtils;

@SportComponentType(classify = 2, name = ComponentName.RESISTANCE_PRODUCER)
/* loaded from: classes8.dex */
public class ResistanceProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_ResistanceProducer";
    private int mSportType = 0;
    private SupportDataRange mSupportDataRange;

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        if (obj instanceof SupportDataRange) {
            this.mSupportDataRange = (SupportDataRange) obj;
            onStagingAndNotification();
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        BaseSportManager.getInstance().stagingAndNotification("SUPPORT_DATA_RANGE_DATA", this.mSupportDataRange);
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data;
            SupportDataRange supportDataRange = this.mSupportDataRange;
            if (supportDataRange != null && supportDataRange.getMaxLevel() != 0 && lbc.a(this.mSportType)) {
                int minLevel = this.mSupportDataRange.getMinIncrementLevel() == 0 ? this.mSupportDataRange.getMinLevel() : this.mSupportDataRange.getMinLevel() / this.mSupportDataRange.getMinIncrementLevel();
                int maxLevel = this.mSupportDataRange.getMinIncrementLevel() == 0 ? this.mSupportDataRange.getMaxLevel() : this.mSupportDataRange.getMaxLevel() / this.mSupportDataRange.getMinIncrementLevel();
                motionPathSimplify.addExtendDataMap("minRes", String.valueOf(minLevel));
                motionPathSimplify.addExtendDataMap("maxRes", String.valueOf(maxLevel));
                LogUtil.a(TAG, "onSaveData minLevel = ", Integer.valueOf(minLevel), " maxLevel = ", Integer.valueOf(maxLevel));
            }
            BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void recoveryData() {
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data;
            if (this.mSupportDataRange == null) {
                this.mSupportDataRange = new SupportDataRange();
            }
            this.mSupportDataRange.setMinLevel(CommonUtils.h(motionPathSimplify.getExtendDataString("minRes")));
            this.mSupportDataRange.setMaxLevel(CommonUtils.h(motionPathSimplify.getExtendDataString("maxRes")));
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        this.mSportType = BaseSportManager.getInstance().getSportType();
        BaseSportManager.getInstance().subscribeToSource("SUPPORT_DATA_RANGE_DATA", this);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        onSaveData();
    }
}
