package com.huawei.health.sportservice.manager;

import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ffy;
import defpackage.gso;
import defpackage.gxn;
import defpackage.gyc;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SportComponentType(classify = 1, name = ComponentName.INTENSITY_MANAGER)
/* loaded from: classes8.dex */
public class BaseIntensityManager implements ManagerComponent {
    private static final String TAG = "SportService_BaseIntensityManager";
    private float mLastMinuteCalorie;
    private ScheduledExecutorService mSoftTimer;
    protected List<gyc> mTrackPointData = new ArrayList(16);
    protected List<gxn> mIntensityList = new ArrayList(16);
    private PluginSportTrackAdapter mPluginTrackAdapter = gso.e().c();

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        LogUtil.a(TAG, "onStartSport()");
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
        this.mSoftTimer = newScheduledThreadPool;
        newScheduledThreadPool.scheduleAtFixedRate(new SoftTimerTick(), 0L, 1L, TimeUnit.SECONDS);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        LogUtil.a(TAG, "onStopSport()");
        ScheduledExecutorService scheduledExecutorService = this.mSoftTimer;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            this.mSoftTimer = null;
        }
        savePointData();
    }

    protected void savePointData() {
        PluginSportTrackAdapter pluginSportTrackAdapter = this.mPluginTrackAdapter;
        if (pluginSportTrackAdapter != null) {
            pluginSportTrackAdapter.saveTrackPointData(this.mTrackPointData, BaseSportManager.getInstance().getSportType());
            this.mPluginTrackAdapter.insertIntensityData(this.mIntensityList);
        } else {
            ReleaseLogUtil.c(TAG, "savePointData failed with null mPluginTrackAdapter, pls check.");
        }
    }

    protected void updatePointData() {
        Object data = BaseSportManager.getInstance().getData("CALORIES_DATA");
        float floatValue = ffy.c(data) ? ((Float) data).floatValue() : 0.0f;
        float f = this.mLastMinuteCalorie;
        long currentTimeMillis = (System.currentTimeMillis() / TimeUnit.MINUTES.toMillis(1L)) * TimeUnit.MINUTES.toMillis(1L);
        gyc gycVar = new gyc();
        gycVar.e(currentTimeMillis - TimeUnit.MINUTES.toMillis(1L));
        gycVar.d(currentTimeMillis);
        gycVar.a((floatValue - f) * 1000.0f);
        this.mTrackPointData.add(gycVar);
        this.mLastMinuteCalorie = floatValue;
        gxn gxnVar = new gxn();
        gxnVar.c(currentTimeMillis - TimeUnit.MINUTES.toMillis(1L));
        gxnVar.a(currentTimeMillis);
        gxnVar.a(8);
        this.mIntensityList.add(gxnVar);
    }

    class SoftTimerTick implements Runnable {
        private long mStart = System.currentTimeMillis() / TimeUnit.MINUTES.toMillis(1);

        SoftTimerTick() {
        }

        @Override // java.lang.Runnable
        public void run() {
            long currentTimeMillis = System.currentTimeMillis() / TimeUnit.MINUTES.toMillis(1L);
            long j = this.mStart;
            if (j != currentTimeMillis) {
                LogUtil.a(BaseIntensityManager.TAG, "Time Changed old ", Long.valueOf(j), " new ", Long.valueOf(currentTimeMillis));
                BaseIntensityManager.this.updatePointData();
                this.mStart = currentTimeMillis;
            }
        }
    }
}
