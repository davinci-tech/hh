package com.huawei.health.sportservice.dataproducer;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import defpackage.ffw;
import defpackage.fgm;
import defpackage.fhs;
import defpackage.gvv;
import defpackage.hln;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@SportComponentType(classify = 2, name = ComponentName.HEART_RATE_PRODUCER)
/* loaded from: classes8.dex */
public class HeartRateProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_HeartRateProducer";
    private int mHeartRate;
    private CopyOnWriteArrayList<HeartRateData> mHeartRateList = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<HeartRateData> mHeartRateInvalidList = new CopyOnWriteArrayList<>();
    private fgm mSportCallbackOption = new fgm();
    private boolean mIsPauseMarker = false;
    private int mHeartPostureType = 1;
    private int mClassifyMethod = 2;

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().subscribeToSource("HEART_RATE_DATA", this);
        ArrayList arrayList = new ArrayList();
        arrayList.add("TIME_FIVE_SECOND_DURATION");
        arrayList.add("TIME_FIVE_SECOND_TIMESTAMP");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new HeartRateFiveSecond());
        initHeartZoneClassifyMethod();
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void fillList(long j, long j2) {
        LogUtil.a(TAG, "duration", Long.valueOf(j), " timestamp ", Long.valueOf(j2));
        this.mHeartRateInvalidList.add(0, new HeartRateData(j2, 0));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        this.mIsPauseMarker = true;
        onSaveData();
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        this.mHeartRate = ((Integer) obj).intValue();
        onStagingAndNotification();
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        if (BaseSportManager.getInstance().getStatus() == 2) {
            BaseSportManager.getInstance().stagingAndNotification("HEART_RATE_DATA", -1);
        } else {
            BaseSportManager.getInstance().stagingAndNotification("HEART_RATE_DATA", Integer.valueOf(this.mHeartRate));
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        LogUtil.a(TAG, "onPauseSport staging and notify invalid heart rate data");
        BaseSportManager.getInstance().stagingAndNotification("HEART_RATE_DATA", -1);
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        Object data2 = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPath) {
            MotionPath motionPath = (MotionPath) data;
            motionPath.saveHeartRateList(new ArrayList<>(this.mHeartRateList));
            motionPath.saveInvalidHeartRateList(new ArrayList<>(this.mHeartRateInvalidList));
            LogUtil.a(TAG, "onStopSport MotionPath saveHeartRateList", Integer.valueOf(this.mHeartRateList.size()));
            BaseSportManager.getInstance().stagingData(JsUtil.DataFunc.MOTION_PATH_DATA, motionPath);
            if (data2 instanceof MotionPathSimplify) {
                MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data2;
                motionPathSimplify.saveAvgHeartRate(gvv.c(motionPath.requestHeartRateList()));
                motionPathSimplify.saveMaxHeartRate(gvv.d(motionPath.requestHeartRateList()));
                motionPathSimplify.addExtendDataMap("sportHeartPosture", String.valueOf(this.mHeartPostureType));
                motionPathSimplify.saveHeartRateZoneType(this.mClassifyMethod);
                LogUtil.a(TAG, "mHeartPostureType: ", Integer.valueOf(this.mHeartPostureType), "mClassifyMethod ", Integer.valueOf(this.mClassifyMethod));
                BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
            }
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void recoveryData() {
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if (data instanceof MotionPath) {
            MotionPath motionPath = (MotionPath) data;
            if (koq.c(motionPath.requestHeartRateList())) {
                this.mHeartRateList = new CopyOnWriteArrayList<>(motionPath.requestHeartRateList());
            }
            LogUtil.a(TAG, "recovery mHeartRateList.size() ", Integer.valueOf(this.mHeartRateList.size()));
            if (koq.c(motionPath.requestInvalidHeartRateList())) {
                this.mHeartRateInvalidList = new CopyOnWriteArrayList<>(motionPath.requestInvalidHeartRateList());
            }
            LogUtil.a(TAG, "recovery mHeartRateInvalidList.size() ", Integer.valueOf(this.mHeartRateInvalidList.size()));
        }
    }

    class HeartRateFiveSecond implements SportDataNotify {
        private HeartRateFiveSecond() {
        }

        @Override // com.huawei.health.sportservice.SportDataNotify
        public void onChange(List<String> list, Map<String, Object> map) {
            if (list.contains("TIME_FIVE_SECOND_DURATION")) {
                HeartRateProducer.this.fillPoint();
                if (HeartRateProducer.this.mHeartRate >= 0) {
                    HeartRateProducer.this.addHeartRateIntoList(((Long) map.get("TIME_FIVE_SECOND_TIMESTAMP")).longValue());
                }
            }
        }
    }

    private void initHeartZoneClassifyMethod() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.sportservice.dataproducer.HeartRateProducer.1
            @Override // java.lang.Runnable
            public void run() {
                HwSportTypeInfo d = hln.c(BaseApplication.getContext()).d(BaseSportManager.getInstance().getSportType());
                HeartRateProducer.this.mHeartPostureType = d == null ? 1 : d.getHeartPostureType();
                HeartRateProducer heartRateProducer = HeartRateProducer.this;
                heartRateProducer.mClassifyMethod = ffw.a(heartRateProducer.mHeartPostureType);
                LogUtil.a(HeartRateProducer.TAG, "mHeartPostureType: ", Integer.valueOf(HeartRateProducer.this.mHeartPostureType));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addHeartRateIntoList(long j) {
        if (fhs.c(this.mHeartRate)) {
            this.mHeartRateList.add(new HeartRateData(j, this.mHeartRate));
            LogUtil.a(TAG, "addHeartRateIntoList heartRate: ", Integer.valueOf(this.mHeartRate), " time", Long.valueOf(j));
        } else {
            boolean z = this.mIsPauseMarker;
            if (z) {
                LogUtil.a(TAG, "mIsPauseMarker: ", Boolean.valueOf(z));
            } else {
                this.mHeartRateInvalidList.add(new HeartRateData(j, 0));
            }
        }
        this.mIsPauseMarker = false;
    }
}
