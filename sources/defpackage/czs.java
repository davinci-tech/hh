package defpackage;

import android.content.Context;
import com.huawei.btsportdevice.callback.DataLifecycle;
import com.huawei.health.device.PluginDeviceAdapter;
import com.huawei.health.sportservice.SportDataOutputApi;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;

/* loaded from: classes3.dex */
public class czs implements PluginDeviceAdapter {
    private final SportDataOutputApi d;

    private czs() {
        this.d = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
    }

    static class b {
        private static czs d = new czs();
    }

    public static czs a() {
        return b.d;
    }

    @Override // com.huawei.health.device.PluginDeviceAdapter
    public void deviceToSportStrack(Context context) {
        gso.e().c(0, 258, -1, -1.0f, null, context);
    }

    @Override // com.huawei.health.device.PluginDeviceAdapter
    public void registerStatusFromService(final DataLifecycle dataLifecycle) {
        SportDataOutputApi sportDataOutputApi;
        if (dataLifecycle == null || (sportDataOutputApi = this.d) == null || sportDataOutputApi.getDataSource() != 5) {
            return;
        }
        this.d.registerSportStatus("PluginDeviceAdapterImpl", new SportLifecycle() { // from class: czs.1
            @Override // com.huawei.health.sportservice.SportLifecycle
            public void onPreSport() {
                LogUtil.a("PluginDeviceAdapterImpl", "onPreSport ");
                DataLifecycle dataLifecycle2 = dataLifecycle;
                if (dataLifecycle2 != null) {
                    dataLifecycle2.init();
                }
            }

            @Override // com.huawei.health.sportservice.SportLifecycle
            public void onCountDown() {
                LogUtil.a("PluginDeviceAdapterImpl", "onCountDown ");
            }

            @Override // com.huawei.health.sportservice.SportLifecycle
            public void onStartSport() {
                LogUtil.a("PluginDeviceAdapterImpl", "onStartSport isRestart: " + czs.this.d.isRestart());
                if (dataLifecycle == null || czs.this.d.isRestart()) {
                    return;
                }
                dataLifecycle.onStart();
            }

            @Override // com.huawei.health.sportservice.SportLifecycle
            public void onResumeSport() {
                LogUtil.a("PluginDeviceAdapterImpl", "onResumeSport ");
                DataLifecycle dataLifecycle2 = dataLifecycle;
                if (dataLifecycle2 != null) {
                    dataLifecycle2.onResume();
                }
            }

            @Override // com.huawei.health.sportservice.SportLifecycle
            public void onPauseSport() {
                LogUtil.a("PluginDeviceAdapterImpl", "onPauseSport ");
                DataLifecycle dataLifecycle2 = dataLifecycle;
                if (dataLifecycle2 != null) {
                    dataLifecycle2.onPause();
                }
            }

            @Override // com.huawei.health.sportservice.SportLifecycle
            /* renamed from: onStopSport */
            public void m134x32b3e3a1() {
                LogUtil.a("PluginDeviceAdapterImpl", "onStopSport ");
                DataLifecycle dataLifecycle2 = dataLifecycle;
                if (dataLifecycle2 != null) {
                    dataLifecycle2.onDestroy();
                }
            }
        });
    }

    @Override // com.huawei.health.device.PluginDeviceAdapter
    public void unregisterStatusFromService() {
        SportDataOutputApi sportDataOutputApi = this.d;
        if (sportDataOutputApi == null || sportDataOutputApi.getDataSource() != 5) {
            return;
        }
        this.d.unRegisterSportStatus("PluginDeviceAdapterImpl");
    }

    @Override // com.huawei.health.device.PluginDeviceAdapter
    public void checkAutoTrackStatus() {
        gso.e();
        gso.a(true);
    }
}
