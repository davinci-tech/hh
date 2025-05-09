package com.huawei.health.manager;

import android.os.Handler;
import android.os.Message;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogicalStepCounter;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes.dex */
public class LogicalStepCounter$ReadStaticDealCallback implements HiAggregateListener {
    private WeakReference<LogicalStepCounter> d;
    private int e;

    public LogicalStepCounter$ReadStaticDealCallback(LogicalStepCounter logicalStepCounter, int i) {
        this.d = null;
        this.d = new WeakReference<>(logicalStepCounter);
        this.e = i;
    }

    @Override // com.huawei.hihealth.data.listener.HiAggregateListener
    public void onResult(List<HiHealthData> list, int i, int i2) {
        Handler handler;
        Handler handler2;
        Handler handler3;
        if (list == null || list.isEmpty()) {
            ReleaseLogUtil.d("Step_LSC", "ReadStaticDealCallback onResult data is null");
            return;
        }
        WeakReference<LogicalStepCounter> weakReference = this.d;
        if (weakReference != null) {
            LogicalStepCounter logicalStepCounter = weakReference.get();
            if (logicalStepCounter != null) {
                handler = logicalStepCounter.br;
                if (handler != null) {
                    handler2 = logicalStepCounter.br;
                    Message obtainMessage = handler2.obtainMessage(this.e);
                    obtainMessage.obj = list;
                    handler3 = logicalStepCounter.br;
                    handler3.sendMessage(obtainMessage);
                    return;
                }
                return;
            }
            return;
        }
        ReleaseLogUtil.d("Step_LSC", "mLogicalStepCounterRef == null");
    }

    @Override // com.huawei.hihealth.data.listener.HiAggregateListener
    public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        if (list == null) {
            LogUtil.a("Step_LSC", "onResultIntent ", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
        } else {
            LogUtil.a("Step_LSC", "onResultIntent ", Integer.valueOf(i), Integer.valueOf(list.size()), Integer.valueOf(i2), Integer.valueOf(i3));
        }
    }
}
