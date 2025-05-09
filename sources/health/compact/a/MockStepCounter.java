package health.compact.a;

import com.huawei.health.connectivity.extendstepcounter.ExtendStepCounter;
import com.huawei.health.icommon.ISimpleResultCallback;

/* loaded from: classes.dex */
public class MockStepCounter extends ExtendStepCounter {
    @Override // com.huawei.health.connectivity.extendstepcounter.ExtendStepCounter
    public void init(ISimpleResultCallback iSimpleResultCallback) {
        if (iSimpleResultCallback != null) {
            iSimpleResultCallback.onSuccess(null);
        }
    }

    @Override // com.huawei.health.connectivity.extendstepcounter.ExtendStepCounter
    public void startStepCounter() {
        com.huawei.hwlogsmodel.LogUtil.a("Step_MockStepCounter", "startStepCounter");
    }

    @Override // com.huawei.health.connectivity.extendstepcounter.ExtendStepCounter
    public void stopStepCounter() {
        com.huawei.hwlogsmodel.LogUtil.a("Step_MockStepCounter", "stopStepCounter");
    }
}
