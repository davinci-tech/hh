package defpackage;

import com.huawei.health.hearratecontrol.HeartRateControlConfigApi;
import com.huawei.health.hearratecontrol.callback.ConfigCallBack;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.pluginfitnessadvice.FitWorkout;

@ApiDefine(uri = HeartRateControlConfigApi.class)
/* loaded from: classes3.dex */
public class cze implements HeartRateControlConfigApi {
    @Override // com.huawei.health.hearratecontrol.HeartRateControlConfigApi
    public void getCourseInfoByCourseId(int i, String str, ConfigCallBack<FitWorkout> configCallBack) {
        czl.a(i, str, configCallBack);
    }

    @Override // com.huawei.health.hearratecontrol.HeartRateControlConfigApi
    public void requestGlobalConfigByType(int i, ConfigCallBack<dsy> configCallBack) {
        czl.b(i, configCallBack);
    }
}
