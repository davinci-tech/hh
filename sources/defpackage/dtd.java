package defpackage;

import android.util.Pair;
import com.huawei.basefitnessadvice.api.HeartRateControlApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.ResponseCallback;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

@ApiDefine(uri = HeartRateControlApi.class)
@Singleton
/* loaded from: classes3.dex */
public class dtd implements HeartRateControlApi {
    @Override // com.huawei.basefitnessadvice.api.HeartRateControlApi
    public void initPersonalParam(int i, ResponseCallback responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.d("R_HRControl_HeartRateControlImpl", "initPersonalParam callback is null");
        } else {
            ReleaseLogUtil.e("R_HRControl_HeartRateControlImpl", "initPersonalParam sportType = ", Integer.valueOf(i));
            dtk.b().c(i, responseCallback);
        }
    }

    @Override // com.huawei.basefitnessadvice.api.HeartRateControlApi
    public Pair<Integer, Integer> getCourseZoneHeartRateScope(Pair<Float, Float> pair, String str) {
        Pair<Float, Float> pair2;
        if (pair == null) {
            ReleaseLogUtil.d("R_HRControl_HeartRateControlImpl", "getCourseZoneHeartRateScope courseZoneScope is null");
            pair2 = new Pair<>(Float.valueOf(0.3f), Float.valueOf(0.7f));
        } else {
            pair2 = pair;
        }
        ReleaseLogUtil.e("R_HRControl_HeartRateControlImpl", "getCourseZoneHeartRateScope courseZoneScope = ", pair.toString(), ", courseZoneScopeType = ", str);
        dtk b = dtk.b();
        return dta.ZO_(b.d(), b.e(), pair2, str);
    }

    @Override // com.huawei.basefitnessadvice.api.HeartRateControlApi
    public int getCourseZoneTargetHeartRate(Pair<Integer, Integer> pair) {
        if (pair == null) {
            ReleaseLogUtil.d("R_HRControl_HeartRateControlImpl", "getCourseZoneTargetHeartRate courseZoneHeartRateScope is null");
            return 0;
        }
        ReleaseLogUtil.e("R_HRControl_HeartRateControlImpl", "getCourseZoneTargetHeartRate courseZoneHeartRateScope = ", pair.toString());
        return dta.ZP_(pair);
    }

    @Override // com.huawei.basefitnessadvice.api.HeartRateControlApi
    public float getCourseZoneTargetSpeed(int i) {
        ReleaseLogUtil.e("R_HRControl_HeartRateControlImpl", "getCourseZoneTargetSpeed courseZoneTargetHeartRate = ", Integer.valueOf(i));
        dtk b = dtk.b();
        return dte.b(i, b.c(), b.a());
    }

    @Override // com.huawei.basefitnessadvice.api.HeartRateControlApi
    public Pair<Float, Float> getCourseZoneSpeedScope(Pair<Integer, Integer> pair, float f) {
        ReleaseLogUtil.e("R_HRControl_HeartRateControlImpl", "getCourseZoneSpeedScope courseZoneHeartRateScope = ", pair, ", machineSpeedLimit = ", Float.valueOf(f));
        dtk b = dtk.b();
        return dte.ZQ_(pair, b.e(), b.c(), b.a(), f);
    }
}
