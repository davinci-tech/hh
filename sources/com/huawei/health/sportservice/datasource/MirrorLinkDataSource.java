package com.huawei.health.sportservice.datasource;

import android.location.Location;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.linkage.sportlinkage.DataListener;
import com.huawei.linkage.sportlinkage.LinkagePlatformApi;
import defpackage.gwe;
import defpackage.ldo;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;

@SportComponentType(classify = 3, name = ComponentName.MIRROR_LINK_SOURCE)
/* loaded from: classes8.dex */
public class MirrorLinkDataSource extends BaseSource<ldo> implements SportLifecycle {
    private static final String TAG = "SportService_MirrorLinkDataSource";
    private DataListener mDataListener;
    private LinkagePlatformApi mLinkagePlatformApi;

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return null;
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        LogUtil.a(TAG, "onPreSport");
        this.mLinkagePlatformApi = (LinkagePlatformApi) Services.a("LinkagePlatform", LinkagePlatformApi.class);
        registerRealDataListener();
    }

    private void initDataListener() {
        if (this.mDataListener == null) {
            this.mDataListener = new DataListener() { // from class: com.huawei.health.sportservice.datasource.MirrorLinkDataSource$$ExternalSyntheticLambda0
                @Override // com.huawei.linkage.sportlinkage.DataListener
                public final void onResponse(ldo ldoVar) {
                    MirrorLinkDataSource.this.m457x1080417e(ldoVar);
                }
            };
        }
    }

    /* renamed from: lambda$initDataListener$0$com-huawei-health-sportservice-datasource-MirrorLinkDataSource, reason: not valid java name */
    /* synthetic */ void m457x1080417e(ldo ldoVar) {
        if (ldoVar == null) {
            LogUtil.a(TAG, "registerRealDataListener data is null");
        } else {
            LogUtil.a(TAG, "receiver linkage data:", ldoVar.toString());
            generateData(ldoVar);
        }
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void destroy() {
        ReleaseLogUtil.b(TAG, "destroy()");
        super.destroy();
        unregisterRealDataListener();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void generateData(ldo ldoVar) {
        long n = ldoVar.n() * 1000;
        int m = ldoVar.m();
        int round = (int) Math.round((ldoVar.j() * 1.0d) / 10.0d);
        int round2 = (int) Math.round((ldoVar.i() * 1.0d) / 1000.0d);
        int c = ldoVar.c();
        int ac = ldoVar.ac();
        int ad = ldoVar.ad();
        int x = ldoVar.x();
        int b = ldoVar.b();
        int ab = ldoVar.ab();
        int t = ldoVar.t();
        int u = ldoVar.u();
        int f = ldoVar.f();
        int s = ldoVar.s();
        int g = ldoVar.g();
        int w = ldoVar.w();
        long l = ldoVar.l() * 1000;
        double p = ldoVar.p();
        double o = ldoVar.o();
        LogUtil.a(TAG, " sportDuration ", Long.valueOf(n), " heartrate ", Integer.valueOf(m), " distance ", Integer.valueOf(round), " calorie ", Integer.valueOf(round2), " descentWave ", Integer.valueOf(ac), " creepWave ", Integer.valueOf(ad), " curPace ", Integer.valueOf(x), " avgPace ", Integer.valueOf(b), " height ", Integer.valueOf(ab), " speed ", Integer.valueOf(u), " avgSpeed ", Integer.valueOf(f), " cadence ", Integer.valueOf(g), " power ", Integer.valueOf(w), " gpsTimeStamp ", Long.valueOf(l), " longitude ", Double.valueOf(p), " latitude ", Double.valueOf(o), " activeCalorie ", Integer.valueOf(c));
        BaseSportManager.getInstance().stagingAndNotification("HEART_RATE_DATA", Integer.valueOf(m));
        BaseSportManager.getInstance().stagingAndNotification("DISTANCE_DATA", Integer.valueOf(round));
        BaseSportManager.getInstance().stagingAndNotification("CALORIES_DATA", Integer.valueOf(round2));
        BaseSportManager.getInstance().stagingAndNotification("ACTIVE_CALORIES_DATA", Integer.valueOf(c));
        BaseSportManager.getInstance().stagingAndNotification("DESCENT_DATA", Integer.valueOf(ac));
        BaseSportManager.getInstance().stagingAndNotification("CREEP_DATA", Integer.valueOf(ad));
        BaseSportManager.getInstance().stagingAndNotification("PACE_DATA", Integer.valueOf(x));
        BaseSportManager.getInstance().stagingAndNotification("AVG_PACE_DATA", Integer.valueOf(b));
        BaseSportManager.getInstance().stagingAndNotification("ALTITUDE_DATA", Integer.valueOf(ab));
        BaseSportManager.getInstance().stagingAndNotification("MAX_ALTITUDE_DATA", Integer.valueOf(t));
        BaseSportManager.getInstance().stagingAndNotification("SPEED_DATA", Integer.valueOf(u));
        BaseSportManager.getInstance().stagingAndNotification("AVG_SPEED_DATA", Integer.valueOf(f));
        BaseSportManager.getInstance().stagingAndNotification("MAX_SPEED_DATA", Integer.valueOf(s));
        BaseSportManager.getInstance().stagingAndNotification("POWER_DATA", Integer.valueOf(w));
        BaseSportManager.getInstance().stagingAndNotification("CADENCE_DATA", Integer.valueOf(g));
        if (!isInvalidLocation(o, p)) {
            BaseSportManager.getInstance().stagingAndNotification("GPS_DATA", buildLocation(l, o, p));
        }
        BaseSportManager.getInstance().stagingAndNotification("TIME_ONE_SECOND_DURATION", Long.valueOf(n));
    }

    boolean isInvalidLocation(double d, double d2) {
        return gwe.a(d2) || gwe.d(d);
    }

    Location buildLocation(long j, double d, double d2) {
        Location location = new Location(GeocodeSearch.GPS);
        location.setTime(j);
        location.setLatitude(d);
        location.setLongitude(d2);
        return location;
    }

    private void registerRealDataListener() {
        if (this.mLinkagePlatformApi == null || this.mDataListener != null) {
            return;
        }
        initDataListener();
        this.mLinkagePlatformApi.registerRealDataListener(this.mDataListener);
        ReleaseLogUtil.b(TAG, "registerRealDataListener");
    }

    private void unregisterRealDataListener() {
        DataListener dataListener;
        LinkagePlatformApi linkagePlatformApi = this.mLinkagePlatformApi;
        if (linkagePlatformApi == null || (dataListener = this.mDataListener) == null) {
            return;
        }
        linkagePlatformApi.unRegisterRealDataListener(dataListener);
        ReleaseLogUtil.b(TAG, "unRegisterRealDataListener");
    }
}
