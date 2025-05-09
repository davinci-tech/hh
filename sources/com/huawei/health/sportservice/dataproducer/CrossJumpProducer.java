package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.fgm;
import defpackage.gvv;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 2, name = ComponentName.CROSS_JUMP_PRODUCER)
/* loaded from: classes8.dex */
public class CrossJumpProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_CrossJumpProducer";
    private long mCurrentStepTime;
    private long mDuration;
    private int mJumpGroup;
    private boolean mIsSendOverThreeTime = false;
    private boolean mIsSendOverEightTime = false;

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        super.onPreSport();
        SportDataNotify sportDataNotify = new SportDataNotify() { // from class: com.huawei.health.sportservice.dataproducer.CrossJumpProducer$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                CrossJumpProducer.this.m437xc8983139(list, map);
            }
        };
        ArrayList arrayList = new ArrayList(3);
        arrayList.add("SPORT_EXAM_SCORE");
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("STATUS_CODE_DATA");
        fgm fgmVar = new fgm();
        fgmVar.a(arrayList);
        fgmVar.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(fgmVar, sportDataNotify);
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-dataproducer-CrossJumpProducer, reason: not valid java name */
    /* synthetic */ void m437xc8983139(List list, Map map) {
        if (list == null || map == null) {
            LogUtil.h(TAG, "tagList == null || sportDataMap == null");
            return;
        }
        if (list.contains("STATUS_CODE_DATA")) {
            int e = gvv.e(map.get("STATUS_CODE_DATA"));
            if (isSporting() && e == -4000) {
                resetOverTimeJumpStatus();
            }
        }
        if (list.contains("SPORT_EXAM_SCORE")) {
            int e2 = gvv.e(map.get("SPORT_EXAM_SCORE"));
            resetOverTimeJumpStatus();
            sendGroupNumber(e2);
        }
        if (list.contains("TIME_ONE_SECOND_DURATION")) {
            if (map.get("TIME_ONE_SECOND_DURATION") != null) {
                this.mDuration = ((Long) map.get("TIME_ONE_SECOND_DURATION")).longValue();
            }
            playOverTimeJumpVoice();
        }
    }

    private void sendGroupNumber(int i) {
        if (i == 4) {
            this.mJumpGroup++;
            BaseSportManager.getInstance().stagingAndNotification("CROSS_JUMP_GROUP_DATA", Integer.valueOf(this.mJumpGroup));
        }
    }

    private boolean isSporting() {
        return BaseSportManager.getInstance().getStatus() == 1;
    }

    private void resetOverTimeJumpStatus() {
        this.mIsSendOverThreeTime = false;
        this.mIsSendOverEightTime = false;
        this.mCurrentStepTime = this.mDuration;
    }

    private void playOverTimeJumpVoice() {
        long j = this.mCurrentStepTime;
        if (j != 0 && this.mDuration - j > 8000 && !this.mIsSendOverEightTime) {
            LogUtil.a(TAG, "playOverTimeJumpVoice 8000");
            BaseSportManager.getInstance().stagingAndNotification("AI_SPORT_UI_UPDATE_SIGNAL", 8);
            this.mIsSendOverEightTime = true;
        } else {
            if (j == 0 || this.mDuration - j <= 3000 || this.mIsSendOverThreeTime) {
                return;
            }
            LogUtil.a(TAG, "playOverTimeJumpVoice 3000");
            this.mIsSendOverThreeTime = true;
            BaseSportManager.getInstance().stagingAndNotification("AI_SPORT_UI_UPDATE_SIGNAL", 3);
        }
    }
}
