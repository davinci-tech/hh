package defpackage;

import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.courseplanservice.api.OnStateListener;
import com.huawei.health.courseplanservice.api.SportServiceApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.pluginfitnessadvice.FitWorkout;

@ApiDefine(uri = SportServiceApi.class)
@Singleton
/* loaded from: classes3.dex */
public class epk implements SportServiceApi {
    @Override // com.huawei.health.courseplanservice.api.SportServiceApi
    public void syncData() {
        etr.b().syncData();
    }

    @Override // com.huawei.health.courseplanservice.api.SportServiceApi
    public void startDeviceRecordSyncService(OnStateListener onStateListener) {
        etn.b(onStateListener);
    }

    @Override // com.huawei.health.courseplanservice.api.SportServiceApi
    public void setLastUseOrWatchCourse(String str, long j, FitWorkout fitWorkout) {
        eva.e(str, j, fitWorkout);
    }

    @Override // com.huawei.health.courseplanservice.api.SportServiceApi
    public void updateAll() {
        etx.b().a();
    }

    @Override // com.huawei.health.courseplanservice.api.SportServiceApi
    public FitWorkout getWorkout(String str) {
        return ett.i().l().a(str);
    }

    @Override // com.huawei.health.courseplanservice.api.SportServiceApi
    public boolean addUpdateFeedback(String str, mof mofVar) {
        return ett.i().o().d(str, mofVar);
    }

    @Override // com.huawei.health.courseplanservice.api.SportServiceApi
    public void getFilterTab(String str, UiCallback<Boolean> uiCallback) {
        etr.b().getFilterTab(str, uiCallback);
    }
}
