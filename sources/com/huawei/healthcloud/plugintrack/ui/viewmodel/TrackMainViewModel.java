package com.huawei.healthcloud.plugintrack.ui.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import com.amap.api.location.AMapLocation;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.basesport.viewmodel.BaseSportingViewModel;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import defpackage.fhk;
import defpackage.gnm;
import defpackage.gtl;
import defpackage.gwe;
import defpackage.gwg;
import defpackage.gxv;
import defpackage.hiy;
import defpackage.hjd;
import defpackage.nsn;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class TrackMainViewModel extends BaseSportingViewModel {
    private int d = 2;
    private boolean e = true;
    private long i = SystemClock.elapsedRealtime() + TimeUnit.DAYS.toMillis(7);
    private boolean b = false;

    /* renamed from: a, reason: collision with root package name */
    private List<hiy> f3828a = new ArrayList();
    private gxv h = new gxv();
    private gtl c = new gtl(BaseApplication.e(), 0);

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initFragment() {
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            b(((Integer) message.obj).intValue());
        } else {
            if (i != 2) {
                return false;
            }
            a();
        }
        return true;
    }

    public void b(int i) {
        if (i == 3) {
            this.e = false;
        }
        if (this.d != i) {
            this.d = i;
            this.mExtendHandler.sendEmptyMessage(2);
        }
    }

    private void a() {
        if (this.d == 3 || !this.e) {
            long elapsedRealtime = this.i - SystemClock.elapsedRealtime();
            if (elapsedRealtime > TimeUnit.SECONDS.toMillis(300L)) {
                this.i = SystemClock.elapsedRealtime() + TimeUnit.SECONDS.toMillis(300L);
                elapsedRealtime = TimeUnit.SECONDS.toMillis(300L);
            }
            if (elapsedRealtime > 0) {
                postValue("deviceDisconnectTime", Integer.valueOf((int) TimeUnit.MILLISECONDS.toSeconds(elapsedRealtime)));
                if (this.mExtendHandler != null) {
                    this.mExtendHandler.removeMessages(2);
                    this.mExtendHandler.sendEmptyMessage(2, 1000L);
                    return;
                }
                return;
            }
            postValue("deviceDisconnectTime", -1);
            onStopSport("DEVICE");
            return;
        }
        postValue("deviceDisconnectTime", -1);
        this.i = SystemClock.elapsedRealtime() + TimeUnit.DAYS.toMillis(7L);
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initReceivedDataHandlers() {
        b();
        e();
        d();
    }

    private void b() {
        this.mReceivedDataHandlers.add(new BaseSportingViewModel.ReceivedDataHandler<Long>(this, "TIME_ONE_SECOND_DURATION", "allData", Long.class) { // from class: com.huawei.healthcloud.plugintrack.ui.viewmodel.TrackMainViewModel.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel.ReceivedDataHandler
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void handleInner(Long l, Map map) {
                TrackMainViewModel.this.postValue(this.mViewModelTag, fhk.axK_(map, TrackMainViewModel.this.getSportType(), TrackMainViewModel.this.b));
                if (TrackMainViewModel.this.e) {
                    return;
                }
                TrackMainViewModel.this.e = true;
            }
        });
    }

    private void d() {
        this.mReceivedDataHandlers.add(new BaseSportingViewModel.ReceivedDataHandler<Location>(this, "GPS_DATA", "allData", Location.class) { // from class: com.huawei.healthcloud.plugintrack.ui.viewmodel.TrackMainViewModel.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel.ReceivedDataHandler
            /* renamed from: bnI_, reason: merged with bridge method [inline-methods] */
            public void handleInner(Location location, Map map) {
                hjd c = gwe.c(new hjd(location.getLatitude(), location.getLongitude()), AMapLocation.COORD_TYPE_WGS84, gwg.b());
                LogUtil.a("Track_TrackMainViewModel", " receive location:", location, " list:", Integer.valueOf(TrackMainViewModel.this.f3828a.size()));
                TrackMainViewModel.this.h.a(new hiy(c.b, c.d, location.getAccuracy(), location.getSpeed(), (float) location.getAltitude(), location.getBearing(), location.getTime(), location.getProvider()));
                TrackMainViewModel.this.postValue(this.mViewModelTag, TrackMainViewModel.this.h);
            }
        });
    }

    void e() {
        this.mReceivedDataHandlers.add(new BaseSportingViewModel.ReceivedDataHandler<Integer>(this, "DEVICE_CONNECT_STATUS", "deviceDisconnectTime", Integer.class) { // from class: com.huawei.healthcloud.plugintrack.ui.viewmodel.TrackMainViewModel.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel.ReceivedDataHandler
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void handleInner(Integer num, Map map) {
                LogUtil.h(TrackMainViewModel.this.getTag(), this.mManagerDataTag, num);
                TrackMainViewModel.this.d(num.intValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        if (this.mExtendHandler != null) {
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = Integer.valueOf(i);
            this.mExtendHandler.sendMessage(obtain);
        }
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void init(Bundle bundle) {
        super.init(bundle);
        addSportDataMap("allData");
        addSportDataMap("deviceDisconnectTime");
        addSportDataMap("allData");
        this.b = getDataSource() == 100;
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport(String str) {
        if (this.mSportLifeCircleApi != null && this.mSportDataOutputApi != null && this.mSportDataOutputApi.getStatus() != 7) {
            ReleaseLogUtil.e("Track_TrackMainViewModel", "onPreSport()");
            this.mSportLifeCircleApi.onPreSport(str);
        }
        observeSportLifeCycle("gpsData", this.h);
        doStartSportBi();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onCountDown(String str) {
        if (this.mSportLifeCircleApi == null || this.mSportDataOutputApi == null || this.mSportDataOutputApi.getStatus() == 6) {
            return;
        }
        ReleaseLogUtil.e("Track_TrackMainViewModel", "onCountDown()");
        this.mSportLifeCircleApi.onCountDown(str);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport(String str) {
        if (this.mSportLifeCircleApi != null && this.mSportDataOutputApi != null && this.mSportDataOutputApi.getStatus() != 1) {
            ReleaseLogUtil.e("Track_TrackMainViewModel", "onStartSport()");
            this.mSportLifeCircleApi.onStartSport(str);
        }
        if (this.mIsFirstIn) {
            SharedPreferenceManager.e(BaseApplication.e(), Integer.toString(20002), "sport_first_start_time_" + getSportType(), String.valueOf(System.currentTimeMillis()), (StorageParams) null);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport(String str) {
        if (this.mSportLifeCircleApi == null || this.mSportDataOutputApi == null || this.mSportDataOutputApi.getStatus() != 2) {
            return;
        }
        ReleaseLogUtil.e("Track_TrackMainViewModel", "onResumeSport()");
        this.mSportLifeCircleApi.onResumeSport(str);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport(String str) {
        if (this.mSportLifeCircleApi == null || this.mSportDataOutputApi == null || this.mSportDataOutputApi.getStatus() != 1) {
            return;
        }
        ReleaseLogUtil.e("Track_TrackMainViewModel", "onPauseSport()");
        this.mSportLifeCircleApi.onPauseSport(str);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStopSport(String str) {
        if (this.mSportLifeCircleApi == null || this.mSportDataOutputApi == null || this.mSportDataOutputApi.getStatus() == 3) {
            return;
        }
        ReleaseLogUtil.e("Track_TrackMainViewModel", "onStopSport()");
        this.mSportLifeCircleApi.onStopSport(str);
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public List<String> getSubscribeTagList() {
        LogUtil.a("Track_TrackMainViewModel", "getSubscribeTagList enter ");
        ArrayList arrayList = new ArrayList();
        arrayList.add("DEVICE_CONNECT_STATUS");
        arrayList.add("DISTANCE_DATA");
        arrayList.add("TIME_ONE_SECOND_DURATION");
        arrayList.add("HEART_RATE_DATA");
        arrayList.add("CALORIES_DATA");
        arrayList.add("ACTIVE_CALORIES_DATA");
        arrayList.add("CREEP_DATA");
        arrayList.add("DESCENT_DATA");
        arrayList.add("ALTITUDE_DATA");
        arrayList.add("SPEED_DATA");
        arrayList.add("AVG_SPEED_DATA");
        arrayList.add("MAX_SPEED_DATA");
        arrayList.add("CADENCE_DATA");
        arrayList.add("POWER_DATA");
        arrayList.add("GPS_DATA");
        LogUtil.a("Track_TrackMainViewModel", "getSubscribeTagList enter ,", arrayList);
        return arrayList;
    }

    public void d(Context context) {
        if (getDataSource() == 100) {
            c(context, 259);
        }
    }

    private static void c(Context context, int i) {
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.health.MainActivity");
        if (nsn.ae(context) && (context instanceof Activity) && ((Activity) context).getWindowManager().getDefaultDisplay().getRotation() == 1) {
            intent.setFlags(131072);
        } else {
            intent.setFlags(AppRouterExtras.COLDSTART);
        }
        intent.putExtra(BleConstants.SPORT_TYPE, i);
        intent.putExtra("isToSportTab", true);
        intent.putExtra(Constants.HOME_TAB_NAME, "SPORT");
        gnm.aPB_(context, intent);
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public String getTag() {
        return "Track_TrackMainViewModel";
    }
}
