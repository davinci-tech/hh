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
import defpackage.koe;
import defpackage.koq;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@SportComponentType(classify = 2, name = ComponentName.SPEED_PRODUCER)
/* loaded from: classes8.dex */
public class SpeedProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_SpeedProducer";
    private int mPace;
    private int mSpeed;
    private final CopyOnWriteArrayList<koe> mSpeedList = new CopyOnWriteArrayList<>();
    private final LinkedList<Integer> mValidTimeList = new LinkedList<>();
    private fgm mSportCallbackOption = new fgm();

    private boolean isSupportSpeedList(int i) {
        return i == 265 || i == 273 || i == 264;
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        this.mSpeed = ((Integer) obj).intValue();
        onStagingAndNotification();
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        BaseSportManager.getInstance().stagingAndNotification("SPEED_DATA", Integer.valueOf(this.mSpeed));
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if (!(data instanceof MotionPath) || BaseSportManager.getInstance().getSportMode().equals("291")) {
            return;
        }
        MotionPath motionPath = (MotionPath) data;
        motionPath.saveSpeedList(this.mSpeedList);
        BaseSportManager.getInstance().stagingData(JsUtil.DataFunc.MOTION_PATH_DATA, motionPath);
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void recoveryData() {
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if (!(data instanceof MotionPath)) {
            LogUtil.b(TAG, "recovery failed. data not simplify type");
            return;
        }
        MotionPath motionPath = (MotionPath) data;
        if (koq.c(motionPath.requestSpeedList())) {
            this.mSpeedList.addAll(motionPath.requestSpeedList());
        }
        LogUtil.a(TAG, "recovery mSpeedList.size() ", Integer.valueOf(this.mSpeedList.size()));
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().subscribeToSource("SPEED_DATA", this);
        ArrayList arrayList = new ArrayList();
        arrayList.add("TIME_FIVE_SECOND_DURATION");
        arrayList.add("PACE_DATA");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.dataproducer.SpeedProducer$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                SpeedProducer.this.m446x7726ec72(list, map);
            }
        });
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-dataproducer-SpeedProducer, reason: not valid java name */
    /* synthetic */ void m446x7726ec72(List list, Map map) {
        if (list.contains("TIME_FIVE_SECOND_DURATION")) {
            Object obj = map.get("TIME_FIVE_SECOND_DURATION");
            fillPoint();
            if (obj instanceof Long) {
                checkValidTime((int) (((Long) obj).longValue() / 1000));
                return;
            }
            return;
        }
        if (list.contains("PACE_DATA")) {
            this.mPace = ((Integer) map.get("PACE_DATA")).intValue();
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        onSaveData();
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }

    private void checkValidTime(int i) {
        int i2 = (i / 5) * 5;
        if (this.mValidTimeList.size() == 0) {
            this.mValidTimeList.add(Integer.valueOf(i2));
            return;
        }
        if (this.mValidTimeList.size() > 2) {
            this.mValidTimeList.set(2, Integer.valueOf(i2));
        } else {
            this.mValidTimeList.add(Integer.valueOf(i2));
        }
        if (i2 - this.mValidTimeList.get(1).intValue() == 10) {
            this.mValidTimeList.set(1, Integer.valueOf(i2 - 5));
            LogUtil.a(TAG, "addDataIntoList update validTime = ", Integer.valueOf(i2));
        }
        int intValue = (this.mValidTimeList.size() <= 2 ? this.mValidTimeList.get(0) : this.mValidTimeList.get(1)).intValue();
        int sportType = BaseSportManager.getInstance().getSportType();
        if (isSupportSpeedList(sportType)) {
            this.mSpeedList.add(new koe(intValue, (this.mSpeed / 3.6f) / 10.0f));
        } else if (sportType == 274) {
            if (this.mPace <= 0) {
                LogUtil.b(TAG, "pace is zero", Integer.valueOf(intValue));
            }
            this.mSpeedList.add(new koe(intValue, 5000.0f / this.mPace));
        }
        if (this.mValidTimeList.size() > 2) {
            LinkedList<Integer> linkedList = this.mValidTimeList;
            linkedList.set(0, linkedList.get(1));
            LinkedList<Integer> linkedList2 = this.mValidTimeList;
            linkedList2.set(1, linkedList2.get(2));
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void fillList(long j, long j2) {
        LogUtil.a(TAG, "duration", Long.valueOf(j), " timestamp ", Long.valueOf(j2));
        this.mSpeedList.add(0, new koe(j / 1000, 0.0f));
    }
}
