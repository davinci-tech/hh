package com.huawei.health.sportservice.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.healthcloud.plugintrack.manager.inteface.ITrackPointDataUpdater;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ffy;
import defpackage.fgm;
import defpackage.fhs;
import defpackage.gso;
import defpackage.gum;
import defpackage.sqs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.POINT_DATA_MANAGER)
/* loaded from: classes8.dex */
public class PointDataManager implements ManagerComponent {
    private static final String TAG = "SportService_PointDataManager";
    private PluginSportTrackAdapter mPluginTrackAdapter;
    private sqs mTreadmillStepPointData;
    private final gum mTrackPointDataUtils = new gum();
    private int mSportType = 0;
    private final fgm mSportCallbackOption = new fgm();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private ITrackPointDataUpdater mTrackPointDataUpdater = new ITrackPointDataUpdater() { // from class: com.huawei.health.sportservice.manager.PointDataManager.1
        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ITrackPointDataUpdater
        public float getActiveCalorie() {
            int intValue;
            Object data = BaseSportManager.getInstance().getData("ACTIVE_CALORIES_DATA");
            if ((data instanceof Integer) && (intValue = ((Integer) data).intValue()) > 0) {
                return (intValue * 1.0f) / 1000.0f;
            }
            if (ffy.c(BaseSportManager.getInstance().getData("CALORIES_DATA"))) {
                return ((Integer) r0).intValue();
            }
            return 0.0f;
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ITrackPointDataUpdater
        public float getDistance() {
            if (ffy.c(BaseSportManager.getInstance().getData("DISTANCE_DATA"))) {
                return ((Integer) r0).intValue();
            }
            return 0.0f;
        }
    };

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void init() {
        LogUtil.a(TAG, "init()");
        if (this.mPluginTrackAdapter == null) {
            this.mPluginTrackAdapter = gso.e().c();
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        int sportType = BaseSportManager.getInstance().getSportType();
        this.mSportType = sportType;
        if (!fhs.d(sportType)) {
            LogUtil.h(TAG, "onPreSport() is not need step");
            return;
        }
        ArrayList arrayList = new ArrayList(2);
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("STEP_DATA");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.PointDataManager$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                PointDataManager.this.m468xc3030c1d(list, map);
            }
        });
    }

    /* renamed from: lambda$onPreSport$0$com-huawei-health-sportservice-manager-PointDataManager, reason: not valid java name */
    /* synthetic */ void m468xc3030c1d(List list, Map map) {
        if (list.contains("TIME_ONE_SECOND_DURATION")) {
            updateStepValue(((Integer) map.get("STEP_DATA")).intValue());
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        LogUtil.a(TAG, "onStartSport()");
        this.mTrackPointDataUtils.d(BaseApplication.e(), false);
        this.mTrackPointDataUtils.d(this.mTrackPointDataUpdater);
        if (fhs.d(this.mSportType)) {
            LogUtil.a(TAG, "onStartSport() startStepPointData()");
            startStepPointData(BaseApplication.e());
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        LogUtil.a(TAG, "onStopSport()");
        this.mTrackPointDataUtils.e();
        if (fhs.d(this.mSportType)) {
            stopStepPointData();
        }
        if (this.mPluginTrackAdapter != null) {
            LogUtil.a(TAG, "onStopSport() saveTrackPointData");
            this.mPluginTrackAdapter.saveTrackPointData(this.mTrackPointDataUtils.c(), BaseSportManager.getInstance().getSportType());
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        LogUtil.a(TAG, "destroy()");
        this.mTrackPointDataUtils.b();
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }

    private void updateStepValue(int i) {
        sqs sqsVar = this.mTreadmillStepPointData;
        if (sqsVar != null) {
            sqsVar.c(System.currentTimeMillis(), i);
        }
    }

    private void stopStepPointData() {
        sqs sqsVar = this.mTreadmillStepPointData;
        if (sqsVar != null) {
            sqsVar.c(System.currentTimeMillis());
        }
    }

    private void startStepPointData(final Context context) {
        LogUtil.a(TAG, "startStepPointData");
        this.mHandler.post(new Runnable() { // from class: com.huawei.health.sportservice.manager.PointDataManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                PointDataManager.this.m469xe645e23a(context);
            }
        });
    }

    /* renamed from: lambda$startStepPointData$1$com-huawei-health-sportservice-manager-PointDataManager, reason: not valid java name */
    /* synthetic */ void m469xe645e23a(Context context) {
        sqs sqsVar = new sqs();
        this.mTreadmillStepPointData = sqsVar;
        sqsVar.c(context, System.currentTimeMillis());
        LogUtil.a(TAG, "startStepPointData end");
    }
}
