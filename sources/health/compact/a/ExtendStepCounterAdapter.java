package health.compact.a;

import com.huawei.health.connectivity.extendstepcounter.ExtendStepCounter;
import com.huawei.health.icommon.ISimpleResultCallback;

/* loaded from: classes.dex */
public class ExtendStepCounterAdapter extends ExtendStepCounter {
    public ExtendStepCounterAdapter() {
        com.huawei.hwlogsmodel.LogUtil.a("Step_ExtendStepCounterAdapter", "ExtendStepCounterAdapter constructed.");
    }

    @Override // com.huawei.health.connectivity.extendstepcounter.ExtendStepCounter
    public void init(ISimpleResultCallback iSimpleResultCallback) {
        com.huawei.hwlogsmodel.LogUtil.a("Step_ExtendStepCounterAdapter", "ExtendStepCounterAdapter init.");
    }

    @Override // com.huawei.health.connectivity.extendstepcounter.ExtendStepCounter
    public void startStepCounter() {
        com.huawei.hwlogsmodel.LogUtil.a("Step_ExtendStepCounterAdapter", "ExtendStepCounterAdapter startStepCounter.");
    }

    @Override // com.huawei.health.connectivity.extendstepcounter.ExtendStepCounter
    public void stopStepCounter() {
        com.huawei.hwlogsmodel.LogUtil.a("Step_ExtendStepCounterAdapter", "ExtendStepCounterAdapter stopStepCounter.");
    }
}
