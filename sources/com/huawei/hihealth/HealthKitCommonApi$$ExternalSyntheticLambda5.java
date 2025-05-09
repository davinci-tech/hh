package com.huawei.hihealth;

import com.huawei.hihealth.listener.ResultCallback;
import com.huawei.hihealth.model.EventTypeInfo;
import com.huawei.hihealth.model.Subscriber;

/* loaded from: classes.dex */
public final /* synthetic */ class HealthKitCommonApi$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ EventTypeInfo b;
    public final /* synthetic */ Subscriber c;
    public final /* synthetic */ ResultCallback d;
    public final /* synthetic */ HealthKitCommonApi e;

    @Override // java.lang.Runnable
    public final void run() {
        this.e.c(this.d, this.c, this.b);
    }
}
