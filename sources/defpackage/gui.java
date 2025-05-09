package defpackage;

import android.os.Bundle;
import com.huawei.health.sportservice.SportComponent;
import com.huawei.health.sportservice.SportDataOutputApi;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.healthcloud.plugintrack.model.SportBeat;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class gui implements SportComponent {
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private final SportDataOutputApi f12941a = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
    protected SportLifecycle c = (SportLifecycle) Services.a("SportService", SportLifecycle.class);
    private SportLaunchParams e;

    @Override // com.huawei.health.sportservice.SportComponent
    public void init() {
        gtx c = gtx.c(BaseApplication.getContext());
        d(c.n(), c.z());
        if (!this.f12941a.isAlreadyInit()) {
            this.f12941a.initModel(aUi_());
        } else if (this.e != null) {
            this.f12941a.setParas(SportParamsType.SPORT_LAUNCH_PARAS, this.e);
        }
    }

    protected void d(int i, int i2) {
        SportLaunchParams sportLaunchParams = new SportLaunchParams();
        this.e = sportLaunchParams;
        sportLaunchParams.setSportType(i);
        this.e.setDataSource(i2);
        this.e.setNeedRestart(true);
        ReleaseLogUtil.e("Track_SportServiceHelper", "mSportLaunchParams=", this.e);
    }

    public void a(SportBeat sportBeat) {
        this.f12941a.setParas(SportParamsType.SPORT_BEAT_CHANGE, sportBeat);
    }

    private Bundle aUi_() {
        Bundle bundle = new Bundle();
        SportLaunchParams sportLaunchParams = this.e;
        if (sportLaunchParams != null) {
            bundle.putParcelable("bundle_key_sport_launch_paras", sportLaunchParams);
        }
        return bundle;
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        if (this.f12941a.getStatus() != 7) {
            ReleaseLogUtil.e("Track_SportServiceHelper", "onPreSport()");
            this.c.onPreSport();
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onCountDown() {
        if (this.f12941a.getStatus() != 6) {
            ReleaseLogUtil.e("Track_SportServiceHelper", "onCountDown()");
            this.c.onCountDown();
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        if (this.f12941a.getStatus() != 1) {
            ReleaseLogUtil.e("Track_SportServiceHelper", "onStartSport()");
            this.c.onStartSport();
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        if (this.f12941a.getStatus() == 2) {
            ReleaseLogUtil.e("Track_SportServiceHelper", "onResumeSport()");
            this.c.onResumeSport();
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        if (this.f12941a.getStatus() == 1) {
            ReleaseLogUtil.e("Track_SportServiceHelper", "onPauseSport()");
            this.c.onPauseSport();
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        if (this.f12941a.getStatus() != 3) {
            ReleaseLogUtil.e("Track_SportServiceHelper", "onStopSport()");
            this.c.m134x32b3e3a1();
        }
    }

    @Override // com.huawei.health.sportservice.SportComponent
    public void destroy() {
        this.f12941a.onDestroy();
        this.f12941a.destroyModel();
    }
}
