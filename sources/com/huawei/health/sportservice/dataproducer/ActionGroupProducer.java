package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.CommonSegment;
import com.huawei.operation.jsoperation.JsUtil;
import defpackage.fgm;
import defpackage.fhs;
import defpackage.gvv;
import defpackage.koq;
import defpackage.kwl;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@SportComponentType(classify = 2, name = ComponentName.ACTION_GROUP_PRODUCER)
/* loaded from: classes8.dex */
public class ActionGroupProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_ActionGroupProducer";
    private int mCount;
    private long mLastDuration;
    private int mPeakPower;
    private int mPeakWeight;
    private int mActionGroup = 1;
    private int mLastActionGroup = 1;
    private final ArrayList<kwl> mSegments = new ArrayList<>();
    private final CopyOnWriteArrayList<HeartRateData> mHeartRateList = new CopyOnWriteArrayList<>();
    private final fgm mSportCallbackOption = new fgm();

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        this.mActionGroup = ((Integer) obj).intValue();
        saveSegmentData();
        this.mCount = ((Integer) BaseSportManager.getInstance().getData("GROUP_COUNT_DATA")).intValue();
        onStagingAndNotification();
    }

    private void saveSegmentData() {
        if (this.mActionGroup > this.mLastActionGroup) {
            long longValue = ((Long) BaseSportManager.getInstance().getData("TIME_ONE_SECOND_DURATION")).longValue() / 1000;
            kwl kwlVar = new kwl();
            kwlVar.d(this.mActionGroup - 1);
            kwlVar.e(longValue - this.mLastDuration);
            kwlVar.e(this.mCount);
            kwlVar.b(this.mPeakWeight);
            kwlVar.a(this.mPeakPower);
            kwlVar.c(gvv.c((ArrayList<HeartRateData>) new ArrayList(this.mHeartRateList)));
            if (!this.mSegments.contains(kwlVar)) {
                this.mSegments.add(kwlVar);
            }
            LogUtil.a(TAG, "saveSegmentData() rowingMachineSegment: ", kwlVar.toTrackString());
            this.mLastActionGroup = this.mActionGroup;
            this.mLastDuration = longValue;
            this.mPeakWeight = 0;
            this.mPeakPower = 0;
            this.mHeartRateList.clear();
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        BaseSportManager.getInstance().stagingAndNotification("ACTION_GROUP_DATA", Integer.valueOf(this.mActionGroup));
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if ((data instanceof MotionPath) && BaseSportManager.getInstance().getSportMode().equals("291")) {
            MotionPath motionPath = (MotionPath) data;
            motionPath.saveCommonSegmentList(this.mSegments);
            BaseSportManager.getInstance().stagingData(JsUtil.DataFunc.MOTION_PATH_DATA, motionPath);
        }
        Object data2 = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data2 instanceof MotionPathSimplify) {
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data2;
            int i = this.mActionGroup - 1;
            this.mActionGroup = i;
            LogUtil.a(TAG, "onSaveData() mPeakPower: ", Integer.valueOf(i));
            motionPathSimplify.addExtendDataMap("total_action_group", String.valueOf(this.mActionGroup));
            BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void recoveryData() {
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            String extendDataString = ((MotionPathSimplify) data).getExtendDataString("total_action_group");
            if (StringUtils.a(extendDataString)) {
                this.mActionGroup = Integer.parseInt(extendDataString);
            }
            LogUtil.a(TAG, "recovery actionGroup ", extendDataString);
        }
        Object data2 = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if ((data2 instanceof MotionPath) && BaseSportManager.getInstance().getSportMode().equals("291")) {
            MotionPath motionPath = (MotionPath) data2;
            if (koq.c(motionPath.requestSegmentList()) && koq.e((Object) motionPath.requestSegmentList(), kwl.class)) {
                Iterator<CommonSegment> it = motionPath.requestSegmentList().iterator();
                while (it.hasNext()) {
                    this.mSegments.add((kwl) it.next());
                }
            }
            LogUtil.a(TAG, "recovery mSegments.size() ", Integer.valueOf(this.mSegments.size()));
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().subscribeToSource("ACTION_GROUP_DATA", this);
        ArrayList arrayList = new ArrayList();
        arrayList.add("TIME_ONE_SECOND_TIMESTAMP");
        arrayList.add("WEIGHT_DATA");
        arrayList.add("POWER_DATA");
        arrayList.add("HEART_RATE_DATA");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.dataproducer.ActionGroupProducer$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                ActionGroupProducer.this.m435x2518e554(list, map);
            }
        });
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-dataproducer-ActionGroupProducer, reason: not valid java name */
    /* synthetic */ void m435x2518e554(List list, Map map) {
        int intValue = getIntValue(map.get("WEIGHT_DATA"));
        if (intValue > this.mPeakWeight) {
            this.mPeakWeight = intValue;
        }
        int intValue2 = getIntValue(map.get("POWER_DATA"));
        if (intValue2 > this.mPeakPower) {
            this.mPeakPower = intValue2;
        }
        int intValue3 = getIntValue(map.get("HEART_RATE_DATA"));
        Object data = BaseSportManager.getInstance().getData("TIME_ONE_SECOND_TIMESTAMP");
        if (data instanceof Long) {
            addHeartRateIntoList(((Long) data).longValue(), intValue3);
        }
    }

    private int getIntValue(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    private void addHeartRateIntoList(long j, int i) {
        if (fhs.c(i)) {
            this.mHeartRateList.add(new HeartRateData(j, i));
            LogUtil.a(TAG, "addHeartRateIntoList heartRate: ", Integer.valueOf(i), " time", Long.valueOf(j));
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        this.mActionGroup++;
        saveSegmentData();
        onSaveData();
        this.mSegments.clear();
    }
}
