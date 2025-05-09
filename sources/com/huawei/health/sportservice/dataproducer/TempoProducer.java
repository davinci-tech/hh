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
import defpackage.fhl;
import defpackage.knw;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@SportComponentType(classify = 2, name = ComponentName.TEMPO_PRODUCER)
/* loaded from: classes8.dex */
public class TempoProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_TempoProducer";
    private float mTempo;
    private CopyOnWriteArrayList<knw> mTempoList = new CopyOnWriteArrayList<>();
    private fgm mSportCallbackOption = new fgm();

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        float floatValue = ((Float) obj).floatValue();
        if (floatValue < 1.0E-6d) {
            this.mTempo = 0.0f;
        } else {
            this.mTempo = fhl.b(floatValue, 1);
        }
        onStagingAndNotification();
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        BaseSportManager.getInstance().stagingAndNotification("TEMPO_DATA", Float.valueOf(this.mTempo));
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if (data instanceof MotionPath) {
            MotionPath motionPath = (MotionPath) data;
            motionPath.savePaddleFrequencyList(this.mTempoList);
            LogUtil.a(TAG, "mTempoList ", Integer.valueOf(this.mTempoList.size()));
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
        LogUtil.a(TAG, "recovery motionPath ", motionPath.toString());
        if (motionPath.requestPaddleFrequencyList() != null) {
            this.mTempoList.addAll(motionPath.requestPaddleFrequencyList());
        }
        LogUtil.a(TAG, "recovery mTempoList size is ", Integer.valueOf(this.mTempoList.size()));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().subscribeToSource("TEMPO_DATA", this);
        ArrayList arrayList = new ArrayList();
        arrayList.add("TIME_FIVE_SECOND_TIMESTAMP");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.dataproducer.TempoProducer$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                TempoProducer.this.m451x96460f46(list, map);
            }
        });
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-dataproducer-TempoProducer, reason: not valid java name */
    /* synthetic */ void m451x96460f46(List list, Map map) {
        this.mTempoList.add(new knw(((Long) map.get("TIME_FIVE_SECOND_TIMESTAMP")).longValue(), this.mTempo));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        onSaveData();
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
        fhl.d(1);
    }
}
