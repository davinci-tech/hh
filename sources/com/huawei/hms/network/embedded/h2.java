package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import java.util.Date;
import java.util.concurrent.Executor;
import org.chromium.net.NetworkQualityRttListener;

/* loaded from: classes9.dex */
public class h2 extends NetworkQualityRttListener {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5284a = "CronetNetworkQualityRtt";

    public void onRttObservation(int i, long j, int i2) {
        Logger.v(f5284a, "onRttObservation[%s]: %d, %d", new Date(j).toString(), Integer.valueOf(i), Integer.valueOf(i2));
    }

    public h2(Executor executor) {
        super(executor);
    }
}
